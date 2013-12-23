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

package com.deepsky.view.query_pane.markup;

import com.deepsky.view.Icons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.impl.DocumentMarkupModel;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.psi.PsiFile;

import javax.swing.*;

public class SqlStatementMarker extends UserDataHolderBase { //} implements UserDataHolder {

    String name;
    PsiFile file;
    final RangeHighlighter highlighter;
    boolean valid = true;
    int resultNumber;

    /*
        public SqlStatementMarker(String name, RangeHighlighter highlighter, @NotNull PlSqlFile file) {
            this.name = name;
            this.highlighter = highlighter;
            this.file = file;

            getHighlighter().setGutterIconRenderer(
                    new StatementGutterRenderer( Icons.GUTTER_03,  name )
            );
        }
    */
    public SqlStatementMarker(String prefix, int resultNumber, RangeHighlighter highlighter, PsiFile file) {
        this.resultNumber = resultNumber;
        this.name = prefix + "#" + resultNumber + " [" + file.getName() + "]";
        this.highlighter = highlighter;
        this.file = file;

        getHighlighter().setGutterIconRenderer(
//                new StatementGutterRenderer( iconByNumber(resultNumber),  name )
                new StatementGutterRenderer(iconByNumber(resultNumber), this)
        );
    }

    private Icon iconByNumber(int gutterNumber) {
        switch (gutterNumber) {
            case 0:
                return Icons.GUTTER_00;
            case 1:
                return Icons.GUTTER_01;
            case 2:
                return Icons.GUTTER_02;
            case 3:
                return Icons.GUTTER_03;
            case 4:
                return Icons.GUTTER_04;
            case 5:
                return Icons.GUTTER_05;
            case 6:
                return Icons.GUTTER_06;
            case 7:
                return Icons.GUTTER_07;
            case 8:
                return Icons.GUTTER_08;
            case 9:
                return Icons.GUTTER_09;
            default:
                return Icons.GUTTER_09;
        }
    }


    public String getName() {
        return name;
    }

    public RangeHighlighter getHighlighter() {
        return highlighter;
    }

    public PsiFile getPlSqlFile() {
        return file;
    }

    public int getNumber() {
        return resultNumber;
    }

    public boolean valid() {
        return valid;
    }

    public String getText() {
        int start = highlighter.getStartOffset();
        int end = highlighter.getEndOffset();

        return highlighter.getDocument().getText().substring(start, end);
    }

    // Remove marker
    public boolean remove() {
        if (!valid) {
            return false;
        }
//        final RangeHighlighter h = getHighlighter();
        final Project project = file.getProject(); //LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        // delete marker
        final Document doc = file.getViewProvider().getDocument();
        if (doc != null) {
            //final MarkupModel model = doc.getMarkupModel(project);
            final MarkupModel model = DocumentMarkupModel.forDocument(doc, project, true);
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    model.removeHighlighter(highlighter);
                    // todo -- should markupModel be disposed?
                }
            });
            valid = false;
            fireEventInvalid();
            return true;
        } else {
            return false;
        }
    }

    public void addStateListener(SqlStatementMarkerListener listener) {
        SqlStatementMarkerListener[] _listeners = new SqlStatementMarkerListener[listeners.length + 1];
        System.arraycopy(listeners, 0, _listeners, 0, listeners.length);
        _listeners[listeners.length] = listener;
        listeners = _listeners;
    }

    public void removeStateListener(SqlStatementMarkerListener listener) {
        for (int i = 0; i < listeners.length; i++) {
            SqlStatementMarkerListener l = listeners[i];
            if (listeners[i] == listener) {
                listeners[i] = null;
            }
        }
    }

    private void fireEventInvalid() {
        for (int i = 0; i < listeners.length; i++) {
            SqlStatementMarkerListener l = listeners[i];
            if (listeners[i] != null) {
                listeners[i].invalidated(this);
            }
        }
    }

    private SqlStatementMarkerListener[] listeners = new SqlStatementMarkerListener[0];


}
