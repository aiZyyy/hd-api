package com.hd.kbswarn.modules.river.domain.entity;

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
 * @Description: 河道水情阈值
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Data
@TableName("sl_threshold_river")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_threshold_river对象", description = "河道水情阈值")
public class SlThresholdRiver {

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
    private String stcd;
    /**
     * 水位一级阈值(低)(米)
     */
    @Excel(name = "水位一级阈值(低)(米)", width = 15)
    @ApiModelProperty(value = "水位一级阈值(低)(米)")
    private java.math.BigDecimal zzLevel1;
    /**
     * 水位二级阈值(中)(米)
     */
    @Excel(name = "水位二级阈值(中)(米)", width = 15)
    @ApiModelProperty(value = "水位二级阈值(中)(米)")
    private java.math.BigDecimal zzLevel2;
    /**
     * 水位三级阈值(高)(米)
     */
    @Excel(name = "水位三级阈值(高)(米)", width = 15)
    @ApiModelProperty(value = "水位三级阈值(高)(米)")
    private java.math.BigDecimal zzLevel3;
    /**
     * 流量一级阈值(低)(立方米/秒)
     */
    @Excel(name = "流量一级阈值(低)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "流量一级阈值(低)(立方米/秒)")
    private java.math.BigDecimal qqLevel1;
    /**
     * 流量二级阈值(中)(立方米/秒)
     */
    @Excel(name = "流量二级阈值(中)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "流量二级阈值(中)(立方米/秒)")
    private java.math.BigDecimal qqLevel2;
    /**
     * 流量三级阈值(高)(立方米/秒)
     */
    @Excel(name = "流量三级阈值(高)(立方米/秒)", width = 15)
    @ApiModelProperty(value = "流量三级阈值(高)(立方米/秒)")
    private java.math.BigDecimal qqLevel3;
}
