package com.deepsky.lang.plsql.completion.legacy;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SyntaxTreePathWrap {

    Class psiClass;
    IElementType eType;

    private List<SyntaxTreePathWrap> children = new ArrayList<SyntaxTreePathWrap>();

    public SyntaxTreePathWrap(){
    }

    public void addChild(@NotNull SyntaxTreePathWrap child){
        children.add(child);
    }

    public void process(@NotNull Visitor visitor){

        if(visitor.accept(this)){
            for(SyntaxTreePathWrap child: children){
                child.process(visitor);
            }
        }
    }


    public Class getPsiClass(){
        return psiClass;
    }

    public IElementType geteType(){
        return eType;
    }

    public boolean matchOne(@NotNull ASTNode node){

        if(eType != null){
            return node.getElementType() == eType;
        } else {
            return  psiClass.isInstance(node.getPsi());
        }
    }

    public List<SyntaxTreePathWrap> getChildren(){
        return children;
    }

    public Step getStep(){
        // TODO - implement me
        return null;
    }
}
