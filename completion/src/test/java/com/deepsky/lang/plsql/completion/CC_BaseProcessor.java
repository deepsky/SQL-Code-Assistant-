package com.deepsky.lang.plsql.completion;


import com.intellij.codeInsight.lookup.LookupElement;

import java.util.ArrayList;
import java.util.List;

public abstract class CC_BaseProcessor {

    final List<LookupElement> out = new ArrayList<LookupElement>();
    VariantsProvider provider;

    private String getLookup() {
        // TODO - implement me
        return "";
    }

}
