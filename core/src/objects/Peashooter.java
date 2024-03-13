package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import helpers.AssetManager;

public class Peashooter extends Actor {
    // Diferents posicions de l'Spacecraft: recta, pujant i baixant
    public static final int PEASHOOTER_STRAIGHT = 0;
    public static final int PEASHOOTER_DIE = 1;
    public static final int PEASHOOTER_SHOOT = 2;

    // Paràmetres de l'Spacecraft
    private Vector2 position;
    private int width, height;
    private int direction;
    private float stateTime;

    public Peashooter(float x, float y, int width, int height) {

        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem l'Spacecraft a l'estat normal
        direction = PEASHOOTER_STRAIGHT;

    }

    public void act(float delta) {
        stateTime += delta;
        // Movem l'Spacecraft depenent de la direcció controlant que no surti de la pantalla
        switch (direction) {
            case PEASHOOTER_DIE:
                break;
            case PEASHOOTER_SHOOT:
                break;
            case PEASHOOTER_STRAIGHT:
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = (TextureRegion) AssetManager.peashooterDefaultAnimated.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);
    }
}
