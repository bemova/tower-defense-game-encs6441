package ui.game;

import core.domain.warriors.defenders.towers.Tower;

public class SelectedTower {

	private String towerType;
	private Tower tower;
	private boolean addTowerFlag;
	
	private static SelectedTower instance;
	
	private SelectedTower(){
		
	}
	
	public static void setInstance(String towerType, Tower tower, boolean addTowerFlag){
		if(instance == null){
			instance = new SelectedTower();
		}
		instance.towerType = towerType;
		instance.tower = tower;
		instance.addTowerFlag = addTowerFlag;
	}

	public static String getTowerType(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.towerType;
	}

	public static Tower getTower(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.tower;
	}

	public static boolean getAddTowerFlag(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.addTowerFlag;
	}

}
