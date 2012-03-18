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

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.psi.ddl.CreateUser;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class PlSqlIndentProcessor {

    final static TokenSet CONSTRAINT_START = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_CONSTRAINT,
            PlSqlTokenTypes.KEYWORD_FOREIGN,
            PlSqlTokenTypes.KEYWORD_CHECK,
            PlSqlTokenTypes.KEYWORD_UNIQUE,
            PlSqlTokenTypes.KEYWORD_PRIMARY);


    /**
     * Calculates indent, based on code style, between parent block and child node
     *
     * @param parent        parent block
     * @param child         child node
     * @param plSqlSettings
     * @return indent
     */
    @NotNull
    public static Indent getChildIndent(@NotNull final com.deepsky.lang.plsql.formatter.PlSqlBlock parent,
                                        @NotNull final ASTNode child,
                                        final PlSqlCodeStyleSettings plSqlSettings) {
        final ASTNode astParent = parent.getNode();
        final PsiElement psiParent = astParent.getPsi();
        final IElementType childType = child.getElementType();
        final IElementType parentType = astParent.getElementType();

        // For Pl/Sql file
        if (psiParent instanceof PlSqlFile) {
            return Indent.getNoneIndent();
        }

        // For arguments
        if (psiParent instanceof CallArgumentList) {
            return Indent.getIndent(Indent.Type.NORMAL, false, true);
        }

        if (psiParent instanceof CreateSequence || psiParent instanceof CreateUser) {
            if (childType == PlSqlTokenTypes.KEYWORD_CREATE || childType == PlSqlTokenTypes.DIVIDE) {
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent();
            }
        }

        // CREATE TABLE/INDEX
        if (psiParent instanceof TableDefinition) {
            if (childType == PlSqlElementTypes.COLUMN_DEF
                    || PlSqlElementTypes.TABLE_LEVEL_CONSTRAINTS.contains(childType)) {
                return Indent.getNormalIndent(false);
            } else if (childType == PlSqlElementTypes.STORAGE_SPEC) {
                return Indent.getNoneIndent();
            } else if (childType == PlSqlTokenTypes.COMMA) {
                return Indent.getNormalIndent(false);
            }
        }

        // ALTER TABLE/INDEX
        if (psiParent instanceof AlterTable) {
            if (childType == PlSqlElementTypes.A_COLUMN_DEF) {
                return Indent.getNormalIndent(false);
            } else if (childType == PlSqlTokenTypes.COMMA) {
                return Indent.getNormalIndent(false);
            } else if (childType == PlSqlElementTypes.ALTER_TABLE_CONSTRAINT) {
                return Indent.getNormalIndent(false);
            }
        }

        // ALTER TABLE ADD/MODIFY CONSTRAINT ...
        if (parentType == PlSqlElementTypes.ALTER_TABLE_CONSTRAINT) {
            return normalWithoutFirstIndent(child);
        }

        if (PlSqlElementTypes.COLUMN_LEVEL_CONSTRAINTS.contains(astParent.getElementType())) {
            return normalWithoutFirstIndent(child);
        }

        if (PlSqlElementTypes.TABLE_LEVEL_CONSTRAINTS.contains(parentType)) {
            return normalWithoutFirstIndent(child);
        }

        if (parentType == PlSqlElementTypes.COLUMN_DEF) {
            if (PsiUtil.prevVisibleSibling(child) == null) {
                return Indent.getNoneIndent();
            }
            return Indent.getNormalIndent(false);
        }

        if (parentType == PlSqlElementTypes.DATATYPE) {
            if (PsiUtil.prevVisibleSibling(child) == null) {
                return Indent.getNoneIndent();
            }
            return Indent.getNormalIndent(false);
        }

        // STORAGE ( ... )
        if (parentType == PlSqlElementTypes.STORAGE_SPEC) {
            if (childType == PlSqlElementTypes.STORAGE_PARAM) {
                return normalWithoutFirstIndent(child, true);
            }
        } else if (parentType == PlSqlElementTypes.EXTERNAL_TYPE) {
            if (childType == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
                return Indent.getNormalIndent(true);
            }
        }

        if (parentType == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
            if (childType == PlSqlElementTypes.LOADER_ACCESS_PARAMS
                    || childType == PlSqlElementTypes.WRITE_ACCESS_PARAMS_SPEC) {
                return Indent.getNormalIndent(true);
            }
        }

        // SQLPLUS commands
        if (psiParent instanceof SqlPlusCommand) {
            return Indent.getNoneIndent();
        }

        // PACKAGE BODY & SPECIFICATION
        if (psiParent instanceof PackageBody || psiParent instanceof PackageSpec) {
            if (PlSqlTokenTypes.PACKAGE_LEVEL_WORDS.contains(childType)
                    || childType == PlSqlElementTypes.PACKAGE_NAME
                    || childType == PlSqlTokenTypes.SEMI
                    || childType == PlSqlTokenTypes.DIVIDE) {
                return Indent.getNoneIndent();
            } else if (child.getPsi() instanceof PsiComment) {
                if (plSqlSettings.COMMENT_AT_FIRST_COLUMN) {
                    return Indent.getAbsoluteNoneIndent();
                }
            }
            return Indent.getNormalIndent(true);
        }

        // TYPE DECLARATION
        if (psiParent instanceof ObjectTypeDecl) {
            if (childType == PlSqlTokenTypes.OPEN_PAREN
                    || childType == PlSqlTokenTypes.CLOSE_PAREN
                    || childType == PlSqlTokenTypes.DIVIDE) {
                return Indent.getNoneIndent();
            } else {
                return normalWithoutFirstIndent(child);
            }
        } else if (psiParent instanceof Declaration) {
            return normalWithoutFirstIndent(child);
        }

        if (parentType == PlSqlElementTypes.IMMEDIATE_COMMAND) {
            return normalWithoutFirstIndent(child, true);
        }

        if (psiParent instanceof DeclarationList) {
            if (childType == PlSqlElementTypes.VARIABLE_DECLARATION
                    || childType == PlSqlElementTypes.CURSOR_DECLARATION
                    || childType == PlSqlElementTypes.AUTONOMOUS_TRN_PRAGMA
                    || childType == PlSqlElementTypes.EXCEPTION_PRAGMA
                    || child.getPsi() instanceof TypeDeclaration) {
                return Indent.getNormalIndent(false);
            }
        }

        if (parentType == PlSqlElementTypes.PLSQL_BLOCK) {
            if (childType == PlSqlElementTypes.STATEMENT_LIST) {
                if (((PlSqlBlock) psiParent).getDeclarations().length > 0) {
//                    return Indent.getNoneIndent();
//                    return Indent.getNormalIndent(false);
//                    return Indent.getIndent(Indent.Type.NONE, 4, true, true);
                    final IElementType granpa = child.getTreeParent().getTreeParent().getElementType();
                    if (granpa == PlSqlElementTypes.PROCEDURE_BODY || granpa == PlSqlElementTypes.FUNCTION_BODY) {
                        return Indent.getIndent(Indent.Type.NONE, plSqlSettings.TAB_SIZE, true, true);
                    }
                    return Indent.getSpaceIndent(plSqlSettings.TAB_SIZE, true);
//                    return Indent.getNormalIndent(true);
                } else {
                    return Indent.getNormalIndent(false);
//                    return Indent.getIndent(Indent.Type.NORMAL, 4, true, true);
                }
            } else if (child.getPsi() instanceof PsiComment) {
                if (plSqlSettings.COMMENT_AT_FIRST_COLUMN) {
                    return Indent.getAbsoluteNoneIndent();
                } else {
                    return Indent.getNormalIndent(false);
                }
//
//                return Indent.getNormalIndent(true);
            }
            return Indent.getNoneIndent();
        }

        if (parentType == PlSqlElementTypes.STATEMENT_LIST) {
            if (PlSqlElementTypes.PLSQL_STATEMENTS.contains(childType)
                    || PlSqlElementTypes.DML_STATEMENTS.contains(childType)
                    || PlSqlElementTypes.SUBQUERY_SELECTS.contains(childType)) {
                return Indent.getNoneIndent();
            } else if (child.getPsi() instanceof PsiComment) {
                if (plSqlSettings.COMMENT_AT_FIRST_COLUMN) {
                    return Indent.getAbsoluteNoneIndent();
                } else {
                    return Indent.getNoneIndent();
                }
            } else if (childType == PlSqlTokenTypes.COMMA) {
                return Indent.getNormalIndent(false);
            }
        }

        if (parentType == PlSqlElementTypes.IF_STATEMENT || parentType == PlSqlElementTypes.ELSIF_STATEMENT) {
            if (PlSqlElementTypes.CONDITION_EXPR_TYPES.contains(childType)) {
                return Indent.getIndent(Indent.Type.NORMAL, true, true);
            } else if (childType == PlSqlElementTypes.STATEMENT_LIST) {
                return Indent.getNormalIndent(false);
            }
        }
        if (parentType == PlSqlElementTypes.ELSE_STATEMENT) {
            if (childType == PlSqlElementTypes.STATEMENT_LIST) {
                return Indent.getNormalIndent(false);
            }
        }

        if (parentType == PlSqlElementTypes.ASSIGNMENT_STATEMENT) {
            return normalWithoutFirstIndent(child);
        }

        if (parentType == PlSqlElementTypes.LOOP_STATEMENT) {
            if (childType == PlSqlTokenTypes.KEYWORD_IN
                    || childType == PlSqlElementTypes.CURSOR_LOOP_INDEX) {
                return Indent.getNormalIndent(false);
            } else if (childType == PlSqlElementTypes.STATEMENT_LIST) {
                return Indent.getNormalIndent(false);
            }
        }

        if (psiParent instanceof InsertStatement) {
            if (childType == PlSqlTokenTypes.KEYWORD_INSERT || childType == PlSqlTokenTypes.KEYWORD_VALUES) {
                return Indent.getNoneIndent();
            } else if (child.getPsi() instanceof SelectStatement) {
                // SELECT_EXPRESSION, SELECT_EXPRESSION_UNION
                return Indent.getNoneIndent();
            }
            return Indent.getNormalIndent(false);
        }

        if (parentType == PlSqlElementTypes.OPEN_STATEMENT) {
            if (childType == PlSqlElementTypes.SELECT_EXPRESSION ||
                    childType == PlSqlElementTypes.SELECT_EXPRESSION_UNION) {
                return Indent.getContinuationIndent();
            } else {
                return normalWithoutFirstIndent(child);
            }
        }

        if (parentType == PlSqlElementTypes.SUBQUERY) {
            final PsiElement granpa = psiParent.getParent();
            if (granpa instanceof PlSqlFile) {
                return Indent.getNoneIndent();
            } else if (granpa instanceof FromSubquery) {
                return Indent.getNormalIndent(false);
//                return Indent.getIndent(Indent.Type.NORMAL, true, true);
            }
            return Indent.getContinuationIndent();
        }

        // SELECT statement's children
        if (psiParent instanceof SelectStatement) {
            if (childType == PlSqlElementTypes.EXPR_COLUMN) {
                // todo --return Indent.getNormalIndent(true);

                // Enforce Indent to Children!!!!
//                return Indent.getIndent(Indent.Type.NORMAL, false, false);
            } else if (childType == PlSqlTokenTypes.COMMA) {
                return Indent.getNormalIndent(true);
            } else if (child.getPsi() instanceof PsiComment) {
                if (plSqlSettings.COMMENT_AT_FIRST_COLUMN) {
                    return Indent.getAbsoluteNoneIndent();
                } else {
                    return Indent.getNormalIndent(false);
                }
            }
            return Indent.getIndent(Indent.Type.NONE, true, false);
        }

        if (parentType == PlSqlElementTypes.EXPR_COLUMN) {
//            return Indent.getIndent(Indent.Type.NORMAL, false, true);
            return Indent.getIndent(Indent.Type.NORMAL, false, false);
        }


/*
        if(parentType == PlSqlElementTypes.AT_TIME_ZONE_EXPR){
            return normalWithoutFirstIndent(child);
        }
*/

        if (psiParent instanceof RelationCondition) {
            return normalWithoutFirstIndent(child);
        }

        if (psiParent instanceof GroupByClause) {
            if (childType == PlSqlTokenTypes.KEYWORD_GROUP) {
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }
        if (psiParent instanceof OrderByClause) {
            if (childType == PlSqlTokenTypes.KEYWORD_ORDER) {
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }
        if (parentType == PlSqlElementTypes.SORTED_DEF) {
            if (PlSqlTokenTypes.SORTED_DEF_TOKENS.contains(childType)) {
                return Indent.getNormalIndent(true);
            } else {
                return Indent.getNoneIndent();
            }
        }

        if (parentType == PlSqlElementTypes.INTO_CLAUSE) {
            if (childType == PlSqlTokenTypes.KEYWORD_INTO) {
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent();
            }
        }

        if (psiParent instanceof FromClause) {
            if (childType == PlSqlElementTypes.TABLE_ALIAS) {
                return Indent.getNormalIndent(false);
            }
        }

        if (parentType == PlSqlElementTypes.WHERE_CONDITION) {
            if (childType == PlSqlTokenTypes.KEYWORD_WHERE) {
                return Indent.getNoneIndent();
            } else {
                // Enforce Indent to Children!!!!
                return Indent.getIndent(Indent.Type.NORMAL, true, true);
            }
        }
/*
        if(parentType == PlSqlElementTypes.LOGICAL_EXPR){
            if(psiParent.getParent() instanceof LogicalExpression){
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }
*/

        if (parentType == PlSqlElementTypes.SIMPLE_UPDATE_COMMAND
                || parentType == PlSqlElementTypes.SUBQUERY_UPDATE_COMMAND) {
            if (childType == PlSqlTokenTypes.KEYWORD_UPDATE
                    || childType == PlSqlTokenTypes.KEYWORD_SET
                    || childType == PlSqlElementTypes.WHERE_CONDITION
                    || childType == PlSqlElementTypes.RETURNING_CLAUSE
                    ) {
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }

        if (parentType == PlSqlElementTypes.VAR_REF) {
            if (childType == PlSqlTokenTypes.DOT) {
                return Indent.getNoneIndent();
            }
        }

        if (childType == PlSqlTokenTypes.SEMI) {
            if (parentType == PlSqlElementTypes.PLSQL_BLOCK
                    || parentType == PlSqlElementTypes.TRUNCATE_TABLE
                    || parentType == PlSqlElementTypes.COMMENT
                    || parentType == PlSqlElementTypes.VARIABLE_DECLARATION
                    || parentType == PlSqlElementTypes.AUTONOMOUS_TRN_PRAGMA
                    || parentType == PlSqlElementTypes.TRUNCATE_TABLE
//                    || parentType == PlSqlElementTypes.STATEMENT_LIST
                    || parentType == PlSqlElementTypes.TABLE_DEF
                // todo -- lots of drop commands
                    ) {
                return Indent.getNoneIndent();
            }
        }

        // todo -- using of in grammar should be revised SEMI
        if (childType == PlSqlTokenTypes.SEMI ||
                childType == PlSqlTokenTypes.DOT
                || childType == PlSqlTokenTypes.COMMA) {
            return Indent.getNormalIndent(false);
        }

        if (childType == PlSqlElementTypes.PLSQL_BLOCK) {
            return Indent.getNoneIndent();
        }

        if (PlSqlTokenTypes.COMMENTS2.contains(childType)) {
            if (plSqlSettings.COMMENT_AT_FIRST_COLUMN) {
                return Indent.getAbsoluteNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }

        if (childType == PlSqlElementTypes.EXT_FIELD_SPEC) {
            return Indent.getNormalIndent(false);
        }

        if (childType == PlSqlElementTypes.PARAMETER_SPEC) {
            return Indent.getNormalIndent(false);
        }


        //return Indent.getNoneIndent();
        return Indent.getIndent(Indent.Type.NONE, false, false);
    }

    private static Indent normalWithoutFirstIndent(ASTNode node) {
        if (PsiUtil.prevVisibleSibling(node) == null) {
            return Indent.getNoneIndent();
        } else {
            return Indent.getNormalIndent(false);
        }
    }

    private static Indent normalWithoutFirstIndent(ASTNode node, boolean enforceIndentToChildren) {
        if (PsiUtil.prevVisibleSibling(node) == null) {
            return Indent.getNoneIndent();
        } else {
            return Indent.getIndent(Indent.Type.NORMAL, false, enforceIndentToChildren);
        }
    }
}
