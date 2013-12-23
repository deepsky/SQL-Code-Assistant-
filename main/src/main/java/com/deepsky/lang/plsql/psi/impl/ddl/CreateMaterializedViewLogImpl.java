package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ddl.CreateMaterializedViewLog;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CreateMaterializedViewLogImpl extends PlSqlElementBase implements CreateMaterializedViewLog {
    public CreateMaterializedViewLogImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getMasterTableName() {
        ASTNode name = getNode().findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
        if (name == null) {
            throw new SyntaxTreeCorruptedException();
        }

        return StringUtils.discloseDoubleQuotes(name.getText());
    }

}
