package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainDay;
import com.hd.kbswarn.modules.rain.service.ISlRainDayService;
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
 * @Description: 日降雨量统计
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="日降雨量统计")
@RestController
@RequestMapping("/rain/slRainDay")
public class SlRainDayController extends HdController<SlRainDay, ISlRainDayService> {
	@Autowired
	private ISlRainDayService slRainDayService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRainDay
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-分页列表查询")
	@ApiOperation(value="日降雨量统计-分页列表查询", notes="日降雨量统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRainDay slRainDay,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRainDay> queryWrapper = QueryGenerator.initQueryWrapper(slRainDay, req.getParameterMap());
		Page<SlRainDay> page = new Page<SlRainDay>(pageNo, pageSize);
		IPage<SlRainDay> pageList = slRainDayService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @AutoLog(value = "日降雨量统计-list查询")
	 @ApiOperation(value = "日降雨量统计-list查询", notes = "日降雨量统计-list查询")
	 @GetMapping(value = "/listByStcd")
	 public Result<?> listByStcd(@RequestParam(name = "stcd", required = true) String stcd,
								 @RequestParam(name = "tm_begin", required = true) String tm_begin,
								 @RequestParam(name = "tm_end", required = true) String tm_end) {
		 return slRainDayService.listByStcd(stcd, tm_begin, tm_end);
	 }

	/**
	 * 添加
	 *
	 * @param slRainDay
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-添加")
	@ApiOperation(value="日降雨量统计-添加", notes="日降雨量统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRainDay slRainDay) {
		slRainDayService.save(slRainDay);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRainDay
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-编辑")
	@ApiOperation(value="日降雨量统计-编辑", notes="日降雨量统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRainDay slRainDay) {
		slRainDayService.updateById(slRainDay);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-通过id删除")
	@ApiOperation(value="日降雨量统计-通过id删除", notes="日降雨量统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRainDayService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-批量删除")
	@ApiOperation(value="日降雨量统计-批量删除", notes="日降雨量统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRainDayService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "日降雨量统计-通过id查询")
	@ApiOperation(value="日降雨量统计-通过id查询", notes="日降雨量统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRainDay slRainDay = slRainDayService.getById(id);
		return Result.ok(slRainDay);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRainDay
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRainDay slRainDay) {
      return super.exportXls(request, slRainDay, SlRainDay.class, "日降雨量统计");
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
      return super.importExcel(request, response, SlRainDay.class);
  }

}
