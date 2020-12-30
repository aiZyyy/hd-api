package com.hd.kbswarn.modules.sms.dto;

import com.hd.kbswarn.modules.sms.dto.AliyunSmsTemplateDTO;
import com.hd.kbswarn.modules.sms.dto.SmsReceiverDTO;
import lombok.Data;

import java.util.List;

/**
 * @Classname SmsDTO
 * @Description TODO
 * @Date 2020/7/29 9:40
 * @Created by Administrator
 */
@Data
public class SmsDTO{

    private List<SmsReceiverDTO> receivers;
    private List<String> userIds;
    private AliyunSmsTemplateDTO template;

}
