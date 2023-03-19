package com.wxq.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 *
 * 树节点对象
 **/
public class TreeNode extends HashMap<String, Object> {

    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(){
        this.put("children", children);
    }

    public void appendChild(TreeNode child) {
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
