package com.hd.kbswarn.modules.sms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlhua
 * @since 2020-07-29
 */
@Data
@TableName("sms_publish")
public class SmsPublish extends Model<SmsPublish> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键UUID
     */
    @TableId(value = "ID",type = IdType.UUID)
    private String id;
    /**
     * 模板ID
     */
    @TableField("TEMPLATE_ID")
    private String templateId;
    /**
     * 发送短信的类型 0.验证码  1.短信通知
     */
    @TableField("TYPE")
    private Integer type;
    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("TM")
    private Date tm;
    /**
     * 发布内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * 发布总人数
     */
    @TableField("HEADCOUNT")
    private Integer headcount;
    /**
     * 成功数
     */
    @TableField("SUCCESS")
    private Integer success;
    /**
     * 失败数
     */
    @TableField("FAIL")
    private Integer fail;
    /**
     * 发送成功率
     */
    @TableField("SUCCESS_RATE")
    private String successRate;
    /**
     * 判断是否是待发送
     */
    @TableField("IS_WAIT_SEND")
    private String isWaitSend;
    /**
     * 备注
     */
    @TableField("NT")
    private String nt;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
