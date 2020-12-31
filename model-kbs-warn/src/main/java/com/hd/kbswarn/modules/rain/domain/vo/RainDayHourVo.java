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
public class RainDayHourVo {

    private String stcd;

    private String name;

    /**
     * 日累计(mm)
     */
    private BigDecimal dayTotal;

    private BigDecimal h1;
    private BigDecimal h2;
    private BigDecimal h3;
    private BigDecimal h4;
    private BigDecimal h5;
    private BigDecimal h6;
    private BigDecimal h7;
    private BigDecimal h8;
    private BigDecimal h9;
    private BigDecimal h10;
    private BigDecimal h11;
    private BigDecimal h12;
    private BigDecimal h13;
    private BigDecimal h14;
    private BigDecimal h15;
    private BigDecimal h16;
    private BigDecimal h17;
    private BigDecimal h18;
    private BigDecimal h19;
    private BigDecimal h20;
    private BigDecimal h21;
    private BigDecimal h22;
    private BigDecimal h23;
    private BigDecimal h0;


}