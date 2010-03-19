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

package com.deepsky.view.schema_pane.impl;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.ToggleAction;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.List;
import java.util.ArrayList;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ProcedureDescriptorView extends ItemViewWrapperBase implements ItemViewWrapper {

    String formattedSign;
    String packageName;
    String pname;
    Cache cache;

    public ProcedureDescriptorView(Project project, ItemViewWrapper parent, ProcedureDescriptor dbo, Cache cache){
        super(project);
        this.formattedSign = Formatter.formatHtmlBasedSignature(dbo);
        this.parent = parent;
        this.pname = dbo.getName();
        this.packageName = dbo.getPackage().getName();
        this.cache = cache;
    }

    public ItemViewWrapper getParent() {
        return parent;
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(formattedSign);
        renderer.setIcon(Icons.PROCEDURE_BODY);
    }

    final int GOTO_DECL = 1002;
    final int GOTO_IMPL = 1003;

    @NotNull
    public ToggleAction[] getPopupActions() {
        LocalToggleAction goToDecl = new LocalToggleAction("Go to Declaration", null, null, GOTO_DECL);
        LocalToggleAction gotToImpl = new LocalToggleAction("Go to Implementation", null, null, GOTO_IMPL);
        return new ToggleAction[]{goToDecl, gotToImpl};
    }

    public void runDefaultAction() {
        notifyListeners(GOTO_DECL);
    }

    private void notifyListeners(int command) {
        switch (command) {
            case GOTO_DECL:
                if(packageName != null){
                    PackageDescriptor pkg = (PackageDescriptor) cache.get(packageName, DbObject.PACKAGE);
                    PlSqlObject[] objs = pkg.findObjectByName(pname);
                    for(PlSqlObject dbo: objs){
                        if(dbo instanceof ProcedureDescriptor){
                            ProcedureDescriptor f = (ProcedureDescriptor) dbo;
                            if( formattedSign.equals(Formatter.formatHtmlBasedSignature(f))){
                                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                                break;
                            }
                        }
                    }
                }
                break;
            case GOTO_IMPL: {
                if(packageName != null){
                    PackageDescriptor pkg = (PackageDescriptor) cache.get(packageName, DbObject.PACKAGE_BODY);
                    PlSqlObject[] objs = pkg.findObjectByName(pname);
                    for(PlSqlObject dbo: objs){
                        if(dbo instanceof ProcedureDescriptor){
                            ProcedureDescriptor f = (ProcedureDescriptor) dbo;
                            if( formattedSign.equals(Formatter.formatHtmlBasedSignature(f))){
                                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    class LocalToggleAction extends ToggleAction {

        int command;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            this.getTemplatePresentation().setEnabled(true);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            notifyListeners(command);
        }
    }


}
