package walls;

import java.awt.Graphics;

public class Wall extends Item {

	public Wall(int startx, int starty, int endx,int endy) {
		super(startx,starty,endx,endy);
	}

	@Override
	public void paint(Graphics g) {
		g.drawLine(startx, starty, endx, endy);

	}

}
