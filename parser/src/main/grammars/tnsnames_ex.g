header {
package com.deepsky.generated.tns.adopted;
}


class TNSParserAdopted extends Parser;

options {
    exportVocab = TNS;
    k = 2;
    buildAST = false;
}

tokens {
    START_RULE;
    NET_SERVICE_DESC;
    ERROR_TOKEN_A;
    NETWORK_ALIAS;
    DESCRIPTION_LIST;
    DESCRIPTION;
    ADDRESS; ADDRESS_ELEMENT; HOST_ADDRESS;
}

{
    protected int returnType = -1;

    public final void __markRule(int type){
        returnType = type;
    }

    boolean isTypeName(Token t){
        return true;
    }

    protected boolean recoverErrorAndCheckEOF() throws TokenStreamException, MismatchedTokenException {
        throw new Error("recoverErrorAndCheckEOF() should be overridden in derived classes!");
    }
}


start_rule:
    (net_service_desc)*
    ;

net_service_desc:
    ( network_alias EQ (description_list | description) )
    {  __markRule(NET_SERVICE_DESC);}
    ;

description_list:
    OPEN_PAREN "description_list" EQ (description)+ CLOSE_PAREN
    {  __markRule(DESCRIPTION_LIST);}
    ;

description:
    (
        OPEN_PAREN "description" EQ (description_params)+ CLOSE_PAREN
    )
    {  __markRule(DESCRIPTION);}
    ;

description_params:
    multi_address_parameters
    | address_info
    | (OPEN_PAREN "connect_data" EQ (connect_data)+ CLOSE_PAREN)
    | (OPEN_PAREN "type_of_service" EQ IDENTIFIER CLOSE_PAREN)
    | security
    ;

network_alias:
    (identifier2 | SERVICE_NAME)
    {  __markRule(NETWORK_ALIAS);}
    ;

multi_address_parameters:
    (OPEN_PAREN "enable" EQ  "broken" CLOSE_PAREN)
    | (OPEN_PAREN "source_route" EQ  ("yes"|"no"|"on"|"off") CLOSE_PAREN)
    | (OPEN_PAREN "load_balance" EQ  ("on"|"off"|"yes"|"no"|"true"|"false") CLOSE_PAREN)
    | (OPEN_PAREN "failover" EQ  ("on"|"off"|"yes"|"no"|"true"|"false") CLOSE_PAREN)
    | (OPEN_PAREN "recv_buf_size" EQ NUMBER CLOSE_PAREN)
    | (OPEN_PAREN "send_buf_size" EQ NUMBER CLOSE_PAREN)
    | (OPEN_PAREN "sdu" EQ NUMBER CLOSE_PAREN)
    ;

address_info:
    address_list | address
    ;

address_list:
    OPEN_PAREN "address_list" EQ
        (multi_address_parameters | address)+
    CLOSE_PAREN
    ;


address:
    OPEN_PAREN "address" EQ  protocol_address_information  CLOSE_PAREN
    {  __markRule(ADDRESS);}
    ;

// (PROTOCOL=tcp)(HOST=host1)(PORT=1630)(SEND_BUF_SIZE=11784)
protocol_address_information:
    (OPEN_PAREN address_element CLOSE_PAREN)+
    ;

address_element:
(
    ("protocol" EQ IDENTIFIER)
    | ("community" EQ IDENTIFIER )
    | ("program" EQ IDENTIFIER )
    | ("argv0" EQ IDENTIFIER )
    | ("args" EQ QUOTED_STRING )
    | ("host" EQ host_address )
    | ("port" EQ NUMBER)
    | ("service" EQ service_name1)
    | ("key" EQ service_name1)
    | ("send_buf_size" EQ NUMBER)
)
    {  __markRule(ADDRESS_ELEMENT);}
    ;

//(PROTOCOL = BEQ)(PROGRAM = oracle)
//          (ARGV0 = oracleDONNA)
//          (ARGS = '(DESCRIPTION=(LOCAL=YES)(ADDRESS=(PROTOCOL=BEQ)))')

host_address:
(
    IDENTIFIER
    | SERVICE_NAME
    | (NUMBER (DOT NUMBER)*)
)
    {  __markRule(HOST_ADDRESS);}
    ;

connect_data:
    (OPEN_PAREN "service_name" EQ service_name1 CLOSE_PAREN)
    | (OPEN_PAREN "instance_name" EQ service_name1 CLOSE_PAREN)
    | (OPEN_PAREN "global_name" EQ service_name1 CLOSE_PAREN)
    | (OPEN_PAREN "server" EQ ("dedicated"|"shared"|"pooled") CLOSE_PAREN)
    | (OPEN_PAREN "sid" EQ service_name1 CLOSE_PAREN)
    | (OPEN_PAREN "hs" EQ "ok" CLOSE_PAREN)
    | (OPEN_PAREN "presentation" EQ service_name1 CLOSE_PAREN)
    ;

service_name1:
    (IDENTIFIER | SERVICE_NAME)
    ;

// Example:
//(SECURITY=
//    (SSL_SERVER_CERT_DN="cn=finance,cn=OracleContext,dc=us,dc=example,dc=com")))
security:
    OPEN_PAREN "security" EQ
        (OPEN_PAREN "ssl_server_cert_dn" EQ STRING_LITERAL CLOSE_PAREN)
    CLOSE_PAREN
    ;

// dirty hack, should be fixed ASAP ----------------
no_one_should_call_me:
    IDENTIFIER
    {  __markRule(ERROR_TOKEN_A);}
    ;


identifier2:
    IDENTIFIER
    | "server"
    | "community"
    | "program"
    ;




//
// Lexer
//

class TNSLexerAdopted extends Lexer;

options {
    exportVocab = TNS;
    testLiterals = false;
    k = 2;

    caseSensitive = false;
    caseSensitiveLiterals = false;
    charVocabulary = '\3' .. '\377';
}

tokens {
    BAD_ML_COMMENT;
    BAD_CHARACTER;
    BAD_CHAR_LITERAL;
    SERVICE_NAME;
}


IDENTIFIER options { testLiterals=true; }
	:
	('a'..'z'|'_') ('a'..'z' | '_' | '0'..'9'
	| '.' ('a'..'z' | '_' | '0'..'9')
        {$setType(TNSTokenTypes.SERVICE_NAME);}
	| '-' ('a'..'z' | '_' | '0'..'9')
        {$setType(TNSTokenTypes.SERVICE_NAME);}
	)*
    ;


protected ANY_CHARACTER:
        'a' .. 'z'
        ;

QUOTED_STRING
      : ( '\''
           (
//              ('\n'|'\r'('\n')?) {newline();}
//              | ~'\''
              ~'\''
           )* '\'' )+
    ;



SEMI : ';';
COLON: ':' ;
DOT : '.'  ;
COMMA : ',';
SHARP : '#';
ASTERISK : '*';
AT_SIGN : '@' ;
OPEN_PAREN : '(' ;
CLOSE_PAREN : ')' ;
OPEN_BRACK : '[' ;
CLOSE_BRACK : ']' ;
PLUS : '+' ;
MINUS : '-' ;
DIVIDE : '/' ;
EQ : '=' ;
PERCENTAGE : '%' ;
DOUBLEDOT: ".." ;
CONCAT: "||" ;
OUTER_JOIN: "(+)" ;
START_LABEL : "<<";
END_LABEL: ">>";
ASSIGNMENT_EQ: ":=" ;
PASS_BY_NAME: "=>" ;
VERTBAR : '|' ;


protected
N : '0' .. '9' ( '0' .. '9' )* ;

// string literals
STRING_LITERAL
	:	'"' (ESC|~('"'|'\\'))* '"'
	;


// escape sequence -- note that this is protected; it can only be called
//   from another lexer rule -- it will not ever directly return a token to
//   the parser
// There are various ambiguities hushed in this rule.  The optional
// '0'...'9' digit matches should be matched here rather than letting
// them go back to STRING_LITERAL to be matched.  ANTLR does the
// right thing by matching immediately; hence, it's ok to shut off
// the FOLLOW ambig warnings.
protected
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
		|	'0'..'3'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'7'
				)?
			)?
		|	'4'..'7'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
			)?
		)
	;

// hexadecimal digit (again, note it's protected!)
protected
HEX_DIGIT
	:	('0'..'9'|'a'..'f')
	;


WS :   (
        ' ' | '\t'
       )+
        ;

LF :   (
            '\n' {newline();}
            | ('\r'('\n')?) {newline();}
       )+
        ;


// Single-line comments
SL_COMMENT
        :       '#'
                (~('\n'|'\r'))* ('\n'|'\r'('\n')?)
                {newline();}
        ;


// Taken right from antlr-2.7.1/examples/java/java/java.g ...
// multiple-line comments
ML_COMMENT
{
    int lcnt = 0;
}
        :          "/*"
                (
/*
 *  '\r' '\n' can be matched in one alternative or by matching
 *  '\r' in one iteration and '\n' in another.  I am trying to
 *  handle any flavor of newline that comes in, but the language
 *  that allows both "\r\n" and "\r" and "\n" to all be valid
 *  newline is ambiguous.  Consequently, the resulting grammar
 *  must be ambiguous.  I'm shutting this warning off.
 */
                        options {
                                generateAmbigWarnings=false;
                        }
                :
                        { LA(2)!='/' }? '*'
                |        '\r' '\n'                {lcnt++; newline();}
                |        '\r'                        {lcnt++; newline();}
                |        '\n'                        {lcnt++; newline();}
                |        ~('*'|'\n'|'\r')
                )*
                "*/"
;

NUMBER:
    N
    ;
