package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import walls.Item;
import walls.Wall;

public class ItemTest {

	/**
	 * Here we are checking to see whether when we put the coords of the item
	 * put on the screen and then set the length we get actual coordinates that
	 * it should be.
	 */
	@Test
	public void setRealCoordsTest() {

		double[] actual = {10.0,10.0,30.0,10.0};
		
		Item test = new Wall(0,0,10,0);
		test.setLength(20);
		double[] prevcoord = {10.0,10.0};
		test.setRealCoords(prevcoord);
		
		double[] result = test.getNewCoords();
		for(int i = 0; i < 4;i++)
			assertEquals(actual[i],result[i] ,0.00001);
	}

}
