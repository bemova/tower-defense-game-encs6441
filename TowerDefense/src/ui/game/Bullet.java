package ui.game;

import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import core.contract.MapConstants;

public class Bullet extends JComponent implements Runnable {
	public int xC=0,yC=0;
	int xPos = 50;
	int x,y;
	Icon critterImg;

	Icon pathImg;
	
	public Bullet(int initX, int initY){
		this.x = initX;
		this.y = initY;
		this.xPos = initX;
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("bullet.png").getFile());
//		critterImg = new ImageIcon(file.getPath()).getImage();
		this.critterImg = new ImageIcon(file.getPath());
		
		file = new File(classLoader.getResource(MapConstants.PATH_IMG).getFile());
		pathImg = new ImageIcon(file.getPath());
	}
	
	public void physic(){
		if (xPos > x+500){
			xPos = x;
		}
		xPos++;
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
			if (xPos > x+200){
				xPos = x;
			}
			xPos++;
		}
	}
	
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		draw(g);//, xPos, y);
		
	}
	
	public void draw(Graphics g){//, int x, int y){
		super.paintComponent(g);
//		pathImg.paintIcon(this, g, xPos-1, y);
		critterImg.paintIcon(this, g, xPos, y);
		
//		g.drawImage(critterImg, xPos, y, MapConstants.UNIT_SIZE,
//				MapConstants.UNIT_SIZE, null);
//		this.y = y;
//		if (x > 1000){
//			x = 100;
//		}
//		x++;
		
	}
}
