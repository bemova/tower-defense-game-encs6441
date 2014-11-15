package ui.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GameControllerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameControllerPanel(LayeredMapPanel mapPanel) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Critter Info");
		tglbtnNewToggleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				mapPanel.critterT.start();
				
//				LayeredMapPanelOtherItems otherItemsPanel = mapPanel.getOtherItemsPanel(); 
//				otherItemsPanel.performScene();
			}
		});
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton.gridx = 0;
		gbc_tglbtnNewToggleButton.gridy = 0;
		add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Pause Game");
		GridBagConstraints gbc_tglbtnNewToggleButton_1 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton_1.gridx = 1;
		gbc_tglbtnNewToggleButton_1.gridy = 0;
		add(tglbtnNewToggleButton_1, gbc_tglbtnNewToggleButton_1);
		tglbtnNewToggleButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Start New Wave");
				mapPanel.getOtherItemsPanel().mapT.start();
			}
		});
		
		TowerPanel towerPanel = new TowerPanel();
		GridBagConstraints gbc_towerPanel = new GridBagConstraints();
		gbc_towerPanel.fill = GridBagConstraints.BOTH;
		gbc_towerPanel.gridx = 2;
		gbc_towerPanel.gridy = 0;
		add(towerPanel, gbc_towerPanel);

	}

}
