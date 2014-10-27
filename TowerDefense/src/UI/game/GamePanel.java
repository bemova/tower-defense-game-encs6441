package UI.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.CanvaObject;
import UI.Constants;
import UI.towerdesign.SimpleInspection;
import core.applicationService.mapServices.MapManager;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.applicationService.warriorServices.TowerFactory;
import core.contract.MapConstants;
import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
//import core.domain.maps.VisualGrid;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;

public class GamePanel extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private BankManager bank;

	private boolean addTower;

	private Tower tower;
	private Tower[][] towers;
	JButton modernTowerBtn;
	JButton ancientTowerBtn;
	JButton kingTowerBtn;

	Color colorToDrawGreed;
	GridCellContentType cellContent;

	Map grid;

	MapManager mapManager;
	CanvaObject canvas;
	JPanel mapContainer;

	JPanel toolBoxContainer = new JPanel();

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
		toolBoxContainer.add(modernTowerBtn);
		toolBoxContainer.add(ancientTowerBtn);
		toolBoxContainer.add(kingTowerBtn);

		mapManager = new MapManager();

		modernTowerBtn.addActionListener(this);
		ancientTowerBtn.addActionListener(this);
		kingTowerBtn.addActionListener(this);
		canvas.addMouseListener(this);

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
		this.width = width;
		this.height = height;

		this.bank = BankManager.getInstance();

		this.addTower = false;

		modernTowerBtn = new JButton(Constants.MODERN_TOWER);
		ancientTowerBtn = new JButton(Constants.ANCIENT_TOWER);
		kingTowerBtn = new JButton(Constants.KING_TOWER);

		towers = new Tower[width][height];
		grid = new Map(width, height);

		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();

		toolBoxContainer = new JPanel();

	}

	@SuppressWarnings("serial")
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
			if (addTower) {
				if (grid.getCell(x, y) == GridCellContentType.SCENERY) {
					TowerFactory factory = new TowerFactory();
					Tower tower = factory.getTower("ModernTower", TowerLevel.two);
					if (tower.cost() < bank.getBalance() - bank.getCurrentBalance()) {
						bank.setCurrentBalance(tower.cost());
						towers[x][y] = tower;
						grid.updateTowers(towers);
						draw(x, y);
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "you don't have enough money :(", "Alert",
						        JOptionPane.WARNING_MESSAGE);
					}
					addTower = false;
				}
			} else {
				if (towers[x][y] != null) {
					SimpleInspection inspection = new SimpleInspection(
							towers[x][y]);
					inspection.setVisible(true);
					JDialog jd = new JDialog();
					jd.setTitle("Tower Inspection");
					jd.setVisible(true);
					jd.setSize(300, 280);
					jd.setLocationRelativeTo(this);

					jd.setLayout(new FlowLayout());
					jd.add(inspection);

				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case Constants.MODERN_TOWER:
			tower(Constants.MODERN_TOWER);
			break;
		case Constants.ANCIENT_TOWER:
			tower(Constants.ANCIENT_TOWER);
			break;
		case Constants.KING_TOWER:
			tower(Constants.KING_TOWER);
			break;
		}
	}

	private void tower(String towerType) {
		try {
			addTower = true;
			switch (towerType) {
			case Constants.MODERN_TOWER:
				colorToDrawGreed = modernTowerBtn.getBackground();
				break;
			case Constants.ANCIENT_TOWER:
				colorToDrawGreed = ancientTowerBtn.getBackground();
				break;
			case Constants.KING_TOWER:
				colorToDrawGreed = kingTowerBtn.getBackground();
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
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");
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
				resetTowerGrid();
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void resetTowerGrid() {
		towers = new Tower[width][height];
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
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

}
