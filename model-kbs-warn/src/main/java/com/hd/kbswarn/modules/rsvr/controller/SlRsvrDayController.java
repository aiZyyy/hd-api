package com.hd.kbswarn.modules.rsvr.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrDay;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrDayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 水库水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="水库水情日极值")
@RestController
@RequestMapping("/rsvr/slRsvrDay")
public class SlRsvrDayController extends HdController<SlRsvrDay, ISlRsvrDayService> {
	@Autowired
	private ISlRsvrDayService slRsvrDayService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRsvrDay
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-分页列表查询")
	@ApiOperation(value="水库水情日极值-分页列表查询", notes="水库水情日极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRsvrDay slRsvrDay,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRsvrDay> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrDay, req.getParameterMap());
		Page<SlRsvrDay> page = new Page<SlRsvrDay>(pageNo, pageSize);
		IPage<SlRsvrDay> pageList = slRsvrDayService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRsvrDay
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-添加")
	@ApiOperation(value="水库水情日极值-添加", notes="水库水情日极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRsvrDay slRsvrDay) {
		slRsvrDayService.save(slRsvrDay);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRsvrDay
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-编辑")
	@ApiOperation(value="水库水情日极值-编辑", notes="水库水情日极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRsvrDay slRsvrDay) {
		slRsvrDayService.updateById(slRsvrDay);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-通过id删除")
	@ApiOperation(value="水库水情日极值-通过id删除", notes="水库水情日极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRsvrDayService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-批量删除")
	@ApiOperation(value="水库水情日极值-批量删除", notes="水库水情日极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRsvrDayService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情日极值-通过id查询")
	@ApiOperation(value="水库水情日极值-通过id查询", notes="水库水情日极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRsvrDay slRsvrDay = slRsvrDayService.getById(id);
		return Result.ok(slRsvrDay);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRsvrDay
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRsvrDay slRsvrDay) {
      return super.exportXls(request, slRsvrDay, SlRsvrDay.class, "水库水情日极值");
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
      return super.importExcel(request, response, SlRsvrDay.class);
  }

}
