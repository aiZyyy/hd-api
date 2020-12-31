package com.hd.kbswarn.modules.river.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName RiverCurListVo
 * Description
 * Create by ZY
 * Date 2020/11/30 13:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiverCurListVo {
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
    private BigDecimal z;
    private BigDecimal q;
    private Integer wptn;
    private Integer zWarnLevel;
    private Integer qWarnLevel;
    private BigDecimal zLevel1;
    private BigDecimal zLevel2;
    private BigDecimal zLevel3;
    private BigDecimal qLevel1;
    private BigDecimal qLevel2;
    private BigDecimal qLevel3;
}
