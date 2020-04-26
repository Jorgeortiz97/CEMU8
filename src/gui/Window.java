package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static final int WIDTH = 64, HEIGHT = 32, SCALE = 10;
	private Screen screen;
	private Keypad keypad;

	public Window() {

		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setVisible(true);
		setTitle("Chip8Emulator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel(new BorderLayout());
		add(contentPane);

		// Adding a menu:
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open"),
				saveItem = new JMenu("Save game..."),
				loadItem = new JMenu("Load game..."),
				pauseItem = new JMenuItem("Pause                               Ctrl+P"),
				resetItem = new JMenuItem("Reset                                Ctrl+F5"),
				scrshotItem = new JMenuItem("Screenshot                     Ctrl+O"),
				exitItem = new JMenuItem("Exit                                      ESC");

	
	
		class OpenListener implements ActionListener {
			private Window w;
			public OpenListener(Window win) {
				w = win;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(w);
				File currentFile = chooser.getSelectedFile();
				if (currentFile != null)
					Controller.getInstance().loadROM(currentFile.getAbsolutePath());
			}
		}
		
		openItem.addActionListener(new OpenListener(this));

		
		JMenuItem si, li;
		class SaveDataListener implements ActionListener {
			private int slot;

			public SaveDataListener(int sl) {
				slot = sl;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().saveData(slot);
			}
		}
		class LoadDataListener implements ActionListener {
			private int slot;

			public LoadDataListener(int sl) {
				slot = sl;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().loadData(slot);
			}
		}

		for (int i = 1; i < 5; i++) {
			si = new JMenuItem("State " + i + "    F" + i);
			li = new JMenuItem("State " + i + "    Ctrl+F" + i);
			si.addActionListener(new SaveDataListener(i - 1));
			li.addActionListener(new LoadDataListener(i - 1));
			saveItem.add(si);
			loadItem.add(li);
		}

		pauseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().pause();
			}
		});

		resetItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().reset();
			}
		});
		
		scrshotItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().screenshot();
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileMenu.add(openItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(pauseItem);
		fileMenu.add(resetItem);
		fileMenu.add(scrshotItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitItem);
		
		mb.add(fileMenu);
		
		JMenu optionsMenu = new JMenu("Options");
		JCheckBox sounds = new JCheckBox("Sound");
		JMenu speeds = new JMenu("Speed...");
		JMenuItem vsItem = new JMenuItem("Very slow"),
				sItem = new JMenuItem("Slow"),
				nItem = new JMenuItem("Normal"),
				fItem = new JMenuItem("Fast"),
				vfItem = new JMenuItem("Very fast");
		
		vsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setSpeed(20);
			}
		});
		sItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setSpeed(8);
			}
		});
		nItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setSpeed(2);
			}
		});
		fItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setSpeed(1);
			}
		});
		vfItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setSpeed(0);
			}
		});
		speeds.add(vsItem); speeds.add(sItem); speeds.add(nItem);
		speeds.add(fItem);speeds.add(vfItem);

		
		
		sounds.setSelected(true);
		sounds.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sounds.isSelected())
					Controller.getInstance().enableSound();
				else
					Controller.getInstance().disableSound();
			}
		});
		optionsMenu.add(sounds);
		optionsMenu.add(speeds);
		mb.add(optionsMenu);
		
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem keysItem = new JMenuItem("Keypad"),
				aboutItem = new JMenuItem("About");
		
		keysItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Keys are '1234-QWER-ASDF-ZXCV'\n"
						+ "You can pause the game pressing Ctrl+P.\n"
						+ "You can save with F1-4 and load with Ctrl+F1-4.\n"
						+ "You can reset a game with Ctrl+F5.");
			}
		});
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Copyrigths JorgeOrtiz. Universidad de Murcia");
			}
		});
		
		helpMenu.add(keysItem);
		helpMenu.add(aboutItem);
		mb.add(helpMenu);
		contentPane.add(mb, BorderLayout.NORTH);

		setSize(WIDTH * SCALE, HEIGHT * SCALE + getInsets().top * 2);

		// Creating objects:
		screen = new Screen();
		keypad = new Keypad();
		contentPane.addKeyListener(keypad);
		contentPane.add(screen, BorderLayout.CENTER);

		contentPane.requestFocus();
		pack();

	}

	public Screen getScreen() {
		return screen;
	}

	public Keypad getKeypad() {
		return keypad;
	}

}
