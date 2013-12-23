package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.Subquery;
import com.deepsky.lang.plsql.psi.ddl.CreateMaterializedView;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CreateMaterializedViewImpl extends PlSqlElementBase implements CreateMaterializedView {

    public CreateMaterializedViewImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getViewName() {
        ASTNode name = getNode().findChildByType(PLSqlTypesAdopted.VIEW_NAME_DDL);
        if (name == null) {
            throw new SyntaxTreeCorruptedException();
        }

        return StringUtils.discloseDoubleQuotes(name.getText());
    }

    @NotNull
    public VColumnDefinition[] getColumnDefs() {
        ASTNode[] columns = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.V_COLUMN_DEF));
        VColumnDefinition[] out = new VColumnDefinition[columns == null ? 0 : columns.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = (VColumnDefinition) columns[i].getPsi();
        }
        return out;

    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        TableDefinition t = findParent(TableDefinition.class);
        if (t != null) {
            String tableName = t.getTableName();
//            TableDescriptor desc = t.describe();
//            if(desc != null){
//                desc.
//            }
            return "[Table] " + tableName.toLowerCase()
                    + "\n [Column] " + getColumnName().toLowerCase() + " "
                    + getType().toString().toUpperCase();
        }
        return null;
    }
*/

    // todo -- needs to review
    public VColumnDefinition getColumnByName(String name) {
        for (VColumnDefinition c : getColumnDefs()) {
            if (c.getColumnName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public int getColumnPos(String columnName) {
        ASTNode[] columns = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.V_COLUMN_DEF));

        if (columns != null && columns.length > 0) {
            int pos = 0;
            for (ASTNode node : columns) {
                if (columnName.equalsIgnoreCase(node.getText())) {
                    return pos;
                } else {
                    pos++;
                }
            }
        }
        return -1;
    }

    @NotNull
    public SelectStatement getSelectExpr() {
        ASTNode select = getNode().findChildByType(
                TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION, PLSqlTypesAdopted.SELECT_EXPRESSION_UNION)
        );
        if (select != null) {
            return (SelectStatement) select.getPsi();
        } else {
            ASTNode subquery = getNode().findChildByType(PLSqlTypesAdopted.SUBQUERY);
            if (subquery != null) {
                return ((Subquery) subquery.getPsi()).getSelectStatement();
            }
            throw new SyntaxTreeCorruptedException();
        }
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateView(this);
        } else {
            super.accept(visitor);
        }
    }

    public Icon getIcon(int flags) {
        return Icons.VIEW;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new ViewPresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    public String getName() {
        return getViewName();
    }


    private class ViewPresentation implements ItemPresentation {
        public String getPresentableText() {
            return "[MaterializedView] " + getViewName().toLowerCase();
        }

        @Nullable
        public String getLocationString() {
            return "(materialized view)";
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return Icons.VIEW;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return TextAttributesKey.find("SQL.VIEW");
        }
    }


}
