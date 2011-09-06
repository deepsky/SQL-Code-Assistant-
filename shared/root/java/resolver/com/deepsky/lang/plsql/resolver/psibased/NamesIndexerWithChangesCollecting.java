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

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.DbObject;

import java.util.HashSet;
import java.util.Set;

public class NamesIndexerWithChangesCollecting extends NamesIndexer {
    private final Set<String> typesBeingUpdated = new HashSet<String>();

    public String[] getUpdatedTypes(){
        return typesBeingUpdated.toArray(new String[typesBeingUpdated.size()]);
    }

    public void visitPackageBody(PackageBody node) {
        super.visitPackageBody(node);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitPackageSpec(PackageSpec node) {
        super.visitPackageSpec(node);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitFunction(Function node) {
        super.visitFunction(node);
        //new ContextPathUtil.CtxPathParser(node.getCtxPath1()).
        // todo -- need to choose correct type
        typesBeingUpdated.add(DbObject.FUNCTION);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitFunctionSpec(FunctionSpec node) {
        super.visitFunctionSpec(node);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitProcedure(Procedure node) {
        super.visitProcedure(node);
        //new ContextPathUtil.CtxPathParser(node.getCtxPath1()).
        // todo -- need to choose correct type
        typesBeingUpdated.add(DbObject.PROCEDURE);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitProcedureSpec(ProcedureSpec node) {
        super.visitProcedureSpec(node);
        typesBeingUpdated.add(DbObject.PACKAGE);
    }

    public void visitCreateSynonym(CreateSynonym node) {
        super.visitCreateSynonym(node);
        typesBeingUpdated.add(DbObject.SYNONYM);
    }

    public void visitCreateSequence(CreateSequence node) {
        super.visitCreateSequence(node);
        typesBeingUpdated.add(DbObject.SEQUENCE);
    }

    public void visitTableDefinition(TableDefinition node) {
        super.visitTableDefinition(node);
        typesBeingUpdated.add(DbObject.TABLE);
    }

    public void visitCreateTriggerGeneric(CreateTriggerGeneric trigger) {
        super.visitCreateTriggerGeneric(trigger);
        typesBeingUpdated.add(DbObject.TRIGGER);
    }

    public void visitCreateTriggerDML(CreateTriggerDML trigger) {
        super.visitCreateTriggerDML(trigger);
        typesBeingUpdated.add(DbObject.TRIGGER);
    }

    public void visitCreateView(CreateView view) {
        super.visitCreateView(view);
        typesBeingUpdated.add(DbObject.VIEW);
    }

    public void visitObjectTypeDecl(ObjectTypeDecl decl) {
        super.visitObjectTypeDecl(decl);
        typesBeingUpdated.add(DbObject.TYPE);
    }

    public void visitVarrayCollectionDecl(VarrayCollectionDecl node) {
        super.visitVarrayCollectionDecl(node);
        typesBeingUpdated.add(DbObject.TYPE);
    }

    public void visitTableCollectionDecl(TableCollectionDecl node) {
        super.visitTableCollectionDecl(node);
        typesBeingUpdated.add(DbObject.TYPE);
    }

    public void visitRecordTypeDecl(RecordTypeDecl node) {
        super.visitRecordTypeDecl(node);
        typesBeingUpdated.add(DbObject.TYPE);
    }

}
