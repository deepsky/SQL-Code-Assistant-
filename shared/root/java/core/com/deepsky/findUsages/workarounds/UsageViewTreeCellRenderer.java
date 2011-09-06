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

package com.deepsky.findUsages.workarounds;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.usageView.UsageTreeColors;
import com.intellij.usageView.UsageTreeColorsScheme;
import com.intellij.usageView.UsageViewBundle;
import com.intellij.usages.*;
import com.intellij.usages.impl.GroupNode;
import com.intellij.usages.impl.Node;
import com.intellij.usages.impl.UsageNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * @author max
 */
class UsageViewTreeCellRenderer extends ColoredTreeCellRenderer {
  private static final Logger LOG = Logger.getInstance("#com.intellij.usages.impl.UsageViewTreeCellRenderer");
  private static final EditorColorsScheme ourColorsScheme = UsageTreeColorsScheme.getInstance().getScheme();
  private static final SimpleTextAttributes ourInvalidAttributes = SimpleTextAttributes.fromTextAttributes(ourColorsScheme.getAttributes(UsageTreeColors.INVALID_PREFIX));
  private static final SimpleTextAttributes ourReadOnlyAttributes = SimpleTextAttributes.fromTextAttributes(ourColorsScheme.getAttributes(UsageTreeColors.READONLY_PREFIX));

  private final SimpleTextAttributes myNumberOfUsagesAttribute;
  private final UsageViewPresentation myPresentation;
  private final UsageView myView;

  UsageViewTreeCellRenderer(UsageView view) {
    myView = view;
    myPresentation = view.getPresentation();
    myNumberOfUsagesAttribute = SimpleTextAttributes.fromTextAttributes(ourColorsScheme.getAttributes(UsageTreeColors.NUMBER_OF_USAGES));
  }

  public void customizeCellRenderer(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    boolean showAsReadOnly = false;
    if (value instanceof Node && value != tree.getModel().getRoot()) {
      Node node = (Node)value;
      if (!node.isValid()) {
        append(UsageViewBundle.message("node.invalid") + " ", ourInvalidAttributes);
      }
      if (myPresentation.isShowReadOnlyStatusAsRed() && node.isReadOnly()) {
        showAsReadOnly = true;
      }
    }

    if (value instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
      Object userObject = treeNode.getUserObject();

      if (userObject instanceof UsageTarget) {
        UsageTarget usageTarget = (UsageTarget)userObject;
        final ItemPresentation presentation = usageTarget.getPresentation();
        LOG.assertTrue(presentation != null);
        if (showAsReadOnly) {
          append(UsageViewBundle.message("node.readonly") + " ", ourReadOnlyAttributes);
        }
        final String text = presentation.getPresentableText();
        append(text != null? text : "", SimpleTextAttributes.REGULAR_ATTRIBUTES);
        setIcon(presentation.getIcon(expanded));
      }
      else if (treeNode instanceof GroupNode) {
        GroupNode node = (GroupNode)treeNode;

        if (node.isRoot()) {
          append(StringUtil.capitalize(myPresentation.getUsagesWord()), patchAttrs(node, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES));
        }
        else {
          append(node.getGroup().getText(myView),
                 patchAttrs(node, showAsReadOnly ? ourReadOnlyAttributes : SimpleTextAttributes.REGULAR_ATTRIBUTES));
          setIcon(node.getGroup().getIcon(expanded));
        }

        int count = node.getRecursiveUsageCount();
        append(" (" + StringUtil.pluralize(count + " " + myPresentation.getUsagesWord(), count) + ")", patchAttrs(node, myNumberOfUsagesAttribute));
      }
      else if (treeNode instanceof UsageNode) {
        UsageNode node = (UsageNode)treeNode;
        setIcon(node.getUsage().getPresentation().getIcon());
        if (showAsReadOnly) {
          append(UsageViewBundle.message("node.readonly") + " ", patchAttrs(node, ourReadOnlyAttributes));
        }

        TextChunk[] text = node.getUsage().getPresentation().getText();
        for (TextChunk textChunk : text) {
          append(textChunk.getText(), patchAttrs(node, SimpleTextAttributes.fromTextAttributes(textChunk.getAttributes())));
        }
      }
      else if (userObject instanceof String) {
        append((String)userObject, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
      }
      else {
        append(userObject == null ? "" : userObject.toString(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
      }
    }
    else {
      append(value.toString(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }
  }

  private static SimpleTextAttributes patchAttrs(Node node, SimpleTextAttributes original) {
    if (node.isExcluded()) {
      original = new SimpleTextAttributes(original.getStyle() | SimpleTextAttributes.STYLE_STRIKEOUT, original.getFgColor(), original.getWaveColor());
    }
    if (node instanceof GroupNode) {
      UsageGroup group = ((GroupNode)node).getGroup();
      FileStatus fileStatus = group != null ? group.getFileStatus() : null;
      if (fileStatus != null && fileStatus != FileStatus.NOT_CHANGED) {
        original = new SimpleTextAttributes(original.getStyle(), fileStatus.getColor(), original.getWaveColor());
      }

      DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
      if (parent != null && parent.isRoot()) {
        original = new SimpleTextAttributes(original.getStyle() | SimpleTextAttributes.STYLE_BOLD, original.getFgColor(), original.getWaveColor());
      }
    }
    // Fix problems with black background or white text as foreground
    // Logic of checking hsb component was taken from CssColorGutterRenderer
    if (original.getBgColor() != null){
      Color fgColor = original.getFgColor();
      if (fgColor != null){
        final float hsb[] = new float[3];
        Color.RGBtoHSB(fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue(), hsb);
        if (hsb[2] > 0.7f){
          fgColor = null;
        }
      }
      return new SimpleTextAttributes(original.getStyle(), fgColor, original.getWaveColor());
    }
    return original;
  }

  public static String getTooltipText(final Object value) {
    if (value instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
      if (treeNode instanceof UsageNode) {
        UsageNode node = (UsageNode)treeNode;
        return node.getUsage().getPresentation().getTooltipText();
      }
    }
    return null;
  }
}
