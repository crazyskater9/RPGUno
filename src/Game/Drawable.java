package Game;

import java.awt.*;

public class Drawable {
    public Vector2D position;
    public Vector2D movement;
    public int width;
    public int height;
    public GameImage gameImage;
    protected boolean passable;
    public int maxHealth;
    public int curHealth;
    private LifeBar lifeBar;

    public Drawable(){
        this.movement = new Vector2D();
        this.lifeBar = new LifeBar();
    }

    public Drawable(Drawable drawable){
        this.position = new Vector2D(drawable.position);
        this.movement = new Vector2D(drawable.movement);

        if(drawable.gameImage.imagePath != null) this.gameImage = new GameImage(drawable.gameImage.imagePath);
        else this.gameImage = new GameImage(GameImage.getColoredImage(drawable.width, drawable.height, Color.BLACK));

        this.width = drawable.width;
        this.height = drawable.height;
        this.passable = drawable.passable;
        this.lifeBar = new LifeBar();
    }

    public Drawable(Vector2D position, Vector2D movement, GameImage gameImage, boolean passable, int health) {
        this.position = new Vector2D(position);
        this.movement = new Vector2D(movement);
        this.gameImage = new GameImage(gameImage);
        this.width = gameImage.image.getWidth();
        this.height = gameImage.image.getHeight();
        this.passable = passable;
        this.maxHealth = health;
        this.curHealth = health;
        this.lifeBar = new LifeBar();
    }

    public void paint(Graphics g) {
        gameImage.paint(g,GameData.WIDTH/2 + (int)position.x - (int)GameData.landscapeToPlayerVector.x, GameData.HEIGHT/2 + (int)position.y - (int)GameData.landscapeToPlayerVector.y);
        lifeBar.drawLifeBar(g,this);
    }

    boolean isPassable(){return passable;}

    boolean isNotPassable(){return !passable;}

    void setPassable(boolean x) {passable = x;}

    public void move() {
        position.add(movement);
    }

    public String toString() {
        return "Drawable (" + position.x + ", " + position.y + ")";
    }

}
