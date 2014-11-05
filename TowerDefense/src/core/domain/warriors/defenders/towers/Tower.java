package core.domain.warriors.defenders.towers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import core.domain.Subject;
import core.domain.warriors.defenders.Defender;
import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.ShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;


public abstract class Tower extends Defender {

	public String Id= UUID.randomUUID().toString();
	MovingBehaviour movingBehaviour;
	ShootingBehaviour shootingBehaviour;
	SoundBehaviour soundBehaviour;
	private TowerLevel level;
	
	public TowerLevel getLevel() {
		return level;
	}
	public void setLevel(TowerLevel level) {
		this.level = level;
	}
	// decorator parts
	protected String description;
	public Map<String, Position> CrittersLocation;
	public Map<String, Position> getCrittersLocation() {
		return CrittersLocation;
	}
	public void setCrittersLocation(Map<String, Position> crittersLocation) {
		CrittersLocation = crittersLocation;
	}
	protected List<Tower> towers;
	public List<Tower> getTowers() {
		return towers;
	}
	public String getDescription() {
		return description;
	}

	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	public void setShootingBehaviour(ShootingBehaviour shootingBehaviour) {
		this.shootingBehaviour = shootingBehaviour;
	}
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}
	public void performMovingBehaviour(){
		movingBehaviour.move();
	}
	public void performShootingBehaviour(){
		shootingBehaviour.shoot();
	}
	public void performSoundBehaviour(){
		soundBehaviour.sound();
	}
	public void register(Subject defenderInformer) {
		this.subject = defenderInformer;
		this.subject.registerObserver(this);
	}

	public abstract Color display();
	public abstract long cost();
	public List<Tower> objectDetials(){
		try {
			if(towers != null)
				towers.add(this);
			else{
				towers = new ArrayList<Tower>();
				towers.add(this);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return towers;
	}
	public void alienUpdate(Position alienPosition, String critterId) {
		
		this.alienPosition = alienPosition;
	}
}
