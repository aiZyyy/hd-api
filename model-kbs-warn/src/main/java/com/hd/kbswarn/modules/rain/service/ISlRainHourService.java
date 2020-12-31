package com.hd.kbswarn.modules.rain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHour;

/**
 * @Description: 小时降雨量统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
public interface ISlRainHourService extends IService<SlRainHour> {

    Result<?> listByStcd(String stcd, String tm_begin, String tm_end);
}
