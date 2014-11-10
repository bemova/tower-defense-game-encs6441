package core.domain.maps;

import java.awt.Point;
import java.util.Observable;

public class GridCell extends Observable{
	public int i; //  (0,0) is the same as for the canvas 
	public int j; //
	int cellType; // type could be scenary, path, or tower that corresponds to what that particular cell contains
	private boolean containsCritter;

public GridCell( int newi, int newj){
	
	i = newi;
	j = newj;
	containsCritter = false;
}

public Point returnPosition(){
	
	return new Point(j,i);
}

public void  changeState(Boolean newState){
	
	containsCritter = newState;
	if( containsCritter){
			setChanged();
			notifyObservers();
	}
}
}
