package mainGame;

public class Ocean {
	// TODO: method that cleans oceans
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
	
	public boolean allBoatsPlaced() {

		if (boatsInOcean == 5) {
			return true;
		}
		return false;
	}

	public void placeBoat(int row, int column, int boatLength, String direction) {
		// TODO: where should the validation logic be?
		if (!allBoatsPlaced()) {
			// TODO: Think this logic should be before we check if it is a valid
			// placement of the boat
			Boat boat = new Boat(boatLength);
			if (isPlacementValid(row, column, boatLength, direction)) {

				placeBoatOnTiles(row, column, boat, direction);
				boatsInOcean++;
			}
			else {
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
			// TODO: should we return hit or miss from this method?
		}
		// TODO: return not a valid choice... what should be returned?
	}

	boolean isValidShot(int row, int column) {
		if (ocean[row][column].tileHasBeenShootBefore()) {
			return false;
		}
		return true;
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
	
	public void showOceanWithBoats(){

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

}
