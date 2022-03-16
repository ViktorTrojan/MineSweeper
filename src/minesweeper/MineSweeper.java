package minesweeper;

import java.awt.Color;
import java.awt.Graphics2D;

public class MineSweeper {

    public static Color c1 = new Color(200, 255, 130), c2 = new Color(180, 220, 120), c3 = new Color(229, 194, 159), c4 = new Color(215, 184, 153);
    public static int xLen = 20, yLen = xLen;
    public Field[][] f;
    public Field fs;
    public int spacing;
    public boolean allRevealed;

    public MineSweeper(int spacing) {
        this.spacing = spacing;

        // INIT ARRAY
        f = new Field[yLen][xLen];
        createField(c1, c2);
    }

    public void createField(Color c1, Color c2) {
        for (int y1 = 0; y1 < yLen; y1++) {
            for (int x1 = 0; x1 < xLen; x1++) {
                Color c;
                if (y1 % 2 == 0) {
                    c = x1 % 2 == 0 ? c1 : c2;
                } else {
                    c = x1 % 2 != 0 ? c1 : c2;
                }

                int x = x1 * Field.size;
                int y = y1 * Field.size;
                f[y1][x1] = new Field(x + spacing, y + spacing, x + Field.size - spacing, y + Field.size - spacing, c);
            }
        }
    }

    public boolean isClickBomb(Field field, int y, int x) {
        int[] xoff = {0, 1, 1, 0, -1, -1, -1, 0, 1};
        int[] yoff = {0, 0, 1, 1, 1, 0, -1, -1, -1};
        for (int j = 0; j < xoff.length; j++) {
            try {
                if (field == f[y + yoff[j]][x + xoff[j]]) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public void setBombs(int amount, int y, int x) {
        for (int i = 0; i < amount; i++) {
            while (true) {
                int y1 = MathHelper.random(0, yLen - 1);
                int x1 = MathHelper.random(0, xLen - 1);

                if (isClickBomb(f[y1][x1], y, x)) {
                    continue;
                }

                if (!f[y1][x1].bomb) {
                    f[y1][x1].bomb = true;
                    break;
                }
            }
        }
    }

    public void setNumber() {
        for (int y1 = 0; y1 < yLen; y1++) {
            for (int x1 = 0; x1 < xLen; x1++) {
                getNeighbours(y1, x1);
            }
        }
    }

    public void getNeighbours(int x, int y) {
        if (f[y][x].bomb) {
            f[y][x].neighbours = -1;
            return;
        }

        int[] xoff = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] yoff = {0, 1, 1, 1, 0, -1, -1, -1};

        int neighbours = 0;

        for (int i = 0; i < xoff.length; i++) {
            try {
                if (f[y + yoff[i]][x + xoff[i]].bomb) {
                    neighbours++;
                }
            } catch (Exception e) {

            }
        }
        f[y][x].neighbours = neighbours;
    }

    public void showNeighbours(Graphics2D g2) {
        if (fs == null) {
            return;
        }
        int x = fs.x1 + Field.size / 2;
        int y = fs.y1 + Field.size / 2;
        g2.setColor(new Color(255, 100, 0));
        DrawHelper.drawRect(g2, x, y, x + Field.size * 2, y + Field.size * 2, true);
    }

    public Field getClosestField(int x1, int y1) {
        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                if (f[y][x].isHovered(x1, y1)) {
                    return f[y][x];
                }
            }
        }
        return null;
    }

    public int[] getClosestField_(int x1, int y1) {
        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                if (f[y][x].isHovered(x1, y1)) {
                    return new int[]{y, x};
                }
            }
        }
        return null;
    }

    public void revealField(boolean b, int y, int x) {

        if (b) {
            if (f[y][x].reveal) {
                return;
            }
            
            // If a flag is set, prevent it from revealing!
            if (f[y][x].flag) {
                return;
            }

            if (f[y][x].bomb) {
                revealAll(true);
                return;
            }

            if (f[y][x].c.equals(MineSweeper.c1)) {
                f[y][x].c = MineSweeper.c3;
            } else {
                f[y][x].c = MineSweeper.c4;
            }
            f[y][x].reveal = true;

            if (f[y][x].neighbours == 0) {
                floodFill(y, x);
            }
        } else {
            if (f[y][x].c.equals(MineSweeper.c3)) {
                f[y][x].c = MineSweeper.c1;
            } else {
                f[y][x].c = MineSweeper.c2;
            }
            f[y][x].reveal = false;
        }
    }

    public void floodFill(int y, int x) {
        int[] xoff = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] yoff = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < xoff.length; i++) {
            try {
                if (!f[y + yoff[i]][x + xoff[i]].bomb) {
                    f[y + yoff[i]][x + xoff[i]].flag = false;
                    revealField(true, y + yoff[i], x + xoff[i]);
                }
            } catch (Exception e) {
            }
        }
    }

    public void revealAll(boolean b) {
        if (b) {
            for (int y = 0; y < yLen; y++) {
                for (int x = 0; x < xLen; x++) {
                    f[y][x].flag = false;
                    f[y][x].reveal = true;
                }
            }
            allRevealed = true;
        } else {
            for (int y = 0; y < yLen; y++) {
                for (int x = 0; x < xLen; x++) {
                    f[y][x].flag = false;
                    f[y][x].reveal = false;
                }
            }
            allRevealed = false;
        }
    }
}
