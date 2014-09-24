import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

public class MainCanva extends Canvas {

	void drawTowers(String towerType) {
	};

	void drawFild(int sizeX, int sizeY) {
	};

	int UnitSize = 20; // size of one cube
	int[][] path = null;
	String entryPoint = "";
	String exitPoint = "";
	

	@Override
	public void paint(Graphics graphics) {
		drawGrid(graphics);
	}

	public void drawGrid(Graphics graphics) {
		Dimension dimension = getSize();

		for (int i = 0; i < dimension.width; i += UnitSize) {
			for (int j = 0; j < dimension.width; j += UnitSize) {
				graphics.drawLine(i, 0, i, dimension.height);
				graphics.drawLine(0, j, dimension.width, j);
			}
		}

		graphics.drawLine(dimension.width - 1, dimension.height - 1,
				dimension.width - 1, 0); // x1y1 x2y2

		graphics.drawLine(0, dimension.height - 1, dimension.width - 1,
				dimension.height - 1);

		
		if(path != null)
		{
		for (int x = 0; x < dimension.width / 20; x++)
		{
			for (int y = 0; y < dimension.height / 20; y++) {

				if (path[x][y] == 1) {
					graphics.fillRect(y * 20, x * 20, 20, 20);

				}
			}
		}
		}
	}
}
