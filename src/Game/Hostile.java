package Game;

import java.util.ArrayList;
import java.util.Iterator;

public class Hostile extends NPC {

    ArrayList<Vector2D> movementPath;
    Iterator<Vector2D> iterator;

    public Hostile(Vector2D position, Vector2D movement, ArrayList<Vector2D> movementPath) {
        super(new Vector2D(position), new Vector2D(movement), "images/enemy1.png", 10);

        this.movementPath = new ArrayList<Vector2D>();
        for(Vector2D v : movementPath) {
            this.movementPath.add(v.add(position));
        }

        iterator = movementPath.iterator();
    }

    public void move() {
        if(iterator.hasNext()) {
            position = iterator.next();
        }
        else{
            iterator = movementPath.iterator();
            position = iterator.next();
        }
    }
}
