package mainGame;

import java.io.BufferedReader;
import java.io.IOException;

public class InputFromPlayer {

	private boolean validCoordinates;
	private char validLetters[];
	private char validNumbers[];

	public InputFromPlayer() {
		validLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j' };
		validNumbers = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	}

	public String getInput(BufferedReader input) {
		validCoordinates = false;
		String inputFromUser = null;
		while (!validCoordinates) {
			
			System.out.println("write coordinates:"); //TODO: Change text
			
			try {
				inputFromUser = input.readLine().toLowerCase();
			} catch (IOException e) {
				System.out.println("can not get input");
			}

			if (inputFromUser.length() == 2) {
				if (this.correctLetter(inputFromUser.charAt(0))
						&& this.correctNumber(inputFromUser.charAt(1))) {
					validCoordinates = true;
				}
			}
		}
		return inputFromUser;

	}

	public boolean correctLetter(char inputAt0) {
		for (char i : validLetters) {
			if (inputAt0 == i) {
				return true;
			}
		}
		return false;
	}

	public boolean correctNumber(char inputAt1) {
		for (char i : validNumbers) {
			if (inputAt1 == i) {
				return true;
			}
		}
		return false;
	}

}
