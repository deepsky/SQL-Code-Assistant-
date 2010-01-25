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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.PlainTable;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.TableDescriptorForRegular;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;


public class PlainTableImpl extends GenericTableBase implements PlainTable {

    static final Logger log = Logger.getInstance("#PlainTableImpl");

    public PlainTableImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getTableName() {
        PsiElement elem = getTableNameElement();
        if(elem == null){
            throw new SyntaxTreeCorruptedException();
        }


        return StringUtils.discloseDoubleQuotes(elem.getText());
    }

    // table_spec ( alias )?
    // table_spec:  ( schema_name DOT )? table_name ( AT_SIGN link_name )?
    // alias:       ( "as" )?  identifier
    public PsiElement getTableNameElement() {
        return this.findChildByType(PLSqlTypesAdopted.TABLE_NAME);
    }

    public String getQuickNavigateInfo() {
        return "[Table] " + getTableName().toLowerCase();
    }


    public String getSchemaName() {
        ASTNode schema = getNode().findChildByType(PLSqlTypesAdopted.SCHEMA_NAME);
        return (schema != null) ? schema.getText() : "";
    }

    public String getAlias() {
        ASTNode alias = getNode().findChildByType(PLSqlTypesAdopted.ALIAS_NAME);
        if (alias != null && alias.getText().trim().length() > 0) {
            String[] splitted = alias.getText().split(" ");
            if (splitted.length > 1) {
                return splitted[1];
            } else {
                return splitted[0];
            }
        }
        return null;
    }


//    static Map<String, TableDescriptor> cache = new HashMap<String, TableDescriptor>();
//    synchronized protected TableDescriptorLegacy describeInternal() {
//        String name = getTableName();
//        TableDescriptor _tdesc = cache.get(name);
//        if(_tdesc == null){
//            _tdesc = SupportStuff.describeTable(name.toLowerCase());
//            if(_tdesc != null){
//                cache.put(name.toLowerCase(), _tdesc);
//            }
//        } else {
//            int hh=0;
//        }
////        TableDescriptor _tdesc = SupportStuff.describeTable(name);
//        if(_tdesc != null){
//            return new TableDescriptorForRegular(_tdesc, getAlias());
//        } else {
//            return null;
//        }
//    }


    protected TableDescriptorLegacy describeInternal() {
        String name = getTableName();
        TableDescriptor _tdesc = ResolveHelper.describeTable(name);

        if(_tdesc != null){
            return new TableDescriptorForRegular(_tdesc, getAlias());
        } else {
            return null;
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPlainTable(this);
        } else {
            super.accept(visitor);
        }
    }
}
