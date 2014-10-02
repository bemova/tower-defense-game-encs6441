
package UserInterfaceClasses;

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
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import GameElements.Grid;
import GameElements.Map;


public class TowerWindowOnMap extends JPanel{
	

	JButton levelUp = new JButton("levelUp");
	JButton levelDown = new JButton("levelDown");
	JTextArea towerInfo = new JTextArea();
	JFrame frame = new JFrame();
	

	
	UserInterface userInterface;
	
	//public TowerWindow(Grid grid)
	public TowerWindowOnMap(UserInterface ui)
	{
		userInterface = ui;
	//	this.canvas = canvas;
		setSize(200, 80);
		

		JLabel htmlTextArea = new JLabel();
		frame.setSize(150, 150);
		frame.add(towerInfo);
		frame.add(levelUp);
		frame.add(levelDown);
		frame.add(htmlTextArea);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		// you can open a new frame here as
		// i have assumed you have declared "frame" as instance variable
    	String initialText = "<html>\n" +
                "Tower characteristics:\n" +
                "<ul>\n" +
                "<li><font color=red>Distance: 2</font>\n" +
                "<li><font color=blue>Frequency: 3</font>\n" +
                "<li><font color=green>Power: 4</font>\n" +
                "<li><font color=green>Damage: 3</font>\n" +
                "</ul>\n";
        htmlTextArea.setText(initialText);

        frame.addWindowListener(new WindowAdapter() {
            public void windowDeactivated(WindowEvent e) {
                frame.setVisible(false);
            }
        });
        
        
        levelUp.addActionListener(new java.awt.event.ActionListener(){
        	
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {

  	
		    	
			    }
			    catch(java.lang.Exception ex)
			    {
			    	//JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
        	
        	
        });


	}

}
