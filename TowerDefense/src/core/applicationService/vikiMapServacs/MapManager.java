package core.applicationService.vikiMapServacs;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import core.domain.maps.EmptyGrid;
//import core.domain.maps.Grid;
import core.domain.maps.Grid2;
import core.domain.maps.GridCellContentType;
import core.domain.maps.VisualGrid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import core.applicationService.vikiMapServacs.StandardAlgorithms;


public class MapManager {

	public StandardAlgorithms algorithms = null;
	
	public MapManager(){
		
		
		
	}
	
	
public String validateMapContent(Grid2 grid){
	String returnVlue = "";

//	algorithms = new StandardAlgorithms(grid);
	return "";
//	return returnVlue = algorithms.mapManager();
	
};

public Grid2 LoadMapFromFile(String fileName){
	Grid2 grid = null;  
	int wdth_ = 0;
	int height_ = 0;
	GridCellContentType[][] pathInMatrix = new GridCellContentType[15][15];
	
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
//        grid = new EmptyGrid(height_, wdth_);
        grid = new VisualGrid(wdth_, height_);
        
//        pathInMatrix = new GridCellContentType [height_][wdth_];
//        grid.content = pathInMatrix;
        
        int i = 0;
        for(String lineComponent : containerForMap)
        {
        	parts = lineComponent.split(" ");
        	for(int j = 0; j<wdth_; j++){
        		switch(Integer.parseInt( parts[j])){
        		case 1:
//        			pathInMatrix[i][j] = GridCellContentType.PATH;
        			grid.setCell(i, j, GridCellContentType.PATH);
        			break;
        		case 2:
//        			pathInMatrix[i][j] = GridCellContentType.ENTRANCE;
        			grid.setCell(i, j, GridCellContentType.ENTRANCE);
        			break;
        		case 3:
//        			pathInMatrix[i][j] = GridCellContentType.EXIT;
        			grid.setCell(i, j, GridCellContentType.EXIT);
        			break;
        		default:
//        			pathInMatrix[i][j] = GridCellContentType.SCENERY;
        			grid.setCell(i, j, GridCellContentType.SCENERY);
        		}
        	}
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


public String SaveMapIntoFle(Grid2 grid, String FileName){
	
	int widht = grid.getWidth();
	int height = grid.getHeight();
	String errorMessage = "";
	
	try {
		
	//	errorMessage = validateEntryExit(grid);
//		errorMessage = validateMapContent(grid);
		
		PrintWriter out = new PrintWriter(FileName );
		for (int y = 0; y < height; y++) {
		
			String line = "";
			for (int x = 0; x < widht; x++) {	
//				if(x < widht -1)
					line = line + grid.getCell(x,y).getValue() + " ";
//				else 
//					line = line + grid.getCell(x,y).getValue();	

			}
			line = line.substring(0, line.length()-1);
			out.println(line);
		}
		
	
		out.close();

	} catch (IOException e) {
		System.out.print("Exception:  " + errorMessage);

	}
	
	return errorMessage;
}

public String validateEntryExit(Grid2 grid){
/*	String  message = "";
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
	
	
	return message;*/
	return "";
}


}
