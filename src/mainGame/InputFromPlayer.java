package mainGame;

import java.io.BufferedReader;
import java.io.IOException;

public class InputFromPlayer {

	private boolean validCoordinates;
	private char validLetters[];
	private char validNumbers[];
	private int coordinatesArray[];
	private String keywords[];

	public InputFromPlayer() {
		validLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j' };
		validNumbers = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };
		coordinatesArray = new int[2];
		keywords = new String[] { "exit", "show" };
	}

	public String getDirection(BufferedReader input) {
		String direction = null;
		boolean validDirection = false;

		System.out
				.println("what direction? ('north', 'south', 'east' or 'west')");

		while (!validDirection) {

			try {
				direction = input.readLine().toLowerCase();
			} catch (IOException e) {
				System.out.println("can not get input");
			}

			if (direction.equals("north") || direction.equals("south")
					|| direction.equals("east") || direction.equals("west")) {
				validDirection = true;
			} else {
				System.out
						.println("Invalid direction. Directions are: 'north', 'south', 'east' or 'west'");
			}

		}

		return direction;
	}

	public String getInput(BufferedReader input, int inputType) {
		validCoordinates = false;
		String inputFromUser = null;

		System.out.println("write coordinates:"); // TODO: Change text

		while (!validCoordinates) {

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

				else if (this.correctLetter(inputFromUser.charAt(1))
						|| this.correctNumber(inputFromUser.charAt(0))) {
					inputFromUser = "" + inputFromUser.charAt(1)
							+ inputFromUser.charAt(0);
					validCoordinates = true;
				}

				else {
					System.out
							.println("Invalid input. Coordinates are: 'letter' (A-J) and 'number' (0-9)");
					// TODO: change text
				}

			}

			else if (inputFromUser.equals(keywords[0])
					|| inputFromUser.equals(keywords[1])) {

				//TODO: What does below ifs mean? What is inputType 0 or 1??
				if (inputType == 0) {
					validCoordinates = true;
				} else if (inputType == 1 && inputFromUser.equals(keywords[0])) {
					System.exit(0);
				} else {
					System.out
							.println("Invalid input. Valid input is 'coordinates'");
				}
			}

			else {
				System.out
						.println("Invalid input. Valid input is 'coordinates', '"
								+ keywords[0] + "' or '" + keywords[1] + "'.");
			}

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

	private boolean correctNumber(char inputAt1) {
		for (char i : validNumbers) {
			if (inputAt1 == i) {
				return true;
			}
		}
		return false;
	}

	public int[] changeCoordinatesToInt(String coordinates) {

		int letterCount = 0, numberCount = 0;
		for (char i : validLetters) {
			if (coordinates.charAt(0) == i) {
				break;
			}
			letterCount++;
		}

		for (char j : validNumbers) {
			if (coordinates.charAt(1) == j) {
				break;
			}
			numberCount++;
		}

		coordinatesArray[0] = numberCount;
		coordinatesArray[1] = letterCount;
		return coordinatesArray;
	}

	public int[] getUserInputForCoordinates(BufferedReader br, int inputType){
		return changeCoordinatesToInt(getInput(br, inputType));
	}
	
}
