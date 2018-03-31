package Game;

import java.awt.*;

public class Player {

    Vector2D position;
    Vector2D movement;


    public Player() {
        position = new Vector2D(400 ,300);
        movement = new Vector2D();
    }

    void paint(Graphics g) {

        move();

        g.setColor(Color.GREEN);
        g.fillRect((int)position.x,(int)position.y,20,20);
    }

    void move() {
        if(position.x < 10 && movement.x < 0) movement.x = 0;
        if(position.x > GameData.WIDTH - 10 - 20 && movement.x > 0) movement.x = 0;
        if(position.y < 10 && movement.y < 0) movement.y = 0;
        if(position.y > GameData.HEIGHT - 10 - 20 && movement.y > 0) movement.y = 0;

        position.add(movement);

        //System.out.println("X: " + position.x + " | Y: " + position.y);
    }

}
