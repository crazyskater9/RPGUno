import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 18));
        g.setColor(Color.BLUE);
        g.drawString("Hallo Welt", 10,40);
    }
}

public class Main {


    public static void main(String[] args) {

        JFrame f = new JFrame("Titel");
        f.setVisible(true);
        f.setSize(600, 800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p = new MyPanel();

        f.add(p);


    }
}
