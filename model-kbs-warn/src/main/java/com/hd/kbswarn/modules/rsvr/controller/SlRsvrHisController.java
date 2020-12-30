package com.hd.kbswarn.modules.rsvr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrHis;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrHisService;
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
 * @Description: 水库水情历史
 * @Author: FishCoder
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="水库水情历史")
@RestController
@RequestMapping("/rsvr/slRsvrHis")
public class SlRsvrHisController extends JeecgController<SlRsvrHis, ISlRsvrHisService> {
	@Autowired
	private ISlRsvrHisService slRsvrHisService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRsvrHis
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "水库水情历史-分页列表查询")
	@ApiOperation(value="水库水情历史-分页列表查询", notes="水库水情历史-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRsvrHis slRsvrHis,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRsvrHis> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrHis, req.getParameterMap());
		Page<SlRsvrHis> page = new Page<SlRsvrHis>(pageNo, pageSize);
		IPage<SlRsvrHis> pageList = slRsvrHisService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @AutoLog(value = "水库水情历史-list查询")
	 @ApiOperation(value = "水库水情历史-list查询", notes = "水库水情历史-list查询")
	 @GetMapping(value = "/listByStcd")
	 public Result<?> listByStcd(@RequestParam(name = "stcd", required = true) String stcd,
								 @RequestParam(name = "tm_begin", required = true) String tm_begin,
								 @RequestParam(name = "tm_end", required = true) String tm_end) {
		 return slRsvrHisService.listByStcd(stcd, tm_begin, tm_end);
	 }

	/**
	 * 添加
	 *
	 * @param slRsvrHis
	 * @return
	 */
	@AutoLog(value = "水库水情历史-添加")
	@ApiOperation(value="水库水情历史-添加", notes="水库水情历史-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRsvrHis slRsvrHis) {
		slRsvrHisService.save(slRsvrHis);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRsvrHis
	 * @return
	 */
	@AutoLog(value = "水库水情历史-编辑")
	@ApiOperation(value="水库水情历史-编辑", notes="水库水情历史-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRsvrHis slRsvrHis) {
		slRsvrHisService.updateById(slRsvrHis);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情历史-通过id删除")
	@ApiOperation(value="水库水情历史-通过id删除", notes="水库水情历史-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRsvrHisService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "水库水情历史-批量删除")
	@ApiOperation(value="水库水情历史-批量删除", notes="水库水情历史-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRsvrHisService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情历史-通过id查询")
	@ApiOperation(value="水库水情历史-通过id查询", notes="水库水情历史-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRsvrHis slRsvrHis = slRsvrHisService.getById(id);
		return Result.ok(slRsvrHis);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRsvrHis
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRsvrHis slRsvrHis) {
      return super.exportXls(request, slRsvrHis, SlRsvrHis.class, "水库水情历史");
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
      return super.importExcel(request, response, SlRsvrHis.class);
  }

}
