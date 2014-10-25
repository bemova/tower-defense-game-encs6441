package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

public class Grid2 {

	private int width;
	private int height;
	private int sizeOfUnit = 30;
	private GridCellContentType[][] content;

	public Grid2() {
		width = 10;
		height = 10;
		content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	public Grid2(int width, int height) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	public Grid2(int width, int height, GridCellContentType cellType) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(cellType);
	}

	public Grid2(Grid2 grid) {
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
	}

	private void initializeCellContents(GridCellContentType cellType) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				setCell(i, j, cellType);
			}
		}
	}

	// public abstract void draw(Graphics g);
	public void draw(Graphics g) {

		for (int i = 0; i < width * sizeOfUnit; i += sizeOfUnit) {
			for (int j = 0; j < height * sizeOfUnit; j += sizeOfUnit) {
				g.drawLine(i, 0, i, height * sizeOfUnit);
				g.drawLine(0, j, width * sizeOfUnit, j);
			}
		}

		g.drawLine(width * sizeOfUnit - 1, height * sizeOfUnit - 1, width
				* sizeOfUnit - 1, 0); // x1y1 x2y2

		g.drawLine(0, height * sizeOfUnit - 1, width * sizeOfUnit - 1, height
				* sizeOfUnit - 1);

	}

	// public void draw(Graphics g) {
	// boolean draw = true;
	// for (int x = 0; x < width; x++) {
	// for (int y = 0; y < height; y++) {
	// if (getCell(x, y) == null) {
	// draw = false;
	// break;
	// }
	// }
	// }
	// if (draw) {
	// for (int x = 0; x < width; x++) {
	// for (int y = 0; y < height; y++) {
	// // if (getCell(x, y) != null) {
	// Color color = Color.WHITE;
	// switch (getCell(x, y)) {
	// case SCENERY:
	// color = Color.GREEN;
	// break;
	// case PATH:
	// color = Color.GRAY;
	// break;
	// case ENTRANCE:
	// color = Color.RED;
	// break;
	// case EXIT:
	// color = Color.BLUE;
	// break;
	// // case BLANK:
	// // color = Color.WHITE;
	// // break;
	// }
	// g.setColor(color);
	// g.fillRect((x * sizeOfUnit), (y * sizeOfUnit), sizeOfUnit,
	// sizeOfUnit);
	// // }
	// }
	// }
	// }
	// }

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	private GridCellContentType[][] getContent() {
		return content;
	}

	public int getUnitSize() {
		return sizeOfUnit;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		content = new GridCellContentType[height][width];
	};

	public void gridAssignmentOperator(Grid2 newGrid) {

		width = newGrid.getWidth();
		height = newGrid.getHeight();

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				content[x][y] = newGrid.getCell(x, y);

	}

	@Deprecated
	public int getCellType(int i, int j) {
		if (i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j].getValue();

		return -1;
	}

	// @Deprecated
	// public void setCell(int cordinatX, int cordinatY, int type)
	// {
	// if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX <
	// width){
	//
	// content[cordinatY][cordinatX] = type;
	// }
	// }

	public void setCell(int cordinatX, int cordinatY, GridCellContentType type) {
		if (cordinatY >= 0 && cordinatY < height && cordinatX >= 0
				&& cordinatX < width)

			content[cordinatY][cordinatX] = type;
	}

	public GridCellContentType getCell(int cordinatX, int cordinatY) {
		if (cordinatY >= 0 && cordinatY < height && cordinatX >= 0
				&& cordinatX < width) {
			return content[cordinatY][cordinatX];
		}
		return null;
	}

	@Deprecated
	public int[][] getIntContent() {
		int[][] c = new int[width][height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				c[i][j] = content[i][j].getValue();

		return c;
	}

}
