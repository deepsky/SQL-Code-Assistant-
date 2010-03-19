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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.ColumnDescriptor;
import com.deepsky.lang.plsql.NotSupportedException;
//import com.deepsky.lang.common.PlSqlFile;
//import com.deepsky.lang.common.ShortNamesCache;
import com.deepsky.database.SqlScriptManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

public class PlainTableColumnContext implements ResolveContext777 {

        TableDescriptor tdesc;
        Project project;
        String column;
        Type type;

        public PlainTableColumnContext(Project project, TableDescriptor tdesc, String column) throws NameNotResolvedException {
            this.project = project;
            this.tdesc = tdesc;
            this.column = column;
            type = tdesc.getColumnType(column);
            if (type == null) {
                throw new NameNotResolvedException("Column " + column + " not found in the table definition");
            }
        }

        public String[] getVariants(String prefix) {
            List<String> out = new ArrayList<String>();
            for (String name : tdesc.getColumnNames()) {
                if (name.toUpperCase().startsWith(prefix.toUpperCase())) {
                    out.add(name);
                }
            }

            return out.toArray(new String[out.size()]);
        }

        @NotNull
        public VariantsProcessor777 create(int narrwo_type) {
            // todo
            return null;
        }

        public PsiElement getDeclaration() {
            ColumnDescriptor cdesc = tdesc.getColumnDescriptor(column);
//            PlSqlFile file = ShortNamesCache.findDbObjectDefinition(project, tdesc);
//            if (file != null) {
//                return file.findDeclaration(cdesc);
//            }
            return SqlScriptManager.mapToPsiTree(project, cdesc);//null;
        }

        public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException, NotSupportedException {
            throw new NameNotResolvedException("Operation not applicable");
        }

        public Type getType() {
            return type;
        }
}
