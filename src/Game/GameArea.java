package Game;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameArea {

    GameKeyListener gameKeyListener;
    Landscape landscape;

    public GameArea(GameKeyListener gameKeyListener){
        this.gameKeyListener = gameKeyListener;

        landscape = new Landscape("levels/level1.map");

        landscape = new Landscape(10000,10000,new ArrayList<Drawable>());
        landscape.objects.add(new Wall(100, 100));
        landscape.objects.add(new Ground(400,100,80,80));
        landscape.objects.add(new Player());

        landscape.toFile();
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){
        for(Drawable d: landscape.objects) {
            d.paint(g);
            if(d instanceof Player){
                d.setMovement(gameKeyListener.keysPressed);
                checkOverlaps();
                d.move();
            }
        }
    }

    boolean checkOverlaps()
    {
        for(Drawable o1: landscape.objects)
        {
            Drawable compareDrawable = new Drawable(o1);
            Vector2D correctionVector = new Vector2D();
            compareDrawable.move();
            for(Drawable o2: landscape.objects)
            {
                if(!o1.equals(o2) && o1.isNotPassable() && o2.isNotPassable())
                {
                    if(compareBoolArrays(compareDrawable, o2))
                    {
                        compareDrawable.movement.set(-compareDrawable.movement.x,-compareDrawable.movement.y);
                        compareDrawable.movement.normalize();
                        do{
                            correctionVector.add(compareDrawable.movement);
                            compareDrawable.move();
                        }while(compareBoolArrays(compareDrawable,o2));
                        o1.movement.add(correctionVector);
                        return true;
                    }
                }
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
}
