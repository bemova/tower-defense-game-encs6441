package GameDesign;
import ThreadPul.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Frame extends JFrame {
	int width = 0;
	int height = 0;
	
	JTextField xField = new JTextField("", 4);
	JTextField yField = new JTextField("", 4);
	JLabel entryPoint = new JLabel("Tag Entery Point");
	JLabel exitPoint = new JLabel("Tag Exit Point");
	JButton startButton = new JButton("Ok");
	JButton startMapButton = new JButton("Draw");
	JButton sinary = new JButton("sinary");
	JButton path = new JButton("path");
	JButton ep = new JButton("entryP");
	JButton exp = new JButton("exitP");
	JButton save = new JButton("Save Map");
	JButton load = new JButton("Load Map");
	Color colorToDrawGreed = Color.green;
	int colorInInteger = 1; // 1 = gray , 2 = green, 3 = red, 4 = blue
	
	
	//GeneralAlgorithms algorithm = new GeneralAlgorithms();
	GameMap canva = new GameMap(); 
	FindingPathAlgorithm pathAlgorithm = new FindingPathAlgorithm();

	JPanel lower = new JPanel();
	JPanel entryP = new JPanel();
	CreatEnemy enimyCreaterThread = null;
	DrawThread drawEnemyThread = null;
	
	
	Frame(){
		
		JPanel upper = new JPanel();
	
		entryP.add(entryPoint);
		entryP.add(exitPoint);
		entryP.add(startMapButton);
		upper.add(new JLabel("Set map size"));
		upper.add(xField);
		upper.add(yField);
		upper.add(startButton);
		sinary.setBackground(Color.green);
		path.setBackground(Color.gray);
		ep.setBackground(Color.red);
		exp.setBackground(Color.blue);
		upper.add(sinary);
		upper.add(path);
		upper.add(exp);
		upper.add(ep);
		upper.add(save);
		upper.add(load);
		
		
		
	
		load.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    				
		               JFileChooser openFile = new JFileChooser();
		               if(JFileChooser.APPROVE_OPTION  == openFile.showOpenDialog(null)){
		                
		                canva.loadMapFromFile(openFile.getSelectedFile().getAbsolutePath());
		                width = canva.wdth_;
		                height = canva.height_;
				    	lower.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
				    	canva.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
				    	
				    	add(entryP, BorderLayout.CENTER);
				    	pack();
				    	
				        setLocationRelativeTo(null);
		               }
		               
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			
		});
		
		
		save.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	
	                JFileChooser saveFile = new JFileChooser();
	                
	               
	                if( saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	                canva.SaveMapIntoFle(saveFile.getSelectedFile().getAbsolutePath());
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			
		});
		
		sinary.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			
			    	colorToDrawGreed = sinary.getBackground();
			    	colorInInteger = 2; // green
			    	
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			
		});
			
		path.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			
			    	colorToDrawGreed = path.getBackground();
			    	colorInInteger = 1; // black for path 
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			
		});	
		
		
		ep.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			
			    	colorToDrawGreed = ep.getBackground();
			    	colorInInteger = 3; // red for entry point
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			
		});
		
		
		exp.addActionListener( new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	colorInInteger = 4; // blue for exit point
			    	colorToDrawGreed = exp.getBackground();
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    	
			    }
			  }
			
		});
			
			
			
		
		
		startMapButton.addActionListener(new java.awt.event.ActionListener() 
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	if(canva.entryPoint.isEmpty() || canva.exitPoint.isEmpty())
			    		throw new Exception("Entry and exit points are not defined");
			    
			   // 	pathAlgorithm.Start(canva.entryPoint, canva.exitPoint, canva.pathInMatrix);
			    //	canva.updatePath();
			    	canva.repaint();
			    	
			    //	enimyCreaterThread = new CreatEnemy(canva.listOfEnemies, canva.processingPatCordinate.get(0),0.5);
			    //	 drawEnemyThread = new DrawThread(canva.listOfEnemies, canva.getGraphics(), canva.processingPatCordinate, canva); // ArrayList<Enemy> listOfMapObjects, Graphics graphics, ArrayList<Position> processingPatCordinate
			    	 
			    	// enimyCreaterThread.start();
			    	// drawEnemyThread.start();
			    	
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			});
		
		
		startButton.addActionListener(new java.awt.event.ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	width = Integer.parseInt(xField.getText());
			    	height = Integer.parseInt(yField.getText());
			    	if(width > 60 || width < 5 || height > 60 || height < 5)
			    		throw new java.lang.Exception("Error size max size: ....., min size: ....");
					
			    	//canva = new GameMap(height,width );
				//	pathAlgorithm = new FindingPathAlgorithm(height,width);

			    	canva.wdth_ = width;
			    	canva.height_ = height;
			    	canva.pathInMatrix = new int[height][width];
			    	
			    	
			    	pathAlgorithm.testingX = height;
			    	pathAlgorithm.testingY  = width;
			    	
					
			    	// canva.pathInMatrix  = new int[height][width];
			    	lower.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
			    	canva.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
			    	
			    	add(entryP, BorderLayout.CENTER);
			    	pack();
			    	
			        setLocationRelativeTo(null);
			        startButton.setEnabled(false);
			        
			    }
			    catch(java.lang.Exception ex)
			    {
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			    }
			  }
			});
		
		  canva.addMouseListener(new MouseAdapter() {
			     @Override
			     public void mousePressed(MouseEvent e) {
				      int i =   e.getY()/canva.sizeOfUnit;
				      int j =  e.getX()/canva.sizeOfUnit;
				      if((i < canva.height_) && (j < canva.wdth_) && (canva.pathInMatrix[i][j] != colorInInteger)){
					      canva.pathInMatrix[i][j] = colorInInteger;
		    		      canva.repaint();
		    		      
					      }
    		      
			     }
			     
			     
			     @Override
			     public void mouseMoved( MouseEvent e ){
			    	 
				      int i =   e.getY()/canva.sizeOfUnit;
				      int j =  e.getX()/canva.sizeOfUnit;
				      if((i < canva.height_) && (j < canva.wdth_) && (canva.pathInMatrix[i][j] != colorInInteger)){
					      canva.pathInMatrix[i][j] = colorInInteger;
		    		      canva.repaint();
		    		      
					      }
				     
			     } 
			     

			  });
		  
		  canva.addMouseMotionListener(new MouseMotionAdapter() {
			  
			     @Override
			     public void mouseDragged( MouseEvent e ){
				      int i =   e.getY()/canva.sizeOfUnit;
				      int j =  e.getX()/canva.sizeOfUnit;
				      if((i < canva.height_) && (j < canva.wdth_) && (canva.pathInMatrix[i][j] != colorInInteger)){
					      canva.pathInMatrix[i][j] = colorInInteger;
		    		      canva.repaint();
		    		      
					      }
			     } 
		  });
		
		
		canva.setSize(210, 210);
		lower.add(canva);
		
		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);
		
		//setLayout(new FlowLayout());
	
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
		

	}

	
	void setMapSize(int width, int height)
	{
		lower.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
    	canva.setSize(width *canva.sizeOfUnit, height * canva.sizeOfUnit);
    	pack();
        setLocationRelativeTo(null);
	}
}

