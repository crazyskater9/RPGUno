package Game;

public class Projectile {

    public int lifeTime;
    public int damageOnHit;
    public Vector2D position;
    public Vector2D movement;

    public Projectile(int lifeTime, int damageOnHit, Vector2D position, Vector2D movement)
    {
        this.lifeTime = lifeTime;
        this.damageOnHit = damageOnHit;
        this.position = position;
        this.movement = movement;
    }

    public void move()
    {
        position.add(movement);
    }

    public Vector2D getPlayerPosition(Vector2D playerPosition)
    {
        return playerPosition;
    }
}
