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


public class TokenBufferEx extends TokenBuffer {

    final int _88188 = 88188;

    public TokenBufferEx(TokenStream tokenStream) {
        super(tokenStream);
    }

    public int LA(int i) throws TokenStreamException {
//        fill2(i);
//        return queue.elementAt(markerOffset + i - 1).getType(); //type;
        int t1;
        while(true) {
            fill2(i);
            t1 = queue.elementAt(markerOffset + i - 1).getType();
            if(t1 != _88188){
                break;
            } else {
                numToConsume++;
            }
        }// while (t1 == _88188);
        return t1; //queue.elementAt(markerOffset + i - 1);

    }

    /**
     * Get a lookahead token
     */
    public Token LT(int i) throws TokenStreamException {
        Token t1 = null;
        while(true) {
            fill2(i);
            t1 = queue.elementAt(markerOffset + i - 1);
            if(t1.getType() != _88188){
                break;
            } else {
                numToConsume++;
            }
        }// while (t1.getType() == _88188);
        return t1; //queue.elementAt(markerOffset + i - 1);
    }

    public void consume() {
        numToConsume++;
    }

    protected void syncConsume() {
        while (numToConsume > 0) {
            if (nMarkers > 0) {
                // guess mode -- leave leading tokens and bump offset.
                markerOffset++;
            } else {
                // normal mode -- remove first token
                queue.removeFirst();
            }
            numToConsume--;
        }
    }

    private void fill2(int amount) throws TokenStreamException {
        syncConsume();
        // Fill the buffer sufficiently to hold needed tokens
        while (queue.nbrEntries < amount + markerOffset) {
            // Append the next token
            queue.append(input.nextToken());

//            Token t0 = input.nextToken();
//            do {
//                queue.append(t0);
//            } while (t0.getType() == _88188);
        }
    }

}
