package walls;

import java.awt.Graphics;

public class Window extends Item {

	private double height;
	
	public Window(int x, int y, double length,double height) {
		super(x, y, length);
		this.setHeight(height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
