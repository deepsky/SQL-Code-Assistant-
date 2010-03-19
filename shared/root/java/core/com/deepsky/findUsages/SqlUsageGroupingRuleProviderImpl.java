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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.usageView.UsageViewBundle;
import com.intellij.usages.UsageView;
import com.intellij.usages.UsageViewSettings;
import com.intellij.usages.impl.RuleAction;
import com.intellij.usages.impl.UsageViewImpl;
import com.intellij.usages.rules.UsageGroupingRule;
import com.intellij.usages.rules.UsageGroupingRuleProvider;
import com.intellij.util.Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SqlUsageGroupingRuleProviderImpl  implements UsageGroupingRuleProvider {
  @NotNull
  public UsageGroupingRule[] getActiveRules(Project project) {
    List<UsageGroupingRule> rules = new ArrayList<UsageGroupingRule>();
//    rules.add(new NonCodeUsageGroupingRule());
//    if (UsageViewSettings.getInstance().GROUP_BY_USAGE_TYPE) {
//      rules.add(new UsageTypeGroupingRule());
//    }

//    if (UsageViewSettings.getInstance().GROUP_BY_MODULE) {
//      rules.add(new ModuleGroupingRule());
//    }
//    if (UsageViewSettings.getInstance().GROUP_BY_PACKAGE) {
//      rules.add(DirectoryGroupingRule.getInstance(project));
//    }
//    if (UsageViewSettings.getInstance().GROUP_BY_FILE_STRUCTURE) {
//      FileStructureGroupRuleProvider[] providers = Extensions.getExtensions(FileStructureGroupRuleProvider.EP_NAME);
//      for (FileStructureGroupRuleProvider ruleProvider : providers) {
//        final UsageGroupingRule rule = ruleProvider.getUsageGroupingRule(project);
//        if(rule != null) {
//          rules.add(rule);
//        }
//      }
//    }
//    else {
//      rules.add(new FileGroupingRule(project));
//    }

    return rules.toArray(new UsageGroupingRule[rules.size()]);
  }

  @NotNull
  public AnAction[] createGroupingActions(UsageView view) {
    final UsageViewImpl impl = (UsageViewImpl)view;
    final JComponent component = impl.getComponent();

//    final GroupByModuleTypeAction groupByModuleTypeAction = new GroupByModuleTypeAction(impl);
//    groupByModuleTypeAction.registerCustomShortcutSet(new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK)), component);
//
//    final GroupByFileStructureAction groupByFileStructureAction = new GroupByFileStructureAction(impl);
//    groupByFileStructureAction.registerCustomShortcutSet(new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_M,
//                                                                                                      InputEvent.CTRL_DOWN_MASK)), component);
//
//    impl.scheduleDisposeOnClose(new Disposable() {
//      public void dispose() {
//        groupByModuleTypeAction.unregisterCustomShortcutSet(component);
//        groupByFileStructureAction.unregisterCustomShortcutSet(component);
//      }
//    });

//    if(view.getPresentation().isCodeUsages()) {
      final GroupByUsageTypeAction groupByUsageTypeAction = new GroupByUsageTypeAction(impl);
      groupByUsageTypeAction.registerCustomShortcutSet(new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK)), component);

//      final GroupByPackageAction groupByPackageAction = new GroupByPackageAction(impl);
//      groupByPackageAction.registerCustomShortcutSet(new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK)), component);

      impl.scheduleDisposeOnClose(new Disposable() {
        public void dispose() {
          groupByUsageTypeAction.unregisterCustomShortcutSet(component);
//          groupByPackageAction.unregisterCustomShortcutSet(component);
        }
      });

      return new AnAction[] {
        groupByUsageTypeAction
//        groupByModuleTypeAction,
//        groupByPackageAction,
//        groupByFileStructureAction
      };
//    }
//    else {
//      return new AnAction[] {
//        groupByModuleTypeAction,
//        groupByFileStructureAction
//      };
//    }
  }

  private static class GroupByUsageTypeAction extends RuleAction {
    private GroupByUsageTypeAction(UsageViewImpl view) {
      super(view, UsageViewBundle.message("action.group.by.usage.type"), IconLoader.getIcon("/ant/filter.png")); //TODO: special icon
    }
    protected boolean getOptionValue() {
      return UsageViewSettings.getInstance().GROUP_BY_USAGE_TYPE;
    }
    protected void setOptionValue(boolean value) {
      UsageViewSettings.getInstance().GROUP_BY_USAGE_TYPE = value;
    }
  }

  private static class GroupByModuleTypeAction extends RuleAction {
    private GroupByModuleTypeAction(UsageViewImpl view) {
      super(view, UsageViewBundle.message("action.group.by.module"), IconLoader.getIcon("/objectBrowser/showModules.png"));
    }

    protected boolean getOptionValue() {
      return UsageViewSettings.getInstance().GROUP_BY_MODULE;
    }

    protected void setOptionValue(boolean value) {
      UsageViewSettings.getInstance().GROUP_BY_MODULE = value;
    }
  }

  private static class GroupByPackageAction extends RuleAction {
    private GroupByPackageAction(UsageViewImpl view) {
      super(view, UsageViewBundle.message("action.group.by.package"), Icons.GROUP_BY_PACKAGES);
    }
    protected boolean getOptionValue() {
      return UsageViewSettings.getInstance().GROUP_BY_PACKAGE;
    }
    protected void setOptionValue(boolean value) {
      UsageViewSettings.getInstance().GROUP_BY_PACKAGE = value;
    }
  }

  private static class GroupByFileStructureAction extends RuleAction {
    private GroupByFileStructureAction(UsageViewImpl view) {
      super(view, UsageViewBundle.message("action.group.by.file.structure"), IconLoader.getIcon("/actions/groupByMethod.png"));
    }
    protected boolean getOptionValue() {
      return UsageViewSettings.getInstance().GROUP_BY_FILE_STRUCTURE;
    }
    protected void setOptionValue(boolean value) {
      UsageViewSettings.getInstance().GROUP_BY_FILE_STRUCTURE = value;
    }
  }

}
