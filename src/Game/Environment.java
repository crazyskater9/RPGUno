package Game;

import java.awt.*;

public class Environment {

    Vector2D position;
    int width;
    int height;
    Color color;
    private boolean passable;

    public Environment() {
        position = new Vector2D();
        width = 0;
        height = 0;
        color = Color.BLACK;
        passable = false;
    }

    public Environment(Vector2D position, int width, int height, Color color, boolean passable) {
        this.position = new Vector2D(position);
        this.width = width;
        this.height = height;
        this.color = color;
        this.passable = passable;
    }

    public Environment(int x, int y, int width, int height, Color color, boolean passable) {
        this.position = new Vector2D(x,y);
        this.width = width;
        this.height = height;
        this.color = color;
        this.passable = passable;
    }

    void paint(Graphics g) {};

    boolean isPassable(){return passable;}

    boolean isNotPassable(){return !passable;}

    void setPassable(boolean x) {passable = x;}

}
