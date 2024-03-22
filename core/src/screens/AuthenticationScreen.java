package screens;

import static helpers.AssetManager.responseAuthenticator;
import static helpers.AssetManager.socket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
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
import helpers.ResponseAuthenticator;
import helpers.UserResponse;

public class AuthenticationScreen implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Table table;
    private TextButton btnLogin;
    private TextField userTextField;
    private TextField passTextField;
    public AuthenticationScreen(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);
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
        batch = new SpriteBatch();
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
        batch.dispose();
    }


    private ClickListener btnLoginListener() {
        return new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Crear un objeto para representar los datos que deseas enviar
                UserResponse dataObject = new UserResponse(userTextField.getText(), passTextField.getText());

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
                .url("http://tr3.dam.inspedralbes.cat:3789/authoritzationLogin")
                .header("Content-Type", "application/json") // Establece el encabezado Content-Type como aplicación/json
                .content(jsonData) // Establece el cuerpo de la solicitud como el JSON que has creado
                .build();
        MyHttpResponseListener httpResponseListener = new MyHttpResponseListener();
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
        System.out.println(httpResponseListener);
    }

    private class MyHttpResponseListener implements Net.HttpResponseListener {
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            // Aquí obtienes y manejas la respuesta
            String responseData = httpResponse.getResultAsString();

            try {
                // Utiliza una instancia de la clase Json para convertir la cadena JSON en un objeto ResponseAuthenticator
                Json json = new Json();
                responseAuthenticator = json.fromJson(ResponseAuthenticator.class, responseData);
                json.setOutputType(JsonWriter.OutputType.json);
                String jsonData = json.toJson(responseAuthenticator);

                if (responseAuthenticator.isAuthorization()) {
                    Gdx.app.postRunnable(() -> {
                        socket.emit("login", jsonData);
                        Gdx.app.log("CAMBIANDO", "ESTOY YENDO A MAIN SCREEN");
                        game.setScreen(new MainScreen(game));
                    });
                } else {
                    Gdx.app.log("AUTHO", "ERROR NO EXISTE USUARIO");
                }
            } catch (SerializationException e) {
                // Manejo en caso de error al analizar el JSON
                System.out.println("Error al analizar el JSON: " + e.getMessage());
            }
        }

        @Override
        public void failed(Throwable t) {
            // Manejo de errores en caso de que la solicitud falle
            System.out.println("La solicitud falló: " + t.getMessage());
        }

        @Override
        public void cancelled() {
            // Manejo en caso de que la solicitud sea cancelada
            System.out.println("La solicitud fue cancelada.");
        }
    }
}
