package core.domain.maps;

public class GridCell {
	public int i; //  (0,0) is the same as for the canvas 
	public int j; //
	String cellType; // type could be scenary, path, or tower that corresponds to what that particular cell contains

public GridCell( int newi, int newj){
	
	i = newi;
	j = newj;
}
}
