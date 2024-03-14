package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import helpers.AssetManager;
import helpers.InputHandler;
import objects.Card;
import objects.Peashooter;
import objects.ScrollHandler;
import plants.vs.zombie.plantsVsZombie;
import utils.Settings;

public class GameScreen implements Screen {
    private plantsVsZombie pVsZ;
    private SpriteBatch batch;
    private Peashooter peashooter;
    private OrthographicCamera camera;
    private Stage stage;
    public GameScreen(plantsVsZombie pVsZ) {
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT, camera);
        stage = new Stage(viewport);

        peashooter = new Peashooter(240, 50, 46, 60);
        stage.addActor(peashooter);
        Gdx.input.setInputProcessor(new InputHandler(this));
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        batch.draw(AssetManager.bgGameScreen, 0,0, plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        batch.end();
        stage.draw();
        stage.act(delta);
    }



    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public plantsVsZombie getpVsZ() {
        return pVsZ;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Peashooter getPeashooter() {
        return peashooter;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStage() {
        return stage;
    }

}
