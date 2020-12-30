//package com.hd.rtu.modules.sms.service.impl;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.google.gson.Gson;
//import com.hd.rtu.modules.sms.dto.InclinationVar;
//import com.hd.rtu.modules.sms.entity.SmsRecord;
//import com.hd.rtu.modules.sms.service.ISmsSendService;
//import org.jeecg.common.constant.SysConstant;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Description: 短信发送服务
// * @Author: suoyasong
// * @Date: 2019-09-06
// * @Version: V1.0
// */
//@Service
//public class SmsSendServiceImpl implements ISmsSendService {
//    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public SendSmsResponse sendSmsRecord(SmsRecord smsRecord) throws Exception {
//        //发短信
//        SendSmsResponse response = sendSms(smsRecord);
//        Thread.sleep(3000L);//必要的，否则无法查询到明细
//        return response;
//    }
//
//
//    public static SendSmsResponse sendSms(SmsRecord smsRecord) throws ClientException {
//        //可自助调整超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//        //初始化acsClient,暂不支持region化
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SysConstant.accessKeyId, SysConstant.accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SysConstant.product, SysConstant.domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//        //组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//        //必填:待发送手机号
//        request.setPhoneNumbers(smsRecord.getTelephone());
////        request.setPhoneNumbers("15902740916");
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName(SysConstant.msgSignName);
//        //必填:短信模板-可在短信控制台中找到
//
//        //预警类型（1，浊度；2，角度；3，流量）
//        int alarmType = smsRecord.getAlarmType();
//        String templateCode = null;
//        switch (alarmType) {
//            case 1:
//                templateCode = SysConstant.alarmTurbidityCode;
//                break;
//            case 2:
//                templateCode = SysConstant.alarmInclinationCode;
//                break;
//            case 3:
//                templateCode = SysConstant.alarmQCode;
//                break;
//
//        }
//        request.setTemplateCode(templateCode);
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        InclinationVar inclinationVar = new InclinationVar();
//        String tm = sdf.format(smsRecord.getAlarmTm());
//        String exp1 = smsRecord.getExp1();
//        List<String> xyAxis = Arrays.asList(exp1.split(","));
//        String xAxis = xyAxis.get(0);
//        String yAxis = xyAxis.get(1);
//        inclinationVar.setName(smsRecord.getStnm());
//        inclinationVar.setTime(tm);
//        inclinationVar.setXaxis(xAxis);
//        inclinationVar.setYaxis(yAxis);
//        Gson gson = new Gson();
//        String msgJsonStr = gson.toJson(inclinationVar);
////        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
//        request.setTemplateParam(msgJsonStr);
//        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
//        //request.setSmsUpExtendCode("90997");
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(smsRecord.getStcd());
//        //hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//
//        return sendSmsResponse;
//    }
//}
