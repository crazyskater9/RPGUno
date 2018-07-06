package Game;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameString {

    public Vector2D position;
    private Font stringFont;
    private String message;
    private Color stringColor;
    private int duration;
    private Graphics graphics;

    public GameString(String message, Vector2D position, Font stringFont, Color stringColor, int duration, Graphics graphics){
        this.message = message;
        this.position = new Vector2D(position);
        this.stringFont = stringFont;
        this.stringColor = stringColor;
        this.duration = duration;
        this.graphics = graphics,
    }

    private void drawString(){
        graphics.setFont(stringFont);
        graphics.setColor(stringColor);
        graphics.drawString(message, (int)position.x ,(int)position.y+(int)(duration*0.5));
        decreaseDuration();
    }

    private void decreaseDuration(){
        duration--;
    }
}
