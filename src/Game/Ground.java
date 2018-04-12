package Game;

import java.awt.*;

public class Ground extends Environment {
    public Ground(int x, int y) {
        super(x, y, 20, 20, Color.WHITE, true);
    }

    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE, true);
    }

    public Ground(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color, true);
    }

}
