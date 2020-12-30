package com.hd.kbswarn.modules.river.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverDay;
import com.hd.kbswarn.modules.river.service.ISlRiverDayService;
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
 * @Description: 河道水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="河道水情日极值")
@RestController
@RequestMapping("/river/slRiverDay")
public class SlRiverDayController extends JeecgController<SlRiverDay, ISlRiverDayService> {
	@Autowired
	private ISlRiverDayService slRiverDayService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRiverDay
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-分页列表查询")
	@ApiOperation(value="河道水情日极值-分页列表查询", notes="河道水情日极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRiverDay slRiverDay,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRiverDay> queryWrapper = QueryGenerator.initQueryWrapper(slRiverDay, req.getParameterMap());
		Page<SlRiverDay> page = new Page<SlRiverDay>(pageNo, pageSize);
		IPage<SlRiverDay> pageList = slRiverDayService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRiverDay
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-添加")
	@ApiOperation(value="河道水情日极值-添加", notes="河道水情日极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRiverDay slRiverDay) {
		slRiverDayService.save(slRiverDay);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRiverDay
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-编辑")
	@ApiOperation(value="河道水情日极值-编辑", notes="河道水情日极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRiverDay slRiverDay) {
		slRiverDayService.updateById(slRiverDay);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-通过id删除")
	@ApiOperation(value="河道水情日极值-通过id删除", notes="河道水情日极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRiverDayService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-批量删除")
	@ApiOperation(value="河道水情日极值-批量删除", notes="河道水情日极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRiverDayService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "河道水情日极值-通过id查询")
	@ApiOperation(value="河道水情日极值-通过id查询", notes="河道水情日极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRiverDay slRiverDay = slRiverDayService.getById(id);
		return Result.ok(slRiverDay);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRiverDay
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRiverDay slRiverDay) {
      return super.exportXls(request, slRiverDay, SlRiverDay.class, "河道水情日极值");
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
      return super.importExcel(request, response, SlRiverDay.class);
  }

}
