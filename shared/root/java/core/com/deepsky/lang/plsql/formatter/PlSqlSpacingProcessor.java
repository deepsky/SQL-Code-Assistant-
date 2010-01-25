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

package com.deepsky.lang.plsql.formatter;

import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.formatting.Spacing;
import com.intellij.openapi.util.TextRange;
import com.deepsky.lang.plsql.formatter.PlSqlNodeVisitor;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;

public class PlSqlSpacingProcessor extends PlSqlNodeVisitor {
    private ASTNode myParent;
    private ASTNode myChild1;
    private ASTNode myChild2;
    private final CodeStyleSettings mySettings;
    private Spacing myResult;
    private final IElementType type1;
    private final IElementType type2;

    public PlSqlSpacingProcessor(final ASTNode parent, final ASTNode child1, final ASTNode child2, final CodeStyleSettings settings) {
        myParent = parent;
        myChild1 = child1;
        myChild2 = child2;
        mySettings = settings;
        type1 = child1.getElementType();
        type2 = child2.getElementType();
        visit(parent);
    }

    public Spacing getResult() {
        return myResult;
    }

    public void visitColumnSpecList(final ASTNode node) {
        if (type1 == PlSqlElementTypes.COLUMN_SPEC && type2 == PlSqlTokenTypes.COMMA) {
            // "abc,"
            setSingleSpace(false);
        } else if (type1 == PlSqlTokenTypes.COMMA && type2 == PlSqlElementTypes.COLUMN_SPEC) {
            setLineBreakSpace(true);
            // ", abs"
//            setSingleSpace(true);
        }
    }

    public void visitParentesizedExprList(final ASTNode node) {

        if (type1 == PlSqlTokenTypes.OPEN_PAREN && type2 == PlSqlElementTypes.PLSQL_EXPR_LIST) {
            setLineBreakSpace(true);
        } else if (type1 == PlSqlElementTypes.PLSQL_EXPR_LIST && type2 == PlSqlTokenTypes.CLOSE_PAREN) {
            setLineBreakSpace(true);
        }
    }

//    public void visitInsertStatement(final ASTNode node) {
//        if (type1 == PlSqlTokenTypes.KEYWORD &&
//                (myChild1.getText().equalsIgnoreCase("insert") || myChild1.getText().equalsIgnoreCase("into"))) {
//            // "insert into" - insert one whitespace between them
//            setSingleSpace(true);
//        } else if (type1 == PlSqlTokenTypes.OPEN_PAREN && type2 == PlSqlElementTypes.COLUMN_SPEC_LIST) {
//            setLineBreakSpace(true);
//        } else if (type1 == PlSqlElementTypes.COLUMN_SPEC_LIST && type2 == PlSqlTokenTypes.CLOSE_PAREN) {
//            setLineBreakSpace(true);
//        } else if (type1 == PlSqlElementTypes.TABLE_ALIAS && type2 == PlSqlTokenTypes.OPEN_PAREN) {
//            // "tab1 ("
//            setSingleSpace(true);
//        } else if (type1 == PlSqlTokenTypes.CLOSE_PAREN && type2 == PlSqlTokenTypes.KEYWORD && myChild2.getText().equalsIgnoreCase("values")) {
//            // ") values"
//            setSingleSpace(true);
//        } else if (type1 == PlSqlTokenTypes.KEYWORD && myChild1.getText().equalsIgnoreCase("values") && type2 == PlSqlTokenTypes.OPEN_PAREN) {
//            // "values ("
//            setSingleSpace(true);
//        }
//    }


    private void setSingleSpace(boolean needSpace) {
        final int spaces = needSpace ? 1 : 0;
//    myResult = Spacing.createSpacing(spaces, spaces, 0, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
        myResult = Spacing.createSpacing(spaces, spaces, 0, false, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }

    private void setBraceSpace(boolean needSpaceSetting, int braceStyleSetting, TextRange textRange) {
        int spaces = needSpaceSetting ? 1 : 0;
        if (braceStyleSetting == CodeStyleSettings.NEXT_LINE_IF_WRAPPED && textRange != null) {
            myResult = Spacing.createDependentLFSpacing(spaces, spaces, textRange, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            int lineBreaks = braceStyleSetting == CodeStyleSettings.END_OF_LINE ? 0 : 1;
            myResult = Spacing.createSpacing(spaces, spaces, lineBreaks, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }

    private void setLineBreakSpace(final boolean needLineBreak) {
        final int breaks = needLineBreak ? 1 : 0;
        myResult = Spacing.createSpacing(1, 1, breaks, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }

/*
  public void visitEmbeddedContent(final ASTNode node) {
    if (PlSqlElementTypes.SOURCE_ELEMENTS.contains(type1) ||
        JSElementTypes.SOURCE_ELEMENTS.contains(type2) && type1 != JSTokenTypes.DOT ||
        type2 == JSTokenTypes.RBRACE) {
      myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }
  }

  public void visitParameterList(final ASTNode node) {
    if (type1 == JSTokenTypes.LPAR && type2 == JSTokenTypes.RPAR) {
      setSingleSpace(false);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_METHOD_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.COMMA) {
      setSingleSpace(mySettings.SPACE_AFTER_COMMA);
    }
    else if (type2 == JSTokenTypes.COMMA) {
      setSingleSpace(mySettings.SPACE_BEFORE_COMMA);
    }
  }

  public void visitBlock(final ASTNode node) {
    if (JSElementTypes.SOURCE_ELEMENTS.contains(type1) || JSElementTypes.SOURCE_ELEMENTS.contains(type2) ||
        type2 == JSTokenTypes.RBRACE) {
      if ( (type1 == JSTokenTypes.BAD_CHARACTER && JSElementTypes.SOURCE_ELEMENTS.contains(type2)) ||
           (type2 == JSTokenTypes.BAD_CHARACTER && JSElementTypes.SOURCE_ELEMENTS.contains(type1)) ||
           type2 == JSTokenTypes.END_OF_LINE_COMMENT
         ) {
        myResult = Spacing.getReadOnlySpacing();
      } else {
        final boolean keepOneLine = myParent.getPsi() instanceof JSFunction ? mySettings.KEEP_SIMPLE_METHODS_IN_ONE_LINE:mySettings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE;

        if (keepOneLine && (type1 == JSTokenTypes.LBRACE || type2 == JSTokenTypes.RBRACE)) {
          myResult = Spacing.createDependentLFSpacing(0, 1, node.getTextRange(), mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
          myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
        }
      }
    }
  }

  public void visitFile(final ASTNode node) {
    if (JSElementTypes.SOURCE_ELEMENTS.contains(type1) || JSElementTypes.SOURCE_ELEMENTS.contains(type2)) {
      if (type2 == JSTokenTypes.END_OF_LINE_COMMENT) {
        myResult = Spacing.getReadOnlySpacing();
      } else {
        myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
      }
    }
  }

  public void visitFunctionDeclaration(final ASTNode node) {
    if (type1 == JSTokenTypes.FUNCTION_KEYWORD && type2 == JSElementTypes.REFERENCE_EXPRESSION) {
      setSingleSpace(true);
    }
    else if (type1 == JSElementTypes.REFERENCE_EXPRESSION && type2 == JSElementTypes.PARAMETER_LIST) {
      setSingleSpace(mySettings.SPACE_BEFORE_METHOD_PARENTHESES);
    }
    else if (type1 == JSElementTypes.PARAMETER_LIST) {
      setBraceSpace(mySettings.SPACE_BEFORE_METHOD_LBRACE, mySettings.METHOD_BRACE_STYLE, myChild1.getTextRange());
    }
  }


  public void visitFunctionExpression(final ASTNode node) {
    visitFunctionDeclaration(node);
  }

  public void visitReferenceExpression(final ASTNode node) {
    if (type1 == JSTokenTypes.NEW_KEYWORD) {
      setSingleSpace(true);
    }
    else {
      setSingleSpace(false); // a.b should not have spaces before and after dot
    }
  }

  public void visitIfStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.IF_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_IF_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_IF_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR && type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_IF_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type2 == JSTokenTypes.ELSE_KEYWORD) {
      setLineBreakSpace(mySettings.ELSE_ON_NEW_LINE);
    }
    else if (type1 == JSTokenTypes.ELSE_KEYWORD && type2 == JSElementTypes.BLOCK) {
      setBraceSpace(mySettings.SPACE_BEFORE_ELSE_LBRACE, mySettings.BRACE_STYLE, null);
    }
  }

  public void visitCallExpression(final ASTNode node) {
    if (type2 == JSElementTypes.ARGUMENT_LIST) {
      setSingleSpace(mySettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES);
    }
  }

  public void visitNewExpression(final ASTNode node) {
    if (type1 == JSTokenTypes.NEW_KEYWORD) {
      setSingleSpace(true);
    }
    else if (type2 == JSElementTypes.ARGUMENT_LIST) {
      setSingleSpace(mySettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES);
    }
  }

  public void visitForStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.SEMICOLON) {
      setSingleSpace(true);
    }
    else if (type2 == JSTokenTypes.SEMICOLON) {
      setSingleSpace(mySettings.SPACE_BEFORE_SEMICOLON);
    }

    if (type1 == JSTokenTypes.FOR_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_FOR_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR && type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_FOR_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_FOR_PARENTHESES);
    }
  }

  public void visitDoWhileStatement(final ASTNode node) {
    if (type2 == JSTokenTypes.WHILE_KEYWORD) {
      if (mySettings.WHILE_ON_NEW_LINE) {
        myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
      }
      else {
        myResult = Spacing.createSpacing(1, 1,  0, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
      }
    } else if (type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_WHILE_PARENTHESES);
    } else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_WHILE_PARENTHESES);
    }
  }

  public void visitForInStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.VAR_KEYWORD || type2 == JSTokenTypes.VAR_KEYWORD) {
      setSingleSpace(true);
    }
    else if (type1 == JSTokenTypes.FOR_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_FOR_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR && type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_FOR_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_FOR_PARENTHESES);
    }
  }

  public void visitWhileStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.WHILE_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_WHILE_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR && type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_WHILE_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_WHILE_PARENTHESES);
    }
  }

  public void visitWithStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.WITH_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_WHILE_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR && type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_WHILE_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_WHILE_PARENTHESES);
    }
  }

  public void visitTryStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.TRY_KEYWORD && type2 == JSElementTypes.BLOCK) {
      setBraceSpace(mySettings.SPACE_BEFORE_TRY_LBRACE, mySettings.BRACE_STYLE, null);
    }
    else if (type2 == JSElementTypes.CATCH_BLOCK) {
      setLineBreakSpace(mySettings.CATCH_ON_NEW_LINE);
    }
    else if (type2 == JSTokenTypes.FINALLY_KEYWORD) {
      setLineBreakSpace(mySettings.FINALLY_ON_NEW_LINE);
    }
    else if (type1 == JSTokenTypes.FINALLY_KEYWORD) {
      setBraceSpace(mySettings.SPACE_BEFORE_FINALLY_LBRACE, mySettings.BRACE_STYLE, null);
    }
  }

  public void visitCatchBlock(final ASTNode node) {
    if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_CATCH_PARENTHESES);
    }
    if (type2 == JSElementTypes.BLOCK) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild2.getTextRange().getStartOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_CATCH_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
  }

  public void visitSwitchStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.SWITCH_KEYWORD && type2 == JSTokenTypes.LPAR) {
      setSingleSpace(mySettings.SPACE_BEFORE_SWITCH_PARENTHESES);
    }
    else if (type1 == JSTokenTypes.RPAR) {
      TextRange dependentRange = new TextRange(myParent.getStartOffset(), myChild1.getTextRange().getEndOffset());
      setBraceSpace(mySettings.SPACE_BEFORE_SWITCH_LBRACE, mySettings.BRACE_STYLE, dependentRange);
    }
    else if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(mySettings.SPACE_WITHIN_SWITCH_PARENTHESES);
    }
  }

  public void visitArgumentList(final ASTNode node) {
    if (type1 == JSTokenTypes.LPAR || type2 == JSTokenTypes.RPAR) {
      setSingleSpace(false);
    }
    else if (type1 == JSTokenTypes.COMMA) {
      setSingleSpace(mySettings.SPACE_AFTER_COMMA);
    }
    else if (type2 == JSTokenTypes.COMMA) {
      setSingleSpace(mySettings.SPACE_BEFORE_COMMA);
    }
  }

  public void visitStatement(final ASTNode node) {
    if (type2 == JSTokenTypes.SEMICOLON) {
      setSingleSpace(false);
    }
  }

  public void visitVarStatement(final ASTNode node) {
    if (type1 == JSTokenTypes.VAR_KEYWORD) {
      setSingleSpace(true);
    }
  }

  public void visitVariable(final ASTNode node) {
    if (type1 == JSTokenTypes.EQ || type2 == JSTokenTypes.EQ) { // Initializer
      setSingleSpace(mySettings.SPACE_AROUND_ASSIGNMENT_OPERATORS);
    }
  }

  public void visitBinaryExpression(final ASTNode node) {
    IElementType opSign = null;
    if (JSTokenTypes.OPERATIONS.contains(type1)) {
      opSign = type1;
    }
    else if (JSTokenTypes.OPERATIONS.contains(type2)) {
      opSign = type2;
    }

    if (opSign != null) {
      setSingleSpace(getSpaceAroundOption(opSign));
    }
  }

  public void visitConditionalExpression(final ASTNode node) {
    if (type1 == JSTokenTypes.QUEST) {
      setSingleSpace(mySettings.SPACE_AFTER_QUEST);
    } else if (type2 == JSTokenTypes.QUEST) {
      setSingleSpace(mySettings.SPACE_BEFORE_QUEST);
    } else if (type1 == JSTokenTypes.COLON) {
      setSingleSpace(mySettings.SPACE_AFTER_COLON);
    } else if (type2 == JSTokenTypes.COLON) {
      setSingleSpace(mySettings.SPACE_BEFORE_COLON);
    }
  }


  private boolean getSpaceAroundOption(final IElementType opSign) {
    boolean option = false;
    if (PlSqlTokenTypes.ADDITIVE_OPERATIONS.contains(opSign)) {
      option = mySettings.SPACE_AROUND_ADDITIVE_OPERATORS;
    }
    else if (PlSqlTokenTypes.MULTIPLICATIVE_OPERATIONS.contains(opSign)) {
      option = mySettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS;
    }
    else if (PlSqlTokenTypes.ASSIGNMENT_OPERATIONS.contains(opSign)) {
      option = mySettings.SPACE_AROUND_ASSIGNMENT_OPERATORS;
    }
    else if (PlSqlTokenTypes.EQUALITY_OPERATIONS.contains(opSign)) {
      option = mySettings.SPACE_AROUND_EQUALITY_OPERATORS;
    }
    else if (PlSqlTokenTypes.RELATIONAL_OPERATIONS.contains(opSign)) {
      option = mySettings.SPACE_AROUND_RELATIONAL_OPERATORS;
    }
//    else if (opSign == PlSqlTokenTypes.ANDAND || opSign == PlSqlTokenTypes.OROR) {
//      option = mySettings.SPACE_AROUND_LOGICAL_OPERATORS;
//    }
//    else if (opSign == PlSqlTokenTypes.OR || opSign == PlSqlTokenTypes.AND || opSign == PlSqlTokenTypes.XOR) {
//      option = mySettings.SPACE_AROUND_BITWISE_OPERATORS;
//    }
    return option;
  }
*/

}

