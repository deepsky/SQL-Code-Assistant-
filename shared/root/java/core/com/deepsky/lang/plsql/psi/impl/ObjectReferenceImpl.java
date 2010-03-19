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

import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.ObjectReference;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.psi.var_proc.VariantsProcessor777_GenericRef;
import com.deepsky.lang.plsql.psi.var_proc.VariantsProcessor777_PlSqlVar;
import com.deepsky.lang.plsql.psi.var_proc.VariantsProcessor777_TableColumn;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;


public class ObjectReferenceImpl extends PlSqlCompositeNameBase implements ObjectReference {

    boolean isPlSqlVarRef;
    public ObjectReferenceImpl(ASTNode astNode, boolean isPlSqlVarRef) {
        super(astNode);
        this.isPlSqlVarRef = isPlSqlVarRef;
    }

    @NotNull
    protected VariantsProcessor777 createVariantsProcessorFront() throws NameNotResolvedException {
        IElementType type = getParent().getNode().getElementType();

        //
        ASTTreeProcessor runner = new ASTTreeProcessor();

        final TokenSet tokenSet = TokenSet.create(
                PlSqlElementTypes.PLSQL_BLOCK,

                PlSqlElementTypes.SELECT_EXPRESSION,
                PlSqlElementTypes.SELECT_EXPRESSION_UNION,
                PlSqlElementTypes.MERGE_COMMAND,
                PlSqlElementTypes.INSERT_COMMAND,
                PlSqlElementTypes.UPDATE_COMMAND,
                PlSqlElementTypes.DELETE_COMMAND
        );

        final Set<PlSqlElementType> context = new HashSet<PlSqlElementType>();
        runner.add(new ContextEnumerator(tokenSet){
            public void handleASTNode(@NotNull ASTNode node) {
                context.add((PlSqlElementType) node.getElementType());
            }
        });

        runner.process(this.getNode());

        if (type == PLSqlTypesAdopted.EXPR_COLUMN
            || type == PLSqlTypesAdopted.LOGICAL_EXPR
            || type == PLSqlTypesAdopted.WHERE_CONDITION
            || type == PLSqlTypesAdopted.CALL_ARGUMENT
            || type == PLSqlTypesAdopted.ARITHMETIC_EXPR
            || type == PLSqlTypesAdopted.NUMERIC_LOOP_SPEC
            || type == PLSqlTypesAdopted.ASSIGNMENT_STATEMENT
            || type == PLSqlTypesAdopted.RELATION_CONDITION){

            if(contains(context, PlSqlElementTypes.PLSQL_BLOCK)){
                // inside of a Procedure OR Function OR Trigger
                if(contains(context, PlSqlElementTypes.SELECT_EXPRESSION,
                        PlSqlElementTypes.MERGE_COMMAND,
                        PlSqlElementTypes.INSERT_COMMAND,
                        PlSqlElementTypes.UPDATE_COMMAND,
                        PlSqlElementTypes.DELETE_COMMAND
                        )){
                    // inside of SQL statement which is wrapped with PL/Sql code
                    return new VariantsProcessor777_GenericRef(this);
                } else {
                    // PL/SQL variable
                    return new VariantsProcessor777_PlSqlVar(this);
                }
            } else {
                // outside of PL/SQL code
                return new VariantsProcessor777_TableColumn(this);
            }
        } else if( type == PLSqlTypesAdopted.INTO_CLAUSE){
            if(contains(context, PlSqlElementTypes.PLSQL_BLOCK)){
                // PL/SQL variable inside of a Procedure OR Function OR Trigger
                return new VariantsProcessor777_PlSqlVar(this);
            }
        } else if( type == PLSqlTypesAdopted.RETURN_STATEMENT){
            // PL/SQL variable
            return new VariantsProcessor777_PlSqlVar(this);
        } else if( type == PLSqlTypesAdopted.SORTED_DEF){
            return new VariantsProcessor777_TableColumn(this);
        } else if( type == PLSqlTypesAdopted.GROUP_CLAUSE) {
            return new VariantsProcessor777_TableColumn(this);
        } else if( type == PLSqlTypesAdopted.COUNT_FUNC) {
            return new VariantsProcessor777_TableColumn(this);
        } else if( type == PLSqlTypesAdopted.IMMEDIATE_COMMAND) {
            if(this.getNode().getElementType() == PLSqlTypesAdopted.PLSQL_VAR_REF){
                // PL/SQL variable
                return new VariantsProcessor777_PlSqlVar(this);
            }
        }

        throw new NameNotResolvedException("Name not resolved");
    }

    private boolean contains(Set<PlSqlElementType> nodes, PlSqlElementType ... probes ){
        for(PlSqlElementType type: probes){
            if(nodes.contains(type)){
                return true;
            }
        }
        return false;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitObjectReference(this);
        } else {
            super.accept(visitor);
        }
    }

    @NotNull
    public Type getExpressionType() {

        NameFragmentRef[] fragments = getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];

        try {
            ResolveContext777 ctx = last.resolveContext();
            return ctx.getType();
        } catch (SyntaxTreeCorruptedException e1) {
            throw new ValidationException("Name '" + getText() + "' not resolved", getNode());
        } catch (ProcessCanceledException e) {
            String text = getText();
            throw e;
        } catch (NotSupportedException e) {
            throw new ValidationException("Name '" + getText() + "' not resolved", getNode());
        } catch (NameNotResolvedException e1) {
            throw new ValidationException(e1.getMessage(), getNode());
        }
    }


    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        return ResolveHelper4.resolveContext2((NameFragmentRef) nodes[0].getPsi(), nodes.length);
    }

    public boolean isPlSqlVarRef() {
        return isPlSqlVarRef;
    }
}
