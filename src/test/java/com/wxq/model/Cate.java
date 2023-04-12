package com.wxq.model;

import com.wxq.modeltree.core.Tree;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 **/
public class Cate implements Tree {

    private String cateCode;

    private String parentCateCode;

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getParentCateCode() {
        return parentCateCode;
    }

    public void setParentCateCode(String parentCateCode) {
        this.parentCateCode = parentCateCode;
    }

    @Override
    public String getCurrentNodeId() {
        return cateCode;
    }

    @Override
    public String getParentNodeId() {
        return parentCateCode;
    }

    @Override
    public String toString() {
        return "Cate{" +
                "cateCode='" + cateCode + '\'' +
                ", parentCateCode='" + parentCateCode + '\'' +
                '}';
    }
}
