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

package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/RIGHTS.html
 *
 * $Id: //depot/code/org.antlr/release/antlr-2.7.2/antlr/TokenBuffer.java#1 $
 */

/**A Stream of Token objects fed to the parser from a Tokenizer that can
 * be rewound via mark()/rewind() methods.
 * <p>
 * A dynamic array is used to buffer up all the input tokens.  Normally,
 * "k" tokens are stored in the buffer.  More tokens may be stored during
 * guess mode (testing syntactic predicate), or when LT(i>k) is referenced.
 * Consumption of tokens is deferred.  In other words, reading the next
 * token is not done by consume(), but deferred until needed by LA or LT.
 * <p>
 *
 * @see Token
 * @see antlr.Tokenizer
 * @see TokenQueue
 */

import antlr.TokenStream;
//import antlr.TokenQueue;
import antlr.TokenStreamException;
import antlr.Token;
//import antlr.fix.*;
//import antlr.fix.TokenQueue;

import java.io.IOException;

public class TokenBuffer {

    // Token source
    protected TokenStream input;

    // Number of active markers
    protected int nMarkers = 0;

    // Additional offset used when markers are active
    protected int markerOffset = 0;

    // Number of calls to consume() since last LA() or LT() call
    protected int numToConsume = 0;

    // Circular queue
    protected TokenQueue queue;

    /** Create a token buffer */
    public TokenBuffer(TokenStream input_) {
        input = input_;
        queue = new TokenQueue(1);
    }

    /** Reset the input buffer to empty state */
    public void reset() {
        nMarkers = 0;
        markerOffset = 0;
        numToConsume = 0;
        queue.reset();
    }

    /** Mark another token for deferred consumption */
    public void consume() {
        numToConsume++;
    }

    /** Ensure that the token buffer is sufficiently full */
    private void fill(int amount) throws TokenStreamException {
        syncConsume();
        // Fill the buffer sufficiently to hold needed tokens
        while (queue.nbrEntries < amount + markerOffset) {
            // Append the next token
            queue.append(input.nextToken());
        }
    }

    /** return the Tokenizer (needed by ParseView) */
    public TokenStream getInput() {
        return input;
    }

    /** Get a lookahead token value */
    public int LA(int i) throws TokenStreamException {
        fill(i);
        return queue.elementAt(markerOffset + i - 1).getType(); //type;
    }

    /** Get a lookahead token */
    public Token LT(int i) throws TokenStreamException {
        fill(i);
        return queue.elementAt(markerOffset + i - 1);
    }

    /**Return an integer marker that can be used to rewind the buffer to
     * its current state.
     */
    public int mark() {
        syncConsume();
//System.out.println("Marking at " + markerOffset);
//try { for (int i = 1; i <= 2; i++) { System.out.println("LA("+i+")=="+LT(i).getText()); } } catch (TokenStreamException e) {}
        nMarkers++;
        return markerOffset;
    }

    /**Rewind the token buffer to a marker.
     * @param mark Marker returned previously from mark()
     */
    public void rewind(int mark) {
        syncConsume();
        markerOffset = mark;
        nMarkers--;
//System.out.println("Rewinding to " + mark);
//try { for (int i = 1; i <= 2; i++) { System.out.println("LA("+i+")=="+LT(i).getText()); } } catch (TokenStreamException e) {}
    }

    /** Sync up deferred consumption */
    protected void syncConsume() {
        while (numToConsume > 0) {
            if (nMarkers > 0) {
                // guess mode -- leave leading tokens and bump offset.
                markerOffset++;
            }
            else {
                // normal mode -- remove first token
                queue.removeFirst();
            }
            numToConsume--;
        }
    }
}
