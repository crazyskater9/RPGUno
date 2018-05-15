package Game;

import java.awt.*;

import static Game.GameImage.getColoredImage;

public class Environment extends Drawable{

    Color color;

    public Environment(int x, int y, int width, int height, Color color, boolean passable, int health) {
        super(new Vector2D(x,y), new Vector2D(), new GameImage(getColoredImage(width,height,color)), passable, health);
        this.color = color;
    }

    public Environment(int x, int y, boolean passable, int health, String imagePath) {
        super(new Vector2D(x,y), new Vector2D(), new GameImage(imagePath), passable, health);
    }

}
