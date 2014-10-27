/*
 * 
 */
package core.applicationService.mapServices.connectivity.imp;

import java.io.BufferedReader;

import core.applicationService.mapServices.connectivity.IConnectivityService;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

/**
 * <b>The Class ConnectivityService.</b>
 * in this class connectivity service was added to check if there is any path between start and end 
 * @author mojtaba
 * @version 0.1
 */
public class ConnectivityService implements IConnectivityService {

	private static BufferedReader br;

	/**
	 * <b>
	 * Check for validating if there is any path between start and end 
	 * in this method we transform our grid that was built as GridCellType
	 * into an graph and after that we make a union between each nodes of graph that has a relation to their neighbors.
	 * </b>
	 * @param start as Position that represent the entrance point
	 * @param end as Position that represent the exit point of aliens
	 * @param matrix as GridCellContentType
	 * @return boolean
	 */
	public boolean isTherePath(Position start, Position end,
			GridCellContentType[][] matrix) {
		try {
			WeightedUnionPathCompression compression = new WeightedUnionPathCompression();
			compression.initialize(matrix.length, matrix[0].length);
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (isRight(matrix, i, j)) {
						if (isPath(matrix, i, j + 1))
							compression.union(new Position(i, j), new Position(
									i, j + 1));
					}
					if (isDown(matrix, i, j)) {
						if (isPath(matrix, i + 1, j))
							compression.union(new Position(i, j), new Position(
									i + 1, j));
					}
				}
			}
			return (compression.connected(start, end));

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param matrix
	 * @param i as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @param j as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @return boolean that shows there is a connected neighbor in right place
	 */
	public boolean isRight(GridCellContentType[][] matrix, int i, int j) {
		return ((matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE 
				|| matrix[i][j] == GridCellContentType.EXIT)
				&& j + 1 < matrix[0].length);
	}
	/**
	 * {@link #isRight(GridCellContentType[][], int, int)}
	 * @param matrix
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isDown(GridCellContentType[][] matrix, int i, int j) {
		return ((matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE 
				|| matrix[i][j] == GridCellContentType.EXIT)
				&& i + 1 < matrix.length);
	}
	/**
	 * it can check is this node is part of path or not
	 * @param matrix
	 * @param i
	 * @param j
	 * @return boolean 
	 */
	public boolean isPath(GridCellContentType[][] matrix, int i, int j) {
		return (matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE
				|| matrix[i][j] == GridCellContentType.EXIT);
	}
}
