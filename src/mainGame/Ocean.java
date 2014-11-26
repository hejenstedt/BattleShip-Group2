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

	public boolean allBoatsPlaced() {

		if (boatsInOcean == 5) {
			return true;
		}
		return false;
	}

	public void placeBoat(int row, int column, int boatLength, String direction) {
		// TODO: where should the validation logic be?
		// TODO: where should we turn char input into int input?
		if (!allBoatsPlaced()) {
			// TODO: Think this logic should be before we check if it is a valid
			// placement of the boat
			Boat boat = new Boat(boatLength);
			ocean[row][column].setBoatOnTile(boat);

			switch (direction) {
			case "south":
				for (int i = 1; i < boatLength; i++) {
					ocean[row + i][column].setBoatOnTile(boat);
				}
				break;
			case "north":
				for (int i = 1; i < boatLength; i++) {
					ocean[row - i][column].setBoatOnTile(boat);
				}
				break;
			case "west":
				for (int i = 1; i < boatLength; i++) {
					ocean[row][column - 1].setBoatOnTile(boat);
				}
				break;
			case "east":
				for (int i = 1; i < boatLength; i++) {
					ocean[row][column + 1].setBoatOnTile(boat);
				}
				break;
			default:
				throw new IllegalArgumentException(
						"That direction does not exist");
			}

			boatsInOcean++;
		} else {
			throw new IllegalArgumentException(
					"All boats already placed in the ocean.");
		}

	}

	public void shoot(int row, int column) {
		if (isValidShot(row, column)) {
			ocean[row][column].shootAtTile();
			// TODO: should we return hit or miss from this method?
		}
		//TODO: return not a valid choice... what should be returned?
	}

	private boolean isValidShot(int row, int column) {
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

}
