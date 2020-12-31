package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainYear;
import com.hd.kbswarn.modules.rain.service.ISlRainYearService;
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
 * @Description: 年降雨量
 * @Author: FishCoder
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "年降雨量")
@RestController
@RequestMapping("/rain/slRainYear")
public class SlRainYearController extends HdController<SlRainYear, ISlRainYearService> {
    @Autowired
    private ISlRainYearService slRainYearService;

    /**
     * 分页列表查询
     *
     * @param slRainYear
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "年降雨量-分页列表查询")
    @ApiOperation(value = "年降雨量-分页列表查询", notes = "年降雨量-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRainYear slRainYear,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRainYear> queryWrapper = QueryGenerator.initQueryWrapper(slRainYear, req.getParameterMap());
        Page<SlRainYear> page = new Page<SlRainYear>(pageNo, pageSize);
        IPage<SlRainYear> pageList = slRainYearService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @AutoLog(value = "年降雨量-list查询")
    @ApiOperation(value = "年降雨量-list查询", notes = "年降雨量-list查询")
    @GetMapping(value = "/listByStcd")
    public Result<?> listByStcd(@RequestParam(name = "stcd", required = true) String stcd,
                                @RequestParam(name = "tm_begin", required = true) String tm_begin,
                                @RequestParam(name = "tm_end", required = true) String tm_end) {
        return slRainYearService.listByStcd(stcd, tm_begin, tm_end);
    }

    /**
     * 添加
     *
     * @param slRainYear
     * @return
     */
    @AutoLog(value = "年降雨量-添加")
    @ApiOperation(value = "年降雨量-添加", notes = "年降雨量-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRainYear slRainYear) {
        slRainYearService.save(slRainYear);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRainYear
     * @return
     */
    @AutoLog(value = "年降雨量-编辑")
    @ApiOperation(value = "年降雨量-编辑", notes = "年降雨量-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRainYear slRainYear) {
        slRainYearService.updateById(slRainYear);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "年降雨量-通过id删除")
    @ApiOperation(value = "年降雨量-通过id删除", notes = "年降雨量-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRainYearService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "年降雨量-批量删除")
    @ApiOperation(value = "年降雨量-批量删除", notes = "年降雨量-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRainYearService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "年降雨量-通过id查询")
    @ApiOperation(value = "年降雨量-通过id查询", notes = "年降雨量-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRainYear slRainYear = slRainYearService.getById(id);
        return Result.ok(slRainYear);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRainYear
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRainYear slRainYear) {
        return super.exportXls(request, slRainYear, SlRainYear.class, "年降雨量");
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
        return super.importExcel(request, response, SlRainYear.class);
    }

}
