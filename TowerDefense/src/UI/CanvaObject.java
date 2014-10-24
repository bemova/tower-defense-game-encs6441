package UI;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

//import core.domain.maps.Grid;
import core.domain.maps.Grid2;

public class CanvaObject extends Canvas {

//	Grid grid = null;
	Grid2 grid = null;
	Dimension d = new Dimension();
	Graphics imageGraphic = null;

	public CanvaObject(Grid2 newGrid) {
		grid = newGrid;

	}

	public void updateGrid(Grid2 newGrid) {
		grid = newGrid;

	};

	/*
	 * public void setSize(int i, int j) { // TODO Auto-generated method stub
	 * super.setSize(i, j); }
	 */

	@Override
	public void paint(Graphics graphics) {
		// update( graphics );

		grid.draw(graphics);

	}

	void paintMapOnCanvas(Grid2 grid) {

	}
	
	
	
	
}
