package Game;

import java.awt.*;
import java.util.Set;

public class Drawable {
    Vector2D position;
    Vector2D movement;
    int width;
    int height;
    GameImage gameImage;
    protected boolean passable;
    int health;

    public Drawable(){
        this.movement = new Vector2D();
    }

    public Drawable(Drawable drawable){
        this.position = new Vector2D(drawable.position);
        this.movement = new Vector2D(drawable.movement);
        this.gameImage = new GameImage("images/Player.png");    // Etwas problematisch ? drawable.gameImage als Attribut nicht nutzbar
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
        this.passable = drawable.passable;
    }

    public Drawable(Vector2D position, Vector2D movement, GameImage gameImage, boolean passable, int health) {
        this.position = new Vector2D(position);
        this.movement = new Vector2D(movement);
        this.gameImage = new GameImage(gameImage);
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
        this.passable = passable;
        this.health = health;
    }

    void paint(Graphics g) {gameImage.paint(g,GameData.WIDTH/2 + (int)position.x - (int)GameData.landscapeToPlayerVector.x, GameData.HEIGHT/2 + (int)position.y - (int)GameData.landscapeToPlayerVector.y);}

    boolean isPassable(){return passable;}

    boolean isNotPassable(){return !passable;}

    void setPassable(boolean x) {passable = x;}

    public void move() {
        position.add(movement);
    }

}
