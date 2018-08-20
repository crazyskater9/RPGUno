package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameImage {

    public BufferedImage image;
    public String imagePath;
    boolean[][] imageBoolArray;

    public GameImage(String imagePath){
        this.imagePath = imagePath;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];

        readBoolArrayFromImage();
        //debugImageBoolArray();
    }

    public GameImage(GameImage gameImage){
        this.imagePath = gameImage.imagePath;
        this.image = gameImage.image;
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];
        readBoolArrayFromImage();
    }

    public GameImage(BufferedImage image) {
        this.imagePath = null;
        this.image = image;
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];
        readBoolArrayFromImage();
    }

    private void readBoolArrayFromImage()
    {
        int pixel;
        imageBoolArray = new boolean[image.getWidth()][image.getHeight()];

        for(int i=0;i<image.getWidth();i++)
        {
            for(int j=0;j<image.getHeight();j++)
            {
                pixel = image.getRGB(i,j);
                imageBoolArray[i][j] = !isTransparent(pixel);
            }
        }
    }

    public void debugImageBoolArray()
    {
        System.out.print(image.getSource() + "\n");
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

    public static void debugImageBoolArray(boolean[][] array)
    {
        for(int i=0;i<array[i].length;i++)
        {
            for(int j=0;j<array.length;j++)
            {
                if(array[j][i]) System.out.print(1);
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
        return (pixel >> 24) == 0x00;
    }

    public static BufferedImage getColoredImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < height ; j++) {
                image.setRGB(i,j,color.getRGB());
            }
        }

        return image;
    }
}
