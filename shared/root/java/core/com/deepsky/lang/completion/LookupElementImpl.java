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

package com.deepsky.lang.completion;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupElementRenderer;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class LookupElementImpl extends LookupElement {
    private static final Logger log = Logger.getInstance("#LookupElementImpl");

    String lookUpStr = "euyeyeu";

    final static TokenSet contexts = TokenSet.create(
            PlSqlElementTypes.ERROR_TOKEN_A,
//            PlSqlElementTypes.PLSQL_VAR_REF,
            PlSqlTokenTypes.FILE,
            PlSqlElementTypes.NAME_FRAGMENT
    );

    public LookupElementImpl(@NotNull PsiElement target) {

        final ASTNode[] targetNode = {null};
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return contexts;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if(node.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A){
                    targetNode[0] = node.getTreeParent();
                } else if(node.getElementType() == PlSqlTokenTypes.FILE){
                    targetNode[0] = node;
                } else if(node.getElementType() == PlSqlElementTypes.NAME_FRAGMENT){
                    targetNode[0] = node.getTreeParent();
                }
                return true;
            }
        });

        runner.process(target.getNode());

        log.info("Target node: " + (targetNode[0] != null? targetNode[0]: "NULL"));
        int hh =0;
    }


    @NotNull
    public String getLookupString() {
        return lookUpStr;
    }

    public Set<String> getAllLookupStrings() {
        return Collections.singleton(getLookupString());
    }

    public void handleInsert(InsertionContext context) {
        int jj = 0;
    }

    public void renderElement(LookupElementPresentation presentation) {
//          super.renderElement(presentation);
        presentation.setIcon(Icons.SQL_FILE);
        presentation.setItemText("ItemText");
        presentation.setTailText("TailText");
        presentation.setTypeText("TypeText");
    }


    class LookupElementRendererImpl extends LookupElementRenderer<LookupElementImpl> {
        public void renderElement(LookupElementImpl lookupElement, LookupElementPresentation lookupElementPresentation) {
//                lookupElementPresentation.setItemText("itemText", false , true);
            lookupElementPresentation.setIcon(Icons.SQL_FILE);
            lookupElementPresentation.setTypeText("typeText");//, Icons.CONNECT);
            lookupElementPresentation.setTailText("tailText");
        }
    }


    class InsertHandlerImpl implements InsertHandler<LookupElementImpl> {

        public void handleInsert(InsertionContext insertionContext, LookupElementImpl lookupElement) {
            // todo
            //insertionContext.
            int hh = 0;
        }
    }
}
