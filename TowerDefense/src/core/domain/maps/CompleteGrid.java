package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

@Deprecated
public class CompleteGrid extends Grid {

	Grid simpleGrid;

	// constructor's section
	public CompleteGrid(Grid newGrid) {
		simpleGrid = newGrid;

	}

	public CompleteGrid() {

	}

	@Override
	public void draw(Graphics g) {
		simpleGrid.draw(g);

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
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
				g.fillRect(j * sizeOfUnit, i * sizeOfUnit, sizeOfUnit,
						sizeOfUnit);

				// }

			}

	}

	public void setSize(int newWidth, int newheight) {

		simpleGrid.setSize(newWidth, newheight);
		width = newWidth;
		height = newheight;
		content = new GridCellContentType[newheight][newWidth];
	};

}
