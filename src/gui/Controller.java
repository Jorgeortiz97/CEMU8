package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import emulator.Chip8;
import emulator.EmuState;
import emulator.Emulator;

public class Controller {
	private static Controller singleton = new Controller();
	private Screen sc;
	private Keypad keypad;
	private Sound snd;
	private Chip8 chip;
	
	private Emulator emulator;
	private Thread emulatorT;

	
	public static Controller getInstance() {
		return singleton;
	}
	
	public void initialize(Window window, Sound sound) {
		sc = window.getScreen();
		keypad = window.getKeypad();
		snd = sound;
	}
	
	public void reset() {
		if (chip != null)
			emulator.reset();
			
	}
	
	public void loadROM(String path) {
		if (emulator != null)
			emulator.stopEmulation();
		chip = new Chip8(sc, keypad, snd);
		if (chip.loadApplication(path)) {
			emulator = new Emulator(chip, sc);
			emulatorT = new Thread(emulator);
			emulator.startEmulation();
			emulatorT.start();
		} else
			chip = null;
	}

	
	public void saveData(int slot) {
		if (chip != null)
			emulator.save(slot);
	}
	
	public void loadData(int slot) {
		if (chip != null)	
			emulator.load(slot);
	}
	
	public void pause() {
		if (chip != null)
			if (emulator.getState() == EmuState.PAUSED)
				emulator.resume();
			else
				emulator.pause();
			
	}
	
	public void enableSound() {
		snd.enableSound();
	}
	
	public void disableSound() {
		snd.disableSound();
	}
	
	public void setSpeed(int speed) {
		if (chip != null)
			emulator.setSpeed(speed);
	}
	public void screenshot() {
		BufferedImage img = sc.screenshot();
		File outputfile = new File("screenshot.jpg");
		int i = 0;
		 while (outputfile.exists()) {
			outputfile = new File("screenshot-" + i + ".jpg");
			i++;
		}
			
		try {
			ImageIO.write(img, "jpg", outputfile);
		} catch (IOException e) {}


	}
}
