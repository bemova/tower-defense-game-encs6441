package ThreadPul;

import GameDesign.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class CreatEnemy extends Thread{

	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	Position startingPointOnMap = new Position();
	double speed;
	public CreatEnemy(ArrayList<Enemy> enemyList, Position startingPoint,double speed ){ // Parameterized constructor
		this.enemyList = enemyList;
		startingPointOnMap = startingPoint;
		this.speed = speed;
		
	};
	
@Override 
public void run(){
	while(true){
		if(enemyList.size() == 0)
	createEnemyObject();
		else 
			if(enemyList.get(enemyList.size()-1).positionOnMap != 0 )
			createEnemyObject();
		
	}
	
};

void createEnemyObject(){
	synchronized (enemyList){
	enemyList.add(new Enemy( 0,speed, new Date() ));
	}
}

}
