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

package com.deepsky.lang.plsql.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.TableReference;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import org.jetbrains.annotations.NotNull;

public class TableReferenceImpl extends PlSqlReferenceBase implements TableReference {

    private static final Logger log = Logger.getInstance("#TableReferenceImpl");

    public TableReferenceImpl(ASTNode node) {
        super(node);
    }

    public PsiElement resolve() {
        try {
            return ResolveHelper.resolve_TableRef((PlSqlElement) getNode().getPsi());
        } catch (SyntaxTreeCorruptedException e1) {
            return null;
        }

//        PsiElement stmt = SupportStuff
//                .findParentNode(getNode(), new PlSqlElementType[]{
//                                PlSqlElementTypes.INSERT_SUBQUERY,
//                                PlSqlElementTypes.SELECT_COMMAND,
//                                PlSqlElementTypes.INSERT_COMMAND,
//                                PlSqlElementTypes.DELETE_COMMAND,
//                                PlSqlElementTypes.UPDATE_COMMAND}
//                );
//
//        GenericTable[] tables = new GenericTable[0];
//        if (stmt != null) {
//            if (stmt instanceof SelectStatement) {
//                FromClause from = ((SelectStatement) stmt).getFromClause();
//                if (from != null) {
//                    tables = from.getTableList();
//                } else {
//                    // TODO - FROM not found !!!
//                    return null;
//                }
//
//            } else if (stmt instanceof InsertSubquery) {
//                FromClause from = ((InsertSubquery) stmt).getFromClause();
//                if (from != null) {
//                    tables = from.getTableList();
//                } else {
//                    // TODO - FROM not found !!!
//                    return null;
//                }
//
//            } else if (stmt instanceof InsertStatement) {
//                tables = new GenericTable[1];
//                tables[0] = ((InsertStatement) stmt).getIntoTable();
//            } else if (stmt instanceof UpdateStatement) {
//                tables = new GenericTable[1];
//                tables[0] = ((UpdateStatement) stmt).getTargetTable();
//            } else if (stmt instanceof DeleteStatement) {
//                tables = new GenericTable[1];
//                tables[0] = ((DeleteStatement) stmt).getTargetTable();
//            } else {
//                // todo - what the hell is this statement?
//                return null;
//            }
//        }
//
//        for(GenericTable t: tables){
//            if (t.getAlias() != null && t.getAlias().equalsIgnoreCase(getText())) {
//                return t;
//            } else if( t instanceof PlainTable ){
//                PlainTable tab = (PlainTable) t;
//                if( tab.getTableName().equalsIgnoreCase(getText())){
//                    return t;
//                }
//            }
//        }

    }


//    public void subtreeChanged(){
//        int hh = 0;
//    }

//    @Nullable
//    public String getQuickNavigateInfo(){
//        SelectStatement select = (SelectStatement) SupportStuff
//                .findParentNode(getNode(), PlSqlElementTypes.SELECT_COMMAND);
//
//        if (select != null) {
//            FromClause from = select.getFromClause();
//            if (from != null) {
//                for (GenericTable t : from.getTableList()) {
//                    if (t.getAlias() != null && t.getAlias().equalsIgnoreCase(getText())) {
//                        if(t instanceof PlainTable){
//                            return "Table " + ((PlainTable)t).getTableName().toUpperCase();
//                        } else if(t instanceof Subquery){
//                            return "Subquery ";
//                        }
//
//                    } else if( t instanceof PlainTable ){
//                        PlainTable tab = (PlainTable) t;
//                        if( tab.getTableName().equalsIgnoreCase(getText())){
//                            return "Table " + tab.getTableName().toUpperCase();
//                        }
//                    }
//                }
//            } else {
//                // FROm not found !!!
//            }
//        }
//
//        return null;
//    }


    @NotNull
    public Object[] getVariants(String text) {
        log.info("#getVariants, type: "
                + this.getNode().getElementType()
                + ", parent: " + this.getNode().getTreeParent().getElementType()
                + ", text: '" + this.getText() + "'"
                + ", refined_text: '" + text + "'");

        return new Object[0];
    }


}
