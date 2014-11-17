package core.domain.warriors.defenders.towers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.domain.Subject;
import core.domain.warriors.defenders.Defender;
import core.domain.warriors.defenders.towers.behaviours.BulletShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.IceShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.LineShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.ShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.warriors.defenders.towers.towertype.TowerType;
import core.domain.waves.Position;


public abstract class Tower extends Defender {

	public String Id= UUID.randomUUID().toString();
	private MovingBehaviour movingBehaviour;
	private ShootingBehaviour shootingBehaviour;
	private SoundBehaviour soundBehaviour;
	private BulletShootingBehaviour  bulletShootingBehaviour;
	private IceShootingBehaviour iceShootingBehaviour;
	private LineShootingBehaviour lineShootingBehaviour;
	private TowerLevel level;
	private Position towerPosition;
	private String shootingStrategy;
	private TowerType towerType; 
	
	
	public TowerType getTowerType() {
		return towerType;
	}
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}
	public String getShootingStrategy() {
		return shootingStrategy;
	}
	public void setShootingStrategy(String shootingStrategy) {
		this.shootingStrategy = shootingStrategy;
	}
	public Position getTowerPosition() {
		return towerPosition;
	}
	public void setTowerPosition(Position towerPosition) {
		this.towerPosition = towerPosition;
	}
	public TowerLevel getLevel() {
		return level;
	}
	public void setLevel(TowerLevel level) {
		this.level = level;
	}
	// decorator parts
	protected String description;
	
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
	/**
	 * Sets the sound behaviour.
	 *
	 * @param soundBehaviour the new sound behaviour
	 */
	public void setBulletShootingBehaviour(BulletShootingBehaviour bulletShootingBehaviour) {
		this.bulletShootingBehaviour = bulletShootingBehaviour;
	}
	
	public IceShootingBehaviour getIceShootingBehaviour() {
		return iceShootingBehaviour;
	}

	public void setIceShootingBehaviour(IceShootingBehaviour iceShootingBehaviour) {
		this.iceShootingBehaviour = iceShootingBehaviour;
	}

	public LineShootingBehaviour getLineShootingBehaviour() {
		return lineShootingBehaviour;
	}

	public void setLineShootingBehaviour(LineShootingBehaviour lineShootingBehaviour) {
		this.lineShootingBehaviour = lineShootingBehaviour;
	}

	public BulletShootingBehaviour getBulletShootingBehaviour() {
		return bulletShootingBehaviour;
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
	
	public abstract String display();
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
	
}
