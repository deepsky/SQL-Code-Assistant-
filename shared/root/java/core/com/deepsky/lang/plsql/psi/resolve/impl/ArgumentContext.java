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

import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.UserDefinedTypeDescriptor;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ArgumentContext implements ResolveContext777 {
        Argument arg;
//        Type type;

        public ArgumentContext(@NotNull Argument arg) {
            this.arg = arg;
//            this.type = arg.getType();
        }

        @NotNull
        public VariantsProcessor777 create(int narrow_type){
            Type type = arg.getType();
//            if (type instanceof UserDefinedType) {
                UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) type);
//                if (tdesc == null) {
//                    throw new NameNotResolvedException("Type definition not found: " + type.typeName());
//                }
                return new UserDefinedTypeVariantProcessor(tdesc);
//            } else {
//                throw new NameNotResolvedException("Type not supported");
//            }
        }

        public PsiElement getDeclaration() {
            // todo -
            return arg;
        }

        public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
            String text = elem.getText();
            Type type = arg.getType();
            if (type instanceof UserDefinedType) {
                String packageScope = ResolveHelper.getSurroundPackageName(elem);
                UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) type, packageScope);
                if (tdesc == null) {
                    throw new NameNotResolvedException("Type definition not found: " + type.typeName());
                }

                return UserDefinedTypeHelper.createResolveContext(tdesc).resolve(elem);
//                if (tdesc instanceof RecordTypeDescriptor) {
//                    return new RecordTypeContext(elem.getProject(), (RecordTypeDescriptor) tdesc).resolve(elem);
//                } else if (tdesc instanceof ObjectTypeDescriptor) {
//                    ObjectTypeDescriptor otype = (ObjectTypeDescriptor) tdesc;
//                    RecordTypeItemDescriptor item = otype.findItem(text);
//                    if (item == null) {
//                        throw new NameNotResolvedException("Type definition does not have " + text + " item");
//                    }
//
//                    return new RecordTypeItemContext(arg.getProject(), otype, item);
//                } else if (tdesc instanceof TableCollectionDescriptor) {
//                    // ...
//                    return new TableCollectionTypeContext(elem.getProject(), (TableCollectionDescriptor) tdesc).resolve(elem);
//                } else {
//                }
//
//                throw new NameNotResolvedException("Type not supported");
            } else if (type instanceof RowtypeType) {
                RowtypeType rowtype = (RowtypeType) type;
                TableDescriptor tdesc = ResolveHelper.describeTable(rowtype.getTableName());
                if (tdesc != null) {
                    return new PlainTableColumnContext(arg.getProject(), tdesc, text);
                }
                throw new NameNotResolvedException("Table " + rowtype.getTableName() + " does not exist");
            } else {
                // type is primitive
                throw new NameNotResolvedException("Operation not applicable");
            }
        }

        public Type getType() throws NameNotResolvedException {
            Type type = arg.getType();
//            if (type.isUserDefined()) {
//                UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type(type.typeName());
//                if (tdesc == null) {
//                    throw new NameNotResolvedException("Type definition not found: " + type.typeName());
//                }
//            }
            return type;
        }
}
