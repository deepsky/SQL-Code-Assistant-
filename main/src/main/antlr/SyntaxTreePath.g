header {
package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import antlr.CommonToken;
}


//
// Lexer
//

class SyntaxTreePathLexer extends Lexer;

options {
    exportVocab = SyntaxTreePathTypes;
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



SEMI : ';';
COLON: ':' ;
DOT : '.'  ;
COMMA : ',';
SHARP : '#';
DOLLAR : '$';
AT_SIGN : '@' ;
OPEN_PAREN : '(' ;
CLOSE_PAREN : ')' ;
OPEN_BRACK : '[' ;
CLOSE_BRACK : ']' ;
SLASH : '/' ;
DOUBLE_SLASH : "//" ;
EQ : '=' ;
DOUBLEDOT: ".." ;
VERTBAR : '|' ;



NUMBER : '0' .. '9' ( '0' .. '9' )* ;

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
        :        (' '| '\t' )+
        { $setType(Token.SKIP); }
        ;


