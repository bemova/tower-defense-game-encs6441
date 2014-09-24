package ThreadPul;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import GameDesign.*;

public class DrawThread extends Thread{
	ArrayList<Enemy> listOfMapObjects= null;
	Graphics graphics = null;
	ArrayList<Position> processingPatCordinate = new ArrayList<Position>();
	GameMap canva = null;
	
	
	public DrawThread(ArrayList<Enemy> listOfMapObjects, Graphics graphics, ArrayList<Position> processingPatCordinate, GameMap canva) {
		this.listOfMapObjects = listOfMapObjects; 
		this.graphics = graphics;
		this. processingPatCordinate = processingPatCordinate;
		this.canva = canva;
	}
	
@Override
public void run(){
	while(true){
	
	try {
		sleep(60);
		draw(listOfMapObjects);
		canva.repaint();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
};

void draw(ArrayList<Enemy> listOfEnemies){ // draw only enemies 
	synchronized (listOfMapObjects){
		Iterator<Enemy> iterator = listOfMapObjects.iterator();
		int newCoordY = 0;
		int newCoordX = 0;
		while (iterator.hasNext()){ 
			Date date = new Date();
			Enemy enemy = iterator.next();
			long  numberOfMiliseconds = date.getTime() -enemy.lastUpdateTime.getTime(); // how many milliseconds are passed after the last update
			enemy.lastUpdateTime = date; // update last updated time
			int  numberOfPixelsPassed = (int)(Math.round((numberOfMiliseconds/30)*enemy.numberOfPicselsPerGivenTime));
			

			
			String currentMovingDirection = processingPatCordinate.get(enemy.positionOnMap).direction;
			Position newPOsition = new Position();
			if(enemy.positionOnMap + 1 <= processingPatCordinate.size() - 1) {
			newPOsition = processingPatCordinate.get(enemy.positionOnMap +1); //new rectangle the object has to go over

			}
			if( currentMovingDirection =="up"  || currentMovingDirection == "down" ){  //if the object was moving up or down then the y coordinate was changed 
	
				enemy.picselY = enemy.picselY + numberOfPixelsPassed; // position in pixels relatively to the beginning of the current rectangle 

				if( enemy.picselY +10 <= 30){ // add size of the object, if object is still in its current rectangle
						 newCoordY = processingPatCordinate.get(enemy.positionOnMap).i *30;
						 newCoordX = processingPatCordinate.get(enemy.positionOnMap).j *30;
					if(currentMovingDirection =="up") newCoordY = newCoordY - enemy.picselY;
					else newCoordY = newCoordY + enemy.picselY;
				}
				else{ //if object has jumped to the next position on the path...
					 newCoordY = newPOsition.i *30;
					 newCoordX = newPOsition.j *30;
					 enemy.positionOnMap = enemy.positionOnMap + 1;
					if(newPOsition.direction == "up" || newPOsition.direction == "down" ){
						enemy.picselY = 30 -  enemy.picselY +10;
						
						if(newPOsition.direction == "up") newCoordY = newCoordY - enemy.picselY;
						else newCoordY =  newCoordY + enemy.picselY;
					}else {
						if(newPOsition.direction == "right" || newPOsition.direction == "left"){
							enemy.picselX = 30 -  enemy.picselY +10;
							enemy.picselY = 0;
							if( newPOsition.direction == "right" ) newCoordX =  newCoordX + enemy.picselX;
							else newCoordX =  newCoordX - enemy.picselX;
						}
						
					}
					
				}
				
			}else{
				
				if( currentMovingDirection =="left"  || currentMovingDirection == "right" ){
					
					enemy.picselX = enemy.picselX + numberOfPixelsPassed;
					
					if( enemy.picselX +10 <= 30){ // add size of the object, if object is still in its current rectangle
						 newCoordY = processingPatCordinate.get(enemy.positionOnMap).i *30;
						 newCoordX = processingPatCordinate.get(enemy.positionOnMap).j *30;
					if(currentMovingDirection =="left") newCoordX = newCoordX - enemy.picselX;
					else newCoordX = newCoordX + enemy.picselX;
				}else  {//if object has jumped to the next position on the path...
					 newCoordY = newPOsition.i *30;
					 newCoordX = newPOsition.j *30;
					 enemy.positionOnMap = enemy.positionOnMap + 1;
					if(newPOsition.direction == "left" || newPOsition.direction == "right" ){
						enemy.picselX = 30 -  enemy.picselX +10;
						
						if(newPOsition.direction == "left") newCoordX = newCoordX - enemy.picselX;
						else newCoordX =  newCoordX + enemy.picselX;
					}else {
						if(newPOsition.direction == "up" || newPOsition.direction == "down"){
							enemy.picselY = 30 -  enemy.picselX +10;
							enemy.picselX = 0;
							if( newPOsition.direction == "down" ) newCoordY =  newCoordY + enemy.picselY;
							else newCoordY =  newCoordY - enemy.picselY;
						}
						
					}
									
				}
				}
			}
			
		//	enemy.draw(graphics, date);
		/////////////////change this later
		//	graphics.setColor(Color.red);
		//	graphics.fillRect(newCoordX, newCoordY, 15 ,15 );
			//canva.repaint();
			
			enemy.realCoordinatX = newCoordX;
			enemy.realCoordinatY = newCoordY;
		}

	}
	
};

}
