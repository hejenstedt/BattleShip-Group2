package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert;


public class OceanTest {

	
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenWrongDirectionIsEntered(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "fdsf");
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenMoreThanFiveBoatsPlaced(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "south");
		ocean.placeBoat(1, 2, 2, "south");
		ocean.placeBoat(1, 3, 2, "south");
		ocean.placeBoat(1, 4, 2, "south");
		ocean.placeBoat(1, 5, 2, "south");
		ocean.placeBoat(1, 6, 2, "south");
	}
	
	@Test
	public void shotShouldReturnsFalseIfTileHasBeenShotAtBefore(){
		Ocean ocean = new Ocean();
		
		ocean.shoot(0, 0);
		assertFalse(ocean.isValidShot(0, 0));
	}
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionIfBoatPlacementIsInvalid(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 1, "north");
		ocean.placeBoat(0, 1, 3, "east");
	}
	
}
