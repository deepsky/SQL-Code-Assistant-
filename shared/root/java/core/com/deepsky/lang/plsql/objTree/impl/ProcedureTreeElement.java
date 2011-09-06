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

import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElementAbstract;
import com.deepsky.lang.plsql.objTree.DetailsTableModel;
import com.deepsky.lang.plsql.objTree.ExecutableTreeElement;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.view.Icons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ProcedureTreeElement extends DbTreeElementAbstract implements ExecutableTreeElement {

    String value;
    boolean isValid = true;

    public ProcedureTreeElement(String path, String name, String value) {
        super(name);
        this.ctxPath = path;
        this.value = value;

    }

    @Override
    public void render(DbObjectTreeCellRenderer renderer) {
        ArgumentSpec[] args = ContextPathUtil.extractArguments(value);

        ContextPathUtil.CtxPathParser p = new ContextPathUtil.CtxPathParser(ctxPath);
        if(p.extractLastCtxType() == ContextPath.PROCEDURE_SPEC){
            renderer.setText(Formatter.formatProcedureSignatureHtmlBased(name, args));
            renderer.setIcon(Icons.PROCEDURE_SPEC);
        } else {
            renderer.setText(Formatter.formatProcedureSignatureHtmlBased(name, args));
//            renderer.setText(Formatter.formatProcedureSignatureHtmlBased(name, Color.GRAY,args));
            renderer.setIcon(Icons.PROCEDURE_BODY);
        }

        renderer.setIsValid(isValid);
    }

    public void addOrUpdateCtxPath(String path) {
        // todo --
    }


    public TableModel getProperties(){
        DetailsTableModel model = new DetailsTableModel(new String[]{"Name", "Value"});

        java.util.List<String> out = new ArrayList<String>();
        out.add("OBJECT TYPE");

        //String ctxPath = getCtxPath();
        ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
        int otype = parser.extractLastCtxType();
        if(otype == ContextPath.PROCEDURE_SPEC){
            out.add("PROCEDURE");
        } else {
            out.add("PROCEDURE BODY");
        }
        model.addRow(out);

        out = new ArrayList<String>();
        out.add("OBJECT NAME");
        out.add(parser.lastCtxName().toUpperCase());
        model.addRow(out);

        return model;
    }


    public AnAction[] getActions() {
        return new AnAction[]{
                new PopupAction("Find Usages", Icons.FIND){
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        PsiElement _psi = PlSqlElementLocator.locatePsiElement(project,dbUrl, ctxPath);
                        if(_psi != null){
                            SqlFindUsagesHelper.runFindUsages(project, _psi);
                        }
                    }
                }
        };
    }


    public void setIsValid(boolean status) {
        this.isValid = status;
    }
}
