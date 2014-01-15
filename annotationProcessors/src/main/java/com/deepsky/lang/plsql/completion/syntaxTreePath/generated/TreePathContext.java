package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import com.intellij.lang.ASTNode;

public interface TreePathContext {

    String getTreePath();

    Marker createMarker(String token);

    interface Marker {

        void rollbackTo();
        void discard();

        void setASTNode(ASTNode node, boolean isPsi);
    }

    CallMetaInfo getMeta();

    void setMetaInfoRef(int ref);

    Object[] getMethodHandlerParameters();
}
