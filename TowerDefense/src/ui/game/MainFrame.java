package ui.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.Constants;
import core.applicationservice.gameservices.GameLogManager;
import core.applicationservice.mapservices.MapManager;
import core.domain.account.LifeManager;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.waves.Wave;

public class MainFrame extends JFrame implements ActionListener {

	// MapPanel mapPanel;
	LayeredMapPanel mapPanel;

	private JPanel contentPane;

	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;
	private JMenuItem loadGameMenutItem;
	private JMenuItem saveGameMenuItem; 

	private GameInfoPanel gameInfoPanel;
	private EmptyBarPanel emptyBarPanel;
	private GameControllerPanel gameControllerPanel;
	private JMenuItem mntmLogViewer;
	
	private LogViewer logViewerDialog;

	/**
	 * Launch the application.
	 * @param args for running
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		
		

		setUpMenuBar();

		setTitle(Constants.GAME_TITLE);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 470);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		gameInfoPanel = new GameInfoPanel();
		contentPane.add(gameInfoPanel, BorderLayout.NORTH);

		emptyBarPanel = new EmptyBarPanel();
		contentPane.add(emptyBarPanel, BorderLayout.WEST);

		EmptyBarPanel emptyBarPanel_1 = new EmptyBarPanel();
		contentPane.add(emptyBarPanel_1, BorderLayout.EAST);

		

		
		// mapPanel = new MapPanel();
		mapPanel = new LayeredMapPanel(getMapPanelDimention(),gameInfoPanel, this);
		// mapPanel.setBackground(Color.RED);
		contentPane.add(mapPanel, BorderLayout.CENTER);

		gameControllerPanel = new GameControllerPanel(mapPanel);
		contentPane.add(gameControllerPanel, BorderLayout.SOUTH);
		
		addMouseListener(new Handler(mapPanel));
		addMouseMotionListener(new Handler(mapPanel));


		setSize(713, 508);
//		 setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
		
		
		
		this.addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		        System.out.println("resized");    
		        mapPanel.resetSize(getMapPanelDimention());
		    }

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public GameControllerPanel getGameControllerPanel(){
		return gameControllerPanel;
	}
	public Dimension getMapPanelDimention() {
		int width = gameInfoPanel.getWidth() - (emptyBarPanel.getWidth() * 2);
		int height = emptyBarPanel.getHeight();

		return new Dimension(width, height);
	}

	private void setUpMenuBar() {
		menuBar = new JMenuBar();

		mapMenu = new JMenu("Actions");

		openMenuItem = new JMenuItem(Constants.LOAD_MAP);
		openMenuItem.addActionListener(this);
		mapMenu.add(openMenuItem);

		menuBar.add(mapMenu);

		loadGameMenutItem = new JMenuItem(Constants.LOAD_GAME);
		loadGameMenutItem.addActionListener(this);
		mapMenu.add(loadGameMenutItem);

		saveGameMenuItem = new JMenuItem(Constants.SAVE_GAME);
		saveGameMenuItem.addActionListener(this);
		mapMenu.add(saveGameMenuItem);
		
		mntmLogViewer = new JMenuItem("Log Viewer");
		mntmLogViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				try {
					
					if(logViewerDialog != null){
						logViewerDialog.dispose();
					}
					logViewerDialog = new LogViewer(GameLogManager.getInstance());
					logViewerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					logViewerDialog.setSize(500, 500);
					logViewerDialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		mapMenu.add(mntmLogViewer);

		setJMenuBar(menuBar);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String menuItem = event.getActionCommand();

		switch (menuItem) {
		case Constants.LOAD_MAP:
			loadMap();
			// continueMapDesign();
			break;
		case Constants.SAVE_GAME:
			saveGame();
			break;
		case Constants.LOAD_GAME:
			loadGame();
			break;
		}

	}

	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				MapManager mapManager = new MapManager();
				GridMap grid = new GridMap((Grid) mapManager.loadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				mapPanel.setGrid(grid);
				resetGameState();
				mapPanel.repaint();
				GameLogManager.getInstance().addWaveLog(1, "New wave started");
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}
	
	protected void saveGame(){
		mapPanel.saveGame();
//		try {
//			mapPanel.getOtherItemsPanel().pauseGame();
//			JFileChooser openFile = new JFileChooser();
//			if (JFileChooser.APPROVE_OPTION == openFile.showSaveDialog(null)) {
//				
//				try {
//					FileOutputStream fileOut = new FileOutputStream(openFile.getSelectedFile().getAbsolutePath());
//					ObjectOutputStream out = new ObjectOutputStream(fileOut);
//					out.writeObject(mapPanel.getGrid());
//					out.writeObject(mapPanel.getBank().getBalance());
//					out.writeObject(mapPanel.getBank().getCurrentBalance());
//					out.writeObject(LifeManager.getInstance().getLife());
//					out.writeObject(mapPanel.getGameInfoPanel().getWave());
//					out.writeObject(mapPanel.getOtherItemsPanel().getWave());
//					out.close();
//					fileOut.close();
//					System.out.printf("Serialized data is saved in /tmp/employee.ser");
//				} catch (IOException i) {
//					i.printStackTrace();
//				}
//				
//			}
//			mapPanel.getOtherItemsPanel().resumeGame();
//		} catch (java.lang.Exception ex) {
//			JOptionPane.showMessageDialog(null, ex.getMessage());
//		}
	}
	
	protected void loadGame(){
		mapPanel.loadGame();
//		try {
//			mapPanel.getOtherItemsPanel().pauseGame();
//			JFileChooser saveFile = new JFileChooser();
//			if (JFileChooser.APPROVE_OPTION == saveFile.showOpenDialog(null)) {
//				try {
//					FileInputStream fileIn = new FileInputStream(saveFile.getSelectedFile().getAbsolutePath());
//					ObjectInputStream in = new ObjectInputStream(fileIn);
//
//					GridMap map = (GridMap)in.readObject();
//					mapPanel.setGrid(map);
//										
//					mapPanel.getBank().setBalance((long)in.readObject());
//					mapPanel.getBank().setCurrentBalance((long)in.readObject());
//					int life = (int)in.readObject();
//					LifeManager.getInstance().setLife(life);
//					mapPanel.getOtherItemsPanel().getGameInfoPanel().setLife(life);
//					
//					int wave = (int)in.readObject();
//					mapPanel.getGameInfoPanel().setWave(wave);
//					
//					mapPanel.getOtherItemsPanel().setWave((Wave)in.readObject());
//					int bank = (int)(mapPanel.getBank().getBalance() - mapPanel.getBank().getCurrentBalance());
//					mapPanel.getOtherItemsPanel().getGameInfoPanel().setBank(bank);
//					
//					mapPanel.setTowers(map.getTowers());
//					mapPanel.resetSize(getMapPanelDimention());
//					
//					mapPanel.repaint();
//					in.close();
//					fileIn.close();
//				} catch (IOException i) {
//					i.printStackTrace();
//					return;
//				} catch (ClassNotFoundException c) {
//					System.out.println("Employee class not found");
//					c.printStackTrace();
//					return;
//				}
//			}
//			mapPanel.getOtherItemsPanel().resumeGame();
//		} catch (java.lang.Exception ex) {
//			JOptionPane.showMessageDialog(null, ex.getMessage());
//		}
		
	}

	private void resetGameState() {
		mapPanel.setTowers(new Tower[(mapPanel.getGrid()).getWidth()][(mapPanel
				.getGrid()).getHeight()]);
		mapPanel.getBank().resetCurrentBalance();
		mapPanel.resetSize(getMapPanelDimention());
	}

	
}
