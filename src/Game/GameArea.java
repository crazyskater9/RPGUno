package Game;

import java.awt.*;

public class GameArea {

    Player player;
    boolean debug_flag;

    public GameArea(){
        // Player init
        player = new Player();
        debug_flag = false;
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){

    }

}
