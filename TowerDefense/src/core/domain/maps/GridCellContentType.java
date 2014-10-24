package core.domain.maps;

public enum GridCellContentType {
	PATH(1), SCENERY(0), ENTRANCE(2), EXIT(3);
	private int value;
	
	private GridCellContentType(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
