package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainCur;
import com.hd.kbswarn.modules.rain.service.ISlRainCurService;
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
 * @Description: 降雨实时
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "降雨实时")
@RestController
@RequestMapping("/rain/slRainCur")
public class SlRainCurController extends HdController<SlRainCur, ISlRainCurService> {
    @Autowired
    private ISlRainCurService slRainCurService;

    /**
     * 分页列表查询
     *
     * @param slRainCur
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "降雨实时-分页列表查询")
    @ApiOperation(value = "降雨实时-分页列表查询", notes = "降雨实时-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRainCur slRainCur,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRainCur> queryWrapper = QueryGenerator.initQueryWrapper(slRainCur, req.getParameterMap());
        Page<SlRainCur> page = new Page<SlRainCur>(pageNo, pageSize);
        IPage<SlRainCur> pageList = slRainCurService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 首页list查询
     *
     * @param req
     * @return
     */
    @AutoLog(value = "降雨实时-首页list查询")
    @ApiOperation(value = "降雨实时-首页list查询", notes = "降雨实时-首页list查询")
    @GetMapping(value = "/homeList")
    public Result<?> homeList(HttpServletRequest req) {
        return slRainCurService.homeList(req);
    }

    /**
     * 添加
     *
     * @param slRainCur
     * @return
     */
    @AutoLog(value = "降雨实时-添加")
    @ApiOperation(value = "降雨实时-添加", notes = "降雨实时-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRainCur slRainCur) {
        slRainCurService.save(slRainCur);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRainCur
     * @return
     */
    @AutoLog(value = "降雨实时-编辑")
    @ApiOperation(value = "降雨实时-编辑", notes = "降雨实时-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRainCur slRainCur) {
        slRainCurService.updateById(slRainCur);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "降雨实时-通过id删除")
    @ApiOperation(value = "降雨实时-通过id删除", notes = "降雨实时-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRainCurService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "降雨实时-批量删除")
    @ApiOperation(value = "降雨实时-批量删除", notes = "降雨实时-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRainCurService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "降雨实时-通过id查询")
    @ApiOperation(value = "降雨实时-通过id查询", notes = "降雨实时-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRainCur slRainCur = slRainCurService.getById(id);
        return Result.ok(slRainCur);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRainCur
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRainCur slRainCur) {
        return super.exportXls(request, slRainCur, SlRainCur.class, "降雨实时");
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
        return super.importExcel(request, response, SlRainCur.class);
    }

}
