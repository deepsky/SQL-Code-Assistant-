package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.AlterIndex;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class AlterIndexImpl extends PlSqlElementBase implements AlterIndex {
    public AlterIndexImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getIndexName() {
        return StringUtils.discloseDoubleQuotes(
                getNode().findChildByType(PLSqlTypesAdopted.INDEX_NAME).getText()
        );
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitAlterIndex(this);
        } else {
            super.accept(visitor);
        }
    }

}
