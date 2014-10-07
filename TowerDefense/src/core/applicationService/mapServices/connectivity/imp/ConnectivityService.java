/*
 * 
 */
package core.applicationService.mapServices.connectivity.imp;

import java.io.BufferedReader;
import java.io.FileReader;

import core.domain.waves.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectivityService.
 */
public class ConnectivityService{
	
	/** The br. */
	private static BufferedReader br;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			WeightedUnionPathCompression compression = new WeightedUnionPathCompression();
			compression.initialize(6, 7);
			br = new BufferedReader(new FileReader("C:\\Users\\mojtaba\\Desktop\\Algorithm\\TD\\matrix.txt"));
			String line;
			int[][] matrix = new int[6][7];
			int index = 0;
			String[] strs;
			while ((line = br.readLine()) != null) {
				strs = line.split("\t");
				for (int i = 0; i < strs.length; i++) {
					matrix[index][i] = Integer.parseInt(strs[i]);
				}
				index++;
			}
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if(matrix[i][j] == 1 && j+1 < matrix[0].length){
						if(matrix[i][j+1] == 1)
							compression.union(new Position(i, j), new Position(i, j+1));
					}
					if(matrix[i][j] == 1 && i+1 < matrix.length ){
						if(matrix[i+1][j] == 1)
						compression.union(new Position(i, j), new Position(i+1, j));
					}
				}
			}
			System.out.println(compression.connected(new Position(0, 1), new Position(3, 3)));

		} catch (Exception e) {
			
		}
	}

}
