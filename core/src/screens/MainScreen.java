package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import helpers.AssetManager;
import helpers.Game;
import helpers.User;

public class MainScreen implements Screen {
    private MyGdxGame game;
    private TextButton btnPlaySolo;
    private TextButton btnPlay1v1;
    private TextButton btnConfig;
    private TextButton btnStore;
    private Table table;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public MainScreen(MyGdxGame game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);
        stage = new Stage(viewport);

        table = new Table();
        btnPlaySolo = new TextButton("Crear Sala", AssetManager.skin);
        btnPlay1v1 = new TextButton("Unirse a sala", AssetManager.skin);
        btnConfig = new TextButton("Configuracion", AssetManager.skin);
        btnStore = new TextButton("Tienda", AssetManager.skin);

        table.add(btnPlaySolo).width(200).height(60).pad(10);
        table.row();
        table.add(btnPlay1v1).width(200).height(60).pad(10);
        table.row();
        table.add(btnConfig).width(200).height(60).pad(10);
        table.row();
        table.add(btnStore).width(200).height(60).pad(10);

        btnPlaySolo.addListener(btnPlayCreateLobbyListener());
        btnPlay1v1.addListener(btnJoinLobbyListener());
        btnConfig.addListener(btnConfigListener());
        btnStore.addListener(btnStoreListener());

        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }
    private ResponseInitGame responseInitGame;
    private ClickListener btnPlayCreateLobbyListener(){
        return new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                responseInitGame = new ResponseInitGame();
                Game dataObject = new Game("5", "waiting", "pruebaPAI7");

                // Convertir el objeto a JSON
                Json json = new Json();
                json.setOutputType(JsonWriter.OutputType.json);
                String jsonData = json.toJson(dataObject);

                userAuthenticator(jsonData);
            }
        };
    }
    public void userAuthenticator(String jsonData) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url("http://localhost:3789/initGame")
                .header("Content-Type", "application/json") // Establece el encabezado Content-Type como aplicación/json
                .content(jsonData) // Establece el cuerpo de la solicitud como el JSON que has creado
                .build();
        MyHttpResponseListener httpResponseListener = new MyHttpResponseListener();
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
        System.out.println(httpResponseListener);
    }
    static class ResponseInitGame {
        private int idGame;
        private String passwordGame;
        public ResponseInitGame(){

        }
    }
    private class MyHttpResponseListener implements Net.HttpResponseListener{
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            String responseData = httpResponse.getResultAsString();
            System.out.println(responseData);
            try {
                // Utiliza una instancia de la clase Json para convertir la cadena JSON en un objeto ResponseAuthenticator
                Json json = new Json();
                responseInitGame = json.fromJson(ResponseInitGame.class, responseData);
                System.out.println(responseInitGame);
                Gdx.app.postRunnable(() -> {
                    Gdx.app.log("CAMBIANDO", "ESTOY YENDO A CREATE LOBBY SCREEN");
                    game.setScreen(new LobbyScreen(game, 1, "password"));
                });


            } catch (SerializationException e) {
                // Manejo en caso de error al analizar el JSON
                System.out.println("Error al analizar el JSON: " + e.getMessage());
            }
        }

        @Override
        public void failed(Throwable t) {

        }

        @Override
        public void cancelled() {

        }
    }

    private ClickListener btnJoinLobbyListener(){
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("CAMBIANDO", "ESTOY YENDO A JOIN LOBBY SCREEN");
                game.setScreen(new JoinLobbyScreen(game));
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
