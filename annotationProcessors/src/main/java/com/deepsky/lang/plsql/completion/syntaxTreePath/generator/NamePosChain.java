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

package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;

import java.util.*;

public class NamePosChain {

    private List<NamePosPair> pairs = new ArrayList<NamePosPair>();
    private Map<Integer,Boolean> boundaries = new HashMap<Integer, Boolean>();

    public void add(NamePosPair pair){
        pairs.add(pair);
    }

    public void goDown(){
        boundaries.put(pairs.size(), true);
    }

    public void goUp(){
        boundaries.put(pairs.size(), false);
    }

    public String printOut(boolean withPrefix){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<pairs.size();i++){
            final NamePosPair pair = pairs.get(i);
            final Boolean down = boundaries.get(i);

            if(down != null &&  down) sb.append("(/");
            if(pair.isExcl){
                sb.append("!");
            }
            if(withPrefix){
                if(pair.pos == -2){
                    // do nothing
                } else if(pair.pos == -1){
                    if(!pair.isDollar){
                        sb.append("#");
                    }
                } else {
                    if(!pair.isDollar){
                        sb.append(pair.pos).append("#");
                    } else {
                        sb.append(pair.pos).append("$");
                    }
                }
                sb.append(pair.name);

            } else {
                sb.append(pair.name);
            }
            final Boolean up = boundaries.get(i+1);
            if(up != null &&  !up) sb.append(")");
            sb.append(" ");
        }
//        for(NamePosPair pair: pairs){
//            if(pair.isExcl){
//                sb.append("!");
//            }
//            if(withPrefix){
//                if(pair.pos == -2){
//                    // do nothing
//                } else if(pair.pos == -1){
//                    if(!pair.isDollar){
//                        sb.append("#");
//                    }
//                } else {
//                    if(!pair.isDollar){
//                        sb.append(pair.pos).append("#");
//                    } else {
//                        sb.append(pair.pos).append("$");
//                    }
//                }
//                sb.append(pair.name).append(" ");
//
//            } else {
//                sb.append(pair.name).append(" ");
//            }
//        }
        return sb.toString().trim();
    }


    public int[] getParameterIndexList() {
            int[] out = new int[pairs.size()];
            int idx = 0;
            int nodeIdx = 0;
            for(NamePosPair p: pairs){
                if(p.pos > 0){
                    out[idx] = nodeIdx; // TODO - p.pos should be validated to be: sequentially incremented and started with 1
                    idx++;
                }
                nodeIdx++;
            }
            return Arrays.copyOf(out, idx);
    }

}
