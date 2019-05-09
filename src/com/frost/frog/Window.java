package com.frost.frog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.frost.frog.state.*;
import com.frost.frog.state.Menu;

public class Window extends JFrame implements ActionListener{

private static Window window = null;

    private Timer running;
    private State state = null;

    private Window(String name){
        super(name);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        state = new Menu(getWidth(), getHeight(), "water.jpg", this);
        getContentPane().add(state);
        running = new Timer(1000/60, e -> this.repaint());
        running.start();

    }
    public static Window instance(String name){
        return (window != null) ? window : (window = new Window(name));
    }
    public void dispose(){
        window = null;
    }
    public void paint(Graphics g){
        if (state != null){
            state.repaint();
        }
    }
    public void setState(State st){
        getContentPane().remove(state);
        this.state = st;
        getContentPane().add(st);

    }
    @Override

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
