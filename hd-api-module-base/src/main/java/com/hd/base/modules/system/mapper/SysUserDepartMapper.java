package com.hd.base.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.base.modules.system.entity.SysUserDepart;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDepartMapper extends BaseMapper<SysUserDepart> {

    List<SysUserDepart> getUserDepartByUid(@Param("userId") String userId);
}
