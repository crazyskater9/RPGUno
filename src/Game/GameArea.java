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
        System.out.println(checkOverlaps((int)player.position.x, (int)player.position.y, player.width, player.height, (int)objects.get(1).position.x, (int)objects.get(1).position.y, objects.get(1).width, objects.get(1).height));
        player.paint(g);


    }

    boolean compareBoolArrays(boolean[][] array1, int x1, int y1, int width1, int height1, boolean[][] array2, int x2, int y2, int width2, int height2)
    {
        //for(int i = )
        return false;
    }

    boolean checkOverlaps(int x1,int y1,int width1,int height1,int x2,int y2,int width2,int height2)
    {
        if(x1<x2)
        {
            if(x1+width1 <= x2) return false;
            else
            {
                if(y1<y2)
                {
                    if(y1+height1 <= y2) return false;
                    else return true;
                }
                else
                {
                    if(y2+height2 <= y1) return false;
                    else return true;
                }
            }
        }
        else
        {
            if(x2+width2 <= x1) return false;
            else
            {
                if(y1<y2)
                {
                    if(y1+height1 <= y2) return false;
                    else return true;
                }
                else
                {
                    if(y2+height2 <= y1) return false;
                    else return true;
                }
            }
        }
    }

    boolean checkOverlapsPlayer(Player player, Environment obj) {

        int x1 = (int) player.position.x;
        int x2 = (int) obj.position.x;
        int y1 = (int) player.position.y;
        int y2 = (int) obj.position.y;
        int width1 = player.width;
        int width2 = obj.width;
        int height1 = player.height;
        int height2 = obj.height;

        if(x1<x2)
        {
            if(x1+width1 <= x2) return false;
            else
            {
                if(y1<y2)
                {
                    if(y1+height1 <= y2) return false;
                    else return true;
                }
                else
                {
                    if(y2+height2 <= y1) return false;
                    else return true;
                }
            }
        }
        else
        {
            if(x2+width2 <= x1) return false;
            else
            {
                if(y1<y2)
                {
                    if(y1+height1 <= y2) return false;
                    else return true;
                }
                else
                {
                    if(y2+height2 <= y1) return false;
                    else return true;
                }
            }
        }
    }
}
