package com.deepsky.lang.plsql.completion.legacy.logic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class STPPattern {

    private List<PatternElement> pattern = new ArrayList<PatternElement>();

    public STPPattern(@NotNull List<PatternElement> pattern){
        this.pattern = pattern;
    }

    public List<PatternElement> getPattern() {
        return pattern;
    }

    public Iterator<PatternElement> iterator() {
        return pattern.iterator();
    }


    public List<PatternElement> patterns(){
        return pattern;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        for(PatternElement e: pattern){
            b.append(e.toString());
        }
        return b.toString();
    }
}
