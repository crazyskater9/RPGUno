package Game;

public class Player {

    Vector2D position;
    Vector2D speed;


        public Player() {
            position = new Vector2D(100,100);

            speed = new Vector2D(2, 0);

            position.add(speed);
        }
}
