package com.hd.kbswarn.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hd.kbswarn.modules.sms.entity.SmsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SmsRecordMapper extends BaseMapper<SmsRecord> {

    /**
     * 新增短信记录
     *
     * @param smsRecord
     */
    void insertSmsRecord(SmsRecord smsRecord);

    /**
     * 查询短信记录
     *
     * @param page
     * @param map
     * @return
     */

    IPage<SmsRecord> getSmsRecordList(IPage<SmsRecord> page, @Param("item") Map<String, Object> map);

    List<SmsRecord> getSmsRecordList(@Param("item") Map<String, Object> map);

    /**
     * 获取最新的短信记录
     * @param smsRecord
     * @return
     */
    SmsRecord getLatestSmsRecord(SmsRecord smsRecord);

}
