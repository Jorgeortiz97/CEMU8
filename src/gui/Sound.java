package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private boolean playable;
	private boolean enable = true;
	
	private final int MAX_SOUNDS = 4;
	
	private Clip clips[] = new Clip[MAX_SOUNDS];
	private int index = 0;

	public Sound() {
		AudioInputStream audioIn;
		try {
			for (int i = 0; i<MAX_SOUNDS; i++) {
				audioIn = AudioSystem.getAudioInputStream(new File("sound/beep.wav"));
				clips[i] = AudioSystem.getClip();
				clips[i].open(audioIn);
			}
			
			playable = true;
		} 
		catch (LineUnavailableException e) {}
		catch (UnsupportedAudioFileException e) {} 
		catch (IOException e) {}
	}
	
	public void play() {
		if (playable && enable) {
			clips[index].setMicrosecondPosition(0);
			clips[index].start();
			index = (index + 1) % MAX_SOUNDS;
		}
	}
	
	public void enableSound() {
		enable = true;
	}
	public void disableSound() {
		enable = false;
	}
}
