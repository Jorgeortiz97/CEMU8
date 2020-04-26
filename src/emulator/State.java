package emulator;

public class State {

	private boolean drawFlag;
	
	private int pc; // Program counter
	private int opcode; // Current opcode
	private int I; // Index register
	private int sp; // Stack pointer
	private int delay_timer, sound_timer;
	
	private byte gfx[];
	private int V[] = new int[16]; // V-regs (V0-VF)
	private int stack[] = new int[16]; // Stack (16 levels)
	private int memory[] = new int[4096]; // Memory (size = 4k)

	public State(boolean _drawFlag, int _pc, int _opcode, int _I, int _sp,
			int _delay_timer, int _sound_timer, byte _gfx[], int _V[],
			int _stack[], int _memory[]) {
		drawFlag = _drawFlag;
		pc = _pc;
		opcode = _opcode;
		I = _I;
		sp = _sp;
		delay_timer = _delay_timer;
		sound_timer = _sound_timer;
		
		gfx = _gfx;
		V = new int[16];
		// Adjust registers to one byte:
		for (int i = 0; i < 16; i++)
			V[i] = _V[i] & 0XFF;

		stack = _stack;
		memory = _memory;
	}

	public boolean isDrawFlag() {
		return drawFlag;
	}

	public int getPc() {
		return pc;
	}

	public int getOpcode() {
		return opcode;
	}

	public int getI() {
		return I;
	}

	public int getSp() {
		return sp;
	}

	public int getDelayTimer() {
		return delay_timer;
	}

	public int getSoundTimer() {
		return sound_timer;
	}

	public byte[] getGfx() {
		return gfx.clone();
	}

	public int[] getV() {
		return V.clone();
	}

	public int[] getStack() {
		return stack.clone();
	}

	public int[] getMemory() {
		return memory.clone();
	}
	
}
