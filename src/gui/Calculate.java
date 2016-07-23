package gui;

import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import walls.Wall;

/**
 * Input the distances and calcuates the outputs and displays them to the user.
 * Area of the floor, amount paint required and volume of the room
 * 
 * @author HughSa
 *
 */
public class Calculate {

	private Panel panel;
	private boolean distInputted = false;

	public Calculate() {

	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	private double inputDistance(int num, int max) throws NumberFormatException, NullPointerException {
		return Double.parseDouble(JOptionPane.showInputDialog(
				"Please input the length of the highlighted wall in metres." + "\n Wall " + num + " of " + max));
	}

	/**
	 * Asking the user to input each of the lengths of the walls that the user
	 * has drawn and then puts that distance into the wall. And highlights the
	 * wall red so that the user knows which wall we are talking about
	 */
	private void getValues() {

		ArrayList<Wall> walls = panel.getWalls();
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
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "Incorrect value or null please try again");
				}

			}

			/**
			 * We are now getting the real life coordinates of where the wall
			 * actually is. Where (0,0) is where the user first clicked
			 */

			wall.setLength(distance);
			wall.setRealCoords(prevEnd);
			prevEnd[0] = wall.getNewCoords()[2];
			prevEnd[1] = wall.getNewCoords()[3];
			wall.setSelected(false);
			panel.repaint();

			System.out.println("Wall " + val + ": " + wall.getNewCoords()[0] + "," + wall.getNewCoords()[1] + " | "
					+ wall.getNewCoords()[2] + "," + wall.getNewCoords()[3]);
		}

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
		input.add(Box.createHorizontalStrut(15));
		input.add(new JLabel("height:"));
		input.add(textHeight);

		// going through the number of doors specified
		for (int i = 0; i < doors; i++) {

			double width = 0.0;
			double height = 0.0;
			while (true) {
				try {
					JOptionPane.showConfirmDialog(null, input, "Please Enter Width and Height of door " + (i + 1),
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

		// System.out.println("The area is: " + area);
		return area;
	}

	/**
	 * Getting all of the lengths of the walls. And checks to make sure that all
	 * of the lengths that were submitted join back at the start to ensure that
	 * we have a complete room
	 */
	public void getWallLength() {

		while (true) {
			getValues();
			Wall lastWall = panel.getWalls().get(panel.getWalls().size() - 1);
			double[] coords = lastWall.getNewCoords();
			if (coords[2] == 0 && coords[3] == 0) {
				break;
			}

			JOptionPane.showMessageDialog(null, "Lengths you entered do not meet back at the start please try again");

		}

		setDistInputted(true);
	}

	/**
	 * Calculated the area of the floor and the length of all of the walls
	 * 
	 * @return {floor area, length of all the walls}
	 */
	private double[] calcFloorAreaLen() {

		double[] vals = { 0.0, 0.0 };
		for (Wall wall : panel.getWalls()) {
			vals[0] += wall.getArea();
			vals[1] += wall.getLength();
		}

		vals[0] = Math.abs(vals[0]);
		return vals;
	}

	/**
	 * Calculates and outputs the outputs; area of the floor, amount of paint
	 * required and volume of the room
	 * 
	 * @param height
	 *            Height of room
	 * @param noOfWindows
	 *            Number of windows
	 * @param noOfDoors
	 *            Number of doors
	 * @param volumeOfPaint
	 *            How much a litre of paint covers in meters
	 * @param noOfCoats
	 *            Number of coats that is required
	 * @return {area of floor, paint required, volume of room}
	 * @throws Exception
	 *             if there is an error with the inputs
	 */
	public double[] calculate(double height, double noOfWindows, double noOfDoors, double volumeOfPaint,
			double noOfCoats) throws Exception {
		// do checks on intital values
		if (isDistInputted() == false) {
			// JOptionPane.showMessageDialog(null, "Room has not been drawn
			// yet.");
			throw new Exception("Room has not been drawn yet.");
		}
		if (height <= 0 | noOfWindows < 0 | noOfDoors < 0 | volumeOfPaint < 0 | noOfCoats < 0) {

			throw new IllegalArgumentException(
					"One or more of the values you entered are negative or\n height/ coats is zero. Please try again");

		}

		double doorWindowArea = getDoorsAndWindows(noOfDoors, noOfWindows);
		double[] roomArea = calcFloorAreaLen();
		double areaFloor = roomArea[0];
		double areaWall = (roomArea[1] * height) - doorWindowArea;
		double volumeRoom = areaFloor * height;
		double paintRequired = (areaWall * noOfCoats) / volumeOfPaint;
		// double wallArea = calc
		if (doorWindowArea > areaWall) {

			// JOptionPane.showMessageDialog(null, );
			throw new Exception("The total area of the windows and doors is higher than "
					+ "the total area of the walls of the room!");
		}

		// JOptionPane.showMessageDialog(null, "Area of the floor: " + areaFloor
		// + "\nAmount of paint required: "
		// + paintRequired + "\nVolume of room: " + volumeRoom);
		double ret[] = { areaFloor, paintRequired, volumeRoom };
		return ret;

	}

	public boolean isDistInputted() {
		return distInputted;
	}

	public void setDistInputted(boolean distInputted) {
		this.distInputted = distInputted;
	}

}
