package com.hd.kbswarn.modules.river.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverCur;
import com.hd.kbswarn.modules.river.domain.vo.RiverCurListVo;
import com.hd.kbswarn.modules.river.domain.vo.RiverCurVo;
import com.hd.kbswarn.modules.river.mapper.SlRiverCurMapper;
import com.hd.kbswarn.modules.river.service.ISlRiverCurService;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;
import com.hd.kbswarn.modules.station.mapper.SlStInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.hd.kbswarn.modules.common.constant.DictConstant.RIVER_STATION_TYPE;

/**
 * @Description: 河道水情实时表
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Service
public class SlRiverCurServiceImpl extends ServiceImpl<SlRiverCurMapper, SlRiverCur> implements ISlRiverCurService {

    @Autowired
    private SlRiverCurMapper slRiverCurMapper;

    @Autowired
    private SlStInfoMapper slStInfoMapper;

    @Override
    public Result<?> homeList(HttpServletRequest req) {
        String column = req.getParameter("column");
        String order = req.getParameter("order");
        HashMap<String, Object> map = new HashMap<>();
        map.put("column", column);
        map.put("order", order);
        List<RiverCurListVo> riverCurListVos = slRiverCurMapper.homeList(map);
        Integer zWarnLevel1Count = 0;
        Integer zWarnLevel2Count = 0;
        Integer zWarnLevel3Count = 0;
        Integer qWarnLevel1Count = 0;
        Integer qWarnLevel2Count = 0;
        Integer qWarnLevel3Count = 0;
        for (RiverCurListVo item : riverCurListVos) {
            //获取预警等级
            BigDecimal z = item.getZ();
            BigDecimal zLevel1 = item.getZLevel1();
            BigDecimal zLevel2 = item.getZLevel2();
            BigDecimal zLevel3 = item.getZLevel3();
            //水位不为空
            if (Objects.nonNull(z)) {
                //阈值不为零
                if (Objects.nonNull(zLevel1) && Objects.nonNull(zLevel2) && Objects.nonNull(zLevel3)) {
                    //水位小于一级阈值
                    if (z.compareTo(zLevel1) == -1) {
                        item.setZWarnLevel(0);
                    } else if ((z.compareTo(zLevel1) == 1 && z.compareTo(zLevel2) == -1) || z.compareTo(zLevel1) == 0) {
                        item.setZWarnLevel(1);
                        zWarnLevel1Count++;
                    } else if ((z.compareTo(zLevel2) == 1 && z.compareTo(zLevel3) == -1) || z.compareTo(zLevel2) == 0) {
                        item.setZWarnLevel(2);
                        zWarnLevel2Count++;
                    } else if ((z.compareTo(zLevel3) == 1) || z.compareTo(zLevel3) == 0) {
                        item.setZWarnLevel(3);
                        zWarnLevel3Count++;
                    }
                }
            }
            //获取预警等级
            BigDecimal q = item.getQ();
            BigDecimal qLevel1 = item.getQLevel1();
            BigDecimal qLevel2 = item.getQLevel2();
            BigDecimal qLevel3 = item.getQLevel3();
            //流量不为空
            if (Objects.nonNull(q)) {
                //阈值不为零
                if (Objects.nonNull(qLevel1) && Objects.nonNull(qLevel2) && Objects.nonNull(qLevel3)) {
                    //流量小于一级阈值
                    if (q.compareTo(qLevel1) == -1) {
                        item.setQWarnLevel(0);
                    } else if ((q.compareTo(qLevel1) == 1 && q.compareTo(qLevel2) == -1) || q.compareTo(qLevel1) == 0) {
                        item.setQWarnLevel(1);
                        qWarnLevel1Count++;
                    } else if ((q.compareTo(qLevel2) == 1 && q.compareTo(qLevel3) == -1) || q.compareTo(qLevel2) == 0) {
                        item.setQWarnLevel(2);
                        qWarnLevel2Count++;
                    } else if ((q.compareTo(qLevel3) == 1) || q.compareTo(qLevel3) == 0) {
                        item.setQWarnLevel(3);
                        qWarnLevel3Count++;
                    }
                }
            }
        }
        Integer zWarnCount = zWarnLevel1Count + zWarnLevel2Count + zWarnLevel3Count;
        Integer qWarnCount = qWarnLevel1Count + qWarnLevel2Count + qWarnLevel3Count;
        QueryWrapper<SlStInfo> stInfoQueryWrapper = new QueryWrapper<>();
        stInfoQueryWrapper.lambda().eq(SlStInfo::getSttp, RIVER_STATION_TYPE);
        Integer stCount = slStInfoMapper.selectCount(stInfoQueryWrapper);
        RiverCurVo riverCurVo = RiverCurVo.builder().stCount(stCount).riverCurListVos(riverCurListVos)
                .zWarnCount(zWarnCount)
                .qWarnCount(qWarnCount)
                .zWarnLevel1Count(zWarnLevel1Count)
                .zWarnLevel2Count(zWarnLevel2Count)
                .zWarnLevel3Count(zWarnLevel3Count)
                .qWarnLevel1Count(qWarnLevel1Count)
                .qWarnLevel2Count(qWarnLevel2Count)
                .qWarnLevel3Count(qWarnLevel3Count)
                .build();
        return Result.OK(riverCurVo);
    }

}
