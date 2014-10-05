package GameContraller;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import GameElements.EmptyGrid;
import GameElements.Grid;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import GameContraller.*;

public class MapManager {

	public StandardAlgorithms algorithms = null;
	
	public MapManager(){
		
		
		
	}
	
	
public String validateMapContent(Grid grid){
	String returnVlue = "";

	algorithms = new StandardAlgorithms(grid);
	
	return returnVlue = algorithms.mapManager();
	
};

public Grid LoadMapFromFile(String fileName){
	Grid grid = null;  
	int wdth_ = 0;
	int height_ = 0;
	int[][] pathInMatrix = new int[15][15];
	
    BufferedReader br = null;
    ArrayList<String> containerForMap = new ArrayList<String>();
    
    try {
    	br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();
        
        while (line != null) {
        	containerForMap.add(line);
            line = br.readLine();
        }
        
        
        String[] parts =  containerForMap.get(0).split(" ");
        wdth_ = parts.length;
        height_ = containerForMap.size();
        grid = new EmptyGrid(height_, wdth_);
        
        pathInMatrix = new int [height_][wdth_];
        grid.content = pathInMatrix;
        
        int i = 0;
        for(String lineComponent : containerForMap)
        {
        	parts = lineComponent.split(" ");
        	for(int j = 0; j<wdth_; j++)
        		pathInMatrix[i][j] =Integer.parseInt( parts[j]);
        	i++;
        }
        
        //  String everything = sb.toString();
        br.close();
    } catch(FileNotFoundException e){
    	System.out.print("Exception");
    	
    }
    catch(IOException e){
    	
    	System.out.print("Exception");
    }
return grid;
	
};


public String SaveMapIntoFle(Grid grid, String FileName){
	
	int widht = grid.getWidth();
	int height = grid.getHeight();
	String errorMessage = "";
	
	try {
		
	//	errorMessage = validateEntryExit(grid);
		errorMessage = validateMapContent(grid);
		
		PrintWriter out = new PrintWriter(FileName );

		for (int i = 0; i < height; i++) {
			String line = "";
			for (int j = 0; j < widht; j++) {
				if(j < widht -1)
					line = line + grid.content[i][j] + " ";
				else 
					line = line + grid.content[i][j];	

			}
			out.println(line);
		}
		
	
		out.close();

	} catch (IOException e) {
		System.out.print("Exception:  " + errorMessage);

	}
	
	return errorMessage;
}

public String validateEntryExit(Grid grid){
	String  message = "";
	int numberOfEntry = 0;
	int numberOfExit = 0;
	int numberofEmptyCells = 0;
	int numberOfTowers = 0;
	for(int i = 0; i < grid.getHeight(); i++)
		for(int j =0; j < grid.getWidth(); j++){
			if(grid.content[i][j] == 0) numberofEmptyCells++;
			else if(grid.content[i][j] == 3) numberOfEntry++;
			else if(grid.content[i][j] == 4) numberOfExit++;
			else if(grid.content[i][j] == 5) numberOfTowers++;
	
		}
	if( numberofEmptyCells >0) message = "Invalid Map: not all the cells are covered";
	else if( numberOfEntry > 1 || numberOfEntry == 0) message = "Invalis Map: Wrong number of entry points";
	else if(numberOfExit > 1 || numberOfExit == 0) message = "Invalis Map: Wrong number of exit points";
	//else if(numberOfTowers == 0) message = "invalid Map: not acceptable number of Towers";
	
	
	return message;
}


}
