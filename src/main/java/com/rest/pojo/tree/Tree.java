package com.rest.pojo.tree;

public class Tree {
    private String path;
    private String mode;
    private String type;
    private String sha;

    public Tree() {
    }

    public Tree(String path, String mode, String type, String sha) {
        this.path = path;
        this.mode = mode;
        this.type = type;
        this.sha = sha;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
