header {
package com.deepsky.tools.generated;

import antlr.CommonToken;
}

class ASTStringParser extends Parser;

options {
    exportVocab = ASTStrTypes;
    k = 2;
    buildAST = true;
}

tokens {
    START_RULE;
    CREATE_PACKAGE;
    LE; GE; LT;
    DATA_TYPE;
    VARCHAR2;
    VARCHAR;
    CHARACTER;
    INTEGER;
    NUMBER;
    CHAR;
    TIMESTAMP;
    AST_DEF;
}

stringa:
    SHARP! identifier EQ! SHARP! OPEN_PAREN! OPEN_BRACK! identifier COMMA! (identifier | STRING_LITERAL) CLOSE_BRACK! COMMA! SHARP! identifier
    { #stringa = #([AST_DEF, "AST_DEF"], #stringa); }
    ;

identifier:
    IDENTIFIER
;


//
// Lexer
//

class ASTStringLexer extends Lexer;

options {
    exportVocab = ASTStrTypes;
    testLiterals = false;
    k = 3;

    caseSensitive = true;
    caseSensitiveLiterals = true;
    charVocabulary = '\3' .. '\377';
}

IDENTIFIER options { testLiterals=true; }
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
    ;

protected ANY_CHARACTER:
        'a' .. 'z'
        ;

QUOTED_STRING
      : ( '\''
           (
              ('\n'|'\r'('\n')?) {newline();}
              | ~'\''
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

NOT_EQ :
        (    '<' { _ttype = LT; }
                (       ( '>' { _ttype = NOT_EQ; } )
                    |   ( '=' { _ttype = LE; } ) )?
        | "!=" | "^="
        )
    ;

GT : '>' ( '=' { _ttype = GE; } )? ;

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
	:	('0'..'9'|'A'..'F'|'a'..'f')
	;


WS
{
int lcnt = 0;
}
        :        (' '
                |        '\t'
                |        ('\n'|'\r'('\n')?) {lcnt++; newline();}
                )+
        { $setType(Token.SKIP); }
        ;

// Single-line comments
SL_COMMENT
        :        "--"
                (~('\n'|'\r'))* ('\n'|'\r'('\n')?)
                {newline();}
        { $setType(Token.SKIP); }
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
        { $setType(Token.SKIP); }
;
