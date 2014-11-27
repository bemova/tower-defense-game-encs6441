package core.domain.maps;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import core.contract.MapConstants;
import core.domain.waves.Position;

/**
 * @author 	Team5
 *
 */

public class Grid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3267090270961396525L;
	
	private int width;
	private int height;
	private int unitSize = MapConstants.UNIT_SIZE;
	private GridCellContentType[][] content;

	private String creationTime;
	private ArrayList<String> modificationTime;
	private ArrayList<PlayLog> playLog;
	
	/**
	 * This is a dummy constructor 
	 */
	public Grid() {
		this.width = 1;
		this.height = 1;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	/**
	 * <b>Creates a Grid using width and height and initializes the content as Scenery</b>
	 * @param width width of grid 
	 * @param height height of grid
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	/**
	 * <b>Creates a Grid with width, height, and content type</b>
	 * @param width width of grid
	 * @param height height of grid
	 * @param cellType type of grid
	 */
	public Grid(int width, int height, GridCellContentType cellType) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(cellType);
	}

	/**
	 * <b>Constructs a Grid by another grid.</b>
	 * @param grid grid object
	 */
	public Grid(Grid grid) {
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
	}

	/**
	 * <b>initializes grid content to cellType</b>
	 * @param cellType type of cell 
	 */
	private void initializeCellContents(GridCellContentType cellType) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setCell(x, y, cellType);
			}
		}
	}

	/**
	 * <b>Draws the grid</b>
	 * @param g draw graphics after iteration
	 */
	public void draw(Graphics g) {
//
//		for (int i = 0; i < width * unitSize; i += unitSize) {
//			for (int j = 0; j < height * unitSize; j += unitSize) {
//				g.drawLine(i, 0, i, height * unitSize);
//				g.drawLine(0, j, width * unitSize, j);
//			}
//		}
//
//		g.drawLine(width * unitSize - 1, height * unitSize - 1, width
//				* unitSize - 1, 0); // x1y1 x2y2
//
//		g.drawLine(0, height * unitSize - 1, width * unitSize - 1, height
//				* unitSize - 1);

	}

	/**
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int value){
		height = value;
	}
	/**
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int value) {
		width = value;
	}

	/**
	 * @return type return content of the grid cell
	 */
	public GridCellContentType[][] getContent() {
		return content;
	}

	public void setContent(GridCellContentType[][] value) {
		content = value;
	}
	
	/**
	 * @return size of the unit 
	 */
	public int getUnitSize() {
		return unitSize;
	}
	
	public void setUnitSize(int value) {
		unitSize = value;
	}


	/**
	 * <b>Sets the size of the grid and resets its content to scenery.</b>
	 * @param width width of the content to set
	 * @param height height of the content to set
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
		
	};


	/**
	 * <b>Sets the content of a cell to type</b>
	 * @param x location of cell
	 * @param y location of cell
	 * @param type  type of cell
	 */
	public void setCell(int x, int y, GridCellContentType type) {
		if (x >= 0 && x < width && 
				y >= 0 && y < height){
			content[x][y] = type;
		}
	}

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return content type
	 */
	public GridCellContentType getCell(int x, int y) {
		if (x >= 0 && x < width && y >= 0
				&& y < height) {
			return content[x][y];
		}
		return null;
	}

	public Position getEntranceLocation(){
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				if (content[x][y]==GridCellContentType.ENTRANCE){
					return new Position(x,y);
				}
			}
		}
		return null;
	}

	public Position getExitLocation(){
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				if (content[x][y]==GridCellContentType.EXIT){
					return new Position(x,y);
				}
			}
		}
		return null;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public ArrayList<String> getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(ArrayList<String> modificationTime) {
		this.modificationTime = modificationTime;
	}
	
	public void addModificationTime (String modificationTime){
		this.modificationTime.add(modificationTime);
	}
	
	
	public ArrayList<PlayLog> getPlayLog() {
		return playLog;
	}

	public void setPlayLog(ArrayList<PlayLog> playLog) {
		this.playLog = playLog;
	}

	public void addPlayLog (String time, long score){
		this.playLog.add(new PlayLog(time, score));
		
	}
	
	
	class PlayLog {
		String time;
		long score;
		
		PlayLog(){
		}
		
		PlayLog(String time, long score){
			this.time = time;
			this.score = score;
		}
	}
}
