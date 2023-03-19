package com.wxq;

import com.wxq.core.Tree;
import com.wxq.core.TreeNode;
import com.wxq.model.Cate;
import com.wxq.utils.TreeUtils;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 **/
public class TreeUtilsTest {

    @Test
    public void buildTree(){
        Cate rootCate = createNode("-1", new Cate());
        Cate firstCate1 = createNode("1", rootCate);
        Cate firstCate2 = createNode("2", rootCate);
        Cate secondCate1 = createNode("3", firstCate1);
        Cate secondCate2 = createNode("4", firstCate1);
        Cate secondCate3 = createNode("5", firstCate2);
        Cate secondCate4 = createNode("6", firstCate2);

        TreeNode treeNode = TreeUtils.buildTree(Arrays.asList(rootCate, firstCate1, firstCate2, secondCate1, secondCate2, secondCate3, secondCate4));
        System.out.println(treeNode);
        List<Cate> cateList = TreeUtils.flatTree(treeNode, Cate.class);
        for (Cate cate : cateList) {
            System.out.println(cate);
        }
    }

    private Cate createNode(String nodeId, Tree parentNode){
        Cate node = new Cate();
        node.setCateCode(nodeId);
        node.setParentCateCode(parentNode.getCurrentNodeId());
        return node;
    }
}
