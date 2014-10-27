package UI.towerdesign;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.applicationService.warriorServices.TowerFactory;
import core.applicationService.warriorServices.TowerMarket;
import core.domain.account.BankManager;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;

@SuppressWarnings("serial")
public class SimpleInspection extends JDialog implements ActionListener {
	private String towerType;
	private BankManager bank;
	private Tower tower;


	JLabel speedCount;
	JLabel rangeCount;
	JLabel powerCount;
	JLabel valueCount;
	JLabel sellPriceLable;
	JLabel sellPriceCount;
	JLabel levelLabel;
	JButton upgradeBtn;
	JPanel panel;

	/**
	 * Create the panel.
	 */
	public SimpleInspection(Tower tower) {
		panel = new JPanel();
		this.tower = tower;
		setLayout(new FlowLayout());
		setTitle("Tower Inspection");
		
		List<Tower> towerList = tower.objectDetials();
		TowerFactory f = new TowerFactory();
		this.towerType = f.getDecoratedName(towerList);
		this.bank = BankManager.getInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gridBagLayout);

		JLabel speedLable = new JLabel("Fire Speed");
		speedLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		panel.add(speedLable, gbc_speedLable);

		speedCount = new JLabel("");
		GridBagConstraints gbc_speedCount = new GridBagConstraints();
		gbc_speedCount.insets = new Insets(0, 0, 5, 0);
		gbc_speedCount.gridx = 2;
		gbc_speedCount.gridy = 1;
		panel.add(speedCount, gbc_speedCount);

		JLabel powerLable = new JLabel("Fire Power");
		powerLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 2;
		panel.add(powerLable, gbc_powerLable);

		powerCount = new JLabel("");
		GridBagConstraints gbc_powerCount = new GridBagConstraints();
		gbc_powerCount.insets = new Insets(0, 0, 5, 0);
		gbc_powerCount.gridx = 2;
		gbc_powerCount.gridy = 2;
		panel.add(powerCount, gbc_powerCount);

		JLabel rangeLable = new JLabel("Fire Range");
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rangeLable = new GridBagConstraints();
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 3;
		panel.add(rangeLable, gbc_rangeLable);

		rangeCount = new JLabel("");
		GridBagConstraints gbc_rangeCount = new GridBagConstraints();
		gbc_rangeCount.insets = new Insets(0, 0, 5, 0);
		gbc_rangeCount.gridx = 2;
		gbc_rangeCount.gridy = 3;
		panel.add(rangeCount, gbc_rangeCount);

		JLabel valueLable = new JLabel("Value         ");
		valueLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_valueLable = new GridBagConstraints();
		gbc_valueLable.insets = new Insets(0, 0, 5, 5);
		gbc_valueLable.gridx = 1;
		gbc_valueLable.gridy = 4;
		panel.add(valueLable, gbc_valueLable);

		valueCount = new JLabel("");
		GridBagConstraints gbc_valueCount = new GridBagConstraints();
		gbc_valueCount.insets = new Insets(0, 0, 5, 0);
		gbc_valueCount.gridx = 2;
		gbc_valueCount.gridy = 4;
		panel.add(valueCount, gbc_valueCount);

		upgradeBtn = new JButton("Upgrade");
		upgradeBtn.setSize(30, 20);
		upgradeBtn.addActionListener(this);
		if (tower.getLevel().equals(TowerLevel.three)) {
			this.upgradeBtn.setEnabled(false);
		}

		JLabel label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 5;
		panel.add(label_1, gbc_label_1);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;

		levelLabel = new JLabel("");
		GridBagConstraints gbc_levelLabel = new GridBagConstraints();
		gbc_levelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_levelLabel.gridx = 1;
		gbc_levelLabel.gridy = 6;
		panel.add(levelLabel, gbc_levelLabel);
		GridBagConstraints gbc_upgradeBtn = new GridBagConstraints();
		gbc_upgradeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_upgradeBtn.gridx = 2;
		gbc_upgradeBtn.gridy = 6;
		panel.add(upgradeBtn, gbc_upgradeBtn);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 7;
		panel.add(label, gbc_label);

		sellPriceLable = new JLabel("Sell Price ");
		sellPriceLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_sellPriceLable = new GridBagConstraints();
		gbc_sellPriceLable.insets = new Insets(0, 0, 5, 5);
		gbc_sellPriceLable.gridx = 1;
		gbc_sellPriceLable.gridy = 8;
		panel.add(sellPriceLable, gbc_sellPriceLable);

		sellPriceCount = new JLabel("");
		GridBagConstraints gbc_sellPriceCount = new GridBagConstraints();
		gbc_sellPriceCount.insets = new Insets(0, 0, 5, 0);
		gbc_sellPriceCount.gridx = 2;
		gbc_sellPriceCount.gridy = 8;
		panel.add(sellPriceCount, gbc_sellPriceCount);
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
		levelLabel.setText("Level " + tower.getLevel());
		JButton sellBtn = new JButton("Sell");
		sellBtn.setSize(30, 20);
		sellBtn.addActionListener(this);
		GridBagConstraints gbc_sellBtn = new GridBagConstraints();
		gbc_sellBtn.gridx = 2;
		gbc_sellBtn.gridy = 9;
		panel.add(sellBtn, gbc_sellBtn);
		TowerMarket market = new TowerMarket();
		long sellPrice = market.sellTower(tower);
		sellPriceCount.setText(new Long(sellPrice).toString());

		// end of texboxes'text setting
		add(panel);
		setVisible(true);
		setSize(300, 280);
		setLocationRelativeTo(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case "Upgrade":
			upgrade();
			break;
		case "Sell":
			sell();
			break;
		}
	}

	private void sell() {
		String str = sellPriceCount.getText();
		long temp = bank.getCurrentBalance();
		long sellPrice = new Long(str);
		temp -= sellPrice;
		bank.resetCurrentBalance();
		bank.setCurrentBalance(temp);
	}

	private void upgrade() {
		if (!tower.getLevel().equals(TowerLevel.three)) {
			String str = this.speedCount.getText();
			int speedCount = Integer.parseInt(str) + 1;
			str = rangeCount.getText();
			int rangeCount = Integer.parseInt(str) + 1;
			str = powerCount.getText();
			int powerCount = Integer.parseInt(str) + 1;

			Tower newTower = upgradeLevel(tower, towerType, speedCount,
					rangeCount, powerCount);
			
			//if()
			this.tower = newTower;
			this.speedCount.setText(new Integer(speedCount++).toString());
			this.rangeCount.setText(new Integer(rangeCount++).toString());
			this.powerCount.setText(new Integer(powerCount++).toString());
			this.valueCount.setText(Long.toString(newTower.cost()));
			TowerMarket market = new TowerMarket();
			this.sellPriceCount.setText(Double.toString(market
					.sellTower(newTower)));
			this.levelLabel.setText("Level " + newTower.getLevel().toString());
			if (newTower.getLevel().equals(TowerLevel.three)) {
				this.upgradeBtn.setEnabled(false);
			}
		}

		// this.setVisible(false);
	}

	public Tower getTower() {
		return this.tower;
	}

	private Tower upgradeLevel(Tower tower, String towerType, int speedCount,
			int rangeCount, int powerCount) {
		TowerFactory factory = new TowerFactory();

		Tower createdTower = factory.updateLevel(towerType, speedCount,
				rangeCount, powerCount);

		long value = tower.cost();
		Tower tempTower = createdTower;
		long delta = tempTower.cost() - value;
		if (delta < bank.getBalance() - bank.getCurrentBalance()) {
			bank.setCurrentBalance(delta);
			switch (tower.getLevel()) {
			case one:
				tempTower.setLevel(TowerLevel.two);
				break;
			case two:
				tempTower.setLevel(TowerLevel.three);
				break;
			default:
				break;
			}

			tower = tempTower;
			return tower;
		}
		return null;
	}

}
