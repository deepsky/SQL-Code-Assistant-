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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.formatter.blocks;

import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.deepsky.lang.plsql.formatter.PlSqlNodeVisitor;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.formatter.Util;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

public class SubBlockVisitor extends PlSqlNodeVisitor {

    private static final Logger log = Logger.getInstance("#SubBlockVisitor");

    List<Block> myBlocks = new ArrayList<Block>();
    private final CodeStyleSettings mySettings;

    public SubBlockVisitor(CodeStyleSettings settings) {
        mySettings = settings;
    }

    public List<Block> getBlocks() {
        log.info("#getBlocks, number of blocks: " + myBlocks.size());
        return myBlocks;
    }

    public void visitElement(final ASTNode node) {
        Alignment alignment = getDefaultAlignment(node);

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != PlSqlTokenTypes.WS && child.getElementType() != TokenType.WHITE_SPACE &&
                    child.getTextRange().getLength() > 0) {
                Wrap wrap = getWrap(node, child);
                Alignment childAlignment = alignmentProjection(alignment, node, child);
                Indent childIndent = getIndent(node, child);
                log.info("#visitElement, parent: " + node.getElementType() + ", child: " + child.getElementType()
                        + ", wrap " + wrap + ", align " + alignment + ", indent " + childIndent);

                log.info("Parent and child " + ((node == child) ? "EQUALS" : "NOT EQUALS"));
                myBlocks.add(new PlSqlFBlock(child, childAlignment, childIndent, wrap, mySettings));
            }
            child = child.getTreeNext();
        }
    }


    @Nullable
    static Alignment getDefaultAlignment(final ASTNode node) {
        if (node.getElementType() == PlSqlElementTypes.LOOP_STATEMENT ||
                node.getElementType() == PlSqlElementTypes.ARGUMENT_LIST ||
                node.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION || //COMMAND ||
                node.getElementType() == PlSqlElementTypes.INSERT_COMMAND ||
                node.getElementType() == PlSqlElementTypes.ASSIGNMENT_STATEMENT 
                //node.getElementType() == PlSqlElementTypes.PLSQL_EXPRESSION
                ) {
            return Alignment.createAlignment();
        }

        return null;
    }

    @Nullable
    private Indent getIndent(final ASTNode node, final ASTNode child) {
        final IElementType nodeEType = node.getElementType();
        final IElementType childEType = child.getElementType();

        if (nodeEType == PlSqlTokenTypes.FILE && (
                childEType == PlSqlElementTypes.INSERT_COMMAND
                        || childEType == PlSqlElementTypes.SELECT_EXPRESSION //COMMAND
                        || childEType == PlSqlElementTypes.DELETE_COMMAND
                        || childEType == PlSqlElementTypes.UPDATE_COMMAND)) {
            return Indent.getNoneIndent();
//        } else if (nodeEType == PlSqlElementTypes.INSERT_COMMAND) {
//            if (childEType == PlSqlTokenTypes.KEYWORD && child.getText().equalsIgnoreCase("values")) {
//                return Indent.getNoneIndent();
//            } else if (childEType == PlSqlElementTypes.INSERT_SUBQUERY) {
//                return Indent.getNoneIndent();
//            }
        } else  if (nodeEType == PlSqlElementTypes.SELECT_EXPRESSION && childEType == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM){
            return Indent.getNoneIndent();
        } else if (nodeEType == PlSqlElementTypes.SELECT_EXPRESSION && childEType == PlSqlElementTypes.WHERE_CONDITION) {
            return Indent.getNoneIndent();
        } else if (nodeEType == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM ){
            if( childEType == PlSqlElementTypes.FROM_SUBQUERY || childEType == PlSqlElementTypes.TABLE_ALIAS) { //FROM_PLAIN_TABLE ) {
                return Indent.getNormalIndent();
            }
        }

        final IElementType childElementType = child.getElementType();
        if (childElementType == PlSqlElementTypes.PLSQL_BLOCK) {
            if (//nodeElementType == PlSqlElementTypes.FUNCTION_DECLARATION &&
                    (mySettings.METHOD_BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED ||
                            mySettings.METHOD_BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED2)) {
                return Indent.getNormalIndent();
            }
            if (mySettings.BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED ||
                    mySettings.BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED2) {
                return Indent.getNormalIndent();
            }
            return Indent.getNoneIndent();
        }

        if(nodeEType == PlSqlElementTypes.INSERT_COMMAND && (
                childEType == PlSqlElementTypes.COLUMN_SPEC_LIST || childEType == PlSqlTokenTypes.CLOSE_PAREN)){
            return Indent.getNormalIndent();
        } else if(nodeEType == PlSqlElementTypes.COLUMN_SPEC_LIST && childEType == PlSqlElementTypes.COLUMN_SPEC){
            return Indent.getNormalIndent();
        }
/*
    if (childElementType == PlSqlTokenTypes.CATCH_BLOCK) {
      return Indent.getNoneIndent();
    }

    if (childElementType == PlSqlTokenTypes.CASE_CLAUSE) {
      return mySettings.INDENT_CASE_FROM_SWITCH ? Indent.getNormalIndent() : Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.CASE_CLAUSE) {
      if (PlSqlTokenTypes.STATEMENTS.contains(childElementType)) {
        return Indent.getNormalIndent();
      }
      return Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.SWITCH_STATEMENT && childElementType == JSTokenTypes.RBRACE) {
      return Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.IF_STATEMENT) {
      if (childElementType == JSTokenTypes.ELSE_KEYWORD) {
        return Indent.getNoneIndent();
      }
      if (PlSqlTokenTypes.SOURCE_ELEMENTS.contains(childElementType)) {
        return Indent.getNormalIndent();
      }
    }

    if (nodeElementType == PlSqlTokenTypes.WITH_STATEMENT &&
        PlSqlTokenTypes.SOURCE_ELEMENTS.contains(childElementType)) {
      return Indent.getNormalIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.DOWHILE_STATEMENT && childElementType == JSTokenTypes.WHILE_KEYWORD) {
      return Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.TRY_STATEMENT && childElementType == JSTokenTypes.FINALLY_KEYWORD) {
      return Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.BLOCK ||
        nodeElementType == PlSqlTokenTypes.CLASS ||
        nodeElementType == PlSqlTokenTypes.PACKAGE_STATEMENT
       ) {
      final ASTNode parent = node.getTreeParent();
      if (parent != null && parent.getElementType() == PlSqlTokenTypes.FUNCTION_DECLARATION &&
          mySettings.METHOD_BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED) {
        return Indent.getNoneIndent();
      }
      if (mySettings.BRACE_STYLE == CodeStyleSettings.NEXT_LINE_SHIFTED) {
        return Indent.getNoneIndent();
      }
      if (PlSqlTokenTypes.SOURCE_ELEMENTS.contains(childElementType) ||
          JSTokenTypes.COMMENTS.contains(childElementType)) {
        return Indent.getNormalIndent();
      }
      return Indent.getNoneIndent();
    }
    else if (node.getPsi() instanceof JSLoopStatement) {
      if (child.getPsi() == ((JSLoopStatement)node.getPsi()).getBody()) {
        if (childElementType == PlSqlTokenTypes.BLOCK) {
          return Indent.getNoneIndent();
        } else {
          return Indent.getNormalIndent();
        }
      }
    }

    if (childElementType == PlSqlTokenTypes.OBJECT_LITERAL_EXPRESSION) {
      return nodeElementType == PlSqlTokenTypes.ARRAY_LITERAL_EXPRESSION ? Indent.getNormalIndent():Indent.getNoneIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.ARRAY_LITERAL_EXPRESSION) {
      if (childElementType == JSTokenTypes.LBRACKET ||
          childElementType == JSTokenTypes.RBRACKET) {
        return Indent.getNoneIndent();
      }
      return Indent.getNormalIndent();
    }

    if (nodeElementType == PlSqlTokenTypes.OBJECT_LITERAL_EXPRESSION) {
      if (childElementType == JSTokenTypes.LBRACE ||
          childElementType == JSTokenTypes.RBRACE) {
        return Indent.getNoneIndent();
      }
      return Indent.getNormalIndent();
    }
*/
        return null;
    }

    @Nullable
    private Alignment alignmentProjection(final Alignment defaultAlignment, final ASTNode parent, final ASTNode child) {
        if (parent.getElementType() == PlSqlElementTypes.LOOP_STATEMENT &&
//        (PlSqlElementTypes.EXPRESSIONS.contains(child.getElementType()) ||
                child.getElementType() == PlSqlElementTypes.ASSIGNMENT_STATEMENT) {
            return defaultAlignment;
//        } else if (parent.getElementType() == PlSqlElementTypes.ARGUMENT_LIST &&
//                child.getElementType() == PlSqlElementTypes.ARGUMENT) {
//            return defaultAlignment;
        }
//    else if (parent.getPsi() instanceof JSBinaryExpression &&
//             PlSqlTokenTypes.EXPRESSIONS.contains(child.getElementType())) {
//      return defaultAlignment;
//    }
//    else if (parent.getElementType() == PlSqlTokenTypes.CONDITIONAL_EXPRESSION &&
//             PlSqlTokenTypes.EXPRESSIONS.contains(child.getElementType())) {
//      return defaultAlignment;
//    }

        return null;
    }

    @Nullable
    private Wrap getWrap(final ASTNode node, final ASTNode child) {
        WrapType wrapType = null;

        final IElementType nodeEType = node.getElementType();
        final IElementType childEType = child.getElementType();

        if (nodeEType == PlSqlElementTypes.INSERT_COMMAND) {
//            if (childEType == PlSqlTokenTypes.KEYWORD && child.getText().equalsIgnoreCase("values")) {
//                return Wrap.createWrap(WrapType.ALWAYS, true);
//            } else if (childEType == PlSqlElementTypes.INSERT_SUBQUERY) {
//                return Wrap.createWrap(WrapType.ALWAYS, true);
//            }
            return Wrap.createWrap(WrapType.NONE, true);
        } else  if (nodeEType == PlSqlElementTypes.SELECT_EXPRESSION && childEType == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM)  {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        } else if (nodeEType == PlSqlElementTypes.SELECT_EXPRESSION && childEType == PlSqlElementTypes.WHERE_CONDITION) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }

/*
    if (node.getElementType() ==  PlSqlTokenTypes.ASSIGNMENT_EXPRESSION) {
      final JSAssignmentExpression assignment = (JSAssignmentExpression)node.getPsi();
      if (child.getElementType() == assignment.getOperationSign() && mySettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE ||
          child.getPsi() == assignment.getROperand() && !mySettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE) {
        wrapType = Util.getWrapType(mySettings.ASSIGNMENT_WRAP);
      }
    } else if (node.getElementType() ==  PlSqlTokenTypes.BINARY_EXPRESSION) {
      final JSBinaryExpression binary = (JSBinaryExpression)node.getPsi();
      if (child.getElementType() == binary.getOperationSign() && mySettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE ||
          child.getPsi() == binary.getROperand() && !mySettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE) {
        wrapType = Util.getWrapType(mySettings.BINARY_OPERATION_WRAP);
      }
    } else if (node.getElementType() ==  PlSqlTokenTypes.PARENTHESIZED_EXPRESSION) {
      if (child == node.findChildByType(JSTokenTypes.LPAR) && mySettings.PARENTHESES_EXPRESSION_LPAREN_WRAP) {
        wrapType = Wrap.NORMAL;
      } else if (child == node.findChildByType(JSTokenTypes.RPAR) && mySettings.PARENTHESES_EXPRESSION_RPAREN_WRAP) {
        wrapType = Wrap.ALWAYS;
      }
    } else if (node.getElementType() ==  PlSqlTokenTypes.ARRAY_LITERAL_EXPRESSION) {
      if (child == node.findChildByType(JSTokenTypes.LBRACE) && mySettings.ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE) {
        wrapType = Wrap.NORMAL;
      } else if (child == node.findChildByType(JSTokenTypes.RPAR) && mySettings.ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE) {
        wrapType = Wrap.ALWAYS;
      }
    } else if (node.getElementType() ==  PlSqlTokenTypes.CONDITIONAL_EXPRESSION) {
      final IElementType elementType = child.getElementType();
      if ((mySettings.TERNARY_OPERATION_SIGNS_ON_NEXT_LINE && (elementType == JSTokenTypes.QUEST || elementType == JSTokenTypes.COLON)) ||
          (!mySettings.TERNARY_OPERATION_SIGNS_ON_NEXT_LINE && child.getPsi() instanceof JSExpression)) {
        wrapType = Util.getWrapType(mySettings.TERNARY_OPERATION_WRAP);
      }
    } else if (node.getElementType() ==  PlSqlTokenTypes.CALL_EXPRESSION) {
      if (child == node.findChildByType(JSTokenTypes.LPAR) && mySettings.CALL_PARAMETERS_LPAREN_ON_NEXT_LINE) {
        wrapType = Wrap.NORMAL;
      } else if (child == node.findChildByType(JSTokenTypes.RPAR) && mySettings.CALL_PARAMETERS_RPAREN_ON_NEXT_LINE) {
        wrapType = Wrap.ALWAYS;
      }
    }
    else if (node.getElementType() == PlSqlTokenTypes.PARAMETER_LIST) {
      if (child.getElementType() == PlSqlTokenTypes.FORMAL_PARAMETER) {
        wrapType = Util.getWrapType(mySettings.METHOD_PARAMETERS_WRAP);
      }
    }
    else if (node.getElementType() == PlSqlTokenTypes.FOR_STATEMENT ||
             node.getElementType() == PlSqlTokenTypes.FOR_IN_STATEMENT) {
      if (PlSqlTokenTypes.EXPRESSIONS.contains(child.getElementType())) {
        wrapType = Util.getWrapType(mySettings.FOR_STATEMENT_WRAP);
      }
    }
*/
        wrapType = Util.getWrapType(mySettings.METHOD_PARAMETERS_WRAP);
        Wrap w = wrapType == null ? null : Wrap.createWrap(wrapType, false);

        log.info("#getWrap ");
        return w;
    }
}

