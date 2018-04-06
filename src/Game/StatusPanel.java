package Game;

import javax.swing.*;
import java.awt.*;
import java.time.Clock;

public class StatusPanel extends JPanel {

    public StatusPanel(FlowLayout layout)
    {
        super(layout);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 14));
        g.setColor(Color.BLACK);
        Clock clock = Clock.systemDefaultZone();
        g.drawString(String.valueOf(clock.instant()), 10,14);
    }
}
