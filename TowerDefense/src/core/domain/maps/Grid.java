package core.domain.maps;

import java.awt.Graphics;
import java.awt.Point;



public abstract class Grid {

 
	public Grid(){
		
	};
	
	abstract public void draw(Graphics g);
	

	
	
/*	public void gridAssignmentOperator(Grid newGrid){
		
		width  = newGrid.getWidth();
		height = newGrid.getHeight();
		
		for(int i = 0; i< height; i++)
			for(int j = 0; j < width; j++)
				content[i][j] = newGrid.content[i][j];
		
	}  */
	

}
