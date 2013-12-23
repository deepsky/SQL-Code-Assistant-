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

package com.deepsky.view.query_pane.grid.renders;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColumnNumberRenderer extends JLabel implements TableCellRenderer {

    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public ColumnNumberRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setHorizontalAlignment(JLabel.RIGHT);
        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
            JTable table, Object color,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        Color newColor = new Color(203, 203, 203); //(Color) color;
        //setBackground(newColor);
        setText(((Integer) color).toString());
        if (isSelected) {
            setBackground(new Color(163, 163, 163));
        } else {
            setBackground(newColor);
        }
        if (isBordered) {
//                if (isSelected) {
//                    if (selectedBorder == null) {
//                        selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
//                                table.getSelectionBackground());
//                    }
//                    setBorder(selectedBorder);
//                } else {
//                    if (unselectedBorder == null) {
//                        unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
//                                table.getBackground());
//                    }
//                    setBorder(unselectedBorder);
//                }
            if (isSelected) {
//                    if (selectedBorder == null) {
//                        selectedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
/*
                                new Color(203, 203, 203),
                                //table.getSelectionBackground(),
                                new Color(163, 163, 163)
                                //table.getBackground()
                        );
*/
//                        selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
//                                table.getSelectionBackground());
//                    }
//                    setBorder(selectedBorder);
            } else {
//                    if (unselectedBorder == null) {
//                        selectedBorder = BorderFactory.createLineBorder(
//                                table.getSelectionBackground()
//                        );
//                        unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
//                                table.getBackground());
//                    }
//                    setBorder(unselectedBorder);
            }
        }

/*
            setToolTipText("RGB value: " + newColor.getRed() + ", "
                    + newColor.getGreen() + ", "
                    + newColor.getBlue());
*/
        return this;
    }
}

