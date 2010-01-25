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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.psi.var_proc;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.resolve.ContextEnumerator;
import com.deepsky.lang.plsql.psi.resolve.TableEnumerator;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VariantsProcessorHelpers {

    public static String[] getVariableVariants(PsiElement reference, final String prefix) {
        ASTTreeProcessor runner = new ASTTreeProcessor();
        final TokenSet tokenSet = TokenSet.create(
                PlSqlElementTypes.PROCEDURE_BODY,
                PlSqlElementTypes.FUNCTION_BODY,
                PlSqlElementTypes.CREATE_TRIGGER
        );
                              
        final List<String> variants = new ArrayList<String>();
        runner.add(new ContextEnumerator(tokenSet){
            public void handleASTNode(@NotNull ASTNode node) {
                if(node.getElementType() == PlSqlElementTypes.CREATE_TRIGGER){
                    // create variants including variables and arguments
                    if(node.getPsi() instanceof CreateTrigger){
                        ASTNode blk = node.findChildByType(PlSqlElementTypes.BEGIN_BLOCK);
                        if(blk != null){
                            ASTNode declare  = blk.findChildByType(PlSqlElementTypes.DECLARE_LIST);
                            if(declare != null){
                                DeclarationList decl = (DeclarationList) declare.getPsi();
                                variants.addAll(processDeclarations(decl, prefix));
                            }
                        }
                    }
                } else if(node.getElementType() == PlSqlElementTypes.PROCEDURE_BODY){
                    // create variants including variables and arguments
                    Procedure proc = (Procedure) node.getPsi();
                    Declaration[] decl = proc.getDeclarationList();
                    variants.addAll(processDeclarations(decl, prefix));
                    Argument[] args = proc.getArguments();
                    variants.addAll(processArguments(args, prefix));

                } else if(node.getElementType() == PlSqlElementTypes.FUNCTION_BODY){
                    // create variants including variables and arguments
                    Function func = (Function) node.getPsi();
                    Declaration[] decl = func.getDeclarationList();
                    variants.addAll(processDeclarations(decl, prefix));
                    Argument[] args = func.getArguments();
                    variants.addAll(processArguments(args, prefix));
                }
            }
        });

        runner.process(reference.getNode());

        return variants.toArray(new String[variants.size()]);
    }

    private static List<String> processArguments(Argument[] args, String prefix) {
        List<String> out = new ArrayList<String>();
        for(Argument arg: args){
            if(prefix.length() == 0
                || arg.getArgumentName().toUpperCase().startsWith(prefix.toUpperCase())){
                out.add(arg.getArgumentName());
            }
        }
        return out;
    }

    private static List<String> processDeclarations(DeclarationList declList, String prefix){
        List<String> out = new ArrayList<String>();
        for(Declaration decl: declList.getDeclList()){
            if(decl instanceof VariableDecl){
                if(prefix.length() == 0
                    || ((VariableDecl)decl).getDeclName().toUpperCase().startsWith(prefix.toUpperCase())){
                    out.add(((VariableDecl)decl).getDeclName());
                }
            }
        }

        return out;
    }

    private static List<String> processDeclarations(Declaration[] declList, String prefix){
        List<String> out = new ArrayList<String>();
        for(Declaration decl: declList){
            if(decl instanceof VariableDecl){
                if(prefix.length() == 0
                    || ((VariableDecl)decl).getDeclName().toUpperCase().startsWith(prefix.toUpperCase())){
                    out.add(((VariableDecl)decl).getDeclName());
                }
            }
        }

        return out;
    }

    public static String[] getTableColumnVariants(PsiElement elem, final String prefix) {
        final Set<String> out = new HashSet<String>();
        ASTTreeProcessor runner = new ASTTreeProcessor();

        runner.add(new TableEnumerator() {
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION); //, PLSqlTypesAdopted.INSERT_SUBQUERY);
            }

            public void handleTable(GenericTable t) {
                // 1. try to use alias or table names as a look up source
                if (prefix.length() == 0) {
                    if (t.getAlias() != null) {
                        out.add(t.getAlias().toUpperCase());
                    } else {
                        // use table name like an alias
//                            if (t instanceof PlainTable) {
//                                out.add(((PlainTable) t).getTableName().toUpperCase());
//                            }
                    }
                } else {
                    // prefix.length() > 0
                    if (t.getAlias() != null && t.getAlias().toUpperCase().startsWith(prefix.toUpperCase())) {
                        out.add(t.getAlias().toUpperCase());
                    } else {
                        // use table name like an alias
//                            if (t instanceof PlainTable) {
//                                out.add(((PlainTable) t).getTableName().toUpperCase());
//                            }
                    }
                }
            }
        });

        runner.process(elem.getNode(), true);

        if (out.size() == 0) {
            // 2. try to use column names
            ASTTreeProcessor runner2 = new ASTTreeProcessor();
            runner2.add(new TableEnumerator() {
                @NotNull
                public TokenSet getTokenSet() {
                    return TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION);//, PLSqlTypesAdopted.INSERT_SUBQUERY);
                }

                public void handleTable(GenericTable t) {
                    TableDescriptorLegacy tdesc = t.describe();
                    if (tdesc != null) {
                        // go thru column in all tables
                        for (String col : tdesc.getColumnNames()) {
                            if (prefix.length() == 0) {
                                out.add(col.toUpperCase());
                            } else {
                                // prefix.length() > 0
                                if (col.toUpperCase().startsWith(prefix.toUpperCase())) {
                                    out.add(col.toUpperCase());
                                }
                            }
                        }
                    }
                }
            });
            runner2.process(elem.getNode(), true);
        }

        return out.toArray(new String[out.size()]);
    }


}
