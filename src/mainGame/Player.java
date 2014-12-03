package mainGame;

public class Player {

	private String name;
	private Ocean ocean;
	private boolean playerIsAI;
	
	public Player(String name) {
		ocean = new Ocean();	
		playerIsAI = false;
		this.name= name;
	}
	
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
