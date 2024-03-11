package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

    public static Texture peashooterTexture;
    public static TextureRegion[] peashooterDefaultMovement;
    public static Animation peashooterDefaultAnimated;
    public static void load(){
        peashooterTexture = new Texture(Gdx.files.internal("peashooter.png"));
        for(int i = 0; i <= 189; i += 27){
            peashooterDefaultMovement[i] = new TextureRegion(peashooterTexture,i, 2, 26, 30);
        }

        peashooterDefaultAnimated = new Animation(0.05f, peashooterDefaultMovement);
    }

    public static void dispose(){

    }
}
