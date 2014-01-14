package com.deepsky.lang.plsql.completion.legacy.steps;

import com.deepsky.lang.plsql.completion.syntaxTreePath.Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.ObjectTree;
import com.deepsky.lang.plsql.completion.syntaxTreePath.Step;
import org.jetbrains.annotations.NotNull;

public class PsiElementStep extends Step {

    Class psiElement;
    int paramRef;

    public PsiElementStep(@NotNull Class psiElement, int paramRef){
        this.psiElement = psiElement;
        this.paramRef = paramRef;
    }

    @Override
    public boolean accept(ObjectTree tree, Context ctx) {

        if(psiElement.isInstance(tree.getTop().getPsi())){
            if(getChild() == null){
                // TODO - Finita la comedia!
                ctx.pathCompleted();
                //
            } else if(tree.length() > 1){
                getChild().accept(tree.subTree(), ctx);
            }
        }
        return false;
    }


    @NotNull
    public Class getPsiElementClass(){
        return psiElement;
    }
}
