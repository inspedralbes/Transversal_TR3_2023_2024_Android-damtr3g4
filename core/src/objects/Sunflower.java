package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import helpers.AssetManager;

public class Sunflower extends CardPlant {
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
        if(super.isActive()){
            TextureRegion currentFrame = (TextureRegion) AssetManager.sunflowerDefaultAnimated.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
        }else{
            Texture t = new Texture(Gdx.files.internal("atrevol.png"));
            batch.draw(t, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
        }
    }
}
