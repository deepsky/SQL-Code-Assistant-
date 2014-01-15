package com.deepsky.lang.plsql.completion.legacy.logic;

import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;

public interface TreePathBuilder {

    void addNode(ASTNode prev);
    void goUp();
    TreePath complete();

}
