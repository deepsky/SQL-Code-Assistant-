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
import com.deepsky.lang.plsql.objTree.*;
import com.deepsky.lang.plsql.objTree.impl.actions.PopupAction;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.view.Icons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.*;

public class PackageTreeElement extends DbTreeElementAbstract {

    String pkgSpecPath;
    String pkgBodyPath;

    boolean isValid = true;
    boolean isSpec = false;

    int currentSortOrder = DbTreeElement.SORT_NATIVE_ORDER;
    Map<String, ExecutableTreeElement> uid2execEl = new HashMap<String, ExecutableTreeElement>();

    protected List<DbTreeElement> children2 = new ArrayList<DbTreeElement>();
    boolean currentHide = false;

    public PackageTreeElement(String ctxPath) {
        super(ContextPathUtil.extractPackageName(ctxPath));
        isSpec = ContextPathUtil.extractLastCtxType(ctxPath) == ContextPath.PACKAGE_SPEC;
        if(isSpec){
            pkgSpecPath = ctxPath;
        } else {
            pkgBodyPath = ctxPath;
        }
    }

    public String getCtxPath() {
        return pkgSpecPath != null? pkgSpecPath: pkgBodyPath;
    }

    public void setIsValid(boolean isValid){
        this.isValid = isValid;
    }

    public void setPackageSpecPath(String ctxPath) {
        this.pkgSpecPath = ctxPath;
    }

    public void setPackageBodyPath(String ctxPath) {
        this.pkgBodyPath = ctxPath;
    }

    public void render(DbObjectTreeCellRenderer renderer) {
        renderer.setText(name);

        if(isSpec){
            renderer.setIcon(Icons.PACKAGE_SPEC);
        } else {
            renderer.setIcon(Icons.PACKAGE_BODY);
        }
        renderer.setIsValid(isValid);
    }

    public void runDefaultAction() {
        // By default: do nothing
    }


    public void updateFunction(String path, String name, String value) {
        String signature = createFuncSign(name, value);
        ExecutableTreeElement el = uid2execEl.get(signature);
        if (el != null) {
            el.addOrUpdateCtxPath(path);

        } else {
            // function not found, create one
            el = new FunctionTreeElement(path, name, value);
            super.add(el);
            uid2execEl.put(signature, el);
        }
    }

    private String createFuncSign(String name, String value) {
        ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
        Type type = ContextPathUtil.extractRetType(value);
        // argument specification with type names only
        return Formatter.formatFunctionSignature2(name, args, type);
    }

    private String createProcSign(String name, String value) {
        ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
        // argument specification with type names only
        return Formatter.formatProcedureSignature2(name, args);
    }

    public void updateProcedure(String path, String name, String value) {
        // todo -- may cause an issue due to doubling of spec/body 
        String signature = createProcSign(name, value);
        ExecutableTreeElement el = uid2execEl.get(signature);
        if (el != null) {
            el.addOrUpdateCtxPath(path);

        } else {
            // function not found, create one
            el = new ProcedureTreeElement(path, name, value);
            super.add(el);
            uid2execEl.put(signature, el);
        }
    }


    public void sort(int sortOrder) {

        currentSortOrder = sortOrder;
        Comparator<DbTreeElement> comparator;
        if(sortOrder == DbTreeElement.SORT_NATIVE_ORDER){
            final int basePathLen = getCtxPath().length();
            comparator = new Comparator<DbTreeElement>() {
                public int compare(DbTreeElement o1, DbTreeElement o2) {
                    String o1s = o1.getCtxPath().substring(basePathLen);
                    int seq1 = ContextPathUtil.extractSeqNumFormFirstPathSegment(o1s);
                    String o2s = o2.getCtxPath().substring(basePathLen);
                    int seq2 = ContextPathUtil.extractSeqNumFormFirstPathSegment(o2s);

                    return seq1 - seq2;
                }
            };
        } else {
            comparator = new Comparator<DbTreeElement>() {
                public int compare(DbTreeElement o1, DbTreeElement o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };
        }

        Collections.sort(children, comparator);
    }

    public void filterPackageScopeItems(boolean hide) {

        currentHide = hide;
        // skip filtering if there is package scope objects
        if(hide && pkgBodyPath == null){
            children2.clear();
            children2.addAll(children);
            return;
        }

        if(hide){
            // make copy of children
            children2.clear();
            children2.addAll(children);
            Iterator<DbTreeElement> ite = children.iterator();
            while(ite.hasNext()){
                DbTreeElement e = ite.next();
                if(e.getCtxPath().startsWith(pkgBodyPath)){
                    // subject to hide
                    ite.remove();
                }
            }
        } else {
            children.clear();
            children.addAll(children2);
            children2.clear();
        }
    }


    public TableModel getProperties(){
        DetailsTableModel model = new DetailsTableModel(new String[]{"Name", "Value"});

        List<String> out = new ArrayList<String>();
        out.add("OBJECT TYPE");

        String ctxPath = getCtxPath();
        ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
        int otype = parser.extractLastCtxType();
        if(otype == ContextPath.PACKAGE_SPEC){
            out.add("PACKAGE");
        } else {
            out.add("PACKAGE BODY");
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
                        PsiElement _psi = PlSqlElementLocator.locatePsiElement(project,dbUrl, getCtxPath());
                        if(_psi != null){
                            SqlFindUsagesHelper.runFindUsages(project, _psi);
                        }
                    }
                },
                new PopupAction("Open Specification", Icons.VIEW_PKG_SPEC, pkgSpecPath != null){
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        boolean result = PlSqlElementLocator.openFileInEditor(project, dbUrl, pkgSpecPath);
                    }
                },
                new PopupAction("Open Package Body", Icons.VIEW_PKG_BODY, pkgBodyPath != null){
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        boolean result = PlSqlElementLocator.openFileInEditor(project, dbUrl, pkgBodyPath);
                    }
                }
        };
    }


}
