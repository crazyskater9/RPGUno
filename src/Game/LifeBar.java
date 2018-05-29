package Game;

import java.awt.*;

public class LifeBar {

    private static final int BARHEIGHT = 6;
    private static final int BARWIDTH = 24;

    public void drawLifeBar(Graphics graphics, Drawable drawable){
        float healthPercentage;
        int positionX = GameData.WIDTH/2 + (int)drawable.position.x - (int)GameData.landscapeToPlayerVector.x + drawable.width/2 - BARWIDTH/2 + 1;
        int positionY = GameData.HEIGHT/2 + (int)drawable.position.y - 10 - (int)GameData.landscapeToPlayerVector.y;

        if((drawable.maxHealth != -1) && (drawable.curHealth != drawable.maxHealth))
        {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(positionX - 1, positionY - 1, BARWIDTH, BARHEIGHT);
            healthPercentage = (float)drawable.curHealth / (float)drawable.maxHealth;

            if(healthPercentage > 0.5) graphics.setColor(Color.GREEN);
            else if(healthPercentage > 0.3) graphics.setColor(Color.YELLOW);
            else if(healthPercentage > 0.1) graphics.setColor(Color.ORANGE);
            else graphics.setColor(Color.RED);

            graphics.fillRect(positionX, positionY, (int)((BARWIDTH-1)*healthPercentage), BARHEIGHT-1);

            graphics.setColor(Color.GRAY);
            graphics.fillRect(positionX + (int)((BARWIDTH-1)*healthPercentage) , positionY, (int)((BARWIDTH-1)*(1 - healthPercentage)) + 1, BARHEIGHT-1);
        }

    }
}
