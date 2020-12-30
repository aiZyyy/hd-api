package com.hd.kbswarn.modules.rsvr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrHis;
import com.hd.kbswarn.modules.rsvr.mapper.SlRsvrHisMapper;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrHisService;
import com.hd.common.api.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 水库水情历史
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Service
public class SlRsvrHisServiceImpl extends ServiceImpl<SlRsvrHisMapper, SlRsvrHis> implements ISlRsvrHisService {

    @Override
    public Result<?> listByStcd(String stcd, String tm_begin, String tm_end) {
        QueryWrapper<SlRsvrHis> riverHisQueryWrapper = new QueryWrapper<>();
        riverHisQueryWrapper.lambda().eq(SlRsvrHis::getStcd, stcd).between(SlRsvrHis::getTm, tm_begin, tm_end).orderByAsc(SlRsvrHis::getTm);
        List<SlRsvrHis> slRsvrHisList = this.baseMapper.selectList(riverHisQueryWrapper);
        return Result.OK(slRsvrHisList);
    }
}
