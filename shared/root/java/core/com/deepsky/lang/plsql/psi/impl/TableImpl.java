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

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ref.Table;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
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

public class TableImpl extends PlSqlReferenceBase implements Table { //}, PsiNamedElement {
    private static final LoggerProxy log = LoggerProxy.getInstance("#TableImpl");

    public TableImpl(ASTNode node) {
        super(node);
    }

    public String getTableName() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    public TextRange getTableNameRange() {
        String text = getText();
        if(text.charAt(0) == '"' && text.charAt(text.length()-1) == '"'){
            return new TextRange(getTextRange().getStartOffset()+1, getTextRange().getEndOffset()-1); 
        } else {
            return getTextRange();
        }
    }


    public boolean isReferenceTo(PsiElement psiElement) {
        if(psiElement instanceof TableDefinition) {
            if(((TableDefinition)psiElement).getTableName().equalsIgnoreCase(getText())){
                return psiElement == resolve();
            }
        } else if(psiElement instanceof CreateView) {
            if(((CreateView)psiElement).getViewName().equalsIgnoreCase(getText())){
                return psiElement == resolve();
            }
        }
//        String targetName = (psiElement instanceof TableDefinition)? ((TableDefinition)elem).getTableName(): ((CreateView)elem).getViewName();
//
//        if(psiElement != null && psiElement.getText().equalsIgnoreCase(getText())){
//            PsiElement psi = resolve();
//            if(psi == psiElement){
//                return true;
//            }
//        }

        return false;
    }

    @NotNull
    public Object[] getVariants(String text) {

        log.info("#getVariants, type: "
                + this.getNode().getElementType()
                + ", parent: " + this.getNode().getTreeParent().getElementType()
                + ", text: " + text
        );

        String[] candidates =
                getTableNameVariantsForPrefix(text);

        return adopt(text, candidates);
    }


    String[] getTableNameVariantsForPrefix(String prefix) {

        ObjectCache cache = PluginKeys.OBJECT_CACHE.getData(getProject()); //ObjectCacheFactory.getObjectCache();
        if(prefix.length() == 0){
            String user = cache.getCurrentUser();
            return cache.getNameListForType(user, ObjectCache.TABLE | ObjectCache.VIEW);
        } else {
            return cache.findByNamePrefix2( ObjectCache.TABLE | ObjectCache.VIEW, prefix);
        }
    }

    static String[] extractNames(DbObject[] objs){
        String[] out = new String[objs.length];
        for(int i=0; i<objs.length; i++){
            out[i] = objs[i].getName();
        }

        return out;
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

                if(v.length() > _v.length()){
                    String s;
                    if(toLower){
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

    public PsiElement resolve() {
        TableDescriptor tdesc = describeTable(getText());
        if (tdesc != null) {
//            PlSqlFile file = ShortNamesCache.findDbObjectDefinition(getProject(), tdesc);
//            PsiElement desc =  file.findDeclaration(tdesc);
            return SqlScriptManager.mapToPsiTree(getProject(), tdesc); //desc;
//            if (file != null) {
//                return file;
//            }
        }
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitTable(this);
      }
      else {
        super.accept(visitor);
      }
    }

    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        // todo
        return this;
    }
}
