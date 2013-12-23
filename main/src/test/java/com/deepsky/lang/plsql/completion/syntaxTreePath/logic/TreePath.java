package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

public interface TreePath {
    String printPath();

    boolean processPattern(STPPattern[] pattern, SyntaxTreeProcessor processor);

    interface SyntaxTreeProcessor {
        void handle(STPPattern patterns, String prefix, ArgumentDesc[] args);
    }


//    void iterate(PathIterator iterator);
//
//    interface PathIterator {
//        void nextLevel(int level);
//        void handle(Object o);
//
//    }

    interface ArgumentDesc {
        int position();
        Class clazz();
        Object object();
//        boolean isASTNode();

    }

}
