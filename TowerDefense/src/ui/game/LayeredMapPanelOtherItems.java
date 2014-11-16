package ui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.towerdesign.SimpleInspection;
import core.applicationservice.informerservices.imp.DefenderInformer;
import core.applicationservice.mapservices.pathfinder.PathService;
import core.applicationservice.warriorservices.TowerFactory;
import core.applicationservice.warriorservices.WaveFactory;
import core.contract.DefenderConstants;
import core.contract.WaveConstants;
import core.domain.account.BankManager;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.behaviourimp.RegularMove;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;
import core.domain.waves.Wave;

public class LayeredMapPanelOtherItems extends JPanel implements Observer,
		ActionListener, MouseListener, Runnable {
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
	private CritterShape critterShape;
	private Bullet bullet;
	private LineBullet lineBullet;
	public Thread critterT, mapT;
	private boolean mapJustLoaded;
	private Cell cell;
	private Position[] path;
	private Wave wave;

	private Icon[] critterImage;

	private boolean startWave;

	private DefenderInformer informer;
	private Tower defender;
	private Critter target;

	private Map<Tower, Critter> defenderTargetPair;
	
	public LayeredMapPanelOtherItems(Dimension dimension) {
		this.grid = new GridMap(1, 1);
		this.bank = BankManager.getInstance();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		// MouseInfo.getPointerInfo().getLocation();
		// System.out.println(getLocationOnScreen());
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());

		// critter = new Critter((int) mapTopLeft.getX() + 50,
		// (int) mapTopLeft.getY() + 50, this.path);
		// WaveFactory waveFactory = new WaveFactory();
		// wave = waveFactory.getWave("FoolishCritter",
		// calcCritterStartingPoint());
		bullet = new Bullet((int) mapTopLeft.getX() + 50,
				(int) mapTopLeft.getY() + 50);
		// add(critter);
		// critterT = new Thread(critter);
		mapT = new Thread(this);
		// t.run();
		setOpaque(false);
		setDimension(dimension);
		critterImage = new Icon[WaveConstants.WAVE_SIZE];
		informer = new DefenderInformer();
		defenderTargetPair = new HashMap<Tower,Critter>();

	}

	public void setGrid(GridMap grid) {
		cell = new Cell();
		// mapJustLoaded = true;
		this.grid = grid;

		towers = new Tower[grid.getWidth()][grid.getHeight()];

	}

	protected Position calcCritterStartingPoint() {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		Position entryPoint = grid.getEntranceLocation();
		if (entryPoint != null) {
			entryPoint = new Position(
					(int) (initX + (entryPoint.getX() * grid.getUnitSize())),
					(int) (initY + (entryPoint.getY() * grid.getUnitSize())));

			// temp
			PathService pathService = new PathService();
			this.path = pathService.pathFinder(grid.getContent());
			// critterShape = new CritterShape((int) entryPoint.getX(), (int)
			// entryPoint.getY()-1, this.path);
			bullet = new Bullet(initX + 100, initY + 153);
			// lineBullet = new LineBullet(initX + 125, initY + 123);
			// temp end

			return entryPoint;
		}
		return null;
	}

	protected Position convertCellToPixel(Position cell) {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		Position entryPoint;
		if (cell != null) {
			entryPoint = new Position(
					(int) (initX + (cell.getX() * grid.getUnitSize())),
					(int) (initY + (cell.getY() * grid.getUnitSize())));

			// temp
			PathService pathService = new PathService();
			this.path = pathService.pathFinder(grid.getContent());
			// critterShape = new CritterShape((int) entryPoint.getX(), (int)
			// entryPoint.getY()-1, this.path);
			bullet = new Bullet(initX + 100, initY + 153);
			// lineBullet = new LineBullet(initX + 125, initY + 123);
			// temp end

			entryPoint.setX(entryPoint.getX() + grid.getUnitSize() / 2);
			entryPoint.setY(entryPoint.getY() + grid.getUnitSize() / 2);
			return entryPoint;
		}
		return null;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
				* grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				int xCoordinate = grid.getUnitSize() * x + initX;
				int yCoordinate = grid.getUnitSize() * y + initY;
				if (grid.getTowers() == null) {
					grid.setTowers(new Tower[1][1]);
				}
				if (grid.getCell(x, y).getValue() == 11) {
					cell.draw(g, grid.getCell(x, y), grid.getTowers(),
							xCoordinate, yCoordinate, x, y);
				}
			}
		}

		if (startWave) {
			for (int i = 0; i < wave.aliens.size(); i++) {
				Position pos = ((RegularMove) (wave.aliens.get(i)
						.getMovingBehaviour())).getPixelPosition();
				new CritterShape().draw(g, critterImage[i], pos.getX(),
						pos.getY());
			}
			
			
			
//			for (Tower key : defenderTargetPair.keySet()) {
//			    System.out.println("Key = " + key + " - " + hm.get(key));
//				
//			}

			for(int i=0; i<defenderTargetPair.size(); i++){
				Iterator it = defenderTargetPair.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<Tower, Critter> pairs = (Map.Entry<Tower, Critter>)it.next();
//			        System.out.println(pairs.getKey() + " = " + pairs.getValue());
//			        it.remove(); // avoids a ConcurrentModificationException
//			    }
				
				
				
				Tower t2 = pairs.getKey();//key;
				Critter c = pairs.getValue();//defenderTargetPair.get(key);
				
				// shoot only if target is in range
				int range = 1;
				if ((path[c.getCurrentPosition()].getY() <= t2
						.getTowerPosition().getY() + range && path[c
						.getCurrentPosition()].getY() >= t2
						.getTowerPosition().getY() - range)
						&& (path[c.getCurrentPosition()].getX() <= t2
								.getTowerPosition().getX() + range && path[c
								.getCurrentPosition()].getX() >= t2
								.getTowerPosition().getX() - range)) {

					new LineBullet().draw(g, convertCellToPixel(t2
							.getTowerPosition()),
							convertCellToPixel(path[c
									.getCurrentPosition()]));
				}
//				defenderTargetPair.remove(key);
				it.remove(); // avoids a ConcurrentModificationException
			}
			}
		
			
//			for (Tower[] t : towers) {
//				for (Tower t2 : t) {
//					if (t2 != null) {
//						Critter c = ((TowerFeatureDecorator) t2).getTarget();
//						if (c != null) {
//							// shoot only if target is in range
//							int range = 1;
//							if ((path[c.getCurrentPosition()].getY() <= t2
//									.getTowerPosition().getY() + range && path[c
//									.getCurrentPosition()].getY() >= t2
//									.getTowerPosition().getY() - range)
//									&& (path[c.getCurrentPosition()].getX() <= t2
//											.getTowerPosition().getX() + range && path[c
//											.getCurrentPosition()].getX() >= t2
//											.getTowerPosition().getX() - range)) {
//
//								new LineBullet().draw(g, convertCellToPixel(t2
//										.getTowerPosition()),
//										convertCellToPixel(path[c
//												.getCurrentPosition()]));
//							}
//						}
//					}
//				}
//			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		event.consume();
		System.out.println("clicked");
		// int[] coordinate = cellCoordinate(event.getX(), event.getY());

		if (x <= grid.getWidth() & y <= grid.getHeight()) {
			boolean addTowerFlag = SelectedTower.getAddTowerFlag();
			if (addTowerFlag) {
				addTower(x, y);
			} else {
				towerUpgradePanels();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("exited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("perssed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("released");

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
			String towerType = SelectedTower.getTowerType();
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
				towers[x][y] = tower;
				tower.setTowerPosition(new Position(x, y));
				grid.setCell(x, y, GridCellContentType.TOWER);
				grid.setTowers(towers);
				((TowerFeatureDecorator) tower)
						.setCrittersLocation(new HashMap<Critter, Position>());
				informer.registerObserver((TowerFeatureDecorator) tower);
				((TowerFeatureDecorator) tower).addObserver(this);
				// draw(x, y);
				repaint();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"you don't have enough money :(", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}
			SelectedTower.setInstance(SelectedTower.getTowerType(),
					SelectedTower.getTower(), false);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("action");
	}

	public void towerOperation() {

		boolean addTowerFlag = SelectedTower.getAddTowerFlag();
		if (addTowerFlag) {
			addTower(x, y);
		} else {
			towerUpgradePanels();
		}

	}

	public void setCellLocation(int mouseX, int mouseY) {
		int i = (mouseX - (int) mapTopLeft.getX() - 75 / 2)
				/ grid.getUnitSize();
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
	 * the a tower from the map.</b>
	 * 
	 * @param arg1
	 *            is object is of type tower perform operation
	 * @param arg0
	 *            observer object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// switch (inspection.getPerformedAction()) {
		// case "Upgrade":
		// upgradeTower();
		// break;
		// case "Sell":
		// clearTower(x, y);
		// break;
		// default:
		// break;
		// }

		// shoot
		if (arg0 instanceof TowerFeatureDecorator) {
			target = ((TowerFeatureDecorator) arg0).getTarget();
			defender = ((TowerFeatureDecorator) arg0).getDefender();
			defenderTargetPair.put(defender, target);
			// repaint();
			System.out.println("shooting");
		}
	}

	private void upgradeTower() {
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		String str = new Long(availFunds).toString();
		// this.bankLbl.setText("$" + str);
	}

	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			String str = new Long(availFunds).toString();
			// this.bankLbl.setText("$" + str);
			informer.removeObserver((TowerFeatureDecorator) towers[x][y]);
			towers[x][y] = null;
			grid.setTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			repaint();
		}
	}

	public void run() {
		while (true) {
			// critter.walk();
			for (Critter critter : wave.aliens) {
				if (critter.getCurrentPosition() != ((RegularMove) critter
						.getMovingBehaviour()).getPath().length - 1) {
					critter.performMovingBehaviour();
					// Position p = path[critter.getCurrentPosition()];
					// informer.setAlienPosition(p.getX(), p.getY(), critter);
					int i = ((RegularMove) critter.getMovingBehaviour())
							.getCurrentPosition();
					Position p = path[i];
					critter.setCurrentPosition(i);
					informer.setAlienPosition(p.getX(), p.getY(), critter);

				} else {
					System.out.println("At exit point.");
//					wave.aliens.remove(critter);
				}
			}

			bullet.physic();
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		// // critter.walk();
		// Position entryPoint = grid.getEntranceLocation();
		// critter = new Critter((int) entryPoint.getX()+200, (int)
		// entryPoint.getY()-1, this.path);
		// // bullet.physic();
		// repaint();
	}

	public void startFoolishWave() {
		WaveFactory waveFactory = new WaveFactory();
		Position[] path = new PathService().pathFinder(grid.getContent());
		wave = waveFactory.getWave("FoolishCritter",
				calcCritterStartingPoint(), path);

		ClassLoader classLoader = getClass().getClassLoader();
		File file;

		for (int i = 0; i < wave.aliens.size(); i++) {
			((RegularMove) (wave.aliens.get(i).getMovingBehaviour()))
					.setFreezeTime(i * 100);

			file = new File(classLoader.getResource(
					wave.aliens.get(i).display()).getFile());
			critterImage[i] = new ImageIcon(file.getPath());
		}
		startWave = true;
		mapT.start();

	}

}
