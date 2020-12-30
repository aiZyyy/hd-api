package com.hd.kbswarn.modules.rain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.common.utils.WarnLevelUtil;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainCur;
import com.hd.kbswarn.modules.rain.domain.vo.RainCurListVo;
import com.hd.kbswarn.modules.rain.domain.vo.RainCurVo;
import com.hd.kbswarn.modules.rain.mapper.SlRainCurMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainCurService;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;
import com.hd.kbswarn.modules.station.mapper.SlStInfoMapper;
import com.hd.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.hd.kbswarn.modules.common.constant.DictConstant.RAIN_STATION_TYPE;

/**
 * @Description: 降雨实时
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Service
public class SlRainCurServiceImpl extends ServiceImpl<SlRainCurMapper, SlRainCur> implements ISlRainCurService {

    @Autowired
    private SlRainCurMapper slRainCurMapper;

    @Autowired
    private SlStInfoMapper slStInfoMapper;

    @Override
    public Result<?> homeList(HttpServletRequest req) {
        String column = req.getParameter("column");
        String order = req.getParameter("order");
        HashMap<String, Object> map = new HashMap<>();
        map.put("column", column);
        map.put("order", order);
        List<RainCurListVo> rainCurListVos = slRainCurMapper.homeList(map);
        Integer rainWarnLevel1Count = 0;
        Integer rainWarnLevel2Count = 0;
        Integer rainWarnLevel3Count = 0;
        for (RainCurListVo item : rainCurListVos) {
            Integer warnLevel1 = WarnLevelUtil.getWarnLevel(item.getH1(), item.getH1Level1(), item.getH1Level2(), item.getH1Level3());
            Integer warnLevel3 = WarnLevelUtil.getWarnLevel(item.getH3(), item.getH3Level1(), item.getH3Level2(), item.getH3Level3());
            Integer warnLevel6 = WarnLevelUtil.getWarnLevel(item.getH6(), item.getH6Level1(), item.getH6Level2(), item.getH6Level3());
            Integer warnLevel12 = WarnLevelUtil.getWarnLevel(item.getH12(), item.getH12Level1(), item.getH12Level2(), item.getH12Level3());
            Integer warnLevel24 = WarnLevelUtil.getWarnLevel(item.getH24(), item.getH24Level1(), item.getH24Level2(), item.getH24Level3());
            List<Integer> warnList = Arrays.asList(warnLevel1, warnLevel3, warnLevel6, warnLevel12, warnLevel24);
            //5个预警等级至少有一个不为空
            if (Objects.nonNull(warnLevel1) || Objects.nonNull(warnLevel3) || Objects.nonNull(warnLevel6) || Objects.nonNull(warnLevel12) || Objects.nonNull(warnLevel24)) {
                Integer integer = warnList.stream().filter(Objects::nonNull).max(Integer::compareTo).get();
                item.setRainWarnLevel(integer);
                switch (integer) {
                    case 1:
                        rainWarnLevel1Count++;
                        break;
                    case 2:
                        rainWarnLevel2Count++;
                        break;
                    case 3:
                        rainWarnLevel3Count++;
                        break;
                }
            }
        }
        Integer rainWarnCount = rainWarnLevel1Count + rainWarnLevel2Count + rainWarnLevel3Count;
        QueryWrapper<SlStInfo> stInfoQueryWrapper = new QueryWrapper<>();
        stInfoQueryWrapper.lambda().eq(SlStInfo::getSttp, RAIN_STATION_TYPE);
        Integer stCount = slStInfoMapper.selectCount(stInfoQueryWrapper);
        RainCurVo rainCurVo = RainCurVo.builder().rainCurListVos(rainCurListVos)
                .rainWarnCount(rainWarnCount)
                .rainWarnLevel1Count(rainWarnLevel1Count)
                .rainWarnLevel2Count(rainWarnLevel2Count)
                .rainWarnLevel3Count(rainWarnLevel3Count)
                .stCount(stCount)
                .build();
        return Result.OK(rainCurVo);
    }

}
