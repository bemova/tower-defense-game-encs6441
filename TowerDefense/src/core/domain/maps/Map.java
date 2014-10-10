package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import core.domain.warriors.defenders.towers.*;


public class Map extends CompleteGrid {

	HashMap<String, GridCell > map;
	ArrayList<GridCell> path;
	public HashMap<String,Tower> towers; 
	String entryPoint = "";
	String exitPoint = "";
	
	
	
	//contructor's section
	public Map(){};
	public Map(Grid grid){
		super(grid);
		towers = new HashMap<String,Tower>();
		
	};
	
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		for(Tower tower: towers.values())
		{
			tower.display();//  .draw(g);
		}
	
		//	Dimension dimension = getSize();


/*
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
			
}	*/
	
	};
	

	
	public String getEntryPoint(){
		
		return entryPoint;
	};
	
	public String getExitPOint(){
		
		return exitPoint;
	};
	
	
	public void addTower(Tower newTower, String positionKey){
		
		towers.put(positionKey , newTower);
	};
	
	public void updateLevel(String towerKey, String upOrDown){
		
		//towers.get(towerKey).levelUpDown(upOrDown);
		
	}
	
}
