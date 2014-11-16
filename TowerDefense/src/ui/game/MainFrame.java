package ui.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.Constants;
import core.applicationservice.mapservices.MapManager;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

public class MainFrame extends JFrame implements ActionListener {

	// MapPanel mapPanel;
	LayeredMapPanel mapPanel;

	private JPanel contentPane;

	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;

	private GameInfoPanel gameInfoPanel;
	private EmptyBarPanel emptyBarPanel;
	private GameControllerPanel gameControllerPanel;

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
		mapPanel = new LayeredMapPanel(getMapPanelDimention());
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
			// continueMapDesign();
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
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void resetGameState() {
		mapPanel.setTowers(new Tower[(mapPanel.getGrid()).getWidth()][(mapPanel
				.getGrid()).getHeight()]);
		mapPanel.getBank().resetCurrentBalance();
		mapPanel.resetSize(getMapPanelDimention());
	}

	
}
