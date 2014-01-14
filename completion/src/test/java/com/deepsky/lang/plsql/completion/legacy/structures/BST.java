package com.deepsky.lang.plsql.completion.legacy.structures;

public class BST {
    Node root;

    public BST add(int key){

        if(root == null){
            root = new Node(key);
        } else {
            addDescendant(root, key);
        }
        return this;
    }

    private void addDescendant(Node parent, int key) {
        if(parent.key == key){
            throw new Error("Duplicate key");
        } else if(parent.key > key){
            if(parent.left == null){
                parent.left = new Node(key);
            } else {
                addDescendant(parent.left, key);
            }
        } else {
            if(parent.right == null){
                parent.right = new Node(key);
            } else {
                addDescendant(parent.right, key);
            }
        }
    }

    public void traverseInOrder(NodeVisitor visitor){
        inOrder(root, visitor);
    }

    public void inOrder(Node parent, NodeVisitor visitor){
        if(parent != null){
            inOrder(parent.left, visitor);
            // visit node
            visitor.visit(parent);
            inOrder(parent.right, visitor);
        }

    }

    public void traversePreOrder(NodeVisitor visitor){
        preOrder(root, visitor);
    }

    public void preOrder(Node parent, NodeVisitor visitor){
        if(parent != null){
            // visit node
            visitor.visit(parent);
            preOrder(parent.left, visitor);
            preOrder(parent.right, visitor);
        }

    }

    public void traversePostOrder(NodeVisitor visitor){
        postOrder(root, visitor);
    }

    public void postOrder(Node parent, NodeVisitor visitor){
        if(parent != null){
            // visit node
            postOrder(parent.left, visitor);
            postOrder(parent.right, visitor);
            visitor.visit(parent);
        }

    }

    public interface NodeVisitor {
        void visit(Node node);
    }

    public boolean isValid(){

/*
        Node current = root;
        while(current != null){


        }
*/
        return false;
    }

}
