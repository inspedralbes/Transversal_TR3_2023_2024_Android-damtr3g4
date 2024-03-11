package plants.vs.zombie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import helpers.AssetManager;
import screens.GameScreen;

public class plantsVsZombie extends Game {
	@Override
	public void create() {

		// A l'iniciar el joc carreguem els recursos
		AssetManager.load();
		// I definim la pantalla principal com a la pantalla
		setScreen(new GameScreen());

	}

	// Cridem per descartar els recursos carregats.
	@Override
	public void dispose() {
		super.dispose();
		AssetManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log("LifeCycle", "resize(" + Integer.toString(width) + ", " + Integer.toString(height) + ")");
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log("LifeCycle", "pause()");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log("LifeCycle", "resume()");
	}

	@Override
	public void render() {
		super.render();
 		Gdx.app.log("LifeCycle", "render()");
	}

}
