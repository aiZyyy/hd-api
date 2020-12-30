package com.hd.kbswarn.modules.sms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.hd.kbswarn.modules.sms.entity.SmsTemplate;
import com.hd.kbswarn.modules.sms.service.ISmsTemplateService;
import io.swagger.annotations.ApiOperation;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 阿里云短信模板
 * @author xlhua
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/smsTemplate")
public class SmsTemplateController  {

    @Autowired private ISmsTemplateService smsTemplateService;

    @AutoLog(value = "查询短信模板ById")
    @ApiOperation(value="查询短信模板ById", notes="查询短信模板ById")
    @GetMapping("/getSmsTemplate")
    public Result<SmsTemplate> get(@PathVariable Integer id) {
        Result<SmsTemplate> result = new Result<>();
        SmsTemplate smsTemplate = smsTemplateService.getById(id);
        if (ObjectUtil.isNotNull(smsTemplate)){
            result.setResult(smsTemplate);
            result.setSuccess(true);
        } else {
            result.error500("未找到对应记录");
        }
        return result;
    }


    @AutoLog(value = "阿里云短信模板-list集合查询")
    @ApiOperation(value="阿里云短信模板-list集合查询", notes="阿里云短信模板-list集合查询")
    @GetMapping(value = "/list")
    public Result<List<SmsTemplate>> queryPageList() {
        Result<List<SmsTemplate>> result = new Result<List<SmsTemplate>>();
        List<SmsTemplate> list = smsTemplateService.list();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

}
