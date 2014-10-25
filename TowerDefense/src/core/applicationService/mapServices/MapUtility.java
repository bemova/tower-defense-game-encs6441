package core.applicationService.mapServices;

import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class MapUtility {
	public Position getEnter(GridCellContentType[][] map){
		try {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == GridCellContentType.ENTRANCE)
						return new Position(i, j);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Position getExit(GridCellContentType[][] map){
		try {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == GridCellContentType.EXIT)
						return new Position(i, j);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
