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

package com.deepsky.lang.plsql.formatter2;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

public class PlSqlWrapProcessor {

    /**
     * Calculates wrap, based on code style, between parent block and child node
     *
     * @param parent parent block
     * @param child  child node
     * @return wrap
     */
    public static Wrap getChildWrap(PlSqlBlock parent, ASTNode child) {
        ASTNode astParent = parent.getNode();
        final PsiElement psiParent = astParent.getPsi();

        // For PL/SQL file
        if (psiParent instanceof PlSqlFile) {
            return Wrap.createWrap(WrapType.NONE, false);
        }

        // Table vs Column Definition, Constraint, CloseParenthesis, StorageSpec
        if (psiParent instanceof TableDefinition) {
            if (child.getElementType() == PlSqlElementTypes.COLUMN_DEF ||
                    PlSqlElementTypes.CONSTRAINTS.contains(child.getElementType())) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (child.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (child.getElementType() == PlSqlElementTypes.STORAGE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (child.getElementType() == PlSqlElementTypes.PARALLEL_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (child.getElementType() == PlSqlElementTypes.MONITORING_CLAUSE) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (PlSqlElementTypes.TABLE_ORGANIZATION.contains(child.getElementType())) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        // CONSTRAINT PRIMARY KEY .. USING ... STORAGE
        if (PlSqlElementTypes.CONSTRAINTS.contains(astParent.getElementType())) { //astParent.getElementType() == PlSqlElementTypes.PK_SPEC){
            if (child.getElementType() == PlSqlElementTypes.STORAGE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        // STORAGE ( ... )
        if (astParent.getElementType() == PlSqlElementTypes.STORAGE_SPEC) {
            if (child.getElementType() == PlSqlElementTypes.STORAGE_PARAM) {
                if (PsiUtil.findTreePrevForType(child, PlSqlElementTypes.STORAGE_PARAM) == null) {
                    // First Storage param element
                    return Wrap.createWrap(WrapType.ALWAYS, true);
                } else {
                    return Wrap.createWrap(WrapType.NORMAL, false);
                }
            } else if (child.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }

        if (astParent.getElementType() == PlSqlElementTypes.EXTERNAL_TYPE) {
            if (child.getElementType() == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        if (astParent.getElementType() == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
            if (child.getElementType() == PlSqlElementTypes.LOADER_ACCESS_PARAMS
                    || child.getElementType() == PlSqlElementTypes.WRITE_ACCESS_PARAMS_SPEC) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        if (child.getElementType() == PlSqlElementTypes.EXT_FIELD_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        if (child.getElementType() == PlSqlElementTypes.RECORD_FMT_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (child.getElementType() == PlSqlElementTypes.PARALLEL_CLAUSE) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (child.getElementType() == PlSqlElementTypes.REJECT_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (child.getElementType() == PlSqlElementTypes.EXT_TABLE_LOCATION_SPEC) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        if (PlSqlElementTypes.DB_OBJECTS_MAYBE_NESTED.contains(child.getElementType())) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

/*
        if(child.getElementType() == PlSqlElementTypes.DECLARE_LIST){
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
*/
        if (astParent.getElementType() == PlSqlElementTypes.PLSQL_BLOCK) {
            if (child.getElementType() == PlSqlTokenTypes.KEYWORD_BEGIN) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            } else if (child.getElementType() == PlSqlElementTypes.PLSQL_BLOCK_END) {
                return Wrap.createWrap(WrapType.ALWAYS, true);
            }
        }
        if (child.getElementType() == PlSqlElementTypes.VARIABLE_DECLARATION) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

        if (PlSqlElementTypes.PLSQL_STATEMENTS.contains(child.getElementType())) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

//        if(child.getElementType() == PlSqlElementTypes.STATEMENT_LIST){
//            return Wrap.createWrap(WrapType.ALWAYS, true);
//        }

        // SELECT_EXPRESSION, SELECT_EXPRESSION_UNION
//        if(PlSqlElementTypes.SUBQUERY_SELECTS.contains(child.getElementType())){
//            return Wrap.createWrap(WrapType.ALWAYS, true);
//        }
        return null; //Wrap.createWrap(WrapType.NORMAL, false);

    }
}
