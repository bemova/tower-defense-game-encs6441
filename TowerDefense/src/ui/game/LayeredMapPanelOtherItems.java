package ui.game;

import infrastructure.loggin.Log4jLogger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.towerdesign.SimpleInspection;
import core.applicationservice.informerservices.imp.DefenderInformer;
import core.applicationservice.locationservices.PositionService;
import core.applicationservice.mapservices.pathfinder.PathService;
import core.applicationservice.warriorservices.TowerFactory;
import core.applicationservice.warriorservices.WaveFactory;
import core.contract.AccountConstants;
import core.contract.DefenderConstants;
import core.contract.WaveConstants;
import core.domain.account.BankManager;
import core.domain.account.LifeManager;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.behaviourimp.RegularMove;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;
import core.domain.waves.Wave;

/**
 * @author Team 5
 * This Class is a Jpanel that gets rendered as a layer in LayeredMapPanel class. It is responsible for tasks related to Towers and Critters on the ui side.
 */

public class LayeredMapPanelOtherItems extends JPanel implements Observer, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tower[][] towers;
	private int x, y;
	private BankManager bank;
	private long availFunds;
	private Point mapTopLeft;
	private Point mapButtomRight;
	private GridMap grid;
	private SimpleInspection inspection;
	public Thread critterT, mapT;
	private Cell cell;
	private Position[] path;
	private Wave wave;

	private Icon[] critterImage;

	private boolean waveStarted;

	private DefenderInformer informer;
	private Tower defender;
	private Critter target;

	private int escapedCritter = 0;

	private Map<Tower, Critter> defenderTargetPair;

	private GameInfoPanel gameInfoPanel;

	private List<Critter> currentWaveAlienList;

	private MainFrame mainFrame;
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * @param dimension height and width of the panel
	 * @param gameInfoPanel a reference to the instance of the game info panel that appears on top of the game ui (life, bank, wave info) 
	 * @param mainFrame a reference to the instance of the main frame that contains ui components.
	 */
	public LayeredMapPanelOtherItems(Dimension dimension, GameInfoPanel gameInfoPanel, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new GridMap(1, 1);
		this.bank = BankManager.getInstance();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());

		mapT = new Thread(this);
		mapT.start();
		setOpaque(false);
		setDimension(dimension);
		critterImage = new Icon[WaveConstants.WAVE_SIZE];
		informer = new DefenderInformer();
		defenderTargetPair = new HashMap<Tower, Critter>();

	}

	/**
	 * this re-initializes the grid when a map is loaded/
	 * @param grid the map that is loaded from a file
	 */
	public void setGrid(GridMap grid) {
		cell = new Cell();
		this.grid = grid;

		towers = new Tower[grid.getWidth()][grid.getHeight()];

	}

	/**
	 * Calculates the critters' starting point on the map in pixels.
	 * @return initial position of the critters. 
	 */
	protected Position calcCritterStartingPoint() {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		Position entryPoint = grid.getEntranceLocation();
		if (entryPoint != null) {
			entryPoint = new Position((int) (initX + (entryPoint.getX() * grid.getUnitSize())),
					(int) (initY + (entryPoint.getY() * grid.getUnitSize())));

			// temp
			PathService pathService = new PathService();
			this.path = pathService.pathFinder(grid.getContent());
			// temp end

			return entryPoint;
		}
		return null;
	}

	/**
	 * This method converts a map cell to a pixel position on the screen
	 * @param cell a cell position on the grid
	 * @return pixel position on the screen
	 */
	protected Position convertCellToPixel(Position cell) {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		Position entryPoint;
		if (cell != null) {
			entryPoint = new Position((int) (initX + (cell.getX() * grid.getUnitSize())),
					(int) (initY + (cell.getY() * grid.getUnitSize())));

			// temp
			PathService pathService = new PathService();
			this.path = pathService.pathFinder(grid.getContent());
			// temp end

			entryPoint.setX(entryPoint.getX() + grid.getUnitSize() / 2);
			entryPoint.setY(entryPoint.getY() + grid.getUnitSize() / 2);
			return entryPoint;
		}
		return null;
	}

	/**
	 * This method draws everything that needs to be drawn at this iteration.
	 * @param g Graphics component
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth() * grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				int xCoordinate = grid.getUnitSize() * x + initX;
				int yCoordinate = grid.getUnitSize() * y + initY;
				if (grid.getTowers() == null) {
					grid.setTowers(new Tower[1][1]);
				}
				if (grid.getCell(x, y).getValue() == 11) {
					cell.draw(g, grid.getCell(x, y), grid.getTowers(), xCoordinate, yCoordinate, x, y);
				}
			}
		}

		if (waveStarted) {
			for (int i = 0; i < currentWaveAlienList.size(); i++) {
				Position pos = ((RegularMove) (currentWaveAlienList.get(i).getMovingBehaviour())).getPixelPosition();
				new CritterShape().draw(g, critterImage[i], pos.getX(), pos.getY());
			}

			Map<Tower, Critter> map = defenderTargetPair;
			Iterable<Entry<Tower, Critter>> its = defenderTargetPair.entrySet();
			for (Entry<Tower, Critter> pairs : its) {
				Tower t2 = pairs.getKey();
				Critter c = pairs.getValue();

				PositionService positionService = new PositionService();
				TowerFactory factory = new TowerFactory();
				int range = factory.getRange(t2);
				if (positionService.isInRange(t2.getTowerPosition(), c.getPath()[c.getCurrentPosition()], range)) {
					new LineBullet().draw(g, convertCellToPixel(t2.getTowerPosition()),
							convertCellToPixel(path[c.getCurrentPosition()]));
				}
				defenderTargetPair = map;
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

//	/**
//	 * 
//	 * @param event
//	 */
//	@Override
//	public void mouseClicked(MouseEvent event) {
//		event.consume();
//		System.out.println("clicked");
//
//		if (x <= grid.getWidth() & y <= grid.getHeight()) {
//			boolean addTowerFlag = SelectedTower.getAddTowerFlag();
//			if (addTowerFlag) {
//				addTower(x, y);
//			} else {
//				towerUpgradePanels();
//			}
//		}
//
//	}

//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		System.out.println("entered");
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		System.out.println("exited");
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		System.out.println("perssed");
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		System.out.println("released");
//
//	}

	/**
	 * Displays the tower upgrade panel. 
	 */
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

	/**
	 * This method adds a tower to the map and also displays in the UI. Before adding the tower it makes sure the user has enough money.
	 * @param x x coordinate of the cell that towers is going to be placed. 
	 * @param y y coordinate of the cell that towers is going to be placed.
	 */
	private void addTower(int x, int y) {

		if (grid.getCell(x, y) == GridCellContentType.SCENERY) {
			TowerFactory factory = new TowerFactory();
			Tower tower;
			String towerType = SelectedTower.getTowerType();
			switch (towerType) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE, TowerLevel.one);
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.ANCIENT_TOWER_TYPE, TowerLevel.one);
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.KING_TOWER_TYPE, TowerLevel.one);
				break;

			default:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE, TowerLevel.one);
			}

			tower.setShootingStrategy(DefenderConstants.NearToEnd_Strategy);
			if (tower.cost() < bank.getBalance() - bank.getCurrentBalance()) {
				bank.setCurrentBalance(tower.cost());
				availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
				gameInfoPanel.setBank((int) availFunds);
				towers[x][y] = tower;
				tower.setTowerPosition(new Position(x, y));
				grid.setCell(x, y, GridCellContentType.TOWER);
				grid.setTowers(towers);
				informer.registerObserver((TowerFeatureDecorator) tower);
				((TowerFeatureDecorator) tower).addObserver(this);
				repaint();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "you don't have enough money :(", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}
			SelectedTower.setInstance(SelectedTower.getTowerType(), SelectedTower.getTower(), false);
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		System.out.println("action");
//	}

	public void towerOperation() {

		boolean addTowerFlag = SelectedTower.getAddTowerFlag();
		if (addTowerFlag) {
			addTower(x, y);
		} else {
			towerUpgradePanels();
		}

	}

	/**
	 * When a mouse click occurs, this method converts the pixel location of the pointer to a cell location. 
	 * @param mouseX x coordinate of mouse pointer
	 * @param mouseY y coordinate of mouse pointer
	 */
	public void setCellLocation(int mouseX, int mouseY) {
		int i = (mouseX - (int) mapTopLeft.getX() - 75 / 2) / grid.getUnitSize();
		int j = (mouseY - (int) mapTopLeft.getY() - 120) / grid.getUnitSize();
		this.x = i;
		this.y = j;
	}

	public Tower[][] getTowers() {
		return towers;
	}

	public GridMap getGrid() {
		return grid;
	}

	public void setTowers(Tower[][] towers) {
		this.towers = towers;
	}

	public BankManager getBank() {
		return bank;
	}

	/**
	 * <b>This method updates the tower stats and the bank balance and removes
	 * the a tower from the map. It also performs the required actions after a target has been identified for a tower.</b>
	 * 
	 * @param arg1
	 *            is object is of type tower perform operation
	 * @param arg0
	 *            observer object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof SimpleInspection) {
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

		// shoot
		if (arg0 instanceof TowerFeatureDecorator) {
			target = ((TowerFeatureDecorator) arg0).getTarget();
			defender = ((TowerFeatureDecorator) arg0).getDefender();
			shoot(defender, target);
			removeDeadCritters();
		}
	}

	
	/**
	 * This method does the tasks that happen when a critter is shot by a tower, like decreasing life.  
	 * @param defender tower (shooter)
	 * @param target critter (to be shot)
	 */
	private void shoot(Tower defender, Critter target) {
		defenderTargetPair.put(defender, target);
		TowerFactory factory = new TowerFactory();
		String defenderType = factory.getDecoratedName(defender.objectDetials());
		switch (defenderType) {
		case DefenderConstants.KING_TOWER_TYPE:
			target.setLife(target.getLife() / 2);
			break;
		case DefenderConstants.MODERN_TOWER_TYPE:
			target.setLife(target.getLife() - 1);// must be: -tower power/impact
			break;
		case DefenderConstants.ANCIENT_TOWER_TYPE:
			PositionService positionService = new PositionService();
			int range = factory.getRange(defender);
			if (positionService.isInRange(defender.getTowerPosition(), target.getPath()[target.getCurrentPosition()],
					range))
				((RegularMove) target.getMovingBehaviour()).setFreezeTime(100);
			break;
		}
		try {
		} catch (Exception e2) {
			logger.writer("shooting :" + defender.Id + " --> " + target.Id + "(" + target.getCurrentPosition() + ")",
					e2);

			System.out.println("shooting :" + defender.Id + " --> " + target.Id + "(" + target.getCurrentPosition()
					+ ")");
		}

	}

	/**
	 * This method is called when the user has finished upgrading a tower.
	 */
	private void upgradeTower() {
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		gameInfoPanel.setBank((int) availFunds);
	}

	/**
	 * When a user sells a tower, this method get's called to remove it from the map.
	 * @param x x location of the tower to be removed
	 * @param y y location of the tower to be removed.
	 */
	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight()) && (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			gameInfoPanel.setBank((int) availFunds);

			informer.removeObserver((TowerFeatureDecorator) towers[x][y]);
			towers[x][y] = null;
			grid.setTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			repaint();
		}
	}

	/** 
	 * This method runs as long as the thread is running to update the screen and all properties of this class.
	 */
	public void run() {
		while (true) {
			System.out.println("thread running");
			if (waveStarted) {
				for (int j = 0; j < currentWaveAlienList.size(); j++) {
					Critter critter = currentWaveAlienList.get(j);
					if (critter.getCurrentPosition() != ((RegularMove) critter.getMovingBehaviour()).getPath().length - 1) {
						critter.performMovingBehaviour();
						int i = ((RegularMove) critter.getMovingBehaviour()).getCurrentPosition();
						Position p = path[i];
						critter.setCurrentPosition(i);
						informer.setAlienPosition(p.getX(), p.getY(), critter, towers[x][y].getShootingStrategy());

					} else {
						System.out.println("At exit point.");
						escapedCritter++;
						updatePlayerLife(escapedCritter);
						currentWaveAlienList.remove(j);

					}
				}
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method checks the wave to see if there are any critters left in it.
	 * @return true if there all critters are killed.
	 */
	private boolean isWaveComplete() {
		if (currentWaveAlienList.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method is called every time a critter is shot. it removes dead critters from the map. 
	 */
	private void removeDeadCritters() {
		for (int i = 0; i < currentWaveAlienList.size(); i++) {
			Critter c = currentWaveAlienList.get(i);
			if (c.getLife() <= 0) {
				bank.addBalance(((long) c.lifeBooster()));
				availFunds = (long) (this.bank.getBalance() + c.lifeBooster());
				gameInfoPanel.setBank((int) availFunds);
				for (Tower[] t1 : towers) {
					for (Tower t : t1) {
						if (t != null) {
							Map<Critter, Position> map = ((TowerFeatureDecorator) t).getCrittersLocation();

							map.remove(c);
							((TowerFeatureDecorator) t).setCrittersLocation(map);
							System.out.println(c.Id);
						}
					}
				}
				currentWaveAlienList.remove(i);
				if (isWaveComplete()) {
					waveCompleted();
				}
			}
		}
	}

	private void waveCompleted() {
		System.out.println("Wave is completed!");
		mainFrame.getGameControllerPanel().wavaCompleted();
		waveStarted = false;

	}

	/**
	 * when a critter reaches the end of the path, it decreases the player's life. 
	 * @param escapedCritters the number of critters that have reached the exit point.
	 */
	private void updatePlayerLife(int escapedCritters) {
		LifeManager lifeManager = LifeManager.getInstance();

		int life = lifeManager.getLife() - escapedCritters;
		System.out.println("life: " + life);
		gameInfoPanel.setLife(life);
		if (life <= 0) {
			gameOver();
		}
	}

	private void gameOver() {
		System.out.println("Game Over");
		JOptionPane.showMessageDialog(null, "Game Over");
		// mapT.stop();
		System.exit(0);
	}

	@SuppressWarnings("deprecation")
	public void pauseGame() {
		mapT.suspend();
	}

	protected Point getMapTopLeft() {
		return mapTopLeft;
	}

	protected void setMapTopLeft(Point mapTopLeft) {
		this.mapTopLeft = mapTopLeft;
	}

	protected Point getMapButtomRight() {
		return mapButtomRight;
	}

	protected void setMapButtomRight(Point mapButtomRight) {
		this.mapButtomRight = mapButtomRight;
	}

	protected void setDimension(Dimension mapPanelDimension) {
		setSize(mapPanelDimension);

	}

	public void performScene() {
	}

	/**
	 * This method is called when the user clicks on the "new wave" button.
	 */
	public void startFoolishWave() {
		WaveFactory waveFactory = new WaveFactory();
		Position[] path = new PathService().pathFinder(grid.getContent());
		wave = waveFactory.getWave("FoolishCritter", calcCritterStartingPoint(), path);

		ClassLoader classLoader = getClass().getClassLoader();
		File file;

		currentWaveAlienList = new ArrayList<>();
		for (int i = 0; i < wave.aliens.size(); i++) {
			currentWaveAlienList.add(wave.aliens.get(i));
		}

		for (int i = 0; i < currentWaveAlienList.size(); i++) {
			((RegularMove) (currentWaveAlienList.get(i).getMovingBehaviour())).setFreezeTime(i * 100);

			file = new File(classLoader.getResource(currentWaveAlienList.get(i).display()).getFile());
			critterImage[i] = new ImageIcon(file.getPath());
		}
		waveStarted = true;

	}

	@SuppressWarnings("deprecation")
	public void resumeGame() {
		mapT.resume();
	}

}
