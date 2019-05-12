package com.frost.frog;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Field {

    Point position;
    int height;
    int cost = 9999;
    Field field = null;
    int indexX, indexY;

    private boolean top = false;

    Field(int x, int y, int height) {
        position = new Point(x, y);
        this.height = height;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(255, 250, 0, 200));
        g2.fill(new Area(new Ellipse2D.Double(position.x, position.y, height, height)));
    }

    boolean collision(int x, int y) {
        return (x >= position.x && x <= (position.x + height) &&
                y >= position.y && y <= (position.y + height));
    }

    void setTop(boolean top) {
        this.top = top;
    }

    Field getField() {
        return (field == null) ? this : field.getField();
    }

    boolean getTop() {
        return top;
    }
}