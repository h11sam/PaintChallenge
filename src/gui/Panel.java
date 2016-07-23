package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import walls.Wall;

/**
 * Panel that shows the drawing of the room.
 * 
 * @author HughSa
 *
 */
@SuppressWarnings("serial")
public class Panel extends JPanel {

	/*
	 * Enum used to show what action is currently being done by the user when we
	 * are drawing the shape. NOTHING: The user hasn't started drawing the shape
	 * MOVING: The user is currently drawing
	 */
	public enum Action {
		NOTHING, MOVING, FINISHED
	}

	private Action action = Action.NOTHING;
	private static final int snapVal = 10;
	boolean xory = true; // true if x, false is y direction
	private int startx = 0; // where the user initially clicked on
	private int starty = 0;
	private Calculate input;
	private Wall currentWall; // The current wall that is being drawn at the
								// moment
	private ArrayList<Wall> walls = new ArrayList<Wall>(); // The rest of the
													// walls

	public Panel(Calculate input) {

		super();
		this.input = input;
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.white);
		setOpaque(true);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		setFocusable(true);
		currentWall = new Wall(0, 0, 0, 0);
	}

	public void paint(Graphics g) {
		paintComponent(g);
		Color c = g.getColor();
		g.setColor(Color.black);
		currentWall.paint(g);
		getWalls().stream().forEach(wall -> {
			wall.paint(g);
		});
		g.setColor(c);
	}

	public class Mouse implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			switch (action) {
			// First press
			case NOTHING:
				int x = e.getX();
				int y = e.getY();
				startx = x;
				starty = y;
				action = Action.MOVING;
				System.out.println("Mouse pressed " + e.getX() + "," + e.getY());
				currentWall.setStartX(x); // TODO shorten this to one method
				currentWall.setStartY(y);
				break;
			// User has pressed again so we will save the current wall
			case MOVING:
				getWalls().add(currentWall);
				Wall lastWall = currentWall;
				int startx = currentWall.getEndx();
				int starty = currentWall.getEndy();
				currentWall = new Wall(startx, starty, startx, starty);

				// check to see whether we have come back to the start
				if (lastWall.getEndx() == getStartx() && lastWall.getEndy() == getStarty()) {
					System.out.println("Run wall lengths gui");
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							input.getWallLength();
						}
					});
					action = Action.FINISHED;
				}
				break;
			// We have reached the start again
			case FINISHED:
				System.out.println("Done");
				break;
			default:
				break;
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

			switch (action) {
			case MOVING:
				moveCurrentLine(e);
				break;
			default:
				break;
			}

		}

		/**
		 * Moves the current wall that is being selected. It only moves in the x
		 * or y direction, so walls can only be 90 degrees from each other
		 * 
		 * @param e
		 */
		private void moveCurrentLine(MouseEvent e) {
			// System.out.println("Mouse moved " + e.getX() + "," + e.getY());
			int x = e.getX();
			int y = e.getY();
			int highY = y + snapVal;
			int highX = x + snapVal;
			int lowY = y - snapVal;
			int lowX = x - snapVal;
			setFocusable(true);

			// first if is see whether we are moving into the x or y direction
			if (Math.abs(currentWall.getStartX() - x) < Math.abs(currentWall.getStartY() - y)) {
				currentWall.setEndCoords(currentWall.getStartX(), y);
				// checks to see if we are close to the start point either in x
				// coords or y coords
				if (lowY < starty && starty < highY)
					currentWall.setEndy(starty);

			} else {
				currentWall.setEndCoords(x, currentWall.getStartY());
				if (lowX < startx && startx < highX)
					currentWall.setEndx(startx);

			}

			repaint();
		}

	}

	public int getStartx() {
		return startx;
	}

	public int getStarty() {
		return starty;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}
}
