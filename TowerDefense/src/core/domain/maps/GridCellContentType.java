package core.domain.maps;

public enum GridCellContentType {
	SCENERY(0), PATH(1), ENTRANCE(2), EXIT(3), TOWER(11);
	private int value;
	
	private GridCellContentType(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
