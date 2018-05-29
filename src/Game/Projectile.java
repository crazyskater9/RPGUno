package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;

public class Projectile extends Drawable{

    public int lifeTime;
    public int onHitAnimationTime;
    public int damageOnHit;
    public boolean hitFlag;

    public Projectile(int lifeTime, int damageOnHit, Vector2D position, Vector2D movement, String imagePath)
    {
        super(position, movement, new GameImage(imagePath), false, -1);
        this.lifeTime = lifeTime;
        this.damageOnHit = damageOnHit;
        this.hitFlag = false;
        this.onHitAnimationTime = 60;
    }

    public void paint(Graphics g) {
        decrementLifeTime();

        if(lifeTime>0)
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
        else if(hitFlag == true)
        {
            onHitAnimationTime--;
            drawOnHitDamage(g);
        }
    }

    protected void decrementLifeTime()
    {
        lifeTime--;
    }

    public void drawOnHitDamage(Graphics graphics)
    {
        Font hitFont;
        int positionX = GameData.WIDTH/2 + (int)position.x - (int)GameData.landscapeToPlayerVector.x;
        int positionY = GameData.HEIGHT/2 + (int)position.y - 10 - (int)GameData.landscapeToPlayerVector.y;
        try{
            hitFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/PressStart2P-Regular.ttf"));

            hitFont = hitFont.deriveFont(10.0f);
            graphics.setFont(hitFont);
            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(damageOnHit),positionX,positionY+(int)(lifeTime*0.5));
        }
        catch (IOException |FontFormatException e)
        {
            System.out.print(e);
        }


    }

    public Vector2D getPlayerPosition(Vector2D playerPosition)
    {
        return playerPosition;
    }
}
