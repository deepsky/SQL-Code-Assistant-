package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

import java.io.PrintStream;

public class StringNode extends TNode {

    private boolean isDollar = true;
    private int pos =-1;
    private int metaInfoRef;

    public StringNode(boolean isDollar, int pos, String name){
        super(name);
        this.isDollar = isDollar;
        this.pos = pos;
    }

    public StringNode(String name){
        super(name);
    }

    @Override
    public void printOut(int offset, PrintStream writer) {
        if(getChildren().size() == 1){
            writer.print(offset(offset));
            writer.print(getName());
            writer.print("\t");
            getChildren().get(0).printOut(0, writer);
        } else if(getChildren().size() > 1){
            writer.print(offset(offset));
            writer.println(getName());
            for(TNode child: getChildren()){
                child.printOut(offset+4, writer);
                writer.println();
            }
        } else {
            writer.print(offset(offset));
            writer.print(getName());
        }
    }

    @Override
    public void accept(TNodeVisitor visitor) {
        visitor.visitStringNode(this);
    }

    public boolean isDollar() {
        return isDollar;
    }

    public int getPos() {
        return pos;
    }

    public void setMetaInfoRef(int metaInfoRef) {
        this.metaInfoRef = metaInfoRef;
    }

    public int getMetaInfoRef() {
        return metaInfoRef;
    }
}
