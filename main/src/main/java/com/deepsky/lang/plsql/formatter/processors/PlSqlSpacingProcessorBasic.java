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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.formatter.SpacingConstants;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ctrl.CommitStatement;
import com.deepsky.lang.plsql.psi.ctrl.ControlStatement;
import com.deepsky.lang.plsql.psi.ctrl.RollbackStatement;
import com.deepsky.lang.plsql.psi.ddl.SqlDDLStatement;
import com.deepsky.lang.PsiUtil;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public class PlSqlSpacingProcessorBasic {

    private static final TokenSet PUNCTUATION_SIGNS = TokenSet.create(PlSqlTokenTypes.COMMA, PlSqlTokenTypes.DOT);

    final static Class[] topLevel = new Class[]{
            SqlDDLStatement.class, com.deepsky.lang.plsql.psi.PlSqlBlock.class,
            Executable.class, TypeDeclaration.class,
            PackageBody.class, PackageSpec.class,
            SqlPlusCommand.class,
            SelectStatement.class, Subquery.class,
            CommitStatement.class, RollbackStatement.class,
            ControlStatement.class
    };

    final static Class[] topLevelDML = new Class[]{
            DeleteStatement.class, UpdateStatement.class, MergeStatement.class, InsertStatement.class
    };

    public static Spacing getSpacing(com.deepsky.lang.plsql.formatter.PlSqlBlock child1,
                                     com.deepsky.lang.plsql.formatter.PlSqlBlock child2,
                                     PlSqlCodeStyleSettings settings) {

        ASTNode leftNode = child1.getNode();
        ASTNode rightNode = child2.getNode();

        final IElementType leftType = leftNode.getElementType();
        final IElementType rightType = rightNode.getElementType();

        // For dots, commas etc.
        if (rightType == PlSqlTokenTypes.COMMA) {
            if (leftType == PlSqlElementTypes.PARAMETER_SPEC
                    || leftType == PlSqlElementTypes.CALL_ARGUMENT
                    || leftType == PlSqlElementTypes.COLUMN_DEF
                    || leftType == PlSqlElementTypes.RECORD_ITEM
                    || leftType == PlSqlElementTypes.PK_SPEC
                    || leftType == PlSqlElementTypes.FK_SPEC
                    || leftType == PlSqlElementTypes.UNIQUE_CONSTRAINT
                    || leftType == PlSqlElementTypes.CHECK_CONSTRAINT) {
                return SpacingConstants.NO_SPACING;
            }
        }

        // :new.column1 or :old.column1
        if (leftType == PlSqlTokenTypes.KEYWORD_OLD || leftType == PlSqlTokenTypes.KEYWORD_NEW) {
            if (rightType == PlSqlTokenTypes.DOT) {
                return SpacingConstants.NO_SPACING;
            }
        } else if (leftType == PlSqlTokenTypes.COLON &&
                (rightType == PlSqlTokenTypes.KEYWORD_OLD || rightType == PlSqlTokenTypes.KEYWORD_NEW)) {
            return SpacingConstants.NO_SPACING;
        }

        if (PUNCTUATION_SIGNS.contains(rightType)) {
            return SpacingConstants.NO_SPACING_WITH_NEWLINE;
        } else if (leftType == PlSqlTokenTypes.DOT) {
            return SpacingConstants.NO_SPACING_WITH_NEWLINE;
        } else if (rightType == PlSqlTokenTypes.SEMI) {
            return SpacingConstants.NO_SPACING;
        }

/*
        if (left instanceof PsiComment || right instanceof PsiComment) {
            // Keep comments
            return Spacing.createSpacing(1, 1, 1, true, settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
        }
*/

        // pragma_autonomous_transaction
        if (leftType == PlSqlTokenTypes.KEYWORD_PRAGMA) {
            if (rightType == PlSqlTokenTypes.KEYWORD_AUTONOMOUS_TRANSACTION) {
                return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        }
        // TAB1%ROWTYPE
        if (rightType == PlSqlTokenTypes.PERCENTAGE || leftType == PlSqlTokenTypes.PERCENTAGE) {
            return SpacingConstants.NO_SPACING;
        }
        // 10g quoted string: q'[Hello!]'
        if (leftType == PlSqlTokenTypes.QUOTED_STR_START && rightType == PlSqlTokenTypes.QUOTED_STR_END) {
            return SpacingConstants.NO_SPACING;
        }

        // ALIAS NAME 'AS DATE'
        if (leftType == PlSqlTokenTypes.KEYWORD_AS || rightType == PlSqlElementTypes.ALIAS_IDENT) {
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        }

        final PsiElement left = leftNode.getPsi();
        final PsiElement right = rightNode.getPsi();
        final PsiElement parent = left.getParent();
        final IElementType parentType = parent.getNode().getElementType();

        if (parent instanceof PsiFile) {
            if (compose(left, right, topLevel)) {
                return Spacing.createSpacing(1, 1,
                        settings.MIN_LINES_BETWEEN_FILE_LEVEL_STMT + 1,
                        true,
                        settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT);

            } else if (leftType == PlSqlTokenTypes.SEMI && containedIn(topLevel, right)) {
                // A workaround for SELECT statement which does not include SEMI
                final ASTNode prevLeft = PsiUtil.prevVisibleSibling(leftNode);
                if (prevLeft != null && prevLeft.getPsi() instanceof SelectStatement) {
                    return Spacing.createSpacing(1, 1,
                            settings.MIN_LINES_BETWEEN_FILE_LEVEL_STMT + 1,
                            true,
                            settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
                }
            } else if (leftType == PlSqlTokenTypes.DIVIDE && containedIn(topLevel, right)) {
                // A workaround for statements which do not include DIVIDE
                final ASTNode prevLeft = PsiUtil.prevVisibleSibling(leftNode);
                if (prevLeft != null && containedIn(topLevel, prevLeft.getPsi())) {
                    return Spacing.createSpacing(1, 1,
                            settings.MIN_LINES_BETWEEN_FILE_LEVEL_STMT + 1,
                            true,
                            settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
                }
            } else if (left instanceof PsiComment || right instanceof PsiComment) {
                // Keep comments
                return Spacing.createSpacing(1, 1, 0, true, settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
            } else if (containedIn(topLevel, right) || containedIn(topLevel, left)) {
                // indent ahead OR behind
                return Spacing.createSpacing(1, 1,
                        settings.MIN_LINES_BETWEEN_FILE_LEVEL_STMT + 1,
                        true,
                        settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
            } if (compose(left, right, topLevelDML)) {
                // Between DML statements of the file level
                return Spacing.createSpacing(1, 1,
                        0,
                        true,
                        settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT);

            }else if (containedIn(topLevelDML, left) || containedIn(topLevelDML, right)) {
                // indent behind OR ahead of DML statement
                return Spacing.createSpacing(1, 1,
                        settings.MIN_LINES_BETWEEN_FILE_LEVEL_STMT + 1,
                        true,
                        settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT + 1);
            }
        } else if (parentType == PlSqlElementTypes.STATEMENT_LIST) {
            if (leftType == PlSqlTokenTypes.SEMI) {
                if (PlSqlElementTypes.PLSQL_STATEMENTS.contains(rightType) || rightType == PlSqlElementTypes.PLSQL_BLOCK) {
                    return Spacing.createSpacing(1, 1,
                            settings.MIN_LINES_BETWEEN_BLOCK_LEVEL_STMT,
                            true,
                            settings.MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT + 1);
                }
            }
        } else if (parentType == PlSqlElementTypes.TRIGGER_FOR_EACH) {
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        } else if (parentType == PlSqlElementTypes.DML_TRIGGER_CLAUSE) {
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        } else if (parentType == PlSqlElementTypes.COUNT_FUNC) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN && rightType == PlSqlElementTypes.ASTERISK_COLUMN
                    || leftType == PlSqlElementTypes.ASTERISK_COLUMN && rightType == PlSqlTokenTypes.CLOSE_PAREN) {
                return SpacingConstants.NO_SPACING;
            }
        } else if (parentType == PlSqlElementTypes.STORAGE_PARAM) {
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        } else if (parentType == PlSqlElementTypes.RECORD_FMT_SPEC) {
            return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
        } else if (parentType == PlSqlElementTypes.EXT_FIELD_SPEC) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN
                    || rightType == PlSqlTokenTypes.CLOSE_PAREN
                    || rightType == PlSqlTokenTypes.OPEN_PAREN) {
                return SpacingConstants.NO_SPACING;
            } else {
                return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        } else if (parentType == PlSqlElementTypes.EXT_TABLE_LOCATION_SPEC) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN
                    || rightType == PlSqlTokenTypes.CLOSE_PAREN) {
                return SpacingConstants.NO_SPACING;
            } else {
                return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        } else if (parentType == PlSqlElementTypes.ALTER_TABLE_CONSTRAINT) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN || rightType == PlSqlTokenTypes.CLOSE_PAREN) {
                return SpacingConstants.NO_SPACING;
            }
        } else if (parentType == PlSqlElementTypes.VARRAY_COLLECTION) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN || rightType == PlSqlTokenTypes.OPEN_PAREN
                    || rightType == PlSqlTokenTypes.CLOSE_PAREN) {
                return SpacingConstants.NO_SPACING;
            }
        } else if (parent instanceof PackageSpec || parent instanceof PackageBody) {
            // Package content
            if (compose(left, right, PlSqlElementTypes.PKG_LEVEL)) {
                return Spacing.createSpacing(1, 1,
                        0,
                        true,
                        settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT);
            } else if (left instanceof PsiComment || right instanceof PsiComment) {
                // Keep comments
                return Spacing.createSpacing(1, 1, 0, true, settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT);
            } else if (PlSqlElementTypes.PKG_LEVEL.contains(left.getNode().getElementType())
                    || PlSqlElementTypes.PKG_LEVEL.contains(right.getNode().getElementType())) {
                // Keep blank lines ahead and behind
                return Spacing.createSpacing(1, 1, 0, true, settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT);
            }
        } else if (parent instanceof DeclarationList ) {
            return Spacing.createSpacing(1, 1,
                    0,
                    true,
                    settings.MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT);
        } else if (parentType == PlSqlElementTypes.EXCEPTION_PRAGMA) {
            if (leftType == PlSqlTokenTypes.OPEN_PAREN || rightType == PlSqlTokenTypes.CLOSE_PAREN) {
                return SpacingConstants.NO_SPACING;
            }
        } else if (parentType == PlSqlElementTypes.EXCEPTION_HANDLER) {
            if (leftType == PlSqlTokenTypes.KEYWORD_WHEN || rightType == PlSqlTokenTypes.KEYWORD_THEN) {
                return SpacingConstants.ONE_SPACE_WITHOUT_NEWLINE;
            }
        } else if (parentType == PlSqlElementTypes.ARITHMETIC_EXPR) {
            // Check for "-3" case
            if(leftType == PlSqlTokenTypes.MINUS && PsiUtil.prevVisibleSibling(leftNode) == null){
                if(rightType == PlSqlElementTypes.NUMERIC_LITERAL){
                    return SpacingConstants.NO_SPACING;
                }
            }
        }

        return SpacingConstants.ONE_SPACE_WITH_NEWLINE; //COMMON_SPACING;
    }

    private static boolean containedIn(Class[] topLevel, PsiElement psiElement) {
        for (Class clazz : topLevel) {
            if (clazz.isInstance(psiElement)) {
                return true;
            }
        }
        return false;
    }

    private static boolean compose(final PsiElement left, final PsiElement right, Class... elements) {
        boolean leftHit = false;
        for (int i = 0; i < elements.length && !leftHit; i++) {
            if (elements[i].isInstance(left)) {
                leftHit = true;
            }
        }

        if (!leftHit) {
            return false;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].isInstance(right)) {
                return true;
            }
        }
        return false;
    }


    private static boolean compose(final PsiElement left, final PsiElement right, TokenSet elements) {
        return elements.contains(left.getNode().getElementType())
                && elements.contains(right.getNode().getElementType());

    }
}
