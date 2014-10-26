package core.domain.account;

import core.contract.AccountConstants;

public class BankManager { 

	private static BankManager instance = null;
	public static long balance;
	public static long currentBalance;
	
	
	public BankManager() {
		// Exists only to defeat instantiation.
	}
	public static BankManager getInstance() {
		if(instance == null) {
			instance = new BankManager();
			balance = AccountConstants.DEFAULT_BALANCE;
			currentBalance = 0;
		}
		return instance;
	}
	public long getBalance() {
		return balance;
	}
	public void addBalance(long balance) {
		BankManager.balance += balance;
	}
	public long getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(long currentBalance) {
		BankManager.currentBalance += currentBalance;
	}
}