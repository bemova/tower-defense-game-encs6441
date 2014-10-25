package core.domain.maps;

import java.awt.Graphics;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Grid implements Serializable{

	private int width;
	private int height;
	private int sizeOfUnit = 30;
	private GridCellContentType[][] content;

	public Grid() {}

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	public Grid(int width, int height, GridCellContentType cellType) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(cellType);
	}

	public Grid(Grid grid) {
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
	}

	private void initializeCellContents(GridCellContentType cellType) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setCell(x, y, cellType);
			}
		}
	}

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

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public GridCellContentType[][] getContent() {
		return content;
	}

	public int getUnitSize() {
		return sizeOfUnit;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
		
	};

	@Deprecated
	public int getCellType(int i, int j) {
		if (i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j].getValue();

		return -1;
	}

	public void setCell(int x, int y, GridCellContentType type) {
		if (x >= 0 && x < width && 
				y >= 0 && y < height){
			content[x][y] = type;
		}
	}

	public GridCellContentType getCell(int x, int y) {
		if (x >= 0 && x < width && y >= 0
				&& y < height) {
			return content[x][y];
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
