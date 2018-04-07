package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class Player {

    Vector2D position;
    Vector2D movement;
    int width;
    int height;


    public Player() {
        position = new Vector2D(400 ,300);
        movement = new Vector2D();
        width = 20;
        height = 20;
    }

    void paint(Graphics g) {

        move();
        checkBorders();

        g.setColor(Color.GREEN);
        g.fillRect((int)position.x,(int)position.y,width,height);
    }

    private void move() {

        position.add(movement);
        //System.out.println("X: " + position.x + " | Y: " + position.y);
    }

    void setMovement(Set<Character> keysPressed) {

        if(keysPressed.contains('w') && movement.y >= -10) movement.y -=2;
        else if(movement.y < 0) movement.y +=2;

        if(keysPressed.contains('a') && movement.x >= -10) movement.x -=2;
        else if(movement.x < 0) movement.x +=2;

        if(keysPressed.contains('s') && movement.y <= 10) movement.y +=2;
        else if(movement.y > 0) movement.y -=2;

        if(keysPressed.contains('d') && movement.x <= 10) movement.x +=2;
        else if(movement.x > 0) movement.x -=2;

        if(keysPressed.contains('w') && keysPressed.contains('s')) movement.y = 0;
        if(keysPressed.contains('a') && keysPressed.contains('d')) movement.x = 0;
    }

    private void checkBorders() {
        if(position.x < 0) position.x = 0;
        if(position.x > GameData.WIDTH - width) position.x = GameData.WIDTH - width;
        if(position.y < 0) position.y = 0;
        if(position.y > GameData.HEIGHT - height) position.y = GameData.HEIGHT - height;
    }

    void checkEnvironment(ArrayList<Environment> objects) {
        for(Environment e: objects) {
            if(e.isNotPassable()) {
                if(position.y <= e.position.y + e.height && position.y + height >= e.position.y) {
                    if(position.x + movement.x <= e.position.x + e.width && position.x >e.position.x && movement.x < 0) movement.x = 0;
                    else if(position.x + movement.x + width >= e.position.x && position.x < e.position.x && movement.x > 0) movement.x = 0;
                }

                if(position.x <= e.position.x + e.width && position.x + width >= e.position.x) {
                    if(position.y + movement.y <= e.position.y + e.height && position.y >e.position.y && movement.y < 0) movement.y = 0;
                    else if(position.y + movement.y + height >= e.position.y && position.y < e.position.y && movement.y > 0) movement.y = 0;
                }
            }

        }
    }
}
