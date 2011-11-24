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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.CreateSynonym;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CreateSynonymImpl extends PlSqlElementBase implements CreateSynonym {
    public CreateSynonymImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getSynonymName() {
        ASTNode synonym = getNode().findChildByType(PLSqlTypesAdopted.SYNONYM_NAME);
        if (synonym != null) {
            return synonym.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    private static TokenSet toks = TokenSet.create(
            PLSqlTypesAdopted.SCHEMA_NAME,
            PLSqlTypesAdopted.SYNONYM_NAME,
            PLSqlTypesAdopted.SYNONYM_OBJ,
            PLSqlTypesAdopted.SYNONYM_OBJ_WITH_LINK
    );

    public boolean isPublic(){
        ASTNode[] nodes = getNode().getChildren(null);
        // trivial search for PUBLIC qualifier
        for( int i=0; i < nodes.length && !nodes[i].getText().equalsIgnoreCase("SYNONYM"); i++){
            if(nodes[i].getText().equalsIgnoreCase("PUBLIC")){
                return true;
            }
        }
        return false;
    }

    public String getSynonymOwner(){
        ASTNode[] schema = getNode().getChildren(toks);

        if (schema[0].getElementType() == PLSqlTypesAdopted.SCHEMA_NAME && schema[1].getElementType() == PLSqlTypesAdopted.SYNONYM_NAME) {
            // the case: create or ... synonym schema_name DOT synonym_name "for" (schema_name DOT!)? synonym_obj
            return schema[0].getText();
        }

        return null;
    }


    public String getTargetSchema() {
        ASTNode[] schema = getNode().getChildren(toks);

        if (schema[0].getElementType() == PLSqlTypesAdopted.SCHEMA_NAME && schema[1].getElementType() == PLSqlTypesAdopted.SYNONYM_NAME) {
            // the case: create or ... synonym schema_name DOT synonym_name "for" (schema_name DOT!)? synonym_obj
            if (schema[2].getElementType() == PLSqlTypesAdopted.SCHEMA_NAME) {
                return schema[2].getText();
            }
        } else if (schema[0].getElementType() == PLSqlTypesAdopted.SYNONYM_NAME && schema[1].getElementType() == PLSqlTypesAdopted.SCHEMA_NAME) {
            // the case: create or ... synonym synonym_name "for" schema_name DOT synonym_obj
            return schema[1].getText();
        }

        return null;
    }

    public String getTargetObject() {
        ASTNode target = getNode().findChildByType(TokenSet.create(
                PLSqlTypesAdopted.SYNONYM_OBJ, PLSqlTypesAdopted.SYNONYM_OBJ_WITH_LINK));

        if (target != null) {
            return target.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateSynonym(this);
        } else {
            super.accept(visitor);
        }
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        // build the string: "my_synonym for sys.just_synonym"
        String text = getCustomString();
        if(text != null){
            return  "[Synonym] " + text.toLowerCase();
        }

        return null;
    }
*/


    private String getCustomString() {
        ASTNode[] nodes = getNode().getChildren(toks);

        if (nodes[0].getElementType() == PLSqlTypesAdopted.SCHEMA_NAME && nodes[1].getElementType() == PLSqlTypesAdopted.SYNONYM_NAME) {
            // the case: create or ... synonym schema_name DOT synonym_name "for" (schema_name DOT!)? synonym_obj
            if(nodes.length == 3){
                // the case: create or ... synonym schema_name DOT synonym_name "for" synonym_obj
                return nodes[1].getText() + " for " + nodes[2].getText();
            } else if(nodes.length == 4){
                // the case: create or ... synonym schema_name DOT synonym_name "for" (schema_name DOT!)? synonym_obj
                return nodes[1].getText() + " for " + nodes[2].getText() + "." + nodes[3].getText();
            }
        } else if (nodes[0].getElementType() == PLSqlTypesAdopted.SYNONYM_NAME) {
            // the case: create or ... synonym synonym_name "for" schema_name DOT synonym_obj
            if(nodes.length == 2){
                // the case: create or ... synonym synonym_name "for" synonym_obj
                return nodes[0].getText() + " for " + nodes[1].getText();
            } else if(nodes.length == 3){
                // the case: create or ... synonym synonym_name "for" (schema_name DOT!)? synonym_obj 
                return nodes[0].getText() + " for " + nodes[1].getText() + "." + nodes[2].getText();
            }
        }

        return null;
    }

}
