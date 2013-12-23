package com.deepsky.lang.integration;

import com.intellij.openapi.vfs.VirtualFile;

public interface MContentHandler {

    void sourceRootAdded(MContent e, VirtualFile sourceRoot);
    void sourceRootRemoved(MContent e, VirtualFile sourceRoot);
    void excludeRootAdded(MContent e, VirtualFile excludeRoot);
    void excludeRootRemoved(MContent e, VirtualFile excludeRoot);
}
