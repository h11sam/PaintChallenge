package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.jar.JarInputStream;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import walls.Wall;

/**
 * Input the distances of the wall
 * 
 * @author HughSa
 *
 */
public class DisInput {

	// this values are to ensure that we check that the walls all meet up at the
	// end. So once all of the wall lengths have been added both directions
	// should still be zero
	private float xdirection = 0;
	private float ydirection = 0;
	private Panel panel;
	private boolean distInputted = false;

	public DisInput() {

	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	private double inputDistance(int num, int max) throws NumberFormatException, NullPointerException {
		return Double.parseDouble(JOptionPane.showInputDialog(
				"Please input the length of the highlighted wall in metres." + "\n Wall " + num + " of " + max));
	}

	private void getValues() {

		ArrayList<Wall> walls = panel.walls;
		int size = walls.size();
		System.out.println("end");
		int val = 0;
		double[] prevEnd = { 0.0, 0.0 };

		for (Wall wall : walls) {
			val++;
			wall.setSelected(true);
			panel.repaint();
			double distance;
			while (true) {
				try {
					distance = inputDistance(val, size);
					break;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Incorrect value or null please try again");
				}

			}

			wall.setLength(distance);
			int diffx = wall.getEndx() - wall.getStartX();
			int diffy = wall.getEndy() - wall.getStartY();

			wall.setSelected(false);
			panel.repaint();

			// when we are moving in the x direction
			if (diffy == 0) {
				double dist = distance * (diffx / Math.abs(diffx));
				double[] newCoords = { prevEnd[0], prevEnd[1], prevEnd[0] + dist, prevEnd[1] };
				prevEnd[0] = prevEnd[0] + dist;
				prevEnd[1] = prevEnd[1];
				wall.setNewCoords(newCoords);
			} else {
				double dist = distance * (diffy / Math.abs(diffy));
				double[] newCoords = { prevEnd[0], prevEnd[1], prevEnd[0], prevEnd[1] + dist };
				prevEnd[0] = prevEnd[0];
				prevEnd[1] = prevEnd[1] + dist;

				wall.setNewCoords(newCoords);
			}

			System.out.println("Wall " + val + ": " + wall.getNewCoords()[0] + "," + wall.getNewCoords()[1] + " | "
					+ wall.getNewCoords()[2] + "," + wall.getNewCoords()[3]);
		}

		// System.out.println("Last coords: " + walls.get(walls.size() -
		// 1).getNewCoords()[2] + "," + walls.get(walls.size() -
		// 1).getNewCoords()[3]);

	}

	/**
	 * Used to get the width and height of the window
	 * 
	 * @param doors
	 *            number of doors
	 * @param windows
	 *            number of windows
	 * @return The total area of the doors and windows
	 */
	private double getDoorsAndWindows(double doors, double windows) {

		double area = 0.0;
		JTextField textWidth = new JTextField(5);
		JTextField textHeight = new JTextField(5);

		JPanel input = new JPanel();
		input.add(new JLabel("width:"));
		input.add(textWidth);
		input.add(Box.createHorizontalStrut(15)); // a spacer
		input.add(new JLabel("height:"));
		input.add(textHeight);

		for (int i = 0; i < doors; i++) {

			double width = 0.0;
			double height = 0.0;
			while (true) {
				try {
					JOptionPane.showConfirmDialog(null, input, "Please Enter Width and Height of door " + i,
							JOptionPane.OK_CANCEL_OPTION);
					width = Double.parseDouble(textWidth.getText());
					height = Double.parseDouble(textHeight.getText());
					break;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a correct value");
				}
			}
			textWidth.setText("");
			textHeight.setText("");

			area += width * height;

		}

		for (int i = 0; i < windows; i++) {

			double width = 0.0;
			double height = 0.0;
			while (true) {
				try {
					JOptionPane.showConfirmDialog(null, input, "Please Enter Width and Height of window " + i,
							JOptionPane.OK_CANCEL_OPTION);
					width = Double.parseDouble(textWidth.getText());
					height = Double.parseDouble(textHeight.getText());
					break;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a correct value");
				}
			}
			textWidth.setText("");
			textHeight.setText("");

			area += width * height;

		}

		System.out.println("The area is: " + area);
		return area;
	}

	public void getWallLength() {

		while (true) {
			getValues();
			Wall lastWall = panel.walls.get(panel.walls.size() - 1);
			double[] coords = lastWall.getNewCoords();
			if (coords[2] == 0 && coords[3] == 0) {
				break;
			}

			JOptionPane.showMessageDialog(null, "Lengths you entered do not meet back at the start please try again");

		}

		distInputted = true;
	}

	public void calculate(double height, double noOfWindows, double noOfDoors, double volumeOfPaint, double noOfCoats) {
		// do checks on intital values
		if (distInputted == false) {
			JOptionPane.showMessageDialog(null, "Room has not been drawn yet.");
			return;
		}
		if (height <= 0 | noOfWindows < 0 | noOfDoors < 0 | volumeOfPaint < 0 | noOfCoats < 0) {

			JOptionPane.showMessageDialog(null,
					"One or more of the values you entered are negative or\n height/ coats is zero. Please try again");
			return;
		}

		double doorWindowArea = getDoorsAndWindows(noOfDoors, noOfWindows);

	}

}
