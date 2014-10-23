package UI;

import java.awt.BorderLayout;
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

import core.applicationService.vikiMapServacs.MoneyManager;
import core.domain.maps.CompleteGrid;
import core.domain.maps.Grid;
import core.domain.maps.Map;
import core.domain.warriors.defenders.towers.vikiTowers.TowerDataCollection;

public class TowerWindow extends JPanel {

	TowerDataCollection towerParams = new TowerDataCollection();
	JFrame frame = new JFrame("new frame");
	final JLabel picLabel1;
	final JLabel picLabel2;
	final JLabel picLabel3;
	JPanel upper = new JPanel();
	JPanel lower = new JPanel();
	JButton levelUp = new JButton("buy");
	JButton levelDown = new JButton("sell");
	String initialTowerView = "";

	JFrame draggedTower = new JFrame();
	JLabel draggedLabel = null;
	Point dragStartPoint = null;
	// GameMap canvas;
	UserInterface userInterface;
	JLabel htmlTextArea = new JLabel();
	
	Point currentPosition = new Point();
	MoneyManager moneyManager = new MoneyManager();
	// public TowerWindow(Grid grid)
	public TowerWindow(UserInterface ui) {
		userInterface = ui;
		// this.canvas = canvas;
		setSize(200, 80);

		setUpDraggable();

		frame.setSize(150, 150);
		lower.add(levelUp);
		lower.add(levelDown);
		levelUp.setVisible(true);
		levelDown.setVisible(true);

		frame.add(upper, BorderLayout.NORTH);
		frame.add(lower, BorderLayout.SOUTH);

		
		initialTowerView = "<html>\n" + "Tower characteristics:\n" + "<ul>\n"
				+ "<li><font color=red>Distance: 2</font>\n"
				+ "<li><font color=blue>Frequency: 3</font>\n"
				+ "<li><font color=green>Power: 4</font>\n"
				+ "<li><font color=green>Damage: 3</font>\n" + "</ul>\n";
		htmlTextArea.setText(initialTowerView);
		upper.add(htmlTextArea);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		frame.setUndecorated(true);
		frame.setLocation(200, 200);

		frame.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		picLabel1 = createImageLabel("/tower1.png");
		constructTowerLabel(picLabel1, 10);

		picLabel2 = createImageLabel("/tower2.png");
		constructTowerLabel(picLabel2, 20);

		picLabel3 = createImageLabel("/tower3.png");
		constructTowerLabel(picLabel3, 30);

		levelUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					levelDown.setEnabled(true);
					userInterface.towerControler(currentPosition, "up");
					boolean anaibleDisable = userInterface.moneyControler(currentPosition, "buy");
				
					if(!anaibleDisable)  levelUp.setEnabled(false);

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		levelDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					levelUp.setEnabled(true);
					boolean anaibleDisable =  userInterface.moneyControler(currentPosition, "sell");
					
					userInterface.towerControler(currentPosition, "down");
					//if anaibleDisable is false it means the level of tower becomes below <0> and it is removed from the map
					//the status of its window has to be changed into false
					if(!anaibleDisable)  frame.setVisible(false);
					
					//userInterface.mapManager.

					// canva.repaint();
					// pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});
		
	frame.pack();

	}

	private void constructTowerLabel(final JLabel label, final int towerType) {

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TowerParameters towerParam = towerParams.returnTowerParameters(towerType);
				String view = "<html>\n" + "Tower characteristics:\n" + "<ul>\n"
						+ "<li><font color=red>Distance: " + towerParam.range
						+ "</font>\n" + "<li><font color=blue>Frequency:"
						+ towerParam.firingSpeed + "</font>\n"
						+ "<li><font color=green>Buy price: " +towerParam.buyPrice + "</font>\n"
						+ "<li><font color=green>Sale price: " + towerParam.salePrice + "</font>\n" + "</ul>\n";
				updateView(view);
				setFrameLocation(label);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				draggedLabel.setIcon(label.getIcon());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (draggedTower.isVisible()) {
					draggedTower.setVisible(false);
					userInterface.placeTowerOnMap(e.getLocationOnScreen(),
							towerType);
					//userInterface.moneyControler(e.getLocationOnScreen(), "buy");
					
				}
			}
		});

		label.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				draggedTower.setVisible(true);
				draggedTower.setLocation(e.getLocationOnScreen().x - 15,
						e.getLocationOnScreen().y - 15);
			}
		});

		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setFrameLocation(label);
			}
		});
		add(label);
	}

	private void setUpDraggable() {
		try {
			draggedLabel = new JLabel(new ImageIcon(ImageIO.read(this
					.getClass().getResource("/tower-drag.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		draggedTower.add(draggedLabel);
		draggedTower.setSize(30, 30);
		draggedTower.setUndecorated(true);
		draggedTower.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draggedTower.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				draggedTower.setVisible(false);
			}
		});
	}

	public void setFrameLocation(JLabel label) {
		Point point = label.getLocationOnScreen();
		point.y += label.getHeight();
		frame.setLocation(point);
		frame.setVisible(true);
	}

	private JLabel createImageLabel(String path) {
		JLabel picLabel = null;

		try {
			BufferedImage image = ImageIO.read(this.getClass()
					.getResource(path));
			picLabel = new JLabel(new ImageIcon(image));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return picLabel;
	}

	public void updateView(String newTowerView) {
		// initialTowerView = newTowerView;

		htmlTextArea.setText(newTowerView);
		// frame.add(levelUp);
		// frame.add(levelDown);
		// frame.repaint();
		frame.pack();
	}
	
	public void updateCurrentPosition(Point newPosition ){
		
		currentPosition = newPosition;
		
		
	}
	
	
	
}