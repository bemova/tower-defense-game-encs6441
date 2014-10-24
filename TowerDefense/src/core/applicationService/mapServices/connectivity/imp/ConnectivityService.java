/*
 * 
 */
package core.applicationService.mapServices.connectivity.imp;

import java.io.BufferedReader;
import java.io.FileReader;

import core.applicationService.mapServices.connectivity.IConnectivityService;
import core.domain.waves.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectivityService.
 */
public class ConnectivityService implements IConnectivityService {

	/** The br. */
	private static BufferedReader br;

	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IConnectivityService#isTherePath(core.domain.waves.Position, core.domain.waves.Position, int[][])
	 */
	@Override
	public boolean isTherePath(Position start, Position end, int[][] matrix){
		try {
			WeightedUnionPathCompression compression = new WeightedUnionPathCompression();
			compression.initialize(matrix.length, matrix[0].length);
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (isRight(matrix, i, j)) {
						if (isPath(matrix, i, j+1))
							compression.union(new Position(i, j), new Position(
									i, j + 1));
					}
					if (isDown(matrix, i, j)) {
						if (isPath(matrix, i+1, j))
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
	public boolean isRight(int[][] matrix, int i, int j) {
		return ((matrix[i][j] == 1 || matrix[i][j] == 2 || matrix[i][j] == 3)
				&& j + 1 < matrix[0].length);
	}
	public boolean isDown(int[][] matrix, int i, int j){
		return ((matrix[i][j] == 1 || matrix[i][j] == 2 || matrix[i][j] == 3)
				&& i + 1 < matrix.length);
	}
	public boolean isPath(int[][] matrix, int i, int j){
		return (matrix[i][j] == 1 || matrix[i][j] == 2
				|| matrix[i][j] == 3);
	}
}
