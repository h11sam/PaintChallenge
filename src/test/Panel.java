package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Panel extends JPanel {

	boolean ctrlPress = false;
	boolean xory = true; // true if x, false is y direction
	int startx = 0;
	int starty = 0;
	int endx = 0;
	int endy = 0;

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
	}

	public void paint(Graphics g) {
		paintComponent(g);
		Color c = g.getColor();
		g.setColor(Color.white);
		g.drawLine(startx, starty, endx, endy);
		g.setColor(c);
	}

	public class Mouse implements MouseListener, MouseMotionListener {

		int pc = 0;
		int nc = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

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
			// System.out.println("Mouse pressed " + e.getX() + "," + e.getY());
			startx = e.getX();
			starty = e.getY();
			pc = starty - startx;
			nc = starty + startx;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
//			 System.out.println("Mouse moved " + e.getX() + "," + e.getY());
			setFocusable(true);
			if (!ctrlPress) {

				endx = e.getX();
				endy = e.getY();
			} else {
				if (Math.abs(startx - e.getX()) < Math.abs(starty - e.getY())) {
					// System.out.println("one");
					xory = false;
				} else {
					// System.out.println("two");
					xory = true;
				}

				int coordy = e.getX() + pc;
				int dist = Math.abs(coordy - e.getY());
				int ncoordy = -e.getX() + nc;
				int ndist = Math.abs(ncoordy - e.getY());
				
				System.out.println("Dist: " + coordy + "," + endy + "--" + dist);
				if (xory) {
					endx = e.getX();
					endy = starty;
				} else {
					endx = startx;
					endy = e.getY();
				}
				if(dist < 20){
//					System.out.println("diagonal");
					endx = e.getX();
					endy = coordy;
				}
				if(ndist < 20){
//					System.out.println("diagonal");
					endx = e.getX();
					endy = ncoordy;
				}
			}

			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

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
