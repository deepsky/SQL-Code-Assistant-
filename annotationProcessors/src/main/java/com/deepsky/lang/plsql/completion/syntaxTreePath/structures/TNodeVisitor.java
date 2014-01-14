package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

public interface TNodeVisitor {
    void visitRoot(RootNode root);

    void visitDD(DoubleDotNode doubleDotNode);

    void visitDS(DoubleSlashNode doubleSlashNode);

    void visitSS(SingleSlashNode singleSlashNode);

    void visitStringNode(StringNode stringNode);
}
