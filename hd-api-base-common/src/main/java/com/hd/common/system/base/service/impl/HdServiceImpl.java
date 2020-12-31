package com.hd.common.system.base.service.impl;

import com.hd.common.system.base.entity.HdEntity;
import com.hd.common.system.base.service.HdService;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: ServiceImpl基类
 * @Author: dangzhenghui@163.com
 * @Date: 2019-4-21 8:13
 * @Version: 1.0
 */
@Slf4j
public class HdServiceImpl<M extends BaseMapper<T>, T extends HdEntity> extends ServiceImpl<M, T> implements HdService<T> {

}
