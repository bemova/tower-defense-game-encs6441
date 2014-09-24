package GameDesign;

public class Position {
public	int i; // a[i][j] => 
	public int j;
	public String direction; // value should be up, down, right, left
	
	public Position(){};
	public Position(int i, int j, String direction){
		
		this.i = i; 
		this.j = j;
		this.direction = direction ;
	};
	
	static Position ToPosition(String position)
	{
		String parts[] = position.split(" ");
		return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), "");
	}
}
