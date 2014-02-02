package com.deepsky.lang.plsql.completion.logic;


import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class TreePathImpl implements TreePath {

    Stack<List<ASTNode>> stack = new Stack<List<ASTNode>>();

    public TreePathImpl(Stack<List<ASTNode>> stack) {
        this.stack = stack;
    }

    @Override
    public String printPath() {
        StringBuilder b = new StringBuilder();
        int size = stack.size() - 1;
        for (int i = size; i >= 0; i--) {
            List<ASTNode> cur = stack.get(i);
            for (ASTNode node : cur) {
                b.append(indent(size - i)).append(node.getElementType()).append("\n");
            }

        }
        return b.toString();
    }

    private String indent(int offset) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < offset; i++) {
            b.append("  ");
        }

        return b.toString();
    }


    public Iterator<List<ASTNode>> iterator() {
        return new Iterator<List<ASTNode>>() {
            int i = stack.size() - 1;

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public List<ASTNode> next() {
                return stack.get(i--);
            }

            @Override
            public void remove() {
            }
        };
    }
}
