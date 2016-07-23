package walls;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class for holding information and displaying a wall
 * 
 * @author HughSa
 *
 */
public class Wall extends Item {

	// Whether or not this wall is being highlighted red or not.
	private boolean selected = false;

	public Wall(int startx, int starty, int endx, int endy) {
		super(startx, starty, endx, endy);
	}

	public double getArea(){
		return (newCoords[2] - newCoords[0]) * newCoords[1];
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		if (selected) {
			g.setColor(Color.red);
		}
		g.drawLine(startx, starty, endx, endy);

		// If we already have the length we then put the length in middles of
		// the line
		if (length > 0) {
			int x = ((endx - startx) / 2) + startx + 2;
			int y = ((endy - starty) / 2) + starty - 2;
			g.drawString("" + length, x, y);
		}
		g.setColor(c);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
