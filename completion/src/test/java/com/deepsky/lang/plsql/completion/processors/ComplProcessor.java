package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.psi.types.RowtypeType;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplProcessor {

    final List<LookupElement> out = new ArrayList<LookupElement>();
    VariantsProvider provider;

    private String getLookup() {
        // TODO - implement me
        return "";
    }

    //@SyntaxTreePath("/1$TableDefinition/ColumnFKSpec/TableRef")
    public void process(TableDefinition tabDef) {
        out.addAll(provider.collectTableNameVariants(getLookup()));
        // Filter out a table which is a context of the FK specification
        final String tableName = tabDef.getTableName();
        Iterator<LookupElement> ite = out.iterator();
        while (ite.hasNext()) {
            LookupElement e = ite.next();
            if (e.getLookupString().equalsIgnoreCase(tableName)) {
                ite.remove();
            }
        }
    }

    //@SyntaxTreePath("//1$ColumnTypeRef/2$TableRef")
    public void process(ColumnTypeRef parent, TableRef tableRef) {
        // TODO - implement me
    }

    //@SyntaxTreePath("//1$RowtypeType/2$TableRef")
    public void process(RowtypeType parent, TableRef tableRef) {
        // TODO - implement me
    }


    //@SyntaxTreePath("//1$SelectStatement//#QUERY_PARTITION_CLAUSE/ObjectReference/2$NameFragmentRef")
    public void process$2(SelectStatement stmt, NameFragmentRef ref) {
        // TODO - implement me
    }

    //@SyntaxTreePath("//TableAlias/TableRef")
    public void process$1() {
        // TODO - implement me
    }



    //@SyntaxTreePath("/1#IDENTIFIER")
    public void process$ident(ASTNode node) {
        // TODO - implement me
    }

    //@SyntaxTreePath("/#KEYWORD_GRANT,#SYSTEM_PRIVILEGE,1#IDENTIFIER")
    public void process$grant(ASTNode ident) {
        // TODO - implement me

    }

}
