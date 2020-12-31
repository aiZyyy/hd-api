package com.hd.kbswarn.modules.rsvr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlThresholdRsvr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 水库水情阈值
 * @Author: FishCoder
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Repository
public interface SlThresholdRsvrMapper extends BaseMapper<SlThresholdRsvr> {

}
