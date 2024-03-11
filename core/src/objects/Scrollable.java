package objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scrollable extends Actor {
    protected Vector2 position;
    protected float velocity;
    protected float width;
    protected float height;
    protected boolean leftOfScreen;

    public Scrollable(float x, float y, float width, float height, float velocity) {
        position = new Vector2(x, y);
        this.velocity = velocity;
        this.width = width;
        this.height = height;
        leftOfScreen = false;

    }

    public void reset(float newX) {
        position.x = newX;
        leftOfScreen = false;
    }

    public boolean isLeftOfScreen() {
        return leftOfScreen;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
