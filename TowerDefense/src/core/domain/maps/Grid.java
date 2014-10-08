package core.domain.maps;

import java.awt.Graphics;
import java.awt.Point;



public abstract class Grid {

	int height = 15;
	int width = 15;
	int sizeOfUnit = 30;
	public int [][] content = new int[height][width];
	
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
		content = new int[height][width];
	};
	
	
	public void gridAssignmentOperator(Grid newGrid){
		
		width  = newGrid.getWidth();
		height = newGrid.getHeight();
		
		for(int i = 0; i< height; i++)
			for(int j = 0; j < width; j++)
				content[i][j] = newGrid.content[i][j];
		
	} 
	
	public int getCellType(int i, int j)
	{
		if(i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j];
		
		return -1;
	}
	
	public void setCell(int cordinatX, int cordinatY, int type)
	{
		if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX < width)
			content[cordinatY][cordinatX] = type;
	}
}
