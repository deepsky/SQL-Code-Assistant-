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
import com.deepsky.database.DBException;
import com.deepsky.database.exec.impl.RespMessageTable;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.structure_view.PlSqlStructureViewElement;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;

public class ExecSQLStmtStructurePopupAction extends ExecuteSQLStatementAction {

    public ExecSQLStmtStructurePopupAction() {
    }

    public void actionPerformed(AnActionEvent event) {
        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        if (psi instanceof PlSqlFile) {
            Navigatable elem = event.getData(LangDataKeys.NAVIGATABLE);
            Project project = event.getData(LangDataKeys.PROJECT);

            boolean isOnline = isTargerDBConnected((PlSqlFile)psi);
            if(!isOnline){
                // todo -- issue message "Not connected"
                DbUrl dbUrl = ((PlSqlFile)psi).getDbUrl();
                String message = "";
                if(IndexManager.FS_URL.equals(dbUrl)){
                    // there is not active connection
                    message = "Not connected to the database. Connect to the database first and try to execute SQL statement once more.";
                } else {
                    // selected conection is not active
                    message = "Not connected to the " + dbUrl.getUserHostPortServiceName()
                            + ". Connect to the database first and try to execute SQL statement once more.";
                }

                Messages.showErrorDialog(message, "SQL execution error");
                return;
            }
            if (elem instanceof PlSqlStructureViewElement) {
                PlSqlStructureViewElement structure = (PlSqlStructureViewElement) elem;
                PlSqlElement plSqlElement = structure.getValue();

                String text = plSqlElement.getText();
                int _st = plSqlElement.getTextRange().getStartOffset();;
                int _end = plSqlElement.getTextRange().getEndOffset();

                try {
                    MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
                    ASTNode root = generator.parse(text);
                    if (root != null ){
                        ASTNode astStmt = root.findChildByType(
                                RespMessageTable.getExecutableStmts()
                        );
                        //PlSqlElementTypes.EXECUTABLE_STATEMENTS);
                        if(astStmt != null){
                            if( astStmt.getElementType() == PlSqlElementTypes.SQLPLUS_COMMAND){
                                ASTNode plsqlBlk = astStmt.findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
                                if(plsqlBlk != null){
                                    executeStatement(project, plsqlBlk, (PlSqlFile) psi, _st, _end);
                                }
                            } else {
                                executeStatement(project, astStmt, (PlSqlFile) psi, _st, _end);
                            }
                        }
                    }

                } catch (NotSupportedException e1) {
                    // todo --
                } catch (DBException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "SQL query error");
                } catch (Throwable e1) {
                    // todo --
                    // error
                }
            }
        }
    }

    private boolean isTargerDBConnected(PlSqlFile plSqlFile) {
        DbUrl dbUrl = plSqlFile.getDbUrl();
        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(plSqlFile.getProject());
        if( manager.isConnected()){
            if(manager.getDbUrl().equals(dbUrl)){
                return true;
            } else if(IndexManager.FS_URL.equals(dbUrl)){
                //
                return true;
            }
        }
        return false;
    }

    public void update(AnActionEvent event) {

        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        if (psi instanceof PlSqlFile) {
            Presentation presentation = event.getPresentation();
            presentation.setVisible(true);

            Navigatable elem = event.getData(LangDataKeys.NAVIGATABLE);
            if (elem instanceof PlSqlStructureViewElement) {
                PlSqlStructureViewElement structure = (PlSqlStructureViewElement) elem;
                PlSqlElement plSqlElement = structure.getValue();
                if (plSqlElement != null && plSqlElement.getNode() != null) {
                    if (RespMessageTable.getExecutableStmts().contains(plSqlElement.getNode().getElementType())) {
                        presentation.setEnabled(true);
                    } else {
                        presentation.setEnabled(false);
                    }
                }
            }

        } else {
            Presentation presentation = event.getPresentation();
            presentation.setVisible(false);
        }
    }
}
