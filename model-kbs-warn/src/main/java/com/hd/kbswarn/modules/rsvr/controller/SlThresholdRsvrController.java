package com.hd.kbswarn.modules.rsvr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlThresholdRsvr;
import com.hd.kbswarn.modules.rsvr.service.ISlThresholdRsvrService;
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
 * @Description: 水库水情阈值
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "水库水情阈值")
@RestController
@RequestMapping("/rsvr/slThresholdRsvr")
public class SlThresholdRsvrController extends HdController<SlThresholdRsvr, ISlThresholdRsvrService> {
    @Autowired
    private ISlThresholdRsvrService slThresholdRsvrService;

    /**
     * 分页列表查询
     *
     * @param slThresholdRsvr
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "水库水情阈值-分页列表查询")
    @ApiOperation(value = "水库水情阈值-分页列表查询", notes = "水库水情阈值-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlThresholdRsvr slThresholdRsvr,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlThresholdRsvr> queryWrapper = QueryGenerator.initQueryWrapper(slThresholdRsvr, req.getParameterMap());
        Page<SlThresholdRsvr> page = new Page<SlThresholdRsvr>(pageNo, pageSize);
        IPage<SlThresholdRsvr> pageList = slThresholdRsvrService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @AutoLog(value = "水库预警阈值-阈值查询")
    @ApiOperation(value = "水库预警阈值-阈值查询", notes = "水库预警阈值-阈值查询")
    @GetMapping(value = "/tsRsvr")
    public Result<?> queryPageList(SlThresholdRsvr slThresholdRsvr, HttpServletRequest req) {
        QueryWrapper<SlThresholdRsvr> queryWrapper = QueryGenerator.initQueryWrapper(slThresholdRsvr, req.getParameterMap());
        SlThresholdRsvr selectOne = slThresholdRsvrService.getBaseMapper().selectOne(queryWrapper);
        return Result.OK(selectOne);
    }

    /**
     * 添加
     *
     * @param slThresholdRsvr
     * @return
     */
    @AutoLog(value = "水库水情阈值-添加")
    @ApiOperation(value = "水库水情阈值-添加", notes = "水库水情阈值-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlThresholdRsvr slThresholdRsvr) {
        slThresholdRsvrService.save(slThresholdRsvr);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slThresholdRsvr
     * @return
     */
    @AutoLog(value = "水库水情阈值-编辑")
    @ApiOperation(value = "水库水情阈值-编辑", notes = "水库水情阈值-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlThresholdRsvr slThresholdRsvr) {
        slThresholdRsvrService.updateById(slThresholdRsvr);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "水库水情阈值-通过id删除")
    @ApiOperation(value = "水库水情阈值-通过id删除", notes = "水库水情阈值-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slThresholdRsvrService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "水库水情阈值-批量删除")
    @ApiOperation(value = "水库水情阈值-批量删除", notes = "水库水情阈值-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slThresholdRsvrService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "水库水情阈值-通过id查询")
    @ApiOperation(value = "水库水情阈值-通过id查询", notes = "水库水情阈值-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlThresholdRsvr slThresholdRsvr = slThresholdRsvrService.getById(id);
        return Result.ok(slThresholdRsvr);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slThresholdRsvr
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlThresholdRsvr slThresholdRsvr) {
        return super.exportXls(request, slThresholdRsvr, SlThresholdRsvr.class, "水库水情阈值");
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
        return super.importExcel(request, response, SlThresholdRsvr.class);
    }

}
