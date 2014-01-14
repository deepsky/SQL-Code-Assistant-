package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

import com.intellij.lang.ASTNode;

import java.util.Iterator;
import java.util.List;

public interface TreePath {
    String printPath();

    Iterator<List<ASTNode>> iterator();


    interface ArgumentDesc {
        int position();
        Class clazz();
        Object object();
//        boolean isASTNode();

    }

}
