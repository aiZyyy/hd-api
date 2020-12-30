package com.hd.kbswarn.modules.rain.domain.entity;

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

import java.util.Date;

/**
 * @Description: 降雨实时
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Data
@TableName("sl_rain_cur")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_rain_cur对象", description = "降雨实时")
public class SlRainCur {

    /**
     * 测站编码
     */
    @Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
    @Dict(dictTable = "sl_st_info", dicText = "STNM", dicCode = "STCD")
    private String stcd;
    /**
     * 测报时间
     */
    @Excel(name = "测报时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "测报时间")
    private Date tm;
    /**
     * 时段长
     */
    @Excel(name = "时段长", width = 15)
    @ApiModelProperty(value = "时段长")
    private java.math.BigDecimal intv;
    /**
     * 监测值
     */
    @Excel(name = "监测值", width = 15)
    @ApiModelProperty(value = "监测值")
    private java.math.BigDecimal val;
    /**
     * 5分钟降雨量
     */
    @Excel(name = "5分钟降雨量", width = 15)
    @ApiModelProperty(value = "5分钟降雨量")
    private java.math.BigDecimal m5;
    /**
     * 10分钟降雨量
     */
    @Excel(name = "10分钟降雨量", width = 15)
    @ApiModelProperty(value = "10分钟降雨量")
    private java.math.BigDecimal m10;
    /**
     * 30分钟降雨量
     */
    @Excel(name = "30分钟降雨量", width = 15)
    @ApiModelProperty(value = "30分钟降雨量")
    private java.math.BigDecimal m30;
    /**
     * 1小时降雨量
     */
    @Excel(name = "1小时降雨量", width = 15)
    @ApiModelProperty(value = "1小时降雨量")
    private java.math.BigDecimal h1;
    /**
     * 3小时降雨量
     */
    @Excel(name = "3小时降雨量", width = 15)
    @ApiModelProperty(value = "3小时降雨量")
    private java.math.BigDecimal h3;
    /**
     * 6小时降雨量
     */
    @Excel(name = "6小时降雨量", width = 15)
    @ApiModelProperty(value = "6小时降雨量")
    private java.math.BigDecimal h6;
    /**
     * 12小时降雨量
     */
    @Excel(name = "12小时降雨量", width = 15)
    @ApiModelProperty(value = "12小时降雨量")
    private java.math.BigDecimal h12;
    /**
     * 24小时降雨量
     */
    @Excel(name = "24小时降雨量", width = 15)
    @ApiModelProperty(value = "24小时降雨量")
    private java.math.BigDecimal h24;
    /**
     * 日降雨量
     */
    @Excel(name = "日降雨量", width = 15)
    @ApiModelProperty(value = "日降雨量")
    private java.math.BigDecimal day;
    /**
     * 月降雨量
     */
    @Excel(name = "月降雨量", width = 15)
    @ApiModelProperty(value = "月降雨量")
    private java.math.BigDecimal month;
    /**
     * 年降雨量
     */
    @Excel(name = "年降雨量", width = 15)
    @ApiModelProperty(value = "年降雨量")
    private java.math.BigDecimal year;
}
