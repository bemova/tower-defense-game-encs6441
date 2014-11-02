package ui.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ui.Constants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;
	private GamePanel mapPanel;
	JDialog mapSizeDialog;

	
	/**
	 * <b>Initializes the main game frame.</b>
	 */
	public MainFrame() {
		setup();
		setUpMenuBar();
		setVisible(true);
	}

	private void setup() {
		setTitle(Constants.GAME_TITLE);
		refresh();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		mapPanel = new GamePanel(8, 8);
	}

	private void refresh() {
		setSize(700, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	private void setUpMenuBar() {
		menuBar = new JMenuBar();

		mapMenu = new JMenu(Constants.MAP_MENU);

		openMenuItem = new JMenuItem(Constants.LOAD_MAP);

		openMenuItem.addActionListener(this);

		mapMenu.add(openMenuItem);

		menuBar.add(mapMenu);

		setJMenuBar(menuBar);

	}

	/** 
	 * <b>Brings up the file browser dialog.</b>
	 * @param event ActionEvent passed based on user action.
	 */
	public void actionPerformed(ActionEvent event) {
		String menuItem = event.getActionCommand();

		switch (menuItem) {
		case Constants.LOAD_MAP:
			mapPanel.loadMap();
			continueMapDesign();
			break;
		}
	}

	private void continueMapDesign() {
		add(mapPanel, BorderLayout.CENTER);
		refresh();

	}

}
