package UI.game;

import java.awt.Color;
import java.awt.Graphics;

import core.contract.MapConstants;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

/**
 * @author Team5
 *
 */
@SuppressWarnings("serial")
public class Map extends Grid {

	Tower[][] towers;

	/**
	 * @param width Map width
	 * @param height Map height
	 */
	public Map(int width, int height) {
		super(width, height);
	}

	/**
	 * <b>Constructs a Map using a grid.</b>
	 * @param grid Grid object 
	 */
	public Map(Grid grid) {
		super(grid);
	}

	/**
	 * <b>Used in Observer design pattern which updates the towers that are on the map.</b>
	 * @param towers list of towers on the grid
	 */
	public void updateTowers(Tower[][] towers) {
		this.towers = towers;

	}
/**
 * <b>Draws the map on the screen.</b>
 * @param g to paint the component 
 */
	public void draw(Graphics g) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = Color.green;
				switch (getCell(x, y)) {
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
				case TOWER:
					color = towers[x][y].display();
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),
						getUnitSize());

			}

		}

	}

}
