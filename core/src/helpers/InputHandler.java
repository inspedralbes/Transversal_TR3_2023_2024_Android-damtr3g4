package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import objects.Card;
import screens.GameScreen;

public class InputHandler implements InputProcessor {
    private ArrayList<Card> plants;
    private GameScreen gameScreen;
    private Card selectedCard; // Variable para almacenar la tarjeta seleccionada para arrastrar

    public InputHandler(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.plants = gameScreen.getPlants();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Verificar si se ha tocado algún objeto (tarjeta)
        for (Card card : plants) {
            if (card.getHitBoxCard().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                selectedCard = card; // Guardar la referencia al objeto seleccionado
                return true; // Indicar que el evento ha sido manejado
            }
        }
        return false; // Si no se ha tocado ningún objeto, continuar propagando el evento
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        selectedCard = null; // Al levantar el dedo, se deselecciona el objeto
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Si hay un objeto seleccionado, actualizar su posición con la nueva posición del cursor
        if (selectedCard != null) {
            selectedCard.setPosition(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
            selectedCard.setHitBoxCard(new Rectangle(screenX, Gdx.graphics.getHeight() - screenY, selectedCard.getWidth(), selectedCard.getHeight()));
            return true; // Indicar que el evento ha sido manejado
        }
        return false; // Si no hay ningún objeto seleccionado, continuar propagando el evento
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
