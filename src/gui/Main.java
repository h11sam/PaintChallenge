package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * Class that sets up the gui.
 * @author HughSa
 *
 */
public class Main {

	private JFrame frame;

	public Main() {

		
		Calculate input = new Calculate();
		frame = new JFrame("Paint");
		Panel panel = new Panel(input);
		input.setPanel(panel);
		panel.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel);
		SideBar side = new SideBar(input);
		frame.getContentPane().add(side,BorderLayout.EAST);
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
