package UI.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.CanvaObject;
import UI.towerdesign.SimpleInspection;
import UI.towerdesign.TowerInfoPanel;
import UI.towerdesign.TowerManagerPanel;
import core.applicationService.mapServices.MapManager;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.applicationService.warriorServices.TowerFactory;
import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Observer, ActionListener,
		MouseListener {
	private JDialog towerInfo;
	private int width;
	private int height;
	private BankManager bank;

	private boolean addTowerFlag;

	private String towerType;
	private Tower[][] towers;
	private JButton modernTowerBtn;
	private JButton ancientTowerBtn;
	private JButton kingTowerBtn;

	private JLabel bankLbl;
	private Color colorToDisplayTower;
	private GridCellContentType cellContent;

	private Map grid;

	private MapManager mapManager;
	private CanvaObject canvas;
	private JPanel mapContainer;

	private JPanel toolBoxContainer = new JPanel();

	private TowerManagerPanel customTowerFeatures;
	private SimpleInspection inspection;
	private int x, y;
	private long availFunds;

	private TowerInfoPanel towerInfoPanel;

	@SuppressWarnings("unused")
	private GamePanel() {
	}

	public GamePanel(int width, int height) {

		initialize(width, height);
		setLayout(new BorderLayout());

		modernTowerBtn.setBackground(MapConstants.MODERN_TOWER_COLOR);
		ancientTowerBtn.setBackground(MapConstants.ANCIENT_TOWER_COLOR);
		kingTowerBtn.setBackground(MapConstants.KING_TOWER_COLOR);

		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(bankLbl);
		toolBoxContainer.add(modernTowerBtn);
		toolBoxContainer.add(ancientTowerBtn);
		toolBoxContainer.add(kingTowerBtn);
		mapManager = new MapManager();

		toolBoxContainer.add(towerInfoPanel);

		modernTowerBtn.addActionListener(this);
		ancientTowerBtn.addActionListener(this);
		kingTowerBtn.addActionListener(this);
		canvas.addMouseListener(this);

		modernTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("ModernTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		ancientTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("AncientTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		kingTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("KingTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		int mapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int mapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(mapPixelWidth, mapPixelHeight);
		mapContainer.setPreferredSize(new Dimension(mapPixelWidth,
				mapPixelHeight));
		mapContainer.add(canvas);

		add(new JPanel(), BorderLayout.NORTH);
		add(toolBoxContainer, BorderLayout.EAST);
		add(mapContainer, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.SOUTH);
		setVisible(true);

	}

	private void initialize(int width, int height) {

		towerInfoPanel = new TowerInfoPanel();

		this.bank = BankManager.getInstance();

		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		String str = new Long(availFunds).toString();
		this.bankLbl = new JLabel("$" + str);
		this.width = width;
		this.height = height;

		this.addTowerFlag = false;

		modernTowerBtn = new JButton(DefenderConstants.MODERN_TOWER_TYPE);
		ancientTowerBtn = new JButton(DefenderConstants.ANCIENT_TOWER_TYPE);
		kingTowerBtn = new JButton(DefenderConstants.KING_TOWER_TYPE);

		towers = new Tower[width][height];
		grid = new Map(width, height);

		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();

		toolBoxContainer = new JPanel();

	}

	private void displayTowerInfo(String towerType) {
		TowerFactory factory = new TowerFactory();

		Tower tower = factory.getTower(towerType, TowerLevel.one);

		towerInfoPanel = new TowerInfoPanel(tower);
		// toolBoxContainer.add(towerInfoPanel);
		towerInfo = new JDialog(new Frame(),"Tower info");
		towerInfo.add(towerInfoPanel);
		towerInfo.setSize(150, 200);
		towerInfo.setVisible(true);

	}

	private void hideTowerInfo() {
		towerInfo.dispose();
	}

	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	public void setGridSize(int width, int height) {
		grid.setSize(width, height);
		canvas.updateGrid(grid);
	}

	public void design(int width, int height) {
		try {
			grid.setSize(width, height);
			canvas.updateGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private int[] cellCoordinate(int pixelX, int pixelY) {
		int i = pixelX / grid.getUnitSize();
		int j = pixelY / grid.getUnitSize();
		int[] coordinate = { i, j };
		this.x = i;
		this.y = j;
		return coordinate;
	}

	private void draw(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) != cellContent)) {
			grid.setCell(x, y, cellContent);
			canvas.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {

		int[] coordinate = cellCoordinate(event.getX(), event.getY());
		int x = coordinate[0];
		int y = coordinate[1];

		if (x <= width & y <= height) {
			if (addTowerFlag) {
				addTower(x, y);
			} else {
				towerUpgradePanels();
			}
		}
	}

	private void towerUpgradePanels() {
		if (towers[x][y] != null) {

			if (inspection != null) {
				inspection.close();
				inspection = null;
			}
			// customTowerFeatures = new TowerManagerPanel(towers[x][y]);
			inspection = new SimpleInspection(towers[x][y]);
			inspection.addObserver(this);
		}
	}

	private void addTower(int x, int y) {

		if (grid.getCell(x, y) == GridCellContentType.SCENERY) {
			TowerFactory factory = new TowerFactory();
			Tower tower;
			switch (towerType) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.ANCIENT_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.KING_TOWER_TYPE,
						TowerLevel.one);
				break;

			default:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
			}

			if (tower.cost() < bank.getBalance() - bank.getCurrentBalance()) {
				bank.setCurrentBalance(tower.cost());
				availFunds = this.bank.getBalance()
						- this.bank.getCurrentBalance();
				String str = new Long(availFunds).toString();
				this.bankLbl.setText("$" + str);
				towers[x][y] = tower;
				grid.updateTowers(towers);
				draw(x, y);
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"you don't have enough money :(", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}
			addTowerFlag = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case DefenderConstants.MODERN_TOWER_TYPE:
			towerType = DefenderConstants.MODERN_TOWER_TYPE;
			tower(towerType);
			break;
		case DefenderConstants.ANCIENT_TOWER_TYPE:
			towerType = DefenderConstants.ANCIENT_TOWER_TYPE;
			tower(towerType);
			break;
		case DefenderConstants.KING_TOWER_TYPE:
			towerType = DefenderConstants.KING_TOWER_TYPE;
			tower(towerType);
			break;
		}
	}

	private void tower(String towerType) {
		try {
			addTowerFlag = true;
			switch (towerType) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				colorToDisplayTower = modernTowerBtn.getBackground();
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				colorToDisplayTower = ancientTowerBtn.getBackground();
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				colorToDisplayTower = kingTowerBtn.getBackground();
				break;
			}
			cellContent = GridCellContentType.TOWER;
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected void setMapSize(int width, int height) {
		try {
			// validation part
			StartEndChecker checker = new StartEndChecker();
			if (!checker.isCorrectSize(height, width))
				// end of validation

				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				grid = new Map((Grid) mapManager.LoadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				canvas.updateGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				resetGameState();
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void resetGameState() {
		towers = new Tower[width][height];
		bank.resetCurrentBalance();
	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		switch (inspection.getPerformedAction()) {
		case "Upgrade":
			upgradeTower();
			break;
		case "Sell":
			clearTower(x, y);
			break;
		default:
			break;
		}

	}

	private void upgradeTower() {
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		String str = new Long(availFunds).toString();
		this.bankLbl.setText("$" + str);
	}

	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			String str = new Long(availFunds).toString();
			this.bankLbl.setText("$" + str);
			towers[x][y] = null;
			grid.updateTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			canvas.repaint();
		}
	}
}
