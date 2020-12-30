package com.hd.kbswarn.modules.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.kbswarn.modules.sms.entity.SmsRecord;

import java.util.List;
import java.util.Map;

public interface ISmsRecordService extends IService<SmsRecord> {

    void addSmsRecord(SmsRecord smsRecord);





    /**
     * 查询集合
     * @param queryMap
     * @return
     */
    List<SmsRecord> getSmsRecordList(Map<String, Object> queryMap);

    /**
     * 分页
     * @param
     * @return
     */
    IPage<SmsRecord> getSmsRecordList(Page<SmsRecord> page, Map<String, Object> queryMap);

    /**
     * 获取最新的短信记录
     * @param smsRecord
     * @return
     */
    SmsRecord getLatestSmsRecord(SmsRecord smsRecord);
}
