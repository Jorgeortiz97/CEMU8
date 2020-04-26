package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Screen extends JPanel {
	private static final long serialVersionUID = 1L;

	private final int WIDTH, HEIGHT, SCALE;
	private byte gfx[] = null;
	
	public long time = System.currentTimeMillis();
	
	public Screen() {
		setFocusable(true);
		WIDTH = Window.WIDTH;
		HEIGHT = Window.HEIGHT;
		SCALE = Window.SCALE;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	}
	
	public void setGraphics(byte graphics[]) {
		gfx = graphics;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		if (gfx != null) {
			// Items
			g.setColor(Color.white);
			for (int y = 0; y < HEIGHT; y++)
				for (int x = 0; x < WIDTH; x++)
					if (gfx[y * WIDTH + x] == 1)
						g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
		}
	}
	

	public void update() {
		repaint();
	}
	
	public BufferedImage screenshot() {

	    int w = getWidth();
	    int h = getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
	    Graphics2D g = bi.createGraphics();
	    paint(g);
	    return bi;
	}


}



