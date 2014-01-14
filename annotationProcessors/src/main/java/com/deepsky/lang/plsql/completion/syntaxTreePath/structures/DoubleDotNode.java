package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

import java.io.PrintStream;

public class DoubleDotNode extends TNode {

    public DoubleDotNode(){
        super("..");
    }

    @Override
    public void printOut(int offset, PrintStream writer) {
        if(getChildren().size() == 1){
            writer.print(offset(offset));
            writer.print("..");
            getChildren().get(0).printOut(0, writer);
        } else if(getChildren().size() > 1){
            writer.print(offset(offset));
            writer.println("..");
            for(TNode child: getChildren()){
                child.printOut(offset+4, writer);
                writer.println();
            }
        }
    }

    @Override
    public void accept(TNodeVisitor visitor) {
        visitor.visitDD(this);
    }

}
