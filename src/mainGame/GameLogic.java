package mainGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameLogic {

	BufferedReader br;
	Ocean playerOcean;
	Ocean aiOcean;
	InputFromPlayer input;
	AIlogic aiLogic;

	public static void main(String[] args) {

		GameLogic game = new GameLogic();
		game.initializeGame(game);
		game.runGame(game);

	}

	public void initializeGame(GameLogic game) {

		br = new BufferedReader(new InputStreamReader(System.in));
		playerOcean = new Ocean();
		aiOcean = new Ocean();
		input = new InputFromPlayer();
		aiLogic = new AIlogic(playerOcean, aiOcean);

	}

	public void runGame(GameLogic game) {
		// TODO: play again while loop here
		game.placeShips();
		game.playGame();

		// TODO: clear grid from boats after game
	}

	public void placeShips() {
		String boatCoordinatesString, boatDirection;
		String boatNames[];
		int boatCoorinatesInt[] = new int[2];
		String shipType[] = new String[] { null, null, "destroyer", "cruiser",
				"battleship", "aircraft carrier" };

		boolean boatPlaced = false;
		for (int i = 5; i > 1; i--) {
			boatPlaced = false;
			playerOcean.showOceanWithBoats();
			while (!boatPlaced) {
				System.out.println("Where do you want to place your "
						+ shipType[i] + "? (" + i + ")");

				boatCoordinatesString = input.getInput(br, 1);
				boatCoorinatesInt = input
						.changeCoordinatesToInt(boatCoordinatesString);
				boatDirection = input.getDirection(br);

				try {
					playerOcean.placeBoat(boatCoorinatesInt[0],
							boatCoorinatesInt[1], i, boatDirection);
					boatPlaced = true;
				} catch (IllegalArgumentException tileOccupied) {
					System.out.print("Can not place boat on top of anohter boat. ");
				} catch (IndexOutOfBoundsException boatOutsideGrid) {
					System.out.print("Boat outside of engagement zone. "); //TODO: better wording
				}
			}

			if (i == 3) {
				boatPlaced = false;
				playerOcean.showOceanWithBoats();
				while (!boatPlaced) {
					System.out
							.println("Where do you want to place your sumbmarine? ("
									+ i + ")");
					boatCoordinatesString = input.getInput(br, 1);
					boatCoorinatesInt = input
							.changeCoordinatesToInt(boatCoordinatesString);
					boatDirection = input.getDirection(br);

					try {
						playerOcean.placeBoat(boatCoorinatesInt[0],
								boatCoorinatesInt[1], i, boatDirection);
						boatPlaced = true;
					} catch (IllegalArgumentException tileOccupied) {
						System.out.print("Can not place boat on top of anohter boat. ");
					} catch (IndexOutOfBoundsException boatOutsideGrid) {
						System.out.print("Boat outside of engagement zone. "); //TODO: better wording
					}
				}
			}
		}

		aiLogic.placeAIShips();
	}

	public void playGame() {
		int currentPlayer = 1; // 1 = player, 0 = AI
		String validInput;
		int[] coordinates = new int[2];
		boolean allBoatsShotDown = false;

		while (!allBoatsShotDown) {
			// currentPlayer++
			// if (currentPlayer == 2){currentPlayer = 0}

			if (currentPlayer == 1) { // get input from player
				boolean coordinatesSelected = false;

				while (!coordinatesSelected) {
					System.out.println("Where do you want to shoot?");
					validInput = input.getInput(br, 0);
					coordinates = input.changeCoordinatesToInt(validInput);

					if (validInput.equals("exit")) {
						System.exit(0); // TODO: change?
					} else if (validInput.equals("show")) {
						playerOcean.showOceanWithBoats();
					} else {
						coordinates = input.changeCoordinatesToInt(validInput);
						coordinatesSelected = true;
					}

				} // while coordinatesSelected

				// TODO: change to AI ocean

				playerOcean.shoot(coordinates[0], coordinates[1]);
				playerOcean.showOcean();

			}
			if (currentPlayer == 0) {
				// TODO: add AIlogic here
			}

		}

	}

}
