package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class Main {

	private JFrame frame;

	public Main() {

		
		DisInput input = new DisInput();
		frame = new JFrame("Paint");
		Panel panel = new Panel(input);
		input.setPanel(panel);
		panel.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel);
		SideBar side = new SideBar(input);
		frame.add(side,BorderLayout.EAST);
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
