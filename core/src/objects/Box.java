package objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {
    private boolean planted;
    private Vector2 position;
    private float width, height;
    private Rectangle hitBox;

    public Box(float x, float y, float width, float height) {
        this.planted = false;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.hitBox = new Rectangle(x,y,width,height);
    }

    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        // Begin drawing filled rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        if (planted) {
            // If planted, draw a green rectangle
            shapeRenderer.setColor(Color.GREEN);
        } else {
            // If not planted, draw a red rectangle
            shapeRenderer.setColor(Color.RED);
        }

        // Draw rectangle using hitBox dimensions
        shapeRenderer.rect(position.x, position.y, width, height);

        // End drawing
        shapeRenderer.end();
    }

    // Método para verificar colisiones con una planta
    public boolean collidesWithCardPlant(CardPlant plant) {
        Rectangle plantBounds = plant.getHitBoxCard(); // Obtener los límites del rectángulo de la planta
        return Intersector.overlaps(hitBox, plantBounds); // Verificar si hay colisión entre la caja y la planta
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }
}
