package com.deepsky.lang.plsql.completion.legacy.steps;


import com.deepsky.lang.plsql.completion.syntaxTreePath.Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.ObjectTree;
import com.deepsky.lang.plsql.completion.syntaxTreePath.Step;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;


public class ASTNodeStep extends Step {

    IElementType eType;
    int parameterRef;

    public ASTNodeStep(@NotNull IElementType eType, int parameterRef){
        this.eType = eType;
        this.parameterRef = parameterRef;
    }

    @Override
    public boolean accept(ObjectTree tree, Context ctx) {

        if(tree.getTop().getElementType() == eType){
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


    public IElementType getElementType() {
        return eType;
    }
}
