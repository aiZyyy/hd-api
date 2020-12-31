package com.hd.kbswarn.modules.river.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverHis;
import com.hd.kbswarn.modules.river.mapper.SlRiverHisMapper;
import com.hd.kbswarn.modules.river.service.ISlRiverHisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 河道水情历史表
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Service
public class SlRiverHisServiceImpl extends ServiceImpl<SlRiverHisMapper, SlRiverHis> implements ISlRiverHisService {

    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRiverHis> riverHisQueryWrapper = new QueryWrapper<>();
        riverHisQueryWrapper.lambda().eq(SlRiverHis::getStcd, stcd).between(SlRiverHis::getTm, tm_begin, tm_end).orderByAsc(SlRiverHis::getTm);
        List<SlRiverHis> slRiverHisList = this.baseMapper.selectList(riverHisQueryWrapper);
        return Result.OK(slRiverHisList);
    }
}
