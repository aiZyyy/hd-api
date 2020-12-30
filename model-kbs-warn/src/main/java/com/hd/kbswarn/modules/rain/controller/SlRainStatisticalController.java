package com.hd.kbswarn.modules.rain.controller;

import com.hd.kbswarn.modules.rain.service.ISlRainStatisticalService;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 雨情数据统计
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/rain/statistical")
public class SlRainStatisticalController {
    @Autowired
    private ISlRainStatisticalService slRainStatisticalService;

    /**
     * 日时累计雨量
     *
     * @param req
     * @return
     */
    @ResponseBody
    @GetMapping("/hourStatistical")
    public Result<?> hourStatistical(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        return slRainStatisticalService.hourStatistical(req, pageNo, pageSize);
    }

    /**
     * 月日累计雨量
     *
     * @param req
     * @return
     */
    @ResponseBody
    @GetMapping("/dayStatistical")
    public Result<?> dayStatistical(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        return slRainStatisticalService.dayStatistical(req, pageNo, pageSize);
    }

    /**
     * 年月累计雨量
     *
     * @param req
     * @return
     */
    @ResponseBody
    @GetMapping("/monthStatistical")
    public Result<?> monthStatistical(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    HttpServletRequest req) {
        return slRainStatisticalService.monthStatistical(req, pageNo, pageSize);
    }
}
