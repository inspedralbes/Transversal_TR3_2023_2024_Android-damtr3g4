package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    public static Texture bgGameScreen;
    public static Texture peashooterTexture;
    public static TextureRegion[] peashooterDefaultMovement;
    public static Animation peashooterDefaultAnimated;
    public static Skin skin;
    public static void load(){
        bgGameScreen = new Texture(Gdx.files.internal("bgGameScreen.png"));
        peashooterTexture = new Texture(Gdx.files.internal("peashooter.png"));
        peashooterDefaultMovement = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            peashooterDefaultMovement[i] = new TextureRegion(peashooterTexture, i * 27, 2, 26, 30);
        }

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        // Crear la animación con las regiones de textura
        peashooterDefaultAnimated = new Animation<>(0.05f, peashooterDefaultMovement);
    }

    public static void dispose(){
        bgGameScreen.dispose();
        peashooterTexture.dispose();
    }
}
