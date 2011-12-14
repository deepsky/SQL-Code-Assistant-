// $ANTLR 2.7.5 (20050128): "/git_app/sqlassistant_super/github/shared/root/grammars/plsql_parser_ex.g" -> "PLSqlParserAdopted.java"$

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

//    public void handle_ws( int action ){
//    }

    protected void process_wrapped_package(String package_name){
        // default action if the package is wrapped
        throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
    }

protected PLSqlParserAdopted(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public PLSqlParserAdopted(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected PLSqlParserAdopted(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public PLSqlParserAdopted(TokenStream lexer) {
  this(lexer,3);
}

public PLSqlParserAdopted(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
}

	public void no_one_should_call_me() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(ERROR_TOKEN_A);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void identifier() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				match(IDENTIFIER);
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
	}
	
	public void start_rule() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop4:
			do {
				if ((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					start_rule_inner();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void start_rule_inner() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_create:
			{
				{
				create_or_replace();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_function:
			{
				{
				function_body();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_procedure:
			{
				{
				procedure_body();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_trigger:
			{
				{
				create_trigger();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case OPEN_PAREN:
			case LITERAL_commit:
			case LITERAL_rollback:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			case LITERAL_merge:
			{
				{
				sql_statement();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_alter:
			{
				{
				alter_command();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_associate:
			{
				{
				associate_statistics();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_comment:
			{
				{
				comment();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_type:
			{
				{
				type_definition();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_drop:
			{
				{
				drop_command();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_truncate:
			{
				{
				truncate_command();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_host:
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
				break;
			}
			default:
				boolean synPredMatched9 = false;
				if (((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3))))) {
					int _m9 = mark();
					synPredMatched9 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_package);
						match(LITERAL_body);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched9 = false;
					}
					rewind(_m9);
					inputState.guessing--;
				}
				if ( synPredMatched9 ) {
					{
					package_body();
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
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
						__markRule(PACKAGE_BODY);
					}
				}
				else {
					boolean synPredMatched13 = false;
					if (((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_7.member(LA(3))))) {
						int _m13 = mark();
						synPredMatched13 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_package);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched13 = false;
						}
						rewind(_m13);
						inputState.guessing--;
					}
					if ( synPredMatched13 ) {
						{
						package_spec();
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
						case LITERAL_package:
						case LITERAL_alter:
						case LITERAL_drop:
						case LITERAL_function:
						case LITERAL_procedure:
						case LITERAL_type:
						case LITERAL_trigger:
						case LITERAL_associate:
						case LITERAL_column:
						case LITERAL_truncate:
						case LITERAL_comment:
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
						case LITERAL_host:
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
						case LITERAL_grant:
						case LITERAL_revoke:
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
							__markRule(PACKAGE_SPEC);
						}
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_4);
				} else {
				  throw ex;
				}
			}
		}
		
	public void create_or_replace() throws RecognitionException, TokenStreamException {
		
		
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
			case LITERAL_package:
			case LITERAL_table:
			case LITERAL_view:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_index:
			case LITERAL_force:
			case LITERAL_sequence:
			case LITERAL_type:
			case LITERAL_public:
			case LITERAL_synonym:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_global:
			case LITERAL_temporary:
			case LITERAL_bigfile:
			case LITERAL_smallfile:
			case LITERAL_tablespace:
			case LITERAL_undo:
			case LITERAL_unique:
			case LITERAL_bitmap:
			case 626:
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
				match(LITERAL_force);
				break;
			}
			case LITERAL_package:
			case LITERAL_table:
			case LITERAL_view:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_index:
			case LITERAL_sequence:
			case LITERAL_type:
			case LITERAL_public:
			case LITERAL_synonym:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_global:
			case LITERAL_temporary:
			case LITERAL_bigfile:
			case LITERAL_smallfile:
			case LITERAL_tablespace:
			case LITERAL_undo:
			case LITERAL_unique:
			case LITERAL_bitmap:
			case 626:
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
				break;
			}
			case LITERAL_function:
			{
				function_body();
				break;
			}
			case LITERAL_view:
			{
				create_view();
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_VIEW);
				}
				break;
			}
			case 626:
			{
				create_view_column_def();
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_VIEW_COLUMN_DEF);
				}
				break;
			}
			case LITERAL_type:
			{
				{
				type_definition();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_index:
			case LITERAL_unique:
			case LITERAL_bitmap:
			{
				{
				create_index2();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_public:
			case LITERAL_synonym:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_public:
				{
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
				create_synonym();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
					__markRule(CREATE_SYNONYM);
				}
				break;
			}
			default:
				if ((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_7.member(LA(3)))) {
					package_spec();
					if ( inputState.guessing==0 ) {
						__markRule(PACKAGE_SPEC);
					}
				}
				else if ((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					package_body();
					if ( inputState.guessing==0 ) {
						__markRule(PACKAGE_BODY);
					}
				}
				else {
					boolean synPredMatched243 = false;
					if (((LA(1)==LITERAL_global||LA(1)==LITERAL_temporary) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))))) {
						int _m243 = mark();
						synPredMatched243 = true;
						inputState.guessing++;
						try {
							{
							{
							switch ( LA(1)) {
							case LITERAL_global:
							{
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
							{
							if ((_tokenSet_5.member(LA(1)))) {
								schema_name();
								match(DOT);
							}
							else if ((LA(1)==LITERAL_table)) {
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
							match(LITERAL_table);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched243 = false;
						}
						rewind(_m243);
						inputState.guessing--;
					}
					if ( synPredMatched243 ) {
						{
						create_temp_table();
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
						case LITERAL_package:
						case LITERAL_alter:
						case LITERAL_drop:
						case LITERAL_function:
						case LITERAL_procedure:
						case LITERAL_type:
						case LITERAL_trigger:
						case LITERAL_associate:
						case LITERAL_column:
						case LITERAL_truncate:
						case LITERAL_comment:
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
						case LITERAL_host:
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
						case LITERAL_grant:
						case LITERAL_revoke:
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
					}
					else if ((_tokenSet_10.member(LA(1))) && (_tokenSet_11.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
						{
						create_tablespace();
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
						case LITERAL_package:
						case LITERAL_alter:
						case LITERAL_drop:
						case LITERAL_function:
						case LITERAL_procedure:
						case LITERAL_type:
						case LITERAL_trigger:
						case LITERAL_associate:
						case LITERAL_column:
						case LITERAL_truncate:
						case LITERAL_comment:
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
						case LITERAL_host:
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
						case LITERAL_grant:
						case LITERAL_revoke:
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
							__markRule(CREATE_TABLESPACE);
						}
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_12);
				} else {
				  throw ex;
				}
			}
		}
		
	public void package_body() throws RecognitionException, TokenStreamException {
		
		
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
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==LITERAL_is||LA(2)==LITERAL_as)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			package_name();
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
				match(SEMI);
			}
			else if ((_tokenSet_13.member(LA(1))) && (_tokenSet_14.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop774:
			do {
				if ((_tokenSet_15.member(LA(1)))) {
					package_obj_body();
				}
				else {
					break _loop774;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_begin:
			{
				package_init_section();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_package);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_16.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			package_name();
			{
			switch ( LA(1)) {
			case LITERAL_authid:
			{
				match(LITERAL_authid);
				identifier();
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
				match(LITERAL_wrapped);
				if ( inputState.guessing==0 ) {
					
					throw new com.deepsky.lang.plsql.parser.WrappedPackageException();
					//                String package_name = #o.getFirstChild().getText();
					// todo                String package_name = #o.getText();
					//                throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
					// todo                 process_wrapped_package(package_name);
					
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
					match(SEMI);
				}
				else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_18.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop757:
				do {
					if ((_tokenSet_15.member(LA(1)))) {
						package_obj_spec_ex();
					}
					else {
						break _loop757;
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
	}
	
	public void function_body() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			function_declaration();
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
				}
				if ( inputState.guessing==0 ) {
					__markRule(FUNCTION_BODY);
				}
				break;
			}
			case LITERAL_language:
			{
				{
				match(LITERAL_language);
				match(LITERAL_java);
				match(LITERAL_name);
				string_literal();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
	}
	
	public void procedure_body() throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		Token  a = null;
		
		try {      // for error handling
			procedure_declaration();
			{
			switch ( LA(1)) {
			case LITERAL_is:
			{
				i = LT(1);
				match(LITERAL_is);
				break;
			}
			case LITERAL_as:
			{
				a = LT(1);
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
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_BODY);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_trigger);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_after||LA(2)==LITERAL_before||LA(2)==LITERAL_instead)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			trigger_name();
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
					match(LITERAL_after);
					break;
				}
				case LITERAL_before:
				{
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
					break;
				}
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_associate:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_create:
				case LITERAL_rename:
				case LITERAL_analyze:
				case LITERAL_audit:
				case LITERAL_noaudit:
				case LITERAL_ddl:
				case LITERAL_diassociate:
				case LITERAL_grant:
				case LITERAL_revoke:
				{
					ddl_trigger();
					break;
				}
				case LITERAL_startup:
				case LITERAL_shutdown:
				case LITERAL_servererror:
				case LITERAL_logon:
				case LITERAL_logoff:
				{
					db_event_trigger();
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
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop346:
			do {
				switch ( LA(1)) {
				case LITERAL_for:
				{
					for_each();
					break;
				}
				case LITERAL_referencing:
				{
					referencing_old_new();
					break;
				}
				default:
				{
					break _loop346;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_when:
			{
				trigger_when();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sql_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case OPEN_PAREN:
			case LITERAL_select:
			{
				select_command();
				break;
			}
			case LITERAL_insert:
			{
				insert_command();
				break;
			}
			case LITERAL_update:
			{
				update_command();
				break;
			}
			case LITERAL_delete:
			{
				delete_command();
				break;
			}
			case LITERAL_merge:
			{
				merge_command();
				break;
			}
			case LITERAL_grant:
			case LITERAL_revoke:
			{
				grant_revoke_command();
				break;
			}
			case LITERAL_commit:
			{
				commit_statement();
				break;
			}
			case LITERAL_rollback:
			{
				rollback_statement();
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
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_alter);
			{
			switch ( LA(1)) {
			case LITERAL_database:
			case LITERAL_system:
			case LITERAL_session:
			{
				alter_system_session();
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_GENERIC);
				}
				break;
			}
			case LITERAL_table:
			{
				alter_table();
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_TABLE);
				}
				break;
			}
			case LITERAL_trigger:
			{
				alter_trigger();
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_TRIGGER);
				}
				break;
			}
			case LITERAL_tablespace:
			{
				alter_tablespace();
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_TABLESPACE);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void associate_statistics() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_associate);
			match(LITERAL_statistics);
			match(LITERAL_with);
			{
			switch ( LA(1)) {
			case LITERAL_column:
			{
				column_association();
				break;
			}
			case LITERAL_functions:
			case LITERAL_packages:
			case LITERAL_types:
			case LITERAL_indexes:
			case LITERAL_indextypes:
			{
				function_association();
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
				storage_table_clause();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void comment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_comment);
			match(LITERAL_on);
			{
			switch ( LA(1)) {
			case LITERAL_table:
			{
				{
				match(LITERAL_table);
				table_ref();
				match(LITERAL_is);
				comment_string();
				}
				break;
			}
			case LITERAL_column:
			{
				{
				match(LITERAL_column);
				table_ref();
				match(DOT);
				column_name_ref();
				match(LITERAL_is);
				comment_string();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_definition() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_type);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is||LA(2)==LITERAL_as||LA(2)==LITERAL_under)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			type_name();
			{
			switch ( LA(1)) {
			case LITERAL_under:
			{
				{
				match(LITERAL_under);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3)))) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_5.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				type_name_ref();
				match(OPEN_PAREN);
				record_item();
				{
				_loop713:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						record_item();
					}
					else {
						break _loop713;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_not:
				{
					match(LITERAL_not);
					match(LITERAL_final);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
					__markRule(OBJECT_TYPE_DEF);
				}
				break;
			}
			case LITERAL_is:
			case LITERAL_as:
			{
				{
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
					{
					_loop720:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop720;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
					{
					switch ( LA(1)) {
					case LITERAL_not:
					{
						match(LITERAL_not);
						match(LITERAL_final);
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
					{
					switch ( LA(1)) {
					case LITERAL_index:
					{
						match(LITERAL_index);
						match(LITERAL_by);
						type_spec();
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
						match(LITERAL_not);
						match(LITERAL_null);
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
					match(LITERAL_record);
					match(OPEN_PAREN);
					record_item();
					{
					_loop727:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop727;
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
					match(LITERAL_ref);
					match(LITERAL_cursor);
					{
					switch ( LA(1)) {
					case LITERAL_return:
					{
						match(LITERAL_return);
						record_name();
						{
						switch ( LA(1)) {
						case PERCENTAGE:
						{
							match(PERCENTAGE);
							match(LITERAL_rowtype);
							break;
						}
						case EOF:
						case AT_PREFIXED:
						case SEMI:
						case OPEN_PAREN:
						case DIVIDE:
						case LITERAL_package:
						case LITERAL_alter:
						case LITERAL_drop:
						case LITERAL_function:
						case LITERAL_procedure:
						case LITERAL_type:
						case LITERAL_trigger:
						case LITERAL_associate:
						case LITERAL_column:
						case LITERAL_truncate:
						case LITERAL_comment:
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
						case LITERAL_host:
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
						case LITERAL_grant:
						case LITERAL_revoke:
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
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
					match(CLOSE_PAREN);
					match(LITERAL_of);
					type_spec();
					{
					switch ( LA(1)) {
					case LITERAL_not:
					{
						match(LITERAL_not);
						match(LITERAL_null);
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_grant:
					case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void drop_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_drop);
			{
			switch ( LA(1)) {
			case LITERAL_table:
			{
				{
				match(LITERAL_table);
				table_ref();
				{
				switch ( LA(1)) {
				case LITERAL_purge:
				{
					match(LITERAL_purge);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_view);
				table_ref();
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					match(LITERAL_constraints);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_function);
				callable_name_ref();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_FUNCTION);
				}
				break;
			}
			case LITERAL_procedure:
			{
				{
				match(LITERAL_procedure);
				callable_name_ref();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_PROCEDURE);
				}
				break;
			}
			case LITERAL_package:
			{
				{
				match(LITERAL_package);
				{
				if ((LA(1)==LITERAL_body) && (_tokenSet_5.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
					match(LITERAL_body);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_22.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_21.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				package_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_PACKAGE);
				}
				break;
			}
			case LITERAL_index:
			{
				{
				match(LITERAL_index);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_23.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				index_name();
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					match(LITERAL_force);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_sequence);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_SEQUENCE);
				}
				break;
			}
			case LITERAL_type:
			{
				{
				match(LITERAL_type);
				{
				if ((LA(1)==LITERAL_body) && (_tokenSet_24.member(LA(2))) && (_tokenSet_25.member(LA(3)))) {
					match(LITERAL_body);
				}
				else if ((_tokenSet_24.member(LA(1))) && (_tokenSet_25.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				object_name();
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					match(LITERAL_force);
					break;
				}
				case LITERAL_validate:
				{
					match(LITERAL_validate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_synonym);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_SYNONYM);
				}
				break;
			}
			case LITERAL_operator:
			{
				{
				match(LITERAL_operator);
				object_name();
				{
				switch ( LA(1)) {
				case LITERAL_force:
				{
					match(LITERAL_force);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_user);
				object_name();
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				match(LITERAL_role);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_ROLE);
				}
				break;
			}
			case LITERAL_directory:
			{
				{
				match(LITERAL_directory);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_DIRECTORY);
				}
				break;
			}
			case LITERAL_library:
			{
				{
				match(LITERAL_library);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_LIBRARY);
				}
				break;
			}
			case LITERAL_database:
			{
				{
				match(LITERAL_database);
				match(LITERAL_link);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_DB_LINK);
				}
				break;
			}
			case LITERAL_trigger:
			{
				{
				match(LITERAL_trigger);
				object_name();
				}
				if ( inputState.guessing==0 ) {
					__markRule(DROP_TRIGGER);
				}
				break;
			}
			case LITERAL_tablespace:
			{
				drop_tablespace();
				if ( inputState.guessing==0 ) {
					__markRule(DROP_TABLESPACE);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void truncate_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_truncate);
			match(LITERAL_table);
			table_ref();
			if ( inputState.guessing==0 ) {
				__markRule(TRUNCATE_TABLE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sqlplus_command_internal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt149=0;
			_loop149:
			do {
				if ((_tokenSet_26.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sqlplus_command();
				}
				else {
					if ( _cnt149>=1 ) { break _loop149; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt149++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(SQLPLUS_COMMAND);
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
	}
	
	public void table_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			} else {
			  throw ex;
			}
		}
	}
	
	public void callable_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop1136:
			do {
				if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment_ex();
					match(DOT);
				}
				else {
					break _loop1136;
				}
				
			} while (true);
			}
			exec_name_ref();
			if ( inputState.guessing==0 ) {
				__markRule(CALLABLE_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void schema_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(SCHEMA_NAME);
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
	}
	
	public void package_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(PACKAGE_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
	}
	
	public void index_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(INDEX_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_30);
			} else {
			  throw ex;
			}
		}
	}
	
	public void object_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier3();
			{
			switch ( LA(1)) {
			case DOT:
			{
				match(DOT);
				identifier2();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_force:
			case LITERAL_type:
			case LITERAL_validate:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_is:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
	}
	
	public void drop_tablespace() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_tablespace);
			tablespace_name();
			{
			switch ( LA(1)) {
			case LITERAL_including:
			{
				match(LITERAL_including);
				match(LITERAL_contents);
				{
				switch ( LA(1)) {
				case LITERAL_and:
				{
					match(LITERAL_and);
					match(LITERAL_datafiles);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_cascade:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					match(LITERAL_constraints);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_association() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_column);
			column_spec_ex();
			{
			_loop73:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec_ex();
				}
				else {
					break _loop73;
				}
				
			} while (true);
			}
			using_statistics_type();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void function_association() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_functions:
			{
				{
				match(LITERAL_functions);
				ident_list();
				{
				_loop81:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ident_list();
					}
					else {
						break _loop81;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_packages:
			{
				{
				match(LITERAL_packages);
				ident_list();
				{
				_loop84:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ident_list();
					}
					else {
						break _loop84;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_types:
			{
				{
				match(LITERAL_types);
				ident_list();
				{
				_loop87:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ident_list();
					}
					else {
						break _loop87;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_indexes:
			{
				{
				match(LITERAL_indexes);
				ident_list();
				{
				_loop90:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ident_list();
					}
					else {
						break _loop90;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_indextypes:
			{
				{
				match(LITERAL_indextypes);
				ident_list();
				{
				_loop93:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ident_list();
					}
					else {
						break _loop93;
					}
					
				} while (true);
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
			{
			switch ( LA(1)) {
			case LITERAL_default:
			{
				default_clause();
				break;
			}
			case LITERAL_using:
			{
				using_statistics_type();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void storage_table_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_with);
			{
			switch ( LA(1)) {
			case LITERAL_user:
			{
				match(LITERAL_user);
				break;
			}
			case LITERAL_system:
			{
				match(LITERAL_system);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_managed);
			match(LITERAL_storage);
			match(LITERAL_table);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_spec_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt76=0;
			_loop76:
			do {
				if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment2();
					match(DOT);
				}
				else {
					if ( _cnt76>=1 ) { break _loop76; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt76++;
			} while (true);
			}
			column_name_ref();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
	}
	
	public void using_statistics_type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_using);
			{
			if ((LA(1)==LITERAL_null)) {
				match(LITERAL_null);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				{
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_32.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				statistics_type();
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void name_fragment2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_timestamp)) {
				match(LITERAL_timestamp);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_timestamp)) {
				match(LITERAL_timestamp);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ident_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==COMMA||LA(2)==LITERAL_default||LA(2)==LITERAL_using)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			} else {
			  throw ex;
			}
		}
	}
	
	public void default_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_default);
			{
			switch ( LA(1)) {
			case LITERAL_cost:
			{
				{
				match(LITERAL_cost);
				match(OPEN_PAREN);
				numeric_literal();
				match(COMMA);
				numeric_literal();
				match(COMMA);
				numeric_literal();
				match(CLOSE_PAREN);
				}
				break;
			}
			case LITERAL_selectivity:
			{
				{
				match(LITERAL_selectivity);
				numeric_literal();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void numeric_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(NUMBER);
			if ( inputState.guessing==0 ) {
				__markRule(NUMERIC_LITERAL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_38);
			} else {
			  throw ex;
			}
		}
	}
	
	public void statistics_type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void comment_string() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			string_literal();
			if ( inputState.guessing==0 ) {
				__markRule(COMMENT_STR);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void string_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1345=0;
			_loop1345:
			do {
				if ((LA(1)==QUOTED_STR) && (_tokenSet_39.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1345>=1 ) { break _loop1345; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1345++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STRING_LITERAL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_39);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ddl();
			type_spec();
			{
			switch ( LA(1)) {
			case LITERAL_default:
			{
				match(LITERAL_default);
				{
				switch ( LA(1)) {
				case LITERAL_sysdate:
				{
					match(LITERAL_sysdate);
					break;
				}
				case LITERAL_systimestamp:
				{
					match(LITERAL_systimestamp);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
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
			case LITERAL_null:
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
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
			if ((LA(1)==LITERAL_null||LA(1)==LITERAL_not) && (_tokenSet_40.member(LA(2))) && (_tokenSet_41.member(LA(3)))) {
				not_null();
			}
			else if ((_tokenSet_42.member(LA(1))) && (_tokenSet_43.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
			{
				column_constraint2();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_name_ddl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_timestamp)) {
				match(LITERAL_timestamp);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_DDL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_46.member(LA(1))) && (_tokenSet_47.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				datatype();
			}
			else {
				boolean synPredMatched1017 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==PERCENTAGE) && (_tokenSet_48.member(LA(3))))) {
					int _m1017 = mark();
					synPredMatched1017 = true;
					inputState.guessing++;
					try {
						{
						table_ref();
						{
						switch ( LA(1)) {
						case DOT:
						{
							{
							match(DOT);
							column_name_ref();
							match(PERCENTAGE);
							match(LITERAL_type);
							}
							break;
						}
						case PERCENTAGE:
						{
							{
							match(PERCENTAGE);
							match(LITERAL_rowtype);
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
					}
					catch (RecognitionException pe) {
						synPredMatched1017 = false;
					}
					rewind(_m1017);
					inputState.guessing--;
				}
				if ( synPredMatched1017 ) {
					percentage_type();
				}
				else {
					boolean synPredMatched1022 = false;
					if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3))))) {
						int _m1022 = mark();
						synPredMatched1022 = true;
						inputState.guessing++;
						try {
							{
							schema_name();
							match(DOT);
							table_ref();
							{
							switch ( LA(1)) {
							case DOT:
							{
								{
								match(DOT);
								column_name_ref();
								match(PERCENTAGE);
								match(LITERAL_type);
								}
								break;
							}
							case PERCENTAGE:
							{
								{
								match(PERCENTAGE);
								match(LITERAL_rowtype);
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
						}
						catch (RecognitionException pe) {
							synPredMatched1022 = false;
						}
						rewind(_m1022);
						inputState.guessing--;
					}
					if ( synPredMatched1022 ) {
						percentage_type_w_schema();
					}
					else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_49.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						type_name_ref();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}
					}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						reportError(ex);
						recover(ex,_tokenSet_50);
					} else {
					  throw ex;
					}
				}
			}
			
	public void not_null() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
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
			match(LITERAL_null);
			{
			if ((LA(1)==LITERAL_disable) && (_tokenSet_51.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_disable);
			}
			else if ((LA(1)==LITERAL_enable) && (_tokenSet_51.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_enable);
			}
			else if ((_tokenSet_51.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(NOT_NULL_STMT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_constraint2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				match(LITERAL_constraint);
				constraint_name();
				break;
			}
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_check:
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
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_52.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_52.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_52.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_PK_SPEC);
				}
				break;
			}
			case LITERAL_references:
			{
				{
				match(LITERAL_references);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==OPEN_PAREN)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				table_ref();
				match(OPEN_PAREN);
				column_name_ref();
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_rely:
				{
					match(LITERAL_rely);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case COMMA:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				if ((LA(1)==LITERAL_disable) && (_tokenSet_52.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_52.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_52.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_FK_SPEC);
				}
				break;
			}
			case LITERAL_check:
			{
				{
				match(LITERAL_check);
				condition();
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_CHECK_CONSTRAINT);
				}
				break;
			}
			case LITERAL_not:
			{
				{
				match(LITERAL_not);
				match(LITERAL_null);
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_NOT_NULL_CONSTRAINT);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
	}
	
	public void row_movement_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_disable:
			{
				match(LITERAL_disable);
				break;
			}
			case LITERAL_enable:
			{
				match(LITERAL_enable);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_row);
			match(LITERAL_movement);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void pk_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_primary);
			match(LITERAL_key);
			{
			switch ( LA(1)) {
			case LITERAL_disable:
			{
				match(LITERAL_disable);
				break;
			}
			case LITERAL_enable:
			{
				match(LITERAL_enable);
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
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_PK_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void fk_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_references);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==OPEN_PAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_ref();
			match(OPEN_PAREN);
			column_name_ref();
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_rely:
			{
				match(LITERAL_rely);
				break;
			}
			case EOF:
			case LITERAL_disable:
			case LITERAL_enable:
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
			case LITERAL_disable:
			{
				match(LITERAL_disable);
				break;
			}
			case LITERAL_enable:
			{
				match(LITERAL_enable);
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
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_FK_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void constraint_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(CONSTRAINT_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_54);
			} else {
			  throw ex;
			}
		}
	}
	
	public void condition() throws RecognitionException, TokenStreamException {
		
		boolean tag1= false;
		
		try {      // for error handling
			logical_term();
			{
			_loop1212:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					logical_term();
				}
				else {
					break _loop1212;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(LOGICAL_EXPR);
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_55);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_constraint);
			identifier();
			{
			switch ( LA(1)) {
			case LITERAL_null:
			case LITERAL_not:
			{
				not_null();
				break;
			}
			case LITERAL_primary:
			{
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop145:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop145;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_disable:
				{
					match(LITERAL_disable);
					break;
				}
				case LITERAL_enable:
				{
					match(LITERAL_enable);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void identifier2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				match(IDENTIFIER);
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			case PLSQL_ENV_VAR:
			{
				match(PLSQL_ENV_VAR);
				break;
			}
			case LITERAL_left:
			{
				match(LITERAL_left);
				break;
			}
			case LITERAL_right:
			{
				match(LITERAL_right);
				break;
			}
			case LITERAL_type:
			{
				match(LITERAL_type);
				break;
			}
			case LITERAL_count:
			{
				match(LITERAL_count);
				break;
			}
			case LITERAL_open:
			{
				match(LITERAL_open);
				break;
			}
			case LITERAL_exec:
			{
				match(LITERAL_exec);
				break;
			}
			case LITERAL_user:
			{
				match(LITERAL_user);
				break;
			}
			case LITERAL_dbtimezone:
			{
				match(LITERAL_dbtimezone);
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
			case LITERAL_savepoint:
			{
				match(LITERAL_savepoint);
				break;
			}
			case LITERAL_comment:
			{
				match(LITERAL_comment);
				break;
			}
			case LITERAL_charset:
			{
				match(LITERAL_charset);
				break;
			}
			case LITERAL_body:
			{
				match(LITERAL_body);
				break;
			}
			case LITERAL_escape:
			{
				match(LITERAL_escape);
				break;
			}
			case LITERAL_reverse:
			{
				match(LITERAL_reverse);
				break;
			}
			case LITERAL_exists:
			{
				match(LITERAL_exists);
				break;
			}
			case LITERAL_delete:
			{
				match(LITERAL_delete);
				break;
			}
			case LITERAL_trim:
			{
				match(LITERAL_trim);
				break;
			}
			case LITERAL_flush:
			{
				match(LITERAL_flush);
				break;
			}
			case LITERAL_interval:
			{
				match(LITERAL_interval);
				break;
			}
			case LITERAL_transaction:
			{
				match(LITERAL_transaction);
				break;
			}
			case LITERAL_session:
			{
				match(LITERAL_session);
				break;
			}
			case LITERAL_close:
			{
				match(LITERAL_close);
				break;
			}
			case LITERAL_read:
			{
				match(LITERAL_read);
				break;
			}
			case LITERAL_write:
			{
				match(LITERAL_write);
				break;
			}
			case LITERAL_only:
			{
				match(LITERAL_only);
				break;
			}
			case LITERAL_normal:
			{
				match(LITERAL_normal);
				break;
			}
			case LITERAL_immediate:
			{
				match(LITERAL_immediate);
				break;
			}
			case LITERAL_replace:
			{
				match(LITERAL_replace);
				break;
			}
			case LITERAL_sid:
			{
				match(LITERAL_sid);
				break;
			}
			case LITERAL_local:
			{
				match(LITERAL_local);
				break;
			}
			case LITERAL_time:
			{
				match(LITERAL_time);
				break;
			}
			case LITERAL_name:
			{
				match(LITERAL_name);
				break;
			}
			case LITERAL_default:
			{
				match(LITERAL_default);
				break;
			}
			case LITERAL_package:
			{
				match(LITERAL_package);
				break;
			}
			case LITERAL_ref:
			{
				match(LITERAL_ref);
				break;
			}
			case LITERAL_byte:
			{
				match(LITERAL_byte);
				break;
			}
			case LITERAL_interface:
			{
				match(LITERAL_interface);
				break;
			}
			case LITERAL_extract:
			{
				match(LITERAL_extract);
				break;
			}
			case LITERAL_next:
			{
				match(LITERAL_next);
				break;
			}
			case LITERAL_column:
			{
				match(LITERAL_column);
				break;
			}
			case LITERAL_col:
			{
				match(LITERAL_col);
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
			case LITERAL_rowcount:
			{
				match(LITERAL_rowcount);
				break;
			}
			case LITERAL_isopen:
			{
				match(LITERAL_isopen);
				break;
			}
			case LITERAL_bulk_rowcount:
			{
				match(LITERAL_bulk_rowcount);
				break;
			}
			case LITERAL_bulk_exceptions:
			{
				match(LITERAL_bulk_exceptions);
				break;
			}
			case LITERAL_nocache:
			{
				match(LITERAL_nocache);
				break;
			}
			case LITERAL_cache:
			{
				match(LITERAL_cache);
				break;
			}
			case LITERAL_compress:
			{
				match(LITERAL_compress);
				break;
			}
			case LITERAL_deterministic:
			{
				match(LITERAL_deterministic);
				break;
			}
			case LITERAL_degree:
			{
				match(LITERAL_degree);
				break;
			}
			case LITERAL_instances:
			{
				match(LITERAL_instances);
				break;
			}
			case LITERAL_range:
			{
				match(LITERAL_range);
				break;
			}
			case LITERAL_parallel:
			{
				match(LITERAL_parallel);
				break;
			}
			case LITERAL_noparallel:
			{
				match(LITERAL_noparallel);
				break;
			}
			case LITERAL_year:
			{
				match(LITERAL_year);
				break;
			}
			case LITERAL_month:
			{
				match(LITERAL_month);
				break;
			}
			case LITERAL_day:
			{
				match(LITERAL_day);
				break;
			}
			case LITERAL_row:
			{
				match(LITERAL_row);
				break;
			}
			case LITERAL_buffer_pool:
			{
				match(LITERAL_buffer_pool);
				break;
			}
			case LITERAL_system:
			{
				match(LITERAL_system);
				break;
			}
			case LITERAL_managed:
			{
				match(LITERAL_managed);
				break;
			}
			case LITERAL_error_code:
			{
				match(LITERAL_error_code);
				break;
			}
			case LITERAL_error_index:
			{
				match(LITERAL_error_index);
				break;
			}
			case LITERAL_temporary:
			{
				match(LITERAL_temporary);
				break;
			}
			case LITERAL_aggregate:
			{
				match(LITERAL_aggregate);
				break;
			}
			case LITERAL_current:
			{
				match(LITERAL_current);
				break;
			}
			case LITERAL_sqlcode:
			{
				match(LITERAL_sqlcode);
				break;
			}
			case LITERAL_sqlerrm:
			{
				match(LITERAL_sqlerrm);
				break;
			}
			case LITERAL_force:
			{
				match(LITERAL_force);
				break;
			}
			case LITERAL_cascade:
			{
				match(LITERAL_cascade);
				break;
			}
			case LITERAL_constraints:
			{
				match(LITERAL_constraints);
				break;
			}
			case LITERAL_purge:
			{
				match(LITERAL_purge);
				break;
			}
			case LITERAL_validate:
			{
				match(LITERAL_validate);
				break;
			}
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
			case LITERAL_rows:
			{
				match(LITERAL_rows);
				break;
			}
			case LITERAL_foreign:
			{
				match(LITERAL_foreign);
				break;
			}
			case LITERAL_primary:
			{
				match(LITERAL_primary);
				break;
			}
			case LITERAL_records:
			{
				match(LITERAL_records);
				break;
			}
			case LITERAL_parameters:
			{
				match(LITERAL_parameters);
				break;
			}
			case LITERAL_access:
			{
				match(LITERAL_access);
				break;
			}
			case LITERAL_newline:
			{
				match(LITERAL_newline);
				break;
			}
			case LITERAL_delimited:
			{
				match(LITERAL_delimited);
				break;
			}
			case LITERAL_fixed:
			{
				match(LITERAL_fixed);
				break;
			}
			case LITERAL_characterset:
			{
				match(LITERAL_characterset);
				break;
			}
			case LITERAL_big:
			{
				match(LITERAL_big);
				break;
			}
			case LITERAL_little:
			{
				match(LITERAL_little);
				break;
			}
			case LITERAL_endian:
			{
				match(LITERAL_endian);
				break;
			}
			case LITERAL_mark:
			{
				match(LITERAL_mark);
				break;
			}
			case LITERAL_check:
			{
				match(LITERAL_check);
				break;
			}
			case LITERAL_nocheck:
			{
				match(LITERAL_nocheck);
				break;
			}
			case LITERAL_string:
			{
				match(LITERAL_string);
				break;
			}
			case LITERAL_sizes:
			{
				match(LITERAL_sizes);
				break;
			}
			case LITERAL_bytes:
			{
				match(LITERAL_bytes);
				break;
			}
			case LITERAL_load:
			{
				match(LITERAL_load);
				break;
			}
			case LITERAL_nobadfile:
			{
				match(LITERAL_nobadfile);
				break;
			}
			case LITERAL_badfile:
			{
				match(LITERAL_badfile);
				break;
			}
			case LITERAL_nodiscardfile:
			{
				match(LITERAL_nodiscardfile);
				break;
			}
			case LITERAL_discardfile:
			{
				match(LITERAL_discardfile);
				break;
			}
			case LITERAL_nologfile:
			{
				match(LITERAL_nologfile);
				break;
			}
			case LITERAL_logfile:
			{
				match(LITERAL_logfile);
				break;
			}
			case LITERAL_readsize:
			{
				match(LITERAL_readsize);
				break;
			}
			case LITERAL_skip:
			{
				match(LITERAL_skip);
				break;
			}
			case LITERAL_data_cache:
			{
				match(LITERAL_data_cache);
				break;
			}
			case LITERAL_fields:
			{
				match(LITERAL_fields);
				break;
			}
			case LITERAL_missing:
			{
				match(LITERAL_missing);
				break;
			}
			case LITERAL_field:
			{
				match(LITERAL_field);
				break;
			}
			case LITERAL_reject:
			{
				match(LITERAL_reject);
				break;
			}
			case LITERAL_with:
			{
				match(LITERAL_with);
				break;
			}
			case LITERAL_lrtrim:
			{
				match(LITERAL_lrtrim);
				break;
			}
			case LITERAL_notrim:
			{
				match(LITERAL_notrim);
				break;
			}
			case LITERAL_ltrim:
			{
				match(LITERAL_ltrim);
				break;
			}
			case LITERAL_rtrim:
			{
				match(LITERAL_rtrim);
				break;
			}
			case LITERAL_ldtrim:
			{
				match(LITERAL_ldtrim);
				break;
			}
			case LITERAL_position:
			{
				match(LITERAL_position);
				break;
			}
			case LITERAL_enclosed:
			{
				match(LITERAL_enclosed);
				break;
			}
			case LITERAL_date_format:
			{
				match(LITERAL_date_format);
				break;
			}
			case LITERAL_varraw:
			{
				match(LITERAL_varraw);
				break;
			}
			case LITERAL_varcharc:
			{
				match(LITERAL_varcharc);
				break;
			}
			case LITERAL_varrawc:
			{
				match(LITERAL_varrawc);
				break;
			}
			case LITERAL_oracle_number:
			{
				match(LITERAL_oracle_number);
				break;
			}
			case LITERAL_oracle_date:
			{
				match(LITERAL_oracle_date);
				break;
			}
			case LITERAL_counted:
			{
				match(LITERAL_counted);
				break;
			}
			case LITERAL_external:
			{
				match(LITERAL_external);
				break;
			}
			case LITERAL_zoned:
			{
				match(LITERAL_zoned);
				break;
			}
			case LITERAL_unsigned:
			{
				match(LITERAL_unsigned);
				break;
			}
			case LITERAL_location:
			{
				match(LITERAL_location);
				break;
			}
			case LITERAL_limit:
			{
				match(LITERAL_limit);
				break;
			}
			case LITERAL_unlimited:
			{
				match(LITERAL_unlimited);
				break;
			}
			case LITERAL_concat:
			{
				match(LITERAL_concat);
				break;
			}
			case LITERAL_clob:
			{
				match(LITERAL_clob);
				break;
			}
			case LITERAL_nclob:
			{
				match(LITERAL_nclob);
				break;
			}
			case LITERAL_blob:
			{
				match(LITERAL_blob);
				break;
			}
			case LITERAL_bfile:
			{
				match(LITERAL_bfile);
				break;
			}
			case LITERAL_lobfile:
			{
				match(LITERAL_lobfile);
				break;
			}
			case LITERAL_float:
			{
				match(LITERAL_float);
				break;
			}
			case LITERAL_preprocessor:
			{
				match(LITERAL_preprocessor);
				break;
			}
			case LITERAL_compression:
			{
				match(LITERAL_compression);
				break;
			}
			case LITERAL_enabled:
			{
				match(LITERAL_enabled);
				break;
			}
			case LITERAL_disabled:
			{
				match(LITERAL_disabled);
				break;
			}
			case LITERAL_encryption:
			{
				match(LITERAL_encryption);
				break;
			}
			case LITERAL_encrypt:
			{
				match(LITERAL_encrypt);
				break;
			}
			case LITERAL_action:
			{
				match(LITERAL_action);
				break;
			}
			case LITERAL_version:
			{
				match(LITERAL_version);
				break;
			}
			case LITERAL_compatible:
			{
				match(LITERAL_compatible);
				break;
			}
			case LITERAL_data:
			{
				match(LITERAL_data);
				break;
			}
			case LITERAL_no:
			{
				match(LITERAL_no);
				break;
			}
			case LITERAL_initrans:
			{
				match(LITERAL_initrans);
				break;
			}
			case LITERAL_maxtrans:
			{
				match(LITERAL_maxtrans);
				break;
			}
			case LITERAL_logging:
			{
				match(LITERAL_logging);
				break;
			}
			case LITERAL_nologging:
			{
				match(LITERAL_nologging);
				break;
			}
			case LITERAL_quit:
			{
				match(LITERAL_quit);
				break;
			}
			case LITERAL_spool:
			{
				match(LITERAL_spool);
				break;
			}
			case LITERAL_def:
			{
				match(LITERAL_def);
				break;
			}
			case LITERAL_define:
			{
				match(LITERAL_define);
				break;
			}
			case LITERAL_novalidate:
			{
				match(LITERAL_novalidate);
				break;
			}
			case LITERAL_heap:
			{
				match(LITERAL_heap);
				break;
			}
			case LITERAL_freelists:
			{
				match(LITERAL_freelists);
				break;
			}
			case LITERAL_freelist:
			{
				match(LITERAL_freelist);
				break;
			}
			case LITERAL_organization:
			{
				match(LITERAL_organization);
				break;
			}
			case LITERAL_rely:
			{
				match(LITERAL_rely);
				break;
			}
			case LITERAL_at:
			{
				match(LITERAL_at);
				break;
			}
			case LITERAL_off:
			{
				match(LITERAL_off);
				break;
			}
			case LITERAL_enable:
			{
				match(LITERAL_enable);
				break;
			}
			case LITERAL_disable:
			{
				match(LITERAL_disable);
				break;
			}
			case LITERAL_sql:
			{
				match(LITERAL_sql);
				break;
			}
			case LITERAL_before:
			{
				match(LITERAL_before);
				break;
			}
			case LITERAL_after:
			{
				match(LITERAL_after);
				break;
			}
			case LITERAL_directory:
			{
				match(LITERAL_directory);
				break;
			}
			case LITERAL_mask:
			{
				match(LITERAL_mask);
				break;
			}
			case LITERAL_terminated:
			{
				match(LITERAL_terminated);
				break;
			}
			case LITERAL_whitespace:
			{
				match(LITERAL_whitespace);
				break;
			}
			case LITERAL_optionally:
			{
				match(LITERAL_optionally);
				break;
			}
			case LITERAL_option:
			{
				match(LITERAL_option);
				break;
			}
			case LITERAL_operations:
			{
				match(LITERAL_operations);
				break;
			}
			case LITERAL_startup:
			{
				match(LITERAL_startup);
				break;
			}
			case LITERAL_shutdown:
			{
				match(LITERAL_shutdown);
				break;
			}
			case LITERAL_servererror:
			{
				match(LITERAL_servererror);
				break;
			}
			case LITERAL_logon:
			{
				match(LITERAL_logon);
				break;
			}
			case LITERAL_logoff:
			{
				match(LITERAL_logoff);
				break;
			}
			case LITERAL_associate:
			{
				match(LITERAL_associate);
				break;
			}
			case LITERAL_statistics:
			{
				match(LITERAL_statistics);
				break;
			}
			case LITERAL_audit:
			{
				match(LITERAL_audit);
				break;
			}
			case LITERAL_noaudit:
			{
				match(LITERAL_noaudit);
				break;
			}
			case LITERAL_ddl:
			{
				match(LITERAL_ddl);
				break;
			}
			case LITERAL_diassociate:
			{
				match(LITERAL_diassociate);
				break;
			}
			case LITERAL_grant:
			{
				match(LITERAL_grant);
				break;
			}
			case LITERAL_rename:
			{
				match(LITERAL_rename);
				break;
			}
			case LITERAL_truncate:
			{
				match(LITERAL_truncate);
				break;
			}
			case LITERAL_revoke:
			{
				match(LITERAL_revoke);
				break;
			}
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
			case LITERAL_schema:
			{
				match(LITERAL_schema);
				break;
			}
			case LITERAL_hash:
			{
				match(LITERAL_hash);
				break;
			}
			case LITERAL_precision:
			{
				match(LITERAL_precision);
				break;
			}
			case LITERAL_key:
			{
				match(LITERAL_key);
				break;
			}
			case LITERAL_monitoring:
			{
				match(LITERAL_monitoring);
				break;
			}
			case LITERAL_collect:
			{
				match(LITERAL_collect);
				break;
			}
			case LITERAL_nulls:
			{
				match(LITERAL_nulls);
				break;
			}
			case LITERAL_first:
			{
				match(LITERAL_first);
				break;
			}
			case LITERAL_last:
			{
				match(LITERAL_last);
				break;
			}
			case LITERAL_timezone:
			{
				match(LITERAL_timezone);
				break;
			}
			case LITERAL_language:
			{
				match(LITERAL_language);
				break;
			}
			case LITERAL_java:
			{
				match(LITERAL_java);
				break;
			}
			case LITERAL_store:
			{
				match(LITERAL_store);
				break;
			}
			case LITERAL_nested:
			{
				match(LITERAL_nested);
				break;
			}
			case LITERAL_library:
			{
				match(LITERAL_library);
				break;
			}
			case LITERAL_role:
			{
				match(LITERAL_role);
				break;
			}
			case LITERAL_online:
			{
				match(LITERAL_online);
				break;
			}
			case LITERAL_offline:
			{
				match(LITERAL_offline);
				break;
			}
			case LITERAL_compute:
			{
				match(LITERAL_compute);
				break;
			}
			case LITERAL_continue:
			{
				match(LITERAL_continue);
				break;
			}
			case LITERAL_var:
			{
				match(LITERAL_var);
				break;
			}
			case LITERAL_variable:
			{
				match(LITERAL_variable);
				break;
			}
			case LITERAL_none:
			{
				match(LITERAL_none);
				break;
			}
			case LITERAL_oserror:
			{
				match(LITERAL_oserror);
				break;
			}
			case LITERAL_sqlerror:
			{
				match(LITERAL_sqlerror);
				break;
			}
			case LITERAL_whenever:
			{
				match(LITERAL_whenever);
				break;
			}
			case LITERAL_the:
			{
				match(LITERAL_the);
				break;
			}
			case LITERAL_identified:
			{
				match(LITERAL_identified);
				break;
			}
			case LITERAL_link:
			{
				match(LITERAL_link);
				break;
			}
			case LITERAL_by:
			{
				match(LITERAL_by);
				break;
			}
			case LITERAL_noorder:
			{
				match(LITERAL_noorder);
				break;
			}
			case LITERAL_maxvalue:
			{
				match(LITERAL_maxvalue);
				break;
			}
			case LITERAL_minvalue:
			{
				match(LITERAL_minvalue);
				break;
			}
			case LITERAL_increment:
			{
				match(LITERAL_increment);
				break;
			}
			case LITERAL_cycle:
			{
				match(LITERAL_cycle);
				break;
			}
			case LITERAL_nocycle:
			{
				match(LITERAL_nocycle);
				break;
			}
			case LITERAL_pctthreshold:
			{
				match(LITERAL_pctthreshold);
				break;
			}
			case LITERAL_including:
			{
				match(LITERAL_including);
				break;
			}
			case LITERAL_repheader:
			{
				match(LITERAL_repheader);
				break;
			}
			case LITERAL_repfooter:
			{
				match(LITERAL_repfooter);
				break;
			}
			case LITERAL_serveroutput:
			{
				match(LITERAL_serveroutput);
				break;
			}
			case LITERAL_groups:
			{
				match(LITERAL_groups);
				break;
			}
			case LITERAL_wait:
			{
				match(LITERAL_wait);
				break;
			}
			case LITERAL_indices:
			{
				match(LITERAL_indices);
				break;
			}
			case LITERAL_subtype:
			{
				match(LITERAL_subtype);
				break;
			}
			case LITERAL_tablespace:
			{
				match(LITERAL_tablespace);
				break;
			}
			case LITERAL_optimal:
			{
				match(LITERAL_optimal);
				break;
			}
			case LITERAL_keep:
			{
				match(LITERAL_keep);
				break;
			}
			case LITERAL_sequence:
			{
				match(LITERAL_sequence);
				break;
			}
			case LITERAL_under:
			{
				match(LITERAL_under);
				break;
			}
			case LITERAL_final:
			{
				match(LITERAL_final);
				break;
			}
			case LITERAL_timezone_hour:
			{
				match(LITERAL_timezone_hour);
				break;
			}
			case LITERAL_timezone_minute:
			{
				match(LITERAL_timezone_minute);
				break;
			}
			case LITERAL_timezone_region:
			{
				match(LITERAL_timezone_region);
				break;
			}
			case LITERAL_timezone_abbr:
			{
				match(LITERAL_timezone_abbr);
				break;
			}
			case LITERAL_hour:
			{
				match(LITERAL_hour);
				break;
			}
			case LITERAL_minute:
			{
				match(LITERAL_minute);
				break;
			}
			case LITERAL_second:
			{
				match(LITERAL_second);
				break;
			}
			case LITERAL_cost:
			{
				match(LITERAL_cost);
				break;
			}
			case LITERAL_selectivity:
			{
				match(LITERAL_selectivity);
				break;
			}
			case LITERAL_functions:
			{
				match(LITERAL_functions);
				break;
			}
			case LITERAL_packages:
			{
				match(LITERAL_packages);
				break;
			}
			case LITERAL_types:
			{
				match(LITERAL_types);
				break;
			}
			case LITERAL_indexes:
			{
				match(LITERAL_indexes);
				break;
			}
			case LITERAL_indextypes:
			{
				match(LITERAL_indextypes);
				break;
			}
			case LITERAL_transforms:
			{
				match(LITERAL_transforms);
				break;
			}
			case LITERAL_host:
			{
				match(LITERAL_host);
				break;
			}
			case LITERAL_multiset:
			{
				match(LITERAL_multiset);
				break;
			}
			case LITERAL_lag:
			{
				match(LITERAL_lag);
				break;
			}
			case LITERAL_lead:
			{
				match(LITERAL_lead);
				break;
			}
			case LITERAL_datafile:
			{
				match(LITERAL_datafile);
				break;
			}
			case LITERAL_add:
			{
				match(LITERAL_add);
				break;
			}
			case LITERAL_reuse:
			{
				match(LITERAL_reuse);
				break;
			}
			case LITERAL_size:
			{
				match(LITERAL_size);
				break;
			}
			case LITERAL_maxsize:
			{
				match(LITERAL_maxsize);
				break;
			}
			case LITERAL_bigfile:
			{
				match(LITERAL_bigfile);
				break;
			}
			case LITERAL_smallfile:
			{
				match(LITERAL_smallfile);
				break;
			}
			case LITERAL_extent:
			{
				match(LITERAL_extent);
				break;
			}
			case LITERAL_management:
			{
				match(LITERAL_management);
				break;
			}
			case LITERAL_dictionary:
			{
				match(LITERAL_dictionary);
				break;
			}
			case LITERAL_uniform:
			{
				match(LITERAL_uniform);
				break;
			}
			case LITERAL_retention:
			{
				match(LITERAL_retention);
				break;
			}
			case LITERAL_guarantee:
			{
				match(LITERAL_guarantee);
				break;
			}
			case LITERAL_noguarantee:
			{
				match(LITERAL_noguarantee);
				break;
			}
			case LITERAL_tempfile:
			{
				match(LITERAL_tempfile);
				break;
			}
			case LITERAL_contents:
			{
				match(LITERAL_contents);
				break;
			}
			case LITERAL_datafiles:
			{
				match(LITERAL_datafiles);
				break;
			}
			case LITERAL_backup:
			{
				match(LITERAL_backup);
				break;
			}
			case LITERAL_coalesce:
			{
				match(LITERAL_coalesce);
				break;
			}
			case LITERAL_permanent:
			{
				match(LITERAL_permanent);
				break;
			}
			case LITERAL_pctversion:
			{
				match(LITERAL_pctversion);
				break;
			}
			case LITERAL_freepools:
			{
				match(LITERAL_freepools);
				break;
			}
			case LITERAL_chunk:
			{
				match(LITERAL_chunk);
				break;
			}
			case LITERAL_reads:
			{
				match(LITERAL_reads);
				break;
			}
			case LITERAL_storage:
			{
				match(LITERAL_storage);
				break;
			}
			case LITERAL_locator:
			{
				match(LITERAL_locator);
				break;
			}
			case LITERAL_value:
			{
				match(LITERAL_value);
				break;
			}
			case LITERAL_cluster:
			{
				match(LITERAL_cluster);
				break;
			}
			case LITERAL_deferred:
			{
				match(LITERAL_deferred);
				break;
			}
			case LITERAL_creation:
			{
				match(LITERAL_creation);
				break;
			}
			case LITERAL_segment:
			{
				match(LITERAL_segment);
				break;
			}
			case LITERAL_flash_cache:
			{
				match(LITERAL_flash_cache);
				break;
			}
			case LITERAL_cell_flash_cache:
			{
				match(LITERAL_cell_flash_cache);
				break;
			}
			default:
				if ((LA(1)==LITERAL_execute) && (_tokenSet_56.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_execute);
				}
				else if ((LA(1)==LITERAL_execute) && (_tokenSet_56.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_execute);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_56);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sqlplus_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_set:
			{
				{
				match(LITERAL_set);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_57.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
				}
				else if ((LA(1)==LITERAL_long)) {
					match(LITERAL_long);
				}
				else if ((_tokenSet_57.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop154:
				do {
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case QUOTED_STR:
					{
						string_literal();
						break;
					}
					default:
						if ((_tokenSet_5.member(LA(1))) && (_tokenSet_57.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
							identifier2();
						}
					else {
						break _loop154;
					}
					}
				} while (true);
				}
				{
				_loop156:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop156;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SET);
				}
				break;
			}
			case LITERAL_show:
			{
				{
				match(LITERAL_show);
				identifier();
				{
				_loop159:
				do {
					if ((_tokenSet_58.member(LA(1))) && (_tokenSet_59.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						base_expression();
					}
					else {
						break _loop159;
					}
					
				} while (true);
				}
				{
				_loop161:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop161;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_SHOW);
				}
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
					match(LITERAL_var);
					break;
				}
				case LITERAL_variable:
				{
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
				datatype();
				{
				_loop165:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop165;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_VARIABLE);
				}
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
					match(LITERAL_col);
					break;
				}
				case LITERAL_column:
				{
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
				{
				_loop169:
				do {
					if ((_tokenSet_5.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						identifier2();
					}
					else {
						break _loop169;
					}
					
				} while (true);
				}
				{
				_loop171:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop171;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_COLUMN);
				}
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
					match(LITERAL_exec);
					break;
				}
				case LITERAL_execute:
				{
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
				{
				_loop175:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop175;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_EXEC);
				}
				break;
			}
			case LITERAL_whenever:
			{
				{
				match(LITERAL_whenever);
				{
				switch ( LA(1)) {
				case LITERAL_sqlerror:
				{
					match(LITERAL_sqlerror);
					break;
				}
				case LITERAL_oserror:
				{
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
					match(LITERAL_exit);
					break;
				}
				case LITERAL_continue:
				{
					match(LITERAL_continue);
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
				case LITERAL_none:
				{
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
				_loop180:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop180;
					}
					
				} while (true);
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_WHENEVER);
				}
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
					match(LITERAL_def);
					break;
				}
				case LITERAL_define:
				{
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
				{
				switch ( LA(1)) {
				case EQ:
				{
					match(EQ);
					{
					if ((_tokenSet_61.member(LA(1))) && (_tokenSet_62.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						plsql_expression();
					}
					else if ((LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(DOUBLE_QUOTED_STRING);
					}
					else if ((LA(1)==STORAGE_SIZE)) {
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				break;
			}
			case LITERAL_prompt:
			{
				{
				match(LITERAL_prompt);
				{
				_loop187:
				do {
					if ((_tokenSet_63.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop187;
					}
					
				} while (true);
				}
				match(CUSTOM_TOKEN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_PROMPT);
				}
				break;
			}
			case LITERAL_rem:
			{
				match(LITERAL_rem);
				{
				_loop189:
				do {
					if ((_tokenSet_63.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop189;
					}
					
				} while (true);
				}
				match(CUSTOM_TOKEN);
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_PROMPT);
				}
				break;
			}
			case LITERAL_host:
			{
				match(LITERAL_host);
				{
				_loop191:
				do {
					if ((_tokenSet_63.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop191;
					}
					
				} while (true);
				}
				match(CUSTOM_TOKEN);
				break;
			}
			case LITERAL_exit:
			{
				match(LITERAL_exit);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
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
				break;
			}
			case LITERAL_quit:
			{
				{
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				break;
			}
			case LITERAL_spool:
			{
				match(LITERAL_spool);
				{
				if ((LA(1)==LITERAL_off) && (_tokenSet_4.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_off);
				}
				else if ((_tokenSet_64.member(LA(1))) && (_tokenSet_65.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					identifier4();
					{
					_loop198:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							identifier4();
						}
						else {
							break _loop198;
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
					match(LITERAL_sta);
					break;
				}
				case LITERAL_start:
				{
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
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_START);
				}
				break;
			}
			case LITERAL_repfooter:
			{
				{
				match(LITERAL_repfooter);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
					match(LITERAL_on);
					break;
				}
				case LITERAL_is:
				{
					{
					match(LITERAL_is);
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				break;
			}
			case LITERAL_repheader:
			{
				{
				match(LITERAL_repheader);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
					match(LITERAL_on);
					break;
				}
				case LITERAL_is:
				{
					{
					match(LITERAL_is);
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				break;
			}
			case LITERAL_serveroutput:
			{
				{
				match(LITERAL_serveroutput);
				{
				switch ( LA(1)) {
				case LITERAL_off:
				{
					match(LITERAL_off);
					break;
				}
				case LITERAL_on:
				{
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				break;
			}
			case LITERAL_begin:
			case LITERAL_declare:
			{
				{
				begin_block();
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case AT_PREFIXED:
			{
				{
				match(AT_PREFIXED);
				{
				_loop219:
				do {
					if ((_tokenSet_63.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop219;
					}
					
				} while (true);
				}
				match(CUSTOM_TOKEN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_RUNSCRIPT);
				}
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
	}
	
	public void base_expression() throws RecognitionException, TokenStreamException {
		
		Token  a1 = null;
		Token  a2 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cast:
			{
				{
				cast_function();
				}
				break;
			}
			case LITERAL_case:
			{
				{
				case_expression();
				}
				break;
			}
			case LITERAL_rank:
			case LITERAL_dense_rank:
			{
				dence_rank_analytics_func();
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
					match(LITERAL_all);
					break;
				}
				case LITERAL_any:
				{
					a2 = LT(1);
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
				break;
			}
			case QUOTED_STR:
			{
				string_literal();
				break;
			}
			case NUMBER:
			{
				numeric_literal();
				break;
			}
			case LITERAL_true:
			case LITERAL_false:
			{
				boolean_literal();
				break;
			}
			case LITERAL_null:
			{
				null_statement();
				break;
			}
			default:
				boolean synPredMatched1262 = false;
				if (((LA(1)==LITERAL_sqlcode) && (_tokenSet_66.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
					int _m1262 = mark();
					synPredMatched1262 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_sqlcode);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1262 = false;
					}
					rewind(_m1262);
					inputState.guessing--;
				}
				if ( synPredMatched1262 ) {
					match(LITERAL_sqlcode);
					if ( inputState.guessing==0 ) {
						__markRule(SQLCODE_SYSVAR);
					}
				}
				else {
					boolean synPredMatched1264 = false;
					if (((LA(1)==LITERAL_sqlerrm) && (_tokenSet_66.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
						int _m1264 = mark();
						synPredMatched1264 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_sqlerrm);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1264 = false;
						}
						rewind(_m1264);
						inputState.guessing--;
					}
					if ( synPredMatched1264 ) {
						{
						match(LITERAL_sqlerrm);
						{
						if ((LA(1)==OPEN_PAREN) && (_tokenSet_58.member(LA(2))) && (_tokenSet_67.member(LA(3)))) {
							match(OPEN_PAREN);
							base_expression();
							match(CLOSE_PAREN);
						}
						else if ((_tokenSet_66.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
						}
						if ( inputState.guessing==0 ) {
							__markRule(SQLERRM_SYSVAR);
						}
					}
					else {
						boolean synPredMatched1271 = false;
						if (((LA(1)==LITERAL_trim) && (LA(2)==OPEN_PAREN) && (_tokenSet_68.member(LA(3))))) {
							int _m1271 = mark();
							synPredMatched1271 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_trim);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1271 = false;
							}
							rewind(_m1271);
							inputState.guessing--;
						}
						if ( synPredMatched1271 ) {
							{
							trim_function();
							}
						}
						else {
							boolean synPredMatched1274 = false;
							if (((LA(1)==LITERAL_count) && (LA(2)==OPEN_PAREN) && (_tokenSet_69.member(LA(3))))) {
								int _m1274 = mark();
								synPredMatched1274 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_count);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1274 = false;
								}
								rewind(_m1274);
								inputState.guessing--;
							}
							if ( synPredMatched1274 ) {
								{
								count_function();
								}
							}
							else {
								boolean synPredMatched1280 = false;
								if (((LA(1)==LITERAL_multiset) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
									int _m1280 = mark();
									synPredMatched1280 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_multiset);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1280 = false;
									}
									rewind(_m1280);
									inputState.guessing--;
								}
								if ( synPredMatched1280 ) {
									{
									multiset_operator();
									}
								}
								else {
									boolean synPredMatched1283 = false;
									if (((LA(1)==LITERAL_lag) && (LA(2)==OPEN_PAREN) && (_tokenSet_61.member(LA(3))))) {
										int _m1283 = mark();
										synPredMatched1283 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_lag);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1283 = false;
										}
										rewind(_m1283);
										inputState.guessing--;
									}
									if ( synPredMatched1283 ) {
										{
										lag_function();
										}
									}
									else {
										boolean synPredMatched1286 = false;
										if (((LA(1)==LITERAL_lead) && (LA(2)==OPEN_PAREN) && (_tokenSet_61.member(LA(3))))) {
											int _m1286 = mark();
											synPredMatched1286 = true;
											inputState.guessing++;
											try {
												{
												match(LITERAL_lead);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1286 = false;
											}
											rewind(_m1286);
											inputState.guessing--;
										}
										if ( synPredMatched1286 ) {
											{
											lead_function();
											}
										}
										else {
											boolean synPredMatched1292 = false;
											if (((LA(1)==LITERAL_extract) && (LA(2)==OPEN_PAREN) && (_tokenSet_70.member(LA(3))))) {
												int _m1292 = mark();
												synPredMatched1292 = true;
												inputState.guessing++;
												try {
													{
													match(LITERAL_extract);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched1292 = false;
												}
												rewind(_m1292);
												inputState.guessing--;
											}
											if ( synPredMatched1292 ) {
												extract_date_function();
											}
											else if ((LA(1)==LITERAL_interval) && (LA(2)==QUOTED_STR) && (_tokenSet_71.member(LA(3)))) {
												{
												match(LITERAL_interval);
												string_literal();
												{
												switch ( LA(1)) {
												case LITERAL_second:
												{
													match(LITERAL_second);
													break;
												}
												case LITERAL_minute:
												{
													match(LITERAL_minute);
													break;
												}
												case LITERAL_hour:
												{
													match(LITERAL_hour);
													break;
												}
												case LITERAL_day:
												{
													match(LITERAL_day);
													break;
												}
												case LITERAL_month:
												{
													match(LITERAL_month);
													break;
												}
												case LITERAL_year:
												{
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
													match(LITERAL_to);
													{
													switch ( LA(1)) {
													case LITERAL_second:
													{
														match(LITERAL_second);
														break;
													}
													case LITERAL_month:
													{
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
														match(NUMBER);
														match(CLOSE_PAREN);
													}
													else if ((_tokenSet_66.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
													}
													else {
														throw new NoViableAltException(LT(1), getFilename());
													}
													
													}
												}
												else if ((_tokenSet_66.member(LA(1)))) {
												}
												else {
													throw new NoViableAltException(LT(1), getFilename());
												}
												
												}
												}
												if ( inputState.guessing==0 ) {
													__markRule(INTERVAL_CONST);
												}
											}
											else {
												boolean synPredMatched1299 = false;
												if (((LA(1)==LITERAL_sql) && (LA(2)==PERCENTAGE))) {
													int _m1299 = mark();
													synPredMatched1299 = true;
													inputState.guessing++;
													try {
														{
														match(LITERAL_sql);
														match(PERCENTAGE);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched1299 = false;
													}
													rewind(_m1299);
													inputState.guessing--;
												}
												if ( synPredMatched1299 ) {
													sql_percentage();
												}
												else if ((LA(1)==LITERAL_timestamp) && (LA(2)==QUOTED_STR) && (_tokenSet_66.member(LA(3)))) {
													{
													match(LITERAL_timestamp);
													string_literal();
													}
													if ( inputState.guessing==0 ) {
														__markRule(TIMESTAMP_CONST);
													}
												}
												else {
													boolean synPredMatched1308 = false;
													if (((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING||LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==PERCENTAGE||LA(2)==LITERAL_select) && (_tokenSet_72.member(LA(3))))) {
														int _m1308 = mark();
														synPredMatched1308 = true;
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
															synPredMatched1308 = false;
														}
														rewind(_m1308);
														inputState.guessing--;
													}
													if ( synPredMatched1308 ) {
														ident_percentage();
													}
													else {
														boolean synPredMatched1310 = false;
														if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_72.member(LA(3))))) {
															int _m1310 = mark();
															synPredMatched1310 = true;
															inputState.guessing++;
															try {
																{
																match(OPEN_PAREN);
																match(LITERAL_select);
																}
															}
															catch (RecognitionException pe) {
																synPredMatched1310 = false;
															}
															rewind(_m1310);
															inputState.guessing--;
														}
														if ( synPredMatched1310 ) {
															subquery();
															if ( inputState.guessing==0 ) {
																__markRule(SUBQUERY_EXPR);
															}
														}
														else if ((LA(1)==OPEN_PAREN) && (_tokenSet_73.member(LA(2))) && (_tokenSet_74.member(LA(3)))) {
															match(OPEN_PAREN);
															condition();
															match(CLOSE_PAREN);
															if ( inputState.guessing==0 ) {
																__markRule(PARENTHESIZED_EXPR);
															}
														}
														else {
															boolean synPredMatched1312 = false;
															if (((_tokenSet_75.member(LA(1))) && (_tokenSet_76.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
																int _m1312 = mark();
																synPredMatched1312 = true;
																inputState.guessing++;
																try {
																	{
																	pseudo_column();
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched1312 = false;
																}
																rewind(_m1312);
																inputState.guessing--;
															}
															if ( synPredMatched1312 ) {
																pseudo_column();
															}
															else {
																boolean synPredMatched1314 = false;
																if (((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_77.member(LA(3))))) {
																	int _m1314 = mark();
																	synPredMatched1314 = true;
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
																		synPredMatched1314 = false;
																	}
																	rewind(_m1314);
																	inputState.guessing--;
																}
																if ( synPredMatched1314 ) {
																	{
																	column_spec();
																	match(OPEN_PAREN);
																	match(PLUS);
																	match(CLOSE_PAREN);
																	}
																	if ( inputState.guessing==0 ) {
																		__markRule(COLUMN_OUTER_JOIN);
																	}
																}
																else {
																	boolean synPredMatched1318 = false;
																	if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==LITERAL_nextval||LA(3)==LITERAL_currval))) {
																		int _m1318 = mark();
																		synPredMatched1318 = true;
																		inputState.guessing++;
																		try {
																			{
																			sequence_ref();
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
																			synPredMatched1318 = false;
																		}
																		rewind(_m1318);
																		inputState.guessing--;
																	}
																	if ( synPredMatched1318 ) {
																		sequence_expr();
																	}
																	else if ((_tokenSet_78.member(LA(1))) && (_tokenSet_76.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
																		rvalue();
																	}
																else {
																	throw new NoViableAltException(LT(1), getFilename());
																}
																}}}}}}}}}}}}}}
															}
															catch (RecognitionException ex) {
																if (inputState.guessing==0) {
																	reportError(ex);
																	recover(ex,_tokenSet_66);
																} else {
																  throw ex;
																}
															}
														}
														
	public void datatype() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_binary_integer:
			{
				match(LITERAL_binary_integer);
				break;
			}
			case LITERAL_natural:
			{
				match(LITERAL_natural);
				break;
			}
			case LITERAL_positive:
			{
				match(LITERAL_positive);
				break;
			}
			case LITERAL_number:
			{
				{
				match(LITERAL_number);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==ASTERISK||LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_50.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case ASTERISK:
					case NUMBER:
					{
						{
						switch ( LA(1)) {
						case NUMBER:
						{
							match(NUMBER);
							break;
						}
						case ASTERISK:
						{
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
							{
							switch ( LA(1)) {
							case MINUS:
							{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				match(LITERAL_char);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_50.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case LITERAL_byte:
						{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				match(LITERAL_long);
				{
				if ((LA(1)==LITERAL_raw)) {
					match(LITERAL_raw);
				}
				else if ((_tokenSet_50.member(LA(1)))) {
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
				match(LITERAL_raw);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_boolean:
			{
				match(LITERAL_boolean);
				break;
			}
			case LITERAL_date:
			{
				match(LITERAL_date);
				break;
			}
			case LITERAL_timestamp:
			{
				match(LITERAL_timestamp);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				boolean synPredMatched982 = false;
				if (((LA(1)==LITERAL_with) && (LA(2)==LITERAL_local) && (LA(3)==LITERAL_time))) {
					int _m982 = mark();
					synPredMatched982 = true;
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
						synPredMatched982 = false;
					}
					rewind(_m982);
					inputState.guessing--;
				}
				if ( synPredMatched982 ) {
					{
					match(LITERAL_with);
					match(LITERAL_local);
					match(LITERAL_time);
					match(LITERAL_zone);
					}
				}
				else if ((LA(1)==LITERAL_with) && (LA(2)==LITERAL_time) && (LA(3)==LITERAL_zone)) {
					{
					match(LITERAL_with);
					match(LITERAL_time);
					match(LITERAL_zone);
					}
				}
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_interval:
			{
				match(LITERAL_interval);
				{
				switch ( LA(1)) {
				case LITERAL_year:
				{
					{
					match(LITERAL_year);
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						match(OPEN_PAREN);
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
					match(LITERAL_to);
					match(LITERAL_month);
					}
					break;
				}
				case LITERAL_day:
				{
					{
					match(LITERAL_day);
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						match(OPEN_PAREN);
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
					match(LITERAL_to);
					match(LITERAL_second);
					{
					if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
						match(OPEN_PAREN);
						match(NUMBER);
						match(CLOSE_PAREN);
					}
					else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				match(LITERAL_smallint);
				break;
			}
			case LITERAL_real:
			{
				match(LITERAL_real);
				break;
			}
			case LITERAL_numeric:
			{
				match(LITERAL_numeric);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER||LA(2)==CLOSE_PAREPlSqlPlllN) && (_tokenSet_79.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case COMMA:
						{
							match(COMMA);
							match(NUMBER);
							break;
						}
						case CLOSE_PAREPlSqlPlllN:
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
					case CLOSE_PAREPlSqlPlllN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(CLOSE_PAREPlSqlPlllN);
				}
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_int:
			{
				match(LITERAL_int);
				break;
			}
			case LITERAL_integer:
			{
				match(LITERAL_integer);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_50.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_pls_integer:
			{
				match(LITERAL_pls_integer);
				break;
			}
			case LITERAL_double:
			{
				match(LITERAL_double);
				match(LITERAL_precision);
				break;
			}
			case LITERAL_float:
			{
				match(LITERAL_float);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_50.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_decimal:
			{
				match(LITERAL_decimal);
				break;
			}
			case LITERAL_varchar:
			{
				match(LITERAL_varchar);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_80.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case LITERAL_byte:
						{
							match(LITERAL_byte);
							break;
						}
						case LITERAL_char:
						{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 692:
			{
				match(692);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_80.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case LITERAL_byte:
						{
							match(LITERAL_byte);
							break;
						}
						case LITERAL_char:
						{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_nvarchar:
			{
				match(LITERAL_nvarchar);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_80.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case LITERAL_byte:
						{
							match(LITERAL_byte);
							break;
						}
						case LITERAL_char:
						{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 694:
			{
				match(694);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_80.member(LA(3)))) {
					match(OPEN_PAREN);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						match(NUMBER);
						{
						switch ( LA(1)) {
						case LITERAL_byte:
						{
							match(LITERAL_byte);
							break;
						}
						case LITERAL_char:
						{
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
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_character:
			{
				match(LITERAL_character);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_50.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case LITERAL_rowid:
			{
				match(LITERAL_rowid);
				break;
			}
			case LITERAL_blob:
			{
				match(LITERAL_blob);
				break;
			}
			case LITERAL_clob:
			{
				match(LITERAL_clob);
				break;
			}
			case LITERAL_nclob:
			{
				match(LITERAL_nclob);
				break;
			}
			case LITERAL_bfile:
			{
				match(LITERAL_bfile);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    throw ex;
					
			} else {
				throw ex;
			}
		}
	}
	
	public void sqlplus_exec_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched222 = false;
			if (((_tokenSet_78.member(LA(1))) && (_tokenSet_81.member(LA(2))) && (_tokenSet_82.member(LA(3))))) {
				int _m222 = mark();
				synPredMatched222 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					match(ASSIGNMENT_EQ);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched222 = false;
				}
				rewind(_m222);
				inputState.guessing--;
			}
			if ( synPredMatched222 ) {
				assignment_statement();
			}
			else if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
				{
				procedure_call();
				{
				_loop225:
				do {
					if ((LA(1)==DOT)) {
						match(DOT);
						procedure_call();
					}
					else {
						break _loop225;
					}
					
				} while (true);
				}
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_expression() throws RecognitionException, TokenStreamException {
		
		Token  c = null;
		boolean tag1 = false;
		
		try {      // for error handling
			num_expression();
			{
			_loop1259:
			do {
				if ((LA(1)==CONCAT) && (_tokenSet_61.member(LA(2))) && (_tokenSet_84.member(LA(3)))) {
					c = LT(1);
					match(CONCAT);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					num_expression();
				}
				else {
					break _loop1259;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(ARITHMETIC_EXPR);
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
	}
	
	public void identifier4() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_prior:
			{
				match(LITERAL_prior);
				break;
			}
			case LITERAL_start:
			{
				match(LITERAL_start);
				break;
			}
			case LITERAL_create:
			{
				match(LITERAL_create);
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_65);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sqlplus_path() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case DOUBLE_DOT:
			{
				match(DOUBLE_DOT);
				break;
			}
			case DOT:
			{
				match(DOT);
				break;
			}
			default:
				if ((_tokenSet_24.member(LA(1)))) {
					identifier3();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop231:
			do {
				if ((LA(1)==DIVIDE||LA(1)==BACKSLASH)) {
					{
					switch ( LA(1)) {
					case DIVIDE:
					{
						match(DIVIDE);
						break;
					}
					case BACKSLASH:
					{
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
						match(DOUBLE_DOT);
						break;
					}
					case DOT:
					{
						match(DOT);
						break;
					}
					default:
						if ((_tokenSet_24.member(LA(1)))) {
							identifier3();
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop231;
				}
				
			} while (true);
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
	}
	
	public void begin_block() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_declare:
			{
				match(LITERAL_declare);
				{
				if ((_tokenSet_86.member(LA(1)))) {
					declare_list();
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
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_BLOCK);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_lvalue() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			boolean synPredMatched938 = false;
			if (((_tokenSet_33.member(LA(1))) && (_tokenSet_87.member(LA(2))) && (_tokenSet_88.member(LA(3))))) {
				int _m938 = mark();
				synPredMatched938 = true;
				inputState.guessing++;
				try {
					{
					{
					_loop937:
					do {
						if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_33.member(LA(3)))) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop937;
						}
						
					} while (true);
					}
					name_fragment2();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched938 = false;
				}
				rewind(_m938);
				inputState.guessing--;
			}
			if ( synPredMatched938 ) {
				{
				_loop940:
				do {
					if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment();
						match(DOT);
					}
					else {
						break _loop940;
					}
					
				} while (true);
				}
				name_fragment2();
				if ( inputState.guessing==0 ) {
					__markRule(PLSQL_VAR_REF);
				}
			}
			else {
				boolean synPredMatched943 = false;
				if (((LA(1)==COLON) && (LA(2)==LITERAL_old||LA(2)==LITERAL_new) && (LA(3)==DOT))) {
					int _m943 = mark();
					synPredMatched943 = true;
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
						synPredMatched943 = false;
					}
					rewind(_m943);
					inputState.guessing--;
				}
				if ( synPredMatched943 ) {
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
					name_fragment();
					}
					if ( inputState.guessing==0 ) {
						__markRule(TRIGGER_COLUMN_REF);
					}
				}
				else if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
					{
					function_call();
					{
					_loop951:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							if ( inputState.guessing==0 ) {
								tag1=true;
							}
							{
							boolean synPredMatched950 = false;
							if (((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3))))) {
								int _m950 = mark();
								synPredMatched950 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched950 = false;
								}
								rewind(_m950);
								inputState.guessing--;
							}
							if ( synPredMatched950 ) {
								function_call();
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_87.member(LA(2))) && (_tokenSet_88.member(LA(3)))) {
								c_record_item_ref();
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop951;
						}
						
					} while (true);
					}
					}
					if ( inputState.guessing==0 ) {
						
						if(tag1){
						__markRule(COLLECTION_METHOD_CALL);
						}
						
					}
				}
				else if ((LA(1)==COLON) && (_tokenSet_5.member(LA(2))) && (_tokenSet_89.member(LA(3)))) {
					bind_variable();
				}
				else {
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
		}
		
	public void assignment_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_lvalue();
			match(ASSIGNMENT_EQ);
			condition();
			if ( inputState.guessing==0 ) {
				__markRule(ASSIGNMENT_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void procedure_call() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref();
			call_argument_list();
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_CALL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
	}
	
	public void identifier3() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_prior:
			{
				match(LITERAL_prior);
				break;
			}
			case LITERAL_start:
			{
				match(LITERAL_start);
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
				}
			else {
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
	}
	
	public void create_view() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_view);
			view_name_ddl();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				v_column_def();
				{
				_loop736:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						v_column_def();
					}
					else {
						break _loop736;
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
			{
			switch ( LA(1)) {
			case LITERAL_with:
			{
				match(LITERAL_with);
				{
				switch ( LA(1)) {
				case LITERAL_check:
				{
					{
					match(LITERAL_check);
					match(LITERAL_option);
					}
					break;
				}
				case LITERAL_read:
				{
					{
					match(LITERAL_read);
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_view_column_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(626);
			view_name_ddl();
			match(OPEN_PAREN);
			column_def();
			{
			_loop744:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_def();
				}
				else {
					break _loop744;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_table2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_table);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_91.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_name_ddl();
			{
			if ((LA(1)==OPEN_PAREN) && (_tokenSet_33.member(LA(2))) && (_tokenSet_92.member(LA(3)))) {
				match(OPEN_PAREN);
				column_def();
				{
				_loop393:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_33.member(LA(2))) && (_tokenSet_92.member(LA(3)))) {
						match(COMMA);
						column_def();
					}
					else {
						break _loop393;
					}
					
				} while (true);
				}
				{
				_loop395:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						constaraint();
					}
					else {
						break _loop395;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
			}
			else if ((_tokenSet_91.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_nested:
			{
				nested_tab_spec();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_storage:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_lob:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_organization:
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
			case LITERAL_lob:
			{
				lob_storage_clause();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_storage:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_organization:
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
			_loop399:
			do {
				switch ( LA(1)) {
				case LITERAL_storage:
				case LITERAL_tablespace:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_organization:
				{
					physical_properties();
					break;
				}
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_local:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				{
					table_properties();
					break;
				}
				default:
				{
					break _loop399;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				match(LITERAL_as);
				select_expression();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_temp_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_global:
			{
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
			{
			if ((_tokenSet_5.member(LA(1)))) {
				schema_name();
				match(DOT);
			}
			else if ((LA(1)==LITERAL_table)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(LITERAL_table);
			table_name_ddl();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				column_def();
				{
				_loop406:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_33.member(LA(2)))) {
						match(COMMA);
						column_def();
					}
					else {
						break _loop406;
					}
					
				} while (true);
				}
				{
				_loop408:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						constaraint();
					}
					else {
						break _loop408;
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
			match(LITERAL_on);
			match(LITERAL_commit);
			{
			switch ( LA(1)) {
			case LITERAL_preserve:
			{
				match(LITERAL_preserve);
				break;
			}
			case LITERAL_delete:
			{
				match(LITERAL_delete);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_rows);
			}
			{
			switch ( LA(1)) {
			case LITERAL_cache:
			case LITERAL_nocache:
			{
				cache_clause();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
				match(LITERAL_as);
				select_expression();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_index2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_unique:
			{
				match(LITERAL_unique);
				break;
			}
			case LITERAL_bitmap:
			{
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
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==LITERAL_on)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			index_name();
			match(LITERAL_on);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==OPEN_PAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_ref();
			match(OPEN_PAREN);
			index_column_spec_list();
			match(CLOSE_PAREN);
			{
			_loop380:
			do {
				switch ( LA(1)) {
				case LITERAL_storage:
				case LITERAL_tablespace:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_organization:
				{
					physical_properties();
					break;
				}
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_local:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				{
					table_properties();
					break;
				}
				default:
				{
					break _loop380;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_directory() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_directory);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_24.member(LA(3)))) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_24.member(LA(1))) && (_tokenSet_93.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			object_name();
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				match(LITERAL_as);
				string_literal();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_db_link() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_database);
			match(LITERAL_link);
			object_name();
			match(LITERAL_connect);
			match(LITERAL_to);
			identifier2();
			match(LITERAL_identified);
			match(LITERAL_by);
			match(DOUBLE_QUOTED_STRING);
			{
			switch ( LA(1)) {
			case LITERAL_using:
			{
				match(LITERAL_using);
				{
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
				}
				else if ((LA(1)==QUOTED_STR)) {
					string_literal();
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_sequence() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_sequence);
			object_name();
			{
			_loop324:
			do {
				if ((_tokenSet_94.member(LA(1))) && (_tokenSet_95.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sequence_opt();
				}
				else {
					break _loop324;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_synonym() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_synonym);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_for)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			synonym_name();
			match(LITERAL_for);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_96.member(LA(1))) && (_tokenSet_20.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			synonym_obj();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_tablespace() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_bigfile:
			{
				match(LITERAL_bigfile);
				break;
			}
			case LITERAL_smallfile:
			{
				match(LITERAL_smallfile);
				break;
			}
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_undo:
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
			case LITERAL_temporary:
			{
				{
				match(LITERAL_temporary);
				match(LITERAL_tablespace);
				tablespace_name();
				{
				switch ( LA(1)) {
				case LITERAL_tempfile:
				{
					match(LITERAL_tempfile);
					file_specification();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_tablespace:
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_tablespace:
				{
					tablespace_group_clause();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_undo:
			{
				{
				match(LITERAL_undo);
				match(LITERAL_tablespace);
				tablespace_name();
				{
				switch ( LA(1)) {
				case LITERAL_extent:
				{
					extent_management_clause();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_retention:
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_retention:
				{
					tablespace_retention_clause();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_tablespace:
			{
				{
				match(LITERAL_tablespace);
				tablespace_name();
				{
				int _cnt272=0;
				_loop272:
				do {
					if ((_tokenSet_97.member(LA(1)))) {
						create_tablespace_rest();
					}
					else {
						if ( _cnt272>=1 ) { break _loop272; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt272++;
				} while (true);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void tablespace_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(TABLESPACE_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_98);
			} else {
			  throw ex;
			}
		}
	}
	
	public void file_specification() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			string_literal();
			{
			switch ( LA(1)) {
			case LITERAL_size:
			{
				match(LITERAL_size);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
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
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_force:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_datafile:
			case LITERAL_next:
			case LITERAL_maxsize:
			case LITERAL_reuse:
			case LITERAL_extent:
			case LITERAL_autoextend:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_no:
			case LITERAL_online:
			case LITERAL_offline:
			case LITERAL_read:
			case LITERAL_permanent:
			case LITERAL_grant:
			case LITERAL_revoke:
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
			case LITERAL_reuse:
			{
				match(LITERAL_reuse);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_force:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_datafile:
			case LITERAL_next:
			case LITERAL_maxsize:
			case LITERAL_extent:
			case LITERAL_autoextend:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_no:
			case LITERAL_online:
			case LITERAL_offline:
			case LITERAL_read:
			case LITERAL_permanent:
			case LITERAL_grant:
			case LITERAL_revoke:
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
			case LITERAL_autoextend:
			{
				autoextend_clause();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_force:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_datafile:
			case LITERAL_next:
			case LITERAL_maxsize:
			case LITERAL_extent:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_no:
			case LITERAL_online:
			case LITERAL_offline:
			case LITERAL_read:
			case LITERAL_permanent:
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_99);
			} else {
			  throw ex;
			}
		}
	}
	
	public void tablespace_group_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_tablespace);
			match(LITERAL_group);
			identifier2();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void extent_management_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_extent);
			match(LITERAL_management);
			{
			switch ( LA(1)) {
			case LITERAL_local:
			{
				{
				match(LITERAL_local);
				{
				switch ( LA(1)) {
				case LITERAL_uniform:
				{
					match(LITERAL_uniform);
					{
					match(LITERAL_size);
					{
					switch ( LA(1)) {
					case STORAGE_SIZE:
					{
						match(STORAGE_SIZE);
						break;
					}
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case EOF:
					case AT_PREFIXED:
					case SEMI:
					case OPEN_PAREN:
					case DIVIDE:
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_force:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_column:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_temporary:
					case LITERAL_datafile:
					case LITERAL_next:
					case LITERAL_maxsize:
					case LITERAL_extent:
					case LITERAL_retention:
					case LITERAL_logging:
					case LITERAL_nologging:
					case LITERAL_no:
					case LITERAL_online:
					case LITERAL_offline:
					case LITERAL_read:
					case LITERAL_permanent:
					case LITERAL_grant:
					case LITERAL_revoke:
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
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_force:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_temporary:
				case LITERAL_datafile:
				case LITERAL_next:
				case LITERAL_maxsize:
				case LITERAL_extent:
				case LITERAL_retention:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_no:
				case LITERAL_online:
				case LITERAL_offline:
				case LITERAL_read:
				case LITERAL_permanent:
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_dictionary:
			{
				match(LITERAL_dictionary);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_100);
			} else {
			  throw ex;
			}
		}
	}
	
	public void tablespace_retention_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_retention);
			{
			switch ( LA(1)) {
			case LITERAL_guarantee:
			{
				match(LITERAL_guarantee);
				break;
			}
			case LITERAL_noguarantee:
			{
				match(LITERAL_noguarantee);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_tablespace_rest() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_datafile:
			{
				match(LITERAL_datafile);
				file_specification();
				{
				_loop276:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						file_specification();
					}
					else {
						break _loop276;
					}
					
				} while (true);
				}
				break;
			}
			case LITERAL_force:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_no:
			{
				tablespace_logging_clauses();
				break;
			}
			case LITERAL_next:
			{
				{
				match(LITERAL_next);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
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
			case LITERAL_maxsize:
			{
				{
				match(LITERAL_maxsize);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
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
			case LITERAL_extent:
			{
				extent_management_clause();
				break;
			}
			case LITERAL_temporary:
			case LITERAL_online:
			case LITERAL_offline:
			case LITERAL_read:
			case LITERAL_permanent:
			{
				tablespace_state_clause();
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
				recover(ex,_tokenSet_101);
			} else {
			  throw ex;
			}
		}
	}
	
	public void tablespace_logging_clauses() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_logging:
			{
				match(LITERAL_logging);
				break;
			}
			case LITERAL_nologging:
			{
				match(LITERAL_nologging);
				break;
			}
			case LITERAL_force:
			case LITERAL_no:
			{
				{
				switch ( LA(1)) {
				case LITERAL_no:
				{
					match(LITERAL_no);
					break;
				}
				case LITERAL_force:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_force);
				match(LITERAL_logging);
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
				recover(ex,_tokenSet_101);
			} else {
			  throw ex;
			}
		}
	}
	
	public void tablespace_state_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_online:
			{
				match(LITERAL_online);
				break;
			}
			case LITERAL_offline:
			{
				match(LITERAL_offline);
				{
				switch ( LA(1)) {
				case LITERAL_normal:
				{
					match(LITERAL_normal);
					break;
				}
				case LITERAL_immediate:
				{
					match(LITERAL_immediate);
					break;
				}
				default:
					if ((LA(1)==LITERAL_temporary) && (_tokenSet_101.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_temporary);
					}
					else if ((_tokenSet_101.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case LITERAL_read:
			{
				match(LITERAL_read);
				{
				switch ( LA(1)) {
				case LITERAL_only:
				{
					match(LITERAL_only);
					break;
				}
				case LITERAL_write:
				{
					match(LITERAL_write);
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
			case LITERAL_permanent:
			{
				match(LITERAL_permanent);
				break;
			}
			case LITERAL_temporary:
			{
				match(LITERAL_temporary);
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
				recover(ex,_tokenSet_101);
			} else {
			  throw ex;
			}
		}
	}
	
	public void autoextend_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_autoextend);
			{
			switch ( LA(1)) {
			case LITERAL_on:
			{
				{
				match(LITERAL_on);
				{
				if ((LA(1)==LITERAL_next) && (LA(2)==STORAGE_SIZE) && (_tokenSet_99.member(LA(3)))) {
					match(LITERAL_next);
					match(STORAGE_SIZE);
				}
				else if ((_tokenSet_99.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_maxsize) && (LA(2)==STORAGE_SIZE||LA(2)==NUMBER) && (_tokenSet_99.member(LA(3)))) {
					match(LITERAL_maxsize);
					{
					switch ( LA(1)) {
					case STORAGE_SIZE:
					{
						match(STORAGE_SIZE);
						break;
					}
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else if ((_tokenSet_99.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_off:
			{
				match(LITERAL_off);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_99);
			} else {
			  throw ex;
			}
		}
	}
	
	public void datafile_tempfile_clauses() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_add:
			{
				match(LITERAL_add);
				{
				switch ( LA(1)) {
				case LITERAL_datafile:
				{
					match(LITERAL_datafile);
					break;
				}
				case LITERAL_tempfile:
				{
					match(LITERAL_tempfile);
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
				case QUOTED_STR:
				{
					file_specification();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_rename:
			{
				match(LITERAL_rename);
				match(LITERAL_datafile);
				string_literal();
				{
				_loop314:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						string_literal();
					}
					else {
						break _loop314;
					}
					
				} while (true);
				}
				match(LITERAL_to);
				string_literal();
				{
				_loop316:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						string_literal();
					}
					else {
						break _loop316;
					}
					
				} while (true);
				}
				break;
			}
			case LITERAL_tempfile:
			case LITERAL_datafile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_datafile:
				{
					match(LITERAL_datafile);
					break;
				}
				case LITERAL_tempfile:
				{
					match(LITERAL_tempfile);
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
				case LITERAL_online:
				{
					match(LITERAL_online);
					break;
				}
				case LITERAL_offline:
				{
					match(LITERAL_offline);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_tablespace() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_tablespace);
			tablespace_name();
			{
			switch ( LA(1)) {
			case LITERAL_autoextend:
			{
				autoextend_clause();
				break;
			}
			case LITERAL_begin:
			case LITERAL_end:
			{
				{
				switch ( LA(1)) {
				case LITERAL_begin:
				{
					match(LITERAL_begin);
					break;
				}
				case LITERAL_end:
				{
					match(LITERAL_end);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_backup);
				break;
			}
			case LITERAL_coalesce:
			{
				match(LITERAL_coalesce);
				break;
			}
			case LITERAL_temporary:
			case LITERAL_online:
			case LITERAL_offline:
			case LITERAL_read:
			case LITERAL_permanent:
			{
				tablespace_state_clause();
				break;
			}
			case LITERAL_retention:
			{
				tablespace_retention_clause();
				break;
			}
			case LITERAL_minimum:
			{
				match(LITERAL_minimum);
				match(LITERAL_extent);
				match(STORAGE_SIZE);
				break;
			}
			case LITERAL_force:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_no:
			{
				tablespace_logging_clauses();
				break;
			}
			default:
				if ((LA(1)==LITERAL_rename) && (LA(2)==LITERAL_to)) {
					match(LITERAL_rename);
					match(LITERAL_to);
					tablespace_name();
				}
				else if ((_tokenSet_102.member(LA(1))) && (_tokenSet_103.member(LA(2)))) {
					datafile_tempfile_clauses();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sequence_opt() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_maxvalue:
			{
				{
				match(LITERAL_maxvalue);
				numeric_literal();
				}
				break;
			}
			case LITERAL_minvalue:
			{
				{
				match(LITERAL_minvalue);
				numeric_literal();
				}
				break;
			}
			case LITERAL_cycle:
			{
				match(LITERAL_cycle);
				break;
			}
			case LITERAL_nocycle:
			{
				match(LITERAL_nocycle);
				break;
			}
			case LITERAL_cache:
			{
				{
				match(LITERAL_cache);
				numeric_literal();
				}
				break;
			}
			case LITERAL_nocache:
			{
				match(LITERAL_nocache);
				break;
			}
			case LITERAL_increment:
			{
				{
				match(LITERAL_increment);
				match(LITERAL_by);
				numeric_literal();
				}
				break;
			}
			case LITERAL_start:
			{
				{
				match(LITERAL_start);
				match(LITERAL_with);
				numeric_literal();
				}
				break;
			}
			case LITERAL_order:
			{
				match(LITERAL_order);
				break;
			}
			case LITERAL_noorder:
			{
				match(LITERAL_noorder);
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
				recover(ex,_tokenSet_104);
			} else {
			  throw ex;
			}
		}
	}
	
	public void synonym_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(SYNONYM_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_105);
			} else {
			  throw ex;
			}
		}
	}
	
	public void synonym_obj() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
				if ( inputState.guessing==0 ) {
					__markRule(SYNONYM_OBJ);
				}
			}
			else if ((LA(1)==TABLE_NAME_SPEC)) {
				match(TABLE_NAME_SPEC);
				if ( inputState.guessing==0 ) {
					__markRule(SYNONYM_OBJ_WITH_LINK);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trigger_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(TRIGGER_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_106);
			} else {
			  throw ex;
			}
		}
	}
	
	public void dml_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			insert_update_delete();
			{
			_loop356:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					insert_update_delete();
				}
				else {
					break _loop356;
				}
				
			} while (true);
			}
			match(LITERAL_on);
			table_ref();
			if ( inputState.guessing==0 ) {
				__markRule(DML_TRIGGER_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ddl_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_create:
			{
				match(LITERAL_create);
				break;
			}
			case LITERAL_alter:
			{
				match(LITERAL_alter);
				break;
			}
			case LITERAL_drop:
			{
				match(LITERAL_drop);
				break;
			}
			case LITERAL_analyze:
			{
				match(LITERAL_analyze);
				break;
			}
			case LITERAL_associate:
			{
				{
				match(LITERAL_associate);
				match(LITERAL_statistics);
				}
				break;
			}
			case LITERAL_audit:
			{
				match(LITERAL_audit);
				break;
			}
			case LITERAL_noaudit:
			{
				match(LITERAL_noaudit);
				break;
			}
			case LITERAL_comment:
			{
				match(LITERAL_comment);
				break;
			}
			case LITERAL_ddl:
			{
				match(LITERAL_ddl);
				break;
			}
			case LITERAL_diassociate:
			{
				{
				match(LITERAL_diassociate);
				match(LITERAL_statistics);
				}
				break;
			}
			case LITERAL_grant:
			{
				match(LITERAL_grant);
				break;
			}
			case LITERAL_rename:
			{
				match(LITERAL_rename);
				break;
			}
			case LITERAL_revoke:
			{
				match(LITERAL_revoke);
				break;
			}
			case LITERAL_truncate:
			{
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
			if ( inputState.guessing==0 ) {
				__markRule(DDL_TRIGGER_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void db_event_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_startup:
			{
				match(LITERAL_startup);
				break;
			}
			case LITERAL_shutdown:
			{
				match(LITERAL_shutdown);
				break;
			}
			case LITERAL_servererror:
			{
				match(LITERAL_servererror);
				break;
			}
			case LITERAL_logon:
			{
				match(LITERAL_logon);
				break;
			}
			case LITERAL_logoff:
			{
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
			if ( inputState.guessing==0 ) {
				__markRule(DB_EVNT_TRIGGER_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void instead_of_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_instead);
			match(LITERAL_of);
			{
			switch ( LA(1)) {
			case LITERAL_delete:
			{
				match(LITERAL_delete);
				break;
			}
			case LITERAL_insert:
			{
				match(LITERAL_insert);
				break;
			}
			case LITERAL_update:
			{
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
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(INSTEADOF_TRIGGER);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void for_each() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_for);
			match(LITERAL_each);
			match(LITERAL_row);
			if ( inputState.guessing==0 ) {
				__markRule(TRIGGER_FOR_EACH);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void referencing_old_new() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_referencing);
			{
			_loop365:
			do {
				switch ( LA(1)) {
				case LITERAL_old:
				{
					{
					match(LITERAL_old);
					match(LITERAL_as);
					identifier2();
					}
					break;
				}
				case LITERAL_new:
				{
					{
					match(LITERAL_new);
					match(LITERAL_as);
					identifier2();
					}
					break;
				}
				default:
				{
					break _loop365;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trigger_when() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_when);
			condition();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_108);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trigger_target() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_schema:
			{
				match(LITERAL_schema);
				break;
			}
			case LITERAL_database:
			{
				match(LITERAL_database);
				if ( inputState.guessing==0 ) {
					__markRule(TRIGGER_TARGET);
				}
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
				recover(ex,_tokenSet_107);
			} else {
			  throw ex;
			}
		}
	}
	
	public void insert_update_delete() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_insert:
			{
				match(LITERAL_insert);
				break;
			}
			case LITERAL_update:
			{
				{
				match(LITERAL_update);
				{
				switch ( LA(1)) {
				case LITERAL_of:
				{
					match(LITERAL_of);
					identifier2();
					{
					_loop371:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop371;
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
				break;
			}
			case LITERAL_delete:
			{
				match(LITERAL_delete);
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
				recover(ex,_tokenSet_109);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_trigger);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_disable||LA(2)==LITERAL_enable)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			trigger_name();
			enable_disable_clause();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void enable_disable_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_enable:
			{
				match(LITERAL_enable);
				break;
			}
			case LITERAL_disable:
			{
				match(LITERAL_disable);
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
	}
	
	public void index_column_spec_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			{
			switch ( LA(1)) {
			case LITERAL_asc:
			{
				match(LITERAL_asc);
				break;
			}
			case LITERAL_desc:
			{
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
			_loop385:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier2();
					{
					switch ( LA(1)) {
					case LITERAL_asc:
					{
						match(LITERAL_asc);
						break;
					}
					case LITERAL_desc:
					{
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
					break _loop385;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void physical_properties() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_storage:
			case LITERAL_tablespace:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_segment:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_organization:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_segment:
				{
					deferred_segment_creation();
					break;
				}
				case LITERAL_storage:
				case LITERAL_tablespace:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_organization:
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
				case LITERAL_storage:
				case LITERAL_tablespace:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				{
					segment_attributes_clause();
					break;
				}
				case LITERAL_organization:
				{
					organization_spec();
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
			case LITERAL_cluster:
			{
				cluster_clause();
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
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_properties() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_local:
			case LITERAL_partition:
			{
				table_partitioning_clause();
				if ( inputState.guessing==0 ) {
					__markRule(PARTITION_SPEC);
				}
				break;
			}
			case LITERAL_cache:
			case LITERAL_nocache:
			{
				cache_clause();
				break;
			}
			case LITERAL_parallel:
			case LITERAL_noparallel:
			{
				parallel_clause();
				break;
			}
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			{
				monitoring_clause();
				break;
			}
			default:
				if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (_tokenSet_112.member(LA(2)))) {
					alter_table_options();
				}
				else if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (LA(2)==LITERAL_row)) {
					row_movement_clause();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_name_ddl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_NAME_DDL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_113);
			} else {
			  throw ex;
			}
		}
	}
	
	public void constaraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_constraint);
			constraint_name();
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				pk_spec_constr();
				break;
			}
			case LITERAL_foreign:
			{
				fk_spec_constr();
				break;
			}
			case LITERAL_check:
			{
				check_condition();
				break;
			}
			case LITERAL_unique:
			{
				unique_contsr();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void nested_tab_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_nested);
			match(LITERAL_table);
			identifier();
			match(LITERAL_store);
			match(LITERAL_as);
			identifier();
			{
			switch ( LA(1)) {
			case LITERAL_return:
			{
				match(LITERAL_return);
				match(LITERAL_as);
				{
				switch ( LA(1)) {
				case LITERAL_locator:
				{
					match(LITERAL_locator);
					break;
				}
				case LITERAL_value:
				{
					match(LITERAL_value);
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_storage:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_lob:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_organization:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_114);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lob_storage_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_lob);
			match(OPEN_PAREN);
			identifier();
			{
			_loop418:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier();
				}
				else {
					break _loop418;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			match(LITERAL_store);
			match(LITERAL_as);
			match(OPEN_PAREN);
			{
			int _cnt420=0;
			_loop420:
			do {
				if ((_tokenSet_115.member(LA(1)))) {
					lob_parameters();
				}
				else {
					if ( _cnt420>=1 ) { break _loop420; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt420++;
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void select_expression() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			select_first();
			{
			_loop1384:
			do {
				if (((LA(1) >= LITERAL_union && LA(1) <= LITERAL_minus))) {
					{
					switch ( LA(1)) {
					case LITERAL_union:
					{
						{
						match(LITERAL_union);
						{
						switch ( LA(1)) {
						case LITERAL_all:
						{
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
						match(LITERAL_intersect);
						break;
					}
					case LITERAL_minus:
					{
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
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
				}
				else {
					break _loop1384;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				{  __markRule(SELECT_EXPRESSION_UNION); }
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_116);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cache_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cache:
			{
				match(LITERAL_cache);
				break;
			}
			case LITERAL_nocache:
			{
				match(LITERAL_nocache);
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
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lob_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			{
				{
				match(LITERAL_tablespace);
				identifier2();
				}
				break;
			}
			case LITERAL_storage:
			{
				storage_spec();
				break;
			}
			case LITERAL_chunk:
			{
				{
				match(LITERAL_chunk);
				match(NUMBER);
				}
				break;
			}
			case LITERAL_retention:
			{
				match(LITERAL_retention);
				break;
			}
			case LITERAL_pctversion:
			{
				{
				match(LITERAL_pctversion);
				match(NUMBER);
				}
				break;
			}
			case LITERAL_freepools:
			{
				{
				match(LITERAL_freepools);
				match(NUMBER);
				}
				break;
			}
			case LITERAL_disable:
			case LITERAL_enable:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_enable:
				{
					match(LITERAL_enable);
					break;
				}
				case LITERAL_disable:
				{
					match(LITERAL_disable);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_storage);
				match(LITERAL_in);
				match(LITERAL_row);
				}
				break;
			}
			default:
				if ((LA(1)==LITERAL_cache) && (_tokenSet_117.member(LA(2))) && (_tokenSet_118.member(LA(3)))) {
					match(LITERAL_cache);
				}
				else if ((LA(1)==LITERAL_cache||LA(1)==LITERAL_nocache) && (_tokenSet_119.member(LA(2))) && (_tokenSet_118.member(LA(3)))) {
					{
					{
					switch ( LA(1)) {
					case LITERAL_nocache:
					{
						match(LITERAL_nocache);
						break;
					}
					case LITERAL_cache:
					{
						{
						match(LITERAL_cache);
						match(LITERAL_reads);
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
					case LITERAL_logging:
					case LITERAL_nologging:
					case LITERAL_filesystem_like_logging:
					{
						logging_clause();
						break;
					}
					case CLOSE_PAREN:
					case LITERAL_storage:
					case LITERAL_disable:
					case LITERAL_enable:
					case LITERAL_tablespace:
					case LITERAL_retention:
					case LITERAL_cache:
					case LITERAL_nocache:
					case LITERAL_chunk:
					case LITERAL_pctversion:
					case LITERAL_freepools:
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
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_117);
			} else {
			  throw ex;
			}
		}
	}
	
	public void storage_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_storage);
			match(OPEN_PAREN);
			{
			int _cnt531=0;
			_loop531:
			do {
				if ((_tokenSet_120.member(LA(1)))) {
					storage_params();
				}
				else {
					if ( _cnt531>=1 ) { break _loop531; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt531++;
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_121);
			} else {
			  throw ex;
			}
		}
	}
	
	public void logging_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_logging:
			{
				match(LITERAL_logging);
				break;
			}
			case LITERAL_nologging:
			{
				match(LITERAL_nologging);
				break;
			}
			case LITERAL_filesystem_like_logging:
			{
				match(LITERAL_filesystem_like_logging);
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
				recover(ex,_tokenSet_121);
			} else {
			  throw ex;
			}
		}
	}
	
	public void deferred_segment_creation() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_segment);
			match(LITERAL_creation);
			{
			switch ( LA(1)) {
			case LITERAL_immediate:
			{
				match(LITERAL_immediate);
				break;
			}
			case LITERAL_deferred:
			{
				match(LITERAL_deferred);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_122);
			} else {
			  throw ex;
			}
		}
	}
	
	public void segment_attributes_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_storage:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			{
				physical_attributes_clause();
				break;
			}
			case LITERAL_tablespace:
			{
				{
				match(LITERAL_tablespace);
				identifier2();
				}
				break;
			}
			case LITERAL_online:
			{
				match(LITERAL_online);
				break;
			}
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			{
				logging_clause();
				break;
			}
			case LITERAL_compress:
			case LITERAL_nocompress:
			{
				table_compression();
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
				recover(ex,_tokenSet_123);
			} else {
			  throw ex;
			}
		}
	}
	
	public void organization_spec() throws RecognitionException, TokenStreamException {
		
		
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
					match(LITERAL_including);
					identifier2();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_storage:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_tablespace:
				case LITERAL_local:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_organization:
				case LITERAL_pctthreshold:
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
					match(LITERAL_pctthreshold);
					numeric_literal();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_storage:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_tablespace:
				case LITERAL_local:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_organization:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cluster_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cluster);
			identifier();
			match(OPEN_PAREN);
			column_name_ddl();
			{
			_loop440:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ddl();
				}
				else {
					break _loop440;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void physical_attributes_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_pctfree:
			{
				{
				match(LITERAL_pctfree);
				numeric_literal();
				}
				break;
			}
			case LITERAL_pctused:
			{
				{
				match(LITERAL_pctused);
				numeric_literal();
				}
				break;
			}
			case LITERAL_initrans:
			{
				{
				match(LITERAL_initrans);
				numeric_literal();
				}
				break;
			}
			case LITERAL_maxtrans:
			{
				{
				match(LITERAL_maxtrans);
				numeric_literal();
				}
				break;
			}
			case LITERAL_compute:
			{
				{
				match(LITERAL_compute);
				match(LITERAL_statistics);
				{
				if ((LA(1)==LITERAL_parallel) && (_tokenSet_124.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_parallel);
				}
				else if ((LA(1)==LITERAL_noparallel) && (_tokenSet_124.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_noparallel);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING)) {
					identifier();
				}
				else if ((_tokenSet_124.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_storage:
			{
				storage_spec();
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
				recover(ex,_tokenSet_124);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_compression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_compress:
			{
				{
				match(LITERAL_compress);
				{
				switch ( LA(1)) {
				case NUMBER:
				{
					numeric_literal();
					break;
				}
				case LITERAL_for:
				{
					{
					match(LITERAL_for);
					{
					switch ( LA(1)) {
					case LITERAL_all:
					{
						match(LITERAL_all);
						break;
					}
					case LITERAL_direct_load:
					{
						match(LITERAL_direct_load);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_cascade:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_storage:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_tablespace:
				case LITERAL_local:
				case LITERAL_logging:
				case LITERAL_nologging:
				case LITERAL_online:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_as:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_filesystem_like_logging:
				case LITERAL_compress:
				case LITERAL_nocompress:
				case LITERAL_pctfree:
				case LITERAL_pctused:
				case LITERAL_initrans:
				case LITERAL_maxtrans:
				case LITERAL_compute:
				case LITERAL_parallel:
				case LITERAL_noparallel:
				case LITERAL_monitoring:
				case LITERAL_nomonitoring:
				case LITERAL_partition:
				case LITERAL_overflow:
				case LITERAL_organization:
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
				}
				break;
			}
			case LITERAL_nocompress:
			{
				match(LITERAL_nocompress);
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
				recover(ex,_tokenSet_125);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_partitioning_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==LITERAL_partition) && (LA(2)==LITERAL_by) && (LA(3)==LITERAL_range)) {
				range_partitions();
				if ( inputState.guessing==0 ) {
					__markRule(RANGE_PARTITION);
				}
			}
			else if ((LA(1)==LITERAL_partition) && (LA(2)==LITERAL_by) && (LA(3)==LITERAL_hash)) {
				hash_partitions();
				if ( inputState.guessing==0 ) {
					__markRule(HASH_PARTITION);
				}
			}
			else if ((LA(1)==LITERAL_local)) {
				local_partitioned_index();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void parallel_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_parallel:
			{
				{
				match(LITERAL_parallel);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_degree) && (LA(3)==NUMBER||LA(3)==LITERAL_default)) {
					{
					match(OPEN_PAREN);
					match(LITERAL_degree);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case LITERAL_default:
					{
						match(LITERAL_default);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LITERAL_instances);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case LITERAL_default:
					{
						match(LITERAL_default);
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
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
				}
				else if ((_tokenSet_126.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_noparallel:
			{
				match(LITERAL_noparallel);
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
				recover(ex,_tokenSet_126);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_table_options() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			enable_disable_clause();
			{
			switch ( LA(1)) {
			case LITERAL_validate:
			{
				match(LITERAL_validate);
				break;
			}
			case LITERAL_novalidate:
			{
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
				match(LITERAL_unique);
				{
				match(OPEN_PAREN);
				identifier2();
				{
				match(COMMA);
				identifier2();
				}
				match(CLOSE_PAREN);
				}
				}
				break;
			}
			case LITERAL_primary:
			{
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				}
				break;
			}
			case LITERAL_constraint:
			{
				{
				match(LITERAL_constraint);
				identifier2();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void monitoring_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_monitoring:
			{
				match(LITERAL_monitoring);
				break;
			}
			case LITERAL_nomonitoring:
			{
				match(LITERAL_nomonitoring);
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
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void range_partitions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			match(LITERAL_by);
			match(LITERAL_range);
			match(OPEN_PAREN);
			column_name_ref();
			{
			_loop462:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop462;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_interval:
			{
				match(LITERAL_interval);
				plsql_expression();
				{
				switch ( LA(1)) {
				case LITERAL_store:
				{
					match(LITERAL_store);
					match(LITERAL_in);
					match(OPEN_PAREN);
					identifier2();
					{
					_loop466:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop466;
						}
						
					} while (true);
					}
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
			match(OPEN_PAREN);
			partition_item();
			{
			_loop468:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					partition_item();
				}
				else {
					break _loop468;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void hash_partitions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			match(LITERAL_by);
			match(LITERAL_hash);
			match(OPEN_PAREN);
			column_name_ref();
			{
			_loop481:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop481;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				{
				int _cnt484=0;
				_loop484:
				do {
					if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_partition) && (_tokenSet_127.member(LA(3)))) {
						individual_hash_partitions();
					}
					else {
						if ( _cnt484>=1 ) { break _loop484; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt484++;
				} while (true);
				}
				break;
			}
			case LITERAL_partitions:
			{
				hash_partitions_by_quantity();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((LA(1)==LITERAL_cache) && (LA(2)==LITERAL_parallel||LA(2)==LITERAL_noparallel) && (_tokenSet_53.member(LA(3)))) {
				{
				match(LITERAL_cache);
				{
				switch ( LA(1)) {
				case LITERAL_parallel:
				{
					match(LITERAL_parallel);
					break;
				}
				case LITERAL_noparallel:
				{
					match(LITERAL_noparallel);
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
			else if ((LA(1)==LITERAL_nocache) && (_tokenSet_53.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_nocache);
			}
			else if ((_tokenSet_53.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void local_partitioned_index() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_local);
			{
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_partition) && (_tokenSet_128.member(LA(3)))) {
				on_range_partitioned_table();
			}
			else if ((_tokenSet_129.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_129);
			} else {
			  throw ex;
			}
		}
	}
	
	public void partition_item() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_values)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			range_values_clause();
			{
			int _cnt472=0;
			_loop472:
			do {
				if ((_tokenSet_130.member(LA(1)))) {
					table_partition_description();
				}
				else {
					if ( _cnt472>=1 ) { break _loop472; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt472++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void range_values_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_values);
			match(LITERAL_less);
			match(LITERAL_than);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case NUMBER:
			{
				numeric_literal();
				break;
			}
			case LITERAL_maxvalue:
			{
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
			_loop477:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					{
					switch ( LA(1)) {
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case LITERAL_maxvalue:
					{
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
					break _loop477;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_130);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_partition_description() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_131.member(LA(1))) && (_tokenSet_132.member(LA(2))) && (_tokenSet_133.member(LA(3)))) {
				segment_attributes_clause();
			}
			else if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_134.member(LA(2))) && (_tokenSet_135.member(LA(3)))) {
				table_compression();
			}
			else if ((LA(1)==LITERAL_overflow)) {
				match(LITERAL_overflow);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_136);
			} else {
			  throw ex;
			}
		}
	}
	
	public void individual_hash_partitions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			hash_partition_spec();
			{
			_loop490:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					hash_partition_spec();
				}
				else {
					break _loop490;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_129);
			} else {
			  throw ex;
			}
		}
	}
	
	public void hash_partitions_by_quantity() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partitions);
			numeric_literal();
			{
			switch ( LA(1)) {
			case LITERAL_store:
			{
				match(LITERAL_store);
				match(LITERAL_in);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop500:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop500;
					}
					
				} while (true);
				}
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_storage:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_overflow:
			case LITERAL_organization:
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
			if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_137.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				table_compression();
			}
			else if ((_tokenSet_125.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_overflow:
			{
				match(LITERAL_overflow);
				match(LITERAL_store);
				match(LITERAL_in);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop504:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop504;
					}
					
				} while (true);
				}
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_cascade:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_storage:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_as:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_filesystem_like_logging:
			case LITERAL_compress:
			case LITERAL_nocompress:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_partition:
			case LITERAL_organization:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_129);
			} else {
			  throw ex;
			}
		}
	}
	
	public void hash_partition_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_138.member(LA(2))) && (_tokenSet_139.member(LA(3)))) {
				identifier2();
			}
			else if ((_tokenSet_138.member(LA(1))) && (_tokenSet_139.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop494:
			do {
				if ((_tokenSet_140.member(LA(1)))) {
					partition_storage_clause();
				}
				else {
					break _loop494;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void partition_storage_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			{
				{
				match(LITERAL_tablespace);
				identifier2();
				}
				break;
			}
			case LITERAL_overflow:
			{
				match(LITERAL_overflow);
				break;
			}
			case LITERAL_compress:
			case LITERAL_nocompress:
			{
				table_compression();
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
				recover(ex,_tokenSet_138);
			} else {
			  throw ex;
			}
		}
	}
	
	public void external_table_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_external);
			match(OPEN_PAREN);
			match(LITERAL_type);
			{
			switch ( LA(1)) {
			case LITERAL_oracle_loader:
			{
				oracle_loader_params();
				break;
			}
			case LITERAL_oracle_datapump:
			{
				oracle_datapump_params();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			location();
			match(CLOSE_PAREN);
			{
			_loop1615:
			do {
				if ((LA(1)==LITERAL_reject)) {
					reject_spec();
				}
				else if ((LA(1)==LITERAL_parallel||LA(1)==LITERAL_noparallel) && (_tokenSet_141.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					parallel_clause();
				}
				else {
					break _loop1615;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void reject_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_reject);
			match(LITERAL_limit);
			{
			switch ( LA(1)) {
			case LITERAL_unlimited:
			{
				match(LITERAL_unlimited);
				break;
			}
			case NUMBER:
			{
				match(NUMBER);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_142);
			} else {
			  throw ex;
			}
		}
	}
	
	public void storage_params() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_initial:
			{
				{
				match(LITERAL_initial);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
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
			case LITERAL_next:
			{
				{
				match(LITERAL_next);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
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
			case LITERAL_minextents:
			{
				{
				match(LITERAL_minextents);
				numeric_literal();
				}
				break;
			}
			case LITERAL_maxextents:
			{
				{
				match(LITERAL_maxextents);
				{
				switch ( LA(1)) {
				case NUMBER:
				{
					numeric_literal();
					break;
				}
				case LITERAL_unlimited:
				{
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
				break;
			}
			case LITERAL_pctincrease:
			{
				{
				match(LITERAL_pctincrease);
				numeric_literal();
				}
				break;
			}
			case LITERAL_freelists:
			{
				{
				match(LITERAL_freelists);
				numeric_literal();
				}
				break;
			}
			case LITERAL_freelist:
			{
				{
				match(LITERAL_freelist);
				match(LITERAL_groups);
				numeric_literal();
				}
				break;
			}
			case LITERAL_optimal:
			{
				{
				match(LITERAL_optimal);
				{
				switch ( LA(1)) {
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				case LITERAL_null:
				{
					match(LITERAL_null);
					break;
				}
				case CLOSE_PAREN:
				case LITERAL_next:
				case LITERAL_initial:
				case LITERAL_minextents:
				case LITERAL_maxextents:
				case LITERAL_pctincrease:
				case LITERAL_freelists:
				case LITERAL_freelist:
				case LITERAL_optimal:
				case LITERAL_buffer_pool:
				case LITERAL_flash_cache:
				case LITERAL_cell_flash_cache:
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
				break;
			}
			case LITERAL_buffer_pool:
			{
				{
				match(LITERAL_buffer_pool);
				{
				switch ( LA(1)) {
				case LITERAL_keep:
				{
					match(LITERAL_keep);
					break;
				}
				case LITERAL_recycle:
				{
					match(LITERAL_recycle);
					break;
				}
				case LITERAL_default:
				{
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
				break;
			}
			case LITERAL_flash_cache:
			{
				{
				match(LITERAL_flash_cache);
				{
				switch ( LA(1)) {
				case LITERAL_keep:
				{
					match(LITERAL_keep);
					break;
				}
				case LITERAL_none:
				{
					match(LITERAL_none);
					break;
				}
				case LITERAL_default:
				{
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
				break;
			}
			case LITERAL_cell_flash_cache:
			{
				{
				match(LITERAL_cell_flash_cache);
				{
				switch ( LA(1)) {
				case LITERAL_keep:
				{
					match(LITERAL_keep);
					break;
				}
				case LITERAL_none:
				{
					match(LITERAL_none);
					break;
				}
				case LITERAL_default:
				{
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
				break;
			}
			case LITERAL_encrypt:
			{
				match(LITERAL_encrypt);
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
				recover(ex,_tokenSet_143);
			} else {
			  throw ex;
			}
		}
	}
	
	public void pk_spec_constr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_primary);
			match(LITERAL_key);
			match(OPEN_PAREN);
			owner_column_name_ref_list();
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_using:
			case LITERAL_rely:
			{
				using_index_clause();
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
				__markRule(PK_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void fk_spec_constr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_foreign);
			match(LITERAL_key);
			match(OPEN_PAREN);
			owner_column_name_ref_list();
			match(CLOSE_PAREN);
			match(LITERAL_references);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==OPEN_PAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_ref();
			match(OPEN_PAREN);
			column_name_ref_list();
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_rely:
			{
				match(LITERAL_rely);
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_on:
			case LITERAL_disable:
			case LITERAL_enable:
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
			case LITERAL_disable:
			{
				match(LITERAL_disable);
				break;
			}
			case LITERAL_enable:
			{
				match(LITERAL_enable);
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
			if ((LA(1)==LITERAL_on) && (LA(2)==LITERAL_update) && (_tokenSet_144.member(LA(3)))) {
				match(LITERAL_on);
				match(LITERAL_update);
				referential_actions();
			}
			else if ((LA(1)==COMMA||LA(1)==CLOSE_PAREN||LA(1)==LITERAL_on) && (_tokenSet_145.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_on:
			{
				match(LITERAL_on);
				match(LITERAL_delete);
				referential_actions();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void check_condition() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_check);
			condition();
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_CHECK_CONSTRAINT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void unique_contsr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_unique);
			match(OPEN_PAREN);
			column_name_ref();
			{
			_loop563:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop563;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_using:
			case LITERAL_rely:
			{
				using_index_clause();
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
				__markRule(UNIQUE_CONSTRAINT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void owner_column_name_ref_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ref();
			{
			_loop571:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop571;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(OWNER_COLUMN_NAME_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void using_index_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_rely:
			{
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
			match(LITERAL_using);
			match(LITERAL_index);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_146.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				{
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_147.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				identifier2();
				}
			}
			else if ((_tokenSet_148.member(LA(1))) && (_tokenSet_149.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				index_properties();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (_tokenSet_147.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				enable_disable_clause();
			}
			else if ((_tokenSet_147.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
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
	}
	
	public void column_name_ref_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ref();
			{
			_loop568:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop568;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_NAME_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void referential_actions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_cascade:
			{
				match(LITERAL_cascade);
				break;
			}
			case LITERAL_restrict:
			{
				match(LITERAL_restrict);
				break;
			}
			case LITERAL_no:
			{
				{
				match(LITERAL_no);
				match(LITERAL_action);
				}
				break;
			}
			default:
				if ((LA(1)==LITERAL_set) && (LA(2)==LITERAL_null)) {
					{
					match(LITERAL_set);
					match(LITERAL_null);
					}
				}
				else if ((LA(1)==LITERAL_set) && (LA(2)==LITERAL_default)) {
					{
					match(LITERAL_set);
					match(LITERAL_default);
					}
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
	}
	
	public void alter_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_table);
			table_ref();
			{
			if ((_tokenSet_151.member(LA(1))) && (_tokenSet_152.member(LA(2))) && (_tokenSet_153.member(LA(3)))) {
				constraint_clause();
			}
			else if ((_tokenSet_154.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void constraint_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_add:
			{
				{
				match(LITERAL_add);
				{
				if ((_tokenSet_155.member(LA(1)))) {
					add_syntax_1();
				}
				else if ((LA(1)==OPEN_PAREN)) {
					{
					match(OPEN_PAREN);
					add_syntax_2();
					match(CLOSE_PAREN);
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_modify:
			{
				{
				match(LITERAL_modify);
				{
				if ((LA(1)==LITERAL_primary||LA(1)==LITERAL_constraint||LA(1)==LITERAL_unique) && (_tokenSet_156.member(LA(2))) && (_tokenSet_60.member(LA(3)))) {
					modify_constraint_clause();
				}
				else if ((LA(1)==OPEN_PAREN)) {
					{
					match(OPEN_PAREN);
					modify_syntax_2();
					match(CLOSE_PAREN);
					}
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_157.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					modify_syntax_1();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_rename:
			{
				{
				match(LITERAL_rename);
				{
				switch ( LA(1)) {
				case LITERAL_constraint:
				{
					{
					match(LITERAL_constraint);
					identifier2();
					match(LITERAL_to);
					identifier2();
					}
					break;
				}
				case LITERAL_to:
				{
					{
					match(LITERAL_to);
					identifier2();
					}
					break;
				}
				case LITERAL_column:
				{
					{
					match(LITERAL_column);
					identifier2();
					match(LITERAL_to);
					identifier2();
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
				break;
			}
			case LITERAL_drop:
			{
				match(LITERAL_drop);
				{
				switch ( LA(1)) {
				case LITERAL_column:
				{
					{
					match(LITERAL_column);
					identifier2();
					}
					break;
				}
				case LITERAL_primary:
				case LITERAL_constraint:
				case LITERAL_unique:
				{
					drop_clause();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_154);
			} else {
			  throw ex;
			}
		}
	}
	
	public void add_syntax_1() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_33.member(LA(1))) && (_tokenSet_157.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				column_add_name();
			}
			else if ((_tokenSet_158.member(LA(1))) && (_tokenSet_159.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				inline_out_of_line_constraint();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_154);
			} else {
			  throw ex;
			}
		}
	}
	
	public void add_syntax_2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched609 = false;
			if (((_tokenSet_33.member(LA(1))) && (_tokenSet_160.member(LA(2))) && (_tokenSet_161.member(LA(3))))) {
				int _m609 = mark();
				synPredMatched609 = true;
				inputState.guessing++;
				try {
					{
					column_add_name();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched609 = false;
				}
				rewind(_m609);
				inputState.guessing--;
			}
			if ( synPredMatched609 ) {
				{
				column_add_name();
				{
				_loop612:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_add_name();
					}
					else {
						break _loop612;
					}
					
				} while (true);
				}
				}
			}
			else if ((_tokenSet_158.member(LA(1))) && (_tokenSet_162.member(LA(2))) && (_tokenSet_163.member(LA(3)))) {
				inline_out_of_line_constraint();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void modify_constraint_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				{
				match(LITERAL_constraint);
				identifier();
				{
				switch ( LA(1)) {
				case LITERAL_rely:
				{
					match(LITERAL_rely);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_validate:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				if ((LA(1)==LITERAL_disable) && (_tokenSet_164.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_164.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_164.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_validate:
				{
					match(LITERAL_validate);
					break;
				}
				case LITERAL_novalidate:
				{
					match(LITERAL_novalidate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_primary:
			{
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					identifier2();
					{
					_loop602:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop602;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_154.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_unique:
			{
				{
				match(LITERAL_unique);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop605:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop605;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
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
				recover(ex,_tokenSet_154);
			} else {
			  throw ex;
			}
		}
	}
	
	public void modify_syntax_2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_modi_name();
			{
			_loop616:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_modi_name();
				}
				else {
					break _loop616;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void modify_syntax_1() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_modi_name();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_154);
			} else {
			  throw ex;
			}
		}
	}
	
	public void drop_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_154.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case LITERAL_keep:
					{
						match(LITERAL_keep);
						break;
					}
					case LITERAL_drop:
					{
						match(LITERAL_drop);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LITERAL_index);
				}
				else if ((_tokenSet_154.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_unique:
			{
				{
				match(LITERAL_unique);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop664:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop664;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_154.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case LITERAL_keep:
					{
						match(LITERAL_keep);
						break;
					}
					case LITERAL_drop:
					{
						match(LITERAL_drop);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LITERAL_index);
				}
				else if ((_tokenSet_154.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_constraint:
			{
				{
				match(LITERAL_constraint);
				identifier();
				{
				switch ( LA(1)) {
				case LITERAL_cascade:
				{
					match(LITERAL_cascade);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_154);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_add_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ddl();
			{
			switch ( LA(1)) {
			case LITERAL_long:
			case LITERAL_interval:
			case LITERAL_timestamp:
			case LITERAL_number:
			case LITERAL_binary_integer:
			case LITERAL_natural:
			case LITERAL_positive:
			case LITERAL_char:
			case LITERAL_raw:
			case LITERAL_boolean:
			case LITERAL_date:
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
			case 692:
			case LITERAL_nvarchar:
			case 694:
			case LITERAL_character:
			case LITERAL_rowid:
			case LITERAL_blob:
			case LITERAL_clob:
			case LITERAL_nclob:
			case LITERAL_bfile:
			{
				datatype();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_default:
			case LITERAL_null:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_not:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
			if ((LA(1)==LITERAL_null||LA(1)==LITERAL_not) && (_tokenSet_165.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				not_null();
			}
			else if ((LA(1)==LITERAL_default)) {
				{
				match(LITERAL_default);
				{
				switch ( LA(1)) {
				case LITERAL_sysdate:
				{
					match(LITERAL_sysdate);
					break;
				}
				case LITERAL_systimestamp:
				{
					match(LITERAL_systimestamp);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
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
			else if ((_tokenSet_166.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
			{
				column_constraint2();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
	}
	
	public void inline_out_of_line_constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				match(LITERAL_constraint);
				identifier();
				break;
			}
			case LITERAL_null:
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_check:
			case LITERAL_unique:
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
			case LITERAL_null:
			case LITERAL_not:
			{
				not_null();
				break;
			}
			case LITERAL_unique:
			{
				{
				match(LITERAL_unique);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					identifier2();
					{
					_loop636:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop636;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_167.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				match(LITERAL_primary);
				match(LITERAL_key);
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2))) && (LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					identifier2();
					{
					_loop640:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop640;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_167.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				match(LITERAL_foreign);
				match(LITERAL_key);
				{
				match(OPEN_PAREN);
				identifier2();
				{
				_loop644:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop644;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				match(LITERAL_references);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==OPEN_PAREN)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				table_ref();
				{
				match(OPEN_PAREN);
				identifier2();
				{
				_loop648:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop648;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				{
				if ((LA(1)==LITERAL_rely) && (_tokenSet_168.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_rely);
				}
				else if ((_tokenSet_168.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_168.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_168.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_168.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_validate:
				{
					match(LITERAL_validate);
					break;
				}
				case LITERAL_novalidate:
				{
					match(LITERAL_novalidate);
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_on:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_rely:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				case LITERAL_on:
				{
					match(LITERAL_on);
					match(LITERAL_delete);
					{
					switch ( LA(1)) {
					case LITERAL_cascade:
					{
						match(LITERAL_cascade);
						break;
					}
					case LITERAL_set:
					{
						{
						match(LITERAL_set);
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
				case LITERAL_disable:
				case LITERAL_enable:
				case LITERAL_rely:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
			case LITERAL_check:
			{
				{
				match(LITERAL_check);
				condition();
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_169);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_modi_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ref();
			{
			switch ( LA(1)) {
			case LITERAL_long:
			case LITERAL_interval:
			case LITERAL_timestamp:
			case LITERAL_number:
			case LITERAL_binary_integer:
			case LITERAL_natural:
			case LITERAL_positive:
			case LITERAL_char:
			case LITERAL_raw:
			case LITERAL_boolean:
			case LITERAL_date:
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
			case 692:
			case LITERAL_nvarchar:
			case 694:
			case LITERAL_character:
			case LITERAL_rowid:
			case LITERAL_blob:
			case LITERAL_clob:
			case LITERAL_nclob:
			case LITERAL_bfile:
			{
				datatype();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_default:
			case LITERAL_null:
			case LITERAL_truncate:
			case LITERAL_comment:
			case LITERAL_not:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
			if ((LA(1)==LITERAL_null||LA(1)==LITERAL_not) && (_tokenSet_165.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				not_null();
			}
			else if ((LA(1)==LITERAL_default)) {
				{
				match(LITERAL_default);
				{
				switch ( LA(1)) {
				case LITERAL_sysdate:
				{
					match(LITERAL_sysdate);
					break;
				}
				case LITERAL_systimestamp:
				{
					match(LITERAL_systimestamp);
					break;
				}
				case NUMBER:
				{
					numeric_literal();
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
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
			else if ((_tokenSet_166.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LITERAL_not:
			case LITERAL_primary:
			case LITERAL_references:
			case LITERAL_constraint:
			case LITERAL_check:
			{
				column_constraint2();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
	}
	
	public void constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			inline_out_of_line_constraint();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void enable_disable_clause2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			using_index_clause();
			{
			switch ( LA(1)) {
			case LITERAL_cascade:
			{
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
					match(LITERAL_keep);
					break;
				}
				case LITERAL_drop:
				{
					match(LITERAL_drop);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void index_properties() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_storage:
			case LITERAL_tablespace:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_filesystem_like_logging:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_sort:
			case LITERAL_nosort:
			case LITERAL_reverse:
			case LITERAL_visible:
			case LITERAL_novisible:
			{
				{
				{
				int _cnt683=0;
				_loop683:
				do {
					if ((_tokenSet_170.member(LA(1)))) {
						index_attributes();
					}
					else {
						if ( _cnt683>=1 ) { break _loop683; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt683++;
				} while (true);
				}
				}
				break;
			}
			case LITERAL_global:
			{
				global_partitioned_index();
				break;
			}
			case LITERAL_local:
			{
				local_partitioned_index();
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
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
	}
	
	public void index_attributes() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_tablespace:
			{
				{
				match(LITERAL_tablespace);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_171.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
				}
				else if ((LA(1)==LITERAL_default) && (_tokenSet_171.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_default);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_storage:
			case LITERAL_pctfree:
			case LITERAL_pctused:
			case LITERAL_initrans:
			case LITERAL_maxtrans:
			case LITERAL_compute:
			{
				physical_attributes_clause();
				break;
			}
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_filesystem_like_logging:
			{
				logging_clause();
				break;
			}
			case LITERAL_online:
			{
				match(LITERAL_online);
				break;
			}
			case LITERAL_sort:
			case LITERAL_nosort:
			{
				{
				switch ( LA(1)) {
				case LITERAL_sort:
				{
					match(LITERAL_sort);
					break;
				}
				case LITERAL_nosort:
				{
					match(LITERAL_nosort);
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
			case LITERAL_reverse:
			{
				match(LITERAL_reverse);
				break;
			}
			case LITERAL_visible:
			case LITERAL_novisible:
			{
				{
				switch ( LA(1)) {
				case LITERAL_visible:
				{
					match(LITERAL_visible);
					break;
				}
				case LITERAL_novisible:
				{
					match(LITERAL_novisible);
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
			case LITERAL_parallel:
			case LITERAL_noparallel:
			{
				parallel_clause();
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
				recover(ex,_tokenSet_171);
			} else {
			  throw ex;
			}
		}
	}
	
	public void global_partitioned_index() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_global);
			match(LITERAL_partition);
			match(LITERAL_by);
			{
			switch ( LA(1)) {
			case LITERAL_range:
			{
				{
				match(LITERAL_range);
				match(OPEN_PAREN);
				index_column_spec_list();
				match(CLOSE_PAREN);
				match(OPEN_PAREN);
				index_partitioning_clause();
				match(CLOSE_PAREN);
				}
				break;
			}
			case LITERAL_hash:
			{
				{
				match(LITERAL_hash);
				match(OPEN_PAREN);
				index_column_spec_list();
				match(CLOSE_PAREN);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					individual_hash_partitions();
					break;
				}
				case LITERAL_partitions:
				{
					hash_partitions_by_quantity();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_147);
			} else {
			  throw ex;
			}
		}
	}
	
	public void index_partitioning_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_values)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(LITERAL_values);
			match(LITERAL_less);
			match(LITERAL_than);
			match(OPEN_PAREN);
			numeric_literal();
			{
			_loop697:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					numeric_literal();
				}
				else {
					break _loop697;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			segment_attributes_clause();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void on_range_partitioned_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			local_partition_item();
			{
			_loop702:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					local_partition_item();
				}
				else {
					break _loop702;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_129);
			} else {
			  throw ex;
			}
		}
	}
	
	public void local_partition_item() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_172.member(LA(2))) && (_tokenSet_173.member(LA(3)))) {
				identifier2();
			}
			else if ((_tokenSet_172.member(LA(1))) && (_tokenSet_173.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop706:
			do {
				if ((_tokenSet_131.member(LA(1))) && (_tokenSet_174.member(LA(2))) && (_tokenSet_175.member(LA(3)))) {
					segment_attributes_clause();
				}
				else if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_176.member(LA(2))) && (_tokenSet_177.member(LA(3)))) {
					table_compression();
				}
				else {
					break _loop706;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(TYPE_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_178);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			name_fragment();
			{
			_loop1033:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					name_fragment();
				}
				else {
					break _loop1033;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(TYPE_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_50);
			} else {
			  throw ex;
			}
		}
	}
	
	public void record_item() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			record_item_name();
			type_spec();
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				match(LITERAL_not);
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
					break;
				}
				case ASSIGNMENT_EQ:
				{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void record_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_179);
			} else {
			  throw ex;
			}
		}
	}
	
	public void view_name_ddl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(VIEW_NAME_DDL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_180);
			} else {
			  throw ex;
			}
		}
	}
	
	public void v_column_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_timestamp:
			{
				match(LITERAL_timestamp);
				break;
			}
			case LITERAL_partition:
			{
				match(LITERAL_partition);
				break;
			}
			default:
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(V_COLUMN_DEF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void serially_reusable_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_serially_reusable);
			if ( inputState.guessing==0 ) {
				__markRule(SERIALLY_REUSABLE_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_spec_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_182.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_184.member(LA(3)))) {
				package_obj_spec();
			}
			else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_185.member(LA(2))) && (_tokenSet_186.member(LA(3)))) {
				package_obj_spec_addon();
			}
			else if ((LA(1)==IF_COND_CMPL)) {
				{
				match(IF_COND_CMPL);
				condition();
				match(THEN_COND_CMPL);
				cond_comp_seq();
				{
				switch ( LA(1)) {
				case ELSE_COND_CMPL:
				{
					match(ELSE_COND_CMPL);
					cond_comp_seq();
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
				match(END_COND_CMPL);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CONDITIONAL_COMPILATION);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_subtype:
			{
				subtype_declaration();
				break;
			}
			case LITERAL_type:
			{
				{
				type_definition();
				match(SEMI);
				}
				break;
			}
			case LITERAL_procedure:
			{
				procedure_spec();
				break;
			}
			case LITERAL_function:
			{
				function_spec();
				break;
			}
			case LITERAL_pragma:
			{
				pragmas();
				break;
			}
			default:
				if ((LA(1)==LITERAL_cursor) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_is)) {
					cursor_declaration();
				}
				else if ((LA(1)==LITERAL_cursor) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_return)) {
					cursor_spec();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_spec_addon() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_33.member(LA(1))) && (_tokenSet_188.member(LA(2)))) {
				variable_declaration();
			}
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
				exception_declaration();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cond_comp_seq() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop764:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
				}
				else {
					break _loop764;
				}
				
			} while (true);
			}
			{
			_loop766:
			do {
				if ((_tokenSet_182.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_184.member(LA(3)))) {
					package_obj_spec();
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_185.member(LA(2))) && (_tokenSet_189.member(LA(3)))) {
					package_obj_spec_addon();
				}
				else {
					break _loop766;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COND_COMP_SEQ);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_190);
			} else {
			  throw ex;
			}
		}
	}
	
	public void error_cond_compliation() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ERROR_COND_CMPL);
			string_literal();
			match(END_COND_CMPL);
			if ( inputState.guessing==0 ) {
				__markRule(COND_COMP_ERROR);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_191);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_body() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched828 = false;
			if (((LA(1)==LITERAL_function) && (_tokenSet_24.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
				int _m828 = mark();
				synPredMatched828 = true;
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
					synPredMatched828 = false;
				}
				rewind(_m828);
				inputState.guessing--;
			}
			if ( synPredMatched828 ) {
				function_body();
			}
			else {
				boolean synPredMatched831 = false;
				if (((LA(1)==LITERAL_procedure) && (_tokenSet_24.member(LA(2))) && (_tokenSet_192.member(LA(3))))) {
					int _m831 = mark();
					synPredMatched831 = true;
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
						synPredMatched831 = false;
					}
					rewind(_m831);
					inputState.guessing--;
				}
				if ( synPredMatched831 ) {
					procedure_body();
				}
				else if ((_tokenSet_182.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_184.member(LA(3)))) {
					package_obj_spec();
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_185.member(LA(2))) && (_tokenSet_193.member(LA(3)))) {
					package_obj_spec_addon();
				}
				else if ((LA(1)==IF_COND_CMPL)) {
					condition_compilation();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_13);
				} else {
				  throw ex;
				}
			}
		}
		
	public void package_init_section() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_begin);
			seq_of_statements();
			if ( inputState.guessing==0 ) {
				__markRule(PACKAGE_INIT_SECTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_194);
			} else {
			  throw ex;
			}
		}
	}
	
	public void seq_of_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt834=0;
			_loop834:
			do {
				if ((_tokenSet_195.member(LA(1)))) {
					statement_tmpl();
				}
				else {
					if ( _cnt834>=1 ) { break _loop834; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt834++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STATEMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_196);
			} else {
			  throw ex;
			}
		}
	}
	
	public void subtype_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_subtype);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is) && (_tokenSet_92.member(LA(3)))) {
				type_name();
			}
			else if ((_tokenSet_46.member(LA(1))) && (_tokenSet_197.member(LA(2))) && (_tokenSet_198.member(LA(3)))) {
				datatype();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(LITERAL_is);
			{
			boolean synPredMatched820 = false;
			if (((_tokenSet_92.member(LA(1))) && (_tokenSet_199.member(LA(2))) && (_tokenSet_200.member(LA(3))))) {
				int _m820 = mark();
				synPredMatched820 = true;
				inputState.guessing++;
				try {
					{
					type_spec();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched820 = false;
				}
				rewind(_m820);
				inputState.guessing--;
			}
			if ( synPredMatched820 ) {
				type_spec();
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==PERCENTAGE) && (LA(3)==LITERAL_type)) {
				{
				table_ref();
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
				match(LITERAL_not);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cursor);
			cursor_name();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
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
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_DECLARATION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_spec() throws RecognitionException, TokenStreamException {
		
		Token  c = null;
		Token  o = null;
		
		try {      // for error handling
			c = LT(1);
			match(LITERAL_cursor);
			cursor_name();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				o = LT(1);
				match(OPEN_PAREN);
				{
				parameter_spec();
				{
				_loop1058:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						parameter_spec();
					}
					else {
						break _loop1058;
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
			match(LITERAL_return);
			return_type();
			match(SEMI);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void procedure_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			procedure_declaration();
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    reportError(ex);
					    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
					
			} else {
				throw ex;
			}
		}
	}
	
	public void function_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			function_declaration();
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(FUNCTION_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    reportError(ex);
					    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
					
			} else {
				throw ex;
			}
		}
	}
	
	public void pragmas() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			{
			switch ( LA(1)) {
			case LITERAL_restrict_references:
			{
				{
				match(LITERAL_restrict_references);
				match(OPEN_PAREN);
				identifier3();
				{
				int _cnt785=0;
				_loop785:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier3();
					}
					else {
						if ( _cnt785>=1 ) { break _loop785; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt785++;
				} while (true);
				}
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(RESTRICT_REF_PRAGMA);
				}
				break;
			}
			case LITERAL_interface:
			{
				{
				match(LITERAL_interface);
				match(OPEN_PAREN);
				identifier3();
				{
				int _cnt788=0;
				_loop788:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier3();
					}
					else {
						if ( _cnt788>=1 ) { break _loop788; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt788++;
				} while (true);
				}
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(INTERFACE_PRAGMA);
				}
				break;
			}
			case LITERAL_builtin:
			{
				{
				match(LITERAL_builtin);
				match(OPEN_PAREN);
				string_literal();
				{
				int _cnt791=0;
				_loop791:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						plsql_expression();
					}
					else {
						if ( _cnt791>=1 ) { break _loop791; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt791++;
				} while (true);
				}
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(BUILTIN_PRAGMA);
				}
				break;
			}
			case LITERAL_fipsflag:
			{
				{
				match(LITERAL_fipsflag);
				match(OPEN_PAREN);
				string_literal();
				{
				int _cnt794=0;
				_loop794:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						plsql_expression();
					}
					else {
						if ( _cnt794>=1 ) { break _loop794; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt794++;
				} while (true);
				}
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(FIPSFLAG_PRAGMA);
				}
				break;
			}
			case LITERAL_timestamp:
			{
				{
				match(LITERAL_timestamp);
				match(OPEN_PAREN);
				string_literal();
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(TIMESTAMPG_PRAGMA);
				}
				break;
			}
			case LITERAL_exception_init:
			{
				{
				match(LITERAL_exception_init);
				match(OPEN_PAREN);
				complex_name();
				match(COMMA);
				plsql_expression();
				match(CLOSE_PAREN);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(EXCEPTION_PRAGMA);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void variable_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			variable_name();
			{
			if ((LA(1)==LITERAL_constant)) {
				match(LITERAL_constant);
			}
			else if ((_tokenSet_92.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			type_spec();
			{
			if ((LA(1)==LITERAL_not)) {
				match(LITERAL_not);
				match(LITERAL_null);
			}
			else if ((_tokenSet_201.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==ASSIGNMENT_EQ||LA(1)==LITERAL_default) && (_tokenSet_61.member(LA(2))) && (_tokenSet_202.member(LA(3)))) {
				{
				switch ( LA(1)) {
				case ASSIGNMENT_EQ:
				{
					match(ASSIGNMENT_EQ);
					break;
				}
				case LITERAL_default:
				{
					default1();
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				plsql_expression();
			}
			else if ((_tokenSet_203.member(LA(1))) && (_tokenSet_204.member(LA(2))) && (_tokenSet_205.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_187.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(VARIABLE_DECLARATION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    throw ex;
					
			} else {
				throw ex;
			}
		}
	}
	
	public void exception_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			exception_name();
			match(LITERAL_exception);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_DECL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_187);
			} else {
			  throw ex;
			}
		}
	}
	
	public void complex_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			{
			_loop1173:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					identifier2();
				}
				else {
					break _loop1173;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COMPLEX_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_206);
			} else {
			  throw ex;
			}
		}
	}
	
	public void condition_compilation() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IF_COND_CMPL);
			condition();
			match(THEN_COND_CMPL);
			cond_comp_seq2();
			{
			switch ( LA(1)) {
			case ELSE_COND_CMPL:
			{
				match(ELSE_COND_CMPL);
				cond_comp_seq2();
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
			match(END_COND_CMPL);
			if ( inputState.guessing==0 ) {
				__markRule(CONDITIONAL_COMPILATION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cond_comp_seq2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop801:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
				}
				else {
					break _loop801;
				}
				
			} while (true);
			}
			{
			_loop809:
			do {
				boolean synPredMatched805 = false;
				if (((LA(1)==LITERAL_function) && (_tokenSet_24.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
					int _m805 = mark();
					synPredMatched805 = true;
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
						synPredMatched805 = false;
					}
					rewind(_m805);
					inputState.guessing--;
				}
				if ( synPredMatched805 ) {
					function_body();
				}
				else {
					boolean synPredMatched808 = false;
					if (((LA(1)==LITERAL_procedure) && (_tokenSet_24.member(LA(2))) && (_tokenSet_192.member(LA(3))))) {
						int _m808 = mark();
						synPredMatched808 = true;
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
							synPredMatched808 = false;
						}
						rewind(_m808);
						inputState.guessing--;
					}
					if ( synPredMatched808 ) {
						procedure_body();
					}
					else if ((_tokenSet_182.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_184.member(LA(3)))) {
						package_obj_spec();
					}
					else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_185.member(LA(2))) && (_tokenSet_189.member(LA(3)))) {
						package_obj_spec_addon();
					}
					else {
						break _loop809;
					}
					}
				} while (true);
				}
				if ( inputState.guessing==0 ) {
					__markRule(COND_COMP_SEQ2);
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_190);
				} else {
				  throw ex;
				}
			}
		}
		
	public void function_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_function);
			object_name();
			{
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
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
			{
			switch ( LA(1)) {
			case LITERAL_character:
			{
				character_set();
				break;
			}
			case SEMI:
			case LITERAL_using:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_language:
			case LITERAL_pipelined:
			case LITERAL_parallel_enable:
			case LITERAL_deterministic:
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
				match(LITERAL_pipelined);
				break;
			}
			case SEMI:
			case LITERAL_using:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_language:
			case LITERAL_parallel_enable:
			case LITERAL_deterministic:
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
				match(LITERAL_parallel_enable);
				break;
			}
			case SEMI:
			case LITERAL_using:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_language:
			case LITERAL_deterministic:
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
				match(LITERAL_using);
				identifier2();
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_language:
			case LITERAL_deterministic:
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
				match(LITERAL_deterministic);
				break;
			}
			case SEMI:
			case LITERAL_is:
			case LITERAL_as:
			case LITERAL_language:
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_207);
			} else {
			  throw ex;
			}
		}
	}
	
	public void procedure_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_procedure);
			object_name();
			{
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				argument_list();
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
			if ((LA(1)==LITERAL_is||LA(1)==LITERAL_as) && (LA(2)==LITERAL_language) && (LA(3)==LITERAL_java)) {
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
				match(LITERAL_language);
				match(LITERAL_java);
				match(LITERAL_name);
				string_literal();
			}
			else if ((LA(1)==SEMI||LA(1)==LITERAL_is||LA(1)==LITERAL_as) && (_tokenSet_187.member(LA(2))) && (_tokenSet_208.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    throw ex;
					
			} else {
				throw ex;
			}
		}
	}
	
	public void variable_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_timestamp)) {
				match(LITERAL_timestamp);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(VARIABLE_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_188);
			} else {
			  throw ex;
			}
		}
	}
	
	public void default1() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_default);
			if ( inputState.guessing==0 ) {
				__markRule(DEFAULT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_61);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_NAME);
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
	}
	
	public void argument_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			parameter_spec();
			{
			_loop1128:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					parameter_spec();
				}
				else {
					break _loop1128;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void select_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_72.member(LA(2))) && (_tokenSet_210.member(LA(3)))) {
				select_expression();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_72.member(LA(3)))) {
				subquery();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_211);
			} else {
			  throw ex;
			}
		}
	}
	
	public void statement_tmpl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case START_LABEL:
			{
				{
				match(START_LABEL);
				label_name();
				match(END_LABEL);
				}
				break;
			}
			case IF_COND_CMPL:
			{
				{
				match(IF_COND_CMPL);
				condition();
				match(THEN_COND_CMPL);
				seq_of_statements();
				{
				switch ( LA(1)) {
				case ELSE_COND_CMPL:
				{
					match(ELSE_COND_CMPL);
					seq_of_statements();
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
				match(END_COND_CMPL);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CONDITIONAL_COMPILATION);
				}
				break;
			}
			default:
				if ((_tokenSet_212.member(LA(1)))) {
					{
					statement();
					match(SEMI);
					}
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_213);
			} else {
			  throw ex;
			}
		}
	}
	
	public void statement() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_begin:
			case LITERAL_declare:
			{
				begin_block();
				break;
			}
			case LITERAL_forall:
			{
				forall_loop();
				if ( inputState.guessing==0 ) {
					__markRule(LOOP_STATEMENT);
				}
				break;
			}
			case LITERAL_if:
			{
				if_statement();
				break;
			}
			case LITERAL_goto:
			{
				goto_statement();
				if ( inputState.guessing==0 ) {
					__markRule(GOTO_STATEMENT);
				}
				break;
			}
			case LITERAL_raise:
			{
				raise_statement();
				if ( inputState.guessing==0 ) {
					__markRule(RAISE_STATEMENT);
				}
				break;
			}
			case LITERAL_exit:
			{
				exit_statement();
				if ( inputState.guessing==0 ) {
					__markRule(EXIT_STATEMENT);
				}
				break;
			}
			case LITERAL_null:
			{
				null_statement();
				break;
			}
			case LITERAL_return:
			{
				return_statement();
				if ( inputState.guessing==0 ) {
					__markRule(RETURN_STATEMENT);
				}
				break;
			}
			case LITERAL_case:
			{
				case_statement();
				if ( inputState.guessing==0 ) {
					__markRule(CASE_STATEMENT);
				}
				break;
			}
			case LITERAL_lock:
			{
				lock_table_statement();
				break;
			}
			case LITERAL_fetch:
			{
				fetch_statement();
				break;
			}
			case LITERAL_set:
			{
				set_transaction_command();
				break;
			}
			case LITERAL_alter:
			{
				alter_command();
				break;
			}
			default:
				boolean synPredMatched844 = false;
				if (((_tokenSet_214.member(LA(1))) && (_tokenSet_215.member(LA(2))) && (_tokenSet_216.member(LA(3))))) {
					int _m844 = mark();
					synPredMatched844 = true;
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
						synPredMatched844 = false;
					}
					rewind(_m844);
					inputState.guessing--;
				}
				if ( synPredMatched844 ) {
					loop_statement();
					if ( inputState.guessing==0 ) {
						__markRule(LOOP_STATEMENT);
					}
				}
				else {
					boolean synPredMatched860 = false;
					if (((_tokenSet_217.member(LA(1))) && (_tokenSet_218.member(LA(2))) && (_tokenSet_219.member(LA(3))))) {
						int _m860 = mark();
						synPredMatched860 = true;
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
							case LITERAL_merge:
							{
								match(LITERAL_merge);
								break;
							}
							case LITERAL_grant:
							{
								match(LITERAL_grant);
								break;
							}
							case LITERAL_revoke:
							{
								match(LITERAL_revoke);
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
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched860 = false;
						}
						rewind(_m860);
						inputState.guessing--;
					}
					if ( synPredMatched860 ) {
						sql_statement();
					}
					else {
						boolean synPredMatched862 = false;
						if (((LA(1)==LITERAL_execute) && (LA(2)==LITERAL_immediate) && (_tokenSet_61.member(LA(3))))) {
							int _m862 = mark();
							synPredMatched862 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_execute);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched862 = false;
							}
							rewind(_m862);
							inputState.guessing--;
						}
						if ( synPredMatched862 ) {
							immediate_command();
						}
						else {
							boolean synPredMatched866 = false;
							if (((LA(1)==LITERAL_open) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (_tokenSet_220.member(LA(3))))) {
								int _m866 = mark();
								synPredMatched866 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_open);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched866 = false;
								}
								rewind(_m866);
								inputState.guessing--;
							}
							if ( synPredMatched866 ) {
								open_statement();
							}
							else {
								boolean synPredMatched868 = false;
								if (((LA(1)==LITERAL_close) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==SEMI))) {
									int _m868 = mark();
									synPredMatched868 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_close);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched868 = false;
									}
									rewind(_m868);
									inputState.guessing--;
								}
								if ( synPredMatched868 ) {
									close_statement();
								}
								else if ((LA(1)==LITERAL_savepoint) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==SEMI)) {
									savepoint_statement();
								}
								else {
									boolean synPredMatched872 = false;
									if (((_tokenSet_78.member(LA(1))) && (_tokenSet_81.member(LA(2))) && (_tokenSet_82.member(LA(3))))) {
										int _m872 = mark();
										synPredMatched872 = true;
										inputState.guessing++;
										try {
											{
											plsql_lvalue();
											match(ASSIGNMENT_EQ);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched872 = false;
										}
										rewind(_m872);
										inputState.guessing--;
									}
									if ( synPredMatched872 ) {
										assignment_statement();
									}
									else {
										boolean synPredMatched876 = false;
										if (((_tokenSet_24.member(LA(1))) && (LA(2)==SEMI||LA(2)==DOT) && (_tokenSet_213.member(LA(3))))) {
											int _m876 = mark();
											synPredMatched876 = true;
											inputState.guessing++;
											try {
												{
												{
												_loop875:
												do {
													if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_24.member(LA(3)))) {
														name_fragment_ex();
														match(DOT);
													}
													else {
														break _loop875;
													}
													
												} while (true);
												}
												exec_name_ref();
												matchNot(OPEN_PAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched876 = false;
											}
											rewind(_m876);
											inputState.guessing--;
										}
										if ( synPredMatched876 ) {
											procedure_call_no_args();
										}
										else if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
											{
											procedure_call();
											{
											_loop879:
											do {
												if ((LA(1)==DOT)) {
													match(DOT);
													if ( inputState.guessing==0 ) {
														tag1=true;
													}
													procedure_call();
												}
												else {
													break _loop879;
												}
												
											} while (true);
											}
											}
											if ( inputState.guessing==0 ) {
												
												if(tag1){
												__markRule(COLLECTION_METHOD_CALL2);
												}
												
											}
										}
									else {
										throw new NoViableAltException(LT(1), getFilename());
									}
									}}}}}}}
								}
								catch (RecognitionException ex) {
									if (inputState.guessing==0) {
										reportError(ex);
										recover(ex,_tokenSet_181);
									} else {
									  throw ex;
									}
								}
							}
							
	public void label_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(LABEL_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_221);
			} else {
			  throw ex;
			}
		}
	}
	
	public void loop_statement() throws RecognitionException, TokenStreamException {
		
		
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
				match(LITERAL_while);
				condition();
				}
				break;
			}
			case LITERAL_for:
			{
				{
				match(LITERAL_for);
				{
				boolean synPredMatched1160 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (_tokenSet_61.member(LA(3))))) {
					int _m1160 = mark();
					synPredMatched1160 = true;
					inputState.guessing++;
					try {
						{
						numeric_loop_spec();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1160 = false;
					}
					rewind(_m1160);
					inputState.guessing--;
				}
				if ( synPredMatched1160 ) {
					numeric_loop_spec();
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (_tokenSet_222.member(LA(3)))) {
					cursor_loop_spec();
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
			match(LITERAL_loop);
			seq_of_statements();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void forall_loop() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			forall_header();
			{
			if ((LA(1)==LITERAL_save)) {
				match(LITERAL_save);
				match(LITERAL_exceptions);
			}
			else if ((_tokenSet_212.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			statement();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void if_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_if);
			condition();
			match(LITERAL_then);
			seq_of_statements();
			{
			_loop1364:
			do {
				if ((LA(1)==LITERAL_elsif)) {
					elsif_statements();
				}
				else {
					break _loop1364;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_else:
			{
				else_statements();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void goto_statement() throws RecognitionException, TokenStreamException {
		
		Token  g = null;
		
		try {      // for error handling
			g = LT(1);
			match(LITERAL_goto);
			label_name();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void raise_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_raise);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				exception_name();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exit_statement() throws RecognitionException, TokenStreamException {
		
		Token  e = null;
		Token  w = null;
		
		try {      // for error handling
			e = LT(1);
			match(LITERAL_exit);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				label_name();
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
				match(LITERAL_when);
				condition();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void null_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_null);
			if ( inputState.guessing==0 ) {
				__markRule(NULL_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void return_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_return);
			{
			if ((_tokenSet_73.member(LA(1)))) {
				condition();
			}
			else if ((LA(1)==SEMI)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void case_statement() throws RecognitionException, TokenStreamException {
		
		Token  t = null;
		Token  e = null;
		
		try {      // for error handling
			match(LITERAL_case);
			{
			boolean synPredMatched894 = false;
			if (((_tokenSet_61.member(LA(1))))) {
				int _m894 = mark();
				synPredMatched894 = true;
				inputState.guessing++;
				try {
					{
					plsql_expression();
					{
					int _cnt893=0;
					_loop893:
					do {
						if ((LA(1)==LITERAL_when)) {
							match(LITERAL_when);
							plsql_expression();
							match(LITERAL_then);
							seq_of_statements();
						}
						else {
							if ( _cnt893>=1 ) { break _loop893; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt893++;
					} while (true);
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched894 = false;
				}
				rewind(_m894);
				inputState.guessing--;
			}
			if ( synPredMatched894 ) {
				plsql_expression();
				{
				int _cnt896=0;
				_loop896:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						plsql_expression();
						match(LITERAL_then);
						seq_of_statements();
					}
					else {
						if ( _cnt896>=1 ) { break _loop896; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt896++;
				} while (true);
				}
			}
			else if ((LA(1)==LITERAL_when)) {
				{
				int _cnt898=0;
				_loop898:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						condition();
						t = LT(1);
						match(LITERAL_then);
						seq_of_statements();
					}
					else {
						if ( _cnt898>=1 ) { break _loop898; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt898++;
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
				match(LITERAL_else);
				seq_of_statements();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void immediate_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_execute);
			match(LITERAL_immediate);
			{
			boolean synPredMatched1412 = false;
			if (((_tokenSet_78.member(LA(1))) && (_tokenSet_223.member(LA(2))) && (_tokenSet_224.member(LA(3))))) {
				int _m1412 = mark();
				synPredMatched1412 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1412 = false;
				}
				rewind(_m1412);
				inputState.guessing--;
			}
			if ( synPredMatched1412 ) {
				plsql_lvalue();
			}
			else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_225.member(LA(2))) && (_tokenSet_226.member(LA(3)))) {
				plsql_expression();
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
					match(LITERAL_bulk);
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
				match(LITERAL_into);
				plsql_lvalue_list();
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
				match(LITERAL_using);
				plsql_exp_list_using();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lock_table_statement() throws RecognitionException, TokenStreamException {
		
		Token  l = null;
		Token  t = null;
		Token  i = null;
		Token  m = null;
		Token  n = null;
		
		try {      // for error handling
			l = LT(1);
			match(LITERAL_lock);
			t = LT(1);
			match(LITERAL_table);
			table_reference_list();
			i = LT(1);
			match(LITERAL_in);
			lock_mode();
			m = LT(1);
			match(LITERAL_mode);
			{
			switch ( LA(1)) {
			case LITERAL_nowait:
			{
				n = LT(1);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void open_statement() throws RecognitionException, TokenStreamException {
		
		Token  o = null;
		Token  f = null;
		
		try {      // for error handling
			o = LT(1);
			match(LITERAL_open);
			cursor_name_ref();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				parentesized_exp_list();
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
				match(LITERAL_for);
				{
				if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_72.member(LA(2))) && (_tokenSet_210.member(LA(3)))) {
					select_expression();
				}
				else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_227.member(LA(2))) && (_tokenSet_228.member(LA(3)))) {
					plsql_expression();
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
				match(LITERAL_using);
				{
				if ((LA(1)==LITERAL_in)) {
					match(LITERAL_in);
				}
				else if ((_tokenSet_78.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				plsql_lvalue_list();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void close_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_close);
			cursor_name_ref();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void fetch_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_fetch);
			cursor_name_ref();
			{
			switch ( LA(1)) {
			case LITERAL_bulk:
			{
				match(LITERAL_bulk);
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
			match(LITERAL_into);
			variable_ref();
			{
			_loop1586:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					variable_ref();
				}
				else {
					break _loop1586;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_limit:
			{
				match(LITERAL_limit);
				{
				if ((_tokenSet_5.member(LA(1)))) {
					identifier2();
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
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
				__markRule(FETCH_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void set_transaction_command() throws RecognitionException, TokenStreamException {
		
		Token  r = null;
		
		try {      // for error handling
			match(LITERAL_set);
			match(LITERAL_transaction);
			r = LT(1);
			match(LITERAL_read);
			match(LITERAL_only);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void savepoint_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_savepoint);
			savepoint_name();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void name_fragment_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier3();
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
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
	}
	
	public void exec_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier3();
			if ( inputState.guessing==0 ) {
				__markRule(EXEC_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void procedure_call_no_args() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref();
			if ( inputState.guessing==0 ) {
				__markRule(PROCEDURE_CALL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sql_percentage() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_sql);
			match(PERCENTAGE);
			{
			switch ( LA(1)) {
			case LITERAL_found:
			{
				match(LITERAL_found);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_notfound:
			{
				match(LITERAL_notfound);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_rowcount:
			{
				match(LITERAL_rowcount);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_NUM);
				}
				break;
			}
			case LITERAL_isopen:
			{
				match(LITERAL_isopen);
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_BOOL);
				}
				break;
			}
			case LITERAL_bulk_rowcount:
			{
				{
				match(LITERAL_bulk_rowcount);
				match(OPEN_PAREN);
				plsql_expression();
				match(CLOSE_PAREN);
				}
				if ( inputState.guessing==0 ) {
					__markRule(LAST_STMT_RESULT_NUM);
				}
				break;
			}
			case LITERAL_bulk_exceptions:
			{
				match(LITERAL_bulk_exceptions);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					{
					match(OPEN_PAREN);
					plsql_expression();
					match(CLOSE_PAREN);
					match(DOT);
					{
					switch ( LA(1)) {
					case LITERAL_error_index:
					{
						match(LITERAL_error_index);
						break;
					}
					case LITERAL_error_code:
					{
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
					match(DOT);
					}
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void declare_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_type:
			{
				{
				type_definition();
				match(SEMI);
				}
				break;
			}
			case LITERAL_cursor:
			{
				cursor_declaration();
				break;
			}
			case LITERAL_subtype:
			{
				subtype_declaration();
				break;
			}
			case LITERAL_function:
			{
				{
				boolean synPredMatched906 = false;
				if (((LA(1)==LITERAL_function) && (_tokenSet_24.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return))) {
					int _m906 = mark();
					synPredMatched906 = true;
					inputState.guessing++;
					try {
						{
						function_declaration();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched906 = false;
					}
					rewind(_m906);
					inputState.guessing--;
				}
				if ( synPredMatched906 ) {
					function_body();
				}
				else if ((LA(1)==LITERAL_function) && (_tokenSet_24.member(LA(2))) && (LA(3)==DOT||LA(3)==OPEN_PAREN||LA(3)==LITERAL_return)) {
					function_spec();
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
				boolean synPredMatched909 = false;
				if (((LA(1)==LITERAL_procedure) && (_tokenSet_24.member(LA(2))) && (_tokenSet_192.member(LA(3))))) {
					int _m909 = mark();
					synPredMatched909 = true;
					inputState.guessing++;
					try {
						{
						procedure_declaration();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched909 = false;
					}
					rewind(_m909);
					inputState.guessing--;
				}
				if ( synPredMatched909 ) {
					procedure_body();
				}
				else if ((LA(1)==LITERAL_procedure) && (_tokenSet_24.member(LA(2))) && (_tokenSet_229.member(LA(3)))) {
					procedure_spec();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			default:
				boolean synPredMatched903 = false;
				if (((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_autonomous_transaction))) {
					int _m903 = mark();
					synPredMatched903 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_pragma);
						match(LITERAL_autonomous_transaction);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched903 = false;
					}
					rewind(_m903);
					inputState.guessing--;
				}
				if ( synPredMatched903 ) {
					pragma_autonomous_transaction();
				}
				else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_exception_init)) {
					exception_pragma();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_230);
			} else {
			  throw ex;
			}
		}
	}
	
	public void pragma_autonomous_transaction() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_autonomous_transaction);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_230);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_exception_init);
			match(OPEN_PAREN);
			complex_name();
			match(COMMA);
			plsql_expression();
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_230);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rvalue() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			boolean synPredMatched917 = false;
			if (((_tokenSet_231.member(LA(1))) && (_tokenSet_76.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
				int _m917 = mark();
				synPredMatched917 = true;
				inputState.guessing++;
				try {
					{
					{
					if ((LA(1)==LITERAL_prior)) {
						match(LITERAL_prior);
					}
					else if ((_tokenSet_33.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					_loop916:
					do {
						if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_33.member(LA(3)))) {
							name_fragment2();
							match(DOT);
						}
						else {
							break _loop916;
						}
						
					} while (true);
					}
					name_fragment2();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched917 = false;
				}
				rewind(_m917);
				inputState.guessing--;
			}
			if ( synPredMatched917 ) {
				{
				{
				if ((LA(1)==LITERAL_prior)) {
					match(LITERAL_prior);
				}
				else if ((_tokenSet_33.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop921:
				do {
					if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment2();
						match(DOT);
					}
					else {
						break _loop921;
					}
					
				} while (true);
				}
				name_fragment2();
				}
				if ( inputState.guessing==0 ) {
					__markRule(VAR_REF);
				}
			}
			else {
				boolean synPredMatched924 = false;
				if (((LA(1)==COLON))) {
					int _m924 = mark();
					synPredMatched924 = true;
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
						synPredMatched924 = false;
					}
					rewind(_m924);
					inputState.guessing--;
				}
				if ( synPredMatched924 ) {
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
					name_fragment();
					}
					if ( inputState.guessing==0 ) {
						__markRule(TRIGGER_COLUMN_REF);
					}
				}
				else if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
					{
					function_call();
					{
					_loop932:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							if ( inputState.guessing==0 ) {
								tag1=true;
							}
							{
							boolean synPredMatched931 = false;
							if (((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3))))) {
								int _m931 = mark();
								synPredMatched931 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched931 = false;
								}
								rewind(_m931);
								inputState.guessing--;
							}
							if ( synPredMatched931 ) {
								function_call();
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_76.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
								c_record_item_ref();
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop932;
						}
						
					} while (true);
					}
					}
					if ( inputState.guessing==0 ) {
						
						if(tag1){
						__markRule(COLLECTION_METHOD_CALL);
						}
						
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_66);
				} else {
				  throw ex;
				}
			}
		}
		
	public void name_fragment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
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
	}
	
	public void function_call() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref();
			call_argument_list();
			if ( inputState.guessing==0 ) {
				__markRule(FUNCTION_CALL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
	}
	
	public void c_record_item_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(C_RECORD_ITEM_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
	}
	
	public void bind_variable() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(COLON);
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(BIND_VAR);
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
	}
	
	public void collection_method() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(COLLECTION_METHOD_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void field_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(FIELD_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void datatype_param_info() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_number);
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				match(LITERAL_number);
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
			match(Token.EOF_TYPE);
			if ( inputState.guessing==0 ) {
				__markRule(DATATYPE_PARAM_INFO);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void percentage_type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			table_ref();
			{
			switch ( LA(1)) {
			case DOT:
			{
				{
				match(DOT);
				column_name_ref();
				match(PERCENTAGE);
				match(LITERAL_type);
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_TYPE_REF);
				}
				break;
			}
			case PERCENTAGE:
			{
				{
				match(PERCENTAGE);
				match(LITERAL_rowtype);
				}
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_TYPE_REF);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_50);
			} else {
			  throw ex;
			}
		}
	}
	
	public void percentage_type_w_schema() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			schema_name();
			match(DOT);
			table_ref();
			{
			switch ( LA(1)) {
			case DOT:
			{
				{
				match(DOT);
				column_name_ref();
				match(PERCENTAGE);
				match(LITERAL_type);
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_TYPE_REF);
				}
				break;
			}
			case PERCENTAGE:
			{
				{
				match(PERCENTAGE);
				match(LITERAL_rowtype);
				}
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_TYPE_REF);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_50);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sequence_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(SEQUENCE_REF);
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
	}
	
	public void parameter_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			parameter_name();
			{
			if ((LA(1)==LITERAL_in)) {
				match(LITERAL_in);
			}
			else if ((_tokenSet_233.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_out)) {
				match(LITERAL_out);
			}
			else if ((_tokenSet_234.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_nocopy)) {
				match(LITERAL_nocopy);
			}
			else if ((_tokenSet_92.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_ref) && (_tokenSet_92.member(LA(2))) && (_tokenSet_235.member(LA(3)))) {
				match(LITERAL_ref);
			}
			else if ((_tokenSet_92.member(LA(1))) && (_tokenSet_235.member(LA(2))) && (_tokenSet_236.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			type_spec();
			}
			{
			switch ( LA(1)) {
			case LITERAL_character:
			{
				character_set();
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
					break;
				}
				case ASSIGNMENT_EQ:
				{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void parameter_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(PARAMETER_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_237);
			} else {
			  throw ex;
			}
		}
	}
	
	public void character_set() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_character);
			match(LITERAL_set);
			{
			if ((LA(1)==LITERAL_any_cs)) {
				match(LITERAL_any_cs);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				{
				identifier2();
				match(PERCENTAGE);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_238);
			} else {
			  throw ex;
			}
		}
	}
	
	public void return_type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			type_spec();
			if ( inputState.guessing==0 ) {
				__markRule(RETURN_TYPE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_239);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			exception_package_name();
			{
			switch ( LA(1)) {
			case DOT:
			{
				match(DOT);
				identifier();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_240);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_package_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_241);
			} else {
			  throw ex;
			}
		}
	}
	
	public void restrict_ref_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_restrict_references);
			match(OPEN_PAREN);
			identifier3();
			{
			int _cnt1069=0;
			_loop1069:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier3();
				}
				else {
					if ( _cnt1069>=1 ) { break _loop1069; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1069++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(RESTRICT_REF_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void interface_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_interface);
			match(OPEN_PAREN);
			identifier3();
			{
			int _cnt1072=0;
			_loop1072:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier3();
				}
				else {
					if ( _cnt1072>=1 ) { break _loop1072; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1072++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(INTERFACE_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void builtin_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_builtin);
			match(OPEN_PAREN);
			string_literal();
			{
			int _cnt1075=0;
			_loop1075:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					if ( _cnt1075>=1 ) { break _loop1075; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1075++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(BUILTIN_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void fipsflag_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_fipsflag);
			match(OPEN_PAREN);
			string_literal();
			{
			int _cnt1078=0;
			_loop1078:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					if ( _cnt1078>=1 ) { break _loop1078; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1078++;
			} while (true);
			}
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(FIPSFLAG_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void timestamp_pragma() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_pragma);
			match(LITERAL_timestamp);
			match(OPEN_PAREN);
			string_literal();
			match(CLOSE_PAREN);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(TIMESTAMPG_PRAGMA);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void oracle_err_number() throws RecognitionException, TokenStreamException {
		
		Token  p = null;
		Token  m = null;
		Token  n = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case PLUS:
			{
				p = LT(1);
				match(PLUS);
				break;
			}
			case MINUS:
			{
				m = LT(1);
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
			match(NUMBER);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void record_item_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(RECORD_ITEM_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_92);
			} else {
			  throw ex;
			}
		}
	}
	
	public void func_proc_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_86.member(LA(1)))) {
				declare_list();
			}
			else if ((LA(1)==LITERAL_begin)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_block();
			match(SEMI);
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_BLOCK);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
	}
	
	public void declare_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1109=0;
			_loop1109:
			do {
				if ((_tokenSet_182.member(LA(1))) && (_tokenSet_242.member(LA(2))) && (_tokenSet_184.member(LA(3)))) {
					declare_spec();
				}
				else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_188.member(LA(2))) && (_tokenSet_243.member(LA(3)))) {
					variable_declaration();
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
					exception_declaration();
				}
				else {
					if ( _cnt1109>=1 ) { break _loop1109; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1109++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(DECLARE_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_244);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_block() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_begin);
			{
			if ((_tokenSet_195.member(LA(1)))) {
				seq_of_statements();
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
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_20.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				identifier2();
			}
			else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_section() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_exception);
			{
			int _cnt1106=0;
			_loop1106:
			do {
				if ((LA(1)==LITERAL_when)) {
					exception_handler();
				}
				else {
					if ( _cnt1106>=1 ) { break _loop1106; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1106++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_SECTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_194);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_handler() throws RecognitionException, TokenStreamException {
		
		Token  w = null;
		Token  o = null;
		Token  t = null;
		
		try {      // for error handling
			w = LT(1);
			match(LITERAL_when);
			exception_name();
			{
			_loop1112:
			do {
				if ((LA(1)==LITERAL_or)) {
					o = LT(1);
					match(LITERAL_or);
					exception_name();
				}
				else {
					break _loop1112;
				}
				
			} while (true);
			}
			t = LT(1);
			match(LITERAL_then);
			seq_of_statements();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_245);
			} else {
			  throw ex;
			}
		}
	}
	
	public void call_argument_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			{
			if ((_tokenSet_73.member(LA(1)))) {
				call_argument();
				{
				_loop1447:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						call_argument();
					}
					else {
						break _loop1447;
					}
					
				} while (true);
				}
			}
			else if ((LA(1)==CLOSE_PAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(CALL_ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
	}
	
	public void forall_header() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_forall);
			num_loop_index();
			match(LITERAL_in);
			{
			if ((_tokenSet_246.member(LA(1))) && (_tokenSet_247.member(LA(2)))) {
				{
				forall_boundary();
				match(DOUBLEDOT);
				forall_boundary();
				}
			}
			else if ((LA(1)==LITERAL_values)) {
				{
				match(LITERAL_values);
				match(LITERAL_of);
				plsql_lvalue();
				}
			}
			else if ((LA(1)==LITERAL_indices) && (LA(2)==LITERAL_of)) {
				{
				match(LITERAL_indices);
				match(LITERAL_of);
				plsql_lvalue();
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FORALL_LOOP_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_248);
			} else {
			  throw ex;
			}
		}
	}
	
	public void num_loop_index() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(NUM_LOOP_INDEX);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_249);
			} else {
			  throw ex;
			}
		}
	}
	
	public void forall_boundary() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==NUMBER)) {
				numeric_literal();
			}
			else if ((_tokenSet_78.member(LA(1)))) {
				plsql_lvalue();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
	}
	
	public void numeric_loop_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			num_loop_index();
			match(LITERAL_in);
			{
			if ((LA(1)==LITERAL_reverse) && (_tokenSet_61.member(LA(2))) && (_tokenSet_251.member(LA(3)))) {
				match(LITERAL_reverse);
			}
			else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_251.member(LA(2))) && (_tokenSet_252.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			numeric_loop_spec2();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_253);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_loop_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			cursor_loop_index();
			match(LITERAL_in);
			cursor_loop_spec2();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_253);
			} else {
			  throw ex;
			}
		}
	}
	
	public void numeric_loop_spec2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			match(DOUBLEDOT);
			plsql_expression();
			if ( inputState.guessing==0 ) {
				__markRule(NUMERIC_LOOP_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_253);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_loop_index() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_LOOP_INDEX);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_249);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_loop_spec2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				{
				cursor_name_ref();
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					call_argument_list();
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
			case LITERAL_select:
			{
				select_command();
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_LOOP_SPEC);
				}
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
				recover(ex,_tokenSet_253);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			if ( inputState.guessing==0 ) {
				__markRule(CURSOR_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_254);
			} else {
			  throw ex;
			}
		}
	}
	
	public void boolean_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_true:
			{
				match(LITERAL_true);
				break;
			}
			case LITERAL_false:
			{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void integer_expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_61.member(LA(1))) && (_tokenSet_84.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
			}
			else if ((_tokenSet_92.member(LA(1))) && (_tokenSet_255.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				type_spec();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
	}
	
	public void num_expression() throws RecognitionException, TokenStreamException {
		
		
		boolean tag1=false;
		boolean tag2=false;
		
		
		try {      // for error handling
			num_term();
			{
			_loop1184:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS) && (_tokenSet_61.member(LA(2))) && (_tokenSet_84.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						if ( inputState.guessing==0 ) {
							tag1=true;
						}
						break;
					}
					case MINUS:
					{
						match(MINUS);
						if ( inputState.guessing==0 ) {
							tag2=true;
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
				}
				else {
					break _loop1184;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1 || tag2){
				__markRule(ARITHMETIC_EXPR);
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
	}
	
	public void num_term() throws RecognitionException, TokenStreamException {
		
		Token  as = null;
		Token  dv = null;
		
		boolean tag1=false;
		boolean tag2=false;
		
		
		try {      // for error handling
			num_factor();
			{
			_loop1188:
			do {
				if ((LA(1)==ASTERISK||LA(1)==DIVIDE) && (_tokenSet_61.member(LA(2))) && (_tokenSet_84.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case ASTERISK:
					{
						as = LT(1);
						match(ASTERISK);
						if ( inputState.guessing==0 ) {
							tag1=true;
						}
						break;
					}
					case DIVIDE:
					{
						dv = LT(1);
						match(DIVIDE);
						if ( inputState.guessing==0 ) {
							tag2=true;
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
				}
				else {
					break _loop1188;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1 || tag2){
				__markRule(ARITHMETIC_EXPR);
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
	}
	
	public void num_factor() throws RecognitionException, TokenStreamException {
		
		boolean tag1 = false; boolean tag2 = false;
		
		try {      // for error handling
			may_be_negate_base_expr();
			{
			if ((LA(1)==718)) {
				match(718);
				integer_expr();
			}
			else if ((_tokenSet_85.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_day) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_to) && (LA(3)==NUMBER||LA(3)==LITERAL_second)) {
				{
				match(LITERAL_day);
				if ( inputState.guessing==0 ) {
					tag1 = true;
				}
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
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
				match(LITERAL_to);
				match(LITERAL_second);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_85.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
			}
			else if ((LA(1)==LITERAL_year) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_to) && (LA(3)==NUMBER||LA(3)==LITERAL_month)) {
				{
				match(LITERAL_year);
				if ( inputState.guessing==0 ) {
					tag2 = true;
				}
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
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
				match(LITERAL_to);
				match(LITERAL_month);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					match(NUMBER);
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_85.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
			}
			else if ((_tokenSet_85.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(INTERVAL_DAY_TO_SEC_EXPR);
				} else if(tag2){
				__markRule(INTERVAL_YEAR_TO_MONTH_EXPR);
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
	}
	
	public void may_be_negate_base_expr() throws RecognitionException, TokenStreamException {
		
		boolean tag1 = false;
		
		try {      // for error handling
			{
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				sign();
				if ( inputState.guessing==0 ) {
					tag1 = true;
				}
			}
			else if ((_tokenSet_58.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			may_be_at_time_zone();
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(ARITHMETIC_EXPR);
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_206);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sign() throws RecognitionException, TokenStreamException {
		
		Token  p = null;
		Token  m = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case PLUS:
			{
				p = LT(1);
				match(PLUS);
				break;
			}
			case MINUS:
			{
				m = LT(1);
				match(MINUS);
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
				recover(ex,_tokenSet_58);
			} else {
			  throw ex;
			}
		}
	}
	
	public void may_be_at_time_zone() throws RecognitionException, TokenStreamException {
		
		boolean tag1 = false;
		
		try {      // for error handling
			base_expression();
			{
			if ((LA(1)==LITERAL_at) && (LA(2)==LITERAL_local||LA(2)==LITERAL_time) && (_tokenSet_256.member(LA(3)))) {
				match(LITERAL_at);
				if ( inputState.guessing==0 ) {
					tag1 = true;
				}
				{
				switch ( LA(1)) {
				case LITERAL_time:
				{
					{
					match(LITERAL_time);
					match(LITERAL_zone);
					timezone_spec();
					}
					break;
				}
				case LITERAL_local:
				{
					{
					match(LITERAL_local);
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
			else if ((_tokenSet_206.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(AT_TIME_ZONE_EXPR);
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_206);
			} else {
			  throw ex;
			}
		}
	}
	
	public void timezone_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case QUOTED_STR:
			{
				string_literal();
				break;
			}
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				complex_name();
				break;
			}
			case LITERAL_sessiontimezone:
			{
				match(LITERAL_sessiontimezone);
				break;
			}
			case LITERAL_dbtimezone:
			{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_206);
			} else {
			  throw ex;
			}
		}
	}
	
	public void expr_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			{
			_loop1208:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					break _loop1208;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXPR_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_257);
			} else {
			  throw ex;
			}
		}
	}
	
	public void parentesized_exp_list() throws RecognitionException, TokenStreamException {
		
		Token  cp = null;
		
		try {      // for error handling
			match(OPEN_PAREN);
			expr_list();
			cp = LT(1);
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_258);
			} else {
			  throw ex;
			}
		}
	}
	
	public void logical_term() throws RecognitionException, TokenStreamException {
		
		boolean tag1= false;
		
		try {      // for error handling
			maybe_neg_factor();
			{
			_loop1215:
			do {
				if ((LA(1)==LITERAL_and)) {
					match(LITERAL_and);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					maybe_neg_factor();
				}
				else {
					break _loop1215;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(LOGICAL_EXPR);
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_259);
			} else {
			  throw ex;
			}
		}
	}
	
	public void maybe_neg_factor() throws RecognitionException, TokenStreamException {
		
		boolean tag1= false;
		
		try {      // for error handling
			{
			if ((LA(1)==LITERAL_not)) {
				match(LITERAL_not);
				if ( inputState.guessing==0 ) {
					tag1=true;
				}
			}
			else if ((_tokenSet_61.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			plsql_expression33();
			if ( inputState.guessing==0 ) {
				
				if(tag1){
				__markRule(LOGICAL_EXPR);
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
	}
	
	public void plsql_expression33() throws RecognitionException, TokenStreamException {
		
		boolean tag1 = false;
		
		try {      // for error handling
			boolean synPredMatched1220 = false;
			if (((LA(1)==LITERAL_current) && (LA(2)==LITERAL_of))) {
				int _m1220 = mark();
				synPredMatched1220 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_current);
					match(LITERAL_of);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1220 = false;
				}
				rewind(_m1220);
				inputState.guessing--;
			}
			if ( synPredMatched1220 ) {
				{
				match(LITERAL_current);
				match(LITERAL_of);
				}
				identifier();
				if ( inputState.guessing==0 ) {
					__markRule(CURRENT_OF_CLAUSE);
				}
			}
			else {
				boolean synPredMatched1223 = false;
				if (((LA(1)==LITERAL_exists) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m1223 = mark();
					synPredMatched1223 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_exists);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1223 = false;
					}
					rewind(_m1223);
					inputState.guessing--;
				}
				if ( synPredMatched1223 ) {
					match(LITERAL_exists);
					subquery();
					if ( inputState.guessing==0 ) {
						__markRule(EXISTS_EXPR);
					}
				}
				else {
					boolean synPredMatched1228 = false;
					if (((LA(1)==OPEN_PAREN) && (_tokenSet_61.member(LA(2))) && (_tokenSet_260.member(LA(3))))) {
						int _m1228 = mark();
						synPredMatched1228 = true;
						inputState.guessing++;
						try {
							{
							match(OPEN_PAREN);
							expr_list();
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
							case LITERAL_not:
							case LITERAL_in:
							{
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
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							match(OPEN_PAREN);
							match(LITERAL_select);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1228 = false;
						}
						rewind(_m1228);
						inputState.guessing--;
					}
					if ( synPredMatched1228 ) {
						match(OPEN_PAREN);
						expr_list();
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
						case LITERAL_not:
						case LITERAL_in:
						{
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
							if ( inputState.guessing==0 ) {
								tag1=true;
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
						subquery();
						if ( inputState.guessing==0 ) {
							
							if(tag1){
							__markRule(IN_CONDITION);
							} else {
							__markRule(SUBQUERY_CONDITION);
							}
							
						}
					}
					else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_261.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						plsql_expression();
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
							plsql_expression();
							if ( inputState.guessing==0 ) {
								__markRule(RELATION_CONDITION);
							}
							break;
						}
						case LITERAL_is:
						{
							{
							match(LITERAL_is);
							{
							switch ( LA(1)) {
							case LITERAL_not:
							{
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
						case LITERAL_package:
						case LITERAL_alter:
						case LITERAL_drop:
						case LITERAL_function:
						case LITERAL_procedure:
						case LITERAL_type:
						case LITERAL_trigger:
						case LITERAL_associate:
						case LITERAL_with:
						case LITERAL_column:
						case LITERAL_using:
						case LITERAL_truncate:
						case LITERAL_comment:
						case LITERAL_disable:
						case LITERAL_enable:
						case LITERAL_rely:
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
						case LITERAL_host:
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
						case LITERAL_group:
						case LITERAL_and:
						case LITERAL_order:
						case LITERAL_for:
						case LITERAL_connect:
						case LITERAL_grant:
						case LITERAL_revoke:
						case LITERAL_delete:
						case LITERAL_insert:
						case LITERAL_update:
						case LITERAL_as:
						case LITERAL_when:
						case LITERAL_return:
						case LITERAL_loop:
						case LITERAL_select:
						case LITERAL_merge:
						case LITERAL_then:
						case LITERAL_byte:
						case LITERAL_union:
						case LITERAL_intersect:
						case LITERAL_minus:
						case LITERAL_left:
						case LITERAL_right:
						case LITERAL_inner:
						case LITERAL_join:
						case LITERAL_full:
						case LITERAL_where:
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
							boolean synPredMatched1237 = false;
							if (((LA(1)==LITERAL_not||LA(1)==LITERAL_in) && (_tokenSet_262.member(LA(2))) && (_tokenSet_263.member(LA(3))))) {
								int _m1237 = mark();
								synPredMatched1237 = true;
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
									synPredMatched1237 = false;
								}
								rewind(_m1237);
								inputState.guessing--;
							}
							if ( synPredMatched1237 ) {
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
								exp_set();
								if ( inputState.guessing==0 ) {
									__markRule(IN_CONDITION);
								}
							}
							else {
								boolean synPredMatched1241 = false;
								if (((LA(1)==LITERAL_not||LA(1)==LITERAL_like) && (_tokenSet_264.member(LA(2))) && (_tokenSet_263.member(LA(3))))) {
									int _m1241 = mark();
									synPredMatched1241 = true;
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
										synPredMatched1241 = false;
									}
									rewind(_m1241);
									inputState.guessing--;
								}
								if ( synPredMatched1241 ) {
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
									plsql_expression();
									{
									switch ( LA(1)) {
									case LITERAL_escape:
									{
										match(LITERAL_escape);
										{
										if ((LA(1)==QUOTED_STR)) {
											string_literal();
										}
										else if ((_tokenSet_5.member(LA(1)))) {
											identifier2();
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
									case LITERAL_package:
									case LITERAL_alter:
									case LITERAL_drop:
									case LITERAL_function:
									case LITERAL_procedure:
									case LITERAL_type:
									case LITERAL_trigger:
									case LITERAL_associate:
									case LITERAL_with:
									case LITERAL_column:
									case LITERAL_using:
									case LITERAL_truncate:
									case LITERAL_comment:
									case LITERAL_disable:
									case LITERAL_enable:
									case LITERAL_rely:
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
									case LITERAL_host:
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
									case LITERAL_group:
									case LITERAL_and:
									case LITERAL_order:
									case LITERAL_for:
									case LITERAL_connect:
									case LITERAL_grant:
									case LITERAL_revoke:
									case LITERAL_delete:
									case LITERAL_insert:
									case LITERAL_update:
									case LITERAL_as:
									case LITERAL_when:
									case LITERAL_return:
									case LITERAL_loop:
									case LITERAL_select:
									case LITERAL_merge:
									case LITERAL_then:
									case LITERAL_byte:
									case LITERAL_union:
									case LITERAL_intersect:
									case LITERAL_minus:
									case LITERAL_left:
									case LITERAL_right:
									case LITERAL_inner:
									case LITERAL_join:
									case LITERAL_full:
									case LITERAL_where:
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
									boolean synPredMatched1247 = false;
									if (((LA(1)==LITERAL_not||LA(1)==LITERAL_between) && (_tokenSet_265.member(LA(2))) && (_tokenSet_266.member(LA(3))))) {
										int _m1247 = mark();
										synPredMatched1247 = true;
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
											synPredMatched1247 = false;
										}
										rewind(_m1247);
										inputState.guessing--;
									}
									if ( synPredMatched1247 ) {
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
										plsql_expression();
										match(LITERAL_and);
										plsql_expression();
										if ( inputState.guessing==0 ) {
											__markRule(BETWEEN_CONDITION);
										}
									}
									else {
										boolean synPredMatched1253 = false;
										if (((LA(1)==LITERAL_not||LA(1)==LITERAL_member) && (LA(2)==LITERAL_of||LA(2)==LITERAL_member))) {
											int _m1253 = mark();
											synPredMatched1253 = true;
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
												synPredMatched1253 = false;
											}
											rewind(_m1253);
											inputState.guessing--;
										}
										if ( synPredMatched1253 ) {
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
												match(DOT);
											}
											else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_258.member(LA(2)))) {
											}
											else {
												throw new NoViableAltException(LT(1), getFilename());
											}
											
											}
											name_fragment();
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
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_258);
								} else {
								  throw ex;
								}
							}
						}
						
	public void subquery() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			select_command();
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SUBQUERY);
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
	}
	
	public void relational_op() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case EQ:
			{
				match(EQ);
				break;
			}
			case LT:
			{
				match(LT);
				break;
			}
			case GT:
			{
				match(GT);
				break;
			}
			case NOT_EQ:
			{
				match(NOT_EQ);
				break;
			}
			case LE:
			{
				match(LE);
				break;
			}
			case GE:
			{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_61);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exp_set() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched1513 = false;
			if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_72.member(LA(3))))) {
				int _m1513 = mark();
				synPredMatched1513 = true;
				inputState.guessing++;
				try {
					{
					match(OPEN_PAREN);
					match(LITERAL_select);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1513 = false;
				}
				rewind(_m1513);
				inputState.guessing--;
			}
			if ( synPredMatched1513 ) {
				subquery();
			}
			else if ((LA(1)==OPEN_PAREN) && (_tokenSet_61.member(LA(2))) && (_tokenSet_260.member(LA(3)))) {
				parentesized_exp_list();
			}
			else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_263.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
	}
	
	public void cast_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cast);
			cast_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(CAST_FUNC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trim_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_trim);
			trim_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(TRIM_FUNC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void count_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_count);
			match(OPEN_PAREN);
			{
			if ((LA(1)==ASTERISK)) {
				asterisk_column();
			}
			else {
				boolean synPredMatched1339 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1339 = mark();
					synPredMatched1339 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1339 = false;
					}
					rewind(_m1339);
					inputState.guessing--;
				}
				if ( synPredMatched1339 ) {
					ident_asterisk_column();
				}
				else if ((_tokenSet_268.member(LA(1))) && (_tokenSet_269.member(LA(2))) && (_tokenSet_270.member(LA(3)))) {
					{
					{
					if ((LA(1)==LITERAL_distinct)) {
						match(LITERAL_distinct);
					}
					else if ((_tokenSet_61.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					plsql_expression();
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
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_66);
				} else {
				  throw ex;
				}
			}
		}
		
	public void case_expression() throws RecognitionException, TokenStreamException {
		
		Token  t = null;
		boolean tag1 = false;
		
		try {      // for error handling
			{
			match(LITERAL_case);
			{
			if ((LA(1)==LITERAL_when)) {
				{
				int _cnt1358=0;
				_loop1358:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						condition();
						t = LT(1);
						match(LITERAL_then);
						plsql_expression();
					}
					else {
						if ( _cnt1358>=1 ) { break _loop1358; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1358++;
				} while (true);
				}
			}
			else if ((_tokenSet_61.member(LA(1)))) {
				plsql_expression();
				if ( inputState.guessing==0 ) {
					tag1 = true;
				}
				{
				int _cnt1360=0;
				_loop1360:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						plsql_expression();
						match(LITERAL_then);
						plsql_expression();
					}
					else {
						if ( _cnt1360>=1 ) { break _loop1360; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1360++;
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
				match(LITERAL_else);
				plsql_expression();
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
				
				if(tag1){
				{ __markRule(CASE_EXPRESSION_SMPL);}
				} else {
				{ __markRule(CASE_EXPRESSION_SRCH);}
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void multiset_operator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_multiset);
			subquery();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lag_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_lag);
			lag_lead_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(LAG_FUNCTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lead_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_lead);
			lag_lead_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(LEAD_FUNCTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void dence_rank_analytics_func() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_rank:
			{
				match(LITERAL_rank);
				break;
			}
			case LITERAL_dense_rank:
			{
				match(LITERAL_dense_rank);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(OPEN_PAREN);
			match(CLOSE_PAREN);
			match(LITERAL_over);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_partition:
			{
				query_partition_clause();
				break;
			}
			case LITERAL_order:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			order_clause();
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(RANK_FUNCTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void extract_date_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_extract);
			extract_date_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(EXTRACT_DATE_FUNC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ident_percentage() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
				if ( inputState.guessing==0 ) {
					__markRule(ROWCOUNT_EXRESSION);
				}
				break;
			}
			case LITERAL_found:
			{
				match(LITERAL_found);
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_STATE);
				}
				break;
			}
			case LITERAL_notfound:
			{
				match(LITERAL_notfound);
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_STATE);
				}
				break;
			}
			case LITERAL_isopen:
			{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void pseudo_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_sysdate:
			{
				match(LITERAL_sysdate);
				if ( inputState.guessing==0 ) {
					__markRule(SYSDATE_CONST);
				}
				break;
			}
			case LITERAL_systimestamp:
			{
				match(LITERAL_systimestamp);
				if ( inputState.guessing==0 ) {
					__markRule(SYSTIMESTAMP_CONST);
				}
				break;
			}
			case LITERAL_current_timestamp:
			{
				match(LITERAL_current_timestamp);
				if ( inputState.guessing==0 ) {
					__markRule(CURRENT_TIMESTAMP_CONST);
				}
				break;
			}
			case LITERAL_rownum:
			{
				match(LITERAL_rownum);
				if ( inputState.guessing==0 ) {
					__markRule(ROWNUM);
				}
				break;
			}
			default:
				if ((LA(1)==LITERAL_user) && (_tokenSet_66.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_user);
					if ( inputState.guessing==0 ) {
						__markRule(USER_CONST);
					}
				}
				else if ((LA(1)==LITERAL_dbtimezone) && (_tokenSet_66.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_dbtimezone);
					if ( inputState.guessing==0 ) {
						__markRule(DBTIMEZONE);
					}
				}
				else if ((_tokenSet_271.member(LA(1))) && (_tokenSet_76.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					if ((_tokenSet_33.member(LA(1)))) {
						name_fragment2();
						match(DOT);
					}
					else if ((LA(1)==LITERAL_rowid)) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					match(LITERAL_rowid);
					if ( inputState.guessing==0 ) {
						__markRule(ROWID);
					}
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT)) {
				name_fragment2();
				match(DOT);
			}
			else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_272.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			name_fragment2();
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_SPEC);
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
	}
	
	public void sequence_expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			sequence_ref();
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
			if ( inputState.guessing==0 ) {
				__markRule(SEQUENCE_EXPR);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void query_partition_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			match(LITERAL_by);
			plsql_expression();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_273);
			} else {
			  throw ex;
			}
		}
	}
	
	public void order_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_order);
			match(LITERAL_by);
			sorted_def();
			{
			_loop1525:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					sorted_def();
				}
				else {
					break _loop1525;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(ORDER_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_274);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lag_lead_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			plsql_expression();
			{
			_loop1329:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					break _loop1329;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			match(LITERAL_over);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_partition:
			{
				query_partition_clause();
				break;
			}
			case LITERAL_order:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			order_clause();
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SPEC_CALL_ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void asterisk_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ASTERISK);
			if ( inputState.guessing==0 ) {
				__markRule(ASTERISK_COLUMN);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_275);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ident_asterisk_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			name_fragment();
			match(DOT);
			match(ASTERISK);
			if ( inputState.guessing==0 ) {
				__markRule(IDENT_ASTERISK_COLUMN);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_275);
			} else {
			  throw ex;
			}
		}
	}
	
	public void extract_date_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			extract_consts();
			match(LITERAL_from);
			plsql_expression();
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SPEC_CALL_ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void extract_consts() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_second:
			{
				match(LITERAL_second);
				break;
			}
			case LITERAL_minute:
			{
				match(LITERAL_minute);
				break;
			}
			case LITERAL_hour:
			{
				match(LITERAL_hour);
				break;
			}
			case LITERAL_day:
			{
				match(LITERAL_day);
				break;
			}
			case LITERAL_month:
			{
				match(LITERAL_month);
				break;
			}
			case LITERAL_year:
			{
				match(LITERAL_year);
				break;
			}
			case LITERAL_timezone_hour:
			{
				match(LITERAL_timezone_hour);
				break;
			}
			case LITERAL_timezone_minute:
			{
				match(LITERAL_timezone_minute);
				break;
			}
			case LITERAL_timezone_region:
			{
				match(LITERAL_timezone_region);
				break;
			}
			case LITERAL_timezone_abbr:
			{
				match(LITERAL_timezone_abbr);
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
				recover(ex,_tokenSet_276);
			} else {
			  throw ex;
			}
		}
	}
	
	public void date_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1351=0;
			_loop1351:
			do {
				if ((LA(1)==QUOTED_STR)) {
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1351>=1 ) { break _loop1351; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1351++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void commit_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_commit);
			{
			switch ( LA(1)) {
			case LITERAL_work:
			{
				match(LITERAL_work);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void elsif_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_elsif);
			condition();
			match(LITERAL_then);
			seq_of_statements();
			if ( inputState.guessing==0 ) {
				__markRule(ELSIF_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_277);
			} else {
			  throw ex;
			}
		}
	}
	
	public void else_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_else);
			seq_of_statements();
			if ( inputState.guessing==0 ) {
				__markRule(ELSE_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_194);
			} else {
			  throw ex;
			}
		}
	}
	
	public void insert_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_insert);
			match(LITERAL_into);
			{
			boolean synPredMatched1540 = false;
			if (((_tokenSet_96.member(LA(1))))) {
				int _m1540 = mark();
				synPredMatched1540 = true;
				inputState.guessing++;
				try {
					{
					table_alias();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1540 = false;
				}
				rewind(_m1540);
				inputState.guessing--;
			}
			if ( synPredMatched1540 ) {
				table_alias();
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_33.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					match(OPEN_PAREN);
					column_spec_list();
					match(CLOSE_PAREN);
				}
				else if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_values||LA(1)==LITERAL_select) && (_tokenSet_72.member(LA(2))) && (_tokenSet_278.member(LA(3)))) {
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
						parentesized_exp_list();
					}
					else if ((_tokenSet_5.member(LA(1)))) {
						variable_ref();
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
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
				{
				match(LITERAL_values);
				{
				if ((LA(1)==OPEN_PAREN)) {
					parentesized_exp_list();
				}
				else if ((_tokenSet_24.member(LA(1)))) {
					function_call();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void update_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			boolean synPredMatched1554 = false;
			if (((LA(1)==LITERAL_update) && (_tokenSet_96.member(LA(2))) && (_tokenSet_279.member(LA(3))))) {
				int _m1554 = mark();
				synPredMatched1554 = true;
				inputState.guessing++;
				try {
					{
					subquery_update();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1554 = false;
				}
				rewind(_m1554);
				inputState.guessing--;
			}
			if ( synPredMatched1554 ) {
				subquery_update();
			}
			else if ((LA(1)==LITERAL_update) && (_tokenSet_96.member(LA(2))) && (_tokenSet_279.member(LA(3)))) {
				simple_update();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(UPDATE_COMMAND);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void delete_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_delete);
			{
			if ((LA(1)==LITERAL_from)) {
				match(LITERAL_from);
			}
			else if ((_tokenSet_96.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_alias();
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void merge_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_merge);
			match(LITERAL_into);
			table_alias();
			match(LITERAL_using);
			{
			if ((_tokenSet_96.member(LA(1)))) {
				table_alias();
			}
			else if ((LA(1)==OPEN_PAREN)) {
				from_subquery();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(LITERAL_on);
			condition();
			when_action();
			{
			switch ( LA(1)) {
			case LITERAL_when:
			{
				when_action();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
			if ((LA(1)==LITERAL_delete) && (LA(2)==LITERAL_where) && (_tokenSet_73.member(LA(3)))) {
				match(LITERAL_delete);
				match(LITERAL_where);
				condition();
			}
			else if ((_tokenSet_21.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(MERGE_COMMAND);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void grant_revoke_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_grant:
			{
				match(LITERAL_grant);
				break;
			}
			case LITERAL_revoke:
			{
				match(LITERAL_revoke);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			privilege();
			{
			_loop1372:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					privilege();
				}
				else {
					break _loop1372;
				}
				
			} while (true);
			}
			match(LITERAL_on);
			identifier2();
			{
			switch ( LA(1)) {
			case LITERAL_to:
			{
				match(LITERAL_to);
				break;
			}
			case LITERAL_from:
			{
				match(LITERAL_from);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_public)) {
				match(LITERAL_public);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rollback_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_rollback);
			{
			switch ( LA(1)) {
			case LITERAL_work:
			{
				match(LITERAL_work);
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
			if ((LA(1)==LITERAL_comment) && (LA(2)==QUOTED_STR) && (_tokenSet_280.member(LA(3)))) {
				match(LITERAL_comment);
				string_literal();
			}
			else if ((_tokenSet_21.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(ROLLBACK_STATEMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void privilege() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_select:
			{
				match(LITERAL_select);
				break;
			}
			case LITERAL_insert:
			{
				match(LITERAL_insert);
				break;
			}
			case LITERAL_update:
			{
				match(LITERAL_update);
				break;
			}
			case LITERAL_delete:
			{
				match(LITERAL_delete);
				break;
			}
			case LITERAL_references:
			{
				match(LITERAL_references);
				break;
			}
			case LITERAL_alter:
			{
				match(LITERAL_alter);
				break;
			}
			case LITERAL_index:
			{
				match(LITERAL_index);
				break;
			}
			case LITERAL_execute:
			{
				match(LITERAL_execute);
				break;
			}
			case LITERAL_all:
			{
				match(LITERAL_all);
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
	}
	
	public void select_first() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_select:
			{
				{
				select_up_to_list();
				{
				switch ( LA(1)) {
				case LITERAL_bulk:
				case LITERAL_into:
				{
					into_clause();
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
				{
				switch ( LA(1)) {
				case LITERAL_where:
				{
					where_condition();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_with:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_group:
				case LITERAL_order:
				case LITERAL_for:
				case LITERAL_connect:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_loop:
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
				if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_with||LA(2)==LITERAL_by) && (_tokenSet_73.member(LA(3)))) {
					connect_clause();
				}
				else if ((_tokenSet_282.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case LITERAL_group:
				{
					group_clause();
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_with:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_order:
				case LITERAL_for:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_loop:
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
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_with:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_for:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_loop:
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
					break;
				}
				case EOF:
				case AT_PREFIXED:
				case SEMI:
				case OPEN_PAREN:
				case CLOSE_PAREN:
				case DIVIDE:
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_with:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_loop:
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
				break;
			}
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				select_expression();
				match(CLOSE_PAREN);
				if ( inputState.guessing==0 ) {
					__markRule(SUBQUERY);
				}
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
	}
	
	public void select_up_to_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_select);
			{
			switch ( LA(1)) {
			case LITERAL_distinct:
			{
				match(LITERAL_distinct);
				break;
			}
			case LITERAL_unique:
			{
				match(LITERAL_unique);
				break;
			}
			default:
				if ((LA(1)==LITERAL_all) && (_tokenSet_284.member(LA(2))) && (_tokenSet_285.member(LA(3)))) {
					match(LITERAL_all);
				}
				else if ((_tokenSet_284.member(LA(1))) && (_tokenSet_285.member(LA(2))) && (_tokenSet_286.member(LA(3)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			displayed_column();
			{
			_loop1401:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					displayed_column();
				}
				else {
					break _loop1401;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_287);
			} else {
			  throw ex;
			}
		}
	}
	
	public void into_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_bulk:
			{
				match(LITERAL_bulk);
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
			match(LITERAL_into);
			plsql_lvalue_list();
			if ( inputState.guessing==0 ) {
				__markRule(INTO_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_276);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_reference_list_from() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_from);
			selected_table();
			{
			_loop1439:
			do {
				switch ( LA(1)) {
				case LITERAL_left:
				case LITERAL_right:
				case LITERAL_inner:
				case LITERAL_join:
				case LITERAL_full:
				{
					ansi_spec();
					break;
				}
				case COMMA:
				{
					{
					match(COMMA);
					selected_table();
					{
					switch ( LA(1)) {
					case LITERAL_partition:
					{
						match(LITERAL_partition);
						match(OPEN_PAREN);
						identifier2();
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
					case LITERAL_package:
					case LITERAL_alter:
					case LITERAL_drop:
					case LITERAL_function:
					case LITERAL_procedure:
					case LITERAL_type:
					case LITERAL_trigger:
					case LITERAL_associate:
					case LITERAL_with:
					case LITERAL_column:
					case LITERAL_using:
					case LITERAL_truncate:
					case LITERAL_comment:
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
					case LITERAL_host:
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
					case LITERAL_group:
					case LITERAL_order:
					case LITERAL_for:
					case LITERAL_connect:
					case LITERAL_grant:
					case LITERAL_revoke:
					case LITERAL_delete:
					case LITERAL_insert:
					case LITERAL_update:
					case LITERAL_return:
					case LITERAL_loop:
					case LITERAL_select:
					case LITERAL_merge:
					case LITERAL_union:
					case LITERAL_intersect:
					case LITERAL_minus:
					case LITERAL_left:
					case LITERAL_right:
					case LITERAL_inner:
					case LITERAL_join:
					case LITERAL_full:
					case LITERAL_where:
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
					break _loop1439;
				}
				}
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				__markRule(TABLE_REFERENCE_LIST_FROM);
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_288);
			} else {
			  throw ex;
			}
		}
	}
	
	public void where_condition() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_where);
			condition();
			if ( inputState.guessing==0 ) {
				__markRule(WHERE_CONDITION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_289);
			} else {
			  throw ex;
			}
		}
	}
	
	public void connect_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			connect_clause_internal();
			{
			if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_with||LA(2)==LITERAL_by) && (_tokenSet_73.member(LA(3)))) {
				connect_clause_internal();
			}
			else if ((_tokenSet_282.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(CONNECT_CLAUSE);
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
	}
	
	public void group_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_group);
			match(LITERAL_by);
			plsql_expression();
			{
			_loop1521:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					break _loop1521;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_having:
			{
				match(LITERAL_having);
				condition();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_with:
			case LITERAL_column:
			case LITERAL_using:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_order:
			case LITERAL_for:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_loop:
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
				__markRule(GROUP_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_290);
			} else {
			  throw ex;
			}
		}
	}
	
	public void update_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_for);
			match(LITERAL_update);
			{
			switch ( LA(1)) {
			case LITERAL_of:
			{
				match(LITERAL_of);
				column_name_ref();
				{
				_loop1534:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_name_ref();
					}
					else {
						break _loop1534;
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_with:
			case LITERAL_column:
			case LITERAL_using:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_loop:
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
				match(LITERAL_nowait);
				break;
			}
			case LITERAL_wait:
			{
				{
				match(LITERAL_wait);
				numeric_literal();
				}
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_with:
			case LITERAL_column:
			case LITERAL_using:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_loop:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_283);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_lvalue_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_lvalue();
			{
			_loop1397:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_lvalue();
				}
				else {
					break _loop1397;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_291);
			} else {
			  throw ex;
			}
		}
	}
	
	public void displayed_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==ASTERISK)) {
				asterisk_column();
			}
			else {
				boolean synPredMatched1404 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1404 = mark();
					synPredMatched1404 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1404 = false;
					}
					rewind(_m1404);
					inputState.guessing--;
				}
				if ( synPredMatched1404 ) {
					ident_asterisk_column();
				}
				else if ((_tokenSet_61.member(LA(1))) && (_tokenSet_285.member(LA(2))) && (_tokenSet_286.member(LA(3)))) {
					expr_column();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_292);
				} else {
				  throw ex;
				}
			}
		}
		
	public void expr_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			{
			if ((_tokenSet_293.member(LA(1)))) {
				alias();
			}
			else if ((_tokenSet_292.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXPR_COLUMN);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_292);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alias() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((LA(1)==LITERAL_as)) {
				match(LITERAL_as);
			}
			else if ((_tokenSet_33.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			alias_ident();
			if ( inputState.guessing==0 ) {
				__markRule(ALIAS_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_294);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_exp_list_using() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
				if ((_tokenSet_61.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			plsql_expression();
			{
			_loop1420:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
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
						if ((_tokenSet_61.member(LA(1)))) {
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					plsql_expression();
				}
				else {
					break _loop1420;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_EXPR_LIST_USING);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_181);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_system_session() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_system:
			{
				match(LITERAL_system);
				break;
			}
			case LITERAL_session:
			{
				match(LITERAL_session);
				break;
			}
			case LITERAL_database:
			{
				match(LITERAL_database);
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
				match(LITERAL_flush);
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
					match(LITERAL_set);
					identifier();
					match(EQ);
					{
					switch ( LA(1)) {
					case QUOTED_STR:
					{
						string_literal();
						break;
					}
					case NUMBER:
					{
						numeric_literal();
						break;
					}
					case IDENTIFIER:
					case DOUBLE_QUOTED_STRING:
					{
						identifier();
						break;
					}
					case LITERAL_local:
					{
						match(LITERAL_local);
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
					match(LITERAL_reset);
					identifier();
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
					match(LITERAL_sid);
					match(EQ);
					{
					switch ( LA(1)) {
					case QUOTED_STR:
					{
						string_literal();
						break;
					}
					case ASTERISK:
					{
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_column:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_grant:
				case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void selected_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_table:
			{
				table_func();
				{
				if ((_tokenSet_293.member(LA(1))) && (_tokenSet_295.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					alias();
				}
				else if ((_tokenSet_296.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_FUNCTION);
				}
				break;
			}
			case OPEN_PAREN:
			{
				from_subquery();
				break;
			}
			default:
				boolean synPredMatched1477 = false;
				if (((LA(1)==LITERAL_the) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m1477 = mark();
					synPredMatched1477 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_the);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1477 = false;
					}
					rewind(_m1477);
					inputState.guessing--;
				}
				if ( synPredMatched1477 ) {
					the_proc();
					{
					if ((_tokenSet_293.member(LA(1))) && (_tokenSet_295.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						alias();
					}
					else if ((_tokenSet_296.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
				}
				else if ((_tokenSet_96.member(LA(1))) && (_tokenSet_297.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					table_alias();
					{
					if ((LA(1)==LITERAL_partition) && (LA(2)==OPEN_PAREN) && (_tokenSet_5.member(LA(3)))) {
						match(LITERAL_partition);
						match(OPEN_PAREN);
						identifier2();
						match(CLOSE_PAREN);
					}
					else if ((_tokenSet_296.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_296);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ansi_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_inner:
			{
				match(LITERAL_inner);
				break;
			}
			case LITERAL_left:
			case LITERAL_right:
			case LITERAL_full:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_left:
				{
					match(LITERAL_left);
					break;
				}
				case LITERAL_right:
				{
					match(LITERAL_right);
					break;
				}
				case LITERAL_full:
				{
					match(LITERAL_full);
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
			match(LITERAL_join);
			selected_table();
			{
			switch ( LA(1)) {
			case LITERAL_on:
			{
				match(LITERAL_on);
				condition();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case COMMA:
			case OPEN_PAREN:
			case CLOSE_PAREN:
			case DIVIDE:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_with:
			case LITERAL_column:
			case LITERAL_using:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_group:
			case LITERAL_order:
			case LITERAL_for:
			case LITERAL_connect:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_loop:
			case LITERAL_select:
			case LITERAL_merge:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_minus:
			case LITERAL_left:
			case LITERAL_right:
			case LITERAL_inner:
			case LITERAL_join:
			case LITERAL_full:
			case LITERAL_where:
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
				__markRule(ANSI_JOIN_TAB_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_298);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_reference_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			selected_table();
			{
			_loop1442:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					selected_table();
				}
				else {
					break _loop1442;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_REFERENCE_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_249);
			} else {
			  throw ex;
			}
		}
	}
	
	public void call_argument() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==PASS_BY_NAME)) {
				parameter_name_ref();
				match(PASS_BY_NAME);
			}
			else if ((_tokenSet_73.member(LA(1))) && (_tokenSet_299.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			condition();
			if ( inputState.guessing==0 ) {
				__markRule(CALL_ARGUMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
					    throw ex;
					
			} else {
				throw ex;
			}
		}
	}
	
	public void parameter_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			name_fragment();
			if ( inputState.guessing==0 ) {
				__markRule(PARAMETER_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_300);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alias_ident() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1)))) {
				identifier2();
			}
			else if ((LA(1)==LITERAL_timestamp)) {
				match(LITERAL_timestamp);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(ALIAS_IDENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_294);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trim_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_leading:
			{
				match(LITERAL_leading);
				break;
			}
			case LITERAL_trailing:
			{
				match(LITERAL_trailing);
				break;
			}
			case LITERAL_both:
			{
				match(LITERAL_both);
				break;
			}
			default:
				if ((_tokenSet_61.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			plsql_expression();
			{
			switch ( LA(1)) {
			case LITERAL_from:
			{
				match(LITERAL_from);
				plsql_expression();
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
				__markRule(SPEC_CALL_ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_func() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_table);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			case LITERAL_select:
			{
				select_command();
				break;
			}
			case LITERAL_cast:
			{
				cast_function();
				break;
			}
			default:
				boolean synPredMatched1496 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==CLOSE_PAREN) && (_tokenSet_301.member(LA(3))))) {
					int _m1496 = mark();
					synPredMatched1496 = true;
					inputState.guessing++;
					try {
						{
						{
						_loop1495:
						do {
							if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3)))) {
								name_fragment();
								match(DOT);
							}
							else {
								break _loop1495;
							}
							
						} while (true);
						}
						name_fragment();
						matchNot(OPEN_PAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1496 = false;
					}
					rewind(_m1496);
					inputState.guessing--;
				}
				if ( synPredMatched1496 ) {
					{
					_loop1498:
					do {
						if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop1498;
						}
						
					} while (true);
					}
					name_fragment();
				}
				else if ((_tokenSet_24.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
					function_call();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_301);
			} else {
			  throw ex;
			}
		}
	}
	
	public void the_proc() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_the);
			subquery();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_301);
			} else {
			  throw ex;
			}
		}
	}
	
	public void from_subquery() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			subquery();
			{
			if ((_tokenSet_293.member(LA(1))) && (_tokenSet_295.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
			}
			else if ((_tokenSet_296.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FROM_SUBQUERY);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_296);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_alias() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			table_spec();
			{
			if ((_tokenSet_293.member(LA(1))) && (_tokenSet_302.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
			}
			else if ((_tokenSet_303.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(TABLE_ALIAS);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_303);
			} else {
			  throw ex;
			}
		}
	}
	
	public void from_plain_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			table_spec();
			{
			if ((_tokenSet_293.member(LA(1)))) {
				alias();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_96.member(LA(1))) && (_tokenSet_304.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_ref_ex();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_304);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cast_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			call_argument();
			match(LITERAL_as);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==CLOSE_PAREN) && (_tokenSet_66.member(LA(3)))) {
				type_name_ref();
			}
			else if ((_tokenSet_46.member(LA(1))) && (_tokenSet_305.member(LA(2))) && (_tokenSet_306.member(LA(3)))) {
				datatype();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SPEC_CALL_ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_ref_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_5.member(LA(1)))) {
				table_ref();
			}
			else if ((LA(1)==TABLE_NAME_SPEC)) {
				match(TABLE_NAME_SPEC);
				if ( inputState.guessing==0 ) {
					__markRule(TABLE_REF_WITH_LINK);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_304);
			} else {
			  throw ex;
			}
		}
	}
	
	public void link_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public void connect_clause_internal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_start:
			{
				{
				match(LITERAL_start);
				match(LITERAL_with);
				condition();
				}
				break;
			}
			case LITERAL_connect:
			{
				{
				match(LITERAL_connect);
				match(LITERAL_by);
				condition();
				}
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
				recover(ex,_tokenSet_307);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sorted_def() throws RecognitionException, TokenStreamException {
		
		Token  a = null;
		Token  d = null;
		
		try {      // for error handling
			plsql_expression();
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
					match(LITERAL_asc);
					break;
				}
				case LITERAL_desc:
				{
					d = LT(1);
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
					match(LITERAL_nulls);
					{
					switch ( LA(1)) {
					case LITERAL_first:
					{
						match(LITERAL_first);
						break;
					}
					case LITERAL_last:
					{
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
				case LITERAL_package:
				case LITERAL_alter:
				case LITERAL_drop:
				case LITERAL_function:
				case LITERAL_procedure:
				case LITERAL_type:
				case LITERAL_trigger:
				case LITERAL_associate:
				case LITERAL_with:
				case LITERAL_column:
				case LITERAL_using:
				case LITERAL_truncate:
				case LITERAL_comment:
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
				case LITERAL_host:
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
				case LITERAL_for:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_return:
				case LITERAL_loop:
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
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_with:
			case LITERAL_column:
			case LITERAL_using:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_for:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_return:
			case LITERAL_loop:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_308);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_spec_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_spec();
			{
			_loop1550:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
				}
				else {
					break _loop1550;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_SPEC_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_309);
			} else {
			  throw ex;
			}
		}
	}
	
	public void variable_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop1591:
			do {
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment();
					match(DOT);
				}
				else {
					break _loop1591;
				}
				
			} while (true);
			}
			name_fragment();
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_VAR_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_310);
			} else {
			  throw ex;
			}
		}
	}
	
	public void returning() throws RecognitionException, TokenStreamException {
		
		
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
			column_spec_list();
			match(LITERAL_into);
			expr_list();
			if ( inputState.guessing==0 ) {
				__markRule(RETURNING_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void subquery_update() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_update);
			table_alias();
			match(LITERAL_set);
			match(OPEN_PAREN);
			column_spec_list();
			match(CLOSE_PAREN);
			match(EQ);
			subquery();
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void simple_update() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_update);
			table_alias();
			match(LITERAL_set);
			column_spec();
			match(EQ);
			plsql_expression();
			{
			_loop1570:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
					match(EQ);
					plsql_expression();
				}
				else {
					break _loop1570;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				where_condition();
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void when_action() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_when);
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
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
				match(LITERAL_update);
				match(LITERAL_set);
				column_spec();
				match(EQ);
				plsql_expression();
				{
				_loop1564:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_spec();
						match(EQ);
						plsql_expression();
					}
					else {
						break _loop1564;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_insert:
			{
				insert_columns();
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
				break;
			}
			case EOF:
			case AT_PREFIXED:
			case SEMI:
			case OPEN_PAREN:
			case LITERAL_package:
			case LITERAL_alter:
			case LITERAL_drop:
			case LITERAL_function:
			case LITERAL_procedure:
			case LITERAL_type:
			case LITERAL_trigger:
			case LITERAL_associate:
			case LITERAL_column:
			case LITERAL_truncate:
			case LITERAL_comment:
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
			case LITERAL_host:
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
			case LITERAL_grant:
			case LITERAL_revoke:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_311);
			} else {
			  throw ex;
			}
		}
	}
	
	public void insert_columns() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_insert);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				match(OPEN_PAREN);
				column_spec_list();
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
			parentesized_exp_list();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_312);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lock_mode() throws RecognitionException, TokenStreamException {
		
		Token  r1 = null;
		Token  s1 = null;
		Token  r2 = null;
		Token  e1 = null;
		Token  s2 = null;
		Token  u = null;
		Token  s3 = null;
		Token  s4 = null;
		Token  r3 = null;
		Token  e2 = null;
		Token  e3 = null;
		
		try {      // for error handling
			if ((LA(1)==LITERAL_row) && (LA(2)==LITERAL_share)) {
				r1 = LT(1);
				match(LITERAL_row);
				s1 = LT(1);
				match(LITERAL_share);
			}
			else if ((LA(1)==LITERAL_row) && (LA(2)==LITERAL_exclusive)) {
				r2 = LT(1);
				match(LITERAL_row);
				e1 = LT(1);
				match(LITERAL_exclusive);
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_update)) {
				s2 = LT(1);
				match(LITERAL_share);
				u = LT(1);
				match(LITERAL_update);
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_mode)) {
				s3 = LT(1);
				match(LITERAL_share);
			}
			else if ((LA(1)==LITERAL_share) && (LA(2)==LITERAL_row)) {
				s4 = LT(1);
				match(LITERAL_share);
				r3 = LT(1);
				match(LITERAL_row);
				e2 = LT(1);
				match(LITERAL_exclusive);
			}
			else if ((LA(1)==LITERAL_exclusive)) {
				e3 = LT(1);
				match(LITERAL_exclusive);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_313);
			} else {
			  throw ex;
			}
		}
	}
	
	public void savepoint_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
	}
	
	public void oracle_loader_params() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_oracle_loader);
			directory_spec();
			{
			switch ( LA(1)) {
			case LITERAL_access:
			{
				access_parameters();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_314);
			} else {
			  throw ex;
			}
		}
	}
	
	public void oracle_datapump_params() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_oracle_datapump);
			directory_spec();
			{
			switch ( LA(1)) {
			case LITERAL_access:
			{
				write_access_parameters();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_314);
			} else {
			  throw ex;
			}
		}
	}
	
	public void location() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_location);
			match(OPEN_PAREN);
			{
			if (((LA(1) >= IDENTIFIER && LA(1) <= DOUBLE_QUOTED_STRING)) && (_tokenSet_315.member(LA(2))) && (_tokenSet_316.member(LA(3)))) {
				file_location_spec();
			}
			else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_316.member(LA(3)))) {
				string_literal();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop1748:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					{
					if (((LA(1) >= IDENTIFIER && LA(1) <= DOUBLE_QUOTED_STRING)) && (_tokenSet_315.member(LA(2))) && (_tokenSet_316.member(LA(3)))) {
						file_location_spec();
					}
					else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_316.member(LA(3)))) {
						string_literal();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
				}
				else {
					break _loop1748;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void directory_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_default:
			{
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
			match(LITERAL_directory);
			identifier();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_317);
			} else {
			  throw ex;
			}
		}
	}
	
	public void access_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_access);
			match(LITERAL_parameters);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_records:
			{
				record_format_info();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_314);
			} else {
			  throw ex;
			}
		}
	}
	
	public void write_access_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_access);
			match(LITERAL_parameters);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case LITERAL_nologfile:
			{
				match(LITERAL_nologfile);
				break;
			}
			case LITERAL_logfile:
			{
				{
				match(LITERAL_logfile);
				file_location_spec();
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
				match(LITERAL_version);
				{
				switch ( LA(1)) {
				case LITERAL_compatible:
				{
					match(LITERAL_compatible);
					break;
				}
				case LITERAL_latest:
				{
					match(LITERAL_latest);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
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
				match(LITERAL_compression);
				{
				switch ( LA(1)) {
				case LITERAL_enabled:
				{
					match(LITERAL_enabled);
					break;
				}
				case LITERAL_disabled:
				{
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
				match(LITERAL_encryption);
				{
				switch ( LA(1)) {
				case LITERAL_enabled:
				{
					match(LITERAL_enabled);
					break;
				}
				case LITERAL_disabled:
				{
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_314);
			} else {
			  throw ex;
			}
		}
	}
	
	public void file_location_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				identifier();
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_318);
			} else {
			  throw ex;
			}
		}
	}
	
	public void record_format_info() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_records);
			rec_format();
			{
			_loop1637:
			do {
				if ((_tokenSet_319.member(LA(1)))) {
					rec_format_tail();
				}
				else {
					break _loop1637;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_320);
			} else {
			  throw ex;
			}
		}
	}
	
	public void field_definitions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_fields);
			{
			switch ( LA(1)) {
			case LITERAL_enclosed:
			case LITERAL_terminated:
			{
				delim_spec();
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
				match(LITERAL_missing);
				match(LITERAL_field);
				match(LITERAL_values);
				match(LITERAL_are);
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
				match(LITERAL_reject);
				match(LITERAL_rows);
				match(LITERAL_with);
				match(LITERAL_all);
				match(LITERAL_null);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_321);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_transforms() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_column);
			match(LITERAL_transforms);
			match(OPEN_PAREN);
			transform();
			{
			_loop1670:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					transform();
				}
				else {
					break _loop1670;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rec_format() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_fixed:
			{
				{
				match(LITERAL_fixed);
				numeric_literal();
				}
				break;
			}
			case LITERAL_variable:
			{
				{
				match(LITERAL_variable);
				numeric_literal();
				}
				break;
			}
			case LITERAL_delimited:
			{
				{
				match(LITERAL_delimited);
				match(LITERAL_by);
				{
				switch ( LA(1)) {
				case LITERAL_newline:
				{
					match(LITERAL_newline);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_322);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rec_format_tail() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_characterset:
			{
				{
				match(LITERAL_characterset);
				{
				switch ( LA(1)) {
				case QUOTED_STR:
				{
					string_literal();
					break;
				}
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					identifier();
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
			case LITERAL_data:
			{
				{
				match(LITERAL_data);
				match(LITERAL_is);
				{
				switch ( LA(1)) {
				case LITERAL_big:
				{
					match(LITERAL_big);
					break;
				}
				case LITERAL_little:
				{
					match(LITERAL_little);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_endian);
				}
				break;
			}
			case LITERAL_byte:
			{
				{
				match(LITERAL_byte);
				match(LITERAL_order);
				match(LITERAL_mark);
				{
				switch ( LA(1)) {
				case LITERAL_check:
				{
					match(LITERAL_check);
					break;
				}
				case LITERAL_nocheck:
				{
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
				break;
			}
			case LITERAL_string:
			{
				{
				match(LITERAL_string);
				match(LITERAL_sizes);
				match(LITERAL_in);
				{
				switch ( LA(1)) {
				case LITERAL_bytes:
				{
					match(LITERAL_bytes);
					break;
				}
				case LITERAL_characters:
				{
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
				break;
			}
			case LITERAL_load:
			{
				{
				match(LITERAL_load);
				match(LITERAL_when);
				condition();
				}
				break;
			}
			case LITERAL_nobadfile:
			case LITERAL_badfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nobadfile:
				{
					match(LITERAL_nobadfile);
					break;
				}
				case LITERAL_badfile:
				{
					{
					match(LITERAL_badfile);
					file_location_spec();
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
			case LITERAL_nodiscardfile:
			case LITERAL_discardfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nodiscardfile:
				{
					match(LITERAL_nodiscardfile);
					break;
				}
				case LITERAL_discardfile:
				{
					{
					match(LITERAL_discardfile);
					file_location_spec();
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
			case LITERAL_nologfile:
			case LITERAL_logfile:
			{
				{
				switch ( LA(1)) {
				case LITERAL_nologfile:
				{
					match(LITERAL_nologfile);
					break;
				}
				case LITERAL_logfile:
				{
					{
					match(LITERAL_logfile);
					file_location_spec();
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
			case LITERAL_readsize:
			case LITERAL_skip:
			case LITERAL_data_cache:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_readsize:
				{
					match(LITERAL_readsize);
					break;
				}
				case LITERAL_data_cache:
				{
					match(LITERAL_data_cache);
					break;
				}
				case LITERAL_skip:
				{
					match(LITERAL_skip);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(NUMBER);
				}
				break;
			}
			case LITERAL_preprocessor:
			{
				{
				match(LITERAL_preprocessor);
				file_location_spec();
				}
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
				recover(ex,_tokenSet_322);
			} else {
			  throw ex;
			}
		}
	}
	
	public void delim_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_enclosed:
			{
				{
				match(LITERAL_enclosed);
				match(LITERAL_by);
				string_literal();
				{
				switch ( LA(1)) {
				case LITERAL_and:
				{
					match(LITERAL_and);
					string_literal();
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
				break;
			}
			case LITERAL_terminated:
			{
				{
				match(LITERAL_terminated);
				match(LITERAL_by);
				{
				switch ( LA(1)) {
				case LITERAL_whitespace:
				{
					match(LITERAL_whitespace);
					break;
				}
				case QUOTED_STR:
				{
					string_literal();
					break;
				}
				case DOUBLE_QUOTED_STRING:
				{
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
					match(LITERAL_enclosed);
					match(LITERAL_by);
					string_literal();
					{
					switch ( LA(1)) {
					case LITERAL_and:
					{
						match(LITERAL_and);
						string_literal();
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
				recover(ex,_tokenSet_323);
			} else {
			  throw ex;
			}
		}
	}
	
	public void trim_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_lrtrim:
			{
				match(LITERAL_lrtrim);
				break;
			}
			case LITERAL_notrim:
			{
				match(LITERAL_notrim);
				break;
			}
			case LITERAL_ltrim:
			{
				match(LITERAL_ltrim);
				break;
			}
			case LITERAL_rtrim:
			{
				match(LITERAL_rtrim);
				break;
			}
			case LITERAL_ldrtrim:
			{
				match(LITERAL_ldrtrim);
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
				recover(ex,_tokenSet_324);
			} else {
			  throw ex;
			}
		}
	}
	
	public void field_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			field_spec();
			{
			_loop1702:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					field_spec();
				}
				else {
					break _loop1702;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_321);
			} else {
			  throw ex;
			}
		}
	}
	
	public void transform() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			match(LITERAL_from);
			{
			switch ( LA(1)) {
			case LITERAL_null:
			{
				match(LITERAL_null);
				break;
			}
			case LITERAL_constant:
			{
				const_str();
				break;
			}
			case LITERAL_concat:
			{
				{
				match(LITERAL_concat);
				match(OPEN_PAREN);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					field_name();
					break;
				}
				case LITERAL_constant:
				{
					const_str();
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop1677:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						switch ( LA(1)) {
						case IDENTIFIER:
						case DOUBLE_QUOTED_STRING:
						{
							field_name();
							break;
						}
						case LITERAL_constant:
						{
							const_str();
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
						break _loop1677;
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
				match(LITERAL_lobfile);
				match(OPEN_PAREN);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case DOUBLE_QUOTED_STRING:
				{
					field_name();
					break;
				}
				case LITERAL_constant:
				{
					{
					const_str();
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
				_loop1684:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						switch ( LA(1)) {
						case IDENTIFIER:
						case DOUBLE_QUOTED_STRING:
						{
							field_name();
							break;
						}
						case LITERAL_constant:
						{
							{
							const_str();
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
						break _loop1684;
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void const_str() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_constant);
			string_literal();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_325);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lobfile_attr_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_from:
			{
				{
				match(LITERAL_from);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop1689:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop1689;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				}
				break;
			}
			case LITERAL_clob:
			{
				match(LITERAL_clob);
				break;
			}
			case LITERAL_blob:
			{
				match(LITERAL_blob);
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
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void field_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			{
			switch ( LA(1)) {
			case LITERAL_position:
			{
				pos_spec();
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void pos_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_position);
			match(OPEN_PAREN);
			{
			switch ( LA(1)) {
			case ASTERISK:
			{
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
				match(PLUS);
				break;
			}
			case MINUS:
			{
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
			match(COLON);
			{
			switch ( LA(1)) {
			case PLUS:
			{
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
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_326);
			} else {
			  throw ex;
			}
		}
	}
	
	public void datatype_spec() throws RecognitionException, TokenStreamException {
		
		
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
				match(LITERAL_integer);
				{
				switch ( LA(1)) {
				case LITERAL_external:
				{
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
					match(OPEN_PAREN);
					match(NUMBER);
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
			case LITERAL_decimal:
			case LITERAL_zoned:
			{
				{
				{
				switch ( LA(1)) {
				case LITERAL_decimal:
				{
					match(LITERAL_decimal);
					break;
				}
				case LITERAL_zoned:
				{
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
					match(LITERAL_external);
					}
					{
					switch ( LA(1)) {
					case OPEN_PAREN:
					{
						match(OPEN_PAREN);
						match(NUMBER);
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
					match(OPEN_PAREN);
					match(NUMBER);
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
				break;
			}
			case LITERAL_oracle_date:
			{
				match(LITERAL_oracle_date);
				break;
			}
			case LITERAL_oracle_number:
			{
				{
				match(LITERAL_oracle_number);
				{
				switch ( LA(1)) {
				case LITERAL_counted:
				{
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
				break;
			}
			case LITERAL_float:
			{
				{
				match(LITERAL_float);
				{
				switch ( LA(1)) {
				case LITERAL_external:
				{
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
					match(OPEN_PAREN);
					match(NUMBER);
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
			case LITERAL_double:
			{
				match(LITERAL_double);
				break;
			}
			case LITERAL_raw:
			{
				{
				match(LITERAL_raw);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
					match(NUMBER);
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
				break;
			}
			case LITERAL_char:
			{
				{
				match(LITERAL_char);
				{
				switch ( LA(1)) {
				case OPEN_PAREN:
				{
					match(OPEN_PAREN);
					match(NUMBER);
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
					match(LITERAL_varchar);
					break;
				}
				case LITERAL_varraw:
				{
					match(LITERAL_varraw);
					break;
				}
				case LITERAL_varcharc:
				{
					match(LITERAL_varcharc);
					break;
				}
				case LITERAL_varrawc:
				{
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
					match(OPEN_PAREN);
					match(NUMBER);
					{
					switch ( LA(1)) {
					case COMMA:
					{
						match(COMMA);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
	}
	
	public void date_format_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_date_format);
			{
			switch ( LA(1)) {
			case LITERAL_timestamp:
			{
				match(LITERAL_timestamp);
				break;
			}
			case LITERAL_date:
			{
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
				match(LITERAL_with);
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
			match(LITERAL_mask);
			{
			switch ( LA(1)) {
			case QUOTED_STR:
			{
				string_literal();
				break;
			}
			case DOUBLE_QUOTED_STRING:
			{
				match(DOUBLE_QUOTED_STRING);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
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
		"PLSQL_ENV_VAR",
		"ANY_CHARACTER",
		"IDENTIFIER",
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
		"PLSQL_EXPR_LIST_USING",
		"EXPR_LIST",
		"DECLARE_LIST",
		"FUNCTION_CALL",
		"PACKAGE_INIT_SECTION",
		"CALL_ARGUMENT_LIST",
		"SPEC_CALL_ARGUMENT_LIST",
		"CALL_ARGUMENT",
		"BASE_EXRESSION",
		"COLUMN_SPEC",
		"LOGICAL_AND",
		"LOGICAL_OR",
		"CASE_EXPRESSION_SMPL",
		"CASE_EXPRESSION_SRCH",
		"CASE_STATEMENT",
		"COUNT_FUNC",
		"RANK_FUNCTION",
		"LEAD_FUNCTION",
		"LAG_FUNCTION",
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
		"CAST_FUNC",
		"VAR_REF",
		"PLSQL_VAR_REF",
		"PARAMETER_REF",
		"EXCEPTION_SECTION",
		"FETCH_STATEMENT",
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
		"CURSOR_NAME_REF",
		"DATATYPE",
		"EXTRACT_DATE_FUNC",
		"ANSI_JOIN_TAB_SPEC",
		"INTERVAL_DAY_TO_SEC_EXPR",
		"INTERVAL_YEAR_TO_MONTH_EXPR",
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
		"TABLE_REF",
		"TABLE_REF_WITH_LINK",
		"FROM_SUBQUERY",
		"FROM_PLAIN_TABLE",
		"SCHEMA_NAME",
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
		"SEQUENCE_REF",
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
		"ALTER_GENERIC",
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
		"OWNER_COLUMN_NAME_LIST",
		"SERIALLY_REUSABLE_PRAGMA",
		"CREATE_VIEW",
		"DATATYPE_PARAM_INFO",
		"CREATE_VIEW_COLUMN_DEF",
		"TABLE_FUNCTION",
		"ROWNUM",
		"ROWID",
		"CUSTOM_AGGR_FUNCTION",
		"LAST_STMT_RESULT_NUM",
		"LAST_STMT_RESULT_BOOL",
		"CURRENT_OF_CLAUSE",
		"CURSOR_STATE",
		"SQLCODE_SYSVAR",
		"SQLERRM_SYSVAR",
		"COLLECTION_METHOD_CALL",
		"COLLECTION_METHOD_CALL2",
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
		"DROP_TRIGGER",
		"CREATE_SYNONYM",
		"SYNONYM_NAME",
		"SYNONYM_OBJ",
		"SYNONYM_OBJ_WITH_LINK",
		"COLUMN_PK_SPEC",
		"COLUMN_FK_SPEC",
		"NOT_NULL_STMT",
		"COLUMN_CHECK_CONSTRAINT",
		"COLUMN_NOT_NULL_CONSTRAINT",
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
		"EXEC_NAME_REF",
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
		"FORALL_LOOP_SPEC",
		"CURSOR_REF_LOOP_SPEC",
		"CURSOR_LOOP_SPEC",
		"CURSOR_LOOP_INDEX",
		"NUM_LOOP_INDEX",
		"NUMERIC_LOOP_SPEC",
		"RECORD_ITEM_NAME",
		"GOTO_STATEMENT",
		"EXIT_STATEMENT",
		"LABEL_NAME",
		"PARTITION_SPEC",
		"RANGE_PARTITION",
		"HASH_PARTITION",
		"CREATE_TABLESPACE",
		"DROP_TABLESPACE",
		"TABLESPACE_NAME",
		"ALTER_TABLESPACE",
		"CREATE_TYPE",
		"BIND_VAR",
		"RETURNING_CLAUSE",
		"\"package\"",
		"\"body\"",
		"\"alter\"",
		"\"drop\"",
		"\"table\"",
		"\"purge\"",
		"\"view\"",
		"\"cascade\"",
		"\"constraints\"",
		"\"function\"",
		"\"procedure\"",
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
		"\"trigger\"",
		"\"associate\"",
		"\"statistics\"",
		"\"with\"",
		"\"column\"",
		"\"functions\"",
		"\"packages\"",
		"\"types\"",
		"\"indexes\"",
		"\"indextypes\"",
		"\"system\"",
		"\"managed\"",
		"\"storage\"",
		"\"default\"",
		"\"cost\"",
		"\"selectivity\"",
		"\"using\"",
		"\"null\"",
		"\"truncate\"",
		"\"comment\"",
		"\"on\"",
		"\"is\"",
		"\"sysdate\"",
		"\"systimestamp\"",
		"\"not\"",
		"\"disable\"",
		"\"enable\"",
		"\"row\"",
		"\"movement\"",
		"\"primary\"",
		"\"key\"",
		"\"references\"",
		"\"rely\"",
		"\"constraint\"",
		"\"check\"",
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
		"\"host\"",
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
		"\"global\"",
		"\"temporary\"",
		"\"bigfile\"",
		"\"smallfile\"",
		"\"tablespace\"",
		"\"tempfile\"",
		"\"undo\"",
		"\"datafile\"",
		"\"next\"",
		"\"maxsize\"",
		"\"size\"",
		"\"reuse\"",
		"\"extent\"",
		"\"management\"",
		"\"local\"",
		"\"uniform\"",
		"\"dictionary\"",
		"\"retention\"",
		"\"guarantee\"",
		"\"noguarantee\"",
		"\"autoextend\"",
		"\"group\"",
		"\"logging\"",
		"\"nologging\"",
		"\"no\"",
		"\"online\"",
		"\"offline\"",
		"\"normal\"",
		"\"immediate\"",
		"\"read\"",
		"\"only\"",
		"\"write\"",
		"\"permanent\"",
		"\"including\"",
		"\"contents\"",
		"\"and\"",
		"\"datafiles\"",
		"\"add\"",
		"\"rename\"",
		"\"to\"",
		"\"end\"",
		"\"backup\"",
		"\"coalesce\"",
		"\"minimum\"",
		"\"maxvalue\"",
		"\"minvalue\"",
		"\"cycle\"",
		"\"nocycle\"",
		"\"cache\"",
		"\"nocache\"",
		"\"increment\"",
		"\"by\"",
		"\"order\"",
		"\"noorder\"",
		"\"for\"",
		"\"connect\"",
		"\"identified\"",
		"\"after\"",
		"\"before\"",
		"\"startup\"",
		"\"shutdown\"",
		"\"servererror\"",
		"\"logon\"",
		"\"logoff\"",
		"\"analyze\"",
		"\"audit\"",
		"\"noaudit\"",
		"\"ddl\"",
		"\"diassociate\"",
		"\"grant\"",
		"\"revoke\"",
		"\"schema\"",
		"\"instead\"",
		"\"of\"",
		"\"delete\"",
		"\"insert\"",
		"\"update\"",
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
		"\"preserve\"",
		"\"rows\"",
		"\"nested\"",
		"\"store\"",
		"\"return\"",
		"\"locator\"",
		"\"value\"",
		"\"lob\"",
		"\"chunk\"",
		"\"reads\"",
		"\"pctversion\"",
		"\"freepools\"",
		"\"in\"",
		"\"segment\"",
		"\"creation\"",
		"\"deferred\"",
		"\"cluster\"",
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
		"\"compute\"",
		"\"parallel\"",
		"\"noparallel\"",
		"\"monitoring\"",
		"\"nomonitoring\"",
		"\"partition\"",
		"\"range\"",
		"\"interval\"",
		"\"values\"",
		"\"less\"",
		"\"than\"",
		"\"overflow\"",
		"\"hash\"",
		"\"partitions\"",
		"\"novalidate\"",
		"\"organization\"",
		"\"pctthreshold\"",
		"\"heap\"",
		"\"external\"",
		"\"degree\"",
		"\"instances\"",
		"\"reject\"",
		"\"limit\"",
		"\"unlimited\"",
		"\"initial\"",
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
		"\"flash_cache\"",
		"\"cell_flash_cache\"",
		"\"encrypt\"",
		"\"foreign\"",
		"\"restrict\"",
		"\"action\"",
		"\"modify\"",
		"\"sort\"",
		"\"nosort\"",
		"\"reverse\"",
		"\"visible\"",
		"\"novisible\"",
		"\"under\"",
		"\"final\"",
		"\"object\"",
		"\"record\"",
		"\"ref\"",
		"\"cursor\"",
		"\"rowtype\"",
		"\"varray\"",
		"\"option\"",
		"\"view_column_def_$internal$\"",
		"\"timestamp\"",
		"\"authid\"",
		"\"wrapped\"",
		"\"pragma\"",
		"\"restrict_references\"",
		"\"interface\"",
		"\"builtin\"",
		"\"fipsflag\"",
		"\"exception_init\"",
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
		"\"autonomous_transaction\"",
		"\"prior\"",
		"\"number\"",
		"\"binary_integer\"",
		"\"natural\"",
		"\"positive\"",
		"\"char\"",
		"\"byte\"",
		"\"raw\"",
		"\"boolean\"",
		"\"date\"",
		"\"time\"",
		"\"zone\"",
		"\"year\"",
		"\"month\"",
		"\"day\"",
		"\"second\"",
		"\"smallint\"",
		"\"real\"",
		"\"numeric\"",
		"CLOSE_PAREPlSqlPlllN",
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
		"\"rowid\"",
		"\"blob\"",
		"\"clob\"",
		"\"nclob\"",
		"\"bfile\"",
		"\"out\"",
		"\"nocopy\"",
		"\"any_cs\"",
		"\"charset\"",
		"\"exception\"",
		"\"serially_reusable\"",
		"\"language\"",
		"\"java\"",
		"\"name\"",
		"\"pipelined\"",
		"\"parallel_enable\"",
		"\"deterministic\"",
		"\"save\"",
		"\"exceptions\"",
		"\"indices\"",
		"\"true\"",
		"\"false\"",
		"\"**\"",
		"\"at\"",
		"\"current\"",
		"\"exists\"",
		"\"like\"",
		"\"escape\"",
		"\"between\"",
		"\"member\"",
		"\"sqlcode\"",
		"\"sqlerrm\"",
		"\"cast\"",
		"\"trim\"",
		"\"multiset\"",
		"\"lag\"",
		"\"lead\"",
		"\"rank\"",
		"\"dense_rank\"",
		"\"extract\"",
		"\"minute\"",
		"\"hour\"",
		"\"any\"",
		"\"nextval\"",
		"\"currval\"",
		"\"over\"",
		"\"sessiontimezone\"",
		"\"dbtimezone\"",
		"\"distinct\"",
		"\"from\"",
		"\"timezone_hour\"",
		"\"timezone_minute\"",
		"\"timezone_region\"",
		"\"timezone_abbr\"",
		"\"work\"",
		"\"elsif\"",
		"\"union\"",
		"\"intersect\"",
		"\"minus\"",
		"\"bulk\"",
		"\"collect\"",
		"\"into\"",
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
		"\"full\"",
		"\"where\"",
		"\"leading\"",
		"\"trailing\"",
		"\"both\"",
		"\"current_timestamp\"",
		"\"rownum\"",
		"\"the\"",
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
		"\"aggregate\"",
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
		"\"transforms\"",
		"\"oracle_loader\"",
		"\"oracle_datapump\"",
		"\"latest\"",
		"\"characters\"",
		"\"are\"",
		"\"ldrtrim\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[14];
		data[0]=2L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[28];
		data[0]=33831139562626562L;
		data[5]=-1013488037042061312L;
		data[6]=-310311387138L;
		data[7]=-2392537570492689L;
		data[8]=-2251802231898369L;
		data[9]=-2199331162029818013L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194629L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[22];
		data[0]=272629760L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[28];
		data[0]=-14L;
		for (int i = 1; i<=12; i++) { data[i]=-1L; }
		data[13]=33554431L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[22];
		data[0]=272629762L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899893194758L;
		data[7]=3456081704643444355L;
		data[8]=4544967339380375295L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[20];
		data[0]=33554432L;
		data[6]=8388608L;
		data[8]=16777216L;
		data[9]=13510798882111488L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016856940669566976L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[5]=-1016856940669566976L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[16];
		data[7]=24064L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643460739L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[22];
		data[0]=4567597058L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[28];
		data[0]=2251799816437760L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456363179620155015L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = new long[28];
		data[0]=2287262615863296L;
		data[5]=-1016883328948633600L;
		data[6]=-2413929994062659590L;
		data[7]=8067767723070832271L;
		data[8]=4546093273631751935L;
		data[9]=-33171965434659005L;
		data[10]=2305834178746253311L;
		data[11]=2076226086106806587L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = new long[28];
		data[0]=2251799816437760L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = new long[20];
		data[6]=8388608L;
		data[8]=16777216L;
		data[9]=13510798882111488L;
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = new long[28];
		data[0]=2251799816437760L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456363179620155011L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = new long[28];
		data[0]=35187924271104L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211606478323718L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=4578514052992728899L;
		data[10]=2305834178746252048L;
		data[11]=2076226086106806587L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = new long[28];
		data[0]=29273402148257794L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=3456363179620155055L;
		data[8]=4544967339365170943L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = new long[22];
		data[0]=314572802L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=48442283296555008L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881474195462L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295132605184L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = new long[22];
		data[0]=314572802L;
		data[5]=120499877334482944L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = new long[16];
		data[0]=4194304L;
		data[6]=-1161577272457560000L;
		data[7]=15L;
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = new long[28];
		data[0]=39576010754L;
		data[5]=-1013488037042061312L;
		data[6]=-319019286530L;
		data[7]=-2682808640225617L;
		data[8]=-4678395866948763905L;
		data[9]=6992443487623053127L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = new long[14];
		data[0]=33554432L;
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572725497780L;
		data[7]=47L;
		data[8]=18636800L;
		data[9]=13510798882111488L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=48442283296555008L;
		data[6]=-1158199572729692084L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=120781352311193600L;
		data[6]=-1158199572725497780L;
		data[7]=-5192650370358181841L;
		data[8]=34378375168L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886356L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = new long[14];
		data[0]=67108864L;
		data[6]=262144L;
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = new long[28];
		data[0]=33832204714515970L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2392537570492689L;
		data[8]=4546102071419272959L;
		data[9]=-2212843816337801405L;
		data[10]=2234630889176825855L;
		data[11]=-4828071418602194119L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = new long[26];
		data[0]=39539703810L;
		data[5]=39435084041814016L;
		data[6]=-1158199183560376212L;
		data[7]=47L;
		data[8]=34361597952L;
		data[9]=4613937818241073154L;
		data[10]=2305551654704906336L;
		data[11]=1970324836974592L;
		data[12]=45056L;
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = new long[14];
		data[0]=67108864L;
		data[6]=294912L;
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = new long[28];
		data[0]=33830005657705986L;
		data[5]=-1013488037042061312L;
		data[6]=-275955843074L;
		data[7]=-2392537302057233L;
		data[8]=-2252077248217345L;
		data[9]=-2212841960907997341L;
		data[10]=2234630889176825855L;
		data[11]=-4828071418602194119L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = new long[28];
		data[0]=33830005657705986L;
		data[5]=-1013488037042061312L;
		data[6]=-275955843074L;
		data[7]=-2251799813701905L;
		data[8]=4546102071419272959L;
		data[9]=7010528220516974403L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=17301503L;
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = new long[14];
		data[0]=603979776L;
		data[6]=114286919680L;
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = new long[28];
		data[0]=35192793858050L;
		data[5]=-1013488037042061312L;
		data[6]=-275960299522L;
		data[7]=3456081704643444399L;
		data[8]=-2260634500792577L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = new long[14];
		data[0]=603979776L;
		data[6]=113883742208L;
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = new long[28];
		data[0]=35192189878274L;
		data[5]=-1013488037042061312L;
		data[6]=-284550234114L;
		data[7]=3456081704643444399L;
		data[8]=-2260634500792577L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = new long[14];
		data[0]=603979776L;
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = new long[28];
		data[0]=887750658L;
		data[5]=-1013488037042061312L;
		data[6]=-1136918530L;
		data[7]=3456081704643444399L;
		data[8]=4544967339365170943L;
		data[9]=2380757434835926851L;
		data[10]=2305834178729474912L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = new long[22];
		data[6]=274877906944L;
		data[9]=2251799813685250L;
		data[10]=2305551654704906240L;
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = new long[28];
		data[0]=33797020291046914L;
		data[5]=-1008984437414690816L;
		data[6]=-276006174722L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2198602094288567136L;
		data[11]=-4828071437409482247L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380898172324282179L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = new long[28];
		data[0]=33797020324601346L;
		data[5]=-1008984437414690816L;
		data[6]=-276006174722L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2198602092141083488L;
		data[11]=-4828071437409482247L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = new long[28];
		data[0]=33797020291046914L;
		data[5]=-1008984437414690816L;
		data[6]=-276006174722L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2198602092141083488L;
		data[11]=-4828071437409482247L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39435084041814016L;
		data[6]=-1158199441267359668L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=512L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = new long[20];
		data[6]=79524003840L;
		data[8]=134217728L;
		data[9]=4294967296L;
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = new long[28];
		data[0]=4503604807335938L;
		data[5]=39435084041814016L;
		data[6]=-1158199555151101844L;
		data[7]=-3458764513283669969L;
		data[8]=34445484032L;
		data[9]=4611686018427387904L;
		data[10]=1075839072L;
		data[11]=-5186176445893836800L;
		data[12]=281354986094595L;
		data[13]=4128L;
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = new long[28];
		data[0]=33836645710700034L;
		data[5]=-1008984437414690816L;
		data[6]=-1073741826L;
		data[7]=-16657L;
		data[8]=-2251802366050561L;
		data[9]=-1059920421945344153L;
		data[10]=9223363206393626623L;
		data[11]=-4828071418602193927L;
		data[12]=-933917L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = new long[28];
		data[0]=35184656908290L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=3456081704643444399L;
		data[8]=4544967339365170943L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	private static final long[] mk_tokenSet_58() {
		long[] data = new long[28];
		data[0]=35184661102592L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
	private static final long[] mk_tokenSet_59() {
		long[] data = new long[28];
		data[0]=35222288203778L;
		data[5]=-1013488037042061312L;
		data[6]=-318914166786L;
		data[7]=3456081704643444399L;
		data[8]=4546093239339122431L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
	private static final long[] mk_tokenSet_60() {
		long[] data = new long[28];
		data[0]=283770882L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=3456081704643444399L;
		data[8]=4544967339365170943L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
	private static final long[] mk_tokenSet_61() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
	private static final long[] mk_tokenSet_62() {
		long[] data = new long[28];
		data[0]=35364147953666L;
		data[5]=-1013488037042061312L;
		data[6]=-318914166786L;
		data[7]=3456081704643444399L;
		data[8]=4546093239339122431L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
	private static final long[] mk_tokenSet_63() {
		long[] data = new long[28];
		data[0]=-8208L;
		for (int i = 1; i<=12; i++) { data[i]=-1L; }
		data[13]=33554431L;
		return data;
	}
	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
	private static final long[] mk_tokenSet_64() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881474195462L;
		data[7]=3456081704643444387L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295132605184L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());
	private static final long[] mk_tokenSet_65() {
		long[] data = new long[22];
		data[0]=306184194L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());
	private static final long[] mk_tokenSet_66() {
		long[] data = new long[28];
		data[0]=33830005657705986L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2392537570492689L;
		data[8]=4546102071419272959L;
		data[9]=7010528220516974403L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());
	private static final long[] mk_tokenSet_67() {
		long[] data = new long[28];
		data[0]=35222812491776L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());
	private static final long[] mk_tokenSet_68() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971012L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());
	private static final long[] mk_tokenSet_69() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076227185618434361L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());
	private static final long[] mk_tokenSet_70() {
		long[] data = new long[24];
		data[10]=1030792151040L;
		data[11]=65983582568448L;
		return data;
	}
	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());
	private static final long[] mk_tokenSet_71() {
		long[] data = new long[24];
		data[0]=1048576L;
		data[10]=1030792151040L;
		data[11]=12884901888L;
		return data;
	}
	public static final BitSet _tokenSet_71 = new BitSet(mk_tokenSet_71());
	private static final long[] mk_tokenSet_72() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239404658431L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076227185618434361L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_72 = new BitSet(mk_tokenSet_72());
	private static final long[] mk_tokenSet_73() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_73 = new BitSet(mk_tokenSet_73());
	private static final long[] mk_tokenSet_74() {
		long[] data = new long[28];
		data[0]=52974046551552L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881347842054L;
		data[7]=3456090500736466627L;
		data[8]=4546102035430702847L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086110230841L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_74 = new BitSet(mk_tokenSet_74());
	private static final long[] mk_tokenSet_75() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899851251718L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2234630889153755904L;
		data[11]=2076226067299535161L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_75 = new BitSet(mk_tokenSet_75());
	private static final long[] mk_tokenSet_76() {
		long[] data = new long[28];
		data[0]=33830005691260418L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2392537570492689L;
		data[8]=4546102071419272959L;
		data[9]=7010528220516974403L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_76 = new BitSet(mk_tokenSet_76());
	private static final long[] mk_tokenSet_77() {
		long[] data = new long[28];
		data[0]=1076494336L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_77 = new BitSet(mk_tokenSet_77());
	private static final long[] mk_tokenSet_78() {
		long[] data = new long[28];
		data[0]=19529728L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881474195462L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2162573295132605184L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_78 = new BitSet(mk_tokenSet_78());
	private static final long[] mk_tokenSet_79() {
		long[] data = new long[28];
		data[0]=33797020291046914L;
		data[5]=-1008984437414690816L;
		data[6]=-276006174722L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2198610888234105696L;
		data[11]=-4828071437409482247L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_79 = new BitSet(mk_tokenSet_79());
	private static final long[] mk_tokenSet_80() {
		long[] data = new long[28];
		data[0]=33797020291046914L;
		data[5]=-1008984437414690816L;
		data[6]=-276006174722L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2198602092677954400L;
		data[11]=-4828071437409482247L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_80 = new BitSet(mk_tokenSet_80());
	private static final long[] mk_tokenSet_81() {
		long[] data = new long[28];
		data[0]=2199327997952L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_81 = new BitSet(mk_tokenSet_81());
	private static final long[] mk_tokenSet_82() {
		long[] data = new long[28];
		data[0]=37387476008960L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_82 = new BitSet(mk_tokenSet_82());
	private static final long[] mk_tokenSet_83() {
		long[] data = new long[28];
		data[0]=35188419198976L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_83 = new BitSet(mk_tokenSet_83());
	private static final long[] mk_tokenSet_84() {
		long[] data = new long[28];
		data[0]=33830040050998786L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2392537570492689L;
		data[8]=4546102071419272959L;
		data[9]=7010528220516974403L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_84 = new BitSet(mk_tokenSet_84());
	private static final long[] mk_tokenSet_85() {
		long[] data = new long[28];
		data[0]=33794821267791362L;
		data[5]=-1013488037042061312L;
		data[6]=-318956371970L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2162573295122119520L;
		data[11]=-4828071437409482439L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_85 = new BitSet(mk_tokenSet_85());
	private static final long[] mk_tokenSet_86() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_86 = new BitSet(mk_tokenSet_86());
	private static final long[] mk_tokenSet_87() {
		long[] data = new long[28];
		data[0]=2268139749376L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269057744902L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2087487265391217465L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_87 = new BitSet(mk_tokenSet_87());
	private static final long[] mk_tokenSet_88() {
		long[] data = new long[28];
		data[0]=29311059537625088L;
		data[5]=-1008966845228646400L;
		data[6]=-2413930260350631937L;
		data[7]=8068189935535898255L;
		data[8]=4546102069926100735L;
		data[9]=-2212843816337801405L;
		data[10]=4540473898388422655L;
		data[11]=2085447690128964923L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_88 = new BitSet(mk_tokenSet_88());
	private static final long[] mk_tokenSet_89() {
		long[] data = new long[28];
		data[0]=2268106194944L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269057744902L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2087487265391217465L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_89 = new BitSet(mk_tokenSet_89());
	private static final long[] mk_tokenSet_90() {
		long[] data = new long[22];
		data[0]=13803454466L;
		data[5]=120781352311193600L;
		data[6]=-1158199572725497780L;
		data[7]=-5192650370358181841L;
		data[8]=34378375168L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_90 = new BitSet(mk_tokenSet_90());
	private static final long[] mk_tokenSet_91() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004161163730944L;
		data[9]=512L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_91 = new BitSet(mk_tokenSet_91());
	private static final long[] mk_tokenSet_92() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_92 = new BitSet(mk_tokenSet_92());
	private static final long[] mk_tokenSet_93() {
		long[] data = new long[22];
		data[0]=4609540098L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=18636800L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_93 = new BitSet(mk_tokenSet_93());
	private static final long[] mk_tokenSet_94() {
		long[] data = new long[16];
		data[6]=4611686018427387904L;
		data[7]=4030721666496593920L;
		return data;
	}
	public static final BitSet _tokenSet_94 = new BitSet(mk_tokenSet_94());
	private static final long[] mk_tokenSet_95() {
		long[] data = new long[22];
		data[0]=35188948074498L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886356L;
		data[7]=4607182418800017455L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_95 = new BitSet(mk_tokenSet_95());
	private static final long[] mk_tokenSet_96() {
		long[] data = new long[28];
		data[0]=2818048L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_96 = new BitSet(mk_tokenSet_96());
	private static final long[] mk_tokenSet_97() {
		long[] data = new long[16];
		data[5]=9007199254740992L;
		data[7]=1270237856256L;
		return data;
	}
	public static final BitSet _tokenSet_97 = new BitSet(mk_tokenSet_97());
	private static final long[] mk_tokenSet_98() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=48442283296555008L;
		data[6]=-1158199572733886388L;
		data[7]=3768197376619055L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_98 = new BitSet(mk_tokenSet_98());
	private static final long[] mk_tokenSet_99() {
		long[] data = new long[22];
		data[0]=4643094530L;
		data[5]=48442283296555008L;
		data[6]=-1158199572733886388L;
		data[7]=1270237860399L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_99 = new BitSet(mk_tokenSet_99());
	private static final long[] mk_tokenSet_100() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=48442283296555008L;
		data[6]=-1158199572733886388L;
		data[7]=1270271410735L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_100 = new BitSet(mk_tokenSet_100());
	private static final long[] mk_tokenSet_101() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=48442283296555008L;
		data[6]=-1158199572733886388L;
		data[7]=1270237856303L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_101 = new BitSet(mk_tokenSet_101());
	private static final long[] mk_tokenSet_102() {
		long[] data = new long[16];
		data[7]=105553116307456L;
		return data;
	}
	public static final BitSet _tokenSet_102 = new BitSet(mk_tokenSet_102());
	private static final long[] mk_tokenSet_103() {
		long[] data = new long[16];
		data[7]=25769844736L;
		return data;
	}
	public static final BitSet _tokenSet_103 = new BitSet(mk_tokenSet_103());
	private static final long[] mk_tokenSet_104() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=4030721666496593967L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_104 = new BitSet(mk_tokenSet_104());
	private static final long[] mk_tokenSet_105() {
		long[] data = new long[16];
		data[7]=4611686018427387904L;
		return data;
	}
	public static final BitSet _tokenSet_105 = new BitSet(mk_tokenSet_105());
	private static final long[] mk_tokenSet_106() {
		long[] data = new long[18];
		data[6]=402653184L;
		data[8]=65542L;
		return data;
	}
	public static final BitSet _tokenSet_106 = new BitSet(mk_tokenSet_106());
	private static final long[] mk_tokenSet_107() {
		long[] data = new long[18];
		data[7]=4611686018427387916L;
		data[8]=71303168L;
		return data;
	}
	public static final BitSet _tokenSet_107 = new BitSet(mk_tokenSet_107());
	private static final long[] mk_tokenSet_108() {
		long[] data = new long[16];
		data[7]=12L;
		return data;
	}
	public static final BitSet _tokenSet_108 = new BitSet(mk_tokenSet_108());
	private static final long[] mk_tokenSet_109() {
		long[] data = new long[16];
		data[6]=4194304L;
		data[7]=64L;
		return data;
	}
	public static final BitSet _tokenSet_109 = new BitSet(mk_tokenSet_109());
	private static final long[] mk_tokenSet_110() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=111774153056452608L;
		data[6]=-1158199535824011188L;
		data[7]=47L;
		data[8]=136077312L;
		data[9]=134217984L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_110 = new BitSet(mk_tokenSet_110());
	private static final long[] mk_tokenSet_111() {
		long[] data = new long[14];
		data[0]=536870912L;
		return data;
	}
	public static final BitSet _tokenSet_111 = new BitSet(mk_tokenSet_111());
	private static final long[] mk_tokenSet_112() {
		long[] data = new long[20];
		data[5]=72057594037927936L;
		data[6]=36507222016L;
		data[8]=134217728L;
		data[9]=256L;
		return data;
	}
	public static final BitSet _tokenSet_112 = new BitSet(mk_tokenSet_112());
	private static final long[] mk_tokenSet_113() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572327022516L;
		data[7]=216172793929142319L;
		data[8]=-8004161163730944L;
		data[9]=512L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_113 = new BitSet(mk_tokenSet_113());
	private static final long[] mk_tokenSet_114() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004169753665536L;
		data[9]=512L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_114 = new BitSet(mk_tokenSet_114());
	private static final long[] mk_tokenSet_115() {
		long[] data = new long[18];
		data[6]=402669568L;
		data[7]=216172782147342336L;
		data[8]=7146825580544L;
		return data;
	}
	public static final BitSet _tokenSet_115 = new BitSet(mk_tokenSet_115());
	private static final long[] mk_tokenSet_116() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=47L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_116 = new BitSet(mk_tokenSet_116());
	private static final long[] mk_tokenSet_117() {
		long[] data = new long[18];
		data[0]=536870912L;
		data[6]=402669568L;
		data[7]=216172782147342336L;
		data[8]=7146825580544L;
		return data;
	}
	public static final BitSet _tokenSet_117 = new BitSet(mk_tokenSet_117());
	private static final long[] mk_tokenSet_118() {
		long[] data = new long[28];
		data[0]=35189487697922L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=3456081704643444399L;
		data[8]=-3386809285542145L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_118 = new BitSet(mk_tokenSet_118());
	private static final long[] mk_tokenSet_119() {
		long[] data = new long[18];
		data[0]=536870912L;
		data[6]=402669568L;
		data[7]=216172785368567808L;
		data[8]=289721313918976L;
		return data;
	}
	public static final BitSet _tokenSet_119 = new BitSet(mk_tokenSet_119());
	private static final long[] mk_tokenSet_120() {
		long[] data = new long[20];
		data[7]=65536L;
		data[9]=3875274752L;
		return data;
	}
	public static final BitSet _tokenSet_120 = new BitSet(mk_tokenSet_120());
	private static final long[] mk_tokenSet_121() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=216172793962696751L;
		data[8]=-7997297805991936L;
		data[9]=2130437997088L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_121 = new BitSet(mk_tokenSet_121());
	private static final long[] mk_tokenSet_122() {
		long[] data = new long[20];
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=568297977978814464L;
		data[9]=512L;
		return data;
	}
	public static final BitSet _tokenSet_122 = new BitSet(mk_tokenSet_122());
	private static final long[] mk_tokenSet_123() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=544L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_123 = new BitSet(mk_tokenSet_123());
	private static final long[] mk_tokenSet_124() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=2130437997088L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_124 = new BitSet(mk_tokenSet_124());
	private static final long[] mk_tokenSet_125() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=134218272L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_125 = new BitSet(mk_tokenSet_125());
	private static final long[] mk_tokenSet_126() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=2130438029824L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_126 = new BitSet(mk_tokenSet_126());
	private static final long[] mk_tokenSet_127() {
		long[] data = new long[28];
		data[0]=606732288L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4553974538618339071L;
		data[9]=2378505635022241635L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_127 = new BitSet(mk_tokenSet_127());
	private static final long[] mk_tokenSet_128() {
		long[] data = new long[28];
		data[0]=606732288L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4608299209123495679L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_128 = new BitSet(mk_tokenSet_128());
	private static final long[] mk_tokenSet_129() {
		long[] data = new long[22];
		data[0]=5179965442L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=134218240L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_129 = new BitSet(mk_tokenSet_129());
	private static final long[] mk_tokenSet_130() {
		long[] data = new long[20];
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=568297977978814464L;
		data[9]=32L;
		return data;
	}
	public static final BitSet _tokenSet_130 = new BitSet(mk_tokenSet_130());
	private static final long[] mk_tokenSet_131() {
		long[] data = new long[18];
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=568297977978814464L;
		return data;
	}
	public static final BitSet _tokenSet_131 = new BitSet(mk_tokenSet_131());
	private static final long[] mk_tokenSet_132() {
		long[] data = new long[28];
		data[0]=35185247256576L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=8067767723070832259L;
		data[8]=4608299209123495679L;
		data[9]=2378505635022241635L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_132 = new BitSet(mk_tokenSet_132());
	private static final long[] mk_tokenSet_133() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-9109565014273L;
		data[9]=2378505635026173795L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_133 = new BitSet(mk_tokenSet_133());
	private static final long[] mk_tokenSet_134() {
		long[] data = new long[20];
		data[0]=35184976068608L;
		data[6]=16384L;
		data[7]=4611686030238552064L;
		data[8]=568297977978814464L;
		data[9]=32L;
		return data;
	}
	public static final BitSet _tokenSet_134 = new BitSet(mk_tokenSet_134());
	private static final long[] mk_tokenSet_135() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-9109565014273L;
		data[9]=2378505635022241635L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_135 = new BitSet(mk_tokenSet_135());
	private static final long[] mk_tokenSet_136() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=568297977978814464L;
		data[9]=32L;
		return data;
	}
	public static final BitSet _tokenSet_136 = new BitSet(mk_tokenSet_136());
	private static final long[] mk_tokenSet_137() {
		long[] data = new long[22];
		data[0]=35189552054274L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=4827858812356530223L;
		data[8]=-8004444631572480L;
		data[9]=134218272L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_137 = new BitSet(mk_tokenSet_137());
	private static final long[] mk_tokenSet_138() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[7]=4096L;
		data[8]=9570149208162304L;
		data[9]=32L;
		return data;
	}
	public static final BitSet _tokenSet_138 = new BitSet(mk_tokenSet_138());
	private static final long[] mk_tokenSet_139() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-3386809285542145L;
		data[9]=2378505635022241635L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_139 = new BitSet(mk_tokenSet_139());
	private static final long[] mk_tokenSet_140() {
		long[] data = new long[20];
		data[7]=4096L;
		data[8]=9570149208162304L;
		data[9]=32L;
		return data;
	}
	public static final BitSet _tokenSet_140 = new BitSet(mk_tokenSet_140());
	private static final long[] mk_tokenSet_141() {
		long[] data = new long[22];
		data[0]=35188948074498L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=33280L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_141 = new BitSet(mk_tokenSet_141());
	private static final long[] mk_tokenSet_142() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331216820L;
		data[7]=216172793929142319L;
		data[8]=-8004444631572480L;
		data[9]=33280L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_142 = new BitSet(mk_tokenSet_142());
	private static final long[] mk_tokenSet_143() {
		long[] data = new long[20];
		data[0]=536870912L;
		data[7]=65536L;
		data[9]=3875274752L;
		return data;
	}
	public static final BitSet _tokenSet_143 = new BitSet(mk_tokenSet_143());
	private static final long[] mk_tokenSet_144() {
		long[] data = new long[20];
		data[5]=281474976710656L;
		data[6]=137438953472L;
		data[7]=4294967296L;
		data[9]=8589934592L;
		return data;
	}
	public static final BitSet _tokenSet_144 = new BitSet(mk_tokenSet_144());
	private static final long[] mk_tokenSet_145() {
		long[] data = new long[22];
		data[0]=4575985666L;
		data[5]=39435084041814016L;
		data[6]=-1158199537967284148L;
		data[7]=216172793929142319L;
		data[8]=-8004161163730944L;
		data[9]=512L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_145 = new BitSet(mk_tokenSet_145());
	private static final long[] mk_tokenSet_146() {
		long[] data = new long[22];
		data[0]=918552578L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=134217728L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_146 = new BitSet(mk_tokenSet_146());
	private static final long[] mk_tokenSet_147() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=134217728L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_147 = new BitSet(mk_tokenSet_147());
	private static final long[] mk_tokenSet_148() {
		long[] data = new long[20];
		data[6]=16384L;
		data[7]=11815358720L;
		data[8]=2288110085680922624L;
		data[9]=2130303778816L;
		return data;
	}
	public static final BitSet _tokenSet_148 = new BitSet(mk_tokenSet_148());
	private static final long[] mk_tokenSet_149() {
		long[] data = new long[28];
		data[0]=35185259839490L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=3456081704643444399L;
		data[8]=-4624080026984448257L;
		data[9]=2378507490448113475L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_149 = new BitSet(mk_tokenSet_149());
	private static final long[] mk_tokenSet_150() {
		long[] data = new long[14];
		data[0]=603979776L;
		data[6]=4194304L;
		return data;
	}
	public static final BitSet _tokenSet_150 = new BitSet(mk_tokenSet_150());
	private static final long[] mk_tokenSet_151() {
		long[] data = new long[20];
		data[5]=17592186044416L;
		data[7]=105553116266496L;
		data[9]=34359738368L;
		return data;
	}
	public static final BitSet _tokenSet_151 = new BitSet(mk_tokenSet_151());
	private static final long[] mk_tokenSet_152() {
		long[] data = new long[28];
		data[0]=271187968L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897865474211846L;
		data[7]=3456222442131799683L;
		data[8]=4544967339497815807L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_152 = new BitSet(mk_tokenSet_152());
	private static final long[] mk_tokenSet_153() {
		long[] data = new long[28];
		data[0]=35187894910978L;
		data[5]=-1013488037042061312L;
		data[6]=-1086324738L;
		data[7]=3456081704643444399L;
		data[8]=4546093239406231295L;
		data[9]=2380757434835926851L;
		data[10]=2305834178746252144L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_153 = new BitSet(mk_tokenSet_153());
	private static final long[] mk_tokenSet_154() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_154 = new BitSet(mk_tokenSet_154());
	private static final long[] mk_tokenSet_155() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897865474211846L;
		data[7]=3456081704643444355L;
		data[8]=4544967339497815807L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_155 = new BitSet(mk_tokenSet_155());
	private static final long[] mk_tokenSet_156() {
		long[] data = new long[14];
		data[0]=271056896L;
		data[6]=4294967296L;
		return data;
	}
	public static final BitSet _tokenSet_156 = new BitSet(mk_tokenSet_156());
	private static final long[] mk_tokenSet_157() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199183569026996L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=2251799813685250L;
		data[10]=2305551654704906336L;
		return data;
	}
	public static final BitSet _tokenSet_157 = new BitSet(mk_tokenSet_157());
	private static final long[] mk_tokenSet_158() {
		long[] data = new long[20];
		data[6]=105294331904L;
		data[8]=134217728L;
		data[9]=4294967296L;
		return data;
	}
	public static final BitSet _tokenSet_158 = new BitSet(mk_tokenSet_158());
	private static final long[] mk_tokenSet_159() {
		long[] data = new long[28];
		data[0]=35187894910978L;
		data[5]=-1013488037042061312L;
		data[6]=-318913904642L;
		data[7]=3456081704643444399L;
		data[8]=4546093239272013567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_159 = new BitSet(mk_tokenSet_159());
	private static final long[] mk_tokenSet_160() {
		long[] data = new long[22];
		data[0]=603979776L;
		data[6]=388762206208L;
		data[9]=2251799813685250L;
		data[10]=2305551654704906240L;
		return data;
	}
	public static final BitSet _tokenSet_160 = new BitSet(mk_tokenSet_160());
	private static final long[] mk_tokenSet_161() {
		long[] data = new long[28];
		data[0]=35188498890754L;
		data[5]=-1013488037042061312L;
		data[6]=-275964493826L;
		data[7]=3456081704643444399L;
		data[8]=4546093239272013567L;
		data[9]=2380757434835926851L;
		data[10]=2234630891318016880L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_161 = new BitSet(mk_tokenSet_161());
	private static final long[] mk_tokenSet_162() {
		long[] data = new long[28];
		data[0]=35188419198976L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881355968518L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_162 = new BitSet(mk_tokenSet_162());
	private static final long[] mk_tokenSet_163() {
		long[] data = new long[28];
		data[0]=52974059134466L;
		data[5]=-1008984437414690816L;
		data[6]=-318905516034L;
		data[7]=3456090500736466671L;
		data[8]=4546102035566493439L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2076226086110230841L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_163 = new BitSet(mk_tokenSet_163());
	private static final long[] mk_tokenSet_164() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=111492678079741952L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=256L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_164 = new BitSet(mk_tokenSet_164());
	private static final long[] mk_tokenSet_165() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39435084041814016L;
		data[6]=-1158199458446966708L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_165 = new BitSet(mk_tokenSet_165());
	private static final long[] mk_tokenSet_166() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39435084041814016L;
		data[6]=-1158199458447490996L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_166 = new BitSet(mk_tokenSet_166());
	private static final long[] mk_tokenSet_167() {
		long[] data = new long[22];
		data[0]=817889282L;
		data[5]=39435084041814016L;
		data[6]=-1158199555151101876L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_167 = new BitSet(mk_tokenSet_167());
	private static final long[] mk_tokenSet_168() {
		long[] data = new long[22];
		data[0]=817889282L;
		data[5]=111492678079741952L;
		data[6]=-1158199555146907572L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=256L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_168 = new BitSet(mk_tokenSet_168());
	private static final long[] mk_tokenSet_169() {
		long[] data = new long[22];
		data[0]=817889282L;
		data[5]=39435084041814016L;
		data[6]=-1158199572331233204L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_169 = new BitSet(mk_tokenSet_169());
	private static final long[] mk_tokenSet_170() {
		long[] data = new long[20];
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=2288110085680922624L;
		data[9]=2130303778816L;
		return data;
	}
	public static final BitSet _tokenSet_170 = new BitSet(mk_tokenSet_170());
	private static final long[] mk_tokenSet_171() {
		long[] data = new long[22];
		data[0]=884998146L;
		data[5]=39716559018524672L;
		data[6]=-1158199572331216820L;
		data[7]=11811164207L;
		data[8]=2288110085682782208L;
		data[9]=2130437996544L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_171 = new BitSet(mk_tokenSet_171());
	private static final long[] mk_tokenSet_172() {
		long[] data = new long[18];
		data[0]=603979776L;
		data[6]=16384L;
		data[7]=11811164160L;
		data[8]=568297977978814464L;
		return data;
	}
	public static final BitSet _tokenSet_172 = new BitSet(mk_tokenSet_172());
	private static final long[] mk_tokenSet_173() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-3386809285542145L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_173 = new BitSet(mk_tokenSet_173());
	private static final long[] mk_tokenSet_174() {
		long[] data = new long[28];
		data[0]=35185247256576L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=8067767723070832259L;
		data[8]=4608299209123495679L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_174 = new BitSet(mk_tokenSet_174());
	private static final long[] mk_tokenSet_175() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-9109565014273L;
		data[9]=2378505635026173763L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_175 = new BitSet(mk_tokenSet_175());
	private static final long[] mk_tokenSet_176() {
		long[] data = new long[18];
		data[0]=35184976068608L;
		data[6]=16384L;
		data[7]=4611686030238552064L;
		data[8]=568297977978814464L;
		return data;
	}
	public static final BitSet _tokenSet_176 = new BitSet(mk_tokenSet_176());
	private static final long[] mk_tokenSet_177() {
		long[] data = new long[28];
		data[0]=35189554806786L;
		data[5]=-1013488037042061312L;
		data[6]=-319032131586L;
		data[7]=8067767723070832303L;
		data[8]=-9109565014273L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115828064L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_177 = new BitSet(mk_tokenSet_177());
	private static final long[] mk_tokenSet_178() {
		long[] data = new long[20];
		data[6]=8388608L;
		data[8]=16777216L;
		data[9]=2199023255552L;
		return data;
	}
	public static final BitSet _tokenSet_178 = new BitSet(mk_tokenSet_178());
	private static final long[] mk_tokenSet_179() {
		long[] data = new long[22];
		data[0]=38935724034L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_179 = new BitSet(mk_tokenSet_179());
	private static final long[] mk_tokenSet_180() {
		long[] data = new long[18];
		data[0]=268435456L;
		data[8]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_180 = new BitSet(mk_tokenSet_180());
	private static final long[] mk_tokenSet_181() {
		long[] data = new long[14];
		data[0]=8388608L;
		return data;
	}
	public static final BitSet _tokenSet_181 = new BitSet(mk_tokenSet_181());
	private static final long[] mk_tokenSet_182() {
		long[] data = new long[20];
		data[5]=39406496739491840L;
		data[9]=2323927776467353600L;
		return data;
	}
	public static final BitSet _tokenSet_182 = new BitSet(mk_tokenSet_182());
	private static final long[] mk_tokenSet_183() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211606596288518L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=3425592548385881923L;
		data[10]=2305834178746252032L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_183 = new BitSet(mk_tokenSet_183());
	private static final long[] mk_tokenSet_184() {
		long[] data = new long[22];
		data[0]=310378496L;
		data[6]=8388640L;
		data[8]=34376515584L;
		data[9]=2199023255552L;
		data[10]=281820721577984L;
		return data;
	}
	public static final BitSet _tokenSet_184 = new BitSet(mk_tokenSet_184());
	private static final long[] mk_tokenSet_185() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=3533678939442773827L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535163L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_185 = new BitSet(mk_tokenSet_185());
	private static final long[] mk_tokenSet_186() {
		long[] data = new long[28];
		data[0]=2254033509810176L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897624956567558L;
		data[7]=3456363179620155011L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_186 = new BitSet(mk_tokenSet_186());
	private static final long[] mk_tokenSet_187() {
		long[] data = new long[28];
		data[0]=29273397580660736L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456363179620155015L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_187 = new BitSet(mk_tokenSet_187());
	private static final long[] mk_tokenSet_188() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=3533678939442773827L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_188 = new BitSet(mk_tokenSet_188());
	private static final long[] mk_tokenSet_189() {
		long[] data = new long[28];
		data[0]=27023831460347904L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897624956567558L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_189 = new BitSet(mk_tokenSet_189());
	private static final long[] mk_tokenSet_190() {
		long[] data = new long[14];
		data[0]=27021597764222976L;
		return data;
	}
	public static final BitSet _tokenSet_190 = new BitSet(mk_tokenSet_190());
	private static final long[] mk_tokenSet_191() {
		long[] data = new long[28];
		data[0]=63050394785939456L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_191 = new BitSet(mk_tokenSet_191());
	private static final long[] mk_tokenSet_192() {
		long[] data = new long[18];
		data[0]=301989888L;
		data[6]=8388608L;
		data[8]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_192 = new BitSet(mk_tokenSet_192());
	private static final long[] mk_tokenSet_193() {
		long[] data = new long[28];
		data[0]=2254033509810176L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897624956567558L;
		data[7]=3456363179620155015L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_193 = new BitSet(mk_tokenSet_193());
	private static final long[] mk_tokenSet_194() {
		long[] data = new long[16];
		data[7]=281474976710656L;
		return data;
	}
	public static final BitSet _tokenSet_194 = new BitSet(mk_tokenSet_194());
	private static final long[] mk_tokenSet_195() {
		long[] data = new long[28];
		data[0]=2252074979557376L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269058007046L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_195 = new BitSet(mk_tokenSet_195());
	private static final long[] mk_tokenSet_196() {
		long[] data = new long[24];
		data[0]=27021597764222976L;
		data[7]=281474976710656L;
		data[8]=67108864L;
		data[10]=4194304L;
		data[11]=140737488355330L;
		return data;
	}
	public static final BitSet _tokenSet_196 = new BitSet(mk_tokenSet_196());
	private static final long[] mk_tokenSet_197() {
		long[] data = new long[22];
		data[0]=268435456L;
		data[6]=8388640L;
		data[10]=281820721577984L;
		return data;
	}
	public static final BitSet _tokenSet_197 = new BitSet(mk_tokenSet_197());
	private static final long[] mk_tokenSet_198() {
		long[] data = new long[28];
		data[0]=35185314365440L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625015287814L;
		data[7]=3456222442131799683L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2305842974822497024L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_198 = new BitSet(mk_tokenSet_198());
	private static final long[] mk_tokenSet_199() {
		long[] data = new long[22];
		data[0]=34670116864L;
		data[6]=67108896L;
		data[10]=281820721577984L;
		return data;
	}
	public static final BitSet _tokenSet_199 = new BitSet(mk_tokenSet_199());
	private static final long[] mk_tokenSet_200() {
		long[] data = new long[28];
		data[0]=29308582900662272L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899833950214L;
		data[7]=3456503917108510343L;
		data[8]=4544967339363598079L;
		data[9]=2398982939577941827L;
		data[10]=2162582091208850176L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_200 = new BitSet(mk_tokenSet_200());
	private static final long[] mk_tokenSet_201() {
		long[] data = new long[28];
		data[0]=29275596612304896L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456363179620155015L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_201 = new BitSet(mk_tokenSet_201());
	private static final long[] mk_tokenSet_202() {
		long[] data = new long[28];
		data[0]=29308761730056192L;
		data[5]=-1013514425321127936L;
		data[6]=-2414211881356230662L;
		data[7]=3456363179620155015L;
		data[8]=4546093239337549567L;
		data[9]=2398842202089586499L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_202 = new BitSet(mk_tokenSet_202());
	private static final long[] mk_tokenSet_203() {
		long[] data = new long[28];
		data[0]=29273397589049344L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456363179620155015L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_203 = new BitSet(mk_tokenSet_203());
	private static final long[] mk_tokenSet_204() {
		long[] data = new long[28];
		data[0]=65337657399050240L;
		data[5]=-1013505629228105728L;
		data[6]=-2413929994062659590L;
		data[7]=8068049198047542927L;
		data[8]=4546093273631751935L;
		data[9]=-15087198180999357L;
		data[10]=2305834178754641919L;
		data[11]=2076226086106806587L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_204 = new BitSet(mk_tokenSet_204());
	private static final long[] mk_tokenSet_205() {
		long[] data = new long[28];
		data[0]=69861241647734274L;
		data[5]=-1008949253042601984L;
		data[6]=-35437936641L;
		data[7]=8068198731628920559L;
		data[8]=4546102069943009023L;
		data[9]=-15087198180999357L;
		data[10]=2305834178754641919L;
		data[11]=2085306952644032827L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_205 = new BitSet(mk_tokenSet_205());
	private static final long[] mk_tokenSet_206() {
		long[] data = new long[28];
		data[0]=33794821267791362L;
		data[5]=-1013488037042061312L;
		data[6]=-318956371970L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2162573295122119520L;
		data[11]=-4828071437409466055L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_206 = new BitSet(mk_tokenSet_206());
	private static final long[] mk_tokenSet_207() {
		long[] data = new long[24];
		data[0]=8388608L;
		data[6]=8388608L;
		data[8]=16777216L;
		data[11]=8L;
		return data;
	}
	public static final BitSet _tokenSet_207 = new BitSet(mk_tokenSet_207());
	private static final long[] mk_tokenSet_208() {
		long[] data = new long[28];
		data[0]=56330458144309248L;
		data[5]=-1013505629228105728L;
		data[6]=-2413929994062659590L;
		data[7]=8068049198047542927L;
		data[8]=4546093273631751935L;
		data[9]=-15087198180999357L;
		data[10]=2305834178754641919L;
		data[11]=2076226086106806587L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_208 = new BitSet(mk_tokenSet_208());
	private static final long[] mk_tokenSet_209() {
		long[] data = new long[18];
		data[0]=34628173824L;
		data[6]=8388608L;
		data[8]=34359738368L;
		return data;
	}
	public static final BitSet _tokenSet_209 = new BitSet(mk_tokenSet_209());
	private static final long[] mk_tokenSet_210() {
		long[] data = new long[28];
		data[0]=35364210868224L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239488544511L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2087488383710132537L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_210 = new BitSet(mk_tokenSet_210());
	private static final long[] mk_tokenSet_211() {
		long[] data = new long[22];
		data[0]=817889282L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_211 = new BitSet(mk_tokenSet_211());
	private static final long[] mk_tokenSet_212() {
		long[] data = new long[28];
		data[0]=287965184L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269058007046L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_212 = new BitSet(mk_tokenSet_212());
	private static final long[] mk_tokenSet_213() {
		long[] data = new long[28];
		data[0]=29273672743780352L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269058007046L;
		data[7]=8068049198047542927L;
		data[8]=4544967373792018175L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295136800767L;
		data[11]=2076366804787890491L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_213 = new BitSet(mk_tokenSet_213());
	private static final long[] mk_tokenSet_214() {
		long[] data = new long[20];
		data[0]=2621440L;
		data[7]=4611686018427387904L;
		data[9]=-4611686018427387904L;
		return data;
	}
	public static final BitSet _tokenSet_214 = new BitSet(mk_tokenSet_214());
	private static final long[] mk_tokenSet_215() {
		long[] data = new long[28];
		data[0]=2287262573920256L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930268940566534L;
		data[7]=8067767723070832271L;
		data[8]=4546093273631751935L;
		data[9]=-2230928583591461053L;
		data[10]=2234630889170534399L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_215 = new BitSet(mk_tokenSet_215());
	private static final long[] mk_tokenSet_216() {
		long[] data = new long[28];
		data[0]=2307247232982528L;
		data[5]=-1008966845228646400L;
		data[6]=-2413930260342243329L;
		data[7]=8068198731628920527L;
		data[8]=4546102069926231807L;
		data[9]=-2212843816337801405L;
		data[10]=2234630889170534399L;
		data[11]=2085306952644032827L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_216 = new BitSet(mk_tokenSet_216());
	private static final long[] mk_tokenSet_217() {
		long[] data = new long[22];
		data[0]=268435456L;
		data[6]=3377699720527872L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_217 = new BitSet(mk_tokenSet_217());
	private static final long[] mk_tokenSet_218() {
		long[] data = new long[28];
		data[0]=35188024999936L;
		data[5]=-1012379729321263104L;
		data[6]=-2414211872833404934L;
		data[7]=3456222442131799683L;
		data[8]=4546093239406231295L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2085306952640608569L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_218 = new BitSet(mk_tokenSet_218());
	private static final long[] mk_tokenSet_219() {
		long[] data = new long[28];
		data[0]=29309036675137536L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930268936372230L;
		data[7]=8068189935535898255L;
		data[8]=4546093273849855743L;
		data[9]=-2230928583591461053L;
		data[10]=2234630889174728703L;
		data[11]=2087629121198487867L;
		data[12]=-938270L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_219 = new BitSet(mk_tokenSet_219());
	private static final long[] mk_tokenSet_220() {
		long[] data = new long[16];
		data[0]=276824064L;
		data[6]=262144L;
		data[7]=4611686018427387904L;
		return data;
	}
	public static final BitSet _tokenSet_220 = new BitSet(mk_tokenSet_220());
	private static final long[] mk_tokenSet_221() {
		long[] data = new long[20];
		data[0]=1099520016384L;
		data[7]=4611686018427387904L;
		data[8]=67108864L;
		data[9]=-4611686018427387904L;
		return data;
	}
	public static final BitSet _tokenSet_221 = new BitSet(mk_tokenSet_221());
	private static final long[] mk_tokenSet_222() {
		long[] data = new long[22];
		data[0]=271056896L;
		data[10]=32L;
		return data;
	}
	public static final BitSet _tokenSet_222 = new BitSet(mk_tokenSet_222());
	private static final long[] mk_tokenSet_223() {
		long[] data = new long[28];
		data[0]=313131008L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901321222L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2087485066367961401L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_223 = new BitSet(mk_tokenSet_223());
	private static final long[] mk_tokenSet_224() {
		long[] data = new long[28];
		data[0]=29308860916957184L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930268940304390L;
		data[7]=8068049198047542927L;
		data[8]=4546102069791883007L;
		data[9]=-2230928583591461053L;
		data[10]=4540473898388422655L;
		data[11]=2087625822663588155L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_224 = new BitSet(mk_tokenSet_224());
	private static final long[] mk_tokenSet_225() {
		long[] data = new long[28];
		data[0]=35364152147968L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881355968518L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2087485085175249209L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_225 = new BitSet(mk_tokenSet_225());
	private static final long[] mk_tokenSet_226() {
		long[] data = new long[28];
		data[0]=29326646510755328L;
		data[5]=-1016883328948633600L;
		data[6]=-2413929994054008838L;
		data[7]=8068198731628920527L;
		data[8]=4546102069926231807L;
		data[9]=-2230928583591461053L;
		data[10]=4611677187964141567L;
		data[11]=2087626922178640187L;
		data[12]=-971012L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_226 = new BitSet(mk_tokenSet_226());
	private static final long[] mk_tokenSet_227() {
		long[] data = new long[28];
		data[0]=35364152147968L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881355968518L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_227 = new BitSet(mk_tokenSet_227());
	private static final long[] mk_tokenSet_228() {
		long[] data = new long[28];
		data[0]=29326646510755328L;
		data[5]=-1016883328948633600L;
		data[6]=-2413929994054008838L;
		data[7]=8068198731628920527L;
		data[8]=4546102069926231807L;
		data[9]=-2230928583591461053L;
		data[10]=2305834178750447615L;
		data[11]=2076367923110213947L;
		data[12]=-971012L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_228 = new BitSet(mk_tokenSet_228());
	private static final long[] mk_tokenSet_229() {
		long[] data = new long[18];
		data[0]=310378496L;
		data[6]=8388608L;
		data[8]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_229 = new BitSet(mk_tokenSet_229());
	private static final long[] mk_tokenSet_230() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444359L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_230 = new BitSet(mk_tokenSet_230());
	private static final long[] mk_tokenSet_231() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2162573295132605184L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_231 = new BitSet(mk_tokenSet_231());
	private static final long[] mk_tokenSet_232() {
		long[] data = new long[28];
		data[0]=33836602761027074L;
		data[5]=-1008984437414690816L;
		data[6]=-275955843074L;
		data[7]=-2392537570492689L;
		data[8]=4546102071419272959L;
		data[9]=-2212843816337801405L;
		data[10]=2270659686195789823L;
		data[11]=-4828071418602193927L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_232 = new BitSet(mk_tokenSet_232());
	private static final long[] mk_tokenSet_233() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=9223363206370556672L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_233 = new BitSet(mk_tokenSet_233());
	private static final long[] mk_tokenSet_234() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=6917520197156862720L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_234 = new BitSet(mk_tokenSet_234());
	private static final long[] mk_tokenSet_235() {
		long[] data = new long[22];
		data[0]=2234288963584L;
		data[6]=32800L;
		data[10]=36310617740541952L;
		return data;
	}
	public static final BitSet _tokenSet_235 = new BitSet(mk_tokenSet_235());
	private static final long[] mk_tokenSet_236() {
		long[] data = new long[28];
		data[0]=37387652169728L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211743975997446L;
		data[7]=3456222442131799683L;
		data[8]=4546093273646956287L;
		data[9]=2380898172324282179L;
		data[10]=2270668482282519312L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_236 = new BitSet(mk_tokenSet_236());
	private static final long[] mk_tokenSet_237() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897625023676422L;
		data[7]=3456081704643444355L;
		data[8]=4544976135456620287L;
		data[9]=2380757434835926851L;
		data[10]=9223363206370556672L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_237 = new BitSet(mk_tokenSet_237());
	private static final long[] mk_tokenSet_238() {
		long[] data = new long[24];
		data[0]=2199635623936L;
		data[6]=8683520L;
		data[8]=16777216L;
		data[11]=456L;
		return data;
	}
	public static final BitSet _tokenSet_238 = new BitSet(mk_tokenSet_238());
	private static final long[] mk_tokenSet_239() {
		long[] data = new long[24];
		data[0]=8388608L;
		data[6]=8650752L;
		data[8]=16777216L;
		data[10]=36028797018963968L;
		data[11]=456L;
		return data;
	}
	public static final BitSet _tokenSet_239 = new BitSet(mk_tokenSet_239());
	private static final long[] mk_tokenSet_240() {
		long[] data = new long[24];
		data[0]=8388608L;
		data[7]=64L;
		data[10]=2097152L;
		data[11]=2L;
		return data;
	}
	public static final BitSet _tokenSet_240 = new BitSet(mk_tokenSet_240());
	private static final long[] mk_tokenSet_241() {
		long[] data = new long[24];
		data[0]=41943040L;
		data[7]=64L;
		data[10]=2097152L;
		data[11]=2L;
		return data;
	}
	public static final BitSet _tokenSet_241 = new BitSet(mk_tokenSet_241());
	private static final long[] mk_tokenSet_242() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211606596288518L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2957218187139350339L;
		data[10]=2305834178754640640L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_242 = new BitSet(mk_tokenSet_242());
	private static final long[] mk_tokenSet_243() {
		long[] data = new long[28];
		data[0]=2233696124928L;
		data[5]=-1013514425321127936L;
		data[6]=-7025897624956567558L;
		data[7]=3456081704643444359L;
		data[8]=4544967339363598079L;
		data[9]=2398842202089586499L;
		data[10]=2305834178729474816L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_243 = new BitSet(mk_tokenSet_243());
	private static final long[] mk_tokenSet_244() {
		long[] data = new long[16];
		data[7]=4L;
		return data;
	}
	public static final BitSet _tokenSet_244 = new BitSet(mk_tokenSet_244());
	private static final long[] mk_tokenSet_245() {
		long[] data = new long[18];
		data[7]=281474976710656L;
		data[8]=67108864L;
		return data;
	}
	public static final BitSet _tokenSet_245 = new BitSet(mk_tokenSet_245());
	private static final long[] mk_tokenSet_246() {
		long[] data = new long[28];
		data[0]=35184391618560L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881474195462L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2162573295132605184L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_246 = new BitSet(mk_tokenSet_246());
	private static final long[] mk_tokenSet_247() {
		long[] data = new long[28];
		data[0]=69024219136L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2378505635022241603L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_247 = new BitSet(mk_tokenSet_247());
	private static final long[] mk_tokenSet_248() {
		long[] data = new long[28];
		data[0]=287965184L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269058007046L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2076226067299535673L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_248 = new BitSet(mk_tokenSet_248());
	private static final long[] mk_tokenSet_249() {
		long[] data = new long[18];
		data[8]=8796093022208L;
		return data;
	}
	public static final BitSet _tokenSet_249 = new BitSet(mk_tokenSet_249());
	private static final long[] mk_tokenSet_250() {
		long[] data = new long[28];
		data[0]=69007441920L;
		data[5]=-1016883328948633600L;
		data[6]=-2413930269058007046L;
		data[7]=8067767723070832271L;
		data[8]=4544967373724909311L;
		data[9]=-2230928583591461053L;
		data[10]=2162573295132606463L;
		data[11]=2076226067299535673L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_250 = new BitSet(mk_tokenSet_250());
	private static final long[] mk_tokenSet_251() {
		long[] data = new long[28];
		data[0]=35432863236096L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_251 = new BitSet(mk_tokenSet_251());
	private static final long[] mk_tokenSet_252() {
		long[] data = new long[28];
		data[0]=53042766028288L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211606469935110L;
		data[7]=3456231238224821955L;
		data[8]=4546102035564920575L;
		data[9]=2380757434835926851L;
		data[10]=2305834178746252080L;
		data[11]=2076227185621858617L;
		data[12]=-971012L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_252 = new BitSet(mk_tokenSet_252());
	private static final long[] mk_tokenSet_253() {
		long[] data = new long[20];
		data[9]=4611686018427387904L;
		return data;
	}
	public static final BitSet _tokenSet_253 = new BitSet(mk_tokenSet_253());
	private static final long[] mk_tokenSet_254() {
		long[] data = new long[24];
		data[0]=276824064L;
		data[6]=262144L;
		data[7]=4611686018427387904L;
		data[9]=4611686018427387904L;
		data[11]=11258999068426240L;
		return data;
	}
	public static final BitSet _tokenSet_254 = new BitSet(mk_tokenSet_254());
	private static final long[] mk_tokenSet_255() {
		long[] data = new long[28];
		data[0]=33794855661084162L;
		data[5]=-1013488037042061312L;
		data[6]=-318956371970L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2162573297269603168L;
		data[11]=-4828071437409482439L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_255 = new BitSet(mk_tokenSet_255());
	private static final long[] mk_tokenSet_256() {
		long[] data = new long[28];
		data[0]=33794821267791362L;
		data[5]=-1013488037042061312L;
		data[6]=-318956371970L;
		data[7]=-2392537570492689L;
		data[8]=4544976171512430335L;
		data[9]=7010528220516974403L;
		data[10]=2162573329481857888L;
		data[11]=-4828071437409466055L;
		data[12]=-938109L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_256 = new BitSet(mk_tokenSet_256());
	private static final long[] mk_tokenSet_257() {
		long[] data = new long[22];
		data[0]=817889282L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_257 = new BitSet(mk_tokenSet_257());
	private static final long[] mk_tokenSet_258() {
		long[] data = new long[28];
		data[0]=4503604807335938L;
		data[5]=39435084041814016L;
		data[6]=-1158199555151101844L;
		data[7]=-3458755717190647697L;
		data[8]=34445484032L;
		data[9]=4611686018427387904L;
		data[10]=1075839072L;
		data[11]=-5186176445893836800L;
		data[12]=281354986094595L;
		data[13]=4128L;
		return data;
	}
	public static final BitSet _tokenSet_258 = new BitSet(mk_tokenSet_258());
	private static final long[] mk_tokenSet_259() {
		long[] data = new long[28];
		data[0]=4503604807335938L;
		data[5]=39435084041814016L;
		data[6]=-1158199555151101844L;
		data[7]=-3458764513283669905L;
		data[8]=34445484032L;
		data[9]=4611686018427387904L;
		data[10]=1075839072L;
		data[11]=-5186176445893836800L;
		data[12]=281354986094595L;
		data[13]=4128L;
		return data;
	}
	public static final BitSet _tokenSet_259 = new BitSet(mk_tokenSet_259());
	private static final long[] mk_tokenSet_260() {
		long[] data = new long[28];
		data[0]=35364747739136L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_260 = new BitSet(mk_tokenSet_260());
	private static final long[] mk_tokenSet_261() {
		long[] data = new long[28];
		data[0]=4556573753613826L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2674012547203345L;
		data[8]=4546102069808660223L;
		data[9]=6992443453263314755L;
		data[10]=2234630889172630384L;
		data[11]=-4839332616693876423L;
		data[12]=-938269L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_261 = new BitSet(mk_tokenSet_261());
	private static final long[] mk_tokenSet_262() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546102035363462911L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_262 = new BitSet(mk_tokenSet_262());
	private static final long[] mk_tokenSet_263() {
		long[] data = new long[28];
		data[0]=4538964387692546L;
		data[5]=-1013488037042061312L;
		data[6]=-318913904642L;
		data[7]=-2674012547203345L;
		data[8]=4546093273715638015L;
		data[9]=6992443453263314755L;
		data[10]=2234630889172630384L;
		data[11]=-4839332616697284295L;
		data[12]=-938269L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_263 = new BitSet(mk_tokenSet_263());
	private static final long[] mk_tokenSet_264() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086107068729L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_264 = new BitSet(mk_tokenSet_264());
	private static final long[] mk_tokenSet_265() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086107855161L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_265 = new BitSet(mk_tokenSet_265());
	private static final long[] mk_tokenSet_266() {
		long[] data = new long[28];
		data[0]=35364143759360L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456090500736466563L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_266 = new BitSet(mk_tokenSet_266());
	private static final long[] mk_tokenSet_267() {
		long[] data = new long[28];
		data[0]=33830040017444354L;
		data[5]=-1013488037042061312L;
		data[6]=-318901321730L;
		data[7]=-2392537570492689L;
		data[8]=-4677269965435502849L;
		data[9]=7010528220516974407L;
		data[10]=2234630889176824688L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_267 = new BitSet(mk_tokenSet_267());
	private static final long[] mk_tokenSet_268() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076227185618434361L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_268 = new BitSet(mk_tokenSet_268());
	private static final long[] mk_tokenSet_269() {
		long[] data = new long[28];
		data[0]=35364680630272L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239337549567L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086106822969L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_269 = new BitSet(mk_tokenSet_269());
	private static final long[] mk_tokenSet_270() {
		long[] data = new long[28];
		data[0]=33830040050998786L;
		data[5]=-1013488037042061312L;
		data[6]=-44027609090L;
		data[7]=-2251800082137361L;
		data[8]=4546102071553621759L;
		data[9]=7010528220516974403L;
		data[10]=2305834178752543600L;
		data[11]=-4828070319090566855L;
		data[12]=-937985L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_270 = new BitSet(mk_tokenSet_270());
	private static final long[] mk_tokenSet_271() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339363598079L;
		data[9]=2380757434835926851L;
		data[10]=2234630889153755904L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_271 = new BitSet(mk_tokenSet_271());
	private static final long[] mk_tokenSet_272() {
		long[] data = new long[24];
		data[0]=18052284416L;
		data[11]=9007199254740992L;
		return data;
	}
	public static final BitSet _tokenSet_272 = new BitSet(mk_tokenSet_272());
	private static final long[] mk_tokenSet_273() {
		long[] data = new long[16];
		data[7]=1152921504606846976L;
		return data;
	}
	public static final BitSet _tokenSet_273 = new BitSet(mk_tokenSet_273());
	private static final long[] mk_tokenSet_274() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=4611686018427387951L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_274 = new BitSet(mk_tokenSet_274());
	private static final long[] mk_tokenSet_275() {
		long[] data = new long[24];
		data[0]=603979776L;
		data[11]=11261198091681792L;
		return data;
	}
	public static final BitSet _tokenSet_275 = new BitSet(mk_tokenSet_275());
	private static final long[] mk_tokenSet_276() {
		long[] data = new long[24];
		data[11]=2199023255552L;
		return data;
	}
	public static final BitSet _tokenSet_276 = new BitSet(mk_tokenSet_276());
	private static final long[] mk_tokenSet_277() {
		long[] data = new long[24];
		data[7]=281474976710656L;
		data[10]=4194304L;
		data[11]=140737488355328L;
		return data;
	}
	public static final BitSet _tokenSet_277 = new BitSet(mk_tokenSet_277());
	private static final long[] mk_tokenSet_278() {
		long[] data = new long[28];
		data[0]=35364223451138L;
		data[5]=-1013488037042061312L;
		data[6]=-318914166786L;
		data[7]=3456081704643444399L;
		data[8]=4546093273849855743L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533232L;
		data[11]=2087488383710132537L;
		data[12]=-938272L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_278 = new BitSet(mk_tokenSet_278());
	private static final long[] mk_tokenSet_279() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897762462629894L;
		data[7]=3456081704643444355L;
		data[8]=4544967339380375295L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_279 = new BitSet(mk_tokenSet_279());
	private static final long[] mk_tokenSet_280() {
		long[] data = new long[22];
		data[0]=282066946L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=1859584L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_280 = new BitSet(mk_tokenSet_280());
	private static final long[] mk_tokenSet_281() {
		long[] data = new long[14];
		data[0]=67108864L;
		data[6]=4194304L;
		return data;
	}
	public static final BitSet _tokenSet_281 = new BitSet(mk_tokenSet_281());
	private static final long[] mk_tokenSet_282() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=5764607523571105839L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_282 = new BitSet(mk_tokenSet_282());
	private static final long[] mk_tokenSet_283() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=47L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_283 = new BitSet(mk_tokenSet_283());
	private static final long[] mk_tokenSet_284() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881423339526L;
		data[7]=3456081704643444355L;
		data[8]=4546093239270440703L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533136L;
		data[11]=2076226086106806585L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_284 = new BitSet(mk_tokenSet_284());
	private static final long[] mk_tokenSet_285() {
		long[] data = new long[28];
		data[0]=35364210868224L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881356230662L;
		data[7]=3456081704643444355L;
		data[8]=4546093239354326783L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2087487284198504761L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_285 = new BitSet(mk_tokenSet_285());
	private static final long[] mk_tokenSet_286() {
		long[] data = new long[28];
		data[0]=52974113725952L;
		data[5]=-1016856940669566976L;
		data[6]=-2414211606469935110L;
		data[7]=3456231238224821955L;
		data[8]=4546102035581697791L;
		data[9]=2380757434835926851L;
		data[10]=2305834178746252080L;
		data[11]=2087488383713540409L;
		data[12]=-971012L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_286 = new BitSet(mk_tokenSet_286());
	private static final long[] mk_tokenSet_287() {
		long[] data = new long[24];
		data[11]=11261198091681792L;
		return data;
	}
	public static final BitSet _tokenSet_287 = new BitSet(mk_tokenSet_287());
	private static final long[] mk_tokenSet_288() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=-3458764513283669969L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32770L;
		return data;
	}
	public static final BitSet _tokenSet_288 = new BitSet(mk_tokenSet_288());
	private static final long[] mk_tokenSet_289() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=-3458764513283669969L;
		data[8]=34428706816L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_289 = new BitSet(mk_tokenSet_289());
	private static final long[] mk_tokenSet_290() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=5764607523034234927L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_290 = new BitSet(mk_tokenSet_290());
	private static final long[] mk_tokenSet_291() {
		long[] data = new long[24];
		data[0]=8388608L;
		data[6]=262144L;
		data[11]=2199023255552L;
		return data;
	}
	public static final BitSet _tokenSet_291 = new BitSet(mk_tokenSet_291());
	private static final long[] mk_tokenSet_292() {
		long[] data = new long[24];
		data[0]=67108864L;
		data[11]=11261198091681792L;
		return data;
	}
	public static final BitSet _tokenSet_292 = new BitSet(mk_tokenSet_292());
	private static final long[] mk_tokenSet_293() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[5]=-1016892125041655808L;
		data[6]=-7025897899901583366L;
		data[7]=3456081704643444355L;
		data[8]=4544967339380375295L;
		data[9]=2380757434835926851L;
		data[10]=2162573295115827968L;
		data[11]=2076226067299535161L;
		data[12]=-971136L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_293 = new BitSet(mk_tokenSet_293());
	private static final long[] mk_tokenSet_294() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572729429908L;
		data[7]=-3458764513283669969L;
		data[8]=-9223363206400155648L;
		data[9]=4611686018427387908L;
		data[10]=96L;
		data[11]=-5174915247802155008L;
		data[12]=32771L;
		return data;
	}
	public static final BitSet _tokenSet_294 = new BitSet(mk_tokenSet_294());
	private static final long[] mk_tokenSet_295() {
		long[] data = new long[28];
		data[0]=5182717954L;
		data[5]=-1013488037042061312L;
		data[6]=-319027675138L;
		data[7]=-2682808640225617L;
		data[8]=-4678395867036844289L;
		data[9]=6992443453263314755L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_295 = new BitSet(mk_tokenSet_295());
	private static final long[] mk_tokenSet_296() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572729429908L;
		data[7]=-3458764513283669969L;
		data[8]=-9223363206400155648L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=-5186176445893836800L;
		data[12]=32771L;
		return data;
	}
	public static final BitSet _tokenSet_296 = new BitSet(mk_tokenSet_296());
	private static final long[] mk_tokenSet_297() {
		long[] data = new long[28];
		data[0]=5216272386L;
		data[5]=-1013488037042061312L;
		data[6]=-319027675138L;
		data[7]=-2682808640225617L;
		data[8]=-4678395867020067073L;
		data[9]=6992443453263314755L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_297 = new BitSet(mk_tokenSet_297());
	private static final long[] mk_tokenSet_298() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=-3458764513283669969L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=-5186176445893836800L;
		data[12]=32771L;
		return data;
	}
	public static final BitSet _tokenSet_298 = new BitSet(mk_tokenSet_298());
	private static final long[] mk_tokenSet_299() {
		long[] data = new long[28];
		data[0]=52974113660416L;
		data[5]=-1016892125041655808L;
		data[6]=-2414211881347842054L;
		data[7]=3456090500736466627L;
		data[8]=4546102035447480063L;
		data[9]=2380757434835926851L;
		data[10]=2234630889170533168L;
		data[11]=2076226086110230841L;
		data[12]=-971040L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_299 = new BitSet(mk_tokenSet_299());
	private static final long[] mk_tokenSet_300() {
		long[] data = new long[14];
		data[0]=4398046511104L;
		return data;
	}
	public static final BitSet _tokenSet_300 = new BitSet(mk_tokenSet_300());
	private static final long[] mk_tokenSet_301() {
		long[] data = new long[28];
		data[0]=5182717954L;
		data[5]=-1013488037042061312L;
		data[6]=-319027675138L;
		data[7]=-2682808640225617L;
		data[8]=-4678395867020067073L;
		data[9]=6992443453263314755L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_301 = new BitSet(mk_tokenSet_301());
	private static final long[] mk_tokenSet_302() {
		long[] data = new long[28];
		data[0]=5182717954L;
		data[5]=-1013488037042061312L;
		data[6]=-319027675138L;
		data[7]=-2682808640225617L;
		data[8]=-4678395867036844289L;
		data[9]=6992443453263314759L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_302 = new BitSet(mk_tokenSet_302());
	private static final long[] mk_tokenSet_303() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572729429908L;
		data[7]=-3458764513283669969L;
		data[8]=-9223363206400155648L;
		data[9]=4611686018427387908L;
		data[10]=96L;
		data[11]=-5186176445893836800L;
		data[12]=32771L;
		return data;
	}
	public static final BitSet _tokenSet_303 = new BitSet(mk_tokenSet_303());
	private static final long[] mk_tokenSet_304() {
		long[] data = new long[28];
		data[0]=5182717954L;
		data[5]=-1013488037042061312L;
		data[6]=-319027675138L;
		data[7]=-2682808640225617L;
		data[8]=-4678395867020067073L;
		data[9]=6992443453263314759L;
		data[10]=2162573295115828064L;
		data[11]=-4839332635504572103L;
		data[12]=-938365L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_304 = new BitSet(mk_tokenSet_304());
	private static final long[] mk_tokenSet_305() {
		long[] data = new long[22];
		data[0]=805306368L;
		data[6]=32L;
		data[10]=281820721577984L;
		return data;
	}
	public static final BitSet _tokenSet_305 = new BitSet(mk_tokenSet_305());
	private static final long[] mk_tokenSet_306() {
		long[] data = new long[28];
		data[0]=33830005657705986L;
		data[5]=-1013488037042061312L;
		data[6]=-318905516034L;
		data[7]=-2251800082137361L;
		data[8]=4546102071419272959L;
		data[9]=7010528220516974403L;
		data[10]=2234639685269846896L;
		data[11]=-4828071418602194631L;
		data[12]=-938013L;
		data[13]=524287L;
		return data;
	}
	public static final BitSet _tokenSet_306 = new BitSet(mk_tokenSet_306());
	private static final long[] mk_tokenSet_307() {
		long[] data = new long[26];
		data[0]=5112856578L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=-3458764513283669969L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_307 = new BitSet(mk_tokenSet_307());
	private static final long[] mk_tokenSet_308() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733624212L;
		data[7]=4611686018427387951L;
		data[8]=34361597952L;
		data[9]=4611686018427387904L;
		data[10]=96L;
		data[11]=1970324836974592L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_308 = new BitSet(mk_tokenSet_308());
	private static final long[] mk_tokenSet_309() {
		long[] data = new long[24];
		data[0]=536870912L;
		data[11]=9007199254740992L;
		return data;
	}
	public static final BitSet _tokenSet_309 = new BitSet(mk_tokenSet_309());
	private static final long[] mk_tokenSet_310() {
		long[] data = new long[26];
		data[0]=348127234L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=34361597952L;
		data[9]=65536L;
		data[10]=96L;
		data[12]=32768L;
		return data;
	}
	public static final BitSet _tokenSet_310 = new BitSet(mk_tokenSet_310());
	private static final long[] mk_tokenSet_311() {
		long[] data = new long[22];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=68968448L;
		data[10]=96L;
		return data;
	}
	public static final BitSet _tokenSet_311 = new BitSet(mk_tokenSet_311());
	private static final long[] mk_tokenSet_312() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[5]=39435084041814016L;
		data[6]=-1158199572733886388L;
		data[7]=47L;
		data[8]=68968448L;
		data[10]=96L;
		data[12]=2L;
		return data;
	}
	public static final BitSet _tokenSet_312 = new BitSet(mk_tokenSet_312());
	private static final long[] mk_tokenSet_313() {
		long[] data = new long[26];
		data[12]=131072L;
		return data;
	}
	public static final BitSet _tokenSet_313 = new BitSet(mk_tokenSet_313());
	private static final long[] mk_tokenSet_314() {
		long[] data = new long[28];
		data[13]=4L;
		return data;
	}
	public static final BitSet _tokenSet_314 = new BitSet(mk_tokenSet_314());
	private static final long[] mk_tokenSet_315() {
		long[] data = new long[14];
		data[0]=621805568L;
		return data;
	}
	public static final BitSet _tokenSet_315 = new BitSet(mk_tokenSet_315());
	private static final long[] mk_tokenSet_316() {
		long[] data = new long[14];
		data[0]=607649792L;
		return data;
	}
	public static final BitSet _tokenSet_316 = new BitSet(mk_tokenSet_316());
	private static final long[] mk_tokenSet_317() {
		long[] data = new long[28];
		data[12]=16777216L;
		data[13]=4L;
		return data;
	}
	public static final BitSet _tokenSet_317 = new BitSet(mk_tokenSet_317());
	private static final long[] mk_tokenSet_318() {
		long[] data = new long[28];
		data[0]=603979776L;
		data[6]=64L;
		data[10]=1073741824L;
		data[12]=281354986061824L;
		data[13]=5728L;
		return data;
	}
	public static final BitSet _tokenSet_318 = new BitSet(mk_tokenSet_318());
	private static final long[] mk_tokenSet_319() {
		long[] data = new long[28];
		data[10]=1073741824L;
		data[12]=140617497706496L;
		data[13]=4128L;
		return data;
	}
	public static final BitSet _tokenSet_319 = new BitSet(mk_tokenSet_319());
	private static final long[] mk_tokenSet_320() {
		long[] data = new long[26];
		data[0]=536870912L;
		data[6]=64L;
		data[12]=140737488355328L;
		return data;
	}
	public static final BitSet _tokenSet_320 = new BitSet(mk_tokenSet_320());
	private static final long[] mk_tokenSet_321() {
		long[] data = new long[14];
		data[0]=536870912L;
		data[6]=64L;
		return data;
	}
	public static final BitSet _tokenSet_321 = new BitSet(mk_tokenSet_321());
	private static final long[] mk_tokenSet_322() {
		long[] data = new long[28];
		data[0]=536870912L;
		data[6]=64L;
		data[10]=1073741824L;
		data[12]=281354986061824L;
		data[13]=4128L;
		return data;
	}
	public static final BitSet _tokenSet_322 = new BitSet(mk_tokenSet_322());
	private static final long[] mk_tokenSet_323() {
		long[] data = new long[28];
		data[0]=872415232L;
		data[6]=64L;
		data[9]=32768L;
		data[12]=161285161655205888L;
		data[13]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_323 = new BitSet(mk_tokenSet_323());
	private static final long[] mk_tokenSet_324() {
		long[] data = new long[26];
		data[0]=872415232L;
		data[6]=64L;
		data[9]=32768L;
		data[12]=144396663052566528L;
		return data;
	}
	public static final BitSet _tokenSet_324 = new BitSet(mk_tokenSet_324());
	private static final long[] mk_tokenSet_325() {
		long[] data = new long[14];
		data[0]=620756992L;
		return data;
	}
	public static final BitSet _tokenSet_325 = new BitSet(mk_tokenSet_325());
	private static final long[] mk_tokenSet_326() {
		long[] data = new long[28];
		data[0]=603979776L;
		data[10]=4116574218747904L;
		data[12]=8935141660703064064L;
		data[13]=3L;
		return data;
	}
	public static final BitSet _tokenSet_326 = new BitSet(mk_tokenSet_326());
	
	}
