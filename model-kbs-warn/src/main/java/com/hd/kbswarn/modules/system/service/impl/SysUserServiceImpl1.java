package com.hd.kbswarn.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.kbswarn.modules.system.entity.SysUser;
import com.hd.kbswarn.modules.system.mapper.SysUserMapper1;
import com.hd.kbswarn.modules.system.service.ISysUserService1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SysUserServiceImpl1 extends ServiceImpl<SysUserMapper1, SysUser> implements ISysUserService1 {


    @Override
    public SysUser getByIds(String userId) {
        return this.baseMapper.getByIds(userId);
    }
}
