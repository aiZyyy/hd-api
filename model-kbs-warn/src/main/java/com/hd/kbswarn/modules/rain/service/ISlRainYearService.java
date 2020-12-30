package com.hd.kbswarn.modules.rain.service;

import com.hd.kbswarn.modules.rain.domain.entity.SlRainYear;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;

/**
 * @Description: 年降雨量
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface ISlRainYearService extends IService<SlRainYear> {

    Result<?> listByStcd(String stcd, String tm_begin, String tm_end);
}
