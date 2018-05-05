package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Environment extends Drawable{

    Color color;

    public Environment() {
        super();
        position = new Vector2D(0,0);
        width = 0;
        height = 0;
        color = Color.BLACK;
        passable = false;
        gameImage = new GameImage(getColoredImage(width,height,color));
    }

    public Environment(Vector2D position, int width, int height, Color color, boolean passable) {
        super();
        this.position = new Vector2D(position);
        this.width = width;
        this.height = height;
        this.color = color;
        this.passable = passable;
        gameImage = new GameImage(getColoredImage(this.width,this.height,this.color));
    }

    public Environment(int x, int y, int width, int height, Color color, boolean passable) {
        super();
        this.position = new Vector2D(x,y);
        this.width = width;
        this.height = height;
        this.color = color;
        this.passable = passable;
        gameImage = new GameImage(getColoredImage(this.width,this.height,this.color));
    }

    public Environment(int x, int y, boolean passable, String imagePath) {
        super();
        this.position = new Vector2D(x,y);
        this.passable = passable;
        gameImage = new GameImage(imagePath);
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
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
