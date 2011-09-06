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

package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;

import javax.swing.*;

public class PackageLookupElement <T extends LookupElement> extends LookupElementDecorator<T> {

    String name;

    protected PackageLookupElement(T delegate, String name) {
        super(delegate);
        this.name = name;
    }

    public static PackageLookupElement create(String name, Icon icon){
        LookupElement e = LookupElementBuilder.create(name)
                            .setIcon(icon)
                            .setCaseSensitive(false);

        return new PackageLookupElement<LookupElement>(e, name);
    }


    public void handleInsert(final InsertionContext context) {
        final Editor editor = context.getEditor();
//        final char completionChar = context.getCompletionChar();
        final TailType tailType = TailType.DOT;

        context.setAddCompletionChar(false);
        tailType.processTail(editor, context.getTailOffset());
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
    }

}
