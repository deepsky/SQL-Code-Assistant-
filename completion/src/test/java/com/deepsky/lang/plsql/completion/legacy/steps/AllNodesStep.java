package com.deepsky.lang.plsql.completion.legacy.steps;

import com.deepsky.lang.plsql.completion.syntaxTreePath.Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.ObjectTree;
import com.deepsky.lang.plsql.completion.syntaxTreePath.Step;


public class AllNodesStep extends Step {

    public AllNodesStep() {
    }

    @Override
    public boolean accept(ObjectTree tree, Context ctx) {

        if (getChild() != null && tree.length() > 0) {
            ObjectTree cur = tree;
            int i = 0;
            do {
                getChild().accept(cur.subTree(), ctx);
                cur = cur.subTree();
            } while (++i < tree.length());
        }

        return false;
    }

}
