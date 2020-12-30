package com.hd.kbswarn.modules.rain.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainMonthDayVo {

    private String stcd;

    private String name;

    /**
     * 最大日雨量(mm)
     */
    private BigDecimal maxRainValue;

    /**
     * 最大日雨量日期
     */
    private String maxRainDates;

    /**
     * 降水日数
     */
    private Integer rainDayCount;

    /**
     * 上旬
     */
    private BigDecimal earlyDays;
    /**
     * 中旬
     */
    private BigDecimal inTenDays;
    /**
     * 下旬
     */
    private BigDecimal lastTenDays;

    /**
     * 月累计(mm)
     */
    private BigDecimal monthTotal;

    private BigDecimal d1;
    private BigDecimal d2;
    private BigDecimal d3;
    private BigDecimal d4;
    private BigDecimal d5;
    private BigDecimal d6;
    private BigDecimal d7;
    private BigDecimal d8;
    private BigDecimal d9;
    private BigDecimal d10;
    private BigDecimal d11;
    private BigDecimal d12;
    private BigDecimal d13;
    private BigDecimal d14;
    private BigDecimal d15;
    private BigDecimal d16;
    private BigDecimal d17;
    private BigDecimal d18;
    private BigDecimal d19;
    private BigDecimal d20;
    private BigDecimal d21;
    private BigDecimal d22;
    private BigDecimal d23;
    private BigDecimal d24;
    private BigDecimal d25;
    private BigDecimal d26;
    private BigDecimal d27;
    private BigDecimal d28;
    private BigDecimal d29;
    private BigDecimal d30;
    private BigDecimal d31;


}