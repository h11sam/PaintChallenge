package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import walls.Wall;

public class Panel extends JPanel {

	private static final int snapVal = 10;
	boolean ctrlPress = false;
	boolean xory = true; // true if x, false is y direction
	int startx = 0;
	int starty = 0;
//	int endx = 0;
//	int endy = 0;
	Wall currentWall;
	ArrayList<Wall> walls = new ArrayList<Wall>();

	public Panel() {

		super();
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.red);
		setOpaque(true);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(new MyKeyListener());
		setFocusable(true);
		currentWall = new Wall(0, 0, 0, 0);
	}

	public void paint(Graphics g) {
		paintComponent(g);
		Color c = g.getColor();
		g.setColor(Color.white);
		currentWall.paint(g);
		walls.stream().forEach(wall -> {
			wall.paint(g);
		});
		// g.drawLine(startx, starty, endx, endy);
		g.setColor(c);
	}

	public class Mouse implements MouseListener, MouseMotionListener {

		int pc = 0; // positive gradient
		int nc = 0; // negative gradient
		boolean drawing = false; // are we currently drawing the shape of the
									// room

		@Override
		public void mouseClicked(MouseEvent e) {

			if (drawing) {
				// adding in another wall to the list
				walls.add(currentWall);
				int startx = currentWall.getEndx();
				int starty = currentWall.getEndy();
				currentWall = new Wall(startx, starty, startx, starty);

			} else {
				int x = e.getX();
				int y = e.getY();
				startx = x;
				starty = y;
				drawing = true;
				System.out.println("Mouse pressed " + e.getX() + "," + e.getY());
				currentWall.setStartX(x); // TODO shorten this to one method
				currentWall.setStartY(y);
				// startx = e.getX();
				// starty = e.getY();
				pc = y - x;
				nc = x + x;

			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (drawing) {
				moveCurrentLine(e);
			}

		}

		private void moveCurrentLine(MouseEvent e) {
			// System.out.println("Mouse moved " + e.getX() + "," + e.getY());
			int x = e.getX();
			int y = e.getY();
			setFocusable(true);

			if (Math.abs(currentWall.getStartX() - x) < Math.abs(currentWall.getStartY() - y))
				currentWall.setEndCoords(currentWall.getStartX(), y);
			else
				currentWall.setEndCoords(x, currentWall.getStartY());

			int highY = y + snapVal;
			int highX = x + snapVal;
			int lowY = y - snapVal;
			int lowX = x - snapVal;
			
			if(lowY < starty && starty < highY)
				currentWall.setEndy(starty);
			else {
				if(lowX< startx && startx < highX)
					currentWall.setEndx(startx);
			}
				
			
//			int coordy = x + pc;
//			int dist = Math.abs(coordy - y);
//			int ncoordy = -x + nc;
//			int ndist = Math.abs(ncoordy - y);
//
//			System.out.println("Dist: " + coordy + "," + endy + "--" + dist);
//			if (xory)
//				currentWall.setEndCoords(x, currentWall.getStartY());
//			else
//				currentWall.setEndCoords(currentWall.getStartX(), y);
//
//			// checking diagonally
//			if (dist < 20)
//				currentWall.setEndCoords(x, coordy);
//			if (ndist < 20)
//				currentWall.setEndCoords(x, ncoordy);

			repaint();
		}

	}

	public class MyKeyListener implements KeyListener {

		int x = -1;
		int y = -1;

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
				ctrlPress = true;

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			ctrlPress = false;
		}
	}
}
