package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameImage {

    public BufferedImage image;
    public File imagePath;
    boolean[][] imageBoolArray;

    public GameImage(File imagePath){
        this.imagePath = imagePath;
        try {
            image = ImageIO.read(new File("images/Player.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];

        readBoolArrayFromImage();
    }

    private void readBoolArrayFromImage()
    {
        int pixel;

        for(int i=0;i<image.getWidth();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                pixel = image.getRGB(i,j);
                if( (pixel>>24) == 0x00 )
                {
                    imageBoolArray[i][j] = true;
                }
                else
                {
                    imageBoolArray[i][j] = false;
                }
            }
        }
    }

    private void debugImageBoolArray()
    {
        for(int i=0;i<image.getWidth();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                System.out.println("x: " + i + " y: " + j + " -> " + imageBoolArray[i][j]);
            }
        }
    }

    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(image, x, y,null);
    }
}
