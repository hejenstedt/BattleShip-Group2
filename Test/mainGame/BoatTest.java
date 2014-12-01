package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoatTest {

	@Test
	public void newBoatShouldNotBeSunken() {
		Boat boat = new Boat(1);
		assertFalse(boat.boatSinked());
	}

	@Test
	public void shotBoatWithLenght2ShouldNotBeSunken() {
		Boat boat = new Boat(2);
		boat.setHit();
		assertFalse(boat.boatSinked());
	}

	@Test
	public void shotBoatWithLenght1ShouldBeSunken() {
		Boat boat = new Boat(1);
		boat.setHit();
		assertTrue(boat.boatSinked());
	}

	@Test
	public void newBoatShouldReturnH() {
		Boat boat = new Boat(1);
		assertTrue(boat.toString().equals("H"));
	}

	@Test
	public void shotBoatWithLenght2ShouldReturnH() {
		Boat boat = new Boat(2);
		boat.setHit();
		assertTrue(boat.toString().equals("H"));
	}

	@Test
	public void shotBoatWithLenght1ShouldReturn1() {
		Boat boat = new Boat(1);
		boat.setHit();
		assertTrue(boat.toString().equals("1"));
	}
	
}
