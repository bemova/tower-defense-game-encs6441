package test;

import java.awt.*;

import javax.swing.JFrame;

public class frame extends Canvas {

	int step = 0;
	Point end = new Point(100, 200);
	Point start = new Point(550, 440);
	
	public static void main(String[] args) {
		final JFrame f = new JFrame();
		final frame c = new frame();
		c.setSize(600, 600);
		f.add(c);
		f.pack();
		c.setVisible(true);
		c.setBackground(Color.BLUE);
		f.setVisible(true);

		Thread thread = new Thread() {
			public void run() {
				
				int length = c.lineLength(c.start, c.end);
				for (int i = 0; i < length; i++) {
					try {
						c.step = i;
						c.repaint();
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();
		
		f.invalidate();
		
		
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(start.x, start.y, 10, 10);
		g.setColor(Color.red);
		g.fillOval(end.x, end.y, 10, 10);
		g.setColor(Color.white);
		g.drawLine(start.x, start.y, end.x, end.y);
		
		drawBullet(g, start, end, step);

	}

	int lineLength(Point start, Point end) {
		double sideA = Math.abs(start.x - end.x);
		double sideB = Math.abs(start.y - end.y);
		return (int) Math.sqrt(sideA * sideA + sideB * sideB);
	}

	/*
	 * 
	 * y = x * tan(a) + b
	 * 
	 * 
	 * x2 = x1 + (L * cos(a)) y2 = y1 + (L * sin(a))
	 */

	void drawBullet(Graphics g, Point start, Point end, int step) {
		System.out.println("step " + step);
		double x1 = start.x;
		double y1 = start.y;
		double x2 = end.x;
		double y2 = end.y;

		double a = (y2 - y1) / (x2 - x1);
		double b = y1 - x1 * ((y2 - y1) / (x2 - x1));

		double angle = Math.atan(a);

		int buletX = 0;
		if(start.x > end.x)
			buletX = (int) (x1 - (step * Math.cos(angle)));
		else
			buletX = (int) (x1 + (step * Math.cos(angle)));
			
		int buletY = 0;
		if(start.y > end.y)
			buletY = (int) (y1 - (step * Math.sin(angle)));
		else
			buletY = (int) (y1 + (step * Math.sin(angle)));

		g.setColor(Color.black);
		g.fillOval(buletX, buletY, 10, 10);
		System.out.println("{" + buletX + ", " + buletY);
	}
}

