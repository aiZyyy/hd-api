package com.hd.kbswarn.modules.rain.service;


import com.hd.common.api.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 雨情数据统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
public interface ISlRainStatisticalService {

    Result<?> hourStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize);

    Result<?> dayStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize);

    Result<?> monthStatistical(HttpServletRequest req, Integer pageNo, Integer pageSize);
}
