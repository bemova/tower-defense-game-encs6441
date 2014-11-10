package wavs;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import UI.CanvaObject;
import core.domain.maps.Map;
import core.domain.warriors.defenders.towers.vikiTowers.Tower;
import critters.BasicCritter;
import critters.Critter;
import critters.CritterMovingStrategy;
import critters.HigherLevelCritter;

public class Wave {

	int numberOfCritters = 10;
	Map map;
	CanvaObject canva;

	public Wave(CanvaObject _canva, Map _map) {
		canva = _canva;
		map = _map;
	}

	public void timerValue() {
		long start = System.currentTimeMillis();

		// some code executed

		long end = System.currentTimeMillis();
		long elapsed = end - start;
		long seconds = elapsed / 1000;
	}

	Timer timer = null;
	Thread thread = null;
	boolean shouldRun = true;

	public void start()
	{
		// current interval, for each critter this interval is less by intervalSeconds
		for(int i = 0; i < numberOfCritters; i++){
			Critter critter = new BasicCritter();
			critter = new HigherLevelCritter(critter);
			// the time interval seconds when the critter should appear on the map
			double intervalSeconds = (map.getUnitSize() / critter.getSpeed()) *(-i);
			critter.setAppearTime((long)(intervalSeconds * 500));
			critter.setPath(map.getPath());
			map.critters.add(critter);	
	
		}	
		
		for(Tower tower: map.towers.values()){
			//tower.createBullet(new Point(tower.getPosition().i,tower.getPosition().j ));
		}
		

		currentMillis = System.currentTimeMillis();
		shouldRun = true;

		
		thread = new Thread(){
		    public void run(){
		    	while(shouldRun)
				{
					update();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}      
		    }
		};
		
		thread.start();
	}

	public void stop() {
		shouldRun = false;
		//timer.cancel();
	}

	long currentMillis = 0;

	void update() {
		long newTime = System.currentTimeMillis();
		int timePassed = (int)(newTime - currentMillis);
		currentMillis = newTime;

		for (Critter critter : map.critters) {
			critter.updatePosition(timePassed);
		}

		for (Tower tower : map.towers.values()){
			tower.updateBullets(timePassed);
		}
		
		canva.repaint();
	}
}
