// $ANTLR 2.7.5 (20050128): "/git_app/sqlassistant_super/github/shared/root/grammars/ast_string.g" -> "ASTStringParser.java"$

package com.deepsky.tools.generated;

import antlr.CommonToken;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class ASTStringParser extends antlr.LLkParser       implements ASTStrTypesTokenTypes
 {

protected ASTStringParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ASTStringParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected ASTStringParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ASTStringParser(TokenStream lexer) {
  this(lexer,2);
}

public ASTStringParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void stringa() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stringa_AST = null;
		
		try {      // for error handling
			match(SHARP);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(EQ);
			match(SHARP);
			match(OPEN_PAREN);
			match(OPEN_BRACK);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(COMMA);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case STRING_LITERAL:
			{
				AST tmp7_AST = null;
				tmp7_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp7_AST);
				match(STRING_LITERAL);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_BRACK);
			match(COMMA);
			match(SHARP);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			stringa_AST = (AST)currentAST.root;
			stringa_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(AST_DEF,"AST_DEF")).add(stringa_AST));
			currentAST.root = stringa_AST;
			currentAST.child = stringa_AST!=null &&stringa_AST.getFirstChild()!=null ?
				stringa_AST.getFirstChild() : stringa_AST;
			currentAST.advanceChildToEnd();
			stringa_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = stringa_AST;
	}
	
	public final void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		
		try {      // for error handling
			AST tmp11_AST = null;
			tmp11_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp11_AST);
			match(IDENTIFIER);
			identifier_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = identifier_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"START_RULE",
		"CREATE_PACKAGE",
		"LE",
		"GE",
		"LT",
		"DATA_TYPE",
		"VARCHAR2",
		"VARCHAR",
		"CHARACTER",
		"INTEGER",
		"NUMBER",
		"CHAR",
		"TIMESTAMP",
		"AST_DEF",
		"SHARP",
		"EQ",
		"OPEN_PAREN",
		"OPEN_BRACK",
		"COMMA",
		"STRING_LITERAL",
		"CLOSE_BRACK",
		"IDENTIFIER",
		"ANY_CHARACTER",
		"QUOTED_STRING",
		"SEMI",
		"COLON",
		"DOT",
		"ASTERISK",
		"AT_SIGN",
		"CLOSE_PAREN",
		"PLUS",
		"MINUS",
		"DIVIDE",
		"PERCENTAGE",
		"DOUBLEDOT",
		"CONCAT",
		"OUTER_JOIN",
		"START_LABEL",
		"END_LABEL",
		"ASSIGNMENT_EQ",
		"PASS_BY_NAME",
		"VERTBAR",
		"NOT_EQ",
		"GT",
		"N",
		"ESC",
		"HEX_DIGIT",
		"WS",
		"SL_COMMENT",
		"ML_COMMENT"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 21495810L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
