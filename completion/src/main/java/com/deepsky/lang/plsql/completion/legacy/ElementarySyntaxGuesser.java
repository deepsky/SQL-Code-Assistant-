package com.deepsky.lang.plsql.completion.legacy;

import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;


/**
 * Dirty implementation of syntax guesser
 * currently it supports RENAME TABLE only
 */
public class ElementarySyntaxGuesser {
    private ASTNode parentAST;

    private final static IElementType[] RENAME_TABLE_GRAPH = new IElementType[]{
            PlSqlBaseTokenTypes.KEYWORD_RENAME,
            PlSqlBaseTokenTypes.KEYWORD_TABLE,
            PlSqlElementTypes.TABLE_REF,
            PlSqlBaseTokenTypes.KEYWORD_TO,
            PlSqlElementTypes.TABLE_NAME_DDL,
            PlSqlBaseTokenTypes.COMMA,
            PlSqlElementTypes.TABLE_REF,
            PlSqlBaseTokenTypes.KEYWORD_TO,
            PlSqlElementTypes.TABLE_NAME_DDL
    };


    private ElementarySyntaxGuesser(@NotNull ASTNode parentAST){
        this.parentAST = parentAST;
    }

    public static ElementarySyntaxGuesser create(@NotNull ASTNode parentAST) {
        return new ElementarySyntaxGuesser(parentAST);
    }

    public IElementType[] getTypeFor(@NotNull final ASTNode target) {

        final IElementType[] out = {null};
        PsiUtil.iterateOverChildren(parentAST.getPsi(), new PsiUtil.PsiElementHandler() {
            int pos = 0;

            @Override
            public boolean handle(PsiElement e) {
                if(pos >= RENAME_TABLE_GRAPH.length){
                    return false;
                } else if(target == e.getNode()){
                    out[0] = RENAME_TABLE_GRAPH[pos];
                    return false;
                }

                return RENAME_TABLE_GRAPH[pos++] == e.getNode().getElementType();

            }
        });

        return out[0] != null? new IElementType[]{out[0]}: new IElementType[0];
    }
}
