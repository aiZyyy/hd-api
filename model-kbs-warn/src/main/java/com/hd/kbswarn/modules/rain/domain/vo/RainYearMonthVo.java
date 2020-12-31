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
public class RainYearMonthVo {

    private String stcd;

    private String name;

    /**
     * 最大月雨量(mm)
     */
    private BigDecimal maxRainValue;

    /**
     * 最大月雨量日期
     */
    private String maxRainDates;

    /**
     * 年累计(mm)
     */
    private BigDecimal yearTotal;

    private BigDecimal m1;
    private BigDecimal m2;
    private BigDecimal m3;
    private BigDecimal m4;
    private BigDecimal m5;
    private BigDecimal m6;
    private BigDecimal m7;
    private BigDecimal m8;
    private BigDecimal m9;
    private BigDecimal m10;
    private BigDecimal m11;
    private BigDecimal m12;
}