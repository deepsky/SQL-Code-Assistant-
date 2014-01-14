package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.*;

import java.io.PrintStream;
import java.util.Stack;


public class TNodePrinterVisitor implements TNodeVisitor {
    WriterAdopter writer;
    int offset = 0;
    Stack<Boolean> lfStack = new Stack<Boolean>();

    public TNodePrinterVisitor(WriterAdopter writer){
        this.writer = writer;
        lfStack.push(false);
    }
    @Override
    public void visitRoot(RootNode root) {
        for (TNode child : root.getChildren()) {
            offset = 0;
            lfStack.push(false);
            child.accept(this);
            writer.println();
            lfStack.pop();
        }
    }

    @Override
    public void visitDD(DoubleDotNode node) {
        if (lfStack.peek()) {
            writer.println();
            writer.print(offset(offset));
        } else {
            writer.print(" ");
        }
        writer.print("..");

        if (node.getChildren().size() == 1) {
            lfStack.push(false);
            node.getChildren().get(0).accept(this);
            lfStack.pop();
        } else {
            offset+=4;
            for(TNode n: node.getChildren()){
                lfStack.push(true);
                n.accept(this);
                lfStack.pop();
            }
            offset-=4;
        }
    }

    @Override
    public void visitDS(DoubleSlashNode node) {
        if (lfStack.peek()) {
            writer.println();
            writer.print(offset(offset));
        } else {
            writer.print(" ");
        }
        writer.print("//");

        if (node.getChildren().size() == 1) {
            lfStack.push(false);
            node.getChildren().get(0).accept(this);
            lfStack.pop();
        } else {
            offset+=4;
            for(TNode n: node.getChildren()){
                lfStack.push(true);
                n.accept(this);
                lfStack.pop();
            }
            offset-=4;
        }
    }

    @Override
    public void visitSS(SingleSlashNode node) {
        if (lfStack.peek()) {
            writer.println();
            writer.print(offset(offset));
        } else {
            writer.print(" ");
        }
        writer.print("/");

        if (node.getChildren().size() == 1) {
            lfStack.push(false);
            node.getChildren().get(0).accept(this);
            lfStack.pop();
        } else {
            offset+=4;
            for(TNode n: node.getChildren()){
                lfStack.push(true);
                n.accept(this);
                lfStack.pop();
            }
            offset-=4;
        }
    }

    @Override
    public void visitStringNode(StringNode node) {
        if (lfStack.peek()) {
            writer.println();
            writer.print(offset(offset));
        } else {
            writer.print(" ");
        }
        writer.print(node.getName());

        if (node.getChildren().size() == 1) {
            lfStack.push(false);
            node.getChildren().get(0).accept(this);
            lfStack.pop();
        } else if (node.getChildren().size() > 1) {
            offset+=4;
            for(TNode n: node.getChildren()){
                lfStack.push(true);
                n.accept(this);
                lfStack.pop();
            }
            offset-=4;
        }
    }

    protected String offset(int offset) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < offset; i++)
            b.append(" ");
        return b.toString();
    }

}
