// $ANTLR 2.7.5 (20050128): "../../shared/root/grammars/plsql_parser_ex.g" -> "PLSqlParserAdopted.java"$

package com.deepsky.generated.plsql.adopted;

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

public class PLSqlParserAdopted extends antlr.LLkParser       implements PLSqlTokenTypes
 {

    protected int depth = 0;
    protected int returnType = -1;
    public void __markRule(int type){
        returnType = type;
    }

    final int _NO_WS_ = 0;
    final int _KEEP_WS_ = 1;
    final int _SINGLE_WS_ = 2;
    final int _NEWLINE_WS_ = 3;

    public void handle_ws( int action ){
    }

    protected void process_wrapped_package(String package_name){
        // default action if the package is wrapped
        throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
    }

protected PLSqlParserAdopted(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public PLSqlParserAdopted(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected PLSqlParserAdopted(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public PLSqlParserAdopted(TokenStream lexer) {
  this(lexer,3);
}

public PLSqlParserAdopted(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public void no_one_should_call_me() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST no_one_should_call_me_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ERROR_TOKEN_A);
			}
			no_one_should_call_me_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = no_one_should_call_me_AST;
	}
	
	public void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				AST tmp1_AST = null;
				tmp1_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1_AST);
				match(IDENTIFIER);
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				AST tmp2_AST = null;
				tmp2_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp2_AST);
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			identifier_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = identifier_AST;
	}
	
	public void start_rule() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST start_rule_AST = null;
		
		try {      // for error handling
			{
			_loop4:
			do {
				if ((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					start_rule_inner();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if(true){
					if ( inputState.guessing==0 ) {
						
						if (LA(1)==EOF) {
						match(EOF);
						break;
						} else {
						// consume();
						// consumeUntil(_tokenSet_2);
						recover(null,_tokenSet_2);
						}
						
					}
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			start_rule_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = start_rule_AST;
	}
	
	public void start_rule_inner() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST start_rule_inner_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_create:
			{
				{
				create_or_replace();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case DIVIDE:
				{
					match(DIVIDE);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_package:
			{
				package_spec();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(PACKAGE_SPEC);
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_function:
			{
				{
				function_body();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case DIVIDE:
				{
					match(DIVIDE);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_procedure:
			{
				{
				procedure_body();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case DIVIDE:
				{
					match(DIVIDE);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case OPEN_PAREN:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				{
				sql_statement();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_alter:
			{
				{
				alter_command();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_comment:
			{
				{
				comment();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_type:
			{
				{
				type_definition();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_drop:
			{
				{
				drop_command();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_truncate:
			{
				{
				truncate_command();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			case AT_PREFIXED:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			{
				sqlplus_command_internal();
				astFactory.addASTChild(currentAST, returnAST);
				start_rule_inner_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = start_rule_inner_AST;
	}
	
	public void create_or_replace() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_or_replace_AST = null;
		
		try {      // for error handling
			match(LITERAL_create);
			{
			switch ( LA(1)) {
			case LITERAL_or:
			{
				match(LITERAL_or);
				match(LITERAL_replace);
				break;
			}
			case LITERAL_table:
			case LITERAL_view:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_index:
			case LITERAL_force:
			case LITERAL_sequence:
			case LITERAL_type:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_unique:
			case LITERAL_bitmap:
			case LITERAL_global:
			case LITERAL_temporary:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_force:
			{
				AST tmp15_AST = null;
				tmp15_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp15_AST);
				match(LITERAL_force);
				break;
			}
			case LITERAL_table:
			case LITERAL_view:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_index:
			case LITERAL_sequence:
			case LITERAL_type:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_unique:
			case LITERAL_bitmap:
			case LITERAL_global:
			case LITERAL_temporary:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_procedure:
			{
				procedure_body();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_PROCEDURE);
				}
				break;
			}
			case LITERAL_function:
			{
				function_body();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_FUNCTION);
				}
				break;
			}
			case LITERAL_view:
			{
				create_view();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_VIEW);
				}
				break;
			}
			case LITERAL_type:
			{
				{
				type_definition();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case LITERAL_table:
			{
				{
				create_table2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_DEF);
				}
				break;
			}
			case LITERAL_global:
			case LITERAL_temporary:
			{
				{
				create_temp_table2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_TEMP_TABLE);
				}
				break;
			}
			case LITERAL_index:
			case LITERAL_unique:
			case LITERAL_bitmap:
			{
				{
				create_index2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_INDEX);
				}
				break;
			}
			case LITERAL_trigger:
			{
				{
				create_trigger();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_TRIGGER);
				}
				break;
			}
			case LITERAL_directory:
			{
				{
				create_directory();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_DIRECTORY);
				}
				break;
			}
			case LITERAL_database:
			{
				{
				create_db_link();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_DB_LINK);
				}
				break;
			}
			case LITERAL_sequence:
			{
				{
				create_sequence();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_SEQUENCE);
				}
				break;
			}
			default:
				if ((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					package_spec();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						__markRule(PACKAGE_SPEC);
					}
				}
				else if ((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_7.member(LA(3)))) {
					package_body();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_or_replace_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = create_or_replace_AST;
	}
	
	public void package_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_spec_AST = null;
		AST o_AST = null;
		Token  a = null;
		AST a_AST = null;
		
		try {      // for error handling
			match(LITERAL_package);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_9.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			package_name();
			o_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_authid:
			{
				a = LT(1);
				a_AST = astFactory.create(a);
				astFactory.addASTChild(currentAST, a_AST);
				match(LITERAL_authid);
				if ( inputState.guessing==0 ) {
					a_AST.setType(AUTHID);
				}
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_wrapped:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_wrapped:
			{
				{
				AST tmp26_AST = null;
				tmp26_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp26_AST);
				match(LITERAL_wrapped);
				if ( inputState.guessing==0 ) {
					
					//                String package_name = #o.getFirstChild().getText();
					String package_name = o_AST.getText();
					//                throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
					process_wrapped_package(package_name);
					
				}
				}
				break;
			}
			case LITERAL_is:
			case LITERAL_as:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_is:
				{
					match(LITERAL_is);
					break;
				}
				case LITERAL_as:
				{
					match(LITERAL_as);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_serially_reusable)) {
					serially_reusable_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					match(SEMI);
				}
				else if ((_tokenSet_10.member(LA(1))) && (_tokenSet_11.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop542:
				do {
					if ((_tokenSet_12.member(LA(1)))) {
						package_obj_spec_ex();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop542;
					}
					
				} while (true);
				}
				match(LITERAL_end);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					package_name();
					break;
				}
				case SEMI:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(SEMI);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			package_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = package_spec_AST;
	}
	
	public void function_body() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_body_AST = null;
		
		try {      // for error handling
			function_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_is:
			case LITERAL_as:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_is:
				{
					match(LITERAL_is);
					break;
				}
				case LITERAL_as:
				{
					match(LITERAL_as);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				func_proc_statements();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(FUNCTION_BODY);
				}
				break;
			}
			case LITERAL_aggregate:
			{
				{
				AST tmp34_AST = null;
				tmp34_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp34_AST);
				match(LITERAL_aggregate);
				AST tmp35_AST = null;
				tmp35_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp35_AST);
				match(LITERAL_using);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CUSTOM_AGGR_FUNCTION);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			function_body_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = function_body_AST;
	}
	
	public void procedure_body() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST procedure_body_AST = null;
		Token  i = null;
		AST i_AST = null;
		Token  a = null;
		AST a_AST = null;
		
		try {      // for error handling
			procedure_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_is:
			{
				i = LT(1);
				i_AST = astFactory.create(i);
				match(LITERAL_is);
				break;
			}
			case LITERAL_as:
			{
				a = LT(1);
				a_AST = astFactory.create(a);
				match(LITERAL_as);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			func_proc_statements();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_BODY);
			}
			procedure_body_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = procedure_body_AST;
	}
	
	public void sql_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sql_statement_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case OPEN_PAREN:
			case LITERAL_select:
			{
				select_command();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_insert:
			{
				insert_command();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_update:
			{
				update_command();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_delete:
			{
				delete_command();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_merge:
			{
				merge_command();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_commit:
			{
				commit_statement();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rollback:
			{
				rollback_statement();
				astFactory.addASTChild(currentAST, returnAST);
				sql_statement_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = sql_statement_AST;
	}
	
	public void alter_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alter_command_AST = null;
		
		try {      // for error handling
			if ((LA(1)==LITERAL_alter) && (LA(2)==LITERAL_system||LA(2)==LITERAL_session)) {
				alter_system_session();
				astFactory.addASTChild(currentAST, returnAST);
				alter_command_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_alter) && (LA(2)==LITERAL_table)) {
				alter_table();
				astFactory.addASTChild(currentAST, returnAST);
				alter_command_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_alter) && (LA(2)==LITERAL_trigger)) {
				alter_trigger();
				astFactory.addASTChild(currentAST, returnAST);
				alter_command_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = alter_command_AST;
	}
	
	public void comment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comment_AST = null;
		
		try {      // for error handling
			match(LITERAL_comment);
			match(LITERAL_on);
			{
			switch ( LA(1)) {
			case LITERAL_table:
			{
				{
				match(LITERAL_table);
				table_name_ddl();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_is);
				comment_string();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case LITERAL_column:
			{
				{
				match(LITERAL_column);
				table_name_ddl();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp41_AST = null;
				tmp41_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp41_AST);
				match(DOT);
				column_name_ddl();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_is);
				comment_string();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(COMMENT);
			}
			comment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = comment_AST;
	}
	
	public void type_definition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_definition_AST = null;
		
		try {      // for error handling
			match(LITERAL_type);
			type_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				match(LITERAL_as);
				break;
			}
			case LITERAL_is:
			{
				match(LITERAL_is);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_object:
			{
				{
				match(LITERAL_object);
				match(OPEN_PAREN);
				record_item();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop511:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						record_item();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop511;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(OBJECT_TYPE_DEF);
				}
				break;
			}
			case LITERAL_table:
			{
				{
				match(LITERAL_table);
				match(LITERAL_of);
				type_spec();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_index:
				{
					match(LITERAL_index);
					match(LITERAL_by);
					type_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_not:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_not:
				{
					AST tmp54_AST = null;
					tmp54_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp54_AST);
					match(LITERAL_not);
					AST tmp55_AST = null;
					tmp55_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp55_AST);
					match(LITERAL_null);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_COLLECTION);
				}
				break;
			}
			case LITERAL_record:
			{
				{
				AST tmp56_AST = null;
				tmp56_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp56_AST);
				match(LITERAL_record);
				match(OPEN_PAREN);
				record_item();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop517:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						record_item();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop517;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(RECORD_TYPE_DECL);
				}
				break;
			}
			case LITERAL_ref:
			{
				{
				AST tmp60_AST = null;
				tmp60_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp60_AST);
				match(LITERAL_ref);
				match(LITERAL_cursor);
				{
				switch ( LA(1)) {
				case LITERAL_return:
				{
					match(LITERAL_return);
					record_name();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case PERCENTAGE:
					{
						AST tmp63_AST = null;
						tmp63_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp63_AST);
						match(PERCENTAGE);
						AST tmp64_AST = null;
						tmp64_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp64_AST);
						match(LITERAL_rowtype);
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_package:
					case LITERAL_type:
					case LITERAL_truncate:
					case LITERAL_comment:
					case LITERAL_column:
					case LITERAL_set:
					case LITERAL_show:
					case LITERAL_var:
					case LITERAL_variable:
					case LITERAL_col:
					case LITERAL_exec:
					case LITERAL_execute:
					case LITERAL_whenever:
					case LITERAL_exit:
					case LITERAL_commit:
					case LITERAL_rollback:
					case LITERAL_def:
					case LITERAL_define:
					case LITERAL_prompt:
					case LITERAL_rem:
					case LITERAL_quit:
					case LITERAL_spool:
					case LITERAL_sta:
					case LITERAL_start:
					case LITERAL_repfooter:
					case LITERAL_repheader:
					case LITERAL_serveroutput:
					case LITERAL_begin:
					case LITERAL_declare:
					case LITERAL_create:
					case LITERAL_delete:
					case LITERAL_insert:
					case LITERAL_update:
					case LITERAL_select:
					case LITERAL_merge:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(REF_CURSOR);
				}
				break;
			}
			case LITERAL_varray:
			{
				{
				match(LITERAL_varray);
				match(OPEN_PAREN);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				match(LITERAL_of);
				type_spec();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_not:
				{
					AST tmp69_AST = null;
					tmp69_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp69_AST);
					match(LITERAL_not);
					AST tmp70_AST = null;
					tmp70_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp70_AST);
					match(LITERAL_null);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(VARRAY_COLLECTION);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			type_definition_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = type_definition_AST;
	}
	
	public void drop_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST drop_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_drop);
			{
			switch ( LA(1)) {
			case LITERAL_table:
			{
				{
				match(LITERAL_table);
				table_name_ddl();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_purge:
				{
					AST tmp73_AST = null;
					tmp73_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp73_AST);
					match(LITERAL_purge);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_TABLE);
				}
				break;
			}
			case LITERAL_view:
			{
				{
				AST tmp74_AST = null;
				tmp74_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp74_AST);
				match(LITERAL_view);
				table_name_ddl();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					AST tmp75_AST = null;
					tmp75_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp75_AST);
					match(LITERAL_cascade);
					AST tmp76_AST = null;
					tmp76_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp76_AST);
					match(LITERAL_constraints);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_VIEW);
				}
				break;
			}
			case LITERAL_function:
			{
				{
				AST tmp77_AST = null;
				tmp77_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp77_AST);
				match(LITERAL_function);
				callable_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_FUNCTION);
				}
				break;
			}
			case LITERAL_procedure:
			{
				{
				AST tmp78_AST = null;
				tmp78_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp78_AST);
				match(LITERAL_procedure);
				callable_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_PROCEDURE);
				}
				break;
			}
			case LITERAL_package:
			{
				{
				AST tmp79_AST = null;
				tmp79_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp79_AST);
				match(LITERAL_package);
				{
				if ((LA(1)==LITERAL_body) && (_tokenSet_5.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
					AST tmp80_AST = null;
					tmp80_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp80_AST);
					match(LITERAL_body);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_16.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					astFactory.addASTChild(currentAST, returnAST);
					match(DOT);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_14.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				package_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_PACKAGE);
				}
				break;
			}
			case LITERAL_index:
			{
				{
				AST tmp82_AST = null;
				tmp82_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp82_AST);
				match(LITERAL_index);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					astFactory.addASTChild(currentAST, returnAST);
					match(DOT);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_17.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				index_name();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					AST tmp84_AST = null;
					tmp84_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp84_AST);
					match(LITERAL_force);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_INDEX);
				}
				break;
			}
			case LITERAL_sequence:
			{
				{
				AST tmp85_AST = null;
				tmp85_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp85_AST);
				match(LITERAL_sequence);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_SEQUENCE);
				}
				break;
			}
			case LITERAL_type:
			{
				{
				AST tmp86_AST = null;
				tmp86_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp86_AST);
				match(LITERAL_type);
				{
				if ((LA(1)==LITERAL_body) && (_tokenSet_18.member(LA(2))) && (_tokenSet_19.member(LA(3)))) {
					AST tmp87_AST = null;
					tmp87_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp87_AST);
					match(LITERAL_body);
				}
				else if ((_tokenSet_18.member(LA(1))) && (_tokenSet_19.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					AST tmp88_AST = null;
					tmp88_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp88_AST);
					match(LITERAL_force);
					break;
				}
				case LITERAL_validate:
				{
					AST tmp89_AST = null;
					tmp89_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp89_AST);
					match(LITERAL_validate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_TYPE);
				}
				break;
			}
			case LITERAL_public:
			case LITERAL_synonym:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_public:
				{
					AST tmp90_AST = null;
					tmp90_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp90_AST);
					match(LITERAL_public);
					break;
				}
				case LITERAL_synonym:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp91_AST = null;
				tmp91_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp91_AST);
				match(LITERAL_synonym);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_SYNONYM);
				}
				break;
			}
			case LITERAL_operator:
			{
				{
				AST tmp92_AST = null;
				tmp92_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp92_AST);
				match(LITERAL_operator);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					AST tmp93_AST = null;
					tmp93_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp93_AST);
					match(LITERAL_force);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_OPERATOR);
				}
				break;
			}
			case LITERAL_user:
			{
				{
				AST tmp94_AST = null;
				tmp94_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp94_AST);
				match(LITERAL_user);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					AST tmp95_AST = null;
					tmp95_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp95_AST);
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_USER);
				}
				break;
			}
			case LITERAL_role:
			{
				{
				AST tmp96_AST = null;
				tmp96_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp96_AST);
				match(LITERAL_role);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_ROLE);
				}
				break;
			}
			case LITERAL_directory:
			{
				{
				AST tmp97_AST = null;
				tmp97_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp97_AST);
				match(LITERAL_directory);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_DIRECTORY);
				}
				break;
			}
			case LITERAL_library:
			{
				{
				AST tmp98_AST = null;
				tmp98_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp98_AST);
				match(LITERAL_library);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_LIBRARY);
				}
				break;
			}
			case LITERAL_database:
			{
				{
				AST tmp99_AST = null;
				tmp99_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp99_AST);
				match(LITERAL_database);
				AST tmp100_AST = null;
				tmp100_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp100_AST);
				match(LITERAL_link);
				object_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_DB_LINK);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			drop_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = drop_command_AST;
	}
	
	public void truncate_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST truncate_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_truncate);
			match(LITERAL_table);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TRUNCATE_TABLE);
			}
			truncate_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = truncate_command_AST;
	}
	
	public void sqlplus_command_internal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sqlplus_command_internal_AST = null;
		
		try {      // for error handling
			{
			int _cnt80=0;
			_loop80:
			do {
				if ((_tokenSet_20.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sqlplus_command();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt80>=1 ) { break _loop80; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt80++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(SQLPLUS_COMMAND);
			}
			sqlplus_command_internal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sqlplus_command_internal_AST;
	}
	
	public void table_name_ddl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_name_ddl_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_NAME_DDL);
			}
			table_name_ddl_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = table_name_ddl_AST;
	}
	
	public void callable_name_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST callable_name_ref_AST = null;
		
		try {      // for error handling
			name_fragment_ex();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop870:
			do {
				if ((LA(1)==DOT) && (_tokenSet_18.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
					match(DOT);
					name_fragment_ex();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop870;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(CALLABLE_NAME_REF);
			}
			callable_name_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = callable_name_ref_AST;
	}
	
	public void schema_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST schema_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(SCHEMA_NAME);
			}
			schema_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = schema_name_AST;
	}
	
	public void package_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PACKAGE_NAME);
			}
			package_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = package_name_AST;
	}
	
	public void index_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(INDEX_NAME);
			}
			index_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_24);
			} else {
			  throw ex;
			}
		}
		returnAST = index_name_AST;
	}
	
	public void object_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST object_name_AST = null;
		
		try {      // for error handling
			identifier3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case DOT:
			{
				match(DOT);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_force:
			case LITERAL_type:
			case LITERAL_validate:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_is:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_maxvalue:
			case LITERAL_minvalue:
			case LITERAL_cycle:
			case LITERAL_nocycle:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_increment:
			case LITERAL_order:
			case LITERAL_noorder:
			case LITERAL_connect:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(OBJECT_NAME);
			}
			object_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = object_name_AST;
	}
	
	public void comment_string() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comment_string_AST = null;
		
		try {      // for error handling
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(COMMENT_STR);
			}
			comment_string_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = comment_string_AST;
	}
	
	public void column_name_ddl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_name_ddl_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_DDL);
			}
			column_name_ddl_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_26);
			} else {
			  throw ex;
			}
		}
		returnAST = column_name_ddl_AST;
	}
	
	public void string_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST string_literal_AST = null;
		
		try {      // for error handling
			{
			int _cnt1045=0;
			_loop1045:
			do {
				if ((LA(1)==QUOTED_STR) && (_tokenSet_27.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp105_AST = null;
					tmp105_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp105_AST);
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1045>=1 ) { break _loop1045; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1045++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STRING_LITERAL);
			}
			string_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			} else {
			  throw ex;
			}
		}
		returnAST = string_literal_AST;
	}
	
	public void column_def() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_def_AST = null;
		
		try {      // for error handling
			column_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			type_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_default:
			{
				AST tmp106_AST = null;
				tmp106_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp106_AST);
				match(LITERAL_default);
				{
				switch ( LA(1)) {
				case LITERAL_sysdate:
				{
					AST tmp107_AST = null;
					tmp107_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp107_AST);
					match(LITERAL_sysdate);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_not:
			case LITERAL_null:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_null:
			{
				not_null();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				pk_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_references:
			{
				fk_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_constraint:
			{
				column_constraint();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_DEF);
			}
			column_def_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = column_def_AST;
	}
	
	public void type_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_spec_AST = null;
		
		try {      // for error handling
			{
			if ((_tokenSet_29.member(LA(1))) && (_tokenSet_30.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				datatype();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				boolean synPredMatched761 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==PERCENTAGE) && (_tokenSet_31.member(LA(3))))) {
					int _m761 = mark();
					synPredMatched761 = true;
					inputState.guessing++;
					try {
						{
						percentage_type();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched761 = false;
					}
					rewind(_m761);
					inputState.guessing--;
				}
				if ( synPredMatched761 ) {
					percentage_type();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					type_name_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				type_spec_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_33);
				} else {
				  throw ex;
				}
			}
			returnAST = type_spec_AST;
		}
		
	public void numeric_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST numeric_literal_AST = null;
		
		try {      // for error handling
			AST tmp108_AST = null;
			tmp108_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp108_AST);
			match(NUMBER);
			if ( inputState.guessing==0 ) {
				__markRule(NUMERIC_LITERAL);
			}
			numeric_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
		returnAST = numeric_literal_AST;
	}
	
	public void not_null() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST not_null_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp109_AST = null;
				tmp109_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp109_AST);
				match(LITERAL_not);
				break;
			}
			case LITERAL_null:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp110_AST = null;
			tmp110_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp110_AST);
			match(LITERAL_null);
			if ( inputState.guessing==0 ) {
				__markRule(NOT_NULL_STMT);
			}
			not_null_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
		returnAST = not_null_AST;
	}
	
	public void pk_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST pk_spec_AST = null;
		
		try {      // for error handling
			AST tmp111_AST = null;
			tmp111_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp111_AST);
			match(LITERAL_primary);
			AST tmp112_AST = null;
			tmp112_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp112_AST);
			match(LITERAL_key);
			{
			if ((LA(1)==LITERAL_disable) && (_tokenSet_36.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				AST tmp113_AST = null;
				tmp113_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp113_AST);
				match(LITERAL_disable);
			}
			else if ((LA(1)==LITERAL_enable) && (_tokenSet_36.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				AST tmp114_AST = null;
				tmp114_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp114_AST);
				match(LITERAL_enable);
			}
			else if ((_tokenSet_36.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_PK_SPEC);
			}
			pk_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		returnAST = pk_spec_AST;
	}
	
	public void fk_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fk_spec_AST = null;
		
		try {      // for error handling
			match(LITERAL_references);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			match(OPEN_PAREN);
			column_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_FK_SPEC);
			}
			fk_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		returnAST = fk_spec_AST;
	}
	
	public void column_constraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_constraint_AST = null;
		
		try {      // for error handling
			AST tmp118_AST = null;
			tmp118_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp118_AST);
			match(LITERAL_constraint);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_null:
			{
				not_null();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_primary:
			{
				{
				AST tmp119_AST = null;
				tmp119_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp119_AST);
				match(LITERAL_primary);
				AST tmp120_AST = null;
				tmp120_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp120_AST);
				match(LITERAL_key);
				AST tmp121_AST = null;
				tmp121_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp121_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop76:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop76;
					}
					
				} while (true);
				}
				AST tmp123_AST = null;
				tmp123_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp123_AST);
				match(CLOSE_PAREN);
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_36.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp124_AST = null;
					tmp124_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp124_AST);
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_36.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp125_AST = null;
					tmp125_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp125_AST);
					match(LITERAL_enable);
				}
				else if ((_tokenSet_36.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			column_constraint_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		returnAST = column_constraint_AST;
	}
	
	public void row_movement_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST row_movement_clause_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_disable:
			{
				AST tmp126_AST = null;
				tmp126_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp126_AST);
				match(LITERAL_disable);
				break;
			}
			case LITERAL_enable:
			{
				AST tmp127_AST = null;
				tmp127_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp127_AST);
				match(LITERAL_enable);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp128_AST = null;
			tmp128_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp128_AST);
			match(LITERAL_row);
			AST tmp129_AST = null;
			tmp129_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp129_AST);
			match(LITERAL_movement);
			row_movement_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = row_movement_clause_AST;
	}
	
	public void identifier2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier2_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				AST tmp130_AST = null;
				tmp130_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp130_AST);
				match(IDENTIFIER);
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				AST tmp131_AST = null;
				tmp131_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp131_AST);
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			case PLSQL_ENV_VAR:
			{
				AST tmp132_AST = null;
				tmp132_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp132_AST);
				match(PLSQL_ENV_VAR);
				break;
			}
			case LITERAL_left:
			{
				AST tmp133_AST = null;
				tmp133_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp133_AST);
				match(LITERAL_left);
				break;
			}
			case LITERAL_right:
			{
				AST tmp134_AST = null;
				tmp134_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp134_AST);
				match(LITERAL_right);
				break;
			}
			case LITERAL_type:
			{
				AST tmp135_AST = null;
				tmp135_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp135_AST);
				match(LITERAL_type);
				break;
			}
			case LITERAL_count:
			{
				AST tmp136_AST = null;
				tmp136_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp136_AST);
				match(LITERAL_count);
				break;
			}
			case LITERAL_open:
			{
				AST tmp137_AST = null;
				tmp137_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp137_AST);
				match(LITERAL_open);
				break;
			}
			case LITERAL_exec:
			{
				AST tmp138_AST = null;
				tmp138_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp138_AST);
				match(LITERAL_exec);
				break;
			}
			case LITERAL_user:
			{
				AST tmp139_AST = null;
				tmp139_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp139_AST);
				match(LITERAL_user);
				break;
			}
			case LITERAL_dbtimezone:
			{
				AST tmp140_AST = null;
				tmp140_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp140_AST);
				match(LITERAL_dbtimezone);
				break;
			}
			case LITERAL_commit:
			{
				AST tmp141_AST = null;
				tmp141_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp141_AST);
				match(LITERAL_commit);
				break;
			}
			case LITERAL_rollback:
			{
				AST tmp142_AST = null;
				tmp142_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp142_AST);
				match(LITERAL_rollback);
				break;
			}
			case LITERAL_savepoint:
			{
				AST tmp143_AST = null;
				tmp143_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp143_AST);
				match(LITERAL_savepoint);
				break;
			}
			case LITERAL_comment:
			{
				AST tmp144_AST = null;
				tmp144_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp144_AST);
				match(LITERAL_comment);
				break;
			}
			case LITERAL_charset:
			{
				AST tmp145_AST = null;
				tmp145_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp145_AST);
				match(LITERAL_charset);
				break;
			}
			case LITERAL_body:
			{
				AST tmp146_AST = null;
				tmp146_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp146_AST);
				match(LITERAL_body);
				break;
			}
			case LITERAL_escape:
			{
				AST tmp147_AST = null;
				tmp147_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp147_AST);
				match(LITERAL_escape);
				break;
			}
			case LITERAL_reverse:
			{
				AST tmp148_AST = null;
				tmp148_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp148_AST);
				match(LITERAL_reverse);
				break;
			}
			case LITERAL_exists:
			{
				AST tmp149_AST = null;
				tmp149_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp149_AST);
				match(LITERAL_exists);
				break;
			}
			case LITERAL_delete:
			{
				AST tmp150_AST = null;
				tmp150_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp150_AST);
				match(LITERAL_delete);
				break;
			}
			case LITERAL_trim:
			{
				AST tmp151_AST = null;
				tmp151_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp151_AST);
				match(LITERAL_trim);
				break;
			}
			case LITERAL_flush:
			{
				AST tmp152_AST = null;
				tmp152_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp152_AST);
				match(LITERAL_flush);
				break;
			}
			case LITERAL_interval:
			{
				AST tmp153_AST = null;
				tmp153_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp153_AST);
				match(LITERAL_interval);
				break;
			}
			case LITERAL_transaction:
			{
				AST tmp154_AST = null;
				tmp154_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp154_AST);
				match(LITERAL_transaction);
				break;
			}
			case LITERAL_session:
			{
				AST tmp155_AST = null;
				tmp155_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp155_AST);
				match(LITERAL_session);
				break;
			}
			case LITERAL_close:
			{
				AST tmp156_AST = null;
				tmp156_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp156_AST);
				match(LITERAL_close);
				break;
			}
			case LITERAL_read:
			{
				AST tmp157_AST = null;
				tmp157_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp157_AST);
				match(LITERAL_read);
				break;
			}
			case LITERAL_immediate:
			{
				AST tmp158_AST = null;
				tmp158_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp158_AST);
				match(LITERAL_immediate);
				break;
			}
			case LITERAL_replace:
			{
				AST tmp159_AST = null;
				tmp159_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp159_AST);
				match(LITERAL_replace);
				break;
			}
			case LITERAL_sid:
			{
				AST tmp160_AST = null;
				tmp160_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp160_AST);
				match(LITERAL_sid);
				break;
			}
			case LITERAL_local:
			{
				AST tmp161_AST = null;
				tmp161_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp161_AST);
				match(LITERAL_local);
				break;
			}
			case LITERAL_time:
			{
				AST tmp162_AST = null;
				tmp162_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp162_AST);
				match(LITERAL_time);
				break;
			}
			case LITERAL_name:
			{
				AST tmp163_AST = null;
				tmp163_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp163_AST);
				match(LITERAL_name);
				break;
			}
			case LITERAL_default:
			{
				AST tmp164_AST = null;
				tmp164_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp164_AST);
				match(LITERAL_default);
				break;
			}
			case LITERAL_package:
			{
				AST tmp165_AST = null;
				tmp165_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp165_AST);
				match(LITERAL_package);
				break;
			}
			case LITERAL_ref:
			{
				AST tmp166_AST = null;
				tmp166_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp166_AST);
				match(LITERAL_ref);
				break;
			}
			case LITERAL_byte:
			{
				AST tmp167_AST = null;
				tmp167_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp167_AST);
				match(LITERAL_byte);
				break;
			}
			case LITERAL_interface:
			{
				AST tmp168_AST = null;
				tmp168_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp168_AST);
				match(LITERAL_interface);
				break;
			}
			case LITERAL_extract:
			{
				AST tmp169_AST = null;
				tmp169_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp169_AST);
				match(LITERAL_extract);
				break;
			}
			case LITERAL_next:
			{
				AST tmp170_AST = null;
				tmp170_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp170_AST);
				match(LITERAL_next);
				break;
			}
			case LITERAL_column:
			{
				AST tmp171_AST = null;
				tmp171_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp171_AST);
				match(LITERAL_column);
				break;
			}
			case LITERAL_col:
			{
				AST tmp172_AST = null;
				tmp172_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp172_AST);
				match(LITERAL_col);
				break;
			}
			case LITERAL_timestamp:
			{
				AST tmp173_AST = null;
				tmp173_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp173_AST);
				match(LITERAL_timestamp);
				break;
			}
			case LITERAL_found:
			{
				AST tmp174_AST = null;
				tmp174_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp174_AST);
				match(LITERAL_found);
				break;
			}
			case LITERAL_notfound:
			{
				AST tmp175_AST = null;
				tmp175_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp175_AST);
				match(LITERAL_notfound);
				break;
			}
			case LITERAL_rowcount:
			{
				AST tmp176_AST = null;
				tmp176_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp176_AST);
				match(LITERAL_rowcount);
				break;
			}
			case LITERAL_isopen:
			{
				AST tmp177_AST = null;
				tmp177_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp177_AST);
				match(LITERAL_isopen);
				break;
			}
			case LITERAL_bulk_rowcount:
			{
				AST tmp178_AST = null;
				tmp178_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp178_AST);
				match(LITERAL_bulk_rowcount);
				break;
			}
			case LITERAL_bulk_exceptions:
			{
				AST tmp179_AST = null;
				tmp179_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp179_AST);
				match(LITERAL_bulk_exceptions);
				break;
			}
			case LITERAL_nocache:
			{
				AST tmp180_AST = null;
				tmp180_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp180_AST);
				match(LITERAL_nocache);
				break;
			}
			case LITERAL_cache:
			{
				AST tmp181_AST = null;
				tmp181_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp181_AST);
				match(LITERAL_cache);
				break;
			}
			case LITERAL_compress:
			{
				AST tmp182_AST = null;
				tmp182_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp182_AST);
				match(LITERAL_compress);
				break;
			}
			case LITERAL_deterministic:
			{
				AST tmp183_AST = null;
				tmp183_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp183_AST);
				match(LITERAL_deterministic);
				break;
			}
			case LITERAL_degree:
			{
				AST tmp184_AST = null;
				tmp184_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp184_AST);
				match(LITERAL_degree);
				break;
			}
			case LITERAL_instances:
			{
				AST tmp185_AST = null;
				tmp185_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp185_AST);
				match(LITERAL_instances);
				break;
			}
			case LITERAL_range:
			{
				AST tmp186_AST = null;
				tmp186_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp186_AST);
				match(LITERAL_range);
				break;
			}
			case LITERAL_parallel:
			{
				AST tmp187_AST = null;
				tmp187_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp187_AST);
				match(LITERAL_parallel);
				break;
			}
			case LITERAL_noparallel:
			{
				AST tmp188_AST = null;
				tmp188_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp188_AST);
				match(LITERAL_noparallel);
				break;
			}
			case LITERAL_year:
			{
				AST tmp189_AST = null;
				tmp189_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp189_AST);
				match(LITERAL_year);
				break;
			}
			case LITERAL_month:
			{
				AST tmp190_AST = null;
				tmp190_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp190_AST);
				match(LITERAL_month);
				break;
			}
			case LITERAL_day:
			{
				AST tmp191_AST = null;
				tmp191_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp191_AST);
				match(LITERAL_day);
				break;
			}
			case LITERAL_row:
			{
				AST tmp192_AST = null;
				tmp192_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp192_AST);
				match(LITERAL_row);
				break;
			}
			case LITERAL_buffer_pool:
			{
				AST tmp193_AST = null;
				tmp193_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp193_AST);
				match(LITERAL_buffer_pool);
				break;
			}
			case LITERAL_system:
			{
				AST tmp194_AST = null;
				tmp194_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp194_AST);
				match(LITERAL_system);
				break;
			}
			case LITERAL_error_code:
			{
				AST tmp195_AST = null;
				tmp195_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp195_AST);
				match(LITERAL_error_code);
				break;
			}
			case LITERAL_error_index:
			{
				AST tmp196_AST = null;
				tmp196_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp196_AST);
				match(LITERAL_error_index);
				break;
			}
			case LITERAL_temporary:
			{
				AST tmp197_AST = null;
				tmp197_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp197_AST);
				match(LITERAL_temporary);
				break;
			}
			case LITERAL_aggregate:
			{
				AST tmp198_AST = null;
				tmp198_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp198_AST);
				match(LITERAL_aggregate);
				break;
			}
			case LITERAL_current:
			{
				AST tmp199_AST = null;
				tmp199_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp199_AST);
				match(LITERAL_current);
				break;
			}
			case LITERAL_sqlcode:
			{
				AST tmp200_AST = null;
				tmp200_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp200_AST);
				match(LITERAL_sqlcode);
				break;
			}
			case LITERAL_sqlerrm:
			{
				AST tmp201_AST = null;
				tmp201_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp201_AST);
				match(LITERAL_sqlerrm);
				break;
			}
			case LITERAL_force:
			{
				AST tmp202_AST = null;
				tmp202_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp202_AST);
				match(LITERAL_force);
				break;
			}
			case LITERAL_cascade:
			{
				AST tmp203_AST = null;
				tmp203_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp203_AST);
				match(LITERAL_cascade);
				break;
			}
			case LITERAL_constraints:
			{
				AST tmp204_AST = null;
				tmp204_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp204_AST);
				match(LITERAL_constraints);
				break;
			}
			case LITERAL_purge:
			{
				AST tmp205_AST = null;
				tmp205_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp205_AST);
				match(LITERAL_purge);
				break;
			}
			case LITERAL_validate:
			{
				AST tmp206_AST = null;
				tmp206_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp206_AST);
				match(LITERAL_validate);
				break;
			}
			case LITERAL_nextval:
			{
				AST tmp207_AST = null;
				tmp207_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp207_AST);
				match(LITERAL_nextval);
				break;
			}
			case LITERAL_currval:
			{
				AST tmp208_AST = null;
				tmp208_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp208_AST);
				match(LITERAL_currval);
				break;
			}
			case LITERAL_rows:
			{
				AST tmp209_AST = null;
				tmp209_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp209_AST);
				match(LITERAL_rows);
				break;
			}
			case LITERAL_foreign:
			{
				AST tmp210_AST = null;
				tmp210_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp210_AST);
				match(LITERAL_foreign);
				break;
			}
			case LITERAL_primary:
			{
				AST tmp211_AST = null;
				tmp211_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp211_AST);
				match(LITERAL_primary);
				break;
			}
			case LITERAL_records:
			{
				AST tmp212_AST = null;
				tmp212_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp212_AST);
				match(LITERAL_records);
				break;
			}
			case LITERAL_parameters:
			{
				AST tmp213_AST = null;
				tmp213_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp213_AST);
				match(LITERAL_parameters);
				break;
			}
			case LITERAL_access:
			{
				AST tmp214_AST = null;
				tmp214_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp214_AST);
				match(LITERAL_access);
				break;
			}
			case LITERAL_newline:
			{
				AST tmp215_AST = null;
				tmp215_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp215_AST);
				match(LITERAL_newline);
				break;
			}
			case LITERAL_delimited:
			{
				AST tmp216_AST = null;
				tmp216_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp216_AST);
				match(LITERAL_delimited);
				break;
			}
			case LITERAL_fixed:
			{
				AST tmp217_AST = null;
				tmp217_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp217_AST);
				match(LITERAL_fixed);
				break;
			}
			case LITERAL_characterset:
			{
				AST tmp218_AST = null;
				tmp218_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp218_AST);
				match(LITERAL_characterset);
				break;
			}
			case LITERAL_big:
			{
				AST tmp219_AST = null;
				tmp219_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp219_AST);
				match(LITERAL_big);
				break;
			}
			case LITERAL_little:
			{
				AST tmp220_AST = null;
				tmp220_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp220_AST);
				match(LITERAL_little);
				break;
			}
			case LITERAL_endian:
			{
				AST tmp221_AST = null;
				tmp221_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp221_AST);
				match(LITERAL_endian);
				break;
			}
			case LITERAL_mark:
			{
				AST tmp222_AST = null;
				tmp222_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp222_AST);
				match(LITERAL_mark);
				break;
			}
			case LITERAL_check:
			{
				AST tmp223_AST = null;
				tmp223_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp223_AST);
				match(LITERAL_check);
				break;
			}
			case LITERAL_nocheck:
			{
				AST tmp224_AST = null;
				tmp224_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp224_AST);
				match(LITERAL_nocheck);
				break;
			}
			case LITERAL_string:
			{
				AST tmp225_AST = null;
				tmp225_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp225_AST);
				match(LITERAL_string);
				break;
			}
			case LITERAL_sizes:
			{
				AST tmp226_AST = null;
				tmp226_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp226_AST);
				match(LITERAL_sizes);
				break;
			}
			case LITERAL_bytes:
			{
				AST tmp227_AST = null;
				tmp227_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp227_AST);
				match(LITERAL_bytes);
				break;
			}
			case LITERAL_load:
			{
				AST tmp228_AST = null;
				tmp228_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp228_AST);
				match(LITERAL_load);
				break;
			}
			case LITERAL_nobadfile:
			{
				AST tmp229_AST = null;
				tmp229_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp229_AST);
				match(LITERAL_nobadfile);
				break;
			}
			case LITERAL_badfile:
			{
				AST tmp230_AST = null;
				tmp230_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp230_AST);
				match(LITERAL_badfile);
				break;
			}
			case LITERAL_nodiscardfile:
			{
				AST tmp231_AST = null;
				tmp231_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp231_AST);
				match(LITERAL_nodiscardfile);
				break;
			}
			case LITERAL_discardfile:
			{
				AST tmp232_AST = null;
				tmp232_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp232_AST);
				match(LITERAL_discardfile);
				break;
			}
			case LITERAL_nologfile:
			{
				AST tmp233_AST = null;
				tmp233_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp233_AST);
				match(LITERAL_nologfile);
				break;
			}
			case LITERAL_logfile:
			{
				AST tmp234_AST = null;
				tmp234_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp234_AST);
				match(LITERAL_logfile);
				break;
			}
			case LITERAL_readsize:
			{
				AST tmp235_AST = null;
				tmp235_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp235_AST);
				match(LITERAL_readsize);
				break;
			}
			case LITERAL_skip:
			{
				AST tmp236_AST = null;
				tmp236_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp236_AST);
				match(LITERAL_skip);
				break;
			}
			case LITERAL_data_cache:
			{
				AST tmp237_AST = null;
				tmp237_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp237_AST);
				match(LITERAL_data_cache);
				break;
			}
			case LITERAL_fields:
			{
				AST tmp238_AST = null;
				tmp238_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp238_AST);
				match(LITERAL_fields);
				break;
			}
			case LITERAL_missing:
			{
				AST tmp239_AST = null;
				tmp239_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp239_AST);
				match(LITERAL_missing);
				break;
			}
			case LITERAL_field:
			{
				AST tmp240_AST = null;
				tmp240_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp240_AST);
				match(LITERAL_field);
				break;
			}
			case LITERAL_reject:
			{
				AST tmp241_AST = null;
				tmp241_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp241_AST);
				match(LITERAL_reject);
				break;
			}
			case LITERAL_with:
			{
				AST tmp242_AST = null;
				tmp242_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp242_AST);
				match(LITERAL_with);
				break;
			}
			case LITERAL_lrtrim:
			{
				AST tmp243_AST = null;
				tmp243_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp243_AST);
				match(LITERAL_lrtrim);
				break;
			}
			case LITERAL_notrim:
			{
				AST tmp244_AST = null;
				tmp244_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp244_AST);
				match(LITERAL_notrim);
				break;
			}
			case LITERAL_ltrim:
			{
				AST tmp245_AST = null;
				tmp245_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp245_AST);
				match(LITERAL_ltrim);
				break;
			}
			case LITERAL_rtrim:
			{
				AST tmp246_AST = null;
				tmp246_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp246_AST);
				match(LITERAL_rtrim);
				break;
			}
			case LITERAL_ldtrim:
			{
				AST tmp247_AST = null;
				tmp247_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp247_AST);
				match(LITERAL_ldtrim);
				break;
			}
			case LITERAL_position:
			{
				AST tmp248_AST = null;
				tmp248_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp248_AST);
				match(LITERAL_position);
				break;
			}
			case LITERAL_enclosed:
			{
				AST tmp249_AST = null;
				tmp249_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp249_AST);
				match(LITERAL_enclosed);
				break;
			}
			case LITERAL_date_format:
			{
				AST tmp250_AST = null;
				tmp250_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp250_AST);
				match(LITERAL_date_format);
				break;
			}
			case LITERAL_varraw:
			{
				AST tmp251_AST = null;
				tmp251_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp251_AST);
				match(LITERAL_varraw);
				break;
			}
			case LITERAL_varcharc:
			{
				AST tmp252_AST = null;
				tmp252_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp252_AST);
				match(LITERAL_varcharc);
				break;
			}
			case LITERAL_varrawc:
			{
				AST tmp253_AST = null;
				tmp253_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp253_AST);
				match(LITERAL_varrawc);
				break;
			}
			case LITERAL_oracle_number:
			{
				AST tmp254_AST = null;
				tmp254_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp254_AST);
				match(LITERAL_oracle_number);
				break;
			}
			case LITERAL_oracle_date:
			{
				AST tmp255_AST = null;
				tmp255_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp255_AST);
				match(LITERAL_oracle_date);
				break;
			}
			case LITERAL_counted:
			{
				AST tmp256_AST = null;
				tmp256_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp256_AST);
				match(LITERAL_counted);
				break;
			}
			case LITERAL_external:
			{
				AST tmp257_AST = null;
				tmp257_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp257_AST);
				match(LITERAL_external);
				break;
			}
			case LITERAL_zoned:
			{
				AST tmp258_AST = null;
				tmp258_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp258_AST);
				match(LITERAL_zoned);
				break;
			}
			case LITERAL_unsigned:
			{
				AST tmp259_AST = null;
				tmp259_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp259_AST);
				match(LITERAL_unsigned);
				break;
			}
			case LITERAL_location:
			{
				AST tmp260_AST = null;
				tmp260_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp260_AST);
				match(LITERAL_location);
				break;
			}
			case LITERAL_limit:
			{
				AST tmp261_AST = null;
				tmp261_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp261_AST);
				match(LITERAL_limit);
				break;
			}
			case LITERAL_unlimited:
			{
				AST tmp262_AST = null;
				tmp262_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp262_AST);
				match(LITERAL_unlimited);
				break;
			}
			case LITERAL_concat:
			{
				AST tmp263_AST = null;
				tmp263_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp263_AST);
				match(LITERAL_concat);
				break;
			}
			case LITERAL_clob:
			{
				AST tmp264_AST = null;
				tmp264_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp264_AST);
				match(LITERAL_clob);
				break;
			}
			case LITERAL_blob:
			{
				AST tmp265_AST = null;
				tmp265_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp265_AST);
				match(LITERAL_blob);
				break;
			}
			case LITERAL_lobfile:
			{
				AST tmp266_AST = null;
				tmp266_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp266_AST);
				match(LITERAL_lobfile);
				break;
			}
			case LITERAL_float:
			{
				AST tmp267_AST = null;
				tmp267_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp267_AST);
				match(LITERAL_float);
				break;
			}
			case LITERAL_preprocessor:
			{
				AST tmp268_AST = null;
				tmp268_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp268_AST);
				match(LITERAL_preprocessor);
				break;
			}
			case LITERAL_compression:
			{
				AST tmp269_AST = null;
				tmp269_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp269_AST);
				match(LITERAL_compression);
				break;
			}
			case LITERAL_enabled:
			{
				AST tmp270_AST = null;
				tmp270_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp270_AST);
				match(LITERAL_enabled);
				break;
			}
			case LITERAL_disabled:
			{
				AST tmp271_AST = null;
				tmp271_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp271_AST);
				match(LITERAL_disabled);
				break;
			}
			case LITERAL_encryption:
			{
				AST tmp272_AST = null;
				tmp272_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp272_AST);
				match(LITERAL_encryption);
				break;
			}
			case LITERAL_encrypt:
			{
				AST tmp273_AST = null;
				tmp273_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp273_AST);
				match(LITERAL_encrypt);
				break;
			}
			case LITERAL_action:
			{
				AST tmp274_AST = null;
				tmp274_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp274_AST);
				match(LITERAL_action);
				break;
			}
			case LITERAL_version:
			{
				AST tmp275_AST = null;
				tmp275_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp275_AST);
				match(LITERAL_version);
				break;
			}
			case LITERAL_compatible:
			{
				AST tmp276_AST = null;
				tmp276_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp276_AST);
				match(LITERAL_compatible);
				break;
			}
			case LITERAL_data:
			{
				AST tmp277_AST = null;
				tmp277_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp277_AST);
				match(LITERAL_data);
				break;
			}
			case LITERAL_no:
			{
				AST tmp278_AST = null;
				tmp278_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp278_AST);
				match(LITERAL_no);
				break;
			}
			case LITERAL_initrans:
			{
				AST tmp279_AST = null;
				tmp279_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp279_AST);
				match(LITERAL_initrans);
				break;
			}
			case LITERAL_maxtrans:
			{
				AST tmp280_AST = null;
				tmp280_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp280_AST);
				match(LITERAL_maxtrans);
				break;
			}
			case LITERAL_logging:
			{
				AST tmp281_AST = null;
				tmp281_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp281_AST);
				match(LITERAL_logging);
				break;
			}
			case LITERAL_nologging:
			{
				AST tmp282_AST = null;
				tmp282_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp282_AST);
				match(LITERAL_nologging);
				break;
			}
			case LITERAL_quit:
			{
				AST tmp283_AST = null;
				tmp283_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp283_AST);
				match(LITERAL_quit);
				break;
			}
			case LITERAL_spool:
			{
				AST tmp284_AST = null;
				tmp284_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp284_AST);
				match(LITERAL_spool);
				break;
			}
			case LITERAL_def:
			{
				AST tmp285_AST = null;
				tmp285_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp285_AST);
				match(LITERAL_def);
				break;
			}
			case LITERAL_define:
			{
				AST tmp286_AST = null;
				tmp286_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp286_AST);
				match(LITERAL_define);
				break;
			}
			case LITERAL_novalidate:
			{
				AST tmp287_AST = null;
				tmp287_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp287_AST);
				match(LITERAL_novalidate);
				break;
			}
			case LITERAL_heap:
			{
				AST tmp288_AST = null;
				tmp288_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp288_AST);
				match(LITERAL_heap);
				break;
			}
			case LITERAL_freelists:
			{
				AST tmp289_AST = null;
				tmp289_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp289_AST);
				match(LITERAL_freelists);
				break;
			}
			case LITERAL_freelist:
			{
				AST tmp290_AST = null;
				tmp290_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp290_AST);
				match(LITERAL_freelist);
				break;
			}
			case LITERAL_organization:
			{
				AST tmp291_AST = null;
				tmp291_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp291_AST);
				match(LITERAL_organization);
				break;
			}
			case LITERAL_rely:
			{
				AST tmp292_AST = null;
				tmp292_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp292_AST);
				match(LITERAL_rely);
				break;
			}
			case LITERAL_at:
			{
				AST tmp293_AST = null;
				tmp293_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp293_AST);
				match(LITERAL_at);
				break;
			}
			case LITERAL_on:
			{
				AST tmp294_AST = null;
				tmp294_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp294_AST);
				match(LITERAL_on);
				break;
			}
			case LITERAL_off:
			{
				AST tmp295_AST = null;
				tmp295_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp295_AST);
				match(LITERAL_off);
				break;
			}
			case LITERAL_enable:
			{
				AST tmp296_AST = null;
				tmp296_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp296_AST);
				match(LITERAL_enable);
				break;
			}
			case LITERAL_disable:
			{
				AST tmp297_AST = null;
				tmp297_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp297_AST);
				match(LITERAL_disable);
				break;
			}
			case LITERAL_sql:
			{
				AST tmp298_AST = null;
				tmp298_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp298_AST);
				match(LITERAL_sql);
				break;
			}
			case LITERAL_before:
			{
				AST tmp299_AST = null;
				tmp299_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp299_AST);
				match(LITERAL_before);
				break;
			}
			case LITERAL_after:
			{
				AST tmp300_AST = null;
				tmp300_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp300_AST);
				match(LITERAL_after);
				break;
			}
			case LITERAL_directory:
			{
				AST tmp301_AST = null;
				tmp301_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp301_AST);
				match(LITERAL_directory);
				break;
			}
			case LITERAL_mask:
			{
				AST tmp302_AST = null;
				tmp302_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp302_AST);
				match(LITERAL_mask);
				break;
			}
			case LITERAL_terminated:
			{
				AST tmp303_AST = null;
				tmp303_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp303_AST);
				match(LITERAL_terminated);
				break;
			}
			case LITERAL_whitespace:
			{
				AST tmp304_AST = null;
				tmp304_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp304_AST);
				match(LITERAL_whitespace);
				break;
			}
			case LITERAL_optionally:
			{
				AST tmp305_AST = null;
				tmp305_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp305_AST);
				match(LITERAL_optionally);
				break;
			}
			case LITERAL_option:
			{
				AST tmp306_AST = null;
				tmp306_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp306_AST);
				match(LITERAL_option);
				break;
			}
			case LITERAL_operations:
			{
				AST tmp307_AST = null;
				tmp307_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp307_AST);
				match(LITERAL_operations);
				break;
			}
			case LITERAL_startup:
			{
				AST tmp308_AST = null;
				tmp308_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp308_AST);
				match(LITERAL_startup);
				break;
			}
			case LITERAL_shutdown:
			{
				AST tmp309_AST = null;
				tmp309_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp309_AST);
				match(LITERAL_shutdown);
				break;
			}
			case LITERAL_servererror:
			{
				AST tmp310_AST = null;
				tmp310_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp310_AST);
				match(LITERAL_servererror);
				break;
			}
			case LITERAL_logon:
			{
				AST tmp311_AST = null;
				tmp311_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp311_AST);
				match(LITERAL_logon);
				break;
			}
			case LITERAL_logoff:
			{
				AST tmp312_AST = null;
				tmp312_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp312_AST);
				match(LITERAL_logoff);
				break;
			}
			case LITERAL_associate:
			{
				AST tmp313_AST = null;
				tmp313_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp313_AST);
				match(LITERAL_associate);
				break;
			}
			case LITERAL_statistics:
			{
				AST tmp314_AST = null;
				tmp314_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp314_AST);
				match(LITERAL_statistics);
				break;
			}
			case LITERAL_audit:
			{
				AST tmp315_AST = null;
				tmp315_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp315_AST);
				match(LITERAL_audit);
				break;
			}
			case LITERAL_noaudit:
			{
				AST tmp316_AST = null;
				tmp316_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp316_AST);
				match(LITERAL_noaudit);
				break;
			}
			case LITERAL_ddl:
			{
				AST tmp317_AST = null;
				tmp317_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp317_AST);
				match(LITERAL_ddl);
				break;
			}
			case LITERAL_diassociate:
			{
				AST tmp318_AST = null;
				tmp318_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp318_AST);
				match(LITERAL_diassociate);
				break;
			}
			case LITERAL_grant:
			{
				AST tmp319_AST = null;
				tmp319_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp319_AST);
				match(LITERAL_grant);
				break;
			}
			case LITERAL_rename:
			{
				AST tmp320_AST = null;
				tmp320_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp320_AST);
				match(LITERAL_rename);
				break;
			}
			case LITERAL_truncate:
			{
				AST tmp321_AST = null;
				tmp321_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp321_AST);
				match(LITERAL_truncate);
				break;
			}
			case LITERAL_revoke:
			{
				AST tmp322_AST = null;
				tmp322_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp322_AST);
				match(LITERAL_revoke);
				break;
			}
			case LITERAL_new:
			{
				AST tmp323_AST = null;
				tmp323_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp323_AST);
				match(LITERAL_new);
				break;
			}
			case LITERAL_old:
			{
				AST tmp324_AST = null;
				tmp324_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp324_AST);
				match(LITERAL_old);
				break;
			}
			case LITERAL_schema:
			{
				AST tmp325_AST = null;
				tmp325_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp325_AST);
				match(LITERAL_schema);
				break;
			}
			case LITERAL_hash:
			{
				AST tmp326_AST = null;
				tmp326_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp326_AST);
				match(LITERAL_hash);
				break;
			}
			case LITERAL_precision:
			{
				AST tmp327_AST = null;
				tmp327_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp327_AST);
				match(LITERAL_precision);
				break;
			}
			case LITERAL_key:
			{
				AST tmp328_AST = null;
				tmp328_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp328_AST);
				match(LITERAL_key);
				break;
			}
			case LITERAL_monitoring:
			{
				AST tmp329_AST = null;
				tmp329_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp329_AST);
				match(LITERAL_monitoring);
				break;
			}
			case LITERAL_collect:
			{
				AST tmp330_AST = null;
				tmp330_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp330_AST);
				match(LITERAL_collect);
				break;
			}
			case LITERAL_nulls:
			{
				AST tmp331_AST = null;
				tmp331_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp331_AST);
				match(LITERAL_nulls);
				break;
			}
			case LITERAL_first:
			{
				AST tmp332_AST = null;
				tmp332_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp332_AST);
				match(LITERAL_first);
				break;
			}
			case LITERAL_last:
			{
				AST tmp333_AST = null;
				tmp333_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp333_AST);
				match(LITERAL_last);
				break;
			}
			case LITERAL_timezone:
			{
				AST tmp334_AST = null;
				tmp334_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp334_AST);
				match(LITERAL_timezone);
				break;
			}
			case LITERAL_language:
			{
				AST tmp335_AST = null;
				tmp335_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp335_AST);
				match(LITERAL_language);
				break;
			}
			case LITERAL_java:
			{
				AST tmp336_AST = null;
				tmp336_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp336_AST);
				match(LITERAL_java);
				break;
			}
			case LITERAL_store:
			{
				AST tmp337_AST = null;
				tmp337_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp337_AST);
				match(LITERAL_store);
				break;
			}
			case LITERAL_library:
			{
				AST tmp338_AST = null;
				tmp338_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp338_AST);
				match(LITERAL_library);
				break;
			}
			case LITERAL_role:
			{
				AST tmp339_AST = null;
				tmp339_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp339_AST);
				match(LITERAL_role);
				break;
			}
			case LITERAL_online:
			{
				AST tmp340_AST = null;
				tmp340_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp340_AST);
				match(LITERAL_online);
				break;
			}
			case LITERAL_compute:
			{
				AST tmp341_AST = null;
				tmp341_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp341_AST);
				match(LITERAL_compute);
				break;
			}
			case LITERAL_continue:
			{
				AST tmp342_AST = null;
				tmp342_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp342_AST);
				match(LITERAL_continue);
				break;
			}
			case LITERAL_var:
			{
				AST tmp343_AST = null;
				tmp343_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp343_AST);
				match(LITERAL_var);
				break;
			}
			case LITERAL_variable:
			{
				AST tmp344_AST = null;
				tmp344_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp344_AST);
				match(LITERAL_variable);
				break;
			}
			case LITERAL_none:
			{
				AST tmp345_AST = null;
				tmp345_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp345_AST);
				match(LITERAL_none);
				break;
			}
			case LITERAL_oserror:
			{
				AST tmp346_AST = null;
				tmp346_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp346_AST);
				match(LITERAL_oserror);
				break;
			}
			case LITERAL_sqlerror:
			{
				AST tmp347_AST = null;
				tmp347_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp347_AST);
				match(LITERAL_sqlerror);
				break;
			}
			case LITERAL_whenever:
			{
				AST tmp348_AST = null;
				tmp348_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp348_AST);
				match(LITERAL_whenever);
				break;
			}
			case LITERAL_the:
			{
				AST tmp349_AST = null;
				tmp349_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp349_AST);
				match(LITERAL_the);
				break;
			}
			case LITERAL_identified:
			{
				AST tmp350_AST = null;
				tmp350_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp350_AST);
				match(LITERAL_identified);
				break;
			}
			case LITERAL_link:
			{
				AST tmp351_AST = null;
				tmp351_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp351_AST);
				match(LITERAL_link);
				break;
			}
			case LITERAL_by:
			{
				AST tmp352_AST = null;
				tmp352_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp352_AST);
				match(LITERAL_by);
				break;
			}
			case LITERAL_noorder:
			{
				AST tmp353_AST = null;
				tmp353_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp353_AST);
				match(LITERAL_noorder);
				break;
			}
			case LITERAL_maxvalue:
			{
				AST tmp354_AST = null;
				tmp354_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp354_AST);
				match(LITERAL_maxvalue);
				break;
			}
			case LITERAL_minvalue:
			{
				AST tmp355_AST = null;
				tmp355_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp355_AST);
				match(LITERAL_minvalue);
				break;
			}
			case LITERAL_increment:
			{
				AST tmp356_AST = null;
				tmp356_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp356_AST);
				match(LITERAL_increment);
				break;
			}
			case LITERAL_cycle:
			{
				AST tmp357_AST = null;
				tmp357_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp357_AST);
				match(LITERAL_cycle);
				break;
			}
			case LITERAL_nocycle:
			{
				AST tmp358_AST = null;
				tmp358_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp358_AST);
				match(LITERAL_nocycle);
				break;
			}
			case LITERAL_pctthreshold:
			{
				AST tmp359_AST = null;
				tmp359_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp359_AST);
				match(LITERAL_pctthreshold);
				break;
			}
			case LITERAL_including:
			{
				AST tmp360_AST = null;
				tmp360_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp360_AST);
				match(LITERAL_including);
				break;
			}
			case LITERAL_repheader:
			{
				AST tmp361_AST = null;
				tmp361_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp361_AST);
				match(LITERAL_repheader);
				break;
			}
			case LITERAL_repfooter:
			{
				AST tmp362_AST = null;
				tmp362_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp362_AST);
				match(LITERAL_repfooter);
				break;
			}
			case LITERAL_serveroutput:
			{
				AST tmp363_AST = null;
				tmp363_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp363_AST);
				match(LITERAL_serveroutput);
				break;
			}
			case LITERAL_groups:
			{
				AST tmp364_AST = null;
				tmp364_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp364_AST);
				match(LITERAL_groups);
				break;
			}
			case LITERAL_wait:
			{
				AST tmp365_AST = null;
				tmp365_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp365_AST);
				match(LITERAL_wait);
				break;
			}
			case LITERAL_indices:
			{
				AST tmp366_AST = null;
				tmp366_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp366_AST);
				match(LITERAL_indices);
				break;
			}
			default:
				if ((LA(1)==LITERAL_execute) && (_tokenSet_38.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp367_AST = null;
					tmp367_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp367_AST);
					match(LITERAL_execute);
				}
				else if ((LA(1)==LITERAL_execute) && (_tokenSet_38.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp368_AST = null;
					tmp368_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp368_AST);
					match(LITERAL_execute);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			identifier2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_38);
			} else {
			  throw ex;
			}
		}
		returnAST = identifier2_AST;
	}
	
	public void sqlplus_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sqlplus_command_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_set:
			{
				{
				AST tmp369_AST = null;
				tmp369_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp369_AST);
				match(LITERAL_set);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_39.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_long)) {
					AST tmp370_AST = null;
					tmp370_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp370_AST);
					match(LITERAL_long);
				}
				else if ((_tokenSet_39.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop85:
				do {
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case QUOTED_STR:
					{
						string_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
						if ((_tokenSet_5.member(LA(1))) && (_tokenSet_39.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
					else {
						break _loop85;
					}
					}
				} while (true);
				}
				{
				_loop87:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop87;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SET);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_show:
			{
				{
				AST tmp372_AST = null;
				tmp372_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp372_AST);
				match(LITERAL_show);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop90:
				do {
					if ((_tokenSet_40.member(LA(1))) && (_tokenSet_41.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						base_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop90;
					}
					
				} while (true);
				}
				{
				_loop92:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop92;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SHOW);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_var:
			case LITERAL_variable:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_var:
				{
					AST tmp374_AST = null;
					tmp374_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp374_AST);
					match(LITERAL_var);
					break;
				}
				case LITERAL_variable:
				{
					AST tmp375_AST = null;
					tmp375_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp375_AST);
					match(LITERAL_variable);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				datatype();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop96:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop96;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_VARIABLE);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_column:
			case LITERAL_col:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_col:
				{
					AST tmp377_AST = null;
					tmp377_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp377_AST);
					match(LITERAL_col);
					break;
				}
				case LITERAL_column:
				{
					AST tmp378_AST = null;
					tmp378_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp378_AST);
					match(LITERAL_column);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop100:
				do {
					if ((_tokenSet_5.member(LA(1))) && (_tokenSet_42.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop100;
					}
					
				} while (true);
				}
				{
				_loop102:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop102;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_COLUMN);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_exec:
			case LITERAL_execute:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_exec:
				{
					AST tmp380_AST = null;
					tmp380_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp380_AST);
					match(LITERAL_exec);
					break;
				}
				case LITERAL_execute:
				{
					AST tmp381_AST = null;
					tmp381_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp381_AST);
					match(LITERAL_execute);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				sqlplus_exec_statement();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop106:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop106;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_EXEC);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_whenever:
			{
				{
				AST tmp383_AST = null;
				tmp383_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp383_AST);
				match(LITERAL_whenever);
				{
				switch ( LA(1)) {
				case LITERAL_sqlerror:
				{
					AST tmp384_AST = null;
					tmp384_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp384_AST);
					match(LITERAL_sqlerror);
					break;
				}
				case LITERAL_oserror:
				{
					AST tmp385_AST = null;
					tmp385_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp385_AST);
					match(LITERAL_oserror);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_exit:
				{
					AST tmp386_AST = null;
					tmp386_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp386_AST);
					match(LITERAL_exit);
					break;
				}
				case LITERAL_continue:
				{
					AST tmp387_AST = null;
					tmp387_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp387_AST);
					match(LITERAL_continue);
					break;
				}
				case LITERAL_commit:
				{
					AST tmp388_AST = null;
					tmp388_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp388_AST);
					match(LITERAL_commit);
					break;
				}
				case LITERAL_rollback:
				{
					AST tmp389_AST = null;
					tmp389_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp389_AST);
					match(LITERAL_rollback);
					break;
				}
				case LITERAL_none:
				{
					AST tmp390_AST = null;
					tmp390_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp390_AST);
					match(LITERAL_none);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop111:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop111;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_WHENEVER);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_def:
			case LITERAL_define:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_def:
				{
					AST tmp392_AST = null;
					tmp392_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp392_AST);
					match(LITERAL_def);
					break;
				}
				case LITERAL_define:
				{
					AST tmp393_AST = null;
					tmp393_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp393_AST);
					match(LITERAL_define);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case EQ:
				{
					AST tmp394_AST = null;
					tmp394_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp394_AST);
					match(EQ);
					{
					if ((_tokenSet_43.member(LA(1))) && (_tokenSet_44.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						AST tmp395_AST = null;
						tmp395_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp395_AST);
						match(DOUBLE_QUOTED_STRING);
					}
					else if ((LA(1)==STORAGE_SIZE)) {
						AST tmp396_AST = null;
						tmp396_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp396_AST);
						match(STORAGE_SIZE);
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_DEFINE);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_prompt:
			{
				{
				AST tmp397_AST = null;
				tmp397_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp397_AST);
				match(LITERAL_prompt);
				{
				_loop118:
				do {
					if ((_tokenSet_45.member(LA(1)))) {
						AST tmp398_AST = null;
						tmp398_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp398_AST);
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop118;
					}
					
				} while (true);
				}
				AST tmp399_AST = null;
				tmp399_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp399_AST);
				match(CUSTOM_TOKEN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_PROMPT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rem:
			{
				AST tmp400_AST = null;
				tmp400_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp400_AST);
				match(LITERAL_rem);
				{
				_loop120:
				do {
					if ((_tokenSet_45.member(LA(1)))) {
						AST tmp401_AST = null;
						tmp401_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp401_AST);
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop120;
					}
					
				} while (true);
				}
				AST tmp402_AST = null;
				tmp402_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp402_AST);
				match(CUSTOM_TOKEN);
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_PROMPT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_exit:
			{
				AST tmp403_AST = null;
				tmp403_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp403_AST);
				match(LITERAL_exit);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if(true){
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_EXIT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_quit:
			{
				{
				AST tmp404_AST = null;
				tmp404_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp404_AST);
				match(LITERAL_quit);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_QUIT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_spool:
			{
				AST tmp406_AST = null;
				tmp406_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp406_AST);
				match(LITERAL_spool);
				{
				if ((LA(1)==LITERAL_off) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp407_AST = null;
					tmp407_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp407_AST);
					match(LITERAL_off);
				}
				else if ((_tokenSet_46.member(LA(1))) && (_tokenSet_47.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					identifier4();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop127:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							identifier4();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop127;
						}
						
					} while (true);
					}
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SPOOL);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_sta:
			case LITERAL_start:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_sta:
				{
					AST tmp409_AST = null;
					tmp409_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp409_AST);
					match(LITERAL_sta);
					break;
				}
				case LITERAL_start:
				{
					AST tmp410_AST = null;
					tmp410_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp410_AST);
					match(LITERAL_start);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				sqlplus_path();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_START);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_repfooter:
			{
				{
				AST tmp411_AST = null;
				tmp411_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp411_AST);
				match(LITERAL_repfooter);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					AST tmp412_AST = null;
					tmp412_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp412_AST);
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
					AST tmp413_AST = null;
					tmp413_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp413_AST);
					match(LITERAL_on);
					break;
				}
				case LITERAL_is:
				{
					{
					AST tmp414_AST = null;
					tmp414_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp414_AST);
					match(LITERAL_is);
					AST tmp415_AST = null;
					tmp415_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp415_AST);
					match(LITERAL_null);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_REPFOOTER);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_repheader:
			{
				{
				AST tmp417_AST = null;
				tmp417_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp417_AST);
				match(LITERAL_repheader);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					AST tmp418_AST = null;
					tmp418_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp418_AST);
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
					AST tmp419_AST = null;
					tmp419_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp419_AST);
					match(LITERAL_on);
					break;
				}
				case LITERAL_is:
				{
					{
					AST tmp420_AST = null;
					tmp420_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp420_AST);
					match(LITERAL_is);
					AST tmp421_AST = null;
					tmp421_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp421_AST);
					match(LITERAL_null);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_REPHEADER);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_serveroutput:
			{
				{
				AST tmp423_AST = null;
				tmp423_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp423_AST);
				match(LITERAL_serveroutput);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					AST tmp424_AST = null;
					tmp424_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp424_AST);
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
					AST tmp425_AST = null;
					tmp425_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp425_AST);
					match(LITERAL_on);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SERVEROUTPUT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_begin:
			case LITERAL_declare:
			{
				{
				begin_block();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DIVIDE:
				{
					match(DIVIDE);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			case AT_PREFIXED:
			{
				{
				AST tmp429_AST = null;
				tmp429_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp429_AST);
				match(AT_PREFIXED);
				{
				_loop148:
				do {
					if ((_tokenSet_45.member(LA(1)))) {
						AST tmp430_AST = null;
						tmp430_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp430_AST);
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop148;
					}
					
				} while (true);
				}
				AST tmp431_AST = null;
				tmp431_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp431_AST);
				match(CUSTOM_TOKEN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_RUNSCRIPT);
				}
				sqlplus_command_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sqlplus_command_AST;
	}
	
	public void base_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST base_expression_AST = null;
		Token  a1 = null;
		AST a1_AST = null;
		Token  a2 = null;
		AST a2_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cast:
			{
				{
				cast_proc();
				astFactory.addASTChild(currentAST, returnAST);
				}
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_case:
			{
				{
				case_expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rank:
			case LITERAL_dense_rank:
			{
				dence_rank_analytics_func();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_all:
			case LITERAL_any:
			{
				{
				switch ( LA(1)) {
				case LITERAL_all:
				{
					a1 = LT(1);
					a1_AST = astFactory.create(a1);
					astFactory.addASTChild(currentAST, a1_AST);
					match(LITERAL_all);
					break;
				}
				case LITERAL_any:
				{
					a2 = LT(1);
					a2_AST = astFactory.create(a2);
					astFactory.addASTChild(currentAST, a2_AST);
					match(LITERAL_any);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case QUOTED_STR:
			{
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_true:
			case LITERAL_false:
			{
				boolean_literal();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_null:
			{
				null_statement();
				astFactory.addASTChild(currentAST, returnAST);
				base_expression_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched984 = false;
				if (((LA(1)==LITERAL_sqlcode) && (_tokenSet_48.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
					int _m984 = mark();
					synPredMatched984 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_sqlcode);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched984 = false;
					}
					rewind(_m984);
					inputState.guessing--;
				}
				if ( synPredMatched984 ) {
					AST tmp432_AST = null;
					tmp432_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp432_AST);
					match(LITERAL_sqlcode);
					if ( inputState.guessing==0 ) {
						__markRule(SQLCODE_SYSVAR);
					}
					base_expression_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched986 = false;
					if (((LA(1)==LITERAL_sqlerrm) && (_tokenSet_48.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
						int _m986 = mark();
						synPredMatched986 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_sqlerrm);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched986 = false;
						}
						rewind(_m986);
						inputState.guessing--;
					}
					if ( synPredMatched986 ) {
						{
						AST tmp433_AST = null;
						tmp433_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp433_AST);
						match(LITERAL_sqlerrm);
						{
						if ((LA(1)==OPEN_PAREN) && (_tokenSet_40.member(LA(2))) && (_tokenSet_49.member(LA(3)))) {
							match(OPEN_PAREN);
							base_expression();
							astFactory.addASTChild(currentAST, returnAST);
							match(CLOSE_PAREN);
						}
						else if ((_tokenSet_48.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
						}
						if ( inputState.guessing==0 ) {
							__markRule(SQLERRM_SYSVAR);
						}
						base_expression_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched990 = false;
						if (((LA(1)==LITERAL_trim) && (LA(2)==OPEN_PAREN) && (_tokenSet_50.member(LA(3))))) {
							int _m990 = mark();
							synPredMatched990 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_trim);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched990 = false;
							}
							rewind(_m990);
							inputState.guessing--;
						}
						if ( synPredMatched990 ) {
							{
							trim_function();
							astFactory.addASTChild(currentAST, returnAST);
							}
							base_expression_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched993 = false;
							if (((LA(1)==LITERAL_count) && (LA(2)==OPEN_PAREN) && (_tokenSet_51.member(LA(3))))) {
								int _m993 = mark();
								synPredMatched993 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_count);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched993 = false;
								}
								rewind(_m993);
								inputState.guessing--;
							}
							if ( synPredMatched993 ) {
								{
								count_function();
								astFactory.addASTChild(currentAST, returnAST);
								}
								base_expression_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched1002 = false;
								if (((LA(1)==LITERAL_sql) && (LA(2)==PERCENTAGE))) {
									int _m1002 = mark();
									synPredMatched1002 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_sql);
										match(PERCENTAGE);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1002 = false;
									}
									rewind(_m1002);
									inputState.guessing--;
								}
								if ( synPredMatched1002 ) {
									sql_percentage();
									astFactory.addASTChild(currentAST, returnAST);
									base_expression_AST = (AST)currentAST.root;
								}
								else {
									boolean synPredMatched1004 = false;
									if (((LA(1)==LITERAL_extract) && (LA(2)==OPEN_PAREN) && (_tokenSet_52.member(LA(3))))) {
										int _m1004 = mark();
										synPredMatched1004 = true;
										inputState.guessing++;
										try {
											{
											extract_date_function();
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1004 = false;
										}
										rewind(_m1004);
										inputState.guessing--;
									}
									if ( synPredMatched1004 ) {
										extract_date_function();
										astFactory.addASTChild(currentAST, returnAST);
										base_expression_AST = (AST)currentAST.root;
									}
									else if ((LA(1)==LITERAL_interval) && (LA(2)==QUOTED_STR) && (_tokenSet_53.member(LA(3)))) {
										{
										match(LITERAL_interval);
										string_literal();
										astFactory.addASTChild(currentAST, returnAST);
										{
										switch ( LA(1)) {
										case LITERAL_second:
										{
											AST tmp437_AST = null;
											tmp437_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp437_AST);
											match(LITERAL_second);
											break;
										}
										case LITERAL_minute:
										{
											AST tmp438_AST = null;
											tmp438_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp438_AST);
											match(LITERAL_minute);
											break;
										}
										case LITERAL_hour:
										{
											AST tmp439_AST = null;
											tmp439_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp439_AST);
											match(LITERAL_hour);
											break;
										}
										case LITERAL_day:
										{
											AST tmp440_AST = null;
											tmp440_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp440_AST);
											match(LITERAL_day);
											break;
										}
										case LITERAL_month:
										{
											AST tmp441_AST = null;
											tmp441_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp441_AST);
											match(LITERAL_month);
											break;
										}
										case LITERAL_year:
										{
											AST tmp442_AST = null;
											tmp442_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp442_AST);
											match(LITERAL_year);
											break;
										}
										default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
										}
										}
										{
										if ((LA(1)==LITERAL_to)) {
											AST tmp443_AST = null;
											tmp443_AST = astFactory.create(LT(1));
											astFactory.addASTChild(currentAST, tmp443_AST);
											match(LITERAL_to);
											{
											switch ( LA(1)) {
											case LITERAL_second:
											{
												AST tmp444_AST = null;
												tmp444_AST = astFactory.create(LT(1));
												astFactory.addASTChild(currentAST, tmp444_AST);
												match(LITERAL_second);
												break;
											}
											case LITERAL_month:
											{
												AST tmp445_AST = null;
												tmp445_AST = astFactory.create(LT(1));
												astFactory.addASTChild(currentAST, tmp445_AST);
												match(LITERAL_month);
												break;
											}
											default:
											{
												throw new NoViableAltException(LT(1), getFilename());
											}
											}
											}
											{
											if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
												match(OPEN_PAREN);
												AST tmp447_AST = null;
												tmp447_AST = astFactory.create(LT(1));
												astFactory.addASTChild(currentAST, tmp447_AST);
												match(NUMBER);
												match(CLOSE_PAREN);
											}
											else if ((_tokenSet_48.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
											}
											else {
												throw new NoViableAltException(LT(1), getFilename());
											}
											
											}
										}
										else if ((_tokenSet_48.member(LA(1)))) {
										}
										else {
											throw new NoViableAltException(LT(1), getFilename());
										}
										
										}
										}
										if ( inputState.guessing==0 ) {
											__markRule(INTERVAL_CONST);
										}
										base_expression_AST = (AST)currentAST.root;
									}
									else if ((LA(1)==LITERAL_timestamp) && (LA(2)==QUOTED_STR) && (_tokenSet_48.member(LA(3)))) {
										{
										match(LITERAL_timestamp);
										string_literal();
										astFactory.addASTChild(currentAST, returnAST);
										}
										if ( inputState.guessing==0 ) {
											__markRule(TIMESTAMP_CONST);
										}
										base_expression_AST = (AST)currentAST.root;
									}
									else {
										boolean synPredMatched1018 = false;
										if (((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING||LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==PERCENTAGE||LA(2)==LITERAL_select) && (_tokenSet_54.member(LA(3))))) {
											int _m1018 = mark();
											synPredMatched1018 = true;
											inputState.guessing++;
											try {
												{
												{
												switch ( LA(1)) {
												case IDENTIFIER:
												case DOUBLE_QUOTED_STRING:
												{
													cursor_name();
													break;
												}
												case OPEN_PAREN:
												{
													subquery();
													break;
												}
												default:
												{
													throw new NoViableAltException(LT(1), getFilename());
												}
												}
												}
												match(PERCENTAGE);
												{
												switch ( LA(1)) {
												case LITERAL_rowcount:
												{
													match(LITERAL_rowcount);
													break;
												}
												case LITERAL_found:
												{
													match(LITERAL_found);
													break;
												}
												case LITERAL_notfound:
												{
													match(LITERAL_notfound);
													break;
												}
												case LITERAL_isopen:
												{
													match(LITERAL_isopen);
													break;
												}
												default:
												{
													throw new NoViableAltException(LT(1), getFilename());
												}
												}
												}
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1018 = false;
											}
											rewind(_m1018);
											inputState.guessing--;
										}
										if ( synPredMatched1018 ) {
											ident_percentage();
											astFactory.addASTChild(currentAST, returnAST);
											base_expression_AST = (AST)currentAST.root;
										}
										else {
											boolean synPredMatched1020 = false;
											if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_54.member(LA(3))))) {
												int _m1020 = mark();
												synPredMatched1020 = true;
												inputState.guessing++;
												try {
													{
													match(OPEN_PAREN);
													match(LITERAL_select);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched1020 = false;
												}
												rewind(_m1020);
												inputState.guessing--;
											}
											if ( synPredMatched1020 ) {
												subquery();
												astFactory.addASTChild(currentAST, returnAST);
												if ( inputState.guessing==0 ) {
													__markRule(SUBQUERY_EXPR);
												}
												base_expression_AST = (AST)currentAST.root;
											}
											else if ((LA(1)==OPEN_PAREN) && (_tokenSet_55.member(LA(2))) && (_tokenSet_56.member(LA(3)))) {
												match(OPEN_PAREN);
												condition();
												astFactory.addASTChild(currentAST, returnAST);
												match(CLOSE_PAREN);
												if ( inputState.guessing==0 ) {
													__markRule(PARENTHESIZED_EXPR);
												}
												base_expression_AST = (AST)currentAST.root;
											}
											else if ((_tokenSet_57.member(LA(1))) && (_tokenSet_48.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
												pseudo_column();
												astFactory.addASTChild(currentAST, returnAST);
												base_expression_AST = (AST)currentAST.root;
											}
											else {
												boolean synPredMatched1022 = false;
												if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_58.member(LA(3))))) {
													int _m1022 = mark();
													synPredMatched1022 = true;
													inputState.guessing++;
													try {
														{
														column_spec();
														match(OPEN_PAREN);
														match(PLUS);
														match(CLOSE_PAREN);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched1022 = false;
													}
													rewind(_m1022);
													inputState.guessing--;
												}
												if ( synPredMatched1022 ) {
													{
													column_spec();
													astFactory.addASTChild(currentAST, returnAST);
													match(OPEN_PAREN);
													match(PLUS);
													match(CLOSE_PAREN);
													}
													if ( inputState.guessing==0 ) {
														__markRule(COLUMN_OUTER_JOIN);
													}
													base_expression_AST = (AST)currentAST.root;
												}
												else {
													boolean synPredMatched1026 = false;
													if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3))))) {
														int _m1026 = mark();
														synPredMatched1026 = true;
														inputState.guessing++;
														try {
															{
															identifier();
															match(DOT);
															{
															switch ( LA(1)) {
															case LITERAL_nextval:
															{
																match(LITERAL_nextval);
																break;
															}
															case LITERAL_currval:
															{
																match(LITERAL_currval);
																break;
															}
															default:
															{
																throw new NoViableAltException(LT(1), getFilename());
															}
															}
															}
															}
														}
														catch (RecognitionException pe) {
															synPredMatched1026 = false;
														}
														rewind(_m1026);
														inputState.guessing--;
													}
													if ( synPredMatched1026 ) {
														sequence_expr();
														astFactory.addASTChild(currentAST, returnAST);
														base_expression_AST = (AST)currentAST.root;
													}
													else if ((_tokenSet_59.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
														rvalue();
														astFactory.addASTChild(currentAST, returnAST);
														base_expression_AST = (AST)currentAST.root;
													}
												else {
													throw new NoViableAltException(LT(1), getFilename());
												}
												}}}}}}}}}}
											}
											catch (RecognitionException ex) {
												if (inputState.guessing==0) {
													reportError(ex);
													recover(ex,_tokenSet_48);
												} else {
												  throw ex;
												}
											}
											returnAST = base_expression_AST;
										}
										
	public void datatype() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datatype_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_binary_integer:
			{
				AST tmp455_AST = null;
				tmp455_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp455_AST);
				match(LITERAL_binary_integer);
				break;
			}
			case LITERAL_natural:
			{
				AST tmp456_AST = null;
				tmp456_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp456_AST);
				match(LITERAL_natural);
				break;
			}
			case LITERAL_positive:
			{
				AST tmp457_AST = null;
				tmp457_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp457_AST);
				match(LITERAL_positive);
				break;
			}
			case LITERAL_number:
			{
				{
				AST tmp458_AST = null;
				tmp458_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp458_AST);
				match(LITERAL_number);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==ASTERISK||LA(2)==NUMBER) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						AST tmp460_AST = null;
						tmp460_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp460_AST);
						match(NUMBER);
						break;
					}
					case ASTERISK:
					{
						AST tmp461_AST = null;
						tmp461_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp461_AST);
						match(ASTERISK);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					{
					switch ( LA(1)) {
					case COMMA:
					{
						match(COMMA);
						AST tmp463_AST = null;
						tmp463_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp463_AST);
						match(NUMBER);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_char:
			{
				{
				AST tmp465_AST = null;
				tmp465_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp465_AST);
				match(LITERAL_char);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN||LA(3)==LITERAL_byte)) {
					match(OPEN_PAREN);
					AST tmp467_AST = null;
					tmp467_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp467_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case LITERAL_byte:
					{
						AST tmp468_AST = null;
						tmp468_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp468_AST);
						match(LITERAL_byte);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_long:
			{
				{
				AST tmp470_AST = null;
				tmp470_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp470_AST);
				match(LITERAL_long);
				{
				if ((LA(1)==LITERAL_raw)) {
					AST tmp471_AST = null;
					tmp471_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp471_AST);
					match(LITERAL_raw);
				}
				else if ((_tokenSet_33.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_raw:
			{
				AST tmp472_AST = null;
				tmp472_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp472_AST);
				match(LITERAL_raw);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp474_AST = null;
					tmp474_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp474_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_boolean:
			{
				AST tmp476_AST = null;
				tmp476_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp476_AST);
				match(LITERAL_boolean);
				break;
			}
			case LITERAL_date:
			{
				AST tmp477_AST = null;
				tmp477_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp477_AST);
				match(LITERAL_date);
				break;
			}
			case LITERAL_timestamp:
			{
				AST tmp478_AST = null;
				tmp478_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp478_AST);
				match(LITERAL_timestamp);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp480_AST = null;
					tmp480_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp480_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				boolean synPredMatched736 = false;
				if (((LA(1)==LITERAL_with) && (LA(2)==LITERAL_local) && (LA(3)==LITERAL_time))) {
					int _m736 = mark();
					synPredMatched736 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_with);
						match(LITERAL_local);
						match(LITERAL_time);
						match(LITERAL_zone);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched736 = false;
					}
					rewind(_m736);
					inputState.guessing--;
				}
				if ( synPredMatched736 ) {
					{
					AST tmp482_AST = null;
					tmp482_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp482_AST);
					match(LITERAL_with);
					AST tmp483_AST = null;
					tmp483_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp483_AST);
					match(LITERAL_local);
					AST tmp484_AST = null;
					tmp484_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp484_AST);
					match(LITERAL_time);
					AST tmp485_AST = null;
					tmp485_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp485_AST);
					match(LITERAL_zone);
					}
				}
				else if ((LA(1)==LITERAL_with) && (LA(2)==LITERAL_time) && (LA(3)==LITERAL_zone)) {
					{
					AST tmp486_AST = null;
					tmp486_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp486_AST);
					match(LITERAL_with);
					AST tmp487_AST = null;
					tmp487_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp487_AST);
					match(LITERAL_time);
					AST tmp488_AST = null;
					tmp488_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp488_AST);
					match(LITERAL_zone);
					}
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_interval:
			{
				AST tmp489_AST = null;
				tmp489_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp489_AST);
				match(LITERAL_interval);
				{
				switch ( LA(1)) {
				case LITERAL_year:
				{
					{
					AST tmp490_AST = null;
					tmp490_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp490_AST);
					match(LITERAL_year);
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						match(OPEN_PAREN);
						AST tmp492_AST = null;
						tmp492_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp492_AST);
						match(NUMBER);
						match(CLOSE_PAREN);
						break;
					}
					case LITERAL_to:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp494_AST = null;
					tmp494_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp494_AST);
					match(LITERAL_to);
					AST tmp495_AST = null;
					tmp495_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp495_AST);
					match(LITERAL_month);
					}
					break;
				}
				case LITERAL_day:
				{
					{
					AST tmp496_AST = null;
					tmp496_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp496_AST);
					match(LITERAL_day);
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						match(OPEN_PAREN);
						AST tmp498_AST = null;
						tmp498_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp498_AST);
						match(NUMBER);
						match(CLOSE_PAREN);
						break;
					}
					case LITERAL_to:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp500_AST = null;
					tmp500_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp500_AST);
					match(LITERAL_to);
					AST tmp501_AST = null;
					tmp501_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp501_AST);
					match(LITERAL_second);
					{
					if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
						match(OPEN_PAREN);
						AST tmp503_AST = null;
						tmp503_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp503_AST);
						match(NUMBER);
						match(CLOSE_PAREN);
					}
					else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case LITERAL_smallint:
			{
				AST tmp505_AST = null;
				tmp505_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp505_AST);
				match(LITERAL_smallint);
				break;
			}
			case LITERAL_real:
			{
				AST tmp506_AST = null;
				tmp506_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp506_AST);
				match(LITERAL_real);
				break;
			}
			case LITERAL_numeric:
			{
				AST tmp507_AST = null;
				tmp507_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp507_AST);
				match(LITERAL_numeric);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp509_AST = null;
					tmp509_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp509_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case COMMA:
					{
						match(COMMA);
						AST tmp511_AST = null;
						tmp511_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp511_AST);
						match(NUMBER);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_int:
			{
				AST tmp513_AST = null;
				tmp513_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp513_AST);
				match(LITERAL_int);
				break;
			}
			case LITERAL_integer:
			{
				AST tmp514_AST = null;
				tmp514_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp514_AST);
				match(LITERAL_integer);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp516_AST = null;
					tmp516_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp516_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_pls_integer:
			{
				AST tmp518_AST = null;
				tmp518_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp518_AST);
				match(LITERAL_pls_integer);
				break;
			}
			case LITERAL_double:
			{
				AST tmp519_AST = null;
				tmp519_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp519_AST);
				match(LITERAL_double);
				AST tmp520_AST = null;
				tmp520_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp520_AST);
				match(LITERAL_precision);
				break;
			}
			case LITERAL_float:
			{
				AST tmp521_AST = null;
				tmp521_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp521_AST);
				match(LITERAL_float);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp523_AST = null;
					tmp523_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp523_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_decimal:
			{
				AST tmp525_AST = null;
				tmp525_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp525_AST);
				match(LITERAL_decimal);
				break;
			}
			case LITERAL_varchar:
			{
				AST tmp526_AST = null;
				tmp526_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp526_AST);
				match(LITERAL_varchar);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN||LA(3)==LITERAL_char||LA(3)==LITERAL_byte)) {
					match(OPEN_PAREN);
					AST tmp528_AST = null;
					tmp528_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp528_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case LITERAL_byte:
					{
						AST tmp529_AST = null;
						tmp529_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp529_AST);
						match(LITERAL_byte);
						break;
					}
					case LITERAL_char:
					{
						AST tmp530_AST = null;
						tmp530_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp530_AST);
						match(LITERAL_char);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 599:
			{
				AST tmp532_AST = null;
				tmp532_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp532_AST);
				match(599);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN||LA(3)==LITERAL_char||LA(3)==LITERAL_byte)) {
					match(OPEN_PAREN);
					AST tmp534_AST = null;
					tmp534_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp534_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case LITERAL_byte:
					{
						AST tmp535_AST = null;
						tmp535_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp535_AST);
						match(LITERAL_byte);
						break;
					}
					case LITERAL_char:
					{
						AST tmp536_AST = null;
						tmp536_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp536_AST);
						match(LITERAL_char);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_nvarchar:
			{
				AST tmp538_AST = null;
				tmp538_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp538_AST);
				match(LITERAL_nvarchar);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN||LA(3)==LITERAL_char||LA(3)==LITERAL_byte)) {
					match(OPEN_PAREN);
					AST tmp540_AST = null;
					tmp540_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp540_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case LITERAL_byte:
					{
						AST tmp541_AST = null;
						tmp541_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp541_AST);
						match(LITERAL_byte);
						break;
					}
					case LITERAL_char:
					{
						AST tmp542_AST = null;
						tmp542_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp542_AST);
						match(LITERAL_char);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 601:
			{
				AST tmp544_AST = null;
				tmp544_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp544_AST);
				match(601);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN||LA(3)==LITERAL_char||LA(3)==LITERAL_byte)) {
					match(OPEN_PAREN);
					AST tmp546_AST = null;
					tmp546_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp546_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case LITERAL_byte:
					{
						AST tmp547_AST = null;
						tmp547_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp547_AST);
						match(LITERAL_byte);
						break;
					}
					case LITERAL_char:
					{
						AST tmp548_AST = null;
						tmp548_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp548_AST);
						match(LITERAL_char);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_character:
			{
				AST tmp550_AST = null;
				tmp550_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp550_AST);
				match(LITERAL_character);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp552_AST = null;
					tmp552_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp552_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_mlslabel:
			{
				AST tmp554_AST = null;
				tmp554_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp554_AST);
				match(LITERAL_mlslabel);
				break;
			}
			case LITERAL_blob:
			{
				AST tmp555_AST = null;
				tmp555_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp555_AST);
				match(LITERAL_blob);
				break;
			}
			case LITERAL_clob:
			{
				AST tmp556_AST = null;
				tmp556_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp556_AST);
				match(LITERAL_clob);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(DATATYPE);
			}
			datatype_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    throw ex;
					
			} else {
				throw ex;
			}
		}
		returnAST = datatype_AST;
	}
	
	public void sqlplus_exec_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sqlplus_exec_statement_AST = null;
		
		try {      // for error handling
			boolean synPredMatched151 = false;
			if (((_tokenSet_59.member(LA(1))) && (_tokenSet_61.member(LA(2))) && (_tokenSet_62.member(LA(3))))) {
				int _m151 = mark();
				synPredMatched151 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					match(ASSIGNMENT_EQ);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched151 = false;
				}
				rewind(_m151);
				inputState.guessing--;
			}
			if ( synPredMatched151 ) {
				assignment_statement();
				astFactory.addASTChild(currentAST, returnAST);
				sqlplus_exec_statement_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_18.member(LA(1))) && (_tokenSet_16.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				{
				procedure_call();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop154:
				do {
					if ((LA(1)==DOT)) {
						AST tmp557_AST = null;
						tmp557_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp557_AST);
						match(DOT);
						procedure_call();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop154;
					}
					
				} while (true);
				}
				}
				sqlplus_exec_statement_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = sqlplus_exec_statement_AST;
	}
	
	public void plsql_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_expression_AST = null;
		Token  c = null;
		AST c_AST = null;
		
		try {      // for error handling
			num_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop978:
			do {
				if ((LA(1)==CONCAT) && (_tokenSet_43.member(LA(2))) && (_tokenSet_63.member(LA(3)))) {
					c = LT(1);
					c_AST = astFactory.create(c);
					astFactory.addASTChild(currentAST, c_AST);
					match(CONCAT);
					if ( inputState.guessing==0 ) {
						c_AST.setType(CONCAT_OP);
					}
					num_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop978;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(c_AST != null ){
				__markRule(ARITHMETIC_EXPR);
				}
				
			}
			plsql_expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_64);
			} else {
			  throw ex;
			}
		}
		returnAST = plsql_expression_AST;
	}
	
	public void identifier4() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier4_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_prior:
			{
				AST tmp558_AST = null;
				tmp558_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp558_AST);
				match(LITERAL_prior);
				identifier4_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_start:
			{
				AST tmp559_AST = null;
				tmp559_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp559_AST);
				match(LITERAL_start);
				identifier4_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_create:
			{
				AST tmp560_AST = null;
				tmp560_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp560_AST);
				match(LITERAL_create);
				identifier4_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					identifier4_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_47);
			} else {
			  throw ex;
			}
		}
		returnAST = identifier4_AST;
	}
	
	public void sqlplus_path() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sqlplus_path_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case DOUBLE_DOT:
			{
				AST tmp561_AST = null;
				tmp561_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp561_AST);
				match(DOUBLE_DOT);
				break;
			}
			case DOT:
			{
				AST tmp562_AST = null;
				tmp562_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp562_AST);
				match(DOT);
				break;
			}
			default:
				if ((_tokenSet_18.member(LA(1)))) {
					identifier3();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop160:
			do {
				if ((LA(1)==DIVIDE||LA(1)==BACKSLASH)) {
					{
					switch ( LA(1)) {
					case DIVIDE:
					{
						AST tmp563_AST = null;
						tmp563_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp563_AST);
						match(DIVIDE);
						break;
					}
					case BACKSLASH:
					{
						AST tmp564_AST = null;
						tmp564_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp564_AST);
						match(BACKSLASH);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					{
					switch ( LA(1)) {
					case DOUBLE_DOT:
					{
						AST tmp565_AST = null;
						tmp565_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp565_AST);
						match(DOUBLE_DOT);
						break;
					}
					case DOT:
					{
						AST tmp566_AST = null;
						tmp566_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp566_AST);
						match(DOT);
						break;
					}
					default:
						if ((_tokenSet_18.member(LA(1)))) {
							identifier3();
							astFactory.addASTChild(currentAST, returnAST);
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop160;
				}
				
			} while (true);
			}
			sqlplus_path_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sqlplus_path_AST;
	}
	
	public void begin_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST begin_block_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_declare:
			{
				AST tmp567_AST = null;
				tmp567_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp567_AST);
				match(LITERAL_declare);
				{
				if ((_tokenSet_65.member(LA(1)))) {
					declare_list();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_begin)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_begin:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			plsql_block();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_BLOCK);
			}
			begin_block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = begin_block_AST;
	}
	
	public void plsql_lvalue() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_lvalue_AST = null;
		Token  d = null;
		AST d_AST = null;
		
		try {      // for error handling
			boolean synPredMatched699 = false;
			if (((_tokenSet_5.member(LA(1))) && (_tokenSet_66.member(LA(2))) && (_tokenSet_67.member(LA(3))))) {
				int _m699 = mark();
				synPredMatched699 = true;
				inputState.guessing++;
				try {
					{
					{
					_loop698:
					do {
						if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3)))) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop698;
						}
						
					} while (true);
					}
					name_fragment();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched699 = false;
				}
				rewind(_m699);
				inputState.guessing--;
			}
			if ( synPredMatched699 ) {
				{
				_loop701:
				do {
					if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment();
						astFactory.addASTChild(currentAST, returnAST);
						match(DOT);
					}
					else {
						break _loop701;
					}
					
				} while (true);
				}
				name_fragment();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(PLSQL_VAR_REF);
				}
				plsql_lvalue_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched704 = false;
				if (((LA(1)==COLON) && (LA(2)==LITERAL_old||LA(2)==LITERAL_new) && (LA(3)==DOT))) {
					int _m704 = mark();
					synPredMatched704 = true;
					inputState.guessing++;
					try {
						{
						match(COLON);
						{
						switch ( LA(1)) {
						case LITERAL_new:
						{
							match(LITERAL_new);
							break;
						}
						case LITERAL_old:
						{
							match(LITERAL_old);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						match(DOT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched704 = false;
					}
					rewind(_m704);
					inputState.guessing--;
				}
				if ( synPredMatched704 ) {
					{
					AST tmp569_AST = null;
					tmp569_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp569_AST);
					match(COLON);
					{
					switch ( LA(1)) {
					case LITERAL_new:
					{
						AST tmp570_AST = null;
						tmp570_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp570_AST);
						match(LITERAL_new);
						break;
					}
					case LITERAL_old:
					{
						AST tmp571_AST = null;
						tmp571_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp571_AST);
						match(LITERAL_old);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(DOT);
					name_fragment();
					astFactory.addASTChild(currentAST, returnAST);
					}
					if ( inputState.guessing==0 ) {
						__markRule(TRIGGER_COLUMN_REF);
					}
					plsql_lvalue_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_18.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_68.member(LA(3)))) {
					{
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop712:
					do {
						if ((LA(1)==DOT)) {
							d = LT(1);
							d_AST = astFactory.create(d);
							match(DOT);
							{
							boolean synPredMatched711 = false;
							if (((_tokenSet_18.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_68.member(LA(3))))) {
								int _m711 = mark();
								synPredMatched711 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched711 = false;
								}
								rewind(_m711);
								inputState.guessing--;
							}
							if ( synPredMatched711 ) {
								function_call();
								astFactory.addASTChild(currentAST, returnAST);
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_66.member(LA(2))) && (_tokenSet_67.member(LA(3)))) {
								c_record_item_ref();
								astFactory.addASTChild(currentAST, returnAST);
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop712;
						}
						
					} while (true);
					}
					}
					if ( inputState.guessing==0 ) {
						
						if(d_AST != null){
						__markRule(COLLECTION_METHOD_CALL);
						}
						
					}
					plsql_lvalue_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==COLON) && (_tokenSet_5.member(LA(2))) && (_tokenSet_69.member(LA(3)))) {
					host_variable();
					astFactory.addASTChild(currentAST, returnAST);
					plsql_lvalue_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_69);
				} else {
				  throw ex;
				}
			}
			returnAST = plsql_lvalue_AST;
		}
		
	public void assignment_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST assignment_statement_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			plsql_lvalue();
			astFactory.addASTChild(currentAST, returnAST);
			p = LT(1);
			p_AST = astFactory.create(p);
			match(ASSIGNMENT_EQ);
			if ( inputState.guessing==0 ) {
				p_AST.setType(ASSIGNMENT_OP);
			}
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ASSIGNMENT_STATEMENT);
			}
			assignment_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = assignment_statement_AST;
	}
	
	public void procedure_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST procedure_call_AST = null;
		
		try {      // for error handling
			callable_name_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==OPEN_PAREN) && (_tokenSet_68.member(LA(2))) && (_tokenSet_70.member(LA(3)))) {
				match(OPEN_PAREN);
				{
				if ((_tokenSet_55.member(LA(1)))) {
					call_argument_list();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==CLOSE_PAREN)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				match(CLOSE_PAREN);
			}
			else if ((_tokenSet_16.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_CALL);
			}
			procedure_call_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = procedure_call_AST;
	}
	
	public void identifier3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier3_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_prior:
			{
				AST tmp575_AST = null;
				tmp575_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp575_AST);
				match(LITERAL_prior);
				identifier3_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_start:
			{
				AST tmp576_AST = null;
				tmp576_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp576_AST);
				match(LITERAL_start);
				identifier3_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					identifier3_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_71);
			} else {
			  throw ex;
			}
		}
		returnAST = identifier3_AST;
	}
	
	public void package_body() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_body_AST = null;
		AST o_AST = null;
		
		try {      // for error handling
			match(LITERAL_package);
			{
			if ((LA(1)==LITERAL_body) && (_tokenSet_5.member(LA(2)))) {
				match(LITERAL_body);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==LITERAL_is||LA(2)==LITERAL_as)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==LITERAL_is||LA(2)==LITERAL_as)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			package_name();
			o_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_is:
			{
				match(LITERAL_is);
				break;
			}
			case LITERAL_as:
			{
				match(LITERAL_as);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_serially_reusable)) {
				serially_reusable_pragma();
				astFactory.addASTChild(currentAST, returnAST);
				match(SEMI);
			}
			else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_73.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop559:
			do {
				if ((_tokenSet_12.member(LA(1)))) {
					package_obj_body();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop559;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_begin:
			{
				package_init_section();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_end:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_end);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				package_name();
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PACKAGE_BODY);
			}
			package_body_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = package_body_AST;
	}
	
	public void create_view() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_view_AST = null;
		
		try {      // for error handling
			match(LITERAL_view);
			view_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				v_column_def();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop526:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						v_column_def();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop526;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_as:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_as);
			select_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_with:
			{
				AST tmp590_AST = null;
				tmp590_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp590_AST);
				match(LITERAL_with);
				{
				switch ( LA(1)) {
				case LITERAL_check:
				{
					{
					AST tmp591_AST = null;
					tmp591_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp591_AST);
					match(LITERAL_check);
					AST tmp592_AST = null;
					tmp592_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp592_AST);
					match(LITERAL_option);
					}
					break;
				}
				case LITERAL_read:
				{
					{
					AST tmp593_AST = null;
					tmp593_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp593_AST);
					match(LITERAL_read);
					AST tmp594_AST = null;
					tmp594_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp594_AST);
					match(LITERAL_only);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case SEMI:
			{
				match(SEMI);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_view_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = create_view_AST;
	}
	
	public void create_table2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_table2_AST = null;
		
		try {      // for error handling
			match(LITERAL_table);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_74.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (_tokenSet_75.member(LA(3)))) {
				match(OPEN_PAREN);
				column_def();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop242:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_5.member(LA(2))) && (_tokenSet_75.member(LA(3)))) {
						match(COMMA);
						column_def();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop242;
					}
					
				} while (true);
				}
				{
				_loop244:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						constaraint();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop244;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
			}
			else if ((_tokenSet_74.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_organization:
			{
				organization_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_tablespace:
			case LITERAL_online:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_storage:
			case LITERAL_local:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop247:
			do {
				switch ( LA(1)) {
				case LITERAL_tablespace:
				case LITERAL_online:
				case LITERAL_compute:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_storage:
				{
					physical_properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_local:
				{
					table_properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop247;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				AST tmp602_AST = null;
				tmp602_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp602_AST);
				match(LITERAL_as);
				select_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_table2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_table2_AST;
	}
	
	public void create_temp_table2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_temp_table2_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_global:
			{
				AST tmp603_AST = null;
				tmp603_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp603_AST);
				match(LITERAL_global);
				break;
			}
			case LITERAL_temporary:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_temporary);
			match(LITERAL_table);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				column_def();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop253:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_5.member(LA(2))) && (_tokenSet_75.member(LA(3)))) {
						match(COMMA);
						column_def();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop253;
					}
					
				} while (true);
				}
				{
				_loop255:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						constaraint();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop255;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_on:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			AST tmp610_AST = null;
			tmp610_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp610_AST);
			match(LITERAL_on);
			AST tmp611_AST = null;
			tmp611_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp611_AST);
			match(LITERAL_commit);
			{
			switch ( LA(1)) {
			case LITERAL_preserve:
			{
				AST tmp612_AST = null;
				tmp612_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp612_AST);
				match(LITERAL_preserve);
				break;
			}
			case LITERAL_delete:
			{
				AST tmp613_AST = null;
				tmp613_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp613_AST);
				match(LITERAL_delete);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp614_AST = null;
			tmp614_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp614_AST);
			match(LITERAL_rows);
			}
			{
			switch ( LA(1)) {
			case LITERAL_cache:
			case LITERAL_nocache:
			{
				cache_clause();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				AST tmp615_AST = null;
				tmp615_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp615_AST);
				match(LITERAL_as);
				select_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_temp_table2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_temp_table2_AST;
	}
	
	public void create_index2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_index2_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_unique:
			{
				AST tmp616_AST = null;
				tmp616_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp616_AST);
				match(LITERAL_unique);
				break;
			}
			case LITERAL_bitmap:
			{
				AST tmp617_AST = null;
				tmp617_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp617_AST);
				match(LITERAL_bitmap);
				break;
			}
			case LITERAL_index:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_index);
			index_name();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_on);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp620_AST = null;
			tmp620_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp620_AST);
			match(OPEN_PAREN);
			index_column_spec_list();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp621_AST = null;
			tmp621_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp621_AST);
			match(CLOSE_PAREN);
			{
			_loop230:
			do {
				switch ( LA(1)) {
				case LITERAL_tablespace:
				case LITERAL_online:
				case LITERAL_compute:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_storage:
				{
					physical_properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_local:
				{
					table_properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop230;
				}
				}
			} while (true);
			}
			create_index2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_index2_AST;
	}
	
	public void create_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_trigger_AST = null;
		
		try {      // for error handling
			match(LITERAL_trigger);
			trigger_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_after:
			case LITERAL_before:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_after:
				{
					AST tmp623_AST = null;
					tmp623_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp623_AST);
					match(LITERAL_after);
					break;
				}
				case LITERAL_before:
				{
					AST tmp624_AST = null;
					tmp624_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp624_AST);
					match(LITERAL_before);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				{
					dml_trigger();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_create:
				case LITERAL_analyze:
				case LITERAL_associate:
				case LITERAL_audit:
				case LITERAL_noaudit:
				case LITERAL_ddl:
				case LITERAL_diassociate:
				case LITERAL_grant:
				case LITERAL_rename:
				case LITERAL_revoke:
				{
					ddl_trigger();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_startup:
				case LITERAL_shutdown:
				case LITERAL_servererror:
				case LITERAL_logon:
				case LITERAL_logoff:
				{
					db_event_trigger();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case LITERAL_instead:
			{
				instead_of_trigger();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop199:
			do {
				switch ( LA(1)) {
				case LITERAL_for:
				{
					for_each();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_referencing:
				{
					referencing_old_new();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop199;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_when:
			{
				trigger_when();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_begin:
			case LITERAL_declare:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			begin_block();
			astFactory.addASTChild(currentAST, returnAST);
			create_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_trigger_AST;
	}
	
	public void create_directory() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_directory_AST = null;
		
		try {      // for error handling
			AST tmp625_AST = null;
			tmp625_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp625_AST);
			match(LITERAL_directory);
			object_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				AST tmp626_AST = null;
				tmp626_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp626_AST);
				match(LITERAL_as);
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_directory_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_directory_AST;
	}
	
	public void create_db_link() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_db_link_AST = null;
		
		try {      // for error handling
			AST tmp627_AST = null;
			tmp627_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp627_AST);
			match(LITERAL_database);
			AST tmp628_AST = null;
			tmp628_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp628_AST);
			match(LITERAL_link);
			object_name();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp629_AST = null;
			tmp629_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp629_AST);
			match(LITERAL_connect);
			AST tmp630_AST = null;
			tmp630_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp630_AST);
			match(LITERAL_to);
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp631_AST = null;
			tmp631_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp631_AST);
			match(LITERAL_identified);
			AST tmp632_AST = null;
			tmp632_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp632_AST);
			match(LITERAL_by);
			AST tmp633_AST = null;
			tmp633_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp633_AST);
			match(DOUBLE_QUOTED_STRING);
			{
			switch ( LA(1)) {
			case LITERAL_using:
			{
				AST tmp634_AST = null;
				tmp634_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp634_AST);
				match(LITERAL_using);
				{
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==QUOTED_STR)) {
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			create_db_link_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_db_link_AST;
	}
	
	public void create_sequence() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST create_sequence_AST = null;
		
		try {      // for error handling
			match(LITERAL_sequence);
			object_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop183:
			do {
				if ((_tokenSet_76.member(LA(1))) && (_tokenSet_77.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sequence_opt();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop183;
				}
				
			} while (true);
			}
			create_sequence_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = create_sequence_AST;
	}
	
	public void sequence_opt() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sequence_opt_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_maxvalue:
			{
				{
				AST tmp636_AST = null;
				tmp636_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp636_AST);
				match(LITERAL_maxvalue);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_minvalue:
			{
				{
				AST tmp637_AST = null;
				tmp637_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp637_AST);
				match(LITERAL_minvalue);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_cycle:
			{
				AST tmp638_AST = null;
				tmp638_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp638_AST);
				match(LITERAL_cycle);
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nocycle:
			{
				AST tmp639_AST = null;
				tmp639_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp639_AST);
				match(LITERAL_nocycle);
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_cache:
			{
				{
				AST tmp640_AST = null;
				tmp640_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp640_AST);
				match(LITERAL_cache);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nocache:
			{
				AST tmp641_AST = null;
				tmp641_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp641_AST);
				match(LITERAL_nocache);
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_increment:
			{
				{
				AST tmp642_AST = null;
				tmp642_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp642_AST);
				match(LITERAL_increment);
				AST tmp643_AST = null;
				tmp643_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp643_AST);
				match(LITERAL_by);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_start:
			{
				{
				AST tmp644_AST = null;
				tmp644_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp644_AST);
				match(LITERAL_start);
				AST tmp645_AST = null;
				tmp645_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp645_AST);
				match(LITERAL_with);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_order:
			{
				AST tmp646_AST = null;
				tmp646_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp646_AST);
				match(LITERAL_order);
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_noorder:
			{
				AST tmp647_AST = null;
				tmp647_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp647_AST);
				match(LITERAL_noorder);
				sequence_opt_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_78);
			} else {
			  throw ex;
			}
		}
		returnAST = sequence_opt_AST;
	}
	
	public void trigger_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trigger_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TRIGGER_NAME);
			}
			trigger_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = trigger_name_AST;
	}
	
	public void dml_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dml_trigger_AST = null;
		
		try {      // for error handling
			insert_update_delete();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop209:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					insert_update_delete();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop209;
				}
				
			} while (true);
			}
			match(LITERAL_on);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(DML_TRIGGER_CLAUSE);
			}
			dml_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = dml_trigger_AST;
	}
	
	public void ddl_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST ddl_trigger_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_create:
			{
				AST tmp650_AST = null;
				tmp650_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp650_AST);
				match(LITERAL_create);
				break;
			}
			case LITERAL_alter:
			{
				AST tmp651_AST = null;
				tmp651_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp651_AST);
				match(LITERAL_alter);
				break;
			}
			case LITERAL_drop:
			{
				AST tmp652_AST = null;
				tmp652_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp652_AST);
				match(LITERAL_drop);
				break;
			}
			case LITERAL_analyze:
			{
				AST tmp653_AST = null;
				tmp653_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp653_AST);
				match(LITERAL_analyze);
				break;
			}
			case LITERAL_associate:
			{
				{
				AST tmp654_AST = null;
				tmp654_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp654_AST);
				match(LITERAL_associate);
				AST tmp655_AST = null;
				tmp655_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp655_AST);
				match(LITERAL_statistics);
				}
				break;
			}
			case LITERAL_audit:
			{
				AST tmp656_AST = null;
				tmp656_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp656_AST);
				match(LITERAL_audit);
				break;
			}
			case LITERAL_noaudit:
			{
				AST tmp657_AST = null;
				tmp657_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp657_AST);
				match(LITERAL_noaudit);
				break;
			}
			case LITERAL_comment:
			{
				AST tmp658_AST = null;
				tmp658_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp658_AST);
				match(LITERAL_comment);
				break;
			}
			case LITERAL_ddl:
			{
				AST tmp659_AST = null;
				tmp659_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp659_AST);
				match(LITERAL_ddl);
				break;
			}
			case LITERAL_diassociate:
			{
				{
				AST tmp660_AST = null;
				tmp660_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp660_AST);
				match(LITERAL_diassociate);
				AST tmp661_AST = null;
				tmp661_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp661_AST);
				match(LITERAL_statistics);
				}
				break;
			}
			case LITERAL_grant:
			{
				AST tmp662_AST = null;
				tmp662_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp662_AST);
				match(LITERAL_grant);
				break;
			}
			case LITERAL_rename:
			{
				AST tmp663_AST = null;
				tmp663_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp663_AST);
				match(LITERAL_rename);
				break;
			}
			case LITERAL_revoke:
			{
				AST tmp664_AST = null;
				tmp664_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp664_AST);
				match(LITERAL_revoke);
				break;
			}
			case LITERAL_truncate:
			{
				AST tmp665_AST = null;
				tmp665_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp665_AST);
				match(LITERAL_truncate);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_on);
			trigger_target();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(DDL_TRIGGER_CLAUSE);
			}
			ddl_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = ddl_trigger_AST;
	}
	
	public void db_event_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST db_event_trigger_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_startup:
			{
				AST tmp667_AST = null;
				tmp667_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp667_AST);
				match(LITERAL_startup);
				break;
			}
			case LITERAL_shutdown:
			{
				AST tmp668_AST = null;
				tmp668_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp668_AST);
				match(LITERAL_shutdown);
				break;
			}
			case LITERAL_servererror:
			{
				AST tmp669_AST = null;
				tmp669_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp669_AST);
				match(LITERAL_servererror);
				break;
			}
			case LITERAL_logon:
			{
				AST tmp670_AST = null;
				tmp670_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp670_AST);
				match(LITERAL_logon);
				break;
			}
			case LITERAL_logoff:
			{
				AST tmp671_AST = null;
				tmp671_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp671_AST);
				match(LITERAL_logoff);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_on);
			trigger_target();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(DB_EVNT_TRIGGER_CLAUSE);
			}
			db_event_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = db_event_trigger_AST;
	}
	
	public void instead_of_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instead_of_trigger_AST = null;
		
		try {      // for error handling
			match(LITERAL_instead);
			match(LITERAL_of);
			{
			switch ( LA(1)) {
			case LITERAL_delete:
			{
				AST tmp675_AST = null;
				tmp675_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp675_AST);
				match(LITERAL_delete);
				break;
			}
			case LITERAL_insert:
			{
				AST tmp676_AST = null;
				tmp676_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp676_AST);
				match(LITERAL_insert);
				break;
			}
			case LITERAL_update:
			{
				AST tmp677_AST = null;
				tmp677_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp677_AST);
				match(LITERAL_update);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_on);
			view_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(INSTEADOF_TRIGGER);
			}
			instead_of_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = instead_of_trigger_AST;
	}
	
	public void for_each() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST for_each_AST = null;
		
		try {      // for error handling
			match(LITERAL_for);
			match(LITERAL_each);
			match(LITERAL_row);
			if ( inputState.guessing==0 ) {
				__markRule(TRIGGER_FOR_EACH);
			}
			for_each_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = for_each_AST;
	}
	
	public void referencing_old_new() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST referencing_old_new_AST = null;
		
		try {      // for error handling
			AST tmp682_AST = null;
			tmp682_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp682_AST);
			match(LITERAL_referencing);
			{
			_loop218:
			do {
				switch ( LA(1)) {
				case LITERAL_old:
				{
					{
					AST tmp683_AST = null;
					tmp683_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp683_AST);
					match(LITERAL_old);
					AST tmp684_AST = null;
					tmp684_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp684_AST);
					match(LITERAL_as);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				case LITERAL_new:
				{
					{
					AST tmp685_AST = null;
					tmp685_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp685_AST);
					match(LITERAL_new);
					AST tmp686_AST = null;
					tmp686_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp686_AST);
					match(LITERAL_as);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					break _loop218;
				}
				}
			} while (true);
			}
			referencing_old_new_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = referencing_old_new_AST;
	}
	
	public void trigger_when() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trigger_when_AST = null;
		
		try {      // for error handling
			match(LITERAL_when);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			trigger_when_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_81);
			} else {
			  throw ex;
			}
		}
		returnAST = trigger_when_AST;
	}
	
	public void trigger_target() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trigger_target_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_schema:
			{
				AST tmp688_AST = null;
				tmp688_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp688_AST);
				match(LITERAL_schema);
				trigger_target_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_database:
			{
				AST tmp689_AST = null;
				tmp689_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp689_AST);
				match(LITERAL_database);
				if ( inputState.guessing==0 ) {
					__markRule(TRIGGER_TARGET);
				}
				trigger_target_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = trigger_target_AST;
	}
	
	public void insert_update_delete() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST insert_update_delete_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_insert:
			{
				AST tmp690_AST = null;
				tmp690_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp690_AST);
				match(LITERAL_insert);
				insert_update_delete_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_update:
			{
				{
				AST tmp691_AST = null;
				tmp691_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp691_AST);
				match(LITERAL_update);
				{
				switch ( LA(1)) {
				case LITERAL_of:
				{
					AST tmp692_AST = null;
					tmp692_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp692_AST);
					match(LITERAL_of);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop224:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop224;
						}
						
					} while (true);
					}
					break;
				}
				case LITERAL_on:
				case LITERAL_or:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				insert_update_delete_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_delete:
			{
				AST tmp694_AST = null;
				tmp694_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp694_AST);
				match(LITERAL_delete);
				insert_update_delete_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
		returnAST = insert_update_delete_AST;
	}
	
	public void view_name_ddl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST view_name_ddl_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(VIEW_NAME_DDL);
			}
			view_name_ddl_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = view_name_ddl_AST;
	}
	
	public void condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			logical_term();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop935:
			do {
				if ((LA(1)==LITERAL_or)) {
					p = LT(1);
					p_AST = astFactory.create(p);
					astFactory.addASTChild(currentAST, p_AST);
					match(LITERAL_or);
					if ( inputState.guessing==0 ) {
						p_AST.setType(OR_LOGICAL);
					}
					logical_term();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop935;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(p_AST != null){
				__markRule(LOGICAL_EXPR);
				}
				
			}
			condition_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = condition_AST;
	}
	
	public void alter_trigger() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alter_trigger_AST = null;
		
		try {      // for error handling
			match(LITERAL_alter);
			match(LITERAL_trigger);
			trigger_name();
			astFactory.addASTChild(currentAST, returnAST);
			enable_disable_clause();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ALTER_TRIGGER);
			}
			alter_trigger_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = alter_trigger_AST;
	}
	
	public void enable_disable_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST enable_disable_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_enable:
			{
				AST tmp697_AST = null;
				tmp697_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp697_AST);
				match(LITERAL_enable);
				enable_disable_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_disable:
			{
				AST tmp698_AST = null;
				tmp698_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp698_AST);
				match(LITERAL_disable);
				enable_disable_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = enable_disable_clause_AST;
	}
	
	public void index_column_spec_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_column_spec_list_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_asc:
			{
				AST tmp699_AST = null;
				tmp699_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp699_AST);
				match(LITERAL_asc);
				break;
			}
			case LITERAL_desc:
			{
				AST tmp700_AST = null;
				tmp700_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp700_AST);
				match(LITERAL_desc);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop235:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case LITERAL_asc:
					{
						AST tmp702_AST = null;
						tmp702_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp702_AST);
						match(LITERAL_asc);
						break;
					}
					case LITERAL_desc:
					{
						AST tmp703_AST = null;
						tmp703_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp703_AST);
						match(LITERAL_desc);
						break;
					}
					case COMMA:
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop235;
				}
				
			} while (true);
			}
			index_column_spec_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = index_column_spec_list_AST;
	}
	
	public void physical_properties() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST physical_properties_AST = null;
		
		try {      // for error handling
			segment_attributes_clause();
			astFactory.addASTChild(currentAST, returnAST);
			physical_properties_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = physical_properties_AST;
	}
	
	public void table_properties() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_properties_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_partition:
			case LITERAL_local:
			{
				table_partitioning_clause();
				astFactory.addASTChild(currentAST, returnAST);
				table_properties_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_cache:
			case LITERAL_nocache:
			{
				cache_clause();
				astFactory.addASTChild(currentAST, returnAST);
				table_properties_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_parallel:
			case LITERAL_noparallel:
			{
				parallel_clause();
				astFactory.addASTChild(currentAST, returnAST);
				table_properties_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			{
				monitoring_clause();
				astFactory.addASTChild(currentAST, returnAST);
				table_properties_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (_tokenSet_87.member(LA(2)))) {
					alter_table_options();
					astFactory.addASTChild(currentAST, returnAST);
					table_properties_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (LA(2)==LITERAL_row)) {
					row_movement_clause();
					astFactory.addASTChild(currentAST, returnAST);
					table_properties_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = table_properties_AST;
	}
	
	public void constaraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constaraint_AST = null;
		
		try {      // for error handling
			match(LITERAL_constraint);
			constraint_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				pk_spec_constr();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_foreign:
			{
				fk_spec_constr();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_check:
			{
				{
				AST tmp705_AST = null;
				tmp705_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp705_AST);
				match(LITERAL_check);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case LITERAL_unique:
			{
				unique_contsr();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(CONSTRAINT);
			}
			constaraint_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = constaraint_AST;
	}
	
	public void organization_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST organization_spec_AST = null;
		
		try {      // for error handling
			match(LITERAL_organization);
			{
			switch ( LA(1)) {
			case LITERAL_index:
			{
				{
				match(LITERAL_index);
				{
				switch ( LA(1)) {
				case LITERAL_including:
				{
					AST tmp708_AST = null;
					tmp708_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp708_AST);
					match(LITERAL_including);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_tablespace:
				case LITERAL_online:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_pctthreshold:
				case LITERAL_storage:
				case LITERAL_local:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_pctthreshold:
				{
					AST tmp709_AST = null;
					tmp709_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp709_AST);
					match(LITERAL_pctthreshold);
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_tablespace:
				case LITERAL_online:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_storage:
				case LITERAL_local:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(IOT_TYPE);
				}
				break;
			}
			case LITERAL_heap:
			{
				match(LITERAL_heap);
				if ( inputState.guessing==0 ) {
					__markRule(HEAP_ORGINIZED);
				}
				break;
			}
			case LITERAL_external:
			{
				external_table_spec();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(EXTERNAL_TYPE);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			organization_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = organization_spec_AST;
	}
	
	public void select_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_expression_AST = null;
		AST sub_AST = null;
		
		try {      // for error handling
			select_first();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1076:
			do {
				if (((LA(1) >= LITERAL_union && LA(1) <= LITERAL_minus))) {
					{
					switch ( LA(1)) {
					case LITERAL_union:
					{
						{
						AST tmp711_AST = null;
						tmp711_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp711_AST);
						match(LITERAL_union);
						{
						switch ( LA(1)) {
						case LITERAL_all:
						{
							AST tmp712_AST = null;
							tmp712_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp712_AST);
							match(LITERAL_all);
							break;
						}
						case OPEN_PAREN:
						case LITERAL_select:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						}
						break;
					}
					case LITERAL_intersect:
					{
						AST tmp713_AST = null;
						tmp713_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp713_AST);
						match(LITERAL_intersect);
						break;
					}
					case LITERAL_minus:
					{
						AST tmp714_AST = null;
						tmp714_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp714_AST);
						match(LITERAL_minus);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					select_first();
					sub_AST = (AST)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1076;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(sub_AST != null){
				{  __markRule(SELECT_EXPRESSION_UNION); }
				}
				
			}
			select_expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_88);
			} else {
			  throw ex;
			}
		}
		returnAST = select_expression_AST;
	}
	
	public void cache_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cache_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cache:
			{
				AST tmp715_AST = null;
				tmp715_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp715_AST);
				match(LITERAL_cache);
				cache_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nocache:
			{
				AST tmp716_AST = null;
				tmp716_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp716_AST);
				match(LITERAL_nocache);
				cache_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = cache_clause_AST;
	}
	
	public void segment_attributes_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST segment_attributes_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_storage:
			{
				physical_attributes_clause();
				astFactory.addASTChild(currentAST, returnAST);
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_tablespace:
			{
				{
				AST tmp717_AST = null;
				tmp717_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp717_AST);
				match(LITERAL_tablespace);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_online:
			{
				AST tmp718_AST = null;
				tmp718_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp718_AST);
				match(LITERAL_online);
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_compute:
			{
				{
				AST tmp719_AST = null;
				tmp719_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp719_AST);
				match(LITERAL_compute);
				AST tmp720_AST = null;
				tmp720_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp720_AST);
				match(LITERAL_statistics);
				{
				if ((LA(1)==LITERAL_parallel) && (_tokenSet_89.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp721_AST = null;
					tmp721_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp721_AST);
					match(LITERAL_parallel);
				}
				else if ((LA(1)==LITERAL_noparallel) && (_tokenSet_89.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp722_AST = null;
					tmp722_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp722_AST);
					match(LITERAL_noparallel);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_89.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_89.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			{
				logging_clause();
				astFactory.addASTChild(currentAST, returnAST);
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_compress:
			case LITERAL_nocompress:
			{
				table_compression();
				astFactory.addASTChild(currentAST, returnAST);
				segment_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_89);
			} else {
			  throw ex;
			}
		}
		returnAST = segment_attributes_clause_AST;
	}
	
	public void physical_attributes_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST physical_attributes_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_pctfree:
			{
				{
				AST tmp723_AST = null;
				tmp723_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp723_AST);
				match(LITERAL_pctfree);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				physical_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_pctused:
			{
				{
				AST tmp724_AST = null;
				tmp724_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp724_AST);
				match(LITERAL_pctused);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				physical_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_initrans:
			{
				{
				AST tmp725_AST = null;
				tmp725_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp725_AST);
				match(LITERAL_initrans);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				physical_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_maxtrans:
			{
				{
				AST tmp726_AST = null;
				tmp726_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp726_AST);
				match(LITERAL_maxtrans);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				physical_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_storage:
			{
				storage_spec();
				astFactory.addASTChild(currentAST, returnAST);
				physical_attributes_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_90);
			} else {
			  throw ex;
			}
		}
		returnAST = physical_attributes_clause_AST;
	}
	
	public void logging_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST logging_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_logging:
			{
				AST tmp727_AST = null;
				tmp727_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp727_AST);
				match(LITERAL_logging);
				logging_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nologging:
			{
				AST tmp728_AST = null;
				tmp728_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp728_AST);
				match(LITERAL_nologging);
				logging_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_filesystem_like_logging:
			{
				AST tmp729_AST = null;
				tmp729_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp729_AST);
				match(LITERAL_filesystem_like_logging);
				logging_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_90);
			} else {
			  throw ex;
			}
		}
		returnAST = logging_clause_AST;
	}
	
	public void table_compression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_compression_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_compress:
			{
				{
				AST tmp730_AST = null;
				tmp730_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp730_AST);
				match(LITERAL_compress);
				{
				switch ( LA(1)) {
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_for:
				{
					{
					AST tmp731_AST = null;
					tmp731_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp731_AST);
					match(LITERAL_for);
					{
					switch ( LA(1)) {
					case LITERAL_all:
					{
						AST tmp732_AST = null;
						tmp732_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp732_AST);
						match(LITERAL_all);
						break;
					}
					case LITERAL_direct_load:
					{
						AST tmp733_AST = null;
						tmp733_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp733_AST);
						match(LITERAL_direct_load);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp734_AST = null;
					tmp734_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp734_AST);
					match(LITERAL_operations);
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_cascade:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_tablespace:
				case LITERAL_online:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_overflow:
				case LITERAL_storage:
				case LITERAL_keep:
				case LITERAL_local:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				table_compression_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nocompress:
			{
				AST tmp735_AST = null;
				tmp735_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp735_AST);
				match(LITERAL_nocompress);
				table_compression_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_91);
			} else {
			  throw ex;
			}
		}
		returnAST = table_compression_AST;
	}
	
	public void storage_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST storage_spec_AST = null;
		
		try {      // for error handling
			AST tmp736_AST = null;
			tmp736_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp736_AST);
			match(LITERAL_storage);
			AST tmp737_AST = null;
			tmp737_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp737_AST);
			match(OPEN_PAREN);
			{
			int _cnt348=0;
			_loop348:
			do {
				if ((_tokenSet_92.member(LA(1)))) {
					storage_params();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt348>=1 ) { break _loop348; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt348++;
			} while (true);
			}
			AST tmp738_AST = null;
			tmp738_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp738_AST);
			match(CLOSE_PAREN);
			storage_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_90);
			} else {
			  throw ex;
			}
		}
		returnAST = storage_spec_AST;
	}
	
	public void table_partitioning_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_partitioning_clause_AST = null;
		
		try {      // for error handling
			if ((LA(1)==LITERAL_partition) && (LA(2)==LITERAL_by) && (LA(3)==LITERAL_range)) {
				range_partitions();
				astFactory.addASTChild(currentAST, returnAST);
				table_partitioning_clause_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_partition) && (LA(2)==LITERAL_by) && (LA(3)==LITERAL_hash)) {
				hash_partitions();
				astFactory.addASTChild(currentAST, returnAST);
				table_partitioning_clause_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_local)) {
				local_partitioned_index();
				astFactory.addASTChild(currentAST, returnAST);
				table_partitioning_clause_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = table_partitioning_clause_AST;
	}
	
	public void parallel_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parallel_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_parallel:
			{
				{
				AST tmp739_AST = null;
				tmp739_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp739_AST);
				match(LITERAL_parallel);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_degree) && (LA(3)==NUMBER||LA(3)==LITERAL_default)) {
					{
					AST tmp740_AST = null;
					tmp740_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp740_AST);
					match(OPEN_PAREN);
					AST tmp741_AST = null;
					tmp741_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp741_AST);
					match(LITERAL_degree);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case LITERAL_default:
					{
						AST tmp742_AST = null;
						tmp742_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp742_AST);
						match(LITERAL_default);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp743_AST = null;
					tmp743_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp743_AST);
					match(LITERAL_instances);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case LITERAL_default:
					{
						AST tmp744_AST = null;
						tmp744_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp744_AST);
						match(LITERAL_default);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp745_AST = null;
					tmp745_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp745_AST);
					match(CLOSE_PAREN);
					}
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_93.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				parallel_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_noparallel:
			{
				AST tmp746_AST = null;
				tmp746_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp746_AST);
				match(LITERAL_noparallel);
				parallel_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_93);
			} else {
			  throw ex;
			}
		}
		returnAST = parallel_clause_AST;
	}
	
	public void alter_table_options() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alter_table_options_AST = null;
		
		try {      // for error handling
			enable_disable_clause();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_validate:
			{
				AST tmp747_AST = null;
				tmp747_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp747_AST);
				match(LITERAL_validate);
				break;
			}
			case LITERAL_novalidate:
			{
				AST tmp748_AST = null;
				tmp748_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp748_AST);
				match(LITERAL_novalidate);
				break;
			}
			case LITERAL_primary:
			case LITERAL_constraint:
			case LITERAL_unique:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_unique:
			{
				{
				AST tmp749_AST = null;
				tmp749_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp749_AST);
				match(LITERAL_unique);
				{
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				match(COMMA);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
				match(CLOSE_PAREN);
				}
				}
				break;
			}
			case LITERAL_primary:
			{
				{
				AST tmp753_AST = null;
				tmp753_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp753_AST);
				match(LITERAL_primary);
				AST tmp754_AST = null;
				tmp754_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp754_AST);
				match(LITERAL_key);
				}
				break;
			}
			case LITERAL_constraint:
			{
				{
				AST tmp755_AST = null;
				tmp755_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp755_AST);
				match(LITERAL_constraint);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			alter_table_options_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = alter_table_options_AST;
	}
	
	public void monitoring_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST monitoring_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_monitoring:
			{
				AST tmp756_AST = null;
				tmp756_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp756_AST);
				match(LITERAL_monitoring);
				monitoring_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nomonitoring:
			{
				AST tmp757_AST = null;
				tmp757_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp757_AST);
				match(LITERAL_nomonitoring);
				monitoring_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = monitoring_clause_AST;
	}
	
	public void range_partitions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST range_partitions_AST = null;
		
		try {      // for error handling
			AST tmp758_AST = null;
			tmp758_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp758_AST);
			match(LITERAL_partition);
			AST tmp759_AST = null;
			tmp759_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp759_AST);
			match(LITERAL_by);
			AST tmp760_AST = null;
			tmp760_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp760_AST);
			match(LITERAL_range);
			match(OPEN_PAREN);
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop282:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop282;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_interval:
			{
				AST tmp764_AST = null;
				tmp764_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp764_AST);
				match(LITERAL_interval);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_store:
				{
					AST tmp765_AST = null;
					tmp765_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp765_AST);
					match(LITERAL_store);
					AST tmp766_AST = null;
					tmp766_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp766_AST);
					match(LITERAL_in);
					AST tmp767_AST = null;
					tmp767_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp767_AST);
					match(OPEN_PAREN);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop286:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop286;
						}
						
					} while (true);
					}
					AST tmp769_AST = null;
					tmp769_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp769_AST);
					match(OPEN_PAREN);
					break;
				}
				case OPEN_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case OPEN_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp770_AST = null;
			tmp770_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp770_AST);
			match(OPEN_PAREN);
			partition_item();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop288:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					partition_item();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop288;
				}
				
			} while (true);
			}
			AST tmp772_AST = null;
			tmp772_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp772_AST);
			match(CLOSE_PAREN);
			range_partitions_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = range_partitions_AST;
	}
	
	public void hash_partitions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST hash_partitions_AST = null;
		
		try {      // for error handling
			AST tmp773_AST = null;
			tmp773_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp773_AST);
			match(LITERAL_partition);
			AST tmp774_AST = null;
			tmp774_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp774_AST);
			match(LITERAL_by);
			AST tmp775_AST = null;
			tmp775_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp775_AST);
			match(LITERAL_hash);
			match(OPEN_PAREN);
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop301:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop301;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				individual_hash_partitions();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_partitions:
			{
				hash_partitions_by_quantity();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			hash_partitions_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = hash_partitions_AST;
	}
	
	public void local_partitioned_index() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST local_partitioned_index_AST = null;
		
		try {      // for error handling
			AST tmp779_AST = null;
			tmp779_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp779_AST);
			match(LITERAL_local);
			{
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_partition) && (_tokenSet_94.member(LA(3)))) {
				on_range_partitioned_table();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_95.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			local_partitioned_index_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_95);
			} else {
			  throw ex;
			}
		}
		returnAST = local_partitioned_index_AST;
	}
	
	public void partition_item() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST partition_item_AST = null;
		
		try {      // for error handling
			AST tmp780_AST = null;
			tmp780_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp780_AST);
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==LITERAL_values)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			range_values_clause();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt292=0;
			_loop292:
			do {
				if ((_tokenSet_96.member(LA(1)))) {
					table_partition_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt292>=1 ) { break _loop292; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt292++;
			} while (true);
			}
			partition_item_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = partition_item_AST;
	}
	
	public void range_values_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST range_values_clause_AST = null;
		
		try {      // for error handling
			AST tmp781_AST = null;
			tmp781_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp781_AST);
			match(LITERAL_values);
			AST tmp782_AST = null;
			tmp782_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp782_AST);
			match(LITERAL_less);
			AST tmp783_AST = null;
			tmp783_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp783_AST);
			match(LITERAL_than);
			AST tmp784_AST = null;
			tmp784_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp784_AST);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case NUMBER:
			{
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_maxvalue:
			{
				AST tmp785_AST = null;
				tmp785_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp785_AST);
				match(LITERAL_maxvalue);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop297:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case LITERAL_maxvalue:
					{
						AST tmp787_AST = null;
						tmp787_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp787_AST);
						match(LITERAL_maxvalue);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop297;
				}
				
			} while (true);
			}
			AST tmp788_AST = null;
			tmp788_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp788_AST);
			match(CLOSE_PAREN);
			range_values_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_96);
			} else {
			  throw ex;
			}
		}
		returnAST = range_values_clause_AST;
	}
	
	public void table_partition_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_partition_description_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_97.member(LA(1))) && (_tokenSet_98.member(LA(2))) && (_tokenSet_99.member(LA(3)))) {
				segment_attributes_clause();
				astFactory.addASTChild(currentAST, returnAST);
				table_partition_description_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_100.member(LA(2))) && (_tokenSet_101.member(LA(3)))) {
				table_compression();
				astFactory.addASTChild(currentAST, returnAST);
				table_partition_description_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_overflow)) {
				AST tmp789_AST = null;
				tmp789_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp789_AST);
				match(LITERAL_overflow);
				table_partition_description_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_102);
			} else {
			  throw ex;
			}
		}
		returnAST = table_partition_description_AST;
	}
	
	public void individual_hash_partitions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST individual_hash_partitions_AST = null;
		
		try {      // for error handling
			AST tmp790_AST = null;
			tmp790_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp790_AST);
			match(OPEN_PAREN);
			AST tmp791_AST = null;
			tmp791_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp791_AST);
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_103.member(LA(2))) && (_tokenSet_104.member(LA(3)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_103.member(LA(1))) && (_tokenSet_104.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop306:
			do {
				if ((_tokenSet_105.member(LA(1)))) {
					partition_storage_clause();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop306;
				}
				
			} while (true);
			}
			{
			_loop311:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					AST tmp793_AST = null;
					tmp793_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp793_AST);
					match(LITERAL_partition);
					{
					if ((_tokenSet_5.member(LA(1))) && (_tokenSet_103.member(LA(2))) && (_tokenSet_104.member(LA(3)))) {
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((_tokenSet_103.member(LA(1))) && (_tokenSet_104.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					_loop310:
					do {
						if ((_tokenSet_105.member(LA(1)))) {
							partition_storage_clause();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop310;
						}
						
					} while (true);
					}
				}
				else {
					break _loop311;
				}
				
			} while (true);
			}
			AST tmp794_AST = null;
			tmp794_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp794_AST);
			match(CLOSE_PAREN);
			individual_hash_partitions_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_95);
			} else {
			  throw ex;
			}
		}
		returnAST = individual_hash_partitions_AST;
	}
	
	public void hash_partitions_by_quantity() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST hash_partitions_by_quantity_AST = null;
		
		try {      // for error handling
			AST tmp795_AST = null;
			tmp795_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp795_AST);
			match(LITERAL_partitions);
			numeric_literal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_store:
			{
				AST tmp796_AST = null;
				tmp796_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp796_AST);
				match(LITERAL_store);
				AST tmp797_AST = null;
				tmp797_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp797_AST);
				match(LITERAL_in);
				AST tmp798_AST = null;
				tmp798_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp798_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop317:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop317;
					}
					
				} while (true);
				}
				AST tmp800_AST = null;
				tmp800_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp800_AST);
				match(CLOSE_PAREN);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_tablespace:
			case LITERAL_online:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_overflow:
			case LITERAL_storage:
			case LITERAL_keep:
			case LITERAL_local:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_106.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				table_compression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_107.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_overflow:
			{
				AST tmp801_AST = null;
				tmp801_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp801_AST);
				match(LITERAL_overflow);
				AST tmp802_AST = null;
				tmp802_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp802_AST);
				match(LITERAL_store);
				AST tmp803_AST = null;
				tmp803_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp803_AST);
				match(LITERAL_in);
				AST tmp804_AST = null;
				tmp804_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp804_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop321:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop321;
					}
					
				} while (true);
				}
				AST tmp806_AST = null;
				tmp806_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp806_AST);
				match(CLOSE_PAREN);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_tablespace:
			case LITERAL_online:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_storage:
			case LITERAL_keep:
			case LITERAL_local:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			hash_partitions_by_quantity_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_95);
			} else {
			  throw ex;
			}
		}
		returnAST = hash_partitions_by_quantity_AST;
	}
	
	public void partition_storage_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST partition_storage_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			{
				{
				AST tmp807_AST = null;
				tmp807_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp807_AST);
				match(LITERAL_tablespace);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
				partition_storage_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_overflow:
			{
				AST tmp808_AST = null;
				tmp808_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp808_AST);
				match(LITERAL_overflow);
				partition_storage_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_compress:
			case LITERAL_nocompress:
			{
				table_compression();
				astFactory.addASTChild(currentAST, returnAST);
				partition_storage_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_103);
			} else {
			  throw ex;
			}
		}
		returnAST = partition_storage_clause_AST;
	}
	
	public void external_table_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST external_table_spec_AST = null;
		
		try {      // for error handling
			match(LITERAL_external);
			match(OPEN_PAREN);
			AST tmp811_AST = null;
			tmp811_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp811_AST);
			match(LITERAL_type);
			{
			switch ( LA(1)) {
			case LITERAL_oracle_loader:
			{
				oracle_loader_params();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_oracle_datapump:
			{
				oracle_datapump_params();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			location();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			{
			_loop1304:
			do {
				if ((LA(1)==LITERAL_reject)) {
					reject_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_parallel||LA(1)==LITERAL_noparallel) && (_tokenSet_108.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					parallel_clause();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1304;
				}
				
			} while (true);
			}
			external_table_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
		returnAST = external_table_spec_AST;
	}
	
	public void reject_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST reject_spec_AST = null;
		
		try {      // for error handling
			AST tmp813_AST = null;
			tmp813_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp813_AST);
			match(LITERAL_reject);
			AST tmp814_AST = null;
			tmp814_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp814_AST);
			match(LITERAL_limit);
			{
			switch ( LA(1)) {
			case LITERAL_unlimited:
			{
				AST tmp815_AST = null;
				tmp815_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp815_AST);
				match(LITERAL_unlimited);
				break;
			}
			case NUMBER:
			{
				AST tmp816_AST = null;
				tmp816_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp816_AST);
				match(NUMBER);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			reject_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_109);
			} else {
			  throw ex;
			}
		}
		returnAST = reject_spec_AST;
	}
	
	public void storage_params() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST storage_params_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_initial:
			{
				{
				AST tmp817_AST = null;
				tmp817_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp817_AST);
				match(LITERAL_initial);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					AST tmp818_AST = null;
					tmp818_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp818_AST);
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_next:
			{
				{
				AST tmp819_AST = null;
				tmp819_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp819_AST);
				match(LITERAL_next);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					AST tmp820_AST = null;
					tmp820_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp820_AST);
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_minextents:
			{
				{
				AST tmp821_AST = null;
				tmp821_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp821_AST);
				match(LITERAL_minextents);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_maxextents:
			{
				{
				AST tmp822_AST = null;
				tmp822_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp822_AST);
				match(LITERAL_maxextents);
				{
				switch ( LA(1)) {
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_unlimited:
				{
					AST tmp823_AST = null;
					tmp823_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp823_AST);
					match(LITERAL_unlimited);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_pctincrease:
			{
				{
				AST tmp824_AST = null;
				tmp824_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp824_AST);
				match(LITERAL_pctincrease);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_freelists:
			{
				{
				AST tmp825_AST = null;
				tmp825_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp825_AST);
				match(LITERAL_freelists);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_freelist:
			{
				{
				AST tmp826_AST = null;
				tmp826_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp826_AST);
				match(LITERAL_freelist);
				AST tmp827_AST = null;
				tmp827_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp827_AST);
				match(LITERAL_groups);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_optimal:
			{
				{
				AST tmp828_AST = null;
				tmp828_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp828_AST);
				match(LITERAL_optimal);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					AST tmp829_AST = null;
					tmp829_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp829_AST);
					match(STORAGE_SIZE);
					break;
				}
				case LITERAL_null:
				{
					AST tmp830_AST = null;
					tmp830_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp830_AST);
					match(LITERAL_null);
					break;
				}
				case CLOSE_PAREN:
				case LITERAL_initial:
				case LITERAL_next:
				case LITERAL_minextents:
				case LITERAL_maxextents:
				case LITERAL_pctincrease:
				case LITERAL_freelists:
				case LITERAL_freelist:
				case LITERAL_optimal:
				case LITERAL_buffer_pool:
				case LITERAL_encrypt:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_buffer_pool:
			{
				{
				AST tmp831_AST = null;
				tmp831_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp831_AST);
				match(LITERAL_buffer_pool);
				{
				switch ( LA(1)) {
				case LITERAL_keep:
				{
					AST tmp832_AST = null;
					tmp832_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp832_AST);
					match(LITERAL_keep);
					break;
				}
				case LITERAL_recycle:
				{
					AST tmp833_AST = null;
					tmp833_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp833_AST);
					match(LITERAL_recycle);
					break;
				}
				case LITERAL_default:
				{
					AST tmp834_AST = null;
					tmp834_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp834_AST);
					match(LITERAL_default);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_encrypt:
			{
				AST tmp835_AST = null;
				tmp835_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp835_AST);
				match(LITERAL_encrypt);
				storage_params_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_110);
			} else {
			  throw ex;
			}
		}
		returnAST = storage_params_AST;
	}
	
	public void constraint_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constraint_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(CONSTRAINT_NAME);
			}
			constraint_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
		returnAST = constraint_name_AST;
	}
	
	public void pk_spec_constr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST pk_spec_constr_AST = null;
		
		try {      // for error handling
			match(LITERAL_primary);
			match(LITERAL_key);
			match(OPEN_PAREN);
			column_name_ddl_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(PK_SPEC);
			}
			pk_spec_constr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = pk_spec_constr_AST;
	}
	
	public void fk_spec_constr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fk_spec_constr_AST = null;
		
		try {      // for error handling
			match(LITERAL_foreign);
			match(LITERAL_key);
			match(OPEN_PAREN);
			column_name_ddl_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			match(LITERAL_references);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			match(OPEN_PAREN);
			column_name_ddl_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_disable:
			{
				AST tmp847_AST = null;
				tmp847_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp847_AST);
				match(LITERAL_disable);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_on:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_on) && (LA(2)==LITERAL_update) && (_tokenSet_112.member(LA(3)))) {
				AST tmp848_AST = null;
				tmp848_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp848_AST);
				match(LITERAL_on);
				AST tmp849_AST = null;
				tmp849_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp849_AST);
				match(LITERAL_update);
				referential_actions();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==COMMA||LA(1)==CLOSE_PAREN||LA(1)==LITERAL_on) && (_tokenSet_113.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_on:
			{
				AST tmp850_AST = null;
				tmp850_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp850_AST);
				match(LITERAL_on);
				AST tmp851_AST = null;
				tmp851_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp851_AST);
				match(LITERAL_delete);
				referential_actions();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(FK_SPEC);
			}
			fk_spec_constr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = fk_spec_constr_AST;
	}
	
	public void unique_contsr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unique_contsr_AST = null;
		
		try {      // for error handling
			AST tmp852_AST = null;
			tmp852_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp852_AST);
			match(LITERAL_unique);
			AST tmp853_AST = null;
			tmp853_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp853_AST);
			match(OPEN_PAREN);
			column_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop374:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ddl();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop374;
				}
				
			} while (true);
			}
			AST tmp855_AST = null;
			tmp855_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp855_AST);
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(UNIQUE_CONSTRAINT);
			}
			unique_contsr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = unique_contsr_AST;
	}
	
	public void column_name_ddl_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_name_ddl_list_AST = null;
		
		try {      // for error handling
			column_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop377:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ddl();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop377;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_LIST);
			}
			column_name_ddl_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = column_name_ddl_list_AST;
	}
	
	public void referential_actions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST referential_actions_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cascade:
			{
				AST tmp857_AST = null;
				tmp857_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp857_AST);
				match(LITERAL_cascade);
				referential_actions_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_restrict:
			{
				AST tmp858_AST = null;
				tmp858_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp858_AST);
				match(LITERAL_restrict);
				referential_actions_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_no:
			{
				{
				AST tmp859_AST = null;
				tmp859_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp859_AST);
				match(LITERAL_no);
				AST tmp860_AST = null;
				tmp860_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp860_AST);
				match(LITERAL_action);
				}
				referential_actions_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((LA(1)==LITERAL_set) && (LA(2)==LITERAL_null)) {
					{
					AST tmp861_AST = null;
					tmp861_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp861_AST);
					match(LITERAL_set);
					AST tmp862_AST = null;
					tmp862_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp862_AST);
					match(LITERAL_null);
					}
					referential_actions_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_set) && (LA(2)==LITERAL_default)) {
					{
					AST tmp863_AST = null;
					tmp863_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp863_AST);
					match(LITERAL_set);
					AST tmp864_AST = null;
					tmp864_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp864_AST);
					match(LITERAL_default);
					}
					referential_actions_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_114);
			} else {
			  throw ex;
			}
		}
		returnAST = referential_actions_AST;
	}
	
	public void alter_table() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alter_table_AST = null;
		
		try {      // for error handling
			match(LITERAL_alter);
			match(LITERAL_table);
			table_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((_tokenSet_115.member(LA(1))) && (_tokenSet_116.member(LA(2))) && (_tokenSet_117.member(LA(3)))) {
				constraint_clause();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_disable:
			case LITERAL_enable:
			{
				alter_table_options();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(ALTER_TABLE);
			}
			alter_table_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = alter_table_AST;
	}
	
	public void constraint_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constraint_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_add:
			{
				{
				AST tmp867_AST = null;
				tmp867_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp867_AST);
				match(LITERAL_add);
				{
				if ((_tokenSet_119.member(LA(1)))) {
					add_syntax_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==OPEN_PAREN)) {
					{
					match(OPEN_PAREN);
					add_syntax_2();
					astFactory.addASTChild(currentAST, returnAST);
					match(CLOSE_PAREN);
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_modify:
			{
				{
				AST tmp870_AST = null;
				tmp870_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp870_AST);
				match(LITERAL_modify);
				{
				if ((LA(1)==LITERAL_primary||LA(1)==LITERAL_constraint||LA(1)==LITERAL_unique) && (_tokenSet_120.member(LA(2))) && (_tokenSet_42.member(LA(3)))) {
					modify_constraint_clause();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==OPEN_PAREN)) {
					{
					match(OPEN_PAREN);
					modify_syntax_2();
					astFactory.addASTChild(currentAST, returnAST);
					match(CLOSE_PAREN);
					}
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_121.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					modify_syntax_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rename:
			{
				{
				AST tmp873_AST = null;
				tmp873_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp873_AST);
				match(LITERAL_rename);
				{
				switch ( LA(1)) {
				case LITERAL_constraint:
				{
					{
					AST tmp874_AST = null;
					tmp874_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp874_AST);
					match(LITERAL_constraint);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp875_AST = null;
					tmp875_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp875_AST);
					match(LITERAL_to);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				case LITERAL_to:
				{
					{
					AST tmp876_AST = null;
					tmp876_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp876_AST);
					match(LITERAL_to);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				case LITERAL_column:
				{
					{
					AST tmp877_AST = null;
					tmp877_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp877_AST);
					match(LITERAL_column);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp878_AST = null;
					tmp878_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp878_AST);
					match(LITERAL_to);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_drop:
			{
				AST tmp879_AST = null;
				tmp879_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp879_AST);
				match(LITERAL_drop);
				{
				switch ( LA(1)) {
				case LITERAL_column:
				{
					{
					AST tmp880_AST = null;
					tmp880_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp880_AST);
					match(LITERAL_column);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				case LITERAL_primary:
				case LITERAL_constraint:
				case LITERAL_unique:
				{
					drop_clause();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_118);
			} else {
			  throw ex;
			}
		}
		returnAST = constraint_clause_AST;
	}
	
	public void add_syntax_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST add_syntax_1_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_121.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				column_def_alter();
				astFactory.addASTChild(currentAST, returnAST);
				add_syntax_1_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_122.member(LA(1))) && (_tokenSet_123.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				inline_out_of_line_constraint();
				astFactory.addASTChild(currentAST, returnAST);
				add_syntax_1_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_118);
			} else {
			  throw ex;
			}
		}
		returnAST = add_syntax_1_AST;
	}
	
	public void add_syntax_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST add_syntax_2_AST = null;
		
		try {      // for error handling
			boolean synPredMatched415 = false;
			if (((_tokenSet_5.member(LA(1))) && (_tokenSet_124.member(LA(2))) && (_tokenSet_125.member(LA(3))))) {
				int _m415 = mark();
				synPredMatched415 = true;
				inputState.guessing++;
				try {
					{
					column_def_alter();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched415 = false;
				}
				rewind(_m415);
				inputState.guessing--;
			}
			if ( synPredMatched415 ) {
				{
				column_def_alter();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop418:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_def_alter();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop418;
					}
					
				} while (true);
				}
				}
				add_syntax_2_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_122.member(LA(1))) && (_tokenSet_126.member(LA(2))) && (_tokenSet_127.member(LA(3)))) {
				inline_out_of_line_constraint();
				astFactory.addASTChild(currentAST, returnAST);
				add_syntax_2_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = add_syntax_2_AST;
	}
	
	public void modify_constraint_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST modify_constraint_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				{
				AST tmp882_AST = null;
				tmp882_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp882_AST);
				match(LITERAL_constraint);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_rely:
				{
					AST tmp883_AST = null;
					tmp883_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp883_AST);
					match(LITERAL_rely);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_validate:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_novalidate:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_128.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp884_AST = null;
					tmp884_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp884_AST);
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_128.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp885_AST = null;
					tmp885_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp885_AST);
					match(LITERAL_enable);
				}
				else if ((_tokenSet_128.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_validate:
				{
					AST tmp886_AST = null;
					tmp886_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp886_AST);
					match(LITERAL_validate);
					break;
				}
				case LITERAL_novalidate:
				{
					AST tmp887_AST = null;
					tmp887_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp887_AST);
					match(LITERAL_novalidate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				modify_constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_primary:
			{
				{
				AST tmp888_AST = null;
				tmp888_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp888_AST);
				match(LITERAL_primary);
				AST tmp889_AST = null;
				tmp889_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp889_AST);
				match(LITERAL_key);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					AST tmp890_AST = null;
					tmp890_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp890_AST);
					match(OPEN_PAREN);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop408:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop408;
						}
						
					} while (true);
					}
					AST tmp892_AST = null;
					tmp892_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp892_AST);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				modify_constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_unique:
			{
				{
				AST tmp893_AST = null;
				tmp893_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp893_AST);
				match(LITERAL_unique);
				AST tmp894_AST = null;
				tmp894_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp894_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop411:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop411;
					}
					
				} while (true);
				}
				AST tmp896_AST = null;
				tmp896_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp896_AST);
				match(CLOSE_PAREN);
				}
				modify_constraint_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_118);
			} else {
			  throw ex;
			}
		}
		returnAST = modify_constraint_clause_AST;
	}
	
	public void modify_syntax_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST modify_syntax_2_AST = null;
		
		try {      // for error handling
			column_def_alter();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop422:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_def_alter();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop422;
				}
				
			} while (true);
			}
			modify_syntax_2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = modify_syntax_2_AST;
	}
	
	public void modify_syntax_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST modify_syntax_1_AST = null;
		
		try {      // for error handling
			column_def_alter();
			astFactory.addASTChild(currentAST, returnAST);
			modify_syntax_1_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_118);
			} else {
			  throw ex;
			}
		}
		returnAST = modify_syntax_1_AST;
	}
	
	public void drop_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST drop_clause_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				{
				AST tmp898_AST = null;
				tmp898_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp898_AST);
				match(LITERAL_primary);
				AST tmp899_AST = null;
				tmp899_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp899_AST);
				match(LITERAL_key);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					AST tmp900_AST = null;
					tmp900_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp900_AST);
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_keep:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_118.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case LITERAL_keep:
					{
						AST tmp901_AST = null;
						tmp901_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp901_AST);
						match(LITERAL_keep);
						break;
					}
					case LITERAL_drop:
					{
						AST tmp902_AST = null;
						tmp902_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp902_AST);
						match(LITERAL_drop);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp903_AST = null;
					tmp903_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp903_AST);
					match(LITERAL_index);
				}
				else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				drop_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_unique:
			{
				{
				AST tmp904_AST = null;
				tmp904_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp904_AST);
				match(LITERAL_unique);
				AST tmp905_AST = null;
				tmp905_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp905_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop463:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop463;
					}
					
				} while (true);
				}
				AST tmp907_AST = null;
				tmp907_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp907_AST);
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					AST tmp908_AST = null;
					tmp908_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp908_AST);
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_keep:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_118.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case LITERAL_keep:
					{
						AST tmp909_AST = null;
						tmp909_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp909_AST);
						match(LITERAL_keep);
						break;
					}
					case LITERAL_drop:
					{
						AST tmp910_AST = null;
						tmp910_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp910_AST);
						match(LITERAL_drop);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp911_AST = null;
					tmp911_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp911_AST);
					match(LITERAL_index);
				}
				else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				drop_clause_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_constraint:
			{
				{
				AST tmp912_AST = null;
				tmp912_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp912_AST);
				match(LITERAL_constraint);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					AST tmp913_AST = null;
					tmp913_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp913_AST);
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				drop_clause_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_118);
			} else {
			  throw ex;
			}
		}
		returnAST = drop_clause_AST;
	}
	
	public void column_def_alter() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_def_alter_AST = null;
		
		try {      // for error handling
			column_name_ddl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_long:
			case LITERAL_interval:
			case LITERAL_binary_integer:
			case LITERAL_natural:
			case LITERAL_positive:
			case LITERAL_number:
			case LITERAL_char:
			case LITERAL_raw:
			case LITERAL_boolean:
			case LITERAL_date:
			case LITERAL_timestamp:
			case LITERAL_smallint:
			case LITERAL_real:
			case LITERAL_numeric:
			case LITERAL_int:
			case LITERAL_integer:
			case LITERAL_pls_integer:
			case LITERAL_double:
			case LITERAL_float:
			case LITERAL_decimal:
			case LITERAL_varchar:
			case 599:
			case LITERAL_nvarchar:
			case 601:
			case LITERAL_character:
			case LITERAL_mlslabel:
			case LITERAL_blob:
			case LITERAL_clob:
			{
				datatype();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_default:
			case LITERAL_not:
			case LITERAL_null:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_null:
			{
				not_null();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_default:
			{
				{
				AST tmp914_AST = null;
				tmp914_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp914_AST);
				match(LITERAL_default);
				{
				switch ( LA(1)) {
				case LITERAL_sysdate:
				{
					AST tmp915_AST = null;
					tmp915_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp915_AST);
					match(LITERAL_sysdate);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				pk_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_references:
			{
				fk_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_constraint:
			{
				column_constraint();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			column_def_alter_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		returnAST = column_def_alter_AST;
	}
	
	public void inline_out_of_line_constraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST inline_out_of_line_constraint_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				AST tmp916_AST = null;
				tmp916_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp916_AST);
				match(LITERAL_constraint);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_not:
			case LITERAL_null:
			case LITERAL_primary:
			case LITERAL_unique:
			case LITERAL_check:
			case LITERAL_foreign:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_null:
			{
				not_null();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_unique:
			{
				{
				AST tmp917_AST = null;
				tmp917_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp917_AST);
				match(LITERAL_unique);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					AST tmp918_AST = null;
					tmp918_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp918_AST);
					match(OPEN_PAREN);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop436:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop436;
						}
						
					} while (true);
					}
					AST tmp920_AST = null;
					tmp920_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp920_AST);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_129.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_primary:
			{
				{
				AST tmp921_AST = null;
				tmp921_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp921_AST);
				match(LITERAL_primary);
				AST tmp922_AST = null;
				tmp922_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp922_AST);
				match(LITERAL_key);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					AST tmp923_AST = null;
					tmp923_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp923_AST);
					match(OPEN_PAREN);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop440:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop440;
						}
						
					} while (true);
					}
					AST tmp925_AST = null;
					tmp925_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp925_AST);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_129.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_foreign:
			{
				{
				AST tmp926_AST = null;
				tmp926_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp926_AST);
				match(LITERAL_foreign);
				AST tmp927_AST = null;
				tmp927_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp927_AST);
				match(LITERAL_key);
				{
				AST tmp928_AST = null;
				tmp928_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp928_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop444:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop444;
					}
					
				} while (true);
				}
				AST tmp930_AST = null;
				tmp930_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp930_AST);
				match(CLOSE_PAREN);
				}
				AST tmp931_AST = null;
				tmp931_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp931_AST);
				match(LITERAL_references);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				AST tmp932_AST = null;
				tmp932_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp932_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop447:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop447;
					}
					
				} while (true);
				}
				AST tmp934_AST = null;
				tmp934_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp934_AST);
				match(CLOSE_PAREN);
				}
				{
				if ((LA(1)==LITERAL_rely) && (_tokenSet_130.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp935_AST = null;
					tmp935_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp935_AST);
					match(LITERAL_rely);
				}
				else if ((_tokenSet_130.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_130.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp936_AST = null;
					tmp936_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp936_AST);
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_130.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp937_AST = null;
					tmp937_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp937_AST);
					match(LITERAL_enable);
				}
				else if ((_tokenSet_130.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_validate:
				{
					AST tmp938_AST = null;
					tmp938_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp938_AST);
					match(LITERAL_validate);
					break;
				}
				case LITERAL_novalidate:
				{
					AST tmp939_AST = null;
					tmp939_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp939_AST);
					match(LITERAL_novalidate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_on:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_rely:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_on:
				{
					AST tmp940_AST = null;
					tmp940_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp940_AST);
					match(LITERAL_on);
					AST tmp941_AST = null;
					tmp941_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp941_AST);
					match(LITERAL_delete);
					{
					switch ( LA(1)) {
					case LITERAL_cascade:
					{
						AST tmp942_AST = null;
						tmp942_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp942_AST);
						match(LITERAL_cascade);
						break;
					}
					case LITERAL_set:
					{
						{
						AST tmp943_AST = null;
						tmp943_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp943_AST);
						match(LITERAL_set);
						AST tmp944_AST = null;
						tmp944_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp944_AST);
						match(LITERAL_null);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_rely:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case LITERAL_check:
			{
				{
				AST tmp945_AST = null;
				tmp945_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp945_AST);
				match(LITERAL_check);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_using:
			case LITERAL_rely:
			{
				using_index_clause();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			inline_out_of_line_constraint_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
			} else {
			  throw ex;
			}
		}
		returnAST = inline_out_of_line_constraint_AST;
	}
	
	public void constraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constraint_AST = null;
		
		try {      // for error handling
			inline_out_of_line_constraint();
			astFactory.addASTChild(currentAST, returnAST);
			constraint_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = constraint_AST;
	}
	
	public void using_index_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST using_index_clause_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_rely:
			{
				AST tmp946_AST = null;
				tmp946_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp946_AST);
				match(LITERAL_rely);
				break;
			}
			case LITERAL_using:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp947_AST = null;
			tmp947_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp947_AST);
			match(LITERAL_using);
			AST tmp948_AST = null;
			tmp948_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp948_AST);
			match(LITERAL_index);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_132.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				{
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp949_AST = null;
					tmp949_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp949_AST);
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_133.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else if ((_tokenSet_134.member(LA(1))) && (_tokenSet_135.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				index_properties();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_enable) && (_tokenSet_133.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				AST tmp950_AST = null;
				tmp950_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp950_AST);
				match(LITERAL_enable);
			}
			else if ((_tokenSet_133.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			using_index_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_133);
			} else {
			  throw ex;
			}
		}
		returnAST = using_index_clause_AST;
	}
	
	public void enable_disable_clause2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST enable_disable_clause2_AST = null;
		
		try {      // for error handling
			using_index_clause();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_cascade:
			{
				AST tmp951_AST = null;
				tmp951_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp951_AST);
				match(LITERAL_cascade);
				break;
			}
			case EOF:
			case LITERAL_drop:
			case LITERAL_keep:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_drop:
			case LITERAL_keep:
			{
				{
				switch ( LA(1)) {
				case LITERAL_keep:
				{
					AST tmp952_AST = null;
					tmp952_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp952_AST);
					match(LITERAL_keep);
					break;
				}
				case LITERAL_drop:
				{
					AST tmp953_AST = null;
					tmp953_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp953_AST);
					match(LITERAL_drop);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp954_AST = null;
				tmp954_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp954_AST);
				match(LITERAL_index);
				break;
			}
			case EOF:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			enable_disable_clause2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = enable_disable_clause2_AST;
	}
	
	public void index_properties() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_properties_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			case LITERAL_online:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_storage:
			case LITERAL_sort:
			case LITERAL_nosort:
			case LITERAL_reverse:
			case LITERAL_visible:
			case LITERAL_novisible:
			{
				{
				{
				int _cnt482=0;
				_loop482:
				do {
					if ((_tokenSet_136.member(LA(1)))) {
						index_attributes();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt482>=1 ) { break _loop482; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt482++;
				} while (true);
				}
				}
				index_properties_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_global:
			{
				global_partitioned_index();
				astFactory.addASTChild(currentAST, returnAST);
				index_properties_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_local:
			{
				local_partitioned_index();
				astFactory.addASTChild(currentAST, returnAST);
				index_properties_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_133);
			} else {
			  throw ex;
			}
		}
		returnAST = index_properties_AST;
	}
	
	public void index_attributes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_attributes_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			{
				{
				AST tmp955_AST = null;
				tmp955_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp955_AST);
				match(LITERAL_tablespace);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_137.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_default) && (_tokenSet_137.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					AST tmp956_AST = null;
					tmp956_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp956_AST);
					match(LITERAL_default);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_storage:
			{
				physical_attributes_clause();
				astFactory.addASTChild(currentAST, returnAST);
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			{
				logging_clause();
				astFactory.addASTChild(currentAST, returnAST);
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_online:
			{
				AST tmp957_AST = null;
				tmp957_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp957_AST);
				match(LITERAL_online);
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_sort:
			case LITERAL_nosort:
			{
				{
				switch ( LA(1)) {
				case LITERAL_sort:
				{
					AST tmp958_AST = null;
					tmp958_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp958_AST);
					match(LITERAL_sort);
					break;
				}
				case LITERAL_nosort:
				{
					AST tmp959_AST = null;
					tmp959_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp959_AST);
					match(LITERAL_nosort);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_reverse:
			{
				AST tmp960_AST = null;
				tmp960_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp960_AST);
				match(LITERAL_reverse);
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_visible:
			case LITERAL_novisible:
			{
				{
				switch ( LA(1)) {
				case LITERAL_visible:
				{
					AST tmp961_AST = null;
					tmp961_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp961_AST);
					match(LITERAL_visible);
					break;
				}
				case LITERAL_novisible:
				{
					AST tmp962_AST = null;
					tmp962_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp962_AST);
					match(LITERAL_novisible);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_parallel:
			case LITERAL_noparallel:
			{
				parallel_clause();
				astFactory.addASTChild(currentAST, returnAST);
				index_attributes_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_137);
			} else {
			  throw ex;
			}
		}
		returnAST = index_attributes_AST;
	}
	
	public void global_partitioned_index() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST global_partitioned_index_AST = null;
		
		try {      // for error handling
			AST tmp963_AST = null;
			tmp963_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp963_AST);
			match(LITERAL_global);
			AST tmp964_AST = null;
			tmp964_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp964_AST);
			match(LITERAL_partition);
			AST tmp965_AST = null;
			tmp965_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp965_AST);
			match(LITERAL_by);
			{
			switch ( LA(1)) {
			case LITERAL_range:
			{
				{
				AST tmp966_AST = null;
				tmp966_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp966_AST);
				match(LITERAL_range);
				AST tmp967_AST = null;
				tmp967_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp967_AST);
				match(OPEN_PAREN);
				index_column_spec_list();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp968_AST = null;
				tmp968_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp968_AST);
				match(CLOSE_PAREN);
				AST tmp969_AST = null;
				tmp969_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp969_AST);
				match(OPEN_PAREN);
				index_partitioning_clause();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp970_AST = null;
				tmp970_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp970_AST);
				match(CLOSE_PAREN);
				}
				break;
			}
			case LITERAL_hash:
			{
				{
				AST tmp971_AST = null;
				tmp971_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp971_AST);
				match(LITERAL_hash);
				AST tmp972_AST = null;
				tmp972_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp972_AST);
				match(OPEN_PAREN);
				index_column_spec_list();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp973_AST = null;
				tmp973_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp973_AST);
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					individual_hash_partitions();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_partitions:
				{
					hash_partitions_by_quantity();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			global_partitioned_index_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_133);
			} else {
			  throw ex;
			}
		}
		returnAST = global_partitioned_index_AST;
	}
	
	public void index_partitioning_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_partitioning_clause_AST = null;
		
		try {      // for error handling
			AST tmp974_AST = null;
			tmp974_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp974_AST);
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==LITERAL_values)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			AST tmp975_AST = null;
			tmp975_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp975_AST);
			match(LITERAL_values);
			AST tmp976_AST = null;
			tmp976_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp976_AST);
			match(LITERAL_less);
			AST tmp977_AST = null;
			tmp977_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp977_AST);
			match(LITERAL_than);
			AST tmp978_AST = null;
			tmp978_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp978_AST);
			match(OPEN_PAREN);
			numeric_literal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop496:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					numeric_literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop496;
				}
				
			} while (true);
			}
			AST tmp980_AST = null;
			tmp980_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp980_AST);
			match(CLOSE_PAREN);
			segment_attributes_clause();
			astFactory.addASTChild(currentAST, returnAST);
			index_partitioning_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = index_partitioning_clause_AST;
	}
	
	public void on_range_partitioned_table() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST on_range_partitioned_table_AST = null;
		
		try {      // for error handling
			AST tmp981_AST = null;
			tmp981_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp981_AST);
			match(OPEN_PAREN);
			local_partition_item();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop501:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					local_partition_item();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop501;
				}
				
			} while (true);
			}
			AST tmp983_AST = null;
			tmp983_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp983_AST);
			match(CLOSE_PAREN);
			on_range_partitioned_table_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_95);
			} else {
			  throw ex;
			}
		}
		returnAST = on_range_partitioned_table_AST;
	}
	
	public void local_partition_item() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST local_partition_item_AST = null;
		
		try {      // for error handling
			AST tmp984_AST = null;
			tmp984_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp984_AST);
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_138.member(LA(2))) && (_tokenSet_139.member(LA(3)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_138.member(LA(1))) && (_tokenSet_139.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop505:
			do {
				if ((_tokenSet_97.member(LA(1))) && (_tokenSet_140.member(LA(2))) && (_tokenSet_141.member(LA(3)))) {
					segment_attributes_clause();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_142.member(LA(2))) && (_tokenSet_143.member(LA(3)))) {
					table_compression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop505;
				}
				
			} while (true);
			}
			local_partition_item_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = local_partition_item_AST;
	}
	
	public void type_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TYPE_NAME);
			}
			type_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_144);
			} else {
			  throw ex;
			}
		}
		returnAST = type_name_AST;
	}
	
	public void record_item() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST record_item_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			record_item_name();
			astFactory.addASTChild(currentAST, returnAST);
			type_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp985_AST = null;
				tmp985_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp985_AST);
				match(LITERAL_not);
				AST tmp986_AST = null;
				tmp986_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp986_AST);
				match(LITERAL_null);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				{
				switch ( LA(1)) {
				case LITERAL_default:
				{
					default1();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case ASSIGNMENT_EQ:
				{
					p = LT(1);
					p_AST = astFactory.create(p);
					astFactory.addASTChild(currentAST, p_AST);
					match(ASSIGNMENT_EQ);
					if ( inputState.guessing==0 ) {
						p_AST.setType(ASSIGNMENT_OP);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(RECORD_ITEM);
			}
			record_item_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = record_item_AST;
	}
	
	public void record_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST record_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			record_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_145);
			} else {
			  throw ex;
			}
		}
		returnAST = record_name_AST;
	}
	
	public void v_column_def() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST v_column_def_AST = null;
		
		try {      // for error handling
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(V_COLUMN_DEF);
			}
			v_column_def_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = v_column_def_AST;
	}
	
	public void name_fragment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST name_fragment_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
			}
			name_fragment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
			} else {
			  throw ex;
			}
		}
		returnAST = name_fragment_AST;
	}
	
	public void serially_reusable_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST serially_reusable_pragma_AST = null;
		
		try {      // for error handling
			AST tmp987_AST = null;
			tmp987_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp987_AST);
			match(LITERAL_pragma);
			AST tmp988_AST = null;
			tmp988_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp988_AST);
			match(LITERAL_serially_reusable);
			if ( inputState.guessing==0 ) {
				__markRule(SERIALLY_REUSABLE_PRAGMA);
			}
			serially_reusable_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = serially_reusable_pragma_AST;
	}
	
	public void package_obj_spec_ex() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_obj_spec_ex_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_65.member(LA(1)))) {
				package_obj_spec();
				astFactory.addASTChild(currentAST, returnAST);
				package_obj_spec_ex_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==IF_COND_CMPL)) {
				{
				AST tmp989_AST = null;
				tmp989_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp989_AST);
				match(IF_COND_CMPL);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp990_AST = null;
				tmp990_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp990_AST);
				match(THEN_COND_CMPL);
				cond_comp_seq();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case ELSE_COND_CMPL:
				{
					AST tmp991_AST = null;
					tmp991_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp991_AST);
					match(ELSE_COND_CMPL);
					cond_comp_seq();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case END_COND_CMPL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp992_AST = null;
				tmp992_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp992_AST);
				match(END_COND_CMPL);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CONDITIONAL_COMPILATION);
				}
				package_obj_spec_ex_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = package_obj_spec_ex_AST;
	}
	
	public void package_obj_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_obj_spec_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_subtype:
			{
				subtype_declaration();
				astFactory.addASTChild(currentAST, returnAST);
				package_obj_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_procedure:
			{
				procedure_spec();
				astFactory.addASTChild(currentAST, returnAST);
				package_obj_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_function:
			{
				function_spec();
				astFactory.addASTChild(currentAST, returnAST);
				package_obj_spec_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_148.member(LA(2))) && (_tokenSet_149.member(LA(3)))) {
					variable_declaration();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_cursor) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_is)) {
					cursor_declaration();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_cursor) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_return)) {
					cursor_spec();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_type) && (_tokenSet_5.member(LA(2))) && (LA(3)==LITERAL_is||LA(3)==LITERAL_as)) {
					{
					type_definition();
					astFactory.addASTChild(currentAST, returnAST);
					match(SEMI);
					}
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
					exception_declaration();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_exception_init)) {
					exception_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_restrict_references)) {
					restrict_ref_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_interface)) {
					interface_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_builtin)) {
					builtin_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_fipsflag)) {
					fipsflag_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_timestamp)) {
					timestamp_pragma();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_spec_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = package_obj_spec_AST;
	}
	
	public void cond_comp_seq() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cond_comp_seq_AST = null;
		
		try {      // for error handling
			{
			_loop549:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop549;
				}
				
			} while (true);
			}
			{
			_loop551:
			do {
				if ((_tokenSet_65.member(LA(1)))) {
					package_obj_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop551;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COND_COMP_SEQ);
			}
			cond_comp_seq_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_151);
			} else {
			  throw ex;
			}
		}
		returnAST = cond_comp_seq_AST;
	}
	
	public void error_cond_compliation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST error_cond_compliation_AST = null;
		
		try {      // for error handling
			AST tmp994_AST = null;
			tmp994_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp994_AST);
			match(ERROR_COND_CMPL);
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp995_AST = null;
			tmp995_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp995_AST);
			match(END_COND_CMPL);
			if ( inputState.guessing==0 ) {
				__markRule(COND_COMP_ERROR);
			}
			error_cond_compliation_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_152);
			} else {
			  throw ex;
			}
		}
		returnAST = error_cond_compliation_AST;
	}
	
	public void package_obj_body() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_obj_body_AST = null;
		
		try {      // for error handling
			boolean synPredMatched595 = false;
			if (((LA(1)==LITERAL_function) && (_tokenSet_18.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
				int _m595 = mark();
				synPredMatched595 = true;
				inputState.guessing++;
				try {
					{
					function_declaration();
					{
					switch ( LA(1)) {
					case LITERAL_is:
					{
						match(LITERAL_is);
						break;
					}
					case LITERAL_as:
					{
						match(LITERAL_as);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched595 = false;
				}
				rewind(_m595);
				inputState.guessing--;
			}
			if ( synPredMatched595 ) {
				function_body();
				astFactory.addASTChild(currentAST, returnAST);
				package_obj_body_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched598 = false;
				if (((LA(1)==LITERAL_procedure) && (_tokenSet_18.member(LA(2))) && (_tokenSet_153.member(LA(3))))) {
					int _m598 = mark();
					synPredMatched598 = true;
					inputState.guessing++;
					try {
						{
						procedure_declaration();
						{
						switch ( LA(1)) {
						case LITERAL_is:
						{
							match(LITERAL_is);
							break;
						}
						case LITERAL_as:
						{
							match(LITERAL_as);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched598 = false;
					}
					rewind(_m598);
					inputState.guessing--;
				}
				if ( synPredMatched598 ) {
					procedure_body();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_body_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_65.member(LA(1))) && (_tokenSet_154.member(LA(2))) && (_tokenSet_155.member(LA(3)))) {
					package_obj_spec();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_body_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==IF_COND_CMPL)) {
					condition_compilation();
					astFactory.addASTChild(currentAST, returnAST);
					package_obj_body_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_72);
				} else {
				  throw ex;
				}
			}
			returnAST = package_obj_body_AST;
		}
		
	public void package_init_section() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST package_init_section_AST = null;
		
		try {      // for error handling
			AST tmp996_AST = null;
			tmp996_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp996_AST);
			match(LITERAL_begin);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PACKAGE_INIT_SECTION);
			}
			package_init_section_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_156);
			} else {
			  throw ex;
			}
		}
		returnAST = package_init_section_AST;
	}
	
	public void seq_of_statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST seq_of_statements_AST = null;
		
		try {      // for error handling
			{
			int _cnt601=0;
			_loop601:
			do {
				if ((_tokenSet_157.member(LA(1)))) {
					statement_tmpl();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt601>=1 ) { break _loop601; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt601++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STATEMENT_LIST);
			}
			seq_of_statements_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_158);
			} else {
			  throw ex;
			}
		}
		returnAST = seq_of_statements_AST;
	}
	
	public void variable_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variable_declaration_AST = null;
		
		try {      // for error handling
			variable_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_constant)) {
				AST tmp997_AST = null;
				tmp997_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp997_AST);
				match(LITERAL_constant);
			}
			else if ((_tokenSet_75.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			type_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp998_AST = null;
				tmp998_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp998_AST);
				match(LITERAL_not);
				AST tmp999_AST = null;
				tmp999_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp999_AST);
				match(LITERAL_null);
				break;
			}
			case SEMI:
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				{
				switch ( LA(1)) {
				case ASSIGNMENT_EQ:
				{
					AST tmp1000_AST = null;
					tmp1000_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1000_AST);
					match(ASSIGNMENT_EQ);
					break;
				}
				case LITERAL_default:
				{
					default1();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(VARIABLE_DECLARATION);
			}
			variable_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = variable_declaration_AST;
	}
	
	public void subtype_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subtype_declaration_AST = null;
		
		try {      // for error handling
			AST tmp1002_AST = null;
			tmp1002_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1002_AST);
			match(LITERAL_subtype);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is) && (_tokenSet_75.member(LA(3)))) {
				type_name();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_159.member(LA(2))) && (_tokenSet_160.member(LA(3)))) {
				datatype();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			AST tmp1003_AST = null;
			tmp1003_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1003_AST);
			match(LITERAL_is);
			{
			boolean synPredMatched587 = false;
			if (((_tokenSet_75.member(LA(1))) && (_tokenSet_161.member(LA(2))) && (_tokenSet_162.member(LA(3))))) {
				int _m587 = mark();
				synPredMatched587 = true;
				inputState.guessing++;
				try {
					{
					type_spec();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched587 = false;
				}
				rewind(_m587);
				inputState.guessing--;
			}
			if ( synPredMatched587 ) {
				type_spec();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==PERCENTAGE) && (LA(3)==LITERAL_type)) {
				{
				table_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(PERCENTAGE);
				match(LITERAL_type);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp1006_AST = null;
				tmp1006_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1006_AST);
				match(LITERAL_not);
				AST tmp1007_AST = null;
				tmp1007_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1007_AST);
				match(LITERAL_null);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(SUBTYPE_DECL);
			}
			subtype_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = subtype_declaration_AST;
	}
	
	public void cursor_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cursor_declaration_AST = null;
		
		try {      // for error handling
			AST tmp1009_AST = null;
			tmp1009_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1009_AST);
			match(LITERAL_cursor);
			cursor_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_is:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_is);
			select_command();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_DECLARATION);
			}
			cursor_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = cursor_declaration_AST;
	}
	
	public void cursor_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cursor_spec_AST = null;
		Token  c = null;
		AST c_AST = null;
		Token  o = null;
		AST o_AST = null;
		
		try {      // for error handling
			c = LT(1);
			c_AST = astFactory.create(c);
			astFactory.addASTChild(currentAST, c_AST);
			match(LITERAL_cursor);
			cursor_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				o = LT(1);
				o_AST = astFactory.create(o);
				match(OPEN_PAREN);
				{
				parameter_spec();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop789:
				do {
					if ((LA(1)==COMMA)) {
						AST tmp1014_AST = null;
						tmp1014_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1014_AST);
						match(COMMA);
						parameter_spec();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop789;
					}
					
				} while (true);
				}
				}
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_return:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1016_AST = null;
			tmp1016_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1016_AST);
			match(LITERAL_return);
			return_type();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			cursor_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = cursor_spec_AST;
	}
	
	public void procedure_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST procedure_spec_AST = null;
		
		try {      // for error handling
			procedure_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_SPEC);
			}
			procedure_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = procedure_spec_AST;
	}
	
	public void function_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_spec_AST = null;
		
		try {      // for error handling
			function_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(FUNCTION_SPEC);
			}
			function_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = function_spec_AST;
	}
	
	public void exception_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_declaration_AST = null;
		
		try {      // for error handling
			exception_name();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_exception);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_DECL);
			}
			exception_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_declaration_AST;
	}
	
	public void exception_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_exception_init);
			match(OPEN_PAREN);
			complex_name();
			astFactory.addASTChild(currentAST, returnAST);
			match(COMMA);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_PRAGMA);
			}
			exception_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_pragma_AST;
	}
	
	public void restrict_ref_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST restrict_ref_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_restrict_references);
			match(OPEN_PAREN);
			identifier3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt800=0;
			_loop800:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier3();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt800>=1 ) { break _loop800; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt800++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(RESTRICT_REF_PRAGMA);
			}
			restrict_ref_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = restrict_ref_pragma_AST;
	}
	
	public void interface_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST interface_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_interface);
			match(OPEN_PAREN);
			identifier3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt803=0;
			_loop803:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier3();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt803>=1 ) { break _loop803; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt803++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(INTERFACE_PRAGMA);
			}
			interface_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = interface_pragma_AST;
	}
	
	public void builtin_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST builtin_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_builtin);
			match(OPEN_PAREN);
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt806=0;
			_loop806:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt806>=1 ) { break _loop806; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt806++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(BUILTIN_PRAGMA);
			}
			builtin_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = builtin_pragma_AST;
	}
	
	public void fipsflag_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fipsflag_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_fipsflag);
			match(OPEN_PAREN);
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt809=0;
			_loop809:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt809>=1 ) { break _loop809; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt809++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(FIPSFLAG_PRAGMA);
			}
			fipsflag_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = fipsflag_pragma_AST;
	}
	
	public void timestamp_pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST timestamp_pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_timestamp);
			match(OPEN_PAREN);
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(TIMESTAMPG_PRAGMA);
			}
			timestamp_pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
			} else {
			  throw ex;
			}
		}
		returnAST = timestamp_pragma_AST;
	}
	
	public void condition_compilation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_compilation_AST = null;
		
		try {      // for error handling
			AST tmp1057_AST = null;
			tmp1057_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1057_AST);
			match(IF_COND_CMPL);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1058_AST = null;
			tmp1058_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1058_AST);
			match(THEN_COND_CMPL);
			cond_comp_seq2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case ELSE_COND_CMPL:
			{
				AST tmp1059_AST = null;
				tmp1059_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1059_AST);
				match(ELSE_COND_CMPL);
				cond_comp_seq2();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case END_COND_CMPL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1060_AST = null;
			tmp1060_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1060_AST);
			match(END_COND_CMPL);
			if ( inputState.guessing==0 ) {
				__markRule(CONDITIONAL_COMPILATION);
			}
			condition_compilation_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_72);
			} else {
			  throw ex;
			}
		}
		returnAST = condition_compilation_AST;
	}
	
	public void cond_comp_seq2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cond_comp_seq2_AST = null;
		
		try {      // for error handling
			{
			_loop569:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop569;
				}
				
			} while (true);
			}
			{
			_loop577:
			do {
				boolean synPredMatched573 = false;
				if (((LA(1)==LITERAL_function) && (_tokenSet_18.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
					int _m573 = mark();
					synPredMatched573 = true;
					inputState.guessing++;
					try {
						{
						function_declaration();
						{
						switch ( LA(1)) {
						case LITERAL_is:
						{
							match(LITERAL_is);
							break;
						}
						case LITERAL_as:
						{
							match(LITERAL_as);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched573 = false;
					}
					rewind(_m573);
					inputState.guessing--;
				}
				if ( synPredMatched573 ) {
					function_body();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					boolean synPredMatched576 = false;
					if (((LA(1)==LITERAL_procedure) && (_tokenSet_18.member(LA(2))) && (_tokenSet_153.member(LA(3))))) {
						int _m576 = mark();
						synPredMatched576 = true;
						inputState.guessing++;
						try {
							{
							procedure_declaration();
							{
							switch ( LA(1)) {
							case LITERAL_is:
							{
								match(LITERAL_is);
								break;
							}
							case LITERAL_as:
							{
								match(LITERAL_as);
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched576 = false;
						}
						rewind(_m576);
						inputState.guessing--;
					}
					if ( synPredMatched576 ) {
						procedure_body();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((_tokenSet_65.member(LA(1))) && (_tokenSet_154.member(LA(2))) && (_tokenSet_155.member(LA(3)))) {
						package_obj_spec();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop577;
					}
					}
				} while (true);
				}
				if ( inputState.guessing==0 ) {
					__markRule(COND_COMP_SEQ2);
				}
				cond_comp_seq2_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_151);
				} else {
				  throw ex;
				}
			}
			returnAST = cond_comp_seq2_AST;
		}
		
	public void function_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_declaration_AST = null;
		
		try {      // for error handling
			match(LITERAL_function);
			object_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_return:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			}
			match(LITERAL_return);
			return_type();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_character:
			{
				character_set();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_using:
			case LITERAL_as:
			case LITERAL_pipelined:
			case LITERAL_parallel_enable:
			case LITERAL_deterministic:
			case LITERAL_aggregate:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_pipelined:
			{
				AST tmp1065_AST = null;
				tmp1065_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1065_AST);
				match(LITERAL_pipelined);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_using:
			case LITERAL_as:
			case LITERAL_parallel_enable:
			case LITERAL_deterministic:
			case LITERAL_aggregate:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_parallel_enable:
			{
				AST tmp1066_AST = null;
				tmp1066_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1066_AST);
				match(LITERAL_parallel_enable);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_using:
			case LITERAL_as:
			case LITERAL_deterministic:
			case LITERAL_aggregate:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_using:
			{
				AST tmp1067_AST = null;
				tmp1067_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1067_AST);
				match(LITERAL_using);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_deterministic:
			case LITERAL_aggregate:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_deterministic:
			{
				AST tmp1068_AST = null;
				tmp1068_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1068_AST);
				match(LITERAL_deterministic);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_aggregate:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_as) && (LA(2)==LITERAL_language) && (LA(3)==LITERAL_java)) {
				AST tmp1069_AST = null;
				tmp1069_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1069_AST);
				match(LITERAL_as);
				AST tmp1070_AST = null;
				tmp1070_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1070_AST);
				match(LITERAL_language);
				AST tmp1071_AST = null;
				tmp1071_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1071_AST);
				match(LITERAL_java);
				AST tmp1072_AST = null;
				tmp1072_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1072_AST);
				match(LITERAL_name);
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_163.member(LA(1))) && (_tokenSet_164.member(LA(2))) && (_tokenSet_165.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			function_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_163);
			} else {
			  throw ex;
			}
		}
		returnAST = function_declaration_AST;
	}
	
	public void procedure_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST procedure_declaration_AST = null;
		
		try {      // for error handling
			match(LITERAL_procedure);
			object_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_as:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_as) && (LA(2)==LITERAL_language) && (LA(3)==LITERAL_java)) {
				AST tmp1076_AST = null;
				tmp1076_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1076_AST);
				match(LITERAL_as);
				AST tmp1077_AST = null;
				tmp1077_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1077_AST);
				match(LITERAL_language);
				AST tmp1078_AST = null;
				tmp1078_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1078_AST);
				match(LITERAL_java);
				AST tmp1079_AST = null;
				tmp1079_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1079_AST);
				match(LITERAL_name);
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==SEMI||LA(1)==LITERAL_is||LA(1)==LITERAL_as) && (_tokenSet_150.member(LA(2))) && (_tokenSet_165.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			}
			procedure_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_166);
			} else {
			  throw ex;
			}
		}
		returnAST = procedure_declaration_AST;
	}
	
	public void variable_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variable_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(VARIABLE_NAME);
			}
			variable_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_148);
			} else {
			  throw ex;
			}
		}
		returnAST = variable_name_AST;
	}
	
	public void default1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST default1_AST = null;
		
		try {      // for error handling
			AST tmp1080_AST = null;
			tmp1080_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1080_AST);
			match(LITERAL_default);
			if ( inputState.guessing==0 ) {
				__markRule(DEFAULT);
			}
			default1_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_43);
			} else {
			  throw ex;
			}
		}
		returnAST = default1_AST;
	}
	
	public void table_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_NAME);
			}
			table_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_167);
			} else {
			  throw ex;
			}
		}
		returnAST = table_name_AST;
	}
	
	public void cursor_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cursor_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_NAME);
			}
			cursor_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_168);
			} else {
			  throw ex;
			}
		}
		returnAST = cursor_name_AST;
	}
	
	public void argument_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST argument_list_AST = null;
		
		try {      // for error handling
			parameter_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop859:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					parameter_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop859;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(ARGUMENT_LIST);
			}
			argument_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = argument_list_AST;
	}
	
	public void select_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_command_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_54.member(LA(2))) && (_tokenSet_169.member(LA(3)))) {
				select_expression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_54.member(LA(3)))) {
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			select_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_170);
			} else {
			  throw ex;
			}
		}
		returnAST = select_command_AST;
	}
	
	public void statement_tmpl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statement_tmpl_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case START_LABEL:
			{
				{
				AST tmp1082_AST = null;
				tmp1082_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1082_AST);
				match(START_LABEL);
				label_name();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1083_AST = null;
				tmp1083_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1083_AST);
				match(END_LABEL);
				}
				statement_tmpl_AST = (AST)currentAST.root;
				break;
			}
			case IF_COND_CMPL:
			{
				{
				AST tmp1084_AST = null;
				tmp1084_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1084_AST);
				match(IF_COND_CMPL);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1085_AST = null;
				tmp1085_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1085_AST);
				match(THEN_COND_CMPL);
				seq_of_statements();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case ELSE_COND_CMPL:
				{
					AST tmp1086_AST = null;
					tmp1086_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1086_AST);
					match(ELSE_COND_CMPL);
					seq_of_statements();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case END_COND_CMPL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1087_AST = null;
				tmp1087_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1087_AST);
				match(END_COND_CMPL);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CONDITIONAL_COMPILATION);
				}
				statement_tmpl_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((_tokenSet_171.member(LA(1)))) {
					{
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					match(SEMI);
					}
					statement_tmpl_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_172);
			} else {
			  throw ex;
			}
		}
		returnAST = statement_tmpl_AST;
	}
	
	public void statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statement_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_begin:
			case LITERAL_declare:
			{
				begin_block();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_forall:
			{
				forall_loop();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(LOOP_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_if:
			{
				if_statement();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_goto:
			{
				goto_statement();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(GOTO_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_raise:
			{
				raise_statement();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(RAISE_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_exit:
			{
				exit_statement();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(EXIT_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_null:
			{
				null_statement();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_return:
			{
				return_statement();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(RETURN_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_case:
			{
				case_statement();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(CASE_STATEMENT);
				}
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_lock:
			{
				lock_table_statement();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_fetch:
			{
				fetch_statement();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_set:
			{
				set_transaction_command();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_alter:
			{
				alter_command();
				astFactory.addASTChild(currentAST, returnAST);
				statement_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched611 = false;
				if (((_tokenSet_173.member(LA(1))) && (_tokenSet_174.member(LA(2))) && (_tokenSet_175.member(LA(3))))) {
					int _m611 = mark();
					synPredMatched611 = true;
					inputState.guessing++;
					try {
						{
						switch ( LA(1)) {
						case LITERAL_loop:
						{
							match(LITERAL_loop);
							break;
						}
						case LITERAL_for:
						{
							match(LITERAL_for);
							break;
						}
						case LITERAL_while:
						{
							match(LITERAL_while);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched611 = false;
					}
					rewind(_m611);
					inputState.guessing--;
				}
				if ( synPredMatched611 ) {
					loop_statement();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						__markRule(LOOP_STATEMENT);
					}
					statement_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched627 = false;
					if (((_tokenSet_176.member(LA(1))) && (_tokenSet_177.member(LA(2))) && (_tokenSet_178.member(LA(3))))) {
						int _m627 = mark();
						synPredMatched627 = true;
						inputState.guessing++;
						try {
							{
							switch ( LA(1)) {
							case LITERAL_select:
							{
								match(LITERAL_select);
								break;
							}
							case LITERAL_update:
							{
								match(LITERAL_update);
								break;
							}
							case LITERAL_insert:
							{
								match(LITERAL_insert);
								break;
							}
							case LITERAL_delete:
							{
								match(LITERAL_delete);
								break;
							}
							case LITERAL_grant:
							{
								match(LITERAL_grant);
								break;
							}
							case LITERAL_commit:
							{
								match(LITERAL_commit);
								break;
							}
							case LITERAL_rollback:
							{
								match(LITERAL_rollback);
								break;
							}
							case LITERAL_merge:
							{
								match(LITERAL_merge);
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched627 = false;
						}
						rewind(_m627);
						inputState.guessing--;
					}
					if ( synPredMatched627 ) {
						sql_statement();
						astFactory.addASTChild(currentAST, returnAST);
						statement_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched629 = false;
						if (((LA(1)==LITERAL_execute) && (LA(2)==LITERAL_immediate) && (_tokenSet_43.member(LA(3))))) {
							int _m629 = mark();
							synPredMatched629 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_execute);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched629 = false;
							}
							rewind(_m629);
							inputState.guessing--;
						}
						if ( synPredMatched629 ) {
							immediate_command();
							astFactory.addASTChild(currentAST, returnAST);
							statement_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched633 = false;
							if (((LA(1)==LITERAL_open) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (_tokenSet_179.member(LA(3))))) {
								int _m633 = mark();
								synPredMatched633 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_open);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched633 = false;
								}
								rewind(_m633);
								inputState.guessing--;
							}
							if ( synPredMatched633 ) {
								open_statement();
								astFactory.addASTChild(currentAST, returnAST);
								statement_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched635 = false;
								if (((LA(1)==LITERAL_close) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==SEMI))) {
									int _m635 = mark();
									synPredMatched635 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_close);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched635 = false;
									}
									rewind(_m635);
									inputState.guessing--;
								}
								if ( synPredMatched635 ) {
									close_statement();
									astFactory.addASTChild(currentAST, returnAST);
									statement_AST = (AST)currentAST.root;
								}
								else if ((LA(1)==LITERAL_savepoint) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==SEMI)) {
									savepoint_statement();
									astFactory.addASTChild(currentAST, returnAST);
									statement_AST = (AST)currentAST.root;
								}
								else {
									boolean synPredMatched639 = false;
									if (((_tokenSet_59.member(LA(1))) && (_tokenSet_61.member(LA(2))) && (_tokenSet_62.member(LA(3))))) {
										int _m639 = mark();
										synPredMatched639 = true;
										inputState.guessing++;
										try {
											{
											plsql_lvalue();
											match(ASSIGNMENT_EQ);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched639 = false;
										}
										rewind(_m639);
										inputState.guessing--;
									}
									if ( synPredMatched639 ) {
										assignment_statement();
										astFactory.addASTChild(currentAST, returnAST);
										statement_AST = (AST)currentAST.root;
									}
									else if ((_tokenSet_18.member(LA(1))) && (LA(2)==SEMI||LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_180.member(LA(3)))) {
										{
										procedure_call();
										astFactory.addASTChild(currentAST, returnAST);
										{
										_loop642:
										do {
											if ((LA(1)==DOT)) {
												AST tmp1089_AST = null;
												tmp1089_AST = astFactory.create(LT(1));
												astFactory.addASTChild(currentAST, tmp1089_AST);
												match(DOT);
												procedure_call();
												astFactory.addASTChild(currentAST, returnAST);
											}
											else {
												break _loop642;
											}
											
										} while (true);
										}
										}
										statement_AST = (AST)currentAST.root;
									}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}}
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_147);
								} else {
								  throw ex;
								}
							}
							returnAST = statement_AST;
						}
						
	public void label_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST label_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(LABEL_NAME);
			}
			label_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
		returnAST = label_name_AST;
	}
	
	public void loop_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST loop_statement_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				label_name();
				break;
			}
			case LITERAL_for:
			case LITERAL_loop:
			case LITERAL_while:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_while:
			{
				{
				AST tmp1090_AST = null;
				tmp1090_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1090_AST);
				match(LITERAL_while);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case LITERAL_for:
			{
				{
				AST tmp1091_AST = null;
				tmp1091_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1091_AST);
				match(LITERAL_for);
				{
				boolean synPredMatched892 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (_tokenSet_43.member(LA(3))))) {
					int _m892 = mark();
					synPredMatched892 = true;
					inputState.guessing++;
					try {
						{
						numeric_loop_spec();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched892 = false;
					}
					rewind(_m892);
					inputState.guessing--;
				}
				if ( synPredMatched892 ) {
					numeric_loop_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (LA(3)==IDENTIFIER||LA(3)==DOUBLE_QUOTED_STRING||LA(3)==OPEN_PAREN)) {
					cursor_loop_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_loop:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1092_AST = null;
			tmp1092_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1092_AST);
			match(LITERAL_loop);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_end);
			match(LITERAL_loop);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				label_name();
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			loop_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = loop_statement_AST;
	}
	
	public void forall_loop() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST forall_loop_AST = null;
		
		try {      // for error handling
			forall_header();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_save)) {
				AST tmp1095_AST = null;
				tmp1095_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1095_AST);
				match(LITERAL_save);
				AST tmp1096_AST = null;
				tmp1096_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1096_AST);
				match(LITERAL_exceptions);
			}
			else if ((_tokenSet_171.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			statement();
			astFactory.addASTChild(currentAST, returnAST);
			forall_loop_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = forall_loop_AST;
	}
	
	public void if_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST if_statement_AST = null;
		
		try {      // for error handling
			match(LITERAL_if);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_then);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1063:
			do {
				if ((LA(1)==LITERAL_elsif)) {
					elsif_statements();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1063;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_else:
			{
				else_statements();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_end:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_end);
			match(LITERAL_if);
			if ( inputState.guessing==0 ) {
				__markRule(IF_STATEMENT);
			}
			if_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = if_statement_AST;
	}
	
	public void goto_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST goto_statement_AST = null;
		Token  g = null;
		AST g_AST = null;
		
		try {      // for error handling
			g = LT(1);
			g_AST = astFactory.create(g);
			astFactory.addASTChild(currentAST, g_AST);
			match(LITERAL_goto);
			label_name();
			astFactory.addASTChild(currentAST, returnAST);
			goto_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = goto_statement_AST;
	}
	
	public void raise_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST raise_statement_AST = null;
		
		try {      // for error handling
			match(LITERAL_raise);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				exception_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			raise_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = raise_statement_AST;
	}
	
	public void exit_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exit_statement_AST = null;
		Token  e = null;
		AST e_AST = null;
		Token  w = null;
		AST w_AST = null;
		
		try {      // for error handling
			e = LT(1);
			e_AST = astFactory.create(e);
			astFactory.addASTChild(currentAST, e_AST);
			match(LITERAL_exit);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				label_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_when:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_when:
			{
				w = LT(1);
				w_AST = astFactory.create(w);
				astFactory.addASTChild(currentAST, w_AST);
				match(LITERAL_when);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			exit_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = exit_statement_AST;
	}
	
	public void null_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST null_statement_AST = null;
		
		try {      // for error handling
			AST tmp1102_AST = null;
			tmp1102_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1102_AST);
			match(LITERAL_null);
			if ( inputState.guessing==0 ) {
				__markRule(NULL_STATEMENT);
			}
			null_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = null_statement_AST;
	}
	
	public void return_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST return_statement_AST = null;
		
		try {      // for error handling
			match(LITERAL_return);
			{
			if ((_tokenSet_55.member(LA(1)))) {
				condition();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==SEMI)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			return_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = return_statement_AST;
	}
	
	public void case_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST case_statement_AST = null;
		Token  t = null;
		AST t_AST = null;
		Token  e = null;
		AST e_AST = null;
		
		try {      // for error handling
			match(LITERAL_case);
			{
			boolean synPredMatched656 = false;
			if (((_tokenSet_43.member(LA(1))))) {
				int _m656 = mark();
				synPredMatched656 = true;
				inputState.guessing++;
				try {
					{
					plsql_expression();
					{
					int _cnt655=0;
					_loop655:
					do {
						if ((LA(1)==LITERAL_when)) {
							match(LITERAL_when);
							plsql_expression();
							match(LITERAL_then);
							seq_of_statements();
						}
						else {
							if ( _cnt655>=1 ) { break _loop655; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt655++;
					} while (true);
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched656 = false;
				}
				rewind(_m656);
				inputState.guessing--;
			}
			if ( synPredMatched656 ) {
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				int _cnt658=0;
				_loop658:
				do {
					if ((LA(1)==LITERAL_when)) {
						AST tmp1105_AST = null;
						tmp1105_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1105_AST);
						match(LITERAL_when);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
						AST tmp1106_AST = null;
						tmp1106_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1106_AST);
						match(LITERAL_then);
						seq_of_statements();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt658>=1 ) { break _loop658; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt658++;
				} while (true);
				}
			}
			else if ((LA(1)==LITERAL_when)) {
				{
				int _cnt660=0;
				_loop660:
				do {
					if ((LA(1)==LITERAL_when)) {
						AST tmp1107_AST = null;
						tmp1107_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1107_AST);
						match(LITERAL_when);
						condition();
						astFactory.addASTChild(currentAST, returnAST);
						t = LT(1);
						t_AST = astFactory.create(t);
						astFactory.addASTChild(currentAST, t_AST);
						match(LITERAL_then);
						seq_of_statements();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt660>=1 ) { break _loop660; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt660++;
				} while (true);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_else:
			{
				e = LT(1);
				e_AST = astFactory.create(e);
				astFactory.addASTChild(currentAST, e_AST);
				match(LITERAL_else);
				seq_of_statements();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_end:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_end);
			match(LITERAL_case);
			if ( inputState.guessing==0 ) {
				__markRule(CASE_STATEMENT);
			}
			case_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = case_statement_AST;
	}
	
	public void immediate_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST immediate_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_execute);
			match(LITERAL_immediate);
			{
			boolean synPredMatched1106 = false;
			if (((_tokenSet_59.member(LA(1))) && (_tokenSet_182.member(LA(2))) && (_tokenSet_183.member(LA(3))))) {
				int _m1106 = mark();
				synPredMatched1106 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1106 = false;
				}
				rewind(_m1106);
				inputState.guessing--;
			}
			if ( synPredMatched1106 ) {
				plsql_lvalue();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_184.member(LA(2))) && (_tokenSet_185.member(LA(3)))) {
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_bulk:
			case LITERAL_into:
			{
				{
				switch ( LA(1)) {
				case LITERAL_bulk:
				{
					AST tmp1112_AST = null;
					tmp1112_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1112_AST);
					match(LITERAL_bulk);
					AST tmp1113_AST = null;
					tmp1113_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1113_AST);
					match(LITERAL_collect);
					break;
				}
				case LITERAL_into:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1114_AST = null;
				tmp1114_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1114_AST);
				match(LITERAL_into);
				plsql_lvalue_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_using:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_using:
			{
				AST tmp1115_AST = null;
				tmp1115_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1115_AST);
				match(LITERAL_using);
				plsql_exp_list_using();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(IMMEDIATE_COMMAND);
			}
			immediate_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = immediate_command_AST;
	}
	
	public void lock_table_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lock_table_statement_AST = null;
		Token  l = null;
		AST l_AST = null;
		Token  t = null;
		AST t_AST = null;
		Token  i = null;
		AST i_AST = null;
		Token  m = null;
		AST m_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		try {      // for error handling
			l = LT(1);
			l_AST = astFactory.create(l);
			astFactory.addASTChild(currentAST, l_AST);
			match(LITERAL_lock);
			t = LT(1);
			t_AST = astFactory.create(t);
			astFactory.addASTChild(currentAST, t_AST);
			match(LITERAL_table);
			table_reference_list();
			astFactory.addASTChild(currentAST, returnAST);
			i = LT(1);
			i_AST = astFactory.create(i);
			astFactory.addASTChild(currentAST, i_AST);
			match(LITERAL_in);
			lock_mode();
			astFactory.addASTChild(currentAST, returnAST);
			m = LT(1);
			m_AST = astFactory.create(m);
			astFactory.addASTChild(currentAST, m_AST);
			match(LITERAL_mode);
			{
			switch ( LA(1)) {
			case LITERAL_nowait:
			{
				n = LT(1);
				n_AST = astFactory.create(n);
				astFactory.addASTChild(currentAST, n_AST);
				match(LITERAL_nowait);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			lock_table_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = lock_table_statement_AST;
	}
	
	public void open_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST open_statement_AST = null;
		Token  o = null;
		AST o_AST = null;
		Token  f = null;
		AST f_AST = null;
		
		try {      // for error handling
			o = LT(1);
			o_AST = astFactory.create(o);
			astFactory.addASTChild(currentAST, o_AST);
			match(LITERAL_open);
			cursor_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				parentesized_plsql_exp_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_using:
			case LITERAL_for:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_for:
			{
				f = LT(1);
				f_AST = astFactory.create(f);
				astFactory.addASTChild(currentAST, f_AST);
				match(LITERAL_for);
				{
				if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_54.member(LA(2))) && (_tokenSet_169.member(LA(3)))) {
					select_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_186.member(LA(2))) && (_tokenSet_187.member(LA(3)))) {
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case SEMI:
			case LITERAL_using:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_using:
			{
				AST tmp1116_AST = null;
				tmp1116_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1116_AST);
				match(LITERAL_using);
				AST tmp1117_AST = null;
				tmp1117_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1117_AST);
				match(LITERAL_in);
				plsql_lvalue_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			open_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = open_statement_AST;
	}
	
	public void close_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST close_statement_AST = null;
		
		try {      // for error handling
			AST tmp1118_AST = null;
			tmp1118_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1118_AST);
			match(LITERAL_close);
			cursor_name();
			astFactory.addASTChild(currentAST, returnAST);
			close_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = close_statement_AST;
	}
	
	public void fetch_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fetch_statement_AST = null;
		
		try {      // for error handling
			AST tmp1119_AST = null;
			tmp1119_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1119_AST);
			match(LITERAL_fetch);
			cursor_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_bulk:
			{
				AST tmp1120_AST = null;
				tmp1120_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1120_AST);
				match(LITERAL_bulk);
				AST tmp1121_AST = null;
				tmp1121_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1121_AST);
				match(LITERAL_collect);
				break;
			}
			case LITERAL_into:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1122_AST = null;
			tmp1122_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1122_AST);
			match(LITERAL_into);
			variable_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1278:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					variable_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1278;
				}
				
			} while (true);
			}
			fetch_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = fetch_statement_AST;
	}
	
	public void set_transaction_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST set_transaction_command_AST = null;
		Token  r = null;
		AST r_AST = null;
		
		try {      // for error handling
			AST tmp1124_AST = null;
			tmp1124_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1124_AST);
			match(LITERAL_set);
			AST tmp1125_AST = null;
			tmp1125_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1125_AST);
			match(LITERAL_transaction);
			r = LT(1);
			r_AST = astFactory.create(r);
			astFactory.addASTChild(currentAST, r_AST);
			match(LITERAL_read);
			AST tmp1126_AST = null;
			tmp1126_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1126_AST);
			match(LITERAL_only);
			set_transaction_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = set_transaction_command_AST;
	}
	
	public void savepoint_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST savepoint_statement_AST = null;
		
		try {      // for error handling
			AST tmp1127_AST = null;
			tmp1127_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1127_AST);
			match(LITERAL_savepoint);
			savepoint_name();
			astFactory.addASTChild(currentAST, returnAST);
			savepoint_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = savepoint_statement_AST;
	}
	
	public void sql_percentage() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sql_percentage_AST = null;
		
		try {      // for error handling
			AST tmp1128_AST = null;
			tmp1128_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1128_AST);
			match(LITERAL_sql);
			AST tmp1129_AST = null;
			tmp1129_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1129_AST);
			match(PERCENTAGE);
			{
			switch ( LA(1)) {
			case LITERAL_found:
			{
				AST tmp1130_AST = null;
				tmp1130_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1130_AST);
				match(LITERAL_found);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_notfound:
			{
				AST tmp1131_AST = null;
				tmp1131_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1131_AST);
				match(LITERAL_notfound);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_rowcount:
			{
				AST tmp1132_AST = null;
				tmp1132_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1132_AST);
				match(LITERAL_rowcount);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_NUM);
				}
				break;
			}
			case LITERAL_isopen:
			{
				AST tmp1133_AST = null;
				tmp1133_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1133_AST);
				match(LITERAL_isopen);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_bulk_rowcount:
			{
				{
				AST tmp1134_AST = null;
				tmp1134_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1134_AST);
				match(LITERAL_bulk_rowcount);
				AST tmp1135_AST = null;
				tmp1135_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1135_AST);
				match(OPEN_PAREN);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1136_AST = null;
				tmp1136_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1136_AST);
				match(CLOSE_PAREN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_NUM);
				}
				break;
			}
			case LITERAL_bulk_exceptions:
			{
				AST tmp1137_AST = null;
				tmp1137_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1137_AST);
				match(LITERAL_bulk_exceptions);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					{
					AST tmp1138_AST = null;
					tmp1138_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1138_AST);
					match(OPEN_PAREN);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp1139_AST = null;
					tmp1139_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1139_AST);
					match(CLOSE_PAREN);
					AST tmp1140_AST = null;
					tmp1140_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1140_AST);
					match(DOT);
					{
					switch ( LA(1)) {
					case LITERAL_error_index:
					{
						AST tmp1141_AST = null;
						tmp1141_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1141_AST);
						match(LITERAL_error_index);
						break;
					}
					case LITERAL_error_code:
					{
						AST tmp1142_AST = null;
						tmp1142_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1142_AST);
						match(LITERAL_error_code);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
					break;
				}
				case DOT:
				{
					{
					{
					AST tmp1143_AST = null;
					tmp1143_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1143_AST);
					match(DOT);
					}
					AST tmp1144_AST = null;
					tmp1144_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1144_AST);
					match(LITERAL_count);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_NUM);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			sql_percentage_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = sql_percentage_AST;
	}
	
	public void declare_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declare_spec_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_subtype:
			{
				subtype_declaration();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_cursor:
			{
				cursor_declaration();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_function:
			{
				{
				boolean synPredMatched667 = false;
				if (((LA(1)==LITERAL_function) && (_tokenSet_18.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
					int _m667 = mark();
					synPredMatched667 = true;
					inputState.guessing++;
					try {
						{
						function_declaration();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched667 = false;
					}
					rewind(_m667);
					inputState.guessing--;
				}
				if ( synPredMatched667 ) {
					function_body();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_function) && (_tokenSet_18.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return)) {
					function_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_procedure:
			{
				{
				boolean synPredMatched670 = false;
				if (((LA(1)==LITERAL_procedure) && (_tokenSet_18.member(LA(2))) && (_tokenSet_153.member(LA(3))))) {
					int _m670 = mark();
					synPredMatched670 = true;
					inputState.guessing++;
					try {
						{
						procedure_declaration();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched670 = false;
					}
					rewind(_m670);
					inputState.guessing--;
				}
				if ( synPredMatched670 ) {
					procedure_body();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_procedure) && (_tokenSet_18.member(LA(2))) && (_tokenSet_188.member(LA(3)))) {
					procedure_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_148.member(LA(2))) && (_tokenSet_149.member(LA(3)))) {
					variable_declaration();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
					exception_declaration();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_exception_init)) {
					exception_pragma();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_type) && (_tokenSet_5.member(LA(2))) && (LA(3)==LITERAL_is||LA(3)==LITERAL_as)) {
					{
					type_definition();
					astFactory.addASTChild(currentAST, returnAST);
					match(SEMI);
					}
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_autonomous_transaction)) {
					pragma();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			declare_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_189);
			} else {
			  throw ex;
			}
		}
		returnAST = declare_spec_AST;
	}
	
	public void pragma() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST pragma_AST = null;
		
		try {      // for error handling
			match(LITERAL_pragma);
			AST tmp1147_AST = null;
			tmp1147_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1147_AST);
			match(LITERAL_autonomous_transaction);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PRAGMA);
			}
			pragma_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_189);
			} else {
			  throw ex;
			}
		}
		returnAST = pragma_AST;
	}
	
	public void rvalue() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rvalue_AST = null;
		Token  d = null;
		AST d_AST = null;
		
		try {      // for error handling
			boolean synPredMatched678 = false;
			if (((_tokenSet_190.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
				int _m678 = mark();
				synPredMatched678 = true;
				inputState.guessing++;
				try {
					{
					{
					if ((LA(1)==LITERAL_prior)) {
						match(LITERAL_prior);
					}
					else if ((_tokenSet_5.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					_loop677:
					do {
						if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3)))) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop677;
						}
						
					} while (true);
					}
					name_fragment();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched678 = false;
				}
				rewind(_m678);
				inputState.guessing--;
			}
			if ( synPredMatched678 ) {
				{
				{
				if ((LA(1)==LITERAL_prior)) {
					match(LITERAL_prior);
				}
				else if ((_tokenSet_5.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop682:
				do {
					if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment();
						astFactory.addASTChild(currentAST, returnAST);
						match(DOT);
					}
					else {
						break _loop682;
					}
					
				} while (true);
				}
				name_fragment();
				astFactory.addASTChild(currentAST, returnAST);
				}
				if ( inputState.guessing==0 ) {
					__markRule(VAR_REF);
				}
				rvalue_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched685 = false;
				if (((LA(1)==COLON))) {
					int _m685 = mark();
					synPredMatched685 = true;
					inputState.guessing++;
					try {
						{
						match(COLON);
						{
						switch ( LA(1)) {
						case LITERAL_new:
						{
							match(LITERAL_new);
							break;
						}
						case LITERAL_old:
						{
							match(LITERAL_old);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						match(DOT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched685 = false;
					}
					rewind(_m685);
					inputState.guessing--;
				}
				if ( synPredMatched685 ) {
					{
					AST tmp1151_AST = null;
					tmp1151_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1151_AST);
					match(COLON);
					{
					switch ( LA(1)) {
					case LITERAL_new:
					{
						AST tmp1152_AST = null;
						tmp1152_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1152_AST);
						match(LITERAL_new);
						break;
					}
					case LITERAL_old:
					{
						AST tmp1153_AST = null;
						tmp1153_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1153_AST);
						match(LITERAL_old);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(DOT);
					name_fragment();
					astFactory.addASTChild(currentAST, returnAST);
					}
					if ( inputState.guessing==0 ) {
						__markRule(TRIGGER_COLUMN_REF);
					}
					rvalue_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_18.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_68.member(LA(3)))) {
					{
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop693:
					do {
						if ((LA(1)==DOT)) {
							d = LT(1);
							d_AST = astFactory.create(d);
							match(DOT);
							{
							boolean synPredMatched692 = false;
							if (((_tokenSet_18.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_68.member(LA(3))))) {
								int _m692 = mark();
								synPredMatched692 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched692 = false;
								}
								rewind(_m692);
								inputState.guessing--;
							}
							if ( synPredMatched692 ) {
								function_call();
								astFactory.addASTChild(currentAST, returnAST);
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
								c_record_item_ref();
								astFactory.addASTChild(currentAST, returnAST);
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop693;
						}
						
					} while (true);
					}
					}
					if ( inputState.guessing==0 ) {
						
						if(d_AST != null){
						__markRule(COLLECTION_METHOD_CALL);
						}
						
					}
					rvalue_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_48);
				} else {
				  throw ex;
				}
			}
			returnAST = rvalue_AST;
		}
		
	public void function_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_call_AST = null;
		
		try {      // for error handling
			callable_name_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(OPEN_PAREN);
			{
			if ((_tokenSet_55.member(LA(1)))) {
				call_argument_list();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==CLOSE_PAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(FUNCTION_CALL);
			}
			function_call_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_191);
			} else {
			  throw ex;
			}
		}
		returnAST = function_call_AST;
	}
	
	public void c_record_item_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST c_record_item_ref_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(C_RECORD_ITEM_REF);
			}
			c_record_item_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_191);
			} else {
			  throw ex;
			}
		}
		returnAST = c_record_item_ref_AST;
	}
	
	public void host_variable() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST host_variable_AST = null;
		
		try {      // for error handling
			AST tmp1157_AST = null;
			tmp1157_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1157_AST);
			match(COLON);
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			host_variable_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = host_variable_AST;
	}
	
	public void collection_method() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST collection_method_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(COLLECTION_METHOD_NAME);
			}
			collection_method_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = collection_method_AST;
	}
	
	public void field_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST field_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(FIELD_NAME);
			}
			field_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = field_name_AST;
	}
	
	public void percentage_type() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST percentage_type_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				table_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				column_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
				match(PERCENTAGE);
				match(LITERAL_type);
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_TYPE_REF);
				}
				percentage_type_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==PERCENTAGE)) {
				table_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(PERCENTAGE);
				match(LITERAL_rowtype);
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_TYPE_REF);
				}
				percentage_type_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
			} else {
			  throw ex;
			}
		}
		returnAST = percentage_type_AST;
	}
	
	public void type_name_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_name_ref_AST = null;
		
		try {      // for error handling
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop765:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					name_fragment();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop765;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(TYPE_NAME_REF);
			}
			type_name_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
			} else {
			  throw ex;
			}
		}
		returnAST = type_name_ref_AST;
	}
	
	public void column_name_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_name_ref_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_REF);
			}
			column_name_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_192);
			} else {
			  throw ex;
			}
		}
		returnAST = column_name_ref_AST;
	}
	
	public void name_fragment_ex() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST name_fragment_ex_AST = null;
		
		try {      // for error handling
			identifier3();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
			}
			name_fragment_ex_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = name_fragment_ex_AST;
	}
	
	public void parameter_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parameter_spec_AST = null;
		
		try {      // for error handling
			parameter_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_in)) {
				AST tmp1164_AST = null;
				tmp1164_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1164_AST);
				match(LITERAL_in);
			}
			else if ((_tokenSet_193.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_out)) {
				AST tmp1165_AST = null;
				tmp1165_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1165_AST);
				match(LITERAL_out);
			}
			else if ((_tokenSet_194.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_nocopy)) {
				AST tmp1166_AST = null;
				tmp1166_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1166_AST);
				match(LITERAL_nocopy);
			}
			else if ((_tokenSet_75.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_ref) && (_tokenSet_75.member(LA(2))) && (_tokenSet_195.member(LA(3)))) {
				AST tmp1167_AST = null;
				tmp1167_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1167_AST);
				match(LITERAL_ref);
			}
			else if ((_tokenSet_75.member(LA(1))) && (_tokenSet_195.member(LA(2))) && (_tokenSet_196.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			type_spec();
			astFactory.addASTChild(currentAST, returnAST);
			}
			{
			switch ( LA(1)) {
			case LITERAL_character:
			{
				character_set();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case ASSIGNMENT_EQ:
			case LITERAL_default:
			{
				{
				switch ( LA(1)) {
				case LITERAL_default:
				{
					default1();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case ASSIGNMENT_EQ:
				{
					AST tmp1168_AST = null;
					tmp1168_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1168_AST);
					match(ASSIGNMENT_EQ);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(PARAMETER_SPEC);
			}
			parameter_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = parameter_spec_AST;
	}
	
	public void parameter_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parameter_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PARAMETER_NAME);
			}
			parameter_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_197);
			} else {
			  throw ex;
			}
		}
		returnAST = parameter_name_AST;
	}
	
	public void character_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST character_set_AST = null;
		
		try {      // for error handling
			AST tmp1169_AST = null;
			tmp1169_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1169_AST);
			match(LITERAL_character);
			AST tmp1170_AST = null;
			tmp1170_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1170_AST);
			match(LITERAL_set);
			{
			if ((LA(1)==LITERAL_any_cs)) {
				AST tmp1171_AST = null;
				tmp1171_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1171_AST);
				match(LITERAL_any_cs);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				{
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1172_AST = null;
				tmp1172_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1172_AST);
				match(PERCENTAGE);
				AST tmp1173_AST = null;
				tmp1173_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1173_AST);
				match(LITERAL_charset);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(CHARACTER_SET);
			}
			character_set_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_198);
			} else {
			  throw ex;
			}
		}
		returnAST = character_set_AST;
	}
	
	public void return_type() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST return_type_AST = null;
		
		try {      // for error handling
			type_spec();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(RETURN_TYPE);
			}
			return_type_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_199);
			} else {
			  throw ex;
			}
		}
		returnAST = return_type_AST;
	}
	
	public void exception_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_name_AST = null;
		
		try {      // for error handling
			exception_package_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case DOT:
			{
				match(DOT);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			case LITERAL_or:
			case LITERAL_then:
			case LITERAL_exception:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			exception_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_200);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_name_AST;
	}
	
	public void exception_package_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_package_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			exception_package_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_201);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_package_name_AST;
	}
	
	public void complex_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST complex_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop904:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop904;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COMPLEX_NAME);
			}
			complex_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
			} else {
			  throw ex;
			}
		}
		returnAST = complex_name_AST;
	}
	
	public void oracle_err_number() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST oracle_err_number_AST = null;
		Token  p = null;
		AST p_AST = null;
		Token  m = null;
		AST m_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case PLUS:
			{
				p = LT(1);
				p_AST = astFactory.create(p);
				astFactory.addASTChild(currentAST, p_AST);
				match(PLUS);
				break;
			}
			case MINUS:
			{
				m = LT(1);
				m_AST = astFactory.create(m);
				astFactory.addASTChild(currentAST, m_AST);
				match(MINUS);
				break;
			}
			case NUMBER:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			n = LT(1);
			n_AST = astFactory.create(n);
			astFactory.addASTChild(currentAST, n_AST);
			match(NUMBER);
			oracle_err_number_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = oracle_err_number_AST;
	}
	
	public void record_item_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST record_item_name_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(RECORD_ITEM_NAME);
			}
			record_item_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_75);
			} else {
			  throw ex;
			}
		}
		returnAST = record_item_name_AST;
	}
	
	public void func_proc_statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_proc_statements_AST = null;
		
		try {      // for error handling
			{
			if ((_tokenSet_65.member(LA(1)))) {
				declare_list();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==LITERAL_begin)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_block();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_BLOCK);
			}
			func_proc_statements_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = func_proc_statements_AST;
	}
	
	public void declare_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declare_list_AST = null;
		
		try {      // for error handling
			{
			int _cnt839=0;
			_loop839:
			do {
				if ((_tokenSet_65.member(LA(1)))) {
					declare_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt839>=1 ) { break _loop839; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt839++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(DECLARE_LIST);
			}
			declare_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_203);
			} else {
			  throw ex;
			}
		}
		returnAST = declare_list_AST;
	}
	
	public void plsql_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_block_AST = null;
		
		try {      // for error handling
			match(LITERAL_begin);
			{
			if ((_tokenSet_157.member(LA(1)))) {
				seq_of_statements();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==LITERAL_end||LA(1)==LITERAL_exception)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_exception:
			{
				exception_section();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_end:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_end);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_15.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_15.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = plsql_block_AST;
	}
	
	public void exception_section() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_section_AST = null;
		
		try {      // for error handling
			match(LITERAL_exception);
			{
			int _cnt836=0;
			_loop836:
			do {
				if ((LA(1)==LITERAL_when)) {
					exception_handler();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt836>=1 ) { break _loop836; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt836++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_SECTION);
			}
			exception_section_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_156);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_section_AST;
	}
	
	public void exception_handler() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exception_handler_AST = null;
		Token  w = null;
		AST w_AST = null;
		Token  o = null;
		AST o_AST = null;
		Token  t = null;
		AST t_AST = null;
		
		try {      // for error handling
			w = LT(1);
			w_AST = astFactory.create(w);
			astFactory.addASTChild(currentAST, w_AST);
			match(LITERAL_when);
			exception_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop842:
			do {
				if ((LA(1)==LITERAL_or)) {
					o = LT(1);
					o_AST = astFactory.create(o);
					astFactory.addASTChild(currentAST, o_AST);
					match(LITERAL_or);
					exception_name();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop842;
				}
				
			} while (true);
			}
			t = LT(1);
			t_AST = astFactory.create(t);
			astFactory.addASTChild(currentAST, t_AST);
			match(LITERAL_then);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			exception_handler_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_204);
			} else {
			  throw ex;
			}
		}
		returnAST = exception_handler_AST;
	}
	
	public void call_argument_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST call_argument_list_AST = null;
		
		try {      // for error handling
			call_argument();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1144:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					call_argument();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1144;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(CALL_ARGUMENT_LIST);
			}
			call_argument_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = call_argument_list_AST;
	}
	
	public void forall_header() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST forall_header_AST = null;
		
		try {      // for error handling
			AST tmp1181_AST = null;
			tmp1181_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1181_AST);
			match(LITERAL_forall);
			loop_index();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1182_AST = null;
			tmp1182_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1182_AST);
			match(LITERAL_in);
			{
			if ((_tokenSet_205.member(LA(1))) && (_tokenSet_206.member(LA(2)))) {
				{
				forall_boundary();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1183_AST = null;
				tmp1183_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1183_AST);
				match(DOUBLEDOT);
				forall_boundary();
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else if ((LA(1)==LITERAL_values)) {
				{
				AST tmp1184_AST = null;
				tmp1184_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1184_AST);
				match(LITERAL_values);
				AST tmp1185_AST = null;
				tmp1185_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1185_AST);
				match(LITERAL_of);
				plsql_lvalue();
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else if ((LA(1)==LITERAL_indices) && (LA(2)==LITERAL_of)) {
				{
				AST tmp1186_AST = null;
				tmp1186_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1186_AST);
				match(LITERAL_indices);
				AST tmp1187_AST = null;
				tmp1187_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1187_AST);
				match(LITERAL_of);
				plsql_lvalue();
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FORALL_LOOP_SPEC);
			}
			forall_header_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_207);
			} else {
			  throw ex;
			}
		}
		returnAST = forall_header_AST;
	}
	
	public void loop_index() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST loop_index_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(LOOP_INDEX);
			}
			loop_index_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_208);
			} else {
			  throw ex;
			}
		}
		returnAST = loop_index_AST;
	}
	
	public void forall_boundary() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST forall_boundary_AST = null;
		
		try {      // for error handling
			if ((LA(1)==NUMBER)) {
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				forall_boundary_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_59.member(LA(1)))) {
				plsql_lvalue();
				astFactory.addASTChild(currentAST, returnAST);
				forall_boundary_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_209);
			} else {
			  throw ex;
			}
		}
		returnAST = forall_boundary_AST;
	}
	
	public void numeric_loop_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST numeric_loop_spec_AST = null;
		
		try {      // for error handling
			loop_index();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_in);
			{
			if ((LA(1)==LITERAL_reverse) && (_tokenSet_43.member(LA(2))) && (_tokenSet_210.member(LA(3)))) {
				match(LITERAL_reverse);
			}
			else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_210.member(LA(2))) && (_tokenSet_211.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(DOUBLEDOT);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(NUMERIC_LOOP_SPEC);
			}
			numeric_loop_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_212);
			} else {
			  throw ex;
			}
		}
		returnAST = numeric_loop_spec_AST;
	}
	
	public void cursor_loop_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cursor_loop_spec_AST = null;
		
		try {      // for error handling
			loop_index();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_in);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				{
				cursor_name();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
					call_argument_list();
					astFactory.addASTChild(currentAST, returnAST);
					match(CLOSE_PAREN);
					break;
				}
				case LITERAL_loop:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_REF_LOOP_SPEC);
				}
				break;
			}
			case OPEN_PAREN:
			{
				{
				match(OPEN_PAREN);
				select_expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_IMPL_LOOP_SPEC);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			cursor_loop_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_212);
			} else {
			  throw ex;
			}
		}
		returnAST = cursor_loop_spec_AST;
	}
	
	public void boolean_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST boolean_literal_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_true:
			{
				AST tmp1196_AST = null;
				tmp1196_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1196_AST);
				match(LITERAL_true);
				break;
			}
			case LITERAL_false:
			{
				AST tmp1197_AST = null;
				tmp1197_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1197_AST);
				match(LITERAL_false);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(BOOLEAN_LITERAL);
			}
			boolean_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = boolean_literal_AST;
	}
	
	public void integer_expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST integer_expr_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_43.member(LA(1))) && (_tokenSet_63.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				integer_expr_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_75.member(LA(1))) && (_tokenSet_213.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				type_spec();
				astFactory.addASTChild(currentAST, returnAST);
				integer_expr_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_64);
			} else {
			  throw ex;
			}
		}
		returnAST = integer_expr_AST;
	}
	
	public void num_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST num_expression_AST = null;
		Token  p = null;
		AST p_AST = null;
		Token  m = null;
		AST m_AST = null;
		
		try {      // for error handling
			num_term();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop914:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS) && (_tokenSet_43.member(LA(2))) && (_tokenSet_63.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						p = LT(1);
						p_AST = astFactory.create(p);
						astFactory.addASTChild(currentAST, p_AST);
						match(PLUS);
						if ( inputState.guessing==0 ) {
							p_AST.setType(PLUS_OP);
						}
						break;
					}
					case MINUS:
					{
						m = LT(1);
						m_AST = astFactory.create(m);
						astFactory.addASTChild(currentAST, m_AST);
						match(MINUS);
						if ( inputState.guessing==0 ) {
							m_AST.setType(MINUS_OP);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					num_term();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop914;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(p_AST != null || m_AST != null){
				__markRule(ARITHMETIC_EXPR);
				}
				
			}
			num_expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_64);
			} else {
			  throw ex;
			}
		}
		returnAST = num_expression_AST;
	}
	
	public void num_term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST num_term_AST = null;
		Token  as = null;
		AST as_AST = null;
		Token  dv = null;
		AST dv_AST = null;
		
		try {      // for error handling
			num_factor();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop918:
			do {
				if ((LA(1)==ASTERISK||LA(1)==DIVIDE) && (_tokenSet_43.member(LA(2))) && (_tokenSet_63.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case ASTERISK:
					{
						as = LT(1);
						as_AST = astFactory.create(as);
						astFactory.addASTChild(currentAST, as_AST);
						match(ASTERISK);
						if ( inputState.guessing==0 ) {
							as_AST.setType(MULTIPLICATION_OP);
						}
						break;
					}
					case DIVIDE:
					{
						dv = LT(1);
						dv_AST = astFactory.create(dv);
						astFactory.addASTChild(currentAST, dv_AST);
						match(DIVIDE);
						if ( inputState.guessing==0 ) {
							dv_AST.setType(DIVIDE_OP);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					num_factor();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop918;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(as_AST != null || dv_AST != null){
				__markRule(ARITHMETIC_EXPR);
				}
				
			}
			num_term_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_64);
			} else {
			  throw ex;
			}
		}
		returnAST = num_term_AST;
	}
	
	public void num_factor() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST num_factor_AST = null;
		Token  d = null;
		AST d_AST = null;
		
		try {      // for error handling
			may_be_negate_base_expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==629)) {
				AST tmp1198_AST = null;
				tmp1198_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1198_AST);
				match(629);
				integer_expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_64.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_day) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_to) && (LA(3)==NUMBER||LA(3)==LITERAL_second)) {
				d = LT(1);
				d_AST = astFactory.create(d);
				astFactory.addASTChild(currentAST, d_AST);
				match(LITERAL_day);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
					AST tmp1200_AST = null;
					tmp1200_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1200_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
					break;
				}
				case LITERAL_to:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1202_AST = null;
				tmp1202_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1202_AST);
				match(LITERAL_to);
				AST tmp1203_AST = null;
				tmp1203_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1203_AST);
				match(LITERAL_second);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					AST tmp1205_AST = null;
					tmp1205_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1205_AST);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_64.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else if ((_tokenSet_64.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				
				if(d_AST != null){
				__markRule(INTERVAL_DAY_TO_SEC_EXPR);
				}
				
			}
			num_factor_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_64);
			} else {
			  throw ex;
			}
		}
		returnAST = num_factor_AST;
	}
	
	public void may_be_negate_base_expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST may_be_negate_base_expr_AST = null;
		AST s1_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				sign();
				s1_AST = (AST)returnAST;
			}
			else if ((_tokenSet_40.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			may_be_at_time_zone();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
				if(s1_AST != null){
				__markRule(ARITHMETIC_EXPR);
				}
				
			}
			may_be_negate_base_expr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
			} else {
			  throw ex;
			}
		}
		returnAST = may_be_negate_base_expr_AST;
	}
	
	public void sign() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sign_AST = null;
		Token  p = null;
		AST p_AST = null;
		Token  m = null;
		AST m_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case PLUS:
			{
				p = LT(1);
				p_AST = astFactory.create(p);
				astFactory.addASTChild(currentAST, p_AST);
				match(PLUS);
				if ( inputState.guessing==0 ) {
					p_AST.setType(PLUS_OP);
				}
				sign_AST = (AST)currentAST.root;
				break;
			}
			case MINUS:
			{
				m = LT(1);
				m_AST = astFactory.create(m);
				astFactory.addASTChild(currentAST, m_AST);
				match(MINUS);
				if ( inputState.guessing==0 ) {
					m_AST.setType(MINUS_OP);
				}
				sign_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			} else {
			  throw ex;
			}
		}
		returnAST = sign_AST;
	}
	
	public void may_be_at_time_zone() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST may_be_at_time_zone_AST = null;
		Token  at = null;
		AST at_AST = null;
		
		try {      // for error handling
			base_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_at) && (LA(2)==LITERAL_time) && (LA(3)==LITERAL_zone)) {
				at = LT(1);
				at_AST = astFactory.create(at);
				match(LITERAL_at);
				match(LITERAL_time);
				match(LITERAL_zone);
				timezone_spec();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_202.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				if(at_AST != null){
				__markRule(AT_TIME_ZONE_EXPR);
				}
				
			}
			may_be_at_time_zone_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
			} else {
			  throw ex;
			}
		}
		returnAST = may_be_at_time_zone_AST;
	}
	
	public void timezone_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST timezone_spec_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case QUOTED_STR:
			{
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				complex_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_sessiontimezone:
			{
				AST tmp1209_AST = null;
				tmp1209_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1209_AST);
				match(LITERAL_sessiontimezone);
				break;
			}
			case LITERAL_dbtimezone:
			{
				AST tmp1210_AST = null;
				tmp1210_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1210_AST);
				match(LITERAL_dbtimezone);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(TIMEZONE_SPEC);
			}
			timezone_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
			} else {
			  throw ex;
			}
		}
		returnAST = timezone_spec_AST;
	}
	
	public void plsql_expr_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_expr_list_AST = null;
		Token  c = null;
		AST c_AST = null;
		
		try {      // for error handling
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop931:
			do {
				if ((LA(1)==COMMA)) {
					c = LT(1);
					c_AST = astFactory.create(c);
					match(COMMA);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop931;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXPR_LIST);
			}
			plsql_expr_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = plsql_expr_list_AST;
	}
	
	public void parentesized_plsql_exp_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parentesized_plsql_exp_list_AST = null;
		Token  cp = null;
		AST cp_AST = null;
		
		try {      // for error handling
			match(OPEN_PAREN);
			plsql_expr_list();
			astFactory.addASTChild(currentAST, returnAST);
			cp = LT(1);
			cp_AST = astFactory.create(cp);
			match(CLOSE_PAREN);
			parentesized_plsql_exp_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_214);
			} else {
			  throw ex;
			}
		}
		returnAST = parentesized_plsql_exp_list_AST;
	}
	
	public void logical_term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST logical_term_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			maybe_neg_factor();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop938:
			do {
				if ((LA(1)==LITERAL_and)) {
					p = LT(1);
					p_AST = astFactory.create(p);
					astFactory.addASTChild(currentAST, p_AST);
					match(LITERAL_and);
					if ( inputState.guessing==0 ) {
						p_AST.setType(AND_LOGICAL);
					}
					maybe_neg_factor();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop938;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(p_AST != null){
				__markRule(LOGICAL_EXPR);
				}
				
			}
			logical_term_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_215);
			} else {
			  throw ex;
			}
		}
		returnAST = logical_term_AST;
	}
	
	public void maybe_neg_factor() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST maybe_neg_factor_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==LITERAL_not)) {
				p = LT(1);
				p_AST = astFactory.create(p);
				match(LITERAL_not);
				if ( inputState.guessing==0 ) {
					p_AST.setType(NOT_LOGICAL);
				}
			}
			else if ((_tokenSet_43.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_expression33();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
				if(p_AST != null){
				__markRule(LOGICAL_EXPR);
				}
				
			}
			maybe_neg_factor_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_214);
			} else {
			  throw ex;
			}
		}
		returnAST = maybe_neg_factor_AST;
	}
	
	public void plsql_expression33() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_expression33_AST = null;
		
		try {      // for error handling
			boolean synPredMatched943 = false;
			if (((LA(1)==LITERAL_current) && (LA(2)==LITERAL_of))) {
				int _m943 = mark();
				synPredMatched943 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_current);
					match(LITERAL_of);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched943 = false;
				}
				rewind(_m943);
				inputState.guessing--;
			}
			if ( synPredMatched943 ) {
				{
				match(LITERAL_current);
				match(LITERAL_of);
				}
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(CURRENT_OF_CLAUSE);
				}
				plsql_expression33_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched946 = false;
				if (((LA(1)==LITERAL_exists) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m946 = mark();
					synPredMatched946 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_exists);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched946 = false;
					}
					rewind(_m946);
					inputState.guessing--;
				}
				if ( synPredMatched946 ) {
					match(LITERAL_exists);
					subquery();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						__markRule(EXISTS_EXPR);
					}
					plsql_expression33_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_216.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case LT:
					case LE:
					case GE:
					case NOT_EQ:
					case EQ:
					case GT:
					{
						relational_op();
						astFactory.addASTChild(currentAST, returnAST);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
						if ( inputState.guessing==0 ) {
							__markRule(RELATION_CONDITION);
						}
						break;
					}
					case LITERAL_is:
					{
						{
						AST tmp1215_AST = null;
						tmp1215_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1215_AST);
						match(LITERAL_is);
						{
						switch ( LA(1)) {
						case LITERAL_not:
						{
							AST tmp1216_AST = null;
							tmp1216_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp1216_AST);
							match(LITERAL_not);
							break;
						}
						case LITERAL_null:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						AST tmp1217_AST = null;
						tmp1217_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1217_AST);
						match(LITERAL_null);
						}
						if ( inputState.guessing==0 ) {
							__markRule(ISNULL_CONDITION);
						}
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case COMMA:
					case OPEN_PAREN:
					case CLOSE_PAREN:
					case DIVIDE:
					case THEN_COND_CMPL:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_package:
					case LITERAL_type:
					case LITERAL_truncate:
					case LITERAL_comment:
					case LITERAL_column:
					case LITERAL_disable:
					case LITERAL_enable:
					case LITERAL_set:
					case LITERAL_show:
					case LITERAL_var:
					case LITERAL_variable:
					case LITERAL_col:
					case LITERAL_exec:
					case LITERAL_execute:
					case LITERAL_whenever:
					case LITERAL_exit:
					case LITERAL_commit:
					case LITERAL_rollback:
					case LITERAL_def:
					case LITERAL_define:
					case LITERAL_prompt:
					case LITERAL_rem:
					case LITERAL_quit:
					case LITERAL_spool:
					case LITERAL_sta:
					case LITERAL_start:
					case LITERAL_repfooter:
					case LITERAL_repheader:
					case LITERAL_serveroutput:
					case LITERAL_begin:
					case LITERAL_declare:
					case LITERAL_create:
					case LITERAL_or:
					case LITERAL_with:
					case LITERAL_order:
					case LITERAL_connect:
					case LITERAL_using:
					case LITERAL_delete:
					case LITERAL_insert:
					case LITERAL_update:
					case LITERAL_for:
					case LITERAL_when:
					case LITERAL_rely:
					case LITERAL_return:
					case LITERAL_loop:
					case LITERAL_select:
					case LITERAL_merge:
					case LITERAL_then:
					case LITERAL_byte:
					case LITERAL_and:
					case LITERAL_union:
					case LITERAL_intersect:
					case LITERAL_minus:
					case LITERAL_left:
					case LITERAL_right:
					case LITERAL_inner:
					case LITERAL_join:
					case LITERAL_where:
					case LITERAL_group:
					case LITERAL_having:
					case LITERAL_returning:
					case LITERAL_characterset:
					case LITERAL_string:
					case LITERAL_load:
					case LITERAL_nobadfile:
					case LITERAL_badfile:
					case LITERAL_nodiscardfile:
					case LITERAL_discardfile:
					case LITERAL_nologfile:
					case LITERAL_logfile:
					case LITERAL_readsize:
					case LITERAL_skip:
					case LITERAL_data_cache:
					case LITERAL_fields:
					case LITERAL_preprocessor:
					case LITERAL_data:
					{
						break;
					}
					default:
						boolean synPredMatched952 = false;
						if (((LA(1)==LITERAL_not||LA(1)==LITERAL_in) && (_tokenSet_217.member(LA(2))) && (_tokenSet_218.member(LA(3))))) {
							int _m952 = mark();
							synPredMatched952 = true;
							inputState.guessing++;
							try {
								{
								{
								switch ( LA(1)) {
								case LITERAL_not:
								{
									match(LITERAL_not);
									break;
								}
								case LITERAL_in:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								match(LITERAL_in);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched952 = false;
							}
							rewind(_m952);
							inputState.guessing--;
						}
						if ( synPredMatched952 ) {
							{
							switch ( LA(1)) {
							case LITERAL_not:
							{
								AST tmp1218_AST = null;
								tmp1218_AST = astFactory.create(LT(1));
								astFactory.addASTChild(currentAST, tmp1218_AST);
								match(LITERAL_not);
								break;
							}
							case LITERAL_in:
							{
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							match(LITERAL_in);
							exp_set();
							astFactory.addASTChild(currentAST, returnAST);
							if ( inputState.guessing==0 ) {
								__markRule(IN_CONDITION);
							}
						}
						else {
							boolean synPredMatched956 = false;
							if (((LA(1)==LITERAL_not||LA(1)==LITERAL_like) && (_tokenSet_219.member(LA(2))) && (_tokenSet_218.member(LA(3))))) {
								int _m956 = mark();
								synPredMatched956 = true;
								inputState.guessing++;
								try {
									{
									{
									switch ( LA(1)) {
									case LITERAL_not:
									{
										match(LITERAL_not);
										break;
									}
									case LITERAL_like:
									{
										break;
									}
									default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
									}
									}
									match(LITERAL_like);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched956 = false;
								}
								rewind(_m956);
								inputState.guessing--;
							}
							if ( synPredMatched956 ) {
								{
								switch ( LA(1)) {
								case LITERAL_not:
								{
									AST tmp1220_AST = null;
									tmp1220_AST = astFactory.create(LT(1));
									astFactory.addASTChild(currentAST, tmp1220_AST);
									match(LITERAL_not);
									break;
								}
								case LITERAL_like:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								AST tmp1221_AST = null;
								tmp1221_AST = astFactory.create(LT(1));
								astFactory.addASTChild(currentAST, tmp1221_AST);
								match(LITERAL_like);
								plsql_expression();
								astFactory.addASTChild(currentAST, returnAST);
								{
								switch ( LA(1)) {
								case LITERAL_escape:
								{
									AST tmp1222_AST = null;
									tmp1222_AST = astFactory.create(LT(1));
									astFactory.addASTChild(currentAST, tmp1222_AST);
									match(LITERAL_escape);
									{
									if ((LA(1)==QUOTED_STR)) {
										string_literal();
										astFactory.addASTChild(currentAST, returnAST);
									}
									else if ((_tokenSet_5.member(LA(1)))) {
										identifier2();
										astFactory.addASTChild(currentAST, returnAST);
									}
									else {
										throw new NoViableAltException(LT(1), getFilename());
									}
									
									}
									break;
								}
								case EOF:
								case AT_PREFIXED:
								case SEMI:
								case COMMA:
								case OPEN_PAREN:
								case CLOSE_PAREN:
								case DIVIDE:
								case THEN_COND_CMPL:
								case LITERAL_alter:
								case LITERAL_drop:
								case LITERAL_function:
								case LITERAL_procedure:
								case LITERAL_package:
								case LITERAL_type:
								case LITERAL_truncate:
								case LITERAL_comment:
								case LITERAL_column:
								case LITERAL_disable:
								case LITERAL_enable:
								case LITERAL_set:
								case LITERAL_show:
								case LITERAL_var:
								case LITERAL_variable:
								case LITERAL_col:
								case LITERAL_exec:
								case LITERAL_execute:
								case LITERAL_whenever:
								case LITERAL_exit:
								case LITERAL_commit:
								case LITERAL_rollback:
								case LITERAL_def:
								case LITERAL_define:
								case LITERAL_prompt:
								case LITERAL_rem:
								case LITERAL_quit:
								case LITERAL_spool:
								case LITERAL_sta:
								case LITERAL_start:
								case LITERAL_repfooter:
								case LITERAL_repheader:
								case LITERAL_serveroutput:
								case LITERAL_begin:
								case LITERAL_declare:
								case LITERAL_create:
								case LITERAL_or:
								case LITERAL_with:
								case LITERAL_order:
								case LITERAL_connect:
								case LITERAL_using:
								case LITERAL_delete:
								case LITERAL_insert:
								case LITERAL_update:
								case LITERAL_for:
								case LITERAL_when:
								case LITERAL_rely:
								case LITERAL_return:
								case LITERAL_loop:
								case LITERAL_select:
								case LITERAL_merge:
								case LITERAL_then:
								case LITERAL_byte:
								case LITERAL_and:
								case LITERAL_union:
								case LITERAL_intersect:
								case LITERAL_minus:
								case LITERAL_left:
								case LITERAL_right:
								case LITERAL_inner:
								case LITERAL_join:
								case LITERAL_where:
								case LITERAL_group:
								case LITERAL_having:
								case LITERAL_returning:
								case LITERAL_characterset:
								case LITERAL_string:
								case LITERAL_load:
								case LITERAL_nobadfile:
								case LITERAL_badfile:
								case LITERAL_nodiscardfile:
								case LITERAL_discardfile:
								case LITERAL_nologfile:
								case LITERAL_logfile:
								case LITERAL_readsize:
								case LITERAL_skip:
								case LITERAL_data_cache:
								case LITERAL_fields:
								case LITERAL_preprocessor:
								case LITERAL_data:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								if ( inputState.guessing==0 ) {
									__markRule(LIKE_CONDITION);
								}
							}
							else {
								boolean synPredMatched962 = false;
								if (((LA(1)==LITERAL_not||LA(1)==LITERAL_between) && (_tokenSet_220.member(LA(2))) && (_tokenSet_221.member(LA(3))))) {
									int _m962 = mark();
									synPredMatched962 = true;
									inputState.guessing++;
									try {
										{
										{
										switch ( LA(1)) {
										case LITERAL_not:
										{
											match(LITERAL_not);
											break;
										}
										case LITERAL_between:
										{
											break;
										}
										default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
										}
										}
										match(LITERAL_between);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched962 = false;
									}
									rewind(_m962);
									inputState.guessing--;
								}
								if ( synPredMatched962 ) {
									{
									switch ( LA(1)) {
									case LITERAL_not:
									{
										AST tmp1223_AST = null;
										tmp1223_AST = astFactory.create(LT(1));
										astFactory.addASTChild(currentAST, tmp1223_AST);
										match(LITERAL_not);
										break;
									}
									case LITERAL_between:
									{
										break;
									}
									default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
									}
									}
									match(LITERAL_between);
									plsql_expression();
									astFactory.addASTChild(currentAST, returnAST);
									match(LITERAL_and);
									plsql_expression();
									astFactory.addASTChild(currentAST, returnAST);
									if ( inputState.guessing==0 ) {
										__markRule(BETWEEN_CONDITION);
									}
								}
								else {
									boolean synPredMatched968 = false;
									if (((LA(1)==LITERAL_not||LA(1)==LITERAL_member) && (LA(2)==LITERAL_of||LA(2)==LITERAL_member))) {
										int _m968 = mark();
										synPredMatched968 = true;
										inputState.guessing++;
										try {
											{
											{
											switch ( LA(1)) {
											case LITERAL_not:
											{
												match(LITERAL_not);
												break;
											}
											case LITERAL_member:
											{
												break;
											}
											default:
											{
												throw new NoViableAltException(LT(1), getFilename());
											}
											}
											}
											match(LITERAL_member);
											match(LITERAL_of);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched968 = false;
										}
										rewind(_m968);
										inputState.guessing--;
									}
									if ( synPredMatched968 ) {
										{
										{
										switch ( LA(1)) {
										case LITERAL_not:
										{
											match(LITERAL_not);
											break;
										}
										case LITERAL_member:
										{
											break;
										}
										default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
										}
										}
										match(LITERAL_member);
										match(LITERAL_of);
										{
										if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
											name_fragment();
											astFactory.addASTChild(currentAST, returnAST);
											match(DOT);
										}
										else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_214.member(LA(2)))) {
										}
										else {
											throw new NoViableAltException(LT(1), getFilename());
										}
										
										}
										name_fragment();
										astFactory.addASTChild(currentAST, returnAST);
										}
										if ( inputState.guessing==0 ) {
											__markRule(MEMBER_OF);
										}
									}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}
								}
								plsql_expression33_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched974 = false;
								if (((LA(1)==OPEN_PAREN) && (_tokenSet_43.member(LA(2))) && (_tokenSet_222.member(LA(3))))) {
									int _m974 = mark();
									synPredMatched974 = true;
									inputState.guessing++;
									try {
										{
										match(OPEN_PAREN);
										plsql_expr_list();
										match(CLOSE_PAREN);
										{
										switch ( LA(1)) {
										case EQ:
										{
											match(EQ);
											break;
										}
										case NOT_EQ:
										{
											match(NOT_EQ);
											break;
										}
										default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
										}
										}
										subquery();
										}
									}
									catch (RecognitionException pe) {
										synPredMatched974 = false;
									}
									rewind(_m974);
									inputState.guessing--;
								}
								if ( synPredMatched974 ) {
									match(OPEN_PAREN);
									plsql_expr_list();
									astFactory.addASTChild(currentAST, returnAST);
									match(CLOSE_PAREN);
									{
									switch ( LA(1)) {
									case EQ:
									{
										AST tmp1232_AST = null;
										tmp1232_AST = astFactory.create(LT(1));
										astFactory.addASTChild(currentAST, tmp1232_AST);
										match(EQ);
										break;
									}
									case NOT_EQ:
									{
										AST tmp1233_AST = null;
										tmp1233_AST = astFactory.create(LT(1));
										astFactory.addASTChild(currentAST, tmp1233_AST);
										match(NOT_EQ);
										break;
									}
									default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
									}
									}
									subquery();
									astFactory.addASTChild(currentAST, returnAST);
									if ( inputState.guessing==0 ) {
										__markRule(SUBQUERY_CONDITION);
									}
									plsql_expression33_AST = (AST)currentAST.root;
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_214);
								} else {
								  throw ex;
								}
							}
							returnAST = plsql_expression33_AST;
						}
						
	public void subquery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subquery_AST = null;
		Token  o = null;
		AST o_AST = null;
		Token  cp = null;
		AST cp_AST = null;
		
		try {      // for error handling
			o = LT(1);
			o_AST = astFactory.create(o);
			match(OPEN_PAREN);
			select_command();
			astFactory.addASTChild(currentAST, returnAST);
			cp = LT(1);
			cp_AST = astFactory.create(cp);
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SUBQUERY);
			}
			subquery_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_223);
			} else {
			  throw ex;
			}
		}
		returnAST = subquery_AST;
	}
	
	public void relational_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST relational_op_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case EQ:
			{
				AST tmp1234_AST = null;
				tmp1234_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1234_AST);
				match(EQ);
				break;
			}
			case LT:
			{
				AST tmp1235_AST = null;
				tmp1235_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1235_AST);
				match(LT);
				break;
			}
			case GT:
			{
				AST tmp1236_AST = null;
				tmp1236_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1236_AST);
				match(GT);
				break;
			}
			case NOT_EQ:
			{
				AST tmp1237_AST = null;
				tmp1237_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1237_AST);
				match(NOT_EQ);
				break;
			}
			case LE:
			{
				AST tmp1238_AST = null;
				tmp1238_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1238_AST);
				match(LE);
				break;
			}
			case GE:
			{
				AST tmp1239_AST = null;
				tmp1239_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1239_AST);
				match(GE);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(RELATION_OP);
			}
			relational_op_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_43);
			} else {
			  throw ex;
			}
		}
		returnAST = relational_op_AST;
	}
	
	public void exp_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_set_AST = null;
		
		try {      // for error handling
			boolean synPredMatched1196 = false;
			if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_54.member(LA(3))))) {
				int _m1196 = mark();
				synPredMatched1196 = true;
				inputState.guessing++;
				try {
					{
					match(OPEN_PAREN);
					match(LITERAL_select);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1196 = false;
				}
				rewind(_m1196);
				inputState.guessing--;
			}
			if ( synPredMatched1196 ) {
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
				exp_set_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==OPEN_PAREN) && (_tokenSet_43.member(LA(2))) && (_tokenSet_222.member(LA(3)))) {
				parentesized_plsql_exp_list();
				astFactory.addASTChild(currentAST, returnAST);
				exp_set_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_218.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				exp_set_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_214);
			} else {
			  throw ex;
			}
		}
		returnAST = exp_set_AST;
	}
	
	public void cast_proc() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cast_proc_AST = null;
		
		try {      // for error handling
			match(LITERAL_cast);
			match(OPEN_PAREN);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_as);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==CLOSE_PAREN) && (_tokenSet_48.member(LA(3)))) {
				type_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_224.member(LA(2))) && (_tokenSet_225.member(LA(3)))) {
				datatype();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(CAST_EXPR);
			}
			cast_proc_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = cast_proc_AST;
	}
	
	public void trim_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trim_function_AST = null;
		
		try {      // for error handling
			match(LITERAL_trim);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_leading:
			{
				AST tmp1246_AST = null;
				tmp1246_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1246_AST);
				match(LITERAL_leading);
				break;
			}
			case LITERAL_trailing:
			{
				AST tmp1247_AST = null;
				tmp1247_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1247_AST);
				match(LITERAL_trailing);
				break;
			}
			case LITERAL_both:
			{
				AST tmp1248_AST = null;
				tmp1248_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1248_AST);
				match(LITERAL_both);
				break;
			}
			default:
				if ((_tokenSet_43.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_from:
			{
				AST tmp1249_AST = null;
				tmp1249_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1249_AST);
				match(LITERAL_from);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(TRIM_FUNC);
			}
			trim_function_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = trim_function_AST;
	}
	
	public void count_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST count_function_AST = null;
		
		try {      // for error handling
			match(LITERAL_count);
			match(OPEN_PAREN);
			{
			if ((LA(1)==ASTERISK)) {
				asterisk_column();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				boolean synPredMatched1040 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1040 = mark();
					synPredMatched1040 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1040 = false;
					}
					rewind(_m1040);
					inputState.guessing--;
				}
				if ( synPredMatched1040 ) {
					ident_asterisk_column();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_226.member(LA(1))) && (_tokenSet_227.member(LA(2))) && (_tokenSet_228.member(LA(3)))) {
					{
					{
					if ((LA(1)==LITERAL_distinct)) {
						AST tmp1253_AST = null;
						tmp1253_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1253_AST);
						match(LITERAL_distinct);
					}
					else if ((_tokenSet_43.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(CLOSE_PAREN);
				if ( inputState.guessing==0 ) {
					__markRule(COUNT_FUNC);
				}
				count_function_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_48);
				} else {
				  throw ex;
				}
			}
			returnAST = count_function_AST;
		}
		
	public void case_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST case_expression_AST = null;
		Token  t = null;
		AST t_AST = null;
		AST smpl_AST = null;
		
		try {      // for error handling
			{
			match(LITERAL_case);
			{
			if ((LA(1)==LITERAL_when)) {
				{
				int _cnt1057=0;
				_loop1057:
				do {
					if ((LA(1)==LITERAL_when)) {
						AST tmp1256_AST = null;
						tmp1256_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1256_AST);
						match(LITERAL_when);
						condition();
						astFactory.addASTChild(currentAST, returnAST);
						t = LT(1);
						t_AST = astFactory.create(t);
						astFactory.addASTChild(currentAST, t_AST);
						match(LITERAL_then);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt1057>=1 ) { break _loop1057; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1057++;
				} while (true);
				}
			}
			else if ((_tokenSet_43.member(LA(1)))) {
				plsql_expression();
				smpl_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				{
				int _cnt1059=0;
				_loop1059:
				do {
					if ((LA(1)==LITERAL_when)) {
						AST tmp1257_AST = null;
						tmp1257_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1257_AST);
						match(LITERAL_when);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
						AST tmp1258_AST = null;
						tmp1258_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1258_AST);
						match(LITERAL_then);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt1059>=1 ) { break _loop1059; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1059++;
				} while (true);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_else:
			{
				AST tmp1259_AST = null;
				tmp1259_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1259_AST);
				match(LITERAL_else);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_end:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_end);
			}
			if ( inputState.guessing==0 ) {
				
				if(smpl_AST != null){
				{ __markRule(CASE_EXPRESSION_SMPL);}
				} else {
				{ __markRule(CASE_EXPRESSION_SRCH);}
				}
				
			}
			case_expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = case_expression_AST;
	}
	
	public void dence_rank_analytics_func() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dence_rank_analytics_func_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_rank:
			{
				AST tmp1261_AST = null;
				tmp1261_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1261_AST);
				match(LITERAL_rank);
				break;
			}
			case LITERAL_dense_rank:
			{
				AST tmp1262_AST = null;
				tmp1262_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1262_AST);
				match(LITERAL_dense_rank);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1263_AST = null;
			tmp1263_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1263_AST);
			match(OPEN_PAREN);
			AST tmp1264_AST = null;
			tmp1264_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1264_AST);
			match(CLOSE_PAREN);
			AST tmp1265_AST = null;
			tmp1265_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1265_AST);
			match(LITERAL_over);
			AST tmp1266_AST = null;
			tmp1266_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1266_AST);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_order:
			{
				order_clause();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_partition:
			{
				{
				AST tmp1267_AST = null;
				tmp1267_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1267_AST);
				match(LITERAL_partition);
				AST tmp1268_AST = null;
				tmp1268_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1268_AST);
				match(LITERAL_by);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1269_AST = null;
			tmp1269_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1269_AST);
			match(CLOSE_PAREN);
			dence_rank_analytics_func_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = dence_rank_analytics_func_AST;
	}
	
	public void extract_date_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST extract_date_function_AST = null;
		
		try {      // for error handling
			match(LITERAL_extract);
			match(OPEN_PAREN);
			extract_consts();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_from);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(EXTRACT_DATE_FUNC);
			}
			extract_date_function_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = extract_date_function_AST;
	}
	
	public void ident_percentage() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST ident_percentage_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				cursor_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case OPEN_PAREN:
			{
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(PERCENTAGE);
			{
			switch ( LA(1)) {
			case LITERAL_rowcount:
			{
				AST tmp1275_AST = null;
				tmp1275_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1275_AST);
				match(LITERAL_rowcount);
				if ( inputState.guessing==0 ) {
					__markRule(ROWCOUNT_EXRESSION);
				}
				break;
			}
			case LITERAL_found:
			{
				AST tmp1276_AST = null;
				tmp1276_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1276_AST);
				match(LITERAL_found);
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_STATE);
				}
				break;
			}
			case LITERAL_notfound:
			{
				AST tmp1277_AST = null;
				tmp1277_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1277_AST);
				match(LITERAL_notfound);
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_STATE);
				}
				break;
			}
			case LITERAL_isopen:
			{
				AST tmp1278_AST = null;
				tmp1278_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1278_AST);
				match(LITERAL_isopen);
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_STATE);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			ident_percentage_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = ident_percentage_AST;
	}
	
	public void pseudo_column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST pseudo_column_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_user:
			{
				AST tmp1279_AST = null;
				tmp1279_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1279_AST);
				match(LITERAL_user);
				if ( inputState.guessing==0 ) {
					__markRule(USER_CONST);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_sysdate:
			{
				AST tmp1280_AST = null;
				tmp1280_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1280_AST);
				match(LITERAL_sysdate);
				if ( inputState.guessing==0 ) {
					__markRule(SYSDATE_CONST);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_systimestamp:
			{
				AST tmp1281_AST = null;
				tmp1281_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1281_AST);
				match(LITERAL_systimestamp);
				if ( inputState.guessing==0 ) {
					__markRule(SYSTIMESTAMP_CONST);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_current_timestamp:
			{
				AST tmp1282_AST = null;
				tmp1282_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1282_AST);
				match(LITERAL_current_timestamp);
				if ( inputState.guessing==0 ) {
					__markRule(CURRENT_TIMESTAMP_CONST);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_dbtimezone:
			{
				AST tmp1283_AST = null;
				tmp1283_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1283_AST);
				match(LITERAL_dbtimezone);
				if ( inputState.guessing==0 ) {
					__markRule(DBTIMEZONE);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rownum:
			{
				AST tmp1284_AST = null;
				tmp1284_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1284_AST);
				match(LITERAL_rownum);
				if ( inputState.guessing==0 ) {
					__markRule(ROWNUM);
				}
				pseudo_column_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = pseudo_column_AST;
	}
	
	public void column_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_spec_AST = null;
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				name_fragment();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_229.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_SPEC);
			}
			column_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_229);
			} else {
			  throw ex;
			}
		}
		returnAST = column_spec_AST;
	}
	
	public void sequence_expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sequence_expr_AST = null;
		
		try {      // for error handling
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			match(DOT);
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(SEQUENCE_EXPR);
			}
			sequence_expr_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		returnAST = sequence_expr_AST;
	}
	
	public void order_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST order_clause_AST = null;
		
		try {      // for error handling
			match(LITERAL_order);
			match(LITERAL_by);
			sorted_def();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1211:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					sorted_def();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1211;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(ORDER_CLAUSE);
			}
			order_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_230);
			} else {
			  throw ex;
			}
		}
		returnAST = order_clause_AST;
	}
	
	public void asterisk_column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST asterisk_column_AST = null;
		
		try {      // for error handling
			AST tmp1290_AST = null;
			tmp1290_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1290_AST);
			match(ASTERISK);
			if ( inputState.guessing==0 ) {
				__markRule(ASTERISK_COLUMN);
			}
			asterisk_column_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_231);
			} else {
			  throw ex;
			}
		}
		returnAST = asterisk_column_AST;
	}
	
	public void ident_asterisk_column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST ident_asterisk_column_AST = null;
		
		try {      // for error handling
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1291_AST = null;
			tmp1291_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1291_AST);
			match(DOT);
			AST tmp1292_AST = null;
			tmp1292_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1292_AST);
			match(ASTERISK);
			if ( inputState.guessing==0 ) {
				__markRule(IDENT_ASTERISK_COLUMN);
			}
			ident_asterisk_column_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_231);
			} else {
			  throw ex;
			}
		}
		returnAST = ident_asterisk_column_AST;
	}
	
	public void extract_consts() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST extract_consts_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_second:
			{
				AST tmp1293_AST = null;
				tmp1293_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1293_AST);
				match(LITERAL_second);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_minute:
			{
				AST tmp1294_AST = null;
				tmp1294_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1294_AST);
				match(LITERAL_minute);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_hour:
			{
				AST tmp1295_AST = null;
				tmp1295_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1295_AST);
				match(LITERAL_hour);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_day:
			{
				AST tmp1296_AST = null;
				tmp1296_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1296_AST);
				match(LITERAL_day);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_month:
			{
				AST tmp1297_AST = null;
				tmp1297_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1297_AST);
				match(LITERAL_month);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_year:
			{
				AST tmp1298_AST = null;
				tmp1298_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1298_AST);
				match(LITERAL_year);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_timezone_hour:
			{
				AST tmp1299_AST = null;
				tmp1299_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1299_AST);
				match(LITERAL_timezone_hour);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_timezone_minute:
			{
				AST tmp1300_AST = null;
				tmp1300_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1300_AST);
				match(LITERAL_timezone_minute);
				extract_consts_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_232);
			} else {
			  throw ex;
			}
		}
		returnAST = extract_consts_AST;
	}
	
	public void date_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST date_literal_AST = null;
		
		try {      // for error handling
			{
			int _cnt1050=0;
			_loop1050:
			do {
				if ((LA(1)==QUOTED_STR)) {
					AST tmp1301_AST = null;
					tmp1301_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1301_AST);
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1050>=1 ) { break _loop1050; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1050++;
			} while (true);
			}
			date_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = date_literal_AST;
	}
	
	public void commit_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST commit_statement_AST = null;
		
		try {      // for error handling
			AST tmp1302_AST = null;
			tmp1302_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1302_AST);
			match(LITERAL_commit);
			{
			switch ( LA(1)) {
			case LITERAL_work:
			{
				AST tmp1303_AST = null;
				tmp1303_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1303_AST);
				match(LITERAL_work);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(COMMIT_STATEMENT);
			}
			commit_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = commit_statement_AST;
	}
	
	public void elsif_statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST elsif_statements_AST = null;
		
		try {      // for error handling
			match(LITERAL_elsif);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_then);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ELSIF_STATEMENT);
			}
			elsif_statements_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_233);
			} else {
			  throw ex;
			}
		}
		returnAST = elsif_statements_AST;
	}
	
	public void else_statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST else_statements_AST = null;
		
		try {      // for error handling
			match(LITERAL_else);
			seq_of_statements();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ELSE_STATEMENT);
			}
			else_statements_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_156);
			} else {
			  throw ex;
			}
		}
		returnAST = else_statements_AST;
	}
	
	public void insert_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST insert_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_insert);
			match(LITERAL_into);
			{
			boolean synPredMatched1226 = false;
			if (((_tokenSet_234.member(LA(1))))) {
				int _m1226 = mark();
				synPredMatched1226 = true;
				inputState.guessing++;
				try {
					{
					table_alias();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1226 = false;
				}
				rewind(_m1226);
				inputState.guessing--;
			}
			if ( synPredMatched1226 ) {
				table_alias();
				astFactory.addASTChild(currentAST, returnAST);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					column_spec_list();
					astFactory.addASTChild(currentAST, returnAST);
					match(CLOSE_PAREN);
				}
				else if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_values||LA(1)==LITERAL_select) && (_tokenSet_54.member(LA(2))) && (_tokenSet_235.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_values:
				{
					{
					match(LITERAL_values);
					{
					if ((LA(1)==OPEN_PAREN)) {
						parentesized_plsql_exp_list();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((_tokenSet_5.member(LA(1)))) {
						variable_ref();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					break;
				}
				case OPEN_PAREN:
				case LITERAL_select:
				{
					select_expression();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_return:
				case LITERAL_returning:
				{
					returning();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(INSERT_COMMAND);
				}
			}
			else if ((LA(1)==OPEN_PAREN)) {
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
				{
				match(LITERAL_values);
				{
				if ((LA(1)==OPEN_PAREN)) {
					parentesized_plsql_exp_list();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_18.member(LA(1)))) {
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(INSERT_INTO_SUBQUERY_COMMAND);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			insert_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = insert_command_AST;
	}
	
	public void update_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST update_command_AST = null;
		
		try {      // for error handling
			{
			boolean synPredMatched1240 = false;
			if (((LA(1)==LITERAL_update) && (_tokenSet_234.member(LA(2))) && (_tokenSet_236.member(LA(3))))) {
				int _m1240 = mark();
				synPredMatched1240 = true;
				inputState.guessing++;
				try {
					{
					subquery_update();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1240 = false;
				}
				rewind(_m1240);
				inputState.guessing--;
			}
			if ( synPredMatched1240 ) {
				subquery_update();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==LITERAL_update) && (_tokenSet_234.member(LA(2))) && (_tokenSet_236.member(LA(3)))) {
				simple_update();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(UPDATE_COMMAND);
			}
			update_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = update_command_AST;
	}
	
	public void delete_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST delete_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_delete);
			{
			if ((LA(1)==LITERAL_from)) {
				match(LITERAL_from);
			}
			else if ((_tokenSet_234.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_alias();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_return:
			case LITERAL_returning:
			{
				returning();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(DELETE_COMMAND);
			}
			delete_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = delete_command_AST;
	}
	
	public void merge_command() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST merge_command_AST = null;
		
		try {      // for error handling
			match(LITERAL_merge);
			match(LITERAL_into);
			table_alias();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_using);
			{
			if ((_tokenSet_234.member(LA(1)))) {
				table_alias();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==OPEN_PAREN)) {
				{
				subquery();
				astFactory.addASTChild(currentAST, returnAST);
				{
				if ((_tokenSet_237.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_55.member(LA(3)))) {
					alias();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_on) && (_tokenSet_55.member(LA(2))) && (_tokenSet_238.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			AST tmp1318_AST = null;
			tmp1318_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1318_AST);
			match(LITERAL_on);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			when_action();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_when:
			{
				when_action();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_delete) && (LA(2)==LITERAL_where) && (_tokenSet_55.member(LA(3)))) {
				AST tmp1319_AST = null;
				tmp1319_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1319_AST);
				match(LITERAL_delete);
				AST tmp1320_AST = null;
				tmp1320_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1320_AST);
				match(LITERAL_where);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_14.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(MERGE_COMMAND);
			}
			merge_command_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = merge_command_AST;
	}
	
	public void rollback_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rollback_statement_AST = null;
		
		try {      // for error handling
			AST tmp1321_AST = null;
			tmp1321_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1321_AST);
			match(LITERAL_rollback);
			{
			switch ( LA(1)) {
			case LITERAL_work:
			{
				AST tmp1322_AST = null;
				tmp1322_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1322_AST);
				match(LITERAL_work);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_to:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_to:
			{
				match(LITERAL_to);
				{
				switch ( LA(1)) {
				case LITERAL_savepoint:
				{
					AST tmp1324_AST = null;
					tmp1324_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1324_AST);
					match(LITERAL_savepoint);
					break;
				}
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				savepoint_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_comment) && (LA(2)==QUOTED_STR) && (_tokenSet_239.member(LA(3)))) {
				match(LITERAL_comment);
				string_literal();
			}
			else if ((_tokenSet_14.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(ROLLBACK_STATEMENT);
			}
			rollback_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = rollback_statement_AST;
	}
	
	public void select_first() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_first_AST = null;
		Token  o = null;
		AST o_AST = null;
		Token  c = null;
		AST c_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_select:
			{
				{
				select_up_to_list();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_bulk:
				case LITERAL_into:
				{
					into_clause();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_from:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				table_reference_list_from();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_where:
				{
					where_condition();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_with:
				case LITERAL_order:
				case LITERAL_connect:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_for:
				case LITERAL_return:
				case LITERAL_select:
				case LITERAL_merge:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_minus:
				case LITERAL_group:
				case LITERAL_having:
				case LITERAL_returning:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_by||LA(2)==LITERAL_with) && (_tokenSet_55.member(LA(3)))) {
					connect_clause();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_240.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_group:
				case LITERAL_having:
				{
					group_clause();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_with:
				case LITERAL_order:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_for:
				case LITERAL_return:
				case LITERAL_select:
				case LITERAL_merge:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_minus:
				case LITERAL_returning:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_order:
				{
					order_clause();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_with:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_for:
				case LITERAL_return:
				case LITERAL_select:
				case LITERAL_merge:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_minus:
				case LITERAL_returning:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_for:
				{
					update_clause();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_with:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_select:
				case LITERAL_merge:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_minus:
				case LITERAL_returning:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SELECT_EXPRESSION);
				}
				select_first_AST = (AST)currentAST.root;
				break;
			}
			case OPEN_PAREN:
			{
				o = LT(1);
				o_AST = astFactory.create(o);
				astFactory.addASTChild(currentAST, o_AST);
				match(OPEN_PAREN);
				select_first();
				astFactory.addASTChild(currentAST, returnAST);
				c = LT(1);
				c_AST = astFactory.create(c);
				astFactory.addASTChild(currentAST, c_AST);
				match(CLOSE_PAREN);
				select_first_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_241);
			} else {
			  throw ex;
			}
		}
		returnAST = select_first_AST;
	}
	
	public void select_up_to_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_up_to_list_AST = null;
		
		try {      // for error handling
			match(LITERAL_select);
			{
			switch ( LA(1)) {
			case LITERAL_distinct:
			{
				AST tmp1327_AST = null;
				tmp1327_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1327_AST);
				match(LITERAL_distinct);
				break;
			}
			case LITERAL_unique:
			{
				AST tmp1328_AST = null;
				tmp1328_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1328_AST);
				match(LITERAL_unique);
				break;
			}
			default:
				if ((LA(1)==LITERAL_all) && (_tokenSet_242.member(LA(2))) && (_tokenSet_243.member(LA(3)))) {
					AST tmp1329_AST = null;
					tmp1329_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1329_AST);
					match(LITERAL_all);
				}
				else if ((_tokenSet_242.member(LA(1))) && (_tokenSet_243.member(LA(2))) && (_tokenSet_244.member(LA(3)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			select_list();
			astFactory.addASTChild(currentAST, returnAST);
			select_up_to_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_245);
			} else {
			  throw ex;
			}
		}
		returnAST = select_up_to_list_AST;
	}
	
	public void into_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST into_clause_AST = null;
		Token  i = null;
		AST i_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_bulk:
			{
				AST tmp1330_AST = null;
				tmp1330_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1330_AST);
				match(LITERAL_bulk);
				AST tmp1331_AST = null;
				tmp1331_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1331_AST);
				match(LITERAL_collect);
				break;
			}
			case LITERAL_into:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			i = LT(1);
			i_AST = astFactory.create(i);
			match(LITERAL_into);
			plsql_lvalue_list();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(INTO_CLAUSE);
			}
			into_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_232);
			} else {
			  throw ex;
			}
		}
		returnAST = into_clause_AST;
	}
	
	public void table_reference_list_from() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_reference_list_from_AST = null;
		
		try {      // for error handling
			match(LITERAL_from);
			selected_table();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_partition:
			{
				match(LITERAL_partition);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_with:
			case LITERAL_order:
			case LITERAL_connect:
			case LITERAL_using:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_for:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_minus:
			case LITERAL_left:
			case LITERAL_right:
			case LITERAL_inner:
			case LITERAL_join:
			case LITERAL_where:
			case LITERAL_group:
			case LITERAL_having:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop1137:
			do {
				switch ( LA(1)) {
				case LITERAL_left:
				case LITERAL_right:
				case LITERAL_inner:
				case LITERAL_join:
				{
					ansi_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				{
					{
					match(COMMA);
					selected_table();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case LITERAL_partition:
					{
						match(LITERAL_partition);
						match(OPEN_PAREN);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
						match(CLOSE_PAREN);
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case COMMA:
					case OPEN_PAREN:
					case CLOSE_PAREN:
					case DIVIDE:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_package:
					case LITERAL_type:
					case LITERAL_truncate:
					case LITERAL_comment:
					case LITERAL_column:
					case LITERAL_set:
					case LITERAL_show:
					case LITERAL_var:
					case LITERAL_variable:
					case LITERAL_col:
					case LITERAL_exec:
					case LITERAL_execute:
					case LITERAL_whenever:
					case LITERAL_exit:
					case LITERAL_commit:
					case LITERAL_rollback:
					case LITERAL_def:
					case LITERAL_define:
					case LITERAL_prompt:
					case LITERAL_rem:
					case LITERAL_quit:
					case LITERAL_spool:
					case LITERAL_sta:
					case LITERAL_start:
					case LITERAL_repfooter:
					case LITERAL_repheader:
					case LITERAL_serveroutput:
					case LITERAL_begin:
					case LITERAL_declare:
					case LITERAL_create:
					case LITERAL_with:
					case LITERAL_order:
					case LITERAL_connect:
					case LITERAL_using:
					case LITERAL_delete:
					case LITERAL_insert:
					case LITERAL_update:
					case LITERAL_for:
					case LITERAL_return:
					case LITERAL_select:
					case LITERAL_merge:
					case LITERAL_union:
					case LITERAL_intersect:
					case LITERAL_minus:
					case LITERAL_left:
					case LITERAL_right:
					case LITERAL_inner:
					case LITERAL_join:
					case LITERAL_where:
					case LITERAL_group:
					case LITERAL_having:
					case LITERAL_returning:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
					break;
				}
				default:
				{
					break _loop1137;
				}
				}
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				__markRule(TABLE_REFERENCE_LIST_FROM);
				
			}
			table_reference_list_from_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_246);
			} else {
			  throw ex;
			}
		}
		returnAST = table_reference_list_from_AST;
	}
	
	public void where_condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST where_condition_AST = null;
		
		try {      // for error handling
			match(LITERAL_where);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(WHERE_CONDITION);
			}
			where_condition_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_247);
			} else {
			  throw ex;
			}
		}
		returnAST = where_condition_AST;
	}
	
	public void connect_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST connect_clause_AST = null;
		
		try {      // for error handling
			connect_clause_internal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_by||LA(2)==LITERAL_with) && (_tokenSet_55.member(LA(3)))) {
				connect_clause_internal();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_240.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(CONNECT_CLAUSE);
			}
			connect_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_240);
			} else {
			  throw ex;
			}
		}
		returnAST = connect_clause_AST;
	}
	
	public void group_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST group_clause_AST = null;
		Token  c = null;
		AST c_AST = null;
		
		try {      // for error handling
			{
			int _cnt1208=0;
			_loop1208:
			do {
				switch ( LA(1)) {
				case LITERAL_group:
				{
					{
					match(LITERAL_group);
					match(LITERAL_by);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
					{
					_loop1206:
					do {
						if ((LA(1)==COMMA)) {
							c = LT(1);
							c_AST = astFactory.create(c);
							match(COMMA);
							plsql_expression();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop1206;
						}
						
					} while (true);
					}
					}
					break;
				}
				case LITERAL_having:
				{
					{
					AST tmp1343_AST = null;
					tmp1343_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1343_AST);
					match(LITERAL_having);
					condition();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					if ( _cnt1208>=1 ) { break _loop1208; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				}
				_cnt1208++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(GROUP_CLAUSE);
			}
			group_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_248);
			} else {
			  throw ex;
			}
		}
		returnAST = group_clause_AST;
	}
	
	public void update_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST update_clause_AST = null;
		
		try {      // for error handling
			AST tmp1344_AST = null;
			tmp1344_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1344_AST);
			match(LITERAL_for);
			AST tmp1345_AST = null;
			tmp1345_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1345_AST);
			match(LITERAL_update);
			{
			switch ( LA(1)) {
			case LITERAL_of:
			{
				AST tmp1346_AST = null;
				tmp1346_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1346_AST);
				match(LITERAL_of);
				column_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop1220:
				do {
					if ((LA(1)==COMMA)) {
						AST tmp1347_AST = null;
						tmp1347_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1347_AST);
						match(COMMA);
						column_name_ref();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop1220;
					}
					
				} while (true);
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_with:
			case LITERAL_using:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_minus:
			case LITERAL_nowait:
			case LITERAL_wait:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_nowait:
			{
				AST tmp1348_AST = null;
				tmp1348_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1348_AST);
				match(LITERAL_nowait);
				break;
			}
			case LITERAL_wait:
			{
				{
				AST tmp1349_AST = null;
				tmp1349_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1349_AST);
				match(LITERAL_wait);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_with:
			case LITERAL_using:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_minus:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(FOR_UPDATE_CLAUSE);
			}
			update_clause_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_241);
			} else {
			  throw ex;
			}
		}
		returnAST = update_clause_AST;
	}
	
	public void plsql_lvalue_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_lvalue_list_AST = null;
		
		try {      // for error handling
			plsql_lvalue();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1089:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_lvalue();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1089;
				}
				
			} while (true);
			}
			plsql_lvalue_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_249);
			} else {
			  throw ex;
			}
		}
		returnAST = plsql_lvalue_list_AST;
	}
	
	public void select_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_list_AST = null;
		
		try {      // for error handling
			{
			displayed_column();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1095:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					displayed_column();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1095;
				}
				
			} while (true);
			}
			}
			select_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_245);
			} else {
			  throw ex;
			}
		}
		returnAST = select_list_AST;
	}
	
	public void displayed_column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST displayed_column_AST = null;
		
		try {      // for error handling
			if ((LA(1)==ASTERISK)) {
				asterisk_column();
				astFactory.addASTChild(currentAST, returnAST);
				displayed_column_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched1098 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1098 = mark();
					synPredMatched1098 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1098 = false;
					}
					rewind(_m1098);
					inputState.guessing--;
				}
				if ( synPredMatched1098 ) {
					ident_asterisk_column();
					astFactory.addASTChild(currentAST, returnAST);
					displayed_column_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_43.member(LA(1))) && (_tokenSet_243.member(LA(2))) && (_tokenSet_244.member(LA(3)))) {
					expr_column();
					astFactory.addASTChild(currentAST, returnAST);
					displayed_column_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_250);
				} else {
				  throw ex;
				}
			}
			returnAST = displayed_column_AST;
		}
		
	public void expr_column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_column_AST = null;
		
		try {      // for error handling
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((_tokenSet_237.member(LA(1)))) {
				alias();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_250.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXPR_COLUMN);
			}
			expr_column_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_250);
			} else {
			  throw ex;
			}
		}
		returnAST = expr_column_AST;
	}
	
	public void alias() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alias_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==LITERAL_as)) {
				match(LITERAL_as);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			alias_ident();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ALIAS_NAME);
			}
			alias_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_251);
			} else {
			  throw ex;
			}
		}
		returnAST = alias_AST;
	}
	
	public void plsql_exp_list_using() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST plsql_exp_list_using_AST = null;
		
		try {      // for error handling
			boolean synPredMatched1113 = false;
			if (((_tokenSet_252.member(LA(1))) && (_tokenSet_253.member(LA(2))) && (_tokenSet_254.member(LA(3))))) {
				int _m1113 = mark();
				synPredMatched1113 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case LITERAL_in:
					{
						match(LITERAL_in);
						break;
					}
					case LITERAL_out:
					{
						match(LITERAL_out);
						break;
					}
					default:
						if ((_tokenSet_43.member(LA(1)))) {
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					plsql_expression();
					match(COMMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1113 = false;
				}
				rewind(_m1113);
				inputState.guessing--;
			}
			if ( synPredMatched1113 ) {
				{
				switch ( LA(1)) {
				case LITERAL_in:
				{
					AST tmp1353_AST = null;
					tmp1353_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1353_AST);
					match(LITERAL_in);
					break;
				}
				case LITERAL_out:
				{
					AST tmp1354_AST = null;
					tmp1354_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1354_AST);
					match(LITERAL_out);
					break;
				}
				default:
					if ((_tokenSet_43.member(LA(1)))) {
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop1117:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						switch ( LA(1)) {
						case LITERAL_in:
						{
							AST tmp1356_AST = null;
							tmp1356_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp1356_AST);
							match(LITERAL_in);
							break;
						}
						case LITERAL_out:
						{
							AST tmp1357_AST = null;
							tmp1357_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp1357_AST);
							match(LITERAL_out);
							break;
						}
						default:
							if ((_tokenSet_43.member(LA(1)))) {
							}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop1117;
					}
					
				} while (true);
				}
				if ( inputState.guessing==0 ) {
					__markRule(PLSQL_EXPR_LIST);
				}
				plsql_exp_list_using_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_252.member(LA(1))) && (_tokenSet_255.member(LA(2))) && (_tokenSet_256.member(LA(3)))) {
				{
				switch ( LA(1)) {
				case LITERAL_in:
				{
					AST tmp1358_AST = null;
					tmp1358_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1358_AST);
					match(LITERAL_in);
					break;
				}
				case LITERAL_out:
				{
					AST tmp1359_AST = null;
					tmp1359_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1359_AST);
					match(LITERAL_out);
					break;
				}
				default:
					if ((_tokenSet_43.member(LA(1)))) {
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				plsql_exp_list_using_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
		returnAST = plsql_exp_list_using_AST;
	}
	
	public void alter_system_session() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alter_system_session_AST = null;
		
		try {      // for error handling
			AST tmp1360_AST = null;
			tmp1360_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1360_AST);
			match(LITERAL_alter);
			{
			switch ( LA(1)) {
			case LITERAL_system:
			{
				AST tmp1361_AST = null;
				tmp1361_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1361_AST);
				match(LITERAL_system);
				break;
			}
			case LITERAL_session:
			{
				AST tmp1362_AST = null;
				tmp1362_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1362_AST);
				match(LITERAL_session);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_flush:
			{
				{
				AST tmp1363_AST = null;
				tmp1363_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1363_AST);
				match(LITERAL_flush);
				AST tmp1364_AST = null;
				tmp1364_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1364_AST);
				match(LITERAL_shared_pool);
				}
				break;
			}
			case LITERAL_set:
			case LITERAL_reset:
			{
				{
				switch ( LA(1)) {
				case LITERAL_set:
				{
					{
					AST tmp1365_AST = null;
					tmp1365_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1365_AST);
					match(LITERAL_set);
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp1366_AST = null;
					tmp1366_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1366_AST);
					match(EQ);
					{
					switch ( LA(1)) {
					case QUOTED_STR:
					{
						string_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case NUMBER:
					{
						numeric_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case IDENTIFIER:
					case DOUBLE_QUOTED_STRING:
					{
						identifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
					break;
				}
				case LITERAL_reset:
				{
					{
					AST tmp1367_AST = null;
					tmp1367_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1367_AST);
					match(LITERAL_reset);
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_sid:
				{
					AST tmp1368_AST = null;
					tmp1368_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1368_AST);
					match(LITERAL_sid);
					AST tmp1369_AST = null;
					tmp1369_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1369_AST);
					match(EQ);
					{
					switch ( LA(1)) {
					case QUOTED_STR:
					{
						string_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case ASTERISK:
					{
						AST tmp1370_AST = null;
						tmp1370_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1370_AST);
						match(ASTERISK);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_select:
				case LITERAL_merge:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			alter_system_session_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = alter_system_session_AST;
	}
	
	public void selected_table() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST selected_table_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_table:
			{
				row_proc();
				astFactory.addASTChild(currentAST, returnAST);
				{
				if ((_tokenSet_237.member(LA(1))) && (_tokenSet_257.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					alias();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_258.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(VIRTUAL_TABLE);
				}
				selected_table_AST = (AST)currentAST.root;
				break;
			}
			case OPEN_PAREN:
			{
				from_subquery();
				astFactory.addASTChild(currentAST, returnAST);
				selected_table_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched1170 = false;
				if (((LA(1)==LITERAL_the) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m1170 = mark();
					synPredMatched1170 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_the);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1170 = false;
					}
					rewind(_m1170);
					inputState.guessing--;
				}
				if ( synPredMatched1170 ) {
					the_proc();
					astFactory.addASTChild(currentAST, returnAST);
					{
					if ((_tokenSet_237.member(LA(1))) && (_tokenSet_257.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						alias();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((_tokenSet_258.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					selected_table_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_234.member(LA(1))) && (_tokenSet_259.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					table_alias();
					astFactory.addASTChild(currentAST, returnAST);
					selected_table_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_258);
			} else {
			  throw ex;
			}
		}
		returnAST = selected_table_AST;
	}
	
	public void ansi_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST ansi_spec_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_inner:
			{
				AST tmp1371_AST = null;
				tmp1371_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1371_AST);
				match(LITERAL_inner);
				break;
			}
			case LITERAL_left:
			case LITERAL_right:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_left:
				{
					AST tmp1372_AST = null;
					tmp1372_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1372_AST);
					match(LITERAL_left);
					break;
				}
				case LITERAL_right:
				{
					AST tmp1373_AST = null;
					tmp1373_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1373_AST);
					match(LITERAL_right);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_outer:
				{
					AST tmp1374_AST = null;
					tmp1374_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1374_AST);
					match(LITERAL_outer);
					break;
				}
				case LITERAL_join:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case LITERAL_join:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1375_AST = null;
			tmp1375_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1375_AST);
			match(LITERAL_join);
			selected_table();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1376_AST = null;
			tmp1376_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1376_AST);
			match(LITERAL_on);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ANSI_JOIN_TAB_SPEC);
			}
			ansi_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_260);
			} else {
			  throw ex;
			}
		}
		returnAST = ansi_spec_AST;
	}
	
	public void table_reference_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_reference_list_AST = null;
		
		try {      // for error handling
			selected_table();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1140:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					selected_table();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1140;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_REFERENCE_LIST);
			}
			table_reference_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_208);
			} else {
			  throw ex;
			}
		}
		returnAST = table_reference_list_AST;
	}
	
	public void call_argument() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST call_argument_AST = null;
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==PASS_BY_NAME)) {
				parameter_name_ref();
				astFactory.addASTChild(currentAST, returnAST);
				match(PASS_BY_NAME);
			}
			else if ((_tokenSet_55.member(LA(1))) && (_tokenSet_261.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(CALL_ARGUMENT);
			}
			call_argument_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = call_argument_AST;
	}
	
	public void parameter_name_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parameter_name_ref_AST = null;
		
		try {      // for error handling
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PARAMETER_REF);
			}
			parameter_name_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_262);
			} else {
			  throw ex;
			}
		}
		returnAST = parameter_name_ref_AST;
	}
	
	public void table_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_ref_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_REF);
			}
			table_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = table_ref_AST;
	}
	
	public void alias_ident() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alias_ident_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(ALIAS_IDENT);
			}
			alias_ident_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_251);
			} else {
			  throw ex;
			}
		}
		returnAST = alias_ident_AST;
	}
	
	public void row_proc() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST row_proc_AST = null;
		
		try {      // for error handling
			match(LITERAL_table);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			case LITERAL_select:
			{
				select_command();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_cast:
			{
				cast_proc();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
				if ((_tokenSet_18.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN)) {
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==CLOSE_PAREN)) {
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_PAREN);
			row_proc_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_263);
			} else {
			  throw ex;
			}
		}
		returnAST = row_proc_AST;
	}
	
	public void the_proc() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST the_proc_AST = null;
		
		try {      // for error handling
			AST tmp1382_AST = null;
			tmp1382_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1382_AST);
			match(LITERAL_the);
			subquery();
			astFactory.addASTChild(currentAST, returnAST);
			the_proc_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_263);
			} else {
			  throw ex;
			}
		}
		returnAST = the_proc_AST;
	}
	
	public void from_subquery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST from_subquery_AST = null;
		
		try {      // for error handling
			subquery();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((_tokenSet_237.member(LA(1))) && (_tokenSet_257.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_258.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FROM_SUBQUERY);
			}
			from_subquery_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_258);
			} else {
			  throw ex;
			}
		}
		returnAST = from_subquery_AST;
	}
	
	public void table_alias() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_alias_AST = null;
		
		try {      // for error handling
			table_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((_tokenSet_237.member(LA(1))) && (_tokenSet_264.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_265.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_ALIAS);
			}
			table_alias_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_265);
			} else {
			  throw ex;
			}
		}
		returnAST = table_alias_AST;
	}
	
	public void from_plain_table() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST from_plain_table_AST = null;
		
		try {      // for error handling
			table_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((_tokenSet_237.member(LA(1)))) {
				alias();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==EOF)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FROM_PLAIN_TABLE);
			}
			from_plain_table_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = from_plain_table_AST;
	}
	
	public void table_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_spec_AST = null;
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1383_AST = null;
				tmp1383_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1383_AST);
				match(DOT);
			}
			else if ((_tokenSet_234.member(LA(1))) && (_tokenSet_266.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_name2();
			astFactory.addASTChild(currentAST, returnAST);
			table_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_266);
			} else {
			  throw ex;
			}
		}
		returnAST = table_spec_AST;
	}
	
	public void table_name2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_name2_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_NAME);
				}
				table_name2_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==TABLE_NAME_SPEC)) {
				AST tmp1384_AST = null;
				tmp1384_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1384_AST);
				match(TABLE_NAME_SPEC);
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_NAME_WITH_LINK);
				}
				table_name2_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_266);
			} else {
			  throw ex;
			}
		}
		returnAST = table_name2_AST;
	}
	
	public void link_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST link_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			link_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = link_name_AST;
	}
	
	public void connect_clause_internal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST connect_clause_internal_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_start:
			{
				{
				AST tmp1385_AST = null;
				tmp1385_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1385_AST);
				match(LITERAL_start);
				AST tmp1386_AST = null;
				tmp1386_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1386_AST);
				match(LITERAL_with);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				connect_clause_internal_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_connect:
			{
				{
				AST tmp1387_AST = null;
				tmp1387_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1387_AST);
				match(LITERAL_connect);
				AST tmp1388_AST = null;
				tmp1388_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1388_AST);
				match(LITERAL_by);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				connect_clause_internal_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_267);
			} else {
			  throw ex;
			}
		}
		returnAST = connect_clause_internal_AST;
	}
	
	public void sorted_def() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sorted_def_AST = null;
		Token  a = null;
		AST a_AST = null;
		Token  d = null;
		AST d_AST = null;
		
		try {      // for error handling
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_asc:
			case LITERAL_desc:
			{
				{
				switch ( LA(1)) {
				case LITERAL_asc:
				{
					a = LT(1);
					a_AST = astFactory.create(a);
					astFactory.addASTChild(currentAST, a_AST);
					match(LITERAL_asc);
					break;
				}
				case LITERAL_desc:
				{
					d = LT(1);
					d_AST = astFactory.create(d);
					astFactory.addASTChild(currentAST, d_AST);
					match(LITERAL_desc);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_nulls:
				{
					AST tmp1389_AST = null;
					tmp1389_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1389_AST);
					match(LITERAL_nulls);
					{
					switch ( LA(1)) {
					case LITERAL_first:
					{
						AST tmp1390_AST = null;
						tmp1390_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1390_AST);
						match(LITERAL_first);
						break;
					}
					case LITERAL_last:
					{
						AST tmp1391_AST = null;
						tmp1391_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1391_AST);
						match(LITERAL_last);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_package:
				case LITERAL_type:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_column:
				case LITERAL_set:
				case LITERAL_show:
				case LITERAL_var:
				case LITERAL_variable:
				case LITERAL_col:
				case LITERAL_exec:
				case LITERAL_execute:
				case LITERAL_whenever:
				case LITERAL_exit:
				case LITERAL_commit:
				case LITERAL_rollback:
				case LITERAL_def:
				case LITERAL_define:
				case LITERAL_prompt:
				case LITERAL_rem:
				case LITERAL_quit:
				case LITERAL_spool:
				case LITERAL_sta:
				case LITERAL_start:
				case LITERAL_repfooter:
				case LITERAL_repheader:
				case LITERAL_serveroutput:
				case LITERAL_begin:
				case LITERAL_declare:
				case LITERAL_create:
				case LITERAL_with:
				case LITERAL_using:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_for:
				case LITERAL_return:
				case LITERAL_select:
				case LITERAL_merge:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_minus:
				case LITERAL_returning:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_with:
			case LITERAL_using:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_for:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_minus:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(SORTED_DEF);
			}
			sorted_def_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_268);
			} else {
			  throw ex;
			}
		}
		returnAST = sorted_def_AST;
	}
	
	public void column_spec_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_spec_list_AST = null;
		
		try {      // for error handling
			column_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1236:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1236;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_SPEC_LIST);
			}
			column_spec_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = column_spec_list_AST;
	}
	
	public void variable_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variable_ref_AST = null;
		
		try {      // for error handling
			{
			_loop1281:
			do {
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp1393_AST = null;
					tmp1393_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1393_AST);
					match(DOT);
				}
				else {
					break _loop1281;
				}
				
			} while (true);
			}
			name_fragment();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_VAR_REF);
			}
			variable_ref_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_269);
			} else {
			  throw ex;
			}
		}
		returnAST = variable_ref_AST;
	}
	
	public void returning() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST returning_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_returning:
			{
				match(LITERAL_returning);
				break;
			}
			case LITERAL_return:
			{
				match(LITERAL_return);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1264:
			do {
				if ((LA(1)==COMMA)) {
					AST tmp1396_AST = null;
					tmp1396_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1396_AST);
					match(COMMA);
					identifier2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1264;
				}
				
			} while (true);
			}
			match(LITERAL_into);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1266:
			do {
				if ((LA(1)==COMMA)) {
					AST tmp1398_AST = null;
					tmp1398_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1398_AST);
					match(COMMA);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1266;
				}
				
			} while (true);
			}
			returning_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = returning_AST;
	}
	
	public void subquery_update() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subquery_update_AST = null;
		
		try {      // for error handling
			match(LITERAL_update);
			table_alias();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1400_AST = null;
			tmp1400_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1400_AST);
			match(LITERAL_set);
			match(OPEN_PAREN);
			column_spec_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			AST tmp1403_AST = null;
			tmp1403_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1403_AST);
			match(EQ);
			subquery();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(SUBQUERY_UPDATE_COMMAND);
			}
			subquery_update_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = subquery_update_AST;
	}
	
	public void simple_update() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST simple_update_AST = null;
		
		try {      // for error handling
			match(LITERAL_update);
			table_alias();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1405_AST = null;
			tmp1405_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1405_AST);
			match(LITERAL_set);
			column_spec();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1406_AST = null;
			tmp1406_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1406_AST);
			match(EQ);
			plsql_expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1258:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp1408_AST = null;
					tmp1408_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1408_AST);
					match(EQ);
					plsql_expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1258;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_returning:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_return:
			case LITERAL_returning:
			{
				returning();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(SIMPLE_UPDATE_COMMAND);
			}
			simple_update_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = simple_update_AST;
	}
	
	public void when_action() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST when_action_AST = null;
		
		try {      // for error handling
			AST tmp1409_AST = null;
			tmp1409_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1409_AST);
			match(LITERAL_when);
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp1410_AST = null;
				tmp1410_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1410_AST);
				match(LITERAL_not);
				break;
			}
			case LITERAL_matched:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_matched);
			match(LITERAL_then);
			{
			switch ( LA(1)) {
			case LITERAL_update:
			{
				{
				AST tmp1413_AST = null;
				tmp1413_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1413_AST);
				match(LITERAL_update);
				AST tmp1414_AST = null;
				tmp1414_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1414_AST);
				match(LITERAL_set);
				column_spec();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1415_AST = null;
				tmp1415_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1415_AST);
				match(EQ);
				plsql_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop1252:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_spec();
						astFactory.addASTChild(currentAST, returnAST);
						AST tmp1417_AST = null;
						tmp1417_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1417_AST);
						match(EQ);
						plsql_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop1252;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_insert:
			{
				insert_columns();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_package:
			case LITERAL_type:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_column:
			case LITERAL_set:
			case LITERAL_show:
			case LITERAL_var:
			case LITERAL_variable:
			case LITERAL_col:
			case LITERAL_exec:
			case LITERAL_execute:
			case LITERAL_whenever:
			case LITERAL_exit:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_def:
			case LITERAL_define:
			case LITERAL_prompt:
			case LITERAL_rem:
			case LITERAL_quit:
			case LITERAL_spool:
			case LITERAL_sta:
			case LITERAL_start:
			case LITERAL_repfooter:
			case LITERAL_repheader:
			case LITERAL_serveroutput:
			case LITERAL_begin:
			case LITERAL_declare:
			case LITERAL_create:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_when:
			case LITERAL_select:
			case LITERAL_merge:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(MERGE_WHEN_COMMAND);
			}
			when_action_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_270);
			} else {
			  throw ex;
			}
		}
		returnAST = when_action_AST;
	}
	
	public void insert_columns() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST insert_columns_AST = null;
		
		try {      // for error handling
			match(LITERAL_insert);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				column_spec_list();
				astFactory.addASTChild(currentAST, returnAST);
				match(CLOSE_PAREN);
				break;
			}
			case LITERAL_values:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_values);
			parentesized_plsql_exp_list();
			astFactory.addASTChild(currentAST, returnAST);
			insert_columns_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_271);
			} else {
			  throw ex;
			}
		}
		returnAST = insert_columns_AST;
	}
	
	public void lock_mode() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lock_mode_AST = null;
		Token  r1 = null;
		AST r1_AST = null;
		Token  s1 = null;
		AST s1_AST = null;
		Token  r2 = null;
		AST r2_AST = null;
		Token  e1 = null;
		AST e1_AST = null;
		Token  s2 = null;
		AST s2_AST = null;
		Token  u = null;
		AST u_AST = null;
		Token  s3 = null;
		AST s3_AST = null;
		Token  s4 = null;
		AST s4_AST = null;
		Token  r3 = null;
		AST r3_AST = null;
		Token  e2 = null;
		AST e2_AST = null;
		Token  e3 = null;
		AST e3_AST = null;
		
		try {      // for error handling
			if ((LA(1)==LITERAL_row) && (LA(2)==LITERAL_share)) {
				r1 = LT(1);
				r1_AST = astFactory.create(r1);
				astFactory.addASTChild(currentAST, r1_AST);
				match(LITERAL_row);
				s1 = LT(1);
				s1_AST = astFactory.create(s1);
				astFactory.addASTChild(currentAST, s1_AST);
				match(LITERAL_share);
				lock_mode_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_row) && (LA(2)==LITERAL_exclusive)) {
				r2 = LT(1);
				r2_AST = astFactory.create(r2);
				astFactory.addASTChild(currentAST, r2_AST);
				match(LITERAL_row);
				e1 = LT(1);
				e1_AST = astFactory.create(e1);
				astFactory.addASTChild(currentAST, e1_AST);
				match(LITERAL_exclusive);
				lock_mode_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_update)) {
				s2 = LT(1);
				s2_AST = astFactory.create(s2);
				astFactory.addASTChild(currentAST, s2_AST);
				match(LITERAL_share);
				u = LT(1);
				u_AST = astFactory.create(u);
				astFactory.addASTChild(currentAST, u_AST);
				match(LITERAL_update);
				lock_mode_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_mode)) {
				s3 = LT(1);
				s3_AST = astFactory.create(s3);
				astFactory.addASTChild(currentAST, s3_AST);
				match(LITERAL_share);
				lock_mode_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_row)) {
				s4 = LT(1);
				s4_AST = astFactory.create(s4);
				astFactory.addASTChild(currentAST, s4_AST);
				match(LITERAL_share);
				r3 = LT(1);
				r3_AST = astFactory.create(r3);
				astFactory.addASTChild(currentAST, r3_AST);
				match(LITERAL_row);
				e2 = LT(1);
				e2_AST = astFactory.create(e2);
				astFactory.addASTChild(currentAST, e2_AST);
				match(LITERAL_exclusive);
				lock_mode_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==LITERAL_exclusive)) {
				e3 = LT(1);
				e3_AST = astFactory.create(e3);
				astFactory.addASTChild(currentAST, e3_AST);
				match(LITERAL_exclusive);
				lock_mode_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_272);
			} else {
			  throw ex;
			}
		}
		returnAST = lock_mode_AST;
	}
	
	public void savepoint_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST savepoint_name_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			savepoint_name_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = savepoint_name_AST;
	}
	
	public void oracle_loader_params() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST oracle_loader_params_AST = null;
		
		try {      // for error handling
			AST tmp1422_AST = null;
			tmp1422_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1422_AST);
			match(LITERAL_oracle_loader);
			directory_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_access:
			{
				access_parameters();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_location:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			oracle_loader_params_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_273);
			} else {
			  throw ex;
			}
		}
		returnAST = oracle_loader_params_AST;
	}
	
	public void oracle_datapump_params() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST oracle_datapump_params_AST = null;
		
		try {      // for error handling
			AST tmp1423_AST = null;
			tmp1423_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1423_AST);
			match(LITERAL_oracle_datapump);
			directory_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_access:
			{
				write_access_parameters();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_location:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			oracle_datapump_params_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_273);
			} else {
			  throw ex;
			}
		}
		returnAST = oracle_datapump_params_AST;
	}
	
	public void location() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST location_AST = null;
		
		try {      // for error handling
			AST tmp1424_AST = null;
			tmp1424_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1424_AST);
			match(LITERAL_location);
			AST tmp1425_AST = null;
			tmp1425_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1425_AST);
			match(OPEN_PAREN);
			{
			if ((LA(1)==IDENTIFIER||LA(1)==QUOTED_STR||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_274.member(LA(2))) && (_tokenSet_275.member(LA(3)))) {
				file_location_spec();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_275.member(LA(3)))) {
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop1437:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					{
					if ((LA(1)==IDENTIFIER||LA(1)==QUOTED_STR||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_274.member(LA(2))) && (_tokenSet_275.member(LA(3)))) {
						file_location_spec();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_275.member(LA(3)))) {
						string_literal();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
				}
				else {
					break _loop1437;
				}
				
			} while (true);
			}
			AST tmp1427_AST = null;
			tmp1427_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1427_AST);
			match(CLOSE_PAREN);
			location_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = location_AST;
	}
	
	public void directory_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST directory_spec_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_default:
			{
				AST tmp1428_AST = null;
				tmp1428_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1428_AST);
				match(LITERAL_default);
				break;
			}
			case LITERAL_directory:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1429_AST = null;
			tmp1429_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1429_AST);
			match(LITERAL_directory);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			directory_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_276);
			} else {
			  throw ex;
			}
		}
		returnAST = directory_spec_AST;
	}
	
	public void access_parameters() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST access_parameters_AST = null;
		
		try {      // for error handling
			AST tmp1430_AST = null;
			tmp1430_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1430_AST);
			match(LITERAL_access);
			AST tmp1431_AST = null;
			tmp1431_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1431_AST);
			match(LITERAL_parameters);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_records:
			{
				record_format_info();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_column:
			case LITERAL_fields:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_fields:
			{
				field_definitions();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_column:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_column:
			{
				column_transforms();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_PAREN);
			access_parameters_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_273);
			} else {
			  throw ex;
			}
		}
		returnAST = access_parameters_AST;
	}
	
	public void write_access_parameters() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST write_access_parameters_AST = null;
		
		try {      // for error handling
			AST tmp1434_AST = null;
			tmp1434_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1434_AST);
			match(LITERAL_access);
			AST tmp1435_AST = null;
			tmp1435_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1435_AST);
			match(LITERAL_parameters);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_nologfile:
			{
				AST tmp1437_AST = null;
				tmp1437_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1437_AST);
				match(LITERAL_nologfile);
				break;
			}
			case LITERAL_logfile:
			{
				{
				AST tmp1438_AST = null;
				tmp1438_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1438_AST);
				match(LITERAL_logfile);
				file_location_spec();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_compression:
			case LITERAL_encryption:
			case LITERAL_version:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_version:
			{
				AST tmp1439_AST = null;
				tmp1439_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1439_AST);
				match(LITERAL_version);
				{
				switch ( LA(1)) {
				case LITERAL_compatible:
				{
					AST tmp1440_AST = null;
					tmp1440_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1440_AST);
					match(LITERAL_compatible);
					break;
				}
				case LITERAL_latest:
				{
					AST tmp1441_AST = null;
					tmp1441_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1441_AST);
					match(LITERAL_latest);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_compression:
			case LITERAL_encryption:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_compression:
			{
				AST tmp1442_AST = null;
				tmp1442_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1442_AST);
				match(LITERAL_compression);
				{
				switch ( LA(1)) {
				case LITERAL_enabled:
				{
					AST tmp1443_AST = null;
					tmp1443_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1443_AST);
					match(LITERAL_enabled);
					break;
				}
				case LITERAL_disabled:
				{
					AST tmp1444_AST = null;
					tmp1444_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1444_AST);
					match(LITERAL_disabled);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_encryption:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_encryption:
			{
				AST tmp1445_AST = null;
				tmp1445_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1445_AST);
				match(LITERAL_encryption);
				{
				switch ( LA(1)) {
				case LITERAL_enabled:
				{
					AST tmp1446_AST = null;
					tmp1446_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1446_AST);
					match(LITERAL_enabled);
					break;
				}
				case LITERAL_disabled:
				{
					AST tmp1447_AST = null;
					tmp1447_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1447_AST);
					match(LITERAL_disabled);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_PAREN);
			write_access_parameters_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_273);
			} else {
			  throw ex;
			}
		}
		returnAST = write_access_parameters_AST;
	}
	
	public void file_location_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST file_location_spec_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp1449_AST = null;
				tmp1449_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1449_AST);
				match(COLON);
				break;
			}
			case QUOTED_STR:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			file_location_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_277);
			} else {
			  throw ex;
			}
		}
		returnAST = file_location_spec_AST;
	}
	
	public void record_format_info() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST record_format_info_AST = null;
		
		try {      // for error handling
			AST tmp1450_AST = null;
			tmp1450_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1450_AST);
			match(LITERAL_records);
			rec_format();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1326:
			do {
				if ((_tokenSet_278.member(LA(1)))) {
					rec_format_tail();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1326;
				}
				
			} while (true);
			}
			record_format_info_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_279);
			} else {
			  throw ex;
			}
		}
		returnAST = record_format_info_AST;
	}
	
	public void field_definitions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST field_definitions_AST = null;
		
		try {      // for error handling
			AST tmp1451_AST = null;
			tmp1451_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1451_AST);
			match(LITERAL_fields);
			{
			switch ( LA(1)) {
			case LITERAL_enclosed:
			case LITERAL_terminated:
			{
				delim_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_column:
			case LITERAL_reject:
			case LITERAL_missing:
			case LITERAL_lrtrim:
			case LITERAL_notrim:
			case LITERAL_ltrim:
			case LITERAL_rtrim:
			case LITERAL_ldrtrim:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_lrtrim:
			case LITERAL_notrim:
			case LITERAL_ltrim:
			case LITERAL_rtrim:
			case LITERAL_ldrtrim:
			{
				trim_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_column:
			case LITERAL_reject:
			case LITERAL_missing:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_missing:
			{
				AST tmp1452_AST = null;
				tmp1452_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1452_AST);
				match(LITERAL_missing);
				AST tmp1453_AST = null;
				tmp1453_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1453_AST);
				match(LITERAL_field);
				AST tmp1454_AST = null;
				tmp1454_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1454_AST);
				match(LITERAL_values);
				AST tmp1455_AST = null;
				tmp1455_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1455_AST);
				match(LITERAL_are);
				AST tmp1456_AST = null;
				tmp1456_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1456_AST);
				match(LITERAL_null);
				break;
			}
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_column:
			case LITERAL_reject:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_reject:
			{
				AST tmp1457_AST = null;
				tmp1457_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1457_AST);
				match(LITERAL_reject);
				AST tmp1458_AST = null;
				tmp1458_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1458_AST);
				match(LITERAL_rows);
				AST tmp1459_AST = null;
				tmp1459_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1459_AST);
				match(LITERAL_with);
				AST tmp1460_AST = null;
				tmp1460_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1460_AST);
				match(LITERAL_all);
				AST tmp1461_AST = null;
				tmp1461_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1461_AST);
				match(LITERAL_null);
				AST tmp1462_AST = null;
				tmp1462_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1462_AST);
				match(LITERAL_fields);
				break;
			}
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_column:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				field_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case CLOSE_PAREN:
			case LITERAL_column:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			field_definitions_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_280);
			} else {
			  throw ex;
			}
		}
		returnAST = field_definitions_AST;
	}
	
	public void column_transforms() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_transforms_AST = null;
		
		try {      // for error handling
			AST tmp1463_AST = null;
			tmp1463_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1463_AST);
			match(LITERAL_column);
			AST tmp1464_AST = null;
			tmp1464_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1464_AST);
			match(LITERAL_transforms);
			AST tmp1465_AST = null;
			tmp1465_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1465_AST);
			match(OPEN_PAREN);
			transform();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1359:
			do {
				if ((LA(1)==COMMA)) {
					AST tmp1466_AST = null;
					tmp1466_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1466_AST);
					match(COMMA);
					transform();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1359;
				}
				
			} while (true);
			}
			AST tmp1467_AST = null;
			tmp1467_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1467_AST);
			match(CLOSE_PAREN);
			column_transforms_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = column_transforms_AST;
	}
	
	public void rec_format() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rec_format_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_fixed:
			{
				{
				AST tmp1468_AST = null;
				tmp1468_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1468_AST);
				match(LITERAL_fixed);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				rec_format_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_variable:
			{
				{
				AST tmp1469_AST = null;
				tmp1469_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1469_AST);
				match(LITERAL_variable);
				numeric_literal();
				astFactory.addASTChild(currentAST, returnAST);
				}
				rec_format_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_delimited:
			{
				{
				AST tmp1470_AST = null;
				tmp1470_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1470_AST);
				match(LITERAL_delimited);
				AST tmp1471_AST = null;
				tmp1471_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1471_AST);
				match(LITERAL_by);
				{
				switch ( LA(1)) {
				case LITERAL_newline:
				{
					AST tmp1472_AST = null;
					tmp1472_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1472_AST);
					match(LITERAL_newline);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				rec_format_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_281);
			} else {
			  throw ex;
			}
		}
		returnAST = rec_format_AST;
	}
	
	public void rec_format_tail() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rec_format_tail_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_characterset:
			{
				{
				AST tmp1473_AST = null;
				tmp1473_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1473_AST);
				match(LITERAL_characterset);
				{
				switch ( LA(1)) {
				case QUOTED_STR:
				{
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_data:
			{
				{
				AST tmp1474_AST = null;
				tmp1474_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1474_AST);
				match(LITERAL_data);
				AST tmp1475_AST = null;
				tmp1475_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1475_AST);
				match(LITERAL_is);
				{
				switch ( LA(1)) {
				case LITERAL_big:
				{
					AST tmp1476_AST = null;
					tmp1476_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1476_AST);
					match(LITERAL_big);
					break;
				}
				case LITERAL_little:
				{
					AST tmp1477_AST = null;
					tmp1477_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1477_AST);
					match(LITERAL_little);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1478_AST = null;
				tmp1478_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1478_AST);
				match(LITERAL_endian);
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_byte:
			{
				{
				AST tmp1479_AST = null;
				tmp1479_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1479_AST);
				match(LITERAL_byte);
				AST tmp1480_AST = null;
				tmp1480_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1480_AST);
				match(LITERAL_order);
				AST tmp1481_AST = null;
				tmp1481_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1481_AST);
				match(LITERAL_mark);
				{
				switch ( LA(1)) {
				case LITERAL_check:
				{
					AST tmp1482_AST = null;
					tmp1482_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1482_AST);
					match(LITERAL_check);
					break;
				}
				case LITERAL_nocheck:
				{
					AST tmp1483_AST = null;
					tmp1483_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1483_AST);
					match(LITERAL_nocheck);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_string:
			{
				{
				AST tmp1484_AST = null;
				tmp1484_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1484_AST);
				match(LITERAL_string);
				AST tmp1485_AST = null;
				tmp1485_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1485_AST);
				match(LITERAL_sizes);
				AST tmp1486_AST = null;
				tmp1486_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1486_AST);
				match(LITERAL_in);
				{
				switch ( LA(1)) {
				case LITERAL_bytes:
				{
					AST tmp1487_AST = null;
					tmp1487_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1487_AST);
					match(LITERAL_bytes);
					break;
				}
				case LITERAL_characters:
				{
					AST tmp1488_AST = null;
					tmp1488_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1488_AST);
					match(LITERAL_characters);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_load:
			{
				{
				AST tmp1489_AST = null;
				tmp1489_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1489_AST);
				match(LITERAL_load);
				AST tmp1490_AST = null;
				tmp1490_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1490_AST);
				match(LITERAL_when);
				condition();
				astFactory.addASTChild(currentAST, returnAST);
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nobadfile:
			case LITERAL_badfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nobadfile:
				{
					AST tmp1491_AST = null;
					tmp1491_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1491_AST);
					match(LITERAL_nobadfile);
					break;
				}
				case LITERAL_badfile:
				{
					{
					AST tmp1492_AST = null;
					tmp1492_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1492_AST);
					match(LITERAL_badfile);
					file_location_spec();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nodiscardfile:
			case LITERAL_discardfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nodiscardfile:
				{
					AST tmp1493_AST = null;
					tmp1493_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1493_AST);
					match(LITERAL_nodiscardfile);
					break;
				}
				case LITERAL_discardfile:
				{
					{
					AST tmp1494_AST = null;
					tmp1494_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1494_AST);
					match(LITERAL_discardfile);
					file_location_spec();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_nologfile:
			case LITERAL_logfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nologfile:
				{
					AST tmp1495_AST = null;
					tmp1495_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1495_AST);
					match(LITERAL_nologfile);
					break;
				}
				case LITERAL_logfile:
				{
					{
					AST tmp1496_AST = null;
					tmp1496_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1496_AST);
					match(LITERAL_logfile);
					file_location_spec();
					astFactory.addASTChild(currentAST, returnAST);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_readsize:
			case LITERAL_skip:
			case LITERAL_data_cache:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_readsize:
				{
					AST tmp1497_AST = null;
					tmp1497_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1497_AST);
					match(LITERAL_readsize);
					break;
				}
				case LITERAL_data_cache:
				{
					AST tmp1498_AST = null;
					tmp1498_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1498_AST);
					match(LITERAL_data_cache);
					break;
				}
				case LITERAL_skip:
				{
					AST tmp1499_AST = null;
					tmp1499_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1499_AST);
					match(LITERAL_skip);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1500_AST = null;
				tmp1500_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1500_AST);
				match(NUMBER);
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_preprocessor:
			{
				{
				AST tmp1501_AST = null;
				tmp1501_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1501_AST);
				match(LITERAL_preprocessor);
				file_location_spec();
				astFactory.addASTChild(currentAST, returnAST);
				}
				rec_format_tail_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_281);
			} else {
			  throw ex;
			}
		}
		returnAST = rec_format_tail_AST;
	}
	
	public void delim_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST delim_spec_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_enclosed:
			{
				{
				AST tmp1502_AST = null;
				tmp1502_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1502_AST);
				match(LITERAL_enclosed);
				AST tmp1503_AST = null;
				tmp1503_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1503_AST);
				match(LITERAL_by);
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_and:
				{
					AST tmp1504_AST = null;
					tmp1504_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1504_AST);
					match(LITERAL_and);
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_column:
				case LITERAL_reject:
				case LITERAL_missing:
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_date_format:
				case LITERAL_ldrtrim:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				delim_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_terminated:
			{
				{
				AST tmp1505_AST = null;
				tmp1505_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1505_AST);
				match(LITERAL_terminated);
				AST tmp1506_AST = null;
				tmp1506_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1506_AST);
				match(LITERAL_by);
				{
				switch ( LA(1)) {
				case LITERAL_whitespace:
				{
					AST tmp1507_AST = null;
					tmp1507_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1507_AST);
					match(LITERAL_whitespace);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case DOUBLE_QUOTED_STRING:
				{
					AST tmp1508_AST = null;
					tmp1508_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1508_AST);
					match(DOUBLE_QUOTED_STRING);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_enclosed:
				case LITERAL_optionally:
				{
					{
					switch ( LA(1)) {
					case LITERAL_optionally:
					{
						AST tmp1509_AST = null;
						tmp1509_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1509_AST);
						match(LITERAL_optionally);
						break;
					}
					case LITERAL_enclosed:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp1510_AST = null;
					tmp1510_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1510_AST);
					match(LITERAL_enclosed);
					AST tmp1511_AST = null;
					tmp1511_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1511_AST);
					match(LITERAL_by);
					string_literal();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case LITERAL_and:
					{
						AST tmp1512_AST = null;
						tmp1512_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1512_AST);
						match(LITERAL_and);
						string_literal();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case COMMA:
					case OPEN_PAREN:
					case CLOSE_PAREN:
					case LITERAL_column:
					case LITERAL_reject:
					case LITERAL_missing:
					case LITERAL_lrtrim:
					case LITERAL_notrim:
					case LITERAL_ltrim:
					case LITERAL_rtrim:
					case LITERAL_date_format:
					case LITERAL_ldrtrim:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_column:
				case LITERAL_reject:
				case LITERAL_missing:
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_date_format:
				case LITERAL_ldrtrim:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				delim_spec_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_282);
			} else {
			  throw ex;
			}
		}
		returnAST = delim_spec_AST;
	}
	
	public void trim_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trim_spec_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_lrtrim:
			{
				AST tmp1513_AST = null;
				tmp1513_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1513_AST);
				match(LITERAL_lrtrim);
				trim_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_notrim:
			{
				AST tmp1514_AST = null;
				tmp1514_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1514_AST);
				match(LITERAL_notrim);
				trim_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_ltrim:
			{
				AST tmp1515_AST = null;
				tmp1515_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1515_AST);
				match(LITERAL_ltrim);
				trim_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_rtrim:
			{
				AST tmp1516_AST = null;
				tmp1516_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1516_AST);
				match(LITERAL_rtrim);
				trim_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_ldrtrim:
			{
				AST tmp1517_AST = null;
				tmp1517_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1517_AST);
				match(LITERAL_ldrtrim);
				trim_spec_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_283);
			} else {
			  throw ex;
			}
		}
		returnAST = trim_spec_AST;
	}
	
	public void field_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST field_list_AST = null;
		
		try {      // for error handling
			match(OPEN_PAREN);
			field_spec();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop1391:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					field_spec();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop1391;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			field_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_280);
			} else {
			  throw ex;
			}
		}
		returnAST = field_list_AST;
	}
	
	public void transform() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST transform_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1521_AST = null;
			tmp1521_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1521_AST);
			match(LITERAL_from);
			{
			switch ( LA(1)) {
			case LITERAL_null:
			{
				AST tmp1522_AST = null;
				tmp1522_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1522_AST);
				match(LITERAL_null);
				break;
			}
			case LITERAL_constant:
			{
				const_str();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_concat:
			{
				{
				AST tmp1523_AST = null;
				tmp1523_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1523_AST);
				match(LITERAL_concat);
				match(OPEN_PAREN);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					field_name();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_constant:
				{
					const_str();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop1366:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						switch ( LA(1)) {
						case IDENTIFIER:
						case DOUBLE_QUOTED_STRING:
						{
							field_name();
							astFactory.addASTChild(currentAST, returnAST);
							break;
						}
						case LITERAL_constant:
						{
							const_str();
							astFactory.addASTChild(currentAST, returnAST);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
					}
					else {
						break _loop1366;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				break;
			}
			case LITERAL_lobfile:
			{
				{
				AST tmp1527_AST = null;
				tmp1527_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1527_AST);
				match(LITERAL_lobfile);
				match(OPEN_PAREN);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					field_name();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_constant:
				{
					{
					const_str();
					astFactory.addASTChild(currentAST, returnAST);
					match(COLON);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop1373:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						switch ( LA(1)) {
						case IDENTIFIER:
						case DOUBLE_QUOTED_STRING:
						{
							field_name();
							astFactory.addASTChild(currentAST, returnAST);
							break;
						}
						case LITERAL_constant:
						{
							{
							const_str();
							astFactory.addASTChild(currentAST, returnAST);
							match(COLON);
							}
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
					}
					else {
						break _loop1373;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_blob:
				case LITERAL_clob:
				case LITERAL_from:
				{
					lobfile_attr_list();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			transform_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = transform_AST;
	}
	
	public void const_str() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST const_str_AST = null;
		
		try {      // for error handling
			AST tmp1533_AST = null;
			tmp1533_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1533_AST);
			match(LITERAL_constant);
			string_literal();
			astFactory.addASTChild(currentAST, returnAST);
			const_str_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_284);
			} else {
			  throw ex;
			}
		}
		returnAST = const_str_AST;
	}
	
	public void lobfile_attr_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lobfile_attr_list_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_from:
			{
				{
				AST tmp1534_AST = null;
				tmp1534_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1534_AST);
				match(LITERAL_from);
				AST tmp1535_AST = null;
				tmp1535_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1535_AST);
				match(OPEN_PAREN);
				identifier2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop1378:
				do {
					if ((LA(1)==COMMA)) {
						AST tmp1536_AST = null;
						tmp1536_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1536_AST);
						match(COMMA);
						identifier2();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop1378;
					}
					
				} while (true);
				}
				AST tmp1537_AST = null;
				tmp1537_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1537_AST);
				match(CLOSE_PAREN);
				}
				lobfile_attr_list_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_clob:
			{
				AST tmp1538_AST = null;
				tmp1538_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1538_AST);
				match(LITERAL_clob);
				lobfile_attr_list_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_blob:
			{
				AST tmp1539_AST = null;
				tmp1539_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1539_AST);
				match(LITERAL_blob);
				lobfile_attr_list_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = lobfile_attr_list_AST;
	}
	
	public void field_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST field_spec_AST = null;
		
		try {      // for error handling
			identifier2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_position:
			{
				pos_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_char:
			case LITERAL_raw:
			case LITERAL_integer:
			case LITERAL_double:
			case LITERAL_float:
			case LITERAL_decimal:
			case LITERAL_varchar:
			case LITERAL_varraw:
			case LITERAL_varcharc:
			case LITERAL_varrawc:
			case LITERAL_oracle_number:
			case LITERAL_oracle_date:
			case LITERAL_zoned:
			case LITERAL_unsigned:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_char:
			case LITERAL_raw:
			case LITERAL_integer:
			case LITERAL_double:
			case LITERAL_float:
			case LITERAL_decimal:
			case LITERAL_varchar:
			case LITERAL_varraw:
			case LITERAL_varcharc:
			case LITERAL_varrawc:
			case LITERAL_oracle_number:
			case LITERAL_oracle_date:
			case LITERAL_zoned:
			case LITERAL_unsigned:
			{
				datatype_spec();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			field_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = field_spec_AST;
	}
	
	public void pos_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST pos_spec_AST = null;
		
		try {      // for error handling
			match(LITERAL_position);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case ASTERISK:
			{
				AST tmp1542_AST = null;
				tmp1542_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1542_AST);
				match(ASTERISK);
				break;
			}
			case PLUS:
			case MINUS:
			case NUMBER:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case PLUS:
			{
				AST tmp1543_AST = null;
				tmp1543_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1543_AST);
				match(PLUS);
				break;
			}
			case MINUS:
			{
				AST tmp1544_AST = null;
				tmp1544_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1544_AST);
				match(MINUS);
				break;
			}
			case NUMBER:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			numeric_literal();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1545_AST = null;
			tmp1545_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1545_AST);
			match(COLON);
			{
			switch ( LA(1)) {
			case PLUS:
			{
				AST tmp1546_AST = null;
				tmp1546_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1546_AST);
				match(PLUS);
				break;
			}
			case NUMBER:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			numeric_literal();
			astFactory.addASTChild(currentAST, returnAST);
			match(CLOSE_PAREN);
			pos_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_285);
			} else {
			  throw ex;
			}
		}
		returnAST = pos_spec_AST;
	}
	
	public void datatype_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datatype_spec_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_integer:
			case LITERAL_unsigned:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_unsigned:
				{
					AST tmp1548_AST = null;
					tmp1548_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1548_AST);
					match(LITERAL_unsigned);
					break;
				}
				case LITERAL_integer:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp1549_AST = null;
				tmp1549_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1549_AST);
				match(LITERAL_integer);
				{
				switch ( LA(1)) {
				case LITERAL_external:
				{
					AST tmp1550_AST = null;
					tmp1550_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1550_AST);
					match(LITERAL_external);
					break;
				}
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					AST tmp1551_AST = null;
					tmp1551_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1551_AST);
					match(OPEN_PAREN);
					AST tmp1552_AST = null;
					tmp1552_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1552_AST);
					match(NUMBER);
					AST tmp1553_AST = null;
					tmp1553_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1553_AST);
					match(CLOSE_PAREN);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					delim_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_decimal:
			case LITERAL_zoned:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_decimal:
				{
					AST tmp1554_AST = null;
					tmp1554_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1554_AST);
					match(LITERAL_decimal);
					break;
				}
				case LITERAL_zoned:
				{
					AST tmp1555_AST = null;
					tmp1555_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1555_AST);
					match(LITERAL_zoned);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_external:
				{
					{
					AST tmp1556_AST = null;
					tmp1556_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1556_AST);
					match(LITERAL_external);
					}
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						AST tmp1557_AST = null;
						tmp1557_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1557_AST);
						match(OPEN_PAREN);
						AST tmp1558_AST = null;
						tmp1558_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1558_AST);
						match(NUMBER);
						AST tmp1559_AST = null;
						tmp1559_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1559_AST);
						match(CLOSE_PAREN);
						break;
					}
					case COMMA:
					case CLOSE_PAREN:
					case LITERAL_enclosed:
					case LITERAL_terminated:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					{
					switch ( LA(1)) {
					case LITERAL_enclosed:
					case LITERAL_terminated:
					{
						delim_spec();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case COMMA:
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case OPEN_PAREN:
				{
					{
					AST tmp1560_AST = null;
					tmp1560_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1560_AST);
					match(OPEN_PAREN);
					AST tmp1561_AST = null;
					tmp1561_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1561_AST);
					match(NUMBER);
					AST tmp1562_AST = null;
					tmp1562_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1562_AST);
					match(CLOSE_PAREN);
					}
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_oracle_date:
			{
				AST tmp1563_AST = null;
				tmp1563_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1563_AST);
				match(LITERAL_oracle_date);
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_oracle_number:
			{
				{
				AST tmp1564_AST = null;
				tmp1564_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1564_AST);
				match(LITERAL_oracle_number);
				{
				switch ( LA(1)) {
				case LITERAL_counted:
				{
					AST tmp1565_AST = null;
					tmp1565_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1565_AST);
					match(LITERAL_counted);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_float:
			{
				{
				AST tmp1566_AST = null;
				tmp1566_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1566_AST);
				match(LITERAL_float);
				{
				switch ( LA(1)) {
				case LITERAL_external:
				{
					AST tmp1567_AST = null;
					tmp1567_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1567_AST);
					match(LITERAL_external);
					break;
				}
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					AST tmp1568_AST = null;
					tmp1568_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1568_AST);
					match(OPEN_PAREN);
					AST tmp1569_AST = null;
					tmp1569_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1569_AST);
					match(NUMBER);
					AST tmp1570_AST = null;
					tmp1570_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1570_AST);
					match(CLOSE_PAREN);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					delim_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_double:
			{
				AST tmp1571_AST = null;
				tmp1571_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1571_AST);
				match(LITERAL_double);
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_raw:
			{
				{
				AST tmp1572_AST = null;
				tmp1572_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1572_AST);
				match(LITERAL_raw);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					AST tmp1573_AST = null;
					tmp1573_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1573_AST);
					match(OPEN_PAREN);
					AST tmp1574_AST = null;
					tmp1574_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1574_AST);
					match(NUMBER);
					AST tmp1575_AST = null;
					tmp1575_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1575_AST);
					match(CLOSE_PAREN);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_char:
			{
				{
				AST tmp1576_AST = null;
				tmp1576_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1576_AST);
				match(LITERAL_char);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					AST tmp1577_AST = null;
					tmp1577_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1577_AST);
					match(OPEN_PAREN);
					AST tmp1578_AST = null;
					tmp1578_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1578_AST);
					match(NUMBER);
					AST tmp1579_AST = null;
					tmp1579_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1579_AST);
					match(CLOSE_PAREN);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_enclosed:
				case LITERAL_date_format:
				case LITERAL_terminated:
				case LITERAL_ldrtrim:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_enclosed:
				case LITERAL_terminated:
				{
					delim_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_date_format:
				case LITERAL_ldrtrim:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_ldrtrim:
				{
					trim_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				case LITERAL_date_format:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LITERAL_date_format:
				{
					date_format_spec();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_varchar:
			case LITERAL_varraw:
			case LITERAL_varcharc:
			case LITERAL_varrawc:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_varchar:
				{
					AST tmp1580_AST = null;
					tmp1580_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1580_AST);
					match(LITERAL_varchar);
					break;
				}
				case LITERAL_varraw:
				{
					AST tmp1581_AST = null;
					tmp1581_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1581_AST);
					match(LITERAL_varraw);
					break;
				}
				case LITERAL_varcharc:
				{
					AST tmp1582_AST = null;
					tmp1582_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1582_AST);
					match(LITERAL_varcharc);
					break;
				}
				case LITERAL_varrawc:
				{
					AST tmp1583_AST = null;
					tmp1583_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1583_AST);
					match(LITERAL_varrawc);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					AST tmp1584_AST = null;
					tmp1584_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1584_AST);
					match(OPEN_PAREN);
					AST tmp1585_AST = null;
					tmp1585_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1585_AST);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case COMMA:
					{
						AST tmp1586_AST = null;
						tmp1586_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1586_AST);
						match(COMMA);
						AST tmp1587_AST = null;
						tmp1587_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp1587_AST);
						match(NUMBER);
						break;
					}
					case CLOSE_PAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp1588_AST = null;
					tmp1588_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp1588_AST);
					match(CLOSE_PAREN);
					break;
				}
				case COMMA:
				case CLOSE_PAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				datatype_spec_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = datatype_spec_AST;
	}
	
	public void date_format_spec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST date_format_spec_AST = null;
		
		try {      // for error handling
			AST tmp1589_AST = null;
			tmp1589_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1589_AST);
			match(LITERAL_date_format);
			{
			switch ( LA(1)) {
			case LITERAL_timestamp:
			{
				AST tmp1590_AST = null;
				tmp1590_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1590_AST);
				match(LITERAL_timestamp);
				break;
			}
			case LITERAL_date:
			{
				AST tmp1591_AST = null;
				tmp1591_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1591_AST);
				match(LITERAL_date);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_with:
			{
				AST tmp1592_AST = null;
				tmp1592_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1592_AST);
				match(LITERAL_with);
				AST tmp1593_AST = null;
				tmp1593_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1593_AST);
				match(LITERAL_timezone);
				break;
			}
			case LITERAL_mask:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp1594_AST = null;
			tmp1594_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1594_AST);
			match(LITERAL_mask);
			{
			switch ( LA(1)) {
			case QUOTED_STR:
			{
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				AST tmp1595_AST = null;
				tmp1595_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1595_AST);
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			date_format_spec_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = date_format_spec_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"BAD_ML_COMMENT",
		"BAD_CHARACTER",
		"BAD_CHAR_LITERAL",
		"BAD_NUMBER_LITERAL",
		"STORAGE_SIZE",
		"LT",
		"LE",
		"GE",
		"NOT_EQ",
		"CUSTOM_TOKEN",
		"COLON_NEW",
		"COLON_OLD",
		"TABLE_NAME_SPEC",
		"IDENTIFIER",
		"PLSQL_ENV_VAR",
		"ANY_CHARACTER",
		"QUOTED_STR",
		"DOUBLE_QUOTED_STRING",
		"AT_PREFIXED",
		"SEMI",
		"COLON",
		"DOT",
		"COMMA",
		"ASTERISK",
		"OPEN_PAREN",
		"CLOSE_PAREN",
		"PLUS",
		"MINUS",
		"DIVIDE",
		"BACKSLASH",
		"EQ",
		"PERCENTAGE",
		"DOUBLEDOT",
		"CONCAT",
		"START_LABEL",
		"DOLLAR",
		"END_LABEL",
		"ASSIGNMENT_EQ",
		"PASS_BY_NAME",
		"VERTBAR",
		"GT",
		"NUMBER",
		"N",
		"WS",
		"LF",
		"SL_COMMENT",
		"ML_COMMENT",
		"IF_COND_CMPL",
		"THEN_COND_CMPL",
		"ELSE_COND_CMPL",
		"END_COND_CMPL",
		"ERROR_COND_CMPL",
		"START_RULE",
		"ERROR_TOKEN_A",
		"PACKAGE_SPEC",
		"PACKAGE_BODY",
		"PACKAGE_NAME",
		"PACKAGE_OBJ_BODY",
		"RECORD_TYPE_DECL",
		"SELECT_EXPRESSION",
		"SELECT_EXPRESSION_UNION",
		"PLSQL_BLOCK",
		"CURSOR_DECLARATION",
		"VARIABLE_DECLARATION",
		"PROCEDURE_BODY",
		"FUNCTION_BODY",
		"PARAMETER_SPEC",
		"SQL_STATEMENT",
		"IF_STATEMENT",
		"LOOP_STATEMENT",
		"STATEMENT",
		"SELECT_LIST",
		"TABLE_REFERENCE_LIST",
		"TABLE_REFERENCE_LIST_FROM",
		"WHERE_CONDITION",
		"SUBQUERY",
		"SQL_IDENTIFIER",
		"FUNCTION",
		"GROUP_FUNCTION",
		"USER_FUNCTION",
		"MULTIPLY",
		"ARGUMENT",
		"ARGUMENT_LIST",
		"FIELD_NAME",
		"PLSQL_EXPR_LIST",
		"EXPR_LIST",
		"DECLARE_LIST",
		"FUNCTION_CALL",
		"PACKAGE_INIT_SECTION",
		"CALL_ARGUMENT_LIST",
		"CALL_ARGUMENT",
		"BASE_EXRESSION",
		"COLUMN_SPEC",
		"LOGICAL_AND",
		"LOGICAL_OR",
		"CASE_EXPRESSION_SMPL",
		"CASE_EXPRESSION_SRCH",
		"CASE_STATEMENT",
		"COUNT_FUNC",
		"TRIM_FUNC",
		"COLUMN_SPEC_LIST",
		"INSERT_COMMAND",
		"UPDATE_COMMAND",
		"DELETE_COMMAND",
		"MERGE_COMMAND",
		"MERGE_WHEN_COMMAND",
		"STRING_LITERAL",
		"NUMERIC_LITERAL",
		"BOOLEAN_LITERAL",
		"RETURN_TYPE",
		"TYPE_SPEC",
		"VARIABLE_NAME",
		"COLUMN_OUTER_JOIN",
		"LOGICAL_FACTOR",
		"TABLE_ALIAS",
		"CAST_EXPR",
		"VAR_REF",
		"PLSQL_VAR_REF",
		"PARAMETER_REF",
		"EXCEPTION_SECTION",
		"SELECTED_TABLE",
		"CREATE_PROCEDURE",
		"CREATE_FUNCTION",
		"COMMIT_STATEMENT",
		"ROLLBACK_STATEMENT",
		"NULL_STATEMENT",
		"ASSIGNMENT_STATEMENT",
		"PROCEDURE_CALL",
		"RETURN_STATEMENT",
		"OBJECT_NAME",
		"PARAMETER_NAME",
		"DEFAULT",
		"THEN_STATEMENT",
		"ELSIF_STATEMENT",
		"ELSE_STATEMENT",
		"STATEMENT_LIST",
		"RELATION_CONDITION",
		"ISNULL_CONDITION",
		"LIKE_CONDITION",
		"BETWEEN_CONDITION",
		"IN_CONDITION",
		"PARENTHESIZED_EXPR",
		"ORDER_CLAUSE",
		"CONNECT_CLAUSE",
		"GROUP_CLAUSE",
		"INTO_CLAUSE",
		"FOR_UPDATE_CLAUSE",
		"ASTERISK1",
		"ROWCOUNT_EXRESSION",
		"MULTIPLICATION_OP",
		"DIVIDE_OP",
		"PLUS_OP",
		"MINUS_OP",
		"CONCAT_OP",
		"CURSOR_NAME",
		"DATATYPE",
		"EXTRACT_DATE_FUNC",
		"ANSI_JOIN_TAB_SPEC",
		"INTERVAL_DAY_TO_SEC_EXPR",
		"INTERVAL_CONST",
		"USER_CONST",
		"SYSDATE_CONST",
		"SYSTIMESTAMP_CONST",
		"CURRENT_TIMESTAMP_CONST",
		"ALIAS_NAME",
		"EXISTS_EXPR",
		"SUBQUERY_EXPR",
		"TYPE_NAME_REF",
		"TYPE_NAME",
		"SORTED_DEF",
		"RAISE_STATEMENT",
		"PACKAGE_OBJ_SPEC",
		"PROCEDURE_SPEC",
		"SIMPLE_UPDATE_COMMAND",
		"SUBQUERY_UPDATE_COMMAND",
		"IMMEDIATE_COMMAND",
		"FUNCTION_SPEC",
		"NEGATE_FACTOR",
		"PRAGMA",
		"TABLE_TYPE_REF",
		"COLUMN_TYPE_REF",
		"STATEMENT_ANNOT",
		"ASTERISK_COLUMN",
		"IDENT_ASTERISK_COLUMN",
		"EXPR_COLUMN",
		"TABLE_NAME",
		"TABLE_NAME_WITH_LINK",
		"FROM_SUBQUERY",
		"FROM_PLAIN_TABLE",
		"SCHEMA_NAME",
		"TABLE_REF",
		"COLUMN_NAME_REF",
		"ARITHMETIC_EXPR",
		"ASSIGNMENT_OP",
		"DBTIMEZONE",
		"SUBQUERY_CONDITION",
		"RECORD_ITEM",
		"EXCEPTION_DECL",
		"EXCEPTION_PRAGMA",
		"COMPLEX_NAME",
		"RESTRICT_REF_PRAGMA",
		"CHARACTER_SET",
		"AUTHID",
		"FIPSFLAG_PRAGMA",
		"BUILTIN_PRAGMA",
		"INTERFACE_PRAGMA",
		"TIMESTAMPG_PRAGMA",
		"TIMESTAMP_CONST",
		"SUBTYPE_DECL",
		"MEMBER_OF",
		"SQLPLUS_SET",
		"SQLPLUS_COMMAND",
		"SQLPLUS_RUNSCRIPT",
		"SQLPLUS_SHOW",
		"SQLPLUS_DEFINE",
		"SQLPLUS_VARIABLE",
		"SQLPLUS_EXEC",
		"SQLPLUS_WHENEVER",
		"SQLPLUS_PROMPT",
		"SQLPLUS_COLUMN",
		"SQLPLUS_START",
		"SQLPLUS_SERVEROUTPUT",
		"SQLPLUS_REPFOOTER",
		"SQLPLUS_REPHEADER",
		"SQLPLUS_EXIT",
		"SQLPLUS_QUIT",
		"SQLPLUS_SPOOL",
		"OR_LOGICAL",
		"AND_LOGICAL",
		"NOT_LOGICAL",
		"LOGICAL_EXPR",
		"RELATION_OP",
		"SEQUENCE_EXPR",
		"ALIAS_IDENT",
		"COLUMN_NAME_DDL",
		"COLUMN_DEF",
		"TABLE_DEF",
		"TABLE_COLLECTION",
		"VARRAY_COLLECTION",
		"REF_CURSOR",
		"OBJECT_TYPE_DEF",
		"AT_TIME_ZONE_EXPR",
		"TIMEZONE_SPEC",
		"ALTER_TABLE",
		"CREATE_TEMP_TABLE",
		"COMMENT",
		"COMMENT_STR",
		"CREATE_INDEX",
		"INSERT_INTO_SUBQUERY_COMMAND",
		"CONDITIONAL_COMPILATION",
		"COND_COMP_SEQ",
		"COND_COMP_SEQ2",
		"COND_COMP_ERROR",
		"COLUMN_NAME_LIST",
		"SERIALLY_REUSABLE_PRAGMA",
		"CREATE_VIEW",
		"VIRTUAL_TABLE",
		"ROWNUM",
		"CUSTOM_AGGR_FUNCTION",
		"LAST_STMT_RESULT_NUM",
		"LAST_STMT_RESULT_BOOL",
		"CURRENT_OF_CLAUSE",
		"CURSOR_STATE",
		"SQLCODE_SYSVAR",
		"SQLERRM_SYSVAR",
		"COLLECTION_METHOD_CALL",
		"C_RECORD_ITEM_REF",
		"CALLABLE_NAME_REF",
		"TRUNCATE_TABLE",
		"DROP_VIEW",
		"DROP_TABLE",
		"DROP_FUNCTION",
		"DROP_PROCEDURE",
		"DROP_PACKAGE",
		"DROP_INDEX",
		"DROP_SEQUENCE",
		"DROP_TYPE",
		"DROP_OPERATOR",
		"DROP_SYNONYM",
		"DROP_USER",
		"DROP_ROLE",
		"DROP_LIBRARY",
		"DROP_DB_LINK",
		"DROP_DIRECTORY",
		"COLUMN_PK_SPEC",
		"COLUMN_FK_SPEC",
		"NOT_NULL_STMT",
		"CONSTRAINT",
		"PK_SPEC",
		"FK_SPEC",
		"UNIQUE_CONSTRAINT",
		"CONSTRAINT_NAME",
		"ADD_CONSTRAINT",
		"ADD_PRIMARY_KEY",
		"IOT_TYPE",
		"HEAP_ORGINIZED",
		"EXTERNAL_TYPE",
		"NAME_FRAGMENT",
		"COLLECTION_METHOD_NAME",
		"V_COLUMN_DEF",
		"TABLE_NAME_DDL",
		"VIEW_NAME",
		"INDEX_NAME",
		"VIEW_NAME_DDL",
		"CREATE_TRIGGER",
		"CREATE_DIRECTORY",
		"CREATE_DB_LINK",
		"CREATE_SEQUENCE",
		"TRIGGER_FOR_EACH",
		"TRIGGER_NAME",
		"ALTER_TRIGGER",
		"DB_EVNT_TRIGGER_CLAUSE",
		"DDL_TRIGGER_CLAUSE",
		"DML_TRIGGER_CLAUSE",
		"TRIGGER_COLUMN_REF",
		"INSTEADOF_TRIGGER",
		"TRIGGER_TARGET",
		"NUMERIC_LOOP_SPEC",
		"FORALL_LOOP_SPEC",
		"CURSOR_REF_LOOP_SPEC",
		"CURSOR_IMPL_LOOP_SPEC",
		"LOOP_INDEX",
		"RECORD_ITEM_NAME",
		"GOTO_STATEMENT",
		"EXIT_STATEMENT",
		"LABEL_NAME",
		"\"alter\"",
		"\"drop\"",
		"\"table\"",
		"\"purge\"",
		"\"view\"",
		"\"cascade\"",
		"\"constraints\"",
		"\"function\"",
		"\"procedure\"",
		"\"package\"",
		"\"body\"",
		"\"index\"",
		"\"force\"",
		"\"sequence\"",
		"\"type\"",
		"\"validate\"",
		"\"public\"",
		"\"synonym\"",
		"\"operator\"",
		"\"user\"",
		"\"role\"",
		"\"directory\"",
		"\"library\"",
		"\"database\"",
		"\"link\"",
		"\"truncate\"",
		"\"comment\"",
		"\"on\"",
		"\"is\"",
		"\"column\"",
		"\"default\"",
		"\"sysdate\"",
		"\"not\"",
		"\"null\"",
		"\"disable\"",
		"\"enable\"",
		"\"row\"",
		"\"movement\"",
		"\"primary\"",
		"\"key\"",
		"\"references\"",
		"\"constraint\"",
		"\"set\"",
		"\"long\"",
		"\"show\"",
		"\"var\"",
		"\"variable\"",
		"\"col\"",
		"\"exec\"",
		"\"execute\"",
		"\"whenever\"",
		"\"sqlerror\"",
		"\"oserror\"",
		"\"exit\"",
		"\"continue\"",
		"\"commit\"",
		"\"rollback\"",
		"\"none\"",
		"\"def\"",
		"\"define\"",
		"\"prompt\"",
		"\"rem\"",
		"\"quit\"",
		"\"spool\"",
		"\"off\"",
		"\"sta\"",
		"\"start\"",
		"\"repfooter\"",
		"\"repheader\"",
		"\"serveroutput\"",
		"\"begin\"",
		"\"declare\"",
		"DOUBLE_DOT",
		"\"create\"",
		"\"or\"",
		"\"replace\"",
		"\"maxvalue\"",
		"\"minvalue\"",
		"\"cycle\"",
		"\"nocycle\"",
		"\"cache\"",
		"\"nocache\"",
		"\"increment\"",
		"\"by\"",
		"\"with\"",
		"\"order\"",
		"\"noorder\"",
		"\"connect\"",
		"\"to\"",
		"\"identified\"",
		"\"using\"",
		"\"trigger\"",
		"\"after\"",
		"\"before\"",
		"\"startup\"",
		"\"shutdown\"",
		"\"servererror\"",
		"\"logon\"",
		"\"logoff\"",
		"\"analyze\"",
		"\"associate\"",
		"\"statistics\"",
		"\"audit\"",
		"\"noaudit\"",
		"\"ddl\"",
		"\"diassociate\"",
		"\"grant\"",
		"\"rename\"",
		"\"revoke\"",
		"\"schema\"",
		"\"instead\"",
		"\"of\"",
		"\"delete\"",
		"\"insert\"",
		"\"update\"",
		"\"for\"",
		"\"each\"",
		"\"referencing\"",
		"\"old\"",
		"\"as\"",
		"\"new\"",
		"\"when\"",
		"\"unique\"",
		"\"bitmap\"",
		"\"asc\"",
		"\"desc\"",
		"\"global\"",
		"\"temporary\"",
		"\"preserve\"",
		"\"rows\"",
		"\"tablespace\"",
		"\"online\"",
		"\"compute\"",
		"\"parallel\"",
		"\"noparallel\"",
		"\"logging\"",
		"\"nologging\"",
		"\"filesystem_like_logging\"",
		"\"compress\"",
		"\"all\"",
		"\"direct_load\"",
		"\"operations\"",
		"\"nocompress\"",
		"\"pctfree\"",
		"\"pctused\"",
		"\"initrans\"",
		"\"maxtrans\"",
		"\"monitoring\"",
		"\"nomonitoring\"",
		"\"partition\"",
		"\"range\"",
		"\"interval\"",
		"\"store\"",
		"\"in\"",
		"\"values\"",
		"\"less\"",
		"\"than\"",
		"\"overflow\"",
		"\"hash\"",
		"\"partitions\"",
		"\"novalidate\"",
		"\"organization\"",
		"\"including\"",
		"\"pctthreshold\"",
		"\"heap\"",
		"\"external\"",
		"\"degree\"",
		"\"instances\"",
		"\"reject\"",
		"\"limit\"",
		"\"unlimited\"",
		"\"storage\"",
		"\"initial\"",
		"\"next\"",
		"\"minextents\"",
		"\"maxextents\"",
		"\"pctincrease\"",
		"\"freelists\"",
		"\"freelist\"",
		"\"groups\"",
		"\"optimal\"",
		"\"buffer_pool\"",
		"\"keep\"",
		"\"recycle\"",
		"\"encrypt\"",
		"\"check\"",
		"\"foreign\"",
		"\"restrict\"",
		"\"no\"",
		"\"action\"",
		"\"add\"",
		"\"modify\"",
		"\"rely\"",
		"\"sort\"",
		"\"nosort\"",
		"\"reverse\"",
		"\"visible\"",
		"\"novisible\"",
		"\"local\"",
		"\"object\"",
		"\"record\"",
		"\"ref\"",
		"\"cursor\"",
		"\"return\"",
		"\"rowtype\"",
		"\"varray\"",
		"\"option\"",
		"\"read\"",
		"\"only\"",
		"\"authid\"",
		"\"wrapped\"",
		"\"end\"",
		"\"constant\"",
		"\"subtype\"",
		"\"loop\"",
		"\"while\"",
		"\"forall\"",
		"\"if\"",
		"\"goto\"",
		"\"raise\"",
		"\"case\"",
		"\"select\"",
		"\"merge\"",
		"\"lock\"",
		"\"open\"",
		"\"close\"",
		"\"fetch\"",
		"\"sql\"",
		"\"found\"",
		"\"notfound\"",
		"\"rowcount\"",
		"\"isopen\"",
		"\"bulk_rowcount\"",
		"\"bulk_exceptions\"",
		"\"error_index\"",
		"\"error_code\"",
		"\"count\"",
		"\"then\"",
		"\"else\"",
		"\"pragma\"",
		"\"autonomous_transaction\"",
		"\"prior\"",
		"\"binary_integer\"",
		"\"natural\"",
		"\"positive\"",
		"\"number\"",
		"\"char\"",
		"\"byte\"",
		"\"raw\"",
		"\"boolean\"",
		"\"date\"",
		"\"timestamp\"",
		"\"time\"",
		"\"zone\"",
		"\"year\"",
		"\"month\"",
		"\"day\"",
		"\"second\"",
		"\"smallint\"",
		"\"real\"",
		"\"numeric\"",
		"\"int\"",
		"\"integer\"",
		"\"pls_integer\"",
		"\"double\"",
		"\"precision\"",
		"\"float\"",
		"\"decimal\"",
		"\"varchar\"",
		"\"varchar2\"",
		"\"nvarchar\"",
		"\"nvarchar2\"",
		"\"character\"",
		"\"mlslabel\"",
		"\"blob\"",
		"\"clob\"",
		"\"out\"",
		"\"nocopy\"",
		"\"any_cs\"",
		"\"charset\"",
		"\"exception\"",
		"\"serially_reusable\"",
		"\"exception_init\"",
		"\"restrict_references\"",
		"\"interface\"",
		"\"builtin\"",
		"\"fipsflag\"",
		"\"language\"",
		"\"java\"",
		"\"name\"",
		"\"pipelined\"",
		"\"parallel_enable\"",
		"\"deterministic\"",
		"\"aggregate\"",
		"\"save\"",
		"\"exceptions\"",
		"\"indices\"",
		"\"true\"",
		"\"false\"",
		"\"**\"",
		"\"at\"",
		"\"and\"",
		"\"current\"",
		"\"exists\"",
		"\"like\"",
		"\"escape\"",
		"\"between\"",
		"\"member\"",
		"\"cast\"",
		"\"sqlcode\"",
		"\"sqlerrm\"",
		"\"trim\"",
		"\"rank\"",
		"\"dense_rank\"",
		"\"minute\"",
		"\"hour\"",
		"\"any\"",
		"\"nextval\"",
		"\"currval\"",
		"\"over\"",
		"\"sessiontimezone\"",
		"\"dbtimezone\"",
		"\"distinct\"",
		"\"extract\"",
		"\"from\"",
		"\"timezone_hour\"",
		"\"timezone_minute\"",
		"\"work\"",
		"\"elsif\"",
		"\"union\"",
		"\"intersect\"",
		"\"minus\"",
		"\"bulk\"",
		"\"collect\"",
		"\"into\"",
		"\"immediate\"",
		"\"system\"",
		"\"session\"",
		"\"flush\"",
		"\"shared_pool\"",
		"\"reset\"",
		"\"sid\"",
		"\"left\"",
		"\"right\"",
		"\"inner\"",
		"\"outer\"",
		"\"join\"",
		"\"where\"",
		"\"leading\"",
		"\"trailing\"",
		"\"both\"",
		"\"systimestamp\"",
		"\"current_timestamp\"",
		"\"rownum\"",
		"\"the\"",
		"\"group\"",
		"\"having\"",
		"\"nulls\"",
		"\"first\"",
		"\"last\"",
		"\"nowait\"",
		"\"wait\"",
		"\"matched\"",
		"\"returning\"",
		"\"transaction\"",
		"\"mode\"",
		"\"share\"",
		"\"exclusive\"",
		"\"savepoint\"",
		"\"records\"",
		"\"parameters\"",
		"\"access\"",
		"\"newline\"",
		"\"delimited\"",
		"\"fixed\"",
		"\"characterset\"",
		"\"big\"",
		"\"little\"",
		"\"endian\"",
		"\"mark\"",
		"\"nocheck\"",
		"\"string\"",
		"\"sizes\"",
		"\"bytes\"",
		"\"load\"",
		"\"nobadfile\"",
		"\"badfile\"",
		"\"nodiscardfile\"",
		"\"discardfile\"",
		"\"nologfile\"",
		"\"logfile\"",
		"\"readsize\"",
		"\"skip\"",
		"\"data_cache\"",
		"\"fields\"",
		"\"missing\"",
		"\"field\"",
		"\"lrtrim\"",
		"\"notrim\"",
		"\"ltrim\"",
		"\"rtrim\"",
		"\"ldtrim\"",
		"\"position\"",
		"\"enclosed\"",
		"\"date_format\"",
		"\"varraw\"",
		"\"varcharc\"",
		"\"varrawc\"",
		"\"oracle_number\"",
		"\"oracle_date\"",
		"\"counted\"",
		"\"zoned\"",
		"\"unsigned\"",
		"\"location\"",
		"\"concat\"",
		"\"lobfile\"",
		"\"preprocessor\"",
		"\"compression\"",
		"\"enabled\"",
		"\"disabled\"",
		"\"encryption\"",
		"\"version\"",
		"\"compatible\"",
		"\"data\"",
		"\"mask\"",
		"\"terminated\"",
		"\"whitespace\"",
		"\"optionally\"",
		"\"timezone\"",
		"\"oracle_loader\"",
		"\"oracle_datapump\"",
		"\"latest\"",
		"\"characters\"",
		"\"are\"",
		"\"transforms\"",
		"\"ldrtrim\""
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[12];
		data[0]=2L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[24];
		data[0]=4557741984456194L;
		data[5]=-12525645533828096L;
		data[6]=-4827894139532345345L;
		data[7]=-5143114007615903009L;
		data[8]=720555888169526492L;
		data[9]=-899099056928926L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[18];
		data[0]=272629760L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[24];
		data[0]=-14L;
		for (int i = 1; i<=10; i++) { data[i]=-1L; }
		data[11]=4611686018427387903L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[18];
		data[0]=272629762L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = new long[18];
		data[0]=33554432L;
		data[5]=274877906944L;
		data[7]=2L;
		data[8]=402653184L;
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[24];
		data[0]=36044800L;
		data[5]=9188313000020910080L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738745L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = new long[18];
		data[0]=4567597058L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[18];
		data[5]=274877906944L;
		data[7]=2L;
		data[8]=402653184L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[24];
		data[0]=2251799816175616L;
		data[5]=9188312725143396352L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=180121997598598364L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = new long[24];
		data[0]=35187924008960L;
		data[5]=9197335317560532992L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=-540453669514435364L;
		data[9]=-3792928129610156161L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[24];
		data[0]=2251799816175616L;
		data[5]=9188312725143396352L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=180121997061727452L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[24];
		data[0]=29273402147995650L;
		data[5]=-12541313574523904L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041977738747L;
		data[8]=180123646866040028L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = new long[18];
		data[0]=314572802L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6931039173666272256L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551739799359L;
		data[7]=-5143114041977738747L;
		data[8]=612467559140762844L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = new long[18];
		data[0]=314572802L;
		data[5]=-6931039173632717824L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = new long[14];
		data[0]=4194304L;
		data[5]=-6931039276767379456L;
		data[6]=261104L;
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = new long[18];
		data[0]=4609540098L;
		data[5]=-6930985984795431936L;
		data[6]=-4890909195122246666L;
		data[7]=9015999628046346L;
		data[8]=1649267507968L;
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = new long[12];
		data[0]=33554432L;
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6931038898792559616L;
		data[6]=2017612633062767606L;
		data[7]=2L;
		data[8]=1649670094848L;
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6931039036227318784L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6931038898754778112L;
		data[6]=2017612648627829750L;
		data[7]=2L;
		data[8]=1649269538816L;
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = new long[24];
		data[0]=887488514L;
		data[5]=-142945581814784L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041977738747L;
		data[8]=-1116913048502065956L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = new long[24];
		data[0]=22571006589017602L;
		data[5]=-9147945813300224L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720555879176938716L;
		data[9]=-899116236796062L;
		data[10]=-257833038783157761L;
		data[11]=2341871806232657919L;
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = new long[12];
		data[0]=603979776L;
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = new long[20];
		data[5]=9007199254740992L;
		data[7]=8589934592L;
		data[8]=-1152921504606846976L;
		data[9]=1073213501L;
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = new long[24];
		data[0]=4523622712876546L;
		data[5]=-9150144834458624L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4628294956734740634L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806841533660L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = new long[24];
		data[0]=4523622746430978L;
		data[5]=-9150144834458624L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4628294956734740638L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = new long[24];
		data[0]=4523622712876546L;
		data[5]=-9150144834458624L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4628294956734740638L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = new long[24];
		data[0]=4556608079535618L;
		data[5]=-9147945813300224L;
		data[6]=4395477897322430463L;
		data[7]=-2680063788337L;
		data[8]=720575936674266333L;
		data[9]=-617641260087454L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = new long[18];
		data[0]=884998146L;
		data[5]=-6927327222415094784L;
		data[6]=2017612701782244342L;
		data[8]=1649267442688L;
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = new long[18];
		data[0]=884998146L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633264094198L;
		data[7]=9007203535024130L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = new long[24];
		data[0]=33836645710437890L;
		data[5]=-140746556462080L;
		data[6]=-4755836528314548225L;
		data[7]=-5134106186546677041L;
		data[8]=-288230376634449955L;
		data[9]=-564861213870209L;
		data[10]=-256707138876315185L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = new long[24];
		data[0]=35184656646146L;
		data[5]=-12541313574523904L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041977738747L;
		data[8]=36008456104781020L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = new long[24];
		data[0]=35184660840448L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = new long[24];
		data[0]=35222287941634L;
		data[5]=-12525920411735040L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041975641587L;
		data[8]=612469483286111452L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = new long[24];
		data[0]=283508738L;
		data[5]=-12541313574523904L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041977738747L;
		data[8]=36008456104781020L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = new long[24];
		data[0]=35364147691522L;
		data[5]=-12525920411735040L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041975641587L;
		data[8]=612469483286111452L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = new long[24];
		data[0]=-8208L;
		for (int i = 1; i<=10; i++) { data[i]=-1L; }
		data[11]=4611686018427387903L;
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551740323647L;
		data[7]=-5143114041977738747L;
		data[8]=612467559140762844L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = new long[18];
		data[0]=306184194L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = new long[24];
		data[0]=4556608079535618L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720555879176938716L;
		data[9]=-899116236798110L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = new long[24];
		data[0]=35222812229632L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266944090371774001L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = new long[24];
		data[0]=35188016283648L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517118513L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = new long[22];
		data[9]=3840L;
		data[10]=98352L;
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = new long[22];
		data[0]=1048576L;
		data[9]=3840L;
		data[10]=48L;
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = new long[24];
		data[0]=35188016283648L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641579L;
		data[8]=612468383774483676L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517118513L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = new long[24];
		data[0]=52974046289408L;
		data[5]=9188328393183698944L;
		data[6]=504367739816703807L;
		data[7]=-5143114007615903219L;
		data[8]=612468383774483676L;
		data[9]=-899116236798110L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = new long[22];
		data[5]=2199560126464L;
		data[10]=15393162790912L;
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	private static final long[] mk_tokenSet_58() {
		long[] data = new long[24];
		data[0]=1076232192L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
	private static final long[] mk_tokenSet_59() {
		long[] data = new long[24];
		data[0]=19267584L;
		data[5]=9188312725143003136L;
		data[6]=360252551739799359L;
		data[7]=-5143114041977738747L;
		data[8]=612467559140762844L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
	private static final long[] mk_tokenSet_60() {
		long[] data = new long[24];
		data[0]=4556608113090050L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720555879176938716L;
		data[9]=-899116236798110L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
	private static final long[] mk_tokenSet_61() {
		long[] data = new long[24];
		data[0]=2199327735808L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
	private static final long[] mk_tokenSet_62() {
		long[] data = new long[24];
		data[0]=37387475746816L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
	private static final long[] mk_tokenSet_63() {
		long[] data = new long[24];
		data[0]=4556642472828418L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720555879176938716L;
		data[9]=-899116236798110L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
	private static final long[] mk_tokenSet_64() {
		long[] data = new long[24];
		data[0]=4521423689620994L;
		data[5]=-12536640650105856L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4628347733359982750L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());
	private static final long[] mk_tokenSet_65() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143396352L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=180121997061727452L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());
	private static final long[] mk_tokenSet_66() {
		long[] data = new long[24];
		data[0]=2268139487232L;
		data[5]=-30546915991378944L;
		data[6]=4395477886583437119L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411089945374488734L;
		data[10]=-266961407658923645L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());
	private static final long[] mk_tokenSet_67() {
		long[] data = new long[24];
		data[0]=29311059537362944L;
		data[5]=-30540318921214976L;
		data[6]=4395477972482783039L;
		data[7]=-5143114007615903203L;
		data[8]=828662329879635164L;
		data[9]=-3792367034275723422L;
		data[10]=-266946014499931697L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());
	private static final long[] mk_tokenSet_68() {
		long[] data = new long[24];
		data[0]=35188418936832L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());
	private static final long[] mk_tokenSet_69() {
		long[] data = new long[24];
		data[0]=2268105932800L;
		data[5]=-30546915991378944L;
		data[6]=4395477886583437119L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411089945374488734L;
		data[10]=-266961407658923645L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());
	private static final long[] mk_tokenSet_70() {
		long[] data = new long[24];
		data[0]=57372172492290L;
		data[5]=-12525645533828096L;
		data[6]=2233749996727697407L;
		data[7]=-5143114007615903219L;
		data[8]=612469483286111452L;
		data[9]=-899116236798110L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());
	private static final long[] mk_tokenSet_71() {
		long[] data = new long[18];
		data[0]=13803454466L;
		data[5]=-6931038898754778112L;
		data[6]=2017612648627829750L;
		data[7]=2L;
		data[8]=1649269538816L;
		return data;
	}
	public static final BitSet _tokenSet_71 = new BitSet(mk_tokenSet_71());
	private static final long[] mk_tokenSet_72() {
		long[] data = new long[24];
		data[0]=2251799816175616L;
		data[5]=9188312725143396352L;
		data[6]=360252551739860799L;
		data[7]=-5143114041977738747L;
		data[8]=180121997598598364L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_72 = new BitSet(mk_tokenSet_72());
	private static final long[] mk_tokenSet_73() {
		long[] data = new long[24];
		data[0]=2287262615601152L;
		data[5]=-21533119666871296L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041975641595L;
		data[8]=-540431958452656932L;
		data[9]=-3792928129610156161L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_73 = new BitSet(mk_tokenSet_73());
	private static final long[] mk_tokenSet_74() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633264094198L;
		data[7]=9015999628046338L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_74 = new BitSet(mk_tokenSet_74());
	private static final long[] mk_tokenSet_75() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9197319924397744128L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_75 = new BitSet(mk_tokenSet_75());
	private static final long[] mk_tokenSet_76() {
		long[] data = new long[14];
		data[6]=6975131648L;
		return data;
	}
	public static final BitSet _tokenSet_76 = new BitSet(mk_tokenSet_76());
	private static final long[] mk_tokenSet_77() {
		long[] data = new long[18];
		data[0]=35188948074498L;
		data[5]=-6931039173670466560L;
		data[6]=2017612641648507894L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_77 = new BitSet(mk_tokenSet_77());
	private static final long[] mk_tokenSet_78() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6931039173670466560L;
		data[6]=2017612640037895158L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_78 = new BitSet(mk_tokenSet_78());
	private static final long[] mk_tokenSet_79() {
		long[] data = new long[14];
		data[5]=52776558133248L;
		data[6]=72058418671648768L;
		return data;
	}
	public static final BitSet _tokenSet_79 = new BitSet(mk_tokenSet_79());
	private static final long[] mk_tokenSet_80() {
		long[] data = new long[16];
		data[6]=-6917529027640885248L;
		data[7]=8L;
		return data;
	}
	public static final BitSet _tokenSet_80 = new BitSet(mk_tokenSet_80());
	private static final long[] mk_tokenSet_81() {
		long[] data = new long[14];
		data[6]=196608L;
		return data;
	}
	public static final BitSet _tokenSet_81 = new BitSet(mk_tokenSet_81());
	private static final long[] mk_tokenSet_82() {
		long[] data = new long[14];
		data[5]=137438953472L;
		data[6]=1048576L;
		return data;
	}
	public static final BitSet _tokenSet_82 = new BitSet(mk_tokenSet_82());
	private static final long[] mk_tokenSet_83() {
		long[] data = new long[16];
		data[0]=268435456L;
		data[6]=-6917529027640885248L;
		data[7]=10L;
		return data;
	}
	public static final BitSet _tokenSet_83 = new BitSet(mk_tokenSet_83());
	private static final long[] mk_tokenSet_84() {
		long[] data = new long[24];
		data[0]=4503604807335938L;
		data[5]=-6930986397112333312L;
		data[6]=4323455722807098358L;
		data[7]=8L;
		data[8]=36030450583471104L;
		data[9]=2L;
		data[10]=9112988597878784L;
		data[11]=567348002028674L;
		return data;
	}
	public static final BitSet _tokenSet_84 = new BitSet(mk_tokenSet_84());
	private static final long[] mk_tokenSet_85() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6928505898846516224L;
		data[6]=2017612633062767606L;
		data[7]=4398046511120L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_85 = new BitSet(mk_tokenSet_85());
	private static final long[] mk_tokenSet_86() {
		long[] data = new long[12];
		data[0]=536870912L;
		return data;
	}
	public static final BitSet _tokenSet_86 = new BitSet(mk_tokenSet_86());
	private static final long[] mk_tokenSet_87() {
		long[] data = new long[16];
		data[5]=2533274823950336L;
		data[7]=4398046511120L;
		return data;
	}
	public static final BitSet _tokenSet_87 = new BitSet(mk_tokenSet_87());
	private static final long[] mk_tokenSet_88() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=2017612702855986166L;
		data[8]=1649269538816L;
		data[10]=9007199254740992L;
		return data;
	}
	public static final BitSet _tokenSet_88 = new BitSet(mk_tokenSet_88());
	private static final long[] mk_tokenSet_89() {
		long[] data = new long[18];
		data[0]=5179965442L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633264094198L;
		data[7]=9007753290838018L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_89 = new BitSet(mk_tokenSet_89());
	private static final long[] mk_tokenSet_90() {
		long[] data = new long[18];
		data[0]=5179965442L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633264094198L;
		data[7]=9007753290838018L;
		data[8]=1649267570689L;
		return data;
	}
	public static final BitSet _tokenSet_90 = new BitSet(mk_tokenSet_90());
	private static final long[] mk_tokenSet_91() {
		long[] data = new long[18];
		data[0]=5179965442L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633264094198L;
		data[7]=9007753290838018L;
		data[8]=1649267507201L;
		return data;
	}
	public static final BitSet _tokenSet_91 = new BitSet(mk_tokenSet_91());
	private static final long[] mk_tokenSet_92() {
		long[] data = new long[18];
		data[7]=-2323857407723175936L;
		data[8]=4L;
		return data;
	}
	public static final BitSet _tokenSet_92 = new BitSet(mk_tokenSet_92());
	private static final long[] mk_tokenSet_93() {
		long[] data = new long[18];
		data[0]=5112856578L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633264094198L;
		data[7]=10133103441866754L;
		data[8]=1649267570689L;
		return data;
	}
	public static final BitSet _tokenSet_93 = new BitSet(mk_tokenSet_93());
	private static final long[] mk_tokenSet_94() {
		long[] data = new long[24];
		data[0]=606470144L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5134106842605028859L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_94 = new BitSet(mk_tokenSet_94());
	private static final long[] mk_tokenSet_95() {
		long[] data = new long[18];
		data[0]=5112856578L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633264094198L;
		data[7]=9007203535024130L;
		data[8]=1649267507201L;
		return data;
	}
	public static final BitSet _tokenSet_95 = new BitSet(mk_tokenSet_95());
	private static final long[] mk_tokenSet_96() {
		long[] data = new long[16];
		data[7]=9007749532643328L;
		return data;
	}
	public static final BitSet _tokenSet_96 = new BitSet(mk_tokenSet_96());
	private static final long[] mk_tokenSet_97() {
		long[] data = new long[16];
		data[7]=9007199776829440L;
		return data;
	}
	public static final BitSet _tokenSet_97 = new BitSet(mk_tokenSet_97());
	private static final long[] mk_tokenSet_98() {
		long[] data = new long[24];
		data[0]=35185246994432L;
		data[5]=9188312725143003136L;
		data[6]=2666095560953489215L;
		data[7]=-5134106292849214971L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_98 = new BitSet(mk_tokenSet_98());
	private static final long[] mk_tokenSet_99() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-2714419332601L;
		data[8]=36008456104781020L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_99 = new BitSet(mk_tokenSet_99());
	private static final long[] mk_tokenSet_100() {
		long[] data = new long[16];
		data[0]=35184976068608L;
		data[6]=2305843009213693952L;
		data[7]=9007749532643328L;
		return data;
	}
	public static final BitSet _tokenSet_100 = new BitSet(mk_tokenSet_100());
	private static final long[] mk_tokenSet_101() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-5134106289621698041L;
		data[8]=36008456104781020L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_101 = new BitSet(mk_tokenSet_101());
	private static final long[] mk_tokenSet_102() {
		long[] data = new long[16];
		data[0]=603979776L;
		data[7]=9007749532643328L;
		return data;
	}
	public static final BitSet _tokenSet_102 = new BitSet(mk_tokenSet_102());
	private static final long[] mk_tokenSet_103() {
		long[] data = new long[16];
		data[0]=603979776L;
		data[7]=549773643776L;
		return data;
	}
	public static final BitSet _tokenSet_103 = new BitSet(mk_tokenSet_103());
	private static final long[] mk_tokenSet_104() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-5134106289627989497L;
		data[8]=36008456104781021L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_104 = new BitSet(mk_tokenSet_104());
	private static final long[] mk_tokenSet_105() {
		long[] data = new long[16];
		data[7]=549773643776L;
		return data;
	}
	public static final BitSet _tokenSet_105 = new BitSet(mk_tokenSet_105());
	private static final long[] mk_tokenSet_106() {
		long[] data = new long[18];
		data[0]=35189484945410L;
		data[5]=-6930986397112300544L;
		data[6]=4323455642477788150L;
		data[7]=9007753290838018L;
		data[8]=1649267507201L;
		return data;
	}
	public static final BitSet _tokenSet_106 = new BitSet(mk_tokenSet_106());
	private static final long[] mk_tokenSet_107() {
		long[] data = new long[18];
		data[0]=5112856578L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633264094198L;
		data[7]=9007753290838018L;
		data[8]=1649267507201L;
		return data;
	}
	public static final BitSet _tokenSet_107 = new BitSet(mk_tokenSet_107());
	private static final long[] mk_tokenSet_108() {
		long[] data = new long[18];
		data[0]=35188948074498L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633264094198L;
		data[7]=10133103441866754L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_108 = new BitSet(mk_tokenSet_108());
	private static final long[] mk_tokenSet_109() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633264094198L;
		data[7]=10133103441866754L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_109 = new BitSet(mk_tokenSet_109());
	private static final long[] mk_tokenSet_110() {
		long[] data = new long[18];
		data[0]=536870912L;
		data[7]=-2323857407723175936L;
		data[8]=4L;
		return data;
	}
	public static final BitSet _tokenSet_110 = new BitSet(mk_tokenSet_110());
	private static final long[] mk_tokenSet_111() {
		long[] data = new long[18];
		data[5]=281474976710656L;
		data[7]=16L;
		data[8]=24L;
		return data;
	}
	public static final BitSet _tokenSet_111 = new BitSet(mk_tokenSet_111());
	private static final long[] mk_tokenSet_112() {
		long[] data = new long[18];
		data[5]=4503599627403264L;
		data[8]=96L;
		return data;
	}
	public static final BitSet _tokenSet_112 = new BitSet(mk_tokenSet_112());
	private static final long[] mk_tokenSet_113() {
		long[] data = new long[18];
		data[0]=4575985666L;
		data[5]=-6928734459859694592L;
		data[6]=2017612633264094198L;
		data[7]=9015999628046338L;
		data[8]=1649267507200L;
		return data;
	}
	public static final BitSet _tokenSet_113 = new BitSet(mk_tokenSet_113());
	private static final long[] mk_tokenSet_114() {
		long[] data = new long[12];
		data[0]=603979776L;
		data[5]=137438953472L;
		return data;
	}
	public static final BitSet _tokenSet_114 = new BitSet(mk_tokenSet_114());
	private static final long[] mk_tokenSet_115() {
		long[] data = new long[18];
		data[5]=2048L;
		data[6]=9007199254740992L;
		data[8]=768L;
		return data;
	}
	public static final BitSet _tokenSet_115 = new BitSet(mk_tokenSet_115());
	private static final long[] mk_tokenSet_116() {
		long[] data = new long[24];
		data[0]=270925824L;
		data[5]=9190577719096221696L;
		data[6]=360252568919664447L;
		data[7]=-5143114041977738731L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_116 = new BitSet(mk_tokenSet_116());
	private static final long[] mk_tokenSet_117() {
		long[] data = new long[24];
		data[0]=35187894648834L;
		data[5]=-141021436466176L;
		data[6]=2089634877370269695L;
		data[7]=-5143114041975641579L;
		data[8]=-540452021320735524L;
		data[9]=-3792930002215897217L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_117 = new BitSet(mk_tokenSet_117());
	private static final long[] mk_tokenSet_118() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_118 = new BitSet(mk_tokenSet_118());
	private static final long[] mk_tokenSet_119() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9190577719096221696L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738731L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_119 = new BitSet(mk_tokenSet_119());
	private static final long[] mk_tokenSet_120() {
		long[] data = new long[12];
		data[0]=270663680L;
		data[5]=562949953421312L;
		return data;
	}
	public static final BitSet _tokenSet_120 = new BitSet(mk_tokenSet_120());
	private static final long[] mk_tokenSet_121() {
		long[] data = new long[20];
		data[0]=281018370L;
		data[5]=-6918305729509192704L;
		data[6]=2017612633062767606L;
		data[7]=8589934592L;
		data[8]=-1152919855339405312L;
		data[9]=1073213501L;
		return data;
	}
	public static final BitSet _tokenSet_121 = new BitSet(mk_tokenSet_121());
	private static final long[] mk_tokenSet_122() {
		long[] data = new long[18];
		data[5]=2546468929929216L;
		data[7]=16L;
		data[8]=24L;
		return data;
	}
	public static final BitSet _tokenSet_122 = new BitSet(mk_tokenSet_122());
	private static final long[] mk_tokenSet_123() {
		long[] data = new long[24];
		data[0]=35187894648834L;
		data[5]=-12525920411735040L;
		data[6]=2089634877370269695L;
		data[7]=-5143114041975641595L;
		data[8]=612469483286111452L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_123 = new BitSet(mk_tokenSet_123());
	private static final long[] mk_tokenSet_124() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[5]=12680667603140608L;
		data[7]=8589934592L;
		data[8]=-1152921504606846976L;
		data[9]=1073213501L;
		return data;
	}
	public static final BitSet _tokenSet_124 = new BitSet(mk_tokenSet_124());
	private static final long[] mk_tokenSet_125() {
		long[] data = new long[24];
		data[0]=35185259577346L;
		data[5]=-9148220691207168L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041977738747L;
		data[8]=36008456104781020L;
		data[9]=-8411371420351199386L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_125 = new BitSet(mk_tokenSet_125());
	private static final long[] mk_tokenSet_126() {
		long[] data = new long[24];
		data[0]=35188418936832L;
		data[5]=9188328118305792000L;
		data[6]=360252620459276095L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_126 = new BitSet(mk_tokenSet_126());
	private static final long[] mk_tokenSet_127() {
		long[] data = new long[24];
		data[0]=52974058872322L;
		data[5]=-12525645531730944L;
		data[6]=2233750065447174143L;
		data[7]=-5143114007615903203L;
		data[8]=612469483286111452L;
		data[9]=-899116236798110L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_127 = new BitSet(mk_tokenSet_127());
	private static final long[] mk_tokenSet_128() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6930986397078778880L;
		data[6]=2017612633062767606L;
		data[7]=4398046511104L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_128 = new BitSet(mk_tokenSet_128());
	private static final long[] mk_tokenSet_129() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6930986397112333312L;
		data[6]=2017612701782244342L;
		data[8]=1649267442688L;
		return data;
	}
	public static final BitSet _tokenSet_129 = new BitSet(mk_tokenSet_129());
	private static final long[] mk_tokenSet_130() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6930986259639825408L;
		data[6]=2017612701782244342L;
		data[7]=4398046511104L;
		data[8]=1649267442688L;
		return data;
	}
	public static final BitSet _tokenSet_130 = new BitSet(mk_tokenSet_130());
	private static final long[] mk_tokenSet_131() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6930986397112333312L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_131 = new BitSet(mk_tokenSet_131());
	private static final long[] mk_tokenSet_132() {
		long[] data = new long[18];
		data[0]=851443714L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633062767606L;
		data[8]=1649267441665L;
		return data;
	}
	public static final BitSet _tokenSet_132 = new BitSet(mk_tokenSet_132());
	private static final long[] mk_tokenSet_133() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633062767606L;
		data[8]=1649267441665L;
		return data;
	}
	public static final BitSet _tokenSet_133 = new BitSet(mk_tokenSet_133());
	private static final long[] mk_tokenSet_134() {
		long[] data = new long[18];
		data[7]=9007199759085824L;
		data[8]=129024L;
		return data;
	}
	public static final BitSet _tokenSet_134 = new BitSet(mk_tokenSet_134());
	private static final long[] mk_tokenSet_135() {
		long[] data = new long[24];
		data[0]=35185192468482L;
		data[5]=-12541313574523904L;
		data[6]=2089634808650792959L;
		data[7]=-5134106840474322427L;
		data[8]=36008456104836317L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_135 = new BitSet(mk_tokenSet_135());
	private static final long[] mk_tokenSet_136() {
		long[] data = new long[18];
		data[7]=9007199759085568L;
		data[8]=63488L;
		return data;
	}
	public static final BitSet _tokenSet_136 = new BitSet(mk_tokenSet_136());
	private static final long[] mk_tokenSet_137() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6930986397112300544L;
		data[6]=2017612633062767606L;
		data[7]=9007199759085568L;
		data[8]=1649267505153L;
		return data;
	}
	public static final BitSet _tokenSet_137 = new BitSet(mk_tokenSet_137());
	private static final long[] mk_tokenSet_138() {
		long[] data = new long[16];
		data[0]=603979776L;
		data[7]=9007199776829440L;
		return data;
	}
	public static final BitSet _tokenSet_138 = new BitSet(mk_tokenSet_138());
	private static final long[] mk_tokenSet_139() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-5134106839383803385L;
		data[8]=36008456104781021L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_139 = new BitSet(mk_tokenSet_139());
	private static final long[] mk_tokenSet_140() {
		long[] data = new long[24];
		data[0]=35185246994432L;
		data[5]=9188312725143003136L;
		data[6]=2666095560953489215L;
		data[7]=-5134106842605028859L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_140 = new BitSet(mk_tokenSet_140());
	private static final long[] mk_tokenSet_141() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-3264175146489L;
		data[8]=36008456104781021L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_141 = new BitSet(mk_tokenSet_141());
	private static final long[] mk_tokenSet_142() {
		long[] data = new long[16];
		data[0]=35184976068608L;
		data[6]=2305843009213693952L;
		data[7]=9007199776829440L;
		return data;
	}
	public static final BitSet _tokenSet_142 = new BitSet(mk_tokenSet_142());
	private static final long[] mk_tokenSet_143() {
		long[] data = new long[24];
		data[0]=35189554544642L;
		data[5]=-12541313574523904L;
		data[6]=4395477817864486911L;
		data[7]=-5134106839377511929L;
		data[8]=36008456104781021L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_143 = new BitSet(mk_tokenSet_143());
	private static final long[] mk_tokenSet_144() {
		long[] data = new long[16];
		data[5]=274877906944L;
		data[7]=2L;
		return data;
	}
	public static final BitSet _tokenSet_144 = new BitSet(mk_tokenSet_144());
	private static final long[] mk_tokenSet_145() {
		long[] data = new long[18];
		data[0]=38935724034L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_145 = new BitSet(mk_tokenSet_145());
	private static final long[] mk_tokenSet_146() {
		long[] data = new long[24];
		data[0]=4563205182856706L;
		data[5]=-9147945811203072L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720575936674211036L;
		data[9]=-564864634845342L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_146 = new BitSet(mk_tokenSet_146());
	private static final long[] mk_tokenSet_147() {
		long[] data = new long[12];
		data[0]=8388608L;
		return data;
	}
	public static final BitSet _tokenSet_147 = new BitSet(mk_tokenSet_147());
	private static final long[] mk_tokenSet_148() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9197319924397744128L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914696695765796L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_148 = new BitSet(mk_tokenSet_148());
	private static final long[] mk_tokenSet_149() {
		long[] data = new long[24];
		data[0]=2233695862784L;
		data[5]=9197324322444255232L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_149 = new BitSet(mk_tokenSet_149());
	private static final long[] mk_tokenSet_150() {
		long[] data = new long[24];
		data[0]=29273397580398592L;
		data[5]=9188312725143396352L;
		data[6]=360252551739860799L;
		data[7]=-5143114041977738747L;
		data[8]=180121997598598364L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_150 = new BitSet(mk_tokenSet_150());
	private static final long[] mk_tokenSet_151() {
		long[] data = new long[12];
		data[0]=27021597764222976L;
		return data;
	}
	public static final BitSet _tokenSet_151 = new BitSet(mk_tokenSet_151());
	private static final long[] mk_tokenSet_152() {
		long[] data = new long[24];
		data[0]=63050394785677312L;
		data[5]=9188312725143396352L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=180121997061727452L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_152 = new BitSet(mk_tokenSet_152());
	private static final long[] mk_tokenSet_153() {
		long[] data = new long[16];
		data[0]=301989888L;
		data[5]=274877906944L;
		data[7]=2L;
		return data;
	}
	public static final BitSet _tokenSet_153 = new BitSet(mk_tokenSet_153());
	private static final long[] mk_tokenSet_154() {
		long[] data = new long[24];
		data[0]=36044800L;
		data[5]=9197319924397744128L;
		data[6]=360252551739799359L;
		data[7]=-5143114041977738747L;
		data[8]=-540453944392342308L;
		data[9]=-8411369547478599809L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_154 = new BitSet(mk_tokenSet_154());
	private static final long[] mk_tokenSet_155() {
		long[] data = new long[24];
		data[0]=2233695862784L;
		data[5]=9197324597322162176L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738745L;
		data[8]=-1116914697767410468L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_155 = new BitSet(mk_tokenSet_155());
	private static final long[] mk_tokenSet_156() {
		long[] data = new long[18];
		data[8]=536870912L;
		return data;
	}
	public static final BitSet _tokenSet_156 = new BitSet(mk_tokenSet_156());
	private static final long[] mk_tokenSet_157() {
		long[] data = new long[24];
		data[0]=2252074979295232L;
		data[5]=-30546915991378944L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_157 = new BitSet(mk_tokenSet_157());
	private static final long[] mk_tokenSet_158() {
		long[] data = new long[22];
		data[0]=27021597764222976L;
		data[7]=8L;
		data[8]=72057594574798848L;
		data[9]=17179869184L;
		data[10]=262144L;
		return data;
	}
	public static final BitSet _tokenSet_158 = new BitSet(mk_tokenSet_158());
	private static final long[] mk_tokenSet_159() {
		long[] data = new long[20];
		data[0]=268435456L;
		data[5]=274877906944L;
		data[6]=1073741824L;
		data[9]=525572L;
		return data;
	}
	public static final BitSet _tokenSet_159 = new BitSet(mk_tokenSet_159());
	private static final long[] mk_tokenSet_160() {
		long[] data = new long[24];
		data[0]=35184777232384L;
		data[5]=9197320199275651072L;
		data[6]=360252568919664447L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371420084340865L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_160 = new BitSet(mk_tokenSet_160());
	private static final long[] mk_tokenSet_161() {
		long[] data = new long[20];
		data[0]=34670116864L;
		data[5]=4398046511104L;
		data[6]=1073741824L;
		data[9]=525572L;
		return data;
	}
	public static final BitSet _tokenSet_161 = new BitSet(mk_tokenSet_161());
	private static final long[] mk_tokenSet_162() {
		long[] data = new long[24];
		data[0]=29308582363529216L;
		data[5]=9188325919282929664L;
		data[6]=360252568919729983L;
		data[7]=-5143114041977738747L;
		data[8]=180121997602792668L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_162 = new BitSet(mk_tokenSet_162());
	private static final long[] mk_tokenSet_163() {
		long[] data = new long[20];
		data[0]=8388608L;
		data[5]=274877906944L;
		data[7]=2L;
		data[9]=140737488355328L;
		return data;
	}
	public static final BitSet _tokenSet_163 = new BitSet(mk_tokenSet_163());
	private static final long[] mk_tokenSet_164() {
		long[] data = new long[24];
		data[0]=29273397580398592L;
		data[5]=9188312725143396352L;
		data[6]=360252620459337535L;
		data[7]=-5143114041977738747L;
		data[8]=180121997598598364L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_164 = new BitSet(mk_tokenSet_164());
	private static final long[] mk_tokenSet_165() {
		long[] data = new long[24];
		data[0]=56330458144047104L;
		data[5]=-21533119666478080L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041975641595L;
		data[8]=-108086391539686180L;
		data[9]=-3792928129610156161L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_165 = new BitSet(mk_tokenSet_165());
	private static final long[] mk_tokenSet_166() {
		long[] data = new long[16];
		data[0]=8388608L;
		data[5]=274877906944L;
		data[7]=2L;
		return data;
	}
	public static final BitSet _tokenSet_166 = new BitSet(mk_tokenSet_166());
	private static final long[] mk_tokenSet_167() {
		long[] data = new long[12];
		data[0]=34393292800L;
		return data;
	}
	public static final BitSet _tokenSet_167 = new BitSet(mk_tokenSet_167());
	private static final long[] mk_tokenSet_168() {
		long[] data = new long[22];
		data[0]=34636562432L;
		data[5]=274877906944L;
		data[6]=2305843077933170688L;
		data[8]=4297064448L;
		data[10]=20971520L;
		return data;
	}
	public static final BitSet _tokenSet_168 = new BitSet(mk_tokenSet_168());
	private static final long[] mk_tokenSet_169() {
		long[] data = new long[24];
		data[0]=35364210606080L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641569L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014496130609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_169 = new BitSet(mk_tokenSet_169());
	private static final long[] mk_tokenSet_170() {
		long[] data = new long[18];
		data[0]=817889282L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_170 = new BitSet(mk_tokenSet_170());
	private static final long[] mk_tokenSet_171() {
		long[] data = new long[24];
		data[0]=287703040L;
		data[5]=-30546915991378944L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_171 = new BitSet(mk_tokenSet_171());
	private static final long[] mk_tokenSet_172() {
		long[] data = new long[24];
		data[0]=29273672743518208L;
		data[5]=-30546915991378944L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041977738739L;
		data[8]=684547139655247068L;
		data[9]=-8411371403171330206L;
		data[10]=-266961407679649405L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_172 = new BitSet(mk_tokenSet_172());
	private static final long[] mk_tokenSet_173() {
		long[] data = new long[18];
		data[0]=2228224L;
		data[6]=2305843009213693952L;
		data[8]=12884901888L;
		return data;
	}
	public static final BitSet _tokenSet_173 = new BitSet(mk_tokenSet_173());
	private static final long[] mk_tokenSet_174() {
		long[] data = new long[24];
		data[0]=2287262573658112L;
		data[5]=-30540318921612288L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041975641595L;
		data[8]=612489545080448220L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_174 = new BitSet(mk_tokenSet_174());
	private static final long[] mk_tokenSet_175() {
		long[] data = new long[24];
		data[0]=2307247232720384L;
		data[5]=-30540044043308032L;
		data[6]=4539593160559687487L;
		data[7]=-5143114007615903203L;
		data[8]=756604735841707228L;
		data[9]=-899099056928926L;
		data[10]=-266946014500193841L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_175 = new BitSet(mk_tokenSet_175());
	private static final long[] mk_tokenSet_176() {
		long[] data = new long[18];
		data[0]=268435456L;
		data[6]=2017612633061982214L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_176 = new BitSet(mk_tokenSet_176());
	private static final long[] mk_tokenSet_177() {
		long[] data = new long[24];
		data[0]=35188024737792L;
		data[5]=9188323720259280896L;
		data[6]=360252568919668543L;
		data[7]=-5143114041975641579L;
		data[8]=612468383774483676L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014500193841L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_177 = new BitSet(mk_tokenSet_177());
	private static final long[] mk_tokenSet_178() {
		long[] data = new long[24];
		data[0]=29309036674875392L;
		data[5]=-30540318921612288L;
		data[6]=4395477835043829567L;
		data[7]=-5143114041975641569L;
		data[8]=684547139655247068L;
		data[9]=-3783922786048145566L;
		data[10]=-257938677802174001L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_178 = new BitSet(mk_tokenSet_178());
	private static final long[] mk_tokenSet_179() {
		long[] data = new long[14];
		data[0]=276824064L;
		data[6]=2305843077933170688L;
		return data;
	}
	public static final BitSet _tokenSet_179 = new BitSet(mk_tokenSet_179());
	private static final long[] mk_tokenSet_180() {
		long[] data = new long[24];
		data[0]=29308860874752000L;
		data[5]=-30540318921612288L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041975641587L;
		data[8]=684547139655247068L;
		data[9]=-3792929985302886558L;
		data[10]=-266946014516860465L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_180 = new BitSet(mk_tokenSet_180());
	private static final long[] mk_tokenSet_181() {
		long[] data = new long[18];
		data[0]=1099520016384L;
		data[6]=2305843009213693952L;
		data[7]=8L;
		data[8]=12884901888L;
		return data;
	}
	public static final BitSet _tokenSet_181 = new BitSet(mk_tokenSet_181());
	private static final long[] mk_tokenSet_182() {
		long[] data = new long[24];
		data[0]=312868864L;
		data[5]=9188312725143003136L;
		data[6]=360252620459271999L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407658940029L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_182 = new BitSet(mk_tokenSet_182());
	private static final long[] mk_tokenSet_183() {
		long[] data = new long[24];
		data[0]=29308860916695040L;
		data[5]=-30540318921612288L;
		data[6]=4395477886583437119L;
		data[7]=-5143114007615903219L;
		data[8]=684547139655247068L;
		data[9]=-3792929984229144734L;
		data[10]=-266946014495888945L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_183 = new BitSet(mk_tokenSet_183());
	private static final long[] mk_tokenSet_184() {
		long[] data = new long[24];
		data[0]=35364151885824L;
		data[5]=9188328118305792000L;
		data[6]=360252620459276095L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014496151089L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_184 = new BitSet(mk_tokenSet_184());
	private static final long[] mk_tokenSet_185() {
		long[] data = new long[24];
		data[0]=29326646510493184L;
		data[5]=-21532844788964352L;
		data[6]=4539593091840210751L;
		data[7]=-5143114007615903203L;
		data[8]=-468374364951599908L;
		data[9]=-899097716326529L;
		data[10]=-266944090350437889L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_185 = new BitSet(mk_tokenSet_185());
	private static final long[] mk_tokenSet_186() {
		long[] data = new long[24];
		data[0]=35364151885824L;
		data[5]=9188328118305792000L;
		data[6]=360252620459276095L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_186 = new BitSet(mk_tokenSet_186());
	private static final long[] mk_tokenSet_187() {
		long[] data = new long[24];
		data[0]=29326646510493184L;
		data[5]=-21532844788964352L;
		data[6]=4539593091840210751L;
		data[7]=-5143114007615903203L;
		data[8]=-468374364951599908L;
		data[9]=-899098790068353L;
		data[10]=-266944090371409409L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_187 = new BitSet(mk_tokenSet_187());
	private static final long[] mk_tokenSet_188() {
		long[] data = new long[16];
		data[0]=310378496L;
		data[7]=2L;
		return data;
	}
	public static final BitSet _tokenSet_188 = new BitSet(mk_tokenSet_188());
	private static final long[] mk_tokenSet_189() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143396352L;
		data[6]=360252551739860799L;
		data[7]=-5143114041977738747L;
		data[8]=180121997061727452L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_189 = new BitSet(mk_tokenSet_189());
	private static final long[] mk_tokenSet_190() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=612467559140762844L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_190 = new BitSet(mk_tokenSet_190());
	private static final long[] mk_tokenSet_191() {
		long[] data = new long[24];
		data[0]=4558807136345602L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903025L;
		data[8]=720575936674211036L;
		data[9]=-617641260087454L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_191 = new BitSet(mk_tokenSet_191());
	private static final long[] mk_tokenSet_192() {
		long[] data = new long[22];
		data[0]=39539703810L;
		data[5]=-6931039173670466560L;
		data[6]=2017612702855986166L;
		data[8]=1649269538816L;
		data[10]=12384898978938880L;
		return data;
	}
	public static final BitSet _tokenSet_192 = new BitSet(mk_tokenSet_192());
	private static final long[] mk_tokenSet_193() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9197319924397744128L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371416863115393L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_193 = new BitSet(mk_tokenSet_193());
	private static final long[] mk_tokenSet_194() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9197319924397744128L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371417936857217L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_194 = new BitSet(mk_tokenSet_194());
	private static final long[] mk_tokenSet_195() {
		long[] data = new long[20];
		data[0]=2234288963584L;
		data[5]=1099511627776L;
		data[6]=1073741824L;
		data[9]=67634436L;
		return data;
	}
	public static final BitSet _tokenSet_195 = new BitSet(mk_tokenSet_195());
	private static final long[] mk_tokenSet_196() {
		long[] data = new long[24];
		data[0]=37387651907584L;
		data[5]=9192827594764558336L;
		data[6]=360252568919668543L;
		data[7]=-5143114041975641593L;
		data[8]=612467834024961244L;
		data[9]=-3792930002415646878L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_196 = new BitSet(mk_tokenSet_196());
	private static final long[] mk_tokenSet_197() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9197319924397744128L;
		data[6]=360252551739795263L;
		data[7]=-5143114007618000379L;
		data[8]=-1116914697769507620L;
		data[9]=-8411371416863115393L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_197 = new BitSet(mk_tokenSet_197());
	private static final long[] mk_tokenSet_198() {
		long[] data = new long[20];
		data[0]=2199635623936L;
		data[5]=1374389534720L;
		data[6]=68719476736L;
		data[7]=2L;
		data[9]=263882790666240L;
		return data;
	}
	public static final BitSet _tokenSet_198 = new BitSet(mk_tokenSet_198());
	private static final long[] mk_tokenSet_199() {
		long[] data = new long[20];
		data[0]=8388608L;
		data[5]=274877906944L;
		data[6]=68719476736L;
		data[7]=2L;
		data[9]=263882857775104L;
		return data;
	}
	public static final BitSet _tokenSet_199 = new BitSet(mk_tokenSet_199());
	private static final long[] mk_tokenSet_200() {
		long[] data = new long[20];
		data[0]=8388608L;
		data[6]=1048576L;
		data[8]=36028797018963968L;
		data[9]=17179869184L;
		return data;
	}
	public static final BitSet _tokenSet_200 = new BitSet(mk_tokenSet_200());
	private static final long[] mk_tokenSet_201() {
		long[] data = new long[20];
		data[0]=41943040L;
		data[6]=1048576L;
		data[8]=36028797018963968L;
		data[9]=17179869184L;
		return data;
	}
	public static final BitSet _tokenSet_201 = new BitSet(mk_tokenSet_201());
	private static final long[] mk_tokenSet_202() {
		long[] data = new long[24];
		data[0]=4521423689620994L;
		data[5]=-12536640650105856L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4619340534105241758L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_202 = new BitSet(mk_tokenSet_202());
	private static final long[] mk_tokenSet_203() {
		long[] data = new long[14];
		data[6]=65536L;
		return data;
	}
	public static final BitSet _tokenSet_203 = new BitSet(mk_tokenSet_203());
	private static final long[] mk_tokenSet_204() {
		long[] data = new long[18];
		data[7]=8L;
		data[8]=536870912L;
		return data;
	}
	public static final BitSet _tokenSet_204 = new BitSet(mk_tokenSet_204());
	private static final long[] mk_tokenSet_205() {
		long[] data = new long[24];
		data[0]=35184391356416L;
		data[5]=9188312725143003136L;
		data[6]=360252551739799359L;
		data[7]=-5143114041977738747L;
		data[8]=612467559140762844L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_205 = new BitSet(mk_tokenSet_205());
	private static final long[] mk_tokenSet_206() {
		long[] data = new long[24];
		data[0]=69023956992L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_206 = new BitSet(mk_tokenSet_206());
	private static final long[] mk_tokenSet_207() {
		long[] data = new long[24];
		data[0]=287703040L;
		data[5]=-30546915991378944L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411089945374488734L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_207 = new BitSet(mk_tokenSet_207());
	private static final long[] mk_tokenSet_208() {
		long[] data = new long[16];
		data[7]=34359738368L;
		return data;
	}
	public static final BitSet _tokenSet_208 = new BitSet(mk_tokenSet_208());
	private static final long[] mk_tokenSet_209() {
		long[] data = new long[24];
		data[0]=69007179776L;
		data[5]=-30546915991378944L;
		data[6]=4395477817863960383L;
		data[7]=-5143114041977738747L;
		data[8]=612489545080448220L;
		data[9]=-8411089945374488734L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_209 = new BitSet(mk_tokenSet_209());
	private static final long[] mk_tokenSet_210() {
		long[] data = new long[24];
		data[0]=35432862973952L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_210 = new BitSet(mk_tokenSet_210());
	private static final long[] mk_tokenSet_211() {
		long[] data = new long[24];
		data[0]=53042765766144L;
		data[5]=9197335592438439936L;
		data[6]=504367756996572991L;
		data[7]=-5143114007615903203L;
		data[8]=-540453120832363300L;
		data[9]=-899115969937537L;
		data[10]=-266944090371671553L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_211 = new BitSet(mk_tokenSet_211());
	private static final long[] mk_tokenSet_212() {
		long[] data = new long[18];
		data[8]=4294967296L;
		return data;
	}
	public static final BitSet _tokenSet_212 = new BitSet(mk_tokenSet_212());
	private static final long[] mk_tokenSet_213() {
		long[] data = new long[24];
		data[0]=4521458082913794L;
		data[5]=-12536640650105856L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007618000177L;
		data[8]=144094851995608284L;
		data[9]=-4628347733359982746L;
		data[10]=-257848431945946749L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_213 = new BitSet(mk_tokenSet_213());
	private static final long[] mk_tokenSet_214() {
		long[] data = new long[24];
		data[0]=4503604807335938L;
		data[5]=-6930986397112333312L;
		data[6]=4323455722808146934L;
		data[7]=8L;
		data[8]=36030450583471104L;
		data[9]=36028797018963970L;
		data[10]=9112988597878784L;
		data[11]=567348002028674L;
		return data;
	}
	public static final BitSet _tokenSet_214 = new BitSet(mk_tokenSet_214());
	private static final long[] mk_tokenSet_215() {
		long[] data = new long[24];
		data[0]=4503604807335938L;
		data[5]=-6930986397112333312L;
		data[6]=4323455722808146934L;
		data[7]=8L;
		data[8]=36030450583471104L;
		data[9]=2L;
		data[10]=9112988597878784L;
		data[11]=567348002028674L;
		return data;
	}
	public static final BitSet _tokenSet_215 = new BitSet(mk_tokenSet_215());
	private static final long[] mk_tokenSet_216() {
		long[] data = new long[24];
		data[0]=4556573753351682L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143114007615903219L;
		data[8]=648498284602139868L;
		data[9]=-899116236798110L;
		data[10]=-257833038804145713L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_216 = new BitSet(mk_tokenSet_216());
	private static final long[] mk_tokenSet_217() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114007615903227L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_217 = new BitSet(mk_tokenSet_217());
	private static final long[] mk_tokenSet_218() {
		long[] data = new long[24];
		data[0]=4538964387430402L;
		data[5]=-12525920411735040L;
		data[6]=4395477897322430463L;
		data[7]=-5143114041975641587L;
		data[8]=648498284602139868L;
		data[9]=-3747894006209050782L;
		data[10]=-257833038804145713L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_218 = new BitSet(mk_tokenSet_218());
	private static final long[] mk_tokenSet_219() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3504699626331043998L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_219 = new BitSet(mk_tokenSet_219());
	private static final long[] mk_tokenSet_220() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-2640008497875908766L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_220 = new BitSet(mk_tokenSet_220());
	private static final long[] mk_tokenSet_221() {
		long[] data = new long[24];
		data[0]=35364143497216L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3747894006209050782L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_221 = new BitSet(mk_tokenSet_221());
	private static final long[] mk_tokenSet_222() {
		long[] data = new long[24];
		data[0]=35364747476992L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_222 = new BitSet(mk_tokenSet_222());
	private static final long[] mk_tokenSet_223() {
		long[] data = new long[24];
		data[0]=4556642439273986L;
		data[5]=-12525645533828096L;
		data[6]=4395477897322430463L;
		data[7]=-5143113936748942641L;
		data[8]=720555879176938716L;
		data[9]=-899116236798110L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_223 = new BitSet(mk_tokenSet_223());
	private static final long[] mk_tokenSet_224() {
		long[] data = new long[20];
		data[0]=805306368L;
		data[6]=1073741824L;
		data[9]=525572L;
		return data;
	}
	public static final BitSet _tokenSet_224 = new BitSet(mk_tokenSet_224());
	private static final long[] mk_tokenSet_225() {
		long[] data = new long[24];
		data[0]=4556608079535618L;
		data[5]=-12525645533828096L;
		data[6]=4395477914502299647L;
		data[7]=-5143114007615903025L;
		data[8]=720555879176938716L;
		data[9]=-899116236798110L;
		data[10]=-257833038783157809L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_225 = new BitSet(mk_tokenSet_225());
	private static final long[] mk_tokenSet_226() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517118513L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_226 = new BitSet(mk_tokenSet_226());
	private static final long[] mk_tokenSet_227() {
		long[] data = new long[24];
		data[0]=35364680368128L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_227 = new BitSet(mk_tokenSet_227());
	private static final long[] mk_tokenSet_228() {
		long[] data = new long[24];
		data[0]=4556642472828418L;
		data[5]=-3518446279087104L;
		data[6]=4539593102578155519L;
		data[7]=-5143114007615903009L;
		data[8]=-432365625429908260L;
		data[9]=-899115969937537L;
		data[10]=-257831114637706753L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_228 = new BitSet(mk_tokenSet_228());
	private static final long[] mk_tokenSet_229() {
		long[] data = new long[12];
		data[0]=18052284416L;
		return data;
	}
	public static final BitSet _tokenSet_229 = new BitSet(mk_tokenSet_229());
	private static final long[] mk_tokenSet_230() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455712069680118L;
		data[8]=1649269538816L;
		data[10]=9007199258411008L;
		return data;
	}
	public static final BitSet _tokenSet_230 = new BitSet(mk_tokenSet_230());
	private static final long[] mk_tokenSet_231() {
		long[] data = new long[22];
		data[0]=603979776L;
		data[10]=20987904L;
		return data;
	}
	public static final BitSet _tokenSet_231 = new BitSet(mk_tokenSet_231());
	private static final long[] mk_tokenSet_232() {
		long[] data = new long[22];
		data[10]=16384L;
		return data;
	}
	public static final BitSet _tokenSet_232 = new BitSet(mk_tokenSet_232());
	private static final long[] mk_tokenSet_233() {
		long[] data = new long[22];
		data[8]=72057594574798848L;
		data[10]=262144L;
		return data;
	}
	public static final BitSet _tokenSet_233 = new BitSet(mk_tokenSet_233());
	private static final long[] mk_tokenSet_234() {
		long[] data = new long[24];
		data[0]=2555904L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738747L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_234 = new BitSet(mk_tokenSet_234());
	private static final long[] mk_tokenSet_235() {
		long[] data = new long[24];
		data[0]=35364223188994L;
		data[5]=-12525920411735040L;
		data[6]=2089634808650792959L;
		data[7]=-5143114041975641569L;
		data[8]=612469483288208604L;
		data[9]=-3783922803228014750L;
		data[10]=-257938815241389617L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_235 = new BitSet(mk_tokenSet_235());
	private static final long[] mk_tokenSet_236() {
		long[] data = new long[24];
		data[0]=36044800L;
		data[5]=9192816324770373632L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738745L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_236 = new BitSet(mk_tokenSet_236());
	private static final long[] mk_tokenSet_237() {
		long[] data = new long[24];
		data[0]=2490368L;
		data[5]=9188312725143003136L;
		data[6]=360252551739795263L;
		data[7]=-5143114041977738745L;
		data[8]=36006806837339356L;
		data[9]=-8411371420351199390L;
		data[10]=-266961407679911549L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_237 = new BitSet(mk_tokenSet_237());
	private static final long[] mk_tokenSet_238() {
		long[] data = new long[24];
		data[0]=52973509418496L;
		data[5]=9188328393183698944L;
		data[6]=504367739816703807L;
		data[7]=-5143114007615903219L;
		data[8]=612468383774483676L;
		data[9]=-899116236798110L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_238 = new BitSet(mk_tokenSet_238());
	private static final long[] mk_tokenSet_239() {
		long[] data = new long[18];
		data[0]=282066946L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_239 = new BitSet(mk_tokenSet_239());
	private static final long[] mk_tokenSet_240() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455714217163766L;
		data[8]=1649269538816L;
		data[10]=9112752374677504L;
		return data;
	}
	public static final BitSet _tokenSet_240 = new BitSet(mk_tokenSet_240());
	private static final long[] mk_tokenSet_241() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=2017612702855986166L;
		data[8]=1649269538816L;
		data[10]=9007199258411008L;
		return data;
	}
	public static final BitSet _tokenSet_241 = new BitSet(mk_tokenSet_241());
	private static final long[] mk_tokenSet_242() {
		long[] data = new long[24];
		data[0]=35188016283648L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641595L;
		data[8]=612467834018669788L;
		data[9]=-3792930002482755742L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_242 = new BitSet(mk_tokenSet_242());
	private static final long[] mk_tokenSet_243() {
		long[] data = new long[24];
		data[0]=35364210606080L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641585L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014496134705L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_243 = new BitSet(mk_tokenSet_243());
	private static final long[] mk_tokenSet_244() {
		long[] data = new long[24];
		data[0]=52974113463808L;
		data[5]=9197335592438444032L;
		data[6]=504367756996572991L;
		data[7]=-5143114007615903201L;
		data[8]=-540453120832363300L;
		data[9]=-899115969937537L;
		data[10]=-266944090350683649L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_244 = new BitSet(mk_tokenSet_244());
	private static final long[] mk_tokenSet_245() {
		long[] data = new long[22];
		data[10]=20987904L;
		return data;
	}
	public static final BitSet _tokenSet_245 = new BitSet(mk_tokenSet_245());
	private static final long[] mk_tokenSet_246() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455722807098358L;
		data[8]=1649269538816L;
		data[10]=9112889813630976L;
		return data;
	}
	public static final BitSet _tokenSet_246 = new BitSet(mk_tokenSet_246());
	private static final long[] mk_tokenSet_247() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455722807098358L;
		data[7]=8L;
		data[8]=1649269538816L;
		data[10]=9112752374677504L;
		return data;
	}
	public static final BitSet _tokenSet_247 = new BitSet(mk_tokenSet_247());
	private static final long[] mk_tokenSet_248() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455714217163766L;
		data[8]=1649269538816L;
		data[10]=9007199258411008L;
		return data;
	}
	public static final BitSet _tokenSet_248 = new BitSet(mk_tokenSet_248());
	private static final long[] mk_tokenSet_249() {
		long[] data = new long[22];
		data[0]=8388608L;
		data[6]=68719476736L;
		data[10]=16384L;
		return data;
	}
	public static final BitSet _tokenSet_249 = new BitSet(mk_tokenSet_249());
	private static final long[] mk_tokenSet_250() {
		long[] data = new long[22];
		data[0]=67108864L;
		data[10]=20987904L;
		return data;
	}
	public static final BitSet _tokenSet_250 = new BitSet(mk_tokenSet_250());
	private static final long[] mk_tokenSet_251() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=-6931039036231513088L;
		data[6]=4323455722807098358L;
		data[7]=105226698752L;
		data[8]=1649269538816L;
		data[10]=9112988618866688L;
		return data;
	}
	public static final BitSet _tokenSet_251 = new BitSet(mk_tokenSet_251());
	private static final long[] mk_tokenSet_252() {
		long[] data = new long[24];
		data[0]=35187882065920L;
		data[5]=9188323720259280896L;
		data[6]=360252551739799359L;
		data[7]=-5143114007615903227L;
		data[8]=612467834018669788L;
		data[9]=-3792930001409013918L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_252 = new BitSet(mk_tokenSet_252());
	private static final long[] mk_tokenSet_253() {
		long[] data = new long[24];
		data[0]=35364218994688L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_253 = new BitSet(mk_tokenSet_253());
	private static final long[] mk_tokenSet_254() {
		long[] data = new long[24];
		data[0]=29326646577602048L;
		data[5]=-21532844788964352L;
		data[6]=4539593023120734015L;
		data[7]=-5143114007615903203L;
		data[8]=-468374364951599908L;
		data[9]=-899097716326529L;
		data[10]=-266944090371409409L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_254 = new BitSet(mk_tokenSet_254());
	private static final long[] mk_tokenSet_255() {
		long[] data = new long[24];
		data[0]=35364151885824L;
		data[5]=9188328118305792000L;
		data[6]=360252551739799359L;
		data[7]=-5143114041975641587L;
		data[8]=612468383774483676L;
		data[9]=-3783922803228014750L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_255 = new BitSet(mk_tokenSet_255());
	private static final long[] mk_tokenSet_256() {
		long[] data = new long[24];
		data[0]=29326646510493184L;
		data[5]=-21532844788964352L;
		data[6]=4539593023120734015L;
		data[7]=-5143114007615903203L;
		data[8]=-468374364951599908L;
		data[9]=-899098790068353L;
		data[10]=-266944090371409409L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_256 = new BitSet(mk_tokenSet_256());
	private static final long[] mk_tokenSet_257() {
		long[] data = new long[24];
		data[0]=5182455810L;
		data[5]=-12541313574523904L;
		data[6]=4395477897321381887L;
		data[7]=-5143114005470516731L;
		data[8]=36008456106878172L;
		data[9]=-8411371420351199390L;
		data[10]=-257848431966934653L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_257 = new BitSet(mk_tokenSet_257());
	private static final long[] mk_tokenSet_258() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=-6931039036231513088L;
		data[6]=4323455722807098358L;
		data[7]=36507222016L;
		data[8]=1649269538816L;
		data[10]=9112988597878784L;
		return data;
	}
	public static final BitSet _tokenSet_258 = new BitSet(mk_tokenSet_258());
	private static final long[] mk_tokenSet_259() {
		long[] data = new long[24];
		data[0]=5216010242L;
		data[5]=-12541313574523904L;
		data[6]=4395477897321381887L;
		data[7]=-5143114005470516729L;
		data[8]=36008456106878172L;
		data[9]=-8411371420351199390L;
		data[10]=-257848431966934653L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_259 = new BitSet(mk_tokenSet_259());
	private static final long[] mk_tokenSet_260() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=-6931039173670466560L;
		data[6]=4323455722807098358L;
		data[8]=1649269538816L;
		data[10]=9112988597878784L;
		return data;
	}
	public static final BitSet _tokenSet_260 = new BitSet(mk_tokenSet_260());
	private static final long[] mk_tokenSet_261() {
		long[] data = new long[24];
		data[0]=52974113398272L;
		data[5]=9188328393183698944L;
		data[6]=504367739816703807L;
		data[7]=-5143114007615903219L;
		data[8]=612468383774483676L;
		data[9]=-899116236798110L;
		data[10]=-266946014517122609L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_261 = new BitSet(mk_tokenSet_261());
	private static final long[] mk_tokenSet_262() {
		long[] data = new long[12];
		data[0]=4398046511104L;
		return data;
	}
	public static final BitSet _tokenSet_262 = new BitSet(mk_tokenSet_262());
	private static final long[] mk_tokenSet_263() {
		long[] data = new long[24];
		data[0]=5182455810L;
		data[5]=-12541313574523904L;
		data[6]=4395477897321381887L;
		data[7]=-5143114005470516729L;
		data[8]=36008456106878172L;
		data[9]=-8411371420351199390L;
		data[10]=-257848431966934653L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_263 = new BitSet(mk_tokenSet_263());
	private static final long[] mk_tokenSet_264() {
		long[] data = new long[24];
		data[0]=5182455810L;
		data[5]=-12541313574523904L;
		data[6]=4395477897321381887L;
		data[7]=-5143113936751039995L;
		data[8]=36008456106878172L;
		data[9]=-8411371420351199390L;
		data[10]=-257848431966934653L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_264 = new BitSet(mk_tokenSet_264());
	private static final long[] mk_tokenSet_265() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=-6931039036231513088L;
		data[6]=4323455722807098358L;
		data[7]=105226698752L;
		data[8]=1649269538816L;
		data[10]=9112988597878784L;
		return data;
	}
	public static final BitSet _tokenSet_265 = new BitSet(mk_tokenSet_265());
	private static final long[] mk_tokenSet_266() {
		long[] data = new long[24];
		data[0]=5182455810L;
		data[5]=-12541313574523904L;
		data[6]=4395477897321381887L;
		data[7]=-5143113936751039993L;
		data[8]=36008456106878172L;
		data[9]=-8411371420351199390L;
		data[10]=-257848431966934653L;
		data[11]=36028797018963967L;
		return data;
	}
	public static final BitSet _tokenSet_266 = new BitSet(mk_tokenSet_266());
	private static final long[] mk_tokenSet_267() {
		long[] data = new long[22];
		data[0]=5112856578L;
		data[5]=-6931039173670466560L;
		data[6]=4323455722807098358L;
		data[8]=1649269538816L;
		data[10]=9112752374677504L;
		return data;
	}
	public static final BitSet _tokenSet_267 = new BitSet(mk_tokenSet_267());
	private static final long[] mk_tokenSet_268() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=-6931039173670466560L;
		data[6]=4323455712069680118L;
		data[8]=1649269538816L;
		data[10]=9007199258411008L;
		return data;
	}
	public static final BitSet _tokenSet_268 = new BitSet(mk_tokenSet_268());
	private static final long[] mk_tokenSet_269() {
		long[] data = new long[22];
		data[0]=348127234L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[8]=1649269538816L;
		data[10]=9007199254740992L;
		return data;
	}
	public static final BitSet _tokenSet_269 = new BitSet(mk_tokenSet_269());
	private static final long[] mk_tokenSet_270() {
		long[] data = new long[18];
		data[0]=281018370L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[7]=8L;
		data[8]=1649267441664L;
		return data;
	}
	public static final BitSet _tokenSet_270 = new BitSet(mk_tokenSet_270());
	private static final long[] mk_tokenSet_271() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=-6931039173670466560L;
		data[6]=2017612633062767606L;
		data[7]=8L;
		data[8]=1649267441664L;
		data[10]=137438953472L;
		return data;
	}
	public static final BitSet _tokenSet_271 = new BitSet(mk_tokenSet_271());
	private static final long[] mk_tokenSet_272() {
		long[] data = new long[22];
		data[10]=36028797018963968L;
		return data;
	}
	public static final BitSet _tokenSet_272 = new BitSet(mk_tokenSet_272());
	private static final long[] mk_tokenSet_273() {
		long[] data = new long[24];
		data[11]=549755813888L;
		return data;
	}
	public static final BitSet _tokenSet_273 = new BitSet(mk_tokenSet_273());
	private static final long[] mk_tokenSet_274() {
		long[] data = new long[12];
		data[0]=621805568L;
		return data;
	}
	public static final BitSet _tokenSet_274 = new BitSet(mk_tokenSet_274());
	private static final long[] mk_tokenSet_275() {
		long[] data = new long[12];
		data[0]=607256576L;
		return data;
	}
	public static final BitSet _tokenSet_275 = new BitSet(mk_tokenSet_275());
	private static final long[] mk_tokenSet_276() {
		long[] data = new long[24];
		data[10]=2305843009213693952L;
		data[11]=549755813888L;
		return data;
	}
	public static final BitSet _tokenSet_276 = new BitSet(mk_tokenSet_276());
	private static final long[] mk_tokenSet_277() {
		long[] data = new long[24];
		data[0]=603979776L;
		data[5]=549755813888L;
		data[9]=2L;
		data[11]=787250327583874L;
		return data;
	}
	public static final BitSet _tokenSet_277 = new BitSet(mk_tokenSet_277());
	private static final long[] mk_tokenSet_278() {
		long[] data = new long[24];
		data[9]=2L;
		data[11]=567348000980098L;
		return data;
	}
	public static final BitSet _tokenSet_278 = new BitSet(mk_tokenSet_278());
	private static final long[] mk_tokenSet_279() {
		long[] data = new long[24];
		data[0]=536870912L;
		data[5]=549755813888L;
		data[11]=1048576L;
		return data;
	}
	public static final BitSet _tokenSet_279 = new BitSet(mk_tokenSet_279());
	private static final long[] mk_tokenSet_280() {
		long[] data = new long[12];
		data[0]=536870912L;
		data[5]=549755813888L;
		return data;
	}
	public static final BitSet _tokenSet_280 = new BitSet(mk_tokenSet_280());
	private static final long[] mk_tokenSet_281() {
		long[] data = new long[24];
		data[0]=536870912L;
		data[5]=549755813888L;
		data[9]=2L;
		data[11]=567348002028674L;
		return data;
	}
	public static final BitSet _tokenSet_281 = new BitSet(mk_tokenSet_281());
	private static final long[] mk_tokenSet_282() {
		long[] data = new long[24];
		data[0]=872415232L;
		data[5]=549755813888L;
		data[7]=1125899906842624L;
		data[11]=2305843010415362048L;
		return data;
	}
	public static final BitSet _tokenSet_282 = new BitSet(mk_tokenSet_282());
	private static final long[] mk_tokenSet_283() {
		long[] data = new long[24];
		data[0]=872415232L;
		data[5]=549755813888L;
		data[7]=1125899906842624L;
		data[11]=1075838976L;
		return data;
	}
	public static final BitSet _tokenSet_283 = new BitSet(mk_tokenSet_283());
	private static final long[] mk_tokenSet_284() {
		long[] data = new long[12];
		data[0]=620756992L;
		return data;
	}
	public static final BitSet _tokenSet_284 = new BitSet(mk_tokenSet_284());
	private static final long[] mk_tokenSet_285() {
		long[] data = new long[24];
		data[0]=603979776L;
		data[9]=7667717L;
		data[11]=478888853504L;
		return data;
	}
	public static final BitSet _tokenSet_285 = new BitSet(mk_tokenSet_285());
	
	}
