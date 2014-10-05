package JUnitTests;

import static org.junit.Assert.*;

import java.nio.file.attribute.GroupPrincipal;

import org.junit.Test;

import GameContraller.MapManager;
import GameContraller.StandardAlgorithms;
import GameElements.CompleteGrid;
import GameElements.EmptyGrid;
import GameElements.Grid;

/**
 * This set of test cases are related to the validation of map
 * To be more concrete, map loading, saving, correct existing path, valid entry and exit points 
 * 
 * 
 * @author vika
 *
 */


/** Checks whether the map is loaded correct or not. For that reason an testing map is created and the path is given to a function
 * The content of the file is assigned to a specific Grid object
 * When the test runs, it loads the map, creates an appropriate Grid and checks the content of that grid with the one that is hardcoded 
 * If there is inconsistency, test fails 
 *
 *
 */
public class MapLoadingTest1 {

	
	

	
	@Test
	public void checkCorrectMapLoading(){
		Grid grid = new EmptyGrid(10,10);

		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
				
		// = CompleteGrid(grid);
		
		
		MapManager mapManager = new MapManager();
		String fileName = this.getClass().getResource("/11_11.txt").getPath();
		Grid loadedGrid = mapManager.LoadMapFromFile(fileName);
		
		// Question => How to test private methods ? do we have to use get() method ??
		
		for(int i = 0; i < 10; i++)
			for( int j = 0; j< 10; j++)
				if(grid.content[i][j] != loadedGrid.content[i][j]) 
					fail("Map loading test failed => map is not being load appropriately from a file");
		
		
	} 
	
/** Test case is used to check the validity of the map. Correctness for this test case mean that there is only one entry and one exit point.
 * For entry point to be valid, it  has to be on the left side of the mat, while the exit point has to be on the right side. Speaking
 * in a math level, for a[i][j] to be a valid entry j = 0 mast be true, for it to be a valid exit point j = 9 mast be true if the
 * size of grid is (10X10) 
 * 	
 */
	
	@Test
	public void validateCorrectnessOfMap(){
		Grid grid = new EmptyGrid(10,10);
		
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		String message = "";
		if(!(message  = algorithm.fillGraph()).equals("")){
			
			message =  "testcase faild " + "  " + message;
			fail(message);
		}
			
		
		
	}
	
	/** Test case checks how the system should react in case of invalid map.
	 * In this particular case there is more that 1 entry point
	 * Test case fails if there is no error message returned
	 */
	
	@Test
	public void validateCorrectnessOfMap_2(){
		Grid grid = new EmptyGrid(10,10);
		MapManager manager  = new MapManager();
		
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		String message = "";
		if((message  = algorithm.fillGraph()).equals("")){
			
			fail("Thest case failed: The system does not recognize invalid number of entry points");
		}
		
		if(!(manager.validateEntryExit(grid)).equals(""))
			fail("Thest case failed: The system does not recognize invalid exit points");
			
		
		
	}
	
	
	
	/** Test case checks how the system should react in case of invalid map.
	 * In this particular case entry point is not on the left side of the map
	 * Test case fails if there is no error message returned 
	 */
	@Test
	public void validateCorrectnessOfMap_3(){
		Grid grid = new EmptyGrid(10,10);
		MapManager manager =  new MapManager();
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 3, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		String message = "";
		if((message  = algorithm.fillGraph()).equals("")){
			

			fail("Thest case failed: The system does not recognize invalid entry points");
		}
		
		if(!(manager.validateEntryExit(grid)).equals(""))
			fail("Thest case failed: The system does not recognize invalid exit points");

			
		
		
	}
	
	
	
	/** Test case checks how the system should react in case of invalid map.
	 * In this particular case exit point is not on the right side of the map
	 * Test case fails if there is no error message returned 
	 */
	@Test
	public void validateCorrectnessOfMap_4(){
		Grid grid = new EmptyGrid(10,10);
		MapManager manager =  new MapManager();
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{3, 1, 3, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 4, 1, 2},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		String message = "";
		if((message  = algorithm.fillGraph()).equals("")){
			
		fail("Thest case failed: The system does not recognize invalid exit points");
		}
			
		if(!(manager.validateEntryExit(grid)).equals(""))
			fail("Thest case failed: The system does not recognize invalid exit points");

		
	}
	
	/** Test case checks how the system should react in case of invalid map.
	 * In this particular case there sre more than one exit points
	 * Test case fails if there is no error message returned 
	 */
	
	@Test
	public void validateCorrectnessOfMap_5(){
		Grid grid = new EmptyGrid(10,10);
		MapManager manager =  new MapManager();
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{3, 1, 3, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 4},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		String message = "";
		if((message  = algorithm.fillGraph()).equals("")){
			
			
			fail("Thest case failed: The system does not recognize invalid exit points");
		}

		if(!(manager.validateEntryExit(grid)).equals(""))
			fail("Thest case failed: The system does not recognize invalidr of numbe exit points");

		
	}
	
	/**Test case checks the validity of existing path, meaning that there should always be a path between 2 given cells in the grid
	 * For this reason a specific grid is created and the entry and exit points are given as 2 
	 * 
	 */

	@Test
	public void validateExistingPath(){
		
		Grid grid = new EmptyGrid(10,10);
		
		grid.setSize(10, 10);
		grid.content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
				{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
				{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
				{2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(grid);
		algorithm.mapManager();
		
	}
	
	
}

	

