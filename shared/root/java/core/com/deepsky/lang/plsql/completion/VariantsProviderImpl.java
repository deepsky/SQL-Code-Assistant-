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

package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.plsql.completion.lookups.*;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.resolver.helpers.PackageResolveHelper;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.utils.*;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

public class VariantsProviderImpl implements VariantsProvider {

    //IndexTree itree;
    NameProvider nameProvider;
    ResolveHelper resolver;

    public VariantsProviderImpl(NameProvider nameProvider, ResolveHelper resolver) {
        this.nameProvider = nameProvider;
        this.resolver = resolver;
    }

    public List<LookupElement> collectTableNameVariants(String lookupString) {
        ContextItem[] findings = nameProvider.findTopLevelItems(new int[]{ContextPath.TABLE_DEF}, null);
        List<LookupElement> out = new ArrayList<LookupElement>();
        for (ContextItem item : findings) {
            String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (name.toLowerCase().startsWith(lookupString.toLowerCase())) {
                // todo - apply appropriate icon for item - view, table, synonym 
                out.add(TableLookupElement.create(name, Icons.TABLE));
            }
        }

        return out;
    }

    public List<LookupElement> collectNamesInContext(ResolveDescriptor desc, String lookUpStr) {
        List<LookupElement> out = new ArrayList<LookupElement>();
        Iterator<ContextItem> ite = desc.iterateOverChildren();
        while (ite.hasNext()) {
            ContextItem item = ite.next();
            String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (lookUpStr == null || name.toLowerCase().startsWith(lookUpStr.toLowerCase())) {
                LookupElementDecorator decorator = create(item);
                if(decorator!=null){
                    out.add(decorator);
                }
            }
        }

        return out;
    }

    public List<LookupElement> collectNamesInContext(NameFragmentRef prev, String lookUpStr, int filter) {
        ResolveDescriptor rhlp;
        if(prev != null && (rhlp = prev.resolveLight()) != null){
            List<LookupElement> out = new ArrayList<LookupElement>();
            Iterator<ContextItem> ite = rhlp.iterateOverChildren();
            while (ite.hasNext()) {
                ContextItem item = ite.next();
                String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
                if (lookUpStr == null || name.toLowerCase().startsWith(lookUpStr.toLowerCase())) {
                    LookupElementDecorator decorator = create(item, filter);
                    if(decorator!=null){
                        out.add(decorator);
                    }
                }
            }
            return out;
        }
        return new ArrayList<LookupElement>();
    }

    private LookupElementDecorator create(ContextItem item, int filter) {
        String ctxPath = item.getCtxPath();
        int ctxType = ContextPathUtil.extractLastCtxType(ctxPath);

        switch (ctxType) {
            case ContextPath.PROCEDURE_SPEC:
            case ContextPath.PROCEDURE_BODY:
                if(filter == FILTER_NONE){
                    return create(item);
                }
                break;
            case ContextPath.FUNCTION_SPEC:
            case ContextPath.FUNCTION_BODY:
                if(filter == FILTER_NONE || filter == FILTER_EXPR){
                    return create(item);
                }
                break;
            case ContextPath.VARIABLE_DECL:
                if(filter == FILTER_NONE || filter == FILTER_EXPR){
                    return create(item);
                }
                break;
        }
        return null;
    }

    private LookupElementDecorator create(ContextItem item) {
        String ctxPath = item.getCtxPath();
        int ctxType = ContextPathUtil.extractLastCtxType(ctxPath);

        switch (ctxType) {
            case ContextPath.PROCEDURE_SPEC:
            case ContextPath.PROCEDURE_BODY: {
                String procName = ContextPathUtil.extractLastCtxName(ctxPath);
                Icon icon = ctxType==ContextPath.PROCEDURE_SPEC? Icons.PROCEDURE_SPEC: Icons.PROCEDURE_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(item.getValue());
                return ProcedureLookupElement.create(procName, args, icon);
            }
            case ContextPath.FUNCTION_SPEC:
            case ContextPath.FUNCTION_BODY: {
                String procName = ContextPathUtil.extractLastCtxName(ctxPath);
                Icon icon = ctxType==ContextPath.FUNCTION_SPEC? Icons.FUNCTION_SPEC: Icons.FUNCTION_BODY;
                String value = item.getValue();
                ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                return FunctionLookupElement.create(procName, args, ContextPathUtil.extractRetType(value), icon);
            }
            case ContextPath.VARIABLE_DECL: {
                String ctxName = findCtxAncestor(item.getCtxPath(), new int[]{
                        ContextPath.PACKAGE_BODY,
                        ContextPath.PACKAGE_SPEC,
                        ContextPath.PROCEDURE_BODY,
                        ContextPath.FUNCTION_BODY
                });
                Type t = TypeFactory.decodeType(item.getValue());
                String name = ContextPathUtil.extractLastCtxName(ctxPath);
                return GenericLookupElement.create(name, t.toString(), ctxName, Icons.VARIABLE_DECL);
            }
        }
        return null;
    }

    public List<LookupElement> collectColumnExprVariants(String ctxPath, String alias, String lookUpStr) {
        final List<ColumnElement> columns = new ArrayList<ColumnElement>();
        String value = nameProvider.getContextPathValue(ctxPath);

        // temp map to speed up the column collecting
        final Map<String, ResolveDescriptor> tabname2resdesc = new HashMap<String, ResolveDescriptor>(); //
        if (value != null && value.length() > 0) {
            ResolveUtil.processSelectValue(alias, value, new ResolveUtil.FromTabProcessor() {
                public void processSubquery(String ctx, String table_alias, boolean isColumnPrefixedWithTabAlias) {
                    List<ColumnElement> list = collectColumnsForSubquery(ctx, table_alias);
                    addWithMarking(columns, list);
                }

                public void processTable(String table_name, String table_alias, boolean isColumnPrefixedWithTabAlias) {
                    ResolveDescriptor rhlp = tabname2resdesc.get(table_name.toLowerCase());
                    if (rhlp == null) {
                        rhlp = resolver.resolveTableRef(table_name);
                        tabname2resdesc.put(table_name.toLowerCase(), rhlp);
                    }
                    if (rhlp != null) {
                        List<ColumnElement> list = collectColumnsForTable(rhlp, table_name, table_alias, isColumnPrefixedWithTabAlias);
                        addWithMarking(columns, list);
                    }
                }
            });
        }

        LookupElement[] out = new LookupElement[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            ColumnElement it = columns.get(i);
            out[i] = SelectFieldLookupElement.create(alias, it);
        }

        return Arrays.asList(out);
    }

    /**
     * Populate target list with columns.
     * If column collision found - strike out the column
     * <p/>
     * Examples with column collisions:
     * 1.  object_name (user_objects)
     * object_name (user_objects)
     * <p/>
     * Collapse and strikeout
     * <p/>
     * 2.  object_name (user_objects s)  -- full qualified column needed (s.object_name)
     * object_name (user_objects)    -- strikeout
     * <p/>
     * 3.  object_name (user_objects s)
     * object_name (user_objects s)
     * <p/>
     * Collapse and strikeout
     * <p/>
     * 4.  object_name                   -- strikeout
     * object_name (user_objects s)  -- full qualified column needed (s.object_name)
     * <p/>
     * 5.  object_name                   -- strikeout
     * object_name (user_objects)    -- full qualified column needed (user_objects.object_name)
     * <p/>
     * 6.  object_name (user_tables)     -- full qualified column needed (user_tables.object_name)
     * object_name (user_objects)    -- full qualified column needed (user_objects.object_name)
     *
     * @param target
     * @param columns
     */
    private void addWithMarking(final List<ColumnElement> target, List<ColumnElement> columns) {
        ColumnElement[] forLoop = target.toArray(new ColumnElement[target.size()]);
        for (final ColumnElement c : columns) {
            // check whether full qualified column is needed
            boolean done = iterateElementsWithName(forLoop, c.getName(), new ColumnElementHandler() {
                public void handle(ColumnElement e) {
                    if (e.tableName.equalsIgnoreCase(c.tableName)) {
                        // table names match each other
                        if (e.tableAlias.equalsIgnoreCase(c.tableAlias)) {
                            e.setStrikeout(true);
                        } else {
                            // aliases different
                            if (e.tableAlias.length() == 0) {
                                // no alias in existing element
                                e.setStrikeout(true);
                                c.setFullQualifiedColumn(true);
                                target.add(c);
                            } else if (c.tableAlias.length() == 0) {
                                // no alias in income element
                                c.setStrikeout(true);
                                e.setFullQualifiedColumn(true);
                                target.add(c);
                            } else {
                                // correct way
                                target.add(c);
                            }
                        }
                    } else {
                        // table names are different
                        if (e.tableName.length() == 0) {
                            // table name not specified (subquery without alias case)
                            e.setStrikeout(true);
                            c.setFullQualifiedColumn(true);
                            target.add(c);
                        } else if (c.tableName.length() == 0) {
                            // table name not specified (subquery without alias case)
                            c.setStrikeout(true);
                            e.setFullQualifiedColumn(true);
                            target.add(c);
                        } else if (c.getName().equalsIgnoreCase(e.getName())) {
                            // table name are different but column name equals each other
                            //c.setStrikeout(true);
                            c.setFullQualifiedColumn(true);
                            e.setFullQualifiedColumn(true);
                            target.add(c);
                        } else {
                            // todo -- possible a case when tab name differenet but
                            // todo -- aliases are the same, need to check
                            target.add(c);
                        }
                    }
                }
            });

            if (!done) {
                target.add(c);
            }
        }
    }


    private boolean iterateElementsWithName(ColumnElement[] target, String name, ColumnElementHandler handler) {
        boolean nonLooped = false;
        for (ColumnElement c : target) {
            if (c.getName().equalsIgnoreCase(name)) {
                handler.handle(c);
                nonLooped = true;
            }
        }
        return nonLooped;
    }


    private interface ColumnElementHandler {
        void handle(ColumnElement e);
    }

    public List<LookupElement> collectColumnNameRef(final ColumnNameRef columnNameRef, final String lookUpStr) {
        final List<LookupElement> out = new ArrayList<LookupElement>();

        ResolveUtil.resolveColumnNameRef(columnNameRef, new ColumnNameRefCallback() {
            private void collectVariants(PsiElement psiElement) {
                if (psiElement instanceof TableDefinition) {
                    TableDefinition def = (TableDefinition) psiElement;
                    for (ColumnDefinition c : def.getColumnDefs()) {
                        String cname = c.getColumnName().toLowerCase();
                        if (lookUpStr == null || cname.startsWith(lookUpStr.toLowerCase())) {
                            // add to the output list
                            out.add(GenericLookupElement.create(
                                    cname, c.getType().typeName(), def.getTableName(), null)
                            );
                        }
                    }
                }
            }

            public void process(ColumnFKSpec fkSpec) {
                PsiElement resolved = fkSpec.getTableRef().resolve();
                collectVariants(resolved);
            }

            public void process(ColumnTypeRef cTypeRef) {
                PsiElement resolved = cTypeRef.getTableRef().resolve();
                collectVariants(resolved);
            }

            public void process(ForeignKeyConstraint fkConst) {
                PsiElement resolved = fkConst.getReferencedTable2().resolve();
                collectVariants(resolved);
            }

            public void process(AlterTable alter) {
                PsiElement resolved = alter.getTableRef().resolve();
                collectVariants(resolved);
            }

            public void process(Comment comment) {
                PsiElement resolved = comment.getTableRef().resolve();
                collectVariants(resolved);
            }

            public void process(PsiElement parent) {
                // todo
            }
        });

        return out;
    }

    public List<LookupElement> collectDataTypeVariants(String ctxPath, String lookUpStr) {
        List<LookupElement> out = new ArrayList<LookupElement>();
        for (String typeName : DataTypeLookupElement.getDataTypeNames()) {
            if (lookUpStr.length() == 0 || typeName.toLowerCase().startsWith(lookUpStr.toLowerCase())) {
                out.add(DataTypeLookupElement.create(typeName, Icons.RECORD_TYPE_DECL));
            }
        }

        return out;
    }

    public List<LookupElement> collectVarVariants(String ctxPath, final String lookUpStr) {
        final List<ContextItem> findings = new ArrayList<ContextItem>();

        // go thru local context
        new ContextPathProcessor(new ContextPathVisitor() {
            public void visitPackageBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.VARIABLE_DECL
                });
                findings.addAll(Arrays.asList(items));
            }

            public void visitPackageSpec(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.VARIABLE_DECL
                });
                findings.addAll(Arrays.asList(items));
            }

            public void visitProcedureBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.ARGUMENT
                });
                findings.addAll(Arrays.asList(items));
            }

            public void visitFunctionBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.ARGUMENT
                });
                findings.addAll(Arrays.asList(items));
            }

            public void visitPlSqlBlock(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.VARIABLE_DECL
                });
                findings.addAll(Arrays.asList(items));
            }
        }).process(ctxPath);

        final List<LookupElement> out = new ArrayList<LookupElement>();
        for (ContextItem item : findings) {
            String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (lookUpStr == null || name.toLowerCase().startsWith(lookUpStr.toLowerCase())) {
                Type t = TypeFactory.decodeType(item.getValue());
                String ctxName = findCtxAncestor(item.getCtxPath(), new int[]{
                        ContextPath.PACKAGE_BODY,
                        ContextPath.PACKAGE_SPEC,
                        ContextPath.PROCEDURE_BODY,
                        ContextPath.FUNCTION_BODY
                });

                out.add(GenericLookupElement.create(name, t.toString(), ctxName, Icons.VARIABLE_DECL));
            }
        }
        return out;
    }

    public List<LookupElement> collectFuncCall(String usageCtx, String prefix, String lookUpStr) {
        List<LookupElement> out = new ArrayList<LookupElement>();
        if (prefix == null) {
            // 1. call a procedure inside the package
            // 2. a schema wide procedure -- todo
            // 3. system function
            ContextPathUtil.ContextAttributes attrs = ContextPathUtil.extractPackageCtx(usageCtx);
            if(attrs == null){
                // usage context has no package context
                out.addAll(collectSchemaWideFunctions(lookUpStr));
            } else {
                // search for items inside package [local]
                out.addAll(collectFunctionsInCtx(attrs.getCtxPath(),lookUpStr));
                // todo -- makes a sense to search for PACKAGE SPEC in extrn context
            }

/*
            String pkgName = ContextPathUtil.extractPackageName(usageCtx);
            out.addAll(collectFunctionsInPkg(
                    new int[]{ContextPath.PACKAGE_SPEC, ContextPath.PACKAGE_BODY},
                    pkgName,
                    lookUpStr)
            );
*/

            out.addAll(collectSystemFuncCall(lookUpStr));
        } else {
            // call a function of the specified package
            PackageResolveHelper rhlp = resolver.resolvePackage(prefix);
            if(rhlp != null){
                out.addAll(collectFunctionsInCtx(rhlp, lookUpStr));
            }
        }
        return out;
    }

    private Collection<LookupElement> collectSchemaWideFunctions(String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] procs = nameProvider.findTopLevelItems(
                new int[]{ContextPath.FUNCTION_BODY, ContextPath.FUNCTION_SPEC}, null
        );

        for (ContextItem proc : procs) {
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.FUNCTION_SPEC ? Icons.FUNCTION_SPEC : Icons.FUNCTION_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }

        return out;
    }

    private Collection<LookupElement> collectFunctionsInCtx(String ctxPath, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] procs = nameProvider.findLocalCtxItems(
                ctxPath, new int[]{ContextPath.FUNCTION_BODY, ContextPath.FUNCTION_SPEC}
        );

        for (ContextItem proc : procs) {
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.FUNCTION_SPEC ? Icons.FUNCTION_SPEC : Icons.FUNCTION_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }

        return out;
    }

    private Collection<LookupElement> collectFunctionsInCtx(PackageResolveHelper rhlp, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        Iterator<ContextItem> ite = rhlp.iterateOverChildren(
                new int[]{ContextPath.FUNCTION_BODY, ContextPath.FUNCTION_SPEC}
        );
        while (ite.hasNext()) {
            ContextItem proc = ite.next();
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.FUNCTION_SPEC ? Icons.FUNCTION_SPEC : Icons.FUNCTION_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }
        return out;
    }

    public List<LookupElement> collectProcCall(String usageCtx, String prefix, String lookUpStr) {

        List<LookupElement> out = new ArrayList<LookupElement>();
        if (prefix == null) {
            // call a procedure inside the package or a schema wide procedure
            ContextPathUtil.ContextAttributes attrs = ContextPathUtil.extractPackageCtx(usageCtx);
            if(attrs == null){
                // usage context has no package context
                out.addAll(collectSchemaWideProcedures(lookUpStr));
            } else {
                // search for items inside package [local]
                out.addAll(collectProceduresInCtx(attrs.getCtxPath(),lookUpStr));
                // todo -- makes a sense to search for PACKAGE SPEC in extrn context
            }
        } else {
            // call a procedure of the specified package
            PackageResolveHelper rhlp = resolver.resolvePackage(prefix);
            if(rhlp != null){
                out.addAll(collectProceduresInCtx(rhlp, lookUpStr));
            }
        }
        return out;
    }


    private Collection<LookupElement> collectProceduresInCtx(PackageResolveHelper rhlp, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        Iterator<ContextItem> ite = rhlp.iterateOverChildren(
                new int[]{ContextPath.PROCEDURE_BODY, ContextPath.PROCEDURE_SPEC}
        );
        while (ite.hasNext()) {
            ContextItem proc = ite.next();
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.PROCEDURE_SPEC ? Icons.PROCEDURE_SPEC : Icons.PROCEDURE_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }

        return out;
    }

    private Collection<LookupElement> collectProceduresInCtx(String ctxPath, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] procs = nameProvider.findLocalCtxItems(
                ctxPath, new int[]{ContextPath.PROCEDURE_BODY, ContextPath.PROCEDURE_SPEC}
        );

        for (ContextItem proc : procs) {
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.PROCEDURE_SPEC ? Icons.PROCEDURE_SPEC : Icons.PROCEDURE_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }

        return out;
    }


    private Collection<LookupElement> collectSchemaWideProcedures(String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] procs = nameProvider.findTopLevelItems(
                new int[]{ContextPath.PROCEDURE_BODY, ContextPath.PROCEDURE_SPEC}, null
        );

        for (ContextItem proc : procs) {
            String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
            if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                Icon icon = ctxType == ContextPath.PROCEDURE_SPEC ? Icons.PROCEDURE_SPEC : Icons.PROCEDURE_BODY;
                ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                out.add(ProcedureLookupElement.create(procName, args, icon));
            }
        }

        return out;
    }

/*
    private Collection<LookupElement> collectProceduresInPkg(int[] ctxTypes, String pkgName, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] items = nameProvider.findCtxItems(ctxTypes, null);
        for (ContextItem item : items) {
            String last = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (last.equalsIgnoreCase(pkgName)) {
                ContextItem[] procs = nameProvider.findCtxItems(
                        item.getCtxPath(), new int[]{ContextPath.PROCEDURE_BODY, ContextPath.PROCEDURE_SPEC}
                );

                for (ContextItem proc : procs) {
                    String procName = ContextPathUtil.extractLastCtxName(proc.getCtxPath());
                    if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                        int ctxType = ContextPathUtil.extractLastCtxType(proc.getCtxPath());

                        Icon icon = ctxType == ContextPath.PROCEDURE_SPEC ? Icons.PROCEDURE_SPEC : Icons.PROCEDURE_BODY;
                        ArgumentSpec[] args = ContextPathUtil.extractArguments(proc.getValue());
                        out.add(ProcedureLookupElement.create(procName, args, icon));
                    }
                }
            }
        }
        return out;
    }
*/

/*
    private Collection<LookupElement> collectFunctionsInPkg(int[] ctxTypes, String pkgName, String lookUpStr) {
        Set<LookupElement> out = new HashSet<LookupElement>();
        ContextItem[] items = nameProvider.findCtxItems(ctxTypes, null);
        for (ContextItem item : items) {
            String last = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (last.equalsIgnoreCase(pkgName)) {
                ContextItem[] funcs = nameProvider.findCtxItems(
                        item.getCtxPath(), new int[]{ContextPath.FUNCTION_BODY, ContextPath.FUNCTION_SPEC}
                );

                for (ContextItem func : funcs) {
                    String procName = ContextPathUtil.extractLastCtxName(func.getCtxPath());
                    if (lookUpStr == null || procName.startsWith(lookUpStr.toLowerCase())) {
                        int ctxType = ContextPathUtil.extractLastCtxType(func.getCtxPath());

                        Icon icon = ctxType == ContextPath.FUNCTION_SPEC ? Icons.FUNCTION_SPEC : Icons.FUNCTION_BODY;
                        ArgumentSpec[] args = ContextPathUtil.extractArguments(func.getValue());
                        Type t = ContextPathUtil.extractRetType(func.getValue());
                        out.add(FunctionLookupElement.create(procName, args, t, icon));
                    }
                }
            }
        }
        return out;
    }
*/

    public List<LookupElement> collectSystemFuncCall(String lookUpStr) {
        final List<LookupElement> out = new ArrayList<LookupElement>();
        ContextItem[] findings = nameProvider.findTopLevelItems(new int[]{ContextPath.SYSTEM_FUNC}, null);
        for (ContextItem item : findings) {
            String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (lookUpStr == null || name.toLowerCase().startsWith(lookUpStr.toLowerCase())) {
                ArgumentSpec[] args = ContextPathUtil.extractArguments(item.getValue());
                Type t = ContextPathUtil.extractRetType(item.getValue());
                out.add(FunctionLookupElement.create(name, args, t, Icons.FUNCTION_SPEC));
            }
        }
        return out;
    }

    public List<LookupElement> collectPackageVariants(String lookUpStr) {
        final List<LookupElement> out = new ArrayList<LookupElement>();
        ContextItem[] items = nameProvider.findTopLevelItems(new int[]{ContextPath.PACKAGE_SPEC}, null);
        for (ContextItem item : items) {
            String last = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (lookUpStr == null || last.startsWith(lookUpStr.toLowerCase())) {
                out.add(PackageLookupElement.create(last, Icons.PACKAGE_SPEC));

            }
        }
        return out;
    }

    public List<LookupElement> collectVarVariantsInPackage(String _pkgName, String lookUpStr) {
        PackageResolveHelper pdesc = resolver.resolvePackage(_pkgName);
        final List<LookupElement> out = new ArrayList<LookupElement>();

        if(pdesc != null){
            Iterator<ContextItem> ite = pdesc.iterateOverChildren(new int[]{ContextPath.VARIABLE_DECL});
            while(ite.hasNext()){
                ContextItem item = ite.next();
                String last = ContextPathUtil.extractLastCtxName(item.getCtxPath());
                if (lookUpStr == null || last.startsWith(lookUpStr.toLowerCase())) {
                    Type t = TypeFactory.decodeType(item.getValue());
                    out.add(GenericLookupElement.create(last, t.toString(), _pkgName.toLowerCase(), Icons.VARIABLE_DECL));
                }
            }
        }
/*
        String pkgName = _pkgName.toLowerCase();
        ContextItem[] items = nameProvider.findCtxItems(new int[]{ContextPath.PACKAGE_SPEC}, pkgName);
        for (ContextItem item : items) {
            ContextItem[] vars = nameProvider.findCtxItems(item.getCtxPath(), new int[]{ContextPath.VARIABLE_DECL});
            for (ContextItem var : vars) {
                String last = ContextPathUtil.extractLastCtxName(var.getCtxPath());
                if (lookUpStr == null || last.startsWith(lookUpStr.toLowerCase())) {
                    Type t = TypeFactory.decodeType(var.getValue());
                    out.add(GenericLookupElement.create(last, t.toString(), pkgName, Icons.VARIABLE_DECL));
                }
            }
        }
*/
        return out;
    }

    public Collection<LookupElement> collectParametersNames(ParameterReference parent, String lookUpStr) {
        final List<LookupElement> out = new ArrayList<LookupElement>();
        Callable callable = parent.getCallArgument().getCallable();
        ResolveDescriptor[] rhlp;
        if(callable != null && (rhlp = resolveCallWithOverloads(callable)).length > 0){
            CallArgumentList argList = parent.getCallArgument().getCallArgumentList();
            Set<String> params = getParametersNames(argList);
            // add to the lookup list parameters not entered by user
            for(ResolveDescriptor r: rhlp){
                if(r instanceof ExecutableResolveHelper){
                    ExecutableResolveHelper chlp = (ExecutableResolveHelper) r;
                    for(ArgumentSpec spec: chlp.getArgumentSpecification()){
                        String name = spec.getName().toLowerCase();
                        if(lookUpStr == null || name.startsWith(lookUpStr.toLowerCase())){
                            if(!params.contains(name)){
                                out.add(GenericLookupElement.create(
                                        name, spec.getType().toString(), chlp.getName(), null)
                                );
                            }
                        }
                    }
                }
            }
/*
            if(rhlp instanceof ExecutableResolveHelper){
                ExecutableResolveHelper chlp = (ExecutableResolveHelper) rhlp;
                for(ArgumentSpec spec: chlp.getArgumentSpecification()){
                    out.add(GenericLookupElement.create(
                            spec.getName(), spec.getType().toString(), chlp.getName(), null)
                    );
                }
            }
*/
        }
        return out;
    }

    public Collection<LookupElement> collectColumnNames(TableAlias tab, String lookUpStr) {
        final List<ColumnElement> columns = new ArrayList<ColumnElement>();
        ResolveDescriptor rhlp = resolver.resolveTableRef(tab.getTableName());

        if (rhlp != null) {
            List<ColumnElement> list = collectColumnsForTable(rhlp, tab.getTableName(), tab.getAlias(), tab.getAlias() != null);
            addWithMarking(columns, list);
            List<LookupElement> out = new ArrayList<LookupElement>(columns.size());
            for (int i = 0; i < columns.size(); i++) {
                ColumnElement it = columns.get(i);
                out.add(SelectFieldLookupElement.create(tab.getAlias(), it));
            }
            return out;
        }


        return new ArrayList<LookupElement>();
    }


    private Set<String> getParametersNames(CallArgumentList argList){
        Set<String> out = new HashSet<String>();
        for(CallArgument a: argList.getArguments()){
            String name = a.getParameterName();
            if(name != null){
                out.add(name.toLowerCase());
            }
        }

        return out;
    }

    private ResolveDescriptor[] resolveCallWithOverloads(Callable callable){
        int ctype = callable instanceof FunctionCall? ContextPath.FUNC_CALL: ContextPath.PROC_CALL;
        String ctxType = callable.getCtxPath1().getPath();
        return resolver.resolveCallable(
                ctype, ctxType, callable.getFunctionName()
        );
    }

    private String findCtxAncestor(String ctxPath, int[] types) {
        String parentCtxPath = null;
        while ((parentCtxPath = ContextPathUtil.extractParentCtx(ctxPath)) != null) {
            int ctxType = ContextPathUtil.extractLastCtxType(parentCtxPath);
            for (int type : types) {
                if (type == ctxType) {
                    return ContextPathUtil.extractLastCtxName(parentCtxPath);
                }
            }
            ctxPath = parentCtxPath;
        }
        return null;
    }

    public List<LookupElement> collectTypeNameVariants(String usageCtx, String lookupString) {

        final List<ContextItem> findings = new ArrayList<ContextItem>();

        new ContextPathProcessor(new ContextPathVisitor() {
            public void visitPackageBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.RECORD_TYPE,
                        ContextPath.OBJECT_TYPE,
                        ContextPath.VARRAY_TYPE,
                        ContextPath.COLLECTION_TYPE
                });

                findings.addAll(Arrays.asList(items));
                // because type is referenced from a package body makes a sense
                // to search for the type in the package spec also

                PackageResolveHelper pdesc = resolver.resolvePackage(name);
                if(pdesc != null){
                    Iterator<ContextItem> ite = pdesc.iterateOverChildren(new int[]{
                            ContextPath.RECORD_TYPE,
                            ContextPath.OBJECT_TYPE,
                            ContextPath.VARRAY_TYPE,
                            ContextPath.COLLECTION_TYPE
                    });
                    while(ite.hasNext()){
                        ContextItem item = ite.next();
                        findings.add(item);
                    }
                }
/*
                ContextItem[] pkgSpecs = nameProvider.findCtxItems(new int[]{ContextPath.PACKAGE_SPEC}, name);
                for (ContextItem spec : pkgSpecs) {
                    ContextItem[] items2 = nameProvider.findCtxItems(spec.getCtxPath(), new int[]{
                            ContextPath.RECORD_TYPE,
                            ContextPath.OBJECT_TYPE,
                            ContextPath.VARRAY_TYPE,
                            ContextPath.COLLECTION_TYPE
                    });

                    findings.addAll(Arrays.asList(items2));
                }
*/
            }

            public void visitPackageSpec(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.RECORD_TYPE,
                        ContextPath.OBJECT_TYPE,
                        ContextPath.VARRAY_TYPE,
                        ContextPath.COLLECTION_TYPE
                });

                findings.addAll(Arrays.asList(items));
            }

            public void visitProcedureBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.RECORD_TYPE,
                        ContextPath.OBJECT_TYPE,
                        ContextPath.VARRAY_TYPE,
                        ContextPath.COLLECTION_TYPE
                });

                findings.addAll(Arrays.asList(items));
            }

            public void visitFunctionBody(String ctxPath, int ctxType, String name) {
                ContextItem[] items = nameProvider.findLocalCtxItems(ctxPath, new int[]{
                        ContextPath.RECORD_TYPE,
                        ContextPath.OBJECT_TYPE,
                        ContextPath.VARRAY_TYPE,
                        ContextPath.COLLECTION_TYPE
                });

                findings.addAll(Arrays.asList(items));
            }
        }).process(usageCtx);

        List<LookupElement> out = new ArrayList<LookupElement>();
        for (ContextItem item : findings) {
            String name = ContextPathUtil.extractLastCtxName(item.getCtxPath());
            if (lookupString == null || name.toLowerCase().startsWith(lookupString.toLowerCase())) {
                // todo -- tune Icon according to the found item
                out.add(GenericLookupElement.create(name, Icons.RECORD_TYPE_DECL));
            }
        }

        return out;
    }


    private List<ColumnElement> collectColumnsForSubquery(String ctx, String tabl_alias) {
        String value = nameProvider.getContextPathValue(ctx);
        final Map<String, ColumnElement> out = new HashMap<String, ColumnElement>();

        // populate output list with the columns, if collision on columns found - strike it out
        final String tableAliasFull = (tabl_alias != null) ? tabl_alias.toLowerCase() : null;
        ResolveUtil.processSubqueryValue(value, new ResolveUtil.SubqueryFieldProcessor() {
            public void processField(String value1, boolean isAliasName) {
                String[] pair = value1.split("\\.");
                String name = pair[pair.length - 1].toLowerCase();
                String lookupName = name + (tableAliasFull != null ? tableAliasFull : "");
                ColumnElement item = out.get(lookupName);
                if (item == null) {
                    out.put(lookupName, new ColumnElement(name, tableAliasFull, null, null));
                } else {
                    item.setStrikeout(true);
                }
            }
        });

        return new ArrayList<ColumnElement>(out.values());
    }


    private List<ColumnElement> collectColumnsForTable(@NotNull ResolveDescriptor rhlp, String table_name, String table_alias, boolean isColumnPrefixedWithTAlias) {

        //String tableAliasFull = (table_alias!=null)? table_name + " " + table_alias: table_name;
        List<ColumnElement> out = new ArrayList<ColumnElement>();
        switch (ContextPathUtil.extractLastCtxType(rhlp.getCtxPath())) {
            case ContextPath.VIEW_DEF:
                String value = rhlp.getValue();
                ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                for (ArgumentSpec a : args) {
                    Type t = a.getType();
                    out.add(new ColumnElement(a.getName(), table_name, table_alias, isColumnPrefixedWithTAlias, t != null ? t.toString() : null, null));
                }

                break;
            case ContextPath.TABLE_DEF:
                Iterator<ContextItem> ite = rhlp.iterateOverChildren();
                while (ite.hasNext()) {
                    ContextItem item = ite.next();
                    String columnCtx = item.getCtxPath();
                    int type = ContextPathUtil.extractLastCtxType(columnCtx);
                    if (type == ContextPath.COLUMN_DEF) {
                        String columnName = ContextPathUtil.extractLastCtxName(columnCtx);
                        String _type = item.getValue();
                        String t = null;
                        try {
                            t = ContextPathUtil.decodeColumnTypeFromValue(_type).toString();
                        } catch (Throwable e) {
                            // ignore
                        }
                        out.add(new ColumnElement(columnName, table_name, table_alias, isColumnPrefixedWithTAlias, t, null));
                    }
                }
                break;
        }

        return out;
    }


    public class ColumnElement extends NamedYItem {

        public String tableName;
        public String tableAlias;
        boolean isFullQualifiedColumn;

        public ColumnElement(String name, String tableName, String type, Icon icon) {
            super(name);
            this.icon = icon;
            this.type = type;

            if (tableName != null) {
                this.tail = "(" + tableName + ")";
            }
            this.tableName = tableName == null ? "" : tableName;
            this.tableAlias = "";
        }

        public ColumnElement(String name, String tableName, String tableAlias, boolean isColumnPrefixedWithTAlias, String type, Icon icon) {
            super(name);
            this.icon = icon;
            this.type = type;
            if (isColumnPrefixedWithTAlias) {
                this.tail = "(" + tableName + ")";
            } else {
                this.tail = "(" + tableName + (tableAlias != null ? " " + tableAlias : "") + ")";
            }
            this.tableName = tableName;
            this.tableAlias = tableAlias == null ? "" : tableAlias;
        }

        public void setFullQualifiedColumn(boolean b) {
            this.isFullQualifiedColumn = b;
        }

        public boolean isFullQualifiedColumn() {
            return isFullQualifiedColumn;
        }

        public String getQualifyName() {
            return tableAlias.length() > 0 ? tableAlias : (tableName.length() > 0 ? tableName : null);
        }
    }

}
