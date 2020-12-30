package com.hd.kbswarn.modules.sms.hander;

import com.hd.common.aliyun.SmsReceiveStatusTemplate;

/**
 * @Classname SmsMessageHandler
 * @Description 阿里云配置中心
 * @Date 2020/7/27 16:11
 * @Created by xlhua
 */
public interface SmsMessageStatusHandler {
    /**
     * 执行入口
     *
     * @param smsReceiveStatusTemplate 信息
     */
    void execute(SmsReceiveStatusTemplate smsReceiveStatusTemplate);

    /**
     * 数据校验
     *
     * @param smsReceiveStatusTemplate 信息
     */
    void check(SmsReceiveStatusTemplate smsReceiveStatusTemplate);

    /**
     * 业务处理
     *
     * @param smsReceiveStatusTemplate 信息
     * @return boolean
     */
    boolean process(SmsReceiveStatusTemplate smsReceiveStatusTemplate);

    /**
     * 失败处理
     *
     * @param smsReceiveStatusTemplate 信息
     */
    void fail(SmsReceiveStatusTemplate smsReceiveStatusTemplate);
}
