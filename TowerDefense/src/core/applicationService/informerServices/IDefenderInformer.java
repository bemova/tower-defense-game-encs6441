package core.applicationService.informerServices;

import core.applicationService.informerServices.Observer;
import core.domain.waves.Position;

public interface IDefenderInformer {

	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.imp.IDefenderInformer#registerObserver(core.applicationService.informerServices.Observer)
	 */
	public void registerObserver(Observer o);

	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.imp.IDefenderInformer#removeObserver(core.applicationService.informerServices.Observer)
	 */
	public void removeObserver(Observer o);

	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.imp.IDefenderInformer#notifyObservers()
	 */
	public void notifyObservers();

	public void setPosition(int x, int y);

	public void positionChange();

	public Position getPosition();

}