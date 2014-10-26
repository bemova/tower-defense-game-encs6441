package UI.towerdesign;

import javax.swing.JPanel;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class TowerInspection extends JPanel {

	/**
	 * Create the panel.
	 */
	public TowerInspection(Tower tower) {
		
		TowerManagerPanel towerManagerPanel = new TowerManagerPanel(tower);
		add(towerManagerPanel);

	}

}
