package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 18));
        g.setColor(Color.BLUE);
        g.drawString("Hallo Welt", 10,40);
    }

}
