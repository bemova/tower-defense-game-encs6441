	package UI;

	import java.awt.BorderLayout;
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
		JPanel lower = new JPanel();
		
		JButton insertFeatureButton = new JButton(">");
		JButton createTowerButten = new JButton("OK");
		JButton removeFeatureButton = new JButton("<");
		DefaultListModel modelFrom = new DefaultListModel(); 
		DefaultListModel modelInto = new DefaultListModel(); 
		 JList listFRom = new JList(modelFrom);
		 JList listInto = new JList(modelInto);
					 
		UserInterface userInterface;		


		public DesignToweerDialog (UserInterface ui) {
			
			userInterface = ui;
			// this.canvas = canvas;
			setSize(300, 200);
			listFRom.setSize(50, 50);
			listInto.setSize(50, 50);
			//setSize(150, 150);
			add(upper, BorderLayout.NORTH);
			add(lower, BorderLayout.SOUTH);
			lower.add(createTowerButten);
			
			lower.add(removeFeatureButton,BorderLayout.WEST);
			lower.add(insertFeatureButton,BorderLayout.EAST);
			upper.add(listFRom, BorderLayout.WEST);
			upper.add(listInto, BorderLayout.EAST);
			
			modelFrom.addElement("feature1 ");
			modelFrom.addElement("feature2 ");
			modelFrom.addElement("feature3 ");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
			//setUndecorated(true);
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
						
						
					} catch (java.lang.Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}

				}

			});
			
			//setVisible(true);
			pack();
	}
	

}
