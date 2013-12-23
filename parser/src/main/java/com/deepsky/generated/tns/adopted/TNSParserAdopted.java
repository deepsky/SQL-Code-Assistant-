// $ANTLR 2.7.5 (20050128): "tnsnames_ex.g" -> "TNSParserAdopted.java"$

package com.deepsky.generated.tns.adopted;

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

public class TNSParserAdopted extends antlr.LLkParser       implements TNSTokenTypes
 {

    protected int returnType = -1;

    public void __markRule(int type){
        returnType = type;
    }

    boolean isTypeName(Token t){
        return true;
    }

    protected boolean recoverErrorAndCheckEOF() throws TokenStreamException, MismatchedTokenException {
        throw new Error("recoverErrorAndCheckEOF() should be overridden in derived classes!");
    }

protected TNSParserAdopted(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TNSParserAdopted(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected TNSParserAdopted(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TNSParserAdopted(TokenStream lexer) {
  this(lexer,2);
}

public TNSParserAdopted(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public void start_rule() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop3:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					net_service_desc();
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public void net_service_desc() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			network_alias();
			match(EQ);
			{
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_description_list)) {
				description_list();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_description)) {
				description();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			}
			__markRule(NET_SERVICE_DESC);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public void network_alias() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			case LITERAL_community:
			case LITERAL_program:
			case LITERAL_server:
			{
				identifier2();
				break;
			}
			case SERVICE_NAME:
			{
				match(SERVICE_NAME);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			__markRule(NETWORK_ALIAS);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public void description_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			match(LITERAL_description_list);
			match(EQ);
			{
			int _cnt9=0;
			_loop9:
			do {
				if ((LA(1)==OPEN_PAREN)) {
					description();
				}
				else {
					if ( _cnt9>=1 ) { break _loop9; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt9++;
			} while (true);
			}
			match(CLOSE_PAREN);
			__markRule(DESCRIPTION_LIST);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public void description() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			match(OPEN_PAREN);
			match(LITERAL_description);
			match(EQ);
			{
			int _cnt13=0;
			_loop13:
			do {
				if ((LA(1)==OPEN_PAREN)) {
					description_params();
				}
				else {
					if ( _cnt13>=1 ) { break _loop13; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt13++;
			} while (true);
			}
			match(CLOSE_PAREN);
			}
			__markRule(DESCRIPTION);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public void description_params() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2)))) {
				multi_address_parameters();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_address_list||LA(2)==LITERAL_address)) {
				address_info();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_connect_data)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_connect_data);
				match(EQ);
				{
				int _cnt17=0;
				_loop17:
				do {
					if ((LA(1)==OPEN_PAREN)) {
						connect_data();
					}
					else {
						if ( _cnt17>=1 ) { break _loop17; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt17++;
				} while (true);
				}
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_type_of_service)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_type_of_service);
				match(EQ);
				match(IDENTIFIER);
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_security)) {
				security();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void multi_address_parameters() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_enable)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_enable);
				match(EQ);
				match(LITERAL_broken);
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_source_route)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_source_route);
				match(EQ);
				{
				switch ( LA(1)) {
				case LITERAL_yes:
				{
					match(LITERAL_yes);
					break;
				}
				case LITERAL_no:
				{
					match(LITERAL_no);
					break;
				}
				case LITERAL_on:
				{
					match(LITERAL_on);
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
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_load_balance)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_load_balance);
				match(EQ);
				{
				switch ( LA(1)) {
				case LITERAL_on:
				{
					match(LITERAL_on);
					break;
				}
				case LITERAL_off:
				{
					match(LITERAL_off);
					break;
				}
				case LITERAL_yes:
				{
					match(LITERAL_yes);
					break;
				}
				case LITERAL_no:
				{
					match(LITERAL_no);
					break;
				}
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
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_failover)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_failover);
				match(EQ);
				{
				switch ( LA(1)) {
				case LITERAL_on:
				{
					match(LITERAL_on);
					break;
				}
				case LITERAL_off:
				{
					match(LITERAL_off);
					break;
				}
				case LITERAL_yes:
				{
					match(LITERAL_yes);
					break;
				}
				case LITERAL_no:
				{
					match(LITERAL_no);
					break;
				}
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
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_recv_buf_size)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_recv_buf_size);
				match(EQ);
				match(NUMBER);
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_send_buf_size)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_send_buf_size);
				match(EQ);
				match(NUMBER);
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_sdu)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_sdu);
				match(EQ);
				match(NUMBER);
				match(CLOSE_PAREN);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void address_info() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_address_list)) {
				address_list();
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_address)) {
				address();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void connect_data() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_service_name)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_service_name);
				match(EQ);
				service_name1();
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_instance_name)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_instance_name);
				match(EQ);
				service_name1();
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_global_name)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_global_name);
				match(EQ);
				service_name1();
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_server)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_server);
				match(EQ);
				{
				switch ( LA(1)) {
				case LITERAL_dedicated:
				{
					match(LITERAL_dedicated);
					break;
				}
				case LITERAL_shared:
				{
					match(LITERAL_shared);
					break;
				}
				case LITERAL_pooled:
				{
					match(LITERAL_pooled);
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
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_sid)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_sid);
				match(EQ);
				service_name1();
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_hs)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_hs);
				match(EQ);
				match(LITERAL_ok);
				match(CLOSE_PAREN);
				}
			}
			else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_presentation)) {
				{
				match(OPEN_PAREN);
				match(LITERAL_presentation);
				match(EQ);
				service_name1();
				match(CLOSE_PAREN);
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void security() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			match(LITERAL_security);
			match(EQ);
			{
			match(OPEN_PAREN);
			match(LITERAL_ssl_server_cert_dn);
			match(EQ);
			match(STRING_LITERAL);
			match(CLOSE_PAREN);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void identifier2() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				match(IDENTIFIER);
				break;
			}
			case LITERAL_server:
			{
				match(LITERAL_server);
				break;
			}
			case LITERAL_community:
			{
				match(LITERAL_community);
				break;
			}
			case LITERAL_program:
			{
				match(LITERAL_program);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public void address_list() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			match(LITERAL_address_list);
			match(EQ);
			{
			int _cnt35=0;
			_loop35:
			do {
				if ((LA(1)==OPEN_PAREN) && (_tokenSet_5.member(LA(2)))) {
					multi_address_parameters();
				}
				else if ((LA(1)==OPEN_PAREN) && (LA(2)==LITERAL_address)) {
					address();
				}
				else {
					if ( _cnt35>=1 ) { break _loop35; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt35++;
			} while (true);
			}
			match(CLOSE_PAREN);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void address() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(OPEN_PAREN);
			match(LITERAL_address);
			match(EQ);
			protocol_address_information();
			match(CLOSE_PAREN);
			__markRule(ADDRESS);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public void protocol_address_information() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt39=0;
			_loop39:
			do {
				if ((LA(1)==OPEN_PAREN)) {
					match(OPEN_PAREN);
					address_element();
					match(CLOSE_PAREN);
				}
				else {
					if ( _cnt39>=1 ) { break _loop39; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt39++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public void address_element() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_protocol:
			{
				{
				match(LITERAL_protocol);
				match(EQ);
				match(IDENTIFIER);
				}
				break;
			}
			case LITERAL_community:
			{
				{
				match(LITERAL_community);
				match(EQ);
				match(IDENTIFIER);
				}
				break;
			}
			case LITERAL_program:
			{
				{
				match(LITERAL_program);
				match(EQ);
				match(IDENTIFIER);
				}
				break;
			}
			case 42:
			{
				{
				match(42);
				match(EQ);
				match(IDENTIFIER);
				}
				break;
			}
			case LITERAL_args:
			{
				{
				match(LITERAL_args);
				match(EQ);
				match(QUOTED_STRING);
				}
				break;
			}
			case LITERAL_host:
			{
				{
				match(LITERAL_host);
				match(EQ);
				host_address();
				}
				break;
			}
			case LITERAL_port:
			{
				{
				match(LITERAL_port);
				match(EQ);
				match(NUMBER);
				}
				break;
			}
			case LITERAL_service:
			{
				{
				match(LITERAL_service);
				match(EQ);
				service_name1();
				}
				break;
			}
			case LITERAL_key:
			{
				{
				match(LITERAL_key);
				match(EQ);
				service_name1();
				}
				break;
			}
			case LITERAL_send_buf_size:
			{
				{
				match(LITERAL_send_buf_size);
				match(EQ);
				match(NUMBER);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			__markRule(ADDRESS_ELEMENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public void host_address() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				match(IDENTIFIER);
				break;
			}
			case SERVICE_NAME:
			{
				match(SERVICE_NAME);
				break;
			}
			case NUMBER:
			{
				{
				match(NUMBER);
				{
				_loop56:
				do {
					if ((LA(1)==DOT)) {
						match(DOT);
						match(NUMBER);
					}
					else {
						break _loop56;
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
			__markRule(HOST_ADDRESS);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public void service_name1() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENTIFIER:
			{
				match(IDENTIFIER);
				break;
			}
			case SERVICE_NAME:
			{
				match(SERVICE_NAME);
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
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public void no_one_should_call_me() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENTIFIER);
			__markRule(ERROR_TOKEN_A);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"START_RULE",
		"NET_SERVICE_DESC",
		"ERROR_TOKEN_A",
		"NETWORK_ALIAS",
		"DESCRIPTION_LIST",
		"DESCRIPTION",
		"ADDRESS",
		"ADDRESS_ELEMENT",
		"HOST_ADDRESS",
		"EQ",
		"OPEN_PAREN",
		"\"description_list\"",
		"CLOSE_PAREN",
		"\"description\"",
		"\"connect_data\"",
		"\"type_of_service\"",
		"IDENTIFIER",
		"SERVICE_NAME",
		"\"enable\"",
		"\"broken\"",
		"\"source_route\"",
		"\"yes\"",
		"\"no\"",
		"\"on\"",
		"\"off\"",
		"\"load_balance\"",
		"\"true\"",
		"\"false\"",
		"\"failover\"",
		"\"recv_buf_size\"",
		"NUMBER",
		"\"send_buf_size\"",
		"\"sdu\"",
		"\"address_list\"",
		"\"address\"",
		"\"protocol\"",
		"\"community\"",
		"\"program\"",
		"\"argv0\"",
		"\"args\"",
		"QUOTED_STRING",
		"\"host\"",
		"\"port\"",
		"\"service\"",
		"\"key\"",
		"DOT",
		"\"service_name\"",
		"\"instance_name\"",
		"\"global_name\"",
		"\"server\"",
		"\"dedicated\"",
		"\"shared\"",
		"\"pooled\"",
		"\"sid\"",
		"\"hs\"",
		"\"ok\"",
		"\"presentation\"",
		"\"security\"",
		"\"ssl_server_cert_dn\"",
		"STRING_LITERAL",
		"BAD_ML_COMMENT",
		"BAD_CHARACTER",
		"BAD_CHAR_LITERAL",
		"ANY_CHARACTER",
		"SEMI",
		"COLON",
		"COMMA",
		"SHARP",
		"ASTERISK",
		"AT_SIGN",
		"OPEN_BRACK",
		"CLOSE_BRACK",
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
		"N",
		"ESC",
		"HEX_DIGIT",
		"WS",
		"LF",
		"SL_COMMENT",
		"ML_COMMENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 9010497792770048L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 9010497792770050L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 8192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 9010497792851970L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 116521959424L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 81920L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 65536L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
