package screens;

import static helpers.AssetManager.socket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import helpers.AssetManager;
import io.socket.emitter.Emitter;
import objects.User;

public class LobbyScreen implements Screen {
    private MyGdxGame game;
    private int idGame;
    private String password;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ArrayList<User> users;
    private Table table;
    private TextButton btnPlay;
    private Label passwordText;
    public LobbyScreen (MyGdxGame game, int idGame, String password){
        this.game = game;
        this.idGame = idGame;
        this.password = password;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);
        stage = new Stage(viewport);

        users = new ArrayList<>();

        DataGame dataGame = new DataGame(idGame, "waiting");
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonData = json.toJson(dataGame);
        table = new Table();
        btnPlay = new TextButton("Jugar", AssetManager.skin);
        passwordText = new Label(password, AssetManager.skin);

        table.add(passwordText).width(200).height(60).pad(10);
        table.row();
        table.add(btnPlay).width(200).height(60).pad(10);

        btnPlay.addListener(btnPlayListener());

        socket.emit("getUsersInGame", jsonData);
        socket.on("usersInGame", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    // Obtén el JSON de usuarios
                    String jsonUsers = args[0].toString();

                    // Convierte el JSON a JSONArray
                    JSONArray jsonArray = new JSONArray(jsonUsers);

                    // Crea un ArrayList para almacenar los usuarios

                    // Itera sobre el JSONArray para extraer los datos de cada usuario
                    System.out.println(jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Extrae los datos del usuario
                        String correo = jsonObject.getString("correo");
                        String usuario = jsonObject.getString("usuario");
                        int id = jsonObject.getInt("id");

                        // Crea un objeto User y agrégalo al ArrayList
                        User user = new User(i*150+150,100,100,200,correo, usuario, id);
                        users.add(user);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.setFillParent(true); // Para que el grupo ocupe todo el tamaño del stage
        verticalGroup.addActor(table); // Agregar el Table al VerticalGroup
        // Agregar los actores User al VerticalGroup
        for (User user : users) {
            verticalGroup.addActor(user);
        }
        // Agregar el VerticalGroup al Stage
        stage.addActor(verticalGroup);
        Gdx.input.setInputProcessor(stage);
    }
    static class DataGame{
        private int idGame;
        private String state;

        public DataGame(int idGame, String state) {
            this.idGame = idGame;
            this.state = state;
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.draw();
        stage.act(delta);
        drawActorBorders();

    }

    private void drawActorBorders() {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);

        for (User u : users) {
            shapeRenderer.rect(u.getHitBoxCard().x, u.getHitBoxCard().y, u.getWidth(), u.getHeight());
        }

        shapeRenderer.end();
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

    }

    private ClickListener btnPlayListener(){
        return new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game,idGame, users));
            }
        };
    }
}
