package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import helpers.AssetManager;
import helpers.InputHandler;
import objects.Box;
import objects.CardPlant;
import objects.Peashooter;
import objects.Sunflower;
import plants.vs.zombie.plantsVsZombie;

public class GameScreen implements Screen {
    private plantsVsZombie pVsZ;
    private SpriteBatch batch;
    private ArrayList<CardPlant> plants;
    private OrthographicCamera camera;
    private Stage stage;
    private CardPlant sunflower;
    private CardPlant peashooter;
    private ArrayList<Box> boxs;

    private int rows = 5; // Número de filas
    private int cols = 2; // Número de columnas
    private float marginX = 8; // Margen horizontal entre los cuadros
    private float marginY = 5; // Margen vertical entre los cuadros
    public GameScreen(plantsVsZombie pVsZ) {
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT, camera);
        stage = new Stage(viewport);

        boxs = new ArrayList<>();
        plants = new ArrayList<>();

        peashooter = new Peashooter(0, 250, 80, 100, true);
        sunflower = new Sunflower(0, 0, 80, 100, true);

        plants.add(sunflower);
        plants.add(peashooter);
        stage.addActor(peashooter);
        stage.addActor(sunflower);

        float boxWidth = 50; // Ancho del cuadro
        float boxHeight = 75; // Altura del cuadro

        // Calcular el espacio horizontal y vertical total ocupado por los cuadros y los márgenes
        float totalWidth = cols * boxWidth + (cols - 1) * marginX;
        float totalHeight = rows * boxHeight + (rows - 1) * marginY;

        // Calcular el margen restante en X e Y para centrar los cuadros en la pantalla
        float remainingMarginX = (plantsVsZombie.WIDTH - totalWidth)/3;
        float remainingMarginY = (plantsVsZombie.HEIGHT - totalHeight) - 110;

        // Colocar cada cuadro en la posición correspondiente con margen
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float x = remainingMarginX + col * (boxWidth + marginX) + boxWidth / 2;
                float y = remainingMarginY + row * (boxHeight + marginY) + boxHeight / 2;
                System.out.println("x: " + x + " y: " + y);
                Box box = new Box(x, y, boxWidth, boxHeight);
                boxs.add(box);
                stage.addActor(box);
            }
        }

        // Colocar la caja en el centro del primer cuadro

        Gdx.input.setInputProcessor(new InputHandler(this));
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        batch.draw(AssetManager.bgGameScreen, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Iterar sobre todas las plantas
        /*for (CardPlant plant : plants) {
            // Verificar colisión entre la caja y la planta actual
            if (box.collidesWithCardPlant(plant)) {
                // Realizar acciones correspondientes, por ejemplo, marcar la caja como "planted"
                box.setPlanted(true);
                System.out.println("TRUE");
                // Aquí podrías agregar más lógica según tus necesidades
            }
        }*/

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

    public plantsVsZombie getpVsZ() {
        return pVsZ;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStage() {
        return stage;
    }

    public ArrayList<CardPlant> getPlants() {
        return plants;
    }
}
