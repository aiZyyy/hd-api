package com.hd.kbswarn.modules.rsvr.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName RsvrCurListVo
 * Description
 * Create by ZY
 * Date 2020/11/30 15:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RsvrCurListVo {
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
    /**
     * 库上水位
     */
    private BigDecimal rz;
    /**
     * 库下水位
     */
    private BigDecimal blrz;
    /**
     * 入库流量
     */
    private BigDecimal inq;
    /**
     * 出库流量
     */
    private BigDecimal otq;
    /**
     * 蓄水量
     */
    private BigDecimal w;
    /**
     * 水势
     */
    private Integer wptn;
    /**
     * 库上预警级别
     */
    private Integer rzWarnLevel;
    /**
     * 库下预警级别
     */
    private Integer blrzWarnLevel;
    /**
     * 入库流量预警级别
     */
    private Integer inqWarnLevel;
    /**
     * 出库流量预警级别
     */
    private Integer otqWarnLevel;

    /**
     * 库上阈值1~3级
     */
    private BigDecimal rzLevel1;
    private BigDecimal rzLevel2;
    private BigDecimal rzLevel3;
    /**
     * 库下阈值1~3级
     */
    private BigDecimal blrzLevel1;
    private BigDecimal blrzLevel2;
    private BigDecimal blrzLevel3;
    /**
     * 入库流量阈值1~3级
     */
    private BigDecimal inqLevel1;
    private BigDecimal inqLevel2;
    private BigDecimal inqLevel3;
    /**
     * 出库流量阈值1~3级
     */
    private BigDecimal otqLevel1;
    private BigDecimal otqLevel2;
    private BigDecimal otqLevel3;
}
