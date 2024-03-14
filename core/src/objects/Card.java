package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import helpers.AssetManager;

public class Card extends Actor {
    private Vector2 position;
    private float width, height;
    private Rectangle hitBoxCard;
    private boolean active;
    private float stateTime;

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setHitBoxCard(Rectangle hitBoxCard) {
        this.hitBoxCard = hitBoxCard;
    }

    public Card(float x, float y, float width, float height, boolean active) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.hitBoxCard = new Rectangle(position.x, position.y, width, height);
        this.active = active;
    }

    public void act(float delta) {
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        /*TextureRegion currentFrame = (TextureRegion) AssetManager.sunflowerDefaultAnimated.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);*/
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getHitBoxCard() {
        return hitBoxCard;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
