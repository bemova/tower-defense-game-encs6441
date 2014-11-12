package ui.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

import core.contract.MapConstants;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.defenders.towers.Tower;

public class Cell extends Rectangle {

	private ClassLoader classLoader = getClass().getClassLoader();
	private File file;
	private Image sceneryImg, pathImg, enteranceImg, exitImg;
	private Image modernTowerImg, ancientTowerImg, kingTowerImg;
	
	public Cell(){
//		setup();
		setupImages();
	}

	private void setupImages(){
		file = new File(classLoader.getResource(MapConstants.SCENERY_IMG).getFile());
		sceneryImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.PATH_IMG).getFile());
		pathImg = new ImageIcon(file.getPath()).getImage();
		
		file = new File(classLoader.getResource(MapConstants.ENTRANCE_IMG).getFile());
		enteranceImg = new ImageIcon(file.getPath()).getImage();
		
		file = new File(classLoader.getResource(MapConstants.EXIT_IMG).getFile());
		exitImg = new ImageIcon(file.getPath()).getImage();
		
		
		file = new File(classLoader.getResource(MapConstants.MODERN_TOWER_IMG).getFile());
		modernTowerImg = new ImageIcon(file.getPath()).getImage();
		
		file = new File(classLoader.getResource(MapConstants.ANCIENT_TOWER_IMG).getFile());
		ancientTowerImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.KING_TOWER_IMG).getFile());
		kingTowerImg = new ImageIcon(file.getPath()).getImage();
	}
	
	
	
	
	
	public void draw(Graphics g, GridCellContentType cellType, Tower[][] towers, int x, int y, int gridX, int gridY) {
		Image image = setup(cellType, towers, gridX, gridY);
		
		//draw scenery
		file = new File(classLoader.getResource(MapConstants.SCENERY_IMG).getFile());
		Image sImage = new ImageIcon(file.getPath()).getImage();
		g.drawImage(sImage, x, y, MapConstants.UNIT_SIZE,
				MapConstants.UNIT_SIZE, null);
		//end 
		
		g.drawImage(image, x, y, MapConstants.UNIT_SIZE,
				MapConstants.UNIT_SIZE, null);
	}

	private Image setup(GridCellContentType cellType, Tower[][] towers, int x, int y) {
		switch (cellType) {
		case SCENERY:
			return sceneryImg;
		case PATH:
			return pathImg;
		case ENTRANCE:
			return enteranceImg;
		case EXIT:
			return exitImg;
		case TOWER:
			switch(towers[x][y].display()){
			case MapConstants.MODERN_TOWER_IMG:
				return modernTowerImg;
			case MapConstants.ANCIENT_TOWER_IMG:
				return ancientTowerImg;
			case MapConstants.KING_TOWER_IMG:
				return kingTowerImg;
			default:
				return modernTowerImg;
			}
		default:
			return sceneryImg;
		}

	}	
	
	
//	private Image setup(GridCellContentType cellType, Tower[][] towers, int x, int y) {
//		String imageFileName;
//		Image image = null;
//		switch (cellType) {
//		case SCENERY:
//			imageFileName = MapConstants.SCENERY_IMG;
//			break;
//		case PATH:
//			imageFileName = MapConstants.PATH_IMG;
//			break;
//		case ENTRANCE:
//			imageFileName = MapConstants.ENTRANCE_IMG;
//			break;
//		case EXIT:
//			imageFileName = MapConstants.EXIT_IMG;
//			break;
//		case TOWER:
//			imageFileName = towers[x][y].display();
//			break;
//		default:
//			imageFileName = MapConstants.SCENERY_IMG;
//		}
//
//		file = new File(classLoader.getResource(imageFileName).getFile());
//		image = new ImageIcon(file.getPath()).getImage();
//
//		return image;
//	}

}
