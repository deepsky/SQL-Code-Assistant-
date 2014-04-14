/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DDLProcessor extends CompletionBase {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     CREATE SEQUENCE
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/..#CREATE_SEQUENCE/#CREATE #ERROR_TOKEN_A/#SEQUENCE 1#IDENTIFIER #C_MARKER")
    public void process$Sequence(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     CREATE TABLE
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #ERROR_TOKEN_A/#TABLE 2#TABLE_NAME_DDL #OPEN_PAREN .. #COLUMN_DEF #CLOSE_PAREN #C_MARKER")
    public void process$Table(C_Context ctx, ASTNode node, ASTNode node2) {
        completeStart(ctx);
        // TODO - implement more cases
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #TABLE #TABLE_NAME_DDL #AS #ERROR_TOKEN_A/#C_MARKER")
    public void process$TableFromSelectAs(C_Context ctx, ASTNode node) {
        ctx.addElement(SelectLookupElement.create());
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #TABLE ..#PK_SPEC//..#OWNER_COLUMN_NAME_LIST/..2$ColumnNameRef/#C_MARKER")
    public void process$TablePKSpec(C_Context ctx, ASTNode node, ColumnNameRef ref) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectColumnNameRef(ref, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #TABLE ..#FK_SPEC//..#OWNER_COLUMN_NAME_LIST/..2$ColumnNameRef/#C_MARKER")
    public void process$TableFKSpec(C_Context ctx, ASTNode node, ColumnNameRef ref) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectColumnNameRef(ref, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    @SyntaxTreePath("/..1$TableDefinition/#CREATE #TABLE ..#FK_SPEC/..#FOREIGN ..#REFERENCES #TABLE_REF/#C_MARKER")
    public void process$TableFKSpecRefTab(C_Context ctx, TableDefinition tab) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));

        // Filter out a table which is a context of the FK specification
        final String tableName = tab.getTableName();
        Iterator<LookupElement> ite = variants.iterator();
        while (ite.hasNext()) {
            LookupElement e = ite.next();
            if (e.getLookupString().equalsIgnoreCase(tableName)) {
                ite.remove();
            }
        }
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #TABLE ..#PARTITION_SPEC/#RANGE_PARTITION/..2$ColumnNameRef/#C_MARKER")
    public void process$TablePartByRange(C_Context ctx, ASTNode node, ColumnNameRef ref) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectColumnNameRef(ref, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #TABLE ..#COLUMN_DEF/#COLUMN_NAME_DDL #TYPE_NAME_REF/2$NameFragmentRef/#C_MARKER")
    public void process$TableTypeName(C_Context ctx, ASTNode node, NameFragmentRef ref) {
        VariantsProvider provider = ctx.getProvider();
        List<LookupElement> variants = provider.collectDataTypeVariants(null, ctx.getLookup());
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/..1#TABLE_DEF/#CREATE #ERROR_TOKEN_A/#TABLE ..#COLUMN_DEF/#COLUMN_NAME_DDL #TYPE_NAME_REF/2$NameFragmentRef/#C_MARKER")
    public void process$TableType2Name(C_Context ctx, ASTNode node, NameFragmentRef ref) {
        VariantsProvider provider = ctx.getProvider();
        List<LookupElement> variants = provider.collectDataTypeVariants(null, ctx.getLookup());
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     RENAME TABLE
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/..1#RENAME_TABLE/#RENAME #TABLE #ERROR_TOKEN_A/#C_MARKER")
    public void process$RenameTable(C_Context context, ASTNode node) {
        collectTableNames(context);
    }

    // rename table t1 to t2, <caret>
    @SyntaxTreePath("/..1#RENAME_TABLE/#RENAME #TABLE ..#TABLE_NAME_DDL #COMMA #ERROR_TOKEN_A/#C_MARKER")
    public void process$RenameTable2(C_Context context, ASTNode node) {
        collectTableNames(context);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     DROP
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/..#DROP_TABLE/#DROP #TABLE #TABLE_REF/#C_MARKER")
    public void process$DropTable(C_Context context) {
        collectTableNames(context);
    }

    @SyntaxTreePath("/..#DROP_VIEW/#DROP #VIEW #TABLE_REF/#C_MARKER")
    public void process$DropView(C_Context context) {
        collectViewNames(context);
    }

    @SyntaxTreePath("/..#DROP_FUNCTION/#DROP #FUNCTION #CALLABLE_NAME_REF/#EXEC_NAME_REF/#C_MARKER")
    public void process$DropFunction(C_Context context) {
        collectFunctionNames(context);
    }

    @SyntaxTreePath("/..#DROP_PROCEDURE/#DROP #PROCEDURE #CALLABLE_NAME_REF/#EXEC_NAME_REF/#C_MARKER")
    public void process$DropProcedure(C_Context context) {
        collectProcedureNames(context);
    }

    @SyntaxTreePath("/..#DROP_SEQUENCE/#DROP #SEQUENCE #SEQUENCE_NAME/#C_MARKER")
    public void process$DropSequence(C_Context context) {
        collectSequenceNames(context);
    }

    @SyntaxTreePath("/..#DROP_TRIGGER/#DROP #TRIGGER #OBJECT_NAME/#C_MARKER")
    public void process$DropTrigger(C_Context context) {
        collectTriggerNames(context);
    }
}
