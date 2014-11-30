package core.applicationservice.gameservices;

import infrastructure.loggin.Log4jLogger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ui.game.GridMap;
import core.domain.account.BankManager;
import core.domain.account.LifeManager;

public class GameStateManager implements Serializable{
	private static final Log4jLogger logger = new Log4jLogger();
	private GridMap map;
	private int wave;
	private long balance;
	private long currentBalance;
	private int life; 
	private String mapLocation;
	
	public GameStateManager(GridMap map, int wave, String mapLocation){
		this.map = map;
		this.wave = wave;
		this.mapLocation = mapLocation;
		this.balance = BankManager.getInstance().getBalance();
		this.currentBalance = BankManager.getInstance().getCurrentBalance();
		this.life = LifeManager.getInstance().getLife();
	}
	
	public static void save(String fileName, GameStateManager game){
		
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
	        oos.writeObject(game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static GameStateManager load(String fileName){
		GameStateManager game = null;
		try {
			FileInputStream streamIn = new FileInputStream(fileName);
			ObjectInputStream objectinputstream = new ObjectInputStream(
					streamIn);
			Object obj = objectinputstream.readObject();
			if(obj instanceof GameStateManager){
				game = (GameStateManager) obj;
			}
			objectinputstream.close();
		} catch (Exception e) {
			logger.writer("GameSaver.java", e);
		}
		return game;
	}

	public GridMap getMap() {
		return map;
	}

	public int getWaveNum() {
		return wave;
	}
	
	public long getBalance(){
		return balance;
	}

	public long getCurrentBalance(){
		return currentBalance;
	}
	
	public int getLife(){
		return life;
	}
	
	public String getMapLocation(){
		return mapLocation;
	}
}
