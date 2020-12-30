package com.hd.kbswarn.modules.sms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.sms.constant.AliyunConstant;
import com.hd.kbswarn.modules.sms.dto.SmsDTO;
import com.hd.kbswarn.modules.sms.dto.SmsReceiverDTO;
import com.hd.kbswarn.modules.sms.entity.SmsPublish;
import com.hd.kbswarn.modules.sms.entity.SmsRecord;
import com.hd.kbswarn.modules.sms.entity.SmsTemplate;
import com.hd.kbswarn.modules.sms.service.ISmsPublishService;
import com.hd.kbswarn.modules.sms.service.ISmsRecordService;
import com.hd.kbswarn.modules.sms.service.ISmsTemplateService;
import com.hd.kbswarn.modules.system.service.ISysUserService1;
import com.hd.kbswarn.modules.system.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import com.hd.common.aliyun.AliyunChannelConstant;
import com.hd.common.aliyun.MobileMsgTemplate;
import com.hd.common.aliyun.MqQueueConstant;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.query.QueryGenerator;
import com.hd.common.system.vo.LoginUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Description: 短信发送记录
 * @Author: suoyasong
 * @Date: 2019-09-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "短信发送记录")
@RestController
@RequestMapping("/smsRecord")
public class SmsRecordController {

    @Autowired
    private ISmsRecordService smsRecordService;
    @Autowired
    private ISmsPublishService smsPublishService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ISysUserService1 sysUserService;
    @Autowired
    private ISmsTemplateService templateService;

    /**
     * 查询短信发送记录page
     */
    @RequestMapping(value = "/getSmsRecordPage", method = RequestMethod.GET)
    public Result<IPage<SmsRecord>> getPacketStatPage(SmsRecord smsRecord, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                      HttpServletRequest req) {
        Result<IPage<SmsRecord>> result = new Result<>();
        QueryWrapper<SmsRecord> queryWrapper = QueryGenerator.initQueryWrapper(smsRecord, req.getParameterMap());
        Page<SmsRecord> page = new Page<SmsRecord>(pageNo, pageSize);
        IPage<SmsRecord> pageList = smsRecordService.page(page, queryWrapper);
        List<SmsRecord> smsRecords = pageList.getRecords();
        for (SmsRecord record:smsRecords){
            if (record.getStatus()==1){
                record.setStatusDesc("发送成功");
            } else if (record.getStatus()==3){
                record.setStatusDesc("正在发送");
            } else {
                if (StringUtils.isBlank(record.getStatusDesc())){
                    record.setStatusDesc("发送失败");
                } else {
                    record.setStatusDesc("发送失败，"+record.getStatusDesc());
                }
            }
        }
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }


    /**
     * 发送短信
     */
    @Transactional
    @AutoLog(value = "阿里云发送短信接口")
    @PostMapping(value = "/sendAliyunMessage")
    @ApiOperation(value = "阿里云发送短信接口", notes = "阿里云发送短信接口")
    public Result<Boolean> sendMessage(@RequestBody SmsDTO smsDTO){

        Result<Boolean> result = new Result<>();
        if (ObjectUtil.isNull(smsDTO) || StringUtils.isBlank(smsDTO.getTemplate().getId()) || StringUtils.isBlank(smsDTO.getTemplate().getTemplateParam())){
            result.error500("短信发送失败，缺少必要参数，请检查！");
            return result;
        }

        //获取当前用户信息
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //1.生成发布记录
        SmsPublish smsPublish = new SmsPublish();
        smsPublish.setTemplateId(smsDTO.getTemplate().getId());
        smsPublish.setType(AliyunConstant.SMS_TYPE_1);
        smsPublish.setTm(new Date());
        smsPublish.setContent(smsDTO.getTemplate().getTemplateParam());
        smsPublish.setHeadcount(smsDTO.getUserIds().size()+smsDTO.getReceivers().size());
        smsPublish.setSuccess(0);
        smsPublish.setFail(smsDTO.getUserIds().size()+smsDTO.getReceivers().size());
        smsPublish.setSuccessRate("0");
        smsPublishService.save(smsPublish);

        SmsTemplate template = templateService.getById(smsDTO.getTemplate().getId());

        //2.生成短信记录
        if (smsDTO.getReceivers().size()>0){
            for (SmsReceiverDTO smsReceiverDTO:smsDTO.getReceivers()){
                SmsRecord smsRecord = new SmsRecord();
                smsRecord.setTemplateId(smsDTO.getTemplate().getId());
                smsRecord.setPublishId(smsPublish.getId());
                smsRecord.setSendTm(new Date());
                smsRecord.setContent(smsDTO.getTemplate().getTemplateParam());
                smsRecord.setStatus(AliyunConstant.SMS_STATUS_3);
                smsRecord.setAcceptor(smsReceiverDTO.getName()+"("+smsReceiverDTO.getPhone()+")");
                smsRecord.setOperator(sysUser.getRealname());
                smsRecordService.save(smsRecord);

                //3.将需要发送的短信放入rabbitMq(自定义用户)
                log.info("短信发送请求消息中心 -> 手机号:{}-> 队列名称: {} -> 短信模板：{} -> 内容：{}", smsReceiverDTO.getName()+"("+smsReceiverDTO.getPhone()+")","通知类队列：ALIYUN_SMS_SEND_RTU_QUEUE", smsDTO.getTemplate().getTemplateCode(), smsDTO.getTemplate().getTemplateParam());
                rabbitTemplate.convertAndSend(MqQueueConstant.ALIYUN_SMS_SEND_RTU_QUEUE,
                        new MobileMsgTemplate(
                                smsRecord.getId(),
                                smsReceiverDTO.getPhone(),
                                smsDTO.getTemplate().getTemplateParam(),
                                AliyunChannelConstant.HUAXIANLIANG_SMS,
                                template.getSmsSign(),
                                smsDTO.getTemplate().getTemplateCode()
                        )
                );
            }
        }

        if (smsDTO.getUserIds().size()>0){
            for (String userId:smsDTO.getUserIds()){
                SysUser user = sysUserService.getByIds(userId);

                SmsRecord smsRecord = new SmsRecord();
                smsRecord.setTemplateId(smsDTO.getTemplate().getId());
                smsRecord.setPublishId(smsPublish.getId());
                smsRecord.setSendTm(new Date());
                smsRecord.setContent(smsDTO.getTemplate().getTemplateParam());
                smsRecord.setStatus(AliyunConstant.SMS_STATUS_3);
                smsRecord.setAcceptor(user.getRealname()+"("+user.getPhone()+")");
                smsRecord.setOperator(sysUser.getRealname());
                smsRecordService.save(smsRecord);

                //3.将需要发送的短信放入rabbitMq (系统用户)
                log.info("短信发送请求消息中心 -> 手机号:{}-> 队列名称: {} -> 短信模板：{} -> 内容：{}", user.getRealname()+"("+user.getPhone()+")","通知类队列：ALIYUN_SMS_SEND_RTU_QUEUE", smsDTO.getTemplate().getTemplateCode(), smsDTO.getTemplate().getTemplateParam());
                rabbitTemplate.convertAndSend(MqQueueConstant.ALIYUN_SMS_SEND_RTU_QUEUE,
                        new MobileMsgTemplate(
                                smsRecord.getId(),
                                user.getPhone(),
                                smsDTO.getTemplate().getTemplateParam(),
                                AliyunChannelConstant.HUAXIANLIANG_SMS,
                                template.getSmsSign(),
                                smsDTO.getTemplate().getTemplateCode()
                        )
                );
            }
        }

        result.setSuccess(true);
        result.setMessage("消息发送成功！");
        result.setResult(true);
        return result;
    }
}
