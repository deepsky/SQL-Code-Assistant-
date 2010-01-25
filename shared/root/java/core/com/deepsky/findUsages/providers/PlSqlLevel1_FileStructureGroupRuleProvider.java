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

package com.deepsky.findUsages.providers;

import com.deepsky.findUsages.rules.FunctionBodyUsageGroup;
import com.deepsky.findUsages.rules.ProcedureBodyUsageGroup;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.Procedure;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usages.Usage;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.impl.FileStructureGroupRuleProvider;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.rules.UsageGroupingRule;
import org.jetbrains.annotations.NotNull;

public class PlSqlLevel1_FileStructureGroupRuleProvider implements FileStructureGroupRuleProvider {
    public UsageGroupingRule getUsageGroupingRule(Project project) {
        return new MethodGroupingRule2();
    }

    static class MethodGroupingRule2 implements UsageGroupingRule {

        public UsageGroup groupUsage(Usage usage) {
            if (usage instanceof PsiElementUsage) {
                PsiElement psiElement = ((PsiElementUsage) usage).getElement();
                UsageGroup usageGroup = getUsageGroupingRule(psiElement);

                return usageGroup;
            }
            return null;
        }
    }

    static public UsageGroup getUsageGroupingRule(PsiElement element) {
        final PsiElement[] etype = {null};
        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        treeProcessor.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(
                        PlSqlElementTypes.FUNCTION_BODY,
                        PlSqlElementTypes.PROCEDURE_BODY
                );
            }

            public boolean handleNode(@NotNull ASTNode node) {
                etype[0] = node.getPsi();
                return true;
            }
        });

        treeProcessor.process(element.getNode(), true /* break on first hit*/);

        if (etype[0] == null || etype[0].getNode() == null) {
            return null;
        }

        if (etype[0].getNode().getElementType() == PlSqlElementTypes.FUNCTION_BODY) {
            return new FunctionBodyUsageGroup((Function) etype[0]);
        } else if (etype[0].getNode().getElementType() == PlSqlElementTypes.PROCEDURE_BODY) {
            return new ProcedureBodyUsageGroup((Procedure) etype[0]);
        }

        return null;
    }

}
