package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class TNode {

    private String name;
    private List<TNode> children = new ArrayList<TNode>();

    public TNode(String name){
        this.name = name;
    }

    public TNode() {
    }

    public TNode findOrAdd(@NotNull String name){

        for(TNode child: children){
            if(child.name.equals(name)){
                return child;
            }
        }

        if(name.equals("//")){
            TNode node = new DoubleSlashNode();
            children.add(node);
            return node;
        } else if(name.equals("/")){
            TNode node = new SingleSlashNode();
            children.add(node);
            return node;
        } else if(name.equals("..")){
            TNode node = new SingleSlashNode();
            children.add(node);
            return node;
        }

        TNode node = new StringNode(name);
        children.add(node);
        return node;
    }

    public String getName(){
        return name;
    }

    public List<TNode> getChildren() {
        return children;
    }

    public abstract void printOut(int offset, PrintStream writer);
    public abstract void accept(TNodeVisitor visitor);

    public TNode findOrAddDoubleSlash(){
        for(TNode child: children){
            if(child.name.equals("//")){
                return child;
            }
        }

        TNode node = new DoubleSlashNode();
        children.add(node);
        return node;
    }

    public TNode findOrAddSingleSlash() {
        for(TNode child: children){
            if(child.name.equals("/")){
                return child;
            }
        }

        TNode node = new SingleSlashNode();
        children.add(node);
        return node;
    }

    public TNode findOrAddDoubleDot() {
        for(TNode child: children){
            if(child.name.equals("..")){
                return child;
            }
        }

        TNode node = new DoubleDotNode();
        children.add(node);
        return node;
    }

    public TNode findOrAdd(boolean isDollar, int pos, String ident) {
        for(TNode child: children){
            if(child.name.equals(ident) && child instanceof StringNode){
                StringNode sNode = (StringNode) child;
                if((sNode.isDollar() && isDollar) || (!sNode.isDollar() && !isDollar)){
                    return child;
                } else {
                    // TODO different nodes with same name!!! Report error
                }

            }
        }

        TNode node = new StringNode(isDollar, pos, ident);
        children.add(node);
        return node;
    }

    protected String offset(int offset){
        StringBuilder b = new StringBuilder();
        for(int i =0; i<offset;i++)
            b.append(" ");
        return b.toString();
    }
}
