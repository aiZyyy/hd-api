package com.hd.kbswarn.modules.rsvr.domain.entity;

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
 * @Description: 水库水情历史
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Data
@TableName("sl_rsvr_his")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_rsvr_his对象", description = "水库水情历史")
public class SlRsvrHis {

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
     * 库水位(米)
     */
    @Excel(name = "库水位(米)", width = 15)
    @ApiModelProperty(value = "库水位(米)")
    private java.math.BigDecimal rz;
    /**
     * 库下水位(米)
     */
    @Excel(name = "库下水位(米)", width = 15)
    @ApiModelProperty(value = "库下水位(米)")
    private java.math.BigDecimal blrz;
    /**
     * 入库流量(立方米/秒)
     */
    @Excel(name = "入库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "入库流量(立方米/秒)")
    private java.math.BigDecimal inq;
    /**
     * 出库流量(立方米/秒)
     */
    @Excel(name = "出库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "出库流量(立方米/秒)")
    private java.math.BigDecimal otq;
    /**
     * 蓄水量(百万立方米)
     */
    @Excel(name = "蓄水量(百万立方米)", width = 15)
    @ApiModelProperty(value = "蓄水量(百万立方米)")
    private java.math.BigDecimal w;
    /**
     * 水势
     */
    @Excel(name = "水势", width = 15)
    @ApiModelProperty(value = "水势")
    @Dict(dicCode = "sl_kbs_wptn")
    private Integer wptn;
}
