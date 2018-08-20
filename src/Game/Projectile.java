package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;

public class Projectile extends Drawable{

    public int lifeTime;
    public int damageOnHit;
    public boolean hitFlag;

    public Projectile(int lifeTime, int damageOnHit, Vector2D position, Vector2D movement, String imagePath)
    {
        super(position, movement, new GameImage(imagePath), false, -1);
        this.lifeTime = lifeTime;
        this.damageOnHit = damageOnHit;
        this.hitFlag = false;
    }

    public void paint(Graphics g) {
        decrementLifeTime();

        if(lifeTime>0 && !hitFlag )
        {
            move();
            double rotationRequired = Math.toRadians (-movement.angle());
            double locationX = gameImage.image.getWidth() / 2;
            double locationY = gameImage.image.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            // Drawing the rotated image at the required drawing locations
            g.drawImage(op.filter(gameImage.image, null), GameData.WIDTH/2 + (int)position.x - (int)GameData.landscapeToPlayerVector.x, GameData.HEIGHT/2 + (int)position.y - (int)GameData.landscapeToPlayerVector.y, null);
        }
    }

    private void decrementLifeTime()
    {
        lifeTime--;
    }
}
