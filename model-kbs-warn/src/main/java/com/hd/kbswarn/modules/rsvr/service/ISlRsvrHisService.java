package com.hd.kbswarn.modules.rsvr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrHis;

/**
 * @Description: 水库水情历史
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
public interface ISlRsvrHisService extends IService<SlRsvrHis> {

    Result<?> listByStcd(String stcd, String tm_begin, String tm_end);
}
