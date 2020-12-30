package com.hd.kbswarn.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.sms.entity.SmsRecord;
import com.hd.kbswarn.modules.sms.mapper.SmsRecordMapper;
import com.hd.kbswarn.modules.sms.service.ISmsRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 短信记录
 * @Author: suoyasong
 * @Date: 2019-09-06
 * @Version: V1.0
 */
@Service
public class SmsRecordServiceImpl extends ServiceImpl<SmsRecordMapper, SmsRecord> implements ISmsRecordService {

    @Override
    public void addSmsRecord(SmsRecord smsRecord) {
        baseMapper.insertSmsRecord(smsRecord);
    }

    @Override
    public List<SmsRecord> getSmsRecordList(Map<String, Object> queryMap) {
        return baseMapper.getSmsRecordList(queryMap);
    }

    @Override
    public IPage<SmsRecord> getSmsRecordList(Page<SmsRecord> page, Map<String, Object> queryMap) {
        return baseMapper.getSmsRecordList(page, queryMap);
    }

    @Override
    public SmsRecord getLatestSmsRecord(SmsRecord smsRecord) {
        return baseMapper.getLatestSmsRecord(smsRecord);
    }
}
