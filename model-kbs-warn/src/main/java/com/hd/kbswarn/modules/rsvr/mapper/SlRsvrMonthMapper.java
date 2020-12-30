package com.hd.kbswarn.modules.rsvr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrMonth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 水库水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Repository
public interface SlRsvrMonthMapper extends BaseMapper<SlRsvrMonth> {

}
