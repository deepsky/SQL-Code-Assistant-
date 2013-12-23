package com.deepsky.uiForms;

import javax.swing.*;
import java.awt.*;

public class ListItemRenderer  extends DefaultListCellRenderer //JLabel
{ //implements ListCellRenderer {

    private Font uhOhFont;

    public ListItemRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
        uhOhFont = getFont();
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
//        int selectedIndex = ((Integer)value).intValue();

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/*
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        //Set the icon and text.  If icon was null, say so.
//        ImageIcon icon = images[selectedIndex];
//        String pet = petStrings[selectedIndex];
//        setIcon(icon);
//        if (icon != null) {
//            setText(pet);
//            setFont(list.getFont());
//        } else {
//            setUhOhText(pet + " (no image available)",
//                    list.getFont());
//        }
//

        setUhOhText((String) value, list.getFont());
        return this;
*/
    }

    //Set the font and text when no image was found.
    protected void setUhOhText(String uhOhText, Font normalFont) {
        if (uhOhFont == null) { //lazily create this font
            uhOhFont = normalFont.deriveFont(12); //deriveFont(Font.ITALIC);
        }
        setFont(uhOhFont);
        setText(uhOhText);
    }

}
