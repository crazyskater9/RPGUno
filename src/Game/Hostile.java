package Game;

import java.awt.*;

public class Hostile extends NPC {

    private int pullRange;
    private int curSpeed;
    private int maxSpeed;
    public static Vector2D playerMiddlePosition;

    public Hostile(Vector2D position, int maxSpeed, String imagePath, int health, int pullRange){
        super(position, new Vector2D(), imagePath, health);
        this.pullRange = pullRange;
        this.maxSpeed = maxSpeed;
        curSpeed = 0;
        playerMiddlePosition = new Vector2D();
    }

    public void paint(Graphics g) {

        checkBorders();
        updateMovement();
        super.paint(g);
    }

    private void updateMovement() {

        Vector2D vec = new Vector2D(playerMiddlePosition.x - (position.x + width/2), playerMiddlePosition.y - (position.y + height/2));
        if(vec.magnitude() <= pullRange){
            if(curSpeed < maxSpeed) curSpeed++;
            movement.set(playerMiddlePosition.x - (position.x + width/2), playerMiddlePosition.y - (position.y + height/2));
        }
        else if(curSpeed > 0) curSpeed--;

        if(Math.abs(movement.magnitude()) < 20) movement.set(0,0);

        movement.normalize().multiply(curSpeed);
    }

    private void checkBorders() {
        if(position.x < 0) position.x = 0;
        if(position.x > Landscape.WIDTH - width) position.x = Landscape.WIDTH - width;
        if(position.y < 0) position.y = 0;
        if(position.y > Landscape.HEIGHT - height) position.y = Landscape.HEIGHT - height;
    }

    @Override
    public String toString() {
        return "Hostile (" + position.x + ", " + position.y + ")";
    }

}
