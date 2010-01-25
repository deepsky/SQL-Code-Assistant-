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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.VarrayCollectionDescriptor;
import com.deepsky.lang.plsql.struct.types.VarrayType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class VarrayCollectionContext  implements ResolveContext777 {

    private final static String COUNT = "count";

    VarrayCollectionDescriptor rdesc;
    Project project;

    public VarrayCollectionContext(Project project, VarrayCollectionDescriptor rdesc) {
        this.project = project;
        this.rdesc = rdesc;
    }

    public String[] getVariants(String prefix) {
        return new String[0];
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return null;
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(project, rdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        String text = elem.getText();

        if(text.equalsIgnoreCase(COUNT)){
            return new VarrayCollectionMethodContext(text);
        }

        throw new NameNotResolvedException("Type definition does not have " + elem.getText() + " item");
    }

    public Type getType() throws NameNotResolvedException {
        return new VarrayType(rdesc.getName());
    }


    class VarrayCollectionMethodContext implements ResolveContext777 {

        String method;

        public VarrayCollectionMethodContext(String method) {
            this.method = method;
        }

        @NotNull
        public VariantsProcessor777 create(int narrow_type /* constants from ObjectCache */) {
            return new VariantsProcessor777() {
                public String[] getVariants(String prefix) {
                    if (prefix.length() == 0) {
                        return new String[]{COUNT};
                    } else if (COUNT.startsWith(prefix.toLowerCase())) {
                        return new String[]{COUNT};
                    }
                    return new String[0];
                }
            };
        }

        public PsiElement getDeclaration() {
            // todo --
            return null;
        }

        public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
            // todo --
            return null;
        }

        public Type getType() throws NameNotResolvedException {
            if (COUNT.equalsIgnoreCase(method)) {
                return TypeFactory.createTypeById(Type.INTEGER);
            } else {
                // todo - more Collection methods must be supported
                throw new NameNotResolvedException("Method " + method + " not supported at the moment");
            }
        }
    }

}
