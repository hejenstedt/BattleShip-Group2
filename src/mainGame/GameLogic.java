package mainGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Class GameLogic.
 */
public class GameLogic {

	private BufferedReader br;
	private InputFromPlayer input;
	private AILogic aiLogic;
	private ArrayList<Player> players;
	private Player playerHuman;
	private Player playerAI;
	private int difficulty = 0;

	/**
	 * The main method. Starts the game and when game is finished returns here
	 * to start again.
	 *
	 */
	public static void main(String[] args) {

		GameLogic game = new GameLogic();
		game.initializeGame(game);

		boolean playingGame = true;

		while (playingGame) {
			game.getSettings();
			game.runGame(game);

		}
	}

	/**
	 * Run game. Places the ships and starts the game itself.
	 *
	 */
	public void runGame(GameLogic game) {
		game.placeShips();
		game.playGame();

	}

	/**
	 * Initialize game, initializes the Players and AILogic..
	 *
	 */
	public void initializeGame(GameLogic game) {

		br = new BufferedReader(new InputStreamReader(System.in));

		players = new ArrayList<Player>();
		playerHuman = new Player("Player");
		playerAI = new Player("AI");
		playerAI.setPlayerAsAI();

		players.add(playerHuman);
		players.add(playerAI);

		input = new InputFromPlayer();
		aiLogic = new AILogic(playerHuman.getOcean());

	}

	/**
	 * Requests player for input of settings for the game. Name, difficulty of
	 * AI.
	 */
	public void getSettings() {
		System.out.println("What is your name?");
		try {
			String name = br.readLine();
			playerHuman.setName(name);
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
							.println("Invalid choise, choose difficulty: '0', '1' or '2'");
				}
			} catch (IOException e) {
				System.out
						.println("Invalid choise, choose difficulty: '0', '1' or '2'");
				validDifficultyInput = false;
			}
		} // while end

		// ------------------- name AI --------------------------
		givaAIRandomName();
		// -----------------------------------------------------------------

	}

	/**
	 * Gives AI player a random name.
	 */
	private void givaAIRandomName() {
		Random rand = new Random();
		int aiNameID = rand.nextInt(10);

		switch (aiNameID) {

		case 0:
			playerAI.setName("Japaneese Naval Marshal General Isoroku Yamamoto");
			break;
		case 1:
			playerAI.setName("German General Erich von Manstein");
			break;
		case 2:
			playerAI.setName("US General Omar Bradley");
			break;
		case 3:
			playerAI.setName("US General Henry Arnold");
			break;
		case 4:
			playerAI.setName("Soviet General Georgy Zhukov");
			break;
		case 5:
			playerAI.setName("Brittish General Bernard Montgomery");
			break;
		case 6:
			playerAI.setName("US General Douglas MacArthur");
			break;
		case 7:
			playerAI.setName("General Dwight D. Eisenhower");
			break;
		case 8:
			playerAI.setName("German Field Marshal Erwin Rommel");
			break;
		case 9:
			playerAI.setName("US General George S. Patton");
			break;
		default:
			break;
		}
	}

	/**
	 * Place ships in the ocean.
	 */
	public void placeShips() {

		String shipType[] = new String[] { null, null, "destroyer", "cruiser",
				"battleship", "aircraft carrier" };
		for (Player currentPlayer : players) {

			for (int i = 5; i > 1; i--) {
				placeOneShip(shipType[i], currentPlayer, i);

				if (i == 3) {
					placeOneShip("Submarine", currentPlayer, i);
				}
			}

		}
	}

	/**
	 * Place one ship. If currentplayer is a Human, this will show output and
	 * request input. If currentplayer is an AI it will get coordinates from the
	 * AILogic class.
	 *
	 * @param shipType
	 *            - the boat name from placeShips()
	 * @param currentPlayer
	 *            the current player - Human or AI
	 * @param i
	 *            the length of the boat
	 */
	private void placeOneShip(String shipType, Player currentPlayer, int i) {
		String boatDirection;
		int[] boatCoorinatesInt;
		boolean boatPlaced;
		boatPlaced = false;

		if (!currentPlayer.isPlayerAnAI()) {
			// only shows if player is not AI
			currentPlayer.getOcean().showOceanWithBoats();
		}
		// TODO: with boats for player, without for AI
		while (!boatPlaced) {

			if (!currentPlayer.isPlayerAnAI()) {
				// only prints if player is not AI
				System.out.println(currentPlayer.getName()
						+ ", Where do you want to place your " + shipType
						+ "? (" + i + ")");

				boatCoorinatesInt = input.getUserInputForCoordinates(br, 1);
				boatDirection = input.getDirection(br);
			} else {

				boatCoorinatesInt = aiLogic.generateAIShipCoordinates();
				boatDirection = aiLogic.generateAIShipDirection();

			}

			try {
				currentPlayer.getOcean().placeBoat(boatCoorinatesInt[0],
						boatCoorinatesInt[1], i, boatDirection, shipType);
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

	/**
	 * Play game. Loops as long as there exists unsunken boats. Requests
	 * coordinates from the player, shoots at it and hands over to the next
	 * player.
	 */
	public void playGame() {
		boolean allBoatsShotDown = false;
		int[] coordinates = null;
		Player currentPlayer = playerHuman;
		Player opposingPlayer = playerAI;
		while (!allBoatsShotDown) {

			if (currentPlayer == playerHuman) {
				System.out.println("\n" + currentPlayer.getName() + "s turn");
				// get input from player
				opposingPlayer.getOcean().showOcean();
				coordinates = this.getPlayerCoordinates();
				// TODO: better output when shooting
			}
			if (currentPlayer == playerAI) {
				System.out.println("\n" + currentPlayer.getName() + "s turn");
				// get coordinates from AI
				coordinates = aiLogic.generateAIcoordinates(difficulty);

				System.out.print("Thinking");
				for (int i = 0; i < 10; i++) {
					this.sleep(100);
					System.out.print(".");
				}
				System.out.println(" ");

			}

			opposingPlayer.getOcean().shoot(coordinates[0], coordinates[1]);
			opposingPlayer.getOcean().showOcean();

			if (currentPlayer.isPlayerAnAI()) {
				// if board is playerboard
				this.aiTextOutput(coordinates[0], coordinates[1]);
			}

			this.sleep(700);

			if (opposingPlayer.getOcean().getBoatsInOcean() == 0) {
				allBoatsShotDown = true;
				break;
			}

			currentPlayer = nextPlayer(currentPlayer);
			opposingPlayer = nextPlayer(opposingPlayer);

		} // allBoatsShotDown

		playerHuman.getOcean().cleanOcean();
		playerAI.getOcean().cleanOcean();
		// game ends

		System.out.println(currentPlayer.getName() + " wins!");

		System.out.print("Restarting game");
		for (int i = 0; i < 10; i++) {
			this.sleep(100);
			System.out.print(".");
		}
		System.out.println("\nStarting new game\n");

	}

	/**
	 * Next player.
	 *
	 * @param currentPlayerFromList
	 *            the current player from list
	 * @return the player that is up next.
	 */
	private Player nextPlayer(Player currentPlayerFromList) {
		int next = players.indexOf(currentPlayerFromList) + 1;
		if (next == 2) {
			next = 0;
		}
		return players.get(next);
	}

	/**
	 * AI text output, when AI has shot on a Tile..
	 *
	 * @param i
	 *            the row
	 * @param j
	 *            the column
	 */
	private void aiTextOutput(int i, int j) {

		System.out.print(playerAI.getName() + " shot at "
				+ this.coordinateToChar(j) + i + ". ");

		if (playerHuman.getOcean().lookAtTile(i, j).equals("X")) {
			System.out.println("Miss");
		} else if (playerHuman.getOcean().lookAtTile(i, j).equals("H")) {
			System.out.println("Hit");
		} else {
			System.out.println("Your "
					+ playerHuman.getOcean().getTile(i, j).getBoat().getName()
					+ " has been destroyed");
		}
	}

	/**
	 * Supportmethod to convert the column coordinate to a char. To be able to
	 * show coordinates in format 1A in output. 
	 *
	 * @param i - the column
	 * @return the string 
	 */
	private String coordinateToChar(int i) {
		switch (i) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		case 7:
			return "H";
		case 8:
			return "I";
		case 9:
			return "J";

		}
		return "error";
	}

	/**
	 * Gets the player coordinates through input from Player.
	 *
	 * @return the player coordinates as int[]
	 */
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
			} else if (validInput.equals("show")) {
				playerHuman.getOcean().showOceanWithBoats();
			} else {
				coordinates = input.changeCoordinatesToInt(validInput);
			}
			if (playerAI.getOcean().isValidShot(coordinates[0], coordinates[1])) {
				coordinatesSelected = true;
			} else {
				System.out.print("Tile already shot at. ");
			}

		} // while coordinatesSelected

		return coordinates;
	}

	/**
	 * Sleep.
	 *
	 * @param millis
	 *            the millis
	 */
	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
