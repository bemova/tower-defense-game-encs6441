package UI.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import UI.MapEditorUserInterface;

public class MainFrame extends JFrame {

	//private TextPanel textPanel;
	private MapEditorUserInterface editorUserInterface;
	private Toolbar toolbar;
	private MapFormPanel mapFormPanel;
	private JFileChooser fileChooser;

	public MainFrame() {
		super("Tower Defence Group 5");

		setLayout(new BorderLayout());
		setJMenuBar(createMenu());

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MapFileFilter());

		//textPanel = new TextPanel();
		editorUserInterface = new MapEditorUserInterface();
		
		toolbar = new Toolbar();
		mapFormPanel = new MapFormPanel();
		mapFormPanel.setListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				int height = e.getHeight();
				int width = e.getWidth();
				//textPanel.append(height + " : " + width);
				editorUserInterface.design(width, height);
			}
		});
		mapFormPanel.setPathListener(new PathListener() {
			
			@Override
			public void pathEventOccured(PathEvent e) {
				Color backgroundColor = e.getBackgroundColor();
				editorUserInterface.drawPath(backgroundColor);
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(editorUserInterface, BorderLayout.CENTER);
		add(mapFormPanel, BorderLayout.WEST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 400));
		setSize(800, 600);
		setVisible(true);

	}

	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu window = new JMenu("Window");
		

		JMenu mapMenu = new JMenu("Map");
		JMenuItem saveMapItem = new JMenuItem("Save");
		JMenuItem loadMapItem = new JMenuItem("Load");
		JMenuItem designMapItem = new JMenuItem("Design");
		JMenuItem exitItem = new JMenuItem("Exit");
		mapMenu.add(saveMapItem);
		mapMenu.add(loadMapItem);
		mapMenu.add(designMapItem);
		mapMenu.addSeparator();
		mapMenu.add(exitItem);

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showGridItem = new JCheckBoxMenuItem("Grid Form");
		showGridItem.setSelected(true);
		showGridItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem gridView = (JCheckBoxMenuItem) e
						.getSource();
				mapFormPanel.setVisible(gridView.isSelected());
				//mapFormPanel.a.setVisible(!gridView.isSelected());
			}
		});
		
		showMenu.add(showGridItem);
		
		window.add(showMenu);
		menuBar.add(mapMenu);
		menuBar.add(window);
		
		///////////// Mnemonic setting
		mapMenu.setMnemonic(KeyEvent.VK_M);// alt+F
		saveMapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));// ctrl+S
		loadMapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.CTRL_MASK));// ctrl+L
		designMapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));// ctrl+D
		
		exitItem.setMnemonic(KeyEvent.VK_X);// alt+F+X
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));// ctrl+X
		/////////////end of Mnemonic setting
		
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = JOptionPane.showInputDialog(MainFrame.this,
						"Enter your Username", "Username Request",
						JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Does "+username+" want to Exit? ", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		
		saveMapItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
				}
				
			}
		});
		
		loadMapItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
				}
				
			}
		});

		return menuBar;
	}

}
