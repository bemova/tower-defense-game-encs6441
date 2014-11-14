package ui.game;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import core.contract.MapConstants;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class Critter extends JComponent implements Runnable {
	public int xC = 0, yC = 0;
	int xPos = 50;
	int x, y;
	Icon critterImg;
	Position[] path;
	Icon pathImg;
	Icon sceneryImg;
	int currPathCell;
	int xStep, yStep;
	//	int cellStep;

	public Critter(int initX, int initY, Position[] path) {
		this.x = initX;
		this.y = initY;
		currPathCell = 0;

		this.xPos = initX;
		this.path = path;

		//		this.path = new Point[15];
		//
		//		path[0] = new Point(0, 2);
		//		path[1] = new Point(1, 2);
		//		path[2] = new Point(2, 2);
		//		path[3] = new Point(3, 2);
		//		path[4] = new Point(4, 2);
		//
		//		path[5] = new Point(4, 3);
		//		path[6] = new Point(4, 4);
		//
		//		path[7] = new Point(5, 4);
		//
		//		path[8] = new Point(5, 5);
		//		path[9] = new Point(5, 6);
		//		path[10] = new Point(5, 7);
		//
		//		path[11] = new Point(6, 7);
		//		path[12] = new Point(7, 7);
		//		path[13] = new Point(8, 7);
		//		path[14] = new Point(0, 2);

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("knight.png").getFile());
		// critterImg = new ImageIcon(file.getPath()).getImage();
		this.critterImg = new ImageIcon(file.getPath());

		file = new File(classLoader.getResource(MapConstants.PATH_IMG)
				.getFile());
		pathImg = new ImageIcon(file.getPath());
		file = new File(classLoader.getResource(MapConstants.SCENERY_IMG)
				.getFile());
		sceneryImg = new ImageIcon(file.getPath());
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return y;
	}

	public void walk() {
		if (currPathCell != path.length) {
			Position currCell = path[currPathCell];
			Position nextCell = path[currPathCell + 1];

			if (currCell.getY() == nextCell.getY()) {
				if(currCell.getX() + 1 == nextCell.getX())
				{
					x+=1;
					xStep+=1;
					if(xStep >= MapConstants.UNIT_SIZE-1){
						xStep=0;
						currPathCell++;
					}

				}else if (currCell.getX() - 1 == nextCell.getX()) {
					x-=1;
					xStep+=1;
					if(xStep >= MapConstants.UNIT_SIZE-1){
						xStep=0;
						currPathCell++;
					}
				}
				
			}
			if(currCell.getX() == nextCell.getX()){
				if(currCell.getY() + 1 == nextCell.getY())
				{
					y+=1;
					yStep+=1;

				}else if (currCell.getY() - 1 == nextCell.getY()) {
					y-=1;
					yStep+=1;
				}
				if(yStep >= MapConstants.UNIT_SIZE-1){
					yStep=0;
					currPathCell++;
				}
			}
		}
	}

	public void run() {

		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			if (xPos > x + 200) {
				xPos = x;
			}
			xPos++;
		}
	}

	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		draw(g);// , xPos, y);

	}

	public void draw(Graphics g) {// , int x, int y){
		super.paintComponent(g);
		// pathImg.paintIcon(this, g, xPos-1, y);
		critterImg.paintIcon(this, g, x, y);

		// g.drawImage(critterImg, xPos, y, MapConstants.UNIT_SIZE,
		// MapConstants.UNIT_SIZE, null);
		// this.y = y;
		// if (x > 1000){
		// x = 100;
		// }
		// x++;

	}
}
