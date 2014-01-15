package com.deepsky.lang.plsql.completion.legacy;

import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectTree {
    List<ASTNode> tree = new ArrayList<ASTNode>();

    public ObjectTree(List<ASTNode> subList) {
        this.tree = subList;
    }

    public int length(){
        return tree.size();
    }

//    public List<ASTNode> get(int index){
//        return tree.size()<index? tree.get(index): null;
//    }

    public ASTNode getTop() {
        // TODO - implement me
        return null;
    }

    Iterator<List<ASTNode>> iterator(){
        // TODO - implement me
        return null;
    }

    public ObjectTree subTree(){
        return new ObjectTree(tree.subList(1, tree.size()));
    }

}