	package UI;

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

	import javax.imageio.ImageIO;

	import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DesignToweerDialog extends JFrame{
	
		JPanel upper = new JPanel();
		JPanel center = new JPanel(new BorderLayout());
		JPanel lower = new JPanel();
		
		JPanel rightPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		
		JLabel towerTypeLable = new JLabel("Tower Type");
		JLabel availableFeatursLable = new JLabel("Available Features");
		JLabel selectedFeaturesLable = new JLabel("Selected Features");
		
		JButton insertFeatureButton = new JButton(">");
		JButton createTowerButten = new JButton("OK");
		JButton removeFeatureButton = new JButton("<");
		DefaultListModel modelFrom = new DefaultListModel(); 
		DefaultListModel modelInto = new DefaultListModel(); 
		DefaultListModel modelTowerType = new DefaultListModel();
		JList listFRom = new JList(modelFrom);
		JList listInto = new JList(modelInto);
		JList listTowerType = new JList(modelTowerType);
					 
		UserInterface userInterface;		


		public DesignToweerDialog (UserInterface ui) {
			
			userInterface = ui;
			// this.canvas = canvas;
			setSize(400, 400);
			listFRom.setSize(50, 50);
			listInto.setSize(50, 50);
			listTowerType.setSize(50, 50);
			
			
			
			add(upper, BorderLayout.NORTH);
			add(center, BorderLayout.CENTER);
			add(lower, BorderLayout.SOUTH);
			
			//setSize(150, 150);
			center.add(rightPanel, BorderLayout.EAST);
			center.add(middlePanel, BorderLayout.CENTER);
			center.add(leftPanel, BorderLayout.WEST);
			
			
			
			upper.add(towerTypeLable, BorderLayout.PAGE_START);
			upper.add(availableFeatursLable, BorderLayout.PAGE_START);
			
			lower.add(createTowerButten);
			lower.add(removeFeatureButton,BorderLayout.WEST);
			lower.add(insertFeatureButton,BorderLayout.EAST);
			
			leftPanel.add(listTowerType, BorderLayout.CENTER);
			middlePanel.add(listFRom, BorderLayout.CENTER);
			rightPanel.add(listInto, BorderLayout.CENTER);
			
			rightPanel.setBackground(Color.WHITE);
			middlePanel.setBackground(Color.YELLOW);
			leftPanel.setBackground(Color.RED);
			
			
			modelFrom.addElement("feature1 ");
			modelFrom.addElement("feature2 ");
			modelFrom.addElement("feature3 ");
			modelTowerType.addElement("Type1");
			modelTowerType.addElement("Type2");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
			setUndecorated(true);
			setLocation(200, 200);
			
			
			insertFeatureButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						modelInto.addElement(listFRom.getSelectedValue().toString());
						
					} catch (java.lang.Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}

				}

			});

			createTowerButten.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						
						// TOWER SHOULD BE CREATED HERE
						
						setVisible(false);
					} catch (java.lang.Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}

				}

			});
			
			//setVisible(true);
			pack();
	}
	

}
