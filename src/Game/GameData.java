package Game;

import javax.swing.*;

public class GameData {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    public static int mouseX;
    public static int mouseY;
    public static int clickedMouseButton;
    public static JPanel activePanel;

    public static Vector2D landscapeToPlayerVector;

    public static int ONHITTEXTDURATION = 40;

    public static int toScreenX(int x){
        return GameData.WIDTH/2 + x - (int)GameData.landscapeToPlayerVector.x;
    }

    public static int toScreenY(int y){
        return GameData.HEIGHT/2 + y - (int)GameData.landscapeToPlayerVector.y;
    }
}
