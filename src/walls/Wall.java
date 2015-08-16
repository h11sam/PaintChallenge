package walls;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Item {

	private boolean selected = false;
	
	public Wall(int startx, int starty, int endx,int endy) {
		super(startx,starty,endx,endy);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		if(selected){
			g.setColor(Color.red);
		}
		g.drawLine(startx, starty, endx, endy);

		if(length > 0){
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
