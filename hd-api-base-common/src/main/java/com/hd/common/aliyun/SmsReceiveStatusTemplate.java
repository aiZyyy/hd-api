package com.hd.common.aliyun;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname SmsReceiveStatusTemplate
 * @Description TODO
 * @Date 2020/7/29 17:26
 * @Created by xlhua
 */
@Data
public class SmsReceiveStatusTemplate implements Serializable {

    /**
     * 短信的主键
     */
    private String smsId;

    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
     */
    private String bizId;

    /**
     * 请求状态码。
     *
     * 返回OK代表请求成功。
     * 其他错误码详见错误码列表。
     */
    private String code;

    /**
     *
     * 状态码的描述。
     */
    private String message;
    /**
     * 短信通道
     */
    private String channel;

    public SmsReceiveStatusTemplate(String smsId, String bizId, String code, String message,String channel) {
        this.smsId = smsId;
        this.bizId = bizId;
        this.code = code;
        this.message = message;
        this.channel = channel;
    }
}
