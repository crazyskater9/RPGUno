package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameArea {

    GameKeyListener gameKeyListener;
    Landscape landscape;

    public GameArea(GameKeyListener gameKeyListener){
        this.gameKeyListener = gameKeyListener;
        landscape = new Landscape("levels/level1.map");

        ArrayList<Vector2D> movementPath = new ArrayList<Vector2D>();
        for(int i = 0; i < 20; i++) {
            movementPath.add(new Vector2D(i, 100));
        }
        for(int i = 0; i < 20; i++) {
            movementPath.add(new Vector2D(20 - i, 100));
        }
        landscape.objects.add(new Hostile(new Vector2D(300,100), new Vector2D(), movementPath));

        //Temp. Level-Editor
/*        ArrayList<Drawable> objects = new ArrayList<Drawable>();
        objects.add(new Ground(0,0,1000,1000));
        objects.add(new Wall(100, 100));
        objects.add(new Wall(200,100, 10));
        objects.add(new Player());
        landscape = new Landscape(1000,1000,objects);
        landscape.toFile();*/
    }

    // Called in GamePanel's repaint method
    void paint(Graphics g){
        for(Iterator<Drawable> iterator = landscape.objects.iterator(); iterator.hasNext();) {
            Drawable d = iterator.next();
            if(d.health == 0) iterator.remove();
            else d.paint(g);

            if(d instanceof Player){
                ((Player) d).setMovement(gameKeyListener.keysPressed);
                checkOverlaps();
                checkProjectileHits(((Player) d).projectileList);
                d.move();
                GameData.landscapeToPlayerVector.set((int) d.position.x + d.width/2, (int) d.position.y + d.height/2);
            }
            else if(d instanceof Hostile) {
                d.move();
            }
        }
    }

    boolean checkOverlaps()
    {
        for(Drawable o1: landscape.objects)
        {
            if(!(o1 instanceof Ground)) {
                Drawable compareDrawable = new Drawable(o1);
                Vector2D correctionVector = new Vector2D();
                compareDrawable.move();

                if(isDrawableOutOfBounds(compareDrawable))
                {
                    compareDrawable.movement.set(-compareDrawable.movement.x,-compareDrawable.movement.y);
                    compareDrawable.movement.normalize();

                    do{
                        correctionVector.add(compareDrawable.movement);
                        compareDrawable.move();
                    }while(isDrawableOutOfBounds(compareDrawable));

                    o1.movement.add(correctionVector);

                    compareDrawable = new Drawable(o1);
                    correctionVector = new Vector2D();
                    compareDrawable.move();
                }

                for(Drawable o2: landscape.objects)
                {
                    if(!o1.equals(o2) && o1.isNotPassable() && o2.isNotPassable() && !(o2 instanceof Ground))
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

        }
        return false;
    }

    void checkProjectileHits(ArrayList<Projectile> projectileList)
    {
        for(Projectile projectile: projectileList)
        {
            if(isDrawableOutOfBounds(projectile)){
                projectile.lifeTime = 0;
            }

            for (Drawable drawable : landscape.objects) {
                if (drawable.isNotPassable() && !(drawable instanceof Player)) {
                    if (compareBoolArrays(projectile, drawable)) {
                        projectile.lifeTime = 0;

                        if (drawable.health - projectile.damageOnHit > 0) drawable.health -= projectile.damageOnHit;
                        else if (drawable.health != -1) drawable.health = 0;

//                        System.out.println(drawable + " was hit for " + projectile.damageOnHit + " Damage | Health = " + drawable.health);
                    }
                }
            }
        }
    }

    boolean compareBoolArrays(Drawable d1, Drawable d2)
    {
        int d1PositionXInt = (int) d1.position.x;
        int d1PositionYInt = (int) d1.position.y;
        int d2PositionXInt = (int) d2.position.x;
        int d2PositionYInt = (int) d2.position.y;

        if(d1PositionXInt < d2PositionXInt)
        {
            if(d1PositionXInt+d1.width <= d2PositionXInt) return false;
            else
            {
                // Top Left
                if(d1PositionYInt < d2PositionYInt)
                {
                    if(d1PositionYInt + d1.height <= d2PositionYInt) return false;
                    else
                    {
                        for(int i=0;i < d1PositionXInt + d1.width - d2PositionXInt;i++) {
                            for(int j=0;j < d1PositionYInt + d1.height - d2PositionYInt;j++){
                                if(d1.gameImage.imageBoolArray[d2PositionXInt-d1PositionXInt +i][d2PositionYInt-d1PositionYInt +j] && d2.gameImage.imageBoolArray[i][j]) return true;
                            }
                        }
                        return false;
                    }
                }
                // Bottom Left
                else
                {
                    if(d2PositionYInt+d2.height <= d1PositionYInt) return false;
                    else
                    {
                        for(int i=0;i < d1PositionXInt+d1.width-d2PositionXInt;i++) {
                            for(int j=0;j < d2PositionYInt+d2.height-d1PositionYInt;j++){
                                if(d1.gameImage.imageBoolArray[d2PositionXInt-d1PositionXInt +i][j] && d2.gameImage.imageBoolArray[i][d1PositionYInt-d2PositionYInt +j]) return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
        else
        {
            if(d2PositionXInt+d2.width <= d1PositionXInt) return false;
            else
            {
                // Top Right
                if(d1PositionYInt < d2PositionYInt)
                {
                    if(d1PositionYInt+d1.height <= d2PositionYInt) return false;
                    else{
                        for(int i=0;i < d2PositionXInt+d2.width-d1PositionXInt;i++) {
                            for(int j=0;j < d1PositionYInt+d1.height-d2PositionYInt;j++){
                                if(d1.gameImage.imageBoolArray[i][d2PositionYInt - d1PositionYInt +j] && d2.gameImage.imageBoolArray[d1PositionXInt - d2PositionXInt +i][j]) return true;
                            }
                        }
                        return false;
                    }
                }
                // Bottom Right
                else
                {
                    if(d2PositionYInt+d2.height <= d1PositionYInt) return false;
                    else
                    {
                        for(int i=0;i < d2PositionXInt+d2.width-d1PositionXInt;i++) {
                            for(int j=0;j < d2PositionYInt+d2.height-d1PositionYInt;j++){
                                if(d1.gameImage.imageBoolArray[i][j] && d2.gameImage.imageBoolArray[d1PositionXInt-d2PositionXInt +i][d1PositionYInt-d2PositionYInt +j]) return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
    }

    boolean isDrawableOutOfBounds(Drawable drawable) {
        return (drawable.position.x < 0)
                || (drawable.position.x > Landscape.WIDTH - drawable.width)
                || (drawable.position.y < 0)
                || (drawable.position.y > Landscape.HEIGHT - drawable.height);
    }
}
