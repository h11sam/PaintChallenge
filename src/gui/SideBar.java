package gui;

import javax.swing.JPanel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

/**
 * Contains the textboxes for the user to input in the required information.
 * Including a calculate button
 * 
 * @author HughSa
 *
 */
@SuppressWarnings("serial")
public class SideBar extends JPanel {
	private JTextField txtMOfPaint;
	private JLabel lblMOfPaint;
	private JButton btnCalculate;
	private JTextPane txtpnPleaseDrawThe;
	private JLabel lblNumberOfCoats;
	private JSpinner noOfWindow;
	private JSpinner noOfDoors;
	private JSpinner noOfCoats;
	private JLabel lblHeightOfRoom;
	private JTextField heightOfRoom;

//	private Calculate input;

	public SideBar(Calculate input) {

//		this.input = input;

		setLayout(new GridLayout(0, 1, 0, 0));

		txtpnPleaseDrawThe = new JTextPane();
		txtpnPleaseDrawThe.setEditable(false);
		txtpnPleaseDrawThe.setText(
				"Please draw the shape of the room on the left\n and ensure that the wall joins back at the start");
		add(txtpnPleaseDrawThe);

		lblHeightOfRoom = new JLabel("Height of Room");
		lblHeightOfRoom.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHeightOfRoom);

		heightOfRoom = new JTextField();
		heightOfRoom.setText("0.0");
		heightOfRoom.setHorizontalAlignment(SwingConstants.CENTER);
		add(heightOfRoom);
		heightOfRoom.setColumns(10);

		JLabel lblNumberOfWindows = new JLabel("Number of windows");
		lblNumberOfWindows.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNumberOfWindows);

		noOfWindow = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
		((JSpinner.DefaultEditor) noOfWindow.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		add(noOfWindow);

		JLabel lblNumberOfDoors = new JLabel("Number of doors");
		lblNumberOfDoors.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNumberOfDoors);

		noOfDoors = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
		((JSpinner.DefaultEditor) noOfDoors.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		add(noOfDoors);

		lblMOfPaint = new JLabel("m^2 of paint per litre");
		lblMOfPaint.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMOfPaint);

		txtMOfPaint = new JTextField();
		txtMOfPaint.setText("0.0");
		txtMOfPaint.setHorizontalAlignment(SwingConstants.CENTER);
		add(txtMOfPaint);

		lblNumberOfCoats = new JLabel("Number of coats");
		lblNumberOfCoats.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNumberOfCoats);

		noOfCoats = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
		((JSpinner.DefaultEditor) noOfCoats.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		add(noOfCoats);

		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				double height = 0.0;
				double windows = 0;
				double doors = 0;
				double vol = 0.0;
				double coat = 0;
				// check to make sure none of the values inputed by the user is
				// incorrect
				try {

					height = Double.valueOf(heightOfRoom.getText());
					windows = (double) noOfWindow.getValue();
					doors = (double) noOfDoors.getValue();
					vol = Double.valueOf(txtMOfPaint.getText());
					coat = (double) noOfCoats.getValue();

				} catch (Exception except) {
					System.out.println(except.getMessage());
					JOptionPane.showMessageDialog(null,
							"One or more of the values is of the wrong type. Please try again");
					return;
				}
				// System.out.println("Height: " + height);
				try {
					double[] vals = input.calculate(height, windows, doors, vol, coat);
					JOptionPane.showMessageDialog(null, "Area of the floor: " + vals[0]
							+ "\nAmount of paint required: " + vals[1] + "\nVolume of room: " + vals[2]);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}

			}
		});

		add(btnCalculate);
	}

}
