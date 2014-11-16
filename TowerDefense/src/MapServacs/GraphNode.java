package MapServacs;

import java.util.LinkedList;
import java.util.List; 
import java.util.ArrayList;

public class GraphNode {
	public GraphNode(){stateColor = "green";};
	public GraphNode(String name, int cordinateX, int cordinateY){
		this.name = name;
		stateColor = "green";
		this.cordinateX = cordinateX; // corresponds to i 
		this.cordinateY = cordinateY;// corresponds to j
		
	};
	
	public String getName(){
		
		return name;
	};
	ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();
	
	String name;
	String stateColor;
	int outputState = 0;
	public int cordinateX;
	public int cordinateY;
	void AddNeighbor(GraphNode node){
		neighbors.add(node);
		
	};

}
