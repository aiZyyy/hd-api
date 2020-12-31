package com.hd.kbswarn.modules.rsvr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.HdController;
import com.hd.common.system.query.QueryGenerator;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrMonth;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrMonthService;
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
 * @Description: 水库水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="水库水情月极值")
@RestController
@RequestMapping("/rsvr/slRsvrMonth")
public class SlRsvrMonthController extends HdController<SlRsvrMonth, ISlRsvrMonthService> {
	@Autowired
	private ISlRsvrMonthService slRsvrMonthService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRsvrMonth
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-分页列表查询")
	@ApiOperation(value="水库水情月极值-分页列表查询", notes="水库水情月极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRsvrMonth slRsvrMonth,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRsvrMonth> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrMonth, req.getParameterMap());
		Page<SlRsvrMonth> page = new Page<SlRsvrMonth>(pageNo, pageSize);
		IPage<SlRsvrMonth> pageList = slRsvrMonthService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRsvrMonth
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-添加")
	@ApiOperation(value="水库水情月极值-添加", notes="水库水情月极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRsvrMonth slRsvrMonth) {
		slRsvrMonthService.save(slRsvrMonth);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRsvrMonth
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-编辑")
	@ApiOperation(value="水库水情月极值-编辑", notes="水库水情月极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRsvrMonth slRsvrMonth) {
		slRsvrMonthService.updateById(slRsvrMonth);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-通过id删除")
	@ApiOperation(value="水库水情月极值-通过id删除", notes="水库水情月极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRsvrMonthService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-批量删除")
	@ApiOperation(value="水库水情月极值-批量删除", notes="水库水情月极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRsvrMonthService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情月极值-通过id查询")
	@ApiOperation(value="水库水情月极值-通过id查询", notes="水库水情月极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRsvrMonth slRsvrMonth = slRsvrMonthService.getById(id);
		return Result.ok(slRsvrMonth);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRsvrMonth
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRsvrMonth slRsvrMonth) {
      return super.exportXls(request, slRsvrMonth, SlRsvrMonth.class, "水库水情日极值");
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
      return super.importExcel(request, response, SlRsvrMonth.class);
  }

}
