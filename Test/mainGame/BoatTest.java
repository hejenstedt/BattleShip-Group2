/*
 * 
 */
package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class BoatTest.
 */
public class BoatTest {

	/**
	 * New boat should not be sunken.
	 */
	@Test
	public void newBoatShouldNotBeSunken() {
		Boat boat = new Boat(1, null);
		assertFalse(boat.boatSinked());
	}

	/**
	 * Shot boat with lenght2 should not be sunken.
	 */
	@Test
	public void shotBoatWithLenght2ShouldNotBeSunken() {
		Boat boat = new Boat(2, null);
		boat.setHit();
		assertFalse(boat.boatSinked());
	}

	/**
	 * Shot boat with lenght1 should be sunken.
	 */
	@Test
	public void shotBoatWithLenght1ShouldBeSunken() {
		Boat boat = new Boat(1, null);
		boat.setHit();
		assertTrue(boat.boatSinked());
	}

	/**
	 * New boat should return h.
	 */
	@Test
	public void newBoatShouldReturnH() {
		Boat boat = new Boat(1, null);
		assertTrue(boat.toString().equals("H"));
	}

	/**
	 * Shot boat with lenght2 should return h.
	 */
	@Test
	public void shotBoatWithLenght2ShouldReturnH() {
		Boat boat = new Boat(2, null);
		boat.setHit();
		assertTrue(boat.toString().equals("H"));
	}

	/**
	 * Shot boat with lenght1 should return1.
	 */
	@Test
	public void shotBoatWithLenght1ShouldReturn1() {
		Boat boat = new Boat(1, null);
		boat.setHit();
		assertTrue(boat.toString().equals("1"));
	}
	
}
