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
import com.deepsky.lang.plsql.psi.GenericTable;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.resolve.TableEnumerator;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class VariantsProcessor777_TableColumn implements VariantsProcessor777 {

    PsiElement elem;

    public VariantsProcessor777_TableColumn(PsiElement elem) {
        this.elem = elem;
    }

    public String[] getVariants(final String prefix) {
/*
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
*/

        return VariantsProcessorHelpers.getTableColumnVariants(elem, prefix);
    }
}
