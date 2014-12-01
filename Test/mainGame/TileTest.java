package mainGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {

	@Test
	public void alreadyShotTileShouldReturnX() {
		Tile tile = new Tile();
		tile.shootAtTile();
		assertTrue(tile.toString().equals("X"));
	}

	@Test
	public void unshotTileShouldReturnTilde(){
		Tile tile = new Tile();
		assertTrue(tile.toString().equals("~"));
	}

	@Test
	public void tileWithShotBoatAtShouldReturnH(){
		Tile tile = new Tile();
		tile.setBoatOnTile(new Boat(2));
		tile.shootAtTile();
		assertTrue(tile.toString().equals("H"));
	}
	
	@Test
	public void tileWithSunkenBoatAtShouldReturn1(){
		Tile tile = new Tile();
		tile.setBoatOnTile(new Boat(1));
		tile.shootAtTile();
		assertTrue(tile.toString().equals("1"));
	}
}
