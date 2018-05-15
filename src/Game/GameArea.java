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

    boolean isDrawableOutOfBounds(Drawable drawable) {
        return (drawable.position.x < 0)
                || (drawable.position.x > Landscape.WIDTH - drawable.width)
                || (drawable.position.y < 0)
                || (drawable.position.y > Landscape.HEIGHT - drawable.height);
    }
}
