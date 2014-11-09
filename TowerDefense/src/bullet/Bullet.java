package bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bullet {
	Point startPoint;
	Point endPoint;
	Point currentPoint;
	double speed; // number of pixels in one millisecond 

	double a;
	double b;
	double x1;
	double y1;
	double x2;
	double y2;
	
	public Bullet(Point start, Point end) {
		x1 = startPoint.x;
		y1 = startPoint.y;
		x2 = endPoint.x;
		y2 = endPoint.y;

		a = (y2 - y1) / (x2 - x1);
		b = y1 - x1 * ((y2 - y1) / (x2 - x1));
	}
	
	public void updatePosition(int millisecPassed){
		
		double step = speed * millisecPassed;
		double x1 = currentPoint.x;
		double y1 = currentPoint.y;
		double x2 = endPoint.x;
		double y2 = endPoint.y;

		double a = (y2 - y1) / (x2 - x1);
		double b = y1 - x1 * ((y2 - y1) / (x2 - x1));

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
