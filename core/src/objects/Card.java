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
    private int width, height;
    private int idAgent;
    private Rectangle hitBoxCard;
    public Card(float x, float y, int width, int height, int idAgent) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        hitBoxCard = new Rectangle(x, y, width, height);
        this.idAgent = idAgent;

    }

    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(new Texture(Gdx.files.internal("atrevol.png")), position.x, position.y, width, height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getIdAgent() {
        return idAgent;
    }

    public Rectangle getHitBoxCard() {
        return hitBoxCard;
    }
}
