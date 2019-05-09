package com.frost.frog.state;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import com.frost.frog.Window;

public abstract class State extends JPanel{

    protected BufferedImage backGround;
    protected Window window;

    private class MouseListener extends MouseAdapter{

    }

    public State(int width, int height, String fileName, Window window){
        setSize(width, height);
        setVisible(true);
        backGround = (fileName != null) ? Loader.load(fileName) : null;
        this.window = window;
    }
}
