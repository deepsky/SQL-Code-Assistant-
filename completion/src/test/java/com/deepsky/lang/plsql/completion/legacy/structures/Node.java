package com.deepsky.lang.plsql.completion.legacy.structures;

public class Node {
    public Node left;
    public Node right;
    public int key;

    public Node(int key, Node left, Node right){
        this.key = key;
        this.left = left;
        this.right = right;
    }

    // Leaf node
    public Node(int key){
        this.key = key;
    }

    public String toString(){
        return "Node:" + key;
    }
}
