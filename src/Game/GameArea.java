package Game;

import java.awt.*;

public class GameArea {

    Player player;
    GameKeyListener gameKeyListener;
    boolean debug_flag;

    public GameArea(GameKeyListener gameKeyListener){
        player = new Player();
        this.gameKeyListener = gameKeyListener;
        debug_flag = false;
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){
        player.setMovement(gameKeyListener.keysPressed);
        player.paint(g);
    }

    //Called for determining the Panel:
    /*
        if(GameData.activePanel instanceof GamePanel)
        {
            System.out.println(GameData.activePanel);
        }
     */

}
