package ui.game;

import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import core.contract.MapConstants;

public class LineBullet extends JComponent implements Runnable {
	public int xC=0,yC=0;
	int xt,yt;
	int xc,yc;
	Icon critterImg;

	Icon pathImg;
	
	public LineBullet(int xt, int yt){
		this.xt = xt;
		this.yt = yt;
		
	}
	
	public void physic(){
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
		}
	}
	
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		draw(g);//, xPos, y);
		
	}
	
	public void draw(Graphics g, int xc, int yc){
		super.paintComponent(g);
		g.drawLine(xt, yt, xc, yc);
	}
}
