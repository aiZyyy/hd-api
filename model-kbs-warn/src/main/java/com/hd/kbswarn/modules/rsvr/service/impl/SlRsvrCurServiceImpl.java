package com.hd.kbswarn.modules.rsvr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrCur;
import com.hd.kbswarn.modules.rsvr.domain.vo.RsvrCurListVo;
import com.hd.kbswarn.modules.rsvr.domain.vo.RsvrCurVo;
import com.hd.kbswarn.modules.rsvr.mapper.SlRsvrCurMapper;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrCurService;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;
import com.hd.kbswarn.modules.station.mapper.SlStInfoMapper;
import com.hd.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.hd.kbswarn.modules.common.constant.DictConstant.RSVR_STATION_TYPE;

/**
 * @Description: 水库水情实时
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Service
public class SlRsvrCurServiceImpl extends ServiceImpl<SlRsvrCurMapper, SlRsvrCur> implements ISlRsvrCurService {

    @Autowired
    private SlRsvrCurMapper slRsvrCurMapper;

    @Autowired
    private SlStInfoMapper slStInfoMapper;

    @Override
    public Result<?> homeList(HttpServletRequest req) {
        String column = req.getParameter("column");
        String order = req.getParameter("order");
        HashMap<String, Object> map = new HashMap<>();
        map.put("column", column);
        map.put("order", order);
        List<RsvrCurListVo> rsvrCurListVos = slRsvrCurMapper.homeList(map);
        Integer rzWarnLevel1Count = 0;
        Integer rzWarnLevel2Count = 0;
        Integer rzWarnLevel3Count = 0;
        Integer blrzWarnLevel1Count = 0;
        Integer blrzWarnLevel2Count = 0;
        Integer blrzWarnLevel3Count = 0;
        Integer inqWarnLevel1Count = 0;
        Integer inqWarnLevel2Count = 0;
        Integer inqWarnLevel3Count = 0;
        Integer otqWarnLevel1Count = 0;
        Integer otqWarnLevel2Count = 0;
        Integer otqWarnLevel3Count = 0;
        for (RsvrCurListVo item : rsvrCurListVos) {
            //获取库上水位预警等级
            BigDecimal rz = item.getRz();
            BigDecimal rzLevel1 = item.getRzLevel1();
            BigDecimal rzLevel2 = item.getRzLevel2();
            BigDecimal rzLevel3 = item.getRzLevel3();
            if (Objects.nonNull(rz)) {
                //阈值不为零
                if (Objects.nonNull(rzLevel1) && Objects.nonNull(rzLevel2) && Objects.nonNull(rzLevel3)) {
                    if (rz.compareTo(rzLevel1) == -1) {
                        item.setRzWarnLevel(0);
                    } else if ((rz.compareTo(rzLevel1) == 1 && rz.compareTo(rzLevel2) == -1) || rz.compareTo(rzLevel1) == 0) {
                        item.setRzWarnLevel(1);
                        rzWarnLevel1Count++;
                    } else if ((rz.compareTo(rzLevel2) == 1 && rz.compareTo(rzLevel3) == -1) || rz.compareTo(rzLevel2) == 0) {
                        item.setRzWarnLevel(2);
                        rzWarnLevel2Count++;
                    } else if ((rz.compareTo(rzLevel3) == 1) || rz.compareTo(rzLevel3) == 0) {
                        item.setRzWarnLevel(3);
                        rzWarnLevel3Count++;
                    }
                }
            }
            //库下水位设置
            BigDecimal blrz = item.getBlrz();
            BigDecimal blrzLevel1 = item.getBlrzLevel1();
            BigDecimal blrzLevel2 = item.getBlrzLevel2();
            BigDecimal blrzLevel3 = item.getBlrzLevel3();
            if (Objects.nonNull(blrz)) {
                //阈值不为零
                if (Objects.nonNull(blrzLevel1) && Objects.nonNull(blrzLevel2) && Objects.nonNull(blrzLevel3)) {
                    if (blrz.compareTo(blrzLevel1) == -1) {
                        item.setBlrzWarnLevel(0);
                    } else if ((blrz.compareTo(blrzLevel1) == 1 && blrz.compareTo(blrzLevel2) == -1) || blrz.compareTo(blrzLevel1) == 0) {
                        item.setBlrzWarnLevel(1);
                        blrzWarnLevel1Count++;
                    } else if ((blrz.compareTo(blrzLevel2) == 1 && blrz.compareTo(blrzLevel3) == -1) || blrz.compareTo(blrzLevel2) == 0) {
                        item.setBlrzWarnLevel(2);
                        blrzWarnLevel2Count++;
                    } else if ((blrz.compareTo(blrzLevel3) == 1) || blrz.compareTo(blrzLevel3) == 0) {
                        item.setBlrzWarnLevel(3);
                        blrzWarnLevel3Count++;
                    }
                }
            }
            //入库流量设置
            BigDecimal inq = item.getInq();
            BigDecimal inqLevel1 = item.getInqLevel1();
            BigDecimal inqLevel2 = item.getInqLevel2();
            BigDecimal inqLevel3 = item.getInqLevel3();
            if (Objects.nonNull(inq)) {
                //阈值不为零
                if (Objects.nonNull(inqLevel1) && Objects.nonNull(inqLevel2) && Objects.nonNull(inqLevel3)) {
                    if (inq.compareTo(inqLevel1) == -1) {
                        item.setInqWarnLevel(0);
                    } else if ((inq.compareTo(inqLevel1) == 1 && inq.compareTo(inqLevel2) == -1) || inq.compareTo(inqLevel1) == 0) {
                        item.setInqWarnLevel(1);
                        inqWarnLevel1Count++;
                    } else if ((inq.compareTo(inqLevel2) == 1 && inq.compareTo(inqLevel3) == -1) || inq.compareTo(inqLevel2) == 0) {
                        item.setInqWarnLevel(2);
                        inqWarnLevel2Count++;
                    } else if ((inq.compareTo(inqLevel3) == 1) || inq.compareTo(inqLevel3) == 0) {
                        item.setInqWarnLevel(3);
                        inqWarnLevel3Count++;
                    }
                }
            }
            //出库流量设置
            BigDecimal otq = item.getOtq();
            BigDecimal otqLevel1 = item.getOtqLevel1();
            BigDecimal otqLevel2 = item.getOtqLevel2();
            BigDecimal otqLevel3 = item.getOtqLevel3();
            if (Objects.nonNull(otq)) {
                //阈值不为零
                if (Objects.nonNull(otqLevel1) && Objects.nonNull(otqLevel2) && Objects.nonNull(otqLevel3)) {
                    if (otq.compareTo(otqLevel1) == -1) {
                        item.setOtqWarnLevel(0);
                    } else if ((otq.compareTo(otqLevel1) == 1 && otq.compareTo(otqLevel2) == -1) || otq.compareTo(otqLevel1) == 0) {
                        item.setOtqWarnLevel(1);
                        otqWarnLevel1Count++;
                    } else if ((otq.compareTo(otqLevel2) == 1 && otq.compareTo(otqLevel3) == -1) || otq.compareTo(otqLevel2) == 0) {
                        item.setOtqWarnLevel(2);
                        otqWarnLevel2Count++;
                    } else if ((otq.compareTo(otqLevel3) == 1) || otq.compareTo(otqLevel3) == 0) {
                        item.setOtqWarnLevel(3);
                        otqWarnLevel3Count++;
                    }
                }
            }
        }
        QueryWrapper<SlStInfo> stInfoQueryWrapper = new QueryWrapper<>();
        stInfoQueryWrapper.lambda().eq(SlStInfo::getSttp, RSVR_STATION_TYPE);
        Integer stCount = slStInfoMapper.selectCount(stInfoQueryWrapper);
        Integer rzWarnCount = rzWarnLevel1Count + rzWarnLevel2Count + rzWarnLevel3Count;
        Integer blrzWarnCount = blrzWarnLevel1Count + blrzWarnLevel2Count + blrzWarnLevel3Count;
        Integer inqWarnCount = inqWarnLevel1Count + inqWarnLevel2Count + inqWarnLevel3Count;
        Integer otqWarnCount = otqWarnLevel1Count + otqWarnLevel2Count + otqWarnLevel3Count;
        RsvrCurVo rsvrCurVo = RsvrCurVo.builder().rsvrCurListVos(rsvrCurListVos)
                .stCount(stCount)
                .rzWarnCount(rzWarnCount)
                .rzWarnLevel1Count(rzWarnLevel1Count)
                .rzWarnLevel2Count(rzWarnLevel2Count)
                .rzWarnLevel3Count(rzWarnLevel3Count)
                .blrzWarnCount(blrzWarnCount)
                .blrzWarnLevel1Count(blrzWarnLevel1Count)
                .blrzWarnLevel2Count(blrzWarnLevel2Count)
                .blrzWarnLevel3Count(blrzWarnLevel3Count)
                .inqWarnCount(inqWarnCount)
                .inqWarnLevel1Count(inqWarnLevel1Count)
                .inqWarnLevel2Count(inqWarnLevel2Count)
                .inqWarnLevel3Count(inqWarnLevel3Count)
                .otqWarnCount(otqWarnCount)
                .otqWarnLevel1Count(otqWarnLevel1Count)
                .otqWarnLevel2Count(otqWarnLevel2Count)
                .otqWarnLevel3Count(otqWarnLevel3Count).build();
        return Result.OK(rsvrCurVo);
    }
}
