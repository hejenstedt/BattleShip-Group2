package mainGame;

public class AIlogic {

	private Ocean playerOcean, aiOcean;
	
	public AIlogic(Ocean playerOcean, Ocean aiOcean) {
		this.aiOcean = aiOcean;
		this.playerOcean = playerOcean;
	}

	public String generateAIcoordinates(int difficulty) {

		String coordinates = null;
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
			coordinates = "a0"; // default. Remove?

		}

		return coordinates;
	}

	private String aiLogicHard() {
		// TODO Auto-generated method stub
		return null;
	}

	private String aiLogicMedium() {
		// TODO Auto-generated method stub
		return null;
	}

	private String aiLogicEasy() {
		// TODO Auto-generated method stub
		return null;
	}

	public void placeAIShips() {
		// TODO Auto-generated method stub
		
	}
}
