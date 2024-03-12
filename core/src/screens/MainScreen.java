package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import helpers.AssetManager;
import plants.vs.zombie.plantsVsZombie;

public class MainScreen implements Screen {
    private plantsVsZombie pVsZ;
    private TextButton btnPlaySolo;
    private TextButton btnPlay1v1;
    private TextButton btnConfig;
    private TextButton btnStore;
    private Table table;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public MainScreen(plantsVsZombie pVsZ){
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT, camera);
        stage = new Stage(viewport);

        table = new Table();
        btnPlaySolo = new TextButton("Solo", AssetManager.skin);
        btnPlay1v1 = new TextButton("1v1", AssetManager.skin);
        btnConfig = new TextButton("Configuracion", AssetManager.skin);
        btnStore = new TextButton("Tienda", AssetManager.skin);

        table.add(btnPlaySolo).width(200).height(60).pad(10);
        table.row();
        table.add(btnPlay1v1).width(200).height(60).pad(10);
        table.row();
        table.add(btnConfig).width(200).height(60).pad(10);
        table.row();
        table.add(btnStore).width(200).height(60).pad(10);

        btnPlaySolo.addListener(btnPlaySoloListener());
        btnPlay1v1.addListener(btnPlay1v1Listener());
        btnConfig.addListener(btnConfigListener());
        btnStore.addListener(btnStoreListener());

        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }
    private ClickListener btnPlaySoloListener(){
        return new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A GAME SCREEN");
                pVsZ.setScreen(new GameScreen(pVsZ));
            }
        };
    }
    private ClickListener btnPlay1v1Listener(){
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A GAME 1v1 SCREEN");
                //pVsZ.setScreen(new GameScreen(pVsZ));
            }
        };
    }
    private ClickListener btnConfigListener(){
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A CONFIG SCREEN");
                //pVsZ.setScreen(new GameScreen(pVsZ));
            }
        };
    }
    private ClickListener btnStoreListener(){
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A STORE SCREEN");
                //pVsZ.setScreen(new GameScreen(pVsZ));
            }
        };
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
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
}
