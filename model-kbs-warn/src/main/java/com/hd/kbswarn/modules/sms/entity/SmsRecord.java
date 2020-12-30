package com.hd.kbswarn.modules.sms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

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
@TableName("sms_record")
public class SmsRecord extends Model<SmsRecord> {

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
     * 发布记录ID
     */
    @TableField("PUBLISH_ID")
    private String publishId;
    /**
     * 发送时间
     */
    @TableField("SEND_TM")
    private Date sendTm;
    /**
     * 短信内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * 发送状态（1、成功；2、失败 3、正在发送）
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 发送状态描述（1、成功；2、失败 3、正在发送）
     */
    @TableField("status_desc")
    private String statusDesc;

    /**
     * 短信接收人
     */
    @TableField("ACCEPTOR")
    private String acceptor;
    /**
     * 操作人
     */
    @TableField("OPERATOR")
    private String operator;
    /**
     * 最新回复时间
     */
    @TableField("ANSWER_TM")
    private Date answerTm;
    /**
     * 最新回复内容
     */
    @TableField("ANSWER_CONTENT")
    private String answerContent;
    /**
     * 拓展字段1
     */
    @TableField("EXP1")
    private String exp1;
    /**
     * 拓展字段2
     */
    @TableField("EXP2")
    private String exp2;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
