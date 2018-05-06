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

    public Drawable(){
        this.movement = new Vector2D();
    }

    public Drawable(Drawable drawable){
        this.position = new Vector2D(drawable.position);
        this.movement = new Vector2D(drawable.movement);
        this.gameImage = new GameImage("images/Player.png");
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
        this.passable = drawable.passable;
    }

    void paint(Graphics g) {gameImage.paint(g,GameData.WIDTH/2 + (int)position.x - (int)GameData.middleOfScreenPosition.x, GameData.HEIGHT/2 + (int)position.y - (int)GameData.middleOfScreenPosition.y);}
    //void paint(Graphics g){gameImage.paint(g,(int)position.x ,(int)position.y);};

    boolean isPassable(){return passable;}

    boolean isNotPassable(){return !passable;}

    void setPassable(boolean x) {passable = x;}

    public void move() {
        position.add(movement);
    }

    void setMovement(Set<Character> keysPressed) {}
}
