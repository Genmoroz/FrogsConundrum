package com.frost.frog;

import java.awt.Graphics2D;

public class Fields {

    private Field[][] fields;

    public Fields(int x, int y, int height) {
        fields = new Field[11][11];
        int xn = x;
        int yn = y;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new Field(xn, yn, height);
                fields[i][j].indexX = i;
                fields[i][j].indexY = j;
                xn += height + 2;
            }
            yn += height + 2;
            if (i % 2 == 0)
                xn = x - height / 2;
            else xn = x;
        }
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (i == 0 || i == fields.length - 1) {
                    fields[i][j].setTop(true);
                    fields[i][j].cost = 9999;
                }
                if (j == 0 || j == fields.length - 1) {
                    fields[i][j].setTop(true);
                    fields[i][j].cost = 9999;
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            fields[((int) (Math.random() * 100) % 9) + 1][((int) (Math.random() * 100) % 9) + 1] = null;
        }
    }

    void resetCoins() {
        for (Field[] field : fields) {
            for (int j = 0; j < field.length; j++) {
                if (field[j] != null) field[j].cost = 9999;
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (Field[] field : fields) {
            for (Field value : field) {
                if (value != null)
                    value.draw(g2);
            }
        }
    }

    public boolean collision(int x, int y) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] != null && fields[i][j].collision(x, y)) {
                    fields[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    Field[][] getFields() {
        return fields;
    }
}
