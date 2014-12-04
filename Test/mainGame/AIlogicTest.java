package mainGame;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AIlogicTest {

	@Test
	public void aiGenerateCoordinatesShouldReturnTwoInt0_9() {
		AIlogic ailogic = new AIlogic(new Ocean());
		int[] temp = ailogic.generateAIShipCoordinates();

		assertTrue("int is 10 or higher", temp[0] < 10);
		assertTrue("int is 10 or higher", temp[1] < 10);
		assertTrue("int is lower than 0", temp[0] >= 0);
		assertTrue("int is lower than 0", temp[1] >= 0);

	}

	@Test
	public void aiGenerateShipDirectionShouldReturn1of4Directions() {
		AIlogic ailogic = new AIlogic(new Ocean());
		String direction = ailogic.generateAIShipDirection();

		assertTrue(direction.equals("south") || direction.equals("north")
				|| direction.equals("east") || direction.equals("west"));

	}

}
