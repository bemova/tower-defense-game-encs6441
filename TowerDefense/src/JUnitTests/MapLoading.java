package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;

import java.nio.file.attribute.GroupPrincipal;
import java.util.Stack;

import  core.applicationService.vikiMapServacs.*;
import core.domain.maps.*;

import org.junit.Test;
/**
 * 1) Map loading returns the exact map from the given file
 * 2) Entry is 1 and Exit is 1
 * 3) More than one Entry 
 * 4) More than one Exit
 * 5) Entry point is chosen arbitrary
 * 6) Exit point is chosen arbitrary 
 * @author vika
 *
 */
public class MapLoading {

	
	/** Checks whether the map is loaded correct or not. For that reason an testing map is created and the path is given to a function
	 * The content of the file is assigned to a specific Grid object
	 * When the test runs, it loads the map, creates an appropriate Grid and checks the content of that grid with the one that is hardcoded 
	 * If there is inconsistency, test fails 
	 *
	 *
	 */
	@Test // test 1
	public void MapLoadingcheckCorrectness(){

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
		String fileName = this.getClass().getResource("/11_11.txt").getPath();
		Grid actualGrid = mapManager.LoadMapFromFile(fileName);
		
		assertArrayEquals(((EmptyGrid)actualGrid).content, ((EmptyGrid)Expectedgrid).content);		

		
	} 
	
	/** Test case is used to check the validity of the map. Correctness for this test case mean that there is only one entry and one
	 *  exit point.	 For entry point to be valid, it  has to be on the left side of the mat, while the exit point has to 
	 *  be on the right side. Speaking
	 * in a math level, for a[i][j] to be a valid entry j = 0 mast be true, for it to be a valid exit point j = 9 mast be true if the
	 * size of grid is (10X10) 
	 * 	
	 */	
	@Test //test 2
		public void validNumberEntryPoints(){
			Grid 	 grid = new EmptyGrid(10,10);

			((EmptyGrid)grid).setSize(10, 10);
			((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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
			assertEquals(message, algorithm.fillGraph());
			
		
		}
		
		/** Test case checks how the system should react in case of invalid map.
		 * In this particular case there is more that 1 entry point
		 * Test case fails if there is no error message returned
		 */
	
		@Test // test 3
		public void invalidNumberEntryPoints(){ 
			Grid grid = new EmptyGrid(10,10);
			
			((EmptyGrid)grid).setSize(10, 10);
			((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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
			
			assertNotEquals(message, algorithm.fillGraph());
			
		}
		/** Test case checks how the system should react in case of invalid map.
		 * In this particular case there is more that 1 Exit point
		 * Test case fails if there is no error message returned
		 * 
		 */
		@Test// test 4
		public void invalidNumberExitPoints(){ 
			Grid grid = new EmptyGrid(10,10);
			
			((EmptyGrid)grid).setSize(10, 10);
			((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 1, 1, 1, 2, 2},
					{2, 1, 2, 2, 2, 1, 2, 1, 2, 2},
					{3, 1, 2, 2, 2, 1, 2, 1, 2, 2},
					{2, 1, 2, 1, 1, 1, 2, 1, 1, 4},
					{2, 1, 1, 1, 2, 2, 2, 2, 2, 4},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
					{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
			
			StandardAlgorithms algorithm = new StandardAlgorithms(grid);
			String message = "";
			
			assertNotEquals(message, algorithm.fillGraph());
			
		} 
		
		
		
		/** Test case checks how the system should react in case of invalid map.
		 * In this particular case entry point is not on the left side of the map
		 * Test case fails if there is no error message returned 
		 */
		
		@Test // test 5
		public void arbitraryEntryPoint(){
			Grid grid = new EmptyGrid(10,10);
			MapManager manager =  new MapManager();
			((EmptyGrid)grid).setSize(10, 10);
			((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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
			
			assertNotEquals(message, algorithm.fillGraph());
			
		}
		
		
		
		/** Test case checks how the system should react in case of invalid map.
		 * In this particular case exit point is not on the right side of the map
		 * Test case fails if there is no error message returned 
		 */
		
		@Test //test 6
		public void arbitraryExitPoint(){
			Grid grid = new EmptyGrid(10,10);
			MapManager manager =  new MapManager();
			((EmptyGrid)grid).setSize(10, 10);
			((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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
			assertNotEquals(message, algorithm.fillGraph());

			
		}
		


}
