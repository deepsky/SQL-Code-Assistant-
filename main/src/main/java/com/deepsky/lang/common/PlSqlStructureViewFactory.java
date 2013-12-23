package com.deepsky.lang.common;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.structure_view.PlSqlStructureViewModel;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class PlSqlStructureViewFactory implements PsiStructureViewFactory {

    public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            public StructureViewModel createStructureViewModel() {
                return new PlSqlStructureViewModel((PlSqlElement) psiFile);
            }

            public boolean isRootNodeShown() {
                return false;
            }
        };
    }
}
