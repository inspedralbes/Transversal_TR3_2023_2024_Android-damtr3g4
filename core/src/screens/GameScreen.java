package screens;

import static helpers.AssetManager.socket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import helpers.AssetManager;
import helpers.InputHandlerUserLocal;
import helpers.InputHandlerUserSocket;
import io.socket.emitter.Emitter;
import objects.User;

public class GameScreen implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private int idGame;
    private ArrayList<User> users;
    private InputHandlerUserLocal inputHandlerUserLocal;
    private InputHandlerUserSocket inputHandlerUserSocket;
    public GameScreen(MyGdxGame game, int idGame, ArrayList<User> users){
        this.game = game;
        this.idGame = idGame;
        this.users = users;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);
        stage = new Stage(viewport);

        for (User u:users) {
            u.setWidth(50);
            u.setHeight(60);
            u.setHitBoxCard(new Rectangle(u.getPosition().x, u.getPosition().y,u.getWidth(), u.getHeight()));
            stage.addActor(u);
        }
        int pos = 1;
        for (User u:users) {
            if(u.getId() == AssetManager.responseAuthenticator.getId()){
                pos = users.indexOf(u);
            }
        }
        inputHandlerUserLocal = new InputHandlerUserLocal(this, pos);
        Gdx.input.setInputProcessor(inputHandlerUserLocal);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        inputHandlerUserLocal.updateUserLocal();
        if (inputHandlerUserLocal.isEmitRequired()) {
            inputHandlerUserLocal.emitUsers();
        }
        setNewPositions();
        stage.draw();
        stage.act(delta);
        drawActorBorders();
    }
    public void setNewPositions(){
        socket.on("getMovementUser", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String jsonUsers = args[0].toString();
                System.out.println(jsonUsers);
                JSONObject jsonObject = new JSONObject(jsonUsers);

                float x = jsonObject.getFloat("x");
                float y = jsonObject.getFloat("y");
                float width = jsonObject.getFloat("width");
                float height = jsonObject.getFloat("height");
                int id = jsonObject.getInt("id");
                for (User u:users) {
                    if(u.getId() == id){
                        u.setPosition(new Vector2(x,y));
                        u.setWidth(width);
                        u.setHeight(height);
                        u.setHitBoxCard(new Rectangle(x,y,width,height));
                    }
                }
            }
        });
    }

    public void udateUserSocket(float delta, User user, int keycode, boolean state){
        Vector2 position = user.getPosition();
        float speed = 505;
        switch (keycode) {
            case Input.Keys.W:
                if(state) position.y += speed * delta;
                break;
            case Input.Keys.S:
                if(state) position.y -= speed * delta;
                break;
            case Input.Keys.A:
                if(state) position.x -= speed * delta;
                break;
            case Input.Keys.D:
                if(state) position.x += speed * delta;
                break;
        }
        // Asegurarse de que el Shinchan no se salga de los límites de la pantalla
        position.x = MathUtils.clamp(position.x, 0, Gdx.graphics.getWidth() - user.getWidth());
        position.y = MathUtils.clamp(position.y, 0, Gdx.graphics.getHeight() - user.getHeight());

        user.setPosition(position.x, position.y);
        user.setHitBoxCard(new Rectangle(position.x, position.y, user.getWidth(), user.getHeight()));
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getIdGame() {
        return idGame;
    }
}
