package com.hd.kbswarn.modules.rain.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName RainCurListVo
 * Description
 * Create by ZY
 * Date 2020/12/1 9:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainCurListVo {
    private String stcd;
    private String stName;
    private Date tm;
    /**
     * 经度
     */
    private BigDecimal lgtd;
    /**
     * 纬度
     */
    private BigDecimal lttd;
    private BigDecimal val;
    private BigDecimal h1;
    private BigDecimal h3;
    private BigDecimal h6;
    private BigDecimal h12;
    private BigDecimal h24;
    private Integer rainWarnLevel;
    private BigDecimal h1Level1;
    private BigDecimal h1Level2;
    private BigDecimal h1Level3;
    private BigDecimal h3Level1;
    private BigDecimal h3Level2;
    private BigDecimal h3Level3;
    private BigDecimal h6Level1;
    private BigDecimal h6Level2;
    private BigDecimal h6Level3;
    private BigDecimal h12Level1;
    private BigDecimal h12Level2;
    private BigDecimal h12Level3;
    private BigDecimal h24Level1;
    private BigDecimal h24Level2;
    private BigDecimal h24Level3;

}
