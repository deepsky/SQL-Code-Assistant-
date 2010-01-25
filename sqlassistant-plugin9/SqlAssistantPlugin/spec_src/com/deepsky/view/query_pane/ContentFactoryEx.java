package com.deepsky.view.query_pane;

import com.intellij.ui.content.ContentFactory;

public class ContentFactoryEx {
    public static ContentFactory getContentFactory() {
        return ContentFactory.SERVICE.getInstance();
    }
}
