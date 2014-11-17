package JUnitTests;

import static org.junit.Assert.*;
import maps.EmptyGrid;
import maps.Grid;
import org.junit.Test;
import MapServacs.MapManager;

public class test_1 {

	@Test // test 13
	public void SaveInvalidMap_NoExit_3(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 1},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		((EmptyGrid)Expectedgrid).sizeOfUnit = 30;
		((EmptyGrid)Expectedgrid).height = 10;
		((EmptyGrid)Expectedgrid).width = 10;
			
		MapManager mapManager = new MapManager();
		String message = "";
		
		String fileName = this.getClass().getResource("/saveInvalidMapPathNotExist.txt").getPath();
		String receivedMessage = mapManager.SaveMapIntoFle(Expectedgrid, fileName);

		System.out.println(receivedMessage + "/");
		assertNotEquals(message,receivedMessage);		
	
		
	}
	



}
