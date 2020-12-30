package com.hd.base.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 通讯录管理
 * @Author: FishCoder
 * @Date: 2020-12-03
 * @Version: V1.0
 */
@Data
@TableName("sl_address_book")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sl_address_book对象", description = "通讯录管理")
public class SlAddressBook {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 名称
     */
    @Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 电话
     */
    @Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private String phone;
    /**
     * 职务
     */
    @Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
    private String position;
    /**
     * 单位
     */
    @Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 部门id
     */
    @Excel(name = "部门id", width = 15)
    @ApiModelProperty(value = "部门id")
    private String depId;

    private String depTreeName;
}
