package launcher;

import gui.Controller;
import gui.Sound;
import gui.Window;

public class Launcher {

	public static void main(String[] args) {

		Window w = new Window();
		Sound snd = new Sound();
		Controller.getInstance().initialize(w, snd);

		Controller.getInstance();

	}
}
