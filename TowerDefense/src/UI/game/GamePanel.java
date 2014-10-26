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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.CanvaObject;
import UI.Constants;
import UI.towerdesign.TowerManagerPanel;
import core.applicationService.mapServices.MapManager;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.applicationService.warriorServices.TowerFactory;
import core.contract.MapConstants;
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

	private boolean addTower;

	private Tower tower;
	private Tower[][] towers;
	JButton towerButton;

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

		towerButton.setBackground(MapConstants.MODERN_TOWER_COLOR);
		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(towerButton);

		mapManager = new MapManager();

		towerButton.addActionListener(this);
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

		this.addTower = false;

		towerButton = new JButton(Constants.MODERN_TOWER);

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
				TowerFactory factory = new TowerFactory();
				towers[x][y] = factory.getTower("ModernTower", TowerLevel.one);
				grid.updateTowers(towers);
				draw(x, y);
				addTower = false;
			} else {
				if (towers[x][y] != null) {
					TowerManagerPanel towerPanel = new TowerManagerPanel(
							towers[x][y]);
					add(towerPanel, BorderLayout.WEST);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case Constants.MODERN_TOWER:
			tower();
			break;
		}
	}

	private void tower() {
		try {
			addTower = true;
			colorToDrawGreed = towerButton.getBackground();
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
				grid = new Map((Grid) mapManager.LoadMapFromFile(openFile.getSelectedFile()
						.getAbsolutePath()));
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
