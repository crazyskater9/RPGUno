package Game;

import java.awt.*;

public class Ground extends Environment {
    public Ground(int x, int y) {
        super(x, y, 20, 20, Color.GRAY, true, -1);
    }

    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height, Color.GRAY, true, -1);
    }

    public Ground(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color, true, -1);
    }

    @Override
    public String toString() {
        return "Ground (" + position.x + ", " + position.y + ")";
    }
}
