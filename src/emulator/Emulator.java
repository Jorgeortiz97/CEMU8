package emulator;

import gui.Screen;

public class Emulator implements Runnable {
	private final static int SLOTS = 4;
	private final static int DEFAULT_SPEED = 2;
	
	private boolean emulation = false;
	private Chip8 chip;
	private Screen sc;
	private State[] state = new State[SLOTS];
	private EmuState emuState = EmuState.RUNNING;
	private int slot, speed = DEFAULT_SPEED;
	
	private final Object pauseLock = new Object();
	
	public Emulator(Chip8 chip, Screen sc) {
		this.chip = chip;
		this.sc = sc;
	}
	
	public void startEmulation() {
		emulation = true;
	}
	
	public void stopEmulation() {
		emulation = false;
		resume();
	}

	public void save(int slot) {
		emuState = EmuState.SAVING;
		this.slot = slot;
	}
	public void load(int slot) {
		emuState = EmuState.LOADING;
		this.slot = slot;
	}
	
	public void reset() {
		emuState = EmuState.RESET;
	}
	
	public EmuState getState() {
		return emuState;
	}
	
	public void pause() {
		emuState = EmuState.PAUSED;
	}
	
	public void resume() {
        synchronized (pauseLock) {
        	emuState = EmuState.RUNNING;
            pauseLock.notify(); // Unblocks thread
        }
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void run() {
		
		long current = System.currentTimeMillis();
		
		while (emulation) {
			chip.emulateCycle();
			if (chip.getDrawFlag()) {
				sc.update();
				chip.setFalseDrawFlag();
			}
			synchronized (pauseLock) {
				if (emuState == EmuState.SAVING)
					state[slot] = chip.saveState();
				else if (emuState == EmuState.LOADING && state[slot] != null)
					 chip.loadState(state[slot]);
				else if (emuState == EmuState.RESET)
					chip.reset();
				else if (emuState == EmuState.PAUSED)
	                try {
	                    pauseLock.wait(); 
	                } catch (InterruptedException ex) {}
				emuState = EmuState.RUNNING;
			}
			
			while (System.currentTimeMillis() - current < speed)
				try {
					Thread.sleep(0, 50);
				} catch (InterruptedException e) {}
			current = System.currentTimeMillis();
		}
	}
}
