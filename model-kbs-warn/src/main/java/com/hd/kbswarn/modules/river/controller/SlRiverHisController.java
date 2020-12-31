package com.hd.kbswarn.modules.river.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverHis;
import com.hd.kbswarn.modules.river.service.ISlRiverHisService;
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
 * @Description: 河道水情历史表
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "河道水情历史表")
@RestController
@RequestMapping("/river/slRiverHis")
public class SlRiverHisController extends HdController<SlRiverHis, ISlRiverHisService> {
    @Autowired
    private ISlRiverHisService slRiverHisService;

    /**
     * 分页列表查询
     *
     * @param slRiverHis
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "河道水情历史表-分页列表查询")
    @ApiOperation(value = "河道水情历史表-分页列表查询", notes = "河道水情历史表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlRiverHis slRiverHis,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlRiverHis> queryWrapper = QueryGenerator.initQueryWrapper(slRiverHis, req.getParameterMap());
        Page<SlRiverHis> page = new Page<>(pageNo, pageSize);
        IPage<SlRiverHis> pageList = slRiverHisService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @AutoLog(value = "河道水情历史表-list查询")
    @ApiOperation(value = "河道水情历史表-list查询", notes = "河道水情历史表-list查询")
    @GetMapping(value = "/listByStcd")
    public Result<?> listByStcd(@RequestParam(name = "stcd", required = true) String stcd,
                                @RequestParam(name = "tm_begin", required = true) String tm_begin,
                                @RequestParam(name = "tm_end", required = true) String tm_end) {
        return slRiverHisService.listByStcd(stcd, tm_begin, tm_end);
    }

    /**
     * 添加
     *
     * @param slRiverHis
     * @return
     */
    @AutoLog(value = "河道水情历史表-添加")
    @ApiOperation(value = "河道水情历史表-添加", notes = "河道水情历史表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlRiverHis slRiverHis) {
        slRiverHisService.save(slRiverHis);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slRiverHis
     * @return
     */
    @AutoLog(value = "河道水情历史表-编辑")
    @ApiOperation(value = "河道水情历史表-编辑", notes = "河道水情历史表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlRiverHis slRiverHis) {
        slRiverHisService.updateById(slRiverHis);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "河道水情历史表-通过id删除")
    @ApiOperation(value = "河道水情历史表-通过id删除", notes = "河道水情历史表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slRiverHisService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "河道水情历史表-批量删除")
    @ApiOperation(value = "河道水情历史表-批量删除", notes = "河道水情历史表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slRiverHisService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "河道水情历史表-通过id查询")
    @ApiOperation(value = "河道水情历史表-通过id查询", notes = "河道水情历史表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlRiverHis slRiverHis = slRiverHisService.getById(id);
        return Result.ok(slRiverHis);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slRiverHis
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlRiverHis slRiverHis) {
        return super.exportXls(request, slRiverHis, SlRiverHis.class, "河道水情历史表");
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
        return super.importExcel(request, response, SlRiverHis.class);
    }

}
