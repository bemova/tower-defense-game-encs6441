package UI.towerdesign;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import core.applicationService.warriorServices.TowerFactory;
import core.contract.DefenderConstants;
import core.domain.account.BankManager;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;

public class TowerManagerPanel extends Observable {//JPanel
	private JTextField speedField;
	private JTextField rangeField;
	private JTextField powerField;
	private long currentBalance;
	private long expectedBalance;
	private long objBalance;
	private String towerType;
	private Tower decoratedTower;
	private JButton submit;
	private JLabel balanceLable;
	private JButton purchaseBtn;
	private BankManager bank;

	public Tower getDecoratedTower() {
		return decoratedTower;
	}

	/**
	 * Create the panel.
	 * @param tower that we want to show its information 
	 */
	public TowerManagerPanel(Tower tower) {
		this.objBalance = tower.cost();
		this.bank = BankManager.getInstance();
		this.towerType = tower.getClass().getSimpleName();
		this.currentBalance = bank.getCurrentBalance();
		submit = new JButton("Submit");
		balanceLable = new JLabel("you don't have enough money to upgrade");
		balanceLable.setVisible(false);
		balanceLable.setForeground(Color.RED);
		purchaseBtn = new JButton("Purchase");
		purchaseBtn.setForeground(Color.BLACK);
		purchaseBtn.setBackground(Color.RED);
		purchaseBtn.setMnemonic(KeyEvent.VK_P);
		purchaseBtn.setVisible(false);
		//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 97, 0, 0, 0, 176, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_balanceLable = new GridBagConstraints();
		gbc_balanceLable.gridwidth = 3;
		gbc_balanceLable.insets = new Insets(0, 0, 5, 5);
		gbc_balanceLable.gridx = 1;
		gbc_balanceLable.gridy = 0;
		add(balanceLable, gbc_balanceLable);

		GridBagConstraints gbc_purchaseBtn = new GridBagConstraints();
		gbc_purchaseBtn.insets = new Insets(0, 0, 5, 5);
		gbc_purchaseBtn.gridx = 4;
		gbc_purchaseBtn.gridy = 0;
		add(purchaseBtn, gbc_purchaseBtn);

		JLabel speedLable = new JLabel("Fire Speed");
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.anchor = GridBagConstraints.EAST;
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		add(speedLable, gbc_speedLable);

		speedField = new JTextField();
		speedField.setEditable(false);

		GridBagConstraints gbc_speedField = new GridBagConstraints();
		gbc_speedField.insets = new Insets(0, 0, 5, 5);
		gbc_speedField.fill = GridBagConstraints.HORIZONTAL;
		gbc_speedField.gridx = 2;
		gbc_speedField.gridy = 1;
		add(speedField, gbc_speedField);
		speedField.setColumns(10);

		JButton speedUpBtn = new JButton("+");

		GridBagConstraints gbc_speedUpBtn = new GridBagConstraints();
		gbc_speedUpBtn.insets = new Insets(0, 0, 5, 5);
		gbc_speedUpBtn.gridx = 3;
		gbc_speedUpBtn.gridy = 1;
		add(speedUpBtn, gbc_speedUpBtn);

		JButton speedDownBtn = new JButton("-");

		GridBagConstraints gbc_speedDownBtn = new GridBagConstraints();
		gbc_speedDownBtn.insets = new Insets(0, 0, 5, 5);
		gbc_speedDownBtn.gridx = 4;
		gbc_speedDownBtn.gridy = 1;
		add(speedDownBtn, gbc_speedDownBtn);

		JLabel rangeLabel = new JLabel("Fire Range");
		GridBagConstraints gbc_rangeLabel = new GridBagConstraints();
		gbc_rangeLabel.anchor = GridBagConstraints.EAST;
		gbc_rangeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLabel.gridx = 1;
		gbc_rangeLabel.gridy = 2;
		add(rangeLabel, gbc_rangeLabel);

		rangeField = new JTextField();
		rangeField.setEditable(false);

		GridBagConstraints gbc_rangeField = new GridBagConstraints();
		gbc_rangeField.insets = new Insets(0, 0, 5, 5);
		gbc_rangeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rangeField.gridx = 2;
		gbc_rangeField.gridy = 2;
		add(rangeField, gbc_rangeField);
		rangeField.setColumns(10);

		JButton rangeUpBtn = new JButton("+");

		GridBagConstraints gbc_rangeUpBtn = new GridBagConstraints();
		gbc_rangeUpBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rangeUpBtn.gridx = 3;
		gbc_rangeUpBtn.gridy = 2;
		add(rangeUpBtn, gbc_rangeUpBtn);

		JButton rangeDownBtn = new JButton("-");

		GridBagConstraints gbc_rangeDownBtn = new GridBagConstraints();
		gbc_rangeDownBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rangeDownBtn.gridx = 4;
		gbc_rangeDownBtn.gridy = 2;
		add(rangeDownBtn, gbc_rangeDownBtn);

		JLabel powerLable = new JLabel("Fire Power");
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.anchor = GridBagConstraints.EAST;
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 3;
		add(powerLable, gbc_powerLable);

		powerField = new JTextField();
		powerField.setEditable(false);

		GridBagConstraints gbc_powerField = new GridBagConstraints();
		gbc_powerField.insets = new Insets(0, 0, 5, 5);
		gbc_powerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_powerField.gridx = 2;
		gbc_powerField.gridy = 3;
		add(powerField, gbc_powerField);
		powerField.setColumns(10);

		JButton powerUpBtn = new JButton("+");

		powerUpBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_powerUpBtn = new GridBagConstraints();
		gbc_powerUpBtn.insets = new Insets(0, 0, 5, 5);
		gbc_powerUpBtn.gridx = 3;
		gbc_powerUpBtn.gridy = 3;
		add(powerUpBtn, gbc_powerUpBtn);

		JButton powerDownBtn = new JButton("-");

		GridBagConstraints gbc_powerDownBtn = new GridBagConstraints();
		gbc_powerDownBtn.insets = new Insets(0, 0, 5, 5);
		gbc_powerDownBtn.gridx = 4;
		gbc_powerDownBtn.gridy = 3;
		add(powerDownBtn, gbc_powerDownBtn);

		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.insets = new Insets(0, 0, 0, 5);
		gbc_submitBtn.gridx = 4;
		gbc_submitBtn.gridy = 4;
		add(submit, gbc_submitBtn);

		List<Tower> towerDetails = tower.objectDetials();
		TowerFactory factory = new TowerFactory();

		// set texboxes with current feature informations
		Map<String, Integer> details = factory.getFeaturesCount(towerDetails);
		Iterator<String> keySetIterator = details.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			switch (key) {
			case "FirePower":
				powerField.setText((details.get(key)).toString());
				break;
			case "FireRange":
				rangeField.setText((details.get(key)).toString());
				break;
			case "FireSpeed":
				speedField.setText((details.get(key)).toString());
				break;
			}
		}
		// end of texboxes'text setting

		// speed up and down ActionListeners
		speedUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long expected = currentBalance + DefenderConstants.FIRE_SPEED;
				if (expected > (bank.getBalance() - bank.getCurrentBalance())) {
					balanceLable.setVisible(true);
					purchaseBtn.setVisible(true);
				} else {
					up(speedField);
					expectedBalance += expected;
				}
			}
		});
		speedDownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				down(speedField);
				currentBalance -= DefenderConstants.FIRE_SPEED;
			}
		});
		// End of Speed ActionListener

		// Power up and down ActionListeners
		powerUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long expected = currentBalance + DefenderConstants.FIRE_POWER;
				if (expected > (bank.getBalance() - bank.getCurrentBalance())) {
					balanceLable.setVisible(true);
					purchaseBtn.setVisible(true);
				} else {
					up(powerField);
					expectedBalance += expected;
				}
			}
		});
		powerDownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				down(powerField);
				currentBalance -= DefenderConstants.FIRE_POWER;
			}
		});
		// End of Power ActionListener

		// Power up and down ActionListeners
		rangeUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long expected = currentBalance + DefenderConstants.FIRE_RANGE;
				if (expected > (bank.getBalance() - bank.getCurrentBalance())) {
					balanceLable.setVisible(true);
					purchaseBtn.setVisible(true);
				} else {
					up(rangeField);
					expectedBalance += expected;
				}
			}
		});
		rangeDownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				down(rangeField);
				currentBalance -= DefenderConstants.FIRE_RANGE;
			}
		});
		// End of Power ActionListener

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(currentBalance);
				towerFactory();
			}
		});

	}

	public void up(JTextField field) {
		int current = Integer.parseInt(field.getText());
		current++;
		field.setText(Integer.toString(current));
	}

	public void down(JTextField field) {
		int current = Integer.parseInt(field.getText());
		if (current > 2)
			current--;
		field.setText(Integer.toString(current));
	}

	public void towerFactory() {
		try {
			TowerFactory factory = new TowerFactory();

			// get details from text fiels
			int speedCount = Integer.parseInt(speedField.getText());
			int rangeCount = Integer.parseInt(rangeField.getText());
			int powerCount = Integer.parseInt(powerField.getText());
			// End of Details

			Tower tower = factory.updateLevel(towerType, speedCount, rangeCount, powerCount);

			this.decoratedTower = tower;
			long value = bank.getCurrentBalance();
			value -= objBalance;
			long temp = this.decoratedTower.cost();
			value += temp;
			bank.setCurrentBalance(value);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void initializeBalance(String TowerType) {
		try {
			TowerFactory factory = new TowerFactory();
			Tower t = factory.getTower(towerType);
			this.currentBalance += t.cost();
			this.currentBalance += DefenderConstants.FIRE_POWER;
			this.currentBalance += DefenderConstants.FIRE_RANGE;
			this.currentBalance += DefenderConstants.FIRE_SPEED;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
