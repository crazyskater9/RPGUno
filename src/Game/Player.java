package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Player extends Drawable{

    Vector2D movement;
    int curSpeed;
    int maxSpeed;
    List<Projectile> projectileList;


    public Player() {
        super();
        position = new Vector2D(400 ,300);
        movement = new Vector2D(0,0);
        curSpeed = 0;
        maxSpeed = 10;
        projectileList = new ArrayList<Projectile>();
        gameImage = new GameImage("images/Player.png");
        width = gameImage.image.getWidth();
        height = gameImage.image.getHeight();
    }

    public Player(Player player) {
        super();
        this.position = new Vector2D(player.position.x ,player.position.y);
        this.movement = new Vector2D(player.movement.x,player.movement.y);
        this.gameImage = new GameImage("images/Player.png");
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
    }

    void paint(Graphics g) {

        checkBorders();
        shoot();
        paintAndCheckProjectiles(g);
        super.paint(g);
    }

    public void move() {

        position.add(movement);
        //System.out.println("X: " + position.x + " | Y: " + position.y);
    }

    void setMovement(Set<Character> keysPressed) {

        movement.set(0,0);

        if(keysPressed.contains('w')) movement.y -= 1;

        if(keysPressed.contains('a')) movement.x -= 1;

        if(keysPressed.contains('s')) movement.y += 1;

        if(keysPressed.contains('d')) movement.x += 1;

        if((movement.x != 0 || movement.y != 0) && curSpeed <= maxSpeed) curSpeed++;
        else if(curSpeed > 0) curSpeed--;

        movement.normalize().multiply(curSpeed);
    }

    private void checkBorders() {
        if(position.x < 0) position.x = 0;
        if(position.x > GameData.WIDTH - width) position.x = GameData.WIDTH - width;
        if(position.y < 0) position.y = 0;
        if(position.y > GameData.HEIGHT - height) position.y = GameData.HEIGHT - height;
    }

    void checkEnvironment(ArrayList<Environment> objects) {
        for(Environment e: objects) {
            if(e.isNotPassable()) {
                if(position.y <= e.position.y + e.height && position.y + height >= e.position.y) {
                    if(position.x + movement.x <= e.position.x + e.width && position.x >e.position.x && movement.x < 0) movement.x = 0;
                    else if(position.x + movement.x + width >= e.position.x && position.x < e.position.x && movement.x > 0) movement.x = 0;
                }

                if(position.x <= e.position.x + e.width && position.x + width >= e.position.x) {
                    if(position.y + movement.y <= e.position.y + e.height && position.y >e.position.y && movement.y < 0) movement.y = 0;
                    else if(position.y + movement.y + height >= e.position.y && position.y < e.position.y && movement.y > 0) movement.y = 0;
                }
            }

        }
    }
    void shoot(){
        if((GameData.activePanel instanceof GamePanel) && (GameData.clickedMouseButton != 0))
        {
            GameData.clickedMouseButton = 0;

            Vector2D direction = new Vector2D(GameData.mouseX-position.x-gameImage.image.getWidth()/2,GameData.mouseY-position.y-gameImage.image.getHeight()/2);
            direction.normalize().multiply(10);

            Vector2D correctedPosition = new Vector2D(position);
            correctedPosition.x = correctedPosition.x + width / 2;
            correctedPosition.y = correctedPosition.y + height / 2;

            Projectile projectileToAdd = new SmallBullet(correctedPosition,direction);
            projectileToAdd.position.x -= projectileToAdd.gameImage.image.getWidth()/2;
            projectileToAdd.position.y -= projectileToAdd.gameImage.image.getHeight()/2;

            projectileList.add(projectileToAdd);
        }
    }

    void paintAndCheckProjectiles(Graphics g){
        for (Iterator<Projectile> iterator = projectileList.iterator(); iterator.hasNext();) {
            Projectile projectile = iterator.next();
            if(projectile.lifeTime == 0)
            {
                iterator.remove();
            }
            else
            {
                projectile.paint(g);
            }
        }
    }

}
