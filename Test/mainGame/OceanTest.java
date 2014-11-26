package mainGame;

import org.junit.Test;

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
	
}
