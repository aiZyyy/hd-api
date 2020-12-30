package com.hd.common.aliyun;

/**
 * @Classname MqQueueConstant
 * @Date 2020/7/27 16:11
 * @Description MQ 消息队列
 * @Created by xlhua
 */
public interface MqQueueConstant {

    /**
     * 短信服务状态队列
     */
    String ALIYUN_SMS_SEND_RTU_QUEUE = "aliyun_sms_send_rtu";

    /**
     * 短信发送状态回执
     */
    String ALIYUN_SMS_SEND_STATUS_RTU_QUEUE = "aliyun_sms_send_status_rtu";
}
