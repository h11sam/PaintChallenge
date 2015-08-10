package walls;

import java.awt.Graphics;

public abstract class Item {
	protected int x;
	protected int y;
	protected double length;
	
	public Item(int x, int y, double length){
		
		this.x = x;
		this.y = y;
		this.length = length;
		
	}

	public abstract void paint(Graphics g);
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	
}
