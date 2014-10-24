package JUnitTests;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import core.applicationService.vikiMapServacs.GraphNode;
import core.applicationService.vikiMapServacs.StandardAlgorithms;
import core.domain.maps.EmptyGrid;
import core.domain.maps.Grid;
/**
 * 7) Path is correct 
 * 8) invalid path is recognized 
 * 
 * @author vika
 *
 */
public class Path {

	
	/**Test case checks the validity of existing path, meaning that there should always be a path between 2 given cells in the grid
	 * For this reason a specific grid is created with a valid path. When the algorithm shortest path compute path the result is compared with the
	 * path we extracted from a given matrix
	 * 
	 */
	
	@Test // test 7
	public void validateExistingPath(){
		
		Grid grid = new EmptyGrid(10,10);
		
		((EmptyGrid)grid).setSize(10, 10);
		((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{3, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 1, 1, 1, 1, 1, 1, 1, 4},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(10,10,((EmptyGrid)grid).content);
		Stack<GraphNode> nodePathActual = new Stack<GraphNode>();

	//	2 0, 2 1, 3 1, 4 1, 5 1, 5 2, 5 3, 5 4, 5 5, 5 6
		String[] expectedValues = new String[13];
		String[] actuallValues = new String[13];
		expectedValues[0] = "2 0";
		expectedValues[1]= "2 1";
		expectedValues[2] = "3 1";
		expectedValues[3] = "4 1";
		expectedValues[4] = "5 1";
		expectedValues[5] = "5 2";
		expectedValues[6] = "5 3";
		expectedValues[7] = "5 4";
		expectedValues[8] = "5 5";
		expectedValues[9] = "5 6";
		expectedValues[10] = "5 7";
		expectedValues[11] = "5 8";
		expectedValues[12] = "5 9";
		
	/*	nodePath.push(new GraphNode("2 0",2,0));
		nodePath.push(new GraphNode("2 1",2 ,1));
		nodePath.push(new GraphNode("3 1",3 ,1));
		nodePath.push(new GraphNode("4 1",4, 1));
		nodePath.push(new GraphNode("5 1",5, 1));
		nodePath.push(new GraphNode("5 2",5, 2));
		nodePath.push(new GraphNode("5 3", 5, 3));
		nodePath.push(new GraphNode("5 4",5 ,4));
		nodePath.push(new GraphNode("5 5",5 ,5));
		nodePath.push(new GraphNode("5 6",5 ,6));
		nodePath.push(new GraphNode("5 7",5 ,7));
		nodePath.push(new GraphNode("5 8",5 ,8));
		nodePath.push(new GraphNode("5 9",5 ,9)); */
		algorithm.fillGraph();
		nodePathActual = algorithm.shortestPathfinding();
		
		int i = 12;
		while(!nodePathActual.empty()){
			actuallValues[i] =nodePathActual.peek().getName();
			nodePathActual.pop();
			i--;
		}
		assertArrayEquals(actuallValues, expectedValues);
				
			}
	
	
	/** Test case checks how the system should react in case of invalid path.
	 *In this particular case there there is no path between entry and exit point
	 */		
	
	@Test // test 8
	public void pathDoesNotExist(){
	Grid grid = new EmptyGrid(10,10);
		
		((EmptyGrid)grid).setSize(10, 10);
		((EmptyGrid)grid).content = new int [][] {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{3, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 1, 1, 2, 1, 1, 1, 1, 1, 4},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
		
		StandardAlgorithms algorithm = new StandardAlgorithms(10,10,((EmptyGrid)grid).content);
	
		Stack<GraphNode> nodePathActual = new Stack<GraphNode>();
		algorithm.fillGraph();
		nodePathActual = algorithm.shortestPathfinding();
		assertNull(nodePathActual);
	
	}
	

}
