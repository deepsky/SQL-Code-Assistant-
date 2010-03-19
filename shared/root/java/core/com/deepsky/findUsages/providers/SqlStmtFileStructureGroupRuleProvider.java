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

package com.deepsky.findUsages.providers;

import com.deepsky.findUsages.rules.DeleteUsageGroup;
import com.deepsky.findUsages.rules.InsertUsageGroup;
import com.deepsky.findUsages.rules.SelectUsageGroup;
import com.deepsky.findUsages.rules.UpdateUsageGroup;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.DeleteStatement;
import com.deepsky.lang.plsql.psi.InsertStatement;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.UpdateStatement;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usages.Usage;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.impl.FileStructureGroupRuleProvider;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.rules.UsageGroupingRule;
import org.jetbrains.annotations.NotNull;

public class SqlStmtFileStructureGroupRuleProvider implements FileStructureGroupRuleProvider {

    private static final Logger LOG = Logger.getInstance("#SqlStmtFileStructureGroupRuleProvider");

    public UsageGroupingRule getUsageGroupingRule(Project project) {
        return new MethodGroupingRule();
    }


    static public UsageGroup getUsageGroupingRule(PsiElement element) {
        final PsiElement[] etype = {null};

        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        treeProcessor.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(
                        PlSqlElementTypes.SELECT_EXPRESSION,
                        PlSqlElementTypes.INSERT_COMMAND,
                        PlSqlElementTypes.UPDATE_COMMAND,
// todo -- merge statement                        PlSqlElementTypes.....
                        PlSqlElementTypes.DELETE_COMMAND
                );
            }

            public boolean handleNode(@NotNull ASTNode node) {
                etype[0] = node.getPsi(); //getElementType();
                return true;
            }
        });

        treeProcessor.process(element.getNode(), true /* break on first hit*/);

        if (etype[0] == null || etype[0].getNode() == null) {
            return null;
        }

        if (etype[0].getNode().getElementType() == PlSqlElementTypes.SELECT_EXPRESSION) {
            // todo -- handle possible cursor declaration
            return new SelectUsageGroup((SelectStatement) etype[0]);
        } else if (etype[0].getNode().getElementType() == PlSqlElementTypes.INSERT_COMMAND) {
            return new InsertUsageGroup((InsertStatement) etype[0]);
        } else if (etype[0].getNode().getElementType() == PlSqlElementTypes.DELETE_COMMAND) {
            return new DeleteUsageGroup((DeleteStatement) etype[0]);
        } else if (etype[0].getNode().getElementType() == PlSqlElementTypes.UPDATE_COMMAND) {
            return new UpdateUsageGroup((UpdateStatement) etype[0]);
        }

        return null;
    }


    static class MethodGroupingRule implements UsageGroupingRule { //UsageGroupingRule {
        //private  final Logger LOG = Logger.getInstance("#com.intellij.usages.impl.rules.MethodGroupingRule");

        public UsageGroup groupUsage(Usage usage) {
            if (usage instanceof PsiElementUsage) {
                PsiElement psiElement = ((PsiElementUsage) usage).getElement();
                UsageGroup usageGroup = getUsageGroupingRule(psiElement);

                return usageGroup;
//                if (psiElement.getContainingFile() instanceof PlSqlFile) {
//                    PsiElement containingMethod = psiElement;
//                    containingMethod = PsiTreeUtil.getParentOfType(containingMethod, SelectStatement.class, true);
//
//                    if (containingMethod != null) {
//                        return new SelectUsageGroup((SelectStatement) containingMethod);
//                    }
//                }
            }
            return null;
        }

        public int getRank() {
            return 0;
        }
    }

}
