package core.applicationService.mapServices;

import Infrastructure.loggin.Log4jLogger;
import core.applicationService.mapServices.connectivity.imp.ConnectivityService;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.domain.maps.Grid;
import core.domain.waves.Position;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * <b>it can used as a service during save and load from or into the file</b>
 * @author ali
 * @version 0.1
 */
public class MapManager {

	private static final Log4jLogger logger = new Log4jLogger();
	public MapManager() {

	}

	/**
	 * 
	 * @param fileName is the address of the file
	 * @return Grid
	 */
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
			logger.writer(this.getClass().getName(), e);
		}
		return grid;
	}

	/**
	 * <b>save the grid into file</b>
	 * @param grid
	 * @param FileName
	 * @return
	 */
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
			logger.writer(this.getClass().getName(), e);
		}
		return errorMessage;
	}

}
