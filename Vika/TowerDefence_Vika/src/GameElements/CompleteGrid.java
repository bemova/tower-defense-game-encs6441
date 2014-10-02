package GameElements;

import java.awt.Color;
import java.awt.Graphics;

public class CompleteGrid extends Grid {

	Grid simpleGrid;
	
	
	//constructor's section 
	public CompleteGrid(Grid newGrid){
		simpleGrid = newGrid;
		
	}
	
public 	CompleteGrid(){
	
	}
	

	@Override
	public void draw(Graphics g) {
		simpleGrid.draw(g);
		
		for(int i = 0; i<height; i++)
			for( int j = 0; j < width; j++){
				if(content[i][j] !=0){
				Color color = Color.gray;
				if( content[i][j] == 1) color = Color.gray;
				if( content[i][j] == 2) color = Color.green;
				if( content[i][j] == 3) color = Color.red;
				if( content[i][j] == 4) color = Color.blue;
			    g.setColor(color);
			    g.fillRect(j * sizeOfUnit, i * sizeOfUnit, sizeOfUnit, sizeOfUnit);
			
				}	
				
				
			}
		
	}
	
	public void setSize(int newWidth, int newheight){
		
		simpleGrid.setSize(newWidth, newheight);
		width = newWidth;
		height = newheight;
		content = new int[newheight][newWidth];
	};

}
