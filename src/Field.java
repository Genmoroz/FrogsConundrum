import MyPaint.Shape.Vector;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Field {

    protected Vector position;
    protected int height;
    private boolean top = false;
    public int cost = 9999;
    public Field field = null;
    public int indexX, indexY;

    public Field(int x, int y, int height){
        position = new Vector(x, y);
        this.height = height;
    }
    public void draw(Graphics2D g2){
        g2.setColor(new Color(255, 250, 0, 200));
        g2.fill(new Area(new Ellipse2D.Double(position.x, position.y, height, height)));
//        g2.setColor(new Color(0, 0, 0));
//        g2.drawString("" + cost, position.x, position.y + 20);
    }
    public boolean collision(int x, int y){
        return (x >= position.x && x <= (position.x + height) &&
                y >= position.y && y <= (position.y + height));
    }
    public void setTop(){
        top = true;
    }
    public Field getField(){
        return (field == null) ? this : field.getField();
    }
    public boolean getTop(){
        return top;
    }
}