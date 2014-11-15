
package ui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.towerdesign.SimpleInspection;
import core.applicationservice.mapservices.pathfinder.PathService;
import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
import core.domain.account.BankManager;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;

public class LayeredMapPanelOtherItems extends JPanel implements Observer, ActionListener,
		MouseListener, Runnable {
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
	private Map grid;
	private SimpleInspection inspection;
	private Critter critter;
	private Bullet bullet;
	private LineBullet lineBullet;
	public Thread critterT, mapT;
	private boolean mapJustLoaded;
	private Cell cell;
	private Position[] path;


	public LayeredMapPanelOtherItems(Dimension dimension) {
		this.grid = new Map(1, 1);
		this.bank = BankManager.getInstance();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		// MouseInfo.getPointerInfo().getLocation();
		// System.out.println(getLocationOnScreen());
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());
		critter = new Critter((int) mapTopLeft.getX() + 50,
				(int) mapTopLeft.getY() + 50, this.path);
		bullet = new Bullet((int) mapTopLeft.getX() + 50,
				(int) mapTopLeft.getY() + 50);
		// add(critter);
//		critterT = new Thread(critter);
		mapT = new Thread(this);
		// t.run();
		setOpaque(false);
		setDimension(dimension);

	}

	public void setGrid(Map grid) {
		cell = new Cell();
//		mapJustLoaded = true;
		this.grid = grid;

		towers = new Tower[grid.getWidth()][grid.getHeight()];


		
		
	}

	protected Point calcCritterStartingPoint() {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();
		
		Point entryPoint = grid.getEntranceLocation();
		entryPoint = new Point((int) (initX+(entryPoint.getX()*grid.getUnitSize())), (int) (initY+(entryPoint.getY()*grid.getUnitSize())));
		
		// temp
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());
		critter = new Critter((int) entryPoint.getX(), (int) entryPoint.getY()-1, this.path);
		bullet = new Bullet(initX + 100, initY + 153);
		lineBullet = new LineBullet(initX + 125, initY + 123);
		//temp end
		
		return entryPoint;
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
		
		
	
		critter.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		System.out.println("clicked");
//		int[] coordinate = cellCoordinate(event.getX(), event.getY());

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
				grid.setCell(x, y, GridCellContentType.TOWER);
				grid.setTowers(towers);
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

	public Map getGrid() {
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
		// this.bankLbl.setText("$" + str);
	}

	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			String str = new Long(availFunds).toString();
			// this.bankLbl.setText("$" + str);
			towers[x][y] = null;
			grid.setTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			repaint();
		}
	}

	public void run() {
		while (true) {
			critter.walk();
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
//		critter.walk();
		Point entryPoint = grid.getEntranceLocation();
		critter = new Critter((int) entryPoint.getX()+200, (int) entryPoint.getY()-1, this.path);
//		bullet.physic();
		repaint();
	}


	
}
