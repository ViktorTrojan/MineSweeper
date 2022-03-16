package minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

public class Draw extends JLabel implements MouseListener, MouseMotionListener, KeyListener {

    public static int WIDTH = 600, HEIGHT = WIDTH;
    public int x, y;
    public boolean firstClick = true;

    public MineSweeper ms;

    public Draw() {
        ms = new MineSweeper(1);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(new Color(100, 100, 100));
        DrawHelper.fillRect(g2, 0, 0, WIDTH, HEIGHT, false);

        for (int y = 0; y < ms.yLen; y++) {
            for (int x = 0; x < ms.xLen; x++) {
                ms.f[y][x].drawField(g2);
                ms.f[y][x].drawString(g2);
                ms.f[y][x].drawBomb(g2);
                ms.f[y][x].drawFlag(g2);
            }
        }

        // Middle Click
        ms.showNeighbours(g2);
    }

    public void setPos(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        setPos(me);
        Field f = ms.getClosestField(x, y);
        if (f == null) {
            return;
        }
        int[] i = ms.getClosestField_(x, y);

        if (me.getButton() == 1) {
            if (firstClick) {
                ms.setBombs(ms.xLen * 3, i[0], i[1]);
                ms.setNumber();
                firstClick = false;
            }

            ms.revealField(true, i[0], i[1]);
        } else if (me.getButton() == 2) {
            ms.fs = f;
        } else if (me.getButton() == 3) {
            if (f.reveal) {
                return;
            }
            f.flag = !f.flag;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        setPos(me);
        if (me.getButton() == 2) {
            ms.fs = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        setPos(me);

        for (int y = 0; y < ms.yLen; y++) {
            for (int x = 0; x < ms.xLen; x++) {
                ms.f[y][x].isHovered(this.x, this.y);
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == (int) 'Q') {
            ms.revealAll(true);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == (int) 'Q') {
            ms.revealAll(false);
        }
        repaint();
    }
}
