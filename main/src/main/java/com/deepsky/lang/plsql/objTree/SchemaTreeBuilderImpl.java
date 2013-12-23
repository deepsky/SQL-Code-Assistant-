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

package com.deepsky.lang.plsql.objTree;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.objTree.impl.*;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.view.schema_pane.tree.DbElementRootAbstract;
import com.intellij.openapi.project.Project;

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SchemaTreeBuilderImpl implements SchemaTreeBuilder {

    List<DbElementRoot> topNodes = new ArrayList<DbElementRoot>();

    public SchemaTreeBuilderImpl(DbTypeElementFactory factory) {
        topNodes.add(factory.create(DbObject.TABLE));
        topNodes.add(factory.create(DbObject.VIEW));
        topNodes.add(factory.create(DbObject.PACKAGE));
        topNodes.add(factory.create(DbObject.SEQUENCE));
        topNodes.add(factory.create(DbObject.FUNCTION));
        topNodes.add(factory.create(DbObject.PROCEDURE));
        topNodes.add(factory.create(DbObject.TRIGGER));
        topNodes.add(factory.create(DbObject.TYPE));
        topNodes.add(factory.create(DbObject.SYNONYM));
    }

    public SchemaTreeBuilderImpl(String[] selectedTypes, DbTypeElementFactory factory) {
        for(String type: selectedTypes){
            topNodes.add(factory.create(type));
        }
        
        List lst = Arrays.asList(selectedTypes);
        if(lst.contains(DbObject.TRIGGER) && !lst.contains(DbObject.TABLE)){
            // add tables to the types being updated
            topNodes.add(factory.create(DbObject.TABLE));
        } else if(lst.contains(DbObject.TABLE) && !lst.contains(DbObject.TRIGGER)){
            // add tables to the types being updated
            topNodes.add(factory.create(DbObject.TRIGGER));
        }

    }

    int i = 0, ui=0;

    private boolean getStatus(String[] attrs){
        String value = new ContextPathUtil.ExtAttributeParser(attrs).get("status");
        return value == null || Boolean.parseBoolean(value);
    }

    public void handle(String ctxPath, String value, String[] attrs) {

        ContextPathUtil.CtxPathIterator ite = new ContextPathUtil.CtxPathIterator(ctxPath);
        int pkgPos = -1;

        PackageTreeElement parentNode = null;

        String parentName = null;
        String parentPath = null;
        int parentType = -1;

        while (ite.next()) {
            if (ite.last()) {
                i++;
                switch (ite.getType()) {
                    case ContextPath.FILE_CTX:
                        int hh =0;
                        break;
                    case ContextPath.PACKAGE_SPEC:
                        parentNode = findOrCreatePackage(ite.getPath()); //ite.getName());
                        //parentNode.setPackageSpecPath(ite.getPath());
                        parentNode.setIsValid(getStatus(attrs));
                        break;
                    case ContextPath.PACKAGE_BODY:
                        parentNode = findOrCreatePackage(ite.getPath());//ite.getName());
                        //parentNode.setPackageBodyPath(ite.getPath());
                        parentNode.setIsValid(getStatus(attrs));
                        break;
                    case ContextPath.TABLE_DEF:
                        findOrCreateTable(ite.getPath(), ite.getName(), value);
                        break;
                    case ContextPath.COLUMN_DEF:
                        TableTreeElement tElem = findOrCreateTableLight(parentPath, parentName);
                        tElem.addColumn(ite.getPath(), ite.getName(), value);
                        break;
                    case ContextPath.VARIABLE_DECL:
                        // use variables in the package scope only
                        if (parentType == ContextPath.PACKAGE_BODY || parentType == ContextPath.PACKAGE_SPEC) {
                            String pkgCtxPath = ContextPathUtil.extractParentCtx(ite.getPath());
                            parentNode = findOrCreatePackage(pkgCtxPath); //parentName);
                            // add variable to the package
                            parentNode.add(new VariableTreeElement(ite.getPath(), ite.getName(), value));
                        }
                        break;
                    case ContextPath.COLLECTION_TYPE:
                    case ContextPath.RECORD_TYPE:
                    case ContextPath.VARRAY_TYPE:
                    case ContextPath.OBJECT_TYPE:
                        if (parentType == ContextPath.PACKAGE_BODY || parentType == ContextPath.PACKAGE_SPEC) {
                            String pkgCtxPath = ContextPathUtil.extractParentCtx(ite.getPath());
                            parentNode = findOrCreatePackage(pkgCtxPath);//parentName);
                            // add type to the package
                            parentNode.add(new TypeTreeElement(ite.getPath(), ite.getName(), value));
                        } else if (parentType == ContextPath.FILE_CTX){
                            TypeTreeElement type = findOrCreateType(ite.getPath(), ite.getName(), value);
                            type.setIsValid(getStatus(attrs));
                        } else {
                            // todo -- types defined in the procedure/function body, ignore them so far
                        }
                        break;
                    case ContextPath.FUNCTION_SPEC:
                    case ContextPath.FUNCTION_BODY:
                        if (parentType == ContextPath.PACKAGE_BODY || parentType == ContextPath.PACKAGE_SPEC) {
                            String pkgCtxPath = ContextPathUtil.extractParentCtx(ite.getPath());
                            parentNode = findOrCreatePackage(pkgCtxPath);//parentName);
                            parentNode.updateFunction(ite.getPath(), ite.getName(), value);
                        } else if (parentType == ContextPath.FILE_CTX){
                            FunctionTreeElement f = findOrCreateFunction(ite.getPath(), ite.getName(), value);
                            f.setIsValid(getStatus(attrs));
                        }
                        break;
                    case ContextPath.PROCEDURE_SPEC:
                    case ContextPath.PROCEDURE_BODY:
                        if (parentType == ContextPath.PACKAGE_BODY || parentType == ContextPath.PACKAGE_SPEC) {
                            String pkgCtxPath = ContextPathUtil.extractParentCtx(ite.getPath());
                            parentNode = findOrCreatePackage(pkgCtxPath);//parentName);
                            parentNode.updateProcedure(ite.getPath(), ite.getName(), value);
                        } else if (parentType == ContextPath.FILE_CTX){
                            ProcedureTreeElement p = findOrCreateProcedure(ite.getPath(), ite.getName(), value);
                            p.setIsValid(getStatus(attrs));
                        }
                        break;
                    case ContextPath.SEQUENCE:
                        createSequence(ite.getPath(), ite.getName());
                        break;
                    case ContextPath.VIEW_DEF:
                        ViewTreeElement view = findOrCreateView(ite.getPath(), ite.getName());
                        ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                        for (ArgumentSpec arg : args) {
                            view.addColumn(ite.getPath(), arg.getName(), arg.getType());
                        }
                        break;
                    case ContextPath.SYNONYM:
                        createSynonym(ite.getPath(), ite.getName(), value);
                        break;
                    case ContextPath.CREATE_TRIGGER:
                        TriggerTreeElement t = createTrigger(ite.getPath(), ite.getName(), value);
                        t.setIsValid(getStatus(attrs));
                        break;
                    default:
                        ui++;
                        break;
                }
            }

            parentName = ite.getName();
            parentType = ite.getType();
            parentPath = ite.getPath();
        }
    }

    private TriggerTreeElement createTrigger(String path, String name, String value) {
        DbElementRoot triggers = findRoot(DbObject.TRIGGER);
        for (DbTreeElement e2 : triggers.getChildren()) {
            if (e2.getCtxPath().equals(path)) {
                // exists
                return (TriggerTreeElement) e2;
            }
        }

        TriggerTreeElement trigger = new TriggerTreeElement(path, name, value);
        triggers.add(trigger);
        return trigger;
    }

    private ProcedureTreeElement findOrCreateProcedure(String path, String name, String value) {
        DbElementRoot procedures = findRoot(DbObject.PROCEDURE);
        for (DbTreeElement e2 : procedures.getChildren()) {
            if (e2.getCtxPath().equals(path)) {
                // exists
                return (ProcedureTreeElement) e2;
            }
        }

        ProcedureTreeElement proc = new ProcedureTreeElement(path, name, value);
        procedures.add(proc);
        return proc;
    }

    private FunctionTreeElement findOrCreateFunction(String path, String name, String value) {
        DbElementRoot functions = findRoot(DbObject.FUNCTION);
        for (DbTreeElement e2 : functions.getChildren()) {
            if (e2.getCtxPath().equals(path)) {
                // exists
                return (FunctionTreeElement) e2;
            }
        }

        FunctionTreeElement proc = new FunctionTreeElement(path, name, value);
        functions.add(proc);
        return proc;
    }

    private SequenceTreeElement createSequence(String path, String name) {
        DbElementRoot sequences = findRoot(DbObject.SEQUENCE);
        for (DbTreeElement e2 : sequences.getChildren()) {
            if (e2.getCtxPath().equals(path)) {
                // exists
                return (SequenceTreeElement) e2;
            }
        }

        SequenceTreeElement seq = new SequenceTreeElement(path, name);
        sequences.add(seq);
        return seq;
    }

    private SynonymTreeElement createSynonym(String path, String name, String value) {
        DbElementRoot synonyms = findRoot(DbObject.SYNONYM);
        for (DbTreeElement e2 : synonyms.getChildren()) {
            if (e2.getCtxPath().equals(path)) {
                // exists
                return (SynonymTreeElement) e2;
            }
        }

        SynonymTreeElement syn = new SynonymTreeElement(path, name, value);
        if(syn.isPublic()){
            // skip
        } else {
            synonyms.add(syn);
        }
        return syn;
    }


    private TableTreeElement findOrCreateTable(String ctxPath, String name, String value) {
        DbElementRoot tables = findRoot(DbObject.TABLE);
        Iterator<DbTreeElement> iterator = tables.childrenIterator();
        while(iterator.hasNext()){
            DbTreeElement elem = iterator.next();
            if(elem.getCtxPath().equals(ctxPath)){
                if(((TableTreeElement) elem).getValue().length() == 0){
                    // table tree elem was created in light mode, needs to be recreated
                    iterator.remove();
                    break;
                } else {
                    return (TableTreeElement) elem;
                }
            }
        }

        TableTreeElement tab = new TableTreeElement(ctxPath, name, value);
        tables.add(tab);
        return tab;
    }

    private TableTreeElement findOrCreateTableLight(String ctxPath, String name) {
        DbElementRoot tables = findRoot(DbObject.TABLE);
        for (DbTreeElement e2 : tables.getChildren()) {
            if (e2.getCtxPath().equals(ctxPath)) {
                // exists
                return (TableTreeElement) e2;
            }
        }

        // call comes from columndef - the table specification should be refined some later
        TableTreeElement tab = new TableTreeElement(ctxPath, name, "");
        tables.add(tab);
        return tab;
    }

/*
    private PackageTreeElement findOrCreatePackage(String name) {
        DbElementRoot packages = findRoot(DbObject.PACKAGE);
        for (DbTreeElement e2 : packages.getChildren()) {
            if (e2.getName().equalsIgnoreCase(name)) {
                // exists
                return (PackageTreeElement) e2;
            }
        }

        PackageTreeElement pkg = new PackageTreeElement(name);
        packages.add(pkg);
        return pkg;
    }
*/

    private PackageTreeElement findOrCreatePackage(String ctxPath) {
        DbElementRoot packages = findRoot(DbObject.PACKAGE);
        for (DbTreeElement e2 : packages.getChildren()) {
            if (e2.getCtxPath().equalsIgnoreCase(ctxPath)) {
                // exists
                return (PackageTreeElement) e2;
            }
        }

        PackageTreeElement pkg = new PackageTreeElement(ctxPath);
        packages.add(pkg);
        return pkg;
    }

    private TypeTreeElement findOrCreateType(String ctxPath, String name, String value) {
        DbElementRoot types = findRoot(DbObject.TYPE);
        for (DbTreeElement e2 : types.getChildren()) {
            if (e2.getName().equalsIgnoreCase(name)) {
                // exists
                return (TypeTreeElement) e2;
            }
        }

        TypeTreeElement type = new TypeTreeElement(ctxPath, name, value);
        types.add(type);
        return type;
    }

    private ViewTreeElement findOrCreateView(String ctxPath, String name) {
        DbElementRoot views = findRoot(DbObject.VIEW);
        for (DbTreeElement e2 : views.getChildren()) {
            if (e2.getCtxPath().equals(ctxPath)) {
                // exists
                return (ViewTreeElement) e2;
            }
        }
        ViewTreeElement view = new ViewTreeElement(ctxPath, name);
        views.add(view);
        return view; 
    }


    public DbElementRoot[] getTreeBuilt() {
        // 1. bind triggers to tables
        DbElementRoot triggers = findRoot(DbObject.TRIGGER);
        for (DbTreeElement e2 : triggers.getChildren()) {
            TriggerTreeElement trigger = (TriggerTreeElement) e2;
            String tableName = trigger.getTableName();
            if(tableName != null){
                TableTreeElement[] tabs = findTable(tableName);
                for(TableTreeElement tab : tabs){
                    tab.addTrigger(trigger.getCtxPath(), trigger.getName(), trigger.getConditionClause());
                }
            }
        }

        // 2. complete building
        DbElementRoot[] out = topNodes.toArray(new DbElementRoot[topNodes.size()]);
        for(DbElementRoot e: out){
            e.completeBuild();
        }

        return out;
    }

    private TableTreeElement[] findTable(String tableName) {
        List<TableTreeElement> out = new ArrayList<TableTreeElement>();

        DbElementRoot tables = findRoot(DbObject.TABLE);
        for (DbTreeElement e2 : tables.getChildren()) {
            if(e2.getName().equals(tableName)){
                out.add((TableTreeElement) e2);
            }
        }
        return out.toArray(new TableTreeElement[out.size()]);
    }


    private DbElementRoot findRoot(String name) {
        for (DbElementRoot e : topNodes) {
            if (e.getName().equals(name)) {
                return e; //.getRoot();
            }
        }
//        DbTreeElement out = new SchemaTreeElementRoot(name);
//        topNodes.add(out);
//        return out;
        return DUMMY_ROOT_ITEM;
    }


    final static DbElementRoot DUMMY_ROOT_ITEM = new SchemaTreeElementRootDummy();


    private static class SchemaTreeElementRootDummy extends DbElementRootAbstract {
        protected SchemaTreeElementRootDummy() {
            super("DUMMY");
        }

        public void add(DbTreeElement elem){
            // skip
        }

        public void render(DefaultTreeCellRenderer renderer) {
        }

        public Project getProject() {
            return null;
        }

        public DbUrl getDbUrl() {
            return null;
        }

        public List<DbTreeElement> getChildren() {
            return new ArrayList<DbTreeElement>();
        }
    }

}
