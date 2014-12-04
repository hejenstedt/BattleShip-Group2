package mainGame;

public class Boat {
	private boolean[] boatShotAt;
	private int boatLength;
	private String name;

	public Boat(int length, String name) {
		this.name = name;
		boatShotAt = new boolean[length];
		boatLength = length;
		for (int i = 0; i < boatShotAt.length; i++) {
			boatShotAt[i] = false;
		}

	}

	public int getBoatLength() {
		return boatLength;
	}

	public boolean boatSinked() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				return false;
			}
		}
		return true;
	}

	public void setHit() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				boatShotAt[i] = true;
				break;
			}
		}
	}

	public String toString() {
		if (boatSinked()) {
			return "" + boatShotAt.length;
		}
		return "H";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
