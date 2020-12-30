package com.hd.kbswarn.modules.river.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverCur;
import com.hd.common.api.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 河道水情实时表
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
public interface ISlRiverCurService extends IService<SlRiverCur> {

    Result<?> homeList(HttpServletRequest req);
}
