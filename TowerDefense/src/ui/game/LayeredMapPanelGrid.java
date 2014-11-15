package ui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import core.domain.warriors.defenders.towers.Tower;

public class LayeredMapPanelGrid extends JPanel
		  {
	private Point mapTopLeft;
	private Point mapButtomRight;
	private Map grid;
	private Cell cell;
	

	public LayeredMapPanelGrid(Dimension dimension) {
		this.grid = new Map(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		setOpaque(false);
		setDimension(dimension);
	}

	public void setGrid(Map grid) {
		cell = new Cell();
		this.grid = grid;
	}

	public void paintComponent(Graphics g) {

		 super.paintComponent(g);

		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
				* grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		if (grid.getWidth() > 1) {
				
				for (int x = 0; x < grid.getWidth(); x++) {
					for (int y = 0; y < grid.getHeight(); y++) {
						int xCoordinate = grid.getUnitSize() * x + initX;
						int yCoordinate = grid.getUnitSize() * y + initY;
						if (grid.getTowers() == null) {
							grid.setTowers(new Tower[1][1]);
						}
						cell.draw(g, grid.getCell(x, y), grid.getTowers(),
								xCoordinate, yCoordinate, x, y);
					}
				}
		}

	}


	
	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
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


	public Map getGrid() {
		return grid;
	}

	protected void setDimension(Dimension mapPanelDimension) {
		setSize(mapPanelDimension);
		
	}


}
