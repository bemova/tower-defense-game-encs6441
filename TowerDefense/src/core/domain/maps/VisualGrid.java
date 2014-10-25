package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

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
//		simpleGrid.draw(g);

		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++) {
				// if(content[i][j] !=0){
				Color color = Color.green;
				if (getCell(i, j) == GridCellContentType.PATH) {
					color = Color.gray;
				}
				if (getCell(i, j) == GridCellContentType.SCENERY) {
					color = Color.green;
				}
				if (getCell(i, j) == GridCellContentType.ENTRANCE) {
					color = Color.red;
				}
				if (getCell(i, j) == GridCellContentType.EXIT) {
					color = Color.blue;
				}
				g.setColor(color);
				g.fillRect(j * getUnitSize(), i * getUnitSize(), getUnitSize(),
						getUnitSize());

				// }

			}

	}


}
