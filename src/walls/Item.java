package walls;

import java.awt.Graphics;

/**
 * Abstract class for any items that might be inside the room
 * 
 * @author HughSa
 *
 */
public abstract class Item {
	protected int startx;
	protected int starty;
	protected int endx;
	protected int endy;
	double[] newCoords; // used to work out where each item is in real
						// life
	protected double length = 0;

	public Item(int startx, int starty, int endx, int endy) {

		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;

	}

	/**
	 * Setting getting the actual coords of the item. As the user can click any
	 * size so we need the actual coordinates
	 * 
	 * @param distance
	 *            The length/distance of the item
	 * @param prevEnd
	 *            The coordinates of the last point of the previous item
	 */
	public void setRealCoords(double prevEnd[]) {

		int diffx = endx - startx;
		int diffy = endy - starty;

		if (diffy == 0) {
			double dist = length * (diffx / Math.abs(diffx));
			double[] newCoords = { prevEnd[0], prevEnd[1], prevEnd[0] + dist, prevEnd[1] };
			prevEnd[0] = prevEnd[0] + dist;
			prevEnd[1] = prevEnd[1];
			setNewCoords(newCoords);
		} else {
			// moving in the y direction
			double dist = length * (diffy / Math.abs(diffy));
			double[] newCoords = { prevEnd[0], prevEnd[1], prevEnd[0], prevEnd[1] + dist };
			prevEnd[0] = prevEnd[0];
			prevEnd[1] = prevEnd[1] + dist;

			setNewCoords(newCoords);
		}

	}

	public abstract void paint(Graphics g);

	public int getStartX() {
		return startx;
	}

	public void setStartX(int x) {
		this.startx = x;
	}

	public int getStartY() {
		return starty;
	}

	public void setStartY(int y) {
		this.starty = y;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getEndx() {
		return endx;
	}

	public void setEndx(int endx) {
		this.endx = endx;
	}

	public int getEndy() {
		return endy;
	}

	public void setEndy(int endy) {
		this.endy = endy;
	}

	public void setEndCoords(int endx, int endy) {
		this.endx = endx;
		this.endy = endy;
	}

	public double[] getNewCoords() {
		return newCoords;
	}

	public void setNewCoords(double[] newCoords) {
		this.newCoords = newCoords;
	}

}
