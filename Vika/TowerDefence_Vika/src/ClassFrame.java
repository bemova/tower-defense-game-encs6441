import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassFrame extends JFrame {
	int width = 0;
	int height = 0;
	
	JTextField xField = new JTextField("", 4);
	JTextField yField = new JTextField("", 4);
	JLabel entryPoint = new JLabel("Tag Entery Point");
	JLabel exitPoint = new JLabel("Tag Exit Point");
	JButton startButton = new JButton("Ok");
	JButton startMapButton = new JButton("Draw");
	
	GeneralAlgorithms algorithm = new GeneralAlgorithms();
	MainCanva canva = new MainCanva();
	

	JPanel lower = new JPanel();
	JPanel entryP = new JPanel();
	
	
	ClassFrame(){
		
		JPanel upper = new JPanel();
		
		entryP.add(entryPoint);
		entryP.add(exitPoint);
		entryP.add(startMapButton);
		upper.add(new JLabel("Set map size"));
		upper.add(xField);
		upper.add(yField);
		upper.add(startButton);
		
		startMapButton.addActionListener(new java.awt.event.ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    try
			    {
			    	if(canva.entryPoint.isEmpty() || canva.exitPoint.isEmpty())
			    		throw new Exception("Entry and exit points are not defined");
			    	
			    	algorithm.Start(width, height, canva);
			    	canva.repaint();
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
			    	//algorithm.Start(width, height, canva);
			    	//canva.repaint();
			    	//canva.path = algorithm.mapInMatric;
			    	
			    	canva.path = new int[height][width];
			    	lower.setSize(width *20, height * 20);
			    	canva.setSize(width *20, height * 20);
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
			      int x =   e.getPoint().x/20;
			      int y =  e.getPoint().y/20;
			     if( canva.path[y][x] == 1)
			    	 canva.path[y][x] = 0;
			     else{
			    	 canva.path[y][x] = 1;
			    	 if(x == 0) 
			    		 canva.entryPoint = Integer.toString(y) + " " + Integer.toString(x);
			    	 else if(x == width -1) 
			    		 canva.exitPoint = Integer.toString(y) + " " + Integer.toString(x);
			     }
			      canva.repaint();
			     }
			  });
		
		
		canva.setSize(200, 200);
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
		lower.setSize(width *20, height * 20);
    	canva.setSize(width *20, height * 20);
    	pack();
        setLocationRelativeTo(null);
	}
}
