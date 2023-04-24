package com.summit.constant.common.util.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TreeNode {
    protected String id;
    protected String parentId;
    protected List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
