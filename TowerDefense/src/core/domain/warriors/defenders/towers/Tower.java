package core.domain.warriors.defenders.towers;

import java.util.UUID;

import core.domain.Subject;
import core.domain.warriors.defenders.Defender;
import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.ShootingBehaviour;
import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
import core.domain.warriors.defenders.towers.vikiTowers.TowerParameters;


public abstract class Tower extends Defender {

	public String Id= UUID.randomUUID().toString();
	MovingBehaviour movingBehaviour;
	ShootingBehaviour shootingBehaviour;
	SoundBehaviour soundBehaviour;
	// decorator parts
	protected String description;
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

	public abstract void display();
	public abstract long cost();
//	public abstract TowerParameters returnParameters(); // addd by Vika, needs to be replaced later 

}
