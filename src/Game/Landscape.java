package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Landscape {
    int width;
    int height;
    ArrayList<Drawable> objects;

    public Landscape(int width, int height, ArrayList<Drawable> objects) {
        this.height = height;
        this.width = width;
        this.objects = new ArrayList<Drawable>(objects);
    }

    public Landscape(String filename)
    {
        File file = new File(filename);
        int length = (int) file.length();
        System.out.println(length);
        byte[] bytes = ByteBuffer.allocate(length).array();

        FileInputStream fis;
        try{
            fis = new FileInputStream(file);
            fis.read(bytes);
            //System.out.println(bytes);

            byte[] arr = ByteBuffer.allocate(4).array();
            for(int i=0;i<4;i++)
            {
                arr[i] = bytes[i];
            }
            ByteBuffer wrapped = ByteBuffer.wrap(arr);
            int num = wrapped.getInt();

            System.out.println(num);



        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public boolean toFile(){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("levels/level1.map");
            byte[] bytes;
            bytes = ByteBuffer.allocate(4).putInt(width).array();
            fos.write(bytes);
            bytes = ByteBuffer.allocate(4).putInt(height).array();
            fos.write(bytes);

            for(Drawable d: objects) {
                if(d instanceof Player){
                    fos.write("PLAYER".getBytes());
                }
                else if(d instanceof Wall){
                    fos.write("WALL".getBytes());
                }
                else if(d instanceof Ground){
                    fos.write("GROUND".getBytes());
                    bytes = ByteBuffer.allocate(4).putInt(d.width).array();
                    fos.write(bytes);
                    bytes = ByteBuffer.allocate(4).putInt(d.height).array();
                    fos.write(bytes);
                }
                bytes = ByteBuffer.allocate(4).putInt((int)d.position.x).array();
                fos.write(bytes);
                bytes = ByteBuffer.allocate(4).putInt((int)d.position.y).array();
                fos.write(bytes);
            }

            fos.flush();
            fos.close();
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
