import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int testingX = 10 ;
		int testingY = 7;
		
		
		MapDesign myMap = new MapDesign();
		GeneralAlgorithms shortestPath = new GeneralAlgorithms();
		int[][] mapInMatric = new int[testingX][testingY];
		
		myMap.buildMap(testingX, testingY);
		
	//	HashMap<String, GraphNode> iterator = new	HashMap<String, GraphNode>();
		
		for(String keyElement: myMap.GraphMap.keySet()){
			 
			System.out.println("---------" + keyElement + "--------");
			ArrayList<GraphNode> arrayList = myMap.GraphMap.get(keyElement).neighbors;
			for (int i = 0; i < arrayList.size(); i++) {
				System.out.println(keyElement + "  " + arrayList.get(i).name 	);
			}
			
		}
		
		//output path if exists
				shortestPath.shortestPathfinding(myMap.GraphMap, "10", "86");
		
		//============output into matrix
		for(int i = 0; i< testingX; i++)
			for(int j = 0; j < testingY; j++){
				
				mapInMatric[i][j] = myMap.GraphMap.get(Integer.toString(i) + Integer.toString(j)).outputState;
				
			}
		
		//=============================
		
		
		//=============output into file=====
		
		try{
			PrintWriter out = new PrintWriter("C:\\Users\\vika\\Desktop\\1111111111.txt");
			//OutputStream os = new FileOutputStream("Desktop\\test.txt");
			for(int i = 0; i< testingX; i++){
				String line = ""; 
				for(int j = 0; j < testingY; j++){
					line = line + mapInMatric[i][j]; 
									
					
				}
			out.println(line);
			}
			out.close();
		
		}
		catch(IOException e){
			System.out.print("Exception");

		}
		
		//=================================
		

		
	}

}
