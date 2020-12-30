package com.hd.kbswarn.modules.river.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hd.common.api.vo.Result;
import com.hd.common.system.query.QueryGenerator;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverYear;
import com.hd.kbswarn.modules.river.service.ISlRiverYearService;
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
 * @Description: 河道水情年极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="河道水情年极值")
@RestController
@RequestMapping("/river/slRiverYear")
public class SlRiverYearController extends JeecgController<SlRiverYear, ISlRiverYearService> {
	@Autowired
	private ISlRiverYearService slRiverYearService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slRiverYear
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-分页列表查询")
	@ApiOperation(value="河道水情年极值-分页列表查询", notes="河道水情年极值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SlRiverYear slRiverYear,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlRiverYear> queryWrapper = QueryGenerator.initQueryWrapper(slRiverYear, req.getParameterMap());
		Page<SlRiverYear> page = new Page<SlRiverYear>(pageNo, pageSize);
		IPage<SlRiverYear> pageList = slRiverYearService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param slRiverYear
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-添加")
	@ApiOperation(value="河道水情年极值-添加", notes="河道水情年极值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SlRiverYear slRiverYear) {
		slRiverYearService.save(slRiverYear);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param slRiverYear
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-编辑")
	@ApiOperation(value="河道水情年极值-编辑", notes="河道水情年极值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SlRiverYear slRiverYear) {
		slRiverYearService.updateById(slRiverYear);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-通过id删除")
	@ApiOperation(value="河道水情年极值-通过id删除", notes="河道水情年极值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		slRiverYearService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-批量删除")
	@ApiOperation(value="河道水情年极值-批量删除", notes="河道水情年极值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slRiverYearService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "河道水情年极值-通过id查询")
	@ApiOperation(value="河道水情年极值-通过id查询", notes="河道水情年极值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SlRiverYear slRiverYear = slRiverYearService.getById(id);
		return Result.ok(slRiverYear);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param slRiverYear
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SlRiverYear slRiverYear) {
      return super.exportXls(request, slRiverYear, SlRiverYear.class, "河道水情年极值");
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
      return super.importExcel(request, response, SlRiverYear.class);
  }

}
