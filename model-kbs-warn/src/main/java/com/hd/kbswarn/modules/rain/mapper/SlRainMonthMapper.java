package com.hd.kbswarn.modules.rain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainMonth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 月降雨量统计
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Repository
public interface SlRainMonthMapper extends BaseMapper<SlRainMonth> {

}
