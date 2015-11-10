package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The Class TileTest.
 */
public class TileTest {

	/**
	 * Already shot tile should return x.
	 */
	@Test
	public void alreadyShotTileShouldReturnX() {
		Tile tile = new Tile();
		tile.shootAtTile();
		assertTrue(tile.toString().equals("X"));
	}

	/**
	 * Unshot tile should return tile.
	 */
	@Test
	public void unshotTileShouldReturnTilde(){
		Tile tile = new Tile();
		assertTrue(tile.toString().equals("~"));
	}

	/**
	 * Tile with shot boat at should return h.
	 */
	@Test
	public void tileWithShotBoatAtShouldReturnH(){
		Tile tile = new Tile();
		tile.setBoatOnTile(new Boat(2, null));
		tile.shootAtTile();
		assertTrue(tile.toString().equals("H"));
	}
	
	/**
	 * Tile with sunken boat at should return1.
	 */
	@Test
	public void tileWithSunkenBoatAtShouldReturn1(){
		Tile tile = new Tile();
		tile.setBoatOnTile(new Boat(1, null));
		tile.shootAtTile();
		assertTrue(tile.toString().equals("1"));
	}
}
