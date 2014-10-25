package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class VisualGrid extends Grid2 {

	Grid2 simpleGrid;

	// constructor's section
	public VisualGrid(Grid2 grid) {
		simpleGrid = grid;

	}

	public VisualGrid(int width, int height) {
		super(width, height);
	}

	public VisualGrid(int width, int height, GridCellContentType cellType) {
		super(width, height, cellType);
	}

	public void draw(Graphics g) {
		// simpleGrid.draw(g);
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = Color.green;
				switch (getCell(x, y)){
				case PATH:
					color = Color.gray;
					break;
				case SCENERY:
					color = Color.green;
					break;
				case ENTRANCE:
					color = Color.red;
					break;
				case EXIT:
					color = Color.blue;
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),
						getUnitSize());

			}

		}

	}

}
