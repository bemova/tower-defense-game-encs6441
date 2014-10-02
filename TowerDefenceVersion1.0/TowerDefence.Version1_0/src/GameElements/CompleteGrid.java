package GameElements;

import java.awt.Graphics;

public class CompleteGrid extends Grid {

	Grid simpleGrid;
	
	
	//constructor's section 
	CompleteGrid(Grid newGrid){
		simpleGrid = newGrid;
		
	}
	
	CompleteGrid(){
	
	}
	

	
	
	
	@Override
	public void draw(Graphics g) {
		simpleGrid.draw(g);
	}

}
