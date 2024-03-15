package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import helpers.AssetManager;

public class Peashooter extends CardPlant {
    private float stateTime;
    public Peashooter(float x, float y, int width, int height, boolean active) {
        super(x, y, width, height, active);
    }

    public void act(float delta){
        stateTime += delta;
        if(super.getPosition().x == 0.0 && super.getPosition().y == 0.0){
            System.out.println("pase para activarme");
            super.setActive(true);
        }else{}
    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        if(super.isActive()){
            TextureRegion currentFrame = (TextureRegion) AssetManager.peashooterDefaultAnimated.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
        }else{
            Texture t = new Texture(Gdx.files.internal("atrevol.png"));
            batch.draw(t, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
        }

    }
}
