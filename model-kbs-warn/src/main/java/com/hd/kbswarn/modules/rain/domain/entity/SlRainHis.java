package com.hd.kbswarn.modules.rain.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hd.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 雨量历史
 * @Author: FishCoder
 * @Date: 2020-11-25
 * @Version: V1.0
 */
@Data
@TableName("sl_rain_his")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_rain_his对象", description = "雨量历史")
public class SlRainHis {

    /**
     * 测站编码
     */
    @Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
    @Dict(dictTable = "sl_st_info", dicText = "STNM", dicCode = "STCD")
    private java.lang.String stcd;
    /**
     * 测报时间
     */
    @Excel(name = "测报时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "测报时间")
    private java.util.Date tm;
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
}
