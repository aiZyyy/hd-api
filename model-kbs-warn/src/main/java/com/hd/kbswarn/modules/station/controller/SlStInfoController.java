package com.hd.kbswarn.modules.station.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.station.domain.entity.SlStInfo;
import com.hd.kbswarn.modules.station.service.ISlStInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.JeecgController;
import com.hd.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 测站基本属性
 * @Author: jeecg-boot
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "测站基本属性")
@RestController
@RequestMapping("/station/slStInfo")
public class SlStInfoController extends JeecgController<SlStInfo, ISlStInfoService> {
    @Autowired
    private ISlStInfoService slStInfoService;

    /**
     * 分页列表查询
     *
     * @param slStInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "测站基本属性-分页列表查询")
    @ApiOperation(value = "测站基本属性-分页列表查询", notes = "测站基本属性-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlStInfo slStInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SlStInfo> queryWrapper = QueryGenerator.initQueryWrapper(slStInfo, req.getParameterMap());
        Page<SlStInfo> page = new Page<SlStInfo>(pageNo, pageSize);
        IPage<SlStInfo> pageList = slStInfoService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @AutoLog(value = "测站基本属性-根据测站类型查询")
    @ApiOperation(value = "测站基本属性-根据测站类型查询", notes = "测站基本属性-根据测站类型查询")
    @GetMapping(value = "/listBySttp")
    public Result<?> listBySttp(@RequestParam(name = "sttp") String sttp) {
        return slStInfoService.listBySttp(sttp);
    }

    /**
     * 添加
     *
     * @param slStInfo
     * @return
     */
    @AutoLog(value = "测站基本属性-添加")
    @ApiOperation(value = "测站基本属性-添加", notes = "测站基本属性-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlStInfo slStInfo) {
        slStInfoService.save(slStInfo);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slStInfo
     * @return
     */
    @AutoLog(value = "测站基本属性-编辑")
    @ApiOperation(value = "测站基本属性-编辑", notes = "测站基本属性-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlStInfo slStInfo) {
        slStInfoService.updateById(slStInfo);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "测站基本属性-通过id删除")
    @ApiOperation(value = "测站基本属性-通过id删除", notes = "测站基本属性-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slStInfoService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "测站基本属性-批量删除")
    @ApiOperation(value = "测站基本属性-批量删除", notes = "测站基本属性-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slStInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "测站基本属性-通过id查询")
    @ApiOperation(value = "测站基本属性-通过id查询", notes = "测站基本属性-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlStInfo slStInfo = slStInfoService.getById(id);
        return Result.ok(slStInfo);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slStInfo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlStInfo slStInfo) {
        return super.exportXls(request, slStInfo, SlStInfo.class, "测站基本属性");
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
        return super.importExcel(request, response, SlStInfo.class);
    }

}
