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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ColumnDefinitionImpl extends PsiElementDumb implements ColumnDefinition {

    String name;
    Type t;
    boolean pk = false;
    boolean notNull = false;
    ForeignKeySpec fk;
    String refTable;
    String refColumn;
    TableDefinition tdef;

    public ColumnDefinitionImpl(String name, Type t, @NotNull TableDefinition tdef) {
        this.name = name;
        this.t = t;
        this.tdef = tdef;
    }

    public String getColumnName() {
        return name;
    }

    public PsiElement getPsiColumnName() {
        // todo -- implement me
        return null;
    }

    public Type getType() {
        return t;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isPrimaryKey() {
        if (pk) {
            return true;
        } else {
            // check the table def
            GenericConstraint[] constraints = tdef.getConstraints();
            for (GenericConstraint con : constraints) {
                if (con instanceof PrimaryKeyConstraint) {
                    String[] names = ((PrimaryKeyConstraint) con).getPrimaryKeys();
                    for (String name : names) {
                        if (name.equalsIgnoreCase(this.name)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    public ForeignKeySpec getForeignKeySpec() {
        if (fk != null) {
            return fk;
        } else {
            // check the table def
            GenericConstraint[] constraints = tdef.getConstraints();
            for (GenericConstraint con : constraints) {
                if (con instanceof ForeignKeyConstraint) {
                    String[] names = ((ForeignKeyConstraint) con).getOwnColumns();
                    for (String name : names) {
                        if (name.equalsIgnoreCase(this.name)) {
                            this.refTable = ((ForeignKeyConstraint) con).getReferencedTable();
                            this.refColumn = "null"; // todo -- find corresponded column

                            fk = new ForeignKeySpec() {
                                public String getReferencedTable() {
                                    return refTable;
                                }

                                public String getReferencedColumn() {
                                    return refColumn;
                                }
                            };

                            return fk;
                        }
                    }
                }
            }
        }

        return null;
    }

    public TableDefinition getTableDefinition() {
        // todo -- implement me
        return null;
    }

    public void setForeignKeySpec(String tab, String column) {
        this.refTable = tab;
        this.refColumn = column;

        fk = new ForeignKeySpec() {
            public String getReferencedTable() {
                return refTable;
            }

            public String getReferencedColumn() {
                return refColumn;
            }
        };
    }

    public void setPrimaryKey(boolean pk) {
        this.pk = pk;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
