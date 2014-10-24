package core.domain.maps;

import java.awt.Graphics;
import java.awt.Point;



public abstract class Grid {

	int height = 15;
	int width = 15;
	int sizeOfUnit = 30;
	GridCellContentType [][] content = new GridCellContentType [height][width];
	
	Grid(){};
	 
	abstract public void draw(Graphics g);
	
	public int  getHeight(){
		
		return height;
	}
	
	public int getWidth(){
		
		return width;
	}
	
	public int getUnitSize(){
		
		return sizeOfUnit;
	}
	
	public void setSize(int newWidth, int newheight){
		
		height = newheight;
		width = newWidth;
		content = new GridCellContentType [height][width];
	};
	
	
	public void gridAssignmentOperator(Grid newGrid){
		
		width  = newGrid.getWidth();
		height = newGrid.getHeight();
		
		for(int i = 0; i< height; i++)
			for(int j = 0; j < width; j++)
				content[i][j] = newGrid.content[i][j];
		
	} 
	
	@Deprecated
	public int getCellType(int i, int j)
	{
		if(i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j].getValue();
		
		return -1;
	}
	
//	@Deprecated
//	public void setCell(int cordinatX, int cordinatY, int type)
//	{
//		if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX < width){
//			
//			content[cordinatY][cordinatX] = type;
//		}
//	}
	
	public void setCell(int cordinatX, int cordinatY, GridCellContentType type)
	{
		if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX < width)
			
			content[cordinatY][cordinatX] = type;
	}
	
	public GridCellContentType getCell(int cordinatX, int cordinatY)
	{
		if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX < width){
			return content[cordinatY][cordinatX];
		}
		return null;
	}
	
	@Deprecated
	public int[][] getIntContent(){
		int[][] c = new int[width][height];
		for(int i = 0; i< height; i++)
			for(int j = 0; j < width; j++)
				c[i][j] = content[i][j].getValue();
		
		return c;
	}
	
}
