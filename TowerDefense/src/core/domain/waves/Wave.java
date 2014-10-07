package core.domain.waves;

import java.util.ArrayList;
import java.util.List;

import core.domain.warriors.aliens.Critter;

public class Wave {
	public List<Critter> aliens;
	public Position headPosition;
	public Wave(int x, int y) {
		headPosition = new Position(x, y);
		aliens = new ArrayList<>();
	}

}
