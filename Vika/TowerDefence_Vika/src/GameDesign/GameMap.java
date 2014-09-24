package GameDesign;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class GameMap extends Canvas {

	//Path gamePath; 
	ArrayList<Tower> listOfTowers;
	ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>()  ;
	public ArrayList<Position> processingPatCordinate = new ArrayList<Position>();
	int[][] pathInMatrix = new int[15][15];
	
	int wdth_ = 0; // width and height in number of squares  like in matrix
	int height_ = 0;
	int sizeOfUnit = 30;
	String entryPoint = ""; // entry point coordinate  in a string mode =>"2 0" means x = 2, y = 0
	String exitPoint = "";
	Image image ;
		

	
	Dimension d = new Dimension() ;
	Graphics imageGraphic = null;// ;= new Graphics();
	 
	GameMap(){ // default contructor
		pathInMatrix = new int[height_][wdth_]; //(i,j)
		
		d.setSize(wdth_, height_);
				
	}; // default constructore
	
	GameMap(int height, int width ){ // parametrized contructor
		this.wdth_ = width;
		this.height_ = height;
		pathInMatrix = new int[height][width]; //pathInMatrix[i][j] => 0<i<height  and 0<j<width
		d.setSize(wdth_, height_);
		image = createImage(d.width, d.height);
		//imageGraphic = image.getGraphics();
	};
	
		@Override
	public void paint(Graphics graphics) {
			//update( graphics );
			
	////////////////	drawMapObjects(imageGraphic);

}
		
	@Override	
	
	public void update(Graphics g){
		if(image == null){
		  d.setSize(wdth_, height_);
			d.setSize(15, 15);
			image = createImage(15, 15);
			imageGraphic = image.getGraphics();
		}
		//drawMapObjects(imageGraphic);
	imageGraphic.setColor(Color.red);
	imageGraphic.drawRect(10, 10, 15, 15);
		paint(imageGraphic);
	g.drawImage(image, 0, 0, this );
		
	}
	
		void updatePath(){
			String direction = "";
			Position current = null;
			processingPatCordinate.clear();
			for(int i = 0; i <height_  ; i++)
				for(int j =0; j < wdth_; j++){
					if(pathInMatrix[i][j] == 1){
						if(i == height_ - 1 && j == wdth_ - 1) processingPatCordinate.add(new Position(i, j, "right"));
						else{
						//up, down, right, left
						if( processingPatCordinate.size() == 0 ) {
							processingPatCordinate.add(new Position(i, j, "null")); 
							
						}
						else {	
							current = processingPatCordinate.get(processingPatCordinate.size()-1);
						if(current.i == i && current.j > j) direction = "left";
						else if(current.i == i && current.j < j) direction = "right";
						else if( current.j == j && current.i < i) direction = "down";
						else if( current.j == j && current.i > i) direction = "up";
						current.direction = direction;
						processingPatCordinate.add(new Position(i, j, "null"));
						}
					}
					}
				}
		}
		
	void drawMapObjects(Graphics graphics){
		
		Dimension dimension = getSize();
//---Draw grid---
		for (int i = 0; i < dimension.width; i += sizeOfUnit) {
			for (int j = 0; j < dimension.width; j += sizeOfUnit) {
				graphics.drawLine(i, 0, i, dimension.height);
				graphics.drawLine(0, j, dimension.width, j);
			}
		}

		graphics.drawLine(dimension.width - 1, dimension.height - 1,
				dimension.width - 1, 0); // x1y1 x2y2

		graphics.drawLine(0, dimension.height - 1, dimension.width - 1,
				dimension.height - 1);
//---end draw grid----
		
// --- draw path on the grid----

Iterator<Position> it = processingPatCordinate.iterator();
while(it.hasNext())
{
    Position obj = it.next();
    graphics.setColor(Color.gray);
    graphics.fillRect(obj.j * sizeOfUnit, obj.i * sizeOfUnit, sizeOfUnit, sizeOfUnit);
   
}

Iterator<Enemy> iterator = listOfEnemies.iterator();
while(iterator.hasNext()){
	Enemy enemy;
	Date date = new Date(); ///////////////////////change later
	//iterator.next().draw(graphics, date);
	enemy = iterator.next();
	graphics.setColor(Color.red);
	graphics.fillRect(enemy.realCoordinatX, enemy.realCoordinatY, 15, 15);
}
		
// ---end drawing path---
		

		
	}
}
