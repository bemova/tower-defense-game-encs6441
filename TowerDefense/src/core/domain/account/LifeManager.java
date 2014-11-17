package core.domain.account;

import core.contract.AccountConstants;

public class LifeManager {
	private static LifeManager instance = null;
	public static int life;
	
	// Exists only to defeat instantiation.
	public LifeManager() {
		
	}
	public static LifeManager getInstance() {
		if(instance == null) {
			instance = new LifeManager();
			life = AccountConstants.DEFAULT_LIFE;
		}
		return instance;
	}
	public static int getLife() {
		return life;
	}
	public static void setLife(int life) {
		LifeManager.life = life;
	}
	public void addLife(long life) {
		LifeManager.life += life;
	}
	/**
	 * this method make the total amount that have been used by player to zero
	 */
	public void resetCurrentLife() {
		LifeManager.life = 0;
	}
}