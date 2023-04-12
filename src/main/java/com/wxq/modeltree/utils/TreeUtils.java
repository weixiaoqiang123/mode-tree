package com.wxq.modeltree.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wxq.modeltree.core.RootNode;
import com.wxq.modeltree.core.Tree;
import com.wxq.modeltree.core.TreeNode;
import com.wxq.modeltree.exception.NoSuchRootNodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 **/
public class TreeUtils {

    public static <T extends Tree> TreeNode buildTree(List<T> nodes){
        return buildTree(nodes, null);
    }

    public static <T extends Tree> TreeNode buildTree(List<T> nodes, String rootNodeId){
        // 根树
        TreeNode rootTree = new TreeNode();
        if(nodes == null || nodes.isEmpty()){
            return rootTree;
        }

        rootNodeId = "".equals(rootNodeId) || null == rootNodeId ? "-1" : rootNodeId;
        Tree rootNode = findRootNode(nodes, rootNodeId);
        // 过滤节点ID为空的节点
        nodes = nodes.stream().filter(node -> !isEmptyNode(node)).collect(Collectors.toList());
        mountChildTreeNodes(rootTree, rootNode, nodes);
        return rootTree.getChildren().get(0);
    }

    public static <T> List<T> flatTree(TreeNode treeNode, Class<T> clazz){
        List<T> flatResults = new ArrayList<>();
        flatTreeNode(treeNode, clazz, flatResults);
        return flatResults;
    }

    private static <T extends Tree> TreeNode transformToTreeNode(T node){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList("currentNodeId", "parentNodeId"));
        SimplePropertyPreFilter[] filters = new SimplePropertyPreFilter[]{filter};
        return JSON.parseObject(JSON.toJSONString(node, filters, SerializerFeature.WriteMapNullValue), TreeNode.class);
    }

    private static <T extends Tree> void mountChildTreeNodes(TreeNode treeNode, Tree parentNode, List<T> nodes){
        String currentNodeId = parentNode.getCurrentNodeId();
        TreeNode currentNode = transformToTreeNode(parentNode);
        List<T> childNodes = nodes.stream().filter(node -> currentNodeId.equals(node.getParentNodeId()))
                .collect(Collectors.toList());

        for (T childNode : childNodes) {
            mountChildTreeNodes(currentNode, childNode, nodes);
        }

        treeNode.appendChild(currentNode);
    }

    private static <T extends Tree> Tree findRootNode(List<T> nodes, String rootNodId){
        Optional<T> result = nodes.stream()
                .filter(node -> !isEmptyNode(node))
                .filter(node -> rootNodId.equals(node.getCurrentNodeId()) || rootNodId.equals(node.getParentNodeId()))
                .findFirst();

        if(!result.isPresent()) {
            throw new NoSuchRootNodeException("Not found this root node, node id: " + rootNodId);
        }

        if(rootNodId.equals(result.get().getParentNodeId())) {
            // 父节点ID匹配记录
            RootNode root = new RootNode();
            root.setNodeId(rootNodId);
            return root;
        }
        // 节点ID匹配记录
        return result.get();
    }

    private static <T> void flatTreeNode(TreeNode node, Class<T> clazz, List<T> flatResults){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("children");
        SimplePropertyPreFilter[] filters = new SimplePropertyPreFilter[]{filter};
        T model = JSONObject.parseObject(JSONObject.toJSONString(node, filters, SerializerFeature.WriteMapNullValue), clazz);
        if(!node.getChildren().isEmpty()) {
            for (TreeNode child : node.getChildren()) {
                flatTreeNode(child, clazz, flatResults);
            }
        }
        flatResults.add(model);
    }

    private static boolean isEmptyNode(Tree node){
        return "".equals(node.getCurrentNodeId()) || null == node.getCurrentNodeId();
    }
}
