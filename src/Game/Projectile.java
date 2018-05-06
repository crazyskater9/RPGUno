package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Projectile extends Drawable{

    public int lifeTime;
    public int damageOnHit;

    public Projectile(int lifeTime, int damageOnHit, Vector2D position, Vector2D movement, String imagePath)
    {
        super();
        this.lifeTime = lifeTime;
        this.damageOnHit = damageOnHit;
        this.position = new Vector2D(position);
        this.movement = new Vector2D(movement);
        this.gameImage = new GameImage(imagePath);
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
        passable = false;
    }

    protected void paint(Graphics g) {
        move();
        decrementLifeTime();

        double rotationRequired = Math.toRadians (-movement.angle());
        double locationX = gameImage.image.getWidth() / 2;
        double locationY = gameImage.image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(gameImage.image, null), GameData.WIDTH/2 + (int)position.x - (int)GameData.middleOfScreenPosition.x, GameData.HEIGHT/2 + (int)position.y - (int)GameData.middleOfScreenPosition.y, null);
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
