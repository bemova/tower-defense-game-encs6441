package core.applicationService.informerServices;

import core.domain.waves.Position;

/**
 * <b>
 * the interfaces that DefenderInformer calss have to implement for dependency injection 
 * for the futures build
 * </b>
 * @author Team5,ali
 * @version 0.1
 */
public interface IDefenderInformer {


	public void registerObserver(Observer o);

	/**
	 * 
	 * this signature can show removing one of tower or defender from observers'list of defenders
	 * @param Observer the object the you want to remove from the observers'list
	 */
	public void removeObserver(Observer o);

	/**
	 * Signature of the method of notify defenders about the latest position of wave 
	 */
	public void notifyObservers();

	/**
	 * set the wave's head position
	 * @param x as integer 
	 * @param y as integer
	 */
	public void setPosition(int x, int y);
	/**
	 * <b>signature of the position changes</b>
	 */
	public void positionChange();

	/**
	 * this signature represent the way of getting position of the wave's head
	 * @return Position 
	 */
	public Position getPosition();

}