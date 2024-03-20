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
    public static Texture sunflowerTexture;
    public static TextureRegion[] sunflowerDefaultMovement;
    public static Animation sunflowerDefaultAnimated;
    public static Texture shinChanTexture;
    public static TextureRegion shinChanDefault;
    public static Texture pitufuTexture;
    public static TextureRegion pitufuDefault;
    public static Skin skin;
    public static void load(){
        shinChanTexture = new Texture(Gdx.files.internal("shinchan.png"));
        pitufuTexture = new Texture(Gdx.files.internal("pitufo.png"));
        bgGameScreen = new Texture(Gdx.files.internal("bgGameScreen.png"));
        peashooterTexture = new Texture(Gdx.files.internal("peashooter.png"));

        pitufuDefault = new TextureRegion(pitufuTexture, 49, 76, 21, 28);
        shinChanDefault = new TextureRegion(shinChanTexture, 12, 33, 21, 28);

        peashooterDefaultMovement = new TextureRegion[8];
        for(int i = 0; i < 8; i++) {
            peashooterDefaultMovement[i] = new TextureRegion(peashooterTexture, i * 27, 2, 26, 30);
        }

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        // Crear la animación con las regiones de textura
        peashooterDefaultAnimated = new Animation<>(0.15f, peashooterDefaultMovement);


        sunflowerTexture = new Texture(Gdx.files.internal("sunflower.png"));
        sunflowerDefaultMovement = new TextureRegion[6];
        for (int i = 0; i < 6; i++){
            sunflowerDefaultMovement[i] = new TextureRegion(sunflowerTexture, i*31, 38, 30, 32);
        }

        sunflowerDefaultAnimated = new Animation<>(0.15f, sunflowerDefaultMovement);
    }

    public static void dispose(){
        bgGameScreen.dispose();
        peashooterTexture.dispose();
    }
}