package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameArea localGameArea;

    public GamePanel(FlowLayout layout)
    {
        super(layout);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        localGameArea.paint(g);
    }

    void setLocalGameArea(GameArea remoteGameArea) {
        localGameArea = remoteGameArea;
    }




}
