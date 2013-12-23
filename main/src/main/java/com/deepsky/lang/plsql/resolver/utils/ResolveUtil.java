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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.CreateIndex;
import com.deepsky.lang.plsql.psi.ddl.RenameTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.psi.types.RowtypeType;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.RefHolder;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResolveUtil {


    public static interface FromTabProcessor {
        void processSubquery(String ctx, String table_alias, boolean isColumnPrefixedWithTabAlias);

        void processTable(String table_name, String table_alias, boolean isColumnPrefixedWithTabAlias);
    }

    public static interface SubqueryFieldProcessor {
        void processColumnField(String value, boolean isAliasName);
        void processAsterisk(@Nullable String tableAlias);
    }


    public static void processSubqueryValue(String subqueryValue, SubqueryFieldProcessor proc) {
        if (subqueryValue != null) {
            String[] fields = subqueryValue.split(",");
            for (final String field : fields) {
                if(field.indexOf('|') >= 0){
                    // Case: "<expression>|." or ".|<alias>"
                    String[] expr_alias = field.split("\\|");
                    if (expr_alias[0].charAt(0) == '.') {
                        // case: '<expression>|.'
                        proc.processColumnField(expr_alias[1], false);
                    } else {
                        // case: '.|<alias>'
                        proc.processColumnField(expr_alias[0], true);
                    }
                } else if(field.equals("*")){
                    // Case: "*"
                    proc.processAsterisk(null);
                } else if(field.endsWith(".*")){
                    // Case: "table_alias.*"
                    proc.processAsterisk(field.substring(0, field.length()-2));
                }
            }
        }
    }

    public static void iterateOverTables(String alias_tab, String val, FromTabProcessor proc) {
        if(val == null){
            return;
        }
        String[] tabs = val.split("\\,");
        for (String tab : tabs) {
            resolveTableColumn(tab, alias_tab, proc);
        }
    }

    private static void resolveTableColumn(String selectEncodedValue, String alias_tab, FromTabProcessor proc) {
        // name_alias_pair: <table_name>*<table_alias>
        String[] name_alias_pair = selectEncodedValue.split("\\*");
        boolean isSubquery = name_alias_pair[0].charAt(0) == '.';

        if (name_alias_pair.length == 2) {
            // case: table with an alias
            if (alias_tab != null) {
                // case: <table_name>.<column_name>
                // case: <alias>.<column_name>
                if (isSubquery) {
                    if (name_alias_pair[1].equalsIgnoreCase(alias_tab)) {
                        // alias_tab == <subquery_alias> (case: <subquery_alias>.<column_name>)
                        proc.processSubquery(name_alias_pair[0].substring(1, name_alias_pair[0].length()), alias_tab, true); //, column);
                    }
                } else {
                    if (name_alias_pair[0].equalsIgnoreCase(alias_tab)) {
                        // alias_tab == <table_name> (case: <table_name>.<column_name>)
                        proc.processTable(name_alias_pair[0].toLowerCase(), alias_tab, true);
                    } else if (name_alias_pair[1].equalsIgnoreCase(alias_tab)) {
                        // alias_tab ==  <table_alias> (case: <alias_tab>.<column_name>)
                        proc.processTable(name_alias_pair[0].toLowerCase(), alias_tab, true);
                    }
                }
            } else {
                // column not prefixed with an alias
                if (isSubquery) {
                    proc.processSubquery(name_alias_pair[0].substring(1, name_alias_pair[0].length()), name_alias_pair[1], false);
                } else {
                    proc.processTable(name_alias_pair[0].toLowerCase(), name_alias_pair[1], false);
                }
            }
        } else {
            // case: table w/o an alias
            if (alias_tab != null) {
                // check the case: alias_tab == table_name
                if (name_alias_pair[0].equalsIgnoreCase(alias_tab)) {
                    // alias_tab == <table_name> (case: <table_name>.<column_name>)
                    if (isSubquery) {
                        proc.processSubquery(name_alias_pair[0].substring(1, name_alias_pair[0].length()), alias_tab, true);
                    } else {
                        proc.processTable(name_alias_pair[0].toLowerCase(), alias_tab, true);
                    }
                }
            } else {
                // case: <column_name>
                if (isSubquery) {
                    proc.processSubquery(name_alias_pair[0].substring(1, name_alias_pair[0].length()), null, false);
                } else {
                    proc.processTable(name_alias_pair[0].toLowerCase(), null, false);
                }
            }
        }
    }

    private final static TokenSet callableNameKids3 = TokenSet.create(
            PlSqlElementTypes.NAME_FRAGMENT,
            PlSqlElementTypes.BUILTIN_FUNC_NAME,
            PlSqlElementTypes.EXEC_NAME_REF);

    private final static TokenSet callableNameKids2 = TokenSet.create(
            PlSqlElementTypes.NAME_FRAGMENT,
            PlSqlElementTypes.EXEC_NAME_REF);


    public static ResolveDescriptor resolve(Callable elem, NameFragmentCallback callback) {
        ASTNode callableName = elem.getNode().findChildByType(PlSqlElementTypes.CALLABLE_NAME_REF);
        ResolveDescriptor hlp = null;

        ASTNode[] nested = callableName.getChildren(callableNameKids3);
        int k = 0;
        do {
            hlp = callback.resolve(hlp, (PlSqlElement) nested[k].getPsi());
        } while (hlp != null && ++k < nested.length);
        return hlp;
    }


    public static ResolveDescriptor resolve(CollectionMethodCall2 elem, NameFragmentCallback callback) {
        ASTNode[] funcs = elem.getNode().getChildren(
                TokenSet.create(PlSqlElementTypes.PROCEDURE_CALL)
        );
        ResolveDescriptor hlp = null;
        int i = 0;
        do {
            ASTNode callableName = funcs[i].findChildByType(PlSqlElementTypes.CALLABLE_NAME_REF);
            ASTNode[] nested = callableName.getChildren(callableNameKids2);
            int k = 0;
            do {
                hlp = callback.resolve(hlp, (PlSqlElement) nested[k].getPsi());
            } while (hlp != null && ++k < nested.length);
        } while (hlp != null && ++i < funcs.length);
        return hlp;
    }


    public static ResolveDescriptor resolve(CollectionMethodCall elem, NameFragmentCallback callback) {
        ASTNode[] funcs = elem.getNode().getChildren(
                TokenSet.create(PlSqlElementTypes.FUNCTION_CALL, PlSqlElementTypes.C_RECORD_ITEM_REF)
        );

        ResolveDescriptor hlp = null;
        int i = 0;
        do {
            if (funcs[i].getElementType() == PlSqlElementTypes.FUNCTION_CALL) {
                ASTNode callableName = funcs[i].findChildByType(PlSqlElementTypes.CALLABLE_NAME_REF);
                ASTNode[] nested = callableName.getChildren(callableNameKids2);
                int k = 0;
                do {
//                    hlp = callback.resolve(hlp, (CompositeElementExt) nested[k]);
                    hlp = callback.resolve(hlp, (PlSqlElement) nested[k].getPsi());
                } while (hlp != null && ++k < nested.length);
            } else {
                // PlSqlElementTypes.C_RECORD_ITEM_REF
//                hlp = callback.resolve(hlp, (CompositeElementExt) funcs[i]);
                hlp = callback.resolve(hlp, (PlSqlElement) funcs[i].getPsi());
            }
        } while (hlp != null && ++i < funcs.length);
        return hlp;
    }


    public static ResolveDescriptor resolveColumnNameRef(final ResolveHelper resolver, final ColumnNameRef ref) {
        final ResolveDescriptor[] out = {null};
        final String columnText = StringUtils.discloseDoubleQuotes(ref.getText()).toLowerCase();

        ResolveUtil.processInContext(ref, new ColumnNameRefCallback() {
            public void process(ColumnFKSpec fkSpec) {
                String text = fkSpec.getReferencedTable();
                RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", text);
                ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                if (tableRHelper != null) {
                    RefHolder holder2 = new RefHolder(ContextPath.TABLE_COLUMN_REF, tableRHelper.getCtxPath(), columnText);
                    out[0] = resolver.resolveReference(holder2);
                }
            }

            public void process(ColumnTypeRef cTypeRef) {
                TableRef tref = cTypeRef.getTableRef();
                String text = tref.getText();
                RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", text);
                ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                if (tableRHelper != null) {
                    ResolveDescriptor[] rr = tableRHelper.resolve(columnText);
                    if (rr.length == 1) {
                        out[0] = rr[0];
                    }
                }
            }

            public void processOwnerColumns(ForeignKeyConstraint fkConst) {
                if (fkConst.getParent() instanceof TableDefinition) {
                    String text = ((TableDefinition) fkConst.getParent()).getTableName();
                    RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", text);
                    ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                    if (tableRHelper != null) {
                        ResolveDescriptor[] out1 = tableRHelper.resolve(columnText);
                        if (out1.length == 1) {
                            out[0] = out1[0];
                        }
                    }
                }
            }

            public void processForeignColumns(ForeignKeyConstraint fkConst) {
                TableRef tref = fkConst.getReferencedTable2();
                String text = tref.getTableName();
                RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", text);
                ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                if (tableRHelper != null) {
                    ResolveDescriptor[] out1 = tableRHelper.resolve(columnText);
                    if (out1.length == 1) {
                        out[0] = out1[0];
                    }
                }
            }

            public void processOwnerColumns(PrimaryKeyConstraint pkConst) {
                // TODO -- names in current context must be resolved directly without searching index
                // Make sure syntax is valid
                if (pkConst.getParent() instanceof TableDefinition) {
                    String text = ((TableDefinition) pkConst.getParent()).getTableName();
                    RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", text);
                    ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                    if (tableRHelper != null) {
                        ResolveDescriptor[] out1 = tableRHelper.resolve(columnText);
                        if (out1.length == 1) {
                            out[0] = out1[0];
                        }
                    }
                }
            }

            public void processTableColumn(TableDefinition t) {
                RefHolder holder = new RefHolder(ContextPath.TABLE_REF, "", t.getTableName());
                ResolveDescriptor tableRHelper = resolver.resolveReference(holder);
                if (tableRHelper != null) {
                    ResolveDescriptor[] out1 = tableRHelper.resolve(columnText);
                    if (out1.length == 1) {
                        out[0] = out1[0];
                    }
                }
            }

            public void process(AlterTable alter) {
                // todo - implement me
            }

            public void process(Comment comment) {
                // todo - implement me
            }

            public void process(PsiElement parent) {
                String path = ref.getCtxPath1().getPath();
                RefHolder holder = new RefHolder(ContextPath.TABLE_COLUMN_REF, path, columnText);
                out[0] = resolver.resolveReference(holder);
            }
        });

        return out[0];
    }

    public static void processInContext(ColumnNameRef ref, ColumnNameRefCallback callback) {
        final PsiElement parent = ref.getParent();
        final IElementType ietype = parent.getNode().getElementType();

        if ( ietype == PLSqlTypesAdopted.RANGE_PARTITION
                || ietype == PLSqlTypesAdopted.HASH_PARTITION) {
            // column names references
            if(parent.getParent().getParent() instanceof TableDefinition){
                callback.processTableColumn((TableDefinition) parent.getParent().getParent());
            } else {
                System.out.println("[Syntax issue] TableDefinition was expected but found: " + parent.getParent().getParent());
            }
        } else if (ietype == PLSqlTypesAdopted.OWNER_COLUMN_NAME_LIST) {
            final PsiElement candidate = parent.getParent();
            if (candidate instanceof ForeignKeyConstraint) {
                callback.processOwnerColumns((ForeignKeyConstraint) candidate);
            } else if (candidate instanceof PrimaryKeyConstraint){
                callback.processOwnerColumns((PrimaryKeyConstraint) candidate);
            } else {
                // Syntax looks not correct (PSI tree corrupted)
            }
        } else if (ietype == PLSqlTypesAdopted.COLUMN_NAME_LIST) {
            // FK_SPEC case, requires reference table resolving
            final PsiElement candidate = parent.getParent();
            if (candidate instanceof ForeignKeyConstraint) {
                callback.processForeignColumns((ForeignKeyConstraint) candidate);
            } else {
                // Syntax looks not correct (PSI tree corrupted)
            }

        } else if (ietype == PLSqlTypesAdopted.COLUMN_TYPE_REF) {
            ColumnTypeRef cTypeRef = (ColumnTypeRef) parent;
            callback.process(cTypeRef);

        } else if (ietype == PLSqlTypesAdopted.FOR_UPDATE_CLAUSE) {
            // generic case
            // TODO -- not correct, multiple refs FIXME
            callback.process(parent);
        } else if (ietype == PLSqlTypesAdopted.COMMENT) {
            Comment comment = (Comment) parent;
            callback.process(comment);
        } else if (ietype == PLSqlTypesAdopted.COLUMN_FK_SPEC) {
            ColumnFKSpec fkSpec = (ColumnFKSpec) parent;
            callback.process(fkSpec);

        } else if (ietype == PLSqlTypesAdopted.UNIQUE_CONSTRAINT) {
            // generic case
            // TODO -- not correct FIXME
            callback.process(parent);
        } else if (ietype == PLSqlTypesAdopted.ALTER_TABLE) {
            // TODO -- not correct FIXME
            AlterTable alter = (AlterTable) parent;
            callback.process(alter);
        } else {
            // generic case
            callback.process(parent);
        }
    }


    public static void processInContext(@NotNull TableRef ref, @NotNull TableRefCallback callback) {
        final PsiElement parent = ref.getParent();
        if(parent instanceof TableAlias){
            callback.process((TableAlias)parent);
        } else if(parent instanceof ColumnFKSpec){
            callback.process((ColumnFKSpec)parent);
        } else if (parent instanceof ForeignKeyConstraint){
            callback.process((ForeignKeyConstraint)parent);
        } else if (parent instanceof AlterTable){
            callback.process((AlterTable)parent);
        } else if (parent instanceof RenameTable){
            callback.process((RenameTable)parent);
        } else if (parent instanceof Comment){
            callback.process(ref);
        } else if (parent instanceof CreateIndex){
            callback.process((CreateIndex)parent);
        } else if (parent instanceof ColumnTypeRef){
            callback.process((ColumnTypeRef)parent);
        } else if (parent instanceof PlSqlFile){
            callback.process((PlSqlFile)parent);
        } else if (parent instanceof RowtypeType){
            callback.process((RowtypeType)parent);
        } else if(parent instanceof ErrorTokenWrapper){
            callback.process((ErrorTokenWrapper)parent);
        } else if(parent.getNode().getElementType() == PlSqlElementTypes.DROP_TABLE){
            callback.process(ref);
        } else if(parent.getNode().getElementType() == PlSqlElementTypes.DROP_VIEW){
            callback.process(ref);
        } else if(parent.getNode().getElementType() == PlSqlElementTypes.TRUNCATE_TABLE){
            callback.process(ref);
        } else if(parent.getNode().getElementType() == PlSqlElementTypes.DML_TRIGGER_CLAUSE){
            // TODO -- implement me
        } else if(parent.getNode().getElementType() == PlSqlElementTypes.INSTEADOF_TRIGGER){
            // TODO -- implement me
        }
    }

}
