package Game;

public class NPC extends Drawable {

    public NPC() {
        super();
    }

    public NPC(Vector2D position, Vector2D movement, String imagePath, int health) {
        super(new Vector2D(position), new Vector2D(movement), new GameImage(imagePath), false, health);
    }
}
