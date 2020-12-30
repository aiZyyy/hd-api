package com.hd.base.modules.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sunday
 * @License: (C) Copyright 2020-2099, 深圳市宏电技术股份有限公司
 * @Contact: sunday
 * @Date: 2020/3/16 13:34
 * @Version: 1.0
 * @Description: 树结构公用类
 */
@Data
public class TreeSelectsDataVO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 主键id
     */
    private String key;

    /**
     *标题
     */
    private String title;

    /**
     * 父id
     */
    private String pid;

    /**
     * 是否叶子节点
     */
    private boolean isLeaf;

    /**
     * 子节点
     */
    private List<TreeSelectsDataVO> children = new ArrayList<TreeSelectsDataVO>();

    /**
     * 类型
     */
    private String type;

    /**
     * 扩展字段1
     */
    private String realname;

    /**
     * 扩展字段2
     */
    private String phone;

    /**
     * 扩展字段3
     */
    private String userId;

    /**
     * 是否是用户
     */
    @TableField(exist = false)
    private boolean isUser;
}
