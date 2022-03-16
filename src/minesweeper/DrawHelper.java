package minesweeper;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawHelper {

    public static void drawOval(Graphics g, int x, int y, int x2, int y2, boolean centered) {
        if (x2 < x) {
            int f = x2;
            x2 = x;
            x = f;
        }

        if (y2 < y) {
            int f = y2;
            y2 = y;
            y = f;
        }

        if (centered) {
            int xoff = (x2 - x) / 2;
            int yoff = (y2 - y) / 2;
            x -= xoff;
            y -= yoff;
            g.drawOval(x, y, x2 - x - xoff, y2 - y - yoff);
        } else {
            g.drawOval(x, y, x2 - x, y2 - y);
        }
    }

    public static void fillOval(Graphics g, int x, int y, int x2, int y2, boolean centered) {
        {
            if (x2 < x) {
                int f = x2;
                x2 = x;
                x = f;
            }

            if (y2 < y) {
                int f = y2;
                y2 = y;
                y = f;
            }
        }

        if (centered) {
            int xoff = (x2 - x) / 2;
            int yoff = (y2 - y) / 2;
            x -= xoff;
            y -= yoff;
            g.fillOval(x, y, x2 - x - xoff, y2 - y - yoff);
        } else {
            g.fillOval(x, y, x2 - x, y2 - y);
        }
    }

    public static void drawRect(Graphics g, int x, int y, int x2, int y2, boolean centered) {
        {
            if (x2 < x) {
                int f = x2;
                x2 = x;
                x = f;
            }

            if (y2 < y) {
                int f = y2;
                y2 = y;
                y = f;
            }
        }

        if (centered) {
            int xoff = (x2 - x) / 2;
            int yoff = (y2 - y) / 2;
            x -= xoff;
            y -= yoff;
            g.drawRect(x, y, x2 - x - xoff, y2 - y - yoff);
        } else {
            g.drawRect(x, y, x2 - x, y2 - y);
        }
    }

    public static void fillRect(Graphics g, int x, int y, int x2, int y2, boolean centered) {
        {
            if (x2 < x) {
                int f = x2;
                x2 = x;
                x = f;
            }

            if (y2 < y) {
                int f = y2;
                y2 = y;
                y = f;
            }
        }

        if (centered) {
            int xoff = (x2 - x) / 2;
            int yoff = (y2 - y) / 2;
            x -= xoff;
            y -= yoff;
            g.fillRect(x, y, x2 - x - xoff, y2 - y - yoff);
        } else {
            g.fillRect(x, y, x2 - x, y2 - y);
        }
    }

    public static void drawPolygon(Graphics g, int[] xvals, int[] yvals, boolean centered) {
        if (xvals.length != yvals.length) {
            System.out.println("drawPolygon xvals length doesn't match yvals length! aborting");
            return;
        }
        g.drawPolygon(xvals, yvals, xvals.length);
    }

    public static void fillPolygon(Graphics g, int[] xvals, int[] yvals, boolean centered) {
        if (xvals.length != yvals.length) {
            System.out.println("drawPolygon xvals length doesn't match yvals length! aborting");
            return;
        }
        g.fillPolygon(xvals, yvals, xvals.length);
    }

    public static void drawString(Graphics g, String s, int x, int y, String fontName, int f, int size) {
        if (!fontName.isEmpty()) {
            g.setFont(new Font(fontName, f, size));
        }

        g.drawString(s, x, y + size);
    }

    public static void drawLine(Graphics2D g2, int x, int y, int x2, int y2, int size, boolean centered) {
        g2.setStroke(new BasicStroke(size));
        if (centered) {
            int xoff = (x2 - x)/2;
            int yoff = (y2 - y)/2;
            x -= xoff;
            y -= yoff;
            g2.drawLine(x, y, x2 - xoff, y2 - yoff);
        } else {
            g2.drawLine(x, y, x2, y2);
        }
    }

    public static void drawLine(Graphics g, int x, int y, int x2, int y2, boolean centered) {
        if (centered) {
            int xoff = (x2 - x)/2;
            int yoff = (y2 - y)/2;
            x -= xoff;
            y -= yoff;
            g.drawLine(x, y, x2 - xoff, y2 - yoff);
        } else {
            g.drawLine(x, y, x2, y2);
        }
    }
}
