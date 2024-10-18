package com.rest.pojo.commit.blob;

import java.util.List;

public class RootCommit {
    public String tree;
    public String message;
    public List<String> parents;

    public RootCommit() {
    }

    public RootCommit(String tree, String message, List<String> parents) {
        this.tree = tree;
        this.message = message;
        this.parents = parents;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }
}
