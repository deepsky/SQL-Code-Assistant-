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

package com.deepsky.lang.plsql.psi.resolve.collection;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.impl.RecordTypeContext;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class TableCollectionTypeContext implements ResolveContext777 {
    private final static String FIRST = "first";
    private final static String LAST = "last";
    private final static String COUNT = "count";
    private final static String EXTEND = "extend";
    private final static String DELETE = "delete";

    TableCollectionDescriptor tdesc;
    Project project;

    public TableCollectionTypeContext(Project project, TableCollectionDescriptor tdesc) {
        this.project = project;
        this.tdesc = tdesc;
    }

//    public TableCollectionTypeContext(TableCollectionDescriptor tdesc) {
//        this.tdesc = tdesc;
//    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return new TableCollectionVariantsProcessor();
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(getProject(), tdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        String text = elem.getText();
        // check for Collection methods
        if (FIRST.equalsIgnoreCase(text)) {
            return new TableCollectionMethodContext(text);
        } else if (COUNT.equalsIgnoreCase(text)) {
            return new TableCollectionMethodContext(text);
        } else if (LAST.equalsIgnoreCase(text)) {
            return new TableCollectionMethodContext(text);
        } else if (DELETE.equalsIgnoreCase(text)) {
            return new TableCollectionMethodContext(text);
        } else if (EXTEND.equalsIgnoreCase(text)) {
            return new TableCollectionMethodContext(text);
        } else if (tdesc.getBaseType() instanceof UserDefinedType) {
            UserDefinedTypeDescriptor basedTypeDesc = ResolveHelper.resolve_Type(project, (UserDefinedType) tdesc.getBaseType());
            if (basedTypeDesc instanceof RecordTypeDescriptor) {
                return new RecordTypeContext(getProject(), ((RecordTypeDescriptor) basedTypeDesc)).resolve(elem);
            }
        }

        throw new NameNotResolvedException("Cannot resolve name: " + elem.getText());
    }

    private Project getProject(){
//        if(project != null){
            return project;
//        } else {
//            project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//            return project;
//        }
    }
    
    public Type getType() throws NameNotResolvedException {
        String packageName = tdesc.getPackage() == null ? null : tdesc.getPackage().getName();
        return new UserDefinedType(packageName, tdesc.getName());
    }


    class TableCollectionMethodContext implements ResolveContext777 {

        String method;

        public TableCollectionMethodContext(String method) {
            this.method = method;
        }

        @NotNull
        public VariantsProcessor777 create(int narrow_type /* constants from ObjectCache */) {
            return new VariantsProcessor777() {
                public String[] getVariants(String prefix) {
                    if (prefix.length() == 0) {
                        return new String[]{FIRST, LAST, COUNT, EXTEND};
                    } else if (FIRST.startsWith(prefix.toLowerCase())) {
                        return new String[]{FIRST};
                    } else if (LAST.startsWith(prefix.toLowerCase())) {
                        return new String[]{LAST};
                    } else if (COUNT.startsWith(prefix.toLowerCase())) {
                        return new String[]{COUNT};
                    } else if (DELETE.startsWith(prefix.toLowerCase())) {
                        return new String[]{DELETE};
                    } else if (EXTEND.startsWith(prefix.toLowerCase())) {
                        return new String[]{EXTEND};
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
            if (FIRST.equalsIgnoreCase(method)) {
                return TypeFactory.createTypeById(Type.INTEGER);
            } else if (COUNT.equalsIgnoreCase(method)) {
                return TypeFactory.createTypeById(Type.INTEGER);
            } else if (DELETE.equalsIgnoreCase(method)) {
                throw new NameNotResolvedException("Method " + method + " have no type");
            } else if (LAST.equalsIgnoreCase(method)) {
                return TypeFactory.createTypeById(Type.INTEGER);
            } else {
                // todo - more Collection methods must be supported
                throw new NameNotResolvedException("Method " + method + " not supported at the moment");
            }
        }
    }


    class TableCollectionVariantsProcessor implements VariantsProcessor777 {

        public String[] getVariants(String prefix) {
            if (prefix.length() == 0) {
                return new String[]{FIRST, LAST, COUNT, EXTEND};
            } else if (FIRST.startsWith(prefix.toLowerCase())) {
                return new String[]{FIRST};
            } else if (LAST.startsWith(prefix.toLowerCase())) {
                return new String[]{LAST};
            } else if (COUNT.startsWith(prefix.toLowerCase())) {
                return new String[]{COUNT};
            } else if (DELETE.startsWith(prefix.toLowerCase())) {
                return new String[]{DELETE};
            } else if (EXTEND.startsWith(prefix.toLowerCase())) {
                return new String[]{EXTEND};
            } else {
                if (tdesc.getBaseType() instanceof UserDefinedType) {
                    UserDefinedTypeDescriptor basedTypeDesc = ResolveHelper.resolve_Type(project, (UserDefinedType) tdesc.getBaseType());
                    if (basedTypeDesc instanceof RecordTypeDescriptor) {
                        // search variants amongs record items
                        return new RecordTypeContext(project, ((RecordTypeDescriptor) basedTypeDesc)).create(0).getVariants(prefix);
                    }
                }
                return new String[0];
            }
        }
   }

}
