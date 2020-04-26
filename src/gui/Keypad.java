package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keypad implements KeyListener {

	public boolean key[] = new boolean[16];
	
	public void reset() {
		for (int i = 0; i<16; i++)
			key[i] = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1: key[1] = true; break;
		case KeyEvent.VK_2: key[2] = true; break;
		case KeyEvent.VK_3: key[3] = true; break;
		case KeyEvent.VK_4: key[12] = true; break;
		
		case KeyEvent.VK_Q: key[4] = true; break;
		case KeyEvent.VK_W: key[5] = true; break;
		case KeyEvent.VK_E: key[6] = true; break;
		case KeyEvent.VK_R: key[13] = true; break;
		
		case KeyEvent.VK_A: key[7] = true; break;
		case KeyEvent.VK_S: key[8] = true; break;
		case KeyEvent.VK_D: key[9] = true; break;
		case KeyEvent.VK_F: key[14] = true; break;
		
		case KeyEvent.VK_Z: key[10] = true; break;
		case KeyEvent.VK_X: key[0] = true; break;
		case KeyEvent.VK_C: key[11] = true; break;
		case KeyEvent.VK_V: key[15] = true; break;
		}
		if (e.isControlDown()) {
			if (e.getKeyCode() == KeyEvent.VK_F1)
				Controller.getInstance().loadData(0);
			else if (e.getKeyCode() == KeyEvent.VK_F2)
				Controller.getInstance().loadData(1);
			else if (e.getKeyCode() == KeyEvent.VK_F3)
				Controller.getInstance().loadData(2);
			else if (e.getKeyCode() == KeyEvent.VK_F4)
				Controller.getInstance().loadData(3);
			else if (e.getKeyCode() == KeyEvent.VK_F5)
				Controller.getInstance().reset();
			else if (e.getKeyCode() == KeyEvent.VK_P)
				Controller.getInstance().pause();
			else if (e.getKeyCode() == KeyEvent.VK_O)
				Controller.getInstance().screenshot();
		} else {
			if (e.getKeyCode() == KeyEvent.VK_F1)
				Controller.getInstance().saveData(0);
			else if (e.getKeyCode() == KeyEvent.VK_F2)
				Controller.getInstance().saveData(1);
			else if (e.getKeyCode() == KeyEvent.VK_F3)
				Controller.getInstance().saveData(2);
			else if (e.getKeyCode() == KeyEvent.VK_F4)
				Controller.getInstance().saveData(3);
		}

	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1: key[1] = false; break;
		case KeyEvent.VK_2: key[2] = false; break;
		case KeyEvent.VK_3: key[3] = false; break;
		case KeyEvent.VK_4: key[12] = false; break;
		
		case KeyEvent.VK_Q: key[4] = false; break;
		case KeyEvent.VK_W: key[5] = false; break;
		case KeyEvent.VK_E: key[6] = false; break;
		case KeyEvent.VK_R: key[13] = false; break;
		
		case KeyEvent.VK_A: key[7] = false; break;
		case KeyEvent.VK_S: key[8] = false; break;
		case KeyEvent.VK_D: key[9] = false; break;
		case KeyEvent.VK_F: key[14] = false; break;
		
		case KeyEvent.VK_Z: key[10] = false; break;
		case KeyEvent.VK_X: key[0] = false; break;
		case KeyEvent.VK_C: key[11] = false; break;
		case KeyEvent.VK_V: key[15] = false; break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
