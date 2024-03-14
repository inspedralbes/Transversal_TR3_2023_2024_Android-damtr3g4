package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import helpers.AssetManager;

public class Peashooter extends Card {
    // Diferents posicions de l'Spacecraft: recta, pujant i baixant
    private float stateTime;

    public Peashooter(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    public void act(float delta) {
        stateTime += delta;
        // Movem l'Spacecraft depenent de la direcció controlant que no surti de la pantalla
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = (TextureRegion) AssetManager.peashooterDefaultAnimated.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, super.getPosition().x, super.getPosition().y, super.getWidth(), super.getHeight());
    }
}
