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

package com.deepsky.lang.plsql.resolver.factory;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class SqlASTFactory extends ASTFactory {
    private int updateCounter = 0;

    final static TokenSet ws_comments = TokenSet.create(
            PlSqlTokenTypes.WS, PlSqlTokenTypes.LF, TokenType.WHITE_SPACE,
            PlSqlTokenTypes.SL_COMMENT, PlSqlTokenTypes.ML_COMMENT, PlSqlTokenTypes.BAD_ML_COMMENT
    );

    @Override
    public CompositeElement createComposite(IElementType type) {
        if (type instanceof IFileElementType) {
            return new FileElement(type, null);
//        } else if (type == PLSqlTypesAdopted.OBJECT_NAME) {
//            return new PlSqlObjectNameImpl();
        }

        return new CompositeElementExt(type) {
/*
            public void rawAddChildren(@NotNull TreeElement first) {
                super.rawAddChildren(first);

                if (!ws_comments.contains(getElementType())) {
                    updateCounter++;
                }
            }
*/

            public void rawAddChildrenWithoutNotifications(TreeElement first) {
                super.rawAddChildrenWithoutNotifications(first);
                if (!ws_comments.contains(getElementType())) {
                    updateCounter++;
                }
            }

            public void subtreeChanged() {
            }

            public void rawRemove() {
                super.rawRemove();

                if (!ws_comments.contains(getElementType())) {
                    updateCounter++;
                }
            }
        };
    }


    @Override
    public LeafElement createLeaf(IElementType type, CharSequence text) {
        final Language lang = type.getLanguage();
        final ParserDefinition parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(lang);
        if (parserDefinition != null) {
            if (parserDefinition.getCommentTokens().contains(type)) {
                return new PsiCommentImpl(type, text);
            }
        }
        return new LeafPsiElement(type, text) {
            public void rawRemove() {
                super.rawRemove();

                if (!ws_comments.contains(getElementType())) {
                    updateCounter++;
                }
            }
        };
    }


    public void clearUpdateCounter() {
        updateCounter = 0;
    }

    public int getUpdateCounter() {
        return updateCounter;
    }

/*
    class LeafPsiElementExt extends LeafPsiElement {

        public LeafPsiElementExt(IElementType type, CharSequence text) {
            super(type, text);
        }


        public void rawRemove() {
            super.rawRemove();

            if(!ws_comments.contains(getElementType())){
                updateCounter++;
            }
        }

    }
*/

}
