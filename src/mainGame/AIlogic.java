package mainGame;

import java.util.Random;

public class AIlogic {

	private Ocean playerOcean, aiOcean;
	private Random rand;
	private int[] coordinates;

	public AIlogic(Ocean playerOcean, Ocean aiOcean) {
		this.aiOcean = aiOcean;
		this.playerOcean = playerOcean;
		rand = new Random();
	}

	public int[] generateAIcoordinates(int difficulty) {

		int[] coordinates = null;
		switch (difficulty) { // different logic depending on difficulty

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
		// TODO Auto-generated method stub
		return null;
	}

	private int[] aiLogicMedium() {
		// TODO Auto-generated method stub
		return null;
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
			return "south";
		case 2:
			return "east";
		case 3:
			return "west";
		}
		return null;
	}

	private void generateRandomCoordinates() {
		int row = rand.nextInt(10);
		int column = rand.nextInt(10);
		coordinates = new int[] { row, column };
	}

	public int[] generateAIShipCoordinates() {
		generateRandomCoordinates();
		return coordinates;
	}
	
	public String generateAIShipDirection(){
	
		int direction = rand.nextInt(3);
		return getDirectionInString(direction);
		
	}

	public void placeAIShips() {
		// TODO Auto-generated method stub
//		aiOcean.placeBoat(row, column, boatLength, direction);
	}
}
