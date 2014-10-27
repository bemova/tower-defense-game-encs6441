package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

import core.contract.MapConstants;

@SuppressWarnings("serial")
public class VisualGrid extends Grid {

	Grid simpleGrid;

//	public VisualGrid(){}
	
	public VisualGrid(Grid grid) {
		simpleGrid = grid;

	}

	public VisualGrid(int width, int height) {
		super(width, height);
	}

	public VisualGrid(int width, int height, GridCellContentType cellType) {
		super(width, height, cellType);
	}

	@SuppressWarnings("incomplete-switch")
	public void draw(Graphics g) {
		// simpleGrid.draw(g);
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = Color.green;
				switch (getCell(x, y)){
				case PATH:
					color = MapConstants.PATH_COLOR;
					break;
				case SCENERY:
					color = MapConstants.SCENERY_COLOR;
					break;
				case ENTRANCE:
					color = MapConstants.ENTRANCE_COLOR;
					break;
				case EXIT:
					color = MapConstants.EXIT_COLOR;
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),
						getUnitSize());

			}

		}

	}

}
