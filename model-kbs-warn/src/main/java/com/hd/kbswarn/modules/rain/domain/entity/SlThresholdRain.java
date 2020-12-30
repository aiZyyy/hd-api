package com.hd.kbswarn.modules.rain.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.hd.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 降雨预警阈值
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Data
@TableName("sl_threshold_rain")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_threshold_rain对象", description = "降雨预警阈值")
public class SlThresholdRain {

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
     * 1小时降雨一级阈值(低)(mm)
     */
    @Excel(name = "1小时降雨一级阈值(低)(mm)", width = 15)
    @ApiModelProperty(value = "1小时降雨一级阈值(低)(mm)")
    private java.math.BigDecimal h1Level1;
    /**
     * 1小时降雨二级阈值(中)(mm)
     */
    @Excel(name = "1小时降雨二级阈值(中)(mm)", width = 15)
    @ApiModelProperty(value = "1小时降雨二级阈值(中)(mm)")
    private java.math.BigDecimal h1Level2;
    /**
     * 1小时降雨三级阈值(高)(mm)
     */
    @Excel(name = "1小时降雨三级阈值(高)(mm)", width = 15)
    @ApiModelProperty(value = "1小时降雨三级阈值(高)(mm)")
    private java.math.BigDecimal h1Level3;
    /**
     * 3小时降雨一级阈值(低)(mm)
     */
    @Excel(name = "3小时降雨一级阈值(低)(mm)", width = 15)
    @ApiModelProperty(value = "3小时降雨一级阈值(低)(mm)")
    private java.math.BigDecimal h3Level1;
    /**
     * 3小时降雨二级阈值(中)(mm)
     */
    @Excel(name = "3小时降雨二级阈值(中)(mm)", width = 15)
    @ApiModelProperty(value = "3小时降雨二级阈值(中)(mm)")
    private java.math.BigDecimal h3Level2;
    /**
     * 3小时降雨三级阈值(高)(mm)
     */
    @Excel(name = "3小时降雨三级阈值(高)(mm)", width = 15)
    @ApiModelProperty(value = "3小时降雨三级阈值(高)(mm)")
    private java.math.BigDecimal h3Level3;
    /**
     * 6小时降雨一级阈值(低)(mm)
     */
    @Excel(name = "6小时降雨一级阈值(低)(mm)", width = 15)
    @ApiModelProperty(value = "6小时降雨一级阈值(低)(mm)")
    private java.math.BigDecimal h6Level1;
    /**
     * 6小时降雨二级阈值(中)(mm)
     */
    @Excel(name = "6小时降雨二级阈值(中)(mm)", width = 15)
    @ApiModelProperty(value = "6小时降雨二级阈值(中)(mm)")
    private java.math.BigDecimal h6Level2;
    /**
     * 6小时降雨三级阈值(高)(mm)
     */
    @Excel(name = "6小时降雨三级阈值(高)(mm)", width = 15)
    @ApiModelProperty(value = "6小时降雨三级阈值(高)(mm)")
    private java.math.BigDecimal h6Level3;
    /**
     * 12小时降雨一级阈值(低)(mm)
     */
    @Excel(name = "12小时降雨一级阈值(低)(mm)", width = 15)
    @ApiModelProperty(value = "12小时降雨一级阈值(低)(mm)")
    private java.math.BigDecimal h12Level1;
    /**
     * 12小时降雨二级阈值(中)(mm)
     */
    @Excel(name = "12小时降雨二级阈值(中)(mm)", width = 15)
    @ApiModelProperty(value = "12小时降雨二级阈值(中)(mm)")
    private java.math.BigDecimal h12Level2;
    /**
     * 12小时降雨三级阈值(高)(mm)
     */
    @Excel(name = "12小时降雨三级阈值(高)(mm)", width = 15)
    @ApiModelProperty(value = "12小时降雨三级阈值(高)(mm)")
    private java.math.BigDecimal h12Level3;
    /**
     * 24小时降雨一级阈值(低)(mm)
     */
    @Excel(name = "24小时降雨一级阈值(低)(mm)", width = 15)
    @ApiModelProperty(value = "24小时降雨一级阈值(低)(mm)")
    private java.math.BigDecimal h24Level1;
    /**
     * 24小时降雨二级阈值(中)(mm)
     */
    @Excel(name = "24小时降雨二级阈值(中)(mm)", width = 15)
    @ApiModelProperty(value = "24小时降雨二级阈值(中)(mm)")
    private java.math.BigDecimal h24Level2;
    /**
     * 24小时降雨三级阈值(高)(mm)
     */
    @Excel(name = "24小时降雨三级阈值(高)(mm)", width = 15)
    @ApiModelProperty(value = "24小时降雨三级阈值(高)(mm)")
    private java.math.BigDecimal h24Level3;
}
