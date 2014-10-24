package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class MapEditor extends JFrame implements ActionListener {

	private JFrame mainFrame;
	  private JMenuBar menuBar;
	  private JMenu mapMenu;
	  private JMenuItem newMenuItem;
	  private JMenuItem openMenuItem;
	  private JMenuItem saveMenuItem;

	  private final String NEW = "New";
	  private final String OPEN = "Open";
	  private final String SAVE = "Save";
	  
	  public MapEditor(){
		  setUpMainFrame();
		  setUpMenuBar();
		  mainFrame.setVisible(true);
	  }
	  
	  private void setUpMainFrame(){
			mainFrame = new JFrame();
			mainFrame.setTitle("Tower Defence - Map Editor");
			mainFrame.setSize(700, 500);
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLocationRelativeTo(null);
	  }
	  
	  private void setUpMenuBar(){
		  menuBar = new JMenuBar();
		  
		// build the File menu
		    mapMenu = new JMenu("Map");
		    
		    newMenuItem = new JMenuItem(NEW);
		    openMenuItem = new JMenuItem(OPEN);
		    saveMenuItem = new JMenuItem(SAVE);
		    
		    newMenuItem.addActionListener(this);
		    openMenuItem.addActionListener(this);
		    saveMenuItem.addActionListener(this);
		    
		    mapMenu.add(newMenuItem);
		    mapMenu.add(openMenuItem);
		    mapMenu.add(saveMenuItem);
		    
		 // add menus to menubar
		    menuBar.add(mapMenu);
		    
		    mainFrame.setJMenuBar(menuBar);
	  }
	  
	public static void main (String[] args){
		
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream("tower3.png");
		
		MapEditor mapEditor = new MapEditor();
		
		//create main window

		
		//create main menu
		
		
		//if new, ask for grid size

		//save
		
		
		//load
		
		
		//validate
		
		
		
		
	}

	private void getNewMapSize(){
		JDialog mapSizeDialog = new JDialog(mainFrame, true); // parent, isModal
		mapSizeDialog.setTitle("Map Size");
		mapSizeDialog.setSize(400, 200);
		mapSizeDialog.setVisible(true);
		
		JTextField width = new JTextField(Constants.EMPTY_STRING, 4);
		JTextField height = new JTextField(Constants.EMPTY_STRING, 4);
		
		JLabel widthLable = new JLabel("Width: ");
		JLabel heightLable = new JLabel("Height: ");
		
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");

	}
	
	public void actionPerformed(ActionEvent event)
	  {
//	    SampleDialog dialog = new SampleDialog();
//	    dialog.setModal(true);
//	    dialog.setVisible(true);
		
		String menuItem = event.getActionCommand();
		
		switch (menuItem) {
		case NEW:
			getNewMapSize();
			break;
		case OPEN:
			break;
		case SAVE:
			break;
		}
		
		
	  }
}
