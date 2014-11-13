package core.domain.maps;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import core.applicationService.vikiMapServacs.GraphNode;
import core.domain.warriors.defenders.towers.vikiTowers.BasicTower;
import core.domain.warriors.defenders.towers.vikiTowers.TowerInterface;
import critters.Critter;
import UI.CanvaObject;


public interface Grid {

	void draw(Graphics g);
	int getHeight();
	int getWidth();
	int getUnitSize();
	void setSize(int newWidth, int newheight);
	int getCellType(int i, int j);
	void setCell(int cordinatX, int cordinatY, int type);
	void setContent(int[][] newContent);
	int[][] getContent();
	void setPath(Stack<GraphNode> path);
	void addTower(TowerInterface newTower, String positionKey);

	//public void stopWave();
	public void registerTowerAsObserver(int positionx, int positiony,  TowerInterface tower);
	void unregisterObserver(TowerInterface tower);
	TowerInterface getTower(String towerPosition); 
	ArrayList<GridCell> getPath();
	void addCritter(Critter newCritter);
	ArrayList<Critter> getCritters();
	HashMap<String, TowerInterface> getTowers();
	public void getState(int state);
	
	
}
