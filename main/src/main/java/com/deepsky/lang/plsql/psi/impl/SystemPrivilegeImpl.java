package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.SystemPrivilege;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;


public class SystemPrivilegeImpl extends PlSqlElementBase implements SystemPrivilege {
    public SystemPrivilegeImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitSystemPrivilege(this);
        } else {
            super.accept(visitor);
        }
    }

}
