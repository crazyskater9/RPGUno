package Game;

import java.awt.*;

public abstract class Drawable {
    Vector2D position;
    int width;
    int height;
    GameImage gameImage;

    void paint(Graphics g) {gameImage.paint(g,(int)position.x,(int)position.y);}
}
