package Game;

public class Vector2D {

    public float x;
    public float y;


    Vector2D(){
        x = 0;
        y = 0;
    }

    Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    Vector2D(Vector2D vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    float angle() {
        if(y>0)
            return -(float) ((float) (Math.acos(x/magnitude()))*180.0/Math.PI);
        else
            return (float) ((float) (Math.acos(x/magnitude()))*180.0/Math.PI);
    }

    Vector2D normalize() {
        float magnitude = magnitude();
        if(magnitude > 0) {
            x = x / magnitude;
            y = y / magnitude;
        }
        return this;
    }

    Vector2D add(Vector2D vector) {
        x += vector.x;
        y += vector.y;
        return this;
    }

    Vector2D substract(Vector2D vector) {
        x -= vector.x;
        y -= vector.y;
        return this;
    }

    Vector2D multiply(float factor) {
        x *= factor;
        y *= factor;
        return this;
    }

    void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    void set(Vector2D vector2D) {
        this.x = vector2D.x;
        this.y = vector2D.y;
    }

    @Override
    public String toString() {
        return "X: " + x + " | Y: " + y;
    }
}
