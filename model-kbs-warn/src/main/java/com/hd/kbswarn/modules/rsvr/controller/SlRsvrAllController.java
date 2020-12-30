package com.hd.kbswarn.modules.rsvr.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hd.common.api.vo.Result;
import com.hd.common.system.query.QueryGenerator;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrAll;
import com.hd.kbswarn.modules.rsvr.service.ISlRsvrAllService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 水库水情历史极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="水库水情历史极值")
@RestController
@RequestMapping("/rsvr/slRsvrAll")
public class SlRsvrAllController extends JeecgController<SlRsvrAll, ISlRsvrAllService> {
	@Autowired
	private ISlRsvrAllService slRsvrAllService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRsvrAll
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-分页列表查询")
	@ApiOperation(value="水库水情历史极值-分页列表查询", notes="水库水情历史极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRsvrAll slRsvrAll,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRsvrAll> queryWrapper = QueryGenerator.initQueryWrapper(slRsvrAll, req.getParameterMap());
		Page<SlRsvrAll> page = new Page<SlRsvrAll>(pageNo, pageSize);
		IPage<SlRsvrAll> pageList = slRsvrAllService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRsvrAll
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-添加")
	@ApiOperation(value="水库水情历史极值-添加", notes="水库水情历史极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRsvrAll slRsvrAll) {
		slRsvrAllService.save(slRsvrAll);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRsvrAll
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-编辑")
	@ApiOperation(value="水库水情历史极值-编辑", notes="水库水情历史极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRsvrAll slRsvrAll) {
		slRsvrAllService.updateById(slRsvrAll);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-通过id删除")
	@ApiOperation(value="水库水情历史极值-通过id删除", notes="水库水情历史极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRsvrAllService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-批量删除")
	@ApiOperation(value="水库水情历史极值-批量删除", notes="水库水情历史极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRsvrAllService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "水库水情历史极值-通过id查询")
	@ApiOperation(value="水库水情历史极值-通过id查询", notes="水库水情历史极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRsvrAll slRsvrAll = slRsvrAllService.getById(id);
		return Result.ok(slRsvrAll);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRsvrAll
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRsvrAll slRsvrAll) {
      return super.exportXls(request, slRsvrAll, SlRsvrAll.class, "水库水情历史极值");
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
      return super.importExcel(request, response, SlRsvrAll.class);
  }

}
