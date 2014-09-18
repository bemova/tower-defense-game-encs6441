import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class GeneralAlgorithms {

void shortestPathfinding(HashMap<String, GraphNode> GraphMap, String source, String destination){
	 Stack<GraphNode> nodeStack = new Stack<GraphNode>();
	 Stack<GraphNode> nodePath = new Stack<GraphNode>();
	 boolean pathIsFound = false;
	 String green = "green"; //is not processed yet
	 String  yellow =  "yellow"; // is in a stack
	 String blo  = "blo"; // neighbors in a stack
	 String  red = "red";// processing is done
	 
	 
	// nodePath.push(source);
	 nodeStack.push(GraphMap.get(source));
	 GraphMap.get(source).stateColor = yellow;
	 
	
	while(! nodeStack.empty()){  // pass over the graph nodes until the destination is find or there are no more nodes left in the stack
	 if(nodeStack.peek().name.equals(destination)){ // if we have the same source and destination th...
		 pathIsFound = true;
		 nodePath.add(nodeStack.peek());
		 break;
		 
	 }else{
		 	GraphNode processingNode = nodeStack.peek();
		if( processingNode.stateColor.equals(yellow)){ // if the neighbors are not in the stack yet
			
			nodePath.push(processingNode); // add current processing node into the stack as part of a path
			processingNode.stateColor = blo;
			Iterator<GraphNode> it = processingNode.neighbors.iterator();
			int insertedNeighbers = 0;
			while(it.hasNext())
			{
				GraphNode currentNaighbor = it.next();
					if(currentNaighbor.stateColor.equals(green)){
						insertedNeighbers++;
						currentNaighbor.stateColor = yellow;
					nodeStack.push(currentNaighbor);
					}
			}
			
			if(insertedNeighbers == 0){
				
				processingNode.stateColor = red;
				if( !nodePath.empty()) nodePath.pop();
				if( ! nodeStack.empty()) nodeStack.pop();
				
			}
		}else if(processingNode.stateColor.equals(blo) || processingNode.stateColor.equals(red)){
			if( !nodePath.empty() && ! nodeStack.empty()){ 
				nodePath.pop(); 
				nodeStack.pop();
			}else {
				
				System.out.println("Stack is empty");
			}
			
			processingNode.stateColor = red;// change the color so that it is clear the processing is already done with this node 
			
			
		}
		 
	 }
	} // close while
	
	if(pathIsFound)
	{
		System.out.println("=========================================1");
		while(! nodePath.empty())
			{
			System.out.println("===========================2");
			System.out.println(nodePath.peek());
			nodePath.peek().outputState = 1;
		nodePath.pop();
			}
	}
	
}



}
