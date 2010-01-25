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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.findUsages.search.GenericSearchOptions;
import com.intellij.find.FindSettings;
import com.intellij.find.findUsages.AbstractFindUsagesDialog;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.openapi.project.Project;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.StateRestoringCheckBox;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SqlFindUsagesDialog extends AbstractFindUsagesDialog {

    private JPanel findWhatPanel;
    private String labelText;

    public SqlFindUsagesDialog(
            @NotNull Project project,
            @NotNull FindUsagesOptions findUsagesOptions,
            boolean toShowInNewTab,
            boolean mustOpenInNewTab,
            boolean isSingleFile,
            GenericSearchOptions searchOptions) {
        super(project, findUsagesOptions, toShowInNewTab, mustOpenInNewTab, isSingleFile, false, false);
        this.labelText = searchOptions.getSearchTitle();
        findWhatPanel = createFindWhatPanel(searchOptions.getSearchOptions());
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

    private JPanel createFindWhatPanel(GenericSearchOptions.SearchOption[] options) {
        if (options.length == 0) {
            return null;
        }

        JPanel findPanel = new JPanel();
        findPanel.setBorder(IdeBorderFactory.createTitledBorder("Find"));
        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.Y_AXIS));
//        addUsagesOptions(findPanel);

        for (GenericSearchOptions.SearchOption op : options) {
            StateRestoringCheckBox sb = addCheckboxToPanel(op.getOptionName(), FindSettings.getInstance().isSkipResultsWithOneUsage(), findPanel, false);
            sb.setSelected(op.isEnabled());
            optionsList.add(new Pair(sb, op));
        }

        return findPanel;
    }

    List<Pair> optionsList = new ArrayList<Pair>();

    private class Pair {
        public Pair(StateRestoringCheckBox sb, GenericSearchOptions.SearchOption op) {
            this.sb = sb;
            this.op = op;
        }

        public StateRestoringCheckBox sb;
        public GenericSearchOptions.SearchOption op;
    }
}
