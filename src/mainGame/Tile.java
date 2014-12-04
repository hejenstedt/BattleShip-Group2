package mainGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Tile {

	private boolean boatExistsOnTile;
	private boolean tileHasBeenShootAt;
	private Boat boat;

	public Boat getBoat() {
		return boat;
	}

	public Tile() {
		tileHasBeenShootAt = false;
		boatExistsOnTile = false;
	}
	
	public void setBoatOnTile(Boat boat){
		this.boat= boat;
		boatExistsOnTile= true;
	}
	
	public boolean isBoatOnTile(){
		return boatExistsOnTile;
	}

	public void shootAtTile() {
		tileHasBeenShootAt = true;
		if (boatExistsOnTile) {
			boat.setHit();
			this.playSound("kerboom.wav");
		}
		else {
			this.playSound("sploosh.wav");
			
			
			
		}
	}

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

	public String tileShowingBoat() {
		
			if (boatExistsOnTile) {
				return ""+boat.getBoatLength();
			}
		return "~";
	}
	
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
