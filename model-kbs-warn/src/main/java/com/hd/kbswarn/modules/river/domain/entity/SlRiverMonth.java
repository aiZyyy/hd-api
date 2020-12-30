package com.hd.kbswarn.modules.river.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.hd.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 河道水情月极值
 * @Author: FishCoder
 * @Date: 2020-11-25
 * @Version: V1.0
 */
@Data
@TableName("sl_river_month")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_river_month对象", description = "河道水情月极值")
public class SlRiverMonth {

    /**
     * 测站编码
     */
    @Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
    @Dict(dictTable = "sl_st_info", dicText = "STNM", dicCode = "STCD")
    private java.lang.String stcd;
    /**
     * 统计月份
     */
    @Excel(name = "统计月份", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计月份")
    private java.util.Date tm;
    /**
     * 最高水位(米)
     */
    @Excel(name = "最高水位(米)", width = 15)
    @ApiModelProperty(value = "最高水位(米)")
    private java.math.BigDecimal zzMax;
    /**
     * 最高水位出现时间集合
     */
    @Excel(name = "最高水位出现时间集合", width = 15)
    @ApiModelProperty(value = "最高水位出现时间集合")
    private java.lang.String zzMaxTms;
    /**
     * 最低水位(米)
     */
    @Excel(name = "最低水位(米)", width = 15)
    @ApiModelProperty(value = "最低水位(米)")
    private java.math.BigDecimal zzMin;
    /**
     * 最低水位出现时间集合
     */
    @Excel(name = "最低水位出现时间集合", width = 15)
    @ApiModelProperty(value = "最低水位出现时间集合")
    private java.lang.String zzMinTms;
    /**
     * 最大流量(立方米/秒)
     */
    @Excel(name = "最大流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最大流量(立方米/秒)")
    private java.math.BigDecimal qqMax;
    /**
     * 最大流量出现时间集合
     */
    @Excel(name = "最大流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最大流量出现时间集合")
    private java.lang.String qqMaxTms;
    /**
     * 最小流量(立方米/秒)
     */
    @Excel(name = "最小流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最小流量(立方米/秒)")
    private java.math.BigDecimal qqMin;
    /**
     * 最小流量出现时间集合
     */
    @Excel(name = "最小流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最小流量出现时间集合")
    private java.lang.String qqMinTms;
}
