package com.deepsky.lang.plsql.tree;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.deepsky.lang.plsql.tree.impl.NodeVisitorImpl;

import java.lang.reflect.Proxy;

public class TreeNodeBuilderEx extends TreeNodeBuilder {

    public TreeNodeBuilderEx(char[] input) {
        super(input);
    }

    public TreeNodeBuilderEx(String input) {
        super(input.toCharArray());
    }

    public ASTNode getTreeBuilt() {
        Node top = buildASTTree();

        NodeVisitorImpl visitor = new NodeVisitorImpl();
        top.process(visitor);

        return top.getUserData(ASTNodeInvocationHandler.ASTNODE_PROXY_KEY);
    }


/*
    class ASTNodeHandler implements InvocationHandler {

        Node node;
        public ASTNodeHandler(Node node){
            this.node = node;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            if (method.getName().equals("getName")) {
//                return synonym;
//            } else if (method.getName().equals("getReferencedTableName")) {
//                return view.getName();
//            } else {
//                return method.invoke(view, args);
//            }
            return null;
        }
    }
*/
}
