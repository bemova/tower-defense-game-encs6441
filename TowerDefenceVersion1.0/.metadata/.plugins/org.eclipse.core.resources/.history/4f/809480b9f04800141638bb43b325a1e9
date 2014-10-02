package GameContraller;

import java.util.LinkedList;
import java.util.List; 
import java.util.ArrayList;

public class GraphNode {
	GraphNode(){stateColor = "green";};
	GraphNode(String name, int cordinateX, int cordinateY){
		this.name = name;
		stateColor = "green";
		this.cordinateX = cordinateX;
		this.cordinateY = cordinateY;
		
	};
	ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();
	
	String name;
	String stateColor;
	int outputState = 0;
	int cordinateX;
	int cordinateY;
	void AddNeighbor(GraphNode node){
		neighbors.add(node);
		
	};

}
