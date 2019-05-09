package com.frost.frog;

import com.frost.frog.state.Game;
import com.frost.frog.state.Loader;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static com.frost.frog.state.Game.ready_Frog;

public class Frog extends Field{

    private BufferedImage imageLeft;
    private BufferedImage imageRight;
    private Field temp = null;
    private Fields field;
    private Field[][] fields;
    private int minMoves = 9999;
    private Field minField = null;
    private int x, y;
    private float xField, yField;
    private Window window;
    private boolean leftMove = false;
    private Point position;

    public Frog(int x, int y, int height, Fields fields, Window window, Game game) {
        super(x, y, height);
        this.window = window;
        this.x = 5;
        this.y = 5;
        imageLeft = Loader.load("frog_left.png");
        imageRight = Loader.load("frog_right.png");
        this.field = fields;
        this.fields = field.getFields();
        try {
            position = new Point(this.fields[this.x][this.y].position.x, this.fields[this.x][this.y].position.y);
            xField = position.x;
            yField = position.y;
        }catch (Throwable e){
            e.printStackTrace();
            game.addFields();
        }
        temp = this.fields[this.y][this.x];
        this.fields[this.y][this.x]= null;
    }
    public void update(){
        field.resetCoins();
        minMoves = 9999;
        minField = null;
        leftDown(x, y);
        rightDown(x, y);
        right(x, y);
        left(x, y);
        rightUp(x, y);
        leftUp(x, y);
        try {
            Thread.sleep(50);
            swap(minField.getField());
        }catch (Throwable e){
            Game.WIN = true;
        }
        ready_Frog = !ready_Frog;
    }
    public void draw(Graphics2D g2){
        g2.setColor(new Color(255, 250, 0, 200));
        g2.fill(new Area(new Ellipse2D.Double(position.x, position.y, height, height)));

        g2.drawImage((leftMove) ? imageLeft : imageRight, (int)position.x - 2, (int)position.y - 4, height + 2, height + 2, null);
        if (ready_Frog)update();

    }

    private void swap(Field field){

        if (field.getTop()) {
            Game.LOSE = true;
        }

        fields[x][y] = temp;
        int tmpY = y;
        int tmpX = x;
        x = field.indexX;
        y = field.indexY;
        leftMove = tmpY > y || (y < tmpY && x < tmpX) || (y < tmpY && x > tmpX);
        temp = fields[x][y];
        fields[x][y] = null;
        position.setLocation(field.position.x, field.position.y);
    }

    private void leftDown(int i, int j){

        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }return;
            }
            if (++i % 2 == 0)
                j--;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;

        }else {

            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }

            if (++i % 2 == 0) {
                j--;
            }


            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;

            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
    private void rightDown(int i, int j){

        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }return;
            }

            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }

            if (++i % 2 != 0)
                j++;

            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;

            if (fields[i][j].cost <= cost) return;



        }else {
            if (++i % 2 != 0)
                j++;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
    private void right(int i, int j){
        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }return;
            }
            j++;

            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;

            if (fields[i][j].cost <= cost) return;



        }else {


            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }


            j++;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
    private void left(int i, int j){
        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }return;
            }
            j--;

            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;

        }else {


            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }

            j--;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
    private void rightUp(int i, int j){
        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }return;
            }

            if (--i % 2 != 0)
                j++;

            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;

            if (fields[i][j].cost <= cost) return;



        }else {

            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }

            if (--i % 2 != 0)
                j++;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
    private void leftUp(int i, int j){

        Field temp = null;
        int cost = 1;

        if (fields[i][j] != null){

            cost = fields[i][j].cost;
            temp = fields[i][j];

            if (fields[i][j].getTop()){
                if (fields[i][j].cost <= minMoves){
                    minMoves = fields[i][j].cost;
                    minField = fields[i][j];
                }
                return;
            }

            if (--i % 2 == 0)
                j--;

            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;

            if (fields[i][j].cost <= cost) return;

        }else {

            if (i == 0 || i == 10 || j == 0 || j == 10){
                Game.WIN = true;
            }

            if (--i % 2 == 0)
                j--;
            if (i < 0 || i > 10 || j < 0 || j > 10 || fields[i][j] == null) return;
            if (fields[i][j].cost <= cost) return;
        }

        fields[i][j].cost = cost + 1;
        fields[i][j].field = temp;

        leftDown(i, j);
        rightDown(i, j);
        right(i, j);
        left(i, j);
        leftUp(i, j);
        rightUp(i, j);
    }
}
