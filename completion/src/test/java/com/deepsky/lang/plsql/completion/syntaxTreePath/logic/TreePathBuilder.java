package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

import com.intellij.lang.ASTNode;

public interface TreePathBuilder {

    void addNode(ASTNode prev);
    void goUp();
    TreePath complete();

}
