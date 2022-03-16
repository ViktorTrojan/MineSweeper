package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

class Vec4 {
    public int x, y, w, h;
    
    public Vec4(int x,int y,int w,int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}

class Field {

    public static int size = 30;
    public int x1, y1, x2, y2, neighbours;
    public Color c;
    public boolean hovered, bomb, flag, reveal;

    public Field(int x1, int y1, int x2, int y2, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
    }

    public boolean isHovered(int x, int y) {
        if (x >= this.x1 && y >= this.y1 && x <= this.x2 && y <= this.y2) {
            hovered = true;
            return true;
        }
        hovered = false;
        return false;
    }

    public boolean isNumber() {
        return neighbours > 0;
    }

    public boolean isBomb() {
        return bomb;
    }

    public boolean isFlag() {
        return flag;
    }

    public void drawFlag(Graphics2D g2) {
        if (flag) {
            g2.setColor(new Color(255, 0, 0));
            DrawHelper.drawOval(g2, x1, y1, x2, y2, false);
        }
    }

    public void drawBomb(Graphics2D g2) {
        if (reveal && bomb) {
            g2.setColor(new Color(255, 0, 0));
            DrawHelper.fillOval(g2, x1, y1, x2, y2, false);
        }
    }

    public void drawString(Graphics2D g2) {
        if (reveal && neighbours > 0) {
            switch (neighbours) {
                case 1:
                    g2.setColor(new Color(25, 118, 210));
                    break;

                case 2:
                    g2.setColor(new Color(61, 144, 64));
                    break;

                case 3:
                    g2.setColor(new Color(211, 47, 47));
                    break;

                case 4:
                    g2.setColor(new Color(123, 31, 162));
                    break;

                case 5:
                    g2.setColor(new Color(251, 149, 21));
                    break;

                case 6:
                    g2.setColor(new Color(20, 154, 165));
                    break;

                case 7:
                    g2.setColor(new Color(245, 252, 16));
                    break;

                case 8:
                    g2.setColor(new Color(239, 29, 223));
                    break;
            }
            DrawHelper.drawString(g2, String.valueOf(neighbours), x1 + ((x2 - x1) / 4), -9 + y1 + ((y2 - y1) / 8), "Calibri", Font.PLAIN, Field.size);
        }
    }

    public void drawField(Graphics2D g2) {
        g2.setColor(hovered ? MathHelper.brighten(c, 22, false) : c);
        DrawHelper.fillRect(g2, x1, y1, x2, y2, false);
    }
}