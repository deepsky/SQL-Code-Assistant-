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

package com.deepsky.lang.plsql.psi.impl.ref;

import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.deepsky.lang.plsql.psi.impl.PlSqlReferenceBase;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;


public class TableRefImpl extends PlSqlReferenceBase implements TableRef {
    public TableRefImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Object[] getVariants(final String text) {
        return new Object[0]; 
    }

    public String getTableName() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    protected PsiElement resolveInternal() {
        return facade.resolveTable(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTableRef(this);
        } else {
            super.accept(visitor);
        }
    }

    public TextRange getTableNameRange() {
        String text = getText();
        if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
            return new TextRange(getTextRange().getStartOffset() + 1, getTextRange().getEndOffset() - 1);
        } else {
            return getTextRange();
        }
    }

    public ResolveDescriptor resolveLight() {
        PsiElement parent = getParent();
        String completeName = getTableName();
        if(parent instanceof TableAlias){
            String schema = ((TableAlias)parent).getSchemaName();
            if(schema.length() > 0){
                completeName = schema + "." + completeName;
            }
        }
        return getResolveFacade().getLLResolver().resolveTableRef(completeName);

    }


/*
    String[] adopt(String template, List<NameHandler.NamedItem> variants) {
        List<String> cc = new ArrayList<String>();

        boolean toLower = false;
        if (template.length() > 0) {
            for (NameHandler.NamedItem item : variants) {
                String v = item.getName();
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
            // copy found items and sort by name
            for (NameHandler.NamedItem item : variants) {
                cc.add(item.getName().toLowerCase());
            }
            Collections.sort(cc);
            return cc.toArray(new String[cc.size()]);
        }
    }
*/

}
