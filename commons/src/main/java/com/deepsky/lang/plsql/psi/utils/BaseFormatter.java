package com.deepsky.lang.plsql.psi.utils;

/**
 * Created with IntelliJ IDEA.
 * User: sky
 * Date: 12/9/12
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseFormatter {
    public static String sql2htmlBase(String element){
        String text = element.trim();
        text = text.replace("\n", "<br>");
        return "<html>" + text + "</html>";
    }

    public static String sql2htmlBase(String element, int maxWidth, int maxHeight){
        String text = element.trim();
        String[] parts = text.split("\n");
        StringBuilder bld = new StringBuilder();
        for(int i =0;i<parts.length; i++){
            boolean truncated = false;
            if(parts[i].length() > maxWidth){
                parts[i] = parts[i].substring(0, maxWidth-4) + " ...";
                truncated = true;
            }
            if(i+1 == maxHeight && i+1 < parts.length){
                if(!truncated){
                    parts[i] = parts[i] + " ...";
                }
                if(i>0) bld.append("<br>");
                bld.append(parts[i]);
                break;
            }
            if(i>0) bld.append("<br>");
            bld.append(parts[i]);
        }

        return "<html>" + bld.toString() + "</html>";
    }

}
