package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import helpers.AssetManager;
import helpers.InputHandler;
import objects.Box;
import objects.CardPlant;
import objects.Peashooter;
import objects.Pitufo;
import objects.Shinchan;
import objects.Sunflower;
import objects.Weapon;
import plants.vs.zombie.plantsVsZombie;

public class GameScreen implements Screen {
    private TiledMap tiledMap;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;
    private plantsVsZombie pVsZ;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;

    private Shinchan shinchan;
    private Pitufo pitufo;
    private Weapon weapon;
    private InputHandler inputHandler;
    private int mapWidth;
    private int mapHeight;
    private MapProperties prop;
    private TiledMapTileLayer objectLayer;
    public GameScreen(plantsVsZombie pVsZ) {
        this.pVsZ = pVsZ;
        batch = new SpriteBatch();
        tiledMap = AssetManager.tiledMap; //Cargo el mapa en el constructor de la clase juego
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        prop = tiledMap.getProperties();
        mapWidth = prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class);
        mapHeight = prop.get("height", Integer.class) * prop.get("tileheight", Integer.class);
        camera = new OrthographicCamera(mapWidth,mapHeight);
        camera.setToOrtho(false, mapWidth, mapHeight); //Es importante que esté para que no se salgan los personajes de la pantalla
        StretchViewport viewport = new StretchViewport(mapWidth, mapHeight, camera);
        stage = new Stage(viewport);

        shinchan = new Shinchan(100, 100, 40,50);
        pitufo = new Pitufo(200, 100, 40, 50);
        weapon = new Weapon(150,150,40,40);

        stage.addActor(weapon);
        stage.addActor(shinchan);
        stage.addActor(pitufo);
        inputHandler = new InputHandler(this);
        Gdx.input.setInputProcessor(inputHandler);
    }

    private void checkCollisions() {
        MapLayer objectLayer = tiledMap.getLayers().get("Cerdo");
        System.out.println(objectLayer);
        if (objectLayer != null) {
            for (MapObject object : objectLayer.getObjects()) {
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectangleObject = (RectangleMapObject) object;
                    Rectangle rectangle = rectangleObject.getRectangle();

                    // Verificar colisión con los rectángulos de los objetos
                    if (shinchan.getHitBoxCard().overlaps(rectangle)) {
                        // Acciones cuando hay colisión con Shinchan
                        // Mover a Shinchan a la posición anterior para evitar que atraviese el objeto
                        shinchan.setPosition(45,50);
                    }
                    if (pitufo.getHitBoxCard().overlaps(rectangle)) {
                        // Acciones cuando hay colisión con Pitufo
                        // Mover a Pitufo a la posición anterior para evitar que atraviese el objeto
                        //pitufo.setPosition(pitufo.getPrevX(), pitufo.getPrevY());
                    }
                    // Puedes agregar más condiciones para otros personajes si los tienes
                }
            }
        }
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        System.out.println("Width: " + mapHeight + " Heidth: " + mapWidth);
        checkCollisions();
        clearScreen();
        inputHandler.updateShinchan(delta);
        inputHandler.updatePitufo(delta);
        stage.draw();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
        shinchan.draw(batch, delta);
        pitufo.draw(batch, delta);
        batch.end();
        stage.act(delta);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        // Begin drawing filled rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);

        // Draw rectangle using hitBox dimensions
        shapeRenderer.rect(weapon.getHitBoxCard().x, weapon.getHitBoxCard().y, weapon.getWidth(), weapon.getHeight());
        shapeRenderer.rect(shinchan.getHitBoxCard().x, shinchan.getHitBoxCard().y, shinchan.getWidth(), shinchan.getHeight());
        shapeRenderer.rect(pitufo.getHitBoxCard().x, pitufo.getHitBoxCard().y, pitufo.getWidth(), pitufo.getHeight());

        shapeRenderer.end();

        if((weapon.isGrabbed() && shinchan.isWeapon()) && (weapon.getIdPlayer() == shinchan.getId()) || (weapon.isGrabbed() && pitufo.isWeapon()) && (weapon.getIdPlayer() == pitufo.getId())){
            weapon.setHitBoxCard(new Rectangle(pitufo.getHitBoxCard().x,pitufo.getHitBoxCard().y + pitufo.getHeight(), weapon.getWidth(), weapon.getHeight()));
        }

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

    public Shinchan getShinchan() {
        return shinchan;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Pitufo getPitufo() {
        return pitufo;
    }
}
