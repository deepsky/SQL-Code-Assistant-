/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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


header {
package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import antlr.CommonToken;
}


class SyntaxTreePathParser extends Parser;

options {
    exportVocab = SyntaxTreePath;
    k = 2;
    buildAST = true;
}

tokens {
    START_RULE;
    STARTER_ONE; STARTER_TWO;
    SYMBOL; ANY_SYMBOL;
    LEFT_OPEN;
}

start_rule:
    (starter_one|starter_two)+
    { #start_rule = #([START_RULE, "START_RULE" ], #start_rule);}
    ;

starter_one:
    SLASH (inner)+
    { #starter_one = #([STARTER_ONE, "STARTER_ONE" ], #starter_one);}
    ;

starter_two:
    DOUBLE_SLASH (inner)+
    { #starter_two = #([STARTER_TWO, "STARTER_TWO" ], #starter_two);}
    ;

inner:
    symbol
    | (DOUBLEDOT symbol)
    { #inner = #([LEFT_OPEN, "LEFT_OPEN" ], #inner);}
    ;

symbol:
    ((NUMBER)? "ANY") => ((NUMBER)? "ANY")
    { #symbol = #([ANY_SYMBOL, "ANY_SYMBOL" ], #symbol);}
    | ((EXCL)? ((NUMBER)? (SHARP|DOLLAR))? IDENTIFIER)
    { #symbol = #([SYMBOL, "SYMBOL" ], #symbol);}
    ;


//
// Lexer
//

class SyntaxTreePathLexer extends Lexer;

options {
    exportVocab = SyntaxTreePath;
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
EXCL : '!' ;



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


