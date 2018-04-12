package Game;

import java.awt.*;

public class Projectile extends Drawable{

    public int lifeTime;
    public int damageOnHit;
    public Vector2D movement;

    public Projectile(int lifeTime, int damageOnHit, Vector2D position, Vector2D movement, int width, int height)
    {
        super();
        this.lifeTime = lifeTime;
        this.damageOnHit = damageOnHit;
        this.position = new Vector2D(position);
        this.movement = new Vector2D(movement);
        this.width = width;
        this.height = height;
    }

    protected void paint(Graphics g) {
        move();
        decrementLifeTime();
    }

    protected void move()
    {
        position.add(movement);
    }

    protected void decrementLifeTime()
    {
        if(lifeTime>0) {
            lifeTime--;
        }
    }

    public Vector2D getPlayerPosition(Vector2D playerPosition)
    {
        return playerPosition;
    }
}
