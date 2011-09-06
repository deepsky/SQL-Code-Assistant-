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

import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColumnSpecImpl extends PlSqlCompositeNameBase implements ColumnSpec {
    public ColumnSpecImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnSpec(this);
        } else {
            super.accept(visitor);
        }
    }

/*
    @NotNull
    protected VariantsProcessor777 createVariantsProcessorFront() throws NameNotResolvedException {
        //  PLSQL:COLUMN_SPEC [parent] PLSQL:COLUMN_SPEC_LIST
        return new VariantsProcessor777Front(this);
    }
*/

    public String getColumnNameRef() {
        NameFragmentRef[] refs = getNamePieces();
        return refs[refs.length - 1].getText();
    }

    public Type getColumnType() {

        NameFragmentRef[] fragments = getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];

        // todo -- resolve stuff refactoring
        throw new NotSupportedException();
/*
        try {
            ResolveContext777 ctx = last.resolveContext();
            return ctx.getType();
        } catch (SyntaxTreeCorruptedException e1) {
            throw new ValidationException("Name '" + getText() + "' not resolved", getNode());
        } catch(ProcessCanceledException e){
            String text = getText();
            throw e;
        } catch (NotSupportedException e){
            throw new ValidationException("Name '" + getText() + "' not resolved", getNode());
        } catch (NameNotResolvedException e1) {
            throw new ValidationException("Name '" + getText() + "' not resolved", getNode());
        }
*/
    }

    @Nullable
    public String getQuickNavigateInfo() {
//        TableDefinition t = findParent(TableDefinition.class);
//        if (t != null) {
//            String tableName = t.getTableName();
//            TableDescriptor desc = t.describe();
//            if(desc != null){
//                desc.
//            }
// todo - throw exception
//            return "[Table] " + "dummy" //tableName.toLowerCase()
//                    + "\n [Column] " + getColumnNameRef().toLowerCase() + " "
//                    + getColumnType().toString().toUpperCase();
//        }
        return null;
    }


/*
    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));

        if (nodes == null || nodes.length == 0) {
            throw new SyntaxTreeCorruptedException();
        }

        List<ResolveContext777> collisions = new ArrayList<ResolveContext777>();

        if (nodes.length == 1) {
            // column only
            GenericTable[] tabs = ResolveHelper.findTablesInScope(nodes[0]);
            for (GenericTable tab1 : tabs) {
                TableDescriptorLegacy tdesc = tab1.describe();
                if (tdesc != null && tdesc.getColumnType(nodes[0].getText()) != null) {
                    collisions.add(new TableColumnContext(tab1, tdesc, nodes[0].getText()));
                }
            }

        } else {
            // table DOT column
            GenericTable t = ResolveHelper.resolve_TableRef((PlSqlElement) nodes[0].getPsi());
            if (t != null) {
                collisions.add(new PsiTableContext(t));
            }
        }

        if (collisions.size() == 1) {
            return collisions.get(0);
        } else if (collisions.size() == 0) {
            // search the schema name
            throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(),
                    "Name not resolved: " + nodes[0].getText());
        } else {
            // name collision?
            throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(),
                    "Name duplication found: " + nodes[0].getText());
        }
    }


    class VariantsProcessor777Front implements VariantsProcessor777 {

        ColumnSpec spec;
        Set<String> out = new HashSet<String>();

        public VariantsProcessor777Front(ColumnSpec spec) {
            this.spec = spec;
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

                    // 2. try to use column names
                    if (out.size() == 0) {
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
                }
            });

            runner.add(new TableEnumerator() {
                @NotNull
                public TokenSet getTokenSet() {
                    return PlSqlElementTypes.DML_STATEMENTS;
                }

                public void handleTable(GenericTable t) {
                    // 1. use column names only
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

            runner.process(spec.getNode(), true);
            return out.toArray(new String[out.size()]);
        }
    }
*/
}
