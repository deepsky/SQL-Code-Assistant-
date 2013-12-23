package com.deepsky.lang.integration.utils;

import java.util.HashSet;
import java.util.Set;

public class StringSet extends HashSet<String> {


    public StringSet(Set<String> strings) {
        super(strings);
    }

    public StringSet() {
    }

    /**
     * Return StringSet which does NOT contain strings matching the regExp
     *
     * @param regExp expression to match against
     * @return
     */
    public StringSet filterOut(String regExp){
        StringSet out = new StringSet();
        for(String s: this){
            if(!s.matches(regExp)){
                out.add(s);
            }
        }
        return out;
    }


    /**
     * Return StringSet which does contain strings matching the regExp
     * @param regExp
     * @return
     */
    public StringSet keepMatching(String ... regExp){
        StringSet out = new StringSet();
        for(String s: this){
            for(String rExp: regExp){
                if(s.matches(rExp)){
                    out.add(s);
                }
            }
        }
        return out;
    }

}
