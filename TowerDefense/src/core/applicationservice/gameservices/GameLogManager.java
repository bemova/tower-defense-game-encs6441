package core.applicationservice.gameservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import core.applicationservice.warriorservices.TowerFactory;
import core.domain.warriors.defenders.towers.Tower;

public class GameLogManager {

	private ArrayList<GameLog> gameLog;
	private static GameLogManager instance = null;

	private GameLogManager() {
		gameLog = new ArrayList<GameLog>();
	}

	public static GameLogManager getInstance() {
		if (instance == null) {
			instance = new GameLogManager();
		}
		return instance;
	}

	public void addTowerLog(int wave, Tower tower, String description) {
		instance.gameLog.add(new GameLog(wave, LogType.Tower, new TowerLog(
				tower, description)));
	}

	public void addWaveLog(int wave, String description) {
		instance.gameLog.add(new GameLog(wave, LogType.Wave, new WaveLog(
				description)));
	}

	public ArrayList<String> getWaves() {

		ArrayList<String> waves = new ArrayList<>();

		int currentWave = -1;
		for (GameLog gL : gameLog) {
			if (gL.wave != currentWave) {
				currentWave = gL.wave;
				waves.add("Wave: " + currentWave);
			}
		}
		return waves;
	}

	public ArrayList<String> getTowers() {

		ArrayList<String> towers = new ArrayList<>();
		HashMap<String, String> hM = new HashMap<String, String>();

		for (GameLog gL : gameLog) {
			if (gL.type == LogType.Tower) {
				hM.put(((TowerLog) gL.logElement).id, "Tower: "
						+ ((TowerLog) gL.logElement).id);
			}
		}

		Iterator it = hM.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			towers.add(pairs.getValue().toString());
			// it.remove(); // avoids a ConcurrentModificationException
		}
		return towers;
	}

	public String getGlobalLog() {
		String str = "";
		for (GameLog logEntry : gameLog) {
			switch (logEntry.type) {
			case Tower:
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
				break;
			case Wave:
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (WaveLog) logEntry.logElement + "\n";
				break;
			}

		}
		return str;
	}

	public String getCollectiveTowerLog() {
		String str = "";
		for (GameLog logEntry : gameLog) {
			if (logEntry.type == LogType.Tower) {
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
			}
		}
		return str;
	}

	public String getIndividualTowerLog(String towerId) {
		String str = "";
		for (GameLog logEntry : gameLog) {
			if (logEntry.type == LogType.Tower
					&& ((TowerLog) logEntry.logElement).id.equalsIgnoreCase(towerId)) {
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
			}
		}
		return str;
	}

	public String getIndividualWaveLog(int waveNum) {
		String str = "";
		for (GameLog logEntry : gameLog) {
			if (logEntry.wave == waveNum) {
				switch (logEntry.type) {
				case Tower:
					str += logEntry.time + " - wave:" + logEntry.wave + " - "
							+ (TowerLog) logEntry.logElement + "\n";
					break;
				case Wave:
					str += logEntry.time + " - wave:" + logEntry.wave + " - "
							+ (WaveLog) logEntry.logElement + "\n";
					break;
				}

			}
		}
		return str;
	}

	// Inner classes

	class GameLog {
		String time;
		int wave;
		LogType type;
		Object logElement;

		GameLog(int wave, LogType type, Object logElement) {
			this.time = new Date().toString();
			this.wave = wave;
			this.type = type;
			this.logElement = logElement;
		}
	}

	enum LogType {
		Tower, Wave;
	}

	class TowerLog {
		String id;
		String towerType;
		String description;

		TowerLog(Tower tower, String description) {
			this.id = tower.Id;
			TowerFactory factory = new TowerFactory();
			String name = factory.getDecoratedName(tower.objectDetials());
			this.towerType = name;
			this.description = description;
		}

		public String toString() {
			return "id: " + id + " type: " + towerType + " desc: "
					+ description;
		}
	}

	class WaveLog {
		String description;

		WaveLog(String description) {
			this.description = description;
		}

		public String toString() {
			return description;
		}
	}

}
