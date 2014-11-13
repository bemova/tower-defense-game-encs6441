package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import bullet.Bullet;
import UI.CanvaObject;
import critters.*;
import core.applicationService.vikiMapServacs.GraphNode;
import core.domain.warriors.defenders.towers.vikiTowers.*;

public class Map extends CompleteGrid implements Observer {
	public int state = 0;
	HashMap<String, GridCell> map;
	ArrayList<GridCell> path = new ArrayList<GridCell>();
	public ArrayList<Critter> critters = new ArrayList<Critter>();
	public ArrayList<Bullet> towerBullets = new ArrayList<Bullet>();
	public HashMap<String, TowerInterface> towers;
	String entryPoint = "";
	String exitPoint = "";

	// contructor's section

	public Map(Grid grid) {
		super(grid);
		towers = new HashMap<String, TowerInterface>();
		map = new HashMap<String, GridCell>();
		for (int i = 0; i < grid.getHeight(); i++)
			for (int j = 0; j < grid.getWidth(); j++) {
				map.put(i + " " + j, new GridCell(i, j));
				map.get(i + " " + j).cellType = grid.getCellType(i, j);
			}

	};

	public void addCritter(Critter newCritter) {

		critters.add(newCritter);
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);

		for (TowerInterface tower : towers.values()) {
			tower.draw(g);
		}

		for (Critter critter : critters) {
			critter.draw(g);
		}
	};




	public String getEntryPoint() {

		return entryPoint;
	};

	public String getExitPOint() {

		return exitPoint;
	};

	public void addTower(TowerInterface newTower, String positionKey) {

		towers.put(positionKey, newTower);

	};

	public void setPath(Stack<GraphNode> newpath) {

		while (!newpath.empty()) {
			path.add(map.get(newpath.peek().cordinateX + " "
					+ newpath.peek().cordinateY));
			// path.add(new
			// GridCell(newpath.peek().cordinateX,newpath.peek().cordinateY ));
			newpath.pop();
		}

		Collections.reverse(path);
	}

	@Override
	public ArrayList<GridCell> getPath() {
		return path;
	}

	public int updateLevel(String towerKey, String upOrDown) {
		int level = 0;

		level = towers.get(towerKey).getCurrentLevel();

		// if the method return negative value we have to remove the tower from
		// the map =>
		// it is completely sell
		if (upOrDown.equals("up")) {
			if (level < 2) {
				unregisterObserver(towers.get(towerKey));
				towers.put(towerKey, new ConcreetTower(towers.get(towerKey)));
				registerTowerAsObserver((towers.get(towerKey)).getPosition().j,
						(towers.get(towerKey)).getPosition().i,
						(towers.get(towerKey)));
			}
		} else {

			towers.remove(towerKey);
			String[] pointParts = towerKey.split(" ");
			int i = Integer.parseInt(pointParts[0]);
			int j = Integer.parseInt(pointParts[1]);
			((EmptyGrid) simpleGrid).content[i][j] = 2;

		}

		return level;

	}


	@Override
	public void registerTowerAsObserver(int positionx, int positiony,
			TowerInterface tower) {

		int radius = tower.getRange();
		for (int i = positiony - radius; i <= positiony + radius; i++)
			for (int j = positionx - radius; j <= positionx + radius; j++)
				if (simpleGrid.getCellType(i, j) == 1)
					// map.get(i + " " +
					// j).addObserver((Observer)towers.get(positionKey));;
					map.get(i + " " + j).addObserver((Observer) tower);
	}

	@Override
	public TowerInterface getTower(String towerPosition) {

		return towers.get(towerPosition);
	}

	@Override
	public void unregisterObserver(TowerInterface tower) {



		int radius = tower.getRange();
		for (int i = tower.getPosition().i - radius; i <= tower.getPosition().i  + radius; i++)
			for (int j = tower.getPosition().j  - radius; j <= tower.getPosition().j  + radius; j++)
				if (simpleGrid.getCellType(i, j) == 1)
					// map.get(i + " " +
					// j).addObserver((Observer)towers.get(positionKey));;
					map.get(i + " " + j).deleteObserver((Observer)tower);
					
	
		
	}

	@Override
	public ArrayList<Critter> getCritters() {

		return critters;
		
	}

	@Override
	public HashMap<String, TowerInterface> getTowers() {
		
		return towers;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	//	critters.remove((Critter)arg0);
		
	}

	@Override
	public void getState(int newstate) {
		state = newstate;
		
	}



}
