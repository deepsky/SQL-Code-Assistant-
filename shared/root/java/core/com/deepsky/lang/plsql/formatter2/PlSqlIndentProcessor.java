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
import com.deepsky.lang.plsql.psi.CallArgumentList;
import com.deepsky.lang.plsql.psi.PackageBody;
import com.deepsky.lang.plsql.psi.PackageSpec;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
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
     * @param parent parent block
     * @param child  child node
     * @return indent
     */
    @NotNull
    public static Indent getChildIndent(@NotNull final PlSqlBlock parent, @NotNull final ASTNode child) {
        ASTNode astParent = parent.getNode();
        final PsiElement psiParent = astParent.getPsi();

        // For Pl/Sql file
        if (psiParent instanceof PlSqlFile) {
            return Indent.getNoneIndent();
        }

        // For arguments
        if (psiParent instanceof CallArgumentList) {
            if (child.getElementType() != PlSqlTokenTypes.OPEN_PAREN &&
                    child.getElementType() != PlSqlTokenTypes.CLOSE_PAREN) {
                return Indent.getContinuationIndent();
            }
        }

        if (psiParent instanceof TableDefinition) {
            if (child.getElementType() == PlSqlElementTypes.COLUMN_DEF
                    || PlSqlElementTypes.CONSTRAINTS.contains(child.getElementType())) {
                return Indent.getNormalIndent(true);
            } else if (child.getElementType() == PlSqlElementTypes.STORAGE_SPEC) {
                return Indent.getNoneIndent();
            }
        }


/*
        if(PlSqlElementTypes.CONSTRAINTS.contains(astParent.getElementType())){
            if( PsiUtil.findTreePrevForTypes(child, CONSTRAINT_START) == null ){
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent();
            }
        }
*/
        if (PlSqlElementTypes.CONSTRAINTS.contains(astParent.getElementType())) {
            return Indent.getContinuationWithoutFirstIndent(true);
        }

        if(astParent.getElementType() == PlSqlElementTypes.COLUMN_DEF){
            return Indent.getContinuationWithoutFirstIndent(true);
        }

        // STORAGE ( ... )
        if (astParent.getElementType() == PlSqlElementTypes.STORAGE_SPEC) {
            if (child.getElementType() == PlSqlElementTypes.STORAGE_PARAM) {
                if (PsiUtil.findTreePrevForType(child, PlSqlElementTypes.STORAGE_PARAM) == null) {
                    // First Storage param element
                    return Indent.getNormalIndent(true);
                } else {
                    return Indent.getNormalIndent(true);
//                    return Indent.getContinuationWithoutFirstIndent(); //Indent(false);
                }
            }
        }

        if (PlSqlTokenTypes.COMMENTS2.contains(child.getElementType())) {
            return Indent.getNormalIndent(true);
        }

        if (astParent.getElementType() == PlSqlElementTypes.EXTERNAL_TYPE) {
            if (child.getElementType() == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
                return Indent.getNormalIndent(true);
            }
        }

        if (child.getElementType() == PlSqlElementTypes.EXT_FIELD_SPEC) {
            return Indent.getNormalIndent(false);
        }

        if (astParent.getElementType() == PlSqlElementTypes.EXTERNAL_TABLE_SPEC) {
            if (child.getElementType() == PlSqlElementTypes.LOADER_ACCESS_PARAMS
                    || child.getElementType() == PlSqlElementTypes.WRITE_ACCESS_PARAMS_SPEC) {
                return Indent.getNormalIndent(true);
            }
        }

        if (psiParent instanceof PackageBody || psiParent instanceof PackageSpec) {
            return Indent.getNormalIndent(true);
        }
        if(child.getElementType() == PlSqlElementTypes.VARIABLE_DECLARATION){
            return Indent.getNormalIndent(false);
        }

        if(child.getElementType() == PlSqlElementTypes.PLSQL_BLOCK){
            if(PlSqlElementTypes.PLSQL_BLOCK_PARENTS.contains(astParent.getElementType())){
                return Indent.getNoneIndent();
            } else {
                return Indent.getNormalIndent(false);
            }
        }

        // SELECT_EXPRESSION, SELECT_EXPRESSION_UNION
        if(PlSqlElementTypes.SUBQUERY_SELECTS.contains(child.getElementType())){
//            if(astParent.getElementType() == PlSqlElementTypes.SUBQUERY){
//                return Indent.getNoneIndent();
//            } else {
                return Indent.getNormalIndent(false);
//            }
        }

        if(PlSqlElementTypes.PLSQL_STATEMENTS.contains(child.getElementType())){
            return Indent.getNormalIndent(false);
        }


/*
        if (psiParent instanceof ColumnDefinition) {
            if(child.getElementType() == PlSqlElementTypes.COLUMN_NAME_DDL){
//                return Indent.getContinuationIndent();
                return Indent.getNormalIndent(true);
            }
        }


*/
        return Indent.getNoneIndent();
    }
}
