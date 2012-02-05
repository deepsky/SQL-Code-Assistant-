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

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.formatter2.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.ColumnNameDDL;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class CaseFormatter extends PlSqlElementVisitor {

    private CodeStyleSettings settings;
    private PlSqlCodeStyleSettings customSettings;
    private PsiFile file;
    PsiDocumentManager psiDocumentManager;
    final Document document;
    TextRange rangeToReformat;

    public CaseFormatter(@NotNull CodeStyleSettings settings, @NotNull PsiFile file) {
        this.settings = settings;
        this.file = file;
        this.customSettings = settings.getCustomSettings(PlSqlCodeStyleSettings.class);
        this.psiDocumentManager = PsiDocumentManager.getInstance(file.getProject());
        this.document = psiDocumentManager.getDocument(file);
        this.rangeToReformat = file.getTextRange();
    }

    public PsiElement process(PsiElement source) {
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        rangeToReformat = source.getTextRange();
        source.accept(this);
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        return source;
    }

    public TextRange processText(PsiFile source, TextRange rangeToReformat) {
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        this.rangeToReformat = rangeToReformat;
        source.accept(this);
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        return rangeToReformat;
    }

    public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {

        if (rangeToReformat.contains(columnNameDDL.getTextRange())) {
            final int offset = columnNameDDL.getTextRange().getStartOffset();
            final int endOffset = columnNameDDL.getTextRange().getEndOffset();

            switch (customSettings.NAMES_CASE) {
                case PlSqlCodeStyleSettings.CASE_NAME_DONT_CHANGE:
                    break;
                case PlSqlCodeStyleSettings.CASE_NAME_UPPER: {
                    String text = columnNameDDL.getText().toUpperCase();
                    document.replaceString(offset, endOffset, text);
                    break;
                }
                case PlSqlCodeStyleSettings.CASE_NAME_LOWER: {
                    String text = columnNameDDL.getText().toLowerCase();
                    document.replaceString(offset, endOffset, text);
                    break;
                }
            }

/*
            String text;
            if(customSettings.NAMES_CASE_UPPER){
                text = columnNameDDL.getText().toUpperCase();
            } else {
                text = columnNameDDL.getText().toLowerCase();
            }
            document.replaceString(offset, endOffset, text);
*/
        }

        int hh = 0;
    }

    // Visit elements recursively
    public void visitElement(PsiElement element) {
        PsiElement child = element.getFirstChild();
        while (child != null) {
            try {
                child.accept(this);
            } catch (SyntaxTreeCorruptedException e) {
                // just skip failed element
                int h = 1;
            } catch (ValidationException e) {
                // just skip failed element
                int h = 1;
            } catch (Throwable e) {
                int h = 1;
            }
            child = child.getNextSibling();
        }
    }

}
