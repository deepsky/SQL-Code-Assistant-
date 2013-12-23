package com.deepsky.lang.plsql.completion.syntaxTreePath;

public abstract class Step {

    private Step child;

    abstract public boolean accept(ObjectTree tree, Context ctx);

    public void setChild(Step child){
        this.child = child;
    }

    public Step getChild() {
        return child;
    }
}
