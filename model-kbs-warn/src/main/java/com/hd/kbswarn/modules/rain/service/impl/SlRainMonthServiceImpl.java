package com.hd.kbswarn.modules.rain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainMonth;
import com.hd.kbswarn.modules.rain.mapper.SlRainMonthMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainMonthService;
import com.hd.common.api.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 月降雨量统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Service
public class SlRainMonthServiceImpl extends ServiceImpl<SlRainMonthMapper, SlRainMonth> implements ISlRainMonthService {
    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRainMonth> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SlRainMonth::getStcd, stcd).between(SlRainMonth::getTm, tm_begin, tm_end).orderByAsc(SlRainMonth::getTm);
        List<SlRainMonth> selectList = this.baseMapper.selectList(queryWrapper);
        return Result.OK(selectList);
    }
}
