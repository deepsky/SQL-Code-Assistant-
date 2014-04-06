/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;

import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.*;

import java.util.List;
import java.util.Stack;


public class TNodePrintVisitor implements TNodeVisitor {
    WriterAdapter writer;
    int offset = 0;
    Stack<Boolean> lfStack = new Stack<Boolean>();

    public TNodePrintVisitor(WriterAdapter writer){
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
        writer.print((node.isNegative()? "!": "") + node.getName());
//        for(SubNode subNode: node.getSubNodeList()){
//            subNode.accept(this);
//        }

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

    @Override
    public void visitAnySymbolNode(AnySymbolNode node) {
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

    @Override
    public void visitSubNode(SubNode node) {
        writer.print("(/");
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

        writer.print(")");
    }

    @Override
    public void visitStringWithSubNode(StringWithSubNode node) {
        if (lfStack.peek()) {
            writer.println();
            writer.print(offset(offset));
        } else {
            writer.print(" ");
        }
        writer.print((node.isNegative()? "!": "") + node.getName());
        node.getSubNode().accept(this);

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
