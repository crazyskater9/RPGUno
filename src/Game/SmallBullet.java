package Game;

public class SmallBullet extends Projectile {



    public SmallBullet(Vector2D position, Vector2D movement){
        super(10,1,position,movement);
        debugValues();
    }

    private void debugValues(){
        System.out.println("---SmallBullet Debug Values---");
        System.out.println("lifetime = "+lifeTime);
        System.out.println("damageOnHit = "+damageOnHit);
        System.out.println("position.x = "+position.x);
        System.out.println("position.y = "+position.y);
        System.out.println("movement.x = "+movement.x);
        System.out.println("movement.y = "+movement.y);
        System.out.println("------------------------------");
    }
}
