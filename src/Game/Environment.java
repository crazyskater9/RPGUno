package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Environment extends Drawable{

    Color color;

    public Environment() {
        super(new Vector2D(), new Vector2D(), new GameImage(), false, -1);
        color = Color.BLACK;
        gameImage = new GameImage(getColoredImage(width,height,color));
    }

    public Environment(Vector2D position, int width, int height, Color color, boolean passable, int health) {
        super(position, new Vector2D(), new GameImage(), passable, health);
        this.width = width;
        this.height = height;
        this.color = color;
        gameImage = new GameImage(getColoredImage(this.width,this.height,this.color));
    }

    public Environment(int x, int y, int width, int height, Color color, boolean passable, int health) {
        super(new Vector2D(x,y), new Vector2D(), new GameImage(), passable, health);
        this.width = width;
        this.height = height;
        this.color = color;
        gameImage = new GameImage(getColoredImage(this.width,this.height,this.color));
    }

    public Environment(int x, int y, boolean passable, int health, String imagePath) {
        super(new Vector2D(x,y), new Vector2D(), new GameImage(imagePath), passable, health);
    }

    BufferedImage getColoredImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < height ; j++) {
                image.setRGB(i,j,color.getRGB());
            }
        }

        return image;
    }

}
