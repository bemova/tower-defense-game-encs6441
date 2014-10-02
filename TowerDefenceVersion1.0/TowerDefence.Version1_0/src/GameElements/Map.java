package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;


public class Map extends CompleteGrid {

	HashMap<String, GridCell > map;
	ArrayList<GridCell> path;
	ArrayList<Tower> towers;
	String entryPoint = "";
	String exitPoint = "";
	
	
	//contructor's section
	Map(){};
	Map(Grid grid){};
	
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
	
		//	Dimension dimension = getSize();



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
	
	};
	

	
	public String getEntryPoint(){
		
		return entryPoint;
	};
	
	public String getExitPOint(){
		
		return exitPoint;
	};
}
