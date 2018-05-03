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
        objects.add(new Wall(100, 100));
        objects.add(new Ground(400,100,80,80));
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){

        for(Environment e: objects) {
            e.paint(g);
        }
        player.paint(g);

        player.setMovement(gameKeyListener.keysPressed);
        checkOverlapsPlayer();
        player.move();

        //player.checkEnvironment(objects);
//        System.out.println(checkOverlaps((int)player.position.x, (int)player.position.y, player.width, player.height, (int)objects.get(1).position.x, (int)objects.get(1).position.y, objects.get(1).width, objects.get(1).height));

    }

    boolean checkOverlapsPlayer()
    {
        Player comparePlayer = new Player(player);
        Vector2D correctionVector = new Vector2D();
        comparePlayer.move();
        for(Environment o: objects)
        {
            if(compareBoolArrays(comparePlayer, o)&&o.isNotPassable())
            {
                comparePlayer.movement.set(-comparePlayer.movement.x,-comparePlayer.movement.y);
                comparePlayer.movement.normalize();
                do{
                    correctionVector.add(comparePlayer.movement);
                    comparePlayer.move();
                }while(compareBoolArrays(comparePlayer,o));
                player.movement.add(correctionVector);
                return true;
            }
        }
        return false;
    }

    boolean compareBoolArrays(Drawable d1, Drawable d2)
    {
        if(d1.position.x<d2.position.x)
        {
            if(d1.position.x+d1.width <= d2.position.x) return false;
            else
            {
                if(d1.position.y<d2.position.y)
                {
                    if(d1.position.y+d1.height <= d2.position.y) return false;
                    else
                    {
                        for(int i=0;i<d1.position.x+d1.width-d2.position.x;i++) {
                            for(int j=0;j<d1.position.y+d1.height-d2.position.y;j++){
                                if(d1.gameImage.imageBoolArray[(int)(d2.position.x-d1.position.x)+i][(int)(d2.position.y-d1.position.y)+j] && d2.gameImage.imageBoolArray[i][j]) return true;
                            }
                        }
                        return false;
                    }
                }
                else
                {
                    if(d2.position.y+d2.height <= d1.position.y) return false;
                    else
                    {
                        for(int i=0;i<d1.position.x+d1.width-d2.position.x;i++) {
                            for(int j=0;j<d2.position.y+d2.height-d1.position.y;j++){
                                if(d1.gameImage.imageBoolArray[(int)(d2.position.x-d1.position.x)+i][j] && d2.gameImage.imageBoolArray[i][(int)(d1.position.y-d2.position.y)+j]) return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
        else
        {
            if(d2.position.x+d2.width <= d1.position.x) return false;
            else
            {
                if(d1.position.y<d2.position.y)
                {
                    if(d1.position.y+d1.height <= d2.position.y) return false;
                    else{
                        for(int i=0;i<d2.position.x+d2.width-d1.position.x;i++) {
                            for(int j=0;j<d1.position.y+d1.height-d2.position.y;j++){
                                if(d1.gameImage.imageBoolArray[i][(int)(d2.position.y-d1.position.y)+j] && d2.gameImage.imageBoolArray[(int)(d1.position.x-d2.position.x)+i][j]) return true;
                            }
                        }
                        return false;
                    }
                }
                else
                {
                    if(d2.position.y+d2.height <= d1.position.y) return false;
                    else
                    {
                        for(int i=0;i<d2.position.x+d2.width-d1.position.x;i++) {
                            for(int j=0;j<d2.position.y+d2.height-d1.position.y;j++){
                                if(d1.gameImage.imageBoolArray[i][j] && d2.gameImage.imageBoolArray[(int)(d1.position.x-d2.position.x)+i][(int)(d1.position.y-d2.position.y)+j]) return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
    }

    boolean isDrawableOverlapping(Drawable d1, Drawable d2) {
        if(d1.position.x<d2.position.x)
        {
            if(d1.position.x+d1.width <= d2.position.x) return false;
            else
            {
                if(d1.position.y<d2.position.y)
                {
                    if(d1.position.y+d1.height <= d2.position.y) return false;
                    else return true;
                }
                else
                {
                    if(d2.position.y+d2.height <= d1.position.y) return false;
                    else return true;
                }
            }
        }
        else
        {
            if(d2.position.x+d2.width <= d1.position.x) return false;
            else
            {
                if(d1.position.y<d2.position.y)
                {
                    if(d1.position.y+d1.height <= d2.position.y) return false;
                    else return true;
                }
                else
                {
                    if(d2.position.y+d2.height <= d1.position.y) return false;
                    else return true;
                }
            }
        }
    }
}
