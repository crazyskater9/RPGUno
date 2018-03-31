package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameArea localGameArea;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        localGameArea.paint(g);
        localGameArea.debug_flag = !localGameArea.debug_flag;

    }

    void setLocalGameArea(GameArea remoteGameArea) {
        localGameArea = remoteGameArea;
    }




}
