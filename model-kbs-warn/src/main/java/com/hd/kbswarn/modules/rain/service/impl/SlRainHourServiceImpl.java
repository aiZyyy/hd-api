package com.hd.kbswarn.modules.rain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHour;
import com.hd.kbswarn.modules.rain.mapper.SlRainHourMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainHourService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 小时降雨量统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Service
public class SlRainHourServiceImpl extends ServiceImpl<SlRainHourMapper, SlRainHour> implements ISlRainHourService {

    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRainHour> rainHourQueryWrapper = new QueryWrapper<>();
        rainHourQueryWrapper.lambda().eq(SlRainHour::getStcd, stcd).between(SlRainHour::getTm, tm_begin, tm_end).orderByAsc(SlRainHour::getTm);
        List<SlRainHour> slRainHours = this.baseMapper.selectList(rainHourQueryWrapper);
        return Result.OK(slRainHours);
    }
}
