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

import com.deepsky.lang.plsql.psi.RecordTypeDecl;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.RecordTypeItem;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.struct.Type;

import java.util.List;
import java.util.ArrayList;

public class RecordTypeDeclImpl extends PsiElementDumb implements RecordTypeDecl {
    String typeName;
    List<RecordTypeItem> items = new ArrayList<RecordTypeItem>();

    public String getDeclName() {
        return typeName;
    }

    public String getPackageName() {
        // todo --
        return null;
    }

    public RecordTypeItem[] getItems() {
        return items.toArray(new RecordTypeItem[0]);
    }

//    public TypeItem[] getTypeItems() {
//        return items.toArray(new TypeItem[0]);
//    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void addTypeItem(String name, Type type, Expression defaultExpr){
        items.add(new RecordTypeItemImpl(name, type, defaultExpr));
    }

    public void addTypeItem(RecordTypeItem rti){
        items.add(rti);
    }

    public void process(Visitor proc) {
        proc.accept(this);
    }

//    class TypeItemImpl implements TypeItem {
//
//        String name;
//        Type type;
//        Expression defaultExpr;
//
//        public TypeItemImpl(String name, Type type, Expression defaultExpr){
//            this.name = name;
//            this.type = type;
//            this.defaultExpr = defaultExpr;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Type getType() {
//            return type;
//        }
//
//        public Expression getDefault(){
//            return defaultExpr;
//        }
//    }

}
