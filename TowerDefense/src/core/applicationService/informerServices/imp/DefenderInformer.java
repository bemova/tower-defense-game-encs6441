package core.applicationService.informerServices.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.applicationService.informerServices.IDefenderInformer;
import core.applicationService.informerServices.Observer;
import core.contract.ITDLogger;
import core.domain.Subject;
import core.domain.waves.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class DefenderInformer.
 */
@Component
public class DefenderInformer implements Subject, IDefenderInformer {
	
	/**
	 * The wave head position.
	 */
	private Position waveHeadPosition;
	
	/** The observers. */
	private List<Observer> observers;
	
	/** The logger. */
	private ITDLogger logger;
	
	/**
	 * Instantiates a new defender informer.
	 */
	public DefenderInformer(){
		observers = new ArrayList<Observer>(); 
	}
	
	/**
	 * Sets the logger.
	 *
	 * @param logger the new logger
	 */
	@Autowired
    public void setLogger(ITDLogger logger) {
		this.logger = logger;
	}
	
	/* (non-Javadoc)
	 * @see core.domain.Subject#registerObserver(core.applicationService.informerServices.Observer)
	 */
	@Override
	public void registerObserver(Observer o) {
		try {
			if( o !=null)
				observers.add(o);
			
		} catch (Exception e) {
			logger.writer("DefenderInformer-->registerObserver", e);
		}
	}


	/* (non-Javadoc)
	 * @see core.domain.Subject#removeObserver(core.applicationService.informerServices.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		try {
			if (o !=null)
				observers.remove(o);
		} catch (Exception e) {
			logger.writer("DefenderInformer-->removeObserver", e);
		}
	}

	
	/* (non-Javadoc)
	 * @see core.domain.Subject#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		try {
			for (Observer ob : observers) {
				ob.update(waveHeadPosition);
			}
		} catch (Exception e) {
			logger.writer("DefenderInformer-->notifyObservers", e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.IDefenderInformer#setPosition(int, int)
	 */
	@Override
	public void setPosition(int x, int y){
		try {
			this.waveHeadPosition.setX(x);
			this.waveHeadPosition.setY(y);
			positionChange();
		} catch (Exception e) {
			logger.writer("DefenderInformer-->setPosition", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.IDefenderInformer#positionChange()
	 */
	@Override
	public void positionChange(){
		try {
			notifyObservers();
		} catch (Exception e) {
			logger.writer("DefenderInformer-->positionChange", e);
		}
	}

	
	/* (non-Javadoc)
	 * @see core.applicationService.informerServices.IDefenderInformer#getPosition()
	 */
	@Override
	public Position getPosition() {
		return waveHeadPosition;
	}

}
