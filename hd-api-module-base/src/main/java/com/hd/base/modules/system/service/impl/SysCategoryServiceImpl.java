package com.hd.base.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.base.modules.system.entity.SysCategory;
import com.hd.base.modules.system.mapper.SysCategoryMapper;
import com.hd.base.modules.system.model.TreeSelectModel;
import com.hd.base.modules.system.service.ISysCategoryService;
import com.hd.common.constant.CommonConstant;
import com.hd.common.exception.JeecgBootException;
import com.hd.common.util.oConvertUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date: 2019-05-29
 * @Version: V1.0
 */
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory> implements ISysCategoryService {

    @Resource
    private SysCategoryMapper sysCategoryMapper;

    @Override
    public void addSysCategory(SysCategory sysCategory) {
        sysCategory.setCreateTime(new Date());
        //----------------------------------------------------------------------
        //判断是否是一级菜单，是的话清空父菜单
        if (CommonConstant.MENU_TYPE_0.equals(sysCategory.getGroupType())) {
            sysCategory.setPid(null);
            sysCategory.setLeaf(true);
            this.save(sysCategory);
            //一级菜单的话直接获取主键插入
            sysCategory.setRootId(sysCategory.getId());
            updateSysCategory(sysCategory);
        } else {
            String pid = sysCategory.getPid();
            sysCategory.setLeaf(true);
            if (oConvertUtils.isNotEmpty(pid)) {
                //设置父节点不为叶子节点
                this.sysCategoryMapper.setMenuLeaf(pid, 0);
            }
            //获取父类的rootId
            String rootIdById = this.sysCategoryMapper.queryRootIdById(pid);
            sysCategory.setRootId(rootIdById);
            this.save(sysCategory);

        }
    }

    @Override
    public void updateSysCategory(SysCategory sysCategory) {
        SysCategory p = this.getById(sysCategory.getId());
        //TODO 该节点判断是否还有子节点
        if (p == null) {
            throw new JeecgBootException("未找到分组信息");
        } else {
            //不能将自己设置成父组别
            if (sysCategory.getId().equalsIgnoreCase(sysCategory.getPid())) {
                throw new JeecgBootException("不能将自己设置成父组别");
            }
            sysCategory.setUpdateTime(new Date());
            //----------------------------------------------------------------------
            //Step1.判断是否是一级菜单，是的话清空父菜单ID
            if (CommonConstant.MENU_TYPE_0.equals(sysCategory.getGroupType())) {
                sysCategory.setPid("");
            }
            //Step2.判断菜单下级是否有菜单，无则设置为叶子节点
            int count = this.count(new QueryWrapper<SysCategory>().lambda().eq(SysCategory::getPid, sysCategory.getId()));
            if (count == 0) {
                sysCategory.setLeaf(true);
            }
            //如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
            String pid = sysCategory.getPid();
            if ((oConvertUtils.isNotEmpty(pid) && !pid.equals(p.getPid())) || oConvertUtils.isEmpty(pid) && oConvertUtils.isNotEmpty(p.getPid())) {
                //a.设置新的父菜单不为叶子节点
                this.sysCategoryMapper.setMenuLeaf(pid, 0);
                if (CommonConstant.MENU_TYPE_0.equals(sysCategory.getGroupType())) {
                    sysCategory.setRootId(sysCategory.getId());
                } else {
                    String rootIdById = this.sysCategoryMapper.queryRootIdById(pid);
                    sysCategory.setRootId(rootIdById);
                }
                //b.判断老的菜单下是否还有其他子菜单，没有的话则设置为叶子节点
                int cc = this.count(new QueryWrapper<SysCategory>().lambda().eq(SysCategory::getPid, p.getPid()));
                if (cc == 0) {
                    if (oConvertUtils.isNotEmpty(p.getPid())) {
                        this.sysCategoryMapper.setMenuLeaf(p.getPid(), 1);
                    }
                }

            }
            //----------------------------------------------------------------------
            this.updateById(sysCategory);
        }
    }

    @Override
    public List<TreeSelectModel> queryListByCode(String pcode) throws JeecgBootException {
        String pid = ROOT_PID_VALUE;
        if (oConvertUtils.isNotEmpty(pcode)) {
            List<SysCategory> list = baseMapper.selectList(new LambdaQueryWrapper<SysCategory>().eq(SysCategory::getCode, pcode));
            if (list == null || list.size() == 0) {
                throw new JeecgBootException("该编码【" + pcode + "】不存在，请核实!");
            }
            if (list.size() > 1) {
                throw new JeecgBootException("该编码【" + pcode + "】存在多个，请核实!");
            }
            pid = list.get(0).getId();
        }
        return baseMapper.queryListByPid(pid, null);
    }

    @Override
    public List<TreeSelectModel> queryListByPid(String pid) {
        if (oConvertUtils.isEmpty(pid)) {
            pid = ROOT_PID_VALUE;
        }
        return baseMapper.queryListByPid(pid, null);
    }

    @Override
    public List<TreeSelectModel> queryListByPid(String pid, Map<String, String> condition) {
        if (oConvertUtils.isEmpty(pid)) {
            pid = ROOT_PID_VALUE;
        }
        return baseMapper.queryListByPid(pid, condition);
    }

    @Override
    public String queryIdByCode(String code) {
        return baseMapper.queryIdByCode(code);
    }

    @Override
    public void deleteGroup(String id) throws JeecgBootException {
        SysCategory sysCategory = this.getById(id);
        if (sysCategory == null) {
            throw new JeecgBootException("未找到分组信息");
        }
        String pid = sysCategory.getPid();
        if (oConvertUtils.isNotEmpty(pid)) {
            int count = this.count(new QueryWrapper<SysCategory>().lambda().eq(SysCategory::getPid, pid));
            if (count == 1) {
                //若父节点无其他子节点，则该父节点是叶子节点
                this.sysCategoryMapper.setMenuLeaf(pid, 1);
            }
        }
        sysCategoryMapper.deleteById(sysCategory.getId());
        // 该节点可能是子节点但也可能是其它节点的父节点,所以需要级联删除
        this.removeChildrenBy(sysCategory.getId());
    }

    /**
     * 根据父id删除其关联的子节点数据
     *
     * @return
     */
    public void removeChildrenBy(String parentId) {
        LambdaQueryWrapper<SysCategory> query = new LambdaQueryWrapper<>();
        // 封装查询条件parentId为主键,
        query.eq(SysCategory::getPid, parentId);
        // 查出该主键下的所有子级
        List<SysCategory> sysCategories = this.list(query);
        if (sysCategories != null && sysCategories.size() > 0) {
            String id = ""; // id
            int num = 0; // 查出的子级数量
            // 如果查出的集合不为空, 则先删除所有
            this.remove(query);
            // 再遍历刚才查出的集合, 根据每个对象,查找其是否仍有子级
            for (int i = 0, len = sysCategories.size(); i < len; i++) {
                id = sysCategories.get(i).getId();
                num = this.count(new LambdaQueryWrapper<SysCategory>().eq(SysCategory::getPid, id));
                // 如果有, 则递归
                if (num > 0) {
                    this.removeChildrenBy(id);
                }
            }
        }
    }
}
