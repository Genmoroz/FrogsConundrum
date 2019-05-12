package com.frost.frog.state;

import com.frost.frog.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends State {

    private Color colorText = Color.BLACK;

    public Menu(int width, int height, String fileName, Window window) {
        super(width, height, fileName, window);
        addMouseListener(new MouseListener());
    }

    private class MouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (e.getX() >= 130 && e.getX() <= (130 + 220)
                    && e.getY() >= 170 && e.getY() <= (170 + 40)) {
                window.setState(new Game(window.getWidth(), window.getHeight(), "water.jpg", window));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(backGround, 0, 0, getWidth(), getHeight(), null);
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 35));
        g2.setColor(colorText);
        g2.drawString("Start the game", 130, 200);
        g.drawImage(buffer, 0, 0, null);
    }
}
