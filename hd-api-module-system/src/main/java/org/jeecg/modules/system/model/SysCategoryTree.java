package org.jeecg.modules.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jeecg.modules.system.entity.SysCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @Author ZY
 */
@NoArgsConstructor
@Setter
@Getter
public class SysCategoryTree implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;

    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 父级节点
     */
    private java.lang.String pid;
    /**
     * 类型名称
     */
    private java.lang.String name;
    /**
     * 类型编码
     */
    private java.lang.String code;
    /**
     * 创建人
     */
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    private java.util.Date updateTime;
    /**
     * 根id
     */
    private java.lang.String rootId;

    /**
     * 菜单排序
     */
    private Double sortNo;

    private Integer groupType;

    /**
     * 是否叶子节点: 1:是 0:不是
     */
    private boolean isLeaf;

    private List<SysCategoryTree> children;

    public SysCategoryTree(SysCategory sysCategory) {
        this.id = sysCategory.getId();
        this.key = sysCategory.getId();
        this.pid = sysCategory.getPid();
        this.name = sysCategory.getName();
        this.code = sysCategory.getCode();
        this.createBy = sysCategory.getCreateBy();
        this.createTime = sysCategory.getCreateTime();
        this.updateBy = sysCategory.getUpdateBy();
        this.updateTime = sysCategory.getUpdateTime();
        this.rootId = sysCategory.getRootId();
        this.sortNo = sysCategory.getSortNo();
        this.groupType = sysCategory.getGroupType();
        this.isLeaf = sysCategory.isLeaf();
        if (!sysCategory.isLeaf()) {
            this.children = new ArrayList<>();
        }
    }

}
