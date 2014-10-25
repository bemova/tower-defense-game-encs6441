package core.applicationService.vikiMapServacs;

import core.domain.maps.Grid2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import core.applicationService.vikiMapServacs.StandardAlgorithms;

public class MapManager {

	public StandardAlgorithms algorithms = null;

	public MapManager() {

	}

	public Grid2 LoadMapFromFile(String fileName) {
		Grid2 grid = null;
		try {
			FileInputStream streamIn = new FileInputStream(fileName);
			ObjectInputStream objectinputstream = new ObjectInputStream(
					streamIn);
			grid = (Grid2) objectinputstream.readObject();
			objectinputstream.close();
		} catch (Exception e) {
			// errorMessage = "I couldn't load the map!";
		}
		return grid;
	}

	public String SaveMapIntoFle(Grid2 grid, String FileName) {

		String errorMessage = "";

		try {
			FileOutputStream fout = new FileOutputStream(FileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(grid);
			oos.close();
		} catch (Exception e) {
			errorMessage = "Oops! something went wrong, I couldn't save the map!";
		}
		return errorMessage;
	}

}
