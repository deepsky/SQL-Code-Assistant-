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
import com.deepsky.lang.plsql.formatter.PlSqlBlock;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

import java.util.List;

public class PlSqlWrapProcessor {

    /**
     * Calculates wrap, based on code style, between parent block and child node
     *
     * @param parent   parent block
     * @param child    child node
     * @param settings
     * @return wrap
     */
    public static Wrap getChildWrap(PlSqlBlock parent, ASTNode child, PlSqlCodeStyleSettings settings) {
        ASTNode astParent = parent.getNode();
        final PsiElement psiParent = astParent.getPsi();
        final IElementType childType = child.getElementType();
        final IElementType parentType = astParent.getElementType();

        // For PL/SQL file
        if (psiParent instanceof PlSqlFile) {
            if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }

            return Wrap.createWrap(WrapType.NONE, false);
        }

        // Table vs Column Definition, Constraint, CloseParenthesis, StorageSpec
        if (psiParent instanceof TableDefinition) {
            if (childType == PlSqlElementTypes.COLUMN_DEF ||
                    PlSqlElementTypes.TABLE_LEVEL_CONSTRAINTS.contains(childType)) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.OPEN_PAREN) {
                return settings.WRAP_OPEN_PAREN_IN_CREATE_TABLE ?
                        Wrap.createWrap(WrapType.ALWAYS, true) : null;
            } else if (childType == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.STORAGE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.PARALLEL_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.MONITORING_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.KEYWORD_AS) {
                final List<ASTNode> list = PsiUtil.nextVisibleSiblings(child);
                if (list.size() > 0) {
                    if (list.get(0).getElementType() == PlSqlElementTypes.SELECT_EXPRESSION
                            || list.get(0).getElementType() == PlSqlElementTypes.SELECT_EXPRESSION_UNION) {
                        return Wrap.createWrap(WrapType.ALWAYS, true);
                    }
                }
            }
        }

        if (psiParent instanceof ObjectTypeDecl) {
            if (childType == PlSqlTokenTypes.OPEN_PAREN) {
                return settings.WRAP_OPEN_PAREN_IN_CREATE_TABLE ?
                        Wrap.createWrap(WrapType.ALWAYS, true) : null;
            } else if (childType == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.RECORD_ITEM) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (PlSqlElementTypes.TABLE_ORGANIZATION.contains(childType)) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        // CONSTRAINT PRIMARY KEY .. USING ... STORAGE
        if (PlSqlElementTypes.TABLE_LEVEL_CONSTRAINTS.contains(parentType)) {
            if (childType == PlSqlElementTypes.STORAGE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        // STORAGE ( ... )
        if (parentType == PlSqlElementTypes.STORAGE_SPEC) {
            if (childType == PlSqlElementTypes.STORAGE_PARAM) {
                if (PsiUtil.findTreePrevForType(child, PlSqlElementTypes.STORAGE_PARAM) == null) {
                    // First Storage param element
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                } else {
                    return Wrap.createWrap(WrapType.NORMAL, false);
                }
            } else if (childType == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (parentType == PlSqlElementTypes.EXTERNAL_TYPE) {
            if (childType == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        if (parentType == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
            if (childType == PlSqlElementTypes.LOADER_ACCESS_PARAMS
                    || childType == PlSqlElementTypes.WRITE_ACCESS_PARAMS_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        if (childType == PlSqlElementTypes.EXT_FIELD_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        if (childType == PlSqlElementTypes.RECORD_FMT_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (childType == PlSqlElementTypes.PARALLEL_CLAUSE) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (childType == PlSqlElementTypes.REJECT_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (childType == PlSqlElementTypes.EXT_TABLE_LOCATION_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        // AlterTable vs Column Definition, Constraint, CloseParenthesis
        if (psiParent instanceof AlterTable) {
            if (childType == PlSqlElementTypes.A_COLUMN_DEF) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.OPEN_PAREN) {
                return settings.WRAP_OPEN_PAREN_IN_CREATE_TABLE ?
                        Wrap.createWrap(WrapType.ALWAYS, true) : null;
            } else if (childType == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.ALTER_TABLE_CONSTRAINT) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (PlSqlElementTypes.DB_OBJECTS_MAYBE_NESTED.contains(childType)) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        if (parentType == PlSqlElementTypes.PLSQL_BLOCK) {
            if (childType == PlSqlTokenTypes.KEYWORD_BEGIN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.KEYWORD_DECLARE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.DECLARE_LIST) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.PLSQL_BLOCK_END) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.EXCEPTION_SECTION) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (parentType == PlSqlElementTypes.EXCEPTION_SECTION) {
            if (childType == PlSqlElementTypes.EXCEPTION_HANDLER) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (childType == PlSqlElementTypes.VARIABLE_DECLARATION
                || child.getPsi() instanceof TypeDeclaration
                || child.getPsi() instanceof CursorDecl) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        // SELECT_EXPRESSION, SELECT_EXPRESSION_UNION
        if (PlSqlElementTypes.SUBQUERY_SELECTS.contains(childType)) {
            if (psiParent instanceof InsertStatement) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

/*
        if (childType == PlSqlElementTypes.ASSIGNMENT_STATEMENT
                || childType == PlSqlElementTypes.IF_STATEMENT
                || childType == PlSqlElementTypes.PROCEDURE_CALL) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
*/

        if (parentType == PlSqlElementTypes.STATEMENT_LIST) {
            if (PlSqlElementTypes.PLSQL_STATEMENTS.contains(childType)) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (PlSqlElementTypes.DML_STATEMENTS.contains(childType)) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (PlSqlElementTypes.SUBQUERY_SELECTS.contains(childType)) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (psiParent instanceof SelectStatementUnion) {
            if (child.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION) {
                if (PsiUtil.prevVisibleSibling(child) != null) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                }
            } else if (child.getElementType() == PlSqlTokenTypes.KEYWORD_UNION
                    || child.getElementType() == PlSqlTokenTypes.KEYWORD_MINUS
                    || child.getElementType() == PlSqlTokenTypes.KEYWORD_INTERSECT) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (psiParent instanceof SelectStatement) {
            if (settings.DONT_WRAP_SELECT_IF_SIMPLE && isSelectTrivial((SelectStatement) psiParent)) {
                return null;
            }
            if (childType == PlSqlElementTypes.WHERE_CONDITION) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (childType == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (childType == PlSqlElementTypes.CONNECT_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }

            if (childType == PlSqlElementTypes.GROUP_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (childType == PlSqlElementTypes.ORDER_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
            if (childType == PlSqlElementTypes.FOR_UPDATE_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }

            if (childType == PlSqlElementTypes.EXPR_COLUMN) {
                SelectStatement select = (SelectStatement) psiParent;
                if (select.getSelectFieldList().length > 3 && settings.COMMA_AFTER_SELECT_EXPR == 2) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                } else if (select.getSelectFieldList().length > 3 && settings.COMMA_AFTER_SELECT_EXPR == 3) {
                    // Wrap the first expr_column
                    if (select.getSelectFieldList()[0] == child.getPsi()) {
                        return Wrap.createWrap(WrapType.ALWAYS, true);
                    }
                }
            }
            if (childType == PlSqlTokenTypes.COMMA) {
                SelectStatement select = (SelectStatement) psiParent;
                if (select.getSelectFieldList().length > 3 && settings.COMMA_AFTER_SELECT_EXPR == 3) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                }
            }
        }

        if (childType == PlSqlElementTypes.TABLE_ALIAS) {
            if (psiParent instanceof FromClause) {
                FromClause from = (FromClause) psiParent;
                if (from.getTableList().length > 3) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                }
            }
        }

        if (childType == PlSqlElementTypes.ANSI_JOIN_TAB_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

/*
        if (childType == PlSqlElementTypes.FROM_SUBQUERY) {
            if (psiParent instanceof FromClause) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
*/

        if (parentType == PlSqlElementTypes.FUNCTION_BODY
                || parentType == PlSqlElementTypes.PROCEDURE_BODY) {
            if (childType == PlSqlTokenTypes.KEYWORD_IS || childType == PlSqlTokenTypes.KEYWORD_AS) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (parentType == PlSqlElementTypes.PACKAGE_BODY
                || parentType == PlSqlElementTypes.PACKAGE_SPEC) {
            if (childType == PlSqlTokenTypes.KEYWORD_IS
                    || childType == PlSqlTokenTypes.KEYWORD_AS
                    || childType == PlSqlTokenTypes.KEYWORD_END
                    || childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else {
                // Wrap any declaration or comment if it follows immediately after AS
                ASTNode prev = PsiUtil.prevVisibleSibling(child);
                if (prev != null && prev.getElementType() == PlSqlTokenTypes.KEYWORD_AS) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                }
            }
        }

        if (parentType == PlSqlElementTypes.CREATE_TRIGGER) {
            if ((childType == PlSqlTokenTypes.KEYWORD_BEFORE
                    || childType == PlSqlTokenTypes.KEYWORD_AFTER
                    || childType == PlSqlTokenTypes.KEYWORD_INSTEAD)
                    && PsiUtil.prevVisibleSibling(child).getElementType() == PlSqlElementTypes.TRIGGER_NAME) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (parentType == PlSqlElementTypes.CREATE_VIEW) {
            if (childType == PlSqlTokenTypes.KEYWORD_AS) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlElementTypes.SELECT_EXPRESSION
                    || childType == PlSqlElementTypes.SELECT_EXPRESSION_UNION) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
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
        if (parentType == PlSqlElementTypes.CREATE_SEQUENCE) {
            if (settings.WRAP_SEQUENCE_OPTIONS) {
                if (childType == PlSqlTokenTypes.KEYWORD_START
                        || childType == PlSqlTokenTypes.KEYWORD_INCREMENT
                        || childType == PlSqlTokenTypes.KEYWORD_MINVALUE
                        || childType == PlSqlTokenTypes.KEYWORD_MAXVALUE
                        || childType == PlSqlTokenTypes.KEYWORD_CYCLE
                        || childType == PlSqlTokenTypes.KEYWORD_NOCYCLE
                        || childType == PlSqlTokenTypes.KEYWORD_CACHE
                        || childType == PlSqlTokenTypes.KEYWORD_NOCACHE
                        || childType == PlSqlTokenTypes.KEYWORD_ORDER
                        || childType == PlSqlTokenTypes.KEYWORD_NOORDER) {
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                }
            }

            if (childType == PlSqlTokenTypes.DIVIDE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
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
        if (parentType == PlSqlElementTypes.CREATE_USER
                && settings.WRAP_USER_OPTIONS) {
            if (childType == PlSqlTokenTypes.KEYWORD_IDENTIFIED
                    || childType == PlSqlTokenTypes.KEYWORD_DEFAULT
                    || childType == PlSqlTokenTypes.KEYWORD_QUOTA
                    || childType == PlSqlTokenTypes.KEYWORD_TEMPORARY
                    || childType == PlSqlTokenTypes.KEYWORD_PROFILE
                    || childType == PlSqlTokenTypes.KEYWORD_PASSWORD) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        // SELECT_EXPRESSION, SELECT_EXPRESSION_UNION
//        if(PlSqlElementTypes.SUBQUERY_SELECTS.contains(childType)){
//            return Wrap.createWrap(WrapType.ALWAYS, true);
//        }
        return null; //Wrap.createWrap(WrapType.NORMAL, false);

    }


    private static final Key<Boolean> SELECT_IS_SIMPLE = Key.create("SELECT_IS_SIMPLE");

    /**
     * Check whether select is simple. Examples of select
     * select * from tab1
     * select ab1, ad1 from meta_table
     * select * from tab1 order by id
     * select to_char(sysdate - 4, 'YYMM') from dual
     *
     * @param select
     * @return
     */
    private static boolean isSelectTrivial(final SelectStatement select) {
        if (select instanceof SelectStatementUnion) {
            return false;
        }

        Boolean isSimple = select.getUserData(SELECT_IS_SIMPLE);
        if (isSimple != null) {
            return isSimple;
        }

        int sizeOfSelect = 0;
        int keywordsSize = "select".length();
        for (SelectFieldCommon field : select.getSelectFieldList()) {
            sizeOfSelect += field.getTextLength();
        }

        int fromSize = select.getFromClause().getTextLength();
        sizeOfSelect += (fromSize - "from".length());
        keywordsSize += "from".length();
        float multiplifier = 3.f;


        if (sizeOfSelect > keywordsSize * multiplifier) {
            select.putUserData(SELECT_IS_SIMPLE, false);
            return false;
        }

        PsiElement into = select.getIntoClause();
        if (into != null) {
            multiplifier -= 0.5;
            sizeOfSelect += (into.getTextLength() - "into".length());
            keywordsSize += "into".length();
            if (sizeOfSelect > keywordsSize * multiplifier) {
                select.putUserData(SELECT_IS_SIMPLE, false);
                return false;
            }
        }

        WhereCondition where = select.getWhereCondition();
        if (where != null) {
            multiplifier -= 0.7;
            sizeOfSelect += (where.getTextLength() - "where".length());
            keywordsSize += "where".length();
            if (sizeOfSelect > keywordsSize * multiplifier) {
                select.putUserData(SELECT_IS_SIMPLE, false);
                return false;
            }
        }

        OrderByClause orderBy = select.getOrderByClause();
        if (orderBy != null) {
            multiplifier -= 0.5;
            sizeOfSelect += (orderBy.getTextLength() - "order by".length());
            keywordsSize += "order by".length();
            if (sizeOfSelect > keywordsSize * multiplifier) {
                select.putUserData(SELECT_IS_SIMPLE, false);
                return false;
            }
        }

        GroupByClause groupBy = select.getGroupByClause();
        if (groupBy != null) {
            multiplifier -= 0.5;
            sizeOfSelect += (groupBy.getTextLength() - "group by".length());
            keywordsSize += "group by".length();
            if (sizeOfSelect > keywordsSize * multiplifier) {
                select.putUserData(SELECT_IS_SIMPLE, false);
                return false;
            }
        }

        select.putUserData(SELECT_IS_SIMPLE, true);
        return true;
    }
}
