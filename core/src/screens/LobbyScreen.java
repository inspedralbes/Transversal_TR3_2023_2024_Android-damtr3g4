package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

import io.socket.emitter.Emitter;
import objects.CharacterUser;
import io.socket.client.IO;
import io.socket.client.Socket;

public class LobbyScreen implements Screen {
    private MyGdxGame game;
    private int idGame;
    private String password;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ArrayList<CharacterUser> users;
    private Socket socket;

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
        connectSocket();
        socket.emit("getUsersInGame", jsonData);
        socket.on("usersInGame", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("ME LLEGA NO SE QUE ME LLEGA");
                System.out.println(args[0]);
            }
        });
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

    public void connectSocket(){
        try{
            socket = IO.socket("http://192.168.19.253:3789");
            socket.connect();
            System.out.println("VA");
        }catch (Exception e){
            System.out.println("NO VA");
            System.out.println(e);
        }
    }
}
