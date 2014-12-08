/*
 * 
 */
package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class OceanTest.
 */
public class OceanTest {

	/**
	 * Ocean should return exception when wrong direction is entered.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenWrongDirectionIsEntered() {
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "fdsf", null);
	}

	/**
	 * Ocean should return exception when more than five boats placed.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenMoreThanFiveBoatsPlaced() {
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "south", null);
		ocean.placeBoat(1, 2, 2, "south", null);
		ocean.placeBoat(1, 3, 2, "south", null);
		ocean.placeBoat(1, 4, 2, "south", null);
		ocean.placeBoat(1, 5, 2, "south", null);
		ocean.placeBoat(1, 6, 2, "south", null);
	}

	/**
	 * Shoot should returns false if tile has been shot at before.
	 */
	@Test
	public void shootShouldReturnsFalseIfTileHasBeenShotAtBefore() {

		Ocean ocean = new Ocean();
		ocean.shoot(0, 0);
		assertFalse(ocean.isValidShot(0, 0));

	}

	/**
	 * Shoot should returns false if tile is not in ocean.
	 */
	@Test
	public void shootShouldReturnsFalseIfTileIsNotInOcean() {

		Ocean ocean = new Ocean();
		assertFalse(ocean.isValidShot(0, 10));

	}
	
	/**
	 * Ocean should return exception if boat placement is invalid.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionIfBoatPlacementIsInvalid() {
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 1, "north", null);
		ocean.placeBoat(0, 1, 3, "south", null);
	}

	/**
	 * Cleaned ocean should not have any boats in it.
	 */
	@Test
	public void cleanedOceanShouldNotHaveAnyBoatsInIt(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "south", null);
		assertTrue(ocean.getBoatsInOcean()==1);
		ocean.cleanOcean();
		assertTrue(ocean.getBoatsInOcean()==0);
		
	}
	
}
