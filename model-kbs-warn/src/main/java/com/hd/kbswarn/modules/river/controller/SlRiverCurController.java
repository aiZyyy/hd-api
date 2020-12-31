package com.hd.kbswarn.modules.river.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverCur;
import com.hd.kbswarn.modules.river.service.ISlRiverCurService;
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
 * @Description: 河道水情实时表
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "河道水情实时表")
@RestController
@RequestMapping("/river/slRiverCur")
public class SlRiverCurController extends HdController<SlRiverCur, ISlRiverCurService> {
    @Autowired
    private ISlRiverCurService slRiverCurService;

    /**
     * 分页列表查询
     *
     * @param slRiverCur
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "河道水情实时表-分页列表查询")
    @ApiOperation(value = "河道水情实时表-分页列表查询", notes = "河道水情实时表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRiverCur slRiverCur,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRiverCur> queryWrapper = QueryGenerator.initQueryWrapper(slRiverCur, req.getParameterMap());
        Page<SlRiverCur> page = new Page<>(pageNo, pageSize);
        IPage<SlRiverCur> pageList = slRiverCurService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 首页list查询
     *
     * @param req
     * @return
     */
    @AutoLog(value = "河道水情实时表-首页list查询")
    @ApiOperation(value = "河道水情实时表-首页list查询", notes = "河道水情实时表-首页list查询")
    @GetMapping(value = "/homeList")
    public Result<?> homeList(HttpServletRequest req) {
        return slRiverCurService.homeList(req);
    }

    /**
     * 添加
     *
     * @param slRiverCur
     * @return
     */
    @AutoLog(value = "河道水情实时表-添加")
    @ApiOperation(value = "河道水情实时表-添加", notes = "河道水情实时表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRiverCur slRiverCur) {
        slRiverCurService.save(slRiverCur);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRiverCur
     * @return
     */
    @AutoLog(value = "河道水情实时表-编辑")
    @ApiOperation(value = "河道水情实时表-编辑", notes = "河道水情实时表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRiverCur slRiverCur) {
        slRiverCurService.updateById(slRiverCur);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "河道水情实时表-通过id删除")
    @ApiOperation(value = "河道水情实时表-通过id删除", notes = "河道水情实时表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRiverCurService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "河道水情实时表-批量删除")
    @ApiOperation(value = "河道水情实时表-批量删除", notes = "河道水情实时表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRiverCurService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "河道水情实时表-通过id查询")
    @ApiOperation(value = "河道水情实时表-通过id查询", notes = "河道水情实时表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRiverCur slRiverCur = slRiverCurService.getById(id);
        return Result.ok(slRiverCur);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRiverCur
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRiverCur slRiverCur) {
        return super.exportXls(request, slRiverCur, SlRiverCur.class, "河道水情实时表");
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
        return super.importExcel(request, response, SlRiverCur.class);
    }

}
