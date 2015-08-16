package walls;

import java.awt.Graphics;

public abstract class Item {
	protected int startx;
	protected int starty;
	protected int endx;
	protected int endy;
	private double[] newCoords; // used to work out where each item is in real life
	protected double length = 0;
	
	public Item(int startx, int starty, int endx, int endy){
		
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		
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
	
	public void setEndCoords(int endx,int endy){
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
