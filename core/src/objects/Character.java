package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor {
    private Vector2 position;
    private float width, height;
    private Rectangle hitBoxCard;
    private float stateTime;
    private boolean weapon;

    public Character(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.hitBoxCard = new Rectangle(x, y, width, height);
        weapon = false;
    }
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public Rectangle getHitBoxCard() {
        return hitBoxCard;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    public void setHitBoxCard(Rectangle hitBoxCard) {
        this.hitBoxCard = hitBoxCard;
    }
}
