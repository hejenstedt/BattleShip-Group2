package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoatTest {

	@Test
	public void newBoatShouldNotBeSunken() {
		Boat boat = new Boat(1, null);
		assertFalse(boat.boatSinked());
	}

	@Test
	public void shotBoatWithLenght2ShouldNotBeSunken() {
		Boat boat = new Boat(2, null);
		boat.setHit();
		assertFalse(boat.boatSinked());
	}

	@Test
	public void shotBoatWithLenght1ShouldBeSunken() {
		Boat boat = new Boat(1, null);
		boat.setHit();
		assertTrue(boat.boatSinked());
	}

	@Test
	public void newBoatShouldReturnH() {
		Boat boat = new Boat(1, null);
		assertTrue(boat.toString().equals("H"));
	}

	@Test
	public void shotBoatWithLenght2ShouldReturnH() {
		Boat boat = new Boat(2, null);
		boat.setHit();
		assertTrue(boat.toString().equals("H"));
	}

	@Test
	public void shotBoatWithLenght1ShouldReturn1() {
		Boat boat = new Boat(1, null);
		boat.setHit();
		assertTrue(boat.toString().equals("1"));
	}
	
}
