package com.hd.kbswarn.modules.station.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;

/**
 * @Description: 测站基本属性
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
public interface ISlStInfoService extends IService<SlStInfo> {

    Result<?> listBySttp(String sttp);

}
