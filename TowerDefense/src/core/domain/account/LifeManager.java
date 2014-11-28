package core.domain.account;

import core.contract.AccountConstants;

public class LifeManager {
	private static LifeManager instance = null;
	public static int life;
	private static int idManager;
	
	// Exists only to defeat instantiation.
	public LifeManager() {
		
	}
	public static LifeManager getInstance() {
		if(instance == null) {
			instance = new LifeManager();
			life = AccountConstants.DEFAULT_LIFE;
			idManager = 0;
		}
		return instance;
	}
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}
	public int getLife() {
		return life;
	}
	public  void setLife(int life) {
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
	public int getIdManager() {
		return idManager;
	}
	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}
}