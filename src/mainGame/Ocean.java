package mainGame;

/**
 * The Class Ocean.
 */
public class Ocean {

	private Tile[][] ocean;
	private int boatsInOcean;

	/**
	 * Instantiates a new ocean. 10 rows, 10 columns Instantiates a tile for
	 * each position
	 */
	public Ocean() {
		ocean = new Tile[10][10];
		boatsInOcean = 0;
		for (int i = 0; i < ocean.length; i++) {
			for (int j = 0; j < ocean[0].length; j++) {
				ocean[i][j] = new Tile();
			}
		}
	}

	public int getBoatsInOcean() {
		return boatsInOcean;
	}

	public Tile[][] getOcean() {
		return ocean;
	}

	/**
	 * Checks if all boats are placed.
	 *
	 * @return true, if all boats are placed
	 */
	private boolean allBoatsPlaced() {

		if (boatsInOcean == 5) {
			return true;
		}
		return false;

	}

	/**
	 * Place boat in ocean.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param boatLength
	 *            the boat length
	 * @param direction
	 *            the direction
	 * @param boatName
	 *            the boat name
	 */
	public void placeBoat(int row, int column, int boatLength,
			String direction, String boatName) {
		// TODO: where should the validation logic be?
		if (!allBoatsPlaced()) {
			// TODO: Think this logic should be before we check if it is a valid
			// placement of the boat
			Boat boat = new Boat(boatLength, boatName);
			if (isPlacementValid(row, column, boatLength, direction)) {

				placeBoatOnTiles(row, column, boat, direction);
				boatsInOcean++;
			} else {
				throw new IllegalArgumentException("Boat placement not valid.");
			}

		} else {
			throw new IllegalArgumentException(
					"All boats already placed in the ocean.");
		}

	}

	/**
	 * Place boat on tiles. Gets the starting position and in what direction the
	 * boat should be placed according to the cardinal directions.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param boat
	 *            the boat
	 * @param direction
	 *            the direction
	 */
	private void placeBoatOnTiles(int row, int column, Boat boat,
			String direction) {

		switch (direction) {
		case "south":
			for (int i = 0; i < boat.getBoatLength(); i++) {
				ocean[row + i][column].setBoatOnTile(boat);
			}
			break;
		case "north":
			for (int i = 0; i < boat.getBoatLength(); i++) {
				ocean[row - i][column].setBoatOnTile(boat);
			}
			break;
		case "west":
			for (int i = 0; i < boat.getBoatLength(); i++) {
				ocean[row][column - i].setBoatOnTile(boat);
			}
			break;
		case "east":
			for (int i = 0; i < boat.getBoatLength(); i++) {
				ocean[row][column + i].setBoatOnTile(boat);
			}
			break;
		default:
			throw new IllegalArgumentException("That direction does not exist");
		}

	}

	/**
	 * Checks if placement of boat is valid. No part of the boat should be on
	 * top of another boat nor outside of the grid.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param boatLength
	 *            the boat length
	 * @param direction
	 *            the direction
	 * @return true, if placement is valid
	 */
	private boolean isPlacementValid(int row, int column, int boatLength,
			String direction) {
		boolean validBoatPlacement = true;

		switch (direction) {
		case "south":
			for (int i = 0; i < boatLength; i++) {
				if (ocean[row + i][column].isBoatOnTile()) {
					validBoatPlacement = false;
				}
			}
			break;
		case "north":

			for (int i = 0; i < boatLength; i++) {
				if (ocean[row - i][column].isBoatOnTile()) {
					validBoatPlacement = false;
				}
			}

			break;
		case "west":

			for (int i = 0; i < boatLength; i++) {
				if (ocean[row][column - i].isBoatOnTile()) {
					validBoatPlacement = false;
				}
			}
			break;
		case "east":

			for (int i = 0; i < boatLength; i++) {
				if (ocean[row][column + i].isBoatOnTile()) {
					validBoatPlacement = false;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("That direction does not exist");
		}

		return validBoatPlacement;
	}

	/**
	 * Shoot at a tile in the Ocean. If the shoot is invalid nothing happens.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 */
	public void shoot(int row, int column) {
		if (isValidShot(row, column)) {
			ocean[row][column].shootAtTile();

			if (ocean[row][column].isBoatOnTile()) {
				if (ocean[row][column].getBoat().boatSinked()) {
					boatsInOcean--;
				}
			}

		}
	}

	/**
	 * Checks if it is a valid shot.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return true, if is valid shot
	 */
	boolean isValidShot(int row, int column) {

		if (row > -1 && row < 10 && column > -1 && column < 10) {
			if (ocean[row][column].tileHasBeenShootBefore()) {
				return false;
			} // if shot before
			return true;
		} // if in grid
		return false;
	}

	/**
	 * Look at tile to see what it would show as in the Ocean.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return the tile's toString
	 */
	public String lookAtTile(int row, int column) {

		return ocean[row][column].toString();
	}

	/**
	 * Clean ocean. Remove all existing tiles and replace with new ones.
	 * 
	 */
	public void cleanOcean() {
		boatsInOcean = 0;
		for (int i = 0; i < ocean.length; i++) {
			for (int j = 0; j < ocean[0].length; j++) {
				ocean[i][j] = null;
				ocean[i][j] = new Tile();
			}
		}
	}

	/**
	 * Show ocean. Prints the ocean out showing if a tile has been hit. And if a
	 * boat is hit or sunk.
	 */
	public void showOcean() {

		System.out.println("  A B C D E F G H I J");
		System.out.println(" ______________________");
		int row = 0;
		for (Tile[] tiles : ocean) {
			System.out.print(row + "|");
			for (Tile tile : tiles) {
				System.out.print(tile.toString() + " ");
			}
			System.out.println("| " + row);
			row++;
		}
		System.out.println(" ______________________");
		System.out.println("  A B C D E F G H I J");

	}

	/**
	 * Shows ocean with boats.
	 */
	public void showOceanWithBoats() {

		System.out.println("  A B C D E F G H I J");
		System.out.println(" ______________________");
		int row = 0;
		for (Tile[] tiles : ocean) {
			System.out.print(row + "|");
			for (Tile tile : tiles) {
				System.out.print(tile.tileShowingBoat() + " ");
			}
			System.out.println("| " + row);
			row++;
		}
		System.out.println(" ______________________");
		System.out.println("  A B C D E F G H I J");

	}

	public Tile getTile(int i, int j) {
		return ocean[i][j];
	}
}
