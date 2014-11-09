package core.domain.maps;

import java.awt.Graphics;
import java.util.Stack;

import core.applicationService.vikiMapServacs.GraphNode;
import core.domain.warriors.defenders.towers.vikiTowers.Tower;
import UI.CanvaObject;


public interface Grid {

	public void draw(Graphics g);

	public int getHeight();

	public int getWidth();

	public int getUnitSize();

	public void setSize(int newWidth, int newheight);

	public int getCellType(int i, int j);

	public void setCell(int cordinatX, int cordinatY, int type);
	
	public void startWave(CanvaObject canva);
	
	public void setContent(int[][] newContent);
	
	public int[][] getContent();
	public void setPath(Stack<GraphNode> path);
	public void addTower(Tower newTower, String positionKey);

	public void stopWave();
	
	
}
