package ui.game;

import java.awt.Graphics;

import javax.swing.JComponent;

import core.domain.waves.Position;

public class LineBullet extends JComponent {
	
	public LineBullet(){
	}
	
	public void draw(Graphics g, Position source, Position target){
		super.paintComponent(g);
		g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
	}
}
