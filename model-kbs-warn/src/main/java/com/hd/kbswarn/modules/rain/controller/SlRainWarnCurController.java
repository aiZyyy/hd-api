package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainWarnCur;
import com.hd.kbswarn.modules.rain.service.ISlRainWarnCurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 降雨实时预警
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "降雨实时预警")
@RestController
@RequestMapping("/rain/slRainWarnCur")
public class SlRainWarnCurController extends HdController<SlRainWarnCur, ISlRainWarnCurService> {
    @Autowired
    private ISlRainWarnCurService slRainWarnCurService;

    /**
     * 分页列表查询
     *
     * @param slRainWarnCur
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "降雨实时预警-分页列表查询")
    @ApiOperation(value = "降雨实时预警-分页列表查询", notes = "降雨实时预警-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRainWarnCur slRainWarnCur,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRainWarnCur> queryWrapper = QueryGenerator.initQueryWrapper(slRainWarnCur, req.getParameterMap());
        Page<SlRainWarnCur> page = new Page<SlRainWarnCur>(pageNo, pageSize);
        IPage<SlRainWarnCur> pageList = slRainWarnCurService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param slRainWarnCur
     * @return
     */
    @AutoLog(value = "降雨实时预警-添加")
    @ApiOperation(value = "降雨实时预警-添加", notes = "降雨实时预警-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRainWarnCur slRainWarnCur) {
        slRainWarnCurService.save(slRainWarnCur);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRainWarnCur
     * @return
     */
    @AutoLog(value = "降雨实时预警-编辑")
    @ApiOperation(value = "降雨实时预警-编辑", notes = "降雨实时预警-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRainWarnCur slRainWarnCur) {
        slRainWarnCurService.updateById(slRainWarnCur);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "降雨实时预警-通过id删除")
    @ApiOperation(value = "降雨实时预警-通过id删除", notes = "降雨实时预警-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRainWarnCurService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "降雨实时预警-批量删除")
    @ApiOperation(value = "降雨实时预警-批量删除", notes = "降雨实时预警-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRainWarnCurService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "降雨实时预警-通过id查询")
    @ApiOperation(value = "降雨实时预警-通过id查询", notes = "降雨实时预警-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRainWarnCur slRainWarnCur = slRainWarnCurService.getById(id);
        return Result.ok(slRainWarnCur);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRainWarnCur
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRainWarnCur slRainWarnCur) {
        return super.exportXls(request, slRainWarnCur, SlRainWarnCur.class, "降雨实时预警");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlRainWarnCur.class);
    }

}
