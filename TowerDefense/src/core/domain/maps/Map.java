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
	
	public int updateLevel(String towerKey, String upOrDown){
		int level = 0;
		level = towers.get(towerKey).levelUpDown(upOrDown);
		
		//if the method return negative value we have to remove the tower from the map => 
		//it is completely sell
		if(level < 0){
			
			towers.remove(towerKey);
			String [] pointParts = towerKey.split(" ");
			int i = Integer.parseInt(pointParts[0]);
			int j = Integer.parseInt(pointParts[1]);
			((EmptyGrid)simpleGrid).content[i][j] = 2;
		
		}
		
			return level;
		
	}
	
}
