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

import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PackageBodyImpl extends PlSqlElementBase implements PackageBody {

//    static private final LoggerProxy log = LoggerProxy.getInstance("#PackageBodyImpl");

    public PackageBodyImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getPackageName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.PACKAGE_NAME);
        return node.getText();
    }

    public Executable[] findExecutableByName(String name) {
        List<Executable> out = new ArrayList<Executable>();
        ASTNode[] nodes = getNode().getChildren(
                TokenSet.create(
                        PlSqlElementTypes.FUNCTION_BODY,
                        PlSqlElementTypes.PROCEDURE_BODY));

        if (nodes != null && nodes.length > 0) {
            for (ASTNode n : nodes) {
                if (((Executable) n.getPsi()).getEName().equalsIgnoreCase(name)) {
                    out.add((Executable) n.getPsi());
                }
            }
        }
        return out.toArray(new Executable[0]);
    }

    @NotNull
    public String getCreateQuery() {
        return PlSqlUtil.completeCreateScript(this);
    }


    @NotNull
    public PlSqlElement[] findObjectByName(String name) {
        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.OBJECT_SPEC);
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        for (ASTNode e : nodes) {
            PlSqlElement plsqle = (PlSqlElement) e.getPsi();
            String _name = "";
            if (plsqle instanceof ExecutableSpec) {
                _name = ((ExecutableSpec) plsqle).getEName();
            } else if (plsqle instanceof Executable) {
                _name = ((Executable) plsqle).getEName();
            } else if (plsqle instanceof VariableDecl) {
                _name = ((VariableDecl) plsqle).getDeclName();
            } else if (plsqle instanceof TypeDeclaration) {
                _name = ((TypeDeclaration) plsqle).getDeclName();
            } else {
                continue;
            }

            if (_name.equalsIgnoreCase(name)) {
                out.add(plsqle);
            }
        }

        return out.toArray(new PlSqlElement[out.size()]);
    }

    @NotNull
    public PlSqlElement[] getObjects() {
        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.OBJECT_SPEC);
        if (nodes != null && nodes.length > 0) {
            PlSqlElement[] out = new PlSqlElement[nodes.length];
            for (int i = 0; i < nodes.length; i++) {
                out[i] = (PlSqlElement) nodes[i].getPsi();
            }
            return out;
        } else {
            return new PlSqlElement[0];
        }
    }

    public PackageSpec getPackageSpecification() {
        ResolveFacade facade = ((ResolveProvider) getContainingFile()).getResolver();
        return facade.findPackageSpecification(this);
    }


//    public void subtreeChanged(){
//        log.info("[subtreeChanged] PackageBody: " + getPackageName() );
//    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPackageBody(this);
        } else {
            super.accept(visitor);
        }
    }


    @NotNull
    public String getObjectType() {
        return "PACKAGE BODY";
    }

    @NotNull
    public String getObjectName() {
        return getPackageName();
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new PackagePresentation();
        } catch (SyntaxTreeCorruptedException e) {
            return null;
        }
    }

    private class PackagePresentation implements ItemPresentation {
        public String getPresentableText() {
            return "[Package] " + getPackageName().toLowerCase();
        }

        @Nullable
        public String getLocationString() {
            return null;
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return null;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return null;
        }
    }
}
