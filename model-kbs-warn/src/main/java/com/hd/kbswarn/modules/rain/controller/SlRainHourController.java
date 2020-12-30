package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHour;
import com.hd.kbswarn.modules.rain.service.ISlRainHourService;
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
 * @Description: 小时降雨量统计
 * @Author: FishCoder
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="小时降雨量统计")
@RestController
@RequestMapping("/rain/slRainHour")
public class SlRainHourController extends JeecgController<SlRainHour, ISlRainHourService> {
	@Autowired
	private ISlRainHourService slRainHourService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRainHour
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-分页列表查询")
	@ApiOperation(value="小时降雨量统计-分页列表查询", notes="小时降雨量统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRainHour slRainHour,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRainHour> queryWrapper = QueryGenerator.initQueryWrapper(slRainHour, req.getParameterMap());
		Page<SlRainHour> page = new Page<SlRainHour>(pageNo, pageSize);
		IPage<SlRainHour> pageList = slRainHourService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @AutoLog(value = "小时降雨量统计-list查询")
	 @ApiOperation(value = "小时降雨量统计-list查询", notes = "小时降雨量统计-list查询")
	 @GetMapping(value = "/listByStcd")
	 public Result<?> listByStcd(@RequestParam(name = "stcd", required = true) String stcd,
								 @RequestParam(name = "tm_begin", required = true) String tm_begin,
								 @RequestParam(name = "tm_end", required = true) String tm_end) {
		 return slRainHourService.listByStcd(stcd, tm_begin, tm_end);
	 }
	
	/**
	 * 添加
	 *
	 * @param slRainHour
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-添加")
	@ApiOperation(value="小时降雨量统计-添加", notes="小时降雨量统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRainHour slRainHour) {
		slRainHourService.save(slRainHour);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRainHour
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-编辑")
	@ApiOperation(value="小时降雨量统计-编辑", notes="小时降雨量统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRainHour slRainHour) {
		slRainHourService.updateById(slRainHour);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-通过id删除")
	@ApiOperation(value="小时降雨量统计-通过id删除", notes="小时降雨量统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRainHourService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-批量删除")
	@ApiOperation(value="小时降雨量统计-批量删除", notes="小时降雨量统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRainHourService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "小时降雨量统计-通过id查询")
	@ApiOperation(value="小时降雨量统计-通过id查询", notes="小时降雨量统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRainHour slRainHour = slRainHourService.getById(id);
		return Result.ok(slRainHour);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRainHour
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRainHour slRainHour) {
      return super.exportXls(request, slRainHour, SlRainHour.class, "小时降雨量统计");
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
      return super.importExcel(request, response, SlRainHour.class);
  }

}
