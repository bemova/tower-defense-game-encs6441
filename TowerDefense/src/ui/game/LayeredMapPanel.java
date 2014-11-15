package ui.game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

public class LayeredMapPanel extends JLayeredPane implements Observer, ActionListener,
		MouseListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread mapT;
	private Map grid;
	
	private Point mapTopLeft;
	private Point mapButtomRight;
	
	private JPanel topBar;
	
	private LayeredMapPanelGrid gridLayer;
	private LayeredMapPanelOtherItems otherItemsLayer;


	public LayeredMapPanel(Dimension dimension) {
		this.grid = new Map(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		
		gridLayer = new LayeredMapPanelGrid(dimension);
		otherItemsLayer = new LayeredMapPanelOtherItems(dimension);
		add(gridLayer,new Integer(1));
		add(otherItemsLayer,new Integer(2));

		mapT = new Thread(this);
		
//		setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
System.out.println("Mouse Clicked");		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


	public LayeredMapPanelOtherItems getOtherItemsPanel(){
		return otherItemsLayer;
	}
	public void setGrid(Map grid) {
		this.grid = grid;
		gridLayer.setGrid(grid);
		otherItemsLayer.setGrid(grid);
		
	}


	public Grid getGrid() {
		return gridLayer.getGrid();
	}


	public void setTowers(Tower[][] towers) {
		otherItemsLayer.setTowers(towers);		
	}


	public BankManager getBank() {
		return otherItemsLayer.getBank();
	}

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

	public void run() {
		while (true) {
			otherItemsLayer.performScene();
//			critter.walk();
//			bullet.physic();
//			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
