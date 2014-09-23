package com.towerdefense.map;

import java.util.ArrayList;

public class Grid {

	/**
	 *
	 */
	private int height;
	private int width;
	private ArrayList<ArrayList<Cell>> cells;

	/**
	 * @param height
	 * @param width
	 * @param cells
	 */
	public Grid(int height, int width, ArrayList<ArrayList<Cell>> cells) {
		super();
		this.height = height;
		this.width = width;
		this.cells = cells;
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
	public ArrayList<ArrayList<Cell>> getCells() {
		return cells;
	}

	/**
	 * @param cells
	 */
	public void setCells(ArrayList<ArrayList<Cell>> cells) {
		this.cells = cells;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grid [height=" + height + ", width=" + width + ", cells="
				+ cells + "]";
	}


}
