package com.hd.kbswarn.modules.station.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 测站基本属性
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Data
@TableName("sl_st_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_st_info对象", description = "测站基本属性")
public class SlStInfo {

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
    private java.lang.String stcd;
    /**
     * 测站名称
     */
    @Excel(name = "测站名称", width = 15)
    @ApiModelProperty(value = "测站名称")
    private java.lang.String stnm;
    /**
     * 河流名称
     */
    @Excel(name = "河流名称", width = 15)
    @ApiModelProperty(value = "河流名称")
    private java.lang.String rvnm;
    /**
     * 水系名称
     */
    @Excel(name = "水系名称", width = 15)
    @ApiModelProperty(value = "水系名称")
    private java.lang.String hnnm;
    /**
     * 流域名称
     */
    @Excel(name = "流域名称", width = 15)
    @ApiModelProperty(value = "流域名称")
    private java.lang.String bsnm;
    /**
     * 经度
     */
    @Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private java.math.BigDecimal lgtd;
    /**
     * 纬度
     */
    @Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
    private java.math.BigDecimal lttd;
    /**
     * 站址
     */
    @Excel(name = "站址", width = 15)
    @ApiModelProperty(value = "站址")
    private java.lang.String stlc;
    /**
     * 行政区划码
     */
    @Excel(name = "行政区划码", width = 15)
    @ApiModelProperty(value = "行政区划码")
    private java.lang.String addvcd;
    /**
     * 基面名称
     */
    @Excel(name = "基面名称", width = 15)
    @ApiModelProperty(value = "基面名称")
    private java.lang.String dtmnm;
    /**
     * 基面高程
     */
    @Excel(name = "基面高程", width = 15)
    @ApiModelProperty(value = "基面高程")
    private java.math.BigDecimal dtmel;
    /**
     * 基面修正值
     */
    @Excel(name = "基面修正值", width = 15)
    @ApiModelProperty(value = "基面修正值")
    private java.math.BigDecimal dtpr;
    /**
     * 站类
     */
    @Excel(name = "站类", width = 15)
    @ApiModelProperty(value = "站类")
    @Dict(dicCode = "station_type")
    private java.lang.String sttp;
    /**
     * 报汛等级
     */
    @Excel(name = "报汛等级", width = 15)
    @ApiModelProperty(value = "报汛等级")
    private java.lang.String frgrd;
    /**
     * 建站年月
     */
    @Excel(name = "建站年月", width = 15)
    @ApiModelProperty(value = "建站年月")
    private java.lang.String esstym;
    /**
     * 始报年月
     */
    @Excel(name = "始报年月", width = 15)
    @ApiModelProperty(value = "始报年月")
    private java.lang.String bgfrym;
    /**
     * 隶属行业单位
     */
    @Excel(name = "隶属行业单位", width = 15)
    @ApiModelProperty(value = "隶属行业单位")
    private java.lang.String atcunit;
    /**
     * 信息管理单位
     */
    @Excel(name = "信息管理单位", width = 15)
    @ApiModelProperty(value = "信息管理单位")
    private java.lang.String admauth;
    /**
     * 交换管理单位
     */
    @Excel(name = "交换管理单位", width = 15)
    @ApiModelProperty(value = "交换管理单位")
    private java.lang.String locality;
    /**
     * 测站岸别
     */
    @Excel(name = "测站岸别", width = 15)
    @ApiModelProperty(value = "测站岸别")
    private java.lang.String stbk;
    /**
     * 测站方位
     */
    @Excel(name = "测站方位", width = 15)
    @ApiModelProperty(value = "测站方位")
    private java.math.BigDecimal stazt;
    /**
     * 至河口距离
     */
    @Excel(name = "至河口距离", width = 15)
    @ApiModelProperty(value = "至河口距离")
    private java.math.BigDecimal dstrvm;
    /**
     * 集水面积
     */
    @Excel(name = "集水面积", width = 15)
    @ApiModelProperty(value = "集水面积")
    private java.math.BigDecimal drna;
    /**
     * 拼音码
     */
    @Excel(name = "拼音码", width = 15)
    @ApiModelProperty(value = "拼音码")
    private java.lang.String phcd;
    /**
     * 启用标志
     */
    @Excel(name = "启用标志", width = 15)
    @ApiModelProperty(value = "启用标志")
    private java.lang.String usfl;
    /**
     * 备注
     */
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String comments;
    /**
     * 时间戳
     */
    @Excel(name = "时间戳", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "时间戳")
    private java.util.Date moditime;
}
