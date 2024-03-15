package helpers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import objects.Pitufo;
import objects.Shinchan;
import objects.Weapon;
import screens.GameScreen;

public class InputHandler implements InputProcessor {
    private GameScreen gameScreen;
    private Shinchan shinchan;
    private Pitufo pitufo;
    private Weapon weapon;
    private boolean movingUpShinchan = false;
    private boolean movingDownShinchan = false;
    private boolean movingLeftShinchan = false;
    private boolean movingRightShinchan = false;
    private boolean movingUpPitufo = false;
    private boolean movingDownPitufo = false;
    private boolean movingLeftPitufo = false;
    private boolean movingRightPitufo = false;

    private float speed = 505; // Velocidad de movimiento del Shinchan

    public InputHandler(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.shinchan = gameScreen.getShinchan();
        this.weapon = gameScreen.getWeapon();
        this.pitufo = gameScreen.getPitufo();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                movingUpShinchan = true;
                break;
            case Input.Keys.S:
                movingDownShinchan = true;
                break;
            case Input.Keys.A:
                movingLeftShinchan = true;
                break;
            case Input.Keys.D:
                movingRightShinchan = true;
                break;
        }
        switch (keycode) {
            case Input.Keys.I:
                movingUpPitufo = true;
                break;
            case Input.Keys.K:
                movingDownPitufo = true;
                break;
            case Input.Keys.J:
                movingLeftPitufo = true;
                break;
            case Input.Keys.L:
                movingRightPitufo = true;
                break;
        }
        return true; // Indica que se ha manejado el evento
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                movingUpShinchan = false;
                break;
            case Input.Keys.S:
                movingDownShinchan = false;
                break;
            case Input.Keys.A:
                movingLeftShinchan = false;
                break;
            case Input.Keys.D:
                movingRightShinchan = false;
                break;
        }
        switch (keycode) {
            case Input.Keys.I:
                movingUpPitufo = false;
                break;
            case Input.Keys.K:
                movingDownPitufo = false;
                break;
            case Input.Keys.J:
                movingLeftPitufo = false;
                break;
            case Input.Keys.L:
                movingRightPitufo = false;
                break;
        }
        return true; // Indica que se ha manejado el evento
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Implementación de la lógica de toque, si es necesario
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

    // Otros métodos de la interfaz InputProcessor que puedes implementar según tus necesidades

    public void updateShinchan(float delta) {
        Vector2 position = shinchan.getPosition();

        if (movingUpShinchan) {
            position.y += speed * delta;
        }
        if (movingDownShinchan) {
            position.y -= speed * delta;
        }
        if (movingLeftShinchan) {
            position.x -= speed * delta;
        }
        if (movingRightShinchan) {
            position.x += speed * delta;
        }

        // Asegurarse de que el Shinchan no se salga de los límites de la pantalla
        position.x = MathUtils.clamp(position.x, 0, Gdx.graphics.getWidth() - shinchan.getWidth());
        position.y = MathUtils.clamp(position.y, 0, Gdx.graphics.getHeight() - shinchan.getHeight());

        shinchan.setPosition(position.x, position.y);
        shinchan.setHitBoxCard(new Rectangle(position.x, position.y, shinchan.getWidth(), shinchan.getHeight()));
        // Verificar la colisión entre Shinchan y Weapon
        if (shinchan.getHitBoxCard().overlaps(weapon.getHitBoxCard())) {
            // Colocar el Weapon encima del Shinchan
            weapon.setGrabbed(true);
            shinchan.setWeapon(true);
            weapon.setIdPlayer(shinchan.getId());
        }
    }

    public void updatePitufo(float delta) {
        Vector2 position = pitufo.getPosition();

        if (movingUpPitufo) {
            position.y += speed * delta;
        }
        if (movingDownPitufo) {
            position.y -= speed * delta;
        }
        if (movingLeftPitufo) {
            position.x -= speed * delta;
        }
        if (movingRightPitufo) {
            position.x += speed * delta;
        }

        // Asegurarse de que el Shinchan no se salga de los límites de la pantalla
        position.x = MathUtils.clamp(position.x, 0, Gdx.graphics.getWidth() - shinchan.getWidth());
        position.y = MathUtils.clamp(position.y, 0, Gdx.graphics.getHeight() - shinchan.getHeight());

        pitufo.setPosition(position.x, position.y);
        pitufo.setHitBoxCard(new Rectangle(position.x, position.y, pitufo.getWidth(), pitufo.getHeight()));
        // Verificar la colisión entre Shinchan y Weapon
        if (pitufo.getHitBoxCard().overlaps(weapon.getHitBoxCard())) {
            // Colocar el Weapon encima del Shinchan
            weapon.setGrabbed(true);
            pitufo.setWeapon(true);
            weapon.setIdPlayer(pitufo.getId());
        }
    }

    // Otros métodos de la interfaz InputProcessor que puedes implementar según tus necesidades
}
