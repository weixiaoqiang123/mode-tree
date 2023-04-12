package com.wxq.modeltree.core;

/**
 * @author weixiaoqiang
 * @date 2023/4/12
 *
 *  虚拟根节点
 **/
public class RootNode implements Tree {

    private String nodeId;

    private String parentNodeId;

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String getCurrentNodeId() {
        return nodeId;
    }

    @Override
    public String getParentNodeId() {
        return null;
    }
}
