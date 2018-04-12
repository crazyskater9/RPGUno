package Game;

import java.awt.*;

public class SmallBullet extends Projectile {

    public SmallBullet(Vector2D position, Vector2D movement){
        super(30,1,position,movement,3,3);
        //debugValues();
    }

    @Override
    protected void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.fillRect((int)position.x,(int)position.y,width,height);
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
