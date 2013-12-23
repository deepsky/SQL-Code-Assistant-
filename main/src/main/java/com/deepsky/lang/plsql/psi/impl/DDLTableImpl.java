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

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DDLTableImpl extends PlSqlElementBase implements DDLTable { //}, PsiNamedElement {
    private static final LoggerProxy log = LoggerProxy.getInstance("#DDLTableImpl");

    public DDLTableImpl(ASTNode node) {
        super(node);
    }

    public String getTableName() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    public TextRange getTableNameRange() {
        String text = getText();
        if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
            return new TextRange(getTextRange().getStartOffset() + 1, getTextRange().getEndOffset() - 1);
        } else {
            return getTextRange();
        }
    }

    public TableDefinition getTableDef(){
        PsiElement psi = getParent();
        if(psi instanceof TableDefinition){
            return (TableDefinition) psi;
        }

        throw new SyntaxTreeCorruptedException();
    }

    String[] adopt(String template, String[] variants) {
        List<String> cc = new ArrayList<String>();

        boolean toLower = false;
        if (template.length() > 0) {
            for (String v : variants) {
                StringBuffer _v = new StringBuffer();
                for (int i = 0; i < template.length(); i++) {
                    char c = template.charAt(i);
                    if (Character.isLetter(c)) {
                        if (Character.isLowerCase(c)) {
                            toLower = true;
                        } else {
                            toLower = false;
                        }
                    }
                    _v.append(c);
                }

                if (v.length() > _v.length()) {
                    String s;
                    if (toLower) {
                        s = v.toLowerCase();
                        _v.append(s.substring(_v.length(), v.length()));
                    } else {
                        s = v.toUpperCase();
                        _v.append(s.substring(_v.length(), v.length()));
                    }
                }
                cc.add(_v.toString());
            }
            return cc.toArray(new String[cc.size()]);
        } else {
            return variants;
        }
    }

/*
        // todo -- resolve stuff refactoring
    public PsiElement resolve() {
        TableDescriptor tdesc = describeTable(getText());
        if (tdesc != null) {
            return SqlScriptManager.mapToPsiTree(getProject(), tdesc);
        }
        return null;
    }
*/

    protected PsiElement resolveInternal() {
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitDDLTable(this);
        } else {
            super.accept(visitor);
        }
    }

    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        // todo
        return this;
    }
}

