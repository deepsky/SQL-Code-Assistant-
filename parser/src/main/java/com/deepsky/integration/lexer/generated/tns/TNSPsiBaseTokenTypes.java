package com.deepsky.integration.lexer.generated.tns;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.deepsky.integration.tns.TNSTokenType;

public interface TNSPsiBaseTokenTypes {

		IElementType DESCRIPTION_LIST = new TNSTokenType(8, "DESCRIPTION_LIST");
		IElementType ESC = new TNSTokenType(89, "ESC");
		IElementType DOUBLEDOT = new TNSTokenType(80, "DOUBLEDOT");
		IElementType ANY_CHARACTER = new TNSTokenType(67, "ANY_CHARACTER");
		IElementType BAD_CHARACTER = new TNSTokenType(65, "BAD_CHARACTER");
		IElementType ASTERISK = new TNSTokenType(72, "ASTERISK");
		IElementType ADDRESS_ELEMENT = new TNSTokenType(11, "ADDRESS_ELEMENT");
		IElementType ML_COMMENT = new TNSTokenType(94, "ML_COMMENT");
		IElementType STRING_LITERAL = new TNSTokenType(63, "STRING_LITERAL");
		IElementType DESCRIPTION = new TNSTokenType(9, "DESCRIPTION");
		IElementType COMMA = new TNSTokenType(70, "COMMA");
		IElementType IDENTIFIER = new TNSTokenType(20, "IDENTIFIER");
		IElementType QUOTED_STRING = new TNSTokenType(44, "QUOTED_STRING");
		IElementType PLUS = new TNSTokenType(76, "PLUS");
		IElementType CLOSE_PAREN = new TNSTokenType(16, "CLOSE_PAREN");
		IElementType PASS_BY_NAME = new TNSTokenType(86, "PASS_BY_NAME");
		IElementType BAD_ML_COMMENT = new TNSTokenType(64, "BAD_ML_COMMENT");
		IElementType EQ = new TNSTokenType(13, "EQ");
		IElementType DOT = new TNSTokenType(49, "DOT");
		IElementType ASSIGNMENT_EQ = new TNSTokenType(85, "ASSIGNMENT_EQ");
		IElementType DIVIDE = new TNSTokenType(78, "DIVIDE");
		IElementType START_LABEL = new TNSTokenType(83, "START_LABEL");
		IElementType OPEN_BRACK = new TNSTokenType(74, "OPEN_BRACK");
		IElementType SHARP = new TNSTokenType(71, "SHARP");
		IElementType CONCAT = new TNSTokenType(81, "CONCAT");
		IElementType CLOSE_BRACK = new TNSTokenType(75, "CLOSE_BRACK");
		IElementType N = new TNSTokenType(88, "N");
		IElementType BAD_CHAR_LITERAL = new TNSTokenType(66, "BAD_CHAR_LITERAL");
		IElementType NUMBER = new TNSTokenType(34, "NUMBER");
		IElementType OPEN_PAREN = new TNSTokenType(14, "OPEN_PAREN");
		IElementType AT_SIGN = new TNSTokenType(73, "AT_SIGN");
		IElementType HEX_DIGIT = new TNSTokenType(90, "HEX_DIGIT");
		IElementType MINUS = new TNSTokenType(77, "MINUS");
		IElementType NETWORK_ALIAS = new TNSTokenType(7, "NETWORK_ALIAS");
		IElementType SEMI = new TNSTokenType(68, "SEMI");
		IElementType PERCENTAGE = new TNSTokenType(79, "PERCENTAGE");
		IElementType HOST_ADDRESS = new TNSTokenType(12, "HOST_ADDRESS");
		IElementType VERTBAR = new TNSTokenType(87, "VERTBAR");
		IElementType COLON = new TNSTokenType(69, "COLON");
		IElementType ERROR_TOKEN_A = new TNSTokenType(6, "ERROR_TOKEN_A");
		IElementType WS = new TNSTokenType(91, "WS");
		IElementType END_LABEL = new TNSTokenType(84, "END_LABEL");
		IElementType ADDRESS = new TNSTokenType(10, "ADDRESS");
		IElementType START_RULE = new TNSTokenType(4, "START_RULE");
		IElementType SL_COMMENT = new TNSTokenType(93, "SL_COMMENT");
		IElementType NET_SERVICE_DESC = new TNSTokenType(5, "NET_SERVICE_DESC");
		IElementType SERVICE_NAME = new TNSTokenType(21, "SERVICE_NAME");
		IElementType OUTER_JOIN = new TNSTokenType(82, "OUTER_JOIN");
		IElementType LF = new TNSTokenType(92, "LF");
		IElementType KEYWORD_PORT = new TNSTokenType(46, "PORT");
		IElementType KEYWORD_HS = new TNSTokenType(58, "HS");
		IElementType KEYWORD_POOLED = new TNSTokenType(56, "POOLED");
		IElementType KEYWORD_ARGS = new TNSTokenType(43, "ARGS");
		IElementType KEYWORD_NO = new TNSTokenType(26, "NO");
		IElementType KEYWORD_FAILOVER = new TNSTokenType(32, "FAILOVER");
		IElementType KEYWORD_ENABLE = new TNSTokenType(22, "ENABLE");
		IElementType KEYWORD_DESCRIPTION_LIST = new TNSTokenType(15, "DESCRIPTION_LIST");
		IElementType KEYWORD_ADDRESS_LIST = new TNSTokenType(37, "ADDRESS_LIST");
		IElementType KEYWORD_SECURITY = new TNSTokenType(61, "SECURITY");
		IElementType KEYWORD_INSTANCE_NAME = new TNSTokenType(51, "INSTANCE_NAME");
		IElementType KEYWORD_SERVICE_NAME = new TNSTokenType(50, "SERVICE_NAME");
		IElementType KEYWORD_TYPE_OF_SERVICE = new TNSTokenType(19, "TYPE_OF_SERVICE");
		IElementType KEYWORD_CONNECT_DATA = new TNSTokenType(18, "CONNECT_DATA");
		IElementType KEYWORD_DEDICATED = new TNSTokenType(54, "DEDICATED");
		IElementType KEYWORD_DESCRIPTION = new TNSTokenType(17, "DESCRIPTION");
		IElementType KEYWORD_ARGV0 = new TNSTokenType(42, "ARGV0");
		IElementType KEYWORD_ON = new TNSTokenType(27, "ON");
		IElementType KEYWORD_FALSE = new TNSTokenType(31, "FALSE");
		IElementType KEYWORD_OK = new TNSTokenType(59, "OK");
		IElementType KEYWORD_KEY = new TNSTokenType(48, "KEY");
		IElementType KEYWORD_SEND_BUF_SIZE = new TNSTokenType(35, "SEND_BUF_SIZE");
		IElementType KEYWORD_SHARED = new TNSTokenType(55, "SHARED");
		IElementType KEYWORD_LOAD_BALANCE = new TNSTokenType(29, "LOAD_BALANCE");
		IElementType KEYWORD_SID = new TNSTokenType(57, "SID");
		IElementType KEYWORD_SDU = new TNSTokenType(36, "SDU");
		IElementType KEYWORD_PROTOCOL = new TNSTokenType(39, "PROTOCOL");
		IElementType KEYWORD_HOST = new TNSTokenType(45, "HOST");
		IElementType KEYWORD_COMMUNITY = new TNSTokenType(40, "COMMUNITY");
		IElementType KEYWORD_TRUE = new TNSTokenType(30, "TRUE");
		IElementType KEYWORD_SSL_SERVER_CERT_DN = new TNSTokenType(62, "SSL_SERVER_CERT_DN");
		IElementType KEYWORD_GLOBAL_NAME = new TNSTokenType(52, "GLOBAL_NAME");
		IElementType KEYWORD_YES = new TNSTokenType(25, "YES");
		IElementType KEYWORD_SOURCE_ROUTE = new TNSTokenType(24, "SOURCE_ROUTE");
		IElementType KEYWORD_BROKEN = new TNSTokenType(23, "BROKEN");
		IElementType KEYWORD_PRESENTATION = new TNSTokenType(60, "PRESENTATION");
		IElementType KEYWORD_ADDRESS = new TNSTokenType(38, "ADDRESS");
		IElementType KEYWORD_PROGRAM = new TNSTokenType(41, "PROGRAM");
		IElementType KEYWORD_SERVICE = new TNSTokenType(47, "SERVICE");
		IElementType KEYWORD_OFF = new TNSTokenType(28, "OFF");
		IElementType KEYWORD_SERVER = new TNSTokenType(53, "SERVER");
		IElementType KEYWORD_RECV_BUF_SIZE = new TNSTokenType(33, "RECV_BUF_SIZE");

	TokenSet KEYWORDS = TokenSet.create(
		KEYWORD_PORT,
		KEYWORD_HS,
		KEYWORD_POOLED,
		KEYWORD_ARGS,
		KEYWORD_NO,
		KEYWORD_FAILOVER,
		KEYWORD_ENABLE,
		KEYWORD_DESCRIPTION_LIST,
		KEYWORD_ADDRESS_LIST,
		KEYWORD_SECURITY,
		KEYWORD_INSTANCE_NAME,
		KEYWORD_SERVICE_NAME,
		KEYWORD_TYPE_OF_SERVICE,
		KEYWORD_CONNECT_DATA,
		KEYWORD_DEDICATED,
		KEYWORD_DESCRIPTION,
		KEYWORD_ARGV0,
		KEYWORD_ON,
		KEYWORD_FALSE,
		KEYWORD_OK,
		KEYWORD_KEY,
		KEYWORD_SEND_BUF_SIZE,
		KEYWORD_SHARED,
		KEYWORD_LOAD_BALANCE,
		KEYWORD_SID,
		KEYWORD_SDU,
		KEYWORD_PROTOCOL,
		KEYWORD_HOST,
		KEYWORD_COMMUNITY,
		KEYWORD_TRUE,
		KEYWORD_SSL_SERVER_CERT_DN,
		KEYWORD_GLOBAL_NAME,
		KEYWORD_YES,
		KEYWORD_SOURCE_ROUTE,
		KEYWORD_BROKEN,
		KEYWORD_PRESENTATION,
		KEYWORD_ADDRESS,
		KEYWORD_PROGRAM,
		KEYWORD_SERVICE,
		KEYWORD_OFF,
		KEYWORD_SERVER,
		KEYWORD_RECV_BUF_SIZE,
		new TNSTokenType(9999, "$$$END_OF_ARRAY_$$$")
		);
}
