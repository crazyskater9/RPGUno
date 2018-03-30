package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameArea game;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        game.update();


    }

    public GamePanel(){
        game = new GameArea();
    }

}
