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

package com.deepsky.lang.plsql.objTree.ui;

import com.deepsky.lang.plsql.objTree.DbTreeElement;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class DbObjectTreeCellRenderer extends DefaultTreeCellRenderer {

    boolean isValid = true;

    public DbObjectTreeCellRenderer() {
        setOpaque(false);
    }

    public void setIsValid(boolean status) {
        this.isValid = status;
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, row, hasFocus);
        if (value instanceof DbTreeElement) {
            DbTreeElement el = (DbTreeElement) value;
            el.render(this);
        }
        return this;
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (!isValid) {
            int xOffset = 0;
            final Icon icon = getIcon();
            if (icon != null) {
                Insets myIpad = new Insets(0, 0, 0, 0);
                int iconTextGap = getIconTextGap();
                xOffset += myIpad.left + icon.getIconWidth() + iconTextGap;

            }
            g.setColor(Color.RED);
            Font font = getFont();
            String fragment = getText();
            final FontMetrics metrics = getFontMetrics(font);
            final int fragmentWidth = metrics.stringWidth(fragment);
            final int textBaseline = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            final int wavedAt = textBaseline + 1;
            for (int x = xOffset; x <= xOffset + fragmentWidth; x += 4) {
                UIUtil.drawLine(g, x, wavedAt, x + 2, wavedAt + 2);
                UIUtil.drawLine(g, x + 3, wavedAt + 1, x + 4, wavedAt);
            }
        }

        // reset not  valid attribute
        isValid = true;
    }
}