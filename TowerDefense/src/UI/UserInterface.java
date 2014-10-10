package UI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import UI.FactoryTower;
import core.applicationService.vikiMapServacs.StandardAlgorithms;
import core.applicationService.vikiMapServacs.MapManager;
import UI.TowerParameters;
import core.domain.maps.CompleteGrid;
import core.domain.maps.EmptyGrid;
import core.domain.maps.Grid;
import core.domain.maps.Map;
import core.domain.warriors.defenders.towers.*;
import core.domain.warriors.defenders.towers.vikiTowers.TowerDataCollection;

public class UserInterface extends JFrame {

	int width = 15;
	int height = 15;
	TowerWindow towerWindow = new TowerWindow(this);
	// TowerWindowOnMap towerPropertyPanel1 = new TowerWindowOnMap(this);
	JTextField xField = new JTextField("", 4);
	JTextField yField = new JTextField("", 4);
	JLabel entryPoint = new JLabel("Tag Entery Point");
	JLabel exitPoint = new JLabel("Tag Exit Point");
	JButton setMapSizeButten = new JButton("Set Size");
	JButton drowMapButton = new JButton("Draw");
	JButton sinary = new JButton("sinary");
	JButton path = new JButton("path");
	JButton ep = new JButton("entryP");
	JButton exp = new JButton("exitP");
	JButton save = new JButton("Save Map");
	JButton load = new JButton("Load Map");
	JButton designMap = new JButton("Design Map");
	JButton playGame = new JButton("Start Playing");

	JButton designTowers = new JButton("Design Towers");
	Color colorToDrawGreed = Color.green;
	int colorInInteger = 1; // 1 = gray , 2 = green, 3 = red, 4 = blue

	Grid grid = new EmptyGrid(height, width);
	MapManager mapManager;
	FactoryTower towerFactory = new FactoryTower(); // //////////????????????
	CanvaObject canva = new CanvaObject(grid);
	// StandardAlgorithms pathAlgorithm = new StandardAlgorithms();
	JPanel lower = new JPanel();
	JPanel entryP = new JPanel();

	TowerParameters towerParam = new TowerParameters();
	DesignToweerDialog towerDialogWindow = new DesignToweerDialog(this);

	// CreatEnemy enimyCreaterThread = null;
	// DrawThread drawEnemyThread = null;
	JPanel upper = new JPanel();

	UserInterface() {

		entryP.add(entryPoint);
		entryP.add(exitPoint);
		entryP.add(drowMapButton);
		upper.add(new JLabel("Set map size"));
		upper.add(xField);
		upper.add(yField);
		upper.add(setMapSizeButten);
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
		upper.add(designMap);
		upper.add(playGame);

		upper.add(towerWindow);
		towerWindow.setVisible(false);

		// make part of interface invisible to a user
		drowMapButton.setVisible(false);

		sinary.setEnabled(false);
		setMapSizeButten.setVisible(false);
		path.setEnabled(false);
		exp.setEnabled(false);
		ep.setEnabled(false);
		save.setEnabled(false);
		xField.setEnabled(false);
		yField.setEnabled(false);

		sinary.setVisible(false);
		path.setVisible(false);
		exp.setVisible(false);
		ep.setVisible(false);
		save.setVisible(false);
		xField.setVisible(false);
		yField.setVisible(false);
		playGame.setVisible(false);

		mapManager = new MapManager();

		xField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();
			}
		});

		xField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		yField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		yField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		playGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					towerWindow.setVisible(true);

					if (grid != null)
						grid = new Map(grid);
					canva.updateGrid(grid);
					upper.add(designTowers);
					designTowers.setVisible(true);

					// canva.repaint();
					pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		designTowers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					towerDialogWindow.setVisible(true);

					// pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		designMap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xField.setVisible(true);
					yField.setVisible(true);
					xField.setEnabled(true);
					yField.setEnabled(true);
					// load.setEnabled(false);
					// designMap.setVisible(false);
					grid = new CompleteGrid(grid);
					canva.updateGrid(grid);
					pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		load.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser openFile = new JFileChooser();
					if (JFileChooser.APPROVE_OPTION == openFile
							.showOpenDialog(null)) {
						grid = new CompleteGrid(grid);
						grid.gridAssignmentOperator(mapManager
								.LoadMapFromFile(openFile.getSelectedFile()
										.getAbsolutePath()));
						canva.updateGrid(grid);
						width = grid.getWidth();
						height = grid.getHeight();
						lower.setSize(width * grid.getUnitSize(),
								height * grid.getUnitSize());
						canva.setSize(width * grid.getUnitSize(),
								height * grid.getUnitSize());
						// designMap.setEnabled(false);

						add(entryP, BorderLayout.CENTER);

						String message = mapManager.validateMapContent(grid);
						if (!message.equals(""))
							JOptionPane.showMessageDialog(null, message);
						else
							playGame.setVisible(true);
						pack();

						setLocationRelativeTo(null);
					}

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		save.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser saveFile = new JFileChooser();

					if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String fileName = saveFile.getSelectedFile()
								.getAbsolutePath();

						String errorMessage = mapManager.SaveMapIntoFle(grid,
								fileName);

						load.setEnabled(true);

						designMap.setEnabled(true);

						if (!errorMessage.equals(""))
							JOptionPane.showMessageDialog(null, errorMessage);

						pack();

					}
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		sinary.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = sinary.getBackground();
					colorInInteger = 2; // green

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		path.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = path.getBackground();
					colorInInteger = 1; // black for path
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		ep.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = ep.getBackground();
					colorInInteger = 3; // red for entry point
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		exp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					colorInInteger = 4; // blue for exit point
					colorToDrawGreed = exp.getBackground();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());

				}
			}

		});

		drowMapButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (((Map) grid).getEntryPoint().isEmpty()
							|| ((Map) grid).getExitPOint().isEmpty())
						throw new Exception(
								"Entry and exit points are not defined");
					canva.updateGrid(grid);
					canva.repaint();

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		setMapSizeButten.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					path.setVisible(true);
					sinary.setVisible(true);
					exp.setVisible(true);
					ep.setVisible(true);
					save.setVisible(true);

					path.setEnabled(true);
					sinary.setEnabled(true);
					exp.setEnabled(true);
					ep.setEnabled(true);
					save.setEnabled(true);

					width = Integer.parseInt(xField.getText());
					height = Integer.parseInt(yField.getText());
					if (width > 60 || width < 5 || height > 60 || height < 5)
						throw new java.lang.Exception(
								"Error size max size: ....., min size: ....");

					// canvas = new GameMap(height,width );
					// pathAlgorithm = new FindingPathAlgorithm(height,width);

					grid.setSize(width, height);
					canva.updateGrid(grid);

					lower.setSize(width * grid.getUnitSize(),
							height * grid.getUnitSize());
					canva.setSize(width * grid.getUnitSize(),
							height * grid.getUnitSize());

					add(entryP, BorderLayout.CENTER);
					pack();

					setLocationRelativeTo(null);
					setMapSizeButten.setEnabled(false);

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		canva.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (designMap.isEnabled()) {
					int i = e.getY() / grid.getUnitSize();
					int j = e.getX() / grid.getUnitSize();
					if ((i < grid.getHeight()) && (j < grid.getWidth())
							&& (grid.content[i][j] != colorInInteger)) {
						if (grid.content[i][j] == 5) {
							Point point = canva.getLocation();
							int x = point.x + j * grid.getUnitSize();
							int y = point.y + i * grid.getUnitSize();
							Point pointOnScreen = e.getLocationOnScreen();
							// add(towerPropertyPanel);

							towerWindow.frame.setLocation(pointOnScreen);
							// towerWindow.frame.setLocation(100, 100);
							towerWindow.setVisible(true);
							towerWindow.frame.setVisible(true);
							String position = Integer.toString(i) + " "
									+ Integer.toString(j);
							/*
							 * towerParam =
							 * ((Map)grid).towers.get(position).parameters;
							 * String view = "<html>\n" +
							 * "Tower characteristics:\n" + "<ul>\n" +
							 * "<li><font color=red>Distance: " +
							 * towerParam.range + "</font>\n" +
							 * "<li><font color=blue>Frequency:" +
							 * towerParam.firingSpeed +"</font>\n" +
							 * "<li><font color=green>Power: x</font>\n" +
							 * "<li><font color=green>Damage: 3</font>\n" +
							 * "</ul>\n";
							 */

							double price = ((Map) grid).towers.get(position)
									.cost();
							// String type =
							// ((Map)grid).towers.get(position).display();
							String view = "<html>\n"
									+ "Tower characteristics:\n" + "<ul>\n"
									+ "<li><font color=red> price : " + price
									+ "</font>\n" +

									"</ul>\n";

							Point currentPosition = new Point(j, i);
							towerWindow.updateCurrentPosition(currentPosition);
							towerWindow.updateView(view);
							canva.repaint();
							// towerPropertyPanel.setLocation(x, y);
							// pack();
						} else {
							grid.content[i][j] = colorInInteger;
						}

						canva.repaint();

					}
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (designMap.isEnabled()) {

					int i = e.getY() / grid.getUnitSize();
					int j = e.getX() / grid.getUnitSize();
					if ((i < grid.getHeight()) && (j < grid.getWidth())
							&& (grid.content[i][j] != colorInInteger)) {
						grid.content[i][j] = colorInInteger;
						canva.repaint();

					}
				}

			}

		});

		canva.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (designMap.isEnabled()) {
					int i = e.getY() / grid.getUnitSize();
					int j = e.getX() / grid.getUnitSize();
					if ((i < grid.getHeight()) && (j < grid.getWidth())
							&& (grid.content[i][j] != colorInInteger)) {
						grid.content[i][j] = colorInInteger;
						canva.repaint();

					}
				}
			}
		});

		canva.setSize(grid.getHeight() * grid.getUnitSize(), grid.getWidth()
				* grid.getUnitSize());
		lower.setSize(grid.getHeight() * grid.getUnitSize(), grid.getWidth()
				* grid.getUnitSize());
		lower.add(canva);

		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);

		// setLayout(new FlowLayout());
		
		


		addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    if(towerDialogWindow.isVisible())
                    {
                    	towerDialogWindow.setVisible(false);
                    	towerDialogWindow.dispose();
                    }
                }
            });



		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	void setMapSize(int width, int height) {
		lower.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
		canva.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
		pack();
		setLocationRelativeTo(null);
	}

	public boolean canPlaceTower(Point point) {
		CanvasCoordinate coordinate = toCanvasCoordinates(point);
		return (grid.getCellType(coordinate.x, coordinate.y) == 2);
	}

	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canva.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	// tower types
	// 10 - basic tower => base level, 11 next level
	// 20 - freezing tower =>base level
	// 30 - firing tower => 31 , 32, ...
	public void placeTowerOnMap(Point point, int towerType) {
		if (true) // canPlaceTower(point)
		{
			TowerParameters newParams = new TowerParameters();
			// newParams.towerType = ;

			newParams.position = toCanvasCoordinates(point);
			newParams.towerType = towerType;
			newParams.towerCurrentLevel = 1;
			// newParams.immagePath = "/tower1.png";

			// Tower tower = towerFactory.creatTower(newParams);

			Tower tower = towerFactory.creatTower();

			CanvasCoordinate localPoint = toCanvasCoordinates(point);
			grid.setCell(localPoint.x, localPoint.y, 5); // @TODO: change the
															// last parameter
															// set proper tower
															// value
			String position = Integer.toString(localPoint.y) + " "
					+ Integer.toString(localPoint.x);

			((Map) grid).addTower(tower, position);
			canva.repaint();
			pack();
		}
	}

	public void towerControler(Point point, String levelUpDown) {
		String position = Integer.toString(point.y) + " "
				+ Integer.toString(point.x);
		((Map) grid).updateLevel(position, levelUpDown);

		double price = ((Map) grid).towers.get(position).cost();

		String view = "<html>\n" + "Tower characteristics:\n" + "<ul>\n"
				+ "<li><font color=red>Distance: " + towerParam.range
				+ "</font>\n" + "<li><font color=blue>Frequency:"
				+ towerParam.firingSpeed + "</font>\n"
				+ "<li><font color=green>Power: x</font>\n"
				+ "<li><font color=green>Damage: 3</font>\n" + "</ul>\n";

		// Point currentPosition = new Point(j,i);
		// towerWindow.updateCurrentPosition(currentPosition);
		canva.repaint();
		towerWindow.updateView(view);

		canva.repaint();
		pack();

	}
}
