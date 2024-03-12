package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import plants.vs.zombie.plantsVsZombie;

public class AuthenticationScreen implements Screen {
    private plantsVsZombie pVsZ;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Table table;
    private TextButton btnLogin;
    public AuthenticationScreen(plantsVsZombie pVsZ){
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT, camera);
        stage = new Stage(viewport);

        table = new Table();

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
