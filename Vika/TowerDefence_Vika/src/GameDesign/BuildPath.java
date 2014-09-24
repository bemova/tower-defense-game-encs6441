package GameDesign;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BuildPath {
	

	

	

		int testingX = 0; // width and height for testing purpose in squares 
		int testingY = 0;
		
		HashMap<String, GraphNode> GraphMap = new HashMap<String, GraphNode>(); // contains the map structure 
		
		int[][] mapInMatric =  new int[testingX][testingY];// 
		
		BuildPath(){
			mapInMatric =  new int[testingX][testingY];
		}; // default contructor
		
		BuildPath(int width, int height){
			testingX = height;
			testingY = width;
			mapInMatric = new int[testingX][testingY];
		};
		

		void shortestPathfinding(String source, String destination) {
			
			Stack<GraphNode> nodeStack = new Stack<GraphNode>();
			Stack<GraphNode> nodePath = new Stack<GraphNode>();
			boolean pathIsFound = false;
			String green = "green"; // is not processed yet
			String yellow = "yellow"; // is in a stack
			String blo = "blo"; // neighbors in a stack
			String red = "red";// processing is done

			// nodePath.push(source);
			nodeStack.push(GraphMap.get(source));
			GraphMap.get(source).stateColor = yellow;

			while (!nodeStack.empty()) { // pass over the graph nodes until the
											// destination is find or there are no
											// more nodes left in the stack
				if (nodeStack.peek().name.equals(destination)) { // if we have the
																	// same source
																	// and
																	// destination
																	// th...
					pathIsFound = true;
					nodePath.add(nodeStack.peek());
					break;

				} else {
					GraphNode processingNode = nodeStack.peek();
					if (processingNode.stateColor.equals(yellow)) { // if the
																	// neighbors are
																	// not in the
																	// stack yet

						nodePath.push(processingNode); // add current processing
														// node into the stack as
														// part of a path
						processingNode.stateColor = blo;
						Iterator<GraphNode> it = processingNode.neighbors
								.iterator();
						int insertedNeighbers = 0;
						while (it.hasNext()) {
							GraphNode currentNaighbor = it.next();
							if (currentNaighbor.stateColor.equals(green)) {
								insertedNeighbers++;
								currentNaighbor.stateColor = yellow;
								nodeStack.push(currentNaighbor);
							}
						}

						if (insertedNeighbers == 0) {

							processingNode.stateColor = red;
							if (!nodePath.empty())
								nodePath.pop();
							if (!nodeStack.empty())
								nodeStack.pop();

						}
					} else if (processingNode.stateColor.equals(blo)
							|| processingNode.stateColor.equals(red)) {
						if (!nodePath.empty() && !nodeStack.empty()) {
							nodePath.pop();
							nodeStack.pop();
						} else {

							System.out.println("Stack is empty");
						}

						processingNode.stateColor = red;// change the color so that
														// it is clear the
														// processing is already
														// done with this node

					}

				}
			} // close while

			if (pathIsFound) {
				System.out.println("=========================================1");
				while (!nodePath.empty()) {
					System.out.println("===========================2");
					System.out.println(nodePath.peek());
					nodePath.peek().outputState = 1;
					nodePath.pop();
				}
			}

		}

		void Start(String fromNode,String  toNode, int resultPathInMatrix[][]) { // starts running algorithm

	//		this.testingY = yy;
	//		this.testingX = xx;
			
			fillGraph();

		//	shortestPathfinding(xx, yy);
			
			// matrix to keep the path, if the square participates in a path=> it has
			// a value of 1


			for (String keyElement : GraphMap.keySet()) {

				System.out.println("---------" + keyElement + "--------");
				ArrayList<GraphNode> arrayList = GraphMap.get(keyElement).neighbors;
				for (int i = 0; i < arrayList.size(); i++) {
					System.out.println(keyElement + "  " + arrayList.get(i).name);
				}

			}

			// output path if exists
			shortestPathfinding(fromNode, toNode);

			// ============output into matrix
			for (int i = 0; i < testingX; i++)
				for (int j = 0; j < testingY; j++) {

					resultPathInMatrix[i][j] = GraphMap.get(Integer.toString(i) + " " 
							+ Integer.toString(j)).outputState;

				}

			// =============================

			// =============output into file=====

			try {
				PrintWriter out = new PrintWriter(
						"C:\\Users\\vika\\Desktop\\1111111111.txt");
				// OutputStream os = new FileOutputStream("Desktop\\test.txt");
				for (int i = 0; i < testingX; i++) {
					String line = "";
					for (int j = 0; j < testingY; j++) {
						line = line + resultPathInMatrix[i][j];

					}
					out.println(line);
				}
				out.close();

			} catch (IOException e) {
				System.out.print("Exception");

			}

			// =================================

		}
		
		
		
		void fillGraph(){ // based on the size, creates a Graph structure

			int sizeX = testingX; //width  and height 
			int sizeY = testingY;
				for (int i = 0; i <= sizeX - 1; i++) {
					for (int j = 0; j <= sizeY - 1; j++) {

						String curentNode = Integer.toString(i) + " "  + Integer.toString(j);
						if (GraphMap.get(curentNode) == null)
							GraphMap.put(curentNode, new GraphNode(curentNode, i, j));

						GraphNode universalGrafNode;
						if (i - 1 >= 0) {
							if ((universalGrafNode = GraphMap.get(Integer
									.toString(i - 1) + " "  + Integer.toString(j))) != null) {
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);

							} else {

								universalGrafNode = new GraphNode(
										Integer.toString(i - 1) + " "  + Integer.toString(j), i-1, j);
								GraphMap.put(universalGrafNode.name, universalGrafNode);
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);
							}
						}

						if (j - 1 >= 0) {
							if ((universalGrafNode = GraphMap.get(Integer.toString(i) + " " 
									+ Integer.toString(j - 1))) != null) {
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);
							} else {
								universalGrafNode = new GraphNode(Integer.toString(i) + " " 
										+ Integer.toString(j - 1), i, j-1);
								GraphMap.put(universalGrafNode.name, universalGrafNode);
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);
							}

						}

						if (i + 1 < sizeX) {
							if ((universalGrafNode = GraphMap.get(Integer
									.toString(i + 1) + " "  + Integer.toString(j))) != null) {
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);

							} else {

								universalGrafNode = new GraphNode(
										Integer.toString(i + 1) + " "  + Integer.toString(j), i+1, j);
								GraphMap.put(universalGrafNode.name, universalGrafNode);
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);
							}

						}

						if (j + 1 < sizeY) {

							if ((universalGrafNode = GraphMap.get(Integer
									.toString(i) + " "  + Integer.toString(j+1))) != null) {
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);

							} else {

								universalGrafNode = new GraphNode(Integer.toString(i) + " " 
										+ Integer.toString(j +1), i, j+1);
								GraphMap.put(universalGrafNode.name, universalGrafNode);
								GraphMap.get(curentNode).neighbors
										.add(universalGrafNode);
							}

						}

					}
				}

		
		}

	}


