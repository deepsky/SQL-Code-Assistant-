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

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.navigation.PlSqlPackageUtil;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TableCollectionDeclImpl extends PlSqlDeclarationBase implements TableCollectionDecl {
    public TableCollectionDeclImpl(ASTNode astNode) {
        super(astNode);
    }

    public Type getBaseType() {
        ASTNode[] types = getNode().getChildren(PlSqlElementTypes.TYPES);
        if(types.length == 0){
            throw new SyntaxTreeCorruptedException("Cannot parser type declaration");
        }
        return ((TypeSpec) types[0].getPsi()).getType();
    }

    public String getDeclName() {
        return StringUtils.discloseDoubleQuotes(getPsiDeclName().getText());
    }

    public PsiElement getPsiDeclName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.TYPE_NAME);
        if(node == null){
            throw new SyntaxTreeCorruptedException();
        }
        return node.getPsi();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTableCollectionDecl(this);
        } else {
            super.accept(visitor);
        }
    }

    @NotNull
    public String getObjectType() {
        return "TYPE";
    }

    @NotNull
    public String getObjectName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.TYPE_NAME);
        if(child == null){
            throw new SyntaxTreeCorruptedException();
        }
        return child.getText().toUpperCase();
    }
    
    @NotNull
    public String getCreateQuery() {
        return PlSqlUtil.completeCreateScript(this);
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        return "[Collection Type] " + getDeclName();
    }
*/

    // presentation stuff
    public Icon getIcon(int flags) {
        return Icons.TABLE_COLL_DECL;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new TablePresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    public String getName() {
        return getDeclName();
    }



    class TablePresentation implements ItemPresentation {
        public String getPresentableText() {
            return "[Collection Type] " + getDeclName().toLowerCase();
        }

        @Nullable
        public String getLocationString() {
            PlSqlElement pkg = PlSqlPackageUtil.findPackageForElement(TableCollectionDeclImpl.this);
            if (pkg instanceof PackageSpec) {
                String packageName = ((PackageSpec) pkg).getPackageName();
                return "in " + packageName + " (Collection Type)";
            } else if (pkg instanceof PackageBody) {
                String packageName = ((PackageBody) pkg).getPackageName();
                return "in " + packageName + " (Collection Type)";
            } else {
                return "(Collection Type)";
            }
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return Icons.TABLE_COLL_DECL;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return null;
        }
    }

}
