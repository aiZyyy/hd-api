package com.hd.kbswarn.modules.common.service.impl;

import com.hd.common.constant.CacheConstant;
import com.hd.kbswarn.modules.common.mapper.CommonMapper;
import com.hd.kbswarn.modules.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ClassName CommonServiceImpl
 * Description
 * Create by ZY
 * Date 2020/11/30 10:06
 */
@Service("CommonServiceImpl")
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    /**
     * 通过查询指定code 获取字典值text
     *
     * @param code
     * @param key
     * @return
     */

    @Override
    @Cacheable(value = CacheConstant.SYS_DICT_CACHE, key = "#code+':'+#key")
    public String queryDictTextByKey(String code, String key) {
        log.info("无缓存dictText的时候调用这里！");
        return commonMapper.queryDictTextByKey(code, key);
    }
}
