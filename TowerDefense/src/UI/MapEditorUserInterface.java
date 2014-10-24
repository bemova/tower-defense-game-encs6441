package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.applicationService.vikiMapServacs.MapManager;
import core.domain.maps.CompleteGrid;
import core.domain.maps.EmptyGrid;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.maps.Map;

public class MapEditorUserInterface extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 15;
	private int height = 15;

	JTextField xField = new JTextField("", 4);
	JTextField yField = new JTextField("", 4);
	JButton setMapSizeButten = new JButton("Set Size");

	JLabel entryPoint = new JLabel(Constants.TAG_ENTRY_POINT);
	JLabel exitPoint = new JLabel(Constants.TAG_EXIT_POINTS);
	JButton drowMapButton = new JButton(Constants.DRAW);
	JButton scenery = new JButton(Constants.SCENERY);
	JButton path = new JButton(Constants.PATH);
	JButton ep = new JButton(Constants.Entrance);
	JButton exp = new JButton(Constants.Exit);

	JButton save = new JButton(Constants.SAVE_MAP);
	JButton load = new JButton(Constants.LOAD_MAP);
	JButton designMap = new JButton(Constants.DESIGN_MAP);

	Color colorToDrawGreed = Color.green;
	GridCellContentType cellContent = GridCellContentType.PATH;

	Grid grid = new EmptyGrid(height, width, GridCellContentType.SCENERY);

	MapManager mapManager;
	CanvaObject canva = new CanvaObject(grid);
	JPanel lower = new JPanel();
	JPanel entryP = new JPanel();

	JPanel upper = new JPanel();

	public MapEditorUserInterface() {
		entryP.add(entryPoint);
		entryP.add(exitPoint);
		entryP.add(drowMapButton);

		upper.add(xField);
		upper.add(yField);
		upper.add(new JLabel("Set map size"));
		upper.add(setMapSizeButten);

		scenery.setBackground(Color.green);
		path.setBackground(Color.gray);
		ep.setBackground(Color.red);
		exp.setBackground(Color.blue);
		upper.add(scenery);
		upper.add(path);
		upper.add(exp);
		upper.add(ep);
		upper.add(save);
		upper.add(load);
		upper.add(designMap);

		// make part of interface invisible to a user
		drowMapButton.setVisible(false);

		scenery.setEnabled(false);

		path.setEnabled(false);
		exp.setEnabled(false);
		ep.setEnabled(false);
		save.setEnabled(false);

		xField.setEnabled(false);
		yField.setEnabled(false);

		scenery.setVisible(false);
		path.setVisible(false);
		exp.setVisible(false);
		ep.setVisible(false);
		save.setVisible(false);

		xField.setVisible(false);
		yField.setVisible(false);
		setMapSizeButten.setVisible(false);

		mapManager = new MapManager();

		designMap.addActionListener(this);
		load.addActionListener(this);
		save.addActionListener(this);
		scenery.addActionListener(this);
		path.addActionListener(this);
		ep.addActionListener(this);
		exp.addActionListener(this);
		drowMapButton.addActionListener(this);
		canva.addMouseListener(this);
		canva.addMouseMotionListener(this);
		setMapSizeButten.addActionListener(this);

		canva.setSize(grid.getHeight() * grid.getUnitSize(), grid.getWidth()
				* grid.getUnitSize());
		lower.setSize(grid.getHeight() * grid.getUnitSize(), grid.getWidth()
				* grid.getUnitSize());
		lower.add(canva);

		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);

		setVisible(true);

	}

	void setMapSize(int width, int height) {
		lower.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
		canva.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());

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

	public void design(int width, int height) {
		try {

			// path.setVisible(true);
			scenery.setVisible(true);
			exp.setVisible(true);
			ep.setVisible(true);
			save.setVisible(true);

			// path.setEnabled(true);
			scenery.setEnabled(true);
			exp.setEnabled(true);
			ep.setEnabled(true);
			save.setEnabled(true);

			if (width > 60 || width < 5 || height > 60 || height < 5)
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");

			grid.setSize(width, height);
			// grid.setAllCells()
			canva.updateGrid(grid);

			lower.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canva.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

			add(entryP, BorderLayout.CENTER);

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	public void drawPath(Color backgroundColor) {
		try {

			colorToDrawGreed = backgroundColor;
			cellContent = GridCellContentType.PATH; // black for path
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		draw(event.getX(), event.getY());
		

	}
	private void draw(int x, int y){
		if (designMap.isEnabled()) {
			int i = y / grid.getUnitSize();
			int j = x / grid.getUnitSize();
			if ((i < grid.getHeight()) && (j < grid.getWidth())
					&& (grid.getCell(i, j) != cellContent)) {
				grid.setCell(i, j, cellContent);
				canva.repaint();

			}
		}
	}
	@Override
	public void mouseMoved(MouseEvent event) {
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		draw(event.getX(), event.getY());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case Constants.DESIGN_MAP:
			designMap();
			break;
		case Constants.LOAD_MAP:
			loadMap();
			break;
		case Constants.SAVE_MAP:
			saveMap();
			break;
		case Constants.SCENERY:
			scenery();
			break;
		case Constants.Entrance:
			entrance();
			break;
		case Constants.Exit:
			exit();
			break;
		case Constants.DRAW:
			drawMap();
			break;
		case Constants.SET_SIZE:
			setMapSize();
			break;
		case Constants.PATH:
			path();
			break;
		}
	}

	private void path() {
		try {

			colorToDrawGreed = path.getBackground();
			cellContent = GridCellContentType.PATH; // black for path
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private void setMapSize() {
		try {

			path.setVisible(true);
			scenery.setVisible(true);
			exp.setVisible(true);
			ep.setVisible(true);
			save.setVisible(true);

			path.setEnabled(true);
			scenery.setEnabled(true);
			exp.setEnabled(true);
			ep.setEnabled(true);
			save.setEnabled(true);

			width = Integer.parseInt(xField.getText());
			height = Integer.parseInt(yField.getText());
			if (width > 60 || width < 5 || height > 60 || height < 5)
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");

			grid.setSize(width, height);
			canva.updateGrid(grid);

			lower.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canva.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

			add(entryP, BorderLayout.CENTER);
			setMapSizeButten.setEnabled(false);

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void drawMap() {
		try {
			if (((Map) grid).getEntryPoint().isEmpty()
					|| ((Map) grid).getExitPOint().isEmpty())
				throw new Exception("Entry and exit points are not defined");
			canva.updateGrid(grid);
			canva.repaint();

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void exit() {
		try {
			cellContent = GridCellContentType.EXIT; // blue for exit point
			colorToDrawGreed = exp.getBackground();
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());

		}

	}

	private void entrance() {
		try {

			colorToDrawGreed = ep.getBackground();
			cellContent = GridCellContentType.ENTRANCE; // red for entry point
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void scenery() {
		try {

			colorToDrawGreed = scenery.getBackground();
			cellContent = GridCellContentType.SCENERY; // green

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void saveMap() {
		try {

			JFileChooser saveFile = new JFileChooser();

			if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = saveFile.getSelectedFile().getAbsolutePath();

				String errorMessage = mapManager.SaveMapIntoFle(grid, fileName);

				if (!errorMessage.equals(""))
					JOptionPane.showMessageDialog(null, errorMessage);
			}
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void loadMap() {
		try {

			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				grid = new CompleteGrid(grid);
				grid.gridAssignmentOperator(mapManager.LoadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				canva.updateGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				lower.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canva.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());

				add(entryP, BorderLayout.CENTER);

				String message = mapManager.validateMapContent(grid);
				if (!message.equals(""))
					JOptionPane.showMessageDialog(null, message);
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void designMap() {
		try {
			setMapSizeButten.setVisible(true);
			xField.setVisible(true);
			yField.setVisible(true);
			xField.setEnabled(true);
			yField.setEnabled(true);
			grid = new CompleteGrid(grid);
			canva.updateGrid(grid);
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	public static void main(String args[]) {
		new MapEditorUserInterface();
	}

}
