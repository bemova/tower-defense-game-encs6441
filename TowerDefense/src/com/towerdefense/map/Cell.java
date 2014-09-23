package com.towerdefense.map;



/**
 * @author Team 5
 *
 */
public class Cell {

	/**
	 *
	 */
	private String image;
	private Object content;

	/**
	 *
	 */
	@SuppressWarnings("unused")
	private Cell(){
		super();
		this.image = null;
		this.content = null;
	}

	/**
	 * @param image
	 * @param content
	 */
	public Cell(String image, Object content) {
		super();
		this.image = image;
		this.content = content;
	}

	/**
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(Object content) {
		this.content = content;
	}
}
