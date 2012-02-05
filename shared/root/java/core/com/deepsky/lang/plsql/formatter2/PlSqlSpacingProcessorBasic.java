/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.formatter2;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public class PlSqlSpacingProcessorBasic {

    static TokenSet PUNCTUATION_SIGNS = TokenSet.create(PlSqlTokenTypes.COMMA,PlSqlTokenTypes.DOT);

    public static Spacing getSpacing(PlSqlBlock child1, PlSqlBlock child2, CommonCodeStyleSettings settings) {

        ASTNode leftNode = child1.getNode();
        ASTNode rightNode = child2.getNode();
        final PsiElement left = leftNode.getPsi();
        final PsiElement right = rightNode.getPsi();

        IElementType leftType = leftNode.getElementType();
        IElementType rightType = rightNode.getElementType();

        // For dots, commas etc.
        if(rightType == PlSqlTokenTypes.COMMA){
            if(leftType == PlSqlElementTypes.PARAMETER_SPEC
                    || leftType == PlSqlElementTypes.CALL_ARGUMENT
                    || leftType == PlSqlElementTypes.COLUMN_DEF
                    || leftType == PlSqlElementTypes.PK_SPEC
                    || leftType == PlSqlElementTypes.FK_SPEC
                    || leftType == PlSqlElementTypes.UNIQUE_CONSTRAINT
                    || leftType == PlSqlElementTypes.CHECK_CONSTRAINT){
                return SpacingConstants.NO_SPACING;
            }
        }
        if (PUNCTUATION_SIGNS.contains(rightType)) {
            return SpacingConstants.NO_SPACING_WITH_NEWLINE;
        } else if(leftType == PlSqlTokenTypes.DOT){
            return SpacingConstants.NO_SPACING_WITH_NEWLINE;
        } else if(rightType == PlSqlTokenTypes.SEMI){
            return SpacingConstants.NO_SPACING;
        }

        if(leftType == PlSqlTokenTypes.OPEN_PAREN || rightType == PlSqlTokenTypes.CLOSE_PAREN ){
            PsiElement parent = (leftType == PlSqlTokenTypes.OPEN_PAREN ? left : right).getParent();
            boolean shouldHaveSpace =
                    parent.getNode().getElementType() == PlSqlElementTypes.DATATYPE
                    && settings.SPACE_WITHIN_BRACKETS;
//            return shouldHaveSpace ? COMMON_SPACING : NO_SPACING; //NO_SPACING_WITH_NEWLINE;
            return shouldHaveSpace ? SpacingConstants.COMMON_SPACING : SpacingConstants.NO_SPACING_WITH_NEWLINE;
        }
        
        if(leftType == PlSqlTokenTypes.KEYWORD_AS || rightType == PlSqlElementTypes.ALIAS_IDENT ){
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
        return SpacingConstants.COMMON_SPACING;
    }
}
