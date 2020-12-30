package com.hd.kbswarn.modules.sms.controller;

import com.hd.kbswarn.modules.sms.service.ISmsAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 短信回复功能
 * @author xlhua
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/smsAnswer")
public class SmsAnswerController {

    @Autowired private ISmsAnswerService smsAnswerService;



}
