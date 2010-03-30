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

package com.deepsky.actions;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionManagerImpl;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;


public class ExecuteSQLStatementAction extends AnAction {

    private static final Logger log = Logger.getInstance("#ExecuteSQLStatementAction");
    boolean lastEnabled = false;

    SqlRunnerHelper lastRunner = null;

    public ExecuteSQLStatementAction() {
        super("ExecSql");
        getTemplatePresentation().setEnabled(lastEnabled);
    }

    public void actionPerformed(AnActionEvent event) {
        PsiFile _psi = event.getData(LangDataKeys.PSI_FILE);
        Editor e = event.getData(LangDataKeys.EDITOR);

//        Editor e = DataKeys.EDITOR.getData(DataManager.getInstance().getDataContext());
//        PlSqlFile psi = (PlSqlFile) DataKeys.PSI_FILE.getData(DataManager.getInstance().getDataContext());
        if (e != null && _psi != null && _psi instanceof PlSqlFile && _psi.getTextLength() > 0) {
            PlSqlFile psi = (PlSqlFile) _psi;
            SelectionModel select = e.getSelectionModel();
            PsiElement start = psi.findElementAt(select.getSelectionStart());
            PsiElement end = psi.findElementAt(select.getSelectionEnd() - 1);

            int spos = (start != null) ? start.getTextOffset() : 0;
            int epos = (end != null) ? end.getTextOffset() + end.getTextLength() : psi.getTextLength();

            IElementType itype = null;
            String text = null;
            if (select.getSelectedText().length() > 0) {
                text = psi.getText().substring(spos, epos);
                try {
                    PlSqlASTParser parser = new PlSqlASTParser();
                    PlSqlElement[] elems = parser.parse(text);
                    if(elems.length == 1 &&
                        PlSqlElementTypes.EXECUTABLE_STATEMENTS.contains(elems[0].getNode().getElementType())){
                        itype = elems[0].getNode().getElementType();
                    }
                } catch (Throwable e1) {
                    // error
                }
            }

            if (itype != null) {
                Project project = event.getData(LangDataKeys.PROJECT);
                
                try {
                    executeStatement(project, text, itype, psi, spos, epos);
                } catch (DBException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "SQL query error");
                    return;
                } catch (NotSupportedException e1) {
//                        Messages.showErrorDialog(e1.getMessage(), "SQL query error");
                    return;
                }

            }
        }
    }

    protected void executeStatement(Project project, String statement, IElementType itype, final PlSqlFile psi, final int _st, final int _end) throws DBException {
        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project); 
//        SQLExecutor executor = ConnectionManagerImpl.getInstance().getSQLExecutor();
        SQLExecutor executor = manager.getSQLExecutor();
        if (itype == PLSqlTypesAdopted.SELECT_EXPRESSION) {
            lastRunner = new SqlRunnerHelper(project, executor, statement, new SqlRunnerHelper.QueryResultListener(){
                public void handleQueryResult(RowSetModel result) {
                    SqlStatementMarker marker = psi.getModel().addMarker(_st, _end);
                    QueryResultPanel resultPane = qrwn.createResultPanel(
                            QueryResultPanel.SELECT_RESULT, marker, Icons.SELECT_RESULTSET, null /* ToolTip text */
                    );
                    resultPane.init(result);
                    // show content pane
//                    QueryResultWindow.getInstance().showContent(marker.getName());
                    qrwn.showContent(marker);
                }
            });
        } else {
            // rely completely on types in "availableStmtSet"
            lastRunner = new SqlRunnerHelper(project, executor, statement, itype, new SqlRunnerHelper.DMLResultListener(){
                public void handleDMLResult(SQLUpdateStatistics result) {
                    SqlStatementMarker marker = psi.getModel().addMarker(_st, _end);
                    QueryResultPanel resultPane = qrwn.createResultPanel(
                            QueryResultPanel.DML_QUERY_RESULT, marker, Icons.DML_RESULT, null /* ToolTip text */
                    );
                    resultPane.init(result);
                    // show content pane
                    qrwn.showContent(marker);
//                    QueryResultWindow.getInstance().showContent(marker.getName());
                }
            });
        }
        // run the query
        lastRunner.runSqlStatement();
    }


    public void update(AnActionEvent event) {
        super.update(event);

        Presentation presentation = event.getPresentation();
        if(lastRunner != null && !lastRunner.isCompleted()){
            // SQL statement is running
            presentation.setEnabled(false);
            return;
        } else {
            //presentation.setEnabled(true);
            lastRunner = null;
        }

        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        Editor e = event.getData(LangDataKeys.EDITOR);

        if (psi != null && e != null) {
            if (psi.getLanguage() instanceof PlSqlLanguage) {
                SelectionModel select = e.getSelectionModel();
                if (!lastEnabled) {
                    String selectedText = select.getSelectedText();
                    if (selectedText != null && selectedText.length() > 0) {
                        if (isSQLAvailable(select, psi)) {
                            presentation.setEnabled(true);
                            lastEnabled = true;
                            return;
                        }
                    } else {
                        startPos = -1;
                        endPos = -1;
                    }
                } else {
                    // lastEnabled == true
                    String selectedText = select.getSelectedText();
                    if (selectedText != null && selectedText.length() > 0) {
                        if (isSQLAvailable(select, psi)) {
                            presentation.setEnabled(true);
                            lastEnabled = true;
                            return;
                        }
                    } else {
                        startPos = -1;
                        endPos = -1;
                    }
                }

                presentation.setEnabled(false);
                lastEnabled = false;
            } else {
                if (lastEnabled) {
                    presentation.setEnabled(false);
                    lastEnabled = false;
                }
                presentation.setEnabled(false);
                startPos = -1;
                endPos = -1;
            }
        }
    }

    int startPos = -1;
    int endPos = -1;

    private boolean isSQLAvailable(SelectionModel select, PsiFile psi) {
        PsiElement start = psi.findElementAt(select.getSelectionStart());
        PsiElement end = psi.findElementAt(select.getSelectionEnd() - 1);

        int spos = (start != null) ? start.getTextOffset() : 0;
        int epos = (end != null) ? end.getTextOffset() + end.getTextLength() : psi.getTextLength();

        boolean checkSelection = false;
        if (startPos != spos) {
            checkSelection = true;
            startPos = spos;
        }

        if (endPos != epos) {
            checkSelection = true;
            endPos = epos;
        }

//        log.info("#update: startPos: " + startPos + ", endPos: " + endPos + ", checkSelection: " + checkSelection + ", lastEnabled: " + lastEnabled);
        if (checkSelection) {
            String text = psi.getText().substring(spos, epos);

            boolean ret = false;
            try {
                PlSqlASTParser parser = new PlSqlASTParser();
                PlSqlElement[] elems = parser.parse(text);
                return elems.length == 1 &&
                    PlSqlElementTypes.EXECUTABLE_STATEMENTS.contains(elems[0].getNode().getElementType());
            } catch (Throwable e1) {
                // error
            }

/*
            try {
                MarkupGenerator generator = new MarkupGenerator();
                TreeNodeBuilder builder = generator.parse0(text);
                Node root = builder.buildASTTree();
                if(root != null){
                    Node[] nodes = root.findChildrenByTypes(PlSqlElementTypes.EXECUTABLE_STATEMENTS);
                    return nodes.length == 1;
                }

            } catch (Throwable e) {
                // error
            }
*/
            return ret;
        }

        return lastEnabled;
    }

}
