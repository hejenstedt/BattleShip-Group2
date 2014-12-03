package mainGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

	BufferedReader br;
	InputFromPlayer input;
	AIlogic aiLogic;
	ArrayList<Player> players;
	Player player1;
	Player player2;
	int difficulty = 0;

	public static void main(String[] args) {

		GameLogic game = new GameLogic();
		game.initializeGame(game);

		game.getSettings();
		game.runGame(game);

	}

	public void initializeGame(GameLogic game) {

		br = new BufferedReader(new InputStreamReader(System.in));

		players = new ArrayList<Player>();
		player1 = new Player("Player");
		player2 = new Player("AI");
		player2.setPlayerAsAI();

		players.add(player1);
		players.add(player2);

		input = new InputFromPlayer();
		aiLogic = new AIlogic(player1.getOcean(), player2.getOcean());

	}

	public void runGame(GameLogic game) {
		game.placeShips();
		game.playGame();

		// TODO: clear grid from boats after game
	}

	public void getSettings() {
		System.out.println("What is your name?");
		try {
			String name = br.readLine();
			player1.setName(name);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out
				.println("What difficulty do you want to play against? (0 = Easy, 1 = Medium, 2 = Hard");

		boolean validDifficultyInput = false;

		while (!validDifficultyInput) {
			try {

				String difficultySelection = br.readLine();

				if (difficultySelection.equals("0")
						|| difficultySelection.equals("1")
						|| difficultySelection.equals("2")) {
					difficulty = Integer.parseInt(difficultySelection);
					validDifficultyInput = true;
				} else {
					System.out
							.println("Invalid choise, choose '0', '1' or '2'");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // while end
		
		Random rand = new Random();
		int aiNameID = rand.nextInt(10);
		
		switch (aiNameID){
		
		case 0: player2.setName("Isoroku Yamamoto"); break;
		case 1: player2.setName("Erich von Manstein"); break;
		case 2: player2.setName("Omar Bradley"); break;
		case 3: player2.setName("Henry Arnold"); break;
		case 4: player2.setName("Georgy Zhukov"); break;
		case 5: player2.setName("Bernard Montgomery"); break;
		case 6: player2.setName("Douglas MacArthur"); break;
		case 7: player2.setName("Dwight D. Eisenhower"); break;
		case 8: player2.setName("Erwin Rommel"); break;
		case 9: player2.setName("George S. Patton"); break;	
		default: break;
		
		}
		

	}

	public void placeShips() {
		String boatCoordinatesString, boatDirection;
		String boatNames[];
		int boatCoorinatesInt[] = new int[2];
		String shipType[] = new String[] { null, null, "destroyer", "cruiser",
				"battleship", "aircraft carrier" };
		for (Player currentPlayer : players) {

			boolean boatPlaced = false;
			for (int i = 5; i > 1; i--) {
				placeOneShip(shipType, currentPlayer, i);

				if (i == 3) {
					placeOneShip(shipType, currentPlayer, i);
				}
			}

		}
	}

	private void placeOneShip(String[] shipType, Player currentPlayer, int i) {
		String boatCoordinatesString;
		String boatDirection;
		int[] boatCoorinatesInt;
		boolean boatPlaced;
		boatPlaced = false;

		if (!currentPlayer.isPlayerAnAI()) { // only shows if player is not AI
			currentPlayer.getOcean().showOceanWithBoats();
		}
		// TODO: with boats for player, without for AI
		// Can we place a method in the Player class that shows the
		// ocean with boats if it is a player and without if it is an AI
		while (!boatPlaced) {

			if (!currentPlayer.isPlayerAnAI()) { // only prints if player is not
													// AI
				System.out.println(currentPlayer.getName()
						+ ", Where do you want to place your " + shipType[i]
						+ "? (" + i + ")");
			}

			if (currentPlayer.isPlayerAnAI()) {
				boatCoorinatesInt = aiLogic.generateAIShipCoordinates();
				boatDirection = aiLogic.generateAIShipDirection();
			} else {

				boatCoordinatesString = input.getInput(br, 1);
				boatCoorinatesInt = input
						.changeCoordinatesToInt(boatCoordinatesString);
				// TODO: change so that the int[] is returned at once
				boatDirection = input.getDirection(br);
			}

			try {
				currentPlayer.getOcean().placeBoat(boatCoorinatesInt[0],
						boatCoorinatesInt[1], i, boatDirection);
				boatPlaced = true;
			} catch (IllegalArgumentException tileOccupied) {
				if (!currentPlayer.isPlayerAnAI()) {

					System.out
							.print("Can not place boat on top of another boat. ");
				}
			} catch (IndexOutOfBoundsException boatOutsideGrid) {
				if (!currentPlayer.isPlayerAnAI()) {

					System.out.print("Boat outside of engagement zone. ");
				}
				// TODO: better wording
			}
		}
	}

	public void playGame() {
		int currentPlayer = 1;
		// 1 = player, 0 = AI
		// TODO: change to boolean method called "isPlayerAI"
		boolean allBoatsShotDown = false;
		int[] coordinates = null;

		while (!allBoatsShotDown) {


			if (currentPlayer == 1) {
				System.out.println("\n"+player1.getName() + "s turn");
				// get input from player

				coordinates = this.getPlayerCoordinates();
				// TODO: better output when shooting

			}
			if (currentPlayer == 0) {
				System.out.println("\n"+player2.getName() + "s turn");
				// get coordinates from AI
				coordinates = aiLogic.generateAIcoordinates(difficulty);

				System.out.print("Thinking");
				for (int i = 0; i < 10; i++) {
					this.sleep(100);
					System.out.print(".");
				}
				System.out.println(" ");

			}

			// shots at the other players ocean (if currentPlayer is 1, shoots
			// at AI ocean)
			players.get(currentPlayer).getOcean()
					.shoot(coordinates[0], coordinates[1]);
			players.get(currentPlayer).getOcean().showOcean();
			
			this.sleep(700);

			currentPlayer++;
			if (currentPlayer == 2) {
				currentPlayer = 0;
			}

		}

	}

	public int[] getPlayerCoordinates() {
		boolean coordinatesSelected = false;
		int[] coordinates = new int[2];

		while (!coordinatesSelected) {

			String validInput;
			System.out.println("Where do you want to shoot?");
			validInput = input.getInput(br, 0);
			coordinates = input.changeCoordinatesToInt(validInput);

			if (validInput.equals("exit")) {
				System.exit(0);
				// TODO: change to equals no matter case?
			} else if (validInput.equals("show")) {
				player1.getOcean().showOceanWithBoats();
			} else {
				coordinates = input.changeCoordinatesToInt(validInput);
				coordinatesSelected = true;
			}

		} // while coordinatesSelected

		if (player1.getOcean().isValidShot(coordinates[0], coordinates[1])) {
			// TODO: change to AI
		} else {
			System.out.print("Tile already shot at. ");
		}
		return coordinates;
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
