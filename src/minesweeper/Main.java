package minesweeper;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
    
    public static void createFrame() {
        JFrame jf = new JFrame("MineSweeper");
        Draw draw = new Draw();
        jf.addKeyListener(draw);
        draw.addMouseListener(draw);
        draw.addMouseMotionListener(draw);
        draw.setPreferredSize(new Dimension(Draw.WIDTH, Draw.HEIGHT));
        draw.setVisible(true);
        jf.getContentPane().add(draw); // add draw to jf
        jf.pack(); // size the frame

        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.requestFocus();
        jf.setVisible(true);
    }
    
    public static void main(String[] args) {
        createFrame();
    }
}
