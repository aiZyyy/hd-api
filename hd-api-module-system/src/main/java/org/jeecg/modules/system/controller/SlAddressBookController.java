package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hd.common.api.vo.Result;
import com.hd.common.aspect.annotation.AutoLog;
import com.hd.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.SlAddressBook;
import org.jeecg.modules.system.service.ISlAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 通讯录管理
 * @Author: FishCoder
 * @Date: 2020-12-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "通讯录管理")
@RestController
@RequestMapping("/system/slAddressBook")
public class SlAddressBookController extends JeecgController<SlAddressBook, ISlAddressBookService> {
    @Autowired
    private ISlAddressBookService slAddressBookService;

    /**
     * 分页列表查询
     *
     * @param slAddressBook
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "通讯录管理-分页列表查询")
    @ApiOperation(value = "通讯录管理-分页列表查询", notes = "通讯录管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SlAddressBook slAddressBook,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Page<SlAddressBook> page = new Page<SlAddressBook>(pageNo, pageSize);
        return slAddressBookService.queryPageList(page, req);
    }

    /**
     * 添加
     *
     * @param slAddressBook
     * @return
     */
    @AutoLog(value = "通讯录管理-添加")
    @ApiOperation(value = "通讯录管理-添加", notes = "通讯录管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SlAddressBook slAddressBook) {
        slAddressBookService.save(slAddressBook);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param slAddressBook
     * @return
     */
    @AutoLog(value = "通讯录管理-编辑")
    @ApiOperation(value = "通讯录管理-编辑", notes = "通讯录管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SlAddressBook slAddressBook) {
        slAddressBookService.updateById(slAddressBook);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通讯录管理-通过id删除")
    @ApiOperation(value = "通讯录管理-通过id删除", notes = "通讯录管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        slAddressBookService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "通讯录管理-批量删除")
    @ApiOperation(value = "通讯录管理-批量删除", notes = "通讯录管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.slAddressBookService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通讯录管理-通过id查询")
    @ApiOperation(value = "通讯录管理-通过id查询", notes = "通讯录管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SlAddressBook slAddressBook = slAddressBookService.getById(id);
        return Result.ok(slAddressBook);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param slAddressBook
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlAddressBook slAddressBook) {
        return super.exportXls(request, slAddressBook, SlAddressBook.class, "通讯录管理");
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
        return super.importExcel(request, response, SlAddressBook.class);
    }

}
