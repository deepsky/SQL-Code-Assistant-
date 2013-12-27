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

package com.deepsky.lang.validation;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.highlighter.DefaultHighlighter;
import com.deepsky.lang.validation.impl.AbstractHighlightingVisitor;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Keep SyntaxHighlightAnnotator and PlSqlHighlighterBase in sync in order
 * to have Color&Fonts configuration syntax highlighting to be correct
 */
public class SyntaxHighlightAnnotator extends AbstractHighlightingVisitor implements Annotator {


    protected void annotate(PsiElement node, final IElementType elementType) {
        if (elementType == PlSqlElementTypes.GENERIC_REF) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.COLUMN);

        } else if (elementType == PlSqlTokenTypes.SL_COMMENT) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.LINE_COMMENT);
        } else if (elementType == PlSqlElementTypes.PLSQL_VAR_REF || elementType == PlSqlElementTypes.VARIABLE_NAME) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.PLSQL_VAR);

        } else if (elementType == PlSqlElementTypes.CONSTRAINT_NAME) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.CONSTRAINT_NAME);

        } else if (elementType == PlSqlElementTypes.COLUMN_NAME_DDL) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.COLUMN);
        } else if (elementType == PlSqlElementTypes.COLUMN_NAME_REF) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.COLUMN);
        } else if (elementType == PlSqlElementTypes.PARAMETER_REF || elementType == PlSqlElementTypes.PARAMETER_NAME) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.PLSQL_PARAMETER);
        } else if (elementType == PlSqlElementTypes.ALIAS_IDENT) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.ALIAS_IDENT);

        } else if (elementType == PlSqlElementTypes.TYPE_NAME_REF) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.USER_DEFINED_TYPE);
        } else if (elementType == PlSqlElementTypes.DATATYPE) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.DATA_TYPE);

        } else if (elementType == PlSqlElementTypes.TABLE_REF_NOT_RESOLVED) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.TABLE_NAME_NOT_RESOLVED);
        } else if (elementType == PlSqlElementTypes.VIEW_NAME_REF) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.VIEW_NAME);
        } else if (elementType == PlSqlElementTypes.TABLE_NAME_REF) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.TABLE_NAME);
        } else if (elementType == PlSqlElementTypes.TABLE_NAME_DDL) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.TABLE_NAME);

        } else if (elementType == PlSqlElementTypes.BUILT_IT_FUNCTION_CALL) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.BUILT_IN_FUNC);
        } else if (elementType == PlSqlElementTypes.CALL_NOT_RESOLVED) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.FUNC_REF_NOT_RESOLVED);

        } else if (elementType == PlSqlElementTypes.OBJECT_NAME) {
            myHolder.createInfoAnnotation(node, null).setTextAttributes(DefaultHighlighter.OBJECT_NAME);

        } else if (elementType == PlSqlElementTypes.ERROR_TOKEN_A) {
            final String text = node.getText();
            if (text != null && text.length() > 0) {
                // cut down too long message
                myHolder.createErrorAnnotation(node,
                        "Unexpected token: " +
                                ((text.length() > 40) ? text.substring(0, 40) + " ..." : text));
            }
        } else if (elementType == PlSqlElementTypes.STRING_LITERAL) {
            myHolder.createErrorAnnotation(node, "Quoted Strings should be concatenated with || operator");
        }
    }

    private AnnotationHolder myHolder;
    private SqlCodeAssistantSettings settings = null;

    public static final Key<Long> JUST_KEY = Key.create("JUST_KEY");
    private long ms = 0;
    private int counter = 0;

    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        myHolder = holder;
        long _ms = System.currentTimeMillis();
        try {
            if (settings == null) {
                settings = PluginKeys.PLUGIN_SETTINGS.getData(psiElement.getProject());
            }

            Long i = holder.getCurrentAnnotationSession().getUserData(JUST_KEY);
            if (i == null) {
                // TODO System.out.println("Yet another null: " + ms + " counter: " + counter);
                ms = 0L;
                counter = 0;
                holder.getCurrentAnnotationSession().putUserData(JUST_KEY, 0L);
            }
            psiElement.accept(this);

        } catch (SyntaxTreeCorruptedException e) {
            // ignore
        } catch (ProcessCanceledException e) {
            settings = null;
        } catch (Throwable e) {
            // ignore
        } finally {
            ms += System.currentTimeMillis() - _ms;
            counter++;
            myHolder = null;
        }
    }


    public void visitElement(PsiElement element) {
        if (element.getFirstChild() == null) {
            // Leaf element, check for KEYWORD
            final IElementType type = element.getNode().getElementType();
            if (type == PlSqlTokenTypes.FILE) {
                int err = 0;
            } else if (type == PlSqlElementTypes.ERROR_TOKEN_A) {
            } else if (type == PlSqlTokenTypes.IDENTIFIER) {
                int err = 0;
            } else if (!PlSqlTokenTypes.NOT_KEYWORDS.contains(type)) {
                // KEYWORD IDENTIFIED
                myHolder.createInfoAnnotation(element, null).setTextAttributes(DefaultHighlighter.KEYWORD);
            } else {
                // PUNCTUATION CHARACTERS or WS
                // These elements were identified by lexer so no highlighting needed here
            }
        } else {
            super.visitElement(element);
        }
    }

}
