package core.applicationservice.mapservices.pathfinder;

import infrastructure.loggin.Log4jLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.core.applicationservice.mapservices.MatrixUtility;
import core.applicationservice.mapservices.MapUtility;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class PathService {
	Map<Position, Integer> nodes;

	/**
	 * application logger definition
	 */
	private static final Log4jLogger logger = new Log4jLogger();
	

	public GridCellContentType[][] matrixReadre(String file, int matrixHeight,
			int matrixWidth) {

		MatrixUtility matrixReaderObject = new MatrixUtility();

		return matrixReaderObject.matrixCellType(file, matrixHeight,
				matrixWidth);
	}

	public List<String> graphInput(GridCellContentType[][] matrix) {
		try {

			nodes = initialize(matrix);

			List<String> relations = new ArrayList<>();

			
			Position p = null;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {

					if (isRight(matrix, i, j)) {

						p = new Position(i, j);
						int node = nodes.get(p); // current cell node number

						p = new Position(i, j + 1);
						int rightNode = nodes.get(p); // right cell node number

						relations.add(node + " " + rightNode);
						; // should add to an array
					}

					if (isDown(matrix, i, j)) {

						p = new Position(i, j);
						int node = nodes.get(p); // current cell node number

						p = new Position(i + 1, j);
						int bottomNode = nodes.get(p); // bottom cell node
						// number
						relations.add(node + " " + bottomNode);

					}

				}

			}

			return relations;

		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);

		}
		return null;
	}

	/**
	 * 
	 * @param matrix
	 *            the matrix that we want for validating
	 * @param i
	 *            as int that shows the index of the node that we want to check
	 *            if there is any neighbor in right or not
	 * @param j
	 *            as int that shows the index of the node that we want to check
	 *            if there is any neighbor in right or not
	 * @return boolean that shows there is a connected neighbor in right place
	 */
	public boolean isRight(GridCellContentType[][] matrix, int i, int j) {
		return (
				((matrix[i][j] == GridCellContentType.PATH ||
				matrix[i][j] == GridCellContentType.ENTRANCE ||
				matrix[i][j] == GridCellContentType.EXIT) &&
				j + 1 < matrix[0].length) && 

				((matrix[i][j+1] == GridCellContentType.PATH ||
				matrix[i][j+1] == GridCellContentType.ENTRANCE ||
				matrix[i][j+1] == GridCellContentType.EXIT)	&&	
				j + 1 < matrix[0].length)
				
				
						);
	}

	/**
	 * {@link #isRight(GridCellContentType[][], int, int)}
	 * 
	 * @param matrix
	 *            that is the grid
	 * @param i
	 *            index of actual node
	 * @param j
	 *            index of actual node
	 * @return true , if it has right connection
	 */
	public boolean isDown(GridCellContentType[][] matrix, int i, int j) {
		return (
				((matrix[i][j] == GridCellContentType.PATH ||
				 matrix[i][j] == GridCellContentType.ENTRANCE || 
				 matrix[i][j] == GridCellContentType.EXIT) &&
				 i + 1 < matrix.length) &&
				( (matrix[i+1][j] == GridCellContentType.PATH ||
				 matrix[i+1][j] == GridCellContentType.ENTRANCE || 
				 matrix[i+1][j] == GridCellContentType.EXIT) 
				 && i + 1 < matrix.length));
	}

	public void writeToFile(List<String> relations,int width, int height) {

		try {
			File file = new File(
					"src/core/applicationservice/mapservices/pathfinder/S.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(width * height + "\n");
			bw.write(relations.size() + "\n");
			for (String str : relations) {
				bw.write(str +"\n");
			}

			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		PathService p = new PathService();
		GridCellContentType[][] a = p.matrixReadre("matrix.txt", 4, 8);
		MapUtility utility = new MapUtility();
		Position s = utility.getEnter(a);
		Position e = utility.getExit(a);
	    List<String> relations = p.graphInput(a);
	    int start = p.nodes.get(s);
		int end = p.nodes.get(e);
		p.writeToFile(relations,a.length, a[0].length);
		DepthFirstPaths.demo(p.nodes, start, end);

	}

	public  Map<Position, Integer> initialize(GridCellContentType[][] matrix) {

		Map<Position, Integer> map = new HashMap<Position, Integer>();
		int key = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Position p = new Position(i, j);

				map.put(p, key++);
			}

		}

		return map;

	}
}
