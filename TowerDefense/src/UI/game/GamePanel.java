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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import UI.CanvaObject;
import UI.Constants;
import UI.towerdesign.SimpleInspection;
import core.applicationService.mapServices.MapManager;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.applicationService.warriorServices.TowerFactory;
import core.contract.DefenderConstants;
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

	private String towerType;
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

	JDialog jd;
	
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

		modernTowerBtn = new JButton(DefenderConstants.MODERN_TOWER_TYPE);
		ancientTowerBtn = new JButton(DefenderConstants.ANCIENT_TOWER_TYPE);
		kingTowerBtn = new JButton(DefenderConstants.KING_TOWER_TYPE);

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
					Tower tower;
					switch (towerType) {
					case DefenderConstants.MODERN_TOWER_TYPE:
						tower = factory.getTower(
								DefenderConstants.MODERN_TOWER_TYPE,
								TowerLevel.one);
						break;
					case DefenderConstants.ANCIENT_TOWER_TYPE:
						tower = factory.getTower(
								DefenderConstants.ANCIENT_TOWER_TYPE,
								TowerLevel.one);
						break;
					case DefenderConstants.KING_TOWER_TYPE:
						tower = factory.getTower(
								DefenderConstants.KING_TOWER_TYPE,
								TowerLevel.one);
						break;

					default:
						tower = factory.getTower(
								DefenderConstants.MODERN_TOWER_TYPE,
								TowerLevel.one);
					}

					if (tower.cost() < bank.getBalance()
							- bank.getCurrentBalance()) {
						bank.setCurrentBalance(tower.cost());
						towers[x][y] = tower;
						grid.updateTowers(towers);
						draw(x, y);
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"you don't have enough money :(", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}
					addTower = false;
				}
			} else {
				if (towers[x][y] != null) {
					SimpleInspection inspection = new SimpleInspection(
							towers[x][y]);
					inspection.setVisible(true);
					jd = new JDialog();
					jd.setTitle("Tower Inspection");
					jd.setVisible(true);
					jd.setSize(300, 280);
					jd.setLocationRelativeTo(this);
					// jd.setDefaultCloseOperation(closeInspector(inspection,x,y));
					jd.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							closeInspector(inspection, x, y);

						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
					jd.setLayout(new FlowLayout());
					jd.add(inspection);

				}
			}
		}
	}

	private int closeInspector(SimpleInspection inspection, int x, int y) {
		towers[x][y] = inspection.getTower();
		grid.updateTowers(towers);
		jd.dispose();
		return 0;
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
			addTower = true;
			switch (towerType) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				colorToDrawGreed = modernTowerBtn.getBackground();
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				colorToDrawGreed = ancientTowerBtn.getBackground();
				break;
			case DefenderConstants.KING_TOWER_TYPE:
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
