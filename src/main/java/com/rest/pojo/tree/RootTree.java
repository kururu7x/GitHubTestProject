package com.rest.pojo.tree;

import java.util.ArrayList;
import java.util.List;

public class RootTree {
    private List<Tree> tree;
    private String base_tree;

    public RootTree() {
    }

    public RootTree(List<Tree> tree, String base_tree) {
        this.tree = tree;
        this.base_tree = base_tree;
    }

    public List<Tree> getTree() {
        return tree;
    }

    public void setTree(List<Tree> tree) {
        this.tree = tree;
    }

    public String getBase_tree() {
        return base_tree;
    }

    public void setBase_tree(String base_tree) {
        this.base_tree = base_tree;
    }
}
