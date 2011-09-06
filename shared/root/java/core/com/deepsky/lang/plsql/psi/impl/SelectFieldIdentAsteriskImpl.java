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

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.SelectFieldIdentAsterisk;
import com.deepsky.lang.plsql.psi.Visitor;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class SelectFieldIdentAsteriskImpl extends PlSqlCompositeNameBase implements SelectFieldIdentAsterisk {

    public SelectFieldIdentAsteriskImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getTableRef() {
        ASTNode alias = getNode().findChildByType(PLSqlTypesAdopted.NAME_FRAGMENT); //TABLE_REF);
        if (alias == null) {
            throw new SyntaxTreeCorruptedException();
        }
        return alias.getText();
    }

/*
    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        throw new NameNotResolvedException("");
    }

    @NotNull
    protected VariantsProcessor777 createVariantsProcessorFront() throws NameNotResolvedException {
        return new VariantsProcessor777Front(this);
    }
*/

    public void process(Visitor proc) {
        proc.accept(this);
    }


/*
    class VariantsProcessor777Front implements VariantsProcessor777 {

        SelectFieldIdentAsterisk elem;
        Set<String> out = new HashSet<String>();

        public VariantsProcessor777Front(SelectFieldIdentAsterisk elem) {
            this.elem = elem;
        }

        public String[] getVariants(final String prefix) {

            final Set<String> out = new HashSet<String>();
            ASTTreeProcessor runner = new ASTTreeProcessor();

            runner.add(new TableEnumerator() {
                @NotNull
                public TokenSet getTokenSet() {
                    return TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION);
                }

                public void handleTable(GenericTable t) {
                    // 1. try to use alias or table names as a look up source
                    if (prefix.length() == 0) {
                        if (t.getAlias() != null) {
                            out.add(t.getAlias().toUpperCase());
                        } else {
                            // use table name like an alias
                            if (t instanceof PlainTable) {
                                out.add(((PlainTable) t).getTableName().toUpperCase());
                            }
                        }
                    } else {
                        // prefix.length() > 0
                        if (t.getAlias() != null && t.getAlias().toUpperCase().startsWith(prefix.toUpperCase())) {
                            out.add(t.getAlias().toUpperCase());
                        } else {
                            // use table name like an alias
                            if (t instanceof PlainTable) {
                                out.add(((PlainTable) t).getTableName().toUpperCase());
                            }
                        }
                    }
                }
            });

            runner.process(elem.getNode(), true);
            return out.toArray(new String[out.size()]);
        }
    }
*/

}
