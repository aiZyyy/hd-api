package com.hd.kbswarn.modules.rain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainDay;
import com.hd.kbswarn.modules.rain.mapper.SlRainDayMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainDayService;
import com.hd.common.api.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 日降雨量统计
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Service
public class SlRainDayServiceImpl extends ServiceImpl<SlRainDayMapper, SlRainDay> implements ISlRainDayService {

    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRainDay> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SlRainDay::getStcd, stcd).between(SlRainDay::getTm, tm_begin, tm_end).orderByAsc(SlRainDay::getTm);
        List<SlRainDay> selectList = this.baseMapper.selectList(queryWrapper);
        return Result.OK(selectList);
    }
}
