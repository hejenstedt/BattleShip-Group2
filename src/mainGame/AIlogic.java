
package mainGame;

import java.util.Random;

/**
 * The Class AILogic.
 */
public class AILogic {

	private Ocean playerOcean;
	private Random rand;
	private int[] coordinates;

	/**
	 * Instantiates a new AILogic.
	 *
	 * @param playerOcean
	 *            the player ocean
	 */
	public AILogic(Ocean playerOcean) {
		this.playerOcean = playerOcean;
		rand = new Random();
		coordinates = new int[2];
	}

	/**
	 * Generate coordinates for the AI player. Depending on the difficulty sent
	 * in it chooses the correct methods to use.
	 *
	 * @param difficulty
	 *            the difficulty
	 * @return the coordinates as an int[]
	 */
	public int[] generateAIcoordinates(int difficulty) {

		int[] coordinates = null;
		switch (difficulty) {
		// different logic depending on difficulty

		case (0): {
			coordinates = aiLogicEasy();
			break;
		}

		case (1): {
			coordinates = aiLogicMedium();
			break;
		}

		case (2): {
			coordinates = aiLogicHard();
			break;
		}

		default:
			// TODO: handle error here
		}

		return coordinates;
	}

	/**
	 * AI logic, hard difficulty.
	 *
	 * @return the coordinates as an int[]
	 */
	private int[] aiLogicHard() {

		coordinates = findUnsunkenBoats();

		if (coordinates == null) {
			coordinates = new int[2];
			this.generateRandomExpertCoordinates();
		}

		return coordinates;
	}

	/**
	 * Checks if direction is clockwise.
	 *
	 * @return true, if direction is clockwise and false if directions is
	 *         counter clockwise
	 */
	private boolean isDirectionClockwise() {
		if (rand.nextInt(1) == 0) {
			// clockwise
			return true;
		} else {
			// counter clockwise
			return false;
		}
	}

	private int generateStartDirection() {
		return rand.nextInt(3);
	}

	/**
	 * AL logic, medium difficulty.
	 *
	 * @return the coordinates as an int[]
	 */
	private int[] aiLogicMedium() {

		coordinates = findUnsunkenBoats();

		// shoot random
		if (coordinates == null) {
			coordinates = new int[2];
			coordinates = aiLogicEasy();
		}

		return coordinates;

	}

	/**
	 * Finds if there any unsunken boats that has been shot at (is shown as H on
	 * the ocean).
	 *
	 * @return the coordinates as an int[]
	 */
	private int[] findUnsunkenBoats() {
		Tile[][] playersOcean = playerOcean.getOcean();
		// check playerOcean if there are any visible unsunken ships (H)

		for (int i = 0; i < playersOcean.length; i++) {
			for (int j = 0; j < playersOcean[0].length; j++) {

				if (tileHasHitButUnsunkenBoat(i, j)) {

					boolean directionIsClockWise = isDirectionClockwise();
					int direction = generateStartDirection();

					for (int k = 0; k < 4; k++) {

						switch (direction) {
						case 0:
							if (playerOcean.isValidShot(i - 1, j)) {
								coordinates[0] = i - 1;
								coordinates[1] = j;
								return coordinates;
							}

							break;
						case 1:
							if (playerOcean.isValidShot(i, j + 1)) {
								coordinates[0] = i;
								coordinates[1] = j + 1;
								return coordinates;
							}
							break;
						case 2:
							if (playerOcean.isValidShot(i + 1, j)) {
								coordinates[0] = i + 1;
								coordinates[1] = j;
								return coordinates;
							}
							break;
						case 3:
							if (playerOcean.isValidShot(i, j - 1)) {
								coordinates[0] = i;
								coordinates[1] = j - 1;
								return coordinates;
							}
							break;
						default:
							break;
						}

						if (directionIsClockWise) {
							direction++;
							if (direction == 4) {
								direction = 0;
							}

						} else {
							direction--;
							if (direction == -1) {
								direction = 3;
							}
						}

					}

				}

			}
		}
		return null;
	}

	/**
	 * Support method to retrun true if Tile has hit but unsunken boat.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @return true, if successful
	 */
	private boolean tileHasHitButUnsunkenBoat(int i, int j) {
		return playerOcean.lookAtTile(i, j).equals("H");
	}

	/**
	 * AI Logic easy.
	 *
	 * @return the coordinates as an int[]
	 */
	private int[] aiLogicEasy() {
		boolean validShot = false;

		while (!validShot) {
			generateRandomCoordinates();
			validShot = playerOcean.isValidShot(coordinates[0], coordinates[1]);
		}

		return coordinates;
	}

	/**
	 * Gets the direction as a string. Send in an int and this method will
	 * return it as the corresponding string.
	 *
	 * @param directionInt
	 *            the direction int
	 * @return the direction in string
	 */
	private String getDirectionInString(int directionInt) {
		switch (directionInt) {

		case 0:
			return "north";
		case 1:
			return "east";
		case 2:
			return "south";
		case 3:
			return "west";
		}
		return null;
	}

	/**
	 * Generate random coordinates. Let's the AI shoot at a random tile.
	 */
	private void generateRandomCoordinates() {

		int row = rand.nextInt(10);
		int column = rand.nextInt(10);
		coordinates[0] = row;
		coordinates[1] = column;

	}

	/**
	 * Generate random expert coordinates. Let's the AI shoot at coordinates in
	 * a grid pattern. It only shoots at every other tile - so to not need to
	 * shot at any tile.
	 */
	private void generateRandomExpertCoordinates() {
		boolean randomTileValid = false;

		while (!randomTileValid) {
			int row = rand.nextInt(10);

			int column = rand.nextInt(5) * 2;
			if (row % 2 == 1) {
				column++;
			}
			coordinates[0] = row;
			coordinates[1] = column;
			if (playerOcean.isValidShot(coordinates[0], coordinates[1])) {
				randomTileValid = true;
			}

		}
	}

	/**
	 * Generate AI ship coordinates for placing boats.
	 *
	 * @return the coordinates as an int[]
	 */
	public int[] generateAIShipCoordinates() {
		generateRandomCoordinates();
		return coordinates;
	}

	/**
	 * Generate AI ship direction for placing boats.
	 *
	 * @return the string
	 */
	public String generateAIShipDirection() {

		int direction = rand.nextInt(3);
		return getDirectionInString(direction);

	}
}
