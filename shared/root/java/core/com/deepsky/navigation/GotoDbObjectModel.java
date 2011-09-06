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

package com.deepsky.navigation;


import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.sqlIndex.DbDumpedSqlFile;
import com.deepsky.lang.plsql.sqlIndex.FSSqlFile;
import com.deepsky.lang.plsql.sqlIndex.SysSqlFile;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.ide.util.PsiElementModuleRenderer;
import com.intellij.ide.util.gotoByName.ChooseByNameModel;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GotoDbObjectModel implements ChooseByNameModel {
    Project myProject;

    DbObjectContributor dbObjContrib;

    public GotoDbObjectModel(Project project) {
        this.myProject = project;
        dbObjContrib = PluginKeys.DB_OBJECT_CONTR.getData(project);
    }

    public String getPromptText() {
        return "Enter database object name";
    }

    public String getCheckBoxName() {
        return null; //IdeBundle.message("checkbox.include.non.project.files");
    }

    public char getCheckBoxMnemonic() {
        return SystemInfo.isMac ? 'P' : 'n';
    }

    public String getNotInMessage() {
        return IdeBundle.message("label.no.non.java.files.found");
    }

    public String getNotFoundMessage() {
        return "No objects found";
    }

    public boolean loadInitialCheckBoxState() {
        return false;
    }

    public void saveInitialCheckBoxState(boolean state) {
    }

    public PsiElementListCellRenderer getListCellRenderer() {
        return new DefaultPsiElementCellRendererEx();
    }

    class DefaultPsiElementCellRendererEx extends DefaultPsiElementCellRenderer {
        protected DefaultListCellRenderer getRightCellRenderer() {
            if (UISettings.getInstance().SHOW_ICONS_IN_QUICK_NAVIGATION) {
                return new PsiElementModuleRenderer() {
                    String text1;

                    public String getText() {
                        return text1;
                    }

                    protected void customizeCellRenderer(Object value, int index, boolean selected, boolean hasFocus) {
                        text1 = "";
                        if (value instanceof NavigationItemEx) {
                            text1 = "[" + ((NavigationItemEx) value).getSchemaAlias() + "]";
                        } else if (value instanceof PsiElement) {
                            PsiElement element = (PsiElement) value;
                            if (element.isValid()) {
                                PsiFile psiFile = element.getContainingFile();
                                VirtualFile file = psiFile.getVirtualFile();
//                                if (psiFile.getVirtualFile() instanceof DbOriginatedSqlFile) {
//                                    DbOriginatedSqlFile dbOrig = (DbOriginatedSqlFile) psiFile.getVirtualFile();
//                                    text1 = dbOrig.getDatabaseUrl();
//                                } else
                                if (psiFile.getVirtualFile() instanceof FSSqlFile) {
                                    text1 = "[Project]";
                                } else if (psiFile.getVirtualFile() instanceof DbDumpedSqlFile) {
                                    text1 = "[" + ((DbDumpedSqlFile) file).getDbUrl().getUserHostPortServiceName() + "]";
                                } else if (psiFile.getVirtualFile() instanceof SysSqlFile) {
                                    text1 = "[" + ((SysSqlFile) file).getDbUrl().getUserHostPortServiceName() + "]";
                                } else {
                                    text1 = "[Project222]";
                                }
                            }
                        }
                        setText(text1);
                        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
                        setHorizontalTextPosition(SwingConstants.LEFT);
                        setBackground(selected ? UIUtil.getListSelectionBackground() : UIUtil.getListBackground());
                        setForeground(selected ? UIUtil.getListSelectionForeground() : UIUtil.getInactiveTextColor());
                    }

                };
            }
            return null;
        }
    }

    public String[] getNames(boolean checkBoxState) {
        return dbObjContrib.getNames(myProject, checkBoxState);
    }

    public Object[] getElementsByName(String name, boolean checkBoxState, String pattern) {
        return dbObjContrib.getItemsByName(name, pattern, myProject); //, checkBoxState);
    }

    public String getElementName(Object element) {
        return ((NavigationItem) element).getName();
    }

    @Nullable
    public String getFullName(final Object element) {
        if (element instanceof PsiFile) {
            final VirtualFile virtualFile = ((PsiFile) element).getVirtualFile();
            return virtualFile != null ? virtualFile.getPath() : null;
        }

        return getElementName(element);
    }

    @NotNull
    public String[] getSeparators() {
        return new String[]{"/", "\\"};
    }

    public String getHelpId() {
        return "sqlassistant.navigation";
    }

    public boolean willOpenEditor() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
