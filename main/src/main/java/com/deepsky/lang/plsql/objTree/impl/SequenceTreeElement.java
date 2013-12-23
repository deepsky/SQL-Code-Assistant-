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

package com.deepsky.lang.plsql.objTree.impl;

import com.deepsky.actions.SqlScriptRunService;
import com.deepsky.actions.SqlScriptRunner;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElementAbstract;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.QueryResultWindowiMPL;
import com.deepsky.view.query_pane.QueryStatisticsPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;

public class SequenceTreeElement  extends DbTreeElementAbstract {

    public SequenceTreeElement(String ctxPath, String name) {
        super(name);
        this.ctxPath = ctxPath;
    }

    public void render(DbObjectTreeCellRenderer renderer){
        renderer.setText(name);
        renderer.setIcon(Icons.SEQUENCE);
    }

    public MenuItemAction[] getActions() {
        return new MenuItemAction[]{
                new PopupAction("Find Usages", Icons.FIND){
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        PsiElement _psi = PlSqlElementLocator.locatePsiElement(project,dbUrl, ctxPath);
                        if(_psi != null){
                            SqlFindUsagesHelper.runFindUsages(project, _psi);
                        }
                    }
                },
                new PopupActionConnectionSensitive("Create DDL script", Icons.QUERY_DATA) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        try {
                            final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
                            SqlScriptRunService.getInstance(project).generateDDLScript(
                                    DbObject.SEQUENCE, getName(),
                                    new SqlScriptRunner.DMLResultListener() {
                                        public void handleDMLResult(SQLUpdateStatistics result) {
                                            QueryStatisticsPanel resultPane = qrwn.findOrCreateDMLResultPanel(
                                                    DDL_TAB_NAME, Icons.TABLE, null /* ToolTip text */
                                            );
                                            resultPane.append(result);
                                            // show content pane
                                            qrwn.showContent(DDL_TAB_NAME);
                                        }
                                    });
                        } catch (SqlScriptRunner.AlreadyStartedException e) {
                            Messages.showErrorDialog(
                                    project,
                                    "Cannot run SQL until the SQL statement running at the moment will be finished",
                                    "Data load failed");
                        } catch (DBException e) {
                            Messages.showErrorDialog(project, e.getMessage(), "Data load failed");
                        }
                    }
                }
        };
    }
    

}
