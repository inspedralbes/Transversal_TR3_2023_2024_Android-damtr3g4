package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import objects.ScrollHandler;

public class GameScreen implements Screen {
    private Stage stage;
    private ScrollHandler scrollHandler;

    public GameScreen() {

        // Creem l'stage
        stage = new Stage();

        // Creem la nau i la resta d'objectes
        scrollHandler = new ScrollHandler();

        // Afegim els actors a l'stage
        stage.addActor(scrollHandler);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Dibuixem i actualitzem tots els actors de l'stage
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

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public void setScrollHandler(ScrollHandler scrollHandler) {
        this.scrollHandler = scrollHandler;
    }

}
