/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.struct.parser;


import com.deepsky.generated.plsql.PLSqlParser;
import antlr.*;
import antlr.collections.impl.BitSet;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

public class PLSqlParserEx extends PLSqlParser {

    Logger log = Logger.getLogger(this.getClass());

    List<String> errors = new ArrayList<String>();
    protected PLSqlParserEx(TokenBuffer tokenBuf, int k) {
        super(tokenBuf, k);
    }

    public PLSqlParserEx(TokenBuffer tokenBuf) {
        super(tokenBuf);
    }

    protected PLSqlParserEx(TokenStream lexer, int k) {
        super(lexer, k);
    }

    public PLSqlParserEx(TokenStream lexer) {
        super(lexer);
    }

    public PLSqlParserEx(ParserSharedInputState state) {
        super(state);
    }


    public List<String> getErrors(){
        return errors;
    }

    /** Parser error-reporting function can be overridden in subclass */
    public void reportError(RecognitionException ex) {
        if(outputErrors){
            log.error("Syntax error: " + ex.toString());
            errors.add("Syntax error: " + ex);
        } else {
            errors.add("Syntax error: " + ex);
        }
    }

    /** Parser error-reporting function can be overridden in subclass */
    public void reportError(String s) {
        if(outputErrors){
            log.error("Syntax error: " + s);
            errors.add("Syntax error: " + s);
        } else {
            errors.add("Syntax error: " + s);
        }
    }

    /** Parser warning-reporting function can be overridden in subclass */
    public void reportWarning(String s) {
        super.reportWarning(s);
    }

	public void recover(RecognitionException ex,
						BitSet tokenSet) throws TokenStreamException {
        super.recover(ex, tokenSet);
	}


    boolean outputErrors = false;
    public void outputErrors(boolean b) {
        outputErrors = b;
    }
}
