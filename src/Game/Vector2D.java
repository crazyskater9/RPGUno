package Game;

class Vector2D {

    float x;
    float y;


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

    Vector2D subtract(Vector2D vector) {
        x -= vector.x;
        y -= vector.y;
        return this;
    }

    Vector2D multiply(int factor) {
        x *= factor;
        y *= factor;
        return this;
    }

    void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
