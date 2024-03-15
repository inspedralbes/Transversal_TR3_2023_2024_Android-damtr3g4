package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import helpers.AssetManager;

public class Pitufo extends Actor {
    private int id;
    private Vector2 position;
    private float width, height;
    private Rectangle hitBoxCard;
    private float stateTime;
    private boolean weapon;

    public int getId() {
        return id;
    }

    public Pitufo(float x, float y, float width, float height) {
        id = 1;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.hitBoxCard = new Rectangle(x, y, width, height);
        weapon = false;
    }

    public void act(float delta) {
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.pitufuDefault, position.x, position.y, width, height);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    public Rectangle getHitBoxCard() {
        return hitBoxCard;
    }

    public void setHitBoxCard(Rectangle hitBoxCard) {
        this.hitBoxCard = hitBoxCard;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }
}
