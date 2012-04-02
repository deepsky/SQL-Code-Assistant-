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
		
		Integer retVal = -1;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_create:
			{
				create_or_replace();
				break;
			}
			case LITERAL_function:
			{
				{
				retVal=function_body();
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
					__markRule(retVal);
				}
				break;
			}
			case LITERAL_procedure:
			{
				{
				retVal=procedure_body();
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
					__markRule(retVal);
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
			case LITERAL_select:
			{
				{
				select_command();
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
			case LITERAL_insert:
			{
				insert_command();
				break;
			}
			case LITERAL_update:
			{
				{
				update_command();
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
					__markRule(UPDATE_COMMAND);
				}
				break;
			}
			case LITERAL_delete:
			{
				{
				delete_command();
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
					__markRule(DELETE_COMMAND);
				}
				break;
			}
			case LITERAL_merge:
			{
				{
				merge_command();
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
					__markRule(MERGE_COMMAND);
				}
				break;
			}
			case LITERAL_grant:
			{
				{
				grant_command();
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
					__markRule(GRANT_COMMAND);
				}
				break;
			}
			case LITERAL_revoke:
			{
				{
				revoke_command();
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
					__markRule(REVOKE_COMMAND);
				}
				break;
			}
			case LITERAL_rollback:
			{
				{
				rollback_statement();
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
					__markRule(ROLLBACK_STATEMENT);
				}
				break;
			}
			case LITERAL_commit:
			{
				{
				commit_statement();
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
					__markRule(COMMIT_STATEMENT);
				}
				break;
			}
			case LITERAL_alter:
			{
				{
				alter_command();
				}
				break;
			}
			case LITERAL_associate:
			{
				associate_statistics();
				break;
			}
			case LITERAL_comment:
			{
				comment();
				break;
			}
			case LITERAL_type:
			{
				type_definition();
				break;
			}
			case LITERAL_drop:
			{
				drop_command();
				break;
			}
			case LITERAL_truncate:
			{
				truncate_command();
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
				boolean synPredMatched7 = false;
				if (((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3))))) {
					int _m7 = mark();
					synPredMatched7 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_package);
						match(LITERAL_body);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched7 = false;
					}
					rewind(_m7);
					inputState.guessing--;
				}
				if ( synPredMatched7 ) {
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
					boolean synPredMatched11 = false;
					if (((LA(1)==LITERAL_package) && (_tokenSet_5.member(LA(2))) && (_tokenSet_7.member(LA(3))))) {
						int _m11 = mark();
						synPredMatched11 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_package);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched11 = false;
						}
						rewind(_m11);
						inputState.guessing--;
					}
					if ( synPredMatched11 ) {
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
		
		Integer retVal = -1;
		
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
			case LITERAL_user:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_unique:
			case LITERAL_global:
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_bigfile:
			case LITERAL_smallfile:
			case LITERAL_undo:
			case LITERAL_bitmap:
			case 662:
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
			case LITERAL_user:
			case LITERAL_directory:
			case LITERAL_database:
			case LITERAL_trigger:
			case LITERAL_unique:
			case LITERAL_global:
			case LITERAL_temporary:
			case LITERAL_tablespace:
			case LITERAL_bigfile:
			case LITERAL_smallfile:
			case LITERAL_undo:
			case LITERAL_bitmap:
			case 662:
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
				{
				retVal=procedure_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
				break;
			}
			case LITERAL_function:
			{
				{
				retVal=function_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
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
			case 662:
			{
				create_view_column_def();
				if ( inputState.guessing==0 ) {
					__markRule(CREATE_VIEW_COLUMN_DEF);
				}
				break;
			}
			case LITERAL_table:
			{
				{
				create_table();
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
			case LITERAL_user:
			{
				{
				create_user();
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
					__markRule(CREATE_USER);
				}
				break;
			}
			case LITERAL_type:
			{
				{
				match(LITERAL_type);
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is||LA(2)==LITERAL_under||LA(2)==LITERAL_as)) {
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
					if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_8.member(LA(3)))) {
						schema_name();
						match(DOT);
					}
					else if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_8.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					type_name_ref();
					match(OPEN_PAREN);
					record_item();
					{
					_loop278:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop278;
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
					match(LITERAL_object);
					match(OPEN_PAREN);
					record_item();
					{
					_loop283:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop283;
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
					if ( inputState.guessing==0 ) {
						__markRule(OBJECT_TYPE_DEF);
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
					boolean synPredMatched252 = false;
					if (((LA(1)==LITERAL_global||LA(1)==LITERAL_temporary) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))))) {
						int _m252 = mark();
						synPredMatched252 = true;
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
							synPredMatched252 = false;
						}
						rewind(_m252);
						inputState.guessing--;
					}
					if ( synPredMatched252 ) {
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
					else if ((_tokenSet_11.member(LA(1))) && (_tokenSet_12.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
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
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_4);
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
			_loop819:
			do {
				if ((_tokenSet_15.member(LA(1)))) {
					package_obj_body();
				}
				else {
					break _loop819;
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
				recover(ex,_tokenSet_16);
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
			else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_17.member(LA(2)))) {
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
				else if ((_tokenSet_18.member(LA(1))) && (_tokenSet_19.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop802:
				do {
					if ((_tokenSet_15.member(LA(1)))) {
						package_obj_spec_ex();
					}
					else {
						break _loop802;
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
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
	}
	
	public Integer  function_body() throws RecognitionException, TokenStreamException {
		Integer retValue;
		
		boolean tag1 = false; retValue = -1;
		
		try {      // for error handling
			function_declaration();
			{
			if ((LA(1)==LITERAL_is||LA(1)==LITERAL_as)) {
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
				if ((LA(1)==LITERAL_language) && (LA(2)==LITERAL_java) && (LA(3)==LITERAL_name)) {
					{
					match(LITERAL_language);
					match(LITERAL_java);
					match(LITERAL_name);
					if ( inputState.guessing==0 ) {
						tag1 = false;
					}
					string_literal();
					}
				}
				else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_21.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
					{
					func_proc_statements();
					if ( inputState.guessing==0 ) {
						tag1 = true;
					}
					}
					if ( inputState.guessing==0 ) {
						retValue = FUNCTION_BODY;
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else if ((_tokenSet_23.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_24.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				if(!tag1){
				//             __markRule(FUNCTION_SPEC);
				{ retValue = FUNCTION_SPEC; }
				}
				
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
		return retValue;
	}
	
	public Integer  procedure_body() throws RecognitionException, TokenStreamException {
		Integer retValue;
		
		boolean tag1 = false; retValue = -1;
		
		try {      // for error handling
			procedure_declaration();
			{
			if ((LA(1)==LITERAL_is||LA(1)==LITERAL_as)) {
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
				if ((LA(1)==LITERAL_language) && (LA(2)==LITERAL_java) && (LA(3)==LITERAL_name)) {
					{
					match(LITERAL_language);
					match(LITERAL_java);
					match(LITERAL_name);
					if ( inputState.guessing==0 ) {
						tag1 = false;
					}
					string_literal();
					}
				}
				else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_21.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
					{
					func_proc_statements();
					if ( inputState.guessing==0 ) {
						tag1 = true;
					}
					}
					if ( inputState.guessing==0 ) {
						retValue = PROCEDURE_BODY;
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else if ((_tokenSet_23.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_24.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				
				if(!tag1){
				//             __markRule(PROCEDURE_SPEC);
				{ retValue = PROCEDURE_SPEC; }
				}
				
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
		return retValue;
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
			_loop391:
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
					break _loop391;
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
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void select_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3)))) {
				select_expression();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_26.member(LA(3)))) {
				subquery();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
	
	public void insert_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			match(LITERAL_insert);
			match(LITERAL_into);
			{
			boolean synPredMatched1592 = false;
			if (((_tokenSet_29.member(LA(1))))) {
				int _m1592 = mark();
				synPredMatched1592 = true;
				inputState.guessing++;
				try {
					{
					table_alias();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1592 = false;
				}
				rewind(_m1592);
				inputState.guessing--;
			}
			if ( synPredMatched1592 ) {
				table_alias();
				{
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_30.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==CLOSE_PAREN)) {
					column_spec_list();
				}
				else if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_values||LA(1)==LITERAL_select) && (_tokenSet_26.member(LA(2))) && (_tokenSet_31.member(LA(3)))) {
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
					else if ((_tokenSet_8.member(LA(1)))) {
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
				if ((LA(1)==LITERAL_return||LA(1)==LITERAL_returning) && (_tokenSet_30.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==LITERAL_into)) {
					returning();
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				else if ((_tokenSet_33.member(LA(1)))) {
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
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_34.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
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
	
	public void update_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			boolean synPredMatched1610 = false;
			if (((LA(1)==LITERAL_update) && (_tokenSet_35.member(LA(2))) && (_tokenSet_36.member(LA(3))))) {
				int _m1610 = mark();
				synPredMatched1610 = true;
				inputState.guessing++;
				try {
					{
					subquery_update();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1610 = false;
				}
				rewind(_m1610);
				inputState.guessing--;
			}
			if ( synPredMatched1610 ) {
				subquery_update();
			}
			else if ((LA(1)==LITERAL_update) && (_tokenSet_35.member(LA(2))) && (_tokenSet_36.member(LA(3)))) {
				simple_update();
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
	
	public void delete_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_delete);
			{
			if ((LA(1)==LITERAL_from)) {
				match(LITERAL_from);
			}
			else if ((_tokenSet_29.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_alias();
			{
			if ((LA(1)==LITERAL_where)) {
				where_condition();
			}
			else if ((_tokenSet_37.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_return||LA(1)==LITERAL_returning) && (_tokenSet_30.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==LITERAL_into)) {
				returning();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
	
	public void merge_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_merge);
			match(LITERAL_into);
			table_alias();
			match(LITERAL_using);
			{
			if ((_tokenSet_29.member(LA(1)))) {
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
			if ((LA(1)==LITERAL_when) && (LA(2)==LITERAL_not||LA(2)==LITERAL_matched) && (LA(3)==LITERAL_then||LA(3)==LITERAL_matched)) {
				when_action();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_delete) && (LA(2)==LITERAL_where) && (_tokenSet_38.member(LA(3)))) {
				match(LITERAL_delete);
				match(LITERAL_where);
				condition();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
	
	public void grant_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_grant);
			{
			switch ( LA(1)) {
			case LITERAL_alter:
			case LITERAL_index:
			case LITERAL_references:
			case LITERAL_execute:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			{
				{
				privilege();
				{
				_loop1419:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						privilege();
					}
					else {
						break _loop1419;
					}
					
				} while (true);
				}
				match(LITERAL_on);
				identifier2();
				}
				break;
			}
			case LITERAL_all:
			{
				{
				match(LITERAL_all);
				match(LITERAL_privileges);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_to);
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void revoke_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_revoke);
			{
			switch ( LA(1)) {
			case LITERAL_alter:
			case LITERAL_index:
			case LITERAL_references:
			case LITERAL_execute:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_select:
			{
				{
				privilege();
				{
				_loop1426:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						privilege();
					}
					else {
						break _loop1426;
					}
					
				} while (true);
				}
				match(LITERAL_on);
				identifier2();
				}
				break;
			}
			case LITERAL_all:
			{
				{
				match(LITERAL_all);
				match(LITERAL_privileges);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_from);
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rollback_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_rollback);
			{
			if ((LA(1)==LITERAL_work)) {
				match(LITERAL_work);
			}
			else if ((_tokenSet_39.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_to)) {
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
			}
			else if ((_tokenSet_32.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_comment) && (LA(2)==QUOTED_STR) && (_tokenSet_40.member(LA(3)))) {
				match(LITERAL_comment);
				string_literal();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
	
	public void commit_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_commit);
			{
			if ((LA(1)==LITERAL_work)) {
				match(LITERAL_work);
			}
			else if ((_tokenSet_32.member(LA(1)))) {
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
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_34.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_GENERIC);
				}
				break;
			}
			case LITERAL_table:
			{
				alter_table();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_34.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_TABLE);
				}
				break;
			}
			case LITERAL_trigger:
			{
				alter_trigger();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_34.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					__markRule(ALTER_TRIGGER);
				}
				break;
			}
			case LITERAL_tablespace:
			{
				alter_tablespace();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_34.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
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
				recover(ex,_tokenSet_34);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
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
			if ( inputState.guessing==0 ) {
				__markRule(COMMENT);
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
	
	public void type_definition() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_type);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is||LA(2)==LITERAL_under||LA(2)==LITERAL_as)) {
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
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_8.member(LA(3)))) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_8.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				type_name_ref();
				match(OPEN_PAREN);
				record_item();
				{
				_loop757:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						record_item();
					}
					else {
						break _loop757;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				if ((LA(1)==LITERAL_not)) {
					match(LITERAL_not);
					match(LITERAL_final);
				}
				else if ((_tokenSet_41.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
					_loop764:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop764;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
					{
					if ((LA(1)==LITERAL_not)) {
						match(LITERAL_not);
						match(LITERAL_final);
					}
					else if ((_tokenSet_41.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
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
					if ((LA(1)==LITERAL_index)) {
						match(LITERAL_index);
						match(LITERAL_by);
						type_spec();
					}
					else if ((_tokenSet_42.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					if ((LA(1)==LITERAL_not)) {
						match(LITERAL_not);
						match(LITERAL_null);
					}
					else if ((_tokenSet_41.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
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
					_loop771:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							record_item();
						}
						else {
							break _loop771;
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
					if ((LA(1)==LITERAL_return)) {
						match(LITERAL_return);
						record_name();
						{
						if ((LA(1)==PERCENTAGE)) {
							match(PERCENTAGE);
							match(LITERAL_rowtype);
						}
						else if ((_tokenSet_41.member(LA(1)))) {
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
					}
					else if ((_tokenSet_41.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
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
					if ((LA(1)==LITERAL_not)) {
						match(LITERAL_not);
						match(LITERAL_null);
					}
					else if ((_tokenSet_41.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
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
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_43.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_43);
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
				if ((LA(1)==LITERAL_body) && (_tokenSet_5.member(LA(2))) && (_tokenSet_44.member(LA(3)))) {
					match(LITERAL_body);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_44.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_45.member(LA(2)))) {
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
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_46.member(LA(2)))) {
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
				if ((LA(1)==LITERAL_body) && (_tokenSet_47.member(LA(2))) && (_tokenSet_48.member(LA(3)))) {
					match(LITERAL_body);
				}
				else if ((_tokenSet_47.member(LA(1))) && (_tokenSet_48.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
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
			if ( inputState.guessing==0 ) {
				__markRule(TRUNCATE_TABLE);
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
	
	public void sqlplus_command_internal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt156=0;
			_loop156:
			do {
				if ((_tokenSet_49.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sqlplus_command();
				}
				else {
					if ( _cnt156>=1 ) { break _loop156; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt156++;
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
				recover(ex,_tokenSet_50);
			} else {
			  throw ex;
			}
		}
	}
	
	public void callable_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop1170:
			do {
				if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment_ex();
					match(DOT);
				}
				else {
					break _loop1170;
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
				recover(ex,_tokenSet_32);
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
				recover(ex,_tokenSet_51);
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
				recover(ex,_tokenSet_52);
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
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
	}
	
	public void object_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_47.member(LA(1))) && (LA(2)==DOT)) {
				identifier3();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_54.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(OBJECT_NAME);
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
				recover(ex,_tokenSet_45);
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
			_loop76:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec_ex();
				}
				else {
					break _loop76;
				}
				
			} while (true);
			}
			using_statistics_type();
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
	
	public void function_association() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_functions:
			{
				{
				match(LITERAL_functions);
				object_name();
				{
				_loop84:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						object_name();
					}
					else {
						break _loop84;
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
				object_name();
				{
				_loop87:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						object_name();
					}
					else {
						break _loop87;
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
				object_name();
				{
				_loop90:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						object_name();
					}
					else {
						break _loop90;
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
				object_name();
				{
				_loop93:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						object_name();
					}
					else {
						break _loop93;
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
				object_name();
				{
				_loop96:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						object_name();
					}
					else {
						break _loop96;
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
				recover(ex,_tokenSet_55);
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
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_spec_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt79=0;
			_loop79:
			do {
				if ((_tokenSet_30.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment2();
					match(DOT);
				}
				else {
					if ( _cnt79>=1 ) { break _loop79; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt79++;
			} while (true);
			}
			column_name_ref();
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
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_55.member(LA(2)))) {
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
				recover(ex,_tokenSet_55);
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
				recover(ex,_tokenSet_57);
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
				recover(ex,_tokenSet_58);
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
				recover(ex,_tokenSet_55);
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
				recover(ex,_tokenSet_59);
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
				recover(ex,_tokenSet_55);
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
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
	}
	
	public void string_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1379=0;
			_loop1379:
			do {
				if ((LA(1)==QUOTED_STR) && (_tokenSet_60.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1379>=1 ) { break _loop1379; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1379++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STRING_LITERAL);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_60);
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
			_loop119:
			do {
				if ((_tokenSet_61.member(LA(1)))) {
					column_constraint();
				}
				else {
					break _loop119;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_DEF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_63);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_64.member(LA(1))) && (_tokenSet_65.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				datatype();
			}
			else {
				boolean synPredMatched1054 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT||LA(2)==PERCENTAGE) && (_tokenSet_66.member(LA(3))))) {
					int _m1054 = mark();
					synPredMatched1054 = true;
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
						synPredMatched1054 = false;
					}
					rewind(_m1054);
					inputState.guessing--;
				}
				if ( synPredMatched1054 ) {
					percentage_type();
				}
				else {
					boolean synPredMatched1059 = false;
					if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_5.member(LA(3))))) {
						int _m1059 = mark();
						synPredMatched1059 = true;
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
							synPredMatched1059 = false;
						}
						rewind(_m1059);
						inputState.guessing--;
					}
					if ( synPredMatched1059 ) {
						percentage_type_w_schema();
					}
					else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_67.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
						recover(ex,_tokenSet_68);
					} else {
					  throw ex;
					}
				}
			}
			
	public void column_constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				{
				match(LITERAL_constraint);
				constraint_name();
				{
				switch ( LA(1)) {
				case LITERAL_primary:
				{
					{
					match(LITERAL_primary);
					match(LITERAL_key);
					{
					if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_disable);
					}
					else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_enable);
					}
					else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
					if ((LA(1)==LITERAL_rely) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_rely);
					}
					else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_disable);
					}
					else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_enable);
					}
					else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
					{
					if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_disable);
					}
					else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_enable);
					}
					else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(COLUMN_CHECK_CONSTRAINT);
					}
					break;
				}
				case LITERAL_null:
				case LITERAL_not:
				{
					{
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
					if ((LA(1)==LITERAL_disable) && (_tokenSet_70.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_disable);
					}
					else if ((LA(1)==LITERAL_enable) && (_tokenSet_70.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						match(LITERAL_enable);
					}
					else if ((_tokenSet_70.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					if ((LA(1)==LITERAL_unique)) {
						match(LITERAL_unique);
					}
					else if ((_tokenSet_69.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
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
				break;
			}
			case LITERAL_primary:
			{
				{
				match(LITERAL_primary);
				match(LITERAL_key);
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==LITERAL_rely) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_rely);
				}
				else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_69.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_69.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_CHECK_CONSTRAINT);
				}
				break;
			}
			case LITERAL_null:
			case LITERAL_not:
			{
				{
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
				if ((LA(1)==LITERAL_disable) && (_tokenSet_70.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_70.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_70.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_unique)) {
					match(LITERAL_unique);
				}
				else if ((_tokenSet_69.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(COLUMN_NOT_NULL_CONSTRAINT);
				}
				break;
			}
			case LITERAL_default:
			{
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
				recover(ex,_tokenSet_69);
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
			if ((LA(1)==LITERAL_disable) && (_tokenSet_71.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_disable);
			}
			else if ((LA(1)==LITERAL_enable) && (_tokenSet_71.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_enable);
			}
			else if ((_tokenSet_71.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_71);
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
				recover(ex,_tokenSet_72);
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
				recover(ex,_tokenSet_73);
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
			_loop1242:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					logical_term();
				}
				else {
					break _loop1242;
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
				recover(ex,_tokenSet_74);
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
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_75.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
				}
				else if ((LA(1)==LITERAL_long)) {
					match(LITERAL_long);
				}
				else if ((_tokenSet_75.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop161:
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
						if ((_tokenSet_5.member(LA(1))) && (_tokenSet_75.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
							identifier2();
						}
					else {
						break _loop161;
					}
					}
				} while (true);
				}
				{
				_loop163:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop163;
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
				_loop166:
				do {
					if ((_tokenSet_76.member(LA(1))) && (_tokenSet_77.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						base_expression();
					}
					else {
						break _loop166;
					}
					
				} while (true);
				}
				{
				_loop168:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop168;
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
				_loop172:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop172;
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
				_loop176:
				do {
					if ((_tokenSet_5.member(LA(1))) && (_tokenSet_78.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						identifier2();
					}
					else {
						break _loop176;
					}
					
				} while (true);
				}
				{
				_loop178:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop178;
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
				_loop182:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop182;
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
				_loop187:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop187;
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
					if ((_tokenSet_79.member(LA(1)))) {
						plsql_expression();
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
				_loop194:
				do {
					if ((_tokenSet_80.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop194;
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
				_loop196:
				do {
					if ((_tokenSet_80.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop196;
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
				_loop198:
				do {
					if ((_tokenSet_80.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop198;
					}
					
				} while (true);
				}
				match(CUSTOM_TOKEN);
				break;
			}
			case LITERAL_exit:
			{
				{
				match(LITERAL_exit);
				{
				if ((_tokenSet_5.member(LA(1))) && (_tokenSet_45.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier2();
				}
				else if ((LA(1)==NUMBER)) {
					numeric_literal();
				}
				else if ((_tokenSet_45.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop202:
				do {
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else {
						break _loop202;
					}
					
				} while (true);
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
				{
				match(LITERAL_spool);
				identifier4();
				{
				_loop207:
				do {
					if ((LA(1)==DOT)) {
						match(DOT);
						identifier4();
					}
					else {
						break _loop207;
					}
					
				} while (true);
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
				if ( inputState.guessing==0 ) {
					__markRule(SQLPLUS_ANONYM_PLSQL_BLOCK);
				}
				break;
			}
			case AT_PREFIXED:
			{
				{
				match(AT_PREFIXED);
				{
				_loop228:
				do {
					if ((_tokenSet_80.member(LA(1)))) {
						matchNot(CUSTOM_TOKEN);
					}
					else {
						break _loop228;
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
			case LITERAL_execute:
			{
				match(LITERAL_execute);
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
			case LITERAL_decode:
			{
				match(LITERAL_decode);
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
			case LITERAL_cast:
			{
				match(LITERAL_cast);
				break;
			}
			case LITERAL_initial:
			{
				match(LITERAL_initial);
				break;
			}
			case LITERAL_minextents:
			{
				match(LITERAL_minextents);
				break;
			}
			case LITERAL_maxextents:
			{
				match(LITERAL_maxextents);
				break;
			}
			case LITERAL_pctincrease:
			{
				match(LITERAL_pctincrease);
				break;
			}
			case LITERAL_overflow:
			{
				match(LITERAL_overflow);
				break;
			}
			case LITERAL_quota:
			{
				match(LITERAL_quota);
				break;
			}
			case LITERAL_profile:
			{
				match(LITERAL_profile);
				break;
			}
			case LITERAL_password:
			{
				match(LITERAL_password);
				break;
			}
			case LITERAL_expire:
			{
				match(LITERAL_expire);
				break;
			}
			case LITERAL_account:
			{
				match(LITERAL_account);
				break;
			}
			case LITERAL_lock:
			{
				match(LITERAL_lock);
				break;
			}
			case LITERAL_unlock:
			{
				match(LITERAL_unlock);
				break;
			}
			case LITERAL_privileges:
			{
				match(LITERAL_privileges);
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
				recover(ex,_tokenSet_81);
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
			case LITERAL_case:
			{
				{
				case_expression();
				}
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
				boolean synPredMatched1292 = false;
				if (((LA(1)==LITERAL_sqlcode) && (_tokenSet_82.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
					int _m1292 = mark();
					synPredMatched1292 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_sqlcode);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1292 = false;
					}
					rewind(_m1292);
					inputState.guessing--;
				}
				if ( synPredMatched1292 ) {
					match(LITERAL_sqlcode);
					if ( inputState.guessing==0 ) {
						__markRule(SQLCODE_SYSVAR);
					}
				}
				else {
					boolean synPredMatched1294 = false;
					if (((LA(1)==LITERAL_sqlerrm) && (_tokenSet_82.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
						int _m1294 = mark();
						synPredMatched1294 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_sqlerrm);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1294 = false;
						}
						rewind(_m1294);
						inputState.guessing--;
					}
					if ( synPredMatched1294 ) {
						{
						match(LITERAL_sqlerrm);
						{
						if ((LA(1)==OPEN_PAREN) && (LA(2)==NUMBER) && (LA(3)==CLOSE_PAREN)) {
							match(OPEN_PAREN);
							numeric_literal();
							match(CLOSE_PAREN);
						}
						else if ((_tokenSet_82.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
						boolean synPredMatched1298 = false;
						if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_38.member(LA(3))))) {
							int _m1298 = mark();
							synPredMatched1298 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_cast);
								match(OPEN_PAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1298 = false;
							}
							rewind(_m1298);
							inputState.guessing--;
						}
						if ( synPredMatched1298 ) {
							{
							cast_function();
							}
						}
						else {
							boolean synPredMatched1301 = false;
							if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3))))) {
								int _m1301 = mark();
								synPredMatched1301 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_decode);
									match(OPEN_PAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1301 = false;
								}
								rewind(_m1301);
								inputState.guessing--;
							}
							if ( synPredMatched1301 ) {
								{
								decode_function();
								}
							}
							else {
								boolean synPredMatched1304 = false;
								if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_84.member(LA(3))))) {
									int _m1304 = mark();
									synPredMatched1304 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_trim);
										match(OPEN_PAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1304 = false;
									}
									rewind(_m1304);
									inputState.guessing--;
								}
								if ( synPredMatched1304 ) {
									{
									trim_function();
									}
								}
								else {
									boolean synPredMatched1307 = false;
									if (((LA(1)==LITERAL_count) && (LA(2)==OPEN_PAREN) && (_tokenSet_85.member(LA(3))))) {
										int _m1307 = mark();
										synPredMatched1307 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_count);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1307 = false;
										}
										rewind(_m1307);
										inputState.guessing--;
									}
									if ( synPredMatched1307 ) {
										{
										count_function();
										}
									}
									else {
										boolean synPredMatched1313 = false;
										if (((LA(1)==LITERAL_multiset) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
											int _m1313 = mark();
											synPredMatched1313 = true;
											inputState.guessing++;
											try {
												{
												match(LITERAL_multiset);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1313 = false;
											}
											rewind(_m1313);
											inputState.guessing--;
										}
										if ( synPredMatched1313 ) {
											{
											multiset_operator();
											}
										}
										else {
											boolean synPredMatched1316 = false;
											if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_38.member(LA(3))))) {
												int _m1316 = mark();
												synPredMatched1316 = true;
												inputState.guessing++;
												try {
													{
													match(LITERAL_lag);
													match(OPEN_PAREN);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched1316 = false;
												}
												rewind(_m1316);
												inputState.guessing--;
											}
											if ( synPredMatched1316 ) {
												{
												lag_function();
												}
											}
											else {
												boolean synPredMatched1319 = false;
												if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_38.member(LA(3))))) {
													int _m1319 = mark();
													synPredMatched1319 = true;
													inputState.guessing++;
													try {
														{
														match(LITERAL_lead);
														match(OPEN_PAREN);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched1319 = false;
													}
													rewind(_m1319);
													inputState.guessing--;
												}
												if ( synPredMatched1319 ) {
													{
													lead_function();
													}
												}
												else {
													boolean synPredMatched1323 = false;
													if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (LA(3)==CLOSE_PAREN))) {
														int _m1323 = mark();
														synPredMatched1323 = true;
														inputState.guessing++;
														try {
															{
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
															}
														}
														catch (RecognitionException pe) {
															synPredMatched1323 = false;
														}
														rewind(_m1323);
														inputState.guessing--;
													}
													if ( synPredMatched1323 ) {
														dence_rank_analytics_func();
													}
													else {
														boolean synPredMatched1325 = false;
														if (((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_86.member(LA(3))))) {
															int _m1325 = mark();
															synPredMatched1325 = true;
															inputState.guessing++;
															try {
																{
																match(LITERAL_extract);
																match(OPEN_PAREN);
																}
															}
															catch (RecognitionException pe) {
																synPredMatched1325 = false;
															}
															rewind(_m1325);
															inputState.guessing--;
														}
														if ( synPredMatched1325 ) {
															extract_date_function();
														}
														else if ((LA(1)==LITERAL_interval) && (LA(2)==QUOTED_STR) && (_tokenSet_87.member(LA(3)))) {
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
																else if ((_tokenSet_82.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
																}
																else {
																	throw new NoViableAltException(LT(1), getFilename());
																}
																
																}
															}
															else if ((_tokenSet_82.member(LA(1)))) {
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
															boolean synPredMatched1332 = false;
															if (((LA(1)==LITERAL_sql) && (LA(2)==PERCENTAGE))) {
																int _m1332 = mark();
																synPredMatched1332 = true;
																inputState.guessing++;
																try {
																	{
																	match(LITERAL_sql);
																	match(PERCENTAGE);
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched1332 = false;
																}
																rewind(_m1332);
																inputState.guessing--;
															}
															if ( synPredMatched1332 ) {
																sql_percentage();
															}
															else if ((LA(1)==LITERAL_timestamp) && (LA(2)==QUOTED_STR) && (_tokenSet_82.member(LA(3)))) {
																{
																match(LITERAL_timestamp);
																string_literal();
																}
																if ( inputState.guessing==0 ) {
																	__markRule(TIMESTAMP_CONST);
																}
															}
															else {
																boolean synPredMatched1341 = false;
																if (((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING||LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==PERCENTAGE||LA(2)==LITERAL_select) && (_tokenSet_26.member(LA(3))))) {
																	int _m1341 = mark();
																	synPredMatched1341 = true;
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
																		synPredMatched1341 = false;
																	}
																	rewind(_m1341);
																	inputState.guessing--;
																}
																if ( synPredMatched1341 ) {
																	ident_percentage();
																}
																else {
																	boolean synPredMatched1343 = false;
																	if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_26.member(LA(3))))) {
																		int _m1343 = mark();
																		synPredMatched1343 = true;
																		inputState.guessing++;
																		try {
																			{
																			match(OPEN_PAREN);
																			match(LITERAL_select);
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched1343 = false;
																		}
																		rewind(_m1343);
																		inputState.guessing--;
																	}
																	if ( synPredMatched1343 ) {
																		subquery();
																		if ( inputState.guessing==0 ) {
																			__markRule(SUBQUERY_EXPR);
																		}
																	}
																	else if ((LA(1)==OPEN_PAREN) && (_tokenSet_38.member(LA(2))) && (_tokenSet_88.member(LA(3)))) {
																		match(OPEN_PAREN);
																		condition();
																		match(CLOSE_PAREN);
																		if ( inputState.guessing==0 ) {
																			__markRule(PARENTHESIZED_EXPR);
																		}
																	}
																	else {
																		boolean synPredMatched1345 = false;
																		if (((_tokenSet_89.member(LA(1))) && (_tokenSet_90.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
																			int _m1345 = mark();
																			synPredMatched1345 = true;
																			inputState.guessing++;
																			try {
																				{
																				pseudo_column();
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched1345 = false;
																			}
																			rewind(_m1345);
																			inputState.guessing--;
																		}
																		if ( synPredMatched1345 ) {
																			pseudo_column();
																		}
																		else {
																			boolean synPredMatched1347 = false;
																			if (((_tokenSet_30.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_91.member(LA(3))))) {
																				int _m1347 = mark();
																				synPredMatched1347 = true;
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
																					synPredMatched1347 = false;
																				}
																				rewind(_m1347);
																				inputState.guessing--;
																			}
																			if ( synPredMatched1347 ) {
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
																				boolean synPredMatched1351 = false;
																				if (((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (LA(3)==LITERAL_nextval||LA(3)==LITERAL_currval))) {
																					int _m1351 = mark();
																					synPredMatched1351 = true;
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
																						synPredMatched1351 = false;
																					}
																					rewind(_m1351);
																					inputState.guessing--;
																				}
																				if ( synPredMatched1351 ) {
																					sequence_expr();
																				}
																				else if ((_tokenSet_92.member(LA(1))) && (_tokenSet_90.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
																					rvalue();
																				}
																			else {
																				throw new NoViableAltException(LT(1), getFilename());
																			}
																			}}}}}}}}}}}}}}}}}
																		}
																		catch (RecognitionException ex) {
																			if (inputState.guessing==0) {
																				reportError(ex);
																				recover(ex,_tokenSet_82);
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==ASTERISK||LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_68.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_68.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				boolean synPredMatched1019 = false;
				if (((LA(1)==LITERAL_with) && (LA(2)==LITERAL_local) && (LA(3)==LITERAL_time))) {
					int _m1019 = mark();
					synPredMatched1019 = true;
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
						synPredMatched1019 = false;
					}
					rewind(_m1019);
					inputState.guessing--;
				}
				if ( synPredMatched1019 ) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
					else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_68.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_68.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_68.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_93.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 718:
			{
				match(718);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_93.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_93.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				break;
			}
			case 720:
			{
				match(720);
				{
				if ((LA(1)==OPEN_PAREN) && (LA(2)==CLOSE_PAREN||LA(2)==NUMBER) && (_tokenSet_93.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				else if ((_tokenSet_68.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
			boolean synPredMatched231 = false;
			if (((_tokenSet_92.member(LA(1))) && (_tokenSet_94.member(LA(2))) && (_tokenSet_95.member(LA(3))))) {
				int _m231 = mark();
				synPredMatched231 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					match(ASSIGNMENT_EQ);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched231 = false;
				}
				rewind(_m231);
				inputState.guessing--;
			}
			if ( synPredMatched231 ) {
				assignment_statement();
				if ( inputState.guessing==0 ) {
					__markRule(ASSIGNMENT_STATEMENT);
				}
			}
			else if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
				{
				procedure_call();
				{
				_loop234:
				do {
					if ((LA(1)==DOT)) {
						match(DOT);
						procedure_call();
					}
					else {
						break _loop234;
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
				recover(ex,_tokenSet_45);
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
			_loop1289:
			do {
				if ((LA(1)==CONCAT) && (_tokenSet_79.member(LA(2))) && (_tokenSet_96.member(LA(3)))) {
					c = LT(1);
					match(CONCAT);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					num_expression();
				}
				else {
					break _loop1289;
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
				recover(ex,_tokenSet_97);
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
			case LITERAL_primary:
			{
				match(LITERAL_primary);
				break;
			}
			case LITERAL_foreign:
			{
				match(LITERAL_foreign);
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
				recover(ex,_tokenSet_98);
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
				if ((_tokenSet_47.member(LA(1)))) {
					identifier3();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop240:
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
						if ((_tokenSet_47.member(LA(1)))) {
							identifier3();
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop240;
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
				if ((_tokenSet_99.member(LA(1)))) {
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
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_lvalue() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			boolean synPredMatched975 = false;
			if (((_tokenSet_100.member(LA(1))) && (_tokenSet_101.member(LA(2))) && (_tokenSet_102.member(LA(3))))) {
				int _m975 = mark();
				synPredMatched975 = true;
				inputState.guessing++;
				try {
					{
					{
					_loop974:
					do {
						if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_100.member(LA(3)))) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop974;
						}
						
					} while (true);
					}
					name_fragment2();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched975 = false;
				}
				rewind(_m975);
				inputState.guessing--;
			}
			if ( synPredMatched975 ) {
				{
				_loop977:
				do {
					if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment();
						match(DOT);
					}
					else {
						break _loop977;
					}
					
				} while (true);
				}
				name_fragment2();
				if ( inputState.guessing==0 ) {
					__markRule(PLSQL_VAR_REF);
				}
			}
			else {
				boolean synPredMatched980 = false;
				if (((LA(1)==COLON) && (LA(2)==LITERAL_old||LA(2)==LITERAL_new) && (LA(3)==DOT))) {
					int _m980 = mark();
					synPredMatched980 = true;
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
						synPredMatched980 = false;
					}
					rewind(_m980);
					inputState.guessing--;
				}
				if ( synPredMatched980 ) {
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
				else if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
					{
					function_call();
					{
					_loop988:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							if ( inputState.guessing==0 ) {
								tag1=true;
							}
							{
							boolean synPredMatched987 = false;
							if (((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3))))) {
								int _m987 = mark();
								synPredMatched987 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched987 = false;
								}
								rewind(_m987);
								inputState.guessing--;
							}
							if ( synPredMatched987 ) {
								function_call();
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_101.member(LA(2))) && (_tokenSet_102.member(LA(3)))) {
								c_record_item_ref();
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop988;
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
				else if ((LA(1)==COLON) && (_tokenSet_5.member(LA(2))) && (_tokenSet_103.member(LA(3)))) {
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
					recover(ex,_tokenSet_103);
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
				recover(ex,_tokenSet_104);
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
				recover(ex,_tokenSet_105);
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
				_loop781:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						v_column_def();
					}
					else {
						break _loop781;
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
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_view_column_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(662);
			view_name_ddl();
			match(OPEN_PAREN);
			column_def();
			{
			_loop789:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_def();
				}
				else {
					break _loop789;
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
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_table);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_106.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			table_name_ddl();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				{
				match(OPEN_PAREN);
				column_def();
				{
				_loop440:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						if ((_tokenSet_30.member(LA(1)))) {
							column_def();
						}
						else if ((_tokenSet_107.member(LA(1)))) {
							constraint();
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
					}
					else {
						break _loop440;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
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
				case LITERAL_including:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_lob:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_pctthreshold:
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
				case LITERAL_overflow:
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
				case LITERAL_including:
				case LITERAL_cache:
				case LITERAL_nocache:
				case LITERAL_grant:
				case LITERAL_revoke:
				case LITERAL_delete:
				case LITERAL_insert:
				case LITERAL_update:
				case LITERAL_segment:
				case LITERAL_cluster:
				case LITERAL_pctthreshold:
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
				case LITERAL_overflow:
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
				_loop444:
				do {
					if ((_tokenSet_108.member(LA(1)))) {
						physical_properties();
					}
					else {
						break _loop444;
					}
					
				} while (true);
				}
				}
				break;
			}
			case LITERAL_storage:
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_as:
			case LITERAL_tablespace:
			case LITERAL_local:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_including:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_pctthreshold:
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
			case LITERAL_overflow:
			case LITERAL_partition:
			case LITERAL_organization:
			{
				{
				{
				_loop447:
				do {
					if ((_tokenSet_108.member(LA(1)))) {
						physical_properties();
					}
					else {
						break _loop447;
					}
					
				} while (true);
				}
				match(LITERAL_as);
				select_expression();
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
				recover(ex,_tokenSet_25);
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
				_loop454:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						{
						if ((_tokenSet_30.member(LA(1)))) {
							column_def();
						}
						else if ((_tokenSet_107.member(LA(1)))) {
							constraint();
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
					}
					else {
						break _loop454;
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
			case LITERAL_as:
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
				recover(ex,_tokenSet_25);
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
			_loop425:
			do {
				if ((_tokenSet_108.member(LA(1)))) {
					physical_properties();
				}
				else {
					break _loop425;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_directory() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_directory);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_47.member(LA(3)))) {
				schema_name();
				match(DOT);
			}
			else if ((_tokenSet_47.member(LA(1))) && (_tokenSet_109.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_25);
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
				recover(ex,_tokenSet_25);
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
			_loop369:
			do {
				if ((_tokenSet_110.member(LA(1))) && (_tokenSet_111.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					sequence_opt();
				}
				else {
					break _loop369;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
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
			else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_25.member(LA(2)))) {
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
				recover(ex,_tokenSet_25);
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
				int _cnt317=0;
				_loop317:
				do {
					if ((_tokenSet_112.member(LA(1)))) {
						create_tablespace_rest();
					}
					else {
						if ( _cnt317>=1 ) { break _loop317; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt317++;
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
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_user() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_user);
			object_name();
			match(LITERAL_identified);
			{
			switch ( LA(1)) {
			case LITERAL_by:
			{
				{
				match(LITERAL_by);
				password();
				}
				break;
			}
			case LITERAL_externally:
			{
				{
				match(LITERAL_externally);
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
				case LITERAL_default:
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
				case LITERAL_quota:
				case LITERAL_profile:
				case LITERAL_password:
				case LITERAL_account:
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
			case LITERAL_globally:
			{
				{
				match(LITERAL_globally);
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
				case LITERAL_default:
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
				case LITERAL_quota:
				case LITERAL_profile:
				case LITERAL_password:
				case LITERAL_account:
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
			{
			_loop295:
			do {
				if ((_tokenSet_113.member(LA(1)))) {
					create_user_spec();
				}
				else {
					break _loop295;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
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
				recover(ex,_tokenSet_114);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_name_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			name_fragment();
			{
			_loop1070:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					name_fragment();
				}
				else {
					break _loop1070;
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
				recover(ex,_tokenSet_68);
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
				recover(ex,_tokenSet_62);
			} else {
			  throw ex;
			}
		}
	}
	
	public void password() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_115);
			} else {
			  throw ex;
			}
		}
	}
	
	public void create_user_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_default:
			{
				{
				match(LITERAL_default);
				match(LITERAL_tablespace);
				tablespace_name();
				}
				break;
			}
			case LITERAL_temporary:
			{
				{
				match(LITERAL_temporary);
				match(LITERAL_tablespace);
				tablespace_name();
				}
				break;
			}
			case LITERAL_quota:
			{
				{
				match(LITERAL_quota);
				{
				switch ( LA(1)) {
				case LITERAL_unlimited:
				{
					match(LITERAL_unlimited);
					break;
				}
				case STORAGE_SIZE:
				{
					match(STORAGE_SIZE);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_on);
				tablespace_name();
				}
				break;
			}
			case LITERAL_profile:
			{
				{
				match(LITERAL_profile);
				identifier();
				}
				break;
			}
			case LITERAL_password:
			{
				{
				match(LITERAL_password);
				match(LITERAL_expire);
				}
				break;
			}
			case LITERAL_account:
			{
				{
				match(LITERAL_account);
				{
				switch ( LA(1)) {
				case LITERAL_lock:
				{
					match(LITERAL_lock);
					break;
				}
				case LITERAL_unlock:
				{
					match(LITERAL_unlock);
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
				recover(ex,_tokenSet_115);
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
				recover(ex,_tokenSet_116);
			} else {
			  throw ex;
			}
		}
	}
	
	public void file_specification() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			string_literal();
			{
			if ((LA(1)==LITERAL_size) && (LA(2)==STORAGE_SIZE||LA(2)==NUMBER) && (_tokenSet_117.member(LA(3)))) {
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
			}
			else if ((_tokenSet_117.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_reuse) && (_tokenSet_117.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_reuse);
			}
			else if ((_tokenSet_117.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_autoextend)) {
				autoextend_clause();
			}
			else if ((_tokenSet_118.member(LA(1)))) {
			}
			else {
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
				recover(ex,_tokenSet_25);
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
				recover(ex,_tokenSet_119);
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
				recover(ex,_tokenSet_120);
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
				_loop321:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						file_specification();
					}
					else {
						break _loop321;
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
				recover(ex,_tokenSet_121);
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
				recover(ex,_tokenSet_120);
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
				if ((LA(1)==LITERAL_normal) && (_tokenSet_120.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_normal);
				}
				else if ((LA(1)==LITERAL_temporary) && (_tokenSet_120.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_temporary);
				}
				else if ((LA(1)==LITERAL_immediate) && (_tokenSet_120.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_immediate);
				}
				else if ((_tokenSet_120.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				recover(ex,_tokenSet_120);
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
				if ((LA(1)==LITERAL_next) && (LA(2)==STORAGE_SIZE) && (_tokenSet_118.member(LA(3)))) {
					match(LITERAL_next);
					match(STORAGE_SIZE);
				}
				else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_maxsize) && (LA(2)==STORAGE_SIZE||LA(2)==NUMBER) && (_tokenSet_118.member(LA(3)))) {
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
				else if ((_tokenSet_118.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_118);
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
				if ((LA(1)==QUOTED_STR)) {
					file_specification();
				}
				else if ((_tokenSet_32.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				_loop359:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						string_literal();
					}
					else {
						break _loop359;
					}
					
				} while (true);
				}
				match(LITERAL_to);
				string_literal();
				{
				_loop361:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						string_literal();
					}
					else {
						break _loop361;
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
				recover(ex,_tokenSet_32);
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
				else if ((_tokenSet_122.member(LA(1))) && (_tokenSet_123.member(LA(2)))) {
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
				recover(ex,_tokenSet_32);
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
				recover(ex,_tokenSet_124);
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
				recover(ex,_tokenSet_125);
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
				recover(ex,_tokenSet_25);
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
				recover(ex,_tokenSet_126);
			} else {
			  throw ex;
			}
		}
	}
	
	public void dml_trigger() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			insert_update_delete();
			{
			_loop401:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					insert_update_delete();
				}
				else {
					break _loop401;
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
				recover(ex,_tokenSet_127);
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
				recover(ex,_tokenSet_127);
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
				recover(ex,_tokenSet_127);
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
			table_ref();
			if ( inputState.guessing==0 ) {
				__markRule(INSTEADOF_TRIGGER);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_127);
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
				recover(ex,_tokenSet_127);
			} else {
			  throw ex;
			}
		}
	}
	
	public void referencing_old_new() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_referencing);
			{
			_loop410:
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
					break _loop410;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_127);
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
				recover(ex,_tokenSet_128);
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
				recover(ex,_tokenSet_127);
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
					_loop416:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop416;
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
				recover(ex,_tokenSet_129);
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
				recover(ex,_tokenSet_32);
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
				recover(ex,_tokenSet_130);
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
			_loop430:
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
					break _loop430;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
			case LITERAL_pctthreshold:
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
				case LITERAL_pctthreshold:
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
				case LITERAL_pctthreshold:
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
			case LITERAL_disable:
			case LITERAL_enable:
			case LITERAL_local:
			case LITERAL_including:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_parallel:
			case LITERAL_noparallel:
			case LITERAL_monitoring:
			case LITERAL_nomonitoring:
			case LITERAL_overflow:
			case LITERAL_partition:
			{
				table_properties();
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
				recover(ex,_tokenSet_72);
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
				recover(ex,_tokenSet_132);
			} else {
			  throw ex;
			}
		}
	}
	
	public void constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				match(LITERAL_constraint);
				constraint_name();
				break;
			}
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
			case LITERAL_primary:
			{
				pk_spec_constr();
				if ( inputState.guessing==0 ) {
					__markRule(PK_SPEC);
				}
				break;
			}
			case LITERAL_foreign:
			{
				fk_spec_constr();
				if ( inputState.guessing==0 ) {
					__markRule(FK_SPEC);
				}
				break;
			}
			case LITERAL_check:
			{
				check_condition();
				if ( inputState.guessing==0 ) {
					__markRule(CHECK_CONSTRAINT);
				}
				break;
			}
			case LITERAL_unique:
			{
				unique_constr();
				if ( inputState.guessing==0 ) {
					__markRule(UNIQUE_CONSTRAINT);
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
				recover(ex,_tokenSet_62);
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
			case LITERAL_including:
			case LITERAL_cache:
			case LITERAL_nocache:
			case LITERAL_grant:
			case LITERAL_revoke:
			case LITERAL_delete:
			case LITERAL_insert:
			case LITERAL_update:
			case LITERAL_lob:
			case LITERAL_segment:
			case LITERAL_cluster:
			case LITERAL_pctthreshold:
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
			case LITERAL_overflow:
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
				recover(ex,_tokenSet_133);
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
			_loop464:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					identifier();
				}
				else {
					break _loop464;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			match(LITERAL_store);
			match(LITERAL_as);
			match(OPEN_PAREN);
			{
			int _cnt466=0;
			_loop466:
			do {
				if ((_tokenSet_134.member(LA(1)))) {
					lob_parameters();
				}
				else {
					if ( _cnt466>=1 ) { break _loop466; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt466++;
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_135);
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
			_loop1438:
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
					break _loop1438;
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
				recover(ex,_tokenSet_136);
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
				recover(ex,_tokenSet_137);
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
				tablespace_name();
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
			case LITERAL_cache:
			case LITERAL_nocache:
			{
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
					{
					switch ( LA(1)) {
					case LITERAL_reads:
					{
						match(LITERAL_reads);
						break;
					}
					case CLOSE_PAREN:
					case LITERAL_storage:
					case LITERAL_disable:
					case LITERAL_enable:
					case LITERAL_tablespace:
					case LITERAL_retention:
					case LITERAL_logging:
					case LITERAL_nologging:
					case LITERAL_cache:
					case LITERAL_nocache:
					case LITERAL_chunk:
					case LITERAL_pctversion:
					case LITERAL_freepools:
					case LITERAL_filesystem_like_logging:
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
	
	public void storage_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_storage);
			match(OPEN_PAREN);
			{
			int _cnt588=0;
			_loop588:
			do {
				if ((_tokenSet_139.member(LA(1)))) {
					storage_params();
				}
				else {
					if ( _cnt588>=1 ) { break _loop588; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt588++;
			} while (true);
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(STORAGE_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_140);
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
				recover(ex,_tokenSet_140);
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
				recover(ex,_tokenSet_141);
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
				tablespace_name();
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
			case LITERAL_pctthreshold:
			{
				{
				match(LITERAL_pctthreshold);
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_142);
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
				if ((LA(1)==LITERAL_including) && (_tokenSet_5.member(LA(2))) && (_tokenSet_72.member(LA(3)))) {
					match(LITERAL_including);
					identifier2();
				}
				else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_pctthreshold) && (LA(2)==NUMBER) && (_tokenSet_72.member(LA(3)))) {
					match(LITERAL_pctthreshold);
					numeric_literal();
				}
				else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				{
				match(LITERAL_external);
				match(OPEN_PAREN);
				external_table_spec();
				match(CLOSE_PAREN);
				{
				_loop575:
				do {
					if ((_tokenSet_143.member(LA(1))) && (_tokenSet_144.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						ext_table_properties();
					}
					else {
						break _loop575;
					}
					
				} while (true);
				}
				}
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
				recover(ex,_tokenSet_72);
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
			_loop487:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ddl();
				}
				else {
					break _loop487;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_72);
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
			case LITERAL_including:
			case LITERAL_overflow:
			{
				index_org_overflow_clause();
				break;
			}
			default:
				if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (_tokenSet_145.member(LA(2)))) {
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
				recover(ex,_tokenSet_72);
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
				if ((LA(1)==LITERAL_parallel) && (_tokenSet_140.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_parallel);
				}
				else if ((LA(1)==LITERAL_noparallel) && (_tokenSet_140.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_noparallel);
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (_tokenSet_140.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					identifier();
				}
				else if ((_tokenSet_140.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_140);
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
				if ((LA(1)==NUMBER)) {
					numeric_literal();
				}
				else if ((LA(1)==LITERAL_for) && (LA(2)==LITERAL_all||LA(2)==LITERAL_direct_load) && (LA(3)==LITERAL_operations)) {
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
				}
				else if ((_tokenSet_146.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				recover(ex,_tokenSet_146);
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
				recover(ex,_tokenSet_72);
			} else {
			  throw ex;
			}
		}
	}
	
	public void parallel_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
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
				else if ((_tokenSet_140.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
			if ( inputState.guessing==0 ) {
				__markRule(PARALLEL_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_140);
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
			case LITERAL_constraint:
			case LITERAL_primary:
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
				recover(ex,_tokenSet_72);
			} else {
			  throw ex;
			}
		}
	}
	
	public void monitoring_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
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
			if ( inputState.guessing==0 ) {
				__markRule(MONITORING_CLAUSE);
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
	}
	
	public void index_org_overflow_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_including:
			{
				match(LITERAL_including);
				column_name_ref();
				break;
			}
			case LITERAL_overflow:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_overflow);
			{
			_loop511:
			do {
				if ((_tokenSet_147.member(LA(1))) && (LA(2)==OPEN_PAREN||LA(2)==NUMBER||LA(2)==LITERAL_statistics) && (_tokenSet_148.member(LA(3)))) {
					physical_attributes_clause();
				}
				else {
					break _loop511;
				}
				
			} while (true);
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
	}
	
	public void range_partitions() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			match(LITERAL_by);
			match(LITERAL_range);
			match(OPEN_PAREN);
			column_name_ref();
			{
			_loop515:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop515;
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
					_loop519:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop519;
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
			_loop521:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					partition_item();
				}
				else {
					break _loop521;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_72);
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
			_loop534:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop534;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				{
				int _cnt537=0;
				_loop537:
				do {
					if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_partition) && (_tokenSet_149.member(LA(3)))) {
						individual_hash_partitions();
					}
					else {
						if ( _cnt537>=1 ) { break _loop537; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt537++;
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
			if ((LA(1)==LITERAL_cache) && (LA(2)==LITERAL_parallel||LA(2)==LITERAL_noparallel) && (_tokenSet_72.member(LA(3)))) {
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
			else if ((LA(1)==LITERAL_nocache) && (_tokenSet_72.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_nocache);
			}
			else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
	}
	
	public void local_partitioned_index() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_local);
			{
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_partition) && (_tokenSet_150.member(LA(3)))) {
				on_range_partitioned_table();
			}
			else if ((_tokenSet_146.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
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
				partition_name();
			}
			else if ((LA(1)==LITERAL_values)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			range_values_clause();
			{
			int _cnt525=0;
			_loop525:
			do {
				if ((_tokenSet_151.member(LA(1)))) {
					table_partition_description();
				}
				else {
					if ( _cnt525>=1 ) { break _loop525; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt525++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
			} else {
			  throw ex;
			}
		}
	}
	
	public void partition_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier2();
			if ( inputState.guessing==0 ) {
				__markRule(PARTITION_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_152);
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
			_loop530:
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
					break _loop530;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_151);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_partition_description() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_storage:
			case LITERAL_tablespace:
			case LITERAL_logging:
			case LITERAL_nologging:
			case LITERAL_online:
			case LITERAL_pctthreshold:
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
			case LITERAL_overflow:
			{
				match(LITERAL_overflow);
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
				recover(ex,_tokenSet_153);
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
			_loop543:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					hash_partition_spec();
				}
				else {
					break _loop543;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
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
			if ((LA(1)==LITERAL_store) && (LA(2)==LITERAL_in) && (LA(3)==OPEN_PAREN)) {
				match(LITERAL_store);
				match(LITERAL_in);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop554:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop554;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
			}
			else if ((_tokenSet_146.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_154.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				table_compression();
			}
			else if ((_tokenSet_146.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_overflow) && (LA(2)==LITERAL_store) && (LA(3)==LITERAL_in)) {
				match(LITERAL_overflow);
				match(LITERAL_store);
				match(LITERAL_in);
				match(OPEN_PAREN);
				identifier2();
				{
				_loop558:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop558;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
			}
			else if ((_tokenSet_146.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
			} else {
			  throw ex;
			}
		}
	}
	
	public void hash_partition_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_155.member(LA(2))) && (_tokenSet_154.member(LA(3)))) {
				partition_name();
			}
			else if ((_tokenSet_155.member(LA(1))) && (_tokenSet_154.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop547:
			do {
				if ((_tokenSet_156.member(LA(1)))) {
					partition_storage_clause();
				}
				else {
					break _loop547;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
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
				tablespace_name();
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
				recover(ex,_tokenSet_155);
			} else {
			  throw ex;
			}
		}
	}
	
	public void external_table_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_type);
			{
			switch ( LA(1)) {
			case LITERAL_oracle_loader:
			{
				{
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
				break;
			}
			case LITERAL_oracle_datapump:
			{
				{
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
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			location();
			if ( inputState.guessing==0 ) {
				__markRule(EXTERNAL_TABLE_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
			} else {
			  throw ex;
			}
		}
	}
	
	public void ext_table_properties() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
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
			case LITERAL_reject:
			{
				reject_spec();
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
			if ( inputState.guessing==0 ) {
				__markRule(REJECT_SPEC);
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
	}
	
	public void storage_params() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
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
			if ( inputState.guessing==0 ) {
				__markRule(STORAGE_PARAM);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_157);
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
			case LITERAL_disable:
			case LITERAL_enable:
			{
				enable_disable_clause();
				break;
			}
			case COMMA:
			case CLOSE_PAREN:
			case LITERAL_using:
			case LITERAL_rely:
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
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
			case LITERAL_enable:
			{
				enable_disable_clause();
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
			switch ( LA(1)) {
			case LITERAL_on:
			{
				match(LITERAL_on);
				{
				switch ( LA(1)) {
				case LITERAL_delete:
				{
					match(LITERAL_delete);
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
			} else {
			  throw ex;
			}
		}
	}
	
	public void check_condition() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_check);
			condition();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
			} else {
			  throw ex;
			}
		}
	}
	
	public void unique_constr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_unique);
			match(OPEN_PAREN);
			column_name_ref();
			{
			_loop623:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop623;
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
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
			} else {
			  throw ex;
			}
		}
	}
	
	public void owner_column_name_ref_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ref();
			{
			_loop631:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop631;
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
				recover(ex,_tokenSet_131);
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
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_158.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				{
				{
				if ((_tokenSet_5.member(LA(1))) && (LA(2)==DOT)) {
					schema_name();
					match(DOT);
				}
				else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_159.member(LA(2)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				identifier2();
				}
			}
			else if ((_tokenSet_160.member(LA(1))) && (_tokenSet_161.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				index_properties();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_disable||LA(1)==LITERAL_enable) && (_tokenSet_159.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				enable_disable_clause();
			}
			else if ((_tokenSet_159.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_159);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_name_ref_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ref();
			{
			_loop628:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_name_ref();
				}
				else {
					break _loop628;
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
				recover(ex,_tokenSet_131);
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
				recover(ex,_tokenSet_62);
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
			if ((_tokenSet_162.member(LA(1))) && (_tokenSet_163.member(LA(2))) && (_tokenSet_164.member(LA(3)))) {
				constraint_clause();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
	
	public void constraint_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_add:
			{
				{
				match(LITERAL_add);
				{
				if ((_tokenSet_165.member(LA(1)))) {
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
				switch ( LA(1)) {
				case LITERAL_constraint:
				case LITERAL_primary:
				case LITERAL_unique:
				{
					modify_constraint_clause();
					break;
				}
				case OPEN_PAREN:
				{
					{
					match(OPEN_PAREN);
					alter_column_def();
					{
					_loop647:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							alter_column_def();
						}
						else {
							break _loop647;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
					}
					break;
				}
				default:
					if ((_tokenSet_30.member(LA(1)))) {
						alter_column_def();
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
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
				case LITERAL_constraint:
				case LITERAL_primary:
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void add_syntax_1() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_30.member(LA(1)))) {
				alter_column_def();
			}
			else if ((_tokenSet_166.member(LA(1)))) {
				inline_out_of_line_constraint();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
	
	public void add_syntax_2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched670 = false;
			if (((_tokenSet_30.member(LA(1))))) {
				int _m670 = mark();
				synPredMatched670 = true;
				inputState.guessing++;
				try {
					{
					alter_column_def();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched670 = false;
				}
				rewind(_m670);
				inputState.guessing--;
			}
			if ( synPredMatched670 ) {
				{
				alter_column_def();
				{
				_loop673:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						alter_column_def();
					}
					else {
						break _loop673;
					}
					
				} while (true);
				}
				}
			}
			else if ((_tokenSet_166.member(LA(1)))) {
				inline_out_of_line_constraint();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
				constraint_name();
				{
				if ((LA(1)==LITERAL_rely) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_rely);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_disable) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_disable);
				}
				else if ((LA(1)==LITERAL_enable) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_enable);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_validate) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_validate);
				}
				else if ((LA(1)==LITERAL_novalidate) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_novalidate);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
					_loop663:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop663;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				_loop666:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop666;
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void alter_column_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_name_ddl();
			type_spec();
			{
			_loop676:
			do {
				if ((_tokenSet_61.member(LA(1))) && (_tokenSet_167.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					column_constraint();
				}
				else {
					break _loop676;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(A_COLUMN_DEF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_159);
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
				if ((LA(1)==LITERAL_cascade) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_cascade);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_32.member(LA(3)))) {
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
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				_loop712:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop712;
					}
					
				} while (true);
				}
				match(CLOSE_PAREN);
				{
				if ((LA(1)==LITERAL_cascade) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_cascade);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_drop||LA(1)==LITERAL_keep) && (LA(2)==LITERAL_index) && (_tokenSet_32.member(LA(3)))) {
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
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				constraint_name();
				{
				if ((LA(1)==LITERAL_cascade) && (_tokenSet_32.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_cascade);
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
	}
	
	public void inline_out_of_line_constraint() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			{
			switch ( LA(1)) {
			case LITERAL_constraint:
			{
				match(LITERAL_constraint);
				constraint_name();
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
					_loop684:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop684;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_71.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
					_loop688:
					do {
						if ((LA(1)==COMMA)) {
							match(COMMA);
							identifier2();
						}
						else {
							break _loop688;
						}
						
					} while (true);
					}
					match(CLOSE_PAREN);
				}
				else if ((_tokenSet_71.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				_loop692:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop692;
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
				_loop696:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop696;
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
				if ((LA(1)==LITERAL_validate) && (_tokenSet_168.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_validate);
				}
				else if ((LA(1)==LITERAL_novalidate) && (_tokenSet_168.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_novalidate);
				}
				else if ((_tokenSet_168.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_on)) {
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
				}
				else if ((_tokenSet_71.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
			if ((LA(1)==LITERAL_using||LA(1)==LITERAL_rely) && (LA(2)==LITERAL_index||LA(2)==LITERAL_using) && (_tokenSet_169.member(LA(3)))) {
				using_index_clause();
			}
			else if ((_tokenSet_28.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(ALTER_TABLE_CONSTRAINT);
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
				int _cnt727=0;
				_loop727:
				do {
					if ((_tokenSet_170.member(LA(1))) && (_tokenSet_171.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						index_attributes();
					}
					else {
						if ( _cnt727>=1 ) { break _loop727; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt727++;
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
				recover(ex,_tokenSet_159);
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
				if ((_tokenSet_5.member(LA(1)))) {
					tablespace_name();
				}
				else if ((LA(1)==LITERAL_default)) {
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
				recover(ex,_tokenSet_172);
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
				recover(ex,_tokenSet_159);
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
				partition_name();
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
			_loop741:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					numeric_literal();
				}
				else {
					break _loop741;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			segment_attributes_clause();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
			_loop746:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					local_partition_item();
				}
				else {
					break _loop746;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
			} else {
			  throw ex;
			}
		}
	}
	
	public void local_partition_item() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			{
			if ((_tokenSet_5.member(LA(1))) && (_tokenSet_173.member(LA(2))) && (_tokenSet_154.member(LA(3)))) {
				partition_name();
			}
			else if ((_tokenSet_173.member(LA(1))) && (_tokenSet_154.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop750:
			do {
				if ((_tokenSet_174.member(LA(1))) && (_tokenSet_175.member(LA(2))) && (_tokenSet_176.member(LA(3)))) {
					segment_attributes_clause();
				}
				else if ((LA(1)==LITERAL_compress||LA(1)==LITERAL_nocompress) && (_tokenSet_177.member(LA(2))) && (_tokenSet_176.member(LA(3)))) {
					table_compression();
				}
				else {
					break _loop750;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_178);
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
				recover(ex,_tokenSet_179);
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
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_180);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_spec_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_99.member(LA(1)))) {
				package_obj_spec();
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
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_spec() throws RecognitionException, TokenStreamException {
		
		Integer retVal = -1;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_subtype:
			{
				subtype_declaration();
				break;
			}
			case LITERAL_cursor:
			{
				cursor_spec();
				break;
			}
			case LITERAL_type:
			{
				type_definition();
				break;
			}
			case LITERAL_procedure:
			{
				{
				retVal=procedure_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
				break;
			}
			case LITERAL_function:
			{
				{
				retVal=function_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
				break;
			}
			case LITERAL_pragma:
			{
				pragmas();
				break;
			}
			default:
				if ((_tokenSet_181.member(LA(1))) && (_tokenSet_182.member(LA(2)))) {
					variable_declaration();
				}
				else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
					exception_declaration();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_183);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cond_comp_seq() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop809:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
				}
				else {
					break _loop809;
				}
				
			} while (true);
			}
			{
			_loop811:
			do {
				if ((_tokenSet_99.member(LA(1)))) {
					package_obj_spec();
				}
				else {
					break _loop811;
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
				recover(ex,_tokenSet_184);
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
				recover(ex,_tokenSet_185);
			} else {
			  throw ex;
			}
		}
	}
	
	public void package_obj_body() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_99.member(LA(1)))) {
				package_obj_spec();
			}
			else if ((LA(1)==IF_COND_CMPL)) {
				condition_compilation();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
				recover(ex,_tokenSet_186);
			} else {
			  throw ex;
			}
		}
	}
	
	public void seq_of_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt870=0;
			_loop870:
			do {
				if ((_tokenSet_187.member(LA(1)))) {
					statement_tmpl();
				}
				else {
					if ( _cnt870>=1 ) { break _loop870; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt870++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(STATEMENT_LIST);
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
	
	public void subtype_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_subtype);
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_is) && (_tokenSet_189.member(LA(3)))) {
				type_name();
			}
			else if ((_tokenSet_64.member(LA(1))) && (_tokenSet_190.member(LA(2))) && (_tokenSet_191.member(LA(3)))) {
				datatype();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(LITERAL_is);
			{
			boolean synPredMatched859 = false;
			if (((_tokenSet_189.member(LA(1))) && (_tokenSet_192.member(LA(2))) && (_tokenSet_193.member(LA(3))))) {
				int _m859 = mark();
				synPredMatched859 = true;
				inputState.guessing++;
				try {
					{
					type_spec();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched859 = false;
				}
				rewind(_m859);
				inputState.guessing--;
			}
			if ( synPredMatched859 ) {
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
				recover(ex,_tokenSet_183);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cursor_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cursor);
			cursor_name();
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				argument_list();
				break;
			}
			case LITERAL_is:
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
			{
			switch ( LA(1)) {
			case LITERAL_is:
			{
				{
				match(LITERAL_is);
				select_statement();
				}
				if ( inputState.guessing==0 ) {
					__markRule(CURSOR_DECLARATION);
				}
				break;
			}
			case LITERAL_return:
			{
				{
				match(LITERAL_return);
				return_type();
				}
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
				recover(ex,_tokenSet_183);
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
				int _cnt830=0;
				_loop830:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier3();
					}
					else {
						if ( _cnt830>=1 ) { break _loop830; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt830++;
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
				int _cnt833=0;
				_loop833:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier3();
					}
					else {
						if ( _cnt833>=1 ) { break _loop833; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt833++;
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
				int _cnt836=0;
				_loop836:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						plsql_expression();
					}
					else {
						if ( _cnt836>=1 ) { break _loop836; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt836++;
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
				int _cnt839=0;
				_loop839:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						plsql_expression();
					}
					else {
						if ( _cnt839>=1 ) { break _loop839; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt839++;
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
				recover(ex,_tokenSet_183);
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
			else if ((_tokenSet_189.member(LA(1)))) {
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
			else if ((_tokenSet_194.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==ASSIGNMENT_EQ||LA(1)==LITERAL_default)) {
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
			else if ((_tokenSet_195.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==SEMI)) {
				match(SEMI);
			}
			else if ((_tokenSet_183.member(LA(1)))) {
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
				recover(ex,_tokenSet_183);
			} else {
			  throw ex;
			}
		}
	}
	
	public void complex_name() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			identifier();
			{
			_loop1203:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					identifier2();
				}
				else {
					break _loop1203;
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
				recover(ex,_tokenSet_196);
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
			_loop846:
			do {
				if ((LA(1)==ERROR_COND_CMPL)) {
					error_cond_compliation();
				}
				else {
					break _loop846;
				}
				
			} while (true);
			}
			{
			_loop848:
			do {
				if ((_tokenSet_99.member(LA(1)))) {
					package_obj_spec();
				}
				else {
					break _loop848;
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
				recover(ex,_tokenSet_184);
			} else {
			  throw ex;
			}
		}
	}
	
	public void variable_name() throws RecognitionException, TokenStreamException {
		
		
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
			case LITERAL_execute:
			{
				match(LITERAL_execute);
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
			case LITERAL_decode:
			{
				match(LITERAL_decode);
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
			case LITERAL_timestamp:
			{
				match(LITERAL_timestamp);
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
			case LITERAL_link:
			{
				match(LITERAL_link);
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
			case LITERAL_initial:
			{
				match(LITERAL_initial);
				break;
			}
			case LITERAL_minextents:
			{
				match(LITERAL_minextents);
				break;
			}
			case LITERAL_maxextents:
			{
				match(LITERAL_maxextents);
				break;
			}
			case LITERAL_pctincrease:
			{
				match(LITERAL_pctincrease);
				break;
			}
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
			case LITERAL_rownum:
			{
				match(LITERAL_rownum);
				break;
			}
			case LITERAL_rowid:
			{
				match(LITERAL_rowid);
				break;
			}
			case LITERAL_quota:
			{
				match(LITERAL_quota);
				break;
			}
			case LITERAL_profile:
			{
				match(LITERAL_profile);
				break;
			}
			case LITERAL_password:
			{
				match(LITERAL_password);
				break;
			}
			case LITERAL_expire:
			{
				match(LITERAL_expire);
				break;
			}
			case LITERAL_account:
			{
				match(LITERAL_account);
				break;
			}
			case LITERAL_lock:
			{
				match(LITERAL_lock);
				break;
			}
			case LITERAL_unlock:
			{
				match(LITERAL_unlock);
				break;
			}
			case LITERAL_privileges:
			{
				match(LITERAL_privileges);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(VARIABLE_NAME);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_182);
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
				recover(ex,_tokenSet_79);
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
				recover(ex,_tokenSet_197);
			} else {
			  throw ex;
			}
		}
	}
	
	public void argument_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			{
			if ((_tokenSet_5.member(LA(1)))) {
				parameter_spec();
				{
				_loop1162:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						parameter_spec();
					}
					else {
						break _loop1162;
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
				__markRule(ARGUMENT_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_198);
			} else {
			  throw ex;
			}
		}
	}
	
	public void select_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3)))) {
				select_expression();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_26.member(LA(3)))) {
				subquery();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_199);
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
				recover(ex,_tokenSet_200);
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
				if ((_tokenSet_201.member(LA(1)))) {
					statement();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
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
				{
				begin_block();
				match(SEMI);
				}
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
				{
				if_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(IF_STATEMENT);
				}
				break;
			}
			case LITERAL_goto:
			{
				{
				goto_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(GOTO_STATEMENT);
				}
				break;
			}
			case LITERAL_raise:
			{
				{
				raise_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(RAISE_STATEMENT);
				}
				break;
			}
			case LITERAL_exit:
			{
				{
				exit_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(EXIT_STATEMENT);
				}
				break;
			}
			case LITERAL_null:
			{
				{
				match(LITERAL_null);
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(NULL_STATEMENT);
				}
				break;
			}
			case LITERAL_return:
			{
				{
				match(LITERAL_return);
				{
				if ((_tokenSet_38.member(LA(1)))) {
					condition();
				}
				else if ((LA(1)==SEMI)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(RETURN_STATEMENT);
				}
				break;
			}
			case LITERAL_case:
			{
				{
				case_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(CASE_STATEMENT);
				}
				break;
			}
			case OPEN_PAREN:
			case LITERAL_select:
			{
				{
				select_command();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_202.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				break;
			}
			case LITERAL_insert:
			{
				insert_command();
				break;
			}
			case LITERAL_update:
			{
				{
				update_command();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_202.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(UPDATE_COMMAND);
				}
				break;
			}
			case LITERAL_merge:
			{
				{
				merge_command();
				{
				if ((LA(1)==SEMI)) {
					match(SEMI);
				}
				else if ((_tokenSet_202.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
				if ( inputState.guessing==0 ) {
					__markRule(MERGE_COMMAND);
				}
				break;
			}
			case LITERAL_fetch:
			{
				{
				fetch_statement();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(FETCH_STATEMENT);
				}
				break;
			}
			case LITERAL_set:
			{
				{
				set_transaction_command();
				match(SEMI);
				}
				if ( inputState.guessing==0 ) {
					__markRule(SET_TRN_STATEMENT);
				}
				break;
			}
			case LITERAL_alter:
			{
				alter_command();
				break;
			}
			default:
				boolean synPredMatched880 = false;
				if (((_tokenSet_203.member(LA(1))) && (_tokenSet_204.member(LA(2))) && (_tokenSet_205.member(LA(3))))) {
					int _m880 = mark();
					synPredMatched880 = true;
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
						synPredMatched880 = false;
					}
					rewind(_m880);
					inputState.guessing--;
				}
				if ( synPredMatched880 ) {
					{
					loop_statement();
					match(SEMI);
					}
					if ( inputState.guessing==0 ) {
						__markRule(LOOP_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_delete) && (_tokenSet_206.member(LA(2))) && (_tokenSet_207.member(LA(3)))) {
					{
					delete_command();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(DELETE_COMMAND);
					}
				}
				else if ((LA(1)==LITERAL_grant) && (_tokenSet_208.member(LA(2))) && (LA(3)==COMMA||LA(3)==LITERAL_on||LA(3)==LITERAL_privileges)) {
					{
					grant_command();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(GRANT_COMMAND);
					}
				}
				else if ((LA(1)==LITERAL_revoke) && (_tokenSet_208.member(LA(2))) && (LA(3)==COMMA||LA(3)==LITERAL_on||LA(3)==LITERAL_privileges)) {
					{
					revoke_command();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(REVOKE_COMMAND);
					}
				}
				else if ((LA(1)==LITERAL_rollback) && (_tokenSet_209.member(LA(2))) && (_tokenSet_210.member(LA(3)))) {
					{
					rollback_statement();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(ROLLBACK_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_commit) && (_tokenSet_211.member(LA(2))) && (_tokenSet_210.member(LA(3)))) {
					{
					commit_statement();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
					if ( inputState.guessing==0 ) {
						__markRule(COMMIT_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_execute) && (LA(2)==LITERAL_immediate) && (_tokenSet_79.member(LA(3)))) {
					{
					immediate_command();
					match(SEMI);
					}
					if ( inputState.guessing==0 ) {
						__markRule(IMMEDIATE_COMMAND);
					}
				}
				else if ((LA(1)==LITERAL_lock) && (LA(2)==LITERAL_table)) {
					{
					lock_table_statement();
					match(SEMI);
					}
					if ( inputState.guessing==0 ) {
						__markRule(LOCK_TABLE_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_open) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (_tokenSet_212.member(LA(3)))) {
					{
					open_statement();
					match(SEMI);
					}
					if ( inputState.guessing==0 ) {
						__markRule(OPEN_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_close) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (LA(3)==SEMI)) {
					{
					close_statement();
					match(SEMI);
					}
					if ( inputState.guessing==0 ) {
						__markRule(CLOSE_STATEMENT);
					}
				}
				else if ((LA(1)==LITERAL_savepoint) && (LA(2)==IDENTIFIER||LA(2)==DOUBLE_QUOTED_STRING) && (_tokenSet_213.member(LA(3)))) {
					{
					savepoint_statement();
					{
					if ((LA(1)==SEMI)) {
						match(SEMI);
					}
					else if ((_tokenSet_202.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
				}
				else {
					boolean synPredMatched915 = false;
					if (((_tokenSet_92.member(LA(1))) && (_tokenSet_94.member(LA(2))) && (_tokenSet_95.member(LA(3))))) {
						int _m915 = mark();
						synPredMatched915 = true;
						inputState.guessing++;
						try {
							{
							plsql_lvalue();
							match(ASSIGNMENT_EQ);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched915 = false;
						}
						rewind(_m915);
						inputState.guessing--;
					}
					if ( synPredMatched915 ) {
						{
						assignment_statement();
						{
						if ((LA(1)==SEMI)) {
							match(SEMI);
						}
						else if ((_tokenSet_202.member(LA(1)))) {
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						
						}
						}
						if ( inputState.guessing==0 ) {
							__markRule(ASSIGNMENT_STATEMENT);
						}
					}
					else {
						boolean synPredMatched921 = false;
						if (((_tokenSet_33.member(LA(1))) && (_tokenSet_214.member(LA(2))) && (_tokenSet_210.member(LA(3))))) {
							int _m921 = mark();
							synPredMatched921 = true;
							inputState.guessing++;
							try {
								{
								{
								_loop920:
								do {
									if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_33.member(LA(3)))) {
										name_fragment_ex();
										match(DOT);
									}
									else {
										break _loop920;
									}
									
								} while (true);
								}
								exec_name_ref();
								matchNot(OPEN_PAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched921 = false;
							}
							rewind(_m921);
							inputState.guessing--;
						}
						if ( synPredMatched921 ) {
							{
							callable_name_ref();
							{
							if ((LA(1)==SEMI)) {
								match(SEMI);
							}
							else if ((_tokenSet_202.member(LA(1)))) {
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
							}
							if ( inputState.guessing==0 ) {
								__markRule(PROCEDURE_CALL);
							}
						}
						else if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
							{
							procedure_call();
							{
							_loop926:
							do {
								if ((LA(1)==DOT)) {
									match(DOT);
									if ( inputState.guessing==0 ) {
										tag1=true;
									}
									procedure_call();
								}
								else {
									break _loop926;
								}
								
							} while (true);
							}
							{
							if ((LA(1)==SEMI)) {
								match(SEMI);
							}
							else if ((_tokenSet_202.member(LA(1)))) {
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
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
					}}}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						reportError(ex);
						recover(ex,_tokenSet_202);
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
				recover(ex,_tokenSet_215);
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
				boolean synPredMatched1190 = false;
				if (((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (_tokenSet_79.member(LA(3))))) {
					int _m1190 = mark();
					synPredMatched1190 = true;
					inputState.guessing++;
					try {
						{
						numeric_loop_spec();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1190 = false;
					}
					rewind(_m1190);
					inputState.guessing--;
				}
				if ( synPredMatched1190 ) {
					numeric_loop_spec();
				}
				else if ((_tokenSet_5.member(LA(1))) && (LA(2)==LITERAL_in) && (_tokenSet_216.member(LA(3)))) {
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
				recover(ex,_tokenSet_180);
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
			else if ((_tokenSet_201.member(LA(1)))) {
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
				recover(ex,_tokenSet_202);
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
			_loop1411:
			do {
				if ((LA(1)==LITERAL_elsif)) {
					elsif_statements();
				}
				else {
					break _loop1411;
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
				recover(ex,_tokenSet_180);
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
				recover(ex,_tokenSet_180);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exit_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
				recover(ex,_tokenSet_180);
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
			boolean synPredMatched941 = false;
			if (((_tokenSet_79.member(LA(1))))) {
				int _m941 = mark();
				synPredMatched941 = true;
				inputState.guessing++;
				try {
					{
					plsql_expression();
					{
					int _cnt940=0;
					_loop940:
					do {
						if ((LA(1)==LITERAL_when)) {
							match(LITERAL_when);
							plsql_expression();
							match(LITERAL_then);
							seq_of_statements();
						}
						else {
							if ( _cnt940>=1 ) { break _loop940; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt940++;
					} while (true);
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched941 = false;
				}
				rewind(_m941);
				inputState.guessing--;
			}
			if ( synPredMatched941 ) {
				plsql_expression();
				{
				int _cnt943=0;
				_loop943:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						plsql_expression();
						match(LITERAL_then);
						seq_of_statements();
					}
					else {
						if ( _cnt943>=1 ) { break _loop943; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt943++;
				} while (true);
				}
			}
			else if ((LA(1)==LITERAL_when)) {
				{
				int _cnt945=0;
				_loop945:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						condition();
						t = LT(1);
						match(LITERAL_then);
						seq_of_statements();
					}
					else {
						if ( _cnt945>=1 ) { break _loop945; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt945++;
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
	
	public void immediate_command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_execute);
			match(LITERAL_immediate);
			{
			boolean synPredMatched1466 = false;
			if (((_tokenSet_92.member(LA(1))) && (_tokenSet_217.member(LA(2))) && (_tokenSet_218.member(LA(3))))) {
				int _m1466 = mark();
				synPredMatched1466 = true;
				inputState.guessing++;
				try {
					{
					plsql_lvalue();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1466 = false;
				}
				rewind(_m1466);
				inputState.guessing--;
			}
			if ( synPredMatched1466 ) {
				plsql_lvalue();
			}
			else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_219.member(LA(2))) && (_tokenSet_220.member(LA(3)))) {
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
	
	public void lock_table_statement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_lock);
			match(LITERAL_table);
			table_reference_list();
			match(LITERAL_in);
			lock_mode();
			match(LITERAL_mode);
			{
			switch ( LA(1)) {
			case LITERAL_nowait:
			{
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
				recover(ex,_tokenSet_180);
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
				if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3)))) {
					select_expression();
				}
				else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_221.member(LA(2))) && (_tokenSet_222.member(LA(3)))) {
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
				else if ((_tokenSet_92.member(LA(1)))) {
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
				recover(ex,_tokenSet_180);
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
				recover(ex,_tokenSet_180);
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
			_loop1648:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					variable_ref();
				}
				else {
					break _loop1648;
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
				recover(ex,_tokenSet_180);
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
				recover(ex,_tokenSet_213);
			} else {
			  throw ex;
			}
		}
	}
	
	public void name_fragment_ex() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				match(LITERAL_primary);
				break;
			}
			case LITERAL_foreign:
			{
				match(LITERAL_foreign);
				break;
			}
			default:
				if ((_tokenSet_47.member(LA(1)))) {
					identifier3();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				__markRule(NAME_FRAGMENT);
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
				recover(ex,_tokenSet_32);
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
				recover(ex,_tokenSet_82);
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
				__markRule(AUTONOMOUS_TRN_PRAGMA);
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
	
	public void rvalue() throws RecognitionException, TokenStreamException {
		
		boolean tag1=false;
		
		try {      // for error handling
			boolean synPredMatched954 = false;
			if (((_tokenSet_223.member(LA(1))) && (_tokenSet_90.member(LA(2))) && (_tokenSet_3.member(LA(3))))) {
				int _m954 = mark();
				synPredMatched954 = true;
				inputState.guessing++;
				try {
					{
					{
					if ((LA(1)==LITERAL_prior)) {
						match(LITERAL_prior);
					}
					else if ((_tokenSet_30.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					_loop953:
					do {
						if ((_tokenSet_30.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_30.member(LA(3)))) {
							name_fragment2();
							match(DOT);
						}
						else {
							break _loop953;
						}
						
					} while (true);
					}
					name_fragment2();
					matchNot(OPEN_PAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched954 = false;
				}
				rewind(_m954);
				inputState.guessing--;
			}
			if ( synPredMatched954 ) {
				{
				{
				if ((LA(1)==LITERAL_prior)) {
					match(LITERAL_prior);
				}
				else if ((_tokenSet_30.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop958:
				do {
					if ((_tokenSet_30.member(LA(1))) && (LA(2)==DOT)) {
						name_fragment2();
						match(DOT);
					}
					else {
						break _loop958;
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
				boolean synPredMatched961 = false;
				if (((LA(1)==COLON))) {
					int _m961 = mark();
					synPredMatched961 = true;
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
						synPredMatched961 = false;
					}
					rewind(_m961);
					inputState.guessing--;
				}
				if ( synPredMatched961 ) {
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
				else if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
					{
					function_call();
					{
					_loop969:
					do {
						if ((LA(1)==DOT)) {
							match(DOT);
							if ( inputState.guessing==0 ) {
								tag1=true;
							}
							{
							boolean synPredMatched968 = false;
							if (((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3))))) {
								int _m968 = mark();
								synPredMatched968 = true;
								inputState.guessing++;
								try {
									{
									function_call();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched968 = false;
								}
								rewind(_m968);
								inputState.guessing--;
							}
							if ( synPredMatched968 ) {
								function_call();
							}
							else if ((_tokenSet_5.member(LA(1))) && (_tokenSet_90.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
								c_record_item_ref();
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
						}
						else {
							break _loop969;
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
					recover(ex,_tokenSet_82);
				} else {
				  throw ex;
				}
			}
		}
		
	public void name_fragment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_primary:
			{
				match(LITERAL_primary);
				break;
			}
			case LITERAL_foreign:
			{
				match(LITERAL_foreign);
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
				__markRule(NAME_FRAGMENT);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_224);
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
				recover(ex,_tokenSet_57);
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
				recover(ex,_tokenSet_57);
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
				recover(ex,_tokenSet_103);
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
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_68);
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
				recover(ex,_tokenSet_68);
			} else {
			  throw ex;
			}
		}
	}
	
	public void type_name_ref_single() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			name_fragment();
			if ( inputState.guessing==0 ) {
				__markRule(TYPE_NAME_REF);
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
				recover(ex,_tokenSet_51);
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
			else if ((_tokenSet_225.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_out)) {
				match(LITERAL_out);
			}
			else if ((_tokenSet_226.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_nocopy)) {
				match(LITERAL_nocopy);
			}
			else if ((_tokenSet_189.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_ref) && (_tokenSet_189.member(LA(2))) && (_tokenSet_227.member(LA(3)))) {
				match(LITERAL_ref);
			}
			else if ((_tokenSet_189.member(LA(1))) && (_tokenSet_227.member(LA(2))) && (_tokenSet_228.member(LA(3)))) {
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
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_229);
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
				recover(ex,_tokenSet_230);
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
	
	public void procedure_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_procedure);
			object_name();
			{
			{
			if ((LA(1)==OPEN_PAREN) && (_tokenSet_231.member(LA(2))) && (_tokenSet_232.member(LA(3)))) {
				argument_list();
			}
			else if ((_tokenSet_233.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_233);
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
	
	public void function_declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_function);
			object_name();
			{
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				argument_list();
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
			if ((LA(1)==LITERAL_character)) {
				character_set();
			}
			else if ((_tokenSet_234.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_pipelined)) {
				match(LITERAL_pipelined);
			}
			else if ((_tokenSet_235.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_parallel_enable)) {
				match(LITERAL_parallel_enable);
			}
			else if ((_tokenSet_236.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_using)) {
				match(LITERAL_using);
				identifier2();
			}
			else if ((_tokenSet_233.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_deterministic) && (_tokenSet_233.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				match(LITERAL_deterministic);
			}
			else if ((_tokenSet_233.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_233);
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
				recover(ex,_tokenSet_237);
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
				recover(ex,_tokenSet_238);
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
				recover(ex,_tokenSet_20);
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
				recover(ex,_tokenSet_189);
			} else {
			  throw ex;
			}
		}
	}
	
	public void func_proc_statements() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_99.member(LA(1)))) {
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
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
	}
	
	public void declare_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1125=0;
			_loop1125:
			do {
				if ((_tokenSet_99.member(LA(1)))) {
					declare_spec();
				}
				else {
					if ( _cnt1125>=1 ) { break _loop1125; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1125++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(DECLARE_LIST);
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
	
	public void plsql_block() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_begin);
			{
			if ((_tokenSet_187.member(LA(1)))) {
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
			plsql_block_end();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_section() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_exception);
			{
			int _cnt1122=0;
			_loop1122:
			do {
				if ((LA(1)==LITERAL_when)) {
					exception_handler();
				}
				else {
					if ( _cnt1122>=1 ) { break _loop1122; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1122++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_SECTION);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_186);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_block_end() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_end);
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case DOUBLE_QUOTED_STRING:
			{
				identifier();
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
			if ( inputState.guessing==0 ) {
				__markRule(PLSQL_BLOCK_END);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exception_handler() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_when);
			exception_name();
			{
			_loop1133:
			do {
				if ((LA(1)==LITERAL_or)) {
					match(LITERAL_or);
					exception_name();
				}
				else {
					break _loop1133;
				}
				
			} while (true);
			}
			match(LITERAL_then);
			seq_of_statements();
			if ( inputState.guessing==0 ) {
				__markRule(EXCEPTION_HANDLER);
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
	
	public void declare_spec() throws RecognitionException, TokenStreamException {
		
		Integer retVal = -1;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_type:
			{
				type_definition();
				break;
			}
			case LITERAL_cursor:
			{
				cursor_spec();
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
				retVal=function_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
				break;
			}
			case LITERAL_procedure:
			{
				{
				retVal=procedure_body();
				}
				if ( inputState.guessing==0 ) {
					__markRule(retVal);
				}
				break;
			}
			default:
				if ((_tokenSet_181.member(LA(1))) && (_tokenSet_182.member(LA(2)))) {
					variable_declaration();
				}
				else {
					boolean synPredMatched1128 = false;
					if (((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_autonomous_transaction))) {
						int _m1128 = mark();
						synPredMatched1128 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_pragma);
							match(LITERAL_autonomous_transaction);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1128 = false;
						}
						rewind(_m1128);
						inputState.guessing--;
					}
					if ( synPredMatched1128 ) {
						pragma_autonomous_transaction();
					}
					else if ((LA(1)==LITERAL_pragma) && (LA(2)==LITERAL_exception_init)) {
						exception_pragma();
					}
					else if ((LA(1)==IDENTIFIER||LA(1)==DOUBLE_QUOTED_STRING) && (LA(2)==DOT||LA(2)==LITERAL_exception)) {
						exception_declaration();
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
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
		
	public void call_argument_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			{
			if ((_tokenSet_38.member(LA(1)))) {
				call_argument();
				{
				_loop1505:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						call_argument();
					}
					else {
						break _loop1505;
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
				recover(ex,_tokenSet_57);
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
				recover(ex,_tokenSet_82);
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
			if ((_tokenSet_241.member(LA(1))) && (_tokenSet_242.member(LA(2)))) {
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
				recover(ex,_tokenSet_243);
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
				recover(ex,_tokenSet_244);
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
			else if ((_tokenSet_92.member(LA(1)))) {
				plsql_lvalue();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
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
	
	public void numeric_loop_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			num_loop_index();
			match(LITERAL_in);
			{
			if ((LA(1)==LITERAL_reverse) && (_tokenSet_79.member(LA(2))) && (_tokenSet_246.member(LA(3)))) {
				match(LITERAL_reverse);
			}
			else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_246.member(LA(2))) && (_tokenSet_247.member(LA(3)))) {
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
				recover(ex,_tokenSet_248);
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
				recover(ex,_tokenSet_248);
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
				recover(ex,_tokenSet_248);
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
				recover(ex,_tokenSet_244);
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
				select_statement();
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
				recover(ex,_tokenSet_248);
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
				recover(ex,_tokenSet_249);
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void integer_expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_79.member(LA(1))) && (_tokenSet_96.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
			}
			else if ((_tokenSet_189.member(LA(1))) && (_tokenSet_250.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				type_spec();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_97);
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
			_loop1214:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS) && (_tokenSet_79.member(LA(2))) && (_tokenSet_96.member(LA(3)))) {
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
					break _loop1214;
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
				recover(ex,_tokenSet_97);
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
			_loop1218:
			do {
				if ((LA(1)==ASTERISK||LA(1)==DIVIDE) && (_tokenSet_79.member(LA(2))) && (_tokenSet_96.member(LA(3)))) {
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
					break _loop1218;
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
				recover(ex,_tokenSet_97);
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
			if ((LA(1)==746)) {
				match(746);
				integer_expr();
			}
			else if ((_tokenSet_97.member(LA(1)))) {
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
				else if ((_tokenSet_97.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				else if ((_tokenSet_97.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				}
			}
			else if ((_tokenSet_97.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_97);
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
			else if ((_tokenSet_76.member(LA(1)))) {
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
				recover(ex,_tokenSet_196);
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
				recover(ex,_tokenSet_76);
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
			if ((LA(1)==LITERAL_at) && (LA(2)==LITERAL_local||LA(2)==LITERAL_time) && (_tokenSet_251.member(LA(3)))) {
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
			else if ((_tokenSet_196.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_196);
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
				recover(ex,_tokenSet_196);
			} else {
			  throw ex;
			}
		}
	}
	
	public void expr_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			{
			_loop1238:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					break _loop1238;
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
				recover(ex,_tokenSet_28);
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
				recover(ex,_tokenSet_252);
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
			_loop1245:
			do {
				if ((LA(1)==LITERAL_and)) {
					match(LITERAL_and);
					if ( inputState.guessing==0 ) {
						tag1=true;
					}
					maybe_neg_factor();
				}
				else {
					break _loop1245;
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
				recover(ex,_tokenSet_253);
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
			else if ((_tokenSet_79.member(LA(1)))) {
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
				recover(ex,_tokenSet_252);
			} else {
			  throw ex;
			}
		}
	}
	
	public void plsql_expression33() throws RecognitionException, TokenStreamException {
		
		boolean tag1 = false;
		
		try {      // for error handling
			boolean synPredMatched1250 = false;
			if (((LA(1)==LITERAL_current) && (LA(2)==LITERAL_of))) {
				int _m1250 = mark();
				synPredMatched1250 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_current);
					match(LITERAL_of);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1250 = false;
				}
				rewind(_m1250);
				inputState.guessing--;
			}
			if ( synPredMatched1250 ) {
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
				boolean synPredMatched1253 = false;
				if (((LA(1)==LITERAL_exists) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m1253 = mark();
					synPredMatched1253 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_exists);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1253 = false;
					}
					rewind(_m1253);
					inputState.guessing--;
				}
				if ( synPredMatched1253 ) {
					match(LITERAL_exists);
					subquery();
					if ( inputState.guessing==0 ) {
						__markRule(EXISTS_EXPR);
					}
				}
				else {
					boolean synPredMatched1258 = false;
					if (((LA(1)==OPEN_PAREN) && (_tokenSet_79.member(LA(2))) && (_tokenSet_254.member(LA(3))))) {
						int _m1258 = mark();
						synPredMatched1258 = true;
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
							synPredMatched1258 = false;
						}
						rewind(_m1258);
						inputState.guessing--;
					}
					if ( synPredMatched1258 ) {
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
					else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_255.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
						default:
							boolean synPredMatched1267 = false;
							if (((LA(1)==LITERAL_not||LA(1)==LITERAL_in) && (_tokenSet_256.member(LA(2))) && (_tokenSet_257.member(LA(3))))) {
								int _m1267 = mark();
								synPredMatched1267 = true;
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
									synPredMatched1267 = false;
								}
								rewind(_m1267);
								inputState.guessing--;
							}
							if ( synPredMatched1267 ) {
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
								boolean synPredMatched1271 = false;
								if (((LA(1)==LITERAL_not||LA(1)==LITERAL_like) && (_tokenSet_258.member(LA(2))) && (_tokenSet_257.member(LA(3))))) {
									int _m1271 = mark();
									synPredMatched1271 = true;
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
										synPredMatched1271 = false;
									}
									rewind(_m1271);
									inputState.guessing--;
								}
								if ( synPredMatched1271 ) {
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
									if ((LA(1)==LITERAL_escape) && (_tokenSet_259.member(LA(2))) && (_tokenSet_260.member(LA(3)))) {
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
									}
									else if ((_tokenSet_252.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
									}
									else {
										throw new NoViableAltException(LT(1), getFilename());
									}
									
									}
									if ( inputState.guessing==0 ) {
										__markRule(LIKE_CONDITION);
									}
								}
								else {
									boolean synPredMatched1277 = false;
									if (((LA(1)==LITERAL_not||LA(1)==LITERAL_between) && (_tokenSet_261.member(LA(2))) && (_tokenSet_262.member(LA(3))))) {
										int _m1277 = mark();
										synPredMatched1277 = true;
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
											synPredMatched1277 = false;
										}
										rewind(_m1277);
										inputState.guessing--;
									}
									if ( synPredMatched1277 ) {
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
										boolean synPredMatched1283 = false;
										if (((LA(1)==LITERAL_not||LA(1)==LITERAL_member) && (LA(2)==LITERAL_of||LA(2)==LITERAL_member) && (_tokenSet_263.member(LA(3))))) {
											int _m1283 = mark();
											synPredMatched1283 = true;
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
												synPredMatched1283 = false;
											}
											rewind(_m1283);
											inputState.guessing--;
										}
										if ( synPredMatched1283 ) {
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
											if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT)) {
												name_fragment();
												match(DOT);
											}
											else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_252.member(LA(2)))) {
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
										else if ((_tokenSet_252.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
									recover(ex,_tokenSet_252);
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
				recover(ex,_tokenSet_264);
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
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
	}
	
	public void exp_set() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolean synPredMatched1564 = false;
			if (((LA(1)==OPEN_PAREN) && (LA(2)==OPEN_PAREN||LA(2)==LITERAL_select) && (_tokenSet_26.member(LA(3))))) {
				int _m1564 = mark();
				synPredMatched1564 = true;
				inputState.guessing++;
				try {
					{
					match(OPEN_PAREN);
					match(LITERAL_select);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1564 = false;
				}
				rewind(_m1564);
				inputState.guessing--;
			}
			if ( synPredMatched1564 ) {
				subquery();
			}
			else if ((LA(1)==OPEN_PAREN) && (_tokenSet_79.member(LA(2))) && (_tokenSet_254.member(LA(3)))) {
				parentesized_exp_list();
			}
			else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_257.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				plsql_expression();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_252);
			} else {
			  throw ex;
			}
		}
	}
	
	public void cast_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			cast_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(CAST_FUNC);
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
	}
	
	public void decode_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			decode_function_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(DECODE_FUNC);
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
	}
	
	public void trim_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			trim_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(TRIM_FUNC);
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
				boolean synPredMatched1373 = false;
				if (((_tokenSet_8.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1373 = mark();
					synPredMatched1373 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1373 = false;
					}
					rewind(_m1373);
					inputState.guessing--;
				}
				if ( synPredMatched1373 ) {
					ident_asterisk_column();
				}
				else if ((_tokenSet_265.member(LA(1))) && (_tokenSet_266.member(LA(2))) && (_tokenSet_267.member(LA(3)))) {
					{
					{
					if ((LA(1)==LITERAL_distinct)) {
						match(LITERAL_distinct);
					}
					else if ((_tokenSet_79.member(LA(1)))) {
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
					recover(ex,_tokenSet_82);
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
				int _cnt1405=0;
				_loop1405:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						condition();
						t = LT(1);
						match(LITERAL_then);
						plsql_expression();
					}
					else {
						if ( _cnt1405>=1 ) { break _loop1405; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1405++;
				} while (true);
				}
			}
			else if ((_tokenSet_79.member(LA(1)))) {
				plsql_expression();
				if ( inputState.guessing==0 ) {
					tag1 = true;
				}
				{
				int _cnt1407=0;
				_loop1407:
				do {
					if ((LA(1)==LITERAL_when)) {
						match(LITERAL_when);
						plsql_expression();
						match(LITERAL_then);
						plsql_expression();
					}
					else {
						if ( _cnt1407>=1 ) { break _loop1407; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt1407++;
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
				recover(ex,_tokenSet_82);
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lag_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			lag_lead_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(LAG_FUNCTION);
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
	}
	
	public void lead_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			lag_lead_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(LEAD_FUNCTION);
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
	}
	
	public void dence_rank_analytics_func() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			dence_rank__arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(RANK_FUNCTION);
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
	}
	
	public void extract_date_function() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			callable_name_ref2();
			extract_date_func_arg_list();
			if ( inputState.guessing==0 ) {
				__markRule(EXTRACT_DATE_FUNC);
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
				recover(ex,_tokenSet_82);
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
				if ((LA(1)==LITERAL_user) && (_tokenSet_82.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_user);
					if ( inputState.guessing==0 ) {
						__markRule(USER_CONST);
					}
				}
				else if ((LA(1)==LITERAL_dbtimezone) && (_tokenSet_82.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					match(LITERAL_dbtimezone);
					if ( inputState.guessing==0 ) {
						__markRule(DBTIMEZONE);
					}
				}
				else if ((_tokenSet_268.member(LA(1))) && (_tokenSet_90.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					if ((_tokenSet_30.member(LA(1)))) {
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void column_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_30.member(LA(1))) && (LA(2)==DOT)) {
				name_fragment2();
				match(DOT);
			}
			else if ((_tokenSet_30.member(LA(1))) && (_tokenSet_269.member(LA(2)))) {
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
				
					    throw ex;
					
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void callable_name_ref2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			exec_name_ref();
			if ( inputState.guessing==0 ) {
				__markRule(CALLABLE_NAME_REF);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_270);
			} else {
			  throw ex;
			}
		}
	}
	
	public void dence_rank__arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
				__markRule(SPEC_CALL_ARGUMENT_LIST);
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
	}
	
	public void query_partition_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_partition);
			match(LITERAL_by);
			plsql_expression();
			if ( inputState.guessing==0 ) {
				__markRule(QUERY_PARTITION_CLAUSE);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_271);
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
			_loop1576:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					sorted_def();
				}
				else {
					break _loop1576;
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
				recover(ex,_tokenSet_272);
			} else {
			  throw ex;
			}
		}
	}
	
	public void lag_lead_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			call_argument();
			{
			_loop1363:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					call_argument();
				}
				else {
					break _loop1363;
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void call_argument() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_8.member(LA(1))) && (LA(2)==PASS_BY_NAME)) {
				parameter_name_ref();
				match(PASS_BY_NAME);
			}
			else if ((_tokenSet_38.member(LA(1))) && (_tokenSet_273.member(LA(2)))) {
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
				recover(ex,_tokenSet_274);
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
				recover(ex,_tokenSet_274);
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
			call_argument();
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(SPEC_CALL_ARGUMENT_LIST);
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
	}
	
	public void extract_consts() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
			if ( inputState.guessing==0 ) {
				__markRule(EXTRACT_OPTIONS);
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
				if ((_tokenSet_38.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			call_argument();
			{
			switch ( LA(1)) {
			case LITERAL_from:
			{
				match(LITERAL_from);
				call_argument();
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void decode_function_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			{
			if ((_tokenSet_38.member(LA(1)))) {
				call_argument();
				{
				_loop1392:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						call_argument();
					}
					else {
						break _loop1392;
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
				__markRule(SPEC_CALL_ARGUMENT_LIST);
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
	}
	
	public void cast_func_arg_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			call_argument();
			match(LITERAL_as);
			{
			if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT||LA(2)==CLOSE_PAREN) && (_tokenSet_82.member(LA(3)))) {
				type_name_ref();
			}
			else if ((_tokenSet_64.member(LA(1))) && (_tokenSet_276.member(LA(2))) && (_tokenSet_277.member(LA(3)))) {
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
				recover(ex,_tokenSet_82);
			} else {
			  throw ex;
			}
		}
	}
	
	public void date_literal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt1398=0;
			_loop1398:
			do {
				if ((LA(1)==QUOTED_STR)) {
					match(QUOTED_STR);
				}
				else {
					if ( _cnt1398>=1 ) { break _loop1398; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1398++;
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
				recover(ex,_tokenSet_278);
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
				recover(ex,_tokenSet_186);
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
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_279);
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
				if ((LA(1)==LITERAL_where)) {
					where_condition();
				}
				else if ((_tokenSet_280.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_with||LA(2)==LITERAL_by) && (_tokenSet_38.member(LA(3)))) {
					connect_clause();
				}
				else if ((_tokenSet_281.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_group)) {
					group_clause();
				}
				else if ((_tokenSet_282.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_order)) {
					order_clause();
				}
				else if ((_tokenSet_272.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LITERAL_for) && (LA(2)==LITERAL_update) && (_tokenSet_283.member(LA(3)))) {
					update_clause();
				}
				else if ((_tokenSet_272.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				recover(ex,_tokenSet_272);
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
			_loop1455:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					displayed_column();
				}
				else {
					break _loop1455;
				}
				
			} while (true);
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
				recover(ex,_tokenSet_275);
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
			_loop1497:
			do {
				boolean synPredMatched1494 = false;
				if (((_tokenSet_287.member(LA(1))) && (_tokenSet_288.member(LA(2))) && (_tokenSet_289.member(LA(3))))) {
					int _m1494 = mark();
					synPredMatched1494 = true;
					inputState.guessing++;
					try {
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
						case LITERAL_inner:
						{
							match(LITERAL_inner);
							break;
						}
						case LITERAL_outer:
						{
							match(LITERAL_outer);
							break;
						}
						case LITERAL_join:
						{
							match(LITERAL_join);
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
					}
					catch (RecognitionException pe) {
						synPredMatched1494 = false;
					}
					rewind(_m1494);
					inputState.guessing--;
				}
				if ( synPredMatched1494 ) {
					ansi_spec();
				}
				else if ((LA(1)==COMMA)) {
					{
					match(COMMA);
					selected_table();
					{
					if ((LA(1)==LITERAL_partition)) {
						match(LITERAL_partition);
						match(OPEN_PAREN);
						partition_name();
						match(CLOSE_PAREN);
					}
					else if ((_tokenSet_290.member(LA(1)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
				}
				else {
					break _loop1497;
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
				recover(ex,_tokenSet_291);
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
				recover(ex,_tokenSet_280);
			} else {
			  throw ex;
			}
		}
	}
	
	public void connect_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			connect_clause_internal();
			{
			if ((LA(1)==LITERAL_start||LA(1)==LITERAL_connect) && (LA(2)==LITERAL_with||LA(2)==LITERAL_by) && (_tokenSet_38.member(LA(3)))) {
				connect_clause_internal();
			}
			else if ((_tokenSet_281.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_281);
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
			_loop1572:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_expression();
				}
				else {
					break _loop1572;
				}
				
			} while (true);
			}
			{
			if ((LA(1)==LITERAL_having)) {
				match(LITERAL_having);
				condition();
			}
			else if ((_tokenSet_282.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(GROUP_CLAUSE);
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
	
	public void update_clause() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_for);
			match(LITERAL_update);
			{
			if ((LA(1)==LITERAL_of)) {
				match(LITERAL_of);
				column_name_ref();
				{
				_loop1585:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_name_ref();
					}
					else {
						break _loop1585;
					}
					
				} while (true);
				}
			}
			else if ((_tokenSet_292.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_nowait)) {
				match(LITERAL_nowait);
			}
			else if ((LA(1)==LITERAL_wait) && (LA(2)==NUMBER) && (_tokenSet_272.member(LA(3)))) {
				{
				match(LITERAL_wait);
				numeric_literal();
				}
			}
			else if ((_tokenSet_272.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(FOR_UPDATE_CLAUSE);
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
	
	public void plsql_lvalue_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_lvalue();
			{
			_loop1451:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					plsql_lvalue();
				}
				else {
					break _loop1451;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_293);
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
				boolean synPredMatched1458 = false;
				if (((_tokenSet_8.member(LA(1))) && (LA(2)==DOT) && (LA(3)==ASTERISK))) {
					int _m1458 = mark();
					synPredMatched1458 = true;
					inputState.guessing++;
					try {
						{
						ident_asterisk_column();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1458 = false;
					}
					rewind(_m1458);
					inputState.guessing--;
				}
				if ( synPredMatched1458 ) {
					ident_asterisk_column();
				}
				else if ((_tokenSet_79.member(LA(1))) && (_tokenSet_285.member(LA(2))) && (_tokenSet_286.member(LA(3)))) {
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
					recover(ex,_tokenSet_294);
				} else {
				  throw ex;
				}
			}
		}
		
	public void expr_column() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			{
			if ((_tokenSet_295.member(LA(1)))) {
				alias();
			}
			else if ((_tokenSet_294.member(LA(1)))) {
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
				recover(ex,_tokenSet_294);
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
			else if ((_tokenSet_30.member(LA(1)))) {
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
				recover(ex,_tokenSet_296);
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
				if ((_tokenSet_79.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			plsql_expression();
			{
			_loop1474:
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
						if ((_tokenSet_79.member(LA(1)))) {
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					plsql_expression();
				}
				else {
					break _loop1474;
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
				recover(ex,_tokenSet_180);
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
				if ((LA(1)==LITERAL_sid) && (LA(2)==EQ) && (LA(3)==QUOTED_STR||LA(3)==ASTERISK)) {
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
				}
				else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
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
				recover(ex,_tokenSet_32);
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
				if ((_tokenSet_295.member(LA(1))) && (_tokenSet_297.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					alias();
				}
				else if ((_tokenSet_297.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				boolean synPredMatched1531 = false;
				if (((LA(1)==LITERAL_the) && (LA(2)==OPEN_PAREN) && (LA(3)==OPEN_PAREN||LA(3)==LITERAL_select))) {
					int _m1531 = mark();
					synPredMatched1531 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_the);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1531 = false;
					}
					rewind(_m1531);
					inputState.guessing--;
				}
				if ( synPredMatched1531 ) {
					the_proc();
					{
					if ((_tokenSet_295.member(LA(1))) && (_tokenSet_297.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
						alias();
					}
					else if ((_tokenSet_297.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
				}
				else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_298.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
					{
					table_alias();
					{
					if ((LA(1)==LITERAL_partition) && (LA(2)==OPEN_PAREN) && (_tokenSet_5.member(LA(3)))) {
						match(LITERAL_partition);
						match(OPEN_PAREN);
						partition_name();
						match(CLOSE_PAREN);
					}
					else if ((_tokenSet_297.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_297);
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
			if ((LA(1)==LITERAL_on)) {
				match(LITERAL_on);
				condition();
			}
			else if ((_tokenSet_290.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(ANSI_JOIN_TAB_SPEC);
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
	
	public void table_reference_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			selected_table();
			{
			_loop1500:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					selected_table();
				}
				else {
					break _loop1500;
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
				recover(ex,_tokenSet_244);
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
				recover(ex,_tokenSet_299);
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
				recover(ex,_tokenSet_296);
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
			if ((LA(1)==OPEN_PAREN||LA(1)==LITERAL_select)) {
				select_statement();
			}
			else if ((_tokenSet_47.member(LA(1))) && (LA(2)==OPEN_PAREN) && (_tokenSet_38.member(LA(3)))) {
				cast_function();
			}
			else {
				boolean synPredMatched1550 = false;
				if (((_tokenSet_8.member(LA(1))) && (LA(2)==DOT||LA(2)==CLOSE_PAREN) && (_tokenSet_300.member(LA(3))))) {
					int _m1550 = mark();
					synPredMatched1550 = true;
					inputState.guessing++;
					try {
						{
						{
						_loop1549:
						do {
							if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT) && (_tokenSet_8.member(LA(3)))) {
								name_fragment();
								match(DOT);
							}
							else {
								break _loop1549;
							}
							
						} while (true);
						}
						name_fragment();
						matchNot(OPEN_PAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1550 = false;
					}
					rewind(_m1550);
					inputState.guessing--;
				}
				if ( synPredMatched1550 ) {
					{
					_loop1552:
					do {
						if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT)) {
							name_fragment();
							match(DOT);
						}
						else {
							break _loop1552;
						}
						
					} while (true);
					}
					name_fragment();
				}
				else if ((_tokenSet_33.member(LA(1))) && (LA(2)==DOT||LA(2)==OPEN_PAREN) && (_tokenSet_83.member(LA(3)))) {
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
					recover(ex,_tokenSet_300);
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
				recover(ex,_tokenSet_300);
			} else {
			  throw ex;
			}
		}
	}
	
	public void from_subquery() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			subquery();
			{
			if ((_tokenSet_295.member(LA(1))) && (_tokenSet_297.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
			}
			else if ((_tokenSet_297.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_297);
			} else {
			  throw ex;
			}
		}
	}
	
	public void table_alias() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			table_spec();
			{
			if ((_tokenSet_295.member(LA(1))) && (_tokenSet_301.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				alias();
			}
			else if ((_tokenSet_301.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				recover(ex,_tokenSet_301);
			} else {
			  throw ex;
			}
		}
	}
	
	public void from_plain_table() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			table_spec();
			{
			if ((_tokenSet_295.member(LA(1)))) {
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
			else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_302.member(LA(2)))) {
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
				recover(ex,_tokenSet_302);
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
				recover(ex,_tokenSet_302);
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
				recover(ex,_tokenSet_280);
			} else {
			  throw ex;
			}
		}
	}
	
	public void sorted_def() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			plsql_expression();
			{
			if ((_tokenSet_303.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
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
				default:
					if ((_tokenSet_304.member(LA(1)))) {
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==LITERAL_nulls) && (LA(2)==LITERAL_first||LA(2)==LITERAL_last) && (_tokenSet_304.member(LA(3)))) {
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
				}
				else if ((_tokenSet_304.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else if ((_tokenSet_304.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(SORTED_DEF);
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
	
	public void column_spec_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			column_spec();
			{
			_loop1603:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
				}
				else {
					break _loop1603;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(COLUMN_SPEC_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_305);
			} else {
			  throw ex;
			}
		}
	}
	
	public void variable_ref() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop1653:
			do {
				if ((_tokenSet_8.member(LA(1))) && (LA(2)==DOT)) {
					name_fragment();
					match(DOT);
				}
				else {
					break _loop1653;
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
				recover(ex,_tokenSet_306);
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
			column_spec_list_wo_paren();
			match(LITERAL_into);
			expr_list();
			if ( inputState.guessing==0 ) {
				__markRule(RETURNING_CLAUSE);
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
	
	public void column_spec_list_wo_paren() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			column_spec();
			{
			_loop1606:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
				}
				else {
					break _loop1606;
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
				recover(ex,_tokenSet_307);
			} else {
			  throw ex;
			}
		}
	}
	
	public void subquery_update() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_update);
			{
			if ((_tokenSet_29.member(LA(1)))) {
				table_alias();
			}
			else if ((LA(1)==OPEN_PAREN)) {
				{
				subquery();
				{
				if ((_tokenSet_295.member(LA(1)))) {
					alias();
				}
				else if ((LA(1)==LITERAL_set)) {
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
			match(LITERAL_set);
			column_spec_list();
			match(EQ);
			subquery();
			{
			if ((LA(1)==LITERAL_where)) {
				where_condition();
			}
			else if ((_tokenSet_32.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(SUBQUERY_UPDATE_COMMAND);
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
	
	public void simple_update() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_update);
			{
			if ((_tokenSet_29.member(LA(1)))) {
				table_alias();
			}
			else if ((LA(1)==OPEN_PAREN)) {
				{
				subquery();
				{
				if ((_tokenSet_295.member(LA(1)))) {
					alias();
				}
				else if ((LA(1)==LITERAL_set)) {
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
			match(LITERAL_set);
			column_spec();
			match(EQ);
			plsql_expression();
			{
			_loop1629:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					column_spec();
					match(EQ);
					plsql_expression();
				}
				else {
					break _loop1629;
				}
				
			} while (true);
			}
			{
			if ((LA(1)==LITERAL_where)) {
				where_condition();
			}
			else if ((_tokenSet_37.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_return||LA(1)==LITERAL_returning) && (_tokenSet_30.member(LA(2))) && (LA(3)==DOT||LA(3)==COMMA||LA(3)==LITERAL_into)) {
				returning();
			}
			else if ((_tokenSet_32.member(LA(1))) && (_tokenSet_3.member(LA(2))) && (_tokenSet_3.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(SIMPLE_UPDATE_COMMAND);
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
				_loop1620:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						column_spec();
						match(EQ);
						plsql_expression();
					}
					else {
						break _loop1620;
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
			if ((LA(1)==LITERAL_where)) {
				where_condition();
			}
			else if ((_tokenSet_32.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				__markRule(MERGE_WHEN_COMMAND);
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
	
	public void insert_columns() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_insert);
			{
			switch ( LA(1)) {
			case OPEN_PAREN:
			{
				column_spec_list();
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
				recover(ex,_tokenSet_308);
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
				recover(ex,_tokenSet_309);
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
				recover(ex,_tokenSet_32);
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
				recover(ex,_tokenSet_310);
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
			loader_access_parameters();
			match(CLOSE_PAREN);
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
	
	public void write_access_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_access);
			match(LITERAL_parameters);
			match(OPEN_PAREN);
			write_access_parameters_spec();
			match(CLOSE_PAREN);
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
	
	public void location() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_location);
			match(OPEN_PAREN);
			{
			if (((LA(1) >= IDENTIFIER && LA(1) <= DOUBLE_QUOTED_STRING)) && (_tokenSet_312.member(LA(2))) && (_tokenSet_313.member(LA(3)))) {
				file_location_spec();
			}
			else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_313.member(LA(3)))) {
				string_literal();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop1809:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					{
					if (((LA(1) >= IDENTIFIER && LA(1) <= DOUBLE_QUOTED_STRING)) && (_tokenSet_312.member(LA(2))) && (_tokenSet_313.member(LA(3)))) {
						file_location_spec();
					}
					else if ((LA(1)==QUOTED_STR) && (LA(2)==QUOTED_STR||LA(2)==COMMA||LA(2)==CLOSE_PAREN) && (_tokenSet_313.member(LA(3)))) {
						string_literal();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
				}
				else {
					break _loop1809;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
			if ( inputState.guessing==0 ) {
				__markRule(EXT_TABLE_LOCATION_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
			} else {
			  throw ex;
			}
		}
	}
	
	public void write_access_parameters_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
			case LITERAL_version:
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
			if ( inputState.guessing==0 ) {
				__markRule(WRITE_ACCESS_PARAMS_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
				recover(ex,_tokenSet_314);
			} else {
			  throw ex;
			}
		}
	}
	
	public void loader_access_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
			if ( inputState.guessing==0 ) {
				__markRule(LOADER_ACCESS_PARAMS);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
			_loop1697:
			do {
				if ((_tokenSet_315.member(LA(1)))) {
					rec_format_spec();
				}
				else {
					break _loop1697;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_316);
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
				match(OPEN_PAREN);
				field_list();
				match(CLOSE_PAREN);
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
				recover(ex,_tokenSet_317);
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
			_loop1731:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					transform();
				}
				else {
					break _loop1731;
				}
				
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
				recover(ex,_tokenSet_318);
			} else {
			  throw ex;
			}
		}
	}
	
	public void rec_format_spec() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
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
			case LITERAL_data_cache:
			case LITERAL_skip:
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
			if ( inputState.guessing==0 ) {
				__markRule(RECORD_FMT_SPEC);
			}
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
				case LITERAL_ldrtrim:
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
					case LITERAL_ldrtrim:
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
				case LITERAL_ldrtrim:
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
				recover(ex,_tokenSet_319);
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
				recover(ex,_tokenSet_320);
			} else {
			  throw ex;
			}
		}
	}
	
	public void field_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			field_spec();
			{
			_loop1763:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					field_spec();
				}
				else {
					break _loop1763;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				__markRule(EXT_FIELD_SPEC_LIST);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_131);
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
				_loop1738:
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
						break _loop1738;
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
				_loop1745:
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
						break _loop1745;
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
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_321);
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
				_loop1750:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						identifier2();
					}
					else {
						break _loop1750;
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
				recover(ex,_tokenSet_62);
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
			case LITERAL_unsigned:
			case LITERAL_zoned:
			case LITERAL_oracle_date:
			case LITERAL_oracle_number:
			case LITERAL_varraw:
			case LITERAL_varcharc:
			case LITERAL_varrawc:
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
			case LITERAL_unsigned:
			case LITERAL_zoned:
			case LITERAL_oracle_date:
			case LITERAL_oracle_number:
			case LITERAL_varraw:
			case LITERAL_varcharc:
			case LITERAL_varrawc:
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
			if ( inputState.guessing==0 ) {
				__markRule(EXT_FIELD_SPEC);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_322);
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
				case LITERAL_enclosed:
				case LITERAL_terminated:
				case LITERAL_lrtrim:
				case LITERAL_notrim:
				case LITERAL_ltrim:
				case LITERAL_rtrim:
				case LITERAL_ldrtrim:
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
				case LITERAL_ldrtrim:
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
				recover(ex,_tokenSet_62);
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
				recover(ex,_tokenSet_62);
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
		"PLSQL_BLOCK_END",
		"CURSOR_DECLARATION",
		"VARIABLE_DECLARATION",
		"PROCEDURE_BODY",
		"FUNCTION_BODY",
		"PARAMETER_SPEC",
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
		"QUERY_PARTITION_CLAUSE",
		"EXTRACT_OPTIONS",
		"CALL_ARGUMENT",
		"BASE_EXRESSION",
		"COLUMN_SPEC",
		"LOGICAL_AND",
		"LOGICAL_OR",
		"CASE_EXPRESSION_SMPL",
		"CASE_EXPRESSION_SRCH",
		"CASE_STATEMENT",
		"COUNT_FUNC",
		"SQLPLUS_ANONYM_PLSQL_BLOCK",
		"RANK_FUNCTION",
		"LEAD_FUNCTION",
		"LAG_FUNCTION",
		"TRIM_FUNC",
		"DECODE_FUNC",
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
		"EXCEPTION_HANDLER",
		"SELECTED_TABLE",
		"CREATE_PROCEDURE",
		"CREATE_FUNCTION",
		"COMMIT_STATEMENT",
		"ROLLBACK_STATEMENT",
		"NULL_STATEMENT",
		"ASSIGNMENT_STATEMENT",
		"PROCEDURE_CALL",
		"RETURN_STATEMENT",
		"LOCK_TABLE_STATEMENT",
		"OPEN_STATEMENT",
		"FETCH_STATEMENT",
		"SET_TRN_STATEMENT",
		"CLOSE_STATEMENT",
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
		"PARTITION_NAME",
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
		"COMPLEX_NAME",
		"CHARACTER_SET",
		"AUTHID",
		"RESTRICT_REF_PRAGMA",
		"AUTONOMOUS_TRN_PRAGMA",
		"EXCEPTION_PRAGMA",
		"FIPSFLAG_PRAGMA",
		"BUILTIN_PRAGMA",
		"INTERFACE_PRAGMA",
		"TIMESTAMPG_PRAGMA",
		"SERIALLY_REUSABLE_PRAGMA",
		"TIMESTAMP_CONST",
		"SUBTYPE_DECL",
		"MEMBER_OF",
		"SQLPLUS_SET",
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
		"SQLPLUS_RUNSCRIPT",
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
		"A_COLUMN_DEF",
		"TABLE_COLLECTION",
		"VARRAY_COLLECTION",
		"REF_CURSOR",
		"OBJECT_TYPE_DEF",
		"AT_TIME_ZONE_EXPR",
		"TIMEZONE_SPEC",
		"GRANT_COMMAND",
		"REVOKE_COMMAND",
		"ALTER_TABLE",
		"ALTER_GENERIC",
		"ALTER_TABLE_CONSTRAINT",
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
		"PK_SPEC",
		"FK_SPEC",
		"UNIQUE_CONSTRAINT",
		"CHECK_CONSTRAINT",
		"CONSTRAINT_NAME",
		"ADD_CONSTRAINT",
		"ADD_PRIMARY_KEY",
		"ALTER_COLUMN_SPEC",
		"IOT_TYPE",
		"HEAP_ORGINIZED",
		"EXTERNAL_TYPE",
		"EXTERNAL_TABLE_SPEC",
		"WRITE_ACCESS_PARAMS_SPEC",
		"LOADER_ACCESS_PARAMS",
		"EXT_FIELD_SPEC_LIST",
		"EXT_FIELD_SPEC",
		"RECORD_FMT_SPEC",
		"EXT_TABLE_LOCATION_SPEC",
		"STORAGE_PARAM",
		"STORAGE_SPEC",
		"PARALLEL_CLAUSE",
		"REJECT_SPEC",
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
		"MONITORING_CLAUSE",
		"CREATE_TABLESPACE",
		"DROP_TABLESPACE",
		"TABLESPACE_NAME",
		"ALTER_TABLESPACE",
		"CREATE_USER",
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
		"\"not\"",
		"\"disable\"",
		"\"enable\"",
		"\"row\"",
		"\"movement\"",
		"\"constraint\"",
		"\"primary\"",
		"\"key\"",
		"\"references\"",
		"\"rely\"",
		"\"check\"",
		"\"unique\"",
		"\"sysdate\"",
		"\"systimestamp\"",
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
		"\"sta\"",
		"\"start\"",
		"\"repfooter\"",
		"\"off\"",
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
		"\"under\"",
		"\"final\"",
		"\"as\"",
		"\"object\"",
		"\"identified\"",
		"\"by\"",
		"\"externally\"",
		"\"globally\"",
		"\"tablespace\"",
		"\"quota\"",
		"\"unlimited\"",
		"\"profile\"",
		"\"password\"",
		"\"expire\"",
		"\"account\"",
		"\"lock\"",
		"\"unlock\"",
		"\"bigfile\"",
		"\"smallfile\"",
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
		"\"order\"",
		"\"noorder\"",
		"\"for\"",
		"\"connect\"",
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
		"\"new\"",
		"\"when\"",
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
		"\"pctthreshold\"",
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
		"\"overflow\"",
		"\"partition\"",
		"\"range\"",
		"\"interval\"",
		"\"values\"",
		"\"less\"",
		"\"than\"",
		"\"hash\"",
		"\"partitions\"",
		"\"novalidate\"",
		"\"organization\"",
		"\"heap\"",
		"\"external\"",
		"\"degree\"",
		"\"instances\"",
		"\"reject\"",
		"\"limit\"",
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
		"\"case\"",
		"\"then\"",
		"\"else\"",
		"\"autonomous_transaction\"",
		"\"prior\"",
		"\"goto\"",
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
		"\"pipelined\"",
		"\"parallel_enable\"",
		"\"deterministic\"",
		"\"language\"",
		"\"java\"",
		"\"name\"",
		"\"raise\"",
		"\"save\"",
		"\"exceptions\"",
		"\"forall\"",
		"\"indices\"",
		"\"true\"",
		"\"false\"",
		"\"**\"",
		"\"at\"",
		"\"current\"",
		"\"exists\"",
		"\"select\"",
		"\"like\"",
		"\"escape\"",
		"\"between\"",
		"\"member\"",
		"\"sqlcode\"",
		"\"sqlerrm\"",
		"\"cast\"",
		"\"decode\"",
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
		"\"leading\"",
		"\"trailing\"",
		"\"both\"",
		"\"work\"",
		"\"if\"",
		"\"elsif\"",
		"\"privileges\"",
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
		"\"current_timestamp\"",
		"\"rownum\"",
		"\"the\"",
		"\"having\"",
		"\"nulls\"",
		"\"first\"",
		"\"last\"",
		"\"nowait\"",
		"\"wait\"",
		"\"merge\"",
		"\"matched\"",
		"\"returning\"",
		"\"transaction\"",
		"\"close\"",
		"\"fetch\"",
		"\"mode\"",
		"\"share\"",
		"\"exclusive\"",
		"\"open\"",
		"\"savepoint\"",
		"\"oracle_loader\"",
		"\"oracle_datapump\"",
		"\"access\"",
		"\"parameters\"",
		"\"nologfile\"",
		"\"logfile\"",
		"\"version\"",
		"\"compatible\"",
		"\"latest\"",
		"\"compression\"",
		"\"enabled\"",
		"\"disabled\"",
		"\"encryption\"",
		"\"records\"",
		"\"fixed\"",
		"\"delimited\"",
		"\"newline\"",
		"\"characterset\"",
		"\"data\"",
		"\"big\"",
		"\"little\"",
		"\"endian\"",
		"\"mark\"",
		"\"nocheck\"",
		"\"string\"",
		"\"sizes\"",
		"\"bytes\"",
		"\"characters\"",
		"\"load\"",
		"\"nobadfile\"",
		"\"badfile\"",
		"\"nodiscardfile\"",
		"\"discardfile\"",
		"\"readsize\"",
		"\"data_cache\"",
		"\"skip\"",
		"\"preprocessor\"",
		"\"fields\"",
		"\"missing\"",
		"\"field\"",
		"\"are\"",
		"\"transforms\"",
		"\"concat\"",
		"\"lobfile\"",
		"\"enclosed\"",
		"\"terminated\"",
		"\"whitespace\"",
		"\"optionally\"",
		"\"lrtrim\"",
		"\"notrim\"",
		"\"ltrim\"",
		"\"rtrim\"",
		"\"ldrtrim\"",
		"\"position\"",
		"\"unsigned\"",
		"\"zoned\"",
		"\"oracle_date\"",
		"\"oracle_number\"",
		"\"counted\"",
		"\"varraw\"",
		"\"varcharc\"",
		"\"varrawc\"",
		"\"date_format\"",
		"\"timezone\"",
		"\"mask\"",
		"\"location\"",
		"\"aggregate\"",
		"\"ldtrim\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[14];
		data[0]=2L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[28];
		data[0]=33831414440533506L;
		data[6]=-36028797160555792L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-404620547457170L;
		data[10]=-6769473198084392201L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[26];
		data[0]=272629760L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[28];
		data[0]=-14L;
		for (int i = 1; i<=12; i++) { data[i]=-1L; }
		data[13]=2305843009213693951L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[26];
		data[0]=272629762L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[6]=1474255976169151024L;
		data[7]=-288285453790482448L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[22];
		data[0]=33554432L;
		data[6]=1125899906842624L;
		data[7]=1099511627776L;
		data[10]=50331648L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917833L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308656L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[28];
		data[0]=36306944L;
		data[6]=1473130076262308656L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = new long[16];
		data[7]=396387273391734784L;
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-56177150398480L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[28];
		data[0]=2251799816306688L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985764795289615L;
		data[8]=1331932440679152895L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = new long[28];
		data[0]=2287262615863296L;
		data[6]=-7603804603958748560L;
		data[7]=-288286550047326217L;
		data[8]=8357550058131946751L;
		data[9]=-407376876814586L;
		data[10]=-4612671180969467145L;
		data[11]=-1731216450401599489L;
		data[12]=-464311799353372889L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = new long[28];
		data[0]=2251799816306688L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985765869031439L;
		data[8]=1331932440410717439L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = new long[26];
		data[0]=4567597058L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = new long[22];
		data[6]=1125899906842624L;
		data[7]=1099511627776L;
		data[10]=50331648L;
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = new long[28];
		data[0]=2251799816306688L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985765869031439L;
		data[8]=1331932440679152895L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = new long[28];
		data[0]=35187924271104L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555787L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-4614923032322759945L;
		data[11]=-1731287128383422465L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = new long[28];
		data[0]=2621440L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985764795289615L;
		data[8]=1331932440410717439L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = new long[28];
		data[0]=2252075013111808L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326218L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032314L;
		data[10]=-4612108232760876297L;
		data[11]=-1731219748936482817L;
		data[12]=-464312211670233306L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = new long[28];
		data[0]=2289496133140480L;
		data[6]=-2414527534603437200L;
		data[7]=-288285450535698441L;
		data[8]=8357550058534599935L;
		data[9]=-407376876814578L;
		data[10]=-4612671189089377545L;
		data[11]=-1731216450401599489L;
		data[12]=-464311799336562713L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7606129112736892176L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = new long[28];
		data[0]=29273402148126722L;
		data[6]=-7606129112736892176L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[6]=-2994370385345045968L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731216759773721593L;
		data[12]=-466633967911301273L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = new long[28];
		data[0]=35364210868224L;
		data[6]=-2992118585531360720L;
		data[7]=-288285453756928015L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967890329625L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = new long[28];
		data[0]=29273673293234178L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = new long[28];
		data[0]=2818048L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = new long[28];
		data[0]=29309036679266306L;
		data[6]=-2992118584994465040L;
		data[7]=-288285441927413765L;
		data[8]=8357550058400382207L;
		data[9]=-407376876814578L;
		data[10]=-6769613935690505481L;
		data[11]=-1731212052355347449L;
		data[12]=-464030324355559449L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = new long[28];
		data[0]=29273672756363266L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553268555792L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6772217630773075209L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = new long[28];
		data[0]=29273672747974658L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = new long[28];
		data[0]=271253504L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = new long[28];
		data[0]=304742400L;
		data[6]=1473130076262308400L;
		data[7]=-288285453790482446L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731220058308867065L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = new long[28];
		data[0]=29273672756363266L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736693391578L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = new long[28];
		data[0]=29273672756363266L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058534599935L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = new long[28];
		data[0]=29273672757411842L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = new long[28];
		data[0]=29273397861548034L;
		data[6]=-7606129112736892176L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = new long[28];
		data[0]=29273397861548034L;
		data[6]=-7603877312923206928L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = new long[28];
		data[0]=29273397853159426L;
		data[6]=-7606129112736892176L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = new long[26];
		data[0]=314572802L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[6]=422222665965776L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553268555792L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6772217630773075337L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = new long[26];
		data[0]=314572802L;
		data[6]=422222666490064L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = new long[16];
		data[0]=4194304L;
		data[6]=8589934592L;
		data[7]=4160623610L;
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = new long[28];
		data[0]=29273712048603138L;
		data[6]=1619039667851590384L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-387585801208049L;
		data[10]=-6769613935690504457L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = new long[14];
		data[0]=33554432L;
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[6]=1548122572742864L;
		data[7]=1112262235130L;
		data[8]=8178536923304820736L;
		data[10]=50331648L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[6]=985172619387088L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = new long[28];
		data[0]=29273402223624194L;
		data[6]=-7604963630411449616L;
		data[7]=-4899980256447823877L;
		data[8]=8357552807179451647L;
		data[9]=-408476598157562L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = new long[26];
		data[0]=281018370L;
		data[6]=422226960867536L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = new long[14];
		data[0]=67108864L;
		data[6]=35184372088832L;
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = new long[28];
		data[0]=33832479592422914L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382396287251449L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	private static final long[] mk_tokenSet_58() {
		long[] data = new long[28];
		data[0]=29273712015048706L;
		data[6]=1618476717898169072L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464013144503677146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
	private static final long[] mk_tokenSet_59() {
		long[] data = new long[28];
		data[0]=33830280535612930L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096575L;
		data[9]=-404620547473554L;
		data[10]=-6769473198134723849L;
		data[11]=-1729382396287251449L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
	private static final long[] mk_tokenSet_60() {
		long[] data = new long[28];
		data[0]=33830280535612930L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006345314303L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305843000622710781L;
		return data;
	}
	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
	private static final long[] mk_tokenSet_61() {
		long[] data = new long[14];
		data[6]=3100803110235275264L;
		return data;
	}
	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
	private static final long[] mk_tokenSet_62() {
		long[] data = new long[14];
		data[0]=603979776L;
		return data;
	}
	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
	private static final long[] mk_tokenSet_63() {
		long[] data = new long[28];
		data[0]=606732288L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427052523521L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
	private static final long[] mk_tokenSet_64() {
		long[] data = new long[24];
		data[7]=4L;
		data[9]=8796093022208L;
		data[10]=2157224221518856192L;
		data[11]=8387576L;
		return data;
	}
	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());
	private static final long[] mk_tokenSet_65() {
		long[] data = new long[28];
		data[0]=33797295185731074L;
		data[6]=-4648277765541332240L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6481242821983067401L;
		data[11]=-1729390159977378809L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());
	private static final long[] mk_tokenSet_66() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671005065L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());
	private static final long[] mk_tokenSet_67() {
		long[] data = new long[28];
		data[0]=33797295219285506L;
		data[6]=-4648277765541332240L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6769473198134779145L;
		data[11]=-1729390159977378809L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());
	private static final long[] mk_tokenSet_68() {
		long[] data = new long[28];
		data[0]=33797295185731074L;
		data[6]=-4648277765541332240L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6769473198134779145L;
		data[11]=-1729390159977378809L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());
	private static final long[] mk_tokenSet_69() {
		long[] data = new long[28];
		data[0]=29273673360343042L;
		data[6]=4573933187034479344L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());
	private static final long[] mk_tokenSet_70() {
		long[] data = new long[28];
		data[0]=29273673360343042L;
		data[6]=9185619205461867248L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());
	private static final long[] mk_tokenSet_71() {
		long[] data = new long[28];
		data[0]=29273673293234178L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_71 = new BitSet(mk_tokenSet_71());
	private static final long[] mk_tokenSet_72() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=13935220571267280L;
		data[7]=71481006412794L;
		data[8]=8178537129465359364L;
		data[9]=1130297006489600L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_72 = new BitSet(mk_tokenSet_72());
	private static final long[] mk_tokenSet_73() {
		long[] data = new long[28];
		data[0]=29273672756363266L;
		data[6]=9113557213377428208L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_73 = new BitSet(mk_tokenSet_73());
	private static final long[] mk_tokenSet_74() {
		long[] data = new long[28];
		data[0]=33777277282680834L;
		data[6]=4573968371406568176L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-407377011032306L;
		data[10]=-6769473198202150153L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669850L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_74 = new BitSet(mk_tokenSet_74());
	private static final long[] mk_tokenSet_75() {
		long[] data = new long[28];
		data[0]=35184656908290L;
		data[6]=1473130076799204080L;
		data[7]=-288286541439041542L;
		data[8]=8357547859108691199L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731220058308867065L;
		data[12]=-466564011483984090L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_75 = new BitSet(mk_tokenSet_75());
	private static final long[] mk_tokenSet_76() {
		long[] data = new long[28];
		data[0]=35184661102592L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_76 = new BitSet(mk_tokenSet_76());
	private static final long[] mk_tokenSet_77() {
		long[] data = new long[28];
		data[0]=35222288203778L;
		data[6]=-7603804603421852944L;
		data[7]=-288286541439041541L;
		data[8]=8357547859108691199L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731216759773721593L;
		data[12]=-466563599167123673L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_77 = new BitSet(mk_tokenSet_77());
	private static final long[] mk_tokenSet_78() {
		long[] data = new long[28];
		data[0]=283770882L;
		data[6]=1473130076799204080L;
		data[7]=-288286541439041542L;
		data[8]=8357547859108691199L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731220058308867065L;
		data[12]=-466564011483984090L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_78 = new BitSet(mk_tokenSet_78());
	private static final long[] mk_tokenSet_79() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_79 = new BitSet(mk_tokenSet_79());
	private static final long[] mk_tokenSet_80() {
		long[] data = new long[28];
		data[0]=-8208L;
		for (int i = 1; i<=12; i++) { data[i]=-1L; }
		data[13]=2305843009213693951L;
		return data;
	}
	public static final BitSet _tokenSet_80 = new BitSet(mk_tokenSet_80());
	private static final long[] mk_tokenSet_81() {
		long[] data = new long[28];
		data[0]=33836920588606978L;
		data[6]=-4647714815587910928L;
		data[7]=-288285424747544577L;
		data[8]=8645785384644509695L;
		data[9]=-387028361429137L;
		data[10]=-4612248976624255241L;
		data[11]=-1729382394651213825L;
		data[12]=-464011508100165721L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_81 = new BitSet(mk_tokenSet_81());
	private static final long[] mk_tokenSet_82() {
		long[] data = new long[28];
		data[0]=33830280535612930L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_82 = new BitSet(mk_tokenSet_82());
	private static final long[] mk_tokenSet_83() {
		long[] data = new long[28];
		data[0]=35188419198976L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_83 = new BitSet(mk_tokenSet_83());
	private static final long[] mk_tokenSet_84() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911272665L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_84 = new BitSet(mk_tokenSet_84());
	private static final long[] mk_tokenSet_85() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301273L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_85 = new BitSet(mk_tokenSet_85());
	private static final long[] mk_tokenSet_86() {
		long[] data = new long[26];
		data[10]=-9223372036854775808L;
		data[11]=-4611686018427387897L;
		data[12]=3840L;
		return data;
	}
	public static final BitSet _tokenSet_86 = new BitSet(mk_tokenSet_86());
	private static final long[] mk_tokenSet_87() {
		long[] data = new long[24];
		data[0]=1048576L;
		data[10]=-9223372036854775808L;
		data[11]=-4611686018427387897L;
		return data;
	}
	public static final BitSet _tokenSet_87 = new BitSet(mk_tokenSet_87());
	private static final long[] mk_tokenSet_88() {
		long[] data = new long[28];
		data[0]=52974046551552L;
		data[6]=-7602678704051906000L;
		data[7]=-288286536088686607L;
		data[8]=2016479583779421439L;
		data[9]=-407376876292338L;
		data[10]=-6772147262020508937L;
		data[11]=-1729382774378591225L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_88 = new BitSet(mk_tokenSet_88());
	private static final long[] mk_tokenSet_89() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=-7750241960592467408L;
		data[7]=-288286553302110223L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731290427052782585L;
		data[12]=-466633967911301338L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_89 = new BitSet(mk_tokenSet_89());
	private static final long[] mk_tokenSet_90() {
		long[] data = new long[28];
		data[0]=33830280569167362L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_90 = new BitSet(mk_tokenSet_90());
	private static final long[] mk_tokenSet_91() {
		long[] data = new long[28];
		data[0]=1076494336L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_91 = new BitSet(mk_tokenSet_91());
	private static final long[] mk_tokenSet_92() {
		long[] data = new long[28];
		data[0]=19529728L;
		data[6]=1617245264338164272L;
		data[7]=-288286553268555792L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6772217630764686601L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_92 = new BitSet(mk_tokenSet_92());
	private static final long[] mk_tokenSet_93() {
		long[] data = new long[28];
		data[0]=33797295185731074L;
		data[6]=-4648277765541332240L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6697415604096851209L;
		data[11]=-1729390159977378809L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_93 = new BitSet(mk_tokenSet_93());
	private static final long[] mk_tokenSet_94() {
		long[] data = new long[28];
		data[0]=2199327997952L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_94 = new BitSet(mk_tokenSet_94());
	private static final long[] mk_tokenSet_95() {
		long[] data = new long[28];
		data[0]=37387476008960L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_95 = new BitSet(mk_tokenSet_95());
	private static final long[] mk_tokenSet_96() {
		long[] data = new long[28];
		data[0]=33830314928905730L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_96 = new BitSet(mk_tokenSet_96());
	private static final long[] mk_tokenSet_97() {
		long[] data = new long[28];
		data[0]=33795096162475522L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6769473198134779145L;
		data[11]=-1729390161588122617L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_97 = new BitSet(mk_tokenSet_97());
	private static final long[] mk_tokenSet_98() {
		long[] data = new long[26];
		data[0]=306184194L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_98 = new BitSet(mk_tokenSet_98());
	private static final long[] mk_tokenSet_99() {
		long[] data = new long[28];
		data[0]=2621440L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985765869031439L;
		data[8]=1331932440410717439L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_99 = new BitSet(mk_tokenSet_99());
	private static final long[] mk_tokenSet_100() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529225L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_100 = new BitSet(mk_tokenSet_100());
	private static final long[] mk_tokenSet_101() {
		long[] data = new long[28];
		data[0]=2268139749376L;
		data[6]=1617350817454430832L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219680351745017L;
		data[12]=-464312211649261658L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_101 = new BitSet(mk_tokenSet_101());
	private static final long[] mk_tokenSet_102() {
		long[] data = new long[28];
		data[0]=29311059537625088L;
		data[6]=-2415657832556790928L;
		data[7]=-288286550047326221L;
		data[8]=8357550058534599935L;
		data[9]=-407376876290290L;
		data[10]=-6769613935623134473L;
		data[11]=-1731216312954516473L;
		data[12]=-464311799336431641L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_102 = new BitSet(mk_tokenSet_102());
	private static final long[] mk_tokenSet_103() {
		long[] data = new long[28];
		data[0]=2268106194944L;
		data[6]=1617350817454430832L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219680351745017L;
		data[12]=-464312211649261658L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_103 = new BitSet(mk_tokenSet_103());
	private static final long[] mk_tokenSet_104() {
		long[] data = new long[28];
		data[0]=29273672789917698L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_104 = new BitSet(mk_tokenSet_104());
	private static final long[] mk_tokenSet_105() {
		long[] data = new long[28];
		data[0]=29273686278799362L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_105 = new BitSet(mk_tokenSet_105());
	private static final long[] mk_tokenSet_106() {
		long[] data = new long[20];
		data[0]=268435456L;
		data[6]=13512997905367040L;
		data[7]=71468255805440L;
		data[8]=206160538628L;
		data[9]=1130297006489600L;
		return data;
	}
	public static final BitSet _tokenSet_106 = new BitSet(mk_tokenSet_106());
	private static final long[] mk_tokenSet_107() {
		long[] data = new long[22];
		data[6]=7133701809754865664L;
		data[10]=128L;
		return data;
	}
	public static final BitSet _tokenSet_107 = new BitSet(mk_tokenSet_107());
	private static final long[] mk_tokenSet_108() {
		long[] data = new long[20];
		data[6]=13512997905367040L;
		data[7]=70368744177664L;
		data[8]=206160538628L;
		data[9]=1130297006489600L;
		return data;
	}
	public static final BitSet _tokenSet_108 = new BitSet(mk_tokenSet_108());
	private static final long[] mk_tokenSet_109() {
		long[] data = new long[26];
		data[0]=4609540098L;
		data[6]=422222665900240L;
		data[7]=1112262235130L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_109 = new BitSet(mk_tokenSet_109());
	private static final long[] mk_tokenSet_110() {
		long[] data = new long[18];
		data[7]=33554432L;
		data[8]=2194728288256L;
		return data;
	}
	public static final BitSet _tokenSet_110 = new BitSet(mk_tokenSet_110());
	private static final long[] mk_tokenSet_111() {
		long[] data = new long[26];
		data[0]=35188948074498L;
		data[6]=422226960867536L;
		data[7]=8808843629562L;
		data[8]=8178539118033108992L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_111 = new BitSet(mk_tokenSet_111());
	private static final long[] mk_tokenSet_112() {
		long[] data = new long[18];
		data[6]=65536L;
		data[7]=4035225403562917888L;
		data[8]=1211393L;
		return data;
	}
	public static final BitSet _tokenSet_112 = new BitSet(mk_tokenSet_112());
	private static final long[] mk_tokenSet_113() {
		long[] data = new long[16];
		data[6]=4398046511104L;
		data[7]=6333324414943232L;
		return data;
	}
	public static final BitSet _tokenSet_113 = new BitSet(mk_tokenSet_113());
	private static final long[] mk_tokenSet_114() {
		long[] data = new long[16];
		data[6]=1125899906842624L;
		data[7]=1374389534720L;
		return data;
	}
	public static final BitSet _tokenSet_114 = new BitSet(mk_tokenSet_114());
	private static final long[] mk_tokenSet_115() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=426620712411344L;
		data[7]=6333337165550586L;
		data[8]=8178536923304820736L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_115 = new BitSet(mk_tokenSet_115());
	private static final long[] mk_tokenSet_116() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617320031665748720L;
		data[7]=-288285441927413766L;
		data[8]=8357550060547866111L;
		data[9]=-404620682215666L;
		data[10]=-6769613935690450185L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_116 = new BitSet(mk_tokenSet_116());
	private static final long[] mk_tokenSet_117() {
		long[] data = new long[28];
		data[0]=29273677118439426L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382463L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_117 = new BitSet(mk_tokenSet_117());
	private static final long[] mk_tokenSet_118() {
		long[] data = new long[28];
		data[0]=29273677118439426L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_118 = new BitSet(mk_tokenSet_118());
	private static final long[] mk_tokenSet_119() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=422222665965776L;
		data[7]=4035225416313525242L;
		data[8]=8178536923306032161L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_119 = new BitSet(mk_tokenSet_119());
	private static final long[] mk_tokenSet_120() {
		long[] data = new long[28];
		data[0]=29273677051330562L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_120 = new BitSet(mk_tokenSet_120());
	private static final long[] mk_tokenSet_121() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=422222665965776L;
		data[7]=4035225416313525242L;
		data[8]=8178536923306032129L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_121 = new BitSet(mk_tokenSet_121());
	private static final long[] mk_tokenSet_122() {
		long[] data = new long[18];
		data[7]=720575940379279360L;
		data[8]=100663296L;
		return data;
	}
	public static final BitSet _tokenSet_122 = new BitSet(mk_tokenSet_122());
	private static final long[] mk_tokenSet_123() {
		long[] data = new long[18];
		data[7]=720575940379279360L;
		data[8]=24576L;
		return data;
	}
	public static final BitSet _tokenSet_123 = new BitSet(mk_tokenSet_123());
	private static final long[] mk_tokenSet_124() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=422222665900240L;
		data[7]=12750607354L;
		data[8]=8178539118033108992L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_124 = new BitSet(mk_tokenSet_124());
	private static final long[] mk_tokenSet_125() {
		long[] data = new long[18];
		data[8]=2199023255552L;
		return data;
	}
	public static final BitSet _tokenSet_125 = new BitSet(mk_tokenSet_125());
	private static final long[] mk_tokenSet_126() {
		long[] data = new long[18];
		data[6]=13510798882111488L;
		data[8]=288256764430778368L;
		return data;
	}
	public static final BitSet _tokenSet_126 = new BitSet(mk_tokenSet_126());
	private static final long[] mk_tokenSet_127() {
		long[] data = new long[20];
		data[7]=3221225472L;
		data[8]=2199023255552L;
		data[9]=9L;
		return data;
	}
	public static final BitSet _tokenSet_127 = new BitSet(mk_tokenSet_127());
	private static final long[] mk_tokenSet_128() {
		long[] data = new long[16];
		data[7]=3221225472L;
		return data;
	}
	public static final BitSet _tokenSet_128 = new BitSet(mk_tokenSet_128());
	private static final long[] mk_tokenSet_129() {
		long[] data = new long[16];
		data[6]=562949953421312L;
		data[7]=17179869184L;
		return data;
	}
	public static final BitSet _tokenSet_129 = new BitSet(mk_tokenSet_129());
	private static final long[] mk_tokenSet_130() {
		long[] data = new long[28];
		data[0]=29273673360343042L;
		data[6]=6301657380410063600L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_130 = new BitSet(mk_tokenSet_130());
	private static final long[] mk_tokenSet_131() {
		long[] data = new long[14];
		data[0]=536870912L;
		return data;
	}
	public static final BitSet _tokenSet_131 = new BitSet(mk_tokenSet_131());
	private static final long[] mk_tokenSet_132() {
		long[] data = new long[20];
		data[0]=268435456L;
		data[6]=14075947858788352L;
		data[7]=71468255805440L;
		data[8]=206160538628L;
		data[9]=1130297006489600L;
		return data;
	}
	public static final BitSet _tokenSet_132 = new BitSet(mk_tokenSet_132());
	private static final long[] mk_tokenSet_133() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=13935220571267280L;
		data[7]=70381494785018L;
		data[8]=8178537129465359364L;
		data[9]=1130297006505984L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_133 = new BitSet(mk_tokenSet_133());
	private static final long[] mk_tokenSet_134() {
		long[] data = new long[20];
		data[6]=13512997905367040L;
		data[7]=70368744177664L;
		data[8]=206158430240L;
		data[9]=425984L;
		return data;
	}
	public static final BitSet _tokenSet_134 = new BitSet(mk_tokenSet_134());
	private static final long[] mk_tokenSet_135() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=13935220571267280L;
		data[7]=70381494785018L;
		data[8]=8178537129465359364L;
		data[9]=1130297006489600L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_135 = new BitSet(mk_tokenSet_135());
	private static final long[] mk_tokenSet_136() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736693391578L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_136 = new BitSet(mk_tokenSet_136());
	private static final long[] mk_tokenSet_137() {
		long[] data = new long[26];
		data[0]=4575985666L;
		data[6]=13935220571267280L;
		data[7]=71481006412794L;
		data[8]=8178537129465359364L;
		data[9]=37159094025453568L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_137 = new BitSet(mk_tokenSet_137());
	private static final long[] mk_tokenSet_138() {
		long[] data = new long[20];
		data[0]=536870912L;
		data[6]=13512997905367040L;
		data[7]=70368744177664L;
		data[8]=206158430240L;
		data[9]=425984L;
		return data;
	}
	public static final BitSet _tokenSet_138 = new BitSet(mk_tokenSet_138());
	private static final long[] mk_tokenSet_139() {
		long[] data = new long[22];
		data[7]=1152921504606846976L;
		data[9]=9079256848778919936L;
		data[10]=115L;
		return data;
	}
	public static final BitSet _tokenSet_139 = new BitSet(mk_tokenSet_139());
	private static final long[] mk_tokenSet_140() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617315633619237616L;
		data[7]=-288285441927413766L;
		data[8]=8357550058400382207L;
		data[9]=-404620682215666L;
		data[10]=-6769613935690450185L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_140 = new BitSet(mk_tokenSet_140());
	private static final long[] mk_tokenSet_141() {
		long[] data = new long[20];
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=1125967670018048L;
		return data;
	}
	public static final BitSet _tokenSet_141 = new BitSet(mk_tokenSet_141());
	private static final long[] mk_tokenSet_142() {
		long[] data = new long[26];
		data[0]=5179965442L;
		data[6]=13935220571267280L;
		data[7]=71481006412794L;
		data[8]=8178537129465359364L;
		data[9]=1130297006489600L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_142 = new BitSet(mk_tokenSet_142());
	private static final long[] mk_tokenSet_143() {
		long[] data = new long[20];
		data[8]=206158430208L;
		data[9]=36029827811115008L;
		return data;
	}
	public static final BitSet _tokenSet_143 = new BitSet(mk_tokenSet_143());
	private static final long[] mk_tokenSet_144() {
		long[] data = new long[26];
		data[0]=35188948074498L;
		data[6]=13935220571267280L;
		data[7]=71481006412794L;
		data[8]=8178537129465359364L;
		data[9]=109216688063381504L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_144 = new BitSet(mk_tokenSet_144());
	private static final long[] mk_tokenSet_145() {
		long[] data = new long[20];
		data[6]=4827858800541696000L;
		data[9]=562949953421312L;
		return data;
	}
	public static final BitSet _tokenSet_145 = new BitSet(mk_tokenSet_145());
	private static final long[] mk_tokenSet_146() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617315633619237616L;
		data[7]=-288285441927413766L;
		data[8]=8357550058400382207L;
		data[9]=-404620682215666L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_146 = new BitSet(mk_tokenSet_146());
	private static final long[] mk_tokenSet_147() {
		long[] data = new long[20];
		data[6]=2199023255552L;
		data[9]=66571993088L;
		return data;
	}
	public static final BitSet _tokenSet_147 = new BitSet(mk_tokenSet_147());
	private static final long[] mk_tokenSet_148() {
		long[] data = new long[26];
		data[0]=4578607106L;
		data[6]=13935220571267280L;
		data[7]=1152992985613259770L;
		data[8]=8178537129465359364L;
		data[9]=9080387145785409536L;
		data[10]=115L;
		for (int i = 11; i<=12; i++) { data[i]=70368744177664L; }
		return data;
	}
	public static final BitSet _tokenSet_148 = new BitSet(mk_tokenSet_148());
	private static final long[] mk_tokenSet_149() {
		long[] data = new long[28];
		data[0]=606732288L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407375937292538L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_149 = new BitSet(mk_tokenSet_149());
	private static final long[] mk_tokenSet_150() {
		long[] data = new long[28];
		data[0]=606732288L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407369461287162L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_150 = new BitSet(mk_tokenSet_150());
	private static final long[] mk_tokenSet_151() {
		long[] data = new long[20];
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=1167274803200L;
		return data;
	}
	public static final BitSet _tokenSet_151 = new BitSet(mk_tokenSet_151());
	private static final long[] mk_tokenSet_152() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=18759460847616L;
		return data;
	}
	public static final BitSet _tokenSet_152 = new BitSet(mk_tokenSet_152());
	private static final long[] mk_tokenSet_153() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=1167274803200L;
		return data;
	}
	public static final BitSet _tokenSet_153 = new BitSet(mk_tokenSet_153());
	private static final long[] mk_tokenSet_154() {
		long[] data = new long[28];
		data[0]=29308862027399170L;
		data[6]=1617315633619237616L;
		data[7]=-288285441927413766L;
		data[8]=8357550058400382207L;
		data[9]=-404620682215666L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_154 = new BitSet(mk_tokenSet_154());
	private static final long[] mk_tokenSet_155() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[7]=70368744177664L;
		data[9]=1100652478464L;
		return data;
	}
	public static final BitSet _tokenSet_155 = new BitSet(mk_tokenSet_155());
	private static final long[] mk_tokenSet_156() {
		long[] data = new long[20];
		data[7]=70368744177664L;
		data[9]=1100652478464L;
		return data;
	}
	public static final BitSet _tokenSet_156 = new BitSet(mk_tokenSet_156());
	private static final long[] mk_tokenSet_157() {
		long[] data = new long[22];
		data[0]=536870912L;
		data[7]=1152921504606846976L;
		data[9]=9079256848778919936L;
		data[10]=115L;
		return data;
	}
	public static final BitSet _tokenSet_157 = new BitSet(mk_tokenSet_157());
	private static final long[] mk_tokenSet_158() {
		long[] data = new long[28];
		data[0]=29273673393897474L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_158 = new BitSet(mk_tokenSet_158());
	private static final long[] mk_tokenSet_159() {
		long[] data = new long[28];
		data[0]=29273673360343042L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_159 = new BitSet(mk_tokenSet_159());
	private static final long[] mk_tokenSet_160() {
		long[] data = new long[22];
		data[6]=2199023255552L;
		data[7]=70437463654400L;
		data[8]=11268L;
		data[9]=272763977728L;
		data[10]=63488L;
		return data;
	}
	public static final BitSet _tokenSet_160 = new BitSet(mk_tokenSet_160());
	private static final long[] mk_tokenSet_161() {
		long[] data = new long[28];
		data[0]=29308857732431874L;
		data[6]=1617320031665748720L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-405171511771378L;
		data[10]=-6769613935690450185L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_161 = new BitSet(mk_tokenSet_161());
	private static final long[] mk_tokenSet_162() {
		long[] data = new long[22];
		data[6]=128L;
		data[8]=100663296L;
		data[10]=1024L;
		return data;
	}
	public static final BitSet _tokenSet_162 = new BitSet(mk_tokenSet_162());
	private static final long[] mk_tokenSet_163() {
		long[] data = new long[28];
		data[0]=271187968L;
		data[6]=8609154054575036976L;
		data[7]=-288286553302110224L;
		data[8]=1440018831601827071L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529225L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_163 = new BitSet(mk_tokenSet_163());
	private static final long[] mk_tokenSet_164() {
		long[] data = new long[28];
		data[0]=29308860350726146L;
		data[6]=-614182797370754320L;
		data[7]=-288286541439041537L;
		data[8]=8357550058400382207L;
		data[9]=-407376876814578L;
		data[10]=-4612389714180037897L;
		data[11]=-1731216450401599489L;
		data[12]=-464311799353241817L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_164 = new BitSet(mk_tokenSet_164());
	private static final long[] mk_tokenSet_165() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=8609154054575036976L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529225L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_165 = new BitSet(mk_tokenSet_165());
	private static final long[] mk_tokenSet_166() {
		long[] data = new long[22];
		data[6]=7136023978312728576L;
		data[10]=128L;
		return data;
	}
	public static final BitSet _tokenSet_166 = new BitSet(mk_tokenSet_166());
	private static final long[] mk_tokenSet_167() {
		long[] data = new long[28];
		data[0]=29308860954705922L;
		data[6]=-37752831392908560L;
		data[7]=-288286541439041541L;
		data[8]=8357550058400382207L;
		data[9]=-407376876814578L;
		data[10]=-6769613935690505481L;
		data[11]=-1731216450401858553L;
		data[12]=-464311799353241817L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_167 = new BitSet(mk_tokenSet_167());
	private static final long[] mk_tokenSet_168() {
		long[] data = new long[28];
		data[0]=29273673293234178L;
		data[6]=1617913767944747760L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_168 = new BitSet(mk_tokenSet_168());
	private static final long[] mk_tokenSet_169() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262341168L;
		data[7]=-288286484582633488L;
		data[8]=1440018831467609343L;
		data[9]=-407370535028986L;
		data[10]=-6773343530679862665L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_169 = new BitSet(mk_tokenSet_169());
	private static final long[] mk_tokenSet_170() {
		long[] data = new long[22];
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=272763977728L;
		data[10]=63488L;
		return data;
	}
	public static final BitSet _tokenSet_170 = new BitSet(mk_tokenSet_170());
	private static final long[] mk_tokenSet_171() {
		long[] data = new long[28];
		data[0]=29308857732431874L;
		data[6]=1617320031665748720L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407370535026930L;
		data[10]=-6769613935690450185L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_171 = new BitSet(mk_tokenSet_171());
	private static final long[] mk_tokenSet_172() {
		long[] data = new long[28];
		data[0]=29273673360343042L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407370535026930L;
		data[10]=-6769613935690450185L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_172 = new BitSet(mk_tokenSet_172());
	private static final long[] mk_tokenSet_173() {
		long[] data = new long[20];
		data[0]=603979776L;
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=67763175424L;
		return data;
	}
	public static final BitSet _tokenSet_173 = new BitSet(mk_tokenSet_173());
	private static final long[] mk_tokenSet_174() {
		long[] data = new long[20];
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=11264L;
		data[9]=67763175424L;
		return data;
	}
	public static final BitSet _tokenSet_174 = new BitSet(mk_tokenSet_174());
	private static final long[] mk_tokenSet_175() {
		long[] data = new long[28];
		data[0]=35185247256576L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440021030490864895L;
		data[9]=-407369461287162L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_175 = new BitSet(mk_tokenSet_175());
	private static final long[] mk_tokenSet_176() {
		long[] data = new long[28];
		data[0]=29308862027399170L;
		data[6]=1617315633619237616L;
		data[7]=-288285441927413766L;
		data[8]=8357550058400382207L;
		data[9]=-404620279562482L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_176 = new BitSet(mk_tokenSet_176());
	private static final long[] mk_tokenSet_177() {
		long[] data = new long[20];
		data[0]=35184976068608L;
		data[6]=2199023255552L;
		data[7]=70368744177664L;
		data[8]=2199023266816L;
		data[9]=67763175424L;
		return data;
	}
	public static final BitSet _tokenSet_177 = new BitSet(mk_tokenSet_177());
	private static final long[] mk_tokenSet_178() {
		long[] data = new long[28];
		data[0]=29273432221286402L;
		data[6]=-7606129112736892176L;
		data[7]=-4899985754005962757L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_178 = new BitSet(mk_tokenSet_178());
	private static final long[] mk_tokenSet_179() {
		long[] data = new long[16];
		data[0]=268435456L;
		data[7]=1099511627776L;
		return data;
	}
	public static final BitSet _tokenSet_179 = new BitSet(mk_tokenSet_179());
	private static final long[] mk_tokenSet_180() {
		long[] data = new long[14];
		data[0]=8388608L;
		return data;
	}
	public static final BitSet _tokenSet_180 = new BitSet(mk_tokenSet_180());
	private static final long[] mk_tokenSet_181() {
		long[] data = new long[28];
		data[0]=2621440L;
		data[6]=-7606410588250760656L;
		data[7]=-4899985765869031439L;
		data[8]=1331932440410717439L;
		data[9]=-408476598159610L;
		data[10]=-6773343607983037705L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_181 = new BitSet(mk_tokenSet_181());
	private static final long[] mk_tokenSet_182() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-4616119304866094345L;
		data[11]=-1731290427052523521L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_182 = new BitSet(mk_tokenSet_182());
	private static final long[] mk_tokenSet_183() {
		long[] data = new long[28];
		data[0]=29273397580529664L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985764795289615L;
		data[8]=1331932440679152895L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_183 = new BitSet(mk_tokenSet_183());
	private static final long[] mk_tokenSet_184() {
		long[] data = new long[14];
		data[0]=27021597764222976L;
		return data;
	}
	public static final BitSet _tokenSet_184 = new BitSet(mk_tokenSet_184());
	private static final long[] mk_tokenSet_185() {
		long[] data = new long[28];
		data[0]=63050394785808384L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985765869031439L;
		data[8]=1331932440410717439L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_185 = new BitSet(mk_tokenSet_185());
	private static final long[] mk_tokenSet_186() {
		long[] data = new long[18];
		data[8]=268435456L;
		return data;
	}
	public static final BitSet _tokenSet_186 = new BitSet(mk_tokenSet_186());
	private static final long[] mk_tokenSet_187() {
		long[] data = new long[28];
		data[0]=2252074979557376L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219749071221753L;
		data[12]=-464312211670233306L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_187 = new BitSet(mk_tokenSet_187());
	private static final long[] mk_tokenSet_188() {
		long[] data = new long[26];
		data[0]=27021597764222976L;
		data[8]=268435456L;
		data[9]=8L;
		data[10]=281474976710656L;
		data[11]=134217728L;
		data[12]=131072L;
		return data;
	}
	public static final BitSet _tokenSet_188 = new BitSet(mk_tokenSet_188());
	private static final long[] mk_tokenSet_189() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427052523521L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_189 = new BitSet(mk_tokenSet_189());
	private static final long[] mk_tokenSet_190() {
		long[] data = new long[24];
		data[0]=268435456L;
		data[6]=1125904201809920L;
		data[10]=-8935141660703064064L;
		data[11]=1026L;
		return data;
	}
	public static final BitSet _tokenSet_190 = new BitSet(mk_tokenSet_190());
	private static final long[] mk_tokenSet_191() {
		long[] data = new long[28];
		data[0]=35185314365440L;
		data[6]=1618371164245006896L;
		data[7]=-288286553302110220L;
		data[8]=1440018831601827071L;
		data[9]=-407377011034362L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427052523521L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_191 = new BitSet(mk_tokenSet_191());
	private static final long[] mk_tokenSet_192() {
		long[] data = new long[24];
		data[0]=34670116864L;
		data[6]=2251804108652544L;
		data[10]=-8935141660703064064L;
		data[11]=1026L;
		return data;
	}
	public static final BitSet _tokenSet_192 = new BitSet(mk_tokenSet_192());
	private static final long[] mk_tokenSet_193() {
		long[] data = new long[28];
		data[0]=29308582900662272L;
		data[6]=-7603804603958724048L;
		data[7]=-288286552228368399L;
		data[8]=1440018831870262527L;
		data[9]=-407377011034362L;
		data[10]=-6773343530603633929L;
		data[11]=-1731290427052782585L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_193 = new BitSet(mk_tokenSet_193());
	private static final long[] mk_tokenSet_194() {
		long[] data = new long[28];
		data[0]=29275596612173824L;
		data[6]=-7606406190203962832L;
		data[7]=-4899985764795289615L;
		data[8]=1331932440679152895L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_194 = new BitSet(mk_tokenSet_194());
	private static final long[] mk_tokenSet_195() {
		long[] data = new long[28];
		data[0]=29273397588918272L;
		data[6]=-7606410588250473936L;
		data[7]=-4899985764795289615L;
		data[8]=1331932440679152895L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740306422400545785L;
		data[12]=-466634105350254810L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_195 = new BitSet(mk_tokenSet_195());
	private static final long[] mk_tokenSet_196() {
		long[] data = new long[28];
		data[0]=33795096162475522L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6769473198134779145L;
		data[11]=-1729385763541611513L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_196 = new BitSet(mk_tokenSet_196());
	private static final long[] mk_tokenSet_197() {
		long[] data = new long[20];
		data[0]=34628173824L;
		data[6]=1125899906842624L;
		data[9]=2048L;
		return data;
	}
	public static final BitSet _tokenSet_197 = new BitSet(mk_tokenSet_197());
	private static final long[] mk_tokenSet_198() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7605003212830049552L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598157562L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_198 = new BitSet(mk_tokenSet_198());
	private static final long[] mk_tokenSet_199() {
		long[] data = new long[22];
		data[0]=545259520L;
		data[10]=17179869184L;
		return data;
	}
	public static final BitSet _tokenSet_199 = new BitSet(mk_tokenSet_199());
	private static final long[] mk_tokenSet_200() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7604968028457960720L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236052045624313L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_200 = new BitSet(mk_tokenSet_200());
	private static final long[] mk_tokenSet_201() {
		long[] data = new long[28];
		data[0]=287965184L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219749071221753L;
		data[12]=-464312211670233306L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_201 = new BitSet(mk_tokenSet_201());
	private static final long[] mk_tokenSet_202() {
		long[] data = new long[28];
		data[0]=29273672743780352L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_202 = new BitSet(mk_tokenSet_202());
	private static final long[] mk_tokenSet_203() {
		long[] data = new long[22];
		data[0]=2621440L;
		data[8]=2199023255552L;
		data[10]=51539607552L;
		return data;
	}
	public static final BitSet _tokenSet_203 = new BitSet(mk_tokenSet_203());
	private static final long[] mk_tokenSet_204() {
		long[] data = new long[28];
		data[0]=2287262573920256L;
		data[6]=-7603804603958748560L;
		data[7]=-288286550047326221L;
		data[8]=8357550058131946751L;
		data[9]=-407376876814586L;
		data[10]=-6769895410667216137L;
		data[11]=-1731216450536076281L;
		data[12]=-464311799353372889L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_204 = new BitSet(mk_tokenSet_204());
	private static final long[] mk_tokenSet_205() {
		long[] data = new long[28];
		data[0]=2307247232982528L;
		data[6]=-2414531932649948304L;
		data[7]=-288286532867457037L;
		data[8]=8934010810846412031L;
		data[9]=-407376876290290L;
		data[10]=-6769895410599845129L;
		data[11]=-1729382465006728185L;
		data[12]=-464311799336562713L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_205 = new BitSet(mk_tokenSet_205());
	private static final long[] mk_tokenSet_206() {
		long[] data = new long[28];
		data[0]=2818048L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161626L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_206 = new BitSet(mk_tokenSet_206());
	private static final long[] mk_tokenSet_207() {
		long[] data = new long[28];
		data[0]=29273672785788928L;
		data[6]=1617315633082342000L;
		data[7]=-288285450535698446L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030667973914842L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_207 = new BitSet(mk_tokenSet_207());
	private static final long[] mk_tokenSet_208() {
		long[] data = new long[24];
		data[6]=576460752303456320L;
		data[7]=256L;
		data[8]=8070450532247928832L;
		data[9]=134217728L;
		data[11]=70368744177664L;
		return data;
	}
	public static final BitSet _tokenSet_208 = new BitSet(mk_tokenSet_208());
	private static final long[] mk_tokenSet_209() {
		long[] data = new long[28];
		data[0]=29273672752168960L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058534599935L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670069466L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_209 = new BitSet(mk_tokenSet_209());
	private static final long[] mk_tokenSet_210() {
		long[] data = new long[28];
		data[0]=29311063836786690L;
		data[6]=-2415657832556790800L;
		data[7]=-288286541439041541L;
		data[8]=8357550058534599935L;
		data[9]=-407376876814578L;
		data[10]=-6769613935623134473L;
		data[11]=-1731216450401858553L;
		data[12]=-464311799336431641L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_210 = new BitSet(mk_tokenSet_210());
	private static final long[] mk_tokenSet_211() {
		long[] data = new long[28];
		data[0]=29273672752168960L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670069466L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_211 = new BitSet(mk_tokenSet_211());
	private static final long[] mk_tokenSet_212() {
		long[] data = new long[18];
		data[0]=276824064L;
		data[6]=35184372088832L;
		data[8]=2199023255552L;
		return data;
	}
	public static final BitSet _tokenSet_212 = new BitSet(mk_tokenSet_212());
	private static final long[] mk_tokenSet_213() {
		long[] data = new long[28];
		data[0]=29273672752168960L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_213 = new BitSet(mk_tokenSet_213());
	private static final long[] mk_tokenSet_214() {
		long[] data = new long[28];
		data[0]=29273672785723392L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312211670102234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_214 = new BitSet(mk_tokenSet_214());
	private static final long[] mk_tokenSet_215() {
		long[] data = new long[22];
		data[0]=1099520016384L;
		data[8]=2199023255552L;
		data[9]=8L;
		data[10]=51539607552L;
		return data;
	}
	public static final BitSet _tokenSet_215 = new BitSet(mk_tokenSet_215());
	private static final long[] mk_tokenSet_216() {
		long[] data = new long[24];
		data[0]=271056896L;
		data[11]=70368744177664L;
		return data;
	}
	public static final BitSet _tokenSet_216 = new BitSet(mk_tokenSet_216());
	private static final long[] mk_tokenSet_217() {
		long[] data = new long[28];
		data[0]=313131008L;
		data[6]=1473165260634397232L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380207190234L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_217 = new BitSet(mk_tokenSet_217());
	private static final long[] mk_tokenSet_218() {
		long[] data = new long[28];
		data[0]=29308860916957184L;
		data[6]=-7603769419586659728L;
		data[7]=-288286550047326221L;
		data[8]=8357550058400382207L;
		data[9]=-407376876290290L;
		data[10]=-6769613935690505481L;
		data[11]=-1731216450393469945L;
		data[12]=-464311799332270297L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_218 = new BitSet(mk_tokenSet_218());
	private static final long[] mk_tokenSet_219() {
		long[] data = new long[28];
		data[0]=35364152147968L;
		data[6]=-7603769419586659792L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967890329817L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_219 = new BitSet(mk_tokenSet_219());
	private static final long[] mk_tokenSet_220() {
		long[] data = new long[28];
		data[0]=29326646510755328L;
		data[6]=-2990957501252429200L;
		data[7]=-288286532867457033L;
		data[8]=8934010810846412031L;
		data[9]=-407376876290290L;
		data[10]=-4612389714180037897L;
		data[11]=-1729382464998080513L;
		data[12]=-464311799332241561L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_220 = new BitSet(mk_tokenSet_220());
	private static final long[] mk_tokenSet_221() {
		long[] data = new long[28];
		data[0]=35364152147968L;
		data[6]=-7603769419586659792L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_221 = new BitSet(mk_tokenSet_221());
	private static final long[] mk_tokenSet_222() {
		long[] data = new long[28];
		data[0]=29326646510755328L;
		data[6]=-2990957501252429200L;
		data[7]=-288286532867457033L;
		data[8]=8934010810846412031L;
		data[9]=-407376876290290L;
		data[10]=-4612389714180037897L;
		data[11]=-1729382465006469121L;
		data[12]=-464311799353213081L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_222 = new BitSet(mk_tokenSet_222());
	private static final long[] mk_tokenSet_223() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6772217630764686729L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_223 = new BitSet(mk_tokenSet_223());
	private static final long[] mk_tokenSet_224() {
		long[] data = new long[28];
		data[0]=33836877638934018L;
		data[6]=-4648277765541332240L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382394676507641L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_224 = new BitSet(mk_tokenSet_224());
	private static final long[] mk_tokenSet_225() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427027357697L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_225 = new BitSet(mk_tokenSet_225());
	private static final long[] mk_tokenSet_226() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427035746305L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_226 = new BitSet(mk_tokenSet_226());
	private static final long[] mk_tokenSet_227() {
		long[] data = new long[24];
		data[0]=2234288963584L;
		data[6]=4402341478400L;
		data[10]=-8935141660703064064L;
		data[11]=132098L;
		return data;
	}
	public static final BitSet _tokenSet_227 = new BitSet(mk_tokenSet_227());
	private static final long[] mk_tokenSet_228() {
		long[] data = new long[28];
		data[0]=29310789529239554L;
		data[6]=-7604926105282184464L;
		data[7]=-288285441927413765L;
		data[8]=8357547859511344383L;
		data[9]=-407376876814586L;
		data[10]=-6772147261952613641L;
		data[11]=-1731216759773590521L;
		data[12]=-466563599167123673L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_228 = new BitSet(mk_tokenSet_228());
	private static final long[] mk_tokenSet_229() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110220L;
		data[8]=1440018831467609343L;
		data[9]=-407377010510074L;
		data[10]=-4616119309161061641L;
		data[11]=-1731290427027357697L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_229 = new BitSet(mk_tokenSet_229());
	private static final long[] mk_tokenSet_230() {
		long[] data = new long[28];
		data[0]=29275601783750658L;
		data[6]=-7604963630411449616L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236052045755385L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_230 = new BitSet(mk_tokenSet_230());
	private static final long[] mk_tokenSet_231() {
		long[] data = new long[28];
		data[0]=539623424L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_231 = new BitSet(mk_tokenSet_231());
	private static final long[] mk_tokenSet_232() {
		long[] data = new long[28];
		data[0]=29273402156646402L;
		data[6]=-7605000872072873232L;
		data[7]=-288285441927413761L;
		data[8]=8357547859377126655L;
		data[9]=-407377010510074L;
		data[10]=-4616119309093690633L;
		data[11]=-1731220058283180033L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_232 = new BitSet(mk_tokenSet_232());
	private static final long[] mk_tokenSet_233() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7605003212830049552L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_233 = new BitSet(mk_tokenSet_233());
	private static final long[] mk_tokenSet_234() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7604968028457960720L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236052045755385L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_234 = new BitSet(mk_tokenSet_234());
	private static final long[] mk_tokenSet_235() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7604968028457960720L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236052582626297L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_235 = new BitSet(mk_tokenSet_235());
	private static final long[] mk_tokenSet_236() {
		long[] data = new long[28];
		data[0]=29273402156515330L;
		data[6]=-7604968028457960720L;
		data[7]=-4899984654494334981L;
		data[8]=8357547859377126655L;
		data[9]=-408476598159610L;
		data[10]=-6773343599325732105L;
		data[11]=-1740236053656368121L;
		data[12]=-466563736606077146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_236 = new BitSet(mk_tokenSet_236());
	private static final long[] mk_tokenSet_237() {
		long[] data = new long[24];
		data[0]=8388608L;
		data[7]=17179869184L;
		data[10]=140737488355328L;
		data[11]=134217728L;
		return data;
	}
	public static final BitSet _tokenSet_237 = new BitSet(mk_tokenSet_237());
	private static final long[] mk_tokenSet_238() {
		long[] data = new long[24];
		data[0]=41943040L;
		data[7]=17179869184L;
		data[10]=140737488355328L;
		data[11]=134217728L;
		return data;
	}
	public static final BitSet _tokenSet_238 = new BitSet(mk_tokenSet_238());
	private static final long[] mk_tokenSet_239() {
		long[] data = new long[16];
		data[7]=1073741824L;
		return data;
	}
	public static final BitSet _tokenSet_239 = new BitSet(mk_tokenSet_239());
	private static final long[] mk_tokenSet_240() {
		long[] data = new long[20];
		data[8]=268435456L;
		data[9]=8L;
		return data;
	}
	public static final BitSet _tokenSet_240 = new BitSet(mk_tokenSet_240());
	private static final long[] mk_tokenSet_241() {
		long[] data = new long[28];
		data[0]=35184391618560L;
		data[6]=1617245264338164272L;
		data[7]=-288286553268555792L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6772217630764686601L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_241 = new BitSet(mk_tokenSet_241());
	private static final long[] mk_tokenSet_242() {
		long[] data = new long[28];
		data[0]=69024219136L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_242 = new BitSet(mk_tokenSet_242());
	private static final long[] mk_tokenSet_243() {
		long[] data = new long[28];
		data[0]=287965184L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219680351745017L;
		data[12]=-464312211670233306L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_243 = new BitSet(mk_tokenSet_243());
	private static final long[] mk_tokenSet_244() {
		long[] data = new long[20];
		data[9]=524288L;
		return data;
	}
	public static final BitSet _tokenSet_244 = new BitSet(mk_tokenSet_244());
	private static final long[] mk_tokenSet_245() {
		long[] data = new long[28];
		data[0]=69007441920L;
		data[6]=1617315633082342000L;
		data[7]=-288286550047326222L;
		data[8]=8357550058131946751L;
		data[9]=-407377011032314L;
		data[10]=-6769895410667216137L;
		data[11]=-1731219680351745017L;
		data[12]=-464312211670233306L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_245 = new BitSet(mk_tokenSet_245());
	private static final long[] mk_tokenSet_246() {
		long[] data = new long[28];
		data[0]=35432863236096L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_246 = new BitSet(mk_tokenSet_246());
	private static final long[] mk_tokenSet_247() {
		long[] data = new long[28];
		data[0]=53042766028288L;
		data[6]=-2990992685624518096L;
		data[7]=-288286536088686603L;
		data[8]=2016479583913639167L;
		data[9]=-407376876292338L;
		data[10]=-4614923040510041353L;
		data[11]=-1729382774378332161L;
		data[12]=-466633967911272601L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_247 = new BitSet(mk_tokenSet_247());
	private static final long[] mk_tokenSet_248() {
		long[] data = new long[22];
		data[10]=17179869184L;
		return data;
	}
	public static final BitSet _tokenSet_248 = new BitSet(mk_tokenSet_248());
	private static final long[] mk_tokenSet_249() {
		long[] data = new long[26];
		data[0]=276824064L;
		data[6]=35184372088832L;
		data[8]=2199023255552L;
		data[10]=17179869184L;
		data[12]=20971520L;
		return data;
	}
	public static final BitSet _tokenSet_249 = new BitSet(mk_tokenSet_249());
	private static final long[] mk_tokenSet_250() {
		long[] data = new long[28];
		data[0]=33795130555768322L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-6481242821983067401L;
		data[11]=-1729390161588122617L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_250 = new BitSet(mk_tokenSet_250());
	private static final long[] mk_tokenSet_251() {
		long[] data = new long[28];
		data[0]=33795096162475522L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407377010507922L;
		data[10]=-2157787179707391241L;
		data[11]=-1729385763541611513L;
		data[12]=-464029237725163610L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_251 = new BitSet(mk_tokenSet_251());
	private static final long[] mk_tokenSet_252() {
		long[] data = new long[28];
		data[0]=33777277282680834L;
		data[6]=4573968371406568176L;
		data[7]=-288285424747544582L;
		data[8]=8357555006211096319L;
		data[9]=-407377011032306L;
		data[10]=-6769473198202150153L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669850L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_252 = new BitSet(mk_tokenSet_252());
	private static final long[] mk_tokenSet_253() {
		long[] data = new long[28];
		data[0]=33777277282680834L;
		data[6]=4573968371406568176L;
		data[7]=-288285424747544582L;
		data[8]=8357555006202707711L;
		data[9]=-407377011032306L;
		data[10]=-6769473198202150153L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669850L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_253 = new BitSet(mk_tokenSet_253());
	private static final long[] mk_tokenSet_254() {
		long[] data = new long[28];
		data[0]=35364747739136L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_254 = new BitSet(mk_tokenSet_254());
	private static final long[] mk_tokenSet_255() {
		long[] data = new long[28];
		data[0]=33830246209428994L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876290290L;
		data[10]=-6769473198202150153L;
		data[11]=-1729382465006728185L;
		data[12]=-464030199818809433L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_255 = new BitSet(mk_tokenSet_255());
	private static final long[] mk_tokenSet_256() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876292346L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_256 = new BitSet(mk_tokenSet_256());
	private static final long[] mk_tokenSet_257() {
		long[] data = new long[28];
		data[0]=33812636843507714L;
		data[6]=-4649403665448207632L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-407376876814578L;
		data[10]=-6769473198202150153L;
		data[11]=-1731212052355347449L;
		data[12]=-464030199818809433L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_257 = new BitSet(mk_tokenSet_257());
	private static final long[] mk_tokenSet_258() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731146391029543929L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_258 = new BitSet(mk_tokenSet_258());
	private static final long[] mk_tokenSet_259() {
		long[] data = new long[28];
		data[0]=3801088L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_259 = new BitSet(mk_tokenSet_259());
	private static final long[] mk_tokenSet_260() {
		long[] data = new long[28];
		data[0]=33777277283729410L;
		data[6]=4573968371406568176L;
		data[7]=-288285424747544582L;
		data[8]=8357555006211096319L;
		data[9]=-407377011032306L;
		data[10]=-6769473198202150153L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669850L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_260 = new BitSet(mk_tokenSet_260());
	private static final long[] mk_tokenSet_261() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1730724178564477945L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_261 = new BitSet(mk_tokenSet_261());
	private static final long[] mk_tokenSet_262() {
		long[] data = new long[28];
		data[0]=35364143759360L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831475997951L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_262 = new BitSet(mk_tokenSet_262());
	private static final long[] mk_tokenSet_263() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1617245264338164272L;
		data[7]=-288286553302110224L;
		data[8]=2016479583771032831L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917833L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_263 = new BitSet(mk_tokenSet_263());
	private static final long[] mk_tokenSet_264() {
		long[] data = new long[28];
		data[0]=33830314895351298L;
		data[6]=-4647714815587943696L;
		data[7]=-288285424747544581L;
		data[8]=8357555006211096319L;
		data[9]=-387585666990226L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_264 = new BitSet(mk_tokenSet_264());
	private static final long[] mk_tokenSet_265() {
		long[] data = new long[28];
		data[0]=35187882328064L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301273L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_265 = new BitSet(mk_tokenSet_265());
	private static final long[] mk_tokenSet_266() {
		long[] data = new long[28];
		data[0]=35364680630272L;
		data[6]=-7603804603958748624L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_266 = new BitSet(mk_tokenSet_266());
	private static final long[] mk_tokenSet_267() {
		long[] data = new long[28];
		data[0]=33830314928905730L;
		data[6]=-36591747113977104L;
		data[7]=-288285424747544577L;
		data[8]=8934015758648737535L;
		data[9]=-407376876290194L;
		data[10]=-4612248976624311561L;
		data[11]=-1729382465006469121L;
		data[12]=-464029100286181401L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_267 = new BitSet(mk_tokenSet_267());
	private static final long[] mk_tokenSet_268() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731290427052782585L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_268 = new BitSet(mk_tokenSet_268());
	private static final long[] mk_tokenSet_269() {
		long[] data = new long[26];
		data[0]=18052284416L;
		data[12]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_269 = new BitSet(mk_tokenSet_269());
	private static final long[] mk_tokenSet_270() {
		long[] data = new long[14];
		data[0]=268435456L;
		return data;
	}
	public static final BitSet _tokenSet_270 = new BitSet(mk_tokenSet_270());
	private static final long[] mk_tokenSet_271() {
		long[] data = new long[18];
		data[8]=549755813888L;
		return data;
	}
	public static final BitSet _tokenSet_271 = new BitSet(mk_tokenSet_271());
	private static final long[] mk_tokenSet_272() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_272 = new BitSet(mk_tokenSet_272());
	private static final long[] mk_tokenSet_273() {
		long[] data = new long[28];
		data[0]=52974113660416L;
		data[6]=-7602678704051906000L;
		data[7]=-288285436577058831L;
		data[8]=2016479583779421439L;
		data[9]=-407376876292338L;
		data[10]=-6772147262020508937L;
		data[11]=-1729382774378591225L;
		data[12]=-466633967911301209L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_273 = new BitSet(mk_tokenSet_273());
	private static final long[] mk_tokenSet_274() {
		long[] data = new long[26];
		data[0]=603979776L;
		data[12]=20971648L;
		return data;
	}
	public static final BitSet _tokenSet_274 = new BitSet(mk_tokenSet_274());
	private static final long[] mk_tokenSet_275() {
		long[] data = new long[26];
		data[12]=128L;
		return data;
	}
	public static final BitSet _tokenSet_275 = new BitSet(mk_tokenSet_275());
	private static final long[] mk_tokenSet_276() {
		long[] data = new long[24];
		data[0]=805306368L;
		data[6]=4294967296L;
		data[10]=-8935141660703064064L;
		data[11]=1026L;
		return data;
	}
	public static final BitSet _tokenSet_276 = new BitSet(mk_tokenSet_276());
	private static final long[] mk_tokenSet_277() {
		long[] data = new long[28];
		data[0]=33830280535612930L;
		data[6]=-4648277765541365008L;
		data[7]=-288285424747544581L;
		data[8]=8357555006345314047L;
		data[9]=-407376876290194L;
		data[10]=-6769473198134779145L;
		data[11]=-1729382465006728185L;
		data[12]=-464029100286210137L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_277 = new BitSet(mk_tokenSet_277());
	private static final long[] mk_tokenSet_278() {
		long[] data = new long[26];
		data[8]=268435456L;
		data[10]=281474976710656L;
		data[12]=131072L;
		return data;
	}
	public static final BitSet _tokenSet_278 = new BitSet(mk_tokenSet_278());
	private static final long[] mk_tokenSet_279() {
		long[] data = new long[14];
		data[0]=67108864L;
		data[6]=562949953421312L;
		return data;
	}
	public static final BitSet _tokenSet_279 = new BitSet(mk_tokenSet_279());
	private static final long[] mk_tokenSet_280() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_280 = new BitSet(mk_tokenSet_280());
	private static final long[] mk_tokenSet_281() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550608156196607L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_281 = new BitSet(mk_tokenSet_281());
	private static final long[] mk_tokenSet_282() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550608156196095L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_282 = new BitSet(mk_tokenSet_282());
	private static final long[] mk_tokenSet_283() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8934010810703805695L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464013144503677146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_283 = new BitSet(mk_tokenSet_283());
	private static final long[] mk_tokenSet_284() {
		long[] data = new long[28];
		data[0]=35188016545792L;
		data[6]=-7606056403772433872L;
		data[7]=-288286553268555791L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816634L;
		data[10]=-6772147262020508937L;
		data[11]=-1731287128517899257L;
		data[12]=-466633967911301337L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_284 = new BitSet(mk_tokenSet_284());
	private static final long[] mk_tokenSet_285() {
		long[] data = new long[28];
		data[0]=35364210868224L;
		data[6]=-7603804603958748624L;
		data[7]=-288285453756928015L;
		data[8]=1440018831467609343L;
		data[9]=-407376876816626L;
		data[10]=-6772147262020508937L;
		data[11]=-1731212361727210489L;
		data[12]=-466633967890329689L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_285 = new BitSet(mk_tokenSet_285());
	private static final long[] mk_tokenSet_286() {
		long[] data = new long[28];
		data[0]=52974113725952L;
		data[6]=-2990992685624517840L;
		data[7]=-288285436577058827L;
		data[8]=2016479583913639167L;
		data[9]=-407376876292338L;
		data[10]=-4614923040510041353L;
		data[11]=-1729382774378332161L;
		data[12]=-466633967890300953L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_286 = new BitSet(mk_tokenSet_286());
	private static final long[] mk_tokenSet_287() {
		long[] data = new long[26];
		data[12]=59055800320L;
		return data;
	}
	public static final BitSet _tokenSet_287 = new BitSet(mk_tokenSet_287());
	private static final long[] mk_tokenSet_288() {
		long[] data = new long[28];
		data[0]=271253504L;
		data[6]=1473130076262308656L;
		data[7]=-288286553302110224L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530679917961L;
		data[11]=-1731290427053044729L;
		data[12]=-466634354458357978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_288 = new BitSet(mk_tokenSet_288());
	private static final long[] mk_tokenSet_289() {
		long[] data = new long[28];
		data[0]=29273677688930306L;
		data[6]=1617913767944748016L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-405177987776754L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_289 = new BitSet(mk_tokenSet_289());
	private static final long[] mk_tokenSet_290() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_290 = new BitSet(mk_tokenSet_290());
	private static final long[] mk_tokenSet_291() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030667970244826L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_291 = new BitSet(mk_tokenSet_291());
	private static final long[] mk_tokenSet_292() {
		long[] data = new long[28];
		data[0]=29273677588201474L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464013144503677146L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_292 = new BitSet(mk_tokenSet_292());
	private static final long[] mk_tokenSet_293() {
		long[] data = new long[26];
		data[0]=8388608L;
		data[6]=35184372088832L;
		data[12]=128L;
		return data;
	}
	public static final BitSet _tokenSet_293 = new BitSet(mk_tokenSet_293());
	private static final long[] mk_tokenSet_294() {
		long[] data = new long[26];
		data[0]=67108864L;
		data[12]=20971648L;
		return data;
	}
	public static final BitSet _tokenSet_294 = new BitSet(mk_tokenSet_294());
	private static final long[] mk_tokenSet_295() {
		long[] data = new long[28];
		data[0]=2752512L;
		data[6]=1473130076262308400L;
		data[7]=-288285453790482448L;
		data[8]=1440018831467609343L;
		data[9]=-407377011034362L;
		data[10]=-6773343530671529353L;
		data[11]=-1731290427053044729L;
		data[12]=-466634380228161754L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_295 = new BitSet(mk_tokenSet_295());
	private static final long[] mk_tokenSet_296() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617913767944747760L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-387585801208050L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612114698330L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_296 = new BitSet(mk_tokenSet_296());
	private static final long[] mk_tokenSet_297() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617913767944747760L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-405177987252466L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_297 = new BitSet(mk_tokenSet_297());
	private static final long[] mk_tokenSet_298() {
		long[] data = new long[28];
		data[0]=29273677688864770L;
		data[6]=1617913767944747760L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-405177987252466L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_298 = new BitSet(mk_tokenSet_298());
	private static final long[] mk_tokenSet_299() {
		long[] data = new long[14];
		data[0]=4398046511104L;
		return data;
	}
	public static final BitSet _tokenSet_299 = new BitSet(mk_tokenSet_299());
	private static final long[] mk_tokenSet_300() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617913767944747760L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-405177987252466L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_300 = new BitSet(mk_tokenSet_300());
	private static final long[] mk_tokenSet_301() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617913767944747760L;
		data[7]=-288286541439041542L;
		data[8]=8357555006202707711L;
		data[9]=-387585801208050L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_301 = new BitSet(mk_tokenSet_301());
	private static final long[] mk_tokenSet_302() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617913767944747760L;
		data[7]=-288285441927413766L;
		data[8]=8357555006202707711L;
		data[9]=-387585801208050L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030612135669978L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_302 = new BitSet(mk_tokenSet_302());
	private static final long[] mk_tokenSet_303() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032210L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_303 = new BitSet(mk_tokenSet_303());
	private static final long[] mk_tokenSet_304() {
		long[] data = new long[28];
		data[0]=29273677655310338L;
		data[6]=1617350817991326448L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736689721562L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_304 = new BitSet(mk_tokenSet_304());
	private static final long[] mk_tokenSet_305() {
		long[] data = new long[24];
		data[0]=17448304640L;
		data[9]=17592186044416L;
		data[11]=70368744177664L;
		return data;
	}
	public static final BitSet _tokenSet_305 = new BitSet(mk_tokenSet_305());
	private static final long[] mk_tokenSet_306() {
		long[] data = new long[28];
		data[0]=29273672823472130L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464030736693391578L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_306 = new BitSet(mk_tokenSet_306());
	private static final long[] mk_tokenSet_307() {
		long[] data = new long[26];
		data[12]=16777216L;
		return data;
	}
	public static final BitSet _tokenSet_307 = new BitSet(mk_tokenSet_307());
	private static final long[] mk_tokenSet_308() {
		long[] data = new long[28];
		data[0]=29273672756363266L;
		data[6]=1617315633619237616L;
		data[7]=-288286541439041542L;
		data[8]=8357550058400382207L;
		data[9]=-407377011032306L;
		data[10]=-6769613935690505481L;
		data[11]=-1731219748937004025L;
		data[12]=-464312142950625498L;
		data[13]=2305807816250621949L;
		return data;
	}
	public static final BitSet _tokenSet_308 = new BitSet(mk_tokenSet_308());
	private static final long[] mk_tokenSet_309() {
		long[] data = new long[26];
		data[12]=4503599627370496L;
		return data;
	}
	public static final BitSet _tokenSet_309 = new BitSet(mk_tokenSet_309());
	private static final long[] mk_tokenSet_310() {
		long[] data = new long[28];
		data[12]=576460752303423488L;
		data[13]=288230376151711744L;
		return data;
	}
	public static final BitSet _tokenSet_310 = new BitSet(mk_tokenSet_310());
	private static final long[] mk_tokenSet_311() {
		long[] data = new long[28];
		data[13]=288230376151711744L;
		return data;
	}
	public static final BitSet _tokenSet_311 = new BitSet(mk_tokenSet_311());
	private static final long[] mk_tokenSet_312() {
		long[] data = new long[14];
		data[0]=621805568L;
		return data;
	}
	public static final BitSet _tokenSet_312 = new BitSet(mk_tokenSet_312());
	private static final long[] mk_tokenSet_313() {
		long[] data = new long[14];
		data[0]=607649792L;
		return data;
	}
	public static final BitSet _tokenSet_313 = new BitSet(mk_tokenSet_313());
	private static final long[] mk_tokenSet_314() {
		long[] data = new long[28];
		data[0]=603979776L;
		data[6]=8589934592L;
		data[10]=144115188075855872L;
		data[12]=-2305843009213693952L;
		data[13]=2145520676L;
		return data;
	}
	public static final BitSet _tokenSet_314 = new BitSet(mk_tokenSet_314());
	private static final long[] mk_tokenSet_315() {
		long[] data = new long[28];
		data[10]=144115188075855872L;
		data[12]=6917529027641081856L;
		data[13]=1071778816L;
		return data;
	}
	public static final BitSet _tokenSet_315 = new BitSet(mk_tokenSet_315());
	private static final long[] mk_tokenSet_316() {
		long[] data = new long[28];
		data[0]=536870912L;
		data[6]=8589934592L;
		data[13]=1073741824L;
		return data;
	}
	public static final BitSet _tokenSet_316 = new BitSet(mk_tokenSet_316());
	private static final long[] mk_tokenSet_317() {
		long[] data = new long[14];
		data[0]=536870912L;
		data[6]=8589934592L;
		return data;
	}
	public static final BitSet _tokenSet_317 = new BitSet(mk_tokenSet_317());
	private static final long[] mk_tokenSet_318() {
		long[] data = new long[28];
		data[0]=536870912L;
		data[6]=8589934592L;
		data[10]=144115188075855872L;
		data[12]=6917529027641081856L;
		data[13]=2145520640L;
		return data;
	}
	public static final BitSet _tokenSet_318 = new BitSet(mk_tokenSet_318());
	private static final long[] mk_tokenSet_319() {
		long[] data = new long[28];
		data[0]=872415232L;
		data[6]=8589934592L;
		data[9]=36028797018963968L;
		data[13]=36096968887369728L;
		return data;
	}
	public static final BitSet _tokenSet_319 = new BitSet(mk_tokenSet_319());
	private static final long[] mk_tokenSet_320() {
		long[] data = new long[28];
		data[0]=872415232L;
		data[6]=8589934592L;
		data[9]=36028797018963968L;
		data[13]=36028799166447616L;
		return data;
	}
	public static final BitSet _tokenSet_320 = new BitSet(mk_tokenSet_320());
	private static final long[] mk_tokenSet_321() {
		long[] data = new long[14];
		data[0]=620756992L;
		return data;
	}
	public static final BitSet _tokenSet_321 = new BitSet(mk_tokenSet_321());
	private static final long[] mk_tokenSet_322() {
		long[] data = new long[28];
		data[0]=603979776L;
		data[10]=360287970189639680L;
		data[11]=14976L;
		data[13]=33636259716923392L;
		return data;
	}
	public static final BitSet _tokenSet_322 = new BitSet(mk_tokenSet_322());
	
	}
