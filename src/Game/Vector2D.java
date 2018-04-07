package Game;

public class Vector2D {

    public float x;
    public float y;


    public Vector2D(){
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    void normalize() {
        float magnitude = magnitude();

        x = x / magnitude;
        y = y / magnitude;
    }

    void add(Vector2D vector) {
        x += vector.x;
        y += vector.y;
    }

    void subtract(Vector2D vector) {
        x -= vector.x;
        y -= vector.y;
    }

    void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
