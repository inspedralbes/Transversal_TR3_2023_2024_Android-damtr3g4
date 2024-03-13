package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import helpers.AssetManager;
import plants.vs.zombie.plantsVsZombie;

public class AuthenticationScreen implements Screen {
    private plantsVsZombie pVsZ;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Table table;
    private TextButton btnLogin;
    private TextField userTextField;
    private TextField passTextField;

    public AuthenticationScreen(plantsVsZombie pVsZ){
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT, camera);
        stage = new Stage(viewport);

        table = new Table();
        btnLogin = new TextButton("Login", AssetManager.skin);
        userTextField = new TextField("", AssetManager.skin);
        passTextField = new TextField("", AssetManager.skin);
        passTextField.setPasswordCharacter('*'); // Reemplaza '*' con cualquier carácter que desees mostrar como reemplazo
        passTextField.setPasswordMode(true);

        table.add(userTextField).width(200).height(60).pad(10);
        table.row();
        table.add(passTextField).width(200).height(60).pad(10);
        table.row();
        table.add(btnLogin).width(200).height(60).pad(10);

        btnLogin.addListener(btnLoginListener());

        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);
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

    private ClickListener btnLoginListener(){
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A MAIN SCREEN");
                pVsZ.setScreen(new MainScreen(pVsZ));
            }
        };
    }
}
