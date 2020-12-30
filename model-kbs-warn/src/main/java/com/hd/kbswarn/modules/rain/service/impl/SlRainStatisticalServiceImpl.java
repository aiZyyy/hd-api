package com.hd.kbswarn.modules.rain.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainDay;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHour;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainYear;
import com.hd.kbswarn.modules.rain.domain.vo.RainDayHourVo;
import com.hd.kbswarn.modules.rain.domain.vo.RainMonthDayVo;
import com.hd.kbswarn.modules.rain.domain.vo.RainYearMonthVo;
import com.hd.kbswarn.modules.rain.mapper.SlRainStatisticalMapper;
import com.hd.kbswarn.modules.rain.service.ISlRainStatisticalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import com.hd.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 雨情数据统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Service
@Slf4j
public class SlRainStatisticalServiceImpl implements ISlRainStatisticalService {

    @Autowired
    private SlRainStatisticalMapper slRainStatisticalMapper;

    @Override
    public Result<?> hourStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize) {
        //2020-11-24 00:00:00
        String time = req.getParameter("time");
        if (Strings.isBlank(time)) {
            return Result.error("时间不可为空!");
        }
        DateTime dateTime = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
        //获取测站信息
        HashMap<String, Object> stDayMap = new HashMap<>();
        stDayMap.put("time", time);
        IPage<RainDayHourVo> hourVoIPage = slRainStatisticalMapper.stDayTotal(new Page<>(pageNo, pageSize), stDayMap);
        if (0 != hourVoIPage.getTotal()) {
            List<RainDayHourVo> records = hourVoIPage.getRecords();
            records.forEach(item -> {
                String stcd = item.getStcd();
                BigDecimal dayTotal = item.getDayTotal();
                //如果日累计为零,小时都为0
                if (Objects.nonNull(dayTotal)) {
                    if (BigDecimal.ZERO.compareTo(dayTotal) == 0) {
                        for (int i = 0; i < 24; i++) {
                            setFieldValueByFieldName("h" + i, item, BigDecimal.ZERO);
                        }
                    } else {
                        setHourRain(dateTime, stDayMap, item, stcd);
                    }
                } else {
                    setHourRain(dateTime, stDayMap, item, stcd);
                }

            });
        }

        return Result.OK(hourVoIPage);
    }


    @Override
    public Result<?> dayStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize) {
        //2020-11-01 00:00:00
        String time = req.getParameter("time");
        if (Strings.isBlank(time)) {
            return Result.error("时间不可为空!");
        }
        DateTime dateTime = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
        //获取测站信息
        HashMap<String, Object> stMonthMap = new HashMap<>();
        stMonthMap.put("time", time);
        IPage<RainMonthDayVo> monthDayVoIPage = slRainStatisticalMapper.stMonthTotal(new Page<>(pageNo, pageSize), stMonthMap);
        if (0 != monthDayVoIPage.getTotal()) {
            List<RainMonthDayVo> records = monthDayVoIPage.getRecords();
            records.forEach(rainMonthDayVo -> {
                String stcd = rainMonthDayVo.getStcd();
                BigDecimal monthTotal = rainMonthDayVo.getMonthTotal();
                //如果月累计为零,其他都为零
                if (Objects.nonNull(monthTotal)) {
                    if (BigDecimal.ZERO.compareTo(monthTotal) == 0) {
                        rainMonthDayVo.setMaxRainDates(DateUtil.format(dateTime, "yyyy-MM-dd"));
                        rainMonthDayVo.setMaxRainValue(BigDecimal.ZERO);
                        rainMonthDayVo.setRainDayCount(0);
                        rainMonthDayVo.setEarlyDays(BigDecimal.ZERO);
                        rainMonthDayVo.setInTenDays(BigDecimal.ZERO);
                        rainMonthDayVo.setLastTenDays(BigDecimal.ZERO);
                        for (int i = 1; i < 32; i++) {
                            setFieldValueByFieldName("d" + i, rainMonthDayVo, BigDecimal.ZERO);
                        }
                    } else {
                        setDayValue(dateTime, stMonthMap, rainMonthDayVo, stcd);
                    }
                } else {
                    setDayValue(dateTime, stMonthMap, rainMonthDayVo, stcd);
                }
            });
        }

        return Result.OK(monthDayVoIPage);
    }

    @Override
    public Result<?> monthStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize) {
        //2020-01-01 00:00:00
        String time = req.getParameter("time");
        DateTime dateTime = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
        if (Strings.isBlank(time)) {
            return Result.error("时间不可为空!");
        }
        //获取测站信息
        HashMap<String, Object> stYearMap = new HashMap<>();
        stYearMap.put("time", time);
        IPage<RainYearMonthVo> yearMonthVoIPage = slRainStatisticalMapper.stYearTotal(new Page<>(pageNo, pageSize), stYearMap);
        if (0 != yearMonthVoIPage.getTotal()) {
            List<RainYearMonthVo> records = yearMonthVoIPage.getRecords();
            records.forEach(rainYearMonthVo -> {
                String stcd = rainYearMonthVo.getStcd();
                BigDecimal yearTotal = rainYearMonthVo.getYearTotal();
                //如果年累计为零,其他都为零
                if (Objects.nonNull(yearTotal)) {
                    if (BigDecimal.ZERO.compareTo(yearTotal) == 0) {
                        rainYearMonthVo.setMaxRainDates(DateUtil.format(dateTime, "yyyy-MM"));
                        rainYearMonthVo.setMaxRainValue(BigDecimal.ZERO);
                        for (int i = 1; i < 13; i++) {
                            setFieldValueByFieldName("m" + i, rainYearMonthVo, BigDecimal.ZERO);
                        }
                    } else {
                        setMonthValue(dateTime, stYearMap, rainYearMonthVo, stcd);
                    }
                } else {
                    setMonthValue(dateTime, stYearMap, rainYearMonthVo, stcd);
                }
            });
        }

        return Result.OK(yearMonthVoIPage);

    }

    private void setMonthValue(DateTime dateTime, HashMap<String, Object> stYearMap, RainYearMonthVo rainYearMonthVo, String stcd) {
        //设置月雨量
        String nextYear = DateUtil.offsetMonth(dateTime, 12).toString();
        stYearMap.put("stcd", stcd);
        stYearMap.put("endTime", nextYear);
        List<SlRainYear> slRainYears = slRainStatisticalMapper.selectMonths(stYearMap);
        BigDecimal maxRainValue = BigDecimal.ZERO;
        String maxRainDates = null;
        for (SlRainYear slRainYear : slRainYears) {
            Date tm = slRainYear.getTm();
            BigDecimal val = slRainYear.getVal();
            int month = DateUtil.month(tm) + 1;
            setFieldValueByFieldName("m" + month, rainYearMonthVo, val);
            if (Objects.nonNull(val)) {
                if (val.compareTo(maxRainValue) == 1) {
                    maxRainValue = val;
                    maxRainDates = DateUtil.format(tm, "yyyy-MM");
                }
            }
        }
        rainYearMonthVo.setMaxRainValue(maxRainValue);
        rainYearMonthVo.setMaxRainDates(maxRainDates);
    }

    private void setDayValue(DateTime dateTime, HashMap<String, Object> stMonthMap, RainMonthDayVo rainMonthDayVo, String stcd) {
        //设置日雨量
        String nextMonth = DateUtil.offsetMonth(dateTime, 1).toString();
        stMonthMap.put("stcd", stcd);
        stMonthMap.put("endTime", nextMonth);
        List<SlRainDay> slRainDays = slRainStatisticalMapper.selectDays(stMonthMap);
        BigDecimal maxRainValue = BigDecimal.ZERO;
        String maxRainDates = null;
        Integer rainDayCount = 0;
        BigDecimal earlyDays = BigDecimal.ZERO;
        BigDecimal inTenDays = BigDecimal.ZERO;
        BigDecimal lastTenDays = BigDecimal.ZERO;
        for (SlRainDay slRainDay : slRainDays) {
            Date tm = slRainDay.getTm();
            BigDecimal val = slRainDay.getVal();
            //获取天数
            int day = DateUtil.dayOfMonth(tm);
            setFieldValueByFieldName("d" + day, rainMonthDayVo, val);
            if (Objects.nonNull(val)) {
                //值不为零
                if (!(val.compareTo(BigDecimal.ZERO) == 0)) {
                    rainDayCount = rainDayCount + 1;

                }
                if (val.compareTo(maxRainValue) == 1) {
                    maxRainValue = val;
                    maxRainDates = DateUtil.format(tm, "yyyy-MM-dd");
                }
            }
            //设置上中下旬
            if (day < 11) {
                if (Objects.nonNull(val)) {
                    BigDecimal add = earlyDays.add(val);
                    earlyDays = add;
                }
            } else if (day < 21 && day > 10) {
                BigDecimal add = inTenDays.add(val);
                inTenDays = add;
            } else {
                BigDecimal add = lastTenDays.add(val);
                lastTenDays = add;
            }
        }
        rainMonthDayVo.setMaxRainValue(maxRainValue);
        rainMonthDayVo.setMaxRainDates(maxRainDates);
        rainMonthDayVo.setRainDayCount(rainDayCount);
        rainMonthDayVo.setEarlyDays(earlyDays);
        rainMonthDayVo.setInTenDays(inTenDays);
        rainMonthDayVo.setLastTenDays(lastTenDays);
    }

    /**
     * 设置小时雨量
     *
     * @param dateTime
     * @param stDayMap
     * @param item
     * @param stcd
     */
    private void setHourRain(DateTime dateTime, HashMap<String, Object> stDayMap, RainDayHourVo item, String stcd) {
        //设置小时雨量
        String endTime = DateUtil.offsetDay(dateTime, 1).toString();
        stDayMap.put("stcd", stcd);
        stDayMap.put("endTime", endTime);
        List<SlRainHour> slRainHours = slRainStatisticalMapper.selectHours(stDayMap);
        slRainHours.forEach(slRainHour -> {
            Date tm = slRainHour.getTm();
            //获取小时数
            int hour = DateUtil.hour(tm, true);
            BigDecimal val = slRainHour.getVal();
            setFieldValueByFieldName("h" + hour, item, val);
        });
    }

    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private void setFieldValueByFieldName(String fieldName, Object object, BigDecimal value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
