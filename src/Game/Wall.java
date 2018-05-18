package Game;

public class Wall extends Environment {

    public Wall(int x, int y) {
        super(x, y, false, -1,"images/Wall.png");
    }

    public Wall(int x, int y, int health) {
        super(x, y, false, health,"images/Wall.png");
    }

    @Override
    public String toString() {
        return "Wall (" + position.x + ", " + position.y + ")";
    }
}
