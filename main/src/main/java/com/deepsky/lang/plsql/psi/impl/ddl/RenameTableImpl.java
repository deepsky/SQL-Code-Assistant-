package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.integration.plsql.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.RenameTable;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class RenameTableImpl  extends PlSqlElementBase implements RenameTable {

    public RenameTableImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitRenameTable(this);
        } else {
            super.accept(visitor);
        }
    }


    @Override
    public String getOriginTable(int group) {
        ASTNode[] tables = getNode().getChildren(TokenSet.create(PlSqlElementTypes.TABLE_REF));
        if(tables.length > 0){
            return StringUtils.discloseDoubleQuotes(tables[0].getText());
        }
        return null;
    }
}
