package core.applicationservice.gameservices;

import java.util.ArrayList;
import java.util.Date;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerType;

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
		TowerType towerType;
		String description;

		TowerLog(Tower tower, String description) {
			this.id = tower.Id;
			this.towerType = tower.getTowerType();
			this.description = description;
		}

		public String toString() {
			return "id: " + id + " type: " + "towerType.name()" + " desc: "
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
}
