package com.hd.kbswarn.modules.sms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlhua
 * @since 2020-07-29
 */
@Data
@TableName("sms_template")
public class SmsTemplate extends Model<SmsTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * UUID主键
     */
    @TableId(value = "ID",type = IdType.UUID)
    private String id;
    /**
     * 短信模板ID。请在控制台模板管理页面模板CODE一列查看。(SMS_153055065)
     */
    @TableField("template_code")
    private String templateCode;
    /**
     * 短信类型。其中：
        0：验证码。
        1：短信通知。
        2：推广短信。
        3：国际/港澳台消息。
     */
    @TableField("template_type")
    private Integer templateType;
    /**
     * 模板名称，长度为1~30个字符。(阿里云短信测试模板)
     */
    @TableField("template_name")
    private String templateName;
    /**
     * 模板内容，长度为1~500个字符(	您正在申请手机注册，验证码为：${code}，5分钟内有效！)
     */
    @TableField("template_content")
    private String templateContent;
    /**
     * 短信模板申请说明。请在申请说明中描述您的业务使用场景，长度为1~100个字符。(当前的短信模板应用于双11大促推广营销)
     */
    private String remark;
    /**
     * 	
        系统规定参数。取值：AddSmsTemplate。(AddSmsTemplate)
     */
    private String action;
    /**
     * 	
        主账号AccessKey的ID。(LTAIP00vvvvvvvvv)
     */
    @TableField("access_keyId")
    private String accessKeyid;
    /**
     * 模板审核状态。其中：
        0：审核中。
        1：审核通过。
        2：审核失败，请在返回参数Reason中查看审核失败原因。
     */
    @TableField("template_status")
    private Integer templateStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private Date updateTime;


    @TableField("sms_sign")
    private String smsSign;

    /**
     * 备注
     */
    private String nt;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
