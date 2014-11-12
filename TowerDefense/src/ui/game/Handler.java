package ui.game;

import java.awt.event.*;
import java.awt.*;
public class Handler implements MouseListener, MouseMotionListener{

	MapPanel mapPanel;
	
	public Handler(MapPanel mapPanel){
		this.mapPanel = mapPanel;
	}
	
	public void mouseDragged(MouseEvent e) {
//		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())+((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	
	public void mouseMoved(MouseEvent e) {
//		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())-((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	
	public void mouseClicked(MouseEvent event) {
		int mouseX = event.getX();
		int mouseY = event.getY();
		
		if((mouseX>=mapPanel.getMapTopLeft().getX() && mouseX<=mapPanel.getMapButtomRight().getX()) &&
				(mouseY>=mapPanel.getMapTopLeft().getY() && mouseY<=mapPanel.getMapButtomRight().getY())){
			mapPanel.setCellLocation(mouseX, mouseY);
			mapPanel.towerOperation();
		}
	}

	
	public void mousePressed(MouseEvent e) {
//		Screen.store.click(e.getButton());
		
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
