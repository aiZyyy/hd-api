package com.hd.kbswarn.modules.rsvr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrYear;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrYearService;
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
 * @Description: 水库水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="水库水情年极值")
@RestController
@RequestMapping("/rsvr/slRsvrYear")
public class SlRsvrYearController extends JeecgController<SlRsvrYear, ISlRsvrYearService> {
	@Autowired
	private ISlRsvrYearService slRsvrYearService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRsvrYear
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-分页列表查询")
	@ApiOperation(value="水库水情年极值-分页列表查询", notes="水库水情年极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRsvrYear slRsvrYear,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRsvrYear> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrYear, req.getParameterMap());
		Page<SlRsvrYear> page = new Page<SlRsvrYear>(pageNo, pageSize);
		IPage<SlRsvrYear> pageList = slRsvrYearService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRsvrYear
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-添加")
	@ApiOperation(value="水库水情年极值-添加", notes="水库水情年极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRsvrYear slRsvrYear) {
		slRsvrYearService.save(slRsvrYear);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRsvrYear
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-编辑")
	@ApiOperation(value="水库水情年极值-编辑", notes="水库水情年极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRsvrYear slRsvrYear) {
		slRsvrYearService.updateById(slRsvrYear);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-通过id删除")
	@ApiOperation(value="水库水情年极值-通过id删除", notes="水库水情年极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRsvrYearService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-批量删除")
	@ApiOperation(value="水库水情年极值-批量删除", notes="水库水情年极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRsvrYearService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情年极值-通过id查询")
	@ApiOperation(value="水库水情年极值-通过id查询", notes="水库水情年极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRsvrYear slRsvrYear = slRsvrYearService.getById(id);
		return Result.ok(slRsvrYear);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRsvrYear
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRsvrYear slRsvrYear) {
      return super.exportXls(request, slRsvrYear, SlRsvrYear.class, "水库水情日极值");
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
      return super.importExcel(request, response, SlRsvrYear.class);
  }

}
