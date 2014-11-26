package mainGame;

import java.io.BufferedReader;
import java.io.IOException;

public class InputFromPlayer {

	private boolean validCoordinates;
	private char validLetters[];
	private int validNumbers[];

	public InputFromPlayer() {
		validLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j' };
		validNumbers = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	}

	public String getInput(BufferedReader input) {
		validCoordinates = false;
		String inputFromUser = null;

		while (!validCoordinates) {
			try {
				inputFromUser = input.readLine().toLowerCase();
			} catch (IOException e) {
				System.out.println("can not get input");
			}

			if (correctLetter(inputFromUser.charAt(0))
					&& correctNumber(inputFromUser.charAt(1)))

				validCoordinates = true;
		}
		return inputFromUser;

	}

	private boolean correctLetter(char inputAt0) {
		for (char i : validLetters) {
			if (inputAt0 == i) {
				return true;
			}
		}
		return false;
	}

	private boolean correctNumber(int inputAt1) {
		for (int i : validNumbers) {
			if (inputAt1 == i) {
				return true;
			}
		}
		return false;
	}

}
