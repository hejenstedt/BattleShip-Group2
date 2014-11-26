import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import mainGame.InputFromPlayer;

import org.junit.Before;
import org.junit.Test;


public class ValidInputReturnsCharAndInt {

	BufferedReader br;
	
	
	@Before
	public void createObjectsNeeded(){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	@Test
	public void testValidInput() {
		InputFromPlayer input = new InputFromPlayer();
		String userInput = input.getInput(br);
		
		assert(userInput.equals("a1"));
		
	}


}
