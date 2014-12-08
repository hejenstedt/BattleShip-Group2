/*
 * 
 */
package mainGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// TODO: Auto-generated Javadoc
/**
 * The Class Tile.
 */
public class Tile {

	/** The boat exists on tile. */
	private boolean boatExistsOnTile;
	
	/** The tile has been shoot at. */
	private boolean tileHasBeenShootAt;
	
	/** The boat. */
	private Boat boat;

	/**
	 * Gets the boat.
	 *
	 * @return the boat
	 */
	public Boat getBoat() {
		return boat;
	}

	/**
	 * Instantiates a new tile.
	 */
	public Tile() {
		tileHasBeenShootAt = false;
		boatExistsOnTile = false;
	}

	/**
	 * Sets the boat on tile.
	 *
	 * @param boat the new boat on tile
	 */
	public void setBoatOnTile(Boat boat) {
		this.boat = boat;
		boatExistsOnTile = true;
	}

	/**
	 * Checks if is boat on tile.
	 *
	 * @return true, if is boat on tile
	 */
	public boolean isBoatOnTile() {
		return boatExistsOnTile;
	}

	/**
	 * Shoot at tile.
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
	 * Tile showing boat.
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
	 * @param fileName the file name
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
