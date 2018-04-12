package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameImage {

    public BufferedImage image;
    //public File imagePath;
    boolean[][] imageBoolArray;

    public GameImage(String imagePath){
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];

        readBoolArrayFromImage();
        //debugImageBoolArray();
    }

    public GameImage(BufferedImage image) {
        this.image = image;
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];
        readBoolArrayFromImage();
    }

    private void readBoolArrayFromImage()
    {
        int pixel;

        for(int i=0;i<image.getWidth();i++)
        {
            for(int j=0;j<image.getHeight();j++)
            {
                pixel = image.getRGB(i,j);
                if(isTransparent(pixel))
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
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                if(imageBoolArray[j][i]) System.out.print(1);
                else System.out.print(0);

                System.out.print(" ");
                //System.out.println("x: " + i + " y: " + j + " -> " + imageBoolArray[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(image, x, y,null);
    }

    private boolean isTransparent(int pixel) {
        if( (pixel>>24) == 0x00 ) return true;
        else return false;
    }
}
