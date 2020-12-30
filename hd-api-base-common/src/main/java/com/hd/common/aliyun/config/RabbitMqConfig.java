package com.hd.common.aliyun.config;

import com.hd.common.aliyun.MqQueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SmsAliyunPropertiesConfig
 * @Description rabbit初始化配置
 * @Date 2020/7/27 17:18
 * @Created by xlhua
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 初始化短信发送队列
     *
     * @return
     */
    @Bean
    public Queue initSmsSendConfigChangeQueue() {
        return new Queue(MqQueueConstant.ALIYUN_SMS_SEND_RTU_QUEUE);
    }

    /**
     * 初始化短信回执队列
     *
     * @return
     */
    @Bean
    public Queue initSmsSendStatusConfigChangeQueue() {
        return new Queue(MqQueueConstant.ALIYUN_SMS_SEND_STATUS_RTU_QUEUE);
    }
}
