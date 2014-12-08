/*
 * 
 */
package mainGame;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import mainGame.InputFromPlayer;

import org.junit.Before;
import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class InputFromPlayerTest.
 */
public class InputFromPlayerTest {

	/** The br. */
	BufferedReader br;
	
	
	/**
	 * Creates the objects needed.
	 */
	@Before
	public void createObjectsNeeded(){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/*
	@Test
	public void testValidInput() {
		InputFromPlayer input = new InputFromPlayer();
		String userInput = input.getInput(br);
		
		assert(userInput.equals("a1"));
		
	}*/
	
	/**
	 * Test change coordinates to int.
	 */
	@Test
	public void testChangeCoordinatesToInt(){
		InputFromPlayer input = new InputFromPlayer();
		String userInput = input.getInput(br, 0);
		int[] coordinates = input.changeCoordinatesToInt(userInput);
		
		System.out.println("row " + coordinates[0] + " " + "column " + coordinates[1]);
	}

	
}
