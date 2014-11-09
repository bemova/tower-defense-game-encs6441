package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

import UI.CanvaObject;
import wavs.Wave;
import critters.*;
import core.applicationService.vikiMapServacs.GraphNode;
import core.domain.warriors.defenders.towers.vikiTowers.*;



public class Map extends CompleteGrid {

	HashMap<String, GridCell > map;
	ArrayList<GridCell> path = new ArrayList<GridCell>();
	public ArrayList<Critter> critters = new ArrayList<Critter>();
	public HashMap<String,Tower> towers; 
	String entryPoint = "";
	String exitPoint = "";
	Wave wave;
	
	
	
	//contructor's section

	public Map(Grid grid){
		super(grid);
		towers = new HashMap<String,Tower>();
		
	};
	
	public void addCritter(Critter newCritter){
		
		critters.add(newCritter);
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		for(Tower tower: towers.values())
		{
			tower.draw(g);
		}
	
		for(Critter critter: critters)
		{
			critter.draw(g);
		}
	};
	@Override
	public void startWave(CanvaObject canva) {
		// TODO Auto-generated method stub
		wave = new Wave(canva, this);
		wave.start();
		
	}
	

	
	public String getEntryPoint(){
		
		return entryPoint;
	};
	
	public String getExitPOint(){
		
		return exitPoint;
	};
	
	
	public void addTower(Tower newTower, String positionKey){
		
		towers.put(positionKey , newTower);
	};
	
	public void setPath(Stack<GraphNode> newpath){
		
		while(!newpath.empty()){
			path.add(new GridCell(newpath.peek().cordinateX,newpath.peek().cordinateY ));
			newpath.pop();
		}
		
		Collections.reverse(path);
	}
	
	public ArrayList<GridCell> getPath()
	{
		return path;
	}
	
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

	@Override
	public void stopWave() {
		wave.stop();		
	}
	
}
