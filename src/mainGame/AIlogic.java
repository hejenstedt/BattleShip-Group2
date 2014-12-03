package mainGame;

import java.util.Random;

public class AIlogic {

	private Ocean playerOcean;
	private Random rand;
	private int[] coordinates;
	boolean expertGoesForRows;

	public AIlogic(Ocean playerOcean) {
		this.playerOcean = playerOcean;
		rand = new Random();
		coordinates = new int[2];
		expertGoesForRows = rand.nextBoolean();
	}

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

	private int[] aiLogicHard() {
		Tile[][] playersOcean = playerOcean.getOcean();
		boolean direction = rand.nextBoolean();
		// check playerOcean if there are any visible unsunken ships (H)
		for (int i = 0; i < playersOcean.length; i++) {
			
			
			for (int j = 0; j < playersOcean[0].length; j++) {
				
				
				findsBoat:
				if (playerOcean.lookAtTile(i, j).equals("H")) {
					
					
					if (j < 9 && playerOcean.lookAtTile(i, j+1).equals("H")){
						break findsBoat;
					}
					
					if (i < 9 && playerOcean.lookAtTile(i+1, j).equals("H")){
						i++;
						j--;
						break findsBoat;
					}
					

					for (int k = 0; k < 4; k++) {

						if (expertGoesForRows) {

							if (direction) {
								if (playerOcean.isValidShot(i-1, j)) {
									coordinates[0] = i-1;
									coordinates[1] = j;
									return coordinates;
								}
								direction = false;
							} else {
								if (playerOcean.isValidShot(i+1, j)) {
									coordinates[0] = i+1;
									coordinates[1] = j;
									return coordinates;
								}
								direction = true;
							}

						}

						else { // horizontal

							if (direction) {
								if (playerOcean.isValidShot(i, j-1)) {
									coordinates[0] = i;
									coordinates[1] = j-1;
									return coordinates;
								}
								direction = false;
							} else {
								if (playerOcean.isValidShot(i, j+1)) {
									coordinates[0] = i;
									coordinates[1] = j+1;
									return coordinates;
								}
							}
							direction = true;
						}

						if (k == 1){ // after second time in loop
							if (expertGoesForRows){
								expertGoesForRows = false;
							}
							else{
								expertGoesForRows = true;
							}
						}
						
					}// k-koop
				}
			}
		}

		coordinates = generateRandomExpertCoordinates();
		return coordinates;
	}

	private boolean isClockwise() {
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

	private int[] aiLogicMedium() {
		Tile[][] playersOcean = playerOcean.getOcean();
		// check playerOcean if there are any visible unsunken ships (H)
		for (int i = 0; i < playersOcean.length; i++) {
			for (int j = 0; j < playersOcean[0].length; j++) {
				if (playerOcean.lookAtTile(i, j).equals("H")) {
					boolean isClockWise = isClockwise();
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

						if (isClockWise) {
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

					// GEnerate startdirection

					// If so - go in all directions to see if there are an unHit
					// tile (boolean !tileHasBeenShotAt)
					// generate clockwise/countclockwise

					// if so shoot at that tile

				}

			}
		}
		// else shoot random
		generateRandomCoordinates();
		return coordinates;

	}

	private int[] aiLogicEasy() {
		boolean validShot = false;
		coordinates = null;

		while (!validShot) {
			generateRandomCoordinates();
			validShot = playerOcean.isValidShot(coordinates[0], coordinates[1]);
		}

		return coordinates;
	}

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

	private void generateRandomCoordinates() {
		int row = rand.nextInt(10);
		int column = rand.nextInt(10);
		coordinates[0] = row;
		coordinates[1] = column;
	}

	private int[] generateRandomExpertCoordinates() {
		int row = rand.nextInt(10);

		int column = rand.nextInt(5) * 2;
		if (row % 2 == 1) {
			column++;
		}
		coordinates[0] = row;
		coordinates[1] = column;
		return coordinates;
	}

	public int[] generateAIShipCoordinates() {
		generateRandomCoordinates();
		return coordinates;
	}

	public String generateAIShipDirection() {

		int direction = rand.nextInt(3);
		return getDirectionInString(direction);

	}

	public void placeAIShips() {
		// TODO Auto-generated method stub
		// aiOcean.placeBoat(row, column, boatLength, direction);
	}
}
