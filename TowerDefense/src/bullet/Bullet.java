package bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import maps.GridCell;

public abstract class Bullet {

	public enum State {
		MOVING, FINISHED
	}

	State state = State.MOVING;

	public State getState() {
		return state;
	}

	Point startPoint;
	Point endPoint;
	Point currentPoint;
	double movingSpeed = 0; // number of pixels in one millisecond
	GridCell target;

	public final GridCell getTargetCell() {
		return target;
	}

	// parameters for y= ax + b
	double a;
	double b;
	double x1;
	double y1;
	double x2;
	double y2;
	double angle;

	public Bullet(Point start, Point end, GridCell newtarget) {
		startPoint = start;
		endPoint = end;
		currentPoint = start;
		x1 = startPoint.x;
		y1 = startPoint.y;
		x2 = endPoint.x;
		y2 = endPoint.y;

		a = (y2 - y1) / (x2 - x1);
		b = y1 - x1 * ((y2 - y1) / (x2 - x1));
		pathLength = length(startPoint, endPoint);
		target = newtarget;
		angle = Math.atan(a);
	}

	public static double length(Point start, Point end) {
		return Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
	}

	double pathLength = 0.0;

	public void updatePosition(int millisecPassed) {

		x1 = currentPoint.x;
		y1 = currentPoint.y;

		double step = movingSpeed * millisecPassed;

		/*
		 * int buletX = 0; if(startPoint.x > endPoint.x) buletX = (int) (x1 -
		 * (step * Math.cos(angle))); else buletX = (int) (x1 + (step *
		 * Math.cos(angle)));
		 * 
		 * int buletY = 0; if(startPoint.y > endPoint.y) buletY = (int) (y1 +
		 * (step * Math.sin(angle))); else buletY = (int) (y1 - (step *
		 * Math.sin(angle)));
		 */
		int buletX = 0;
		int buletY = 0;
		if (startPoint.x <= endPoint.x && startPoint.y < endPoint.y) {
			buletX = (int) (x1 + (step * Math.cos(angle)));
			buletY = (int) (y1 + (step * Math.sin(angle)));
		}
		else if (startPoint.x > endPoint.x && startPoint.y > endPoint.y)
		{
			buletX = (int) (x1 - (step * Math.cos(angle)));
			buletY = (int) (y1 - (step * Math.sin(angle)));
		}
		else {
			if (startPoint.x > endPoint.x)
				buletX = (int) (x1 - (step * Math.cos(angle)));
			else
				buletX = (int) (x1 + (step * Math.cos(angle)));

			if (startPoint.y > endPoint.y)
				buletY = (int) (y1 + (step * Math.sin(angle)));
			else
				buletY = (int) (y1 - (step * Math.sin(angle)));
		}

		currentPoint = new Point(buletX, buletY);

		if (changeTargetState())
			target.containsBullet = true;

		if (length(startPoint, currentPoint) >= pathLength) {
			state = State.FINISHED;
			target.containsBullet = false;
		}

	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(currentPoint.x, currentPoint.y, 12, 12);
		g.setColor(Color.white);
		g.fillOval(currentPoint.x, currentPoint.y, 10, 10);
		g.setColor(Color.red);
		g.fillOval(currentPoint.x, currentPoint.y, 8, 8);
		g.drawString("{" + startPoint.x + "," + startPoint.y + "} -> {" + endPoint.x + "," + endPoint.y + "}, Grid {"
				+ target.i + "," + target.j + "}", currentPoint.x, currentPoint.y);
	}

	public void setSpeed(double newspeed) {
		movingSpeed = newspeed / 1000;
	}

	public double getSpeed() {
		return movingSpeed;
	}

	public boolean changeTargetState() {
		return false;
	}

}
