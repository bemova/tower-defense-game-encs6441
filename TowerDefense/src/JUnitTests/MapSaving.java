package JUnitTests;

import static org.junit.Assert.*;
import maps.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import MapServacs.*;

import java.nio.file.attribute.GroupPrincipal;
import java.util.Stack;
/**
 * This set of test cases are related to the validation of map => loading, save, entry exit points, existing path
 * To be more concrete :

* 9) save  valid map into a file properly
* 10) - 16) Save invalid map
 * 
 * @author vika
 *
 */
public class MapSaving {
	
		/** Test case checks how the system should react in case of invalid map.
		 * In this particular case there are more than one exit points
		 * Test case fails if there is no error message returned 
		 */
	/**This particular case validates map saving mechanism. First it saves file, then it loads file and checks whethere that is exactly what ws sved
		 * 
		 */
	@Test // test 9
		public void SaveMapcheckCorrectness(){
	
		Grid Expectedgrid = new EmptyGrid(10,10);

			((EmptyGrid)Expectedgrid).setSize(10, 10);
			((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
					{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
					{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
					{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
					{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
			((EmptyGrid)Expectedgrid).sizeOfUnit = 30;
			((EmptyGrid)Expectedgrid).height = 10;
			((EmptyGrid)Expectedgrid).width = 10;
				
			MapManager mapManager = new MapManager();
			String fileName = this.getClass().getResource("/saveLoadTestCase.txt").getPath();
			mapManager.SaveMapIntoFle(Expectedgrid, fileName);
			
			Grid actualGrid = mapManager.LoadMapFromFile(fileName);
			
			assertArrayEquals(((EmptyGrid)actualGrid).content, ((EmptyGrid)Expectedgrid).content);		
			//assertEquals(((EmptyGrid)actualGrid), ((EmptyGrid)Expectedgrid));	
			
		} 
	
	/**Test case checks whether invalid maps with an invalid path are recognized or not  when it is being saved into a file
	 *
	 * 
	 */
	@Test // test 10
	public void SaveInvalidMap_PathNotExist(){

	Grid Expectedgrid = new EmptyGrid(10,10);

		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 2, 2, 2, 2, 2, 2, 2},
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
		mapManager.SaveMapIntoFle(Expectedgrid, fileName);
		
		assertNotEquals(message,mapManager.SaveMapIntoFle(Expectedgrid, fileName));		
	
		
	} 
	
	/**Test case checks whether invalid maps with an invalid number of entry points are recognized or not  when it is being saved into a file
	 *
	 * 
	 */
	@Test // test 11
	public void SaveInvalidMap_InvalidEntry_1(){

	Grid Expectedgrid = new EmptyGrid(10,10);

		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
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
		mapManager.SaveMapIntoFle(Expectedgrid, fileName);
		
		assertNotEquals(message,mapManager.SaveMapIntoFle(Expectedgrid, fileName));		
	
		
	} 
	
	/**Test case checks whether invalid maps with an invalid place of entry point are recognized or not  when it is being saved into a file
	 *
	 * 
	 */
	@Test // test 12
	public void SaveInvalidMap_InvalidEntry_2(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 3, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		((EmptyGrid)Expectedgrid).sizeOfUnit = 30;
		((EmptyGrid)Expectedgrid).height = 10;
		((EmptyGrid)Expectedgrid).width = 10;
			
		MapManager mapManager = new MapManager();
		String message = "";
		
		String fileName = this.getClass().getResource("/saveInvalidMapPathNotExist.txt").getPath();
		String receivedMessage = mapManager.SaveMapIntoFle(Expectedgrid, fileName);

	//	System.out.println(receivedMessage);
		assertNotEquals(message,receivedMessage);		
	
		
	}
	
	/**Test case checks whether invalid maps with an invalid place of exit point are recognized or not  when it is being saved into a file
	 *
	 * 
	 */
	@Test // test 13
	public void SaveInvalidMap_InvalidExit_1(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 2},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 4, 2},
				{2, 3, 2, 2, 2, 2, 2, 2, 2, 2},
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
	
	/**Test case checks whether invalid maps with an invalid number of exit point are recognized or not  when it is being saved into a file
	 *
	 * 
	 */
	@Test // test 14
	public void SaveInvalidMap_InvalidExit_2(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 4},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 3, 2, 2, 2, 2, 2, 2, 2, 2},
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

	/**Test case checks whether invalid maps with no exit point are recognized or not  when it is being saved into a file
	 *
	 * 
	 */	
	@Test // test 15
	public void SaveInvalidMap_NoExit_3(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 1},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 1},
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
		mapManager.SaveMapIntoFle(Expectedgrid, fileName);
		System.out.println(receivedMessage + "/");
		assertNotEquals(message,receivedMessage);		
	
		
	}
	
	/**Test case checks whether invalid maps with no entry point are recognized or not when it is being saved into a file
	 *
	 * 
	 */	
	@Test // test 16
	public void SaveInvalidMap_NoEntry_3(){

	Grid Expectedgrid = new EmptyGrid(10,10);
//Invalid Map: inappropriate entry point
		((EmptyGrid)Expectedgrid).setSize(10, 10);
		((EmptyGrid)Expectedgrid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{1, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 1},
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
		
	


