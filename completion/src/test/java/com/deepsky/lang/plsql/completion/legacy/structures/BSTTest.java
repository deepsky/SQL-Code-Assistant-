package com.deepsky.lang.plsql.completion.legacy.structures;

import org.junit.Test;

public class BSTTest {

    @Test
    public void testAdd(){

        BST bst = new BST();
        bst.add(8).add(2).add(11).add(1).add(5).add(10).add(19).add(17).add(21);

        int u =0;
    }


    @Test
    public void testAdd2(){

        BST bst = new BST();
        bst.add(8).add(2).add(11).add(1).add(5).add(10).add(19).add(17).add(21);

        int u =0;
    }

    @Test
    public void testInOrder(){

        BST bst = new BST();
        bst.add(8).add(2).add(11).add(1).add(5).add(10).add(19).add(17).add(21);

        System.out.println("InOrder --------------");
        bst.traverseInOrder(new BST.NodeVisitor() {
            @Override
            public void visit(Node node) {
                System.out.println(node.key);
            }
        });
        System.out.println("PreOrder --------------");
        bst.traversePreOrder(new BST.NodeVisitor() {
            @Override
            public void visit(Node node) {
                System.out.println(node.key);
            }
        });
        System.out.println("PostOrder --------------");
        bst.traversePostOrder(new BST.NodeVisitor() {
            @Override
            public void visit(Node node) {
                System.out.println(node.key);
            }
        });
    }

/*
    @Test
    public void testPreOrder(){

        BST bst = new BST();
        bst.add(8).add(2).add(11).add(1).add(5).add(10).add(19).add(17).add(21);

        System.out.println("PreOrder");
        bst.traversePreOrder(new BST.NodeVisitor() {
            @Override
            public void visit(Node node) {
                System.out.println(node.key);
            }
        });
        int u =0;
    }
*/
}
