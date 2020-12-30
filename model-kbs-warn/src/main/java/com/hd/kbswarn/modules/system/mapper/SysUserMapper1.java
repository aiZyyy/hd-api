package com.hd.kbswarn.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.kbswarn.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
public interface SysUserMapper1 extends BaseMapper<SysUser> {
    SysUser getByIds(@Param(value = "userId") String userId);
}
