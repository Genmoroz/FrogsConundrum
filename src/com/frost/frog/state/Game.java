package com.frost.frog.state;

import com.frost.frog.Fields;
import com.frost.frog.Frog;
import com.frost.frog.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Game extends State {

    public static boolean ready_Frog = false;
    public static boolean LOSE = false;
    public static boolean WIN = false;

    private Fields fields;
    private boolean lock;
    private boolean restart;
    private int score = 0;
    private Frog frog;

    public Game(int width, int height, String fileName, Window window) {
        super(width, height, fileName, window);
        fields = new Fields(58, 20, 35);

        addMouseListener(new MouseListener());
        restart = false;
        lock = false;
        if (fields == null)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        frog = new Frog(0, 0, 35, fields, this);
    }

    public void addFields() {
        fields = new Fields(58, 20, 35);
    }

    private class MouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!LOSE && !WIN) {
                if (e.getX() >= 400 && e.getX() <= (400 + 60) &&
                        e.getY() >= 430 && e.getY() <= (430 + 30)) {
                    restart = !restart;

                    if (!lock) {
                        setLock();
                    }
                }

                if (restart) {
                    if (e.getX() >= 150 && e.getX() <= (50 + 200) &&
                            e.getY() >= 150 && e.getY() <= (100 + 150)) {
                        window.setState(new Game(getWidth(), getHeight(), "water.jpg", window));
                    }
                    if (e.getX() >= 250 && e.getX() <= (150 + 200) &&
                            e.getY() >= 150 && e.getY() <= (100 + 150)) {
                        restart = !restart;
                    }
                }
                if (!ready_Frog)
                    if (!lock) {
                        if (fields.collision(e.getX(), e.getY())) {
                            score++;
                            ready_Frog = !ready_Frog;
                        }
                    }

                if ((!restart) && e.getX() >= 48 && e.getX() <= (48 + 60) &&
                        e.getY() >= 430 && e.getY() <= (430 + 35)) {
                    setLock();
                }
            } else {
                window.setState(new Menu(getWidth(), getHeight(), "water.jpg", window));
                LOSE = false;
                WIN = false;
            }
        }
    }


    public void paint(Graphics g) {

        BufferedImage buffer = (BufferedImage) createImage(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(backGround, 0, 0, getWidth(), getHeight(), null);
        fields.draw(g2);
        frog.draw(g2);

        g2.setColor((lock) ? Color.BLACK : Color.WHITE);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        g2.drawString("lock", 50, 450);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        g2.drawString("Restart", 400, 450);

        g2.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        g2.drawString("Score: " + score, 190, 450);

        if (restart) {
            paintDarkPanel(g2);
            g2.drawString("Yes", 170, 210);
            g2.drawString("No", 270, 210);
        }
        if (LOSE) {
            paintDarkPanel(g2);
            g2.drawString("You lose", 160, 210);
        }
        if (WIN) {
            paintDarkPanel(g2);
            g2.drawString("You win", 160, 210);
        }
        g.drawImage(buffer, 0, 0, null);
    }

    private void paintDarkPanel(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(new Color(161, 29, 0, 230));
        g2.fillRect(150, 150, 200, 100);
        g2.setColor(new Color(71, 15, 0, 180));
        g2.drawRect(149, 149, 200, 101);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 40));
    }

    private void setLock() {
        lock = !lock;
    }
}
