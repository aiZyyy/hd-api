package com.hd.rtu.modules.sms.listener;

import com.hd.kbswarn.modules.sms.hander.SmsMessageStatusHandler;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.aliyun.MqQueueConstant;
import com.hd.common.aliyun.SmsReceiveStatusTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @Classname AliyunSmsSendReceiveListener
 * @Description 监听服务状态改变发送请求
 * @Date 2020/7/27 17:11
 * @Created by xlhua
 */
@Slf4j
@Component
@RabbitListener(queues = MqQueueConstant.ALIYUN_SMS_SEND_STATUS_RTU_QUEUE)
public class AliyunSmsSendStatusReceiveListener {
    @Autowired
    private Map<String, SmsMessageStatusHandler> messageHandlerMap;

    @RabbitHandler
    public void receive(SmsReceiveStatusTemplate smsReceiveStatusTemplate) {
        long startTime = System.currentTimeMillis();
        log.info("接受回执消息队列====消息中心接收到短信发送回执-> 短信的主键：{} -> 回执的状态：{} ", smsReceiveStatusTemplate.getSmsId(), smsReceiveStatusTemplate.getCode());
        String channel = smsReceiveStatusTemplate.getChannel();
        SmsMessageStatusHandler smsMessageStatusHandler = messageHandlerMap.get(channel);
        if (smsMessageStatusHandler == null) {
            log.error("接受回执消息队列====没有找到指定的路由通道，不进行发送处理完毕！");
            return;
        }

        smsMessageStatusHandler.execute(smsReceiveStatusTemplate);
        long useTime = System.currentTimeMillis() - startTime;
        log.info("接受回执消息队列====短信发送回执处理完毕，耗时 {}毫秒", useTime);
    }
}
