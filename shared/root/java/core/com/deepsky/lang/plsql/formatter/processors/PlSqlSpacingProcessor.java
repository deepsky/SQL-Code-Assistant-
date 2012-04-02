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

package com.deepsky.lang.plsql.formatter.processors;

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.formatter.SpacingConstants;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.impl.ColumnDefinitionForAlterImpl;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.psi.types.RowtypeType;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nullable;

public class PlSqlSpacingProcessor extends PlSqlElementVisitor {
    private PsiElement myParent;

    private Spacing myResult;
    private ASTNode leftChild;
    private ASTNode rightChild;

    private final PlSqlCodeStyleSettings styleSettings;

    public PlSqlSpacingProcessor(ASTNode node, PlSqlCodeStyleSettings plSqlSettings) {
        styleSettings = plSqlSettings;

        _init(node);

        if (leftChild == null || rightChild == null) {
            return;
        }

        final PsiElement psi1 = leftChild.getPsi();
        final PsiElement psi2 = rightChild.getPsi();
        if (psi1 == null || psi2 == null) return;
        if (psi1.getLanguage() != PlSqlFileType.PLSQL_LANGUAGE
                || psi2.getLanguage() != PlSqlFileType.PLSQL_LANGUAGE) { //Language.findInstance(PlSqlLanguage.class)) {
            return;
        }

        if (myParent instanceof PlSqlElement) {
            myParent.accept(this);
        }
    }

    private void _init(final ASTNode child) {
        if (child != null) {
            ASTNode treePrev = child.getTreePrev();
            while (treePrev != null && isWhiteSpace(treePrev)) {
                treePrev = treePrev.getTreePrev();
            }
            if (treePrev == null) {
                _init(child.getTreeParent());
            } else {
                rightChild = child;
                leftChild = treePrev;
                final CompositeElement parent = (CompositeElement) treePrev.getTreeParent();
                myParent = SourceTreeToPsiMap.treeElementToPsi(parent);
            }
        }
    }

/*
    @Nullable
    static ASTNode getPrevElementType(final ASTNode child) {
        return FormatterUtil.getPreviousNonWhitespaceLeaf(child);
    }
*/

    static boolean isWhiteSpace(final ASTNode node) {
        return node != null && (node.getPsi() instanceof PsiWhiteSpace || node.getTextLength() == 0);
    }

    public Spacing getSpacing() {
        return myResult;
    }

    private void createSpaceInCode(final boolean space) {
        createSpaceProperty(space, styleSettings.MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT);
    }

    private void createSpaceProperty(boolean space, int keepBlankLines) {
        createSpaceProperty(space, styleSettings.KEEP_LINE_BREAKS, keepBlankLines);
    }

    private void createSpaceProperty(boolean space, boolean keepLineBreaks, final int keepBlankLines) {
        myResult = Spacing.createSpacing(space ? 1 : 0, space ? 1 : 0, 0, keepLineBreaks, keepBlankLines);
    }

    private void createSpaceProperty(int spaces, boolean keepLineBreaks, final int keepBlankLines) {
        myResult = Spacing.createSpacing(spaces, spaces, 0, keepLineBreaks, keepBlankLines);
    }

    // Visitors
    public void visitCallArgumentList(CallArgumentList node) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            createSpaceInCode(styleSettings.SPACE_WITHIN_BRACKETS);
        }
    }

    public void visitTableDefinition(TableDefinition tableDefinition) {
        if (rightChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN) {
            if (!styleSettings.WRAP_OPEN_PAREN_IN_CREATE_TABLE) {
                myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        }
    }

    public void visitPrimaryKeyConstraint(PrimaryKeyConstraint constraint) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitForeignKeyConstraint(ForeignKeyConstraint constraint) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitAlterTable(AlterTable table) {
        if (compose(PlSqlTokenTypes.KEYWORD_ALTER, PlSqlTokenTypes.KEYWORD_TABLE, PlSqlElementTypes.TABLE_REF)) {
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_ADD)) {
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_MODIFY)) {
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_RENAME)) {
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_DROP)) {
        } else if (rightChild.getElementType() == PlSqlTokenTypes.SEMI) {
            // do not keep blank lines
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitCreateTriggerDML(CreateTriggerDML trigger) {
        if (compose(PlSqlTokenTypes.KEYWORD_CREATE, PlSqlTokenTypes.KEYWORD_TRIGGER, PlSqlElementTypes.TRIGGER_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_CREATE, PlSqlTokenTypes.KEYWORD_OR, PlSqlTokenTypes.KEYWORD_REPLACE, PlSqlTokenTypes.KEYWORD_TRIGGER, PlSqlElementTypes.TRIGGER_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_AFTER, PlSqlElementTypes.DML_TRIGGER_CLAUSE)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_BEFORE, PlSqlElementTypes.DML_TRIGGER_CLAUSE)) {
/*
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_RENAME)) {
        } else if (compose(PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.KEYWORD_DROP)) {
        } else if (rightChild.getElementType() == PlSqlTokenTypes.SEMI) {
            // do not keep blank lines
            myResult = SpacingConstants.NO_SPACING;
*/
        }
    }

    public void visitDatatype(DataType dataType) {
        if (rightChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN) {
            createSpaceInCode(styleSettings.SPACE_BEFORE_OPEN_PAREN_IN_DATATYPE);
        } else if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            createSpaceProperty(0, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else if (rightChild.getElementType() == PlSqlTokenTypes.COMMA) {
            createSpaceProperty(0, false, 0);
        } else {
            myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
    }


    public void visitVariableDecl(VariableDecl node) {
        final IElementType leftType = leftChild.getElementType();
        final IElementType rightType = rightChild.getElementType();

        if (leftType == PlSqlElementTypes.VARIABLE_NAME && rightChild.getPsi() instanceof TypeSpec) {
            int len = leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else if (leftType == PlSqlElementTypes.VARIABLE_NAME && rightType == PlSqlTokenTypes.KEYWORD_CONSTANT) {
            int len = leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            if (rightType == PlSqlTokenTypes.SEMI) {
                myResult = SpacingConstants.NO_SPACING;
            } else {
                createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
            }
        }
    }

    public void visitColumnDefinition(ColumnDefinition definition) {
        if (leftChild.getElementType() == PlSqlElementTypes.COLUMN_NAME_DDL
                && rightChild.getPsi() instanceof TypeSpec) {

            int len = leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }

    public void visitColumnDefinitionForAlter(ColumnDefinitionForAlterImpl definition) {
        if (leftChild.getElementType() == PlSqlElementTypes.COLUMN_NAME_DDL
                && rightChild.getPsi() instanceof TypeSpec) {

            int len = leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }

    public void visitTableCollectionDecl(TableCollectionDecl node) {
        final IElementType leftType = leftChild.getElementType();
        final IElementType rightType = rightChild.getElementType();
        if (leftType == PlSqlElementTypes.TYPE_NAME
                && (rightType == PlSqlTokenTypes.KEYWORD_AS || rightType == PlSqlTokenTypes.KEYWORD_IS)) {
            int len = "TYPE ".length() + leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            if (rightType == PlSqlTokenTypes.SEMI) {
                myResult = SpacingConstants.NO_SPACING;
            } else {
                createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
            }
        }
    }

    public void visitVarrayCollectionDecl(VarrayCollectionDecl decl) {
        final IElementType leftType = leftChild.getElementType();
        final IElementType rightType = rightChild.getElementType();
        if (leftType == PlSqlElementTypes.TYPE_NAME
                && (rightType == PlSqlTokenTypes.KEYWORD_AS || rightType == PlSqlTokenTypes.KEYWORD_IS)) {
            int len = "TYPE ".length() + leftChild.getTextLength();
            int spaces = len % (styleSettings.TAB_SIZE * 2);
            spaces = styleSettings.TAB_SIZE * 2 - spaces;
            createSpaceProperty(spaces, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            if (rightType == PlSqlTokenTypes.SEMI
                    || rightType == PlSqlTokenTypes.CLOSE_PAREN
                    || rightType == PlSqlTokenTypes.OPEN_PAREN
                    || leftType == PlSqlTokenTypes.OPEN_PAREN) {
                myResult = SpacingConstants.NO_SPACING;
            } else {
                createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
            }
        }
    }


    public void visitInsertStatement(InsertStatement node) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                && rightChild.getElementType() == PlSqlElementTypes.EXPR_LIST) {
            myResult = SpacingConstants.NO_SPACING;
        } else if (leftChild.getElementType() == PlSqlElementTypes.EXPR_LIST
                && rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitCreateView(CreateView view) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                && rightChild.getElementType() == PlSqlElementTypes.V_COLUMN_DEF) {
            myResult = SpacingConstants.NO_SPACING;
        } else if (leftChild.getElementType() == PlSqlElementTypes.V_COLUMN_DEF
                && rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }


    public void visitColumnFKSpec(ColumnFKSpec columnFKSpec) {
        createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnPKSpec(ColumnPKSpec columnPKSpec) {
        createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnCheckConstraint(ColumnCheckConstraint checkConstraint) {
        createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnNotNullConstraint(ColumnNotNullConstraint nullConstraint) {
        createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitUniqueConstraint(UniqueConstraint constraint) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING_WITH_NEWLINE;
        } else {
            createSpaceProperty(1, false, styleSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }


    public void visitObjectReference(ObjectReference node) {
        myResult = SpacingConstants.NO_SPACING;
    }

    public void visitFunctionCall(FunctionCall call) {
        if (leftChild.getElementType() == PlSqlElementTypes.CALLABLE_NAME_REF
                && rightChild.getElementType() == PlSqlElementTypes.CALL_ARGUMENT_LIST) {
            createSpaceInCode(styleSettings.SPACE_BEFORE_PARAMETERS);
//            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitFunction(Function body) {
        if (leftChild.getElementType() == PlSqlTokenTypes.KEYWORD_RETURN
                && rightChild.getElementType() == PlSqlElementTypes.RETURN_TYPE) {
            myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
    }

    public void visitReturnStatement(ReturnStatement statement) {
        if (leftChild.getElementType() == PlSqlTokenTypes.KEYWORD_RETURN
                && rightChild.getPsi() instanceof Expression) {
            myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
    }

    public void visitProcedureCall(ProcedureCall call) {
        if (leftChild.getElementType() == PlSqlElementTypes.CALLABLE_NAME_REF
                && rightChild.getElementType() == PlSqlElementTypes.CALL_ARGUMENT_LIST) {
            createSpaceInCode(styleSettings.SPACE_BEFORE_PARAMETERS);
//            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitRowtypeType(RowtypeType type) {
        if (leftChild.getElementType() == PlSqlTokenTypes.PERCENTAGE
                || rightChild.getElementType() == PlSqlTokenTypes.PERCENTAGE) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitColumnTypeRef(ColumnTypeRef columnTypeRef) {
        if (leftChild.getElementType() == PlSqlTokenTypes.PERCENTAGE
                || rightChild.getElementType() == PlSqlTokenTypes.PERCENTAGE) {
            myResult = SpacingConstants.NO_SPACING;
        }
    }

    public void visitColumnSpecList(ColumnSpecList node) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING_WITH_NEWLINE;
        }
    }

    public void visitArgumentList(ArgumentList argumentList) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING_WITH_NEWLINE;
        }
    }

    public void visitParenthesizedExpr(ParenthesizedExpr expr) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING_WITH_NEWLINE;
        }
    }


    /*
    CREATE SEQUENCE T1_SEQ
      START WITH 100000
      INCREMENT BY 2
      MINVALUE 1
      MAXVALUE 9999999999999999999999999999
      MINVALUE 1
      CYCLE
      CACHE 25
      NOORDER;
    */
    public void visitCreateSequence(CreateSequence sequence) {
        if (compose(PlSqlTokenTypes.KEYWORD_CREATE, PlSqlTokenTypes.KEYWORD_SEQUENCE, PlSqlElementTypes.OBJECT_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_START, PlSqlTokenTypes.KEYWORD_WITH, PlSqlElementTypes.NUMERIC_LITERAL)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_INCREMENT, PlSqlTokenTypes.KEYWORD_BY, PlSqlElementTypes.NUMERIC_LITERAL)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_MINVALUE, PlSqlElementTypes.NUMERIC_LITERAL)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_MAXVALUE, PlSqlElementTypes.NUMERIC_LITERAL)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_CACHE, PlSqlElementTypes.NUMERIC_LITERAL)) {
        } else if (rightChild.getElementType() == PlSqlTokenTypes.SEMI) {
            myResult = SpacingConstants.NO_SPACING;
        } else {
            // do not keep blank lines
            myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
        }
    }


    /*
    CREATE USER helen
        IDENTIFIED BY out_standing1
        DEFAULT TABLESPACE example
        QUOTA 10M ON example
        TEMPORARY TABLESPACE temp
        QUOTA 5M ON system
        PROFILE app_user
        PASSWORD EXPIRE;
    */
    public void visitCreateUser(CreateUser user) {
        if (compose(PlSqlTokenTypes.KEYWORD_CREATE, PlSqlTokenTypes.KEYWORD_USER, PlSqlElementTypes.OBJECT_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_IDENTIFIED, PlSqlTokenTypes.KEYWORD_BY, PlSqlTokenTypes.IDENTIFIER)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_DEFAULT, PlSqlTokenTypes.KEYWORD_TABLESPACE, PlSqlElementTypes.TABLESPACE_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_QUOTA, PlSqlTokenTypes.STORAGE_SIZE, PlSqlTokenTypes.KEYWORD_ON, PlSqlElementTypes.TABLESPACE_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_TEMPORARY, PlSqlTokenTypes.KEYWORD_TABLESPACE, PlSqlElementTypes.TABLESPACE_NAME)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_PROFILE, PlSqlTokenTypes.IDENTIFIER)) {
        } else if (compose(PlSqlTokenTypes.KEYWORD_PASSWORD, PlSqlTokenTypes.KEYWORD_EXPIRE)) {
        } else if (rightChild.getElementType() == PlSqlTokenTypes.SEMI) {
            myResult = SpacingConstants.NO_SPACING;
        } else {
            // do not keep blank lines
            myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
        }
    }

    public void visitGroupByClause(GroupByClause clause) {
        if (leftChild.getElementType() == PlSqlTokenTypes.KEYWORD_GROUP
                && rightChild.getElementType() == PlSqlTokenTypes.KEYWORD_BY) {
            myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
    }


    public void visitOrderByClause(OrderByClause clause) {
        if (leftChild.getElementType() == PlSqlTokenTypes.KEYWORD_ORDER
                && rightChild.getElementType() == PlSqlTokenTypes.KEYWORD_BY) {
            myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }
    }


    public void visitSubquery(Subquery node) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            myResult = SpacingConstants.NO_SPACING_WITH_NEWLINE;
        }
    }

    private boolean compose(IElementType... types) {
        for (int i = 1; i < types.length; i++) {
            if (leftChild.getElementType() == types[i - 1]) {
                if (rightChild.getElementType() == types[i]) {
                    myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
                    return true;
                }
            }
        }
        return false;
    }

    public void visitSelectStatement(SelectStatement node) {
        if (leftChild.getElementType() == PlSqlElementTypes.EXPR_COLUMN
                && rightChild.getElementType() == PlSqlTokenTypes.COMMA) {
            if (styleSettings.COMMA_AFTER_SELECT_EXPR == 2) {
                myResult = SpacingConstants.NO_SPACING;
            }
        } else if (leftChild.getElementType() == PlSqlTokenTypes.COMMA
                && rightChild.getElementType() == PlSqlElementTypes.EXPR_COLUMN) {
            if (styleSettings.COMMA_AFTER_SELECT_EXPR == 3) {
                myResult = SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        }

    }


/*
    public void visitSelectStatementUnion(SelectStatementUnion select) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitSelectStatement(SelectStatement select) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitFromClause(FromClause node) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitPlainTable(TableAlias node) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitFromSubquery(FromSubquery fromSubquery) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitSubquery(Subquery subquery) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitWhereCondition(WhereCondition node) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }

    public void visitLogicalExpression(LogicalExpression expression) {
        myResult = SpacingConstants.ONE_SPACE_WITH_NEWLINE;
    }
*/

    final private static TokenSet PACKAGE_LEVEL_WORDS =
            TokenSet.create(
                    PlSqlTokenTypes.KEYWORD_CREATE,
                    PlSqlTokenTypes.KEYWORD_OR,
                    PlSqlTokenTypes.KEYWORD_REPLACE,
                    PlSqlTokenTypes.KEYWORD_PACKAGE,
                    PlSqlTokenTypes.KEYWORD_BODY,
                    PlSqlTokenTypes.KEYWORD_END,
                    PlSqlElementTypes.PACKAGE_NAME
            );


    public void visitPackageBody(PackageBody body) {
        if (PACKAGE_LEVEL_WORDS.contains(leftChild.getElementType()) &&
                PACKAGE_LEVEL_WORDS.contains(rightChild.getElementType())) {
            createSpaceProperty(1, false, 0);
        }
    }

    public void visitPackageSpec(PackageSpec spec) {
        if (PACKAGE_LEVEL_WORDS.contains(leftChild.getElementType()) &&
                PACKAGE_LEVEL_WORDS.contains(rightChild.getElementType())) {
            createSpaceProperty(1, false, 0);
        }
    }
}
