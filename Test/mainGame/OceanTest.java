package mainGame;

import org.junit.Test;

public class OceanTest {

	
	@Test(expected = IllegalArgumentException.class)
	public void oceanShouldReturnExceptionWhenWrongDirectionIsEntered(){
		Ocean ocean = new Ocean();
		ocean.placeBoat(1, 1, 2, "fdsf");
		
	}
	
	
}
