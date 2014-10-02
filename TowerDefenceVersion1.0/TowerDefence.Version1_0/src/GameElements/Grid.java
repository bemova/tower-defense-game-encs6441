package GameElements;

import java.awt.Graphics;

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
	
}
