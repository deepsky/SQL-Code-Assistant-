header {
package com.deepsky.generated.plsql;

import antlr.CommonToken;
}

//
// Lexer
//

class PLSqlLexer extends Lexer;

options {
    exportVocab = PLSql2;
    testLiterals = false;
//    k = 2;
    k = 3;
    caseSensitive = false;

    caseSensitiveLiterals = false;
//    charVocabulary = '\3' .. '\377';

    // Allow any char but \uFFFF (16 bit -1)
    charVocabulary='\u0000'..'\uFFFE';
}

tokens {
    BAD_ML_COMMENT;
    BAD_CHARACTER;
    BAD_CHAR_LITERAL;
    BAD_NUMBER_LITERAL;
    STORAGE_SIZE;
    LT; LE; GE; NOT_EQ;
    CUSTOM_TOKEN;
    COLON_NEW; COLON_OLD;
    TABLE_NAME_SPEC;
}

/*
IDENTIFIER options { testLiterals=true; }
    :
        ('a' .. 'z' | '_') ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#')*
    ;
TABLE_NAME_SPEC options { testLiterals=true; }
    :
        ('a' .. 'z' | '_') ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#' | q:'@')*
        {
            if(#q == null){
                $setType(PLSql2TokenTypes.IDENTIFIER);
            }
        }
    ;

*/

PLSQL_ENV_VAR:
    '&' '&' ( 'a' .. 'z' | '0' .. '9' | '_')+ ('.')?
    //  ('a' .. 'z' | '_') ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#')* ('.')?
    ;

protected ANY_CHARACTER:
        'a' .. 'z'
        ;

/*
IDENTIFIER options { testLiterals=true; }
    :
       ('a' .. 'z' | '_') ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#'
         | '@' {$setType(PLSql2TokenTypes.TABLE_NAME_SPEC);}
        )* (PLSQL_ENV_VAR)? ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#'
         | '@' {$setType(PLSql2TokenTypes.TABLE_NAME_SPEC);}
        )*
    ;
*/
IDENTIFIER options { testLiterals=true; }
    :
      (('q' '\'') => ('q' QUOTED_STR) {$setType(PLSql2TokenTypes.QUOTED_STR);})
      | (
            ('a' .. 'z' | '_') ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#'
                | '@' {$setType(PLSql2TokenTypes.TABLE_NAME_SPEC);}
            )* (PLSQL_ENV_VAR)? ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#'
                | '@' {$setType(PLSql2TokenTypes.TABLE_NAME_SPEC);}
            )*
      )
    ;

QUOTED_STR:
		'\'' (~('\'' | '\uFFFF' ))*
	        ('\''
	            | (('\uFFFF') => {$setType(PLSql2TokenTypes.BAD_CHAR_LITERAL);})
	        )
    ;


DOUBLE_QUOTED_STRING :
      '"' ( ~('"' | '\uFFFF' ))*
      ( '"'
      | (('\uFFFF') => {$setType(PLSql2TokenTypes.BAD_CHAR_LITERAL);})
	  )
    ;

AT_PREFIXED:
    '@' ('@')? ( 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#' | '/' | '.')+
    ;

SEMI : ';' ;
COLON: ':' ;

DOT : '.'  ;
COMMA : ',' ;
ASTERISK : '*' ;
//AT_SIGN : '@' ;
OPEN_PAREN : '(' ;
CLOSE_PAREN : ')' ;
PLUS : '+' ;
MINUS : '-' ;
DIVIDE : '/' ;
BACKSLASH : '\\' ;
EQ : '=' ;
PERCENTAGE : '%' ;
DOUBLEDOT: ".." ;
CONCAT: "||" ;
// OUTER_JOIN: "(+)" ;
//AMP : '&' ;
START_LABEL : "<<" ;
DOLLAR: '$' ;

END_LABEL: ">>" ;

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

NUMBER:
    (
        ( N DOT N ) => N DOT N
        | DOT N
        | ( N ('k'|'m')) => N ('k'|'m') {$setType(PLSql2TokenTypes.STORAGE_SIZE);}
        | N
     )
    (
        ("e" ( PLUS | MINUS )? N) => "e" ( PLUS | MINUS )? N
        | "e" {$setType(PLSql2TokenTypes.BAD_NUMBER_LITERAL);}
    )?
    ;

protected
N : '0' .. '9' ( '0' .. '9' )* ;

/* -- original
WS :        (' '
                |        '\t'
                |        ('\n'|'\r'('\n')?) {newline();}
                )+
//        { $setType(Token.SKIP); }
        ;
*/

WS :        (' '|'\t')+
        ;

LF :   (
            '\n' {newline();} 
            | ('\r'('\n')?) {newline();}
       )+
        ;

// Single-line comments
SL_COMMENT
        :        "--"
                (~('\n'|'\r'|'\uFFFF'))*
                (   '\n' {newline();}
                    |'\r'('\n')? {newline();}
                    |{ int dummy = 0;}  ///'\uFFFF'
                )
        ;


// Taken right from antlr-2.7.1/examples/java/java/java.g ...
// multiple-line comments
ML_COMMENT :          "/*"
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
                |        '\r' '\n'                { newline();}
                |        '\r'                        { newline();}
                |        '\n'                        { newline();}
                |        ~('*'|'\n'|'\r'|'\uFFFF')
                )*
		( '\uFFFF'! {$setType(PLSql2TokenTypes.BAD_ML_COMMENT);} | "*/" )
//                "*/"
//        { $setType(Token.SKIP); }
;

IF_COND_CMPL:
    DOLLAR "if"
    ;
THEN_COND_CMPL:
    DOLLAR "then"
    ;


ELSE_COND_CMPL:
    DOLLAR "else"
    ;

END_COND_CMPL:
    DOLLAR "end"
    ;

ERROR_COND_CMPL:
    DOLLAR "error"
    ;
