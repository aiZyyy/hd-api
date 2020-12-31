package com.hd.kbswarn.modules.rsvr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrCur;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrCurService;
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
 * @Description: 水库水情实时
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "水库水情实时")
@RestController
@RequestMapping("/rsvr/slRsvrCur")
public class SlRsvrCurController extends HdController<SlRsvrCur, ISlRsvrCurService> {
    @Autowired
    private ISlRsvrCurService slRsvrCurService;

    /**
     * 分页列表查询
     *
     * @param slRsvrCur
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "水库水情实时-分页列表查询")
    @ApiOperation(value = "水库水情实时-分页列表查询", notes = "水库水情实时-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRsvrCur slRsvrCur,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRsvrCur> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrCur, req.getParameterMap());
        Page<SlRsvrCur> page = new Page<>(pageNo, pageSize);
        IPage<SlRsvrCur> pageList = slRsvrCurService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 首页list查询
     *
     * @param req
     * @return
     */
    @AutoLog(value = "水库水情实时-首页list查询")
    @ApiOperation(value = "水库水情实时-首页list查询", notes = "水库水情实时-首页list查询")
    @GetMapping(value = "/homeList")
    public Result<?> homeList(HttpServletRequest req) {
        return slRsvrCurService.homeList(req);
    }

    /**
     * 添加
     *
     * @param slRsvrCur
     * @return
     */
    @AutoLog(value = "水库水情实时-添加")
    @ApiOperation(value = "水库水情实时-添加", notes = "水库水情实时-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRsvrCur slRsvrCur) {
        slRsvrCurService.save(slRsvrCur);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRsvrCur
     * @return
     */
    @AutoLog(value = "水库水情实时-编辑")
    @ApiOperation(value = "水库水情实时-编辑", notes = "水库水情实时-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRsvrCur slRsvrCur) {
        slRsvrCurService.updateById(slRsvrCur);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "水库水情实时-通过id删除")
    @ApiOperation(value = "水库水情实时-通过id删除", notes = "水库水情实时-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRsvrCurService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "水库水情实时-批量删除")
    @ApiOperation(value = "水库水情实时-批量删除", notes = "水库水情实时-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRsvrCurService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "水库水情实时-通过id查询")
    @ApiOperation(value = "水库水情实时-通过id查询", notes = "水库水情实时-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRsvrCur slRsvrCur = slRsvrCurService.getById(id);
        return Result.ok(slRsvrCur);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRsvrCur
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRsvrCur slRsvrCur) {
        return super.exportXls(request, slRsvrCur, SlRsvrCur.class, "水库水情实时");
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
        return super.importExcel(request, response, SlRsvrCur.class);
    }

}
