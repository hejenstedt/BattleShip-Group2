package mainGame;

/**
 * The Class Boat.
 */
public class Boat {

	private boolean[] boatShotAt;
	private int boatLength;
	private String name;

	/**
	 * Instantiates a new boat, taking length and name as parameters.
	 *
	 * @param length
	 *            - the length of the boat
	 * @param name
	 *            - the name of the boat
	 */
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

	/**
	 * If boat is sinked returns true.
	 *
	 * @return true, if successful
	 */
	public boolean boatSinked() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * If the boat is shot adds a hit to the boatShotAt int[].
	 */
	public void setHit() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				boatShotAt[i] = true;
				break;
			}
		}
	}

	/**
	 * ToString returns H as long as the boat is hit but not sunk. If sunk it
	 * returns the length of boat.
	 */
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
