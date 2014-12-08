package mainGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Class Tile.
 */
public class Tile {

	private boolean boatExistsOnTile;
	private boolean tileHasBeenShootAt;
	private Boat boat;

	/**
	 * Instantiates a new tile.
	 */
	public Tile() {
		tileHasBeenShootAt = false;
		boatExistsOnTile = false;
	}

	public Boat getBoat() {
		return boat;
	}

	/**
	 * Sets the boatOnTile to true when a boat is placed on the tile.
	 *
	 * @param boat
	 *            the new boat on tile
	 */
	public void setBoatOnTile(Boat boat) {
		this.boat = boat;
		boatExistsOnTile = true;
	}

	/**
	 * Checks if there is a boat on the tile.
	 *
	 * @return true, if boat is on tile
	 */
	public boolean isBoatOnTile() {
		return boatExistsOnTile;
	}

	/**
	 * Shoot at tile.
	 * Plays the corresponding sound for hit or miss.
	 */
	public void shootAtTile() {
		tileHasBeenShootAt = true;
		if (boatExistsOnTile) {
			boat.setHit();
			this.playSound("kerboom.wav");
		} else {
			this.playSound("sploosh.wav");
		}
	}

	/**
	 * Tile has been shoot before.
	 *
	 * @return true, if successful
	 */
	public boolean tileHasBeenShootBefore() {
		return tileHasBeenShootAt;
	}

	@Override
	public String toString() {
		if (tileHasBeenShootAt) {

			if (boatExistsOnTile) {
				return boat.toString();
			}
			return "X";
		}
		return "~";
	}

	/**
	 * Tile returns the value of the boat if a boat exists on the tile. Otherwise it returns ~.
	 *
	 * @return the string
	 */
	public String tileShowingBoat() {

		if (boatExistsOnTile) {
			return "" + boat.getBoatLength();
		}
		return "~";
	}

	/**
	 * Play sound.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void playSound(String fileName) {
		String soundFile = "sounds\\" + fileName;
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(soundFile)));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
