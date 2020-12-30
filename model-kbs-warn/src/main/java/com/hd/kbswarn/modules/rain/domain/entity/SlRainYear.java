package com.hd.kbswarn.modules.rain.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 年降雨量
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("sl_rain_year")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sl_rain_year对象", description="年降雨量")
public class SlRainYear {
    
	/**测站编码*/
	@Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
	private java.lang.String stcd;
	/**测报时间*/
	@Excel(name = "测报时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "测报时间")
	private java.util.Date tm;
	/**年累计降雨量*/
	@Excel(name = "年累计降雨量", width = 15)
    @ApiModelProperty(value = "年累计降雨量")
	private java.math.BigDecimal val;
}
