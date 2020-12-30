package com.hd.common.aliyun;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname MobileMsgTemplate
 * @Date 2020/7/27 16:11
 * @Created by xlhua
 */
@Data
public class MobileMsgTemplate implements Serializable {

    /**
     * 短信的主键
     */
    private String smsId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 组装后的模板内容JSON字符串  {"code":"1111"}
     */
    private String context;
    /**
     * 短信通道
     */
    private String channel;
    /**
     * 模板编码
     */
    private String templateCode;
    /**
     * 短信签名
     */
    private String signName;

    public MobileMsgTemplate(String smsId,String mobile, String context, String channel, String signName, String templateCode){
        this.smsId = smsId;
        this.mobile = mobile;
        this.context = context;
        this.channel = channel;
        this.signName = signName;
        this.templateCode = templateCode;
    }
}
