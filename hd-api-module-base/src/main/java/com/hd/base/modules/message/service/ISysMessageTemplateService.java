package com.hd.base.modules.message.service;

import java.util.List;

import com.hd.base.modules.message.entity.SysMessageTemplate;
import com.hd.common.system.base.service.HdService;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends HdService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
