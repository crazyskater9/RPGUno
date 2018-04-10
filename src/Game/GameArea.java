package Game;

import java.awt.*;
import java.util.ArrayList;

public class GameArea {

    Player player;
    GameKeyListener gameKeyListener;
    ArrayList<Environment> objects;

    public GameArea(GameKeyListener gameKeyListener){
        player = new Player();
        this.gameKeyListener = gameKeyListener;
        objects = new ArrayList<Environment>();
        objects.add(new Wall(100, 100, 80, 80));
        objects.add(new Ground(400,100,80,80));
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){

        for(Environment e: objects) {
            e.paint(g);
        }

        player.setMovement(gameKeyListener.keysPressed);
        player.checkEnvironment(objects);
        player.paint(g);

    }
}
