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

import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.plsql.psi.FunctionCall;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class FunctionCallImpl extends CallableImpl implements FunctionCall {

    public FunctionCallImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getFragmentText() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitFunctionCall(this);
        } else {
            super.accept(visitor);
        }
    }


    @NotNull
    public Type getExpressionType() {
        return ((ResolveProvider) getContainingFile()).getResolver().resolveType(this);
    }


    public boolean isAggregate() {
        final String funcName = getCompositeName().getText().toUpperCase();
        return aggregateFunctionSet.contains(funcName);
    }


    /*
The aggregate functions are:

AVG
COLLECT
CORR
CORR_S
CORR_K
COUNT
COVAR_POP
COVAR_SAMP
CUME_DIST
DENSE_RANK
FIRST
GROUP_ID
GROUPING
GROUPING_ID
LAST
MAX
MEDIAN
MIN
PERCENTILE_CONT
PERCENTILE_DISC
PERCENT_RANK
RANK
REGR_ (Linear Regression) Functions
STATS_BINOMIAL_TEST
STATS_CROSSTAB
STATS_F_TEST
STATS_KS_TEST
STATS_MODE
STATS_MW_TEST
STATS_ONE_WAY_ANOVA
STATS_T_TEST_*
STATS_WSR_TEST
STDDEV
STDDEV_POP
STDDEV_SAMP
SUM
VAR_POP
VAR_SAMP
VARIANCE
     */
    private static final Set<String> aggregateFunctionSet = new HashSet<String>();
    static {
        aggregateFunctionSet.add("AVG");
        aggregateFunctionSet.add("COLLECT");
        aggregateFunctionSet.add("CORR");
        aggregateFunctionSet.add("CORR_S");
        aggregateFunctionSet.add("CORR_K");
        aggregateFunctionSet.add("COUNT");
        aggregateFunctionSet.add("COVAR_POP");
        aggregateFunctionSet.add("COVAR_SAMP");
        aggregateFunctionSet.add("CUME_DIST");
        aggregateFunctionSet.add("DENSE_RANK");
        aggregateFunctionSet.add("FIRST");
        aggregateFunctionSet.add("GROUP_ID");
        aggregateFunctionSet.add("GROUPING");
        aggregateFunctionSet.add("GROUPING_ID");
        aggregateFunctionSet.add("LAST");
        aggregateFunctionSet.add("MAX");
        aggregateFunctionSet.add("MEDIAN");
        aggregateFunctionSet.add("MIN");
        aggregateFunctionSet.add("PERCENTILE_CONT");
        aggregateFunctionSet.add("PERCENTILE_DISC");
        aggregateFunctionSet.add("PERCENT_RANK");
        aggregateFunctionSet.add("RANK");
        aggregateFunctionSet.add("REGR_SLOPE");
        aggregateFunctionSet.add("REGR_INTERCEPT");
        aggregateFunctionSet.add("REGR_COUNT");
        aggregateFunctionSet.add("REGR_R2");
        aggregateFunctionSet.add("REGR_AVGX");
        aggregateFunctionSet.add("REGR_AVGY");
        aggregateFunctionSet.add("REGR_SXX");
        aggregateFunctionSet.add("REGR_SYY");
        aggregateFunctionSet.add("REGR_SXY");
        aggregateFunctionSet.add("STATS_BINOMIAL_TEST");
        aggregateFunctionSet.add("STATS_CROSSTAB");
        aggregateFunctionSet.add("STATS_F_TEST");
        aggregateFunctionSet.add("STATS_KS_TEST");
        aggregateFunctionSet.add("STATS_MODE");
        aggregateFunctionSet.add("STATS_MW_TEST");
        aggregateFunctionSet.add("STATS_ONE_WAY_ANOVA");
        aggregateFunctionSet.add("STATS_T_TEST_ONE");
        aggregateFunctionSet.add("STATS_T_TEST_PAIRED");
        aggregateFunctionSet.add("STATS_T_TEST_INDEP");
        aggregateFunctionSet.add("STATS_T_TEST_INDEPU");
        aggregateFunctionSet.add("STATS_WSR_TEST");
        aggregateFunctionSet.add("STDDEV");
        aggregateFunctionSet.add("STDDEV_POP");
        aggregateFunctionSet.add("STDDEV_SAMP");
        aggregateFunctionSet.add("SUM");
        aggregateFunctionSet.add("VAR_POP");
        aggregateFunctionSet.add("VAR_SAMP");
        aggregateFunctionSet.add("VARIANCE");

    }

}
