package com.wxq.core;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 *
 * 具有层级关系的对象接口
 **/
public interface Tree {

    /**
     * 获取当前节点ID
     * @return 节点ID
     */
    String getCurrentNodeId();

    /**
     * 获取当前节点上级别ID
     * @return 父级ID
     */
    String getParentNodeId();
}
