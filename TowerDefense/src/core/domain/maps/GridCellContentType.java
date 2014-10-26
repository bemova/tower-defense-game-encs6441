package core.domain.maps;

public enum GridCellContentType {
	SCENERY(0), PATH(1), ENTRANCE(2), EXIT(3), TOWER1(11), TOWER2(12), TOWER3(13);
	private int value;
	
	private GridCellContentType(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
