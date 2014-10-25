package UI.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import UI.CanvaObject;
import UI.Constants;
import core.applicationService.vikiMapServacs.MapManager;
//import core.domain.maps.CompleteGrid;
//import core.domain.maps.EmptyGrid;
//import core.domain.maps.Grid;
import core.domain.maps.Grid2;
import core.domain.maps.GridCellContentType;
import core.domain.maps.Map;
import core.domain.maps.VisualGrid;

public class MapEditorPanel extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;

	JButton scenery;
	JButton path;
	JButton ep;
	JButton exp;

	Color colorToDrawGreed;
	GridCellContentType cellContent;

//	Grid grid = new EmptyGrid(height, width, GridCellContentType.SCENERY);
	Grid2 grid;

	MapManager mapManager;
	CanvaObject canvas;
	JPanel mapContainer;
//	JPanel entryP;

	JPanel toolBoxContainer = new JPanel();

	@SuppressWarnings("unused")
	private MapEditorPanel() {
	}

	public MapEditorPanel(int width, int height) {
		
		initialize(width, height);
		setLayout(new BorderLayout());
//		this.width = width;
//		this.height = height;
		
		scenery.setBackground(Constants.SCENERY_COLOR);
		path.setBackground(Constants.PATH_COLOR);
		ep.setBackground(Constants.ENTRANCE_COLOR);
		exp.setBackground(Constants.EXIT_COLOR);
		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(scenery);
		toolBoxContainer.add(path);
		toolBoxContainer.add(exp);
		toolBoxContainer.add(ep);

		mapManager = new MapManager();

		scenery.addActionListener(this);
		path.addActionListener(this);
		ep.addActionListener(this);
		exp.addActionListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		int mapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int mapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(mapPixelWidth, mapPixelHeight);
		mapContainer.setPreferredSize(new Dimension(mapPixelWidth, mapPixelHeight));
		mapContainer.add(canvas);

		add(new JPanel() ,BorderLayout.NORTH);
		add(toolBoxContainer, BorderLayout.EAST);
		add(mapContainer, BorderLayout.CENTER);
		add(new JPanel() ,BorderLayout.WEST);
		add(new JPanel() ,BorderLayout.SOUTH);
		setVisible(true);

	}

	// void setMapSize(int width, int height) {
	// mapContainer.setSize(width * grid.getUnitSize(), height *
	// grid.getUnitSize());
	// canvas.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
	//
	// }

	private void initialize(int width, int height) {
//		width = 15;
//		height = 15;
		this.width = width;
		this.height = height;

		scenery = new JButton(Constants.SCENERY);
		path = new JButton(Constants.PATH);
		ep = new JButton(Constants.ENTRANCE);
		exp = new JButton(Constants.EXIT);

		colorToDrawGreed = Color.green;
		cellContent = GridCellContentType.PATH;

//		Grid grid = new EmptyGrid(height, width, GridCellContentType.SCENERY);
//		grid = null;
//		grid = new Grid2(width, height, GridCellContentType.SCENERY);
		grid = new VisualGrid(width, height, GridCellContentType.SCENERY);

//		MapManager mapManager;
//		canvas = null;
		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();
//		entryP = new JPanel();

		toolBoxContainer = new JPanel();
		
	}

	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	public void setGridSize(int width, int height) {
		grid.setSize(width, height);
		canvas.updateGrid(grid);
	}
	public void design(int width, int height) {
		try {
//			if (width > 60 || width < 5 || height > 60 || height < 5)
//				throw new java.lang.Exception(
//						"Error size max size: ....., min size: ....");

			grid.setSize(width, height);
//			grid = new VisualGrid(width, height);
			// grid.setAllCells()
			canvas.updateGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

//			add(entryP, BorderLayout.CENTER);

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

	private void draw(int x, int y) {
		int i = y / grid.getUnitSize();
		int j = x / grid.getUnitSize();
		if ((i < grid.getHeight()) && (j < grid.getWidth())
				&& (grid.getCell(i, j) != cellContent)) {
			grid.setCell(i, j, cellContent);
			canvas.repaint();

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
//		case Constants.DESIGN_MAP:
////			designMap();
//			break;
//		case Constants.LOAD_MAP:
////			loadMap();
//			break;
//		case Constants.SAVE_MAP:
////			saveMap();
//			break;
		case Constants.SCENERY:
			scenery();
			break;
		case Constants.ENTRANCE:
			entrance();
			break;
		case Constants.EXIT:
			exit();
			break;
//		case Constants.DRAW:
////			drawMap();
//			break;
//		case Constants.SET_SIZE:
////			setMapSize();
//			break;
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

	protected void setMapSize(int width, int height) {
		try {
			this.width = width;
			this.height = height;
			if (width > 60 || width < 5 || height > 60 || height < 5)
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");

//			grid.setSize(width, height);
//			canvas.updateGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

//			add(entryP, BorderLayout.CENTER);

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void drawMap() {
		try {
//			if (((Map) grid).getEntryPoint().isEmpty()
//					|| ((Map) grid).getExitPOint().isEmpty())

			//validate grid
//				throw new Exception("Entry and exit points are not defined");
			canvas.updateGrid(grid);
			canvas.repaint();

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

	protected void saveMap() {
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

	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
//				grid = new CompleteGrid(grid);
//				grid = new VisualGrid(grid);
				grid.gridAssignmentOperator(mapManager.LoadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				canvas.updateGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());

//				add(entryP, BorderLayout.CENTER);

				String message = mapManager.validateMapContent(grid);
				if (!message.equals(""))
					JOptionPane.showMessageDialog(null, message);
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	protected void designMap() {
		try {
//			grid = new CompleteGrid(grid);
//			grid = new VisualGrid(grid);
			canvas.updateGrid(grid);
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

}
