### 概述

该工具可快速将具有层级关系的对象集合转换为一棵树，也可将树对象转换为具有层级关系的对象集合。

## 使用方法

1.实现Tree接口并定义当前节点属性和父级节点属性

```java
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
```

转换为树结构

```java
Cate rootCate = createNode("-1", new Cate());
        Cate firstCate1 = createNode("1", rootCate);
        Cate firstCate2 = createNode("2", rootCate);
        Cate secondCate1 = createNode("3", firstCate1);
        Cate secondCate2 = createNode("4", firstCate1);
        Cate secondCate3 = createNode("5", firstCate2);
        Cate secondCate4 = createNode("6", firstCate2);

        TreeNode treeNode = TreeUtils.buildTree(Arrays.asList(rootCate, firstCate1, firstCate2, secondCate1, secondCate2, secondCate3, secondCate4));
        System.out.println(treeNode);
```

树结构转对象集合

```java
List<Cate> cateList = TreeUtils.flatTree(treeNode, Cate.class);
        for (Cate cate : cateList) {
            System.out.println(cate);
        }
```

