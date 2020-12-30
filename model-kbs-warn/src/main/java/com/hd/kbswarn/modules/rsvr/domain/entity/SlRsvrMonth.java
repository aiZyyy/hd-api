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
 * @Description: 水库水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Data
@TableName("sl_rsvr_month")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sl_rsvr_month对象", description="水库水情日极值")
public class SlRsvrMonth {
    
	/**测站编码*/
	@Excel(name = "测站编码", width = 15)
    @ApiModelProperty(value = "测站编码")
	@Dict(dictTable = "sl_st_info", dicText = "STNM", dicCode = "STCD")
	private java.lang.String stcd;
	/**统计月份*/
	@Excel(name = "统计月份", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计月份")
	private java.util.Date tm;
	/**最高库水位(米)*/
	@Excel(name = "最高库水位(米)", width = 15)
    @ApiModelProperty(value = "最高库水位(米)")
	private java.math.BigDecimal rzMax;
	/**最高库水位出现时间集合*/
	@Excel(name = "最高库水位出现时间集合", width = 15)
    @ApiModelProperty(value = "最高库水位出现时间集合")
	private java.lang.String rzMaxTms;
	/**最低库水位(米)*/
	@Excel(name = "最低库水位(米)", width = 15)
    @ApiModelProperty(value = "最低库水位(米)")
	private java.math.BigDecimal rzMin;
	/**最低库水位出现时间集合*/
	@Excel(name = "最低库水位出现时间集合", width = 15)
    @ApiModelProperty(value = "最低库水位出现时间集合")
	private java.lang.String rzMinTms;
	/**最大入库流量(立方米/秒)*/
	@Excel(name = "最大入库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最大入库流量(立方米/秒)")
	private java.math.BigDecimal inqMax;
	/**最大入库流量出现时间集合*/
	@Excel(name = "最大入库流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最大入库流量出现时间集合")
	private java.lang.String inqMaxTms;
	/**最小入库流量(立方米/秒)*/
	@Excel(name = "最小入库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最小入库流量(立方米/秒)")
	private java.math.BigDecimal inqMin;
	/**最小入库流量出现时间集合*/
	@Excel(name = "最小入库流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最小入库流量出现时间集合")
	private java.lang.String inqMinTms;
	/**最大出库流量(立方米/秒)*/
	@Excel(name = "最大出库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最大出库流量(立方米/秒)")
	private java.math.BigDecimal otqMax;
	/**最大出库流量出现时间集合*/
	@Excel(name = "最大出库流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最大出库流量出现时间集合")
	private java.lang.String otqMaxTms;
	/**最小出库流量(立方米/秒)*/
	@Excel(name = "最小出库流量(立方米/秒)", width = 15)
    @ApiModelProperty(value = "最小出库流量(立方米/秒)")
	private java.math.BigDecimal otqMin;
	/**最小出库流量出现时间集合*/
	@Excel(name = "最小出库流量出现时间集合", width = 15)
    @ApiModelProperty(value = "最小出库流量出现时间集合")
	private java.lang.String otqMinTms;
}
