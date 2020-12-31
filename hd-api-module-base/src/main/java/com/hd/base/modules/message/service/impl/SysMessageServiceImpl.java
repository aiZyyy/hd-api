package com.hd.base.modules.message.service.impl;

import com.hd.base.modules.message.entity.SysMessage;
import com.hd.base.modules.message.mapper.SysMessageMapper;
import com.hd.common.system.base.service.impl.HdServiceImpl;
import com.hd.base.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends HdServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
