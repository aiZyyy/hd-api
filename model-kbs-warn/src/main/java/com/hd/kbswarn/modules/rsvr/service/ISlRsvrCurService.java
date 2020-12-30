package com.hd.kbswarn.modules.rsvr.service;

import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrCur;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.common.api.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 水库水情实时
 * @Author: FishCoder
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface ISlRsvrCurService extends IService<SlRsvrCur> {

    Result<?> homeList(HttpServletRequest req);
}
