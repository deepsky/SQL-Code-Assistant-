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

package com.deepsky.view;

import javax.swing.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.StringTokenizer;

public class Helpers {

    private static final Icon DEFAULT_ICON = getDefaultIcon();

    public static Icon getIcon(String path) {
        URL url = (Helpers.class).getResource(path);
        if (url == null)
            try {
                url = new URL(path);
            }
            catch (MalformedURLException e) {
                return DEFAULT_ICON;
            }
        Icon icon = new ImageIcon(url);
        if (icon.getIconWidth() < 0 || icon.getIconHeight() < 0) {
            return DEFAULT_ICON;
        } else {
            return icon;
        }
    }

    private static Icon getDefaultIcon() {
        BufferedImage bi = new BufferedImage(18, 18, 3);
        Graphics2D g2 = bi.createGraphics();
        g2.setBackground(Color.red);
        g2.clearRect(0, 0, bi.getWidth(), bi.getHeight());
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2.0F));
        GeneralPath x = new GeneralPath();
        x.moveTo(0.0F, 0.0F);
        x.lineTo(bi.getWidth() - 1, bi.getHeight() - 1);
        x.moveTo(0.0F, bi.getHeight() - 1);
        x.lineTo(bi.getWidth() - 1, 0.0F);
        g2.draw(x);
        return new ImageIcon(bi);
    }

    public static Color parseColor(String rgba) {
        int red = 0;
        int green = 0;
        int blue = 0;
        int alpha = 128;
        StringTokenizer t = new StringTokenizer(rgba);
        try {
            if (t.hasMoreTokens())
                red = nextSample(t);
            if (t.hasMoreTokens())
                green = nextSample(t);
            if (t.hasMoreTokens())
                blue = nextSample(t);
            if (t.hasMoreTokens())
                alpha = nextSample(t);
        }
        catch (NumberFormatException nfe) {
        }
        return new Color(red, green, blue, alpha);
    }

    private static int nextSample(StringTokenizer t) {
        return Math.min(Math.abs(Integer.valueOf(t.nextToken()).intValue()), 255);
    }

    public static String encodeColor(Color color) {
        return color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + color.getAlpha();
    }

}
