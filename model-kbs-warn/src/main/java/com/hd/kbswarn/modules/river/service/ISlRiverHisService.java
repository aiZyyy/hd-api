package com.hd.kbswarn.modules.river.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverHis;

/**
 * @Description: 河道水情历史表
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
public interface ISlRiverHisService extends IService<SlRiverHis> {

    Result<?> listByStcd(String stcd, String tm_begin, String tm_end);
}
