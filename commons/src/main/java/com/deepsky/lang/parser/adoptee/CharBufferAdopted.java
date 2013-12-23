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

package com.deepsky.lang.parser.adoptee;

import antlr.InputBuffer;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import com.intellij.openapi.diagnostic.Logger;

import java.io.Reader;
import java.io.CharArrayReader;
import java.io.IOException;

public class CharBufferAdopted extends InputBuffer {

    static final Logger log = Logger.getInstance("#CharBufferAdapted");
    // char source
    private Reader input;
    private int start = -1;
    private int length = -1;
    private char[] chars = null;

    public CharBufferAdopted(char[] chars, int start, int length) { // SAS: for proper text i/o
        super();
        input = new CharArrayReaderExt(chars, start, length);

        this.start = start;
        this.length = length;
        this.chars = chars;
    }



    public CharBufferAdopted(char[] chars) { // SAS: for proper text i/o
        super();
        input = new CharArrayReaderExt(chars);
        this.chars = chars;
    }

    /** Ensure that the character buffer is sufficiently full */
    public final void fill(int amount) throws CharStreamException {
        try {
            syncConsume();
            // Fill the buffer sufficiently to hold needed characters
            while (queue.getNbrEntries() < amount + markerOffset) {
                // Append the next character
                final int t = input.read();
                if(t == 0){
                    log.info("##### t == 0");
                    queue.append((char) -1);
                } else {
                    queue.append((char) t);
                }
            }
        }
        catch (IOException io) {
            throw new CharStreamIOException(io);
        }
    }

    private class CharArrayReaderExt extends CharArrayReader {

        public CharArrayReaderExt(char[] chars, int start, int length){
            super(chars, start, length);
        }

        public CharArrayReaderExt(char[] chars){
            super(chars);
        }

        public int read() throws IOException {
            if (pos >= count)
                return -1;
            else
                return buf[pos++];
        }
    }
}

