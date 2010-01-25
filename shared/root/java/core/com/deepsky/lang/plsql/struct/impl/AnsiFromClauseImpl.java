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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.AnsiFromClause;
import com.deepsky.lang.plsql.psi.GenericTable;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.Visitor;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

public class AnsiFromClauseImpl extends PsiElementDumb implements AnsiFromClause {

    private List<JoinPart> joinParts = new ArrayList <JoinPart>();
    private GenericTable lead;

    public AnsiFromClauseImpl(GenericTable lead){
        this.lead = lead;
    }

    public GenericTable getLeadTable() {
        return lead;
    }

    public JoinPart[] getJoinParts() {
        return joinParts.toArray(new JoinPart[0]);
    }

    @NotNull
    public GenericTable[] getTableList() {
        return new GenericTable[0];
    }

    public void setLead(GenericTable lead) {
        this.lead = lead;
    }

    public void addJoinPart(List<String> joinSpec, GenericTable table, Expression cond) {
        joinParts.add(new JoinPartImpl(joinSpec, table, cond));
    }

    class JoinPartImpl implements JoinPart {

        List<String> joinSpec;
        GenericTable table;
        Expression cond;
        public JoinPartImpl(List<String> joinSpec, GenericTable table, Expression cond){
            this.joinSpec = joinSpec;
            this.table = table;
            this.cond = cond;
        }

        public String[] joinSpec() {
            return joinSpec.toArray(new String[0]);
        }

        public GenericTable getTable() {
            return table;
        }

        public Expression getCondition() {
            return cond;
        }
    }

    public void process(Visitor visitor){
        visitor.accept(this);
    }

}
