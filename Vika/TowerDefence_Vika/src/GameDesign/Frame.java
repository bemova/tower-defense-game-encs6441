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
		
	//	canva = new GameMap();
		
		
		startMapButton.addActionListener(new java.awt.event.ActionListener() 
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	if(canva.entryPoint.isEmpty() || canva.exitPoint.isEmpty())
			    		throw new Exception("Entry and exit points are not defined");
			    	
			    	pathAlgorithm.Start(canva.entryPoint, canva.exitPoint, canva.pathInMatrix);
			    	canva.updatePath();
			    	canva.repaint();
			    	
			    	enimyCreaterThread = new CreatEnemy(canva.listOfEnemies, canva.processingPatCordinate.get(0),0.5);
			    	 drawEnemyThread = new DrawThread(canva.listOfEnemies, canva.getGraphics(), canva.processingPatCordinate, canva); // ArrayList<Enemy> listOfMapObjects, Graphics graphics, ArrayList<Position> processingPatCordinate
			    	 
			    	 enimyCreaterThread.start();
			    	 drawEnemyThread.start();
			    	
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
			      int x =   e.getPoint().x/canva.sizeOfUnit;
			      int y =  e.getPoint().y/canva.sizeOfUnit;
			     if( canva.pathInMatrix[y][x] == 1){
			    	 canva.pathInMatrix[y][x] = 0;
			    	 canva.updatePath(); // update the path coordinates
			    	 
			     }
			     else{
			    	 canva.pathInMatrix[y][x] = 1;
			    	 if(x == 0) 
			    		 canva.entryPoint = Integer.toString(y) + " " + Integer.toString(x);
			    	 else if(x == width -1) 
			    		 canva.exitPoint = Integer.toString(y) + " " + Integer.toString(x);
			    	 canva.updatePath(); // update the path coordinates
			     }
			      canva.repaint();
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

