package com.hd.kbswarn.modules.sms.hander;

import com.hd.kbswarn.modules.sms.comon.Assert;
import com.hd.kbswarn.modules.sms.constant.AliyunConstant;
import com.hd.kbswarn.modules.sms.entity.SmsPublish;
import com.hd.kbswarn.modules.sms.entity.SmsRecord;
import com.hd.kbswarn.modules.sms.service.ISmsPublishService;
import com.hd.kbswarn.modules.sms.service.ISmsRecordService;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.aliyun.AliyunChannelConstant;
import com.hd.common.aliyun.SmsReceiveStatusTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import static java.math.BigDecimal.ROUND_DOWN;

/**
 * @Classname SmsAliyunMessageHandler
 * @Description 阿里短息服务回执处理
 * @Date 2020/7/27 16:11
 * @Created by xlhua
 */
@Slf4j
@Component(AliyunChannelConstant.HUAXIANLIANG_SMS_STATUS)
public class AliyunSmsSendStatusReceiveHandler extends AbstractAliyunSmsSendStatusReceiveHandler {

    @Autowired
    private ISmsRecordService smsRecordService;

    @Autowired
    private ISmsPublishService smsPublishService;

    /**
     * 数据校验
     *
     * @param smsReceiveStatusTemplate 消息
     */
    @Override
    public void check(SmsReceiveStatusTemplate smsReceiveStatusTemplate) {
//        Assert.isBlank(smsReceiveStatusTemplate.getBizId(), "发送回执ID不能为空");
        Assert.isBlank(smsReceiveStatusTemplate.getSmsId(), "发送短信ID不能为空");
        Assert.isBlank(smsReceiveStatusTemplate.getCode(), "请求状态码不能为空");
        Assert.isBlank(smsReceiveStatusTemplate.getMessage(), "状态码的描述不能为空");
    }

    /**
     * 业务处理
     *
     * @param smsReceiveStatusTemplate 消息
     */
    @Transactional
    @Override
    public boolean process(SmsReceiveStatusTemplate smsReceiveStatusTemplate) {
        //拿到短信的回执  然后处理
        SmsRecord smsRecord = smsRecordService.getById(smsReceiveStatusTemplate.getSmsId());

        if ("OK".equals(smsReceiveStatusTemplate.getCode())){
            log.info(smsRecord.getId()+"发送成功，并得到发送成功回执！");
            //修改发送状态为成功
            smsRecord.setStatus(AliyunConstant.SMS_STATUS_1);
            smsRecordService.updateById(smsRecord);
            //修改发布的成功数量与成功率
            SmsPublish smsPublish = smsPublishService.getById(smsRecord.getPublishId());
            smsPublish.setSuccess(smsPublish.getSuccess()+1);
            smsPublish.setFail(smsPublish.getFail()-1);
            //计算成功率
            DecimalFormat df = new DecimalFormat("0%");
            BigDecimal successB = new BigDecimal(smsPublish.getSuccess());
            BigDecimal headcount = new BigDecimal(smsPublish.getHeadcount());
            BigDecimal result = successB.divide(headcount,2,ROUND_DOWN);
            smsPublish.setSuccessRate(df.format(result));
            smsPublishService.updateById(smsPublish);
            return true;
        } else {
            smsRecord.setStatus(AliyunConstant.SMS_STATUS_2);
            log.error(smsRecord.getId() + "发送失败，并得到发送发送回执！回执状态码为：" + smsReceiveStatusTemplate.getCode());
            switch(smsReceiveStatusTemplate.getCode()){
                case "isv.SMS_SIGNATURE_SCENE_ILLEGAL":
                    smsRecord.setStatusDesc("短信所使用签名场景非法");
                    break;
                case "isv.EXTEND_CODE_ERROR":
                    smsRecord.setStatusDesc("扩展码使用错误，相同的扩展码不可用于多个签名");
                    break;
                case "isv.DOMESTIC_NUMBER_NOT_SUPPORTED":
                    smsRecord.setStatusDesc("国际/港澳台消息模板不支持发送境内号码");
                    break;
                case "isv.DENY_IP_RANGE":
                    smsRecord.setStatusDesc("源IP地址所在的地区被禁用");
                    break;
                case "isv.DAY_LIMIT_CONTROL":
                    smsRecord.setStatusDesc("触发日发送限额");
                    break;
                case "isv.SMS_CONTENT_ILLEGAL":
                    smsRecord.setStatusDesc("短信内容包含禁止发送内容");
                    break;
                case "isv.SMS_SIGN_ILLEGAL":
                    smsRecord.setStatusDesc("签名禁止使用");
                    break;
                case "isp.RAM_PERMISSION_DENY":
                    smsRecord.setStatusDesc("RAM权限DENY");
                    break;
                case "isv.OUT_OF_SERVICE":
                    smsRecord.setStatusDesc("业务停机");
                    break;
                case "isv.PRODUCT_UN_SUBSCRIPT":
                    smsRecord.setStatusDesc("未开通云通信产品的阿里云客户");
                    break;
                case "isv.PRODUCT_UNSUBSCRIBE":
                    smsRecord.setStatusDesc("产品未开通");
                    break;
                case "isv.ACCOUNT_NOT_EXISTS":
                    smsRecord.setStatusDesc("账户不存在");
                    break;
                case "isv.ACCOUNT_ABNORMAL":
                    smsRecord.setStatusDesc("账户异常");
                    break;
                case "isv.SMS_TEMPLATE_ILLEGAL":
                    smsRecord.setStatusDesc("短信模版不合法");
                    break;
                case "isv.SMS_SIGNATURE_ILLEGAL":
                    smsRecord.setStatusDesc("短信签名不合法");
                    break;
                case "isv.INVALID_PARAMETERS":
                    smsRecord.setStatusDesc("参数异常");
                    break;
                case "isp.SYSTEM_ERROR":
                    smsRecord.setStatusDesc("阿里云系统错误");
                    break;
                case "isv.MOBILE_NUMBER_ILLEGAL":
                    smsRecord.setStatusDesc("非法手机号");
                    break;
                case "isv.MOBILE_COUNT_OVER_LIMIT":
                    smsRecord.setStatusDesc("手机号码数量超过限制");
                    break;
                case "isv.TEMPLATE_MISSING_PARAMETERS":
                    smsRecord.setStatusDesc("模版缺少变量");
                    break;
                case "isv.BUSINESS_LIMIT_CONTROL":
                    smsRecord.setStatusDesc("业务限流");
                    break;
                case "isv.INVALID_JSON_PARAM":
                    smsRecord.setStatusDesc("黑名单管控");
                    break;
                case "isv.BLACK_KEY_CONTROL_LIMIT":
                    smsRecord.setStatusDesc("JSON参数不合法，只接受字符串值");
                    break;
                case "isv.PARAM_LENGTH_LIMIT":
                    smsRecord.setStatusDesc("参数超出长度限制");
                    break;
                case "isv.PARAM_NOT_SUPPORT_URL":
                    smsRecord.setStatusDesc("不支持URL");
                    break;
                case "isv.AMOUNT_NOT_ENOUGH":
                    smsRecord.setStatusDesc("账户余额不足");
                    break;
                case "isv.TEMPLATE_PARAMS_ILLEGAL":
                    smsRecord.setStatusDesc("模版变量里包含非法关键字");
                    break;
                case "SignatureDoesNotMatch":
                    smsRecord.setStatusDesc("签名（Signature）加密错误");
                    break;
                case "InvalidTimeStamp.Expired":
                    smsRecord.setStatusDesc("一般由于时区差异造成时间戳错误，发出请求的时间和服务器接收到请求的时间不在15分钟内。");
                    break;
                case "SignatureNonceUsed":
                    smsRecord.setStatusDesc("唯一随机数重复，SignatureNonce为唯一随机数，用于防止网络重放攻击。");
                    break;
                case "InvalidVersion":
                    smsRecord.setStatusDesc(" 版本号（Version）错误。");
                    break;
                case "InvalidAction.NotFound":
                    smsRecord.setStatusDesc(" 参数Action中指定的接口名错误。");
                    break;
                case "isv.SIGN_COUNT_OVER_LIMIT":
                    smsRecord.setStatusDesc("一个自然日中申请签名数量超过限制。");
                    break;
                case "isv.TEMPLATE_COUNT_OVER_LIMIT":
                    smsRecord.setStatusDesc("一个自然日中申请模板数量超过限制。");
                    break;
                case "isv.SIGN_NAME_ILLEGAL":
                    smsRecord.setStatusDesc("签名名称不符合规范。");
                    break;
                case "isv.SIGN_FILE_LIMIT":
                    smsRecord.setStatusDesc("签名认证材料附件大小超过限制。");
                    break;
                case "isv.SIGN_OVER_LIMIT":
                    smsRecord.setStatusDesc("签名的名称或申请说明的字数超过限制。");
                    break;
                case "isv.TEMPLATE_OVER_LIMIT":
                    smsRecord.setStatusDesc("模板的名称、内容或申请说明的字数超过限制。");
                    break;
                case "SIGNATURE_BLACKLIST":
                    smsRecord.setStatusDesc("签名黑名单");
                    break;
                default:
                    smsRecord.setStatusDesc("未知错误，在阿里云官方提供的文档未说明该错误。");
                    break;
            }
            smsRecordService.updateById(smsRecord);
            return false;
        }

    }

    /**
     * 失败处理
     *
     * @param smsReceiveStatusTemplate 消息
     */
    @Override
    public void fail(SmsReceiveStatusTemplate smsReceiveStatusTemplate) {
        log.error("短信发送失败 -> 网关：{} -> 发送短信记录主键ID：{}", smsReceiveStatusTemplate.getChannel(),smsReceiveStatusTemplate.getSmsId());
    }
}
