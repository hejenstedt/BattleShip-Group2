package mainGame;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClass {
	public void playSound(String fileName) {
		//URL soundFile = getClass().getResource("sounds/"+fileName);
		//	"sounds\\" + fileName;
		//fileName = "buzzer.wav";
		try {
			System.out.println("method");
		
		
			InputStream audioSrc = getClass().getResourceAsStream("/sounds/"+fileName);
			//add buffer for mark/reset support
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
			
			
			  Clip crit = AudioSystem.getClip();
		         crit.open(audioStream);
		        crit.start();
		        System.out.println("method");
			
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		
			
	}
}
