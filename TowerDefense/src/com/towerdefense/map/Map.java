package com.towerdefense.map;

/**
 * @author Team 5
 *
 */
public class Map {

	private int height;
	private int width;
	private Grid path;
	private Grid towers;

	/**
	 * @param height
	 * @param width
	 * @param path
	 * @param towers
	 */
	public Map(int height, int width, Grid path, Grid towers) {
		super();
		this.height = height;
		this.width = width;
		this.path = path;
		this.towers = towers;
	}


	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}


	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return
	 */
	public Grid getPath() {
		return path;
	}


	/**
	 * @param path
	 */
	public void setPath(Grid path) {
		this.path = path;
	}


	/**
	 * @return
	 */
	public Grid getTowers() {
		return towers;
	}


	/**
	 * @param towers
	 */
	public void setTowers(Grid towers) {
		this.towers = towers;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [height=" + height + ", width=" + width + ", path=" + path
				+ ", towers=" + towers + "]";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
