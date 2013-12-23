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

import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ErrorTokenWrapper;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class SyntaxValidateAnnotator extends PlSqlElementVisitor implements Annotator {
    public void visitErrorTokenWrapper(ErrorTokenWrapper node) {
        String text = node.getText();
        // cut down too long message
        text = text != null ? ((text.length() > 40) ? text.substring(0, 40) + " ..." : text) : "Error token";
        myHolder.createErrorAnnotation(node, "Unexpected token: " + text);
    }


    private AnnotationHolder myHolder;
    private SqlCodeAssistantSettings settings = null;

    public static final Key<Long> JUST_KEY = Key.create("SyntaxValidateAnnotator");
    private long ms = 0;
    private int counter = 0;

    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        myHolder = holder;
        long _ms = System.currentTimeMillis();
        try {
//            if (settings == null) {
//                settings = PluginKeys.PLUGIN_SETTINGS.getData(psiElement.getProject());
//            }

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

}
