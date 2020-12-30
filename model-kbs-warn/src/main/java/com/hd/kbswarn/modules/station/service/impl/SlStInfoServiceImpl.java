package com.hd.kbswarn.modules.station.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;
import com.hd.kbswarn.modules.station.mapper.SlStInfoMapper;
import com.hd.kbswarn.modules.station.service.ISlStInfoService;
import com.hd.common.api.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 测站基本属性
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Service
public class SlStInfoServiceImpl extends ServiceImpl<SlStInfoMapper, SlStInfo> implements ISlStInfoService {

    @Override
    public Result<?> listBySttp(String sttp) {
        QueryWrapper<SlStInfo> stInfoQueryWrapper = new QueryWrapper<>();
        stInfoQueryWrapper.lambda().eq(SlStInfo::getSttp, sttp);
        List<SlStInfo> slStInfos = this.baseMapper.selectList(stInfoQueryWrapper);
        return Result.OK(slStInfos);
    }
}
