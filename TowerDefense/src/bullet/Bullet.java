package bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bullet {
	Point startPoint;
	Point endPoint;
	Point currentPoint;
	double speed = 15.0 / 1000; // number of pixels in one millisecond 

	// parameters for y= ax + b
	double a; 
	double b;
	double x1;
	double y1;
	double x2;
	double y2;
	
	public Bullet(Point start, Point end) {
		startPoint = start;
		endPoint = end;
		currentPoint = start;
		x1 = startPoint.x;
		y1 = startPoint.y;
		x2 = endPoint.x;
		y2 = endPoint.y;

		a = (y2 - y1) / (x2 - x1);
		b = y1 - x1 * ((y2 - y1) / (x2 - x1));
	}
	
	public void updatePosition(int millisecPassed){
	
		x1 = currentPoint.x;
		y1 = currentPoint.y;
		
		double step = speed * millisecPassed;
		
		double angle = Math.atan(a);

		int buletX = 0;
		if(startPoint.x > endPoint.x)
			buletX = (int) (x1 - (step * Math.cos(angle)));
		else
			buletX = (int) (x1 + (step * Math.cos(angle)));
			
		int buletY = 0;
		if(startPoint.y > endPoint.y)
			buletY = (int) (y1 - (step * Math.sin(angle)));
		else
			buletY = (int) (y1 + (step * Math.sin(angle)));
		
		currentPoint = new Point(buletX, buletY);
		
		if(Math.abs(currentPoint.x - endPoint.x) < 5 && Math.abs(currentPoint.y - endPoint.y) < 5)
			currentPoint = startPoint;
	}
		
	public void draw(Graphics g){ 
		g.setColor(Color.black);
		g.fillOval(currentPoint.x, currentPoint.y, 12, 12);
		g.setColor(Color.white);
		g.fillOval(currentPoint.x, currentPoint.y, 10, 10);
		g.setColor(Color.red);
		g.fillOval(currentPoint.x, currentPoint.y, 8, 8);
	}

}
