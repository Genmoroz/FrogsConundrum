package com.frost.frog.state;

import com.frost.frog.Window;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public abstract class State extends JPanel {

    BufferedImage backGround;
    Window window;

    private class MouseListener extends MouseAdapter {

    }

    public State(int width, int height, String fileName, Window window) {
        setSize(width, height);
        setVisible(true);
        backGround = (fileName != null) ? Loader.load(fileName) : null;
        this.window = window;
    }
}
