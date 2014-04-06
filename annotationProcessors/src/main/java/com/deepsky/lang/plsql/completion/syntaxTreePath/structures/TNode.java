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

package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

import java.util.ArrayList;
import java.util.List;

public abstract class TNode {

    private String name;
    private List<TNode> children = new ArrayList<TNode>();

    public TNode(String name) {
        this.name = name;
    }

    public TNode() {
    }

//    public TNode findOrAdd(@NotNull String name){
//
//        for(TNode child: children){
//            if(child.name.equals(name)){
//                return child;
//            }
//        }
//
//        if(name.equals("//")){
//            TNode node = new DoubleSlashNode();
//            children.add(node);
//            return node;
//        } else if(name.equals("/")){
//            TNode node = new SingleSlashNode();
//            children.add(node);
//            return node;
//        } else if(name.equals("..")){
//            TNode node = new SingleSlashNode();
//            children.add(node);
//            return node;
//        }
//
//        TNode node = new StringNode(name);
//        children.add(node);
//        return node;
//    }

    public String getName() {
        return name;
    }

    public List<TNode> getChildren() {
        return children;
    }

    public abstract void accept(TNodeVisitor visitor);

    public TNode findOrAddDoubleSlash() {
        for (TNode child : children) {
            if (child.name.equals("//")) {
                return child;
            }
        }

        TNode node = new DoubleSlashNode();
        children.add(node);
        return node;
    }

    public TNode findOrAddSingleSlash() {
        for (TNode child : children) {
            if (child.name.equals("/")) {
                return child;
            }
        }

        TNode node = new SingleSlashNode();
        children.add(node);
        return node;
    }

    public TNode findOrAddDoubleDot() {
        for (TNode child : children) {
            if (child.name.equals("..")) {
                return child;
            }
        }

        TNode node = new DoubleDotNode();
        children.add(node);
        return node;
    }

    public TNode findOrAdd(boolean isDollar, int pos, String ident, boolean isNegative) {
        for (TNode child : children) {
            if (child instanceof StringWithSubNode) {
                // String node can be merged with String Node only
                continue;
            } else if (child instanceof StringNode) {
                if (child.name.equals(ident)) {
                    StringNode sNode = (StringNode) child;
                    if ((sNode.isDollar() && isDollar) || (!sNode.isDollar() && !isDollar)) {
                        if ((sNode.isNegative() && isNegative) || (!sNode.isNegative() && !isNegative)) {
                            return sNode;
                        }
                    } else {
                        // TODO different nodes with the same name!!! Report error
                    }
                } else {
                    // TODO check the case #TABLE_REF and $TableRef i.e. ASTNode element type against PSI element
                }
            }
        }

        TNode node = new StringNode(isDollar, pos, ident, isNegative);
        children.add(node);
        return node;
    }


    public TNode findOrAddAnySymbol(int pos) {
        for (TNode child : children) {
            if (child instanceof AnySymbolNode) {
                return child;
            }
        }

        TNode node = new AnySymbolNode(pos);
        children.add(node);
        return node;
    }

    protected String offset(int offset) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < offset; i++)
            b.append(" ");
        return b.toString();
    }

    public TNode findOrAdd(SubNode subNode, String name, boolean isDollar, int pos, boolean isNegative) {
        for (TNode child : children) {
            if (child instanceof StringWithSubNode) {
                StringWithSubNode sNode = (StringWithSubNode) child;
                if (sNode.getName().equals(name)) {
                    if ((sNode.isDollar() && isDollar) || (!sNode.isDollar() && !isDollar)) {
                        if ((sNode.isNegative() && isNegative) || (!sNode.isNegative() && !isNegative)) {
                            if (sNode.getSubNode().equals(subNode)) {
                                return sNode;
                            }
                        }
                    } else {
                        // TODO different nodes with the same name!!! Report error
                    }
                } else {
                    // TODO check the case #TABLE_REF and $TableRef i.e. ASTNode element type against PSI element
                }
            }
        }

        TNode node = new StringWithSubNode(subNode, name, isDollar, pos, isNegative);
        children.add(node);
        return node;
    }
}
