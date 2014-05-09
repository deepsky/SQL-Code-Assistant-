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

import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.exec.impl.RespMessageTable;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.QueryStatisticsPanel;
import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;


public class ExecuteSQLStatementAction extends AnAction {

    private static final Logger log = Logger.getInstance("#ExecuteSQLStatementAction");
    boolean lastEnabled = false;

    public ExecuteSQLStatementAction() {
        super("ExecSql");
        getTemplatePresentation().setEnabled(true);
    }

    public void actionPerformed(AnActionEvent event) {

        PsiFile _psi = event.getData(LangDataKeys.PSI_FILE);
        Editor e = event.getData(LangDataKeys.EDITOR);

//        Editor e = DataKeys.EDITOR.getData(DataManager.getInstance().getDataContext());
//        PlSqlFile psi = (PlSqlFile) DataKeys.PSI_FILE.getData(DataManager.getInstance().getDataContext());
        if (e != null && _psi != null && _psi instanceof PlSqlFile && _psi.getTextLength() > 0) {
            PlSqlFile psi = (PlSqlFile) _psi;
            SelectionModel select = e.getSelectionModel();

            if (select.getSelectedText() == null) {
                Messages.showErrorDialog("SQL Code not selected. Select a script fragment and run SQL Execution again.",
                        "SQL Execution Error");
                return;
            }

            PsiElement start = psi.findElementAt(select.getSelectionStart());
            PsiElement end = psi.findElementAt(select.getSelectionEnd() - 1);

            int spos = (start != null) ? start.getTextOffset() : 0;
            int epos = (end != null) ? end.getTextOffset() + end.getTextLength() : psi.getTextLength();

            ASTNode ancestor = null;
            if (select.getSelectedText().length() > 0) {
                String text = psi.getText().substring(spos, epos).trim();
                try {
                    MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
                    ASTNode root = generator.parse(text);
                    ancestor = root.findChildByType(
                            RespMessageTable.getExecutableStmts()
                    );
                    if (ancestor == null) {
                        Messages.showErrorDialog(
                                "SQL statement was not recognized, please check the syntax.",
                                "SQL Statement Syntax Error");
                    } else if (ancestor.getElementType() == PlSqlElementTypes.SQLPLUS_ANONYM_PLSQL_BLOCK) {
                        // should be check for the particular command
                        ASTNode plsqlBlk = ancestor.findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
                        if (plsqlBlk != null) {
                            ancestor = plsqlBlk;
                        } else {
                            ancestor = null;
                        }
                    } else {
                        // statement recognized!
                    }

                } catch (Throwable e1) {
                    // error
                }
            }

            if (ancestor != null) {
                int _st = spos + ancestor.getTextRange().getStartOffset();
                int _end = spos + ancestor.getTextRange().getEndOffset();
                Project project = event.getData(LangDataKeys.PROJECT);

                try {
                    executeStatement(project, ancestor, psi, _st, _end);
                } catch (DBException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "SQL Query Error");
                    return;
                } catch (NotSupportedException e1) {
//                        Messages.chooseFileToImport(e1.getMessage(), "SQL query error");
                    return;
                }

            } else {
                String text = select.getSelectedText();
                log.info("#actionPerformed [NOT IDENTIFIED] selected text: " + text);
            }
        }
    }

    protected void executeStatement(Project project, @NotNull ASTNode statement, final PlSqlFile psi, final int _st, final int _end) throws DBException {
        final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
        try {
            ((ResolveProvider) statement.getPsi().getContainingFile()).setResolver(psi.getResolver());
            SqlScriptRunService.getInstance(project).runSqlStatement(
                    statement,
                    new SqlScriptRunner.SqlStatementResultListener() {
                        public void handleQueryResult(RowSetManager result) {
                            SqlStatementMarker marker = psi.getModel().addMarker(_st, _end);
                            QueryResultPanel resultPane = qrwn.createResultPanel(
                                    QueryResultPanel.SELECT_RESULT, marker, Icons.SELECT_RESULTSET, null /* ToolTip text */
                            );
                            resultPane.init(result);
                            // show content pane
                            qrwn.showContent(marker, false, 500);
                        }

                        public void handleDMLResult(SQLUpdateStatistics result) {
                            QueryStatisticsPanel resultPane = qrwn.findOrCreateDMLResultPanel(
                                    "DML Result Panel", Icons.TABLE, null /* ToolTip text */
                            );
                            resultPane.append(result);
                            // show content pane
                            qrwn.showContent("DML Result Panel");
                        }
                    });
        } catch (SqlScriptRunner.AlreadyStartedException e) {
            Messages.showErrorDialog(
                    project,
                    "Cannot run SQL until the SQL statement running at the moment will be finished",
                    "Running SQL Failed");
        }

    }

}
