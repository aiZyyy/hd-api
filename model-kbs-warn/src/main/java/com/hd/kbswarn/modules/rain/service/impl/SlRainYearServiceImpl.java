package com.hd.kbswarn.modules.rain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainYear;
import com.hd.kbswarn.modules.rain.mapper.SlRainYearMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainYearService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 年降雨量
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Service
public class SlRainYearServiceImpl extends ServiceImpl<SlRainYearMapper, SlRainYear> implements ISlRainYearService {

    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRainYear> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SlRainYear::getStcd, stcd).between(SlRainYear::getTm, tm_begin, tm_end).orderByAsc(SlRainYear::getTm);
        List<SlRainYear> selectList = this.baseMapper.selectList(queryWrapper);
        return Result.OK(selectList);
    }
}
