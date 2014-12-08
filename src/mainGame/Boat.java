/*
 * 
 */
package mainGame;

// TODO: Auto-generated Javadoc
/**
 * The Class Boat.
 */
public class Boat {
	
	/** The boat shot at. */
	private boolean[] boatShotAt;
	
	/** The boat length. */
	private int boatLength;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new boat.
	 *
	 * @param length the length
	 * @param name the name
	 */
	public Boat(int length, String name) {
		this.name = name;
		boatShotAt = new boolean[length];
		boatLength = length;
		for (int i = 0; i < boatShotAt.length; i++) {
			boatShotAt[i] = false;
		}

	}

	/**
	 * Gets the boat length.
	 *
	 * @return the boat length
	 */
	public int getBoatLength() {
		return boatLength;
	}

	/**
	 * Boat sinked.
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
	 * Sets the hit.
	 */
	public void setHit() {
		for (int i = 0; i < boatShotAt.length; i++) {
			if (boatShotAt[i] == false) {
				boatShotAt[i] = true;
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (boatSinked()) {
			return "" + boatShotAt.length;
		}
		return "H";
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
