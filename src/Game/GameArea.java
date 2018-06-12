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

        landscape.objects.add(new Hostile(new Vector2D(300,100),1, "images/enemy1.png", 10, 300));

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
            if((d.curHealth <= 0) && (d.maxHealth != -1)) iterator.remove();
            else d.paint(g);

            if(d instanceof Player){
                ((Player) d).setMovement(gameKeyListener.keysPressed);
                checkOverlaps();
                checkProjectileHits(((Player) d).projectileList);
                d.move();
                GameData.landscapeToPlayerVector.set((int) d.position.x + d.width/2, (int) d.position.y + d.height/2);
                Hostile.playerMiddlePosition.set(d.position.x + d.width/2, d.position.y + d.height/2);
            }
            else if(d instanceof Hostile){
                checkOverlaps();
                d.move();
            }
        }
    }

    boolean checkOverlapsBackup()
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
//                            compareDrawable.movement.set(compareDrawable.position.x - o2.position.x, compareDrawable.position.y - o2.position.y);
                            compareDrawable.movement.normalize();
                            do{
                                correctionVector.add(compareDrawable.movement);
                                compareDrawable.move();
//                                if(o2 instanceof Hostile) System.out.println(correctionVector.toString());
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

    private boolean checkOverlaps()
    {
        for(Drawable o1: landscape.objects)
        {
            if(!(o1 instanceof Ground)) {

                o1.move();
                if(isDrawableOutOfBounds(o1))
                {
                    Vector2D step = new Vector2D(o1.movement.x * 0.1, o1.movement.y * 0.1);
                    do{
                        o1.unmove();
                        o1.movement.subtract(step);
                        o1.move();
                    }while(isDrawableOutOfBounds(o1));
                    o1.unmove();
                }

                for(Drawable o2: landscape.objects)
                {
                    if(!o1.equals(o2) && o1.isNotPassable() && o2.isNotPassable() && !(o2 instanceof Ground))
                    {
                        if(compareBoolArrays(o1, o2))
                        {
                            Vector2D step1 = new Vector2D(o1.movement.x * 0.1, o1.movement.y * 0.1);
                            Vector2D step2 = new Vector2D(o2.movement.x * 0.1, o2.movement.y * 0.1);
                            o1.move();
                            o2.move();
                            do{
                                o1.unmove();
                                o1.movement.subtract(step1);
                                o1.move();

                                o2.unmove();
                                o2.movement.subtract(step2);
                                o2.move();
                            }while(compareBoolArrays(o1, o2));
                            o1.unmove();
                            o2.unmove();
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    private void checkProjectileHits(ArrayList<Projectile> projectileList)
    {
        for(Projectile projectile: projectileList)
        {
            if(isDrawableOutOfBounds(projectile)){
                projectile.lifeTime = 0;
            }

            for (Drawable drawable : landscape.objects) {
                if (drawable.isNotPassable() && !(drawable instanceof Player) && !(drawable instanceof Ground) && (projectile.lifeTime>0)) {
                    if (compareBoolArrays(projectile, drawable)) {
                        projectile.lifeTime = 0;
                        if(drawable.maxHealth != -1)
                        {
                            projectile.hitFlag = true;
                            drawable.curHealth -= projectile.damageOnHit;
                        }
//                        System.out.println(drawable + " was hit for " + projectile.damageOnHit + " Damage | Health = " + drawable.health);
                    }
                }
            }
        }
    }

    boolean compareBoolArraysBackup(Drawable d1, Drawable d2)
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
                                if(d2PositionXInt-d1PositionXInt + i >= d1.height-1
                                        || d2PositionYInt-d1PositionYInt + j >= d1.width-1) return false;

                                try{
                                    if(d1.gameImage.imageBoolArray[d2PositionXInt-d1PositionXInt + i][d2PositionYInt-d1PositionYInt + j] && d2.gameImage.imageBoolArray[i][j]) return true;

                                }
                                catch (ArrayIndexOutOfBoundsException ex){
                                   ex.printStackTrace();
                                }
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
                                if(d2PositionXInt-d1PositionXInt + i >= d1.height-1
                                        || d1PositionYInt-d2PositionYInt + j >= d2.width-1) return false;
                                try{
                                    if(d1.gameImage.imageBoolArray[d2PositionXInt-d1PositionXInt + i][j] && d2.gameImage.imageBoolArray[i][d1PositionYInt-d2PositionYInt + j]) return true;

                                }
                                catch (ArrayIndexOutOfBoundsException ex){
                                    ex.printStackTrace();
                                }
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
                                if(d2PositionYInt - d1PositionYInt + j >= d1.height-1
                                        || d1PositionXInt - d2PositionXInt + i >= d2.width-1) return false;
                                try{
                                    if(d1.gameImage.imageBoolArray[i][d2PositionYInt - d1PositionYInt + j] && d2.gameImage.imageBoolArray[d1PositionXInt - d2PositionXInt + i][j]) return true;

                                }
                                catch (ArrayIndexOutOfBoundsException ex){
                                    ex.printStackTrace();
                                }
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
                                if(d1PositionXInt-d2PositionXInt + i >= d2.height-1
                                        || d1PositionYInt-d2PositionYInt + j >= d2.width-1) return false;
                                try{
                                    if(d1.gameImage.imageBoolArray[i][j] && d2.gameImage.imageBoolArray[d1PositionXInt-d2PositionXInt + i][d1PositionYInt-d2PositionYInt + j]) return true;

                                }
                                catch (ArrayIndexOutOfBoundsException ex){
                                    ex.printStackTrace();
                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
    }

    private boolean compareBoolArrays(Drawable d1, Drawable d2)
    {
        int d1PositionXInt = (int) d1.position.x;
        int d1PositionYInt = (int) d1.position.y;
        int d2PositionXInt = (int) d2.position.x;
        int d2PositionYInt = (int) d2.position.y;


        boolean compareArrayD1[][] = new boolean[Landscape.WIDTH][Landscape.HEIGHT];
        boolean compareArrayD2[][] = new boolean[Landscape.WIDTH][Landscape.HEIGHT];


        for (int i = 0; i < d1.gameImage.imageBoolArray.length; i++){
            System.arraycopy(d1.gameImage.imageBoolArray[i], 0, compareArrayD1[i + d1PositionXInt], d1PositionYInt, d1.gameImage.imageBoolArray[i].length);
        }
        for (int i = 0; i < d2.gameImage.imageBoolArray.length; i++){
            System.arraycopy(d2.gameImage.imageBoolArray[i], 0, compareArrayD2[i + d2PositionXInt], d2PositionYInt, d2.gameImage.imageBoolArray[i].length);
        }

        // Debug

        for(int i = 0; i < Landscape.WIDTH; i++){
            for(int j = 0; j < Landscape.HEIGHT; j++){
                if(compareArrayD1[i][j] && compareArrayD2[i][j]){
//                    System.out.println("D1: \n");
//                    d1.gameImage.debugImageBoolArray();
//                    System.out.println("D2: \n");
//                    d2.gameImage.debugImageBoolArray();
//                    System.out.println("D1 Compare: \n");
//                    GameImage.debugImageBoolArray(compareArrayD1);
//                    System.out.println("D2 Compare: \n");
//                    GameImage.debugImageBoolArray(compareArrayD2);
//                    System.out.println("\nFinished!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDrawableOutOfBounds(Drawable drawable) {
        return (drawable.position.x < 0)
                || (drawable.position.x > Landscape.WIDTH - drawable.width)
                || (drawable.position.y < 0)
                || (drawable.position.y > Landscape.HEIGHT - drawable.height);
    }

}
