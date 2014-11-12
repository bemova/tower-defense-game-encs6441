package ui.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.Constants;
import core.applicationservice.mapservices.MapManager;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

public class MainFrame extends JFrame implements ActionListener {

	MapPanel mapPanel;
	
	private JPanel contentPane;
	
	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;
	

	/**
	 * Launch the application.
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
//		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 470);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mapPanel = new MapPanel();
//		mapPanel.setBackground(Color.RED);
		contentPane.add(mapPanel, BorderLayout.CENTER);
		
		addMouseListener(new Handler(mapPanel));
		addMouseMotionListener(new Handler(mapPanel));
		
		GameControllerPanel gameControllerPanel = new GameControllerPanel(mapPanel);
		contentPane.add(gameControllerPanel, BorderLayout.SOUTH);
		
		GameInfoPanel gameInfoPanel = new GameInfoPanel();
		contentPane.add(gameInfoPanel, BorderLayout.NORTH);
		
		Thread tRace = new Thread(gameInfoPanel);
//		  tRace.start();
		  
		
		EmptyBarPanel emptyBarPanel = new EmptyBarPanel();
		contentPane.add(emptyBarPanel, BorderLayout.WEST);
		
		EmptyBarPanel emptyBarPanel_1 = new EmptyBarPanel();
		contentPane.add(emptyBarPanel_1, BorderLayout.EAST);
		setSize(713, 508);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void setUpMenuBar() {
		menuBar = new JMenuBar();

		mapMenu = new JMenu("Actions");

		openMenuItem = new JMenuItem(Constants.LOAD_MAP);

		openMenuItem.addActionListener(this);

		mapMenu.add(openMenuItem);

		menuBar.add(mapMenu);
		
		JMenuItem mntmTest = new JMenuItem("Load Game");
		mapMenu.add(mntmTest);
		
		JMenuItem mntmSameGame = new JMenuItem("Same Game");
		mapMenu.add(mntmSameGame);

		setJMenuBar(menuBar);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String menuItem = event.getActionCommand();

		switch (menuItem) {
		case Constants.LOAD_MAP:
			loadMap();
//			continueMapDesign();
			break;
		}
		
	}
	
	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				MapManager mapManager = new MapManager();
				Map grid = new Map((Grid) mapManager.loadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				mapPanel.setGrid(grid);
				mapPanel.repaint();
				resetGameState();
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}
	
	private void resetGameState() {
		mapPanel.setTowers(new Tower[(mapPanel.getGrid()).getWidth()][(mapPanel.getGrid()).getHeight()]);
		mapPanel.getBank().resetCurrentBalance();
	}	

}
