package helpers;

import static helpers.AssetManager.socket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import objects.User;
import screens.GameScreen;

public class InputHandlerUserLocal implements InputProcessor {
    private GameScreen gameScreen;
    private User user;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private float speed = 505;

    public InputHandlerUserLocal(GameScreen gameScreen, User u){
        this.gameScreen = gameScreen;
        this.user = u;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                socket.emit("sendMovementUser", user.getId(), keycode, true, gameScreen.getIdGame());
                movingUp = true;
                break;
            case Input.Keys.S:
                socket.emit("sendMovementUser", user.getId(), keycode, true, gameScreen.getIdGame());
                movingDown = true;
                break;
            case Input.Keys.A:
                socket.emit("sendMovementUser", user.getId(), keycode, true, gameScreen.getIdGame());
                movingLeft = true;
                break;
            case Input.Keys.D:
                socket.emit("sendMovementUser", user.getId(), keycode, true, gameScreen.getIdGame());
                movingRight = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                socket.emit("sendMovementUser", user.getId(), keycode, false, gameScreen.getIdGame());
                movingUp = false;
                break;
            case Input.Keys.S:
                socket.emit("sendMovementUser", user.getId(), keycode, false, gameScreen.getIdGame());
                movingDown = false;
                break;
            case Input.Keys.A:
                socket.emit("sendMovementUser", user.getId(), keycode, false, gameScreen.getIdGame());
                movingLeft = false;
                break;
            case Input.Keys.D:
                socket.emit("sendMovementUser", user.getId(), keycode, false, gameScreen.getIdGame());
                movingRight = false;
                break;
        }
        return false;
    }

    public void updateUserLocal(float delta) {
        Vector2 position = user.getPosition();

        if (movingUp) {
            position.y += speed * delta;
        }
        if (movingDown) {
            position.y -= speed * delta;
        }
        if (movingLeft) {
            position.x -= speed * delta;
        }
        if (movingRight) {
            position.x += speed * delta;
        }

        // Asegurarse de que el Shinchan no se salga de los límites de la pantalla
        position.x = MathUtils.clamp(position.x, 0, Gdx.graphics.getWidth() - user.getWidth());
        position.y = MathUtils.clamp(position.y, 0, Gdx.graphics.getHeight() - user.getHeight());

        user.setPosition(position.x, position.y);
        user.setHitBoxCard(new Rectangle(position.x, position.y, user.getWidth(), user.getHeight()));
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
