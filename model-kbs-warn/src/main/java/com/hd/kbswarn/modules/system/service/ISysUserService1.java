package com.hd.kbswarn.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.kbswarn.modules.system.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
public interface ISysUserService1 extends IService<SysUser> {

    SysUser getByIds(String userId);

}
