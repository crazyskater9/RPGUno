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

        updatePosition();

        g.setColor(Color.GREEN);
        g.fillRect((int)position.x,(int)position.y,20,20);
    }

    void updatePosition() {
        position.add(movement);
    }

}
