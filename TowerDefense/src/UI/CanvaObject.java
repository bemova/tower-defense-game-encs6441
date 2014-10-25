package UI;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import core.domain.maps.Grid2;

@SuppressWarnings("serial")
public class CanvaObject extends Canvas {

	Grid2 grid = null;
	Dimension d = new Dimension();
	Graphics imageGraphic = null;

	public CanvaObject(Grid2 newGrid) {
		grid = newGrid;

	}

	public void updateGrid(Grid2 newGrid) {
		grid = newGrid;

	};

	@Override
	public void paint(Graphics graphics) {
//		 update( graphics );

		grid.draw(graphics);

	}

	void paintMapOnCanvas(Grid2 grid) {

	}
	
	
	
	
}
