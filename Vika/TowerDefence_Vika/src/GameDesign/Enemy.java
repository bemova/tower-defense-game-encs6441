package GameDesign;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;


public class Enemy extends MapObject {
	
	int height = 15;
	int width = 15;
	public int picselX = 0; //coordinates relatively to a current rectangle, the same as in a coordinating system => (x,y)
	public int picselY = 0;//coordinates relatively to a current rectangle 
	public int realCoordinatX = 0;
	public int realCoordinatY = 0;
	public int numberOfMaxLifesLeft; 
	
	public double numberOfPicselsPerGivenTime = 0.5; //  0.5 pixel in 30 millisecond as default
	public Date lastUpdateTime;
	
	public int  positionOnMap = 0; // position on the path relatively to the beginning of the path
	
	public Enemy(){};
	public Enemy(int  positionOnMap,double numberOfPicselsPerGivenTime, Date timeCreated ){
		
		this.positionOnMap = positionOnMap; // position on the map in the form that the game player understands
		this.numberOfPicselsPerGivenTime = numberOfPicselsPerGivenTime;
		lastUpdateTime = timeCreated;
	};
	
	@Override
public	void draw(Graphics graphics, Date date){
		// draw an enemy with a given position
	
		graphics.setColor(Color.red);
		graphics.fillRect(realCoordinatX, realCoordinatY, width, height);
	};
}
