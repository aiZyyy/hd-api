package com.hd.kbswarn.modules.rain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHis;
import com.hd.kbswarn.modules.rain.service.ISlRainHisService;
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
 * @Description: 雨量历史
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="雨量历史")
@RestController
@RequestMapping("/rain/slRainHis")
public class SlRainHisController extends JeecgController<SlRainHis, ISlRainHisService> {
	@Autowired
	private ISlRainHisService slRainHisService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRainHis
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "雨量历史-分页列表查询")
	@ApiOperation(value="雨量历史-分页列表查询", notes="雨量历史-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRainHis slRainHis,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRainHis> queryWrapper = QueryGenerator.initQueryWrapper(slRainHis, req.getParameterMap());
		Page<SlRainHis> page = new Page<SlRainHis>(pageNo, pageSize);
		IPage<SlRainHis> pageList = slRainHisService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRainHis
	 * @return
	 */
	@AutoLog(value = "雨量历史-添加")
	@ApiOperation(value="雨量历史-添加", notes="雨量历史-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRainHis slRainHis) {
		slRainHisService.save(slRainHis);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRainHis
	 * @return
	 */
	@AutoLog(value = "雨量历史-编辑")
	@ApiOperation(value="雨量历史-编辑", notes="雨量历史-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRainHis slRainHis) {
		slRainHisService.updateById(slRainHis);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "雨量历史-通过id删除")
	@ApiOperation(value="雨量历史-通过id删除", notes="雨量历史-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRainHisService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "雨量历史-批量删除")
	@ApiOperation(value="雨量历史-批量删除", notes="雨量历史-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRainHisService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "雨量历史-通过id查询")
	@ApiOperation(value="雨量历史-通过id查询", notes="雨量历史-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRainHis slRainHis = slRainHisService.getById(id);
		return Result.ok(slRainHis);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRainHis
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRainHis slRainHis) {
      return super.exportXls(request, slRainHis, SlRainHis.class, "雨量历史");
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
      return super.importExcel(request, response, SlRainHis.class);
  }

}
