import javax.swing.*;
import java.awt.*;
import Game.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Titel");
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p = new GamePanel();

        frame.add(p);


    }
}
