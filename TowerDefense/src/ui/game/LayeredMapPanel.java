package ui.game;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import core.applicationservice.gameservices.GameStateManager;
import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

/**
 * @author Team 5
 * This class is JLayeredPane that has two layers, one for map and one for towers and critters.
 */
public class LayeredMapPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	public Thread mapT;
	private GridMap grid;
	
	private Point mapTopLeft;
	private Point mapButtomRight;
	
	
	private LayeredMapPanelGrid gridLayer;
	private LayeredMapPanelOtherItems otherItemsLayer;
	private GameInfoPanel gameInfoPanel;


	public GameInfoPanel getGameInfoPanel() {
		return gameInfoPanel;
	}


	public void setGameInfoPanel(GameInfoPanel gameInfoPanel) {
		this.gameInfoPanel = gameInfoPanel;
	}


	public LayeredMapPanel(Dimension dimension, GameInfoPanel gameInfoPanel, MainFrame mainFrame) {
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new GridMap(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		
		gridLayer = new LayeredMapPanelGrid(dimension);
		otherItemsLayer = new LayeredMapPanelOtherItems(dimension, gameInfoPanel, mainFrame, this);
		add(gridLayer,new Integer(1));
		add(otherItemsLayer,new Integer(2));
	}


	public LayeredMapPanelOtherItems getOtherItemsPanel(){
		return otherItemsLayer;
	}
	public void setGrid(GridMap grid) {
		this.grid = grid;
		gridLayer.setGrid(grid);
		otherItemsLayer.setGrid(grid);
	}


	public Grid getGrid() {
		return gridLayer.getGrid();
	}
	
	public GridMap getGridMap() {
		return otherItemsLayer.getGrid();
	}

	public void setTowers(Tower[][] towers) {
		otherItemsLayer.setTowers(towers);		
	}


	public BankManager getBank() {
		return otherItemsLayer.getBank();
	}

	/**
	 * This method calculates the starting point of the map on the screen in pixels to draw the map right in the center of the screen.
	 * @param mapPanelDimension dimension of the map panel. 
	 * @return a point in pixel that represents the top-left corner of the map on the screen.
	 */
	private Point calcMapStartingPoint(Dimension mapPanelDimension) {
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) {
			initX = (panelWidth - mapWidth) / 2;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) {

			initY = (panelHeight - mapHeight) / 2;
		}

		return new Point(initX, initY);
	}
	
	/** This method calculates the bottom-right corner of the map on the screen in pixels to know the boundary of the map on the screen.
	 * @param mapPanelDimension dimension of the map panel.
	 * @return a point in pixel that represents the bottom-right corner of the map on the screen.
	 */
	private Point calcMapButtomRight(Dimension mapPanelDimension){
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) {
			initX = ((panelWidth - mapWidth) / 2)+mapWidth;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) {

			initY = ((panelHeight - mapHeight) / 2)+mapHeight;
		}

		return new Point(initX, initY);
	}
	

	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

	public Point getMapTopLeft() {
		return mapTopLeft;
	}

	private void setMapTopLeft(Point mapTopLeft) {
		this.mapTopLeft = mapTopLeft;
	}

	public Point getMapButtomRight() {
		return mapButtomRight;
	}

	private void setMapButtomRight(Point mapButtomRight) {
		this.mapButtomRight = mapButtomRight;
	}


	/**
	 * When a map is loaded, this method is called to resize the panel and its layers.
	 * @param mapPanelDimension width and height of the map in cell numbers.
	 */
	public void resetSize(Dimension mapPanelDimension) {
		Point mapTopLeft = calcMapStartingPoint(mapPanelDimension);
		Point mapBottomRight = calcMapButtomRight(mapPanelDimension);
		
		gridLayer.setMapTopLeft(mapTopLeft);
		gridLayer.setMapButtomRight(mapBottomRight);
		gridLayer.setDimension(mapPanelDimension);

		otherItemsLayer.setMapTopLeft(mapTopLeft);
		otherItemsLayer.setMapButtomRight(mapBottomRight);
		otherItemsLayer.setDimension(mapPanelDimension);
		otherItemsLayer.calcCritterStartingPoint();
		
	}


//	public void saveGame(String fileName) {
//		otherItemsLayer.saveGame(fileName);
//		
//	}


//	public void loadGame(String fileName) {
//		otherItemsLayer.loadGame(fileName);
//		
//	}


	public void setGameInfo(GameStateManager game) {
		otherItemsLayer.setGameInfo(game);
		
	}


	public int getWaveNumber() {
		return otherItemsLayer.getWaveNumber();
	}
}
