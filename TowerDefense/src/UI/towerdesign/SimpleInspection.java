package UI.towerdesign;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.applicationService.warriorServices.TowerFactory;
import core.applicationService.warriorServices.TowerMarket;
import core.domain.warriors.defenders.towers.Tower;

public class SimpleInspection extends JPanel {

	/**
	 * Create the panel.
	 */
	public SimpleInspection(Tower tower) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, -27, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel speedLable = new JLabel("FireSpeed");
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		add(speedLable, gbc_speedLable);
		
		JLabel speedCount = new JLabel("");
		GridBagConstraints gbc_speedCount = new GridBagConstraints();
		gbc_speedCount.insets = new Insets(0, 0, 5, 0);
		gbc_speedCount.gridx = 4;
		gbc_speedCount.gridy = 1;
		add(speedCount, gbc_speedCount);
		
		JLabel powerLable = new JLabel("FirePower");
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 2;
		add(powerLable, gbc_powerLable);
		
		JLabel powerCount = new JLabel("");
		GridBagConstraints gbc_powerCount = new GridBagConstraints();
		gbc_powerCount.insets = new Insets(0, 0, 5, 5);
		gbc_powerCount.gridx = 3;
		gbc_powerCount.gridy = 2;
		add(powerCount, gbc_powerCount);
		
		JLabel rangeLable = new JLabel("FireRange");
		GridBagConstraints gbc_rangeLable = new GridBagConstraints();
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 3;
		add(rangeLable, gbc_rangeLable);
		
		JLabel rangeCount = new JLabel("");
		GridBagConstraints gbc_rangeCount = new GridBagConstraints();
		gbc_rangeCount.insets = new Insets(0, 0, 5, 5);
		gbc_rangeCount.gridx = 3;
		gbc_rangeCount.gridy = 3;
		add(rangeCount, gbc_rangeCount);
		
		JButton sellBtn = new JButton("Sell");
		sellBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel valueLable = new JLabel("Value");
		GridBagConstraints gbc_valueLable = new GridBagConstraints();
		gbc_valueLable.insets = new Insets(0, 0, 5, 5);
		gbc_valueLable.gridx = 1;
		gbc_valueLable.gridy = 4;
		add(valueLable, gbc_valueLable);
		
		JLabel valueCount = new JLabel("");
		GridBagConstraints gbc_valueCount = new GridBagConstraints();
		gbc_valueCount.insets = new Insets(0, 0, 5, 5);
		gbc_valueCount.gridx = 2;
		gbc_valueCount.gridy = 4;
		add(valueCount, gbc_valueCount);
		
		JLabel sellPriceLable = new JLabel("Sell Price");
		GridBagConstraints gbc_sellPriceLable = new GridBagConstraints();
		gbc_sellPriceLable.insets = new Insets(0, 0, 5, 5);
		gbc_sellPriceLable.gridx = 1;
		gbc_sellPriceLable.gridy = 5;
		add(sellPriceLable, gbc_sellPriceLable);
		
		JLabel sellPriceCount = new JLabel("");
		GridBagConstraints gbc_sellPriceCount = new GridBagConstraints();
		gbc_sellPriceCount.insets = new Insets(0, 0, 5, 5);
		gbc_sellPriceCount.gridx = 2;
		gbc_sellPriceCount.gridy = 5;
		add(sellPriceCount, gbc_sellPriceCount);
		GridBagConstraints gbc_sellBtn = new GridBagConstraints();
		gbc_sellBtn.insets = new Insets(0, 0, 0, 5);
		gbc_sellBtn.gridx = 1;
		gbc_sellBtn.gridy = 6;
		add(sellBtn, gbc_sellBtn);
		
		JButton upgradeBtn = new JButton("Upgrade");
		upgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_upgradeBtn = new GridBagConstraints();
		gbc_upgradeBtn.insets = new Insets(0, 0, 0, 5);
		gbc_upgradeBtn.gridx = 2;
		gbc_upgradeBtn.gridy = 6;
		add(upgradeBtn, gbc_upgradeBtn);
		List<Tower> towerDetails = tower.objectDetials();
		TowerFactory factory = new TowerFactory();

		// set texboxes with current feature informations
		Map<String, Integer> details = factory.getFeaturesCount(towerDetails);
		Iterator<String> keySetIterator = details.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			switch (key) {
			case "FirePower":
				powerCount.setText((details.get(key)).toString());
				break;
			case "FireRange":
				rangeCount.setText((details.get(key)).toString());
				break;
			case "FireSpeed":
				speedCount.setText((details.get(key)).toString());
				break;
			}
		}
		long value = (tower.cost());
		valueCount.setText(Long.toString(value));
		TowerMarket market = new TowerMarket();
		double sellPrice = market.sellTower(tower);
		sellPriceCount.setText(Double.toString(sellPrice));
		// end of texboxes'text setting

	}

}
