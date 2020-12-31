package com.hd.kbswarn.modules.rain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainDay;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHour;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainYear;
import com.hd.kbswarn.modules.rain.domain.vo.RainDayHourVo;
import com.hd.kbswarn.modules.rain.domain.vo.RainMonthDayVo;
import com.hd.kbswarn.modules.rain.domain.vo.RainYearMonthVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 雨情数据统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Repository
public interface SlRainStatisticalMapper extends BaseMapper<SlRainHour> {

    IPage<RainDayHourVo> stDayTotal(Page<RainDayHourVo> page, @Param("item") HashMap<String, Object> map);

    List<SlRainHour> selectHours(@Param("item") HashMap<String, Object> map);

    IPage<RainMonthDayVo> stMonthTotal(Page<RainMonthDayVo> dayVoPage, @Param("item") HashMap<String, Object> stMonthMap);

    List<SlRainDay> selectDays(@Param("item") HashMap<String, Object> stMonthMap);

    IPage<RainYearMonthVo> stYearTotal(Page<Object> objectPage, @Param("item") HashMap<String, Object> stYearMap);

    List<SlRainYear> selectMonths(@Param("item") HashMap<String, Object> stYearMap);
}
