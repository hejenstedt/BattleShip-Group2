package mainGame;

/**
 * The Class Player.
 */
public class Player {

	private String name;
	private Ocean ocean;
	private boolean playerIsAI;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name of the player
	 */
	public Player(String name) {
		ocean = new Ocean();	
		playerIsAI = false;
		this.name= name;
	}
	
	/**
	 * Checks if is player an AI.
	 *
	 * @return true, if player is an AI
	 */
	public boolean isPlayerAnAI(){
		return playerIsAI;
	}
	
	public void setPlayerAsAI() {
		playerIsAI = true;
	}
	
	public String getName() {
		return name;
	}

	public Ocean getOcean() {
		return ocean;
	}

	public void setName(String name) {
		this.name = name;
		
	}

}
