package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import gui.Calculate;
import gui.Panel;
import walls.Wall;

public class CalculateTest {

	// a basic square room to test things on
	static Calculate cal;

	@BeforeClass
	public static void setUp() {

		cal = new Calculate();
		Panel panel = new Panel(cal);
		cal.setPanel(panel);
		cal.setDistInputted(true);

		ArrayList<Wall> walls = panel.getWalls();
		Wall wall = new Wall(0, 0, 0, 10);
		double[] newCo = { 0, 0, 0, 10 };
		wall.setLength(10);
		wall.setNewCoords(newCo);
		walls.add(wall);

		wall = new Wall(0, 10, 10, 10);
		double[] newCo1 = { 0, 10, 10, 10 };
		wall.setLength(10);
		wall.setNewCoords(newCo1);
		walls.add(wall);

		wall = new Wall(10, 10, 10, 0);
		double[] newCo2 = { 10, 10, 10, 0 };
		wall.setLength(10);
		wall.setNewCoords(newCo2);
		walls.add(wall);

		wall = new Wall(10, 0, 0, 0);
		double[] newCo3 = { 10, 10, 10, 0 };
		wall.setLength(10);
		wall.setNewCoords(newCo3);
		walls.add(wall);

	}

	/**
	 * Tests whether when given the correct length of walls, etc it gives the
	 * correct result
	 * 
	 * @throws Exception
	 */
	@Test
	public void correct() throws Exception {
		double[] correct = { 100.0, 80.0, 200.0 };
		double[] output = cal.calculate(2, 0, 0, 2, 2);
		System.out.println(output[0] + " " + output[1] + " " + output[2]);
		Assert.assertEquals(output[0], correct[0], 0.0001);
		Assert.assertEquals(output[1], correct[1], 0.0001);
		Assert.assertEquals(output[2], correct[2], 0.0001);
	}

	/**
	 * Test to see if Calculate will throw an exception to say that the room
	 * hasn't been drawn yet
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void disNotInputtedTest() throws Exception {

		Calculate cal = new Calculate();
		cal.calculate(12, 1, 10, 10, 10);

		fail("Distance hasn't been inputted");
	}

	/**
	 * Tests to see whether when a negative height is inputted that it should
	 * throw an exception
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void negHeightTest() throws Exception {
		cal.calculate(-10, 0, 0, 10, 1);

	}

	@Test(expected = Exception.class)
	public void negWindowTest() throws Exception {
		cal.calculate(10, -10, 0, 10, 1);
	}

	@Test(expected = Exception.class)
	public void negDoorsTest() throws Exception {
		cal.calculate(10, 0, -10, 10, 1);
	}

	@Test(expected = Exception.class)
	public void negCoatsTest() throws Exception {
		cal.calculate(10, 0, 0, 10, -1);
	}

}
