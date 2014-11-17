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

/**
 * Based on our design we can have different type of defenders 
 * Towers is one of these types which we currently use in this version of the game
 * @author Team 5
 *
 */
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
	
	/**
	 * 
	 * @return towerType
	 */
	public TowerType getTowerType() {
		return towerType;
	}
	/**
	 * 
	 * @param towerType
	 */
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}
	/**
	 * 
	 * @return getShootingStrategy
	 */
	public String getShootingStrategy() {
		return shootingStrategy;
	}
	/**
	 * 
	 * @param shootingStrategy
	 */
	public void setShootingStrategy(String shootingStrategy) {
		this.shootingStrategy = shootingStrategy;
	}
	/**
	 * 
	 * @return towerPosition
	 */
	public Position getTowerPosition() {
		return towerPosition;
	}
	/**
	 * 
	 * @param towerPosition
	 */
	public void setTowerPosition(Position towerPosition) {
		this.towerPosition = towerPosition;
	}
	/**
	 * 
	 * @return level
	 */
	public TowerLevel getLevel() {
		return level;
	}
	/**
	 * 
	 * @param level
	 */
	public void setLevel(TowerLevel level) {
		this.level = level;
	}
	// decorator parts
	protected String description;
	
	protected List<Tower> towers;
	public List<Tower> getTowers() {
		return towers;
	}
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @param movingBehaviour
	 */
	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	/**
	 * 
	 * @param shootingBehaviour
	 */
	public void setShootingBehaviour(ShootingBehaviour shootingBehaviour) {
		this.shootingBehaviour = shootingBehaviour;
	}
	/**
	 * 
	 * @param soundBehaviour
	 */
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
	/**
	 * 
	 * @return iceShootingBehaviour
	 */
	public IceShootingBehaviour getIceShootingBehaviour() {
		return iceShootingBehaviour;
	}
	/**
	 * 
	 * @param iceShootingBehaviour
	 */
	public void setIceShootingBehaviour(IceShootingBehaviour iceShootingBehaviour) {
		this.iceShootingBehaviour = iceShootingBehaviour;
	}
	/**
	 * 
	 * @return lineShootingBehaviour
	 */
	public LineShootingBehaviour getLineShootingBehaviour() {
		return lineShootingBehaviour;
	}
	/**
	 * 
	 * @param lineShootingBehaviour
	 */
	public void setLineShootingBehaviour(LineShootingBehaviour lineShootingBehaviour) {
		this.lineShootingBehaviour = lineShootingBehaviour;
	}
	/**
	 * 
	 * @return bulletShootingBehaviour
	 */
	public BulletShootingBehaviour getBulletShootingBehaviour() {
		return bulletShootingBehaviour;
	}
	/**
	 * Perform moving behavior
	 */
	public void performMovingBehaviour(){
		movingBehaviour.move();
	}
	/**
	 * Perform shooting behavior
	 */
	public void performShootingBehaviour(){
		shootingBehaviour.shoot();
	}
	/**
	 * Perform sound behavior
	 */
	public void performSoundBehaviour(){
		soundBehaviour.sound();
	}
	
	public abstract String display();
	public abstract long cost();
	/**
	 * Create a list of towers in our map
	 * @return towers
	 */
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
