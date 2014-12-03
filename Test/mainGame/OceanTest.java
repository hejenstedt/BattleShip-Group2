package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class OceanTest {

	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenWrongDirectionIsEntered() {
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "fdsf", null);
	}

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

	@Test
	public void shootShouldReturnsFalseIfTileHasBeenShotAtBefore() {

		Ocean ocean = new Ocean();
		ocean.shoot(0, 0);
		assertFalse(ocean.isValidShot(0, 0));

	}

	@Test
	public void shootShouldReturnsFalseIfTileIsNotInOcean() {

		Ocean ocean = new Ocean();
		assertFalse(ocean.isValidShot(0, 10));

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionIfBoatPlacementIsInvalid() {
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 1, "north", null);
		ocean.placeBoat(0, 1, 3, "south", null);
	}

	@Test
	public void cleanedOceanShouldNotHaveAnyBoatsInIt(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "south", null);
		assertTrue(ocean.boatsInOcean==1);
		ocean.cleanOcean();
		assertTrue(ocean.boatsInOcean==0);
		
	}
	
}
