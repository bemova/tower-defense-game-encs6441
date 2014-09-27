package GameDesign;

public class MapManager {

	int [][]map = null;
	int sizeX = 0;
	int sizeY = 0;
	public MapManager(){
		map = new int[sizeX][sizeY];
		
	}
	
	public MapManager( int sizeX, int sizeY, int [][] map){
		this.map = new int[sizeX][sizeY];
		this.map = map;
		
	}
	
	String validateEntryExit(){
		String  message = "";
		int numberOfEntry = 0;
		int numberOfExit = 0;
		int numberofEmptyCells = 0;
		int numberOfTowers = 0;
		for(int i = 0; i <= sizeX; i++)
			for(int j =0; j <= sizeY; j++){
				if(map[i][j] == 0) numberofEmptyCells++;
				else if(map[i][j] == 3) numberOfEntry++;
				else if(map[i][j] == 4) numberOfExit++;
				else if(map[i][j] == 5) numberOfTowers++;
		
			}
		if( numberofEmptyCells >0) message = "Invalid Map: not all the cells are covered";
		else if( numberOfEntry > 1 || numberOfEntry == 0) message = "Invalis Map: Wrong number of entry points";
		else if(numberOfExit > 1 || numberOfExit == 0) message = "Invalis Map: Wrong number of exit points";
		else if(numberOfTowers == 0) message = "invalid Map: not acceptable number of Towers";
		
		
		return message;
	}
	
}
