package test.core.applicationService.mapServices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MatrixUtility {
	public int[][] matrixReadre(String file, int matrixHeight, int matrixWidth) {
		int[][] matrix = new int[matrixHeight][matrixWidth];
		try (InputStream input = getClass().getResourceAsStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));) {
			String line;
			int index = 0;
			String[] strs;
			while ((line = br.readLine()) != null) {
				strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) {
					matrix[index][i] = Integer.parseInt(strs[i]);
				}
				index++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return matrix;
	}
}