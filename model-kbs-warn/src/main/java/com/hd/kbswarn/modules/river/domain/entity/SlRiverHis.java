package com.hd.kbswarn.modules.river.domain.entity;

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

import java.util.Date;

/**
 * @Description: 河道水情历史表
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Data
@TableName("sl_river_his")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_river_his对象", description = "河道水情历史表")
public class SlRiverHis {

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
     * 水位(米)
     */
    @Excel(name = "水位(米)", width = 15)
    @ApiModelProperty(value = "水位(米)")
    private java.math.BigDecimal z;
    /**
     * 流量(立方米/秒)
     */
    @Excel(name = "流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "流量(立方米/秒)")
    private java.math.BigDecimal q;
    /**
     * 流速(米/秒)
     */
    @Excel(name = "流速(米/秒)", width = 15)
    @ApiModelProperty(value = "流速(米/秒)")
    private java.math.BigDecimal speed;
    /**
     * 累计过水量(米)
     */
    @Excel(name = "累计过水量(米)", width = 15)
    @ApiModelProperty(value = "累计过水量(米)")
    private java.math.BigDecimal total;
    /**
     * 水势
     */
    @Excel(name = "水势", width = 15)
    @ApiModelProperty(value = "水势")
    @Dict(dicCode = "sl_kbs_wptn")
    private Integer wptn;
}
