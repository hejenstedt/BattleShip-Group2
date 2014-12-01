package mainGame;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import mainGame.InputFromPlayer;

import org.junit.Before;
import org.junit.Test;


public class InputFromPlayerTest {

	BufferedReader br;
	
	
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
	
	@Test
	public void testChangeCoordinatesToInt(){
		InputFromPlayer input = new InputFromPlayer();
		String userInput = input.getInput(br, 0);
		int[] coordinates = input.changeCoordinatesToInt(userInput);
		
		System.out.println("row " + coordinates[0] + " " + "column " + coordinates[1]);
	}

	
}
