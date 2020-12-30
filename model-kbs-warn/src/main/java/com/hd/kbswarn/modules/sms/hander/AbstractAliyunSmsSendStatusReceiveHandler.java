package com.hd.kbswarn.modules.sms.hander;

import com.hd.common.aliyun.SmsReceiveStatusTemplate;

/**
 * @Classname AbstractMessageHandler
 * @Description 抽象hander
 * @Date 2020/7/27 16:11
 * @Created by xlhua
 */
public abstract class AbstractAliyunSmsSendStatusReceiveHandler implements SmsMessageStatusHandler {

    /**
     * 执行入口
     *
     * @param smsReceiveStatusTemplate 信息
     */
    @Override
    public void execute(SmsReceiveStatusTemplate smsReceiveStatusTemplate) {
        check(smsReceiveStatusTemplate);
        if (!process(smsReceiveStatusTemplate)) {
            fail(smsReceiveStatusTemplate);
        }
    }

    /**
     * 数据校验
     *
     * @param smsReceiveStatusTemplate 信息
     */
    @Override
    public abstract void check(SmsReceiveStatusTemplate smsReceiveStatusTemplate);

    /**
     * 业务处理
     *
     * @param smsReceiveStatusTemplate 信息
     * @return boolean
     */
    @Override
    public abstract boolean process(SmsReceiveStatusTemplate smsReceiveStatusTemplate);

    /**
     * 失败处理
     *
     * @param smsReceiveStatusTemplate 信息
     */
    @Override
    public abstract void fail(SmsReceiveStatusTemplate smsReceiveStatusTemplate);
}
