package core.domain.maps;

/**
 * @author Team5
 *
 */
public enum GridCellContentType {
	SCENERY(0), PATH(1), ENTRANCE(2), EXIT(3), TOWER(11);
	private int value;
	/**
	 * 
	 * @param value each enum value corresponds to an object
	 */
	private GridCellContentType(int value){
		this.value = value;
	}
	/**
	 * 
	 * @return value which is calling
	 */
	public int getValue() {
		return this.value;
	}
}
