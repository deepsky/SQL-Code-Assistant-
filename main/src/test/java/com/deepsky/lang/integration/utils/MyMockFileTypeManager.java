package com.deepsky.lang.integration.utils;

import com.intellij.mock.MockFileTypeManager;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by sky on 6/12/13.
 */
public class MyMockFileTypeManager extends MockFileTypeManager {

    private Map<FileType, Set<FileNameMatcher>> type2mather = new HashMap<FileType, Set<FileNameMatcher>>();

    public MyMockFileTypeManager(FileType fileType) {
        super(fileType);
    }

    public void associate(@NotNull FileType type, @NotNull FileNameMatcher matcher) {
        Set<FileNameMatcher> m = type2mather.get(type);
        if(m == null){
            m = new HashSet<FileNameMatcher>();
            type2mather.put(type, m);
        }

        m.add(matcher);
    }

    @Override
    public void removeAssociation(@NotNull FileType type, @NotNull FileNameMatcher matcher) {
        Set<FileNameMatcher> m = type2mather.get(type);
        if(m!= null){
            m.remove(matcher);
        }
    }


    @NotNull
    public List<FileNameMatcher> getAssociations(@NotNull FileType type) {
        Set<FileNameMatcher> m = type2mather.get(type);
        if(m== null){
            return Collections.emptyList();
        }
        return new ArrayList<FileNameMatcher>(m);
    }

}
