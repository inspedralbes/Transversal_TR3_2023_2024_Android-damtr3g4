package helpers;

import static helpers.AssetManager.responseAuthenticator;
import static helpers.AssetManager.socket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.ArrayList;

import objects.User;
import screens.GameScreen;
import screens.LobbyScreen;

public class InputHandlerUserLocal implements InputProcessor {
    private GameScreen gameScreen;
    private ArrayList<User> users;
    private int posUserLocal;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean emitRequired = false; // Indica si se debe emitir el estado actualizado
    private String jsonData;
    private float speed = 505;
    ArrayList<UserDataSendSocket> usersSend;

    static class UserDataSendSocket{
        private int id;
        private float x,y, width, height;

        public UserDataSendSocket(int id, float x, float y, float width, float height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }
    public InputHandlerUserLocal(GameScreen gameScreen, int pos){
        this.gameScreen = gameScreen;
        this.users = gameScreen.getUsers();
        this.posUserLocal = pos;


    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                movingUp = true;
                break;
            case Input.Keys.S:
                movingDown = true;
                break;
            case Input.Keys.A:
                movingLeft = true;
                break;
            case Input.Keys.D:
                movingRight = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                movingUp = false;
                emitRequired = true;
                break;
            case Input.Keys.S:
                emitRequired = true;
                movingDown = false;
                break;
            case Input.Keys.A:
                emitRequired = true;
                movingLeft = false;
                break;
            case Input.Keys.D:
                emitRequired = true;
                movingRight = false;
                break;
        }
        return false;
    }

    public void updateUserLocal() {
        Vector2 position = users.get(posUserLocal).getPosition();

        if (movingUp) {
            position.y += speed *  Gdx.graphics.getDeltaTime();
        }
        if (movingDown) {
            position.y -= speed *  Gdx.graphics.getDeltaTime();
        }
        if (movingLeft) {
            position.x -= speed *  Gdx.graphics.getDeltaTime();
        }
        if (movingRight) {
            position.x += speed *  Gdx.graphics.getDeltaTime();
        }

        // Asegurarse de que el Shinchan no se salga de los límites de la pantalla
        position.x = MathUtils.clamp(position.x, 0, Gdx.graphics.getWidth() - users.get(posUserLocal).getWidth());
        position.y = MathUtils.clamp(position.y, 0, Gdx.graphics.getHeight() - users.get(posUserLocal).getHeight());

        users.get(posUserLocal).setPosition(position.x, position.y);
        users.get(posUserLocal).setHitBoxCard(new Rectangle(position.x, position.y, users.get(posUserLocal).getWidth(), users.get(posUserLocal).getHeight()));

        User userLocal = users.get(posUserLocal);
        this.users.remove(userLocal);
        this.users.add(userLocal);

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        usersSend = new ArrayList<>();

        for (User u :users) {
            UserDataSendSocket uSend = new UserDataSendSocket(u.getId(),u.getPosition().x,u.getPosition().y,u.getWidth(), u.getHeight());
            usersSend.add(uSend);
        }
        this.jsonData = json.toJson(usersSend);
    }

    public boolean isEmitRequired() {
        return emitRequired;
    }
    public void emitUsers() {
        socket.emit("sendMovementUser", jsonData);
        emitRequired = false;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
