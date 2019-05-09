package com.frost.frog.state;

import com.frost.frog.Window;

import java.awt.*;

public class New extends State {
    public New(int width, int height, String fileName, Window window) {
        super(width, height, fileName, window);
    }
    public void paint(Graphics g){
        Image buffer = createImage(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D)buffer.getGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(buffer, 0, 0, null);
    }
}
