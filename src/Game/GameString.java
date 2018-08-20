package Game;

import java.awt.*;

public class GameString {

    public Vector2D position;
    private Font stringFont;
    private String message;
    private Color stringColor;
    public int duration;
    private Graphics graphics;

    public GameString(String message, Vector2D position, Font stringFont, Color stringColor, int duration){
        this.message = message;
        this.position = new Vector2D(position);
        this.stringFont = stringFont;
        this.stringColor = stringColor;
        this.duration = duration;
    }

    public void drawString(Graphics graphics){
        stringFont = stringFont.deriveFont(10.0f);
        graphics.setFont(stringFont);
        graphics.setColor(stringColor);
        graphics.drawString(message, GameData.toScreenX((int)position.x) ,GameData.toScreenY((int)position.y+(int)(duration*0.6)));
        decreaseDuration();
    }

    private void decreaseDuration(){
        duration--;
    }
}
