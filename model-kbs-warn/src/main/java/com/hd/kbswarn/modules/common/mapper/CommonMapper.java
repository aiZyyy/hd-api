package com.hd.kbswarn.modules.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName CommonMapper
 * Description
 * Create by ZY
 * Date 2020/11/30 10:08
 */
@Repository
public interface CommonMapper {
    public String queryDictTextByKey(@Param("code") String code, @Param("key") String key);
}
