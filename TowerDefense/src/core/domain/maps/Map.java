package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import core.domain.warriors.defenders.towers.vikiTowers.*;
//import core.domain.warriors.defenders.towers.*; // This is Mojtaba towers


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
			tower.draw(g);
		}
	
		//	Dimension dimension = getSize();

	
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
		
		towers.get(towerKey).levelUpDown(upOrDown);
		
	}
	
}
