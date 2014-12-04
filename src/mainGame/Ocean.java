package mainGame;

public class Ocean {

	private Tile[][] ocean;
	private int boatsInOcean;

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

	//TODO: not used - remove
	public void setBoatsInOcean(int boatsInOcean) {
		this.boatsInOcean = boatsInOcean;
	}

	public Tile[][] getOcean() {
		return ocean;
	}

	private boolean allBoatsPlaced() {

		if (boatsInOcean == 5) {
			return true;
		}
		return false;

	}

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

	public void shoot(int row, int column) {
		if (isValidShot(row, column)) {
			ocean[row][column].shootAtTile();

			if (ocean[row][column].isBoatOnTile()) {
				if (ocean[row][column].getBoat().boatSinked()) {
					boatsInOcean--;
				}
			}

			// TODO: should we return hit or miss from this method?
		}
		// TODO: return not a valid choice... what should be returned?
	}

	boolean isValidShot(int row, int column) {

		if (row > -1 && row < 10 && column > -1 && column < 10) {
			if (ocean[row][column].tileHasBeenShootBefore()) {
				return false;
			} // if shot before
			return true;
		} // if in grid
		return false;
	}

	public String lookAtTile(int row, int column) {

		return ocean[row][column].toString();
	}

	public void cleanOcean() {
		boatsInOcean = 0;
		for (int i = 0; i < ocean.length; i++) {
			for (int j = 0; j < ocean[0].length; j++) {
				ocean[i][j] = null;
				ocean[i][j] = new Tile();
			}
		}
	}

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
