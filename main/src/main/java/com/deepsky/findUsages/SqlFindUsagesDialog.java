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

package com.deepsky.findUsages;

import com.deepsky.findUsages.options.SqlSearchOptions;
import com.intellij.find.FindSettings;
import com.intellij.find.findUsages.AbstractFindUsagesDialog;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.StateRestoringCheckBox;
import com.intellij.usageView.UsageViewUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SqlFindUsagesDialog extends AbstractFindUsagesDialog {

    private JPanel findWhatPanel;
    private String labelText;
    private String searchScopeName;

    public SqlFindUsagesDialog(
            @NotNull Project project,
            @NotNull FindUsagesOptions findUsagesOptions,
            boolean toShowInNewTab,
            boolean mustOpenInNewTab,
            boolean isSingleFile,
            SqlSearchOptions searchOptions) {
        super(project, findUsagesOptions, toShowInNewTab, mustOpenInNewTab, isSingleFile, false, false);
        this.labelText = searchOptions.getSearchTitle();
        findWhatPanel = createFindWhatPanel(searchOptions.getSearchOptions());
        searchScopeName = searchOptions.getTargetSchema().getAlias();
        init();
    }

    protected JPanel createFindWhatPanel() {
        return findWhatPanel;
    }


    public String getLabelText() {
//      return StringUtil.capitalize(UsageViewUtil.getType(myPsiElement)) + " " + UsageViewUtil.getDescriptiveName(myPsiElement);
        return labelText;
    }

    @Override
    protected void doHelpAction() {
        // todo --
        // HelpManager.getInstance().invokeHelp(FindUsagesManager.getHelpID(myPsiElement));
    }


    @Override
    public void configureLabelComponent(SimpleColoredComponent simpleColoredComponent) {
        simpleColoredComponent.append(labelText);
    }

    protected void doOKAction() {
        if (shouldDoOkAction()) {
            for (Pair p : optionsList) {
                p.op.setEnabled(p.sb.isSelected());
            }
        } else {
            return;
        }
        super.doOKAction();
    }


    protected boolean shouldDoOkAction() {
        if (optionsList.size() > 0) {
            for (Pair p : optionsList) {
                if (p.sb.isSelected()) {
                    return super.shouldDoOkAction();
                }
            }

            return false;
        } else {
            return true;
        }
    }

    private JPanel createFindWhatPanel(SqlSearchOptions.SearchOption[] options) {
        if (options.length == 0) {
            return null;
        }

        JPanel findPanel = new JPanel();
        findPanel.setBorder(IdeBorderFactory.createTitledBorder("Find"));
        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.Y_AXIS));
//        addUsagesOptions(findPanel);

        for (SqlSearchOptions.SearchOption op : options) {
            StateRestoringCheckBox sb = addCheckboxToPanel(op.getOptionName(), FindSettings.getInstance().isSkipResultsWithOneUsage(), findPanel, false);
            sb.setSelected(op.isEnabled());
            optionsList.add(new Pair(sb, op));
        }

        return findPanel;
    }

    List<Pair> optionsList = new ArrayList<Pair>();

    private class Pair {
        public Pair(StateRestoringCheckBox sb, SqlSearchOptions.SearchOption op) {
            this.sb = sb;
            this.op = op;
        }

        public StateRestoringCheckBox sb;
        public SqlSearchOptions.SearchOption op;
    }


    protected JPanel createAllOptionsPanel() {
        JPanel allOptionsPanel = new JPanel();

        JPanel findWhatPanel = createFindWhatPanel();
        JPanel usagesOptionsPanel = createUsagesOptionsPanel();
        int grids = 0;
        if (findWhatPanel != null) {
            grids++;
}
        if (usagesOptionsPanel != null) {
            grids++;
        }
        if (grids != 0) {
            allOptionsPanel.setLayout(new GridLayout(1, grids, 8, 0));
            if (findWhatPanel != null) {
                allOptionsPanel.add(findWhatPanel);
            }
            if (usagesOptionsPanel != null) {
                allOptionsPanel.add(usagesOptionsPanel);
            }
        }

        JPanel scopePanel = createSearchScopePanel();
        if (scopePanel != null) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(allOptionsPanel, BorderLayout.CENTER);
            panel.add(scopePanel, BorderLayout.SOUTH);
            return panel;
        }

        return allOptionsPanel;
    }


    private JPanel createSearchScopePanel(){
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.setBorder(BorderFactory.createTitledBorder("Search Scope"));
        JLabel searchScope = new JLabel();
        searchScope.setOpaque(true);
        searchScope.setText(searchScopeName);
        searchScope.setBorder(new EmptyBorder(2, 2, 2, 2));
        searchScope.setBackground(new Color(180, 180, 180));
        panel3.add(searchScope, BorderLayout.CENTER);
        return panel3;
    }

}

