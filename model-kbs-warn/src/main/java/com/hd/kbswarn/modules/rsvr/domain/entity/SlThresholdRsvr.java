package com.hd.kbswarn.modules.rsvr.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hd.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 水库水情阈值
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Data
@TableName("sl_threshold_rsvr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_threshold_rsvr对象", description = "水库水情阈值")
public class SlThresholdRsvr {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;

    /**
     * 测站编码
     */
    @Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
    @Dict(dictTable = "sl_st_info", dicText = "STNM", dicCode = "STCD")
    private java.lang.String stcd;
    /**
     * 库上水位一级阈值(低)(米)
     */
    @Excel(name = "库上水位一级阈值(低)(米)", width = 15)
    @ApiModelProperty(value = "库上水位一级阈值(低)(米)")
    private java.math.BigDecimal rzLevel1;
    /**
     * 库上水位二级阈值(中)(米)
     */
    @Excel(name = "库上水位二级阈值(中)(米)", width = 15)
    @ApiModelProperty(value = "库上水位二级阈值(中)(米)")
    private java.math.BigDecimal rzLevel2;
    /**
     * 库上水位三级阈值(高)(米)
     */
    @Excel(name = "库上水位三级阈值(高)(米)", width = 15)
    @ApiModelProperty(value = "库上水位三级阈值(高)(米)")
    private java.math.BigDecimal rzLevel3;
    /**
     * 库下水位一级阈值(低)(米)
     */
    @Excel(name = "库下水位一级阈值(低)(米)", width = 15)
    @ApiModelProperty(value = "库下水位一级阈值(低)(米)")
    private java.math.BigDecimal blrzLevel1;
    /**
     * 库下水位二级阈值(中)(米)
     */
    @Excel(name = "库下水位二级阈值(中)(米)", width = 15)
    @ApiModelProperty(value = "库下水位二级阈值(中)(米)")
    private java.math.BigDecimal blrzLevel2;
    /**
     * 库下水位三级阈值(高)(米)
     */
    @Excel(name = "库下水位三级阈值(高)(米)", width = 15)
    @ApiModelProperty(value = "库下水位三级阈值(高)(米)")
    private java.math.BigDecimal blrzLevel3;
    /**
     * 入库流量一级阈值(低)(立方米/秒)
     */
    @Excel(name = "入库流量一级阈值(低)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "入库流量一级阈值(低)(立方米/秒)")
    private java.math.BigDecimal inqLevel1;
    /**
     * 入库流量二级阈值(中)(立方米/秒)
     */
    @Excel(name = "入库流量二级阈值(中)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "入库流量二级阈值(中)(立方米/秒)")
    private java.math.BigDecimal inqLevel2;
    /**
     * 入库流量三级阈值(高)(立方米/秒)
     */
    @Excel(name = "入库流量三级阈值(高)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "入库流量三级阈值(高)(立方米/秒)")
    private java.math.BigDecimal inqLevel3;
    /**
     * 出库流量一级阈值(低)(立方米/秒)
     */
    @Excel(name = "出库流量一级阈值(低)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "出库流量一级阈值(低)(立方米/秒)")
    private java.math.BigDecimal otqLevel1;
    /**
     * 出库流量二级阈值(中)(立方米/秒)
     */
    @Excel(name = "出库流量二级阈值(中)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "出库流量二级阈值(中)(立方米/秒)")
    private java.math.BigDecimal otqLevel2;
    /**
     * 出库流量三级阈值(高)(立方米/秒)
     */
    @Excel(name = "出库流量三级阈值(高)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "出库流量三级阈值(高)(立方米/秒)")
    private java.math.BigDecimal otqLevel3;
}
