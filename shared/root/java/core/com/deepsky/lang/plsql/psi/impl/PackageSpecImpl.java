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

import com.deepsky.database.ora.desc.PackageSpecDescriptorImpl;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.FileBasedContextUrl;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PackageSpecImpl extends PlSqlElementBase implements PackageSpec {

    public PackageSpecImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getPackageName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.PACKAGE_NAME);
        return node.getText();
    }

    public PlSqlElement[] getObjects() {
        PsiElement[] elems = findChildrenByType(PlSqlElementTypes.OBJECT_SPEC, PlSqlElement.class);
        PlSqlElement[] out = new PlSqlElement[elems.length];
        int i=0;
        for(PsiElement e: elems){
            out[i++] = (PlSqlElement) e;
        }

        return out;
    }

    public PlSqlElement[] findObjectByName(String name){
/*
        PsiElement[] elems = findChildrenByType(PlSqlElementTypes.OBJECT_SPEC, PlSqlElement.class);
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        for(PsiElement e: elems){
            PlSqlElement plsqle = (PlSqlElement) e;
            String _name = "";
            if(plsqle instanceof ExecutableSpec ){
                _name = ((ExecutableSpec)plsqle).getEName();
            } else if(plsqle instanceof VariableDecl){
                _name = ((VariableDecl)plsqle).getRecordItemName();
            } else if(plsqle instanceof TypeDeclaration){
                _name = ((TypeDeclaration)plsqle).getTypeName();
            } else {
                int  d=0;
                continue;
            }

            if(_name.equalsIgnoreCase(name)){
                out.add((PlSqlElement) e);
            }
        }

        return out.toArray(new PlSqlElement[out.size()]);
*/

        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.OBJECT_SPEC);
//        PsiElement[] elems = findChildrenByType(PlSqlElementTypes.OBJECT_SPEC, PlSqlElement.class);
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        for(ASTNode e: nodes){
            PlSqlElement plsqle = (PlSqlElement) e.getPsi();
            String _name = "";
            if(plsqle instanceof ExecutableSpec ){
                _name = ((ExecutableSpec)plsqle).getEName();
            } else if(plsqle instanceof VariableDecl){
                _name = ((VariableDecl)plsqle).getDeclName();
            } else if(plsqle instanceof TypeDeclaration){
                _name = ((TypeDeclaration)plsqle).getDeclName();
            } else {
                continue;
            }

            if(_name.equalsIgnoreCase(name)){
                out.add(plsqle);
            }
        }

        return out.toArray(new PlSqlElement[out.size()]);
        
    }

    public PackageDescriptor describe() {

        String url = getContainingFile().getVirtualFile().getUrl();
        PackageDescriptor pdesc = new PackageSpecDescriptorImpl(
                new FileBasedContextUrl(url), getPackageName()
        );

        // todo -- decsriptor is not populated with objects
        return pdesc;
    }

    @NotNull
    public ExecutableSpec[] findExecutableByName(String name) {
        List<ExecutableSpec> out = new ArrayList<ExecutableSpec>();
        ASTNode[] nodes = getNode().getChildren(
                TokenSet.create(
                        PlSqlElementTypes.FUNCTION_SPEC,
                        PlSqlElementTypes.PROCEDURE_SPEC));

        if(nodes != null && nodes.length > 0){
            for(ASTNode n: nodes){
                if(((ExecutableSpec)n.getPsi()).getEName().equalsIgnoreCase(name)){
                    out.add((ExecutableSpec)n.getPsi());
                }
            }
        }
        return out.toArray(new ExecutableSpec[0]);
    }

    public PackageBody getPackageBody() {
        VirtualFile f = getContainingFile().getVirtualFile();
        if(f != null){
            VirtualFileSystem vfs = f.getFileSystem();
            PackageBody body = ResolveHelper.resolve_PackageBody(vfs, getPackageName());
            return body;
        } else {
            return null;
        }
    }

    @Nullable
    public String getQuickNavigateInfo(){
        return "[Package] " + getPackageName();    
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPackageSpec(this);
        } else {
            super.accept(visitor);
        }
    }

    public String getCtxPath() {
        return super.getCtxPath() + "Ps:" + getPackageName();
    }

}
