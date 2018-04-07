package Game;

import java.awt.*;

public class Wall extends Environment {

    public Wall(int x, int y) {
        super(x, y, 20, 20, Color.DARK_GRAY, false);
    }

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height, Color.DARK_GRAY, false);
    }

    public Wall(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color, false);
    }

    @Override
    void paint(Graphics g) {
        g.setColor(color);
        g.fillRect((int)position.x, (int)position.y, width, height);
    }
}
