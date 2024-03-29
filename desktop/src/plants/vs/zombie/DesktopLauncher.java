package plants.vs.zombie;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import plants.vs.zombie.plantsVsZombie;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(plantsVsZombie.WIDTH, plantsVsZombie.HEIGHT);
		config.setTitle("plantsVsZombie");
		new Lwjgl3Application(new plantsVsZombie(), config);
	}
}
