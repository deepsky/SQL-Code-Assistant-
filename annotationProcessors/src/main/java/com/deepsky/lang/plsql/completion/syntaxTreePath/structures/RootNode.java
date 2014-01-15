package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

import java.io.PrintStream;

public abstract class RootNode extends TNode {
    @Override
    public void printOut(int offset, PrintStream writer) {
        for (TNode child : getChildren()) {
            child.printOut(0, writer);
            writer.println();
        }
    }

    @Override
    public void accept(TNodeVisitor visitor) {
        visitor.visitRoot(this);
    }

    public abstract String getProcessorClassName();

    public abstract int getTreePathListSize();

    public abstract String getTreePath(int pathIndex);

    public abstract int[] getParameterIndexList(int pathIndex);

    public abstract String[] getParameterClassList(int pathIndex);

    public abstract String getClassFor(int pathIndex);

    public abstract String getMethodFor(int pathIndex);
}
