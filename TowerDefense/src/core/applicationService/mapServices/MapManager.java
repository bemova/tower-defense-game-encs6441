package core.applicationService.mapServices;

import core.domain.maps.Grid;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapManager {

	public MapManager() {

	}

	public Grid LoadMapFromFile(String fileName) {
		Grid grid = null;
		try {
			FileInputStream streamIn = new FileInputStream(fileName);
			ObjectInputStream objectinputstream = new ObjectInputStream(
					streamIn);
			grid = (Grid) objectinputstream.readObject();
			objectinputstream.close();
		} catch (Exception e) {
			// errorMessage = "I couldn't load the map!";
		}
		return grid;
	}

	public String SaveMapIntoFle(Grid grid, String FileName) {

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
