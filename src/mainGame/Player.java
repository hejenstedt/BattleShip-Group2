/*
 * 
 */
package mainGame;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player {

	/** The name. */
	private String name;
	
	/** The ocean. */
	private Ocean ocean;
	
	/** The player is ai. */
	private boolean playerIsAI;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	public Player(String name) {
		ocean = new Ocean();	
		playerIsAI = false;
		this.name= name;
	}
	
	/**
	 * Checks if is player an ai.
	 *
	 * @return true, if is player an ai
	 */
	public boolean isPlayerAnAI(){
		return playerIsAI;
	}
	
	/**
	 * Sets the player as ai.
	 */
	public void setPlayerAsAI() {
		playerIsAI = true;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the ocean.
	 *
	 * @return the ocean
	 */
	public Ocean getOcean() {
		return ocean;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
		
	}

}
