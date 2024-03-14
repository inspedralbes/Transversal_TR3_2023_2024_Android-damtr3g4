package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import helpers.AssetManager;

public class Sunflower extends Card{
    private float stateTime;

    public Sunflower(float x, float y, int width, int height, boolean active) {
        super(x, y, width, height, active);
    }

    public void act(float delta) {
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = (TextureRegion) AssetManager.sunflowerDefaultAnimated.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
    }
}
