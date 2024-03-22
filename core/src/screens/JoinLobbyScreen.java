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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import helpers.AssetManager;
import helpers.DataGameJoinSend;

public class JoinLobbyScreen implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Table table;
    private TextButton btnJoinLobby;
    private TextField passwordTextField;
    public JoinLobbyScreen (MyGdxGame game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);
        stage = new Stage(viewport);

        table = new Table();
        passwordTextField = new TextField("", AssetManager.skin);
        btnJoinLobby = new TextButton("Entrar", AssetManager.skin);


        table.add(passwordTextField).width(200).height(60).pad(10);
        table.row();
        table.add(btnJoinLobby).width(200).height(60).pad(10);

        btnJoinLobby.addListener(btnJoinLobbyListener());

        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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

    private ClickListener btnJoinLobbyListener(){
        return new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                DataGameJoinSend dataObject = new DataGameJoinSend(passwordTextField.getText(), AssetManager.responseAuthenticator.getId());

                // Convertir el objeto a JSON
                Json json = new Json();
                json.setOutputType(JsonWriter.OutputType.json);
                String jsonData = json.toJson(dataObject);

                joinGame(jsonData);
                //game.setScreen(new LobbyScreen(this, idGame, passwordTextField.getText()));
            }
        };
    }
    public void joinGame(String jsonData) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url("http://tr3.dam.inspedralbes.cat:3789/joinGame")
                .header("Content-Type", "application/json") // Establece el encabezado Content-Type como aplicación/json
                .content(jsonData) // Establece el cuerpo de la solicitud como el JSON que has creado
                .build();
        MyHttpResponseListener httpResponseListener = new MyHttpResponseListener();
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
        System.out.println(httpResponseListener);
    }
    private ResponseJoinGame responseJoinGame;
    private class MyHttpResponseListener implements Net.HttpResponseListener{
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            String responseData = httpResponse.getResultAsString();
            responseJoinGame = new ResponseJoinGame();
            try {
                // Utiliza una instancia de la clase Json para convertir la cadena JSON en un objeto ResponseAuthenticator
                Json json = new Json();
                responseJoinGame = json.fromJson(ResponseJoinGame.class, responseData);
                System.out.println(responseJoinGame);
                Gdx.app.postRunnable(() -> {
                    Gdx.app.log("CAMBIANDO", "ESTOY YENDO A CREATE LOBBY SCREEN");
                    //TENGO QUE CAMBIAR EL IDGAME POR EL Q SE GENERA Y EL PASSWORD
                    game.setScreen(new LobbyScreen(game, responseJoinGame.getIdGame(), "password"));
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

    static class ResponseJoinGame{
        private int idGame;

        public ResponseJoinGame() {
        }

        public ResponseJoinGame(int idGame) {
            this.idGame = idGame;
        }

        public int getIdGame() {
            return idGame;
        }

        public void setIdGame(int idGame) {
            this.idGame = idGame;
        }
    }
}
