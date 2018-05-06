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
        objects = new ArrayList<Drawable>();
        File file = new File(filename);
        int length = (int) file.length();
        System.out.println(length);
        byte[] bytes = ByteBuffer.allocate(length).array();
        ArrayList<String> objectTypeList = getObjectTypeList();
        int indexForObjectTypeSearch = 0;
        boolean escapeFlag = false;

        FileInputStream fis;
        try{
            fis = new FileInputStream(file);
            fis.read(bytes);
            //System.out.println(bytes);

            byte[] arr = ByteBuffer.allocate(4).array();
            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
            ByteBuffer wrapped = ByteBuffer.wrap(arr);
            width = wrapped.getInt();
            indexForObjectTypeSearch += 4;

            arr = ByteBuffer.allocate(4).array();
            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
            wrapped = ByteBuffer.wrap(arr);
            height = wrapped.getInt();
            indexForObjectTypeSearch += 4;

            String compareObjectTypeString;

            while(indexForObjectTypeSearch<length)
            {
                for(String s: objectTypeList)
                {
                    escapeFlag = true;
                    arr = ByteBuffer.allocate(s.length()).array();
                    System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,s.length());
                    compareObjectTypeString = new String(arr);

                    if(s.equals(compareObjectTypeString))
                    {
                        indexForObjectTypeSearch += s.length();
                        if(s.equals("PLAYER"))
                        {
                            Vector2D position = new Vector2D();
                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.x = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.y = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            objects.add(new Player(position));
                            GameData.middleOfScreenPosition = new Vector2D(position);
                        }
                        else if(s.equals("WALL"))
                        {
                            Vector2D position = new Vector2D();
                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.x = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.y = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            objects.add(new Wall((int) position.x,(int) position.y));
                        }
                        else if(s.equals("GROUND"))
                        {
                            int width, height;
                            Vector2D position = new Vector2D();

                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            width = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            height = wrapped.getInt();
                            indexForObjectTypeSearch += 4;


                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.x = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            arr = ByteBuffer.allocate(4).array();
                            System.arraycopy(bytes,indexForObjectTypeSearch,arr,0,4);
                            wrapped = ByteBuffer.wrap(arr);
                            position.y = wrapped.getInt();
                            indexForObjectTypeSearch += 4;

                            objects.add(new Ground((int) position.x,(int) position.y, width, height));
                        }
                        escapeFlag = false;
                        if(indexForObjectTypeSearch>=length)break;
                    }
                }
                if(escapeFlag == true)
                {
                    System.out.println("Error while converting File-Structure!!!");
                    break;
                }
            }

            System.out.println(width);
            System.out.println(height);



        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private ArrayList<String> getObjectTypeList(){
        ArrayList<String> objectTypeList = new ArrayList<String>();
        objectTypeList.add("PLAYER");
        objectTypeList.add("WALL");
        objectTypeList.add("GROUND");

        return objectTypeList;
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
