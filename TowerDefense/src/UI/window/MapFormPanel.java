package UI.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import UI.Constants;

public class MapFormPanel extends JPanel {
	private JLabel heightLable;
	private JLabel widthLable;
	private JTextField heightField;
	private JTextField widthField;
	private JButton submitBtn;

	//t 

	///////
	private JButton path;
	private JLabel entryPoint;
	private JLabel exitPoint;
	private JButton drowMapButton;
	private JButton scenery;
	private JButton entrance;
	private JButton exit;

	private FormListener listener;
	private PathListener pathListener;

	public void setListener(FormListener listener) {
		this.listener = listener;
	}

	public void setPathListener(PathListener pathListener) {
		this.pathListener = pathListener;
	}

	public MapFormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);


		heightLable = new JLabel("Height: ");
		heightField = new JTextField(10);
		heightLable.setDisplayedMnemonic(KeyEvent.VK_H);
		heightLable.setLabelFor(heightField);// if you press alt_N focus will be on name field

		widthLable = new JLabel("Width: ");

		widthField = new JTextField(10);




		submitBtn = new JButton("submit");
		submitBtn.setMnemonic(KeyEvent.VK_S);//alt+s
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitOnClock(e);
			}
		});

		path = new JButton(Constants.PATH);
		path.setBackground(Color.gray);
		path.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pathOnClick(e);
			}
		});
		entryPoint = new JLabel(Constants.TAG_ENTRY_POINT);
		exitPoint = new JLabel(Constants.TAG_EXIT_POINTS);
		drowMapButton = new JButton(Constants.DRAW);
		scenery = new JButton(Constants.SCENERY);
		entrance = new JButton(Constants.Entrance);
		exit = new JButton(Constants.Exit);

		scenery.setBackground(Color.green);
		entrance.setBackground(Color.red);
		exit.setBackground(Color.blue);

		Border innerBorder = BorderFactory.createTitledBorder("Map Design");
		Border outerBorder = BorderFactory.createEmptyBorder(4, 4, 4, 4);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		firstLayaoutComponents();

	}

	public void firstLayaoutComponents(){
		setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		///// first Line////////////////////////
		gb.fill = GridBagConstraints.NONE;
		gb.weightx = 1;
		gb.weighty = 0.1;

		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_END;
		add(heightLable, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.LINE_START;
		add(heightField, gb);

		///// Next Line////////////////////////
		gb.gridy ++;
		gb.weightx = 1;
		gb.weighty = 0.1;

		gb.gridx = 0;

		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_END;
		add(widthLable, gb);

		gb.gridx = 1;
		gb.gridy = 1;
		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.LINE_START;
		add(widthField, gb);



		///// Next Line////////////////////////
		gb.gridy ++;
		gb.weightx = 1;
		gb.weighty = 2;

		gb.gridx = 1;

		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submitBtn, gb);
	}

	public void secondLayaoutComponents(){
		setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		///// first Line////////////////////////
		gb.fill = GridBagConstraints.NONE;
		gb.weightx = 1;
		gb.weighty = 0.0009;

		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_START;
		add(drowMapButton, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.LINE_START;
		add(path, gb);

		///// Next Line////////////////////////
		gb.gridy ++;
		gb.weightx = 1;
		gb.weighty = 0.0009;

		gb.gridx = 0;

		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_START;
		add(entryPoint, gb);

		gb.gridx = 1;
		gb.gridy = 1;
		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.LINE_START;
		add(exitPoint, gb);

		///// Next Line////////////////////////
		gb.gridy ++;
		gb.weightx = 1;
		gb.weighty = 0.0009;

		gb.gridx = 0;

		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_START;
		add(entrance, gb);

		gb.gridx = 1;
		gb.gridy = 2;
		gb.insets = new Insets(0, 0, 0, 0);
		gb.anchor = GridBagConstraints.LINE_START;
		add(exit, gb);

		///// Next Line////////////////////////
		gb.gridy ++;
		gb.weightx = 1;
		gb.weighty = 0.0009;

		gb.gridx = 0;

		gb.insets = new Insets(0, 0, 0, 5);
		gb.anchor = GridBagConstraints.LINE_START;
		add(scenery, gb);

	}

	public void submitOnClock(ActionEvent e){
		int height = Integer.parseInt(heightField.getText());
		int width = Integer.parseInt(widthField.getText());

		FormEvent formEvent = new FormEvent(e, width, height);
		if( listener != null){
			listener.formEventOccured(formEvent);
			heightField.setVisible(false);
			heightLable.setEnabled(false);
			widthField.setVisible(false);
			heightLable.setVisible(false);
			widthLable.setVisible(false);
			submitBtn.setVisible(false);

			///Visible true
			path.setVisible(true);
			entryPoint.setVisible(true);
			exitPoint.setVisible(true);
			drowMapButton.setVisible(true);
			scenery.setVisible(true);
			entrance.setVisible(true);
			exit.setVisible(true);

			Border innerBorder = BorderFactory.createTitledBorder("ToolBox");
			Border outerBorder = BorderFactory.createEmptyBorder(4, 4, 4, 4);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			secondLayaoutComponents();
		}

	}

	public void pathOnClick(ActionEvent e){

		Color color = path.getBackground();

		PathEvent pathEvent = new PathEvent(e, color);
		if( pathListener != null){
			pathListener.pathEventOccured(pathEvent);
		}
	}
}