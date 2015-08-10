package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	private JFrame frame;

	public Main() {

		frame = new JFrame("Paint");
		frame.add(new Panel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	private static void createAndShowGUI() {
		new Main().start();

	}

	public void start() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
