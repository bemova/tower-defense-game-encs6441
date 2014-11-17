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
	 * Return tower type
	 * @return towerType
	 */
	public TowerType getTowerType() {
		return towerType;
	}
	/**
	 * Set tower type
	 * @param towerType tower type is a enum that can describe the decorated object for a tower
	 */
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}
	/**
	 * Return shooting strategy
	 * @return getShootingStrategy
	 */
	public String getShootingStrategy() {
		return shootingStrategy;
	}
	/**
	 * Set shooting strategy
	 * @param shootingStrategy shooting strategy for killing the critters
	 */
	public void setShootingStrategy(String shootingStrategy) {
		this.shootingStrategy = shootingStrategy;
	}
	/**
	 * return tower position
	 * @return towerPosition
	 */
	public Position getTowerPosition() {
		return towerPosition;
	}
	/**
	 * Set tower position
	 * @param towerPosition tower position on the map
	 */
	public void setTowerPosition(Position towerPosition) {
		this.towerPosition = towerPosition;
	}
	/**
	 * Return tower level
	 * @return level
	 */
	public TowerLevel getLevel() {
		return level;
	}
	/**
	 * Set tower level
	 * @param level tower level that is a between one  to three
	 */
	public void setLevel(TowerLevel level) {
		this.level = level;
	}
	// decorator parts
	protected String description;
	
	protected List<Tower> towers;
	/**
	 * Get tower list
	 * @return towers
	 */
	public List<Tower> getTowers() {
		return towers;
	}
	/**
	 * Return tower description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Set moving behavior
	 * @param movingBehaviour moving behaviour like regular moving and so on.
	 */
	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	/**
	 * Set shooting behavior
	 * @param shootingBehaviour shooting behaviour
	 */
	public void setShootingBehaviour(ShootingBehaviour shootingBehaviour) {
		this.shootingBehaviour = shootingBehaviour;
	}
	/**
	 * Set sound behavior
	 * @param soundBehaviour sound behaviour for a tower
	 */
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}
	/**
	 * Sets the sound behaviour.
	 *
	 * @param bulletShootingBehaviour the new sound behaviour
	 */
	public void setBulletShootingBehaviour(BulletShootingBehaviour bulletShootingBehaviour) {
		this.bulletShootingBehaviour = bulletShootingBehaviour;
	}
	/**
	 * Return Ice shooting behavior
	 * @return iceShootingBehaviour
	 */
	public IceShootingBehaviour getIceShootingBehaviour() {
		return iceShootingBehaviour;
	}
	/**
	 * Set ice shooting behavior
	 * @param iceShootingBehaviour by this behaviour we will freez the critter.
	 */
	public void setIceShootingBehaviour(IceShootingBehaviour iceShootingBehaviour) {
		this.iceShootingBehaviour = iceShootingBehaviour;
	}
	/**
	 * Return line shooting behavior
	 * @return lineShootingBehaviour
	 */
	public LineShootingBehaviour getLineShootingBehaviour() {
		return lineShootingBehaviour;
	}
	/**
	 * Set line shooting behavior
	 * @param lineShootingBehaviour shooting shape will be a line
	 */
	public void setLineShootingBehaviour(LineShootingBehaviour lineShootingBehaviour) {
		this.lineShootingBehaviour = lineShootingBehaviour;
	}
	/**
	 * Return bullet shooting behavior
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
