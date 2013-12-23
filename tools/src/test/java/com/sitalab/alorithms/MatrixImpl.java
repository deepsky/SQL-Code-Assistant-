package com.sitalab.alorithms;

import java.util.HashSet;
import java.util.Set;

public class MatrixImpl implements Matrix {

    private Set<String> marks = new HashSet<String>();
    private String[] yNames, xNames;

    public MatrixImpl(String[] yNames, String[] xNames){
        this.yNames = yNames;
        this.xNames = xNames;
    }

    public String[] getYNames() {
        return yNames;
    }

    public String[] getXNames() {
        return xNames;
    }

    public boolean isMarked(String posY, String posX) {
        return marks.contains(posY + "$" +posX);
    }

    public void mark(String posY, String posX) {
        marks.add(posY + "$" +posX);
    }
}
