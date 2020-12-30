package org.jeecg.modules.system.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jeecg.modules.system.vo.TreeSelectsDataVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: sunday
 * @License: (C) Copyright 2020-2099, 深圳市宏电技术股份有限公司
 * @Contact: www.hongdian.com
 * @Date: 2020/5/12 13:57
 * @Version: 1.0
 * @Description: 树结构util
 */
public class TreesUtil {

    /**
     * 根节点对象存放集合
     *
     */
    private static List<TreeSelectsDataVO> rootList;

    /**
     * 其他节点存放集合(可以包含根节点)
     *
     */
    private static List<TreeSelectsDataVO> bodyList; // 其他节点存放到这里，可以包含根节点

    /**
     * 设置数据
     * @param rootList
     * @param bodyList
     */
    public static void setTreeData(List<TreeSelectsDataVO> rootList, List<TreeSelectsDataVO> bodyList) {
        TreesUtil.rootList = rootList;
        TreesUtil.bodyList = bodyList;
    }


    /**
     *    调用的方法入口
     */
    public static List<TreeSelectsDataVO> getTree() {
        if (bodyList != null && !bodyList.isEmpty()) {
            // 声明一个map，用来过滤已操作过的数据
            Map<String, String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(treeSelectsDataVo -> {
                getChild(treeSelectsDataVo, map);
            });
            return rootList;
        }
        return null;
    }
    /**
     * 递归获取子节点
     * @param treeSelectsDataVo
     * @param map
     */
    public static void getChild(TreeSelectsDataVO treeSelectsDataVo, Map<String, String> map) {
        List<TreeSelectsDataVO> childList = Lists.newArrayList();
        bodyList.stream()
                //一个人多个角色的时候不需要去重
//                .filter(c -> !map.containsKey(c.getId()))
                .filter(c -> c.getPid().equals(treeSelectsDataVo.getId()))
                .forEach(e -> {
                    map.put(e.getId(), e.getPid());
                    getChild(e, map);
                    childList.add(e);
                });
        treeSelectsDataVo.setChildren(childList);
    }



}
