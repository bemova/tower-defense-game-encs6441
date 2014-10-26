package core.applicationService.mapServices;

import core.applicationService.mapServices.connectivity.imp.ConnectivityService;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.domain.maps.Grid;
import core.domain.waves.Position;

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
			Object obj = objectinputstream.readObject();
			if(obj instanceof Grid){
				grid = (Grid) obj;
			}
			objectinputstream.close();
		} catch (Exception e) {
			// errorMessage = "I couldn't load the map!";
		}
		return grid;
	}

	public String SaveMapIntoFle(Grid grid, String FileName) {

		String errorMessage = "";
		//validation part 
		try {
			StartEndChecker startEndChecker = new StartEndChecker();
			if(!startEndChecker.hasEnd(grid.getContent()))
				throw new Exception("There isn't any exit point in the map!");
				
			if(!startEndChecker.hasStart(grid.getContent()))
				throw new Exception("There isn't any enterance point in the map!");

			MapUtility mapUtility = new MapUtility();
			Position start = mapUtility.getEnter(grid.getContent());
			Position end = mapUtility.getExit(grid.getContent());
			
			ConnectivityService connectivityService = new ConnectivityService();
			if(!connectivityService.isTherePath(start, end, grid.getContent()))
				throw new Exception("There isn't any path between start and end points in the map!");
			
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
