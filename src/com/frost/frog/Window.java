package com.frost.frog;

import com.frost.frog.state.Menu;
import com.frost.frog.state.State;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private static Window window = null;

    private State state;

    private Window(String name) {
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
        Timer running = new Timer(1000 / 60, e -> this.repaint());
        running.start();
    }

    static Window instance() {
        return (window != null) ? window : (window = new Window("com.frost.frog.Frog"));
    }

    public void dispose() {
        window = null;
    }

    public void paint(Graphics g) {
        if (state != null) {
            state.repaint();
        }
    }

    public void setState(State st) {
        getContentPane().remove(state);
        this.state = st;
        getContentPane().add(st);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
