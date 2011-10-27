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

package com.deepsky.view.query_pane.markup.impl;

import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.deepsky.view.query_pane.markup.SqlStatementMarkerListener;
import com.deepsky.view.query_pane.markup.SqlStatementMarkupModelListener;
import com.deepsky.view.query_pane.markup.SqlStatementMarkupModel;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SqlStatementMarkupModelImpl implements SqlStatementMarkupModel,
        SqlStatementMarkerListener {

    PlSqlFile file;
    String fileName;
    List<SqlStatementMarkupModelListener> listeners = new ArrayList<SqlStatementMarkupModelListener>();
    List<SqlStatementMarker> markers = new ArrayList<SqlStatementMarker>();

    static final Key<SqlStatementMarker> MRK = new Key<SqlStatementMarker>("MRK");

    public SqlStatementMarkupModelImpl(@NotNull PlSqlFile file) {
        this.file = file;
        this.fileName = file.getName();
    }

//    public void addSqlStatementMarkupModelListener(SqlStatementMarkupModelListener listener) {
//        listeners.add(listener);
//    }
//
//    public void removeSqlStatementMarkupModelListener(SqlStatementMarkupModelListener listener) {
//        listeners.remove(listener);
//    }

    int idx = 0;

    @NotNull
    public synchronized SqlStatementMarker addMarker(int startOffset, int endOffset) {
        // search the marker among existing ones, first
//        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        Project project = file.getProject();

        Document d = file.getViewProvider().getDocument();
        MarkupModel model = d.getMarkupModel(project);
        for (RangeHighlighter h : model.getAllHighlighters()) {
            if (h.getUserData(MRK) != null && h.getStartOffset() == startOffset) {
                return h.getUserData(MRK);
            }
        }
        // RangeHighlighter not found create a new one
        RangeHighlighter m = model.addRangeHighlighter(
                startOffset, endOffset, 5000, //HighlighterLayer.SYNTAX,
                TextAttributesKey.find("PLSQL_NUMBER").getDefaultAttributes(),
                HighlighterTargetArea.EXACT_RANGE
        );
        m.setGreedyToRight(false);
        m.setGreedyToLeft(false);

        // INSERT#1 [test.sql]
        //String tabName = psi2mnemo(startOffset) + "#" + ++idx + " [" + fileName + "]";
        String prefix = psi2mnemo(startOffset);
        int resultNumber = getResultNumber();
//        SqlStatementMarker stmtMarker = new SqlStatementMarker(tabName, m, file);

        SqlStatementMarker stmtMarker = new SqlStatementMarker(prefix, resultNumber, m, file);
        stmtMarker.addStateListener(this);
        m.putUserData(MRK, stmtMarker);
        markers.add(stmtMarker);

        return stmtMarker;
    }

    private Map<Long, Integer> markerRegistry = new HashMap<Long, Integer>();
    private boolean[] array = new boolean[10];

    private int getResultNumber() {
        if (markerRegistry.size() > 9) {
            // remove the oldest marker
            long time0 = Long.MAX_VALUE;
            for (Long time : markerRegistry.keySet()) {
                time0 = Math.min(time, time0);
            }

            if (time0 != Long.MAX_VALUE) {
                int number = markerRegistry.remove(time0);
                // remove from "markers"
                Iterator<SqlStatementMarker> iterator = markers.iterator();
                while (iterator.hasNext()) {
                    SqlStatementMarker m = iterator.next();
                    if(m.getNumber() == number){
                        m.remove();
                        iterator.remove();
                        break;
                    }
                }
            }
        }

        // just add one more tab
        Arrays.fill(array, true);
        for (Integer i : markerRegistry.values()) {
            array[i] = false;
        }

        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                index = i;
                break;
            }
        }

        markerRegistry.put(new Date().getTime(), index);
        return index;
    }

    private void removeMarker(int number) {
        long time = -1;
        for (Map.Entry<Long, Integer> e : markerRegistry.entrySet()) {
            if (e.getValue() == number) {
                time = e.getKey();
                break;
            }
        }

        if (time != -1) {
            markerRegistry.remove(time);
        }
    }

    // marker is not valid any longer
    public void invalidated(SqlStatementMarker marker) {
        removeMarker(marker.getNumber());
        marker.removeStateListener(this);
    }

    private String psi2mnemo(int startOffset) {
        PsiElement psi = file.findElementAt(startOffset);
        while (!(psi instanceof PlSqlElement)) {
            psi = psi.getParent();
        }

        if (psi instanceof SelectStatement) {
            return "SELECT";
        } else if (psi instanceof InsertStatement) {
            return "INSERT";
        } else if (psi instanceof DeleteStatement) {
            return "DELETE";
        } else if (psi instanceof UpdateStatement) {
            return "UPDATE";
        } else {
            // todo more
            return "SQL";
        }
    }

    public synchronized void validate() {
        Iterator<SqlStatementMarker> iterator = markers.iterator();
        while (iterator.hasNext()) {
            SqlStatementMarker marker = iterator.next();
            final RangeHighlighter h = marker.getHighlighter(); //node.getExecRangeMarker(i);
            if (!h.isValid()) {
                if (marker.remove()) {
//                    marker.fireEventInvalid();
                    iterator.remove();
                }
//                Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                // delete marker
//                final MarkupModel model = file.getViewProvider().getDocument().getMarkupModel(project);
//                ApplicationManager.getApplication().invokeLater(new Runnable() {
//                    public void run() {
//                        model.removeHighlighter(h);
//                    }
//                });

//                marker.fireEventInvalid();
//                iterator.remove();
            } else {
                PsiElement el = file.findElementAt(h.getStartOffset());
                if (el != null) {
                    PsiElement parent = el.getParent();
                    if (el.getStartOffsetInParent() == 0 ){ //&&
//                            (parent instanceof SelectStatement || parent.getParent() instanceof SelectStatement)
//                            ) {
                        int hh = 0;
                    } else {
                        // delete marker
                        if (marker.remove()) {
//                            marker.fireEventInvalid();
                            iterator.remove();
                        }
//                        Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                        final MarkupModel model = file.getViewProvider().getDocument().getMarkupModel(project);
//                        ApplicationManager.getApplication().invokeLater(new Runnable() {
//                            public void run() {
//                                model.removeHighlighter(h);
//                            }
//                        });
//
//                        marker.fireEventInvalid();
//                        iterator.remove();
                    }
                }
            }
        }
    }

}
