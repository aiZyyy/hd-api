package com.hd.kbswarn.modules.sms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.sms.entity.SmsPublish;
import com.hd.kbswarn.modules.sms.service.ISmsPublishService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 短信发布
 * @author xlhua
 * @since 2020-07-29
 */
@Slf4j
@RestController
@RequestMapping("/smsPublish")
public class SmsPublishController  {

    @Autowired private ISmsPublishService smsPublishService;

    @AutoLog(value = "查询发布记录ById")
    @ApiOperation(value="查询发布记录ById", notes="查询发布记录ById")
    @GetMapping("/getSmsTemplate")
    public Result<SmsPublish> get(@PathVariable Integer id) {
        Result<SmsPublish> result = new Result<>();
        SmsPublish smsPublish = smsPublishService.getById(id);
        if (ObjectUtil.isNotNull(smsPublish)){
            result.setResult(smsPublish);
            result.setSuccess(true);
        } else {
            result.error500("未找到对应记录");
        }
        return result;
    }


    @AutoLog(value = "短信发布记录-list集合查询")
    @ApiOperation(value="短信发布记录-list集合查询", notes="短信发布记录-list集合查询")
    @GetMapping(value = "/list")
    public Result<IPage<SmsPublish>> queryPageList(SmsPublish smsPublish,
                                                    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                    HttpServletRequest req) {
        Result<IPage<SmsPublish>> result = new Result<IPage<SmsPublish>>();
        QueryWrapper<SmsPublish> queryWrapper = QueryGenerator.initQueryWrapper(smsPublish, req.getParameterMap());
        Page<SmsPublish> page = new Page<SmsPublish>(pageNo, pageSize);
        IPage<SmsPublish> pageList = smsPublishService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @AutoLog(value = "短信发布-添加")
    @ApiOperation(value="短信发布-添加", notes="短信发布-添加")
    @PostMapping(value = "/add")
    public Result<SmsPublish> add(@RequestBody(required = true) SmsPublish smsPublish) {
        Result<SmsPublish> result = new Result<SmsPublish>();
        try {
            smsPublishService.save(smsPublish);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return result;
    }

}
